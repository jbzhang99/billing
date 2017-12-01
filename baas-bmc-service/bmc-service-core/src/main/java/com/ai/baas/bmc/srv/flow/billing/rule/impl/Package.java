package com.ai.baas.bmc.srv.flow.billing.rule.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ai.baas.bmc.srv.entity.BillingPriceParam;
import com.ai.baas.bmc.srv.entity.BillingPriceResult;
import com.ai.baas.bmc.srv.entity.ResBookUpdater;
import com.ai.baas.bmc.srv.entity.SubjectAndPrice;
import com.ai.baas.bmc.srv.failbill.BusinessException;
import com.ai.baas.bmc.srv.flow.billing.lock.DistributedMultiLock;
import com.ai.baas.bmc.srv.flow.billing.rule.IBilling;
import com.ai.baas.bmc.srv.flow.cache.ChargeCache;
import com.ai.baas.bmc.srv.persistence.entity.ResBook;
import com.ai.baas.bmc.srv.persistence.service.ResBookService;
import com.ai.baas.bmc.srv.util.BaasConstants;
import com.ai.baas.bmc.srv.util.UnitConverter;
import com.ai.opt.sdk.util.ApplicationContextUtil;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class Package implements IBilling {
	
	private ChargeCache chargeCache = new ChargeCache();
	private UnitConverter unitConverter = new UnitConverter();
	private ResBookService resBookService = ApplicationContextUtil.getService("resBookService");
	
	@Override
	public boolean checkPrice(String detailCode, Map<String, String> data) {
		boolean isSucc = false;
		Map<String,String> packageData = chargeCache.getPackageInfoData(detailCode);
		if (packageData != null) {
			String factorCode = packageData.get(BaasConstants.FACTOR_CODE);
			if(chargeCache.isMatchFactorCode(factorCode, data)){
				isSucc = true;
			}
		}
		return isSucc;
	}

	@Override
	public BillingPriceResult calculate(BillingPriceParam priceParam) throws BusinessException,Exception{
		boolean deductSucc = false;
		int deductTimes = 0;
		Map<String, String> data = priceParam.getData();
		String usageAmountStr = priceParam.getUsageAmount();
		BigDecimal usageAmount = null;
		String unitType = "";
		
		List<ResBookUpdater> rbookUpdaters = Lists.newArrayList();
		List<SubjectAndPrice> subjectAndPrices = Lists.newArrayList();
		String lockName = StringUtils.defaultIfBlank(getMultiLockName(priceParam.getPriceDetails()),"package");
		DistributedMultiLock lock = new DistributedMultiLock(lockName);
		try{
			lock.acquire();
			boolean readUsageAmount = false;
			String subs_id = data.get(BaasConstants.SUBS_ID);
			String start_time = data.get(BaasConstants.START_TIME);
			Map<String,String> packageData = null;
			for (Map<String, String> priceDetail : priceParam.getPriceDetails()) {
				boolean isDeduct = false;
				if (usageAmount == null && !readUsageAmount) {
					String detailCode = priceDetail.get(BaasConstants.DETAIL_CODE);
					packageData = chargeCache.getPackageInfoData(detailCode);
					unitType = packageData.get(BaasConstants.UNIT_TYPE);
					usageAmount = unitConverter.convertToUpperByKey(unitType, usageAmountStr);
					readUsageAmount = true;
				}
				String priceCode = priceDetail.get(BaasConstants.PRICE_CODE);
				//System.out.println("subs_id="+subs_id);
				//System.out.println("priceCode="+priceCode);
				//System.out.println("start_time="+start_time);
				List<ResBook> resBooks = resBookService.queryResBookBalance(subs_id, priceCode, start_time);
				//System.out.println("--------"+resBooks.get(0).getTenantId()+","+resBooks.get(0).getBookId());
				for (ResBook resBook : resBooks) {
					BigDecimal balance = new BigDecimal(String.valueOf(resBook.getBalance()));
					if(balance.compareTo(BigDecimal.ZERO) == 0){
						continue;
					}
					ResBookUpdater resBookUpdater = new ResBookUpdater();
					resBookUpdater.setResBook(resBook);
					BigDecimal subtractValue = balance.subtract(usageAmount);
					//差值 大于或等于0
					if(subtractValue.compareTo(BigDecimal.ZERO) == 1 ||subtractValue.compareTo(BigDecimal.ZERO) == 0){
						//System.out.println("[资源包抵扣,抵扣数量]-->>>"+usageAmount.toPlainString());
						//需要写入表.............subtractValue更新到balance字段
						resBookUpdater.setBalance(subtractValue);
						resBookUpdater.setChangeAmount(usageAmount);
						resBookUpdater.setChangeBefore(balance);
						rbookUpdaters.add(resBookUpdater);
						//usageAmount = BigDecimal.ZERO;
						deductSucc = true;
						isDeduct = true;
						deductTimes++;
						break;
					}else{
						//System.out.println("[资源包抵扣,抵扣数量]-->>>"+balance.toPlainString());
						//需要写入表.............0更新到balance字段
						resBookUpdater.setBalance(BigDecimal.ZERO);
						resBookUpdater.setChangeAmount(balance);
						resBookUpdater.setChangeBefore(balance);
						usageAmount = subtractValue.abs();
						rbookUpdaters.add(resBookUpdater);
						isDeduct = true;
						deductTimes++;
					}
//					isDeduct = true;
//					deductTimes++;
				}
				
				if(isDeduct){
					subjectAndPrices.add(new SubjectAndPrice("", new BigDecimal(0), priceCode));
				}
				
				//抵扣成功,终止循环
				if(deductSucc){
					break;
				}
				
				//System.out.println("11111111111111111111");
			}
			//需要写入资源表和资源日志表.............
			if (rbookUpdaters.size() > 0) {
				String account_period = data.get(BaasConstants.ACCOUNT_PERIOD);
				String drTable = assembleDrTableName(data);
				String drKey = data.get(BaasConstants.SERIAL_NUMBER);
				resBookService.updateResBookAndLog(rbookUpdaters, account_period, drTable, drKey);
			}
			//System.out.println("2222222222222222222");
		}catch(Exception e){
			throw e;
		}finally{
			lock.release();
		}
		BillingPriceResult priceResult = new BillingPriceResult();
		//如果没有抵扣资源
		if (deductTimes == 0) {
			priceResult.setCompleted(false);
			priceResult.setRemainUsageAmount(usageAmountStr);
			return priceResult;
		}
		priceResult.setSubjectAndPrices(subjectAndPrices);
		
		//if(usageAmount.compareTo(BigDecimal.ZERO) != 0){
		if (!deductSucc) {
			priceResult.setCompleted(false);
			//需要单位转换.............
			BigDecimal remainUsageAmount = unitConverter.convertToLowerByKey(unitType, usageAmount);
			priceResult.setRemainUsageAmount(remainUsageAmount.stripTrailingZeros().toPlainString());
			//System.out.println("[资源包抵扣,抵扣剩余使用量]-->>>"+remainUsageAmount.toPlainString());
		}
		return priceResult;
	}


	private String assembleDrTableName(Map<String, String> data){
		return Joiner.on("_").join(StringUtils.lowerCase(data.get(BaasConstants.TENANT_ID)),
				StringUtils.lowerCase(data.get(BaasConstants.SERVICE_TYPE)), "dr",
				data.get(BaasConstants.ACCOUNT_PERIOD));
	}
	
	private String getMultiLockName(List<Map<String,String>> priceDetails){
		StringBuilder lockStr = new StringBuilder();
		for(Map<String,String> priceDetail:priceDetails){
			lockStr.append(priceDetail.get(BaasConstants.PRICE_CODE)).append("$");
		}
		return lockStr.delete(lockStr.length()-1, lockStr.length()).toString();
	}
	

	private boolean deductResource(){
		return true;
	}
	
	
	
	
	
}
