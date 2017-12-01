package com.ai.baas.prd.api.convert.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ai.baas.prd.api.convert.interfaces.IConvertSV;
import com.ai.baas.prd.api.convert.params.ConvertParams;
import com.ai.baas.prd.api.convert.params.PolicyParams;
import com.ai.baas.prd.service.business.interfaces.IConvertBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.sdk.util.StringUtil;

public class ConvertSVImpl implements IConvertSV{
    private static final Log LOG = LogFactory.getLog(ConvertSVImpl.class);
    
    @Autowired
    IConvertBusiSV iConvertBusiSV;
    @Override
    public BaseResponse increaseConvert(ConvertParams vo)throws BusinessException, SystemException {
        BaseResponse baseResponse = new BaseResponse();
        if(StringUtil.isBlank(vo.getTenantId())){
            throw new BusinessException("租户id不能为空");
        }
        if(StringUtil.isBlank(vo.getCategoryId())){
             throw new BusinessException("类目ID不能为空");
        }
//        iConvertBusiSV.buildMsg(vo);
        
        return baseResponse;
    }

    @Override
    public BaseResponse updateByPolicy(ConvertParams vo) throws BusinessException, SystemException {
        return null;
    }
    @Override
    public BaseResponse delByPolicy(ConvertParams vo) throws BusinessException, SystemException {
        return null;
    }

}
