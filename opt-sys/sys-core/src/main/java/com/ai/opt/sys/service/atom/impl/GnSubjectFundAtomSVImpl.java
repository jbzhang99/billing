package com.ai.opt.sys.service.atom.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sys.dao.mapper.bo.GnSubjectFund;
import com.ai.opt.sys.dao.mapper.bo.GnSubjectFundCriteria;
import com.ai.opt.sys.dao.mapper.bo.GnSubjectFundKey;
import com.ai.opt.sys.dao.mapper.factory.MapperFactory;
import com.ai.opt.sys.service.atom.interfaces.IGnSubjectFundAtomSV;

@Component
public class GnSubjectFundAtomSVImpl implements IGnSubjectFundAtomSV {

    @Override
    public GnSubjectFund queryGnSubjectFund(String tenantId,String industryCode,long subjectId) {
        GnSubjectFundKey key = new GnSubjectFundKey();
        key.setIndustryCode(industryCode);
        key.setSubjectId(subjectId);
        key.setTenantId(tenantId);
        return MapperFactory.getGnSubjectFundMapper().selectByPrimaryKey(key);
    }

    @Override
    public List<GnSubjectFund> queryGnSubjectFund() {
        return MapperFactory.getGnSubjectFundMapper().selectByExample(new GnSubjectFundCriteria());
    }

    @Override
    public int addSubjectFund(GnSubjectFund vo) throws SystemException {
        return MapperFactory.getGnSubjectFundMapper().insertSelective(vo);
    }

    @Override
    public int delSubjectFund(GnSubjectFundKey key) throws SystemException {
        return MapperFactory.getGnSubjectFundMapper().deleteByPrimaryKey(key);
    }

    @Override
    public int modSubjectFund(GnSubjectFund vo, GnSubjectFundKey key) throws SystemException {
        GnSubjectFundCriteria example = new GnSubjectFundCriteria();
        example.createCriteria().andTenantIdEqualTo(key.getTenantId())
                .andIndustryCodeEqualTo(key.getIndustryCode())
                .andSubjectIdEqualTo(key.getSubjectId());
        return MapperFactory.getGnSubjectFundMapper().updateByExampleSelective(vo, example);
    }
}
