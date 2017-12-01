package com.ai.baas.bmc.srv.flow.billing;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.bmc.srv.entity.BillingDetailRecord;
import com.ai.baas.bmc.srv.entity.BillingPriceItem;
import com.ai.baas.bmc.srv.entity.BillingPriceItemGroup;
import com.ai.baas.bmc.srv.entity.BillingPriceParam;
import com.ai.baas.bmc.srv.entity.BillingPriceResult;
import com.ai.baas.bmc.srv.entity.SubjectAndPrice;
import com.ai.baas.bmc.srv.failbill.BusinessException;
import com.ai.baas.bmc.srv.flow.billing.rule.BillingRuleFactory;
import com.ai.baas.bmc.srv.flow.billing.rule.IBilling;
import com.ai.baas.bmc.srv.flow.cache.ChargeCache;
import com.ai.baas.bmc.srv.util.BaasConstants;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


public class BillingMaster {
	private static Logger logger = LoggerFactory.getLogger(BillingMaster.class);
	private ChargeCache chargeCache = new ChargeCache();
	
	public BillingMaster(){
		
	}
	
	
	/**
	 * 计费,根据计费类型进行计费
	 * @param data
	 * @return
	 * @throws BusinessException
	 */
	public BillingDetailRecord billing(Map<String, String> data) throws BusinessException,Exception {
		BillingDetailRecord billingDetailRecord = null;
		List<Map<String,String>> priceDetails = filterPriceDetail(data);
		//得到批价单元
		List<BillingPriceItem> priceItems = matchingCharge(priceDetails,data);
		if (priceItems.size() > 0) {
			billingDetailRecord = accordingToRulesBilling(data,priceItems);
		}else{
			billingDetailRecord = new BillingDetailRecord();
			billingDetailRecord.setPriceSuccess(false);
			//billingDetailRecord.setData(data);
		}
		//需要组合套餐包批价.............需要增加
		return billingDetailRecord;
		//return accordingToRulesBilling(data,priceDetails);
	}
	
	/**
	 * 过滤有效的产品资费信息
	 * @param data
	 * @return
	 * @throws BusinessException
	 * @throws Exception
	 */
	private List<Map<String,String>> filterPriceDetail(Map<String, String> data) throws BusinessException,Exception {
		String tenant_id = data.get(BaasConstants.TENANT_ID);
		String product_ids = data.get(BaasConstants.PRODUCT_ID);
		String start_time = data.get(BaasConstants.START_TIME);
		List<String> effectivePriceCodes = chargeCache.getEffectivePriceInfo(tenant_id, product_ids, start_time);
		if(effectivePriceCodes.size() == 0){
			throw new BusinessException("BMC-B0010","该产品没有对应的资费信息!");
		}
		List<Map<String,String>> priceDetails = chargeCache.getPriceDetail(effectivePriceCodes);
		int priceDetailNum = priceDetails.size();
		if(priceDetailNum == 0){
			throw new BusinessException("BMC-B0011","该产品没有对应的资费明细信息!");
		}
		return priceDetails;
	}
	
	/**
	 * 匹配批价的资费
	 * @param priceDetails
	 * @return
	 * @throws BusinessException
	 * @throws Exception
	 */
	private List<BillingPriceItem> matchingCharge(List<Map<String,String>> priceDetails,Map<String, String> data) throws BusinessException,Exception{
		IBilling billingRule = null;
		String chargeType = "",detailCode = "";
		List<BillingPriceItem> priceItems = Lists.newArrayList();
		for (Map<String, String> priceDetail : priceDetails) {
			chargeType = StringUtils.upperCase(priceDetail.get(BaasConstants.CHARGE_TYPE));
			billingRule = BillingRuleFactory.getRuleInstance(chargeType);
			if(billingRule == null){
				logger.error("["+chargeType+"]不存在的计费规则!");
				continue;
			}
			detailCode = priceDetail.get(BaasConstants.DETAIL_CODE);
			if(billingRule.checkPrice(detailCode, data)){
				BillingPriceItem priceItem = new BillingPriceItem();
				priceItem.setPriority(Integer.valueOf(priceDetail.get(BaasConstants.PRODUCT_PRIORITY)));
				priceItem.setBillingRule(billingRule);
				priceItem.setPriceDetail(priceDetail);
				priceItem.setChargeType(chargeType);
				
				priceItems.add(priceItem);
			}
		}
		//超过一个批价资费,需要按照优先级排序
		if (priceItems.size() > 1) {
			Collections.sort(priceItems);
		}
		return priceItems;
	}
	
	private List<BillingPriceItemGroup> accordingToChargeTypeGroupBy(List<BillingPriceItem> priceItems){
		List<BillingPriceItemGroup> priceItemGroups = Lists.newArrayList();
		//Map<String,BillingPriceItemGroup> priceItemGroups = Maps.newHashMap();
		Map<String,Integer> keys = Maps.newHashMap();
		String chargeType = "";
		int position = 0,current = 0;
		//System.out.println("计费批价信息********************begin");
		BillingPriceItemGroup priceItemGrop = null;
		for (BillingPriceItem priceItem : priceItems) {
			chargeType = priceItem.getChargeType();
			//System.out.println("[price_code]-->"+priceItem.getPriceDetail().get(BaasConstants.PRICE_CODE));
			//System.out.println("[detail_code]-->"+priceItem.getPriceDetail().get(BaasConstants.DETAIL_CODE));
			//System.out.println("[charge_type]-->"+chargeType);
			//System.out.println("[priority]-->"+priceItem.getPriority());
			if(keys.containsKey(chargeType)){
				current = keys.get(chargeType).intValue();
				priceItemGrop = priceItemGroups.get(current);
				priceItemGrop.addBillPriceItem(priceItem);
			}else{
				priceItemGrop = new BillingPriceItemGroup();
				priceItemGrop.addChargeType(chargeType);
				priceItemGrop.addBillingRule(priceItem.getBillingRule());
				priceItemGrop.addBillPriceItem(priceItem);
				priceItemGroups.add(priceItemGrop);
				keys.put(chargeType, position);
				position++;
			}
		}
		//System.out.println("计费批价信息********************end");
		return priceItemGroups;
	}
	
	
	
	
	private BillingDetailRecord accordingToRulesBilling(Map<String, String> data, List<BillingPriceItem> priceItems) throws BusinessException,Exception{
		String remainUsageAmount = data.get(BaasConstants.USAGE_AMOUNT);
		if(StringUtils.isBlank(remainUsageAmount)){
			throw new BusinessException("BMC-B0017","原始报文中没有找到时长字段值!");
		}
		List<BillingPriceItemGroup> priceItemGroups = accordingToChargeTypeGroupBy(priceItems);
		BillingDetailRecord billingDetailRecord = new BillingDetailRecord();
		billingDetailRecord.setData(data);
		
		IBilling billingRule = null;
		String chargeType = "";
		BillingPriceResult billingPriceResult = null;
		for (BillingPriceItemGroup priceItemGroup : priceItemGroups) {
			chargeType = priceItemGroup.getChargeType();
			BillingPriceParam billingPriceDetail = new BillingPriceParam();
			billingPriceDetail.setData(data);
			billingPriceDetail.setPriceDetails(priceItemGroup.getPriceDetailAll());
			billingPriceDetail.setUsageAmount(remainUsageAmount);
			
			billingRule = priceItemGroup.getBillingRule();
			billingPriceResult = billingRule.calculate(billingPriceDetail);
			List<SubjectAndPrice> subjectAndPrices = billingPriceResult.getSubjectAndPrices();
			
			//if (subjectAndPrices.size()>0) {
			for (SubjectAndPrice subjectAndPrice:subjectAndPrices) {
				if(!chargeType.equals(BaasConstants.CHARGE_TYPES_PACKAGE) && StringUtils.isNotBlank(subjectAndPrice.getSubjectCode())){
					dealWithPriceAccuracy(subjectAndPrice);
					//billingDetailRecord.addFee(subjectAndPrice);
				}
				billingDetailRecord.addFee(subjectAndPrice);
			}
			
			if(billingPriceResult.isCompleted()){
				billingDetailRecord.setPriceSuccess(true);
				break;
			}
			
			//读取剩余资源使用量
			remainUsageAmount = billingPriceResult.getRemainUsageAmount();
			
			//System.out.println("remainUsageAmount==="+remainUsageAmount);
		}
		//if (billingDetailRecord.getFees().size() > 0) {
		if (billingDetailRecord.isPriceSuccess()) {
			//需要增加订购扩展表信息。。。。
			billingDetailRecord.setMsgData(data);
			//setFeesMsgData(billingDetailRecord);
			setFeesData(billingDetailRecord);
			//billingDetailRecord.setInstDr(true);
		}
		return billingDetailRecord;
	}
	
	private void setFeesData(BillingDetailRecord billingDetailRecord) throws BusinessException{
		Map<String,String> data = billingDetailRecord.getData();
		String subs_id = data.get(BaasConstants.SUBS_ID);
		Map<String,Object> msgData = billingDetailRecord.getMsgData();
		List<Map<String,String>> msgFeeDatas = Lists.newArrayList();
		String subjectCode = "";
		StringBuilder productStr = new StringBuilder();
		
		BigDecimal total = new BigDecimal(0);
		for(SubjectAndPrice subjectAndPrice:billingDetailRecord.getFees()){
			//将详单输出的费用汇总
			total = total.add(subjectAndPrice.getPrice());
			//如果多个科目,以最后一个科目为主
			subjectCode = subjectAndPrice.getSubjectCode();
			
			msgFeeDatas.add(setFeesMsgData(subjectAndPrice,subs_id));
			productStr.append(subjectAndPrice.getPriceCode()).append(",");
		}
		data.put("fee1", total.toString());
		data.put("subject1", subjectCode);
		String productIds = productStr.delete(productStr.length()-1, productStr.length()).toString();
		data.put(BaasConstants.PRODUCT_ID, productIds);
		
		msgData.put(BaasConstants.PRODUCT_ID, productIds);
		msgData.put("fee_info", msgFeeDatas);
	}
	
	private Map<String,String> setFeesMsgData(SubjectAndPrice subjectAndPrice,String subs_id) throws BusinessException{
		Map<String,String> feeMap = Maps.newHashMap();
		feeMap.put("fee", subjectAndPrice.getPrice().toPlainString());
		feeMap.put("subject", subjectAndPrice.getSubjectCode());
		//feeMap.put("priceCode", subjectAndPrice.getPriceCode());
		chargeCache.setSubsCommExtData(subjectAndPrice.getPriceCode(), subs_id, feeMap);
		return feeMap;
	}
	
	
	
//	private void setFeesMsgData(BillingDetailRecord billingDetailRecord){
//		Map<String,String> msgData = billingDetailRecord.getMsgData();
//		int index = 1;
//		String key;
//		for(SubjectAndPrice subjectAndPrice:billingDetailRecord.getFees()){
//			key = Joiner.on("").join("fee", String.valueOf(index));
//			msgData.put(key, subjectAndPrice.getPrice().toString());
//			key = Joiner.on("").join("subject", String.valueOf(index));
//			msgData.put(key, subjectAndPrice.getSubjectCode());
//			index++;
//		}
//	}
	
	private void dealWithPriceAccuracy(SubjectAndPrice subjectAndPrice){
		//BigDecimal tmpPrice = subjectAndPrice.getPrice().setScale(10, BigDecimal.ROUND_HALF_UP);
		//元转成厘,然后保留3位小数
		BigDecimal tmpPrice = subjectAndPrice.getPrice().multiply(new BigDecimal(1000)).setScale(3, BigDecimal.ROUND_HALF_UP);
		subjectAndPrice.setPrice(tmpPrice);
	}
	
	
	/**
	 * 根据计费规则进行计费
	 * @param data
	 * @param priceDetails
	 * @return
	 * @throws BusinessException
	 */
//	private BillingDetailRecord accordingToRulesBilling(Map<String, String> data, List<Map<String,String>> priceDetails) throws BusinessException,Exception{
//		BillingDetailRecord billingDetailRecord = new BillingDetailRecord();
//		billingDetailRecord.setData(data);
//		IBilling billingRule = null;
//		SubjectAndPrice subjectAndPrice = null;
//		String chargeType = "",priceCode = "";
//		//boolean isPriceSucc = false;//是否批价成功
//		for (Map<String, String> priceDetail : priceDetails) {
//			chargeType = priceDetail.get(BaasConstants.CHARGE_TYPE);
//			billingRule = BillingRuleFactory.getRuleInstance(chargeType);
//			if(billingRule == null){
//				logger.error("["+chargeType+"]不存在的计费规则!");
//				continue;
//			}
//			priceCode = priceDetail.get(BaasConstants.PRICE_CODE);
//			chargeCache.setSubsCommExtData(priceCode, data);
//			data.put(BaasConstants.PRICE_CODE, priceCode);
//			BillingPriceDetail billingPriceDetail = new BillingPriceDetail();
//			billingPriceDetail.setData(data);
//			billingPriceDetail.setPriceDetail(priceDetail);
//			subjectAndPrice = billingRule.calculate(billingPriceDetail);
//			if(subjectAndPrice != null) {
//				dealWithPriceAccuracy(subjectAndPrice);
//				billingDetailRecord.addFee(subjectAndPrice);
//				//如果批价成功直接退出
//				//isPriceSucc = true;
//				billingDetailRecord.setPriceSuccess(true);
//				break;
//			}
//		}
//		if (billingDetailRecord.isPriceSuccess()) {
//			setFeesData(billingDetailRecord);
//		}
//		return billingDetailRecord;
//	}
	
	
//	private void setFeesData(BillingDetailRecord billingDetailRecord){
//		Map<String,String> data = billingDetailRecord.getData();
//		int index = 1;
//		String key;
//		for(SubjectAndPrice subjectAndPrice:billingDetailRecord.getFees()){
//			if (index > 3) {
//				break;
//			}
//			key = Joiner.on("").join("fee", String.valueOf(index));
//			data.put(key, subjectAndPrice.getPrice().toString());
//			key = Joiner.on("").join("subject", String.valueOf(index));
//			data.put(key, subjectAndPrice.getSubjectCode());
//			index++;
//		}
//	}
	
	
}
