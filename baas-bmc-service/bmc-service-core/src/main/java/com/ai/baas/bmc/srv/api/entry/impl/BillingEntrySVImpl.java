package com.ai.baas.bmc.srv.api.entry.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.bmc.srv.api.entry.interfaces.IBillingEntrySV;
import com.ai.baas.bmc.srv.executor.MessageHandler;
import com.ai.baas.bmc.srv.util.BaasConstants;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.alibaba.dubbo.config.annotation.Service;

@Service
public class BillingEntrySVImpl implements IBillingEntrySV {
	private static Logger logger = LoggerFactory.getLogger(BillingEntrySVImpl.class);
	
	@Override
	public BaseResponse doBilling(String billingMessage) throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		//System.out.println("billingMessage=="+billingMessage);
		//System.out.println("doBilling..................");
		//test.exectue();
		//MessageHandler.addMsgToQueue(billingMsg);
		//Map<String, Integer> mappingRule = MappingRule.getIndexes(new RecordFmtKey("TR","VOICE","TEST"));
//		Map<String, String> data = null;
//		try {
//			data = MessageParser.parseObject(testData());
//		} catch (com.ai.baas.bmc.srv.failbill.BusinessException e) {
//			e.printStackTrace();
//		}
//		System.out.println("data="+data.toString());
		
		String[] inputDatas = StringUtils.splitPreserveAllTokens(testData(),BaasConstants.RECORD_SPLIT);
		for(String inputData : inputDatas){
			//System.out.println("inputData="+inputData);
			MessageHandler.addMsgToQueue(inputData);
		}
//		System.out.println("-------调用成功");
//		FailureBill failureBill = new FailureBill();
//		failureBill.setTenantId("ecitic");
//		failureBill.setServiceType("ecs");
//		failureBill.setSource("ali");
//		
//		FailureBillService failureBillService = ApplicationContextUtil.getService(com.ai.baas.bmc.srv.persistence.service.FailureBillService.class);
//		failureBillService.insertFailBillData(failureBill);
		
		BaseResponse response = new BaseResponse();
		ResponseHeader responseHeader = new ResponseHeader();
		responseHeader.setSuccess(true);
		responseHeader.setResultCode("00");
		responseHeader.setResultMessage("成功");
		response.setResponseHeader(responseHeader);
		return response;
	}
	
	
	public String testData(){
		StringBuilder str = new StringBuilder();
//		str.append("TR").append(BaasConstants.FIELD_SPLIT);
//		str.append("VOICE").append(BaasConstants.FIELD_SPLIT);
//		str.append("TEST").append(BaasConstants.FIELD_SPLIT);
//		str.append("1456281622845").append(BaasConstants.FIELD_SPLIT);
//		str.append("1064802120010120060709230900141").append(BaasConstants.FIELD_SPLIT);
//		str.append("20160410").append(BaasConstants.FIELD_SPLIT);
//		str.append("20160411").append(BaasConstants.FIELD_SPLIT);
//		str.append("17012345678").append(BaasConstants.FIELD_SPLIT);
//		str.append("10").append(BaasConstants.FIELD_SPLIT);
//		str.append("20160413").append(BaasConstants.RECORD_SPLIT);
		
		str.append("TR").append(BaasConstants.FIELD_SPLIT);
		str.append("VOICE").append(BaasConstants.FIELD_SPLIT);
		str.append("TEST").append(BaasConstants.FIELD_SPLIT);
		str.append("1456281622845").append(BaasConstants.FIELD_SPLIT);
		str.append("1064802120010120060709230900142").append(BaasConstants.FIELD_SPLIT);
		str.append("20160410").append(BaasConstants.FIELD_SPLIT);
		str.append("20160411").append(BaasConstants.FIELD_SPLIT);
		str.append("17012345678").append(BaasConstants.FIELD_SPLIT);
		str.append("20").append(BaasConstants.FIELD_SPLIT);
		str.append("20160413");
		
		return str.toString();
	}
	
	
}
