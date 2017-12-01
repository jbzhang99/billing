package com.ai.baas.bmc.srv.persistence.service;

public interface DuplicateCheckingService {

	int createDupTable(String tableName);
	
	boolean isExist(String dup_table,String dup_key);
	
	int insertDupKey(String dup_table,String dup_key);
}
