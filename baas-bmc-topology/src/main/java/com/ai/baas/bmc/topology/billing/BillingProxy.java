//package com.ai.baas.bmc.topology.billing;
//
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.ai.baas.bmc.topology.billing.rule.BillingMaster;
//import com.ai.baas.bmc.topology.cache.ChargeCache;
//import com.ai.baas.bmc.topology.entity.BillingDetailRecord;
//import com.ai.baas.bmc.topology.util.BmcConstants;
//import com.ai.baas.storm.exception.BusinessException;
//import com.ai.baas.storm.util.BaseConstants;
//import com.google.common.collect.ArrayListMultimap;
//import com.google.common.collect.Lists;
//import com.google.common.collect.Multimap;
//
///**
// * 计费代理类
// * @author majun
// *
// */
//public class BillingProxy {
//	private static Logger logger = LoggerFactory.getLogger(BillingProxy.class);
//	private ChargeCache chargeCache = new ChargeCache();
//	private BillingMaster billingMaster;
//	private List<String> chargePriority;//计费优先级
//	
//	public BillingProxy(){
//		//需要加载计费规则....................
//		chargePriority = Lists.newArrayList("PACKAGE","STEP");
//		billingMaster = new BillingMaster(chargeCache);
//		
//	}
//	
//	public BillingDetailRecord billing(Map<String, String> data) throws BusinessException {
//		String tenant_id = data.get(BaseConstants.TENANT_ID);
//		String product_ids = data.get(BmcConstants.PRODUCT_ID);
//		List<String> effectiveProductIds = chargeCache.getEffectiveProduct(tenant_id, product_ids);
//		if(effectiveProductIds.size() == 0){
//			throw new BusinessException("BMC-B0004","该产品没有对应的资费信息!");
//		}
//		List<Map<String,String>> priceDetails = chargeCache.getPriceDetail(effectiveProductIds);
//		int priceDetailNum = priceDetails.size();
//		BillingDetailRecord billingDetailRecord = null; 
//		if(priceDetailNum == 1){
//			billingDetailRecord = billingMaster.billing(data, priceDetails.get(0));
//		}else if(priceDetailNum == 0){
//			throw new BusinessException("BMC-B0005","该产品没有对应的资费明细信息!");
//		}else{
//			billingDetailRecord = complexBilling(data, priceDetails);
//		}
//		/*
//		if(!verifyPriceDetail(priceDetails)){
//			throw new BusinessException("BMC-B0006","有重复的阶梯资费或者阶梯、套餐资费同时存在!");
//		}
//		*/
//		return billingDetailRecord;
//	}
//	
//	
//	private BillingDetailRecord complexBilling(Map<String, String> data,List<Map<String,String>> priceDetails) throws BusinessException{
//		Multimap<String,Map<String,String>> categoryMap = ArrayListMultimap.create();
//		for(Map<String,String> priceDetail:priceDetails){
//			categoryMap.put(StringUtils.upperCase(priceDetail.get(BmcConstants.CHARGE_TYPE)), priceDetail);
//		}
//		int rowNum = 0;
//		BillingDetailRecord billingDetailRecord = null;
//		for(String priority:chargePriority){
//			rowNum = categoryMap.get(priority).size();
//			if(rowNum > 0){
//				billingDetailRecord = billingMaster.billing(data, priority, categoryMap);
//				break;
//			}
//		}
//		return billingDetailRecord;
//	}
//	
////	
////	private void doPackageCharge(Multimap<String,Map<String,String>> categoryMap){
////		
////	}
//	
//	
//	
//}
