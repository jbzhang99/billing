package com.ai.baas.bmc.srv.persistence.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BlUserInfoDaoImpl implements BlUserInfoDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public int queryUserCnt(String service_id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from bl_userinfo ");
		sql.append("where SERVICE_ID = '").append(service_id).append("'");
		Integer cnt = jdbcTemplate.queryForObject(sql.toString(), Integer.class);
		return cnt.intValue();
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
}
