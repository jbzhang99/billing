package com.ai.runner.center.ctp.rtm.core.db.dao;


import com.ai.runner.center.ctp.rtm.core.db.JdbcTemplate;
import com.ai.runner.center.ctp.rtm.core.db.entity.RtmFailureBill;

public class RtmFailureBillDaoImpl implements RtmFailureBillDao {
	
	@Override
	public int insertFailureLog(RtmFailureBill failBill) {
		StringBuilder strSql = new StringBuilder();
		strSql.append("insert into rtm_failure_bill(");
		strSql.append("service_id,tenant_id,source,error_txt,error_row_num,");
		strSql.append("error_msg,inst_time");
		strSql.append(") values(?,?,?,?,?,?,?) ");
		
		Object[] params = new Object[7];
		params[0] = failBill.getService_id();
		params[1] = failBill.getTenant_id();
		params[2] = failBill.getSource();
		params[3] = failBill.getError_txt();
		params[4] = failBill.getError_row_num();
		params[5] = failBill.getError_msg();
		params[6] = failBill.getInst_time();
		
		return JdbcTemplate.update(dsName,strSql.toString(), params);
	}

}
