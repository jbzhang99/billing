package com.ai.baas.batch.client.prepareflow.bakJson;

import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ai.baas.batch.client.constants.ClientConstants;
import com.ai.baas.batch.client.dao.mapper.bo.BmcOrderLogYyyymm;
import com.ai.baas.batch.client.service.atom.interfaces.IBmcOrderLogAtom;
import com.ai.opt.sdk.util.DateUtil;
import com.alibaba.fastjson.JSONObject;
@Component
public class BakJson implements IBakJson{
	@Autowired
	@Qualifier("batchOrderLog") 
	IBmcOrderLogAtom iBmcOrderLogAtom;
	@Override
	public String saveOrderJson(String json) {
		System.err.println("order length::::::::: "+json.length());
		BmcOrderLogYyyymm bmcOrderLog=new BmcOrderLogYyyymm(); 
		String currentMonth=DateUtil.getDateString("YYYYMM");
        Timestamp creatTime=DateUtil.getSysDate();   //创建时间
        
        String userId = null;
//        String oldOrdId = null;
        String orderId = null;
        if(!StringUtils.isBlank(json)){
             JSONObject rootObject = (JSONObject)JSONObject.parse(json);
             JSONObject orderObject = rootObject.getJSONObject("order");
            
        	 userId = orderObject.getString("user_id");
//             oldOrdId = orderObject.getString("old_order_id");
             orderId = orderObject.getString("id");
        }

        String tagTime=DateUtil.getDateString(creatTime, "YYYYMMddHHmmssSSS");
        bmcOrderLog.setCreateTime(creatTime);
        bmcOrderLog.setUserId(userId);
        bmcOrderLog.setOrderId(orderId);
        bmcOrderLog.setRemark("ok");
        bmcOrderLog.setTag(tagTime);
        
        //需要修改字段长度限制
        if(json.length()<ClientConstants.SQL_LENGTH){
    	  bmcOrderLog.setOrderRecord(json);
        }else{
    	  bmcOrderLog.setOrderRecord(json.substring(0, ClientConstants.SQL_LENGTH-1));
        }
        iBmcOrderLogAtom.insertOrderLog(currentMonth, bmcOrderLog);
        return null;
	}

}
