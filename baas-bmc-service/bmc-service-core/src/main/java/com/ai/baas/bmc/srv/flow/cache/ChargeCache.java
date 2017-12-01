package com.ai.baas.bmc.srv.flow.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.bmc.srv.failbill.BusinessException;
import com.ai.baas.bmc.srv.util.BaasConstants;
import com.ai.baas.bmc.srv.util.DateUtil;
import com.ai.baas.dshm.client.impl.DshmClient;
import com.ai.baas.dshm.client.interfaces.IDshmClient;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;


public class ChargeCache {
	private static Logger logger = LoggerFactory.getLogger(ChargeCache.class);
	private String priceInfoTable = "cp_price_info";
	private String priceDetailTable = "cp_price_detail";
	private String stepInfoTable = "cp_step_info";
	private String unitpriceInfoTable = "cp_unitprice_info";
	private String unitpriceItemTable = "cp_unitprice_item";
	private String accumulateTablePrefix = "bmc_accumulate_";
	private String accumulateTableAll = "bmc_accumulate_all";
	private String subsCommExtTable = "bl_subscomm_ext";
	private String factorInfoTable = "cp_factor_info";
	private String cunitPriceInfoTable = "cp_cunitprice_info";
	private String extInfoTable = "cp_ext_info";
	private String priceBasisTable="cp_price_basis";
	private String packageInfoTable = "cp_package_info";
	public static final String priceBasicAddupSubject = "add_up_subject";
	public static final String priceBasicCalWay = "cal_way";
	
	/**
	 * 得到有效的资费信息
	 * @param tenant_id
	 * @param product_ids
	 * @return
	 * @throws BusinessException
     */
	public List<String> getEffectivePriceInfo(String tenant_id,String product_ids,String start_time) throws BusinessException{
		Map<String,String> params = new TreeMap<String,String>();
		Map<String,String> result;
		List<String> effectProductId = new ArrayList<String>();
		//String currentTime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"); 
		String star,end;
		String[] productIds = StringUtils.splitPreserveAllTokens(product_ids, BaasConstants.COMMON_SPLIT);
		IDshmClient dshmClient = new DshmClient();
		String productPriority = "";
		for(String productId:productIds){
			params.put(BaasConstants.TENANT_ID, tenant_id);
			params.put(BaasConstants.PRICE_CODE, productId);
			List<Map<String, String>> results = dshmClient.list(priceInfoTable).where(params).executeQuery(CacheProxy.getCacheClient());
			params.clear();
			if (results != null && results.size() > 0) {
				result = results.get(0);
				star = result.get(BaasConstants.ACTIVE_TIME);
				end = result.get(BaasConstants.INACTIVE_TIME);
				if (DateUtil.isRange(star, end, start_time)) {
					productPriority = StringUtils.defaultString(result.get(BaasConstants.PRODUCT_PRIORITY), "100");
					effectProductId.add(Joiner.on(":").join(productId, productPriority));
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
	public List<Map<String,String>> getPriceDetail(List<String> effectivePriceCodes){
		List<Map<String,String>> rtnValue = new ArrayList<Map<String,String>>();
		Map<String,String> params = new TreeMap<String,String>();
		IDshmClient dshmClient = new DshmClient();
		Map<String, String> priceDetailData = null;
		for (String priceCode : effectivePriceCodes) {
			//0:price_code 1:product_priority
			String[] priceCodeAndPriority = StringUtils.splitPreserveAllTokens(priceCode, ":");
			params.put(BaasConstants.PRICE_CODE, priceCodeAndPriority[0]);
			List<Map<String, String>> results = dshmClient.list(priceDetailTable).where(params).executeQuery(CacheProxy.getCacheClient());
			params.clear();
			if (results != null && results.size() > 0) {
				priceDetailData = results.get(0);
				priceDetailData.put(BaasConstants.PRODUCT_PRIORITY, priceCodeAndPriority[1]);
				rtnValue.add(priceDetailData);
			}
		}
		return rtnValue;
	}
	
	
	public List<Map<String,String>> getStepInfoData(String detail_code){
		Map<String,String> params = new TreeMap<String,String>();
		params.put(BaasConstants.DETAIL_CODE, detail_code);
		IDshmClient dshmClient = new DshmClient();
		return dshmClient.list(stepInfoTable).where(params).executeQuery(CacheProxy.getCacheClient());
	}
	
	public Map<String,String> getCunitInfoData(String detail_code){
		Map<String,String> params = new TreeMap<String,String>();
		params.put(BaasConstants.CUNIT_PRICE_CODE, detail_code);
		IDshmClient dshmClient = new DshmClient();
		List<Map<String, String>> results = dshmClient.list(cunitPriceInfoTable).where(params).executeQuery(CacheProxy.getCacheClient());
		Map<String,String> cunitMap = null; 
		if (results != null && results.size() > 0) {
			cunitMap = results.get(0);
		}
		return cunitMap;
	}
	
	public Map<String,String> getPackageInfoData(String detail_code){
		Map<String,String> params = new TreeMap<String,String>();
		params.put(BaasConstants.DETAIL_CODE, detail_code);
		IDshmClient dshmClient = new DshmClient();
		List<Map<String, String>> results = dshmClient.list(packageInfoTable).where(params).executeQuery(CacheProxy.getCacheClient());
		Map<String,String> packageMap = null;
		if (results != null && results.size() > 0) {
			packageMap = results.get(0);
		}
		return packageMap;
	}
	
	
	/**
	 * 得到累计后的值
	 * @param data
	 * @param unit_type
	 * @param insert
	 * @return
	 */
	public Double getAddupAfter(Map<String, String> data,String unit_type,String insert){
		String tenant_id = data.get(BaasConstants.TENANT_ID);
		//String service_type = data.get(BaasConstants.SERVICE_TYPE);
		//String addupSubject = PriceBasisContainer.getAddUpSubject(tenant_id, unit_type);
		String addupSubject = getAddUpSubject(data);
		//String[] addupSubjects = StringUtils.splitPreserveAllTokens(addupSubject,BaasConstants.COMMON_SPLIT);
		String[] addupSubjects = StringUtils.splitPreserveAllTokens(addupSubject,";");
		StringBuilder table = new StringBuilder();
		table.append(accumulateTablePrefix);
		table.append(data.get(BaasConstants.ACCOUNT_PERIOD).substring(0, 6));
		StringBuilder columnName = new StringBuilder();
		columnName.append(tenant_id).append(":");
		columnName.append(data.get(BaasConstants.SERVICE_TYPE)).append(":");
		//columnName.append(data.get(BaasConstants.PRICE_CODE)).append(":");
		for(String subject:addupSubjects){
			columnName.append(data.get(subject)).append(":");
		}
		String column = columnName.delete(columnName.length()-1, columnName.length()).toString();
		data.put(BaasConstants.STEP_HASH_KEY, column);
		return CacheProxy.getCacheClient().hincrByFloat(table.toString(), column, Double.parseDouble(insert));
	}
	
	public String getAddUpSubject(Map<String, String> data){
		Map<String,String> params = new TreeMap<String,String>();
		params.put(BaasConstants.TENANT_ID, data.get(BaasConstants.TENANT_ID));
		params.put(BaasConstants.DETAIL_CODE, data.get(BaasConstants.SERVICE_TYPE));
		IDshmClient dshmClient = new DshmClient();
		List<Map<String, String>> results = dshmClient.list(priceBasisTable).where(params).executeQuery(CacheProxy.getCacheClient());
		String addupSubject = "";
		if(results != null && results.size()>0){
			addupSubject = results.get(0).get("add_up_subject");
		}
		return addupSubject;
	}
	
	
	public List<Map<String,String>> getUnitpriceItemData(String detail_code){
		Map<String,String> params = new TreeMap<String,String>();
		params.put(BaasConstants.DETAIL_CODE, detail_code);
		IDshmClient dshmClient = new DshmClient();
		List<Map<String, String>> results = dshmClient.list(unitpriceInfoTable).where(params).executeQuery(CacheProxy.getCacheClient());
		String fee_item_code = "";
		if(results != null && results.size()>0){
			fee_item_code = results.get(0).get(BaasConstants.FEE_ITEM_CODE);
			params.clear();
			params.put(BaasConstants.FEE_ITEM_CODE, fee_item_code);
			//results = cacheProxy.doQuery(unitpriceItemTable, params);
			results = dshmClient.list(unitpriceItemTable).where(params).executeQuery(CacheProxy.getCacheClient());
		}
		return results;
	}
	
	/**
	 * 设置订购扩展表数据
	 * @param price_code
	 * @param data
	 * @throws BusinessException
	 */
	public void setSubsCommExtData(String price_code,Map<String, String> data) throws BusinessException{
		Map<String,String> params = new TreeMap<String,String>();
		params.put(BaasConstants.PRODUCT_ID, price_code);
		params.put(BaasConstants.SUBS_ID, data.get(BaasConstants.SUBS_ID));
		IDshmClient dshmClient = new DshmClient();
		List<Map<String, String>> results = dshmClient.list(subsCommExtTable).where(params).executeQuery(CacheProxy.getCacheClient());
	    if(results != null){
	    	for(Map<String, String> result:results){
	    		data.put(result.get(BaasConstants.EXT_NAME), result.get(BaasConstants.EXT_VALUE));
	    	}
		}
	    
	}
	
	public void setSubsCommExtData(String price_code, String subs_id, Map<String, String> feeMap) throws BusinessException{
		Map<String,String> params = new TreeMap<String,String>();
		params.put(BaasConstants.PRODUCT_ID, price_code);
		params.put(BaasConstants.SUBS_ID, subs_id);
		IDshmClient dshmClient = new DshmClient();
		List<Map<String, String>> results = dshmClient.list(subsCommExtTable).where(params).executeQuery(CacheProxy.getCacheClient());
	    if(results != null){
	    	for(Map<String, String> result:results){
	    		feeMap.put(result.get(BaasConstants.EXT_NAME), result.get(BaasConstants.EXT_VALUE));
	    	}
		}
	}
	
	/**
	 * 匹配参考因素的值
	 * @param factor_code
	 * @param data
	 * @return
	 */
	public boolean isMatchFactorCode(String factor_code,Map<String, String> data){
		boolean isMatching = false;
		if (StringUtils.isBlank(factor_code)) {
			return isMatching;
		}
		Map<String,String> params = new TreeMap<String,String>();
		params.put(BaasConstants.FACTOR_CODE, factor_code);
		IDshmClient dshmClient = new DshmClient();
		List<Map<String, String>> results = dshmClient.list(factorInfoTable).where(params).executeQuery(CacheProxy.getCacheClient());
		String factorName = "",factorValue = "";
		int flag = 0;
		if(results != null){
			for(Map<String, String> result:results){
				factorName = result.get(BaasConstants.FACTOR_NAME);
				factorValue = result.get(BaasConstants.FACTOR_VALUE);
				//logger.debug("[ChargeCache]factorName="+factorName+",factorValue="+factorValue);
				if (StringUtils.isNotBlank(factorName) && StringUtils.isNotBlank(factorValue)) {
					String paramIn = data.get(StringUtils.trim(factorName));
					//logger.debug("[ChargeCache]paramIn="+paramIn);
					if(StringUtils.isNotBlank(paramIn)){
						String[] factorValues = StringUtils.splitPreserveAllTokens(factorValue,";");
						//logger.debug("[ChargeCache]factorValues="+factorValues);
						if (ArrayUtils.contains(factorValues, StringUtils.trim(paramIn))){
							flag++;
						}
					}
				}
			}
			int cnt = results.size();
			if (cnt > 0 && cnt == flag) {
				isMatching = true;
			}
			//logger.debug("[ChargeCache]cnt="+cnt+",flag="+flag);
		}
		return isMatching;
	}
	
	public List<Map<String,String>> getExtInfoValue(String tenant_id,String ext_owner,String ext_code){
		Map<String,String> params = new TreeMap<String,String>();
		params.put(BaasConstants.TENANT_ID, tenant_id);
		params.put(BaasConstants.EXT_OWNER, ext_owner);
		params.put(BaasConstants.EXT_CODE, ext_code);
		IDshmClient dshmClient = new DshmClient();
		return dshmClient.list(extInfoTable).where(params).executeQuery(CacheProxy.getCacheClient());
	}
	
	public Double getAccumulateAfter(Map<String, String> data,String unit_type,String insert){
		Map<String,String> priceBasisColumn = getAccumulateConfig(data);
		/**
		 * 目前提供三种累帐方式
		 * real_time:month  按月累(默认)
		 * real_time:year   按年累
         * real_time:always 永远累
		 */
		String calWayColumnStr = StringUtils.defaultString(priceBasisColumn.get(priceBasicCalWay), BaasConstants.REAL_TIME);
		StringBuilder table = new StringBuilder();
		if(calWayColumnStr.equalsIgnoreCase(BaasConstants.REAL_TIME)||calWayColumnStr.equalsIgnoreCase(BaasConstants.REAL_TIME_MONTH)){
			table.append(accumulateTablePrefix);
			table.append(data.get(BaasConstants.ACCOUNT_PERIOD).substring(0, 6));
		}else if(calWayColumnStr.equalsIgnoreCase(BaasConstants.REAL_TIME_YEAR)){
			table.append(accumulateTablePrefix);
			table.append(data.get(BaasConstants.ACCOUNT_PERIOD).substring(0, 4));
		}else if(calWayColumnStr.equalsIgnoreCase(BaasConstants.REAL_TIME_ALWAYS)){
			table.append(accumulateTableAll);
		}
		String[] addupSubjects = StringUtils.splitPreserveAllTokens(priceBasisColumn.get(priceBasicAddupSubject),";");
		String tenant_id = data.get(BaasConstants.TENANT_ID);
		StringBuilder columnName = new StringBuilder();
		columnName.append(tenant_id).append(":");
		columnName.append(data.get(BaasConstants.SERVICE_TYPE)).append(":");
		for(String subject:addupSubjects){
			columnName.append(data.get(subject)).append(":");
		}
		String column = columnName.delete(columnName.length()-1, columnName.length()).toString();
		data.put(BaasConstants.STEP_HASH_KEY, column);
		return CacheProxy.getCacheClient().hincrByFloat(table.toString(), column, Double.parseDouble(insert));
	}
	
	public Map<String,String> getAccumulateConfig(Map<String, String> data){
		Map<String,String> params = new TreeMap<String,String>();
		params.put(BaasConstants.TENANT_ID, data.get(BaasConstants.TENANT_ID));
		params.put(BaasConstants.DETAIL_CODE, data.get(BaasConstants.SERVICE_TYPE));
		IDshmClient dshmClient = new DshmClient();
		List<Map<String, String>> results = dshmClient.list(priceBasisTable).where(params).executeQuery(CacheProxy.getCacheClient());
		Map<String,String> priceBasis = Maps.newHashMap();
		if (results != null && results.size() > 0) {
			priceBasis.put(priceBasicAddupSubject, results.get(0).get(priceBasicAddupSubject));
			priceBasis.put(priceBasicCalWay, results.get(0).get(priceBasicCalWay));
		}
		return priceBasis;
	}
		
}
