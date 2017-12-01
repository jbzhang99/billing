package com.ai.baas.batch.client.prepareflow.convert;

import com.ai.baas.batch.client.prepareflow.params.OrderParam;

public interface IConvert {

	public OrderParam transtoOrder(String json);
}
