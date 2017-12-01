package com.ai.runner.center.ctp.rtm.core.packet;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PacketFactory {

	private static Logger logger = LoggerFactory.getLogger(PacketFactory.class);
	private static Map<String, String> clazzMap = new HashMap<String, String>();
	private static Map<String, String> mapping = new HashMap<String, String>();
	private String strPacketMapping;
	static{
		clazzMap.put("GENERAL", "com.ai.runner.center.ctp.rtm.core.packet.GeneralPackets");
		clazzMap.put("CLC", "com.ai.runner.center.ctp.rtm.core.packet.CLCPackets");
		
			
	}
	
	
	@SuppressWarnings("unchecked")
	public  IPacket createPacketObj(String service_id){
		IPacket ipacket = null;
		if(StringUtils.isNotBlank(strPacketMapping)){
			String[] mappingSplit = StringUtils.splitPreserveAllTokens(strPacketMapping,",");
			for(String mappingStr:mappingSplit){
				String[] tmpSplit = StringUtils.splitPreserveAllTokens(mappingStr,"$");
				mapping.put(tmpSplit[0], StringUtils.upperCase(tmpSplit[1]));
			}
		}
		String packetName = mapping.get(service_id);
		if(StringUtils.isBlank(packetName)){
			return ipacket;
		}
		String clazzName = clazzMap.get(packetName);
		if(StringUtils.isBlank(clazzName)){
			return ipacket;
		}
		Class<IPacket> clazz = null;
		try {
			clazz = (Class<IPacket>)Class.forName(clazzName);
			ipacket = clazz.newInstance();
		} catch (Exception e) {
			logger.error("context", e);
		}
		return ipacket;
	}


	public String getStrPacketMapping() {
		return strPacketMapping;
	}


	public void setStrPacketMapping(String strPacketMapping) {
		this.strPacketMapping = strPacketMapping;
	}
	
	
}
