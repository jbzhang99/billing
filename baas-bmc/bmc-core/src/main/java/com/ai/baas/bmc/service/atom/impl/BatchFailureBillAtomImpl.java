package com.ai.baas.bmc.service.atom.impl;

import com.ai.baas.bmc.api.failedbills.params.FailedOrderQueryVo;
import com.ai.baas.bmc.dao.interfaces.BatchFailureBillMapper;
import com.ai.baas.bmc.dao.mapper.bo.BatchFailureBill;
import com.ai.baas.bmc.dao.mapper.bo.BatchFailureBillCriteria;
import com.ai.baas.bmc.service.atom.interfaces.IBatchFailureBillAtomSV;
import com.ai.opt.sdk.util.StringUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class BatchFailureBillAtomImpl implements IBatchFailureBillAtomSV{

    @Resource
    private BatchFailureBillMapper batchFailureBillMapper;

    @Override
    public List<BatchFailureBill> queryFailedOrders(FailedOrderQueryVo queryVo) {
        BatchFailureBillCriteria con = new BatchFailureBillCriteria();
        con.setLimitStart((queryVo.getPageNo()-1)*queryVo.getPageSize());
        con.setLimitEnd(queryVo.getPageSize());
        BatchFailureBillCriteria.Criteria criteria = con.createCriteria();
        criteria.andTenantIdEqualTo(queryVo.getTenantId());
        if(!StringUtil.isBlank(queryVo.getOrderId())){
            criteria.andOrderIdEqualTo(queryVo.getOrderId());
        }
        if(!StringUtil.isBlank(queryVo.getFailCode())){
            criteria.andFailCodeEqualTo(queryVo.getFailCode());
        }
        return batchFailureBillMapper.selectByExampleWithBLOBs(con);
    }

    @Override
    public Integer countFailedOrders(FailedOrderQueryVo queryVo) {
        BatchFailureBillCriteria con = new BatchFailureBillCriteria();
        BatchFailureBillCriteria.Criteria criteria = con.createCriteria();
        criteria.andTenantIdEqualTo(queryVo.getTenantId());
        if(!StringUtil.isBlank(queryVo.getOrderId())){
            criteria.andOrderIdEqualTo(queryVo.getOrderId());
        }
        if(!StringUtil.isBlank(queryVo.getFailCode())){
            criteria.andFailCodeEqualTo(queryVo.getFailCode());
        }
        return batchFailureBillMapper.countByExample(con);
    }

    @Override
    public BatchFailureBill getFailedOrderById(Long id) {
        return batchFailureBillMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteBillById(Long id) {
        batchFailureBillMapper.deleteByPrimaryKey(id);
    }
}
