package com.ai.runner.center.bmc.resdeposit.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.runner.center.bmc.resdeposit.dao.mapper.bo.FailMsgLog;
import com.ai.runner.center.bmc.resdeposit.service.interfaces.IFailMsgSV;
import com.ai.runner.center.bmc.resdeposit.util.KafkaUtil;
import com.ai.runner.center.bmc.resdeposit.util.LoggerUtil;
import com.ai.runner.center.bmc.resdeposit.vo.PageInfo;
import com.google.gson.Gson;

/**
 * Date: 2016年5月18日 <br>
 * 
 * @author zhoushanbin 
 * 
 * Copyright (c) 2016 asiainfo.com <br>
 */
@Component
public class FailMsgLogHandle {

	private static final String DEAL = "deal";
	private static final String UNDEAL = "undeal";
	//private static final String SYSTEM_ID = "RESDEPOSIT";
	//private static final String TENANT_ID = "VIV-BYD";
	private static final Logger LOG = LoggerFactory.getLogger(FailMsgLogHandle.class);
	
	
	private KafkaUtil kafka;
	
	@Autowired
	private IFailMsgSV failMsgSv;

	public FailMsgLogHandle() {
		kafka = new KafkaUtil();
	}

	private void updateStaticState(FailMsgLog failMsgLog) {

		try {
			failMsgSv.updateByPrimaryKey(failMsgLog);
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtil.log.error("", e);
		}

	}

	public void doFailMsgLog(String systemId,String tenantId,String date) {
		
		try {
			LOG.info("开始进行错误消息处理！！！");
			LOG.info("入口参数为：systemId="+systemId +";tenantId=" +tenantId +";date="+ date);
			// 由于错误的消息正式环境数据量不会太多，故一次捞取数据即可。
			PageInfo<FailMsgLog> page = new PageInfo<FailMsgLog>();
			page.setCurrPosInDb(0);
			page.setSize(0);
			page = failMsgSv.query(systemId, tenantId,date, UNDEAL, page);
			Gson gson2 = new Gson();
			LOG.info("查询结果为："+gson2.toJson(page));
			for (FailMsgLog msg : page.getList()) {
				Gson gson = new Gson();
				LoggerUtil.log.debug("将消息投递至消息源；" + gson.toJson(msg));
				try {
					kafka.addQueue(msg.getMsg());
				} catch (Exception e) {
					ReEnterHandle.pushToStatic(msg.getMsg(), "错发发生发送消息上，错误原因："
							+ e.getMessage(), msg.getType());
				}
				try {
					msg.setStatus(DEAL);
					updateStaticState(msg);
				} catch (Exception e) {
					ReEnterHandle.pushToStatic(msg.getMsg(), "错发发生发送消息上，错误原因："
							+ e.getMessage(), msg.getType());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtil.log.error("", e);
		} finally {
			
		}
	}

}
