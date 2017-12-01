package com.ai.baas.bmc.service.atom.impl;

import com.ai.baas.bmc.api.failedbills.params.FailedBillQueryVo;
import com.ai.baas.bmc.dao.interfaces.BmcFailureBillMapper;
import com.ai.baas.bmc.dao.mapper.bo.BmcFailureBillCriteria;
import com.ai.baas.bmc.dao.mapper.bo.BmcFailureBillWithBLOBs;
import com.ai.baas.bmc.service.atom.interfaces.IBmcFailureBillAtomSV;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class BmcFailureBillAtomSVImpl implements IBmcFailureBillAtomSV{
    @Resource
    private BmcFailureBillMapper billMapper;
    @Override
    public List<BmcFailureBillWithBLOBs> queryFailedBills(FailedBillQueryVo queryVo) {
        BmcFailureBillCriteria con = new BmcFailureBillCriteria();
        con.setLimitStart((queryVo.getPageNo()-1)*queryVo.getPageSize());
        con.setLimitEnd(queryVo.getPageSize());
        BmcFailureBillCriteria.Criteria criteria = con.createCriteria();
        criteria.andTenantIdEqualTo(queryVo.getTenantId());
        criteria.andServiceTypeEqualTo(queryVo.getServiceType());
        criteria.andFailCodeEqualTo(queryVo.getFailCode());
        criteria.andFailStepEqualTo(queryVo.getFailStep());
        return billMapper.selectByExampleWithBLOBs(con);
    }

    @Override
    public Integer countFailedBills(FailedBillQueryVo queryVo) {
        BmcFailureBillCriteria con = new BmcFailureBillCriteria();
        BmcFailureBillCriteria.Criteria criteria = con.createCriteria();
        criteria.andTenantIdEqualTo(queryVo.getTenantId());
        criteria.andFailCodeEqualTo(queryVo.getFailCode());
        criteria.andServiceTypeEqualTo(queryVo.getServiceType());
        criteria.andFailStepEqualTo(queryVo.getFailStep());
        return billMapper.countByExample(con);
    }

    @Override
    public BmcFailureBillWithBLOBs getFailedBillById(Long id) {
        return billMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delteFailedBillById(Long id) {
        billMapper.deleteByPrimaryKey(id);
    }
}
