package com.ai.runner.center.ctp.rtm.core.db.dao;

import com.ai.runner.center.ctp.rtm.core.db.entity.SingleFileInfo;
import com.ai.runner.center.ctp.rtm.core.util.RtmConstants;

public interface SingleFileInfoDao {

	String name = "singleFileInfoDao";
	String dsName = RtmConstants.MYSQL_DATASOURCE_NAME;
	
	int insertSingleFileInfo(SingleFileInfo info);
	
}
