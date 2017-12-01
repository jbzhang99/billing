package com.ai.baas.ccp.service.business.interfaces;

import com.ai.baas.ccp.topoligy.core.exception.OmcException;
import com.ai.baas.ccp.vo.ConfigContainerObject;
import com.ai.baas.ccp.vo.CreditCalProcessObject;
import com.ai.baas.ccp.vo.InformationProcessorObject;
import com.google.gson.JsonObject;

public interface ICreditCalProcessBusiSV {

    public abstract CreditCalProcessObject process(ConfigContainerObject confContainer,
            InformationProcessorObject informationProcessorObject, JsonObject data)
            throws OmcException;

}