package com.ai.baas.batch.client.core;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import net.sf.json.JSONArray;
import com.alibaba.fastjson.JSONArray;
//import net.sf.json.JSONObject;
import com.alibaba.fastjson.JSONObject;
import com.ai.baas.batch.client.util.getUrl;
import com.ai.baas.bmc.api.baseInfo.params.BaseCode;
import com.ai.opt.sdk.dubbo.util.HttpClientUtil;

public class GetOrgUrl {
	private static final Logger LOG=LoggerFactory.getLogger(GetOrgUrl.class);
	public Map<String, String> orgUrl=new HashMap<String, String>();
	private String adaptUrl=null;
	public GetOrgUrl(){
		adaptUrl=getUrl.getInstance().getAdaptUrl();
	}
	
	public void adaptUrl(){
		Map<String, String> param=new HashMap<String,String>();
		String serviceId=getUrl.getInstance().getServiceId();
		if("".equals(serviceId)){
			param.put("select_type", "8");
			param.put("select_id", "");
		}else{
			param.put("select_type", "3");
			param.put("select_id", serviceId);
		}
		String ORG_QUERY_PATH=null;
		if(GetRecordDetail.baseCodes!=null){
	         for(BaseCode baseCode : GetRecordDetail.baseCodes){
	            if("ORG_QUERY_PATH".equals(baseCode.getParamCode())){
	            	ORG_QUERY_PATH = baseCode.getDefaultValue();
	             }                
	          }
	     }
		//ORG_QUERY_PATH="http://10.248.4.7:80/usrmgt/service_org.do";
		if(null==ORG_QUERY_PATH){
			throw new RuntimeException("机构基本信息查询url没有加载进缓存........");
		}
		LOG.debug("the 机构基本信息查询url： "+ORG_QUERY_PATH);
		String jsonString=HttpClientUtil.sendGet(ORG_QUERY_PATH,param);
		//String jsonString="{\"data\":\"{\\\"orgs\\\":[{\\\"org_id\\\":\\\"576203a86ae6ca04e1459582\\\",\\\"is_tenant\\\":\\\"0\\\",\\\"level\\\":\\\"\\\",\\\"superior\\\":\\\"\\\",\\\"citic_org_id\\\":\\\"\\\",\\\"name\\\":\\\"阿里云\\\",\\\"is_supplier\\\":\\\"1\\\",\\\"is_cost_center\\\":\\\"1\\\",\\\"bank_account\\\":\\\" \\\",\\\"uri\\\":\\\"http://aliapt.citicdao.com\\\"},{\\\"org_id\\\":\\\"57981910e1983a04188487c8\\\",\\\"is_tenant\\\":\\\"0\\\",\\\"level\\\":\\\"\\\",\\\"superior\\\":\\\"\\\",\\\"citic_org_id\\\":\\\"\\\",\\\"name\\\":\\\"smartCloud\\\",\\\"is_supplier\\\":\\\"1\\\",\\\"is_cost_center\\\":\\\"1\\\",\\\"uri\\\":\\\"http://scapt.citicdao.com\\\"}]}\",\"resultCode\":\"000000\",\"resultMessage\":\"请求成功\"}";
//		LOG.debug("the jsonString is  "+jsonString);
//		String jsonString="{\"orgs\": [{\"org_id\": \"6ba76566-0251-4e1a-a188-64e1b2d26be2\",\"vpc_id\": \"b1305db4-c5e3-4413-80fa-b0273536d0b5\",\"uri\": \"http://csadaptor.citic.com/\",\"bank_account\" : \"60193480640935824035008\"}]}";
		//String jsonString="{\"data\":\"{\"orgs\":[{\"org_id\":\"576203a86ae6ca04e1459582\",\"is_tenant\":\"0\",\"level\":\"\",\"superior\":\"\",\"citic_org_id\":\"\",\"name\":\"阿里云\",\"is_supplier\":\"1\",\"is_cost_center\":\"0\",\"bank_account\":\" \",\"uri\":\"http://aliapt.citicdao.com\"},{\"org_id\":\"57981910e1983a04188487c8\",\"is_tenant\":\"0\",\"level\":\"\",\"superior\":\"\",\"citic_org_id\":\"\",\"name\":\"smartCloud\",\"is_supplier\":\"1\",\"is_cost_center\":\"0\",\"uri\":\"http://scapt.citicdao.com\"}]}\",\"resultCod\":\"000000\",\"resultMessage\":\"请求成功\"}";
		//String jsonString="{\"data\":\"{\"orgs\":[{\"org_id\":\"576203a86ae6ca04e1459582\",\"is_tenant\":\"0\",\"level\":\"\",\"superior\":\"\",\"citic_org_id\":\"\",\"name\":\"阿里云\",\"is_supplier\":\"1\",\"is_cost_center\":\"0\",\"bank_account\":\" \",\"uri\":\"http://aliapt.citicdao.com\"},{\"org_id\":\"57981910e1983a04188487c8\",\"is_tenant\":\"0\",\"level\":\"\",\"superior\":\"\",\"citic_org_id\":\"\",\"name\":\"smartCloud\",\"is_supplier\":\"1\",\"is_cost_center\":\"0\",\"uri\":\"http://scapt.citicdao.com\"}]}\",\"resultCode\":\"000000\",\"resultMessage\":\"请求成功\"}";
		System.out.println("the json is "+jsonString);
		// JSONObject data = JSONObject.parseObject(jsonString);
		
			 JSONObject data = JSONObject.parseObject(jsonString);
			 String order = data.getString("data");
		   if(!("".equals(order))&&null!=order){
			JSONObject jsonObject=JSONObject.parseObject(order);
			//for(Object key1:jsonObject1.keySet()){
				//String valueAdapt1=String.valueOf((Object)jsonObject1.get(key1));
				//JSONObject jsonObject=JSONObject.fromObject((Object)valueAdapt1);
			for(Object key:jsonObject.keySet()){
				if("orgs".equals(key)){
					String valueAdapt=String.valueOf((Object)jsonObject.get(key));
					LOG.debug("the valueAdapt " +valueAdapt);
					
					if("[".equals(valueAdapt.substring(0,1))){
						JSONArray jsonArray=jsonObject.getJSONArray("orgs");
						//JSONArray jsonArray=JSONArray.fromObject((Object)valueAdapt);
						for(int i=0;i<jsonArray.size();i++){
							addUrl(jsonArray.getString(i));
						}
					}else{
						addUrl(valueAdapt);
					}
				}
			}
		}
	}
	
	private void addUrl(String json){
		JSONObject jsonAdapt=JSONObject.parseObject(json);
		String orgId=null;
		String url=null;
		for(Object key:jsonAdapt.keySet()){
			if("org_id".equals(key)||"Org_id".equals(key)){
				orgId=String.valueOf(jsonAdapt.get(key));
			}
			if("uri".equals(key)||"Url".equals(key)){
				url=String.valueOf(jsonAdapt.get(key));
			}
		}
		if(orgId=="57981910e1983a04188487c8"){
			orgId=null;
			url=null;
		}else
			orgUrl.put(orgId,url);
	}
	
}
