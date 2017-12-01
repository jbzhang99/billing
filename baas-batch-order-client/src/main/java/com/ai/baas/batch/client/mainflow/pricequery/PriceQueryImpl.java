package com.ai.baas.batch.client.mainflow.pricequery;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ai.baas.batch.client.dao.mapper.bo.PmBasedataCode;
import com.ai.baas.batch.client.mainflow.failorder.BatchException;
import com.ai.baas.batch.client.mainflow.url.GetUrl;
import com.ai.baas.batch.client.mainflow.url.IGetUrl;
import com.ai.baas.batch.client.prepareflow.checkutil.BillingCheck;
import com.ai.baas.batch.client.prepareflow.params.Shopping;
import com.ai.baas.batch.client.service.business.interfaces.IServiceInfoSV;
import com.ai.baas.batch.client.util.BatchConstants;
import com.ai.baas.bmc.api.pricemaking.params.PricemakingResponseZX;
import com.ai.opt.base.exception.BusinessException;
import com.alibaba.fastjson.JSONObject;
@Component("priceQuery")
public class PriceQueryImpl implements IPriceQuery{

	private static Logger logger = LoggerFactory.getLogger(PriceQueryImpl.class);
	@Autowired
	@Qualifier("serviceInfo")
	IServiceInfoSV serviceInfoSV;
	
	@Override
	public Shopping getPrice(Shopping shopping) throws BatchException {
		//根据service_id拿到priceType
		IGetUrl getUrl = new GetUrl();
		//查询服务初始化表 	, 获取priceType
		PmBasedataCode pmBasedataCode = serviceInfoSV.getBasedataCode("ECITIC", shopping.getServiceId());
		shopping.setPmBasedataCode(pmBasedataCode); 
		shopping.setServiceType(pmBasedataCode.getPriceType()); 
		/*
		 * 目前仅有一种计费模式对应，先从pm表中直接获取
		 */
		String billingMode = checkBillingType(shopping.getPmBasedataCode());
		shopping.setBillingMode(billingMode);
		
		boolean needPrice = false;
		/*
		 *  判断是否需要调用价格查询 package,onetime,usage,step
		 */
		if(BillingCheck.needPriceMaking(billingMode)){
			needPrice = true;
			logger.info("request priceMaking api"); 
		}else{
			logger.info("not request priceMaking api"); 
		}
		
		if(!shopping.isNeedPrice()){
			logger.info("获取到detail_cost,跳过价格查询");
			needPrice = false;
		}
		if(needPrice){
			PricemakingResponseZX price = new PricemakingResponseZX();
			try{
				//priceMakingResponse
				price = getUrl.queryPricemaking(shopping);
				//isSuccess
				if(!price.getResponseHeader().getIsSuccess()){
				  logger.error("priceMaking failed, Response: "+JSONObject.toJSONString(price));
                  throw new BatchException("000004","Get pricemaking failed"); 
                }
				logger.info("priceMakingResponse :"+price); 
				shopping.setPricemaking(price); 
			}catch(Exception e){
				System.out.println("Get pricemaking failed"+shopping.getParameters()); 
				throw new BatchException("000004","Get pricemaking failed", e); 
			}
		}
		if(billingMode.equals(BatchConstants.Billing.ONETIME)||billingMode.equals(BatchConstants.Billing.PACKAGE)){
			
			shopping.setCronTab(createCronString()); 
			logger.info("set crontab: "+shopping.getCronTab());
		}
		System.out.println("crontab::::::"+shopping.getCronTab()); 
		
		return shopping;
	}
	  
	public static String createCronString() {
	        Calendar c  = Calendar.getInstance();  
	        String tomorrow = String.valueOf(c.get(Calendar.DAY_OF_MONTH)+1);
	        String thisMonth = String.valueOf(c.get(Calendar.MONTH)+1);
	        String thisYear = String.valueOf(c.get(Calendar.YEAR)); 
	        
	        String cron = "* * * "+tomorrow+" "+thisMonth+" ? "+thisYear;
	        
	        return cron;
	}
	
	public static String checkBillingType(PmBasedataCode pmBasedataCode){
		
		String[] billingTypes = pmBasedataCode.getBillingType().split(";");
		if(billingTypes.length == 1){
			return billingTypes[0];
		}else if(billingTypes.length > 1){
			/*
			 *  多种计费模式需通过价格查询匹配
			 */
			return "double";   // 多计费模式
		}else{
			throw new BusinessException("billingType match failed");
		}
		
		
	}
	

}
