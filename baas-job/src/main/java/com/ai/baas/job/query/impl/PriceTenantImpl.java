package com.ai.baas.job.query.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ai.baas.job.query.interfaces.IpriceTenant;
import com.ai.baas.job.util.CopyBean;
import com.ai.baas.job.util.DbUtil;
import com.ai.baas.job.vo.PriceBasisInfo;
import com.ai.baas.job.vo.StepIdInfo;

public class PriceTenantImpl implements IpriceTenant{
	Logger logger=Logger.getLogger(PriceTenantImpl.class);
	PreparedStatement detailStmt;
	ResultSet detailRes;
	PreparedStatement infoStmt;
	ResultSet infoRes;
	public List<PriceBasisInfo> patchPriceInfo(List<PriceBasisInfo> pricebasisinfos) {
		Connection connection=DbUtil.getConnection();
		try{
			String priceDetailSql=" select price_code from cp_price_detail where detail_code=? and charge_type='step' ";
			String priceInfoSql=" select tenant_id from cp_price_info where price_code=? ";
			detailStmt=connection.prepareStatement(priceDetailSql);
			infoStmt=connection.prepareStatement(priceInfoSql);
			List<PriceBasisInfo> priceBasisInfos=new ArrayList<PriceBasisInfo>();
			for(PriceBasisInfo priceinfo:pricebasisinfos){		
				List<StepIdInfo> stepInfos=priceinfo.getStepIdInfos();
				for(StepIdInfo stepInfo:stepInfos){
					detailStmt.setString(1, stepInfo.getDetailCode());
					break;
				}
				detailRes=detailStmt.executeQuery();
				while(detailRes.next()){
					infoStmt.setString(1, detailRes.getString(1));
					priceinfo.setPriceCode(detailRes.getString(1));
					infoRes=infoStmt.executeQuery();
					if(infoRes.next()){
						if((infoRes.getString(1)).equals(priceinfo.getTenantId())){
							PriceBasisInfo priceBasisInfo=new PriceBasisInfo();
							CopyBean bean=new CopyBean();
							priceBasisInfo=bean.copyBean(priceinfo);
							priceBasisInfos.add(priceBasisInfo);
							break;
						}
					}
				}
			}
			return priceBasisInfos;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			DbUtil.closeConnection(connection);
		}
	}

}
