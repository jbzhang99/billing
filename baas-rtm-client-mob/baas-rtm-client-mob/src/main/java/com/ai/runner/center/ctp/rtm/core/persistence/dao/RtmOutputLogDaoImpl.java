package com.ai.runner.center.ctp.rtm.core.persistence.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ai.runner.center.ctp.rtm.core.persistence.entity.RtmOutputLog;

public class RtmOutputLogDaoImpl implements RtmOutputLogDao {

	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int queryPsnCnt(String psn) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from rtm_output_log ");
		sql.append("where psn='").append(psn).append("'");
		Integer cnt = jdbcTemplate.queryForObject(sql.toString(), Integer.class);
		return cnt.intValue();
	}

	@Override
	public int updatePsnCnt(String psn,int cnt) {
		StringBuilder sql = new StringBuilder();
		sql.append("update rtm_output_log ");
		sql.append("set total_send=total_send+").append(cnt).append(",");
		sql.append("   finished_time=SYSDATE() ");
		sql.append("where psn = '").append(psn).append("'");
		return jdbcTemplate.update(sql.toString());
	}

	@Override
	public int insertPsnCnt(RtmOutputLog rtmOutputLog) {
		StringBuilder strSql = new StringBuilder();
		strSql.append("insert into rtm_output_log(");
		strSql.append("psn,system_id,service_id,tenant_id,total_send,finished_time");
		strSql.append(") values(?,?,?,?,?,?)");
		Object[] params = new Object[6];
		params[0] = rtmOutputLog.getPsn();
		params[1] = rtmOutputLog.getSystemId();
		params[2] = rtmOutputLog.getServiceId();
		params[3] = rtmOutputLog.getTenantId();
		params[4] = rtmOutputLog.getTotalSend();
		params[5] = rtmOutputLog.getFinishedTime();
		return jdbcTemplate.update(strSql.toString(), params);
	}
	
}
