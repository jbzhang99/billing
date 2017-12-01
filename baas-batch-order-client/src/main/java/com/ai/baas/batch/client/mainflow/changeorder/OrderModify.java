package com.ai.baas.batch.client.mainflow.changeorder;

import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ai.baas.batch.client.mainflow.failorder.BatchException;
import com.ai.baas.batch.client.prepareflow.params.Shopping;
import com.ai.baas.bmc.api.citic.interfaces.IOrderChangeSV;
import com.ai.baas.bmc.api.citic.params.InstanceInfo;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.alibaba.fastjson.JSON;

public class OrderModify implements IOrderModify{
	private Date orderDate;
	public OrderModify(Date currentDate) {
		this.orderDate = currentDate;
	}
	@Override
	public String modify(Shopping shopping)throws Exception{
		IOrderChangeSV iorderChange=DubboConsumerFactory.getService(IOrderChangeSV.class);
    	InstanceInfo info=new InstanceInfo();
    	info.setInstanceId(shopping.getInstance_id());
    	info.setTenantId("ECITIC");
    	info.setInactiveTime(getInActiveTime(orderDate));

       try{
    	   //调用修改服务
    	   BaseResponse res=iorderChange.updateInstance(info);
    		if(!res.getResponseHeader().getResultCode().equals("000000")){
    			String errorMsg = "orderChange failed! instance_id:"+shopping.getInstance_id();
    			throw new BatchException("000001",errorMsg);
        	}
    	}catch(Exception e){
    		throw new BatchException("000001","orderChange dubbo error",e);
    	}	
		return "000001";
	}
	
    private Timestamp getInActiveTime(Date currentDate) {
        Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(currentDate);//时间存储为字符串
        System.out.println(JSON.toJSONString(Timestamp.valueOf(str)));
        return Timestamp.valueOf(str);
    }

}
