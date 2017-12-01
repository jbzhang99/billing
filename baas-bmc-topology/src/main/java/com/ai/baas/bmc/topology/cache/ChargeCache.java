package com.ai.baas.bmc.topology.cache;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.bmc.topology.billing.container.PriceBasisContainer;
import com.ai.baas.bmc.topology.util.BmcConstants;
import com.ai.baas.bmc.topology.util.DateUtil;
import com.ai.baas.dshm.client.impl.DshmClient;
import com.ai.baas.dshm.client.interfaces.IDshmClient;
import com.ai.baas.storm.exception.BusinessException;
import com.ai.baas.storm.util.BaseConstants;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;

public class ChargeCache {
	private static Logger logger = LoggerFactory.getLogger(ChargeCache.class);
	private String priceInfoTable = "cp_price_info";
	private String priceDetailTable = "cp_price_detail";
	private String stepInfoTable = "cp_step_info";
	private String unitpriceInfoTable = "cp_unitprice_info";
	private String unitpriceItemTable = "cp_unitprice_item";
	private String accumulateTablePrefix = "bmc_accumulate_";
	private CacheProxy cacheProxy = CacheProxy.getInstance();

	/**
	 * 得到有效的资费信息
	 * @param tenant_id
	 * @param product_ids
	 * @return
	 * @throws BusinessException
     */
	public List<String> getEffectiveProduct(String tenant_id,String product_ids) throws BusinessException{
		Map<String,String> params = new TreeMap<String,String>();
		Map<String,String> result;
		List<String> effectProductId = new ArrayList<String>();
		String currentTime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
		String star,end;
		String[] productIds = StringUtils.splitPreserveAllTokens(product_ids, BaseConstants.COMMON_SPLIT);
		IDshmClient dshmClient = new DshmClient();
		for(String productId:productIds){
			params.put(BaseConstants.TENANT_ID, tenant_id);
			params.put(BmcConstants.PRICE_CODE, productId);
			//List<Map<String, String>> results = cacheProxy.doQuery(priceInfoTable, params);
			List<Map<String, String>> results = dshmClient.list(priceInfoTable).where(params).executeQuery(cacheProxy.getCacheClient());
			params.clear();
			if (results != null && results.size() > 0) {
				result = results.get(0);
				star = result.get(BmcConstants.ACTIVE_TIME);
				end = result.get(BmcConstants.INACTIVE_TIME);
				if(DateUtil.isRange(star,end,currentTime)){
					effectProductId.add(productId);
				}
			}
		}
		return effectProductId;
	}
	
	/**
	 * 根据资费代码得到资费明细信息
	 * @param price_codes
	 * @return
	 */
	public List<Map<String,String>> getPriceDetail(List<String> price_codes){
		List<Map<String,String>> rtnValue = new ArrayList<Map<String,String>>();
		Map<String,String> params = new TreeMap<String,String>();
		IDshmClient dshmClient = new DshmClient();
		for(String price_code:price_codes){
			params.put(BmcConstants.PRICE_CODE, price_code);
			//List<Map<String, String>> results = cacheProxy.doQuery(priceDetailTable, params);
			List<Map<String, String>> results = dshmClient.list(priceDetailTable).where(params).executeQuery(cacheProxy.getCacheClient());
			params.clear();
			if (results != null && results.size() > 0) {
				rtnValue.add(results.get(0));
			}
		}
		return rtnValue;
	}
	
	
	public List<Map<String,String>> getStepInfoData(String detail_code){
		Map<String,String> params = new TreeMap<String,String>();
		params.put(BmcConstants.DETAIL_CODE, detail_code);
		//return cacheProxy.doQuery(stepInfoTable, params);
		IDshmClient dshmClient = new DshmClient();
		return dshmClient.list(stepInfoTable).where(params).executeQuery(cacheProxy.getCacheClient());
	}
	
	/**
	 * 得到累计后的值
	 * @param data
	 * @param unit_type
	 * @param insert
	 * @return
	 */
	public Double getAddupAfter(Map<String, String> data,String unit_type,String insert){
		String tenant_id = data.get(BaseConstants.TENANT_ID);
		String addupSubject = PriceBasisContainer.getAddUpSubject(tenant_id, unit_type);
		String[] addupSubjects = StringUtils.splitPreserveAllTokens(addupSubject,BaseConstants.COMMON_SPLIT);
		StringBuilder table = new StringBuilder();
		table.append(accumulateTablePrefix);
		table.append(data.get(BaseConstants.ACCOUNT_PERIOD).substring(0, 6));
		StringBuilder columnName = new StringBuilder();
		columnName.append(tenant_id).append(":");
		columnName.append(data.get(BaseConstants.SERVICE_TYPE)).append(":");
		columnName.append(data.get(BmcConstants.PRICE_CODE)).append(":");
		for(String subject:addupSubjects){
			columnName.append(data.get(subject)).append(":");
		}
		String column = columnName.delete(columnName.length()-1, columnName.length()).toString();
		return cacheProxy.getCacheClient().hincrByFloat(table.toString(), column, Double.parseDouble(insert));
	}
	
	
	public List<Map<String,String>> getUnitpriceItemData(String detail_code){
		Map<String,String> params = new TreeMap<String,String>();
		params.put(BmcConstants.DETAIL_CODE, detail_code);
		//List<Map<String,String>> results = cacheProxy.doQuery(unitpriceInfoTable, params);
		IDshmClient dshmClient = new DshmClient();
		List<Map<String, String>> results = dshmClient.list(unitpriceInfoTable).where(params).executeQuery(cacheProxy.getCacheClient());
		String fee_item_code = "";
		if(results != null && results.size()>0){
			fee_item_code = results.get(0).get(BmcConstants.FEE_ITEM_CODE);
			params.clear();
			params.put(BmcConstants.FEE_ITEM_CODE, fee_item_code);
			//results = cacheProxy.doQuery(unitpriceItemTable, params);
			results = dshmClient.list(unitpriceItemTable).where(params).executeQuery(cacheProxy.getCacheClient());
		}
		return results;
	}
	
}
