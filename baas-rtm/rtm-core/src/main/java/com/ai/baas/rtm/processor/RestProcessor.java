package com.ai.baas.rtm.processor;



import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.ai.baas.rtm.constants.RtmConstants;
import com.ai.baas.rtm.entry.RtmRestStart;
import com.ai.baas.rtm.executor.LoopThread;
import com.ai.baas.rtm.generators.AuthCheck;
import com.ai.baas.rtm.generators.MessageSplit;
import com.ai.baas.rtm.utils.PropertiesUtil;
import com.ai.opt.sdk.util.DateUtil;


public class RestProcessor extends LoopThread{
	private static Logger logger=LoggerFactory.getLogger(RestProcessor.class);
	private String message;
	private  String tenant_id;
	private  String system_id;
	private  String flow;
	private  String user_id;
	private  String arrival_time;
	private String[] splitMessage=new String[6];
	private String jsBsn;
	private int type=0;
	String remark;
	public RestProcessor(String message){
		this.message=message;
		logger.debug("------------ restProcessor start");
		String sqlflag=(String) PropertiesUtil.getValue("usedatabase");
		if("0".equals(sqlflag)){
			logger.debug("use mysql to store raw data ......");
		}
		//如果为1，则使用hbase
		//HbaseUtil.init();
		
		logger.debug("hbase 建立连接......");
	}
	@Override
	public boolean init() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean unInit() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void work() {
		try{
			logger.debug("restProcess work ......");
			String month=new SimpleDateFormat("yyyyMM").format(new Date());
			arrival_time= new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			String code=messageSplit(message);
			
			if("0000001".equals(code))
				remark="消息头配置错误";
			else{
				if("0000002".equals(code))
					remark="鉴权失败";
				else {
					remark="鉴权成功";
				}
			}
			logger.debug("the remark is "+remark);
			//此时开始向hbase表中插入数据
			//HbaseUtil baseUtil= new HbaseUtil();
			//baseUtil.batchCopy(message, month, remark, flow, system_id);
			
			
			String sqlflag=PropertiesUtil.getValue("usedatabase");
			String tableprefix=PropertiesUtil.getValue("mysql.copy.tableName");
			
			if("0".equals(sqlflag)){
				//insert raw data
				String tablename=tableprefix+month;
				String sql="insert into "+tablename+" (flow,message,create_time,batch_remark) values(?,?,?,?)";
				RtmRestStart.jdbcTemplate.update(sql,new PreparedStatementSetter(){
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setString(1, flow);
						if(message.length()<RtmConstants.SQL_LENGTH){
							ps.setString(2, message);
						}else{
							ps.setString(2, message.substring(0, RtmConstants.SQL_LENGTH-1));
							logger.error("message数据过长，只截取了4096");
						}
						
						ps.setTimestamp(3, DateUtil.getSysDate());
						ps.setString(4, remark);
						
					}
				});
			}
			
			if("0000001".equals(code)||"0000002".equals(code)){
				//logger.debug("消息头鉴权失败");
				exitFlag=true;
			}else{
				MessageSplit messageSplit=new MessageSplit(splitMessage[5],tenant_id,system_id,arrival_time,flow,type,jsBsn);
				messageSplit.dataDeal();
				exitFlag=true;
			}
		}catch(Exception e){
			e.printStackTrace();
			exitFlag=true;
		}
		
	}
	
	public String messageSplit(String message){
		String batchHead=(String) PropertiesUtil.getValue("rtm.batch.head");
		String splitFlag=(String)PropertiesUtil.getValue("rtm.batch.head.properits");
		String[] headProperties=StringUtils.splitPreserveAllTokens(batchHead, splitFlag);
		splitMessage=StringUtils.splitPreserveAllTokens(message, RtmConstants.INFO_SPLIT);
		String tempTenantId=null;
		if(headProperties.length+1!=splitMessage.length){
			//return "消息头配置错误";
			return "0000001";
		}else{//此处存在耦合性
			tempTenantId=splitMessage[0];
			user_id=splitMessage[3];
			String passwd=splitMessage[4];
			system_id=splitMessage[1];
			flow=splitMessage[2];
			if(tempTenantId.contains(RtmConstants.JS_SPLIT)){
				String[] bsnTenant=StringUtils.splitPreserveAllTokens(tempTenantId, RtmConstants.JS_SPLIT);
				type=1;
				tenant_id=bsnTenant[1];
				jsBsn=bsnTenant[0];
			}else {
				tenant_id=tempTenantId;
			}
			AuthCheck authCheck=new AuthCheck(tenant_id,user_id,passwd);
			return authCheck.check();
		}
	}

}
