package com.ai.baas.bmc.service.business.interfaces;

import com.ai.baas.bmc.api.detailsquery.params.DetailVo;
import com.ai.baas.bmc.api.detailsquery.params.DetailsQueryRequest;
import com.ai.baas.bmc.api.detailsquery.params.DetailsQueryResponse;

import java.util.List;

public interface IDetailsQueryBusiness {
    List<DetailVo> getDetails(DetailsQueryRequest request);
}
