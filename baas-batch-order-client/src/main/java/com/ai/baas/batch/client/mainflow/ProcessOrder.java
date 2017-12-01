package com.ai.baas.batch.client.mainflow;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ai.baas.batch.client.constants.ClientConstants;
import com.ai.baas.batch.client.mainflow.failorder.BatchException;
import com.ai.baas.batch.client.mainflow.failorder.IRecordBatchFailMsg;
import com.ai.baas.batch.client.mainflow.priceinfo.IRecordPriceInfo;
import com.ai.baas.batch.client.mainflow.priceinfo.RecordPriceInfoImpl;
import com.ai.baas.batch.client.mainflow.pricequery.IPriceQuery;
import com.ai.baas.batch.client.mainflow.url.GetUrl;
import com.ai.baas.batch.client.mainflow.url.IGetUrl;
import com.ai.baas.batch.client.prepareflow.bakJson.IBakJson;
import com.ai.baas.batch.client.prepareflow.checkJson.ICheckJson;
import com.ai.baas.batch.client.prepareflow.convert.IConvert;
import com.ai.baas.batch.client.prepareflow.params.OrderParam;
import com.ai.baas.batch.client.prepareflow.params.Shopping;
import com.alibaba.fastjson.JSON;

@Component
public class ProcessOrder {

	private static Logger logger = LoggerFactory.getLogger(ProcessOrder.class);
	@Autowired
	@Qualifier("recordFailMsg")
	IRecordBatchFailMsg recordFailMsgImpl;
	@Autowired
	ICheckJson checkJson; 
	@Autowired
	IConvert convert;
	@Autowired
	@Qualifier("priceQuery")
	IPriceQuery priceQuery;
	@Autowired
	IBakJson bakJson;	
	@Autowired
	@Qualifier("recordPriceInfo")
	IRecordPriceInfo iRecordPriceInfo;
	private OrderParam orderParam;
	
	public String processOrder(String json){
		System.out.println("bakJSON");
		bakJson.saveOrderJson(json); 
		
		IGetUrl getUrl = new GetUrl();
		try{
			checkJson.checkJson(json);
		}catch(BatchException e){
			logger.info("[json error]",e);
			recordFailMsgImpl.recordOriginJson(e.getCode(),subExceptionString(e.getStrStackTrace()), "checkJson", json);
			return e.getCode();
		}catch(Exception e){
			recordFailMsgImpl.recordOriginJson("000001", subExceptionString(e.toString()), "checkJson", json); 
			return "000001";
		}
		try{
			orderParam =convert.transtoOrder(json);
			logger.info("orderParam : "+JSON.toJSONString(orderParam)); 
		}catch(Exception e){
			logger.info("[json trans error]",e);
			recordFailMsgImpl.recordOriginJson("000002", subExceptionString(e.toString()), "convert", json);
			return "000002";
		}
		logger.info("[Convert json]:"+JSON.toJSONString(orderParam));    
		
		/*
		 * 获取必要的数据
		 */
		String extCustId = null;
		try{
			extCustId = getUrl.queryExtCustId(orderParam.getUserId());
			orderParam.setExtCustId(extCustId);
		}catch(Exception e){
			logger.info("",e);
			recordFailMsgImpl.recordRegularFail("000003",subExceptionString(e.toString()),"orgId",orderParam);
			return "000003";
		}
		for(Shopping shopping : orderParam.getShoppingList()){
			try{
				/*
				 * 获取serviceId关联参数，价格查询，匹配计费模式
				 */
				shopping = priceQuery.getPrice(shopping);
			}catch(Exception e){
				logger.error("[pricemaking failed]",e);
				recordFailMsgImpl.recordRegularFail("000004",subExceptionString(e.toString()),"priceFetch",orderParam);
				return "000004";
			}
			try{
				List<Map<String, String>> ratios = getUrl.queryRatio(shopping.getInstance_id());
				shopping.setRatioList(ratios); 
			}catch(Exception e){
				logger.error("[Ratios query failed],instance_id:"+shopping.getInstance_id(),e);
				recordFailMsgImpl.recordRegularFail("000005",subExceptionString(e.toString()),"ratio",orderParam);
				return "000005";
			}
		}
		
		/*
		 * 订单修改，资费表沉淀，订购服务调用
		 */
		try{
			/*
			 * for循环写资费表and订购, 注意事务回滚
			 */
			for(Shopping shopping : orderParam.getShoppingList()){
				iRecordPriceInfo.recordPriceInfo(orderParam,shopping);
			}
		}catch(BatchException e){
			recordFailMsgImpl.recordRegularFail(e.getCode(),subExceptionString(e.getStrStackTrace()),"priceInfo&orderInfo",orderParam);
			return e.getCode();
		}catch(Exception e){ 
			logger.error("[PriceInfo Recording failed]",e);
			recordFailMsgImpl.recordRegularFail("000006",subExceptionString(e.toString()),"priceInfo&orderInfo",orderParam);
			return "000006";
		}
		return "000000";
	}
	
	private String subExceptionString(String exception){
		if(exception.length()<1000){
      	  return exception;
        }else{
          return exception.substring(0, 1000);
        }
	}
}
