package com.ai.baas.prd.service.business.interfaces;


import com.ai.baas.prd.api.convert.params.PolicyParams;
import com.ai.baas.prd.api.element.params.ConvertParams;
import com.ai.baas.prd.api.pricemaking.params.PriceMakingAddParam;
import com.ai.baas.prd.api.pricemaking.params.PriceMakingDelParam;

public interface IConvertBusiSV {

    public PriceMakingAddParam buildMsg(ConvertParams vo);
    
    public PriceMakingDelParam buildDelMsg(ConvertParams vo);

    PriceMakingDelParam buildDelMsgByPolicyId(PolicyParams vo);
}
