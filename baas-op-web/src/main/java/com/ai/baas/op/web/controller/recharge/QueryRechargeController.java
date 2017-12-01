package com.ai.baas.op.web.controller.recharge;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ai.baas.amc.api.paymentquery.interfaces.IPaymentQuerySV;
import com.ai.baas.amc.api.paymentquery.param.PaymentLog;
import com.ai.baas.amc.api.paymentquery.param.PaymentLogQueryRequest;
import com.ai.baas.amc.api.paymentquery.param.PaymentLogQueryResponse;
import com.ai.baas.op.web.constants.BaaSOPConstants;
import com.ai.baas.op.web.constants.Constants.ExcelConstants;
import com.ai.baas.op.web.model.PaymentLogVo;
import com.ai.baas.op.web.model.recharge.PaymentLogExcel;
import com.ai.baas.op.web.util.AmountUtil;
import com.ai.baas.op.web.util.CustGradeUtil;
import com.ai.baas.op.web.util.DateUtil;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.components.ccs.CCSClientFactory;
import com.ai.opt.sdk.components.excel.client.AbstractExcelHelper;
import com.ai.opt.sdk.components.excel.factory.ExcelFactory;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.opt.sdk.web.model.ResponseData;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import com.ai.opt.sso.client.filter.SSOClientUser;
import com.ai.paas.ipaas.ccs.IConfigClient;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@RestController
@RequestMapping("/recharge")
public class QueryRechargeController {
    private static final Logger LOG = Logger.getLogger(QueryRechargeController.class);
    @RequestMapping("/toQueryCharge")
    public ModelAndView toQueryCharge(HttpServletRequest request) {
        IConfigClient configClient = CCSClientFactory.getDefaultConfigClient();
        ModelAndView view = null;
        try{
            String maxRow =  configClient.get(ExcelConstants.EXCEL_OUTPUT_MAX_ROW);
            request.setAttribute("maxRow", maxRow);
            view = new ModelAndView("jsp/recharge/queryRecharge");
        }catch(Exception e){
            LOG.error("获取配置信息出错：", e);  
        }
        return view;
    }
    /**
     * 充值缴费查询
     * @param request
     * @return
     */
    @RequestMapping("/getRechargeList")
    @ResponseBody
    public ResponseData<PageInfo<PaymentLog>> getList(HttpServletRequest request , PaymentLogVo queryInfo ){
        HttpSession session = request.getSession();
        SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
        IPaymentQuerySV iPaymentQuerySV = DubboConsumerFactory.getService("iPaymentQuerySV");
        PaymentLogQueryRequest req = new PaymentLogQueryRequest();
         if(!StringUtil.isBlank(queryInfo.getCustName())){
             req.setCustName(queryInfo.getCustName());
         }
         if(!StringUtil.isBlank(queryInfo.getCustGrade())){
             req.setCustGrade(queryInfo.getCustGrade());
         }
        req.setTenantId(user.getTenantId());
        if(!StringUtil.isBlank(queryInfo.getStartTime())){
            req.setStartTime(DateUtil.getTimestamp(queryInfo.getStartTime()));
        }
        if(!StringUtil.isBlank(queryInfo.getEndTime())){
            req.setEndTime(DateUtil.getTimestamp(queryInfo.getEndTime()));
        }
        if(!StringUtil.isBlank(queryInfo.getBottomAmount())){
            req.setBottomAmount( Double.parseDouble(queryInfo.getBottomAmount()));
        }
        if(!StringUtil.isBlank(queryInfo.getTopAmount())){
            req.setTopAmount( Double.parseDouble(queryInfo.getTopAmount()));
        }
        if(!StringUtil.isBlank(queryInfo.getPaySerialCode())){
            req.setPaySerialCode(queryInfo.getPaySerialCode());
        }
        ResponseData<PageInfo<PaymentLog>> responseData = null;
        queryInfo.setTenantId(user.getTenantId());
        PageInfo<PaymentLog> pageInfo = new PageInfo<PaymentLog> ();
        String strPageNo=(null==request.getParameter(BaaSOPConstants.PAGENO))?"1":request.getParameter(BaaSOPConstants.PAGENO);
        String strPageSize=(null==request.getParameter(BaaSOPConstants.PAGESIZE))?"10":request.getParameter(BaaSOPConstants.PAGESIZE);
        pageInfo.setPageNo(Integer.parseInt(strPageNo));
        pageInfo.setPageSize(Integer.parseInt(strPageSize));
        try {
            LOG.debug("充值缴费查询入参:"+JSONObject.fromObject(queryInfo).toString());
            req.setPageInfo(pageInfo);
            PaymentLogQueryResponse resultInfo = iPaymentQuerySV.queryPaymentLog(req);
            PageInfo<PaymentLog> result= resultInfo.getPageInfo();
            //翻译客户等级
            if(result!=null){
              List<PaymentLog> paylog = result.getResult();
              if(!CollectionUtil.isEmpty(paylog)){
                  for(PaymentLog log:paylog){
                      String custGradename = CustGradeUtil.getCustLevelName(log.getCustGrade());
                      log.setCustGrade(custGradename);
                  }
              }
             }
            LOG.debug("充值缴费查询出参:"+JSONArray.fromObject(resultInfo).toString());
            responseData = new ResponseData<PageInfo<PaymentLog>>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功", result);
        } catch (Exception e) {
            responseData = new ResponseData<PageInfo<PaymentLog>>(ResponseData.AJAX_STATUS_FAILURE, "查询失败");
            LOG.error("获取信息出错：", e);
        }
        return responseData;
    }
   //导出
    @RequestMapping("/exportExcel")
    public void download(HttpServletRequest request ,HttpServletResponse response,  PaymentLogVo queryInfo ){
        List<PaymentLogExcel> paymentExcelList = new ArrayList<PaymentLogExcel>();
        IConfigClient configClient = CCSClientFactory.getDefaultConfigClient();
        int pageCount = 0;
        try {
            PageInfo<PaymentLog> result = this.queryPament(request,queryInfo,"1","10");
            //获取总页数
             pageCount =  result.getPageCount();
             int totalCount = pageCount*10;
             //获取配置中的导出最大数值
            String maxRow =  configClient.get(ExcelConstants.EXCEL_OUTPUT_MAX_ROW);
            int excelMaxRow = Integer.valueOf(maxRow);
            if(totalCount>excelMaxRow){
                pageCount = excelMaxRow/10;  
            }
             if(pageCount!=0){
                while(pageCount!=0){
                    PageInfo<PaymentLog> subresult = this.queryPament(request,queryInfo,String.valueOf(pageCount),"10"); 
                    List<PaymentLog> paylog = subresult.getResult();
                    if(!CollectionUtil.isEmpty(paylog)){
                        for(PaymentLog log:paylog){
                            PaymentLogExcel paymentExcel = new PaymentLogExcel();
                            //翻译客户等级
                            String custGradename = CustGradeUtil.getCustLevelName(log.getCustGrade());
                            paymentExcel.setCustGrade(custGradename);
                            paymentExcel.setCustName(log.getCustName());
                            paymentExcel.setPayTime(log.getPayTime());
                            paymentExcel.setPeerSerialCode(log.getPeerSerialCode());
                            //转换金额
                            double orgFee = AmountUtil.changeL2Y(log.getTotalAmount());
                            paymentExcel.setTotalAmount(orgFee);
                            paymentExcelList.add(paymentExcel);
                        }
                    }
                    pageCount--;
                }
             }
             ServletOutputStream outputStream = response.getOutputStream();
             //获取查询时间
             String time = null;
             if(!StringUtil.isBlank(queryInfo.getStartTime())){
                 time = queryInfo.getStartTime().substring(0, 7);
             }else if(!StringUtil.isBlank(queryInfo.getEndTime())){
                 time = queryInfo.getEndTime().substring(0, 7);
             }else{
               //获取当前时间
                 Date now = new Date(); 
                 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");//可以方便地修改日期格式
                 String nowTime = dateFormat.format( now ); 
                 time=nowTime;
             }
            
             response.reset();// 清空输出流
             response.setContentType("application/msexcel");// 定义输出类型
             response.setHeader("Content-disposition", "attachment; filename=payment"+time+".xls");// 设定输出文件头
             String[] titles = new String[] { "缴费充值流水号","客户名称", "客户等级", "缴费充值日期", "缴费充值金额（元）"};
             String[] fieldNames = new String[] { "peerSerialCode","custName", "custGrade", "payTime","totalAmount"};
             AbstractExcelHelper excelHelper = ExcelFactory.getJxlExcelHelper();
             excelHelper.writeExcel(outputStream, "缴费充值信息"+new Date().getTime(), PaymentLogExcel.class, paymentExcelList,fieldNames, titles);
        } catch (Exception e) {
            LOG.error("导出文件出错：", e);
        }
    }
    
    //查询
    public PageInfo<PaymentLog> queryPament(HttpServletRequest request , PaymentLogVo queryInfo ,String strPageNo,String strPageSize){
        HttpSession session = request.getSession();
        SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
        IPaymentQuerySV iPaymentQuerySV = DubboConsumerFactory.getService("iPaymentQuerySV");
        PaymentLogQueryRequest req = new PaymentLogQueryRequest();
         if(!StringUtil.isBlank(queryInfo.getCustName())){
             req.setCustName(queryInfo.getCustName());
         }
         if(!StringUtil.isBlank(queryInfo.getCustGrade())){
             req.setCustGrade(queryInfo.getCustGrade());
         }
        req.setTenantId(user.getTenantId());
        if(!StringUtil.isBlank(queryInfo.getStartTime())){
            req.setStartTime(DateUtil.getTimestamp(queryInfo.getStartTime()));
        }
        if(!StringUtil.isBlank(queryInfo.getEndTime())){
            req.setEndTime(DateUtil.getTimestamp(queryInfo.getEndTime()));
        }
        if(!StringUtil.isBlank(queryInfo.getBottomAmount())){
            req.setBottomAmount( Double.parseDouble(queryInfo.getBottomAmount()));
        }
        if(!StringUtil.isBlank(queryInfo.getTopAmount())){
            req.setTopAmount( Double.parseDouble(queryInfo.getTopAmount()));
        }
        if(!StringUtil.isBlank(queryInfo.getPaySerialCode())){
            req.setPaySerialCode(queryInfo.getPaySerialCode());
        }
        queryInfo.setTenantId(user.getTenantId());
        PageInfo<PaymentLog> pageInfo = new PageInfo<PaymentLog> ();
        pageInfo.setPageNo(Integer.parseInt(strPageNo));
        pageInfo.setPageSize(Integer.parseInt(strPageSize));
        req.setPageInfo(pageInfo);
        PaymentLogQueryResponse resultInfo = iPaymentQuerySV.queryPaymentLog(req);
        return resultInfo.getPageInfo();
    }
    
}
