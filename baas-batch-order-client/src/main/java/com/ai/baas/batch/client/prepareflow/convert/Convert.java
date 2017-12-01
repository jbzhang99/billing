package com.ai.baas.batch.client.prepareflow.convert;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Component;

import com.ai.baas.batch.client.prepareflow.params.OrderParam;
import com.ai.baas.batch.client.prepareflow.params.Shopping;
import com.ai.baas.batch.client.util.LoggerUtil;
import com.ai.baas.bmc.api.pricemaking.params.PricemakingResponseZX;
import com.ai.opt.base.exception.BusinessException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Component
public class Convert implements IConvert{

	public OrderParam transtoOrder(String json) {  
	      try{
	    	OrderParam orderParam = new OrderParam();
	    	
//	    	LoggerUtil.log.info("[original json]:"+json);     
	
	        JSONObject rootObject = (JSONObject)JSONObject.parse(json);
	        JSONObject orderObject = rootObject.getJSONObject("order");
	        
	        Calendar calendar = Calendar.getInstance ();
	        orderParam.setCalendar(calendar);
	        orderParam.setOldOrderId(orderObject.getString("old_order_id"));
	        orderParam.setOrderId(orderObject.getString("id"));
	        orderParam.setStateId(orderObject.getString("state_id"));
	        orderParam.setUserId(orderObject.getString("user_id"));
	        orderParam.setOrginJson(json);
	        List<Shopping> shoppingList = new ArrayList<>();
	        orderParam.setShoppingList(shoppingList);
		        
	        JSONArray shoppingArray = orderObject.getJSONArray("shopping_lists");      
	        
	        for(int j=0;j<shoppingArray.size();j++){
	        	JSONObject shoppingObject = shoppingArray.getJSONObject(j);
	        	Shopping shopping = new Shopping();
	        	
	        	shopping.setListId(shoppingObject.getString("list_id"));
	        	shopping.setParameters(shoppingObject.getString("parameters"));
	        	shopping.setServiceId(shoppingObject.getString("service_id"));
	        	shopping.setServiceName(shoppingObject.getString("service_name"));
	        	
	        	@SuppressWarnings("unchecked")
				Map<String, String> parameterMap = (Map<String, String>) JSON.parse(shoppingObject.getString("parameters"));
	          	shopping.setParameterMap(parameterMap);
	          	
	          	PricemakingResponseZX pricemakingResponseZX = new PricemakingResponseZX();
	          	boolean needPrice = true;
                if(shoppingObject.containsKey("detail_costs")){
                    if(shoppingObject.getJSONArray("detail_costs").getJSONObject(0).size()!=0){      
                        needPrice = false;
                        JSONObject detail_cost =new JSONObject();
                        detail_cost.put("detail_costs", shoppingObject.getJSONArray("detail_costs"));
                        pricemakingResponseZX = JSON.parseObject(StringEscapeUtils.unescapeJava(detail_cost.toJSONString()), PricemakingResponseZX.class);
                        shopping.setPricemaking(pricemakingResponseZX);
                    }else{
                        needPrice = true;
                    }
                }
                shopping.setNeedPrice(needPrice); 
                
//	        	List<Instances> instanceList = new ArrayList<>();
//				shopping.setInstanceList(instanceList);
					
	            JSONArray instanceArray = shoppingObject.getJSONArray("instances");
	            
	            for(int k =0 ; k<instanceArray.size();k++){
	                    JSONObject instanceObject = instanceArray.getJSONObject(k);
	                    shopping.setInstance_id(instanceObject.getString("instance_id"));     
		        }
	            shoppingList.add(shopping);
		    }                    
	        
	        return orderParam;
	  	 }catch (Exception e){
		   LoggerUtil.log.error("[convert failed]",e);
	       throw new BusinessException("convert failed!", e);
	  	 }
    }
}
