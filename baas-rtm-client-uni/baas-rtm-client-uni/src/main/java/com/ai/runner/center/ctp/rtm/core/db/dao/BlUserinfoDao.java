package com.ai.runner.center.ctp.rtm.core.db.dao;

import java.util.List;

import com.ai.runner.center.ctp.rtm.core.db.entity.BlUserinfo;
import com.ai.runner.center.ctp.rtm.core.util.RtmConstants;

public interface BlUserinfoDao {
	
	String name = "blUserinfoDao";
	String dsName = RtmConstants.MYSQL_DATASOURCE_NAME;

    List<BlUserinfo> queryUsers(BlUserinfo user);

}