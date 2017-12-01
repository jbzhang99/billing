package com.ai.baas.batch.client.prepareflow.checkJson;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.ai.baas.batch.client.mainflow.failorder.BatchException;
import com.ai.baas.batch.client.util.CheckUtil;
import com.ai.baas.batch.client.util.LoggerUtil;
import com.ai.opt.base.exception.BusinessException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
@Component
public class CheckJson implements ICheckJson{

	public String checkJson(String json) throws Exception {  
	      
		try{
	    	LoggerUtil.log.info("[original json]:"+json);     
	        JSONObject rootObject = (JSONObject)JSONObject.parse(json);
	        JSONObject orderObject = rootObject.getJSONObject("order");
	        
	        if(orderObject == null||orderObject.toJSONString().isEmpty()||orderObject.size()==0){
	        	throw new BusinessException("000001", "orderJSON不能为空");
	        }
	        orderObject.getString("old_order_id");
	        orderObject.getString("state_id");
	        
	        CheckUtil.checkNull(orderObject.getString("id"), "order_id", "000001");
	        CheckUtil.checkNull(orderObject.getString("user_id"), "user_id", "000001");
	         
	        JSONArray shoppingArray = orderObject.getJSONArray("shopping_lists");      
	        for(int j=0;j<shoppingArray.size();j++){
	          	JSONObject shoppingObject = shoppingArray.getJSONObject(j);
	          	
	          	shoppingObject.getString("parameters");
	          	CheckUtil.checkNull(shoppingObject.getString("parameters"), "parameters", "000001");
	          	CheckUtil.checkNull(shoppingObject.getString("service_id"), "service_id", "000001");	          	
	          	
	          	Map<String, String> parameterMap = (Map<String, String>) JSON.parse(shoppingObject.getString("parameters"));
	            	 					
	            JSONArray instanceArray = shoppingObject.getJSONArray("instances");	              
	            for(int k =0 ; k<instanceArray.size();k++){
                    JSONObject instanceObject = instanceArray.getJSONObject(k);
                    CheckUtil.checkNull(instanceObject.getString("instance_id"), "instance_id", "000001");    
		        }	               
	        }                    	        
	        return null;
	      
	      }catch (Exception e){
	    	  LoggerUtil.log.error("[convert failed]",e); 
	          throw new BatchException("000001", "[convert failed]",e);
	      }
	    }
}
