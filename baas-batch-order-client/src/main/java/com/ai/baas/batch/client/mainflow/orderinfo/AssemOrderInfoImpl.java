package com.ai.baas.batch.client.mainflow.orderinfo;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.ai.baas.batch.client.util.PropertiesUtil;
import com.ai.baas.bmc.api.orderinfo.params.ExtInfo;
import com.ai.baas.bmc.api.orderinfo.params.OrderInfoParams;
import com.ai.baas.bmc.api.orderinfo.params.Product;
import com.ai.opt.sdk.components.sequence.util.SeqUtil;
import com.alibaba.fastjson.JSON;

public class AssemOrderInfoImpl implements IAssemOrderInfo{
	
	@Autowired
	public OrderInfoParams assembleOutput(String extCustId, List<Map<String, String>> ratioList, List<String> priceCodeList, 
			String userId, String cronTab, boolean newOrder, Date currentDatePlus1) {
        String tenantId = PropertiesUtil.getValue("batch.order.tenant.id");
        String tradeSeq = createTradeSeq(tenantId);
        OrderInfoParams orderInfoParams = new OrderInfoParams();
        orderInfoParams.setTenantId("ECITIC");//tenantId通过配置文件传入
        orderInfoParams.setTradeSeq(tradeSeq);
        orderInfoParams.setActiveTime("20150101120000");
        orderInfoParams.setInactiveTime("20990101120000");
        orderInfoParams.setExtCustId(extCustId); 
        orderInfoParams.setServiceId(userId);
        orderInfoParams.setState("Normal");
        orderInfoParams.setUsetype("Test");
         
        List<ExtInfo>extInfoList = new ArrayList<>();
        ExtInfo extInfo = new ExtInfo();
        extInfo.setExtType("apportion_list");//按金额分摊
        extInfo.setExtValue(JSON.toJSONString(ratioList));
        extInfoList.add(extInfo);
        
        if(cronTab!=null){
            ExtInfo extInfo2 = new ExtInfo();
            extInfo2.setExtType("crontab");//套餐包触发时间
            extInfo2.setExtValue(cronTab);
            extInfoList.add(extInfo2);
        }
        
        orderInfoParams.setSublist(extInfoList);

        List<Product> productList = new ArrayList<>();
        for(String p : priceCodeList){
            Product product = new Product();
            product.setActiveTime(createActiveTime(newOrder,currentDatePlus1));
            product.setInactiveTime("20990101120000");
            product.setProductId(p);
            product.setProductNumber(1);
            product.setProductType("dr");// dr bill
            productList.add(product);
        }
        orderInfoParams.setProductList(productList);
        
        return orderInfoParams;   
    }
	
	private static String createActiveTime(boolean newOrder,Date currentDatePlus1) {
        if(newOrder){
            Format format = new SimpleDateFormat("yyyyMMddHH0000");
            return format.format(currentDatePlus1);
        }else{
            Format format = new SimpleDateFormat("yyyyMMddHHmmss");
            return format.format(currentDatePlus1);
        }
    }
	
	private static String createTradeSeq(String tenantId) {
	        
	        String time = new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date() );
	        String sequence = ""+SeqUtil.getNewId("ORDERINFOSEQ");
	        String tradeSeq = tenantId + time + sequence;
	        return tradeSeq;
    }
	


}
