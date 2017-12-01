package com.ai.citic.billing.web.controller.invoice;

import com.ai.baas.amc.api.invoice.interfaces.IInvoiceInfoSV;
import com.ai.baas.amc.api.invoice.params.*;
import com.ai.baas.bmc.api.queryidinfo.params.BlAcctInfoInfo;
import com.ai.citic.billing.web.common.CommonCaller;
import com.ai.citic.billing.web.model.sso.CiticSSOClientUser;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.opt.sdk.web.model.ResponseData;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import net.sf.json.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 发票信息管理模块
 * @author wangluyang
 *
 */
@RestController
@RequestMapping("/invoice")
public class InvoiceInfoController {

	public static final Logger LOGGER = LoggerFactory.getLogger(InvoiceInfoController.class);
	
	@Autowired
    private CommonCaller commonCaller;
	
	@RequestMapping("/toInvoiceInfo")
	public ModelAndView toInvoiceInfo(InvoiceRecordVo vo,HttpServletRequest request) {

//		HttpSession session = request.getSession();
//		CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
		try {
			if(vo!=null){
				if(vo.getStatus()!=null && 1==vo.getStatus()){
					try {
						vo.setTitle(URLDecoder.decode(vo.getTitle().trim(), "utf-8"));
						vo.setTaxRegNo(URLDecoder.decode(vo.getTaxRegNo().trim(), "utf-8"));
						vo.setBankName(URLDecoder.decode(vo.getBankName().trim(), "utf-8"));
						vo.setRegAddress(URLDecoder.decode(vo.getRegAddress().trim(), "utf-8"));
						vo.setLinkName(URLDecoder.decode(vo.getLinkName().trim(), "utf-8"));
						vo.setAddress(URLDecoder.decode(vo.getAddress().trim(), "utf-8"));
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					request.setAttribute("invoiceInfo", vo);
				}else{
					IInvoiceInfoSV iInvoiceInfoSV = DubboConsumerFactory.getService("iInvoiceInfoSV");
					InvoiceQueryParam queryParam = new InvoiceQueryParam();
					queryParam.setAcctId(vo.getAcctId());
					queryParam.setTenantId(vo.getTenantId());
					queryParam.setUserType("1");
					LOGGER.info("发票信息查询入參"+JSONArray.fromObject(queryParam).toString());
					InvoiceInfoQueryResponse infoQueryResponse = iInvoiceInfoSV.queryInvoiceInfo(queryParam);
					request.setAttribute("invoiceInfo", infoQueryResponse.getInvoiceInfoVO());
				}
			}
		} catch (Exception e) {
			LOGGER.error("获取信息出错：", e);
			return null;
		}
		return new ModelAndView("jsp/invoice/invoiceInfoShow");
    }
	
	@RequestMapping("/toInvoiceInfoUpdate")
	public ModelAndView toInvoiceInfoUpdate(HttpServletRequest request) {

		try {
			HttpSession session = request.getSession();
			CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
			IInvoiceInfoSV iInvoiceInfoSV = DubboConsumerFactory.getService("iInvoiceInfoSV");
			InvoiceQueryParam queryParam = new InvoiceQueryParam();
	//		queryParam.setAcctId("17");
	//		queryParam.setTenantId("VIV-BYD");
			queryParam.setAcctId(user.getAcctId());
			queryParam.setTenantId(user.getTenantId());
			queryParam.setUserType("0");
			LOGGER.info("发票信息查询入參"+JSONArray.fromObject(queryParam).toString());
			InvoiceInfoQueryResponse infoQueryResponse = iInvoiceInfoSV.queryInvoiceInfo(queryParam);
			request.setAttribute("invoiceInfo", infoQueryResponse.getInvoiceInfoVO());
		} catch (Exception e) {
			LOGGER.error("获取信息出错：", e);
			return null;
		}
		return new ModelAndView("jsp/invoice/invoiceInfoAdd");
    }
	
	@RequestMapping("/saveInvoice")
	public ResponseData<Object> save(InvoiceInfoVO vo, HttpServletRequest request){
		 
		ResponseData<Object> responseData = null;
		String msg = "保存";
		try {
			HttpSession session = request.getSession();
			CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
			
			IInvoiceInfoSV iInvoiceInfoSV = DubboConsumerFactory.getService("iInvoiceInfoSV");
			InvoiceInfoParam infoParam = new InvoiceInfoParam();
			BeanUtils.copyProperties(infoParam, vo);
			infoParam.setAcctId(user.getAcctId());
			infoParam.setTenantId(user.getTenantId());
			
			if(!StringUtil.isBlank(user.getTenant())){
                String custId = null;
				List<BlAcctInfoInfo> acctAndCustInfos = commonCaller.getAcctAndCustInfo(user.getTenantId(), user.getTenant());
				if(!CollectionUtil.isEmpty(acctAndCustInfos)){
					custId = acctAndCustInfos.get(0).getOwnerId();
				}
                infoParam.setCustId(custId);
            }
			
			if(StringUtils.isNotBlank(vo.getInvoiceInfoId())){
				msg = "更新";
			}
			LOGGER.info("发票信息新增修改入參"+JSONArray.fromObject(infoParam).toString());
			BaseResponse response = iInvoiceInfoSV.saveInvoiceInfo(infoParam);
			LOGGER.info("发票信息新增修改出參"+JSONArray.fromObject(response).toString());
			if(response!=null && response.getResponseHeader()!=null && response.getResponseHeader().isSuccess()){
    			responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_SUCCESS, msg+"成功");
    		}else{
    			responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_FAILURE, msg+"失败");
    		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_FAILURE, msg+"异常");
			LOGGER.error("获取信息出错：", e);
		} 
		return responseData;
	 }
}
