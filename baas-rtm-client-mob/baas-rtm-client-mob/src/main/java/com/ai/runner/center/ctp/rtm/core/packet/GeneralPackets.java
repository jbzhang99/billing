package com.ai.runner.center.ctp.rtm.core.packet;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import com.ai.runner.center.ctp.rtm.core.generator.IPsnGenerator;
import com.ai.runner.center.ctp.rtm.core.generator.PsnContainer;
import com.ai.runner.center.ctp.rtm.core.util.RtmConstants;
import com.ai.runner.center.ctp.rtm.core.util.StringLine;

public class GeneralPackets extends Packets implements IPacket {
	private PsnContainer psnContainer;
	
	public PsnContainer getPsnContainer() {
		return psnContainer;
	}

	public void setPsnContainer(PsnContainer psnContainer) {
		this.psnContainer = psnContainer;
	}

	@Override
	public String assemblePacketByLine(String service_id, List<StringLine> lines) {
		IPsnGenerator psnGenerator = psnContainer.getGenerator(service_id);
		StringBuilder busData = new StringBuilder();
		busData.append(system_id).append(RtmConstants.PACKET_HEADER_SPLIT);
		busData.append(service_id).append(RtmConstants.PACKET_HEADER_SPLIT);
		busData.append(tenant_id).append(RtmConstants.PACKET_HEADER_SPLIT);
		//busData.append(String.valueOf(System.currentTimeMillis())).append(RtmConstants.PACKET_HEADER_SPLIT);
		
		/////////////////////////////////
		//busData.append("psn").append(RtmConstants.PACKET_HEADER_SPLIT);
		busData.append(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss")).append(RtmConstants.PACKET_HEADER_SPLIT);
		String psn=psnGenerator.getNext(lines.size());
		
		String[] fieldNames = null;
		for(StringLine line:lines){
			StringBuilder record = new StringBuilder();
			//record.append(line.getSource()).append(RtmConstants.FIELD_SPLIT);
			//record.append(String.valueOf(line.getRowNum())).append(RtmConstants.FIELD_SPLIT);
			record.append(psn).append(RtmConstants.FIELD_SPLIT);
			
			fieldNames = StringUtils.splitPreserveAllTokens(line.getData(), line.getDelimiter());
			for(String fieldName:fieldNames){
				record.append(fieldName).append(RtmConstants.FIELD_SPLIT);
			}
			
			busData.append(record.substring(0, record.length()-1)).append(RtmConstants.RECORD_SPLIT);
		}
		//return busData.substring(0, busData.length()-1).toString();
		return busData.delete(busData.length()-1, busData.length()).toString();
	}

}
