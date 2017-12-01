package com.ai.baas.batch.client.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

public class GetServiceId {
	private static final Logger LOG=LoggerFactory.getLogger(GetServiceId.class);
	public  Map<String, List<String>> supIds=new HashMap<String, List<String>>();
	private String supplyUrl=null;
	Map<String, String> param=new HashMap<String,String>();
	public GetServiceId(){
		supplyUrl=getUrl.getInstance().getSupplyUrl();
	}
	
	public Map<String, List<String>> getSerIDs(){
		String SERVICE_PATH_QUERY=null;
		//此处需要
		//String jsonString="{\"suppliers\": [{\"id\": \"6ba76566-0251-4e1a-a188-64e1b2d26be2\",\"services\": [{\"id\": \"576206bb6ae6ca04e145958d\",\"config_options\": [{\"name\": \"RegionID\"}]}]}]}";
		if(GetRecordDetail.baseCodes!=null){
	         for(BaseCode baseCode : GetRecordDetail.baseCodes){
	            if("SERVICE_PATH_QUERY".equals(baseCode.getParamCode())){
	            	SERVICE_PATH_QUERY = baseCode.getDefaultValue();
	             }                
	          }
	     }
		//SERVICE_PATH_QUERY="http://10.248.4.11:80/newservermgt/services.do";
		if(null==SERVICE_PATH_QUERY){
			throw new RuntimeException("服务目录url没有加载进缓存........");
		}
		LOG.debug("服务目录url地址为："+SERVICE_PATH_QUERY);
		param.put("type", "1");
		param.put("value", "");
		String jsonString=HttpClientUtil.sendGet(SERVICE_PATH_QUERY,param);
		//String jsonString="{\"data\":\"{\\\"services\\\":[{\\\"service_id\\\":\\\"576206bb6ae6ca04e145958d\\\",\\\"service_type_name\\\":\\\"线上采购\\\",\\\"supplier_id\\\":\\\"576203a86ae6ca04e1459582\\\",\\\"image_url\\\":\\\"https://img.alicdn.com/tps/TB16cHjLFXXXXX4XFXXXXXXXXXX-111-27.png\\\",\\\"company\\\":\\\"阿里云\\\",\\\"name\\\":\\\"云服务器ECS\\\",\\\"score\\\":\\\"85\\\",\\\"service_type_id\\\":\\\"1\\\"},{\\\"service_id\\\":\\\"5762107c6ae6ca04e14595b8\\\",\\\"service_type_name\\\":\\\"线上采购\\\",\\\"supplier_id\\\":\\\"576203a86ae6ca04e1459582\\\",\\\"image_url\\\":\\\"https://img.alicdn.com/tps/TB16cHjLFXXXXX4XFXXXXXXXXXX-111-27.png\\\",\\\"company\\\":\\\"阿里云\\\",\\\"name\\\":\\\"云数据库RDS\\\",\\\"score\\\":\\\"85\\\",\\\"service_type_id\\\":\\\"1\\\"},{\\\"service_id\\\":\\\"57721abd2fa45f06e1c013d2\\\",\\\"service_type_name\\\":\\\"线上开通\\\",\\\"supplier_id\\\":\\\"576203a86ae6ca04e1459582\\\",\\\"image_url\\\":\\\"https://img.alicdn.com/tps/TB16cHjLFXXXXX4XFXXXXXXXXXX-111-27.png\\\",\\\"company\\\":\\\"阿里云\\\",\\\"name\\\":\\\"容器服务\\\",\\\"score\\\":\\\"85\\\",\\\"service_type_id\\\":\\\"2\\\"},{\\\"service_id\\\":\\\"57721cb62fa45f06e1c013d6\\\",\\\"service_type_name\\\":\\\"线上开通\\\",\\\"supplier_id\\\":\\\"576203a86ae6ca04e1459582\\\",\\\"image_url\\\":\\\"https://img.alicdn.com/tps/TB16cHjLFXXXXX4XFXXXXXXXXXX-111-27.png\\\",\\\"company\\\":\\\"阿里云\\\",\\\"name\\\":\\\"OSS服务\\\",\\\"score\\\":\\\"85\\\",\\\"service_type_id\\\":\\\"2\\\"},{\\\"service_id\\\":\\\"57721e052fa45f06e1c013da\\\",\\\"service_type_name\\\":\\\"线上开通\\\",\\\"supplier_id\\\":\\\"576203a86ae6ca04e1459582\\\",\\\"image_url\\\":\\\"https://img.alicdn.com/tps/TB16cHjLFXXXXX4XFXXXXXXXXXX-111-27.png\\\",\\\"company\\\":\\\"阿里云\\\",\\\"name\\\":\\\"消息队列\\\",\\\"score\\\":\\\"85\\\",\\\"service_type_id\\\":\\\"2\\\"},{\\\"service_id\\\":\\\"5785e232b9aa1e3769039c19\\\",\\\"service_type_name\\\":\\\"线上采购\\\",\\\"supplier_id\\\":\\\"576203a86ae6ca04e1459582\\\",\\\"image_url\\\":\\\"https://img.alicdn.com/tps/TB16cHjLFXXXXX4XFXXXXXXXXXX-111-27.png\\\",\\\"company\\\":\\\"阿里云\\\",\\\"name\\\":\\\"Redis\\\",\\\"score\\\":\\\"85\\\",\\\"service_type_id\\\":\\\"1\\\"},{\\\"service_id\\\":\\\"57981ab7e1983a04434e1b4e\\\",\\\"service_type_name\\\":\\\"线上采购\\\",\\\"supplier_id\\\":\\\"57981910e1983a04188487c8\\\",\\\"image_url\\\":\\\"http://yun.citic.com/images/smart_cloud.png\\\",\\\"company\\\":\\\"SmartCloud\\\",\\\"name\\\":\\\"ResourcePool\\\",\\\"score\\\":\\\"85\\\",\\\"service_type_id\\\":\\\"1\\\"}]}\",\"resultCode\":\"000000\",\"resultMessage\":\"请求成功\"}";
		if(!("".equals(jsonString))&&null!=jsonString){
			analyJson(jsonString);
			return supIds;
		}
		else {
			return null;
		}
	}
	
	
	
	private  void analyJson(String jsonString){
		 JSONObject data = JSONObject.parseObject(jsonString);
		 String order = data.getString("data");
		 if(!("".equals(order))&&null!=order){
		JSONObject jsonObject=JSONObject.parseObject(order);
			for(Object firstKey:jsonObject.keySet()){
				//此时对应的key为suppliers
		        
				if("services".equals(firstKey)){
					
					String valueSup=String.valueOf((Object)jsonObject.get(firstKey));
					//此处需要添加判断  是否为数组的形式
					if("[".equals(valueSup.substring(0,1))){
						//JSONArray jsonDetail=JSONArray.fromObject((Object)valueSup);
						
						JSONArray jsonDetail=jsonObject.getJSONArray("services");
						for(int i=0;i<jsonDetail.size();i++){
							getSupId(jsonDetail.getString(i));
						}
					}else{
						getSupId(valueSup);
					}
				}
			}
		 }
		
	}
	
	private void getSupId(String valueSup){
		//List<String> serviceId=new ArrayList<String>();
		String idValue=null;
		String valueSer=null;
		JSONObject jsonSup = JSONObject.parseObject(valueSup);
		//JSONObject jsonSup= JSONObject.fromObject((Object)valueSup);
		for(Object supId:jsonSup.keySet()){
			if("supplier_id".equals(supId)||"Supplier_id".equals(supId)||"Supplier_Id".equals(supId)){
				idValue=String.valueOf((Object)jsonSup.get(supId));
				LOG.debug("the supply id is "+idValue);
			}
			if("service_id".equals(supId)||"Service_id".equals(supId)){
				//此处需要对services 是否为数组进行遍历
				valueSer=String.valueOf((Object)jsonSup.get(supId));
				
//				if("[".equals(valueSer.substring(0,1))){
//					JSONArray jsonDetail=jsonSup.getJSONArray("services");
//					//JSONArray jsonDetail=JSONArray.fromObject((Object)valueSer);
//					for(int i=0;i<jsonDetail.size();i++){
//						String serValue=getServiceId(jsonDetail.getString(i));
//						LOG.debug("the serValue is "+serValue);
//						serviceId.add(serValue);
//					}
//				}else{
//					serviceId.add(getServiceId(valueSer));
//				}
			}
		}
		if(idValue!=null&&valueSer!=null){
			List<String> serviceIds=supIds.get(idValue);
			if(serviceIds==null){
				List<String> serviceId=new ArrayList<String>();
				serviceId.add(valueSer);
				LOG.debug("first  the supply is "+idValue+ "   the service_id "+valueSer);
				supIds.put(idValue, serviceId);
			}else{
				serviceIds.add(valueSer);
				LOG.debug("second   the supply is "+idValue+ "   the service_id "+valueSer);
				supIds.put(idValue, serviceIds);
			}
		}
		//supIds.put(idValue, serviceId);
		//LOG.debug("the supply is "+idValue+"  the serviceId is "+serviceId);
	}
	
	//获取serviceId
	private String getServiceId(String value){
		JSONObject jsonSup = JSONObject.parseObject(value);
		//JSONObject jsonSup= JSONObject.fromObject((Object)value);
		for(Object sueviceId:jsonSup.keySet()){
			if("id".equals(sueviceId)||"ID".equals(sueviceId)||"Id".equals(sueviceId)){
				return String.valueOf((Object)jsonSup.get(sueviceId));
			}
		}
		return null;
	}
}
