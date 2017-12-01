package com.ai.baas.bmc.topology.cache;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.bmc.topology.util.BmcConstants;
import com.ai.baas.bmc.topology.util.DateUtil;
import com.ai.baas.dshm.client.impl.DshmClient;
import com.ai.baas.dshm.client.interfaces.IDshmClient;
import com.ai.baas.storm.exception.BusinessException;
import com.ai.baas.storm.util.BaseConstants;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

/**
 * 基本信息类(从缓存中查询)
 * 包括:客户、用户、账户和产品基本信息
 * @author majun
 * @since 2016.4.1
 * 
 */
public class BasicInfo {
	private static Logger logger = LoggerFactory.getLogger(BasicInfo.class);
	private String userInfoTable = "bl_userinfo";
	private String subsCommTable = "bl_subs_comm";
	private String priceInfoTable = "cp_price_info";
	private String priceDetailTable = "cp_price_detail";
	private String packageInfoTable = "cp_package_info";
	private String factorInfoTable = "cp_factor_info";
	private CacheProxy cacheProxy = CacheProxy.getInstance();
	//private IDshmClient dshmClient = new DshmClient();
	
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
		List<Map<String, String>> results = dshmClient.list(userInfoTable).where(queryParams).executeQuery(cacheProxy.getCacheClient());
		if(results == null || results.size()==0){
			throw new BusinessException("BMC-B0001","bl_userinfo表没有找到用户信息!");
		}
	   // String currentTime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
	    String startTime=data.get(BaseConstants.START_TIME);
		
	    String star,end;
	    for(Map<String, String> result:results){
	    	star = result.get(BmcConstants.ACTIVE_TIME);
	    	end = result.get(BmcConstants.INACTIVE_TIME);
	    	//if(DateUtil.isRange(star,end,currentTime))
	    	if(DateUtil.isRange(star,end,startTime)){
				data.put(BaseConstants.SUBS_ID, result.get(BaseConstants.SUBS_ID));
				data.put(BaseConstants.CUST_ID, result.get(BaseConstants.CUST_ID));
				data.put(BaseConstants.ACCT_ID, result.get(BaseConstants.ACCT_ID));
				break;
			}
	    }
	    
	}
	
	
	public void setProductData(Map<String, String> data) throws BusinessException{
		Map<String,String> params = new TreeMap<String,String>();
		params.put(BaseConstants.TENANT_ID, data.get(BaseConstants.TENANT_ID));
		params.put(BaseConstants.SUBS_ID, data.get(BaseConstants.SUBS_ID));
		//List<Map<String, String>> results = CacheProxy.getInstance().doQuery(subsCommTable, params);
		IDshmClient dshmClient = new DshmClient();
		List<Map<String, String>> results = dshmClient.list(subsCommTable).where(params).executeQuery(cacheProxy.getCacheClient());
	    if(results == null || results.size()==0){
			throw new BusinessException("BMC-B0003","没有找到用户订购产品信息!");
		}
	    String currentTime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
	    String star,end;
	    StringBuilder productIds = new StringBuilder();
	    for(Map<String, String> result:results){
	    	star = result.get(BmcConstants.ACTIVE_TIME);
	    	end = result.get(BmcConstants.INACTIVE_TIME);
	    	if(DateUtil.isRange(star,end,currentTime)){
	    		productIds.append(result.get(BmcConstants.PRODUCT_ID)).append(BaseConstants.COMMON_SPLIT);
			}
	    }
	    System.out.println("productIds===="+productIds.length());
	    data.put(BmcConstants.PRODUCT_ID, productIds.delete(productIds.length()-1, productIds.length()).toString());
	}
	
	
	public void setApnMatch(Map<String,String>data)throws BusinessException{
	    //cp_price_info表
	    Map<String,String> infoParams = new TreeMap<String,String>();
	    infoParams.put(BaseConstants.TENANT_ID, data.get(BaseConstants.TENANT_ID));
	    infoParams.put(BmcConstants.PRICE_CODE, data.get(BmcConstants.PRODUCT_ID));      
        //List<Map<String, String>> results = CacheProxy.getInstance().doQuery(subsCommTable, params);
        IDshmClient dshmClient = new DshmClient();
        List<Map<String, String>> priceInfoResults = dshmClient.list(priceInfoTable).where(infoParams).executeQuery(cacheProxy.getCacheClient());
        if(priceInfoResults == null || priceInfoResults.size()==0){
            throw new BusinessException("BMC-B0003","cp_price_info表中没有找到产品信息! product_id : "+data.get(BmcConstants.PRODUCT_ID));
        }
        String priceCode = null;
        for(Map<String, String>result:priceInfoResults){
            priceCode = result.get(BmcConstants.PRICE_CODE);
        }
        //cp_price_detail表
        Map<String,String> detailParams = new TreeMap<String,String>();
        detailParams.put(BmcConstants.PRICE_CODE, priceCode);
        List<Map<String, String>> priceDetailResults = dshmClient.list(priceDetailTable).where(detailParams).executeQuery(cacheProxy.getCacheClient());
        if(priceDetailResults == null || priceDetailResults.size()==0){
            throw new BusinessException("BMC-B0003","cp_price_detail表中没有找到产品信息! product_id : "+data.get(BmcConstants.PRODUCT_ID));
        }
        String detailCode = null;
        for(Map<String, String>result:priceDetailResults){
            detailCode = result.get(BmcConstants.DETAIL_CODE);
        }
        //cp_package_info表
        Map<String,String> packageParams = new TreeMap<String,String>();
        packageParams.put(BmcConstants.DETAIL_CODE, detailCode);
        List<Map<String, String>> packageResults = dshmClient.list(packageInfoTable).where(packageParams).executeQuery(cacheProxy.getCacheClient());
        if(packageResults == null || packageResults.size()==0){
            throw new BusinessException("BMC-B0003","cp_package_info表中没有找到产品信息! product_id : "+data.get(BmcConstants.PRODUCT_ID));
        }
        String factorCode=null;
        for(Map<String, String>result:packageResults){
            factorCode = result.get(BmcConstants.FACTOR_CODE);                 
        }
        //cp_factor_info表
        Map<String, String>factorParams = new TreeMap<String,String>();
        factorParams.put(BmcConstants.FACTOR_CODE, factorCode);
        System.out.println("1");
        List<Map<String, String>> factorResults = dshmClient.list(factorInfoTable).where(factorParams).executeQuery(cacheProxy.getCacheClient());
        if(factorResults == null || factorResults.size()==0){
            throw new BusinessException("BMC-B0003","cp_factor_info表中没有找到产品信息! product_id : "+data.get(BmcConstants.PRODUCT_ID));
        }
        System.out.println("2"); 
        String factorName = null;
        String factorValue = null;
        for(Map<String, String>result:factorResults){
            System.out.println("result:" +result.toString()); 
            factorName = result.get("factor_name");
            if(factorName.equals("apn_code")){
                factorValue = result.get("factor_value");
                System.out.println("apn_code " + factorValue); 
                break;
            }
        }
        if(factorValue == null){
            throw new BusinessException("BMC-B0003","参考因素表中，未找到对应的apn_code");       
        }       
        System.out.println("factorValue"+factorValue); 
        //比较，apnMatch存入data
        String dataApn = data.get(BmcConstants.APN_CODE);
        String[] apnValues;
        String apnMatch = "0";
        apnValues = StringUtils.splitPreserveAllTokens(factorValue,";");
        for(String apnValue : apnValues){
            if(apnValue.equals(dataApn)){
                apnMatch = "1";
                break;
            }
        }
        data.put(BmcConstants.APN_MATCH,apnMatch);
        System.out.println("setApnMatch成功，apnMatch:"+data.get(BmcConstants.APN_MATCH)); 
        System.out.println("setApnMatch成功，apnMatch:"+apnMatch); 
	}
	
}
