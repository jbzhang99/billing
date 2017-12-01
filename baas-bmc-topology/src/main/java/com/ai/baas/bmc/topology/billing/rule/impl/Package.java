package com.ai.baas.bmc.topology.billing.rule.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.bmc.topology.billing.rule.IBilling;
import com.ai.baas.bmc.topology.entity.BillingPriceDetail;
import com.ai.baas.bmc.topology.entity.SubjectAndPrice;
import com.ai.baas.bmc.topology.util.BmcConstants;
import com.ai.baas.storm.exception.BusinessException;
import com.ai.baas.storm.util.BaseConstants;
import com.google.gson.Gson;

public class Package implements IBilling {
	private static Logger logger = LoggerFactory.getLogger(Package.class);
	private Gson jsonConverter = new Gson();
	
	@Override
	public SubjectAndPrice calculate(BillingPriceDetail priceDetail)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	public String getReduceSendData(Map<String, String> fields) {
		Map<String, String> map = new HashMap<>();
		map.put(BmcConstants.EVENT_ID, fields.get(BaseConstants.SERIAL_NUMBER));
		map.put("business_id", fields.get(BmcConstants.SYSTEM_ID));
		map.put(BaseConstants.TENANT_ID, fields.get(BaseConstants.TENANT_ID));
		map.put(BaseConstants.ACCT_ID, fields.get(BaseConstants.ACCT_ID));
		map.put(BaseConstants.SUBS_ID, fields.get(BaseConstants.SUBS_ID));
		//BigDecimal upStream = new BigDecimal(Double.parseDouble(fields.get("up_stream")));
		BigDecimal upStream = new BigDecimal(fields.get("gprs_up"));
		logger.info("get down_stream................" + fields.get("gprs_down"));
		//BigDecimal downStream = new BigDecimal(Double.parseDouble(fields.get("down_stream")));
		BigDecimal downStream = new BigDecimal(fields.get("gprs_down"));
		map.put(BmcConstants.AMOUNT, upStream.add(downStream).setScale(0, BigDecimal.ROUND_UP).toPlainString());
		map.put(BmcConstants.AMOUNT_TYPE, BmcConstants.AMOUNT_TYPE_DATA);
		logger.debug("send ResourceBook data="+jsonConverter.toJson(map));
		System.out.println("send ResourceBook data="+jsonConverter.toJson(map));
		return jsonConverter.toJson(map);
	}

}
