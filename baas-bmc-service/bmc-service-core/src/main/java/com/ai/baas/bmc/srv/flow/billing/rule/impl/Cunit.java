package com.ai.baas.bmc.srv.flow.billing.rule.impl;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ai.baas.bmc.srv.entity.BillingPriceParam;
import com.ai.baas.bmc.srv.entity.BillingPriceResult;
import com.ai.baas.bmc.srv.entity.SubjectAndPrice;
import com.ai.baas.bmc.srv.failbill.BusinessException;
import com.ai.baas.bmc.srv.flow.billing.rule.IBilling;
import com.ai.baas.bmc.srv.flow.cache.ChargeCache;
import com.ai.baas.bmc.srv.util.BaasConstants;
import com.ai.baas.bmc.srv.util.UnitConverter;

/**
 * 复合单价计费
 * @author majun
 *
 */
public class Cunit implements IBilling {
	
	private ChargeCache chargeCache = new ChargeCache();
	private UnitConverter unitConverter = new UnitConverter();
	
	@Override
	public boolean checkPrice(String detailCode, Map<String, String> data) {
		boolean isSucc = false;
		Map<String,String> cunitData = chargeCache.getCunitInfoData(detailCode);
		if (cunitData != null) {
			String factorCode = cunitData.get(BaasConstants.FACTOR_CODE);
			if(chargeCache.isMatchFactorCode(factorCode, data)){
				isSucc = true;
			}
		}
		return isSucc;
	}


	@Override
	public BillingPriceResult calculate(BillingPriceParam priceParam)throws BusinessException {
		Map<String,String> priceDetail = priceParam.getPriceDetails().get(0);
		String detailCode = priceDetail.get(BaasConstants.DETAIL_CODE);
		//System.out.println("detailCode="+detailCode);
		Map<String,String> cunitData = chargeCache.getCunitInfoData(detailCode);
		if (cunitData == null) {
			throw new BusinessException("BMC-B0012","[在缓存表cp_cunitprice_info中没有找到对应的detail_code值]");
		}
		//Map<String, String> data = priceParam.getData();
//		String factorCode = cunitData.get(BaasConstants.FACTOR_CODE);
//		if(!chargeCache.isMatchFactorCode(factorCode, data)){
//			return billingPriceResult;
//		}
		String unitType = cunitData.get(BaasConstants.UNIT_TYPE);
		if(StringUtils.isBlank(unitType)){
			throw new BusinessException("BMC-B0013","该复合单价没有配置UNIT_TYPE!");
		}
		//String usageAmountIn = data.get(BaasConstants.USAGE_AMOUNT);
		String usageAmountIn = priceParam.getUsageAmount();
		//System.out.println("[复合单价计费,转换前使用量]-->>"+usageAmountIn);
//		if(StringUtils.isBlank(usageAmountIn)){
//			//需要拋出异常信息
//			throw new BusinessException("BMC-B0014","原始报文中没有找到时长字段值!");
//		}
		usageAmountIn = unitConverter.convertToAdvanced(unitType, usageAmountIn);
		//System.out.println("[复合单价计费,转换后使用量]-->>"+usageAmountIn);
		BigDecimal usageAmountValue = new BigDecimal(usageAmountIn);
		BigDecimal price = new BigDecimal(cunitData.get(BaasConstants.PRICE_VALUE));
		//System.out.println("[复合单价计费,单价]-->>"+price.toPlainString());
		BigDecimal total = usageAmountValue.multiply(price);
		//System.out.println("[复合单价计费,总金额]-->>"+total.toPlainString());
		SubjectAndPrice subjectAndPrice = new SubjectAndPrice(cunitData.get(BaasConstants.SUBJECT_CODE),total);
		subjectAndPrice.setPriceCode(priceDetail.get(BaasConstants.PRICE_CODE));
		BillingPriceResult billingPriceResult = new BillingPriceResult();
		//billingPriceResult.setSubjectAndPrice(subjectAndPrice);
		billingPriceResult.addSubjectAndPrices(subjectAndPrice);
		return billingPriceResult;
	}

}
