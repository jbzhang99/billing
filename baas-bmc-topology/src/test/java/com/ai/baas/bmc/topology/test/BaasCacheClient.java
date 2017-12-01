package com.ai.baas.bmc.topology.test;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.TreeMap;

import com.ai.baas.bmc.topology.util.BmcConstants;
import com.ai.baas.dshm.client.CacheFactoryUtil;
import com.ai.baas.dshm.client.impl.CacheBLMapper;
import com.ai.baas.dshm.client.impl.DshmClient;
import com.ai.baas.dshm.client.interfaces.IDshmClient;
import com.ai.baas.storm.util.BaseConstants;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;

public class BaasCacheClient {
	public static final String CCS_APPNAME = "ccs.appname";
	public static final String CCS_ZK_ADDRESS = "ccs.zk_address";
	private ICacheClient cacheClient;
	private IDshmClient dshmClient;
	
	public BaasCacheClient(){
		Properties param = new Properties();
//		param.setProperty(CCS_APPNAME, "aiopt-baas-dshm");
//	    param.setProperty(CCS_ZK_ADDRESS, "10.1.130.84:39181");
		param.setProperty("paas.auth.url", "http://10.1.245.4:19811/service-portal-uac-web/service/auth");
	    param.setProperty("paas.auth.pid", "87EA5A771D9647F1B5EBB600812E3067");
	    param.setProperty("paas.ccs.serviceid", "CCS008");
	    param.setProperty("paas.ccs.servicepassword", "123456");
	    System.out.println("CacheClient.Properties==="+param.toString());
	    cacheClient = CacheFactoryUtil.getCacheClient(param,CacheBLMapper.CACHE_BL_CAL_PARAM);
	    dshmClient = new DshmClient();	
	}
	
	private List<Map<String, String>> doQuery(String tableName, Map<String,String> params){
		return dshmClient.list(tableName).where(params).executeQuery(cacheClient);
	}
	
	public void test1(){
		Map<String, String> params = new TreeMap<String, String>();
		params.put("service_id", "17012345678");
		params.put("tenant_id", "TR");
		List<Map<String, String>> results = doQuery("bl_userinfo", params);
		for (Map<String, String> map : results) {
			for (Entry<String, String> result : map.entrySet()) {
				//System.out.println("the key is " + result.getKey() + "=" + result.getValue());
			}
		}
	}
	
	
	public void test2(){
		Map<String, String> params = new TreeMap<String, String>();
		//params.put("detail_code", "800");
		params.put("tenant_id", "TR");
		params.put("measure_word_code", "m");
		params.put("cal_way", "real_time");
		List<Map<String, String>> results = doQuery("cp_price_basis", params);
		for (Map<String, String> map : results) {
			System.out.println("--->>"+map.toString());
		} 
	}
	
	public void test3(){
		Map<String, String> params = new TreeMap<String, String>();
		params.put(BaseConstants.TENANT_ID, "TR");
		params.put(BmcConstants.PRICE_CODE, "P1");
		List<Map<String, String>> results = doQuery("cp_price_info", params);
		for (Map<String, String> map : results) {
			System.out.println("--->>"+map.toString());
		} 
	}
	
	public static void main(String[] args) {
		BaasCacheClient client = new BaasCacheClient();
		client.test1();;

	}

}
