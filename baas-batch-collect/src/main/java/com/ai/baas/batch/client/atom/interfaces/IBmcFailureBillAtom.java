package com.ai.baas.batch.client.atom.interfaces;

import org.mybatis.spring.SqlSessionTemplate;

import com.ai.baas.batch.client.dao.mapper.bo.BmcFailureBillWithBLOBs;

public interface IBmcFailureBillAtom {

    public void insertFailureBill(BmcFailureBillWithBLOBs bmcFailureBillWithBLOBs);
    
    public void init(SqlSessionTemplate sqlSessionTemplate2);
}
