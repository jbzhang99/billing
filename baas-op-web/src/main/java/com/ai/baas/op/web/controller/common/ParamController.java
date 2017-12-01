package com.ai.baas.op.web.controller.common;

import com.ai.baas.bmc.api.baseInfo.interfaces.IBaseInfoSV;
import com.ai.baas.bmc.api.baseInfo.params.*;
import com.ai.baas.op.web.util.TradeSeqUtil;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import com.ai.opt.sso.client.filter.SSOClientUser;
import com.ai.opt.sys.api.gnsubject.interfaces.IGnSubjectQuerySV;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectDetailVo;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectListResponse;
import com.ai.paas.ipaas.util.StringUtil;
import net.sf.json.JSONArray;
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
	
	
	@RequestMapping(value = "/getStandardUnit")
	public List<BaseCode> getStandardUnit(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
		List<BaseCode> baseList = new ArrayList<BaseCode>();
		try {
			baseList = ParamController.getSysParams(
				"PUB", "STANDARD_UNIT", TradeSeqUtil.newTradeSeq(user.getTenantId()));
		} catch (Exception e) {
			LOGGER.info("获取菜单失败", e);
		}
		return baseList;
	}
	
	@RequestMapping(value = "/getStandardStatus")
	public List<BaseCode> getStandardStatus(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
		List<BaseCode> baseList = new ArrayList<BaseCode>();
		try {
			baseList = ParamController.getSysParams(
				"PUB", "STANDARD_STATUS", TradeSeqUtil.newTradeSeq(user.getTenantId()));
		} catch (Exception e) {
			LOGGER.info("获取菜单失败", e);
		}
		return baseList;
	}
	
	@RequestMapping(value = "/getServiceType")
	public List<BaseCode> getServiceType(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
		List<BaseCode> baseList = new ArrayList<BaseCode>();
		try {
			baseList = ParamController.getSysParams(
				"PUB", "SERVICE_TYPE", TradeSeqUtil.newTradeSeq(user.getTenantId()));
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

	/**
	 * 获取组合套餐计费产品类型
	 * @param request
	 * @return
     */
	@RequestMapping(value = "/getGroupBillingType")
	public List<BaseCode> getGroupBillingType(HttpServletRequest request) {
		HttpSession session = request.getSession();
		SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
		List<BaseCode> baseList = new ArrayList<BaseCode>();
		try {
			baseList = ParamController.getSysParams(
					user.getTenantId(), "GROUP_BILLING_TYPE", TradeSeqUtil.newTradeSeq(user.getTenantId()));
		} catch (Exception e) {
			LOGGER.info("获取信息出错", e);
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
		SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
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
	 * 获取单位列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getUnitType")
	public List<BaseCode> getUnitType(HttpServletRequest request) {
		HttpSession session = request.getSession();
		SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
		List<BaseCode> baseList = new ArrayList<BaseCode>();
		try {
			baseList = ParamController.getSysParams(
					user.getTenantId(), "UNIT", TradeSeqUtil.newTradeSeq(user.getTenantId()));
		} catch (Exception e) {
			LOGGER.info("获取信息出错", e);
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
    
    /**
     * 获取账单优惠－优惠类型
     * @param request
     * @return
     */
    @RequestMapping(value = "/getDiscountType")
    public List<BaseCode> getDiscountType(HttpServletRequest request) {
        HttpSession session = request.getSession();
        SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
        List<BaseCode> baseList = new ArrayList<BaseCode>();
        try {
            baseList = ParamController.getSysParams(user.getTenantId(), "BILL_DISCOUNT_TYPE", TradeSeqUtil.newTradeSeq(user.getTenantId()));
        } catch (Exception e) {
            LOGGER.info("获取信息出错", e);
        }
        return baseList;
    }
    
    /**
     * 获取账单优惠－赠送业务生效方式
     * @param request
     * @return
     */
    @RequestMapping(value = "/getGiftActiveMode")
    public List<BaseCode> getGiftActiveMode(HttpServletRequest request) {
        HttpSession session = request.getSession();
        SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
        List<BaseCode> baseList = new ArrayList<BaseCode>();
        try {
            baseList = ParamController.getSysParams(user.getTenantId(), "GIFT_ACTIVE_MODE", TradeSeqUtil.newTradeSeq(user.getTenantId()));
        } catch (Exception e) {
            LOGGER.info("获取信息出错", e);
        }
        return baseList;
    }
    
    /**
     * 获取账单优惠－赠送业务周期
     * @param request
     * @return
     */
    @RequestMapping(value = "/getGiftActivePeriod")
    public List<BaseCode> getGiftActivePeriod(HttpServletRequest request) {
        HttpSession session = request.getSession();
        SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
        List<BaseCode> baseList = new ArrayList<BaseCode>();
        try {
            baseList = ParamController.getSysParams(user.getTenantId(), "GIFT_ACTIVE_PERIOD", TradeSeqUtil.newTradeSeq(user.getTenantId()));
        } catch (Exception e) {
            LOGGER.info("获取信息出错", e);
        }
        return baseList;
    }
    
    /**
     * 关联账单科目下拉菜单查询
     */
    @RequestMapping("/getRelatedSubjectList")
    public List<GnSubjectListResponse> getRelatedSubjectList(HttpServletRequest request){
        
        List<GnSubjectListResponse> resultList = null;
        try {
            String subjectType = request.getParameter("subjectType");
            if(StringUtil.isBlank(subjectType)){
                return resultList;
            }
            
            SSOClientUser user = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
            GnSubjectDetailVo queryParam = new GnSubjectDetailVo();
            queryParam.setTenantId(user.getTenantId());
            queryParam.setSubjectType(subjectType);
            
            IGnSubjectQuerySV iGnSubjectQuerySV = DubboConsumerFactory.getService("iGnSubjectQuerySV");
            LOGGER.error("关联科目下拉菜单查询入参:" + JSONArray.fromObject(queryParam));
            resultList = iGnSubjectQuerySV.getGnSubjectListMayRelated(queryParam);
            LOGGER.error("关联科目下拉菜单查询出参:" + JSONArray.fromObject(resultList));
            
        } catch (Exception e) {
            LOGGER.error("获取信息出错：", e);
        }
        return resultList;
    }
    
    /**
     * 资料管理:测试URL
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getDataTestUrl")
	public List<BaseCode> getDataTestUrl(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
		List<BaseCode> baseList = new ArrayList<BaseCode>();
		try {
			baseList = ParamController.getSysParams(
					"PUB", "DATA_TEST_URL", TradeSeqUtil.newTradeSeq(user.getTenantId()));
		} catch (Exception e) {
			LOGGER.info("获取菜单失败", e);
		}
		return baseList;
	}
    
    /**
     * 资料管理:正式URL
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getDataFormalUrl")
	public List<BaseCode> getDataFormalUrl(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
		List<BaseCode> baseList = new ArrayList<BaseCode>();
		try {
			baseList = ParamController.getSysParams(
					"PUB", "DATA_FORMAL_URL", TradeSeqUtil.newTradeSeq(user.getTenantId()));
		} catch (Exception e) {
			LOGGER.info("获取菜单失败", e);
		}
		return baseList;
	}
    
    /**
     * 资料管理:口令
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getDataPassword")
	public List<BaseCode> getDataPassword(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
		List<BaseCode> baseList = new ArrayList<BaseCode>();
		try {
			baseList = ParamController.getSysParams(
					"PUB", "DATA_PASSWORD", TradeSeqUtil.newTradeSeq(user.getTenantId()));
		} catch (Exception e) {
			LOGGER.info("获取菜单失败", e);
		}
		return baseList;
	}
    
    /**
     * 资料管理:处理数据上限
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getDataLimitUpnum")
	public List<BaseCode> getDataLimitUpnum(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
		List<BaseCode> baseList = new ArrayList<BaseCode>();
		try {
			baseList = ParamController.getSysParams(
					"PUB", "DATA_LIMIT_UPNUM", TradeSeqUtil.newTradeSeq(user.getTenantId()));
		} catch (Exception e) {
			LOGGER.info("获取菜单失败", e);
		}
		return baseList;
	}
    
    /**
     * 获得优惠单位
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getCostUnit")
	public List<BaseCode> getCostUnit(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
		List<BaseCode> baseList = new ArrayList<BaseCode>();
		try {
			baseList = ParamController.getSysParams(
				"PUB", "COST_UNIT_ID", TradeSeqUtil.newTradeSeq(user.getTenantId()));
		} catch (Exception e) {
			LOGGER.info("获取菜单失败", e);
		}
		return baseList;
	}
    
    /**
     * 获得优惠规则类型
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getDiscountRullType")
	public List<BaseCode> getDiscountRullType(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
		List<BaseCode> baseList = new ArrayList<BaseCode>();
		try {
			baseList = ParamController.getSysParams(
				"PUB", "DISCOUNT_RULL_TYPE", TradeSeqUtil.newTradeSeq(user.getTenantId()));
		} catch (Exception e) {
			LOGGER.info("获取菜单失败", e);
		}
		return baseList;
	}
    
    /**
     * 获得优惠券类型
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getReduceType")
	public List<BaseCode> getReduceType(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
		List<BaseCode> baseList = new ArrayList<BaseCode>();
		try {
			baseList = ParamController.getSysParams(
				"PUB", "REDUCE_TYPE", TradeSeqUtil.newTradeSeq(user.getTenantId()));
		} catch (Exception e) {
			LOGGER.info("获取菜单失败", e);
		}
		return baseList;
	}
    
    /**
     * 获得优惠类型
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getDiscountUnit")
	public List<BaseCode> getDiscountUnit(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
		List<BaseCode> baseList = new ArrayList<BaseCode>();
		try {
			baseList = ParamController.getSysParams(
				"PUB", "DISCOUNT_UNIT_ID", TradeSeqUtil.newTradeSeq(user.getTenantId()));
		} catch (Exception e) {
			LOGGER.info("获取菜单失败", e);
		}
		return baseList;
	}
}
