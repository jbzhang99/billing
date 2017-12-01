package com.ai.baas.bmc.srv.persistence.dao;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ai.baas.bmc.srv.entity.FailureBill;
import com.google.common.base.Joiner;

@Repository("failureBillFromMysql")
public class FailureBillFromMysqlDaoImpl implements FailureBillDao {
	private static Logger logger = LoggerFactory.getLogger(FailureBillFromMysqlDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void insertFailBillData(FailureBill failureBill) {
		//logger.debug("插入错单数据*********");
		StringBuilder strSql = new StringBuilder();
		strSql.append("insert into `bmc_failure_bill`(`tenant_id`,`service_type`,`source`,`bsn`,`sn`,`account_period`,`arrival_time`,`fail_step`,`fail_code`,`fail_reason`,`fail_packet`,`fail_date`)");
		strSql.append("values (?,?,?,?,?,?,?,?,?,?,?,?)");
		Object[] params = new Object[12];
		params[0] = failureBill.getTenantId();
		params[1] = failureBill.getServiceType();
		params[2] = failureBill.getSource();
		params[3] = failureBill.getBsn();
		params[4] = failureBill.getSn();
		params[5] = failureBill.getAccountPeriod();
		params[6] = failureBill.getArrivalTime();
		params[7] = failureBill.getFailStep();
		params[8] = failureBill.getFailCode();
		params[9] = failureBill.getFailReason();
		params[10] = failureBill.getFailPakcet();
		String failDate = failureBill.getFailDate();
		params[11] = StringUtils.isBlank(failDate)?DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"):failDate;
		//logger.debug("插入错单参数："+Joiner.on(",").join(params));
		//System.out.println("插入错单参数："+Joiner.on(",").join(params));
		jdbcTemplate.update(strSql.toString(), params);
	}
}
