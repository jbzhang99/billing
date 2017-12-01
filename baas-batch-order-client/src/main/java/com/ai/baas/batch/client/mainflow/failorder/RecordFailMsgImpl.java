package com.ai.baas.batch.client.mainflow.failorder;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ai.baas.batch.client.dao.mapper.bo.BatchFailureBill;
import com.ai.baas.batch.client.prepareflow.params.OrderParam;
import com.ai.baas.batch.client.service.atom.interfaces.IBatchFailureBillAtom;
import com.ai.opt.sdk.util.DateUtil;
import com.alibaba.fastjson.JSONObject;
@Component("recordFailMsg")
public class RecordFailMsgImpl implements IRecordBatchFailMsg{

	@Autowired
	@Qualifier("batchFailureBill")
	IBatchFailureBillAtom iBatchFailureBillAtom;
	
	@Override
	public void recordOriginJson(String failCode, String failReason, String source, String json) {
		Timestamp creatTime=DateUtil.getSysDate();
		BatchFailureBill batchFailureBill = new BatchFailureBill();
		batchFailureBill.setFailCode(failCode);
		batchFailureBill.setFailTime(creatTime);
		batchFailureBill.setOrderJson(json);
		batchFailureBill.setSource(source);
		batchFailureBill.setTenantId("ECITIC"); 
		batchFailureBill.setFailReason(failReason);
		
		JSONObject rootObject = (JSONObject)JSONObject.parse(json);
        JSONObject orderObject = rootObject.getJSONObject("order");
		batchFailureBill.setOrderId(orderObject.getString("id"));
		
//		batchFailureBill.setInstanceId(instanceId);
		iBatchFailureBillAtom.insert(batchFailureBill);

	}
	@Override
	public void recordRegularFail(String failCode, Exception failReason, String source, OrderParam orderParam) {
		Timestamp creatTime=DateUtil.getSysDate();
		BatchFailureBill batchFailureBill = new BatchFailureBill();
		batchFailureBill.setFailCode(failCode);
		batchFailureBill.setFailTime(creatTime);
		batchFailureBill.setOrderJson(orderParam.getOrginJson());
		batchFailureBill.setSource(source);
		batchFailureBill.setTenantId("ECITIC"); 
		batchFailureBill.setFailReason(failReason.toString());

		batchFailureBill.setOrderId(orderParam.getOrderId());
		/*
		 * 实例ID是否需要保存？多个实例ID如何展现？？
		 */
		
//		batchFailureBill.setInstanceId(instanceId);
		
		iBatchFailureBillAtom.insert(batchFailureBill);
		
	}
	@Override
	public void recordRegularFail(String failCode, String failReason, String source, OrderParam orderParam) {
		Timestamp creatTime=DateUtil.getSysDate();
		BatchFailureBill batchFailureBill = new BatchFailureBill();
		batchFailureBill.setFailCode(failCode);
		batchFailureBill.setFailTime(creatTime);
		batchFailureBill.setOrderJson(orderParam.getOrginJson());
		batchFailureBill.setSource(source);
		batchFailureBill.setTenantId("ECITIC"); 
		batchFailureBill.setFailReason(failReason);;
		
		batchFailureBill.setOrderId(orderParam.getOrderId());
		/*
		 * 实例ID是否需要保存？多个实例ID如何展现？？
		 */
//		batchFailureBill.setInstanceId(instanceId);
		iBatchFailureBillAtom.insert(batchFailureBill);
		
	}





}
