package com.ai.citic.billing.web.common;

import com.ai.baas.bmc.api.baseInfo.interfaces.IBaseInfoSV;
import com.ai.baas.bmc.api.baseInfo.params.*;
import com.ai.citic.billing.web.model.sso.CiticSSOClientUser;
import com.ai.citic.billing.web.util.TradeSeqUtil;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import com.ai.opt.sso.client.filter.SSOClientUser;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 数据库参数查询
 * @author wangluyang
 *
 */
@RestController
@RequestMapping("/param")
public class ParamController {

	private static final Logger LOGGER = Logger
			.getLogger(ParamController.class);
	
	
	/**
	 * 获得定价策略变量单位
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getPolicyVarUnit")
	public List<BaseCode> getStandardUnit(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
		List<BaseCode> baseList = new ArrayList<BaseCode>();
		try {
			baseList = ParamController.getSysParams(
				"ECITIC", "POLICY_VAR_UNIT", TradeSeqUtil.newTradeSeq(user.getTenantId()));
		} catch (Exception e) {
			LOGGER.info("获取菜单失败", e);
		}
		return baseList;
	}
	
	/**
	 * 获得变量类型
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getVarType")
	public List<BaseCode> getVarType(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
		List<BaseCode> baseList = new ArrayList<BaseCode>();
		try {
			baseList = ParamController.getSysParams(
				"ECITIC", "VAR_TYPE", TradeSeqUtil.newTradeSeq(user.getTenantId()));
		} catch (Exception e) {
			LOGGER.info("获取菜单失败", e);
		}
		return baseList;
	}
	
	/**
	 * 获得定价策略类型
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getPolicyType")
	public List<BaseCode> getPolicyType(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
		List<BaseCode> baseList = new ArrayList<BaseCode>();
		try {
			baseList = ParamController.getSysParams(
				"ECITIC", "POLICY_TYPE", TradeSeqUtil.newTradeSeq(user.getTenantId()));
		} catch (Exception e) {
			LOGGER.info("获取菜单失败", e);
		}
		return baseList;
	}
	
	/**
	 * 获取周期类型
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getCycleType")
	public List<BaseCode> getCycleType(HttpServletRequest request) {
		HttpSession session = request.getSession();
		CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
		List<BaseCode> baseList = new ArrayList<BaseCode>();
		try {
			baseList = ParamController.getSysParams(
					user.getTenantId(), "CYCLE", TradeSeqUtil.newTradeSeq(user.getTenantId()));
		} catch (Exception e) {
			LOGGER.info("获取信息出错", e);
		}
		return baseList;
	}
	
	/**
	 * 获得计费模式
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getBillingMode")
	public List<BaseCode> getBillingMode(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
		List<BaseCode> baseList = new ArrayList<BaseCode>();
		try {
			baseList = ParamController.getSysParams(
				"ECITIC", "BILLING_MODE", TradeSeqUtil.newTradeSeq(user.getTenantId()));
		} catch (Exception e) {
			LOGGER.info("获取菜单失败", e);
		}
		return baseList;
	}
	
	/**
	 * 获得价格类型
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getPriceType")
	public List<BaseCode> getPriceType(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
		List<BaseCode> baseList = new ArrayList<BaseCode>();
		try {
			baseList = ParamController.getSysParams(
				user.getTenantId(), "PRICE_TYPE", TradeSeqUtil.newTradeSeq(user.getTenantId()));
		} catch (Exception e) {
			LOGGER.info("获取菜单失败", e);
		}
		return baseList;
	}
	
	/**
	 * 获取系统参数
	 * @param tenantId
	 * @param paramType
	 * @param tradeSeq
	 * @return
	 */
    public static List<BaseCode> getSysParams(String tenantId, String paramType, String tradeSeq) {
        
    	List<BaseCode> baseList = new ArrayList<BaseCode>();
    	try {
	    	IBaseInfoSV iBaseInfoSV = DubboConsumerFactory.getService("iBaseInfoSV");
	        
	        QueryInfoParams queryInfo = new QueryInfoParams();
	        queryInfo.setTenantId(tenantId);
	        queryInfo.setParamType(paramType);
	        queryInfo.setTradeSeq(tradeSeq);
	        
	        BaseCodeInfo resultInfo = iBaseInfoSV.getBaseInfo(queryInfo);
	        if(resultInfo!=null){
	        	baseList = resultInfo.getParamList();
	        }
    	} catch (Exception e) {
    		LOGGER.error("获取信息出错：", e);
		}
        return baseList;
    }
    
    /**
	 * 获取系统参数
	 * @param tenantId
	 * @param parentCode
	 * @param tradeSeq
	 * @return
	 */
    public static List<BaseCode> getChildCode(String tenantId, String parentCode, String tradeSeq) {
        
    	List<BaseCode> baseList = new ArrayList<BaseCode>();
    	try {
	    	IBaseInfoSV iBaseInfoSV = DubboConsumerFactory.getService("iBaseInfoSV");
	        
	    	QueryChildCodeRequest queryInfo = new QueryChildCodeRequest();
	        queryInfo.setTenantId(tenantId);
	        queryInfo.setParentCode(parentCode);
	        queryInfo.setTradeSeq(tradeSeq);
	        
	        ChildeCodeResponse resultInfo = iBaseInfoSV.getChildCode(queryInfo);
	        if(resultInfo!=null){
	        	baseList = resultInfo.getParamList();
	        }
    	} catch (Exception e) {
    		LOGGER.error("获取信息出错：", e);
		}
        return baseList;
    }
    
}
