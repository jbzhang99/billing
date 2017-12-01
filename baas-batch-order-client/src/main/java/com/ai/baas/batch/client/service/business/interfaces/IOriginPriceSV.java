package com.ai.baas.batch.client.service.business.interfaces;

import java.util.List;

import com.ai.baas.batch.client.prepareflow.params.Shopping;

public interface IOriginPriceSV {
	public List<String> insertMsg(Shopping shopping,String userId)throws Exception; 
}
