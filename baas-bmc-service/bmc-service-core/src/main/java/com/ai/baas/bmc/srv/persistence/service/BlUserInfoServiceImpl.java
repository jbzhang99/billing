package com.ai.baas.bmc.srv.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.ai.baas.bmc.srv.persistence.dao.BlUserInfoDao;

@Service("blUserInfoServiceImpl")
public class BlUserInfoServiceImpl implements BlUserInfoService {

	@Autowired
	private BlUserInfoDao blUserInfoDao;
	@Autowired
	private ApplicationContext context;
	
	@Override
	public int queryUserCnt(String service_id) {
		//System.out.println("1111111111111111111111111");
		return blUserInfoDao.queryUserCnt(service_id);
	}

	public void setBlUserInfoDao(BlUserInfoDao blUserInfoDao) {
		this.blUserInfoDao = blUserInfoDao;
	}
	

}
