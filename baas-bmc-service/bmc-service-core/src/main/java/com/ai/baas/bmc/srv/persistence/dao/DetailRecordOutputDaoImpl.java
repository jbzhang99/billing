package com.ai.baas.bmc.srv.persistence.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ai.baas.bmc.srv.util.BaasConstants;

@Repository
public class DetailRecordOutputDaoImpl implements DetailRecordOutputDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int createTable(String tableName, List<String> columnNames) {
		StringBuilder strSql = new StringBuilder();
		strSql.append("CREATE TABLE `").append(tableName).append("` (");
		strSql.append(" `row_key` varchar(200) NOT NULL,");
		strSql.append(" `create_date` datetime NULL,");
		for(String columnName:columnNames){
			strSql.append(" `").append(columnName);
			if(columnName.equalsIgnoreCase(BaasConstants.STEP_HASH_KEY)){
				strSql.append("` varchar(200) NULL,");
			}else{
				strSql.append("` varchar(100) NULL,");
			}
		}
		strSql.append(" PRIMARY KEY (`row_key`)");
		strSql.append(" ) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
		return jdbcTemplate.update(strSql.toString());
	}

	@Override
	public int insertDetailRecord(String table, String key, List<String> columnNames, Map<String, String> data) {
		StringBuilder columnName = new StringBuilder();
		StringBuilder placeholder = new StringBuilder();
		Object[] params = new Object[columnNames.size()+2];
		int number = 0;
		for (String col : columnNames) {
			columnName.append("`").append(col).append("`").append(",");
			placeholder.append("?").append(",");
			params[number] = data.get(col);
			number++;
		}
		columnName.append("`row_key`,`create_date`");
		placeholder.append("?,?");
		params[number] = key;
		params[number+1] = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
		
		StringBuilder strSql = new StringBuilder();
		strSql.append("insert into `").append(table);
		strSql.append("`(").append(columnName).append(") ");
		strSql.append("values (").append(placeholder).append(")");
		
		return jdbcTemplate.update(strSql.toString(), params);
	}

	
//	private String assembleColunmName(List<String> columnNames){
//		
//	}
	

}
