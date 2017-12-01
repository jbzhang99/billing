package com.ai.baas.job.statictis.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ai.baas.job.query.impl.PriceStepImpl;
import com.ai.baas.job.statictis.interfaces.IstatictisSum;
import com.ai.baas.job.util.DbUtil;
import com.ai.baas.job.vo.PriceBasisInfo;
import com.ai.baas.job.vo.StatictisInfo;
import com.ai.baas.job.vo.StepIdInfo;

public class StatictisSumImpl implements IstatictisSum{
	Connection connection=DbUtil.getConnection();
	Logger logger=Logger.getLogger(PriceStepImpl.class);
	public List<StatictisInfo> StatictisSum(List<PriceBasisInfo> priceInfos) {
		
		PreparedStatement custStmt;
		ResultSet custRes;
		PreparedStatement staticStmt;
		ResultSet staticRes;
		String custSql="select distinct cust_id from bl_subs_comm where tenant_id=? ";
		String staticAmount=" select count(*) from bl_subs_comm where subs_id in( select subs_id from bl_userinfo "+
		" where serv_type='seat') and cust_id=? and product_id=? and tenant_id=? ";
		try{
			custStmt=connection.prepareStatement(custSql);
			staticStmt=connection.prepareStatement(staticAmount);
			List<StatictisInfo> statictisInfos=new ArrayList<StatictisInfo>();
			for(PriceBasisInfo priceInfo:priceInfos){
				custStmt.setString(1, priceInfo.getTenantId());
				custRes=custStmt.executeQuery();
				List<StepIdInfo> stepInfos= new ArrayList<StepIdInfo>();
				stepInfos=priceInfo.getStepIdInfos();
				while(custRes.next()){
					String custId=custRes.getString(1);
					logger.debug("the custId is "+custRes.getString(1));
					staticStmt.setString(1, custId);
					staticStmt.setString(2, priceInfo.getPriceCode());
					staticStmt.setString(3, priceInfo.getTenantId());
					staticRes=staticStmt.executeQuery();
					if(staticRes.next()){
						StatictisInfo statictisInfo=new StatictisInfo();
						statictisInfo.setTenantId(priceInfo.getTenantId());
						statictisInfo.setPrductId(priceInfo.getPriceCode());
						statictisInfo.setCustId(custId);
						long num=staticRes.getLong(1);
						statictisInfo.setSubsNum(num);
						logger.debug("the count is "+num);
						//进行区间的判断
						double price=getPrice(stepInfos, num);
						if(price==-1) continue;
						else{
							statictisInfo.setPriceInfo(price);
							statictisInfo.setAmount(num*price);
							statictisInfos.add(statictisInfo);
						}
					}
				}
			}
			return statictisInfos;
		}catch(SQLException e){
			logger.error("the statictis is error......");
			e.printStackTrace();
			return null;
		}finally{
			DbUtil.closeConnection(connection);
		}
				
	}

	
	//进行区间的判断
	private double getPrice(List<StepIdInfo> stepInfos,long num){
		double price=-1.0;
		for(StepIdInfo stepInfo: stepInfos){
			double sectionA=stepInfo.getSectionA();
			logger.debug("the sectionA is "+sectionA);
			double sectionB=stepInfo.getSectionB();
			logger.debug("the sectionB is "+sectionB);
			if(sectionB>=num&&sectionA<num){
				logger.debug("the price is "+stepInfo.getPriceValue());
				price=stepInfo.getPriceValue();
				break;
			}
		}
		return price;
	}
	

}
