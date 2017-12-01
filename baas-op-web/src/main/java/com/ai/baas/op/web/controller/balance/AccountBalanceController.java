package com.ai.baas.op.web.controller.balance;

import com.ai.baas.amc.api.custbalancequery.interfaces.ICustBalanceQuerySV;
import com.ai.baas.amc.api.custbalancequery.param.UsableBalanceQueryRequest;
import com.ai.baas.amc.api.custbalancequery.param.UsableBalanceQueryResponse;
import com.ai.baas.amc.api.custbalancequery.param.UsableBalanceVo;
import com.ai.baas.op.web.constants.BaaSOPConstants;
import com.ai.baas.op.web.constants.Constants.ExcelConstants;
import com.ai.baas.op.web.util.CustGradeUtil;
import com.ai.net.xss.util.StringUtil;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.components.ccs.CCSClientFactory;
import com.ai.opt.sdk.components.excel.client.AbstractExcelHelper;
import com.ai.opt.sdk.components.excel.client.impl.HssfExcelHelper;
import com.ai.opt.sdk.components.excel.factory.ExcelFactory;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.web.model.ResponseData;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import com.ai.opt.sso.client.filter.SSOClientUser;
import com.ai.paas.ipaas.ccs.IConfigClient;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/balance")
public class AccountBalanceController {
    private static final Logger LOG = Logger.getLogger(AccountBalanceController.class);
    @RequestMapping("/toQueryBalance")
    public ModelAndView toQueryBalance(HttpServletRequest request) {

    	return new ModelAndView("jsp/balance/queryAccountBalance");
    }
    /**
     * 余额查询
     * @param request
     * @return
     */
    @RequestMapping("/getBalanceList")
    public ResponseData<PageInfo<UsableBalanceVo>> getList(HttpServletRequest request){
        HttpSession session = request.getSession();
        SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
        
        String custName = request.getParameter("custName");
        String custGrade = request.getParameter("custGrade");
        
        ICustBalanceQuerySV iBalanceQuerySV = DubboConsumerFactory.getService("iCustBalanceQuerySV");
        ResponseData<PageInfo<UsableBalanceVo>> responseData = null;
        UsableBalanceQueryRequest req = new UsableBalanceQueryRequest();
        req.setTenantId(user.getTenantId());
        if(!StringUtil.isBlank(custGrade)){
            req.setCustGrade(custGrade);
        }
        if(!StringUtil.isBlank(custName)){
            req.setCustName(custName);
        }
        PageInfo<UsableBalanceVo> pageInfo = new PageInfo<UsableBalanceVo> ();
        String strPageNo=(null==request.getParameter(BaaSOPConstants.PAGENO))?"1":request.getParameter(BaaSOPConstants.PAGENO);
        String strPageSize=(null==request.getParameter(BaaSOPConstants.PAGESIZE))?"10":request.getParameter(BaaSOPConstants.PAGESIZE);
        pageInfo.setPageNo(Integer.parseInt(strPageNo));
        pageInfo.setPageSize(Integer.parseInt(strPageSize));
        try {
            LOG.debug("余额查询入参:"+JSONObject.fromObject(req).toString());
            req.setPageInfo(pageInfo);
            UsableBalanceQueryResponse resultInfo = iBalanceQuerySV.queryUsableBalance(req);
            PageInfo<UsableBalanceVo> result= resultInfo.getPageInfo();
          //翻译客户等级
            if(result!=null){
              List<UsableBalanceVo> balance = result.getResult();
              if(!CollectionUtil.isEmpty(balance)){
                  for(UsableBalanceVo ba:balance){
                      String custGradename = CustGradeUtil.getCustLevelName(ba.getCustGrade());
                      ba.setCustGrade(custGradename);
                  }
              }
             }
            
            responseData = new ResponseData<PageInfo<UsableBalanceVo>>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功", result);
        } catch (Exception e) {
            responseData = new ResponseData<PageInfo<UsableBalanceVo>>(ResponseData.AJAX_STATUS_FAILURE, "查询失败");
            LOG.error("获取信息出错：", e);
        }
        return responseData;
    }
    
    /**
     * 导出
     * @param request
     */
    @RequestMapping("/exportBalanceList")
    public ResponseData<String> export(HttpServletRequest request, HttpServletResponse response){
    	
    	ICustBalanceQuerySV iBalanceQuerySV = DubboConsumerFactory.getService("iCustBalanceQuerySV");
		UsableBalanceQueryRequest req = new UsableBalanceQueryRequest();
		IConfigClient configClient = CCSClientFactory.getDefaultConfigClient();
		ResponseData<String> responseData = null;
    	try {
    		String custName = request.getParameter("custName");
            String custGrade = request.getParameter("custGrade");
            String strPageNo=(null==request.getParameter(BaaSOPConstants.PAGENO))?"1":request.getParameter(BaaSOPConstants.PAGENO);
            String strPageSize=(null==request.getParameter(BaaSOPConstants.PAGESIZE))?"10":request.getParameter(BaaSOPConstants.PAGESIZE);
            int totalCount = Integer.parseInt(strPageNo)*Integer.parseInt(strPageSize);
            int maxRow =  Integer.parseInt(configClient.get(ExcelConstants.EXCEL_OUTPUT_MAX_ROW));
			req.setCustName(custName);
    		req.setCustGrade(custGrade);
    		HttpSession session = request.getSession();
            SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
    		req.setTenantId(user.getTenantId());
    		PageInfo<UsableBalanceVo> pageInfo = new PageInfo<UsableBalanceVo> ();
            pageInfo.setPageNo(Integer.parseInt(strPageNo));
            pageInfo.setPageSize(totalCount);
            req.setPageInfo(pageInfo);
            LOG.debug("余额查询入参:"+JSONObject.fromObject(req).toString());
            UsableBalanceQueryResponse resultInfo = iBalanceQuerySV.queryUsableBalance(req);
            if(resultInfo!=null && resultInfo.getPageInfo()!=null){
            	if(totalCount<resultInfo.getPageInfo().getCount()){
            		totalCount = resultInfo.getPageInfo().getCount()<=maxRow?resultInfo.getPageInfo().getCount():maxRow;
            		pageInfo.setPageSize(totalCount);
            		req.setPageInfo(pageInfo);
            		resultInfo = iBalanceQuerySV.queryUsableBalance(req);
            	}
            }
            
            PageInfo<UsableBalanceVo> result = new PageInfo<UsableBalanceVo>();
            if(resultInfo!=null){
            	result= resultInfo.getPageInfo();
            }
            List<UsableBalanceVo> balance = new ArrayList<>();
            if(result!=null){
            	balance = result.getResult();
                if(!CollectionUtil.isEmpty(balance)){
                    for(UsableBalanceVo ba:balance){
                        String custGradename = CustGradeUtil.getCustLevelName(ba.getCustGrade());
                        ba.setCustGrade(custGradename);
                    }
                }
               }
            AbstractExcelHelper eh = ExcelFactory.getJxlExcelHelper();
            String[] titles = new String[]{"客户名称", "客户等级", "查询日期", "可用账户余额（元）"};
    		String[] fieldNames = new String[]{"custName", "custGrade", "queryTime", "usableAmount"};
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=ISO-8859-1");
			response.setCharacterEncoding("utf-8");
			response.setHeader("charset", "ISO-8859-1");
			String fileName = new String(("account_balance_" + System.currentTimeMillis())
					.getBytes(), "iso-8859-1"); 
			response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xls");
			eh.writeExcel(os, "账户余额信息", UsableBalanceVo.class, balance, fieldNames, titles);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, "查询失败");
            LOG.error("获取信息出错：", e);
		}catch (Exception e) {
			responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, "导出失败");
			LOG.error("获取信息出错：", e);
		}
    	return responseData;
    }
}
