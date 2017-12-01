package com.ai.runner.center.ctp.rtm.core.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class JsonUtil {

	private String business_id = "";
	private String service_id = "";
	private String packet_sn = "";
	private String create_date = "";
	private List<Map<String, String>> datas = new ArrayList<Map<String, String>>();
	
	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}
	
	public void setService_id(String service_id) {
		this.service_id = service_id;
	}
	
	public void setPacket_sn(String packet_sn) {
		this.packet_sn = packet_sn;
	}
	
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	
	public void setDatas(List<Map<String, String>> datas) {
		this.datas = datas;
	}

	public String toJsonString(){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put(RtmConstants.SYSTEM_ID, business_id);
		jsonObj.put(RtmConstants.SERVICE_ID, service_id);
		jsonObj.put(RtmConstants.PACKET_SERIAL_NUMBER, packet_sn);
		jsonObj.put(RtmConstants.PACKET_CREATE_DATE, create_date);
		jsonObj.put(RtmConstants.PACKET_DATA, datas);
		return jsonObj.toString();
	}
	
	
}
