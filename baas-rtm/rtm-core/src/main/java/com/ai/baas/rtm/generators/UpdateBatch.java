package com.ai.baas.rtm.generators;


import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ai.baas.rtm.entry.RtmRestStart;
import com.ai.baas.rtm.utils.PropertiesUtil;

public class UpdateBatch {
	private static Logger logger=LoggerFactory.getLogger(UpdateBatch.class);
	private static String name=(String) PropertiesUtil.getValue("rtm.batch.log.name");
	public static void updateCount(String bsn,String acct,String acctLast,Long batchNum,Long batchNext){
		logger.debug("the bsn is 111111....... "+bsn);
		try{
			int i=UpdateCount(bsn,acct,batchNum);
			if(i!=0){
				logger.debug("账期为 "+acct+"的批量日志表更新成功 ......");
			}else{
				logger.error("账期为 "+acct+"的批量日志表更新失败 ......");
			}
			if(acctLast!=null){
				int j=UpdateCount(bsn,acctLast,batchNext);
				if(j!=0){
					logger.debug("账期为 "+acct+"的批量日志表更新成功 ......");
				}else{
					logger.error("账期为 "+acct+"的批量日志表更新失败 ......");
				}
			}
		}
		catch(SQLException e){
			logger.error("the update error is :"+e.getMessage());	
			e.printStackTrace();
		}
	}
		
	private static synchronized int UpdateCount(String bsn,String acct,long batchNum) throws SQLException{
		StringBuilder tableName=new StringBuilder();
		String name=(String) PropertiesUtil.getValue("rtm.batch.log.name");
		tableName.append(name).append(acct);
		String updateSql=" update "+tableName.toString()+" set DATA_COUNT=DATA_COUNT+? where bsn=?  ";
		Object[] params=new Object[2];
		params[0]=batchNum;
		params[1]=bsn;
		logger.debug("the bsn is ........."+bsn);
		logger.debug("the batchNUm is "+batchNum);
		int i=RtmRestStart.jdbcTemplate.update(updateSql, params);
		if(i==0){
			//表示没有更新成功，此时需要插入操作
			String insertSql=" insert into "+tableName.toString()+" (bsn,data_count,check_state,is_switch) values(?,?,'N','N') ";
			params[0]=bsn;
			params[1]=batchNum;
			logger.debug("the insert bsn "+bsn);
			logger.debug("the batchNmu "+batchNum);
			int j=RtmRestStart.jdbcTemplate.update(insertSql,params);
			logger.debug("the j is "+j);
			return j;
		}else {
			return i;
		}
	}
	public static void updateSwitch(String bsn,String acct,String acctNext){
		logger.debug("the acct is......... "+acct);
		if(acct!=null){
			updateSql(acct,bsn);
		}
		logger.debug("the acctNext is......... "+acctNext);
		if(acctNext!=null){
			updateSql(acctNext, bsn);
		}
	}
	public static void updateSql(String acct,String bsn){
		StringBuilder tableName=new StringBuilder();
		tableName.append(name).append(acct);
		String updateSql=" update "+tableName.toString()+" set is_switch='Y',switch_time=now() where bsn=? ";
		Object[] params=new Object[1];
		params[0]=bsn;
		int i=RtmRestStart.jdbcTemplate.update(updateSql,params);
		if(i==1)
			logger.debug("账期 "+acct+"  更新完成........");
		else{
			logger.error("账期 "+acct+"  更新失败......请查看原因");
		}
	}
}
