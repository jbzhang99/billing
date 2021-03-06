package com.ai.baas.bmc.service.atom.interfaces;

import java.util.List;

import com.ai.baas.bmc.dao.mapper.bo.BlSubsComm;

public interface IBlSubsCommonAtomSV {

	List<BlSubsComm> getSubsCommon(String subsId, String tenantId);

    void updateSubsCommon(String productId, BlSubsComm blSubsComm);

    List<BlSubsComm> getSubsCommonByProductId(String productId);
}
