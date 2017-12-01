package com.ai.baas.bmc.service.atom.interfaces;

import com.ai.baas.bmc.api.failedbills.params.FailedOrderQueryVo;
import com.ai.baas.bmc.dao.mapper.bo.BatchFailureBill;

import java.util.List;

public interface IBatchFailureBillAtomSV {
    List<BatchFailureBill> queryFailedOrders(FailedOrderQueryVo queryVo);

    Integer countFailedOrders(FailedOrderQueryVo queryVo);

    BatchFailureBill getFailedOrderById(Long id);

    void deleteBillById(Long id);
}
