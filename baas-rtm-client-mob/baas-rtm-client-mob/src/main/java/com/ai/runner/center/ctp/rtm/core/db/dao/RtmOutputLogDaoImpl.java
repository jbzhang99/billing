package com.ai.runner.center.ctp.rtm.core.db.dao;

import com.ai.runner.center.ctp.rtm.core.db.JdbcTemplate;
import com.ai.runner.center.ctp.rtm.core.db.entity.RtmOutputLog;

public class RtmOutputLogDaoImpl implements RtmOutputLogDao {

	@Override
	public int insertOutputLog(RtmOutputLog rtmOutputLog) {
		StringBuilder strSql = new StringBuilder();
		strSql.append("insert into rtm_output_log(");
		strSql.append("psn,total_send,finished,finished_time,system_id,service_id,tenant_id,path");
		strSql.append(") values(?,?,?,?,?,?,?,?)");
		
		Object[] params = new Object[8];
		params[0] = rtmOutputLog.getPsn();
		params[1] = rtmOutputLog.getTotal_send();
		params[2] = rtmOutputLog.getFinished();
		params[3] = rtmOutputLog.getFinished_time();
		params[4] = rtmOutputLog.getSystem_id();
		params[5] = rtmOutputLog.getService_id();
		params[6] = rtmOutputLog.getTenant_id();
		params[7] = rtmOutputLog.getPath();
		
		return JdbcTemplate.update(dsName, strSql.toString(), params);
	}

//	@Override
//	public int updateTotalSend(String psn, int record_number) {
//		StringBuilder strSql = new StringBuilder();
//		strSql.append(" update rtm_output_log r ");
//		strSql.append(" set r.total_send=r.total_send+").append(record_number);
//		strSql.append(" where r.psn = '").append(psn).append("' ");
//		return JdbcTemplate.update(dsName, strSql.toString(), (Object[])null);
//	}

	@Override
	public int updateFinished(String psn, int totalSend, String path) {
		StringBuilder strSql = new StringBuilder();
		strSql.append("update rtm_output_log r ");
		strSql.append("set r.finished = 1,");
		strSql.append("r.total_send=").append(totalSend).append(",");
		strSql.append("r.path='").append(path).append("' ");
		strSql.append("where r.psn = '").append(psn).append("' ");
		return JdbcTemplate.update(dsName, strSql.toString(), (Object[])null);
	}

}
