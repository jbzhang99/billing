package com.ai.baas.bmc.srv.flow.billing.container;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import com.ai.baas.bmc.srv.flow.cache.CacheProxy;
import com.ai.baas.bmc.srv.util.BaasConstants;
import com.ai.baas.dshm.client.impl.DshmClient;
import com.ai.baas.dshm.client.interfaces.IDshmClient;

public class PriceBasisContainer {
	private static ConcurrentHashMap<String,String> container = new ConcurrentHashMap<String,String>();
	private static final String CAL_WAY = "real_time";
	private static final String CAL_WAY_TAG = "cal_way";
	private static final String MEASURE_WORD_CODE = "measure_word_code";
	private static final String ADD_UP_SUBJECT = "add_up_subject";
	private static String priceBasisTable="cp_price_basis";
	
	
	public static String getAddUpSubject(String tenant_id,String unit_type){
		StringBuilder key = new StringBuilder();
		key.append(tenant_id).append(":").append(unit_type);
		String addUpSubject = container.get(key.toString());
		if(StringUtils.isBlank(addUpSubject)){
			addUpSubject = addNewData(tenant_id,unit_type);
		}
		return addUpSubject;
	}
	
	
	private static String addNewData(String tenant_id,String unit_type){
		Map<String,String> params = new TreeMap<String,String>();
		params.put(BaasConstants.TENANT_ID, tenant_id);
		params.put(CAL_WAY_TAG, CAL_WAY);
		params.put(MEASURE_WORD_CODE, unit_type);
		IDshmClient dshmClient = new DshmClient();
		List<Map<String, String>> results = dshmClient.list(priceBasisTable).where(params).executeQuery(CacheProxy.getCacheClient());
		String addupSubject = "";
		if(results != null && results.size()>0){
			addupSubject = results.get(0).get(ADD_UP_SUBJECT);
			StringBuilder key = new StringBuilder();
			key.append(tenant_id).append(":").append(unit_type);
			container.put(key.toString(), addupSubject);
		}
		return addupSubject;
	}
	
	
}
