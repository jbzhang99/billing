package com.ai.runner.center.ctp.rtm.core.db.dao;

import com.ai.runner.center.ctp.rtm.core.db.entity.RtmOutputLog;
import com.ai.runner.center.ctp.rtm.core.util.RtmConstants;

public interface RtmOutputLogDao {

	String name = "rtmOutputLogDao";
	String dsName = RtmConstants.MYSQL_DATASOURCE_NAME;
	
	int insertOutputLog(RtmOutputLog rtmOutputLog);
	
	//int updateTotalSend(String psn,int record_number);
	
	int updateFinished(String psn, int totalSend, String path);
}
