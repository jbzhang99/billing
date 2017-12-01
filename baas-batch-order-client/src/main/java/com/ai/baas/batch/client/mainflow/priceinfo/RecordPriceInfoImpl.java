package com.ai.baas.batch.client.mainflow.priceinfo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.batch.client.mainflow.ProcessOrder;
import com.ai.baas.batch.client.mainflow.changeorder.IOrderModify;
import com.ai.baas.batch.client.mainflow.changeorder.OrderModify;
import com.ai.baas.batch.client.mainflow.failorder.BatchException;
import com.ai.baas.batch.client.mainflow.orderinfo.AssemOrderInfoImpl;
import com.ai.baas.batch.client.mainflow.orderinfo.IAssemOrderInfo;
import com.ai.baas.batch.client.prepareflow.params.OrderParam;
import com.ai.baas.batch.client.prepareflow.params.Shopping;
import com.ai.baas.batch.client.service.business.interfaces.ICheckInstanceIdSV;
import com.ai.baas.batch.client.service.business.interfaces.IOriginPriceSV;
import com.ai.baas.bmc.api.orderinfo.interfaces.IOrderInfoSV;
import com.ai.baas.bmc.api.orderinfo.params.OrderInfoParams;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.alibaba.fastjson.JSON;
@Transactional
@Service("recordPriceInfo")
public class RecordPriceInfoImpl implements IRecordPriceInfo{
	private static Logger logger = LoggerFactory.getLogger(ProcessOrder.class);
	@Autowired
	ICheckInstanceIdSV checkInstanceIdSVImpl;
	@Autowired
	IOriginPriceSV originPriceSVImpl;

	@Override
	public String recordPriceInfo(OrderParam orderParam,Shopping shopping) throws Exception {

		Calendar calendar = orderParam.getCalendar();
        Date orderDate1 = calendar.getTime();
        calendar.add(Calendar.SECOND, 1);
        Date orderDate2 = calendar.getTime();
        boolean newOrder = true;
        IOrderModify orderModify = new OrderModify(orderDate1);
        IAssemOrderInfo assemOrderInfo = new AssemOrderInfoImpl();
        IOrderInfoSV orderSv = DubboConsumerFactory.getService(IOrderInfoSV.class);

		if(!StringUtils.isEmpty(orderParam.getOldOrderId())){
			//调用订单修改
			orderModify.modify(shopping);
			newOrder = false;
		}else{
			System.out.println("不是修改 ");
		}
		//实例ID判重
		if(checkInstanceIdSVImpl.checkDuplicate(shopping.getInstance_id())==false){
			System.out.println("重复订购~~~~~~~~~~~~~~");
			throw new BatchException("000007", "duplicate instanceId");
		}
		List<String> priceCodeList = new ArrayList<>();
		
		//insert资费表
		priceCodeList = originPriceSVImpl.insertMsg(shopping,orderParam.getUserId()); 
		
		//调用orderinfo
		OrderInfoParams orderinfo = new OrderInfoParams();
		BaseResponse baseResponse = new BaseResponse();
		orderinfo = assemOrderInfo.assembleOutput(orderParam.getExtCustId(), shopping.getRatioList(), priceCodeList, orderParam.getUserId(), 
				shopping.getCronTab(), newOrder, orderDate2);
		try{
			logger.info(JSON.toJSONString("orderinfo input: "+orderinfo));
			baseResponse = orderSv.orderInfo(orderinfo);
		}catch(Exception e){
			throw new BatchException("000008", "orderinfo dubbo error",e);
		}
		logger.info("orderinfo baseResponse: "+JSON.toJSONString(baseResponse)); 
        if(baseResponse.getResponseHeader().getIsSuccess()){
            
        }else{
           	throw new BatchException("000009", "orderinfo failed");
        }
			
		return null;
	}
	
}
