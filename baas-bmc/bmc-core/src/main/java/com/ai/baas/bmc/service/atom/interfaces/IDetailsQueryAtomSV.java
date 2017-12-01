package com.ai.baas.bmc.service.atom.interfaces;

import com.ai.baas.bmc.api.detailsquery.params.DetailsQueryRequest;
import com.ai.baas.bmc.dao.mapper.bo.CiticDrRecord;

import java.util.List;

public interface IDetailsQueryAtomSV {
    List<CiticDrRecord> getDrDetails(String tableName, DetailsQueryRequest request);

    int getTableNum(String tableName);
}
