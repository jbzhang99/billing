package com.ai.runner.center.ctp.rtm.core.db.dao;

import com.ai.runner.center.ctp.rtm.core.db.entity.RtmFailureBill;
import com.ai.runner.center.ctp.rtm.core.util.RtmConstants;

public interface RtmFailureBillDao {

	String name = "rtmFailureBillDao";
	String dsName = RtmConstants.MYSQL_DATASOURCE_NAME;
	
	int insertFailureLog(RtmFailureBill failBill);
	
}
