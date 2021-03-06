package com.ai.runner.center.bmc.deduct.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ai.runner.base.exception.BusinessException;
import com.ai.runner.base.exception.CallerException;

import com.ai.runner.center.bmc.deduct.service.interfaces.Ideduct;
import com.ai.runner.center.bmc.deduct.util.DbUtil;
import com.ai.runner.center.bmc.deduct.util.HbaseUtil;
import com.ai.runner.utils.util.DubboConsumerFactory;
import com.ai.runner.viv.api.balance.interfaces.IResBalanceSV;
import com.ai.runner.viv.api.balance.param.ResourceDeduct;

@Component
public class DeductImpl implements Ideduct{
	 private static Logger logger = LoggerFactory.getLogger(DeductImpl.class);
	 
	 public void resDeduct(JSONObject jsonobject,String msgContent ) throws Exception{
		 
	     
	     logger.debug(" the resdeduct is       "+jsonobject);
		 ResourceDeduct resdeduct= new ResourceDeduct();
		 Iterator it=jsonobject.keys();
		 String tableName="ResDeductDetail";
		 String rowKey;
		 String family;
		 String qualifier;
		 String value;
		 logger.debug("enter into the rescource deduct");
		 try{
			 while(it.hasNext()){
				 String key=String.valueOf(it.next());
				 String lowkey=key.toLowerCase();
				 switch(lowkey){
					 case "event_id":
						 resdeduct.setExternalId((String)jsonobject.get(key));
						 break;
					 case "business_id":
						 resdeduct.setSystemId((String)jsonobject.get(key));
						 break;
					 case "tenant_id":
						 resdeduct.setTenantId((String)jsonobject.get(key));
						 break;
					 case "subs_id":
						 resdeduct.setOwnerId(Long.parseLong((String)jsonobject.get(key)));
						 break;
					 case "amount":
                         logger.debug("fff   the resdeduct amount is "+Double.parseDouble((String)jsonobject.get(key)));
                         System.out.println(resdeduct.getTotalAmount());
                         double total=Double.valueOf((String)jsonobject.get(key));
                         BigDecimal feeDec = new BigDecimal(total);
                         Double feeD =  feeDec.setScale(0, RoundingMode.UP).doubleValue();
                         resdeduct.setTotalAmount(feeD.longValue());
                   
                         System.out.println(resdeduct.getTotalAmount());
                         break;
					 case "amount_type":
						 String type=((String)jsonobject.get(key)).toLowerCase();
						 logger.debug("the resdeduct amount_type is "+type);
						 resdeduct.setOwnerType(0);
						 switch(type){
						 case "voice":
							 resdeduct.setResourceType(10);
							 break;
						 case "data":
							 resdeduct.setResourceType(60);
							 break;
						 case "sm":
							 resdeduct.setResourceType(50);
							 break;
						 case "vc":
							 resdeduct.setResourceType(99);
							 break;
						 }
						 break;
				 }
			 }
		 }catch(Exception e){
			 rowKey=resdeduct.getExternalId();
			 family="mess_context";
			 qualifier=resdeduct.getTenantId();
			 value=msgContent;
			 HbaseUtil.addRecord(tableName, rowKey, family, qualifier, value);
			 family="deal_flag";
			 qualifier=resdeduct.getTenantId();
			 value="0";
			 HbaseUtil.addRecord(tableName, rowKey, family, qualifier, value);
			 e.printStackTrace();
		 }
//		 if(j!=5){
//			 //此时需要写入错单表
//			 Connection conn = null;
//			 try{
//				 String insert_sql=" insert into mds_error_table(insert_time,type,message,deal_flag) values(now(),?,?,0) ";
//				 
//				 PreparedStatement pstmt = null;	
//				 conn=DbUtil.getConnection();
//				 pstmt = conn.prepareStatement(insert_sql);
//				 pstmt.setString(1, "bmc_deduct");
//				 pstmt.setString(2, msgContent);
//				 pstmt.executeUpdate();
//				 conn.commit();
//			 }catch(SQLException e){
//				 throw new SQLException(e);
//			 }finally{
//				 conn.close();
//			 }
//		 }

			 try{
			     IResBalanceSV iresdeductSV= DubboConsumerFactory.getService("iresdeductSV",IResBalanceSV.class);
				 iresdeductSV.deductResource(resdeduct);
				 rowKey=resdeduct.getExternalId();
				 family="mess_context";
				 qualifier=resdeduct.getTenantId();
				 value=msgContent;
				 HbaseUtil.addRecord(tableName, rowKey, family, qualifier, value);
				 family="deal_flag";
				 qualifier=resdeduct.getTenantId();
				 value="1";
				 HbaseUtil.addRecord(tableName, rowKey, family, qualifier, value);
			 }
			 catch(CallerException e){
				 //进行插入hbase中的操作
				 e.printStackTrace();
				 rowKey=resdeduct.getExternalId();
				 family="mess_context";
				 qualifier=resdeduct.getTenantId();
				 value=msgContent;
				 HbaseUtil.addRecord(tableName, rowKey, family, qualifier, value);
				 family="deal_flag";
				 qualifier=resdeduct.getTenantId();
				 value="0";
				 HbaseUtil.addRecord(tableName, rowKey, family, qualifier, value);
				 
			 }catch(Exception e){
			     System.out.println(" Error "+e.getMessage());
			     e.printStackTrace();
			 }

		 
	 }
	 
	public void funDeduct(JSONObject jsonobject,String msgContent ) throws Exception{
//		DeductAccount deductPara = new DeductAccount();
//		Iterator it=jsonobject.keys();
//		System.out.println("the jsonobject is 1111111 :"+ jsonobject);
//		logger.debug("the jsonobject is 1111111 :"+ jsonobject);
//		 String tableName="funDeductDetail";
//		 String rowKey;
//		 String family;
//		 String qualifier;
//		 String value;
//		 Long balance;
//		 try{
//			 while(it.hasNext()){
//				 String key=String.valueOf(it.next());
//				 String lowkey=key.toLowerCase();
//				 logger.debug("the key is "+key+"  the lowkey is "+lowkey);
//				 switch(lowkey){
//				 	case "event_id":
//				 		deductPara.setExternalId((String)jsonobject.get(key));
//				 		break;
//				 	case "system_id":
//				 		deductPara.setSystemId((String)jsonobject.get(key));
//				 		break;
//				 	case "tenant_id":
//				 		deductPara.setTenantId((String)jsonobject.get(key));
//				 		break;
//				 	case "acct_id":
//				 		deductPara.setAccountId(Long.parseLong((String)jsonobject.get(key)));
//				 		break;
//				 	case "amount":
//				 		String feeBalance=(String)jsonobject.get(key);
//				 		double total=Double.valueOf(feeBalance)/1000;
//				 		BigDecimal feeDec = new BigDecimal(total);
//			            Double feeD =  feeDec.setScale(0, RoundingMode.HALF_UP).doubleValue();
//				 		deductPara.setTotalAmount(feeD.longValue());
//				 		System.out.println("the deduct is "+feeD.longValue());
//				 		deductPara.setBusinessCode("billing_deduct");
//				 		break;
//				 }
//			 }
//		 }catch(Exception e){
//			 System.out.println("the deductPara is error ..........");
//			 rowKey=Long.toString(deductPara.getAccountId());
//			 family="mess_context";
//			 qualifier=deductPara.getExternalId();
//			 value=msgContent;
//			 HbaseUtil.addRecord(tableName, rowKey, family, qualifier, value);
//			 family="deal_flag";
//			 qualifier=deductPara.getExternalId();
//			 value="0";
//			 HbaseUtil.addRecord(tableName, rowKey, family, qualifier, value); 
////			 e.printStackTrace();
//		 }
//		 try{
//			 System.out.println("the dubbo service start  is .......  ");
//			 IDeductSV ifundeductSV= DubboConsumerFactory.getService("ideductSV",IDeductSV.class);
//			 System.out.println("the dubbo bean is .......  "+ifundeductSV);
//			 balance=ifundeductSV.deductPartFundByAccount(deductPara);
//			 System.out.println(" the balance is "+balance);
//			 if(balance!=0){
//			 updateBalace(balance,deductPara);
//			 }
//			 rowKey=Long.toString(deductPara.getAccountId());
//			 family="mess_context";
//			 qualifier=deductPara.getExternalId();
//			 value=msgContent;
//			 HbaseUtil.addRecord(tableName, rowKey, family, qualifier, value);
//			 family="deal_flag";
//			 qualifier=deductPara.getExternalId();
//			 value="0";
//			HbaseUtil.addRecord(tableName, rowKey, family, qualifier, value); 
//		 }
//
//		 catch(Exception e){
//			 System.out.println("------------------"+e.getClass().getName());
//			 System.out.println("------------------"+e.getMessage());
//			 rowKey=Long.toString(deductPara.getAccountId());
//			 logger.debug(" the dubbo service is false 00000000000000000000000000  0000000000000");
//			 family="mess_context";
//			 qualifier=deductPara.getExternalId();
//			 value=msgContent;
//			 HbaseUtil.addRecord(tableName, rowKey, family, qualifier, value);
//			 family="deal_flag";
//			 qualifier=deductPara.getExternalId();
//			 value="0";
//			 HbaseUtil.addRecord(tableName, rowKey, family, qualifier, value);
//			// e.printStackTrace();
//		 }
//		 
	}
	
	
	//将欠费记录  记录到欠费表中
//	private void updateBalace(long balance,DeductAccount deductPara) throws SQLException{
//		 Connection conn = null;
//		 try{
//			 String update_sql="update ACC_OWE_INFO set balance=balance+?,CREATE_TIME=now() where tenant_id=? and acct_id=? ";
//			 String sel_acc_sql=" select acct_id  from ACC_OWE_INFO where tenant_id=? and acct_id=? ";
//			 String insert_sql=" insert into ACC_OWE_INFO (SYSTEM_ID,TENANT_ID,ACCT_ID,BALANCE,MONTH,CREATE_TIME) values(?,?,?,?,?,now())";
//			 PreparedStatement pstmt = null;	
//			 PreparedStatement selstmt=null;
//			 PreparedStatement insertstmt=null;
//			 ResultSet selset=null;
//			 DateFormat dateFormat = null;
//			 long billbalance=balance*1000;
//			 System.out.println("the billingbalance="+billbalance);
//			 conn=DbUtil.getConnection();
//			 pstmt = conn.prepareStatement(update_sql);
//			 selstmt=conn.prepareStatement(sel_acc_sql);
//			 insertstmt=conn.prepareStatement(insert_sql);
//			 selstmt.setString(1, deductPara.getTenantId());
//			 selstmt.setString(2, String.valueOf(deductPara.getAccountId()));
//			 selset=selstmt.executeQuery();
//			 if(selset.next()){
//				 pstmt.setLong(1, billbalance);
//				 System.out.println("the billingbalance="+billbalance);
//				 pstmt.setString(2, deductPara.getTenantId());
//				 System.out.println("deductPara.getTenantId()="+deductPara.getTenantId());
//				 pstmt.setString(3, String.valueOf(deductPara.getAccountId()));
//				 System.out.println("deductPara.getAccountId()="+deductPara.getAccountId());
//				 pstmt.executeUpdate();
//				 System.out.println("the update is success");
//				 conn.commit();
//				 System.out.println("the update commit success");
//			 }
//			 else{
//				 dateFormat = new SimpleDateFormat("yyyyMM");
//				 Date date = new Date();
//				 System.out.println("enter into the insert package ..........");
//				 insertstmt.setString(1, deductPara.getSystemId());
//				 insertstmt.setString(2, deductPara.getTenantId());
//				 insertstmt.setString(3,String.valueOf(deductPara.getAccountId()));
//				 insertstmt.setLong(4,balance);
//				 insertstmt.setString(5, dateFormat.format(date));
//				 conn.commit();
//			 }
//			
//		 }catch(SQLException e){
//			 logger.error("the acct_id= "+deductPara.getAccountId()+" the balance="+balance+" update ACC_OWE_INFO is false");
//			 throw new SQLException(e);
//		 }finally{
//			 conn.close();
//		 }
//	}
	 
}
