package com.ai.baas.bmc.srv.persistence.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.baas.bmc.srv.persistence.dao.DetailRecordOutputDao;

@Service("detailRecordOutput")
public class DetailRecordOutputServiceImpl implements DetailRecordOutputService {

	@Autowired
	private DetailRecordOutputDao detailRecordOutputDao;
	
	@Override
	public int createTable(String tableName, List<String> columnNames) {
		return detailRecordOutputDao.createTable(tableName, columnNames);
	}

	@Override
	public int insertDetailRecord(String table, String key,
			List<String> columnNames, Map<String, String> data) {
		return detailRecordOutputDao.insertDetailRecord(table, key, columnNames, data);
	}

	
	
}
