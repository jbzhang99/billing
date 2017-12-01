package com.ai.runner.center.ctp.rtm.core.deliver;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.runner.center.ctp.rtm.core.db.DaoFactory;
import com.ai.runner.center.ctp.rtm.core.db.dao.RtmFailureBillDao;
import com.ai.runner.center.ctp.rtm.core.db.entity.RtmFailureBill;
import com.ai.runner.center.ctp.rtm.core.util.BpsRecordManager;
import com.ai.runner.center.ctp.rtm.core.util.JsonUtil;
import com.ai.runner.center.ctp.rtm.core.util.RtmConstants;
import com.ai.runner.center.ctp.rtm.core.util.StringLine;

public class Packets {
	
	private static Logger logger = LoggerFactory.getLogger(Packets.class);
	private String business_id;
	private String tenant_id;
	
	public String getBusiness_id() {
		return business_id;
	}

	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}

	public String getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}

	
	
	public String assembleJsonPacket(String service_id, List<StringLine> lines){
		List<String> cacheFieldName = BpsRecordManager.getInstance().getFieldNames(service_id);
		//String delimiter = (String)PropertiesUtil.getValue("ctp.rtm.file-reader.data.delimiter");
		JsonUtil jsonUtil = new JsonUtil();
		List<Map<String, String>> busDatas = new ArrayList<Map<String, String>>();
		String[] fieldNames = null;
		for(StringLine line:lines){
			try{
				Map<String, String> busData = new HashMap<String, String>();
				fieldNames = StringUtils.splitPreserveAllTokens(line.getData(), line.getDelimiter());
				int len = fieldNames.length;
				for(int i=0;i<len;i++){
					busData.put(cacheFieldName.get(i), fieldNames[i]);
					//logger.debug(cacheFieldName.get(i)+"---"+fieldNames[i]);
				}
				busData.put(RtmConstants.PACKET_DATA_ROW_NUM, String.valueOf(line.getRowNum()));
				busData.put(RtmConstants.PACKET_DATA_SOURCE, line.getSource());
				busDatas.add(busData);
			}catch(Exception e){
				logger.error(e.getMessage());
				//line.setBusinessType(service_id);
				line.setServiceId(service_id);
				StringWriter sw = new StringWriter();
				e.printStackTrace(new PrintWriter(sw, true));
				writeFailureLog(line,sw.toString());
				continue;
			}
		}
		jsonUtil.setBusiness_id(business_id);
		jsonUtil.setService_id(service_id);
		jsonUtil.setPacket_sn(String.valueOf(System.currentTimeMillis()));
		jsonUtil.setCreate_date(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		jsonUtil.setDatas(busDatas);
		return jsonUtil.toJsonString();
	}
		
	public String assembleLinePacket(String service_id, String psn, List<StringLine> lines){
		StringBuilder busData = new StringBuilder();
		busData.append(business_id).append(RtmConstants.PACKET_HEADER_SPLIT);
		busData.append(service_id).append(RtmConstants.PACKET_HEADER_SPLIT);
		busData.append(tenant_id).append(RtmConstants.PACKET_HEADER_SPLIT);
		//busData.append(String.valueOf(System.currentTimeMillis())).append(RtmConstants.PACKET_HEADER_SPLIT);
		busData.append(psn).append(RtmConstants.PACKET_HEADER_SPLIT);
		busData.append(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss")).append(RtmConstants.PACKET_HEADER_SPLIT);
		
		String[] fieldNames = null;
		for(StringLine line:lines){
			StringBuilder record = new StringBuilder();
			record.append(line.getSource()).append(RtmConstants.FIELD_SPLIT);
			record.append(String.valueOf(line.getRowNum())).append(RtmConstants.FIELD_SPLIT);
			fieldNames = StringUtils.splitPreserveAllTokens(line.getData(), line.getDelimiter());
			for(String fieldName:fieldNames){
				record.append(fieldName).append(RtmConstants.FIELD_SPLIT);
			}
			
			busData.append(record.substring(0, record.length()-1)).append(RtmConstants.RECORD_SPLIT);
		}
		return busData.substring(0, busData.length()-1).toString();
	}
	
	private void writeFailureLog(StringLine line, String error_msg){
		RtmFailureBill rtmFailureBill = new RtmFailureBill();
		//rtmFailureBill.setBusiness_type(line.getBusinessType());
		rtmFailureBill.setService_id(line.getServiceId());
		rtmFailureBill.setTenant_id(tenant_id);
		rtmFailureBill.setSource(line.getSource());
		rtmFailureBill.setError_txt(line.getData());
		rtmFailureBill.setError_row_num(line.getRowNum());
		rtmFailureBill.setError_msg(error_msg);
		rtmFailureBill.setInst_time(new Timestamp(System.currentTimeMillis()));
		
		RtmFailureBillDao failBill = (RtmFailureBillDao)DaoFactory.getInstance(RtmFailureBillDao.name);
		failBill.insertFailureLog(rtmFailureBill);
	}
	
}
