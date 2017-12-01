package com.ai.runner.center.ctp.rtm.core.db;

import java.util.HashMap;
import java.util.Map;
import com.ai.runner.center.ctp.rtm.core.db.dao.BlUserinfoDao;
import com.ai.runner.center.ctp.rtm.core.db.dao.BlUserinfoDaoImpl;

public class DaoFactory {

	private static Map<String, Object> daoMap = new HashMap<String, Object>();
	
	static{
		daoMap.put(BlUserinfoDao.name, new BlUserinfoDaoImpl());
	}
	
	public static Object getInstance(String daoName){
		return daoMap.get(daoName);
	}
	
	
}
