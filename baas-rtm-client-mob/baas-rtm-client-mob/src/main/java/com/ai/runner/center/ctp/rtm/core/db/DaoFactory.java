package com.ai.runner.center.ctp.rtm.core.db;

import java.util.HashMap;
import java.util.Map;

import com.ai.runner.center.ctp.rtm.core.db.dao.RtmFailureBillDao;
import com.ai.runner.center.ctp.rtm.core.db.dao.RtmFailureBillDaoImpl;
import com.ai.runner.center.ctp.rtm.core.db.dao.RtmLogDao;
import com.ai.runner.center.ctp.rtm.core.db.dao.RtmLogDaoImpl;
import com.ai.runner.center.ctp.rtm.core.db.dao.RtmOutputDetailDao;
import com.ai.runner.center.ctp.rtm.core.db.dao.RtmOutputDetailDaoImpl;
import com.ai.runner.center.ctp.rtm.core.db.dao.RtmOutputLogDao;
import com.ai.runner.center.ctp.rtm.core.db.dao.RtmOutputLogDaoImpl;
import com.ai.runner.center.ctp.rtm.core.db.dao.SingleFileInfoDao;
import com.ai.runner.center.ctp.rtm.core.db.dao.SingleFileInfoDaoImpl;

public class DaoFactory {

	private static Map<String, Object> daoMap = new HashMap<String, Object>();
	
	static{
		daoMap.put(RtmLogDao.name, new RtmLogDaoImpl());
		daoMap.put(RtmFailureBillDao.name, new RtmFailureBillDaoImpl());
		daoMap.put(RtmOutputLogDao.name, new RtmOutputLogDaoImpl());
		daoMap.put(RtmOutputDetailDao.name, new RtmOutputDetailDaoImpl());
		daoMap.put(SingleFileInfoDao.name, new SingleFileInfoDaoImpl());
	}
	
	public static Object getInstance(String daoName){
		return daoMap.get(daoName);
	}
	
	
}
