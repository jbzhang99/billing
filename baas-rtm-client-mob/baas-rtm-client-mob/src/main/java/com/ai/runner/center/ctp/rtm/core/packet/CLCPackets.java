package com.ai.runner.center.ctp.rtm.core.packet;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.runner.center.ctp.rtm.core.cache.CacheProxy;
import com.ai.runner.center.ctp.rtm.core.generator.IPsnGenerator;
import com.ai.runner.center.ctp.rtm.core.generator.ISnGenerator;
import com.ai.runner.center.ctp.rtm.core.generator.PsnContainer;
import com.ai.runner.center.ctp.rtm.core.generator.SnContainer;
import com.ai.runner.center.ctp.rtm.core.util.RtmConstants;
import com.ai.runner.center.ctp.rtm.core.util.StringLine;

public class CLCPackets extends Packets implements IPacket {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(CLCPackets.class);
	private CacheProxy cacheClient;
	private PsnContainer psnContainer;
	private SnContainer snContainer;
	
	public PsnContainer getPsnContainer() {
		return psnContainer;
	}


	public void setPsnContainer(PsnContainer psnContainer) {
		this.psnContainer = psnContainer;
	}


	public SnContainer getSnContainer() {
		return snContainer;
	}


	public void setSnContainer(SnContainer snContainer) {
		this.snContainer = snContainer;
	}


	public CacheProxy getCacheClient() {
		return cacheClient;
	}


	public void setCacheClient(CacheProxy cacheClient) {
		this.cacheClient = cacheClient;
	}


	@Override
	public String assemblePacketByLine(String service_id, List<StringLine> lines) {
		IPsnGenerator psnGenerator = psnContainer.getGenerator(service_id);
		ISnGenerator snGenerator = snContainer.getGenerator(service_id);
		StringBuilder busData = new StringBuilder();		
		String[] fieldNames = null;
		String psn="",sn="";
		for(StringLine line:lines){
			StringBuilder record = new StringBuilder();
			record.append(system_id).append(RtmConstants.FIELD_SPLIT);
			record.append(tenant_id).append(RtmConstants.FIELD_SPLIT);
			record.append(service_id).append(RtmConstants.FIELD_SPLIT);
			
			fieldNames = StringUtils.splitPreserveAllTokens(line.getData(), line.getDelimiter());
			psn = psnGenerator.getNext(fieldNames);
			line.setPsn(psn);
			record.append(psn).append(RtmConstants.FIELD_SPLIT);
			setPsnAccumulate(psn,service_id);
			sn = snGenerator.getNext(fieldNames);
			line.setSn(sn);
			record.append(sn).append(RtmConstants.FIELD_SPLIT);
			for(String fieldName:fieldNames){
				record.append(fieldName).append(RtmConstants.FIELD_SPLIT);
			}
			busData.append(record.substring(0, record.length()-1)).append(RtmConstants.RECORD_SPLIT);
		}
		return busData.delete(busData.length()-1, busData.length()).toString();
	}
	
	
	private void setPsnAccumulate(String psn,String service_id){
		//logger.debug("psn--->>>{}", psn);
		//cacheClient.incr(psn);
		StringBuilder key = new StringBuilder();
		key.append(StringUtils.upperCase(system_id)).append("$");
		key.append(StringUtils.upperCase(tenant_id)).append("$");
		key.append(StringUtils.upperCase(service_id)).append("$PSN");
		cacheClient.getCache().hincrBy(key.toString(), psn, 1);
		//cacheClient.sadd(key.toString(), psn);
	}

}
