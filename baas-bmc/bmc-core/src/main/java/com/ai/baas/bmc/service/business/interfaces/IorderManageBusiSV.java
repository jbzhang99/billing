package com.ai.baas.bmc.service.business.interfaces;

import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.bmc.api.citic.params.InstanceInfo;


public interface IorderManageBusiSV {
	 String OrderUpdate(InstanceInfo info);
}
