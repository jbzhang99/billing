package com.ai.baas.batch.client.core;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.batch.client.constants.ClientConstants;
import com.ai.baas.batch.client.dao.interfaces.BmcUsageLogYyyymmMapper;
import com.ai.baas.batch.client.dao.mapper.bo.BmcUsageLogYyyymm;
import com.ai.baas.batch.client.service.ICalProcessor;
import com.ai.baas.batch.client.util.DshmUtil;
import com.ai.baas.batch.client.util.MyJsonUtil;
import com.ai.baas.batch.client.util.PropertiesUtil;
import com.ai.baas.batch.client.util.RouterUtil;
import com.ai.baas.batch.client.util.getUrl;
import com.ai.baas.batch.client.util.lastDayUtil;
import com.ai.baas.bmc.api.baseInfo.interfaces.IBaseInfoSV;
import com.ai.baas.bmc.api.baseInfo.params.BaseCode;
import com.ai.baas.bmc.api.baseInfo.params.BaseCodeInfo;
import com.ai.baas.bmc.api.baseInfo.params.QueryInfoParams;
import com.ai.opt.sdk.dubbo.extension.DubboRestResponse;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.dubbo.util.HttpClientUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
@Service
@Transactional
public class GetRecordDetail {
	private static final Logger LOG=LoggerFactory.getLogger(GetRecordDetail.class);
	private ICacheClient cacheClient;
	//private Map<String, String> adaptUrl=new HashMap<String,String>();
	//private Map<String, List<String>> supIds=new HashMap<String,List<String>>();
	private Map<String,List<String>> urlIds=new HashMap<String,List<String>>();
	public static List<BaseCode> baseCodes=new ArrayList<BaseCode>();
	@Autowired
    BmcUsageLogYyyymmMapper umapper;   

	public void recordFromZX(){
		
		String szNoRtmServiceid = PropertiesUtil.getValue("batch.nortm_service_id");
		LOG.debug("nortm_service_id = " + szNoRtmServiceid);
		String szAr[] = szNoRtmServiceid.split(";",0);
		List lServiceId = new ArrayList<String>();
		for(int i=0;i<szAr.length;i++)
		{
			lServiceId.add(szAr[i]);
		}
		
		
		Map<String, String> param=new HashMap<String,String>();
//		Date dnow=new Date();
//		SimpleDateFormat dfor=new SimpleDateFormat("yyyyMMdd");
		lastDayUtil lastDay=new lastDayUtil();
		String dbefore=lastDay.getLast2Day();
		String nowTime=lastDay.getLastDay();
		//用于获取接口的url地址
		getUrlParam();
		String startData=getUrl.getInstance().getStartTime();
		String endData=getUrl.getInstance().getEndTime();
		if("".equals(startData)||("".equals(endData))){
			StringBuilder startTime=new StringBuilder();
			startTime.append(dbefore).append("000000");
			StringBuilder endTime=new StringBuilder();
			endTime.append(dbefore).append("235959");
			param.put("start_date", dbefore);
			param.put("end_date", nowTime);
		}
		else{
			param.put("start_date", startData);
			param.put("end_date", endData);
		}
//		param.put("start_date", "20160808");
//		param.put("end_date", "20160807");
//		param.put("grain_size", "day");
		//StringBuilder zkUrl=new StringBuilder();
		//用于获取供应商的信息
		getUrlIds();
		//开始对每个供应商进行遍历
		LOG.debug("");
		String SERVICE_RECORDS_PATH=null;
		if(baseCodes!=null){
	         for(BaseCode baseCode : baseCodes){
	            if("SERVICE_RECORDS_PATH".equals(baseCode.getParamCode())){
	                	SERVICE_RECORDS_PATH = baseCode.getDefaultValue();
	             }                
	          }
	     }
		if(null==SERVICE_RECORDS_PATH){
			throw new RuntimeException("供应商使用记录url没有加载进缓存........");
		}
		try{			
			for(Entry<String, List<String>> entry:urlIds.entrySet()){
				
				//zkUrl.append("http://10.248.4.12:32795").append(SERVICE_RECORDS_PATH);
				//zkUrl.append(entry.getKey()).append(SERVICE_RECORDS_PATH);	
				for(String serviceId:entry.getValue()){
					try{
						LOG.debug("the service_id is  "+serviceId);
						StringBuilder zkUrl=new StringBuilder();
						zkUrl.append(entry.getKey()).append(SERVICE_RECORDS_PATH);	
						if(lServiceId.contains(serviceId))
							continue;
						if("57721abd2fa45f06e1c013d2".equals(serviceId))
							continue;  
						
						
	//					if(("57721e052fa45f06e1c013da".equals(serviceId))||("5785e232b9aa1e3769039c19".equals(serviceId))||("57721cb62fa45f06e1c013d6").equals(serviceId))
	//						param.put("grain_size", "hour");
						else
							param.put("grain_size", "hour");
						
						//测试一下，只处理云盘的  后面再去掉
						/*if(!serviceId.equals("5836a0462fbaaf0aee0db7a2"))
							continue;*/
						
						zkUrl.append(serviceId);
						LOG.debug("使用记录url为："+zkUrl.toString()+      param.get("grain_size"));
						String response=HttpClientUtil.sendGet(zkUrl.toString(), param);
						//String response="{\"data\":\"{\\\"requestId\\\": \\\"57a9940bc9e77c00348c4e25\\\",\\\"state\\\": \\\"succeeded\\\",\\\"usage_and_expenses_format\\\": [{\\\"name\\\": \\\"CPUs\\\",\\\"in_list\\\": true},{\\\"name\\\": \\\"CloudDataDiskSize\\\",\\\"in_list\\\": true}],\\\"usage_and_expenses_data\\\": [{\\\"ProviderId\\\": \\\"1991067246874857\\\",\\\"EndTime\\\": \\\"2016-07-12T00:00:00Z\\\",\\\"InstanceId\\\": \\\"citictest5\\\",\\\"ImageSize\\\": \\\"42949672960\\\",\\\"Memory\\\": \\\"1073741824\\\",\\\"StartTime\\\": \\\"2016-07-11T16:00:00Z\\\",\\\"TmpDataDiskSize\\\": \\\"0\\\",\\\"SsdDataDiskSize\\\": \\\"0\\\",\\\"NetworkOut\\\": \\\"0\\\",\\\"ImageOS\\\": \\\"CentOS\\\",\\\"CPUs\\\": \\\"1\\\",\\\"NetworkIn\\\": \\\"0\\\",\\\"Bandwidth\\\": \\\"0\\\",\\\"Region\\\": \\\"cn-hangzhou-dg-a01\\\",\\\"CloudDataDiskSize\\\": \\\"0\\\",\\\"Disk\\\": \\\"0\\\"},{\\\"ProviderId\\\": \\\"1255207400969322\\\",\\\"EndTime\\\": \\\"2016-07-12T00:00:00Z\\\",\\\"InstanceId\\\": \\\"citictest\\\",\\\"ImageSize\\\": \\\"21474836480\\\",\\\"Memory\\\": \\\"1073741824\\\",\\\"StartTime\\\": \\\"2016-07-11T16:00:00Z\\\",\\\"TmpDataDiskSize\\\": \\\"0\\\",\\\"SsdDataDiskSize\\\": \\\"0\\\",\\\"NetworkOut\\\": \\\"0\\\",\\\"ImageOS\\\": \\\"CENTOS5\\\",\\\"CPUs\\\": \\\"1\\\",\\\"NetworkIn\\\": \\\"0\\\",\\\"Bandwidth\\\": \\\"0\\\",\\\"Region\\\": \\\"cn-beijing-btc-a01\\\",\\\"CloudDataDiskSize\\\": \\\"0\\\",\\\"Disk\\\": \\\"0\\\"}]}\",\"resultCode\":\"000000\",\"resultMessage\":\"请求成功\"};";
						
						if(!("".equals(response))&&null!=response&&"000000".equals(JSON.parseObject(response, DubboRestResponse.class).getResultCode())){
							processUsageMessages(serviceId, response);
						}
					}catch(FileNotFoundException e){
						 LOG.error("FileNotFoundException 异常"+e.getMessage());
						 continue;
					}catch(Exception e){
						 LOG.error("Exception 异常"+e.getMessage());
						 continue;
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void processUsageMessages(String serviceId, String response) throws Exception {
		LOG.debug("开始记录原始数据");

		BmcUsageLogYyyymm usageRecord=new BmcUsageLogYyyymm();
		String currentMonth= DateUtil.getDateString("YYYYMM");

		JSONObject[] jsonarray= MyJsonUtil.analyJson(response);
		for(JSONObject json:jsonarray){

            usageRecord.setCreateTime(DateUtil.getSysDate());
            usageRecord.setServiceId(serviceId);
            if(JSON.toJSONString(json).length()< ClientConstants.SQL_LENGTH){
                usageRecord.setUsageRecord(JSON.toJSONString(json));
            }else{
                usageRecord.setUsageRecord(JSON.toJSONString(json).substring(0, ClientConstants.SQL_LENGTH-1));
                LOG.error("批处理数据serviceI:"+serviceId+"对应数据过长，截取了4096个");
            }
            umapper.insert(currentMonth, usageRecord);
        }
		LOG.debug("记录原始数据结束");
		//String response="{\"requestId\": \"57a9940bc9e77c00348c4e25\",\"state\": \"succeeded\",\"usage_and_expenses_format\": [{\"name\": \"CPUs\",\"in_list\": true},{\"name\": \"CloudDataDiskSize\",\"in_list\": true}],\"usage_and_expenses_data\": [{\"ProviderId\": \"1991067246874857\",\"EndTime\": \"2016-07-12T16:00:00Z\",\"InstanceId\": \"citictest5\",\"ImageSize\": \"42949672960\",\"Memory\": \"1073741824\",\"StartTime\": \"2016-07-11T16:00:00Z\",\"TmpDataDiskSize\": \"0\",\"SsdDataDiskSize\": \"0\",\"NetworkOut\": \"0\",\"ImageOS\": \"CentOS\",\"CPUs\": \"1\",\"NetworkIn\": \"0\",\"Bandwidth\": \"0\",\"Region\": \"cn-hangzhou-dg-a01\",\"CloudDataDiskSize\": \"0\",\"Disk\": \"0\"},{\"ProviderId\": \"1255207400969322\",\"EndTime\": \"2016-07-12T16:00:00Z\",\"InstanceId\": \"citictest\",\"ImageSize\": \"21474836480\",\"Memory\": \"1073741824\",\"StartTime\": \"2016-07-11T16:00:00Z\",\"TmpDataDiskSize\": \"0\",\"SsdDataDiskSize\": \"0\",\"NetworkOut\": \"0\",\"ImageOS\": \"CENTOS5\",\"CPUs\": \"1\",\"NetworkIn\": \"0\",\"Bandwidth\": \"0\",\"Region\": \"cn-beijing-btc-a01\",\"CloudDataDiskSize\": \"0\",\"Disk\": \"0\"}]}";
		//String response="{\"usage_and_expenses_format\": [{\"name\": \"tradetype3\"}],\"usage_and_expenses_data\": [{\"EndTime\": \"2016-07-10T09:00:00Z\",\"InstanceId\": \"fbf20ccc-ef9e-4377-bbe1-7c56bd6b996c\",\"StartTime\": \"2016-07-10T08:00:00Z\",\"NetworkOut\": \"124\",\"NetworkIn\": \"0\",\"Region\": \"cn-beijing\"}]}";
		String strClazz=getType(serviceId);
		if(strClazz!=null){
            ICalProcessor processor= RouterUtil.getProcessorByRoute(strClazz);
            processor.buildCalAdapt(response,serviceId);
        }
	}

	private void getUrlParam(){
		 IBaseInfoSV baseinfoSV = DubboConsumerFactory.getService(IBaseInfoSV.class);
	        QueryInfoParams param = new QueryInfoParams();
	        param.setParamType("ADAPTER_PARAM");
	        param.setTradeSeq(System.currentTimeMillis()+"");
	        param.setTenantId("PUB");
	        BaseCodeInfo info = baseinfoSV.getBaseInfo(param);
	        baseCodes = info.getParamList();
	}
	private void getUrlIds(){
		GetServiceId serviceId=new GetServiceId();
		serviceId.getSerIDs();
		GetOrgUrl orgUrl=new GetOrgUrl();
		orgUrl.adaptUrl();
		//supIds=serviceId.supIds;
		for(Entry<String, List<String>> entry: serviceId.supIds.entrySet()){
			String orgId=entry.getKey();
			List<String> ids=entry.getValue();
			for(Entry<String, String> entryId:orgUrl.orgUrl.entrySet()){
				if(orgId.equals(entryId.getKey())){
					if("57981910e1983a04188487c8".equals(orgId)){
						LOG.debug("the org is .........."+orgId);
						break;
					}
						
					String url=entryId.getValue();
					urlIds.put(url, ids);
				}
			}
	}
	}
	
	private String getType(String serviceId){
		String clazz=null;
		switch(serviceId){
		case "576206bb6ae6ca04e145958d":  
			clazz="com.ai.baas.batch.client.service.impl.EcsCalProcessorImpl";
			break;
		case "57721e052fa45f06e1c013da":
			clazz="com.ai.baas.batch.client.service.impl.OnsCalProcessorImpl";
			break;
		case "5762107c6ae6ca04e14595b8":
			clazz="com.ai.baas.batch.client.service.impl.RdsCalProcessorImpl";
			break;
		case "5785e232b9aa1e3769039c19": 
			clazz="com.ai.baas.batch.client.service.impl.KvsCalProcessorImpl";
			break;
		case "57721cb62fa45f06e1c013d6":
			clazz="com.ai.baas.batch.client.service.impl.OssCalProcessorImpl";
			break;
		case "583b954378c93e0473411da8":
			clazz="com.ai.baas.batch.client.service.impl.SlbCalProcessorImpl";
			break;
		case "58b3cc2618e1fb442d8f4077"://新增 EIP的处理
			clazz="com.ai.baas.batch.client.service.impl.EipCalProcessorImpl";
			break;
		case "5836a0462fbaaf0aee0db7a2"://新增云盘的处理
			clazz="com.ai.baas.batch.client.service.impl.YupCalProcessorImpl";
			break;
		case "58e74c1a557f56075552c503"://新增DRDS的处理
			clazz="com.ai.baas.batch.client.service.impl.DrdsCalProcessorImpl";
			break;
		case "58f6f22318795506e40112fc"://新增nat网关的处理
			clazz="com.ai.baas.batch.client.service.impl.NatCalProcessorImpl";
			break;
		case "58f86433434fa917ce66a178"://新增共享带宽的处理
			clazz="com.ai.baas.batch.client.service.impl.ShwidthCalProcessorImpl";
			break;	
		}
		
		return clazz;
	}
//	private static void Test(){
//		Map<String, List<String>> supId1=new HashMap<String, List<String>>();
//		List<String> serviceId=new ArrayList<String>();
//		serviceId.add("1");
//		serviceId.add("2");
//		supId1.put("qwe", serviceId);
//		List<String> serviceId2=new ArrayList<String>();
//		serviceId2.add("1");
//		serviceId2.add("2");
//		supId1.put("576203a86ae6ca04e1459582", serviceId);
//		Map<String, String> url=new HashMap<String, String>();
//		url.put("qwe", "http:111");
//		url.put("576203a86ae6ca04e1459582", "http:222");
//		for(Entry<String, List<String>> entry: supId1.entrySet()){
//			String orgId=entry.getKey();
//			List<String> ids=entry.getValue();
//			for(Entry<String, String> entryId:url.entrySet()){
//				if(orgId.equals(entryId.getKey())){
//					if("576203a86ae6ca04e1459582".equals(orgId))
//						break;
//					String url1=entryId.getValue();
//					System.out.println("the url1"+url1+"    ids "+ids.size());
//					//urlIds.put(url1, ids);
//				}
//			}
//	}
//	}
	public static void  main(String [] args){
		//Test();
//		getRecordDetail test=new getRecordDetail();
//		test.recordFromZX();
		String json="{\"factor_name\":\"IoOptimized\",\"factor_value\":\"optimized\",\"price_product_type\":\"ECS\",\"cunit_price_code\":\"3269\"}";
		DshmUtil.getIdshmSV().initLoader("cp_cunitprice_detail", json,1);
		
	}
	

}
