package com.ai.runner.center.ctp.rtm.core.db.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.runner.center.ctp.rtm.core.db.entity.BlUserinfo;
import com.ai.runner.center.ctp.rtm.core.util.DataSourceUtil;
import com.ai.runner.center.ctp.rtm.core.util.RtmConstants;
import com.alibaba.fastjson.JSON;

public class BlUserinfoDaoImpl implements BlUserinfoDao {
	
	private static Logger logger = LoggerFactory.getLogger(BlUserinfoDaoImpl.class);

	@Override
	public List<BlUserinfo> queryUsers(BlUserinfo user) {
		StringBuilder strSql = new StringBuilder();
		strSql.append("SELECT SUBS_ID subsId,service_ID serviceNum,null IMSI1");
		strSql.append(" FROM bl_userinfo user");
		strSql.append(" WHERE");
		
		strSql.append(" NOW() between user.active_time and user.inactive_time ");
		strSql.append(" and EXISTS( select 1 from bl_userinfo_ext user_ext where user_ext.subs_id=`user`.SUBS_ID and user_ext.EXT_NAME=? and user_ext.ext_value like ? )");
		
		
		
		Object[] params = new Object[2];
		
		params[0]="basic_org_id";
		params[1] = StringUtils.defaultString(RtmConstants.BASIC_ORG_ID_CU+"%");
		try {
			QueryRunner runner = new QueryRunner(DataSourceUtil.getBasicDataSource(RtmConstants.MYSQL_DATASOURCE_NAME));
			System.out.println("打印的联通用户---》"+JSON.toJSONString(runner.query(strSql.toString(),new BeanListHandler<BlUserinfo>(BlUserinfo.class), params)));
			return runner.query(strSql.toString(),new BeanListHandler<BlUserinfo>(BlUserinfo.class), params);
		} catch (SQLException e) {
			logger.error("查询用户信息失败",e);
		}
		return null;
	}
	
	
	
	private List<String> getSUbsIds(){
		StringBuilder sb=new StringBuilder();
		sb.append("select SUBS_ID  From bl_userinfo_ext WHERE  EXT_NAME=? and EXT_VALUE like ?");
		Object[] params = new Object[2];
		params[0]="basic_org_id";
		params[1] = StringUtils.defaultString(RtmConstants.BASIC_ORG_ID_CU+"%");
		
		QueryRunner runner;
		try {
			runner = new QueryRunner(DataSourceUtil.getBasicDataSource(RtmConstants.MYSQL_DATASOURCE_NAME));
			System.out.println("查询扩展表-----》"+JSON.toJSONString(runner.query(sb.toString(),new ColumnListHandler<String>("SUBS_ID"), params)));
			return runner.query(sb.toString(),new ColumnListHandler<String>("SUBS_ID"), params);
		} catch (SQLException e) {
			logger.error("查询联通信息失败：",e.getMessage());
		}
	
		return null;
	}

}
