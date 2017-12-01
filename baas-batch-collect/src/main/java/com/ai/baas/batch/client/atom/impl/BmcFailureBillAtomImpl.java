package com.ai.baas.batch.client.atom.impl;

import org.mybatis.spring.SqlSessionTemplate;

import com.ai.baas.batch.client.atom.interfaces.IBmcFailureBillAtom;
import com.ai.baas.batch.client.dao.interfaces.BmcFailureBillMapper;
import com.ai.baas.batch.client.dao.mapper.bo.BmcFailureBillWithBLOBs;


public class BmcFailureBillAtomImpl {

    private SqlSessionTemplate sqlSessionTemplate;
    
    private BmcFailureBillMapper bmcFailureBillMapper;
    
    
    public void init(SqlSessionTemplate sqlSessionTemplate2) {
        this.sqlSessionTemplate = sqlSessionTemplate2;
    }
    
    public void insertFailureBill(BmcFailureBillWithBLOBs bmcFailureBillWithBLOBs) {
        bmcFailureBillMapper = sqlSessionTemplate.getMapper(BmcFailureBillMapper.class);      
        bmcFailureBillMapper.insertSelective(bmcFailureBillWithBLOBs);      
    }



    
    
}
