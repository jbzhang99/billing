package com.ai.baas.ccp.service.business.interfaces;

import com.ai.baas.ccp.topoligy.core.pojo.OmcObj;
import com.ai.baas.ccp.vo.ConfigContainerObject;
import com.ai.baas.ccp.vo.InformationProcessorObject;
import com.google.gson.JsonObject;

public interface IInformationProcessorBusiSV {

    InformationProcessorObject process(ConfigContainerObject confContainer, OmcObj obj,
            JsonObject jsonObject);

    String getRemindNbr(String speremind, String tenantid, String ownertype, String oid);

}
