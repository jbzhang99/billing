package com.ai.baas.bmc.api.detailsquery.params;

import com.ai.opt.base.vo.BaseResponse;

import java.util.List;

public class DetailsQueryResponse extends BaseResponse{
    private List<DetailVo> details;

    public List<DetailVo> getDetails() {
        return details;
    }

    public void setDetails(List<DetailVo> details) {
        this.details = details;
    }
}
