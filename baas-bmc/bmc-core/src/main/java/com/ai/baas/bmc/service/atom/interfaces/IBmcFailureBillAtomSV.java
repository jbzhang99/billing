package com.ai.baas.bmc.service.atom.interfaces;

import com.ai.baas.bmc.api.failedbills.params.FailedBillQueryVo;
import com.ai.baas.bmc.dao.mapper.bo.BmcFailureBillWithBLOBs;

import java.util.List;

public interface IBmcFailureBillAtomSV {
    List<BmcFailureBillWithBLOBs> queryFailedBills(FailedBillQueryVo queryVo);

    Integer countFailedBills(FailedBillQueryVo queryVo);

    BmcFailureBillWithBLOBs getFailedBillById(Long id);

    void delteFailedBillById(Long id);
}
