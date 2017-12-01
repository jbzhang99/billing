package com.ai.baas.bmc.srv.persistence.dao;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DuplicateCheckingDaoImpl implements DuplicateCheckingDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int createTable(String tableName) {
		StringBuilder strSql = new StringBuilder();
		strSql.append("CREATE TABLE `").append(tableName).append("` (");
		strSql.append(" `dup_key` varchar(100) NOT NULL,");
		strSql.append(" `create_date` datetime NOT NULL,");
		strSql.append(" PRIMARY KEY (`dup_key`)");
		strSql.append(" ) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
		return jdbcTemplate.update(strSql.toString());
	}

	@Override
	public int queryDataExistByKey(String dup_table,String dup_key) {
		StringBuilder strSql = new StringBuilder();
		strSql.append(" select count(1)");
		strSql.append(" from ").append(dup_table);
		strSql.append(" where dup_key = '").append(dup_key).append("'");
		return jdbcTemplate.queryForObject(strSql.toString(), Integer.class);
	}

	@Override
	public int insertDupKey(String dup_table, String dup_key) {
		StringBuilder strSql = new StringBuilder();
		strSql.append("insert into `").append(dup_table).append("`(`dup_key`, `create_date`) ");
		strSql.append("values (?,?)");
		Object[] params = new Object[2];
		params[0] = dup_key;
		params[1] = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
		return jdbcTemplate.update(strSql.toString(), params);
	}
	
	
	

}
