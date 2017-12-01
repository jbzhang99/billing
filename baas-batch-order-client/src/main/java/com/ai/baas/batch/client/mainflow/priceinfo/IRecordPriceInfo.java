package com.ai.baas.batch.client.mainflow.priceinfo;

import com.ai.baas.batch.client.prepareflow.params.OrderParam;
import com.ai.baas.batch.client.prepareflow.params.Shopping;

public interface IRecordPriceInfo {

	public String recordPriceInfo(OrderParam orderParam,Shopping shopping) throws Exception; 
}
