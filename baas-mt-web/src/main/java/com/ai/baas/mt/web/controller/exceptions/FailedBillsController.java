package com.ai.baas.mt.web.controller.exceptions;

import com.ai.baas.bmc.api.baseInfo.params.BaseCode;
import com.ai.baas.bmc.api.failedbillmaintain.interfaces.IFailedBillMaintainSV;
import com.ai.baas.bmc.api.failedbillmaintain.params.*;
import com.ai.baas.mt.web.constants.BaaSMTConstants;
import com.ai.baas.mt.web.controller.common.ParamController;
import com.ai.baas.mt.web.controller.configure.ConfigureLoadController;
import com.ai.baas.mt.web.model.FailedBillShowVo;
import com.ai.baas.mt.web.model.FailedBillVo;
import com.ai.baas.mt.web.util.DateUtil;
import com.ai.baas.mt.web.util.TradeSeqUtil;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.HBasePager;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.opt.sdk.web.model.ResponseData;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 错单管理
 * @author wangluyang
 *
 */
@RestController
@RequestMapping("/failedBills")
public class FailedBillsController {
	
	private static final Logger LOG = Logger.getLogger(ConfigureLoadController.class);

	@RequestMapping("/list")
    public ModelAndView toList(HttpServletRequest request) {
        return new ModelAndView("jsp/exceptions/failedBillsList");
    }
	
	 /**
	  * 错单查询
	  * @param request
	  * @return
	  */
    @RequestMapping("/getList")
    public ResponseData<PageInfo<FailedBill>> getList(HttpServletRequest request){
    	
    	ResponseData<PageInfo<FailedBill>> responseData = null;
    	
    	try {
        	IFailedBillMaintainSV iFailedBillMaintainSV = DubboConsumerFactory.getService("iFailedBillMaintainSV");
        	FailedBillCriteria queryInfo = new FailedBillCriteria();
        	queryInfo.setTenantId(request.getParameter("tenantId"));   //需要修改一下
        	queryInfo.setServiceType(request.getParameter("serviceType"));
        	queryInfo.setErrorCode(request.getParameter("errorCode"));
        	String startTime=request.getParameter("startTime");
        	String endTime=request.getParameter("endTime");
        	if(!StringUtil.isBlank(startTime)){
        		//拼接时间
        		//startTime=startTime.split("-").toString()+"000000";
        		startTime=transArr2Str(startTime.split("-"))+"000000";
        	}
        	if(!StringUtil.isBlank(endTime)){
        		//startTime=startTime.split("-").toString()+"235959";
        		endTime=transArr2Str(endTime.split("-"))+"235959";
        	}
        	queryInfo.setStartTime(startTime);
        	queryInfo.setEndTime(endTime);
        	//分页
        	int strPageNo=(null==request.getParameter(BaaSMTConstants.PAGENO))?1:Integer.parseInt(request.getParameter(BaaSMTConstants.PAGENO));
        	int strPageSize=(null==request.getParameter(BaaSMTConstants.PAGESIZE))?10:Integer.parseInt(request.getParameter(BaaSMTConstants.PAGESIZE));
        	String startRow = request.getParameter("startRow");
        	String previousRow = request.getParameter("previousRow");
        	HBasePager<FailedBill> searchpage=new HBasePager<FailedBill>();
    		searchpage.setPreviousRow(previousRow);
    		searchpage.setStartRow(startRow);
    		searchpage.setPageSize(strPageSize);
//    		queryInfo.setPager(searchpage);
    		
        	LOG.info("错单查询入參"+JSONObject.fromObject(queryInfo).toString());
        	int count = 0;
        	FailedBillPagerResponse resultInfo = iFailedBillMaintainSV.queryFailedBills(queryInfo);
        	/*for( FailedBill failedBill:  resultInfo.getBillHBasePager().getResult()){
        		if ("20160419202210".equals(failedBill.getFailDate())){
        			count++;
        		}
        	}*/
        	
        	PageInfo<FailedBill> pageResult = new PageInfo<FailedBill>();
        	pageResult.setPageSize(strPageSize);
        	pageResult.setPageNo(strPageNo);
        	pageResult.setPageCount(strPageSize);
        	pageResult.setCount(strPageSize);
        	List<FailedBill> results = new ArrayList<FailedBill>();
        	if(resultInfo.getBillHBasePager().getResult()!=null){
        		List<BaseCode> serverTypes = ParamController.getSysParams(
    					"PUB", "SERVICE_TYPE", TradeSeqUtil.newTradeSeq("PUB"));
        		for(FailedBill bill:resultInfo.getBillHBasePager().getResult()){
        			FailedBillShowVo vo = new FailedBillShowVo();
        			BeanUtils.copyProperties(vo, bill);
        			for(BaseCode code:serverTypes){
        				if(code.getParamCode().equals(bill.getServiceId())){
        					vo.setServiceTypeName(code.getParamName());
        				}
        			}
        			Date date = DateUtil.to_date(String.valueOf(bill.getFailDate()), DateUtil.YYYYMMDDHHMMSS);
        			vo.setFailBillDate(date);
        			results.add(vo);
        		}
        	}
        	pageResult.setResult(results);
        	
        	responseData = new ResponseData<PageInfo<FailedBill>>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功", pageResult);
        	
    	} catch (Exception e) {
			responseData = new ResponseData<PageInfo<FailedBill>>(ResponseData.AJAX_STATUS_FAILURE,"获取信息异常");
			LOG.error("获取信息出错：", e);
		}
        return responseData;
    }
	private String transArr2Str(String[] arr){
		StringBuilder sb=new StringBuilder();
    	 for(int i=0;i<arr.length;i++){
    		 sb.append(arr[i]);
    	 }
    	return sb.toString();
    }
    /**
     * getFailedBillDetail
     * @param param
     * @param request
     * @return
     */
    @RequestMapping("/getFailedBillDetail")
    public ResponseData<Map<String, String>> getFailedBillDetail(FailedBillParam param, HttpServletRequest request){
    	
    	ResponseData<Map<String, String>> responseData = null;
    	Map<String, String> result = null;
    	try {
    		
        	IFailedBillMaintainSV iFailedBillMaintainSV = DubboConsumerFactory.getService("iFailedBillMaintainSV");
        	
//        	LOG.info("错单查询入參"+JSONObject.fromObject(param).toString());
        	FailedBillResponse resultInfo = iFailedBillMaintainSV.queryFailedBillsById(param);
        	LOG.info("错单查询出參"+JSONObject.fromObject(resultInfo).toString());
        	
        	if(resultInfo!=null){
        		result = resultInfo.getFailedBill().getFailPacket();
        	}
        	
        	if(resultInfo!=null && resultInfo.getResponseHeader()!=null && resultInfo.getResponseHeader().isSuccess()){
        		responseData = new ResponseData<Map<String, String>>(ResponseData.AJAX_STATUS_SUCCESS, "记录存在", result);
        	}else{
        		responseData = new ResponseData<Map<String, String>>(ResponseData.AJAX_STATUS_SUCCESS, "记录不存在", null);
        	}
    	} catch (Exception e) {
    		responseData = new ResponseData<Map<String, String>>(ResponseData.AJAX_STATUS_FAILURE, "查询失败", null);
			LOG.error("获取信息出错：", e);
		}
        return responseData;
    }
    
    /**
     * 判断是否存在
     */
    @RequestMapping("/getFailedBill")
    public ResponseData<Object> getFailedBill(FailedBillParam param, HttpServletRequest request){
    	
    	ResponseData<Object> responseData = null;
    	
    	try {
//    		HttpSession session = request.getSession();
//    		SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
        	IFailedBillMaintainSV iFailedBillMaintainSV = DubboConsumerFactory.getService("iFailedBillMaintainSV");
        	
//        	LOG.info("错单查询入參"+JSONObject.fromObject(param).toString());
        	FailedBillResponse resultInfo = iFailedBillMaintainSV.queryFailedBillsById(param);
        	LOG.info("错单查询出參"+JSONObject.fromObject(resultInfo).toString());
        	
        	if(resultInfo!=null && resultInfo.getResponseHeader()!=null && resultInfo.getResponseHeader().isSuccess()){
        		responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_SUCCESS, "记录存在", true);
        	}else{
        		responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_SUCCESS, "记录不存在", false);
        	}
    	} catch (Exception e) {
    		responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_FAILURE, "查询失败", false);
			LOG.error("获取信息出错：", e);
		}
        return responseData;
    }
    
    /**
     * 冲发送编辑后的错单
     * @param vo
     * @param request
     * @return
     */
    @RequestMapping("/editResendBill")
    public ResponseData<Object> editResendBill(FailedBillVo vo, HttpServletRequest request){
    	
    	ResponseData<Object> responseData = null;
    	try {
        	IFailedBillMaintainSV iFailedBillMaintainSV = DubboConsumerFactory.getService("iFailedBillMaintainSV");
        	
        	FailedBillParam param = new FailedBillParam();
        	Map<String, String> map = new HashMap<String, String>();
        	if(StringUtils.isNoneBlank(vo.getBillkeysStr(), vo.getBillValuesStr())){
        		JSONArray billkeys = JSONArray.fromObject(vo.getBillkeysStr());
        		JSONArray billValues = JSONArray.fromObject(vo.getBillValuesStr());
        		for(int i=0; i<billkeys.size(); i++){
        			map.put(billkeys.getString(i), (String) billValues.get(i));
        		}
        	}
        	param.setBsn(vo.getBsn());
        	param.setSn(vo.getSn());
        	param.setAccountPeriod(vo.getAccountPeriod());
        	param.setArrivalTime(vo.getArrivalTime());
        	param.setFailDate(vo.getFailDate());
        	param.setFailedCode(vo.getFailedCode());
        	param.setFailStep(vo.getFailStep());
        	param.setServiceId(vo.getServiceId());
        	param.setSource(vo.getSource());
        	param.setTenantId(vo.getTenantId());
        	param.setTenantPwd(vo.getTenantPwd());
        	param.setFailPacket(map);
        	
        	LOG.info("单次错单重发送入參"+JSONObject.fromObject(param).toString());  
        	BaseResponse resultInfo = iFailedBillMaintainSV.resendFailedBill(param);
        	if(resultInfo!=null){
        		responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_SUCCESS, 
        				resultInfo.getResponseHeader().getResultMessage(), resultInfo.getResponseHeader().isSuccess());
        	}
    	} catch (Exception e) {
    		responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_FAILURE, "操作失败", false);
			LOG.error("获取信息出错：", e);
		}
        return responseData;
    }
    
    /**
     * 批量重发送
     * @param params
     * @param request
     * @return
     */
    @RequestMapping("/batchResendBill")
    public ResponseData<Object> batchResendBill(String jsonData, HttpServletRequest request){
    	
    	ResponseData<Object> responseData = null;
    	try {
        	IFailedBillMaintainSV iFailedBillMaintainSV = DubboConsumerFactory.getService("iFailedBillMaintainSV");
        	
        	List<FailedBillParam> params = new ArrayList<FailedBillParam>();
        	if(StringUtils.isNotBlank(jsonData)){
        		params = com.alibaba.fastjson.JSONObject.parseArray(jsonData, FailedBillParam.class);
        	}
        	BaseResponse resultInfo = iFailedBillMaintainSV.batchResendFailedBill(params);
        	if(resultInfo!=null){
        		responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_SUCCESS, resultInfo.getResponseHeader().getResultMessage(), 
        				resultInfo.getResponseHeader().isSuccess());
        	}
    	} catch (Exception e) {
    		responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_FAILURE, "查询失败", false);
			LOG.error("获取信息出错：", e);
		}
        return responseData;
    }
    
  
}
