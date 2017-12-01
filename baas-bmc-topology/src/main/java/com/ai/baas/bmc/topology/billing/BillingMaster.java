package com.ai.baas.bmc.topology.billing;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.bmc.topology.billing.rule.IBilling;
import com.ai.baas.bmc.topology.billing.rule.impl.Package;
import com.ai.baas.bmc.topology.billing.rule.impl.Step;
import com.ai.baas.bmc.topology.cache.ChargeCache;
import com.ai.baas.bmc.topology.entity.BillingDetailRecord;
import com.ai.baas.bmc.topology.entity.BillingPriceDetail;
import com.ai.baas.bmc.topology.entity.SubjectAndPrice;
import com.ai.baas.bmc.topology.util.BmcConstants;
import com.ai.baas.storm.exception.BusinessException;
import com.ai.baas.storm.util.BaseConstants;
import com.google.common.base.Joiner;


/**
 * 计费,针对各种类型进行计费
 * @author majun
 * @since 2016.04.07
 */
public class BillingMaster {
	private static Logger logger = LoggerFactory.getLogger(BillingMaster.class);
	private Map<String, IBilling> rules = new HashMap<String, IBilling>();
	private ChargeCache chargeCache = new ChargeCache();
	
	public BillingMaster(){
		rules.put(BmcConstants.CHARGE_TYPES.PACKAGE.name(), new Package());
		rules.put(BmcConstants.CHARGE_TYPES.STEP.name(), new Step(chargeCache));
		//rules.put(BmcConstants.CHARGE_TYPES.UNIT.name(), new Unit());
	}
	
	/**
	 * 计费,根据计费类型进行计费
	 * @param data
	 * @return
	 * @throws BusinessException
	 */
	public BillingDetailRecord billing(Map<String, String> data) throws BusinessException {
		String tenant_id = data.get(BaseConstants.TENANT_ID);
		String product_ids = data.get(BmcConstants.PRODUCT_ID);
		List<String> effectiveProductIds = chargeCache.getEffectiveProduct(tenant_id, product_ids);
		if(effectiveProductIds.size() == 0){
			throw new BusinessException("BMC-B0004","该产品没有对应的资费信息!");
		}
		List<Map<String,String>> priceDetails = chargeCache.getPriceDetail(effectiveProductIds);
		int priceDetailNum = priceDetails.size();
		if(priceDetailNum == 0){
			throw new BusinessException("BMC-B0005","该产品没有对应的资费明细信息!");
		}
		return accordingToRulesBilling(data,priceDetails);
	}
	
	/**
	 * 根据计费规则进行计费
	 * @param data
	 * @param priceDetails
	 * @return
	 * @throws BusinessException
	 */
	private BillingDetailRecord accordingToRulesBilling(Map<String, String> data, List<Map<String,String>> priceDetails) throws BusinessException{
		BillingDetailRecord billingDetailRecord = new BillingDetailRecord();
		billingDetailRecord.setData(data);
		IBilling billingRule = null;
		SubjectAndPrice subjectAndPrice = null;
		String chargeType = "";
		for (Map<String, String> priceDetail : priceDetails) {
			chargeType = priceDetail.get(BmcConstants.CHARGE_TYPE);
			billingRule = rules.get(chargeType);
			if(billingRule == null){
				logger.error("不存在的计费规则!");
				continue;
			}
			data.put(BmcConstants.PRICE_CODE, priceDetail.get(BmcConstants.PRICE_CODE));
			BillingPriceDetail billingPriceDetail = new BillingPriceDetail();
			billingPriceDetail.setData(data);
			billingPriceDetail.setPriceDetail(priceDetail);
			subjectAndPrice = billingRule.calculate(billingPriceDetail);
			if(subjectAndPrice != null) {
				dealWithPriceAccuracy(subjectAndPrice);
				billingDetailRecord.addFee(subjectAndPrice);
			}
		}
		setFeesData(billingDetailRecord);
		return billingDetailRecord;
	}
	
	
	private void setFeesData(BillingDetailRecord billingDetailRecord){
		Map<String,String> data = billingDetailRecord.getData();
		int index = 1;
		String key;
		for(SubjectAndPrice subjectAndPrice:billingDetailRecord.getFees()){
			if (index > 3) {
				break;
			}
			key = Joiner.on("").join("fee", String.valueOf(index));
			data.put(key, subjectAndPrice.getPrice().toString());
			key = Joiner.on("").join("subject", String.valueOf(index));
			data.put(key, subjectAndPrice.getSubjectCode());
			index++;
		}
	}
	
	private void dealWithPriceAccuracy(SubjectAndPrice subjectAndPrice){
		BigDecimal tmpPrice = subjectAndPrice.getPrice().setScale(3, BigDecimal.ROUND_HALF_UP);
		subjectAndPrice.setPrice(tmpPrice);
	}
	
	
}
