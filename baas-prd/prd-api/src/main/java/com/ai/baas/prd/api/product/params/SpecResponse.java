package com.ai.baas.prd.api.product.params;

import com.ai.opt.base.vo.BaseResponse;

import java.util.List;

public class SpecResponse extends BaseResponse{
    List<SpecVo> specVos;

    public List<SpecVo> getSpecVos() {
        return specVos;
    }

    public void setSpecVos(List<SpecVo> specVos) {
        this.specVos = specVos;
    }
}
