package com.ai.baas.batch.client.mainflow.url;

import java.util.List;
import java.util.Map;

import com.ai.baas.batch.client.prepareflow.params.Shopping;
import com.ai.baas.bmc.api.orderinfo.params.OrderInfoParams;
import com.ai.baas.bmc.api.pricemaking.params.PricemakingResponseZX;

public interface IGetUrl {

	public String queryExtCustId(String userId);
	
	public PricemakingResponseZX queryPricemaking(Shopping shopping);
	
	public List<Map<String, String>> queryRatio(String instance_id)throws Exception;
	
	public String dubboOrderinfo(OrderInfoParams orderinfo);
}
