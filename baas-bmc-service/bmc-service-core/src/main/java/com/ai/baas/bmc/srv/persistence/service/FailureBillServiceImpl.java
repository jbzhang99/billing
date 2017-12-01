package com.ai.baas.bmc.srv.persistence.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ai.baas.bmc.srv.entity.FailureBill;
import com.ai.baas.bmc.srv.persistence.dao.FailureBillDao;

@Service("failureBillService")
public class FailureBillServiceImpl implements FailureBillService {

	@Resource(name="failureBillFromMysql")
	private FailureBillDao failureBillDao;
	
	@Override
	public void insertFailBillData(FailureBill failureBill) {
		failureBillDao.insertFailBillData(failureBill);
	}
	

}
