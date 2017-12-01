package com.ai.runner.center.bmc.resdeposit.service.interfaces;

import java.util.List;

import com.ai.runner.center.bmc.resdeposit.dao.mapper.bo.MsgScheduleHandle;
import com.ai.runner.center.bmc.resdeposit.vo.PageInfo;

/**
 * Date: 2016年5月12日 <br>
 * @author zhoushanbin
 * Copyright (c) 2016 asiainfo.com <br>
 */
public interface IMsgScheduleHandle {
	
	int countByFilter(String systemId,String tenantId,String source,String dealTime,String status);
	
	PageInfo<MsgScheduleHandle> queryByFilter(String systemId,String tenantId,String source,String dealTime,String status,PageInfo<MsgScheduleHandle> page);
	
	void insert(MsgScheduleHandle msg);
	
	void updateMsg(MsgScheduleHandle msg);
	
	void batchInsert(List<MsgScheduleHandle> list);
	
	void batchUpdateByPrimaryKey(List<MsgScheduleHandle> list);
	
	
}
