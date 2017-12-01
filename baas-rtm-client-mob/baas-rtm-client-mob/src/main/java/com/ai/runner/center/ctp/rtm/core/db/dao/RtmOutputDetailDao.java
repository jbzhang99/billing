package com.ai.runner.center.ctp.rtm.core.db.dao;

import java.util.List;

import com.ai.runner.center.ctp.rtm.core.util.RtmConstants;
import com.ai.runner.center.ctp.rtm.core.util.StringLine;

public interface RtmOutputDetailDao {
	
	String name = "rtmOutputDetailDao";
	String dsName = RtmConstants.HBASE_DATASOURCE_NAME;
	
	//int[] batchInstOutputDetail(String psn,List<StringLine> lines);
	int[] batchInstOutputDetail(List<StringLine> lines);
}
