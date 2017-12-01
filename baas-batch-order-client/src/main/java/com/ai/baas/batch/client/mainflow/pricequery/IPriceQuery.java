package com.ai.baas.batch.client.mainflow.pricequery;

import com.ai.baas.batch.client.mainflow.failorder.BatchException;
import com.ai.baas.batch.client.prepareflow.params.Shopping;

/*
 * 计费模式转换、价格查询接口
 */
public interface IPriceQuery {

	public Shopping getPrice(Shopping shopping) throws BatchException;

}
