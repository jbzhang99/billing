package com.ai.baas.batch.client.mainflow.changeorder;

import com.ai.baas.batch.client.prepareflow.params.Shopping;

public interface IOrderModify {

	public String modify(Shopping shopping) throws Exception;
}
