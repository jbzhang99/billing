package com.ai.runner.center.bmc.deduct.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

import com.ai.paas.ipaas.mds.IMessageProcessor;
import com.ai.paas.ipaas.mds.vo.MessageAndMetadata;
import com.ai.runner.center.bmc.deduct.service.impl.DeductImpl;
//import com.google.gson.JsonObject;
//import com.ai.runner.base.exception.BusinessException;
//import com.ai.runner.center.balance.api.resdeduct.interfaces.IResDeductSV;
//import com.ai.runner.center.balance.api.resdeduct.param.ResourceDeduct;
import com.ai.runner.center.bmc.deduct.service.interfaces.Ideduct;


public class MessageProcessorImpl implements IMessageProcessor{	
	private static Logger logger = LoggerFactory.getLogger(MessageProcessorImpl.class);
//	@Autowired
	private Ideduct deductImpl;
	
	public MessageProcessorImpl(Ideduct deductImpl) {
		this.deductImpl = deductImpl;
	}
	@Override
	public void process(MessageAndMetadata msg) throws Exception {

		String msgContent=(new String(msg.getMessage(),"UTF-8"));
		JSONObject jsonobject=JSONObject.fromObject(msgContent);//将string转换成json的形式
		System.out.println("the msgcontent is:"+msgContent);
		String amount_type=(String) jsonobject.getString("amount_type");
		System.out.println("    the amount_type is "+amount_type);
		try{
			switch(amount_type){
				case "VOICE":
					deductImpl.resDeduct(jsonobject, msgContent);
					break;
				case "DATA":
					deductImpl.resDeduct(jsonobject, msgContent);
					break;
				case "SM":
					deductImpl.resDeduct(jsonobject, msgContent);
					break;
				case "VC":
					deductImpl.resDeduct(jsonobject, msgContent);
					break;
				case "BOOK":
					System.out.println("if enter into the fun deduct                               \n");
					deductImpl.funDeduct(jsonobject, msgContent);
					
					break;	
			}
		}catch(Exception e){
			logger.error("the error is "+msgContent);
		}
	}
}
//	 private void ErrorDeal(String msgContent){
////		 HashMap<String, String> data=new HashMap<String,String>();
////		 JSONObject jsonobject=JSONObject.fromObject(msgContent);
////		 Iterator it=jsonobject.keys();
////		 while(it.hasNext()){
////			 
////		 }
//		 
//	 }
//	 private void resDeduct(JSONObject jsonobject,String msgContent ) throws SQLException{
//		 ResourceDeduct resdeduct= new ResourceDeduct();
//		 HashMap<String, String> data=new HashMap<String,String>();
//		// JSONObject jsonobject=JSONObject.fromObject(msgContent);
//		 Iterator it=jsonobject.keys();
//		 System.out.println("the jsonobject is :"+ jsonobject);
//		 int j=0;
//		 while(it.hasNext()){
//			 String key=String.valueOf(it.next());
//			 String lowkey=key.toLowerCase();
//			 System.out.println("the key is "+key);
//			 if("eventid".equals(lowkey)){
//				 j++;
//				 resdeduct.setSystemId((String)jsonobject.get(key));
//				 resdeduct.setOwnerType(0);
//				 resdeduct.setResourceType(60);
//				 System.out.println("the eventid is :"+ (String)jsonobject.get(key)+"   ,j="+j);
//			 }
//			 else{
//				 if("business_id".equals(lowkey)){
//					 resdeduct.setExternalId((String)jsonobject.get(key));
//					 j++;
//					 System.out.println("the business_id business_id is :"+ (String)jsonobject.get(key)+"   ,j="+j);
//				 }
//				 else{
//					 if("tenant_id".equals(lowkey)){
//					 	resdeduct.setTenantId((String)jsonobject.get(key));
//					 	j++;
//					 	System.out.println("the tenant_id is :"+ (String)jsonobject.get(key)+"   ,j="+j);
//					 }
//					 else{
//						 if("subs_id".equals(lowkey)){
//							 resdeduct.setOwnerId(Long.parseLong((String)jsonobject.get(key)));
//							 j++;
//							 System.out.println("the subs_id is :"+ (String)jsonobject.get(key)+"   ,j="+j);
//						 }
//						 else{
//							 if("amount".equals(lowkey)){
//								 resdeduct.setTotalAmount(Long.parseLong((String)jsonobject.get(key)));
//								 j++;
//								 System.out.println("the amount is :"+ (String)jsonobject.get(key)+"   ,j="+j);
//							 }
//						 }
//					 }
//				 }
//			 }
//		 }
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
//		 else{
//			 try{
//				 IResDeductSV iresdeductSV= DubboConsumerFactory.getService("iresdeductSV",IResDeductSV.class);
//				 iresdeductSV.deductResource(resdeduct);
//			 }
//			 catch(BusinessException e){
//				 //进行插入hbase中的操作
//			 }
//		 }
//		 
//	 }
//
//}
