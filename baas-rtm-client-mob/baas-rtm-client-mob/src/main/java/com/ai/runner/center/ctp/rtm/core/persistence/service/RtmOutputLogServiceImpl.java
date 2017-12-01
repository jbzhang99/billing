package com.ai.runner.center.ctp.rtm.core.persistence.service;

import org.springframework.transaction.annotation.Transactional;

import com.ai.runner.center.ctp.rtm.core.persistence.dao.RtmOutputLogDao;
import com.ai.runner.center.ctp.rtm.core.persistence.entity.RtmOutputLog;

public class RtmOutputLogServiceImpl implements RtmOutputLogService {

	private RtmOutputLogDao rtmOutputLogDao;

	public void setRtmOutputLogDao(RtmOutputLogDao rtmOutputLogDao) {
		this.rtmOutputLogDao = rtmOutputLogDao;
	}

	@Override
	@Transactional
	public int updateAccumulatedPsn(RtmOutputLog rtmOutputLog) {
		String psn = rtmOutputLog.getPsn();
		int cnt = rtmOutputLogDao.queryPsnCnt(psn);
		int rtnValue;
		if(cnt==0){
			rtnValue = rtmOutputLogDao.insertPsnCnt(rtmOutputLog);
		}else{
			rtnValue = rtmOutputLogDao.updatePsnCnt(psn, rtmOutputLog.getTotalSend());
		}
		return rtnValue;
	}
	
}
