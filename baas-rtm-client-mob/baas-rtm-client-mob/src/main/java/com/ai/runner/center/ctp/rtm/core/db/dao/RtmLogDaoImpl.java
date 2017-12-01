package com.ai.runner.center.ctp.rtm.core.db.dao;


import org.apache.commons.lang.StringUtils;

import com.ai.runner.center.ctp.rtm.core.db.JdbcTemplate;
import com.ai.runner.center.ctp.rtm.core.db.entity.RtmLog;

public class RtmLogDaoImpl implements RtmLogDao {

	@Override
	public int insertRtmLog(RtmLog rtmLog) {
		StringBuilder strSql = new StringBuilder();
		strSql.append("insert into rtm_log(");
		strSql.append("service_id,tenant_id,protocol,source,path,size,");
		strSql.append("create_date,total,start_time,end_time");
		strSql.append(") values(?,?,?,?,?,?,?,?,?,?) ");
		
		Object[] params = new Object[10];
		params[0] = StringUtils.defaultString(rtmLog.getService_id());
		params[1] = StringUtils.defaultString(rtmLog.getTenant_id());
		params[2] = rtmLog.getProtocol();
		params[3] = StringUtils.defaultString(rtmLog.getSource());
		params[4] = StringUtils.defaultString(rtmLog.getPath());
		params[5] = rtmLog.getSize();
		params[6] = rtmLog.getCreate_date();
		params[7] = rtmLog.getTotal();
		params[8] = rtmLog.getStart_time();
		params[9] = rtmLog.getEnd_time();
		
		return JdbcTemplate.update(dsName, strSql.toString(), params);
	}
	
	
//	public static void main(String[] args) {
//		RtmLogDao dao = new RtmLogDaoImpl();
//		dao.insertRtmLog(null);
//	}

}
