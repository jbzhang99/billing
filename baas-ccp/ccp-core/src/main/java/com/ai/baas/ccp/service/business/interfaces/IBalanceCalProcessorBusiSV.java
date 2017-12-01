package com.ai.baas.ccp.service.business.interfaces;

import com.ai.baas.ccp.vo.BalanceCalProcessorObject;
import com.ai.baas.ccp.vo.ConfigContainerObject;
import com.ai.baas.ccp.vo.InformationProcessorObject;
import com.google.gson.JsonObject;

public interface IBalanceCalProcessorBusiSV {

    BalanceCalProcessorObject process(ConfigContainerObject confContainer,
            InformationProcessorObject informationProcessorObject, JsonObject jsonObject);

}
