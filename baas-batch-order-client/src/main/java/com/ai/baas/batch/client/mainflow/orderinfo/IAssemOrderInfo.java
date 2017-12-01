package com.ai.baas.batch.client.mainflow.orderinfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ai.baas.bmc.api.orderinfo.params.OrderInfoParams;

public interface IAssemOrderInfo {

	public OrderInfoParams assembleOutput(String extCustId, List<Map<String, String>> ratioList, List<String> priceCodeList, 
			String userId, String cronTab, boolean newOrder, Date currentDatePlus1);
}
