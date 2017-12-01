package com.ai.runner.center.ctp.rtm.core.db.dao;

import com.ai.runner.center.ctp.rtm.core.db.entity.RtmLog;
import com.ai.runner.center.ctp.rtm.core.util.RtmConstants;

public interface RtmLogDao {

	String name = "rtmLogDao";
	String dsName = RtmConstants.MYSQL_DATASOURCE_NAME;
	
	int insertRtmLog(RtmLog rtmLog);
	
}
