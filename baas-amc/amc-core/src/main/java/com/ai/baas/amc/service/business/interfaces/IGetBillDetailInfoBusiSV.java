package com.ai.baas.amc.service.business.interfaces;

import com.ai.baas.amc.api.billQuery.params.BillDetailInput;
import com.ai.baas.amc.api.billQuery.params.BillDetailOutput;

public interface IGetBillDetailInfoBusiSV {

    public BillDetailOutput getBillDetail(BillDetailInput record);
}
