package com.ai.baas.bmc.service.business.interfaces;

import com.ai.baas.bmc.api.failedbills.params.FailedOrderQueryVo;
import com.ai.baas.bmc.api.failedbills.params.FailedOrderVo;

import java.util.List;

public interface IOrderFailedBillBusiSV {
    Integer countFailedOrders(FailedOrderQueryVo queryVo);

    List<FailedOrderVo> queryFailedOrders(FailedOrderQueryVo queryVo);

    FailedOrderVo getFailedOrderById(Long id);

    void resendOrder(FailedOrderVo orderVo);
}
