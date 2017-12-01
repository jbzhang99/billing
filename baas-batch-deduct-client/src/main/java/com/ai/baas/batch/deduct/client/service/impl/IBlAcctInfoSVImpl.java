package com.ai.baas.batch.deduct.client.service.impl;

import com.ai.baas.batch.deduct.client.dao.interfaces.BlAcctInfoMapper;
import com.ai.baas.batch.deduct.client.dao.mapper.bo.BlAcctInfo;
import com.ai.baas.batch.deduct.client.dao.mapper.bo.BlAcctInfoCriteria;
import com.ai.baas.batch.deduct.client.service.IBlAcctInfoSV;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class IBlAcctInfoSVImpl implements IBlAcctInfoSV{

    @Resource
    private BlAcctInfoMapper acctInfoMapper;


    @Override
    public List<BlAcctInfo> getAcctList(int start, int pageSize) {
        BlAcctInfoCriteria con = new BlAcctInfoCriteria();
        con.setLimitStart(start);
        con.setLimitEnd(pageSize);
        return acctInfoMapper.selectByExample(con);
    }
}
