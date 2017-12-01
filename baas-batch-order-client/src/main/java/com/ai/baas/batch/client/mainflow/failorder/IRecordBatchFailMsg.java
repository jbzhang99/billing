package com.ai.baas.batch.client.mainflow.failorder;

import com.ai.baas.batch.client.prepareflow.params.OrderParam;

public interface IRecordBatchFailMsg {
	//原始订单类错误
	public void recordOriginJson(String failCode,String failReason, String source,String json);
	//常规错误
	public void recordRegularFail(String failCode,Exception failReason,String source,OrderParam orderParam);
	
	public void recordRegularFail(String failCode,String failReason,String source,OrderParam orderParam);
}
