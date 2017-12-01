package com.ai.baas.bmc.srv.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.bmc.srv.persistence.dao.DuplicateCheckingDao;

@Service("duplicateChecking")
public class DuplicateCheckingServiceImpl implements DuplicateCheckingService {

	@Autowired
	private DuplicateCheckingDao duplicateCheckingDao;
	
	@Override
	@Transactional("transactionManagerBmc")
	public int createDupTable(String tableName) {
		return duplicateCheckingDao.createTable(tableName);
	}

	@Override
	public boolean isExist(String dup_table, String dup_key) {
		int result = duplicateCheckingDao.queryDataExistByKey(dup_table, dup_key);
		if (result == 0) {
			return false;
		}else{
			return true;
		}
	}

	@Override
	public int insertDupKey(String dup_table, String dup_key) {
		return duplicateCheckingDao.insertDupKey(dup_table, dup_key);
	}
	
	
	
	

}
