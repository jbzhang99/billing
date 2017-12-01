package com.ai.baas.bmc.service.atom.impl;

import com.ai.baas.bmc.api.detailsquery.params.DetailsQueryRequest;
import com.ai.baas.bmc.dao.interfaces.CiticDrRecordMapper;
import com.ai.baas.bmc.dao.mapper.bo.CiticDrRecord;
import com.ai.baas.bmc.dao.mapper.bo.CiticDrRecordCriteria;
import com.ai.baas.bmc.service.atom.interfaces.IDetailsQueryAtomSV;
import com.ai.opt.sdk.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IDetailsQueryAtomSVMysqlImpl implements IDetailsQueryAtomSV{

    @Autowired
    private CiticDrRecordMapper mapper;

    @Override
    public List<CiticDrRecord> getDrDetails(String tableName, DetailsQueryRequest request) {
        CiticDrRecordCriteria condition = new CiticDrRecordCriteria();
        condition.setTableName(tableName);
        CiticDrRecordCriteria.Criteria criteria = condition.createCriteria();
        criteria.andStartTimeGreaterThanOrEqualTo(request.getStartDate());
        criteria.andStartTimeLessThanOrEqualTo(request.getEndDate());
        criteria.andTenantIdEqualTo(request.getTenantId());
        if(!CollectionUtil.isEmpty(request.getAccountIds())){
            criteria.andAcctIdIn(request.getAccountIds());
        }
        if(!CollectionUtil.isEmpty(request.getUserIds())){
            criteria.andServiceIdIn(request.getUserIds());
        }
        return mapper.selectByExample(condition);
    }

    @Override
    public int getTableNum(String tableName) {
        return mapper.getTableNum(tableName);
    }
}
