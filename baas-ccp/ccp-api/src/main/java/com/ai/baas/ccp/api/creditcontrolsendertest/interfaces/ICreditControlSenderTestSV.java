package com.ai.baas.ccp.api.creditcontrolsendertest.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 信控消息模拟器
 * @author mayt
 *
 */
@Path("/creditcontrolsendertest")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface ICreditControlSenderTestSV {
	/**
	 * @param message
	 * @RestRelativeURL creditcontrolsendertest/sendMessage
	 */
	@POST
    @Path("/sendMessage")
	void sendMessage(String message);
}
