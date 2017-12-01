package com.ai.baas.op.web.controller.arrearage;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ai.baas.amc.api.oweinfoquery.interfaces.IOweInfoQuerySV;
import com.ai.baas.amc.api.oweinfoquery.param.ChargeDetailInfo;
import com.ai.baas.amc.api.oweinfoquery.param.OweDetailInfo;
import com.ai.baas.amc.api.oweinfoquery.param.OweDetailInfoQueryRequest;
import com.ai.baas.amc.api.oweinfoquery.param.OweDetailInfoQueryResponse;
import com.ai.baas.amc.api.oweinfoquery.param.OweInfo;
import com.ai.baas.amc.api.oweinfoquery.param.OweInfoListQueryRequest;
import com.ai.baas.amc.api.oweinfoquery.param.OweInfoListQueryResponse;
import com.ai.baas.op.web.constants.BaaSOPConstants;
import com.ai.baas.op.web.model.OweDescInfo;
import com.ai.baas.op.web.model.arrearage.OweDetailShowInfo;
import com.ai.baas.op.web.model.arrearage.OweGDetailShowInfo;
import com.ai.baas.op.web.model.arrearage.OweInfoListQueryVo;
import com.ai.baas.op.web.util.AmountUtil;
import com.ai.baas.op.web.util.CustGradeUtil;
import com.ai.baas.op.web.util.DateUtil;
import com.ai.baas.op.web.util.ExcelUtil;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.components.excel.client.AbstractExcelHelper;
import com.ai.opt.sdk.components.excel.factory.ExcelFactory;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.opt.sdk.web.model.ResponseData;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import com.ai.opt.sso.client.filter.SSOClientUser;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("/arrearage")
public class QueryArrearageController {
    private static final Logger LOG = Logger.getLogger(QueryArrearageController.class);
    
    @RequestMapping("/toQueryArrearage")
    public ModelAndView toQueryArrearage(HttpServletRequest request) {

    	return new ModelAndView("jsp/arrearage/queryArrearage");
    }
   
    /**
     * 欠费查询
     * @param request
     * @return
     */
    @RequestMapping("/getArrearegeList")
    public ResponseData<PageInfo<OweInfo>> getList(HttpServletRequest request , OweInfoListQueryVo queryInfo ){
       HttpSession session = request.getSession();
        ResponseData<PageInfo<OweInfo>> responseData = null;
        SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
        IOweInfoQuerySV iOweInfoQuerySV = DubboConsumerFactory.getService("iOweInfoQuerySV");
        OweInfoListQueryRequest realrequest = new OweInfoListQueryRequest();
        if(!StringUtil.isBlank(queryInfo.getCustName())){
         realrequest.setCustName(queryInfo.getCustName());
        }
       if(!StringUtil.isBlank(queryInfo.getCustGrade())){
         realrequest.setCustGrade(queryInfo.getCustGrade());
       }
        realrequest.setTenantId(user.getTenantId());
        if(!StringUtil.isBlank(queryInfo.getStartDate())){
            //截取数据
            String sDate =  queryInfo.getStartDate();
            sDate=sDate.replace("-",""); 
            realrequest.setStartDate(sDate);
         }
        if(!StringUtil.isBlank(queryInfo.getEndDate())){
            //修改欠费时间格式
            String eDate =  queryInfo.getEndDate();
            eDate=eDate.replace("-",""); 
            realrequest.setEndDate(eDate);
          }
       
        if(!StringUtil.isBlank(queryInfo.getBottomAmount())){
            realrequest.setBottomAmount( Double.parseDouble(queryInfo.getBottomAmount()));
        }
        if(!StringUtil.isBlank(queryInfo.getTopAmount())){
            realrequest.setTopAmount( Double.parseDouble(queryInfo.getTopAmount()));
        }
        PageInfo<OweInfo> pageInfo = new PageInfo<OweInfo> ();
        String strPageNo=(null==request.getParameter(BaaSOPConstants.PAGENO))?"1":request.getParameter(BaaSOPConstants.PAGENO);
        String strPageSize=(null==request.getParameter(BaaSOPConstants.PAGESIZE))?"10":request.getParameter(BaaSOPConstants.PAGESIZE);
        pageInfo.setPageNo(Integer.parseInt(strPageNo));
        pageInfo.setPageSize(Integer.parseInt(strPageSize));
        try {
            realrequest.setPageInfo(pageInfo);
            LOG.error("欠费查询入参:"+JSONObject.fromObject(realrequest).toString());
            OweInfoListQueryResponse resultInfo = iOweInfoQuerySV.queryOweInfoList(realrequest);
            PageInfo<OweInfo> result= resultInfo.getPageInfo();
            if(result!=null){
                //翻译欠费开始时间
                List<OweInfo> list = result.getResult();
                if(!CollectionUtil.isEmpty(list)){
                    for(OweInfo info:list){
                        String time = info.getUnsettledMonth(); 
                        if(!StringUtil.isBlank(time)){
                            String year = time.substring(0, 4);
                            String month = time.substring(4);
                            String unableTime = year+"年"+month+"月";
                            info.setUnsettledMonth(unableTime);
                        }
                       //翻译客户等级
                        String custGradename = CustGradeUtil.getCustLevelName( info.getCustGrade());
                        info.setCustGrade(custGradename);
                    } 
                } 
            }
            responseData = new ResponseData<PageInfo<OweInfo>>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功", result);
        } catch (Exception e) {
            responseData = new ResponseData<PageInfo<OweInfo>>(ResponseData.AJAX_STATUS_FAILURE, "查询失败");
            LOG.error("获取信息出错：", e);
        }
        return responseData;
    }
    
    @RequestMapping("/toDtailArrearage")
    public ModelAndView toDtailArrearage(HttpServletRequest request,@RequestParam(value = "custId", required = false) String custId) {
    	Map<String,String> model = new HashMap<String,String>();
    	model.put("custId", custId);
    	return new ModelAndView("jsp/arrearage/queryArrearageDetail",model);
    }
    
    @RequestMapping("/searchDtailArrearage")
    @ResponseBody
    public ResponseData<OweDetailShowInfo> searchDtailArrearage(HttpServletRequest request,@RequestParam(value = "custId", required = false) String custId) {
//        OweDetailInfoQueryResponse oweDetailInfo = new OweDetailInfoQueryResponse();
        SSOClientUser user = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
        IOweInfoQuerySV iOweInfoQuerySV = DubboConsumerFactory.getService("iOweInfoQuerySV");
        Map<String, OweDetailShowInfo> model = new HashMap<String,OweDetailShowInfo>();
        OweDetailShowInfo oweDetailData = new OweDetailShowInfo();
        try {
            OweDetailInfoQueryRequest realrequest = new OweDetailInfoQueryRequest();
            realrequest.setCustId(custId);
            realrequest.setTenantId(user.getTenantId());
            OweDetailInfoQueryResponse oweDetailInfo =iOweInfoQuerySV.queryOweDetailInfo(realrequest);
            if("000000".equals(oweDetailInfo.getResponseHeader().getResultCode())){
            	BeanUtils.copyProperties(oweDetailData, oweDetailInfo);
            	
                String time = oweDetailInfo.getUnsettledMonth();
                String year = time.substring(0, 4);
                String month = time.substring(4);
                String unableTime = year+"年"+month+"月";
                oweDetailData.setUnsettledMonth(unableTime);
                //翻译客户等级
                String custGradename = CustGradeUtil.getCustLevelName(oweDetailInfo.getCustGrade());
                oweDetailData.setCustGrade(custGradename);
                List<OweDetailInfo> oweDetailInfos = oweDetailInfo.getOweDetailInfos();
                if(!CollectionUtil.isEmpty(oweDetailInfos)){
                	Map<String,List<OweDetailInfo>> oweGDetailMap = new HashMap<String,List<OweDetailInfo>>();
                	Map<String,Integer> oweInfoCountMap = new HashMap<String,Integer>();
                    for(OweDetailInfo info:oweDetailInfos){
                    	//翻译时间
                        if(!StringUtil.isBlank(info.getAccDate())){
                            String t = info.getAccDate(); 
                            if(!StringUtil.isBlank(t)){
                                String years = t.substring(0, 4);
                                String months = t.substring(4);
                                String accTime = years+"年"+months+"月";
                                info.setAccDate(accTime);
                            }
                        }
                        //整理List数据
                        if(!CollectionUtil.isEmpty(info.getChargeDetailInfos())){
                            List<ChargeDetailInfo> chargeDetailInfos = info.getChargeDetailInfos();
                            if(!CollectionUtil.isEmpty(chargeDetailInfos)){
                            	//将数据以date为key 划分
                            	String accDate = info.getAccDate();
                            	List<OweDetailInfo> oweDetailInfoList = oweGDetailMap.get(accDate);
                            	if(oweDetailInfoList == null){
                            		oweDetailInfoList = new LinkedList<OweDetailInfo>();
                            		oweDetailInfoList.add(info);
                            		oweGDetailMap.put(accDate, oweDetailInfoList);
                            		//设置条数
                            		List<ChargeDetailInfo> chargeDetailInfolist = info.getChargeDetailInfos();
                            		oweInfoCountMap.put(accDate, chargeDetailInfolist == null?0:chargeDetailInfolist.size());
                            	}else{
                            		oweDetailInfoList.add(info);
                            		oweGDetailMap.put(accDate, oweDetailInfoList);
                            		//设置条数
                            		List<ChargeDetailInfo> chargeDetailInfolist = info.getChargeDetailInfos();
                            		if(chargeDetailInfolist != null){
                            			int count = oweInfoCountMap.get(accDate);
                            			oweInfoCountMap.put(accDate, count+chargeDetailInfolist.size());
                            		}
                            	}
                                
                            }
                        }
                    }
                    if(oweGDetailMap.size()>0){
                    	Set<String> dateKeySet = oweGDetailMap.keySet();
                    	List<OweGDetailShowInfo> detailShowInfoList=new LinkedList<OweGDetailShowInfo>();
                    	for(String dateKey : dateKeySet){
                    		OweGDetailShowInfo gDetailShowInfo = new OweGDetailShowInfo();
                    		gDetailShowInfo.setDate(dateKey);
                    		gDetailShowInfo.setCount(oweInfoCountMap.get(dateKey));
                    		gDetailShowInfo.setOweDetailInfoList(oweGDetailMap.get(dateKey));
                    		detailShowInfoList.add(gDetailShowInfo);
                    	}
                    	oweDetailData.setOweDetailShowInfoList(detailShowInfoList);
                    }
                }
            }
            model.put("oweDetailInfo",oweDetailData);
        } catch (Exception e) {
            LOG.error("获取信息出错：", e);
        }
        return new ResponseData<OweDetailShowInfo>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功", oweDetailData);
    }
    
    /**
     * 导出欠费详情到excel
     */
    @RequestMapping("/exportArrearageDetailToExcel")
    public void exportArrearageDetailToExcel(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "custId", required = false) String custId){
        
        SSOClientUser user = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
        try {
            OweDetailInfoQueryRequest requestParams = new OweDetailInfoQueryRequest();
            requestParams.setCustId(custId);
            requestParams.setTenantId(user.getTenantId());
            
            IOweInfoQuerySV iOweInfoQuerySV = DubboConsumerFactory.getService("iOweInfoQuerySV");
            LOG.error("欠费详情导出excel入参:" + JSONObject.fromObject(requestParams));
            OweDetailInfoQueryResponse oweDetailInfo =iOweInfoQuerySV.queryOweDetailInfo(requestParams);
            LOG.error("欠费详情导出excel出参:" + JSONObject.fromObject(oweDetailInfo));
            
            OweDetailShowInfo oweDetailShowInfo = new OweDetailShowInfo();  
            if(oweDetailInfo!=null && "000000".equals(oweDetailInfo.getResponseHeader().getResultCode())){
                oweDetailShowInfo = this.getOweDetailShowInfo(oweDetailInfo);//翻译
            }
            
            
            ServletOutputStream outputStream = response.getOutputStream();
            response.reset();// 清空输出流
            response.setContentType("application/msexcel");// 定义输出类型
            response.setHeader("Content-disposition", "attachment; filename=ArrearageDetail"
                    +DateUtil.getDateString(new Date(), DateUtil.yyyyMMddHHmmssSSS)+".xls");// 设定输出文件头
            
            ExcelUtil.exportExcel(outputStream, "欠费明细", oweDetailShowInfo);
            
        } catch (Exception e) {
            LOG.error("欠费明细导出失败：", e);
        }
    }
    
    private OweDetailShowInfo getOweDetailShowInfo(OweDetailInfoQueryResponse oweDetailInfo){
        OweDetailShowInfo oweDetailShowInfo = new OweDetailShowInfo();
        
        BeanUtils.copyProperties(oweDetailShowInfo, oweDetailInfo);
        
        String time = oweDetailInfo.getUnsettledMonth();
        String year = time.substring(0, 4);
        String month = time.substring(4);
        String unableTime = year+"年"+month+"月";
        oweDetailShowInfo.setUnsettledMonth(unableTime);
        //翻译客户等级
        String custGradename = CustGradeUtil.getCustLevelName(oweDetailInfo.getCustGrade());
        oweDetailShowInfo.setCustGrade(custGradename);
        List<OweDetailInfo> oweDetailInfos = oweDetailInfo.getOweDetailInfos();
        if(!CollectionUtil.isEmpty(oweDetailInfos)){
            Map<String,List<OweDetailInfo>> oweGDetailMap = new HashMap<String,List<OweDetailInfo>>();
            Map<String,Integer> oweInfoCountMap = new HashMap<String,Integer>();
            for(OweDetailInfo info:oweDetailInfos){
                //翻译时间
                if(!StringUtil.isBlank(info.getAccDate())){
                    String t = info.getAccDate(); 
                    if(!StringUtil.isBlank(t)){
                        String years = t.substring(0, 4);
                        String months = t.substring(4);
                        String accTime = years+"年"+months+"月";
                        info.setAccDate(accTime);
                    }
                }
                //整理List数据
                if(!CollectionUtil.isEmpty(info.getChargeDetailInfos())){
                    List<ChargeDetailInfo> chargeDetailInfos = info.getChargeDetailInfos();
                    if(!CollectionUtil.isEmpty(chargeDetailInfos)){
                        //将数据以date为key 划分
                        String accDate = info.getAccDate();
                        List<OweDetailInfo> oweDetailInfoList = oweGDetailMap.get(accDate);
                        if(oweDetailInfoList == null){
                            oweDetailInfoList = new LinkedList<OweDetailInfo>();
                            oweDetailInfoList.add(info);
                            oweGDetailMap.put(accDate, oweDetailInfoList);
                            //设置条数
                            List<ChargeDetailInfo> chargeDetailInfolist = info.getChargeDetailInfos();
                            oweInfoCountMap.put(accDate, chargeDetailInfolist == null?0:chargeDetailInfolist.size());
                        }else{
                            oweDetailInfoList.add(info);
                            oweGDetailMap.put(accDate, oweDetailInfoList);
                            //设置条数
                            List<ChargeDetailInfo> chargeDetailInfolist = info.getChargeDetailInfos();
                            if(chargeDetailInfolist != null){
                                int count = oweInfoCountMap.get(accDate);
                                oweInfoCountMap.put(accDate, count+chargeDetailInfolist.size());
                            }
                        }
                        
                    }
                }
            }
            if(oweGDetailMap.size()>0){
                Set<String> dateKeySet = oweGDetailMap.keySet();
                List<OweGDetailShowInfo> detailShowInfoList=new LinkedList<OweGDetailShowInfo>();
                for(String dateKey : dateKeySet){
                    OweGDetailShowInfo gDetailShowInfo = new OweGDetailShowInfo();
                    gDetailShowInfo.setDate(dateKey);
                    gDetailShowInfo.setCount(oweInfoCountMap.get(dateKey));
                    gDetailShowInfo.setOweDetailInfoList(oweGDetailMap.get(dateKey));
                    detailShowInfoList.add(gDetailShowInfo);
                }
                oweDetailShowInfo.setOweDetailShowInfoList(detailShowInfoList);
            }
        }
        return oweDetailShowInfo;
    }
    
    /**
     * 导出欠费列表到excel
     */
    @RequestMapping("/exportArrearageListToExcel")
    public void exportArrearageListToExcel(HttpServletRequest request, HttpServletResponse response, OweInfoListQueryVo queryInfo){
        
        try {
            List<OweDescInfo> resultList = this.getOweInfoList(request, queryInfo);//查询数据
            
            ServletOutputStream outputStream = response.getOutputStream();
            response.reset();// 清空输出流
            response.setContentType("application/msexcel");// 定义输出类型
            response.setHeader("Content-disposition", "attachment; filename=ArrearageList"
                    +DateUtil.getDateString(new Date(), DateUtil.yyyyMMddHHmmssSSS)+".xls");// 设定输出文件头
            
            String[] titles = new String[] { "客户名称", "客户等级", "欠费开始时间", "欠费金额（元）" };
            String[] fieldNames = new String[] { "custName", "custGrade", "unsettledMonth", "balanceDesc" };
            AbstractExcelHelper excelHelper = ExcelFactory.getJxlExcelHelper();
            excelHelper.writeExcel(outputStream, "欠费信息", OweDescInfo.class, resultList, fieldNames, titles);
            
        } catch (Exception e) {
            LOG.error("欠费信息导出失败：", e);
        }
    }
    
    //导出excel，查询数据
    private List<OweDescInfo> getOweInfoList(HttpServletRequest request, OweInfoListQueryVo queryInfo){
        List<OweDescInfo> resultList = new ArrayList<OweDescInfo>();
        
        OweInfoListQueryRequest requestParams = this.generateRequestParam(request, queryInfo);//构建查询参数
        
        IOweInfoQuerySV iOweInfoQuerySV = DubboConsumerFactory.getService("iOweInfoQuerySV");
        LOG.error("欠费查询导出excel总入参:" + JSONObject.fromObject(requestParams));
        OweInfoListQueryResponse resultInfo = iOweInfoQuerySV.queryOweInfoList(requestParams);
        LOG.error("欠费查询导出excel总出参:" + JSONObject.fromObject(resultInfo));
        
        PageInfo<OweInfo> result= resultInfo.getPageInfo();
        if(result != null){
            int pageCount = result.getPageCount();
            if(pageCount > 0){
                for(int i=1; i<=pageCount; i++){
                    requestParams.getPageInfo().setPageNo(i);
                    LOG.error("欠费查询导出excel第"+i+"页入参:" + JSONObject.fromObject(requestParams));
                    resultInfo = iOweInfoQuerySV.queryOweInfoList(requestParams);
                    LOG.error("欠费查询导出excel第"+i+"页出参:" + JSONObject.fromObject(resultInfo));
                    result = resultInfo.getPageInfo();
                    resultList.addAll(this.traslateList(result.getResult()));//翻译查询结果
                }
            }
        }
        
        return resultList;
    }
    
    //导出excel，构建查询参数
    private OweInfoListQueryRequest generateRequestParam (HttpServletRequest request, OweInfoListQueryVo queryInfo){
        SSOClientUser user = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
        
        OweInfoListQueryRequest realrequest = new OweInfoListQueryRequest();
        if(!StringUtil.isBlank(queryInfo.getCustName())){
            realrequest.setCustName(queryInfo.getCustName());
        }
        if(!StringUtil.isBlank(queryInfo.getCustGrade())){
            realrequest.setCustGrade(queryInfo.getCustGrade());
        }
        realrequest.setTenantId(user.getTenantId());
        if(!StringUtil.isBlank(queryInfo.getStartDate())){
            //截取数据
            String sDate =  queryInfo.getStartDate();
            sDate=sDate.replace("-",""); 
            realrequest.setStartDate(sDate);
        }
        if(!StringUtil.isBlank(queryInfo.getEndDate())){
            //修改欠费时间格式
            String eDate =  queryInfo.getEndDate();
            eDate=eDate.replace("-",""); 
            realrequest.setEndDate(eDate);
        }
        
        if(!StringUtil.isBlank(queryInfo.getBottomAmount())){
            realrequest.setBottomAmount( Double.parseDouble(queryInfo.getBottomAmount()));
        }
        if(!StringUtil.isBlank(queryInfo.getTopAmount())){
            realrequest.setTopAmount( Double.parseDouble(queryInfo.getTopAmount()));
        }
        PageInfo<OweInfo> pageInfo = new PageInfo<OweInfo> ();
        String strPageNo=(null==request.getParameter(BaaSOPConstants.PAGENO))?"1":request.getParameter(BaaSOPConstants.PAGENO);
        String strPageSize=(null==request.getParameter(BaaSOPConstants.PAGESIZE))?"10":request.getParameter(BaaSOPConstants.PAGESIZE);
        pageInfo.setPageNo(Integer.parseInt(strPageNo));
        pageInfo.setPageSize(Integer.parseInt(strPageSize));
        realrequest.setPageInfo(pageInfo);
        
        return realrequest;
    }
    
    //导出excel，翻译查询结果
    private List<OweDescInfo> traslateList(List<OweInfo> oweInfoList){
        List<OweDescInfo> resultList = new ArrayList<OweDescInfo>();
        
        if(!CollectionUtil.isEmpty(oweInfoList)){
            for(OweInfo oweInfo:oweInfoList){
                OweDescInfo oweDescInfo = new OweDescInfo();
                BeanUtils.copyProperties(oweDescInfo, oweInfo);
            
                //1.翻译欠费开始时间
                String time = oweInfo.getUnsettledMonth(); 
                if(!StringUtil.isBlank(time)){
                    String year = time.substring(0, 4);
                    String month = time.substring(4);
                    String unableTime = year+"年"+month+"月";
                    oweDescInfo.setUnsettledMonth(unableTime);
                }
                //2.翻译客户等级
                String custGradename = CustGradeUtil.getCustLevelName(oweInfo.getCustGrade());
                oweDescInfo.setCustGrade(custGradename);
                
                //3.金额从厘转换到元
                oweDescInfo.setBalanceDesc(AmountUtil.Li2Yuan(oweInfo.getBalance()));
                
                resultList.add(oweDescInfo);
            } 
        } 
        return resultList;
    }
    
}


