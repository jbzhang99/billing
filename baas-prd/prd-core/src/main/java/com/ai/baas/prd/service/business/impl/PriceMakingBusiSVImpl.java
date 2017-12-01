package com.ai.baas.prd.service.business.impl;


import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.prd.api.pricemaking.params.DetailInfoVO;
import com.ai.baas.prd.api.pricemaking.params.ElementInfoVO;
import com.ai.baas.prd.api.pricemaking.params.MemberVO;
import com.ai.baas.prd.api.pricemaking.params.PriceFactorVO;
import com.ai.baas.prd.api.pricemaking.params.PriceInfoVO;
import com.ai.baas.prd.api.pricemaking.params.PriceMakingAddParam;
import com.ai.baas.prd.api.pricemaking.params.PriceMakingDelParam;
import com.ai.baas.prd.api.pricemaking.params.SpecificationVO;
import com.ai.baas.prd.dao.mapper.bo.CpPricemakingFactor;
import com.ai.baas.prd.dao.mapper.bo.CpPricemakingRule;
import com.ai.baas.prd.service.atom.interfaces.ICpPricemakingFactorAtomSV;
import com.ai.baas.prd.service.atom.interfaces.ICpPricemakingRuleAtomSV;
import com.ai.baas.prd.service.business.interfaces.IPriceMakingBusiSV;
import com.ai.baas.prd.util.PriceMakingUtil;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.util.CollectionUtil;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;


@Service
@Transactional
public class PriceMakingBusiSVImpl implements IPriceMakingBusiSV {
	private static Logger logger = LoggerFactory.getLogger(PriceMakingBusiSVImpl.class);
	public final static String PRICE_MAKING_FACTOR_KEY = "PriceMakingFactor";
	public final static String PRICE_MAKING_RULE_KEY = "PriceMakingRule";
	
	@Autowired
	ICpPricemakingFactorAtomSV pricemakingFactorAtomSV;
	
	@Autowired
	ICpPricemakingRuleAtomSV pricemakingRuleAtomSV;

	@Override
	public void addPriceMaking(PriceMakingAddParam param) {
		ListMultimap<String,Object> priceMakingMultimap = assemblePriceMakingEntity(param);
		List<Object> pricemakingFactors = priceMakingMultimap.get(PRICE_MAKING_FACTOR_KEY);
		for(Object obj:pricemakingFactors){
			pricemakingFactorAtomSV.addCpPricemakingFactor((CpPricemakingFactor)obj);
		}
		List<Object> pricemakingRules = priceMakingMultimap.get(PRICE_MAKING_RULE_KEY);
		for(Object obj:pricemakingRules){
			pricemakingRuleAtomSV.addCpPricemakingRule((CpPricemakingRule)obj);
		}
		
		
		
	}

	@Override
	public void modifyPriceMaking(PriceMakingAddParam param) {
		ListMultimap<String,Object> priceMakingMultimap = assemblePriceMakingEntity(param);
		List<Object> pricemakingFactors = priceMakingMultimap.get(PRICE_MAKING_FACTOR_KEY);
		Set<String> productIds = new HashSet<String>();
		String priceProductId = "";
		for(Object obj:pricemakingFactors){
			CpPricemakingFactor pricemakingFactor = (CpPricemakingFactor)obj;
			priceProductId = pricemakingFactor.getPriceProductId();
			if(!productIds.contains(priceProductId)){
				pricemakingFactorAtomSV.delCpPricemakingFactorByProductId(pricemakingFactor.getTenantId(), priceProductId);
				productIds.add(priceProductId);
			}
			pricemakingFactorAtomSV.addCpPricemakingFactor(pricemakingFactor);
		}
		productIds.clear();
		
		List<Object> pricemakingRules = priceMakingMultimap.get(PRICE_MAKING_RULE_KEY);
		for(Object obj:pricemakingRules){
			CpPricemakingRule pricemakingRule = (CpPricemakingRule)obj;
			priceProductId = pricemakingRule.getPriceProductId();
			if(!productIds.contains(priceProductId)){
				pricemakingRuleAtomSV.delCpPricemakingRuleByProductId(pricemakingRule.getTenantId(), priceProductId);
				productIds.add(priceProductId);
			}
			pricemakingRuleAtomSV.addCpPricemakingRule(pricemakingRule);
		}
		productIds.clear();
		
	}

	@Override
	public void deletePriceMaking(PriceMakingDelParam param) {
		String tenantId = "",detailId = "";
		for(DetailInfoVO detailInfo:param.getDetailInfoList()){
			tenantId = detailInfo.getTenantId();
			detailId = detailInfo.getDetailId();
			if(StringUtils.isBlank(tenantId)||StringUtils.isBlank(detailId)){
				throw new BusinessException("租户或明细ID为空!");
			}
			pricemakingFactorAtomSV.delCpPricemakingFactorByProductId(tenantId, detailId);
			pricemakingRuleAtomSV.delCpPricemakingRuleByProductId(tenantId, detailId);
		}
	}
	
	
	private ListMultimap<String,Object> assemblePriceMakingEntity(PriceMakingAddParam param){
		ListMultimap<String, Object> rtnMultimap = ArrayListMultimap.create();
		String tenantIdStr = param.getTenantId();
		List<MemberVO> members = null;
		List<SpecificationVO> specList = null;
		for(PriceFactorVO priceFactor:param.getPriceFactorList()){
			members = priceFactor.getMemberList();
			specList = priceFactor.getSpecList();
			if (CollectionUtil.isEmpty(members) || CollectionUtil.isEmpty(specList)) {
				throw new BusinessException("类目构成节点或规格列表为空!");
			}
			for (SpecificationVO spec:specList) {
				addPriceMakingData(rtnMultimap, spec, members, tenantIdStr);			
			}
		}
		return rtnMultimap;
	}
	
	
	private void addPriceMakingData(ListMultimap<String, Object> rtnMultimap,SpecificationVO spec,List<MemberVO> members,String tenantId){
		String specTypeId = spec.getSpecTypeId();
		String billingCycle = spec.getBillingCycle();
		String billingRule = spec.getBillingRule();
		String elementId = "";
		for(PriceInfoVO priceInfo:spec.getPriceInfoList()){
			elementId = priceInfo.getElementId();
			for (MemberVO member : members) {
				CpPricemakingFactor pricemakingFactor = new CpPricemakingFactor();
				pricemakingFactor.setTenantId(tenantId);
				pricemakingFactor.setPriceProductType(specTypeId);
				pricemakingFactor.setPriceProductId(elementId);
				pricemakingFactor.setFactorName(member.getDimension());
				pricemakingFactor.setFactorValue(member.getBranch());
				
				rtnMultimap.put(PRICE_MAKING_FACTOR_KEY, pricemakingFactor);
			}
			for(ElementInfoVO elementInfo:priceInfo.getElementInfoList()){
				CpPricemakingFactor pricemakingFactor = new CpPricemakingFactor();
				pricemakingFactor.setTenantId(tenantId);
				pricemakingFactor.setPriceProductType(specTypeId);
				pricemakingFactor.setPriceProductId(elementId);
				pricemakingFactor.setFactorName(elementInfo.getVarCode());
				pricemakingFactor.setFactorValue(elementInfo.getVarValue());
				
				rtnMultimap.put(PRICE_MAKING_FACTOR_KEY, pricemakingFactor);
			}
			CpPricemakingRule pricemakingRule = new CpPricemakingRule();
			pricemakingRule.setTenantId(tenantId);
			pricemakingRule.setPriceProductType(specTypeId);
			pricemakingRule.setPriceProductId(elementId);
			pricemakingRule.setPriceType(PriceMakingUtil.ruleMapping(tenantId, billingCycle));
			pricemakingRule.setRuleCode(PriceMakingUtil.ruleMapping(tenantId, billingRule));
			pricemakingRule.setRuleExpresion(priceInfo.getPrice());
			pricemakingRule.setExtInfo(priceInfo.getExtInfo());
			pricemakingRule.setActiveTime(new Timestamp(System.currentTimeMillis()));
			//pricemakingRule.setInactiveTime(new Timestamp(System.currentTimeMillis()));
			//pricemakingRule.setInactiveTime(Timestamp.valueOf("2099-01-01 12:00:00"));
			
			rtnMultimap.put(PRICE_MAKING_RULE_KEY, pricemakingRule);
		}
		
	}
	
	
}
