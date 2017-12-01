package com.ai.baas.job.query.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ai.baas.job.query.interfaces.IpriceStep;
import com.ai.baas.job.util.DbUtil;
import com.ai.baas.job.vo.PriceBasisInfo;
import com.ai.baas.job.vo.StepIdInfo;

public class PriceStepImpl implements IpriceStep{
	Logger logger=Logger.getLogger(PriceStepImpl.class);
	private Connection connection;	
	PreparedStatement chargeStmt=null;
	ResultSet chargeRes=null;
	PreparedStatement basisStepStmt=null;
	ResultSet basisStepRes=null;
	PreparedStatement basisStmt=null;
	ResultSet basisRes=null;
	public List<PriceBasisInfo> getPriceInfo() {
		connection=DbUtil.getConnection();
		String chargeSql=" select price_code,detail_code from cp_price_detail where charge_type='STEP' ";
		String basisStepSql=" select a.detail_code,a.section_a,a.section_b,a.price_value,a.total_price_value, "+
							" b.detail_code,b.measure_word_code from cp_step_info a,cp_price_basis b "+
							" where a.service_type=b.detail_code and a.unit_type=b.measure_word_code and a.detail_code=? and b.cal_way='fixed_time' ";
		String basisSql="select tenant_id,add_up_subject from cp_price_basis where detail_code=? and measure_word_code=? ";
		List<PriceBasisInfo> priceList=new ArrayList<PriceBasisInfo>();
		try{
			chargeStmt=connection.prepareStatement(chargeSql);
			chargeRes=chargeStmt.executeQuery();
			basisStepStmt=connection.prepareStatement(basisStepSql);
			while(chargeRes.next()){
				int num=0;
				List<StepIdInfo> stepInfoList=new ArrayList<StepIdInfo>();
				basisStepStmt.setString(1, chargeRes.getString(2));
				logger.debug("the detail_code is "+chargeRes.getString(2));
				basisStepRes=basisStepStmt.executeQuery();
				String serviceType=null;
				String measureWord=null;
				while(basisStepRes.next()){
					StepIdInfo step=new StepIdInfo();
					step.setDetailCode(basisStepRes.getString(1));
					step.setSectionA(basisStepRes.getDouble(2));
					step.setSectionB(basisStepRes.getDouble(3));
					step.setPriceValue(basisStepRes.getDouble(4));
					step.setTotalPriceValue(basisStepRes.getDouble(5));
					if(num==0){
						serviceType=basisStepRes.getString(6);
						measureWord=basisStepRes.getString(7);
					}
					stepInfoList.add(step);
					num++;
				}
				basisStmt=connection.prepareStatement(basisSql);
				basisStmt.setString(1, serviceType);
				basisStmt.setString(2, measureWord);
				basisRes=basisStmt.executeQuery();
				while(basisRes.next()){
					PriceBasisInfo price=new PriceBasisInfo();
					price.setPriceCode(chargeRes.getString(1));
					price.setTenantId(basisRes.getString(1));
					price.setAddUpSubject(basisRes.getString(2));
					price.setMeasureWordCode(measureWord);
					price.setServiceType(serviceType);
					price.setStepIdInfos(stepInfoList);
					priceList.add(price);
				}	
			}
			return priceList;
		}catch(SQLException e){
			logger.error("the peicre basis is error........");
			e.printStackTrace();
			return null;
		}finally{
			DbUtil.closeConnection(connection);
		}
		
	}

}
