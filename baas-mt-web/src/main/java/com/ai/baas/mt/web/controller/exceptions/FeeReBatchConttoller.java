package com.ai.baas.mt.web.controller.exceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ai.baas.bmc.api.baseInfo.params.BaseCode;
import com.ai.baas.bmc.api.failedbillmaintain.interfaces.IFailedBillMaintainSV;
import com.ai.baas.bmc.api.failedbillmaintain.params.FailedBill;
import com.ai.baas.bmc.api.failedbillmaintain.params.FailedBillCriteria;
import com.ai.baas.bmc.api.failedbillmaintain.params.FailedBillPagerResponse;
import com.ai.baas.bmc.api.failedbillmaintain.params.FailedBillParam;
import com.ai.baas.bmc.api.feeReBatch.interfaces.IFeeReBatchSV;
import com.ai.baas.bmc.api.feeReBatch.params.FeeParam;
import com.ai.baas.bmc.api.feeReBatch.params.FeeParamPagerResponse;
import com.ai.baas.bmc.api.feeReBatch.params.FeeReBatchCriteria;
import com.ai.baas.bmc.api.feeReBatch.params.FeeReBatchParam;
import com.ai.baas.mt.web.constants.BaaSMTConstants;
import com.ai.baas.mt.web.controller.common.ParamController;
import com.ai.baas.mt.web.controller.configure.ConfigureLoadController;
import com.ai.baas.mt.web.model.FailedBillShowVo;
import com.ai.baas.mt.web.util.DateUtil;
import com.ai.baas.mt.web.util.TradeSeqUtil;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.HBasePager;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.web.model.ResponseData;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import com.ai.opt.sso.client.filter.SSOClientUser;

import net.sf.json.JSONObject;

/**
 * 费用重批
 * @author wangluyang
 *
 */
@RestController
@RequestMapping("/feeReBatch")
public class FeeReBatchConttoller {

	private static final Logger LOG = Logger.getLogger(FeeReBatchConttoller.class);
	
	@RequestMapping("/list")
    public ModelAndView toList(HttpServletRequest request) {
        return new ModelAndView("jsp/exceptions/feeReBatchList");
    }

    @RequestMapping("/getList")
    public ResponseData<PageInfo<FeeParam>> getList(HttpServletRequest request){
    	
    	ResponseData<PageInfo<FeeParam>> responseData = null;
    	
    	try {
    		IFeeReBatchSV iFeeReBatchSV = DubboConsumerFactory.getService("iFeeReBatchSV");
    		FeeReBatchCriteria queryInfo = new FeeReBatchCriteria();
        	queryInfo.setTenantId(request.getParameter("tenantId"));
        	String accountPeriod = request.getParameter("accountPeriod");
        	if(StringUtils.isNotBlank(accountPeriod)){
        		accountPeriod = accountPeriod.replaceAll("-", "");
        	}
        	queryInfo.setAccountPeriod(accountPeriod);
        	queryInfo.setServiceType(request.getParameter("serviceType"));
        	queryInfo.setServiceId(request.getParameter("serviceId"));
    		
        	int strPageNo=(null==request.getParameter(BaaSMTConstants.PAGENO))?1:Integer.parseInt(request.getParameter(BaaSMTConstants.PAGENO));
        	int strPageSize=(null==request.getParameter(BaaSMTConstants.PAGESIZE))?10:Integer.parseInt(request.getParameter(BaaSMTConstants.PAGESIZE));
        	
        	LOG.info("费用重批查询入參"+JSONObject.fromObject(queryInfo).toString());
        	FeeParamPagerResponse resultInfo = iFeeReBatchSV.queryFeeReBatch(queryInfo);
        	LOG.info("费用重批查询出參"+JSONObject.fromObject(resultInfo).toString());
        	
        	PageInfo<FeeParam> pageResult = new PageInfo<FeeParam>();
        	pageResult.setPageSize(strPageSize);
        	pageResult.setPageNo(strPageNo);
        	pageResult.setPageCount(strPageSize);
        	pageResult.setCount(strPageSize);
        	pageResult.setResult(resultInfo.getFeeParamPager().getResult());
        	
        	responseData = new ResponseData<PageInfo<FeeParam>>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功", pageResult);
        	
    	} catch (Exception e) {
			responseData = new ResponseData<PageInfo<FeeParam>>(ResponseData.AJAX_STATUS_FAILURE,"获取信息异常");
			LOG.error("获取信息出错：", e);
		}
        return responseData;
    }
    
    /**
     * 批量重批
     * @param params
     * @param request
     * @return
     */
    @RequestMapping("/reBatchFee")
    public ResponseData<Object> reBatchFee(HttpServletRequest request){
    	
    	ResponseData<Object> responseData = null;
    	try {
    		IFeeReBatchSV iFeeReBatchSV = DubboConsumerFactory.getService("iFeeReBatchSV");
    		FeeReBatchParam param = new FeeReBatchParam();
    		FeeReBatchCriteria queryInfo = new FeeReBatchCriteria();
        	queryInfo.setTenantId(request.getParameter("tenantId"));
        	String accountPeriod = request.getParameter("accountPeriod");
        	if(StringUtils.isNotBlank(accountPeriod)){
        		accountPeriod = accountPeriod.replaceAll("-", "");
        	}
        	queryInfo.setAccountPeriod(accountPeriod);
        	queryInfo.setServiceType(request.getParameter("serviceType"));
        	queryInfo.setServiceId(request.getParameter("serviceId"));
        	queryInfo.setReBatchType(request.getParameter("fallBackType"));
        	param.setCriteria(queryInfo);
        	LOG.info("费用重批入參"+JSONObject.fromObject(param).toString());
        	BaseResponse resultInfo = iFeeReBatchSV.batchResendFee(param);
        	if(resultInfo!=null){
        		responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_SUCCESS, resultInfo.getResponseHeader().getResultMessage(), 
        				resultInfo.getResponseHeader().isSuccess());
        	}
    	} catch (Exception e) {
    		responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_FAILURE, "发送失败", false);
			LOG.error("获取信息出错：", e);
		}
        return responseData;
    }
}
