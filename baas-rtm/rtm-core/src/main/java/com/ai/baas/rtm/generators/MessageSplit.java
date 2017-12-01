package com.ai.baas.rtm.generators;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.ai.baas.rtm.constants.RtmConstants;
import com.ai.baas.rtm.entry.RtmRestStart;
import com.ai.baas.rtm.initload.McsClient;
import com.ai.baas.rtm.processor.RestProcessor;
import com.ai.baas.rtm.utils.HbaseUtil;
import com.ai.baas.rtm.utils.PropertiesUtil;
import com.ai.opt.sdk.util.DateUtil;

public class MessageSplit {
	private static Logger logger = LoggerFactory.getLogger(MessageSplit.class);
	private String data;
	private long batchNum;
	private long batchNext;
	private String acct = null;
	private String acctNext = null;
	private String tenant_id;
	private String system_id;
	private String arrival_time;
	private String flow;
	private int type;
	private String jsBsn;
	private String acctLast = null;
	private Object object = new Object();
	private String sn;
	private String acc;
	private String bsn;
	private String rec;
	public MessageSplit(String data, String tenant_id, String system_id, String arrival_time, String flow, int type,
			String jsBsn) {
		this.data = data;
		this.system_id = system_id;
		this.tenant_id = tenant_id;
		this.arrival_time = arrival_time;
		this.flow = flow;
		this.type = type;
		this.jsBsn = jsBsn;
	}

	private String getRecordSplit(String tenant_id, String system_id) {
		StringBuilder recordKey = new StringBuilder();
		recordKey.append(tenant_id).append(RtmConstants.KEY_JOINER).append(system_id);
		String recordSplit = (String) PropertiesUtil.getValue("rtm.record.split");
		logger.debug("the recordKey is" + recordKey.toString() + " the recordSplit is " + recordSplit);
		String record = McsClient.client.hget(recordKey.toString(), recordSplit);
		return McsClient.client.hget(recordKey.toString(), recordSplit);
	}

	private String getFieldSplit(String tenant_id, String system_id) {
		StringBuilder recordKey = new StringBuilder();
		recordKey.append(tenant_id).append(RtmConstants.KEY_JOINER).append(system_id);
		String fieldSplit = (String) PropertiesUtil.getValue("rtm.field.split");
		logger.debug("the recordKey is" + recordKey.toString() + " the fieldSplit is " + fieldSplit);
		// String field=McsClient.client.hget(recordKey.toString(),fieldSplit);
		return McsClient.client.hget(recordKey.toString(), fieldSplit);
	}

	// public String getMessage(){
	// String[] data=StringUtils.splitPreserveAllTokens(message, (String)
	// PropertiesUtil.getValue("rtm.batch.head.message"));
	// int length=data.length;
	// return data[length-1];
	// }
	public void dataDeal() {
		String recordSplit = getRecordSplit(tenant_id, system_id);
		String[] records = StringUtils.splitPreserveAllTokens(data, recordSplit);
		// batchNum=records.length;
		String fieldSplit = getFieldSplit(tenant_id, system_id);
		int i = 0;
		String[] bsns = new String[2];
		if (type == 1) {
			bsns[0] = jsBsn;
			bsns[1] = jsBsn;
		} else {
			bsns = BsnGenerator.getBsn();
		}
		for (String record : records) {

			String[] dataRecords = StringUtils.splitPreserveAllTokens(record, fieldSplit);
			// 生成账期
			if (i == 0) {
				if (type == 1) {
					acct = AcctTransfer.getAcct(dataRecords[0], dataRecords, arrival_time, type, tenant_id, system_id);
				} else {
					acct = AcctTransfer.getAcct(dataRecords[0], dataRecords, arrival_time, 0, tenant_id, system_id);
				}
				String sn = SnGenerator.getSn(dataRecords);
				logger.debug("the sn is " + sn);
				StringBuilder re = new StringBuilder();
				int local = record.indexOf(fieldSplit);
				re.append(tenant_id).append(fieldSplit).append(system_id).append(fieldSplit).append(dataRecords[0])
						.append(fieldSplit).append(bsns[1]).append(fieldSplit).append(sn).append(fieldSplit)
						.append(arrival_time).append(fieldSplit).append(acct).append(fieldSplit)
						.append(StringUtils.substring(record, local + 1));
				// re.append(acct).append(fieldSplit).append(sn).append(fieldSplit).append(arrival_time).append(fieldSplit).append(record);
				records[i] = re.toString();
				i++;
				batchNum++;
				logger.debug("the batchNum is " + batchNum);
			} else {
				if (type == 1) {
					acctNext = AcctTransfer.getAcct(dataRecords[0], dataRecords, arrival_time, type, tenant_id,
							system_id);
				} else {
					acctNext = AcctTransfer.getAcct(dataRecords[0], dataRecords, arrival_time, 0, tenant_id, system_id);
				}
				if (acctNext.equals(acct)) {
					batchNum++;
					logger.debug("the batchNum2 is " + batchNum);
				} else {
					acctLast = acctNext;
					batchNext++;
					logger.debug("the batchNext is " + batchNext);
				}
				String sn = SnGenerator.getSn(dataRecords);
				logger.debug("the sn is " + sn);
				StringBuilder re = new StringBuilder();
				int local = record.indexOf(fieldSplit);
				re.append(tenant_id).append(fieldSplit).append(system_id).append(fieldSplit).append(dataRecords[0])
						.append(fieldSplit).append(bsns[1]).append(fieldSplit).append(sn).append(fieldSplit)
						.append(arrival_time).append(fieldSplit).append(acctNext).append(fieldSplit)
						.append(StringUtils.substring(record, local + 1));
				records[i] = re.toString();
				i++;
			}
		}
		// String[] bsns=BsnGenerator.getBsn();
		// 此时更新批量日志表(可能会涉及到同步机制的问题)(向mysql中)
		// String[] jsbsns= new String[2];
		StringBuilder mcsAcct = new StringBuilder();
		mcsAcct.append(tenant_id).append(RtmConstants.KEY_JOINER).append(RtmConstants.DEMO_NUM);
		String demoNum = McsClient.client.hget(mcsAcct.toString(), arrival_time.substring(0, 6));
		if (demoNum != null) {
			long num = Long.parseLong(demoNum) + batchNum;
			McsClient.client.hset(mcsAcct.toString(), arrival_time.substring(0, 6), String.valueOf(num));
		}
		// UpdateBatch update= new UpdateBatch();
		// synchronized(this){
		UpdateBatch.updateCount(bsns[1], acct, acctLast, batchNum, batchNext);
		// }
		SentToMds mds = new SentToMds();
		mds.MessagePackage(system_id, records, recordSplit, type);

		//HbaseUtil baseadd = new HbaseUtil();
		//baseadd.detailInsert(records, tenant_id, system_id, bsns[1], fieldSplit);

		// 将要发送的已经拆分的数据保存到mysql当中
		String sqlflag = (String) PropertiesUtil.getValue("usedatabase");
		String tableprefix=PropertiesUtil.getValue("mysql.output.tableName");
		bsn= bsns[1];
		if ("0".equals(sqlflag)) {
			for (String record : records) {
				String[] recordList = StringUtils.splitPreserveAllTokens(record, fieldSplit);
				rec=record;
				sn = recordList[4];
				acc = recordList[6];
				String tablename = tableprefix + acc;
				String sql="insert into "+tablename+" (sn,record,create_time,bsn) values(?,?,?,?)";
				RtmRestStart.jdbcTemplate.update(sql,new PreparedStatementSetter(){
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setString(1, sn);
						if(rec.length()<RtmConstants.SQL_LENGTH){
							ps.setString(2, rec);
						}else{
							ps.setString(2, rec.substring(0, RtmConstants.SQL_LENGTH-1));
							logger.error("数据过长，只截取了4096");
						}
						
						ps.setTimestamp(3, DateUtil.getSysDate());
						ps.setString(4, bsn);
						
					}
				});
			}
		}
		logger.debug("the bsn is " + bsns[0]);
		logger.debug("the next bsn is " + bsns[1]);
		if (!bsns[0].equals(bsns[1])) {
			logger.debug("the bsns ........ " + bsns[0]);
			logger.debug("the bsns1........ " + bsns[1]);
			UpdateBatch.updateSwitch(bsns[0], acct, acctLast);
		}
		// 开始封包向kafka中发送

		// 此时如果需要更新批量日志表则将日志表进行更新

	}

	public static void main(String[] args) {
		String[] as = new String[4];
		as[0] = "q";
		as[1] = "s";
		as[2] = "w";
		as[3] = "f";
		int i = 0;
		for (String a : as) {
			a = "1" + a;
			as[i] = a;
			i++;
		}
		for (String b : as) {
			System.out.println("the char is " + b);
		}
	}

}
