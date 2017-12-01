package com.ai.baas.mt.web.controller.common;


import com.ai.baas.bmc.api.baseInfo.interfaces.IBaseInfoSV;
import com.ai.baas.bmc.api.baseInfo.params.BaseCode;
import com.ai.baas.bmc.api.baseInfo.params.BaseCodeInfo;
import com.ai.baas.bmc.api.baseInfo.params.ChildeCodeResponse;
import com.ai.baas.bmc.api.baseInfo.params.QueryChildCodeRequest;
import com.ai.baas.bmc.api.baseInfo.params.QueryInfoParams;
import com.ai.baas.mt.web.constants.BaaSMTConstants;
import com.ai.baas.mt.web.util.TradeSeqUtil;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import com.ai.opt.sso.client.filter.SSOClientUser;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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
	
	@RequestMapping(value = "/getServiceType")
	public List<BaseCode> getServiceType(HttpServletRequest request,
			HttpServletResponse response) {
		List<BaseCode> baseList = new ArrayList<BaseCode>();
		try {
			baseList = ParamController.getSysParams(
					BaaSMTConstants.TENANTID, "SERVICE_TYPE", TradeSeqUtil.newTradeSeq("PUB"));
		} catch (Exception e) {
			LOGGER.info("获取菜单失败", e);
		}
		return baseList;
	}
	
	@RequestMapping(value = "/getFallBackType")
	public List<BaseCode> getFallBackType(HttpServletRequest request,
			HttpServletResponse response) {
		List<BaseCode> baseList = new ArrayList<BaseCode>();
		try {
			baseList = ParamController.getSysParams(
				"PUB", "FALL_BACK_TYPE", TradeSeqUtil.newTradeSeq("PUB"));
		} catch (Exception e) {
			LOGGER.info("获取菜单失败", e);
		}
		return baseList;
	}
	
	@RequestMapping(value = "/getServiceDetail")
	public List<BaseCode> getServiceDetail(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
		String serverId = request.getParameter("serverId");
		List<BaseCode> baseList = new ArrayList<BaseCode>();
		try {
			baseList = ParamController.getChildCode(
				user.getTenantId(), serverId, TradeSeqUtil.newTradeSeq(user.getTenantId()));
		} catch (Exception e) {
			LOGGER.info("获取菜单失败", e);
		}
		return baseList;
	}
	
	@RequestMapping(value = "/getFeeServiceType")
	public List<BaseCode> getFeeServiceType(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
		List<BaseCode> baseList = new ArrayList<BaseCode>();
		try {
			baseList = ParamController.getSysParams(
				"PUB", "FEE_SERVICE_TYPE", TradeSeqUtil.newTradeSeq(user.getTenantId()));
		} catch (Exception e) {
			LOGGER.info("获取菜单失败", e);
		}
		return baseList;
	}
	/**
	 * 获取错单编码
	 * @param request
	 * @return
	 * @author gaogang
	 */
	@RequestMapping("/getFailCode")
	public List<BaseCode> getFailCode(HttpServletRequest request){
		List<BaseCode> baseList = new ArrayList<BaseCode>();
		try {
			baseList = ParamController.getSysParams(
				BaaSMTConstants.TENANTID, "FAIL_CODE", TradeSeqUtil.newTradeSeq("PUB"));
		} catch (Exception e) {
			LOGGER.info("获取菜单失败", e);
		}
		return baseList;
	}
	/**
	 * 获取订单错单编码
	 * @param request
	 * @return
	 * @author gaogang
	 */
	@RequestMapping("/getOrderFailCode")
	public List<BaseCode> getOrderFailCode(HttpServletRequest request){
		List<BaseCode> baseList = new ArrayList<BaseCode>();
		try {
			baseList = ParamController.getSysParams(
					BaaSMTConstants.TENANTID, "ORDER_FAIL_CODE", TradeSeqUtil.newTradeSeq("PUB"));
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
	    	IBaseInfoSV iBaseInfoSV = DubboConsumerFactory.getService(IBaseInfoSV.class);
	        
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
	    	IBaseInfoSV iBaseInfoSV = DubboConsumerFactory.getService(IBaseInfoSV.class);
	        
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
