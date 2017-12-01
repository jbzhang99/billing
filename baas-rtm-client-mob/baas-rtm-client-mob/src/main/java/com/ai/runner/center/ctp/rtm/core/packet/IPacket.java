package com.ai.runner.center.ctp.rtm.core.packet;

import java.util.List;

import com.ai.runner.center.ctp.rtm.core.util.StringLine;

public interface IPacket {

	String assemblePacketByLine(String service_id, List<StringLine> lines);
	
}
