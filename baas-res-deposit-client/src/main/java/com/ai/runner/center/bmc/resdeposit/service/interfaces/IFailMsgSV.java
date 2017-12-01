package com.ai.runner.center.bmc.resdeposit.service.interfaces;

import com.ai.runner.center.bmc.resdeposit.dao.mapper.bo.FailMsgLog;
import com.ai.runner.center.bmc.resdeposit.vo.PageInfo;

/**
 * Date: 2016年5月4日 <br>
 * @author zhoushanbin
 * Copyright (c) 2016 asiainfo.com <br>
 */
public interface IFailMsgSV {
	
	
	PageInfo<FailMsgLog> query(String systemId,String tenantId,String monthDate,String status,PageInfo<FailMsgLog> page);
	
	int countByFilter(String systemId,String tenantId,String monthDate,String status);
	
	void insert(FailMsgLog log);
	
	void updateByPrimaryKey(FailMsgLog log);
	
	
}
