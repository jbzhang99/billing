package com.ai.baas.batch.client.prepareflow.checkJson;

public interface ICheckJson {

	/**
	 * 校验JSON格式
	 * @param json
	 * @return
	 */
	public String checkJson(String json) throws Exception;
	
}
