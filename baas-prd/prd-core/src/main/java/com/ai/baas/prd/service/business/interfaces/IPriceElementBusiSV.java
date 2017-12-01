package com.ai.baas.prd.service.business.interfaces;


import com.ai.baas.prd.api.element.params.BaseSpecResponse;
import com.ai.baas.prd.api.element.params.CheckCategoryId;
import com.ai.baas.prd.api.element.params.CheckPolicyParam;
import com.ai.baas.prd.api.element.params.ConvertParams;
import com.ai.baas.prd.api.element.params.ElementDeleteVO;
import com.ai.baas.prd.api.element.params.ElementDetailRequireResult;
import com.ai.baas.prd.api.element.params.ElementDetailRequireVO;
import com.ai.baas.prd.api.element.params.ElementIncreaseVO;
import com.ai.baas.prd.api.element.params.ElementRequireResult;
import com.ai.baas.prd.api.element.params.ElementRequireVO;
import com.ai.baas.prd.api.element.params.SpecTypeQueryReq;
import com.ai.baas.prd.api.element.params.UpdateByProductVo;

public interface IPriceElementBusiSV {

    
    public ElementRequireResult queryPriceElement(ElementRequireVO vo);

    public ElementDetailRequireResult queryPriceElementDetail(ElementDetailRequireVO vo);
    
//    public ElementRequireResult searchElement(ElementRequireVO vo);
    
    public String addElement(ElementIncreaseVO vo);

    public String deleteElement(ElementDeleteVO vo);

    public String alterElement(ElementIncreaseVO vo);
    
    public String checkElement(CheckCategoryId vo);

    public void alterElementByProduct(UpdateByProductVo vo);
    
    public BaseSpecResponse querySpecTypeList(SpecTypeQueryReq req);
    
    public String alterPriceMakingByPolicy(ConvertParams vo);
    
    public boolean checkExistPolicyId(CheckPolicyParam vo);
}
