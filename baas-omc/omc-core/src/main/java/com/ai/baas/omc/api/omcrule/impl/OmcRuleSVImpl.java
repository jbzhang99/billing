package com.ai.baas.omc.api.omcrule.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
//import org.apache.hadoop.hbase.client.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.omc.api.rule.interfaces.IOmcRuleSV;
import com.ai.baas.omc.api.rule.params.GetRuleInfoParam;
import com.ai.baas.omc.api.rule.params.GetRuleResponse;
import com.ai.baas.omc.api.rule.params.OmcRuleInfoVO;
import com.ai.baas.omc.api.rule.params.OmcRuleResponse;
import com.ai.baas.omc.api.rule.params.QueryInfoParam;
import com.ai.baas.omc.api.rule.params.QueryRuleResponse;
import com.ai.baas.omc.constants.Context;
import com.ai.baas.omc.constants.ExceptCodeConstant;
import com.ai.baas.omc.constants.RuleType;
import com.ai.baas.omc.constants.TableCon;
import com.ai.baas.omc.constants.TableCon.ConTradeSeqLog;
import com.ai.baas.omc.dao.mapper.bo.OmcScoutRule;
import com.ai.baas.omc.dao.mapper.bo.OmcScoutRuleCriteria;
import com.ai.baas.omc.service.atom.interfaces.IOmcRuleAtom;
//import com.ai.baas.omc.util.MyHbaseUtil;
//import com.ai.baas.omc.util.MyHbaseUtil.CellTemp;
import com.ai.baas.omc.util.MyJsonUtil;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.ResponseHeader;
//import com.ai.opt.sdk.sequence.util.SeqUtil;
import com.ai.opt.sdk.components.sequence.util.SeqUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.alibaba.dubbo.config.annotation.Service;

@Service(validation = "true")
@Component
public class OmcRuleSVImpl implements IOmcRuleSV {

	@Autowired
	private IOmcRuleAtom omcRuleAtom;

	@Override
	public OmcRuleResponse addRule(OmcRuleInfoVO vo) throws BusinessException, SystemException {
		if (vo.getTenantId() == null) {
			throw new BusinessException(Context.MissTenantId, "tenantId不能空！");
		}
		if (vo.getPressPayment() < 0) {
			throw new BusinessException(Context.ValidateParamError, "催缴值金额不能小于0！");
		}
		OmcRuleResponse omcRuleResponse = new OmcRuleResponse();
		String returnFlag = this.hasSeq(vo);
		if (returnFlag.equals("exit")) {
			ResponseHeader responseHeader = new ResponseHeader(false, Context.EXIST, "tradeSeq已使用");
			omcRuleResponse.setResponseHeader(responseHeader);
			return omcRuleResponse;
		}

		OmcScoutRule omcScoutRule = new OmcScoutRule();
		omcScoutRule.setPolicyid(1L);
		omcScoutRule.setScoutRule(vo.getRuleName());
		omcScoutRule.setScoutType(RuleType.stop.toString());
		omcScoutRule.setBalanceFloor(-999999999L);
		omcScoutRule.setBalanceCeil(0L);
		omcScoutRule.setOweMaxdays(0);
		omcScoutRule.setOweMindays(0);
		omcScoutRule.setChargeCeil(0L);
		omcScoutRule.setChargeFloor(0L);
		omcScoutRule.setCustType("0");
		omcScoutRule.setAcctType("0");
		omcScoutRule.setUserType("0");
		omcScoutRule.setCustLevel("A");
		omcScoutRule.setTenantId(vo.getTenantId());
		// 暂时使用sectiontype字段存一下description
		omcScoutRule.setSectionType(vo.getDescription());
		Long ruleId = SeqUtil.getNewId("OMC_SCOUT_RULE$RULE_ID$SEQ");
		omcScoutRule.setRuleId(ruleId);
		omcRuleAtom.addOmcScoutRule(omcScoutRule);

		omcScoutRule.setScoutType(RuleType.warning.toString());
		omcScoutRule.setBalanceFloor(0L);
		omcScoutRule.setBalanceCeil(vo.getPressPayment());
		omcRuleAtom.addOmcScoutRule(omcScoutRule);

		omcScoutRule.setScoutType(RuleType.start.toString());
		omcScoutRule.setBalanceFloor(0L);
		omcScoutRule.setBalanceCeil(999999999L);
		omcRuleAtom.addOmcScoutRule(omcScoutRule);

		omcScoutRule.setScoutType(RuleType.warnoff.toString());
		omcScoutRule.setBalanceFloor(vo.getPressPayment());
		omcScoutRule.setBalanceCeil(999999999L);
		omcRuleAtom.addOmcScoutRule(omcScoutRule);

		omcRuleResponse.setRuleId(omcScoutRule.getRuleId());
		omcRuleResponse.setTradeSeq(vo.getTradeSeq());
		ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
		omcRuleResponse.setResponseHeader(responseHeader);
		return omcRuleResponse;
	}

	@Override
	public OmcRuleResponse updateRule(OmcRuleInfoVO vo) throws BusinessException, SystemException {
		if (vo.getTenantId() == null) {
			throw new BusinessException(Context.MissTenantId, "tenantId不能空！");
		}
		if (vo.getPressPayment() < 0) {
			throw new BusinessException(Context.ValidateParamError, "催缴值金额不能小于0！");
		}
		OmcRuleResponse omcRuleResponse = new OmcRuleResponse();
		String returnFlag = this.hasSeq(vo);
		if (returnFlag.equals("exit")) {
			ResponseHeader responseHeader = new ResponseHeader(false, Context.EXIST, "tradeSeq已使用");
			omcRuleResponse.setResponseHeader(responseHeader);
			return omcRuleResponse;
		}

		OmcScoutRule omcScoutRule = new OmcScoutRule();
		omcScoutRule.setScoutType(RuleType.stop.toString());
		omcScoutRule.setBalanceFloor(-999999999L);
		omcScoutRule.setBalanceCeil(0L);
		omcScoutRule.setScoutRule(vo.getRuleName());
		omcScoutRule.setSectionType(vo.getDescription());
		omcScoutRule.setRuleId(vo.getRuleId());
		omcRuleAtom.updateOmcScoutRule(omcScoutRule);

		omcScoutRule.setScoutType(RuleType.warning.toString());
		omcScoutRule.setBalanceFloor(0L);
		omcScoutRule.setBalanceCeil(vo.getPressPayment());
		omcRuleAtom.updateOmcScoutRule(omcScoutRule);

		omcScoutRule.setScoutType(RuleType.start.toString());
		omcScoutRule.setBalanceFloor(0L);
		omcScoutRule.setBalanceCeil(999999999L);
		omcRuleAtom.updateOmcScoutRule(omcScoutRule);

		omcScoutRule.setScoutType(RuleType.warnoff.toString());
		omcScoutRule.setBalanceFloor(vo.getPressPayment());
		omcScoutRule.setBalanceCeil(999999999L);
		omcRuleAtom.updateOmcScoutRule(omcScoutRule);

		omcRuleResponse.setRuleId(omcScoutRule.getRuleId());
		ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
		omcRuleResponse.setResponseHeader(responseHeader);
		return omcRuleResponse;
	}

	@Override
	public BaseResponse delRule(OmcRuleInfoVO vo) throws BusinessException, SystemException {
		if (vo.getTenantId() == null) {
			throw new BusinessException(Context.MissTenantId, "tenantId不能空！");
		}
		BaseResponse BaseResponse = new BaseResponse();
		String returnFlag = this.hasSeq(vo);
		if (returnFlag.equals("exit")) {
			ResponseHeader responseHeader = new ResponseHeader(false, Context.EXIST, "tradeSeq已使用");
			BaseResponse.setResponseHeader(responseHeader);
			return BaseResponse;
		}
		OmcScoutRule omcScoutRule = new OmcScoutRule();
		omcScoutRule.setRuleId(vo.getRuleId());
		omcRuleAtom.deleteOmcScoutRule(omcScoutRule);
		ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
		BaseResponse.setResponseHeader(responseHeader);
		return BaseResponse;
	}

	@Override
	public QueryRuleResponse queryRule(QueryInfoParam param) throws BusinessException, SystemException {
		QueryRuleResponse queryRuleResponse = new QueryRuleResponse();
		OmcScoutRuleCriteria omcScoutRuleCriteria = new OmcScoutRuleCriteria();
		OmcScoutRuleCriteria.Criteria criteria = omcScoutRuleCriteria.createCriteria();
		criteria.andScoutTypeEqualTo(RuleType.warning.toString());
		if (StringUtils.isNotEmpty(param.getRuleName())) {
			criteria.andScoutRuleLike(param.getRuleName());
		}
		int count = omcRuleAtom.getCount(omcScoutRuleCriteria);
		omcScoutRuleCriteria.setLimitStart((param.getPageNo() - 1) * param.getPageSize());
		omcScoutRuleCriteria.setLimitEnd(param.getPageNo() * param.getPageSize());
		List<OmcScoutRule> rules = omcRuleAtom.getOmcScoutRule(omcScoutRuleCriteria);
		if (CollectionUtils.isNotEmpty(rules)) {
			PageInfo<OmcRuleInfoVO> page = new PageInfo<OmcRuleInfoVO>();
			page.setPageNo(param.getPageNo());
			page.setPageSize(param.getPageSize());
			page.setCount(count);
			List<OmcRuleInfoVO> resultList = new ArrayList<>();
			for (OmcScoutRule rule : rules) {
				OmcRuleInfoVO omcRuleInfoVO = new OmcRuleInfoVO();
				omcRuleInfoVO.setRuleId(rule.getRuleId());
				omcRuleInfoVO.setRuleName(rule.getScoutRule());
				omcRuleInfoVO.setDescription(rule.getSectionType());
				omcRuleInfoVO.setPressPayment(rule.getBalanceCeil());
				resultList.add(omcRuleInfoVO);
			}
			page.setResult(resultList);
			queryRuleResponse.setOmcRuleInfoVOs(page);
		}
		ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
		queryRuleResponse.setResponseHeader(responseHeader);
		return queryRuleResponse;
	}

	@Override
	public GetRuleResponse getRule(GetRuleInfoParam param) throws BusinessException, SystemException {
		GetRuleResponse getRuleResponse = new GetRuleResponse();
		OmcScoutRuleCriteria omcScoutRuleCriteria = new OmcScoutRuleCriteria();
		OmcScoutRuleCriteria.Criteria criteria = omcScoutRuleCriteria.createCriteria();
		criteria.andScoutTypeEqualTo(RuleType.warning.toString());
		if (param.getRuleId() != null) {
			criteria.andRuleIdEqualTo(param.getRuleId());
		} else
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "miss rule_id");
		List<OmcScoutRule> rules = omcRuleAtom.getOmcScoutRule(omcScoutRuleCriteria);
		if (CollectionUtils.isNotEmpty(rules)) {
			OmcRuleInfoVO omcRuleInfoVO = new OmcRuleInfoVO();
			OmcScoutRule omcScoutRule = rules.get(0);
			omcRuleInfoVO.setRuleId(omcScoutRule.getRuleId());
			omcRuleInfoVO.setRuleName(omcScoutRule.getScoutRule());
			omcRuleInfoVO.setPressPayment(omcScoutRule.getBalanceCeil());
			omcRuleInfoVO.setDescription(omcScoutRule.getSectionType());
			getRuleResponse.setOmcRuleInfoVO(omcRuleInfoVO);
		}
		ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
		getRuleResponse.setResponseHeader(responseHeader);
		return getRuleResponse;
	}

	/**
	 * 判断序列号是否存在
	 * 
	 * @param vo
	 * @return
	 * @throws SystemException
	 * @author bixy
	 */
	private String hasSeq(OmcRuleInfoVO vo) throws SystemException {
		String flag = "noexit";
//		try {
//			String rowkey = vo.getTenantId() + Context.SPLIT + Context.AddProduct + Context.SPLIT + vo.getTradeSeq();
//			Table table = MyHbaseUtil.getTable(TableCon.TRADE_SEQ_LOG);
//			System.out.println("-------hasSeq:" + table + "--->" + rowkey);
//			if (MyHbaseUtil.hasExists(table, rowkey)) {
//				flag = "exit";
//				return flag;
//			}
//			MyHbaseUtil.addData(table, rowkey, CellTemp.inst(ConTradeSeqLog.TENANT_ID, vo.getTenantId()), CellTemp.inst(ConTradeSeqLog.INTERFACE_CODE, Context.AddProduct),
//					CellTemp.inst(ConTradeSeqLog.TRADE_SEQ, vo.getTradeSeq()), CellTemp.inst(ConTradeSeqLog.RECEIVE_TIME, DateUtil.getDateString(DateUtil.YYYYMMDDHHMMSS)),
//					CellTemp.inst(ConTradeSeqLog.MSG_CONTENT, MyJsonUtil.toJson(vo)));
//		} catch (Exception e) {
//			throw new SystemException(e);
//		}
		return flag;

	}
}

