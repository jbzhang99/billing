package com.ai.baas.bmc.srv.flow.cache;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.bmc.srv.failbill.BusinessException;
import com.ai.baas.bmc.srv.util.BaasConstants;
import com.ai.baas.bmc.srv.util.DateUtil;
import com.ai.baas.dshm.client.impl.DshmClient;
import com.ai.baas.dshm.client.interfaces.IDshmClient;


public class BasicInfo {
	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);
	private String userInfoTable = "bl_userinfo";
	private String subsCommTable = "bl_subs_comm";
	
	/**
	 * 查询用户数据
	 * @param data
	 * @throws BusinessException
	 */
	public void setUserData(Map<String, String> data,Map<String,String> queryParams) throws BusinessException{
		//Map<String,String> params = new TreeMap<String,String>();
		//params.put(BaseConstants.TENANT_ID, data.get(BaseConstants.TENANT_ID));
	    //params.put("price_code", "999");
	    //List<Map<String, String>> results = CacheProxy.getInstance().doQuery(userInfoTable, queryParams);
		IDshmClient dshmClient = new DshmClient();
		List<Map<String, String>> results = dshmClient.list(userInfoTable).where(queryParams).executeQuery(CacheProxy.getCacheClient());
		if(results == null || results.size()==0){
			throw new BusinessException("BMC-B0001","bl_userinfo表没有找到用户信息!");
		}
	    String currentTime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
	    //String startTime=data.get(BaasConstants.START_TIME);
	    String star,end;
	    for(Map<String, String> result:results){
	    	star = result.get(BaasConstants.ACTIVE_TIME);
	    	end = result.get(BaasConstants.INACTIVE_TIME);
	    	if(DateUtil.isRange(star,end,currentTime)){
	    	//if(DateUtil.isRange(star,end,startTime)){
				data.put(BaasConstants.SUBS_ID, result.get(BaasConstants.SUBS_ID));
				data.put(BaasConstants.CUST_ID, result.get(BaasConstants.CUST_ID));
				data.put(BaasConstants.ACCT_ID, result.get(BaasConstants.ACCT_ID));
				break;
			}
	    }
	}
	
	/**
	 * 查询订购信息
	 * @param data
	 * @throws BusinessException
	 */
	public void setProductData(Map<String, String> data) throws BusinessException{
		Map<String,String> params = new TreeMap<String,String>();
		params.put(BaasConstants.TENANT_ID, data.get(BaasConstants.TENANT_ID));
		params.put(BaasConstants.SUBS_ID, data.get(BaasConstants.SUBS_ID));
		IDshmClient dshmClient = new DshmClient();
		List<Map<String, String>> results = dshmClient.list(subsCommTable).where(params).executeQuery(CacheProxy.getCacheClient());
	    if(results == null || results.size()==0){
			throw new BusinessException("BMC-B0002","没有找到用户订购产品信息!");
		}
	    //String currentTime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
	    String currentTime = getCompareTime(data);
	    String star,end;
	    StringBuilder productIds = new StringBuilder();
	    for(Map<String, String> result:results){
	    	star = result.get(BaasConstants.ACTIVE_TIME);
	    	end = result.get(BaasConstants.INACTIVE_TIME);
	    	//System.out.println("--->>>["+result.get(BaasConstants.PRODUCT_ID)+"] star="+star+",end="+end+",current="+currentTime);
	    	if(DateUtil.isRange(star,end,currentTime)){
	    		productIds.append(result.get(BaasConstants.PRODUCT_ID)).append(BaasConstants.COMMON_SPLIT);
			}
	    }
	    //System.out.println("productIds===="+productIds.length());
		if (productIds.length() <= 0) {
	    	throw new BusinessException("BMC-B0003","没有找到该用户订购信息!");
	    }
	    data.put(BaasConstants.PRODUCT_ID, productIds.delete(productIds.length()-1, productIds.length()).toString());
	    
	}
	
	private String getCompareTime(Map<String, String> data){
		String rtnTime = data.get(BaasConstants.START_TIME);
		if(StringUtils.isNotBlank(rtnTime) && rtnTime.length() == 14){
			return rtnTime;
		}else{
			return DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
		}
	}
	
	
}
