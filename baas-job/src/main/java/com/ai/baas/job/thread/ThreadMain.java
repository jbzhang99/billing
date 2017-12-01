package com.ai.baas.job.thread;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ai.baas.job.query.interfaces.IpriceStep;
import com.ai.baas.job.statictis.interfaces.IstatictisSum;
import com.ai.baas.job.util.DbUtil;
import com.ai.baas.job.vo.PriceBasisInfo;
import com.ai.baas.job.vo.StatictisInfo;
import com.alibaba.fastjson.JSON;

public class ThreadMain {
	@Autowired
	private IpriceStep priceStep;
	@Autowired
	private IstatictisSum statictisSum;
	private String monthAcct=new SimpleDateFormat("yyyyMM").format(new Date());
	private static Logger logger=Logger.getLogger(ThreadMain.class);
	public int InsertStatic(){
		List<PriceBasisInfo> priceInfos= new ArrayList<PriceBasisInfo>();
		priceInfos=priceStep.getPriceInfo();
		for(PriceBasisInfo priceInfo:priceInfos){
			logger.debug("the priceInfo is "+JSON.toJSONString(priceInfo));
		}
		List<StatictisInfo> statictisInfos=new ArrayList<StatictisInfo>();
		statictisInfos=statictisSum.StatictisSum(priceInfos);
		for(StatictisInfo statictisInfo:statictisInfos){
			logger.debug("the statictis is "+JSON.toJSONString(statictisInfo));
		}
		Connection connection=DbUtil.getConnection();
		PreparedStatement realUpdateStmt=null;
		PreparedStatement realInsertStmt=null;
		String updateSql=" update real_charge_"+monthAcct+" set total=total+? where tenant_id=? and cust_id=? ";
		String insertSql=" insert into real_charge_"+monthAcct+" (tenant_id,cust_id,total) value(?,?,?) ";
		try
			{
				realUpdateStmt=connection.prepareStatement(updateSql);
				realInsertStmt=connection.prepareStatement(insertSql);
				for(StatictisInfo statictisInfo:statictisInfos){
					realUpdateStmt.setDouble(1, statictisInfo.getAmount());
					realUpdateStmt.setString(2, statictisInfo.getTenantId());
					realUpdateStmt.setString(3, statictisInfo.getCustId());
					int i=realUpdateStmt.executeUpdate();
					connection.commit();
					logger.debug("the update num is "+i);
					if(i==0){
						realInsertStmt.setString(1, statictisInfo.getTenantId());
						realInsertStmt.setString(2, statictisInfo.getCustId());
						realInsertStmt.setDouble(3, statictisInfo.getAmount());
						int j=realInsertStmt.executeUpdate();
						connection.commit();
						logger.debug("the j is "+j);
					}		
				}
				return 1;
			}catch(SQLException e){
				e.printStackTrace();
				return -1;
			}finally{
				DbUtil.closeConnection(connection);
			}
		}
	public static void main(String[] args){
		ApplicationContext context=new ClassPathXmlApplicationContext("context/job-context.xml");
		ThreadMain threadMain=(ThreadMain)context.getBean("insertStatic");
		int Flag=threadMain.InsertStatic();
		if(Flag==1)
			logger.debug("the statictis is success......");
		else
			logger.error("the statictis is false.......");
			
	}
}
