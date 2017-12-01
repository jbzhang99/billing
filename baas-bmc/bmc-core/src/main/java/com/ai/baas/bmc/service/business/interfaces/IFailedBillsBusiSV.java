package com.ai.baas.bmc.service.business.interfaces;

import com.ai.baas.bmc.api.failedbills.params.BillIdList;
import com.ai.baas.bmc.api.failedbills.params.FailedBillQueryVo;
import com.ai.baas.bmc.api.failedbills.params.FailedBillVo;

import java.io.IOException;
import java.util.List;

public interface IFailedBillsBusiSV {
    List<FailedBillVo> queryFailedBills(FailedBillQueryVo queryVo);

    Integer countFailedBills(FailedBillQueryVo queryVo);

    FailedBillVo getFailedBillById(Long id);

    void resendBill(FailedBillVo billVo) throws IOException;

    void batchResendBill(BillIdList idList) throws IOException;

    void batchDeleteBill(BillIdList idList);
}
