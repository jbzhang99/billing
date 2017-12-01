package com.ai.runner.center.ctp.rtm.core.persistence.dao;

import com.ai.runner.center.ctp.rtm.core.persistence.entity.RtmOutputLog;

public interface RtmOutputLogDao {

	//int[] batchUpdatePsn(String[] sqls);
	
	int queryPsnCnt(String psn);
	
	int updatePsnCnt(String psn,int cnt);
	
	int insertPsnCnt(RtmOutputLog rtmOutputLog);
	
	
}
