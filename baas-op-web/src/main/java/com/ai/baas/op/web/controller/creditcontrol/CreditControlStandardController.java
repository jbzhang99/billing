package com.ai.baas.op.web.controller.creditcontrol;

import com.ai.baas.omc.api.rule.interfaces.IOmcRuleSV;
import com.ai.baas.omc.api.rule.params.*;
import com.ai.baas.op.web.constants.Constants.ResultCode;
import com.ai.baas.op.web.constants.VerifyConstants.ResultCodeConstants;
import com.ai.baas.op.web.util.TradeSeqUtil;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.web.model.ResponseData;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import com.ai.opt.sso.client.filter.SSOClientUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 信控规则控制
 * 
 * @author jiaxs
 *
 */
@RequestMapping("/creditControl/standard")
@Controller
public class CreditControlStandardController {

	public static final int pageSizeDef = 5;

	@RequestMapping("/list")
	public ModelAndView gotoStandardListPage(HttpServletRequest request) {
		return new ModelAndView("/jsp/creditcontrol/standardList");
	}

	@RequestMapping("/searchList")
	@ResponseBody
	public ResponseData<PageInfo<OmcRuleInfoVO>> searchStandardList(HttpServletRequest request, QueryInfoParam params) {
		IOmcRuleSV iOmcRuleSV = DubboConsumerFactory.getService("iOmcRuleSV");
		SSOClientUser userClient = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
		Integer pageNo = params.getPageNo();
		if (pageNo == null) {
			params.setPageNo(1);
		}
		Integer pageSize = params.getPageSize();
		if (pageSize == null) {
			params.setPageSize(pageSizeDef);
		}
		String newTradeSeq = TradeSeqUtil.newTradeSeq(userClient.getTenantId());
		params.setTradeSeq(newTradeSeq);
		QueryRuleResponse queryRule = iOmcRuleSV.queryRule(params);
		PageInfo<OmcRuleInfoVO> omcRuleInfoVOs = queryRule.getOmcRuleInfoVOs();

		ResponseData<PageInfo<OmcRuleInfoVO>> responseData = new ResponseData<PageInfo<OmcRuleInfoVO>>(ResultCode.SUCCESS_CODE, "查询成功", omcRuleInfoVOs);
		ResponseHeader responseHeader = new ResponseHeader(true, ResultCodeConstants.SUCCESS_CODE, "成功");
		responseData.setResponseHeader(responseHeader);
		return responseData;
	}

	@RequestMapping("/add")
	public ModelAndView gotoAddPage(HttpServletRequest request) {
		return new ModelAndView("/jsp/creditcontrol/addStandard");
	}

	@RequestMapping("/insertStandard")
	@ResponseBody
	public ResponseData<String> insertStandard(HttpServletRequest request, OmcRuleInfoVO omcruleinfovo) {
		IOmcRuleSV iOmcRuleSV = DubboConsumerFactory.getService("iOmcRuleSV");
		SSOClientUser userClient = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
		String tenantId = userClient.getTenantId();
		String newTradeSeq = TradeSeqUtil.newTradeSeq(tenantId);
		omcruleinfovo.setTradeSeq(newTradeSeq);
		omcruleinfovo.setTenantId(tenantId);
		OmcRuleResponse addRule = iOmcRuleSV.addRule(omcruleinfovo);
		Long ruleId = addRule.getRuleId();
		ResponseData<String> responseData = null;
		ResponseHeader responseHeader = null;
		if (ruleId != null) {
			responseData = new ResponseData<String>(ResultCode.SUCCESS_CODE, "添加成功", "/creditControl/standard/list");
			responseHeader = new ResponseHeader(true, ResultCodeConstants.SUCCESS_CODE, "添加成功");
		} else {
			responseData = new ResponseData<String>(ResultCode.ERROR_CODE, "添加失败", null);
			responseHeader = new ResponseHeader(false, ResultCodeConstants.ERROR_CODE, "添加失败");
		}
		responseData.setResponseHeader(responseHeader);
		return responseData;
	}

	@RequestMapping("/edit")
	public ModelAndView gotoEditPage(HttpServletRequest request) {
		String ruleIdStr = request.getParameter("ruleId");
		Long ruleId = Long.valueOf(ruleIdStr);
		IOmcRuleSV iOmcRuleSV = DubboConsumerFactory.getService("iOmcRuleSV");
		GetRuleInfoParam getRuleInfoParam = new GetRuleInfoParam();
		getRuleInfoParam.setRuleId(ruleId);
		SSOClientUser userClient = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
		String tenantId = userClient.getTenantId();
		getRuleInfoParam.setTenantId(tenantId);
		String newTradeSeq = TradeSeqUtil.newTradeSeq(tenantId);
		getRuleInfoParam.setTradeSeq(newTradeSeq);
		GetRuleResponse ruleResponse = iOmcRuleSV.getRule(getRuleInfoParam);
		OmcRuleInfoVO ruleInfo = ruleResponse.getOmcRuleInfoVO();

		Map<String, OmcRuleInfoVO> model = new HashMap<String, OmcRuleInfoVO>();
		if (ruleInfo != null) {
			model.put("ruleInfo", ruleInfo);
		}else{
			model.put("ruleInfo", new OmcRuleInfoVO());
		}
		return new ModelAndView("/jsp/creditcontrol/editStandard", model);
	}

	@RequestMapping("/modifyStandard")
	@ResponseBody
	public ResponseData<String> modifyStandard(HttpServletRequest request, OmcRuleInfoVO ruleInfoVO) {
		IOmcRuleSV iOmcRuleSV = DubboConsumerFactory.getService("iOmcRuleSV");
		SSOClientUser userClient = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
		String tenantId = userClient.getTenantId();
		String newTradeSeq = TradeSeqUtil.newTradeSeq(tenantId);
		ruleInfoVO.setTenantId(tenantId);
		ruleInfoVO.setTradeSeq(newTradeSeq);
		OmcRuleResponse updateRule = iOmcRuleSV.updateRule(ruleInfoVO);
		ResponseHeader responseHeader = null;
		ResponseData<String> responseData = null;
		if (updateRule.getResponseHeader().isSuccess()) {
			responseData = new ResponseData<String>(ResultCode.SUCCESS_CODE, "修改成功");
			responseHeader = new ResponseHeader(true, ResultCodeConstants.SUCCESS_CODE, "修改成功");
		} else {
			responseData = new ResponseData<String>(ResultCode.ERROR_CODE, "修改失败");
			responseHeader = new ResponseHeader(false, ResultCodeConstants.ERROR_CODE, "修改失败");
		}
		responseData.setResponseHeader(responseHeader);
		return responseData;
	}

	@RequestMapping("/deleteStandard")
	@ResponseBody
	public ResponseData<String> deleteStandard(HttpServletRequest request, OmcRuleInfoVO omcruleinfovo) {
		IOmcRuleSV iOmcRuleSV = DubboConsumerFactory.getService("iOmcRuleSV");
		SSOClientUser userClient = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
		String tenantId = userClient.getTenantId();
		String newTradeSeq = TradeSeqUtil.newTradeSeq(tenantId);
		omcruleinfovo.setTradeSeq(newTradeSeq);
		omcruleinfovo.setTenantId(tenantId);
		BaseResponse delRule = iOmcRuleSV.delRule(omcruleinfovo);
		ResponseData<String> responseData = null;
		ResponseHeader deleteHeader = null;
		if (delRule.getResponseHeader().isSuccess()) {
			responseData = new ResponseData<String>(ResultCode.SUCCESS_CODE, "删除成功");
			deleteHeader = new ResponseHeader(true, ResultCodeConstants.SUCCESS_CODE, "删除成功");
		} else {
			responseData = new ResponseData<String>(ResultCode.ERROR_CODE, "删除失败");
			deleteHeader = new ResponseHeader(false, ResultCodeConstants.ERROR_CODE, "删除失败");
		}
		responseData.setResponseHeader(deleteHeader);
		return responseData;
	}
}
