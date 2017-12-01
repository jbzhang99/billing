package com.ai.baas.bmc.srv.persistence.dao;

public interface DuplicateCheckingDao {

	int createTable(String tableName);
	
	int queryDataExistByKey(String dup_table,String dup_key);
	
	int insertDupKey(String dup_table,String dup_key);
}
