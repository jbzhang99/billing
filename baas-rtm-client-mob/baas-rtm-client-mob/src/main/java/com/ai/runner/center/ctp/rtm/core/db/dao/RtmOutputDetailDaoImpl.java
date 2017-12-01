package com.ai.runner.center.ctp.rtm.core.db.dao;

import java.util.ArrayList;
import java.util.List;

import com.ai.runner.center.ctp.rtm.core.db.JdbcTemplate;
import com.ai.runner.center.ctp.rtm.core.util.StringLine;

public class RtmOutputDetailDaoImpl implements RtmOutputDetailDao {

	@Override
	public int[] batchInstOutputDetail(List<StringLine> lines) {
		StringBuilder strSql = new StringBuilder();
		strSql.append("UPSERT INTO RTM_OUTPUT_DETAIL_A(");
		strSql.append("DETAIL_ID,PSN,SN,LINE_VALUE");
		strSql.append(") VALUES(?,?,?,?)");
		
		List<Object[]> inputParams = new ArrayList<Object[]>();
		Object[] param = null;
		StringLine stringLine = null;
		for(int i=0;i<lines.size();i++){
			stringLine = lines.get(i);
			param = new Object[4];
			param[0] = String.valueOf(System.currentTimeMillis())+(i+1);
			param[1] = stringLine.getPsn();
			param[2] = stringLine.getSn();
			param[3] = stringLine.getData();
			
			inputParams.add(param);
			System.out.println("------------------------------");
		}
		return JdbcTemplate.batchUpdate(dsName, strSql.toString(), inputParams);
	}

}
