package com.ai.baas.amc.service.business.interfaces;

import com.ai.baas.amc.api.billQuery.params.BillInput;
import com.ai.baas.amc.api.billQuery.params.BillOutput;

public interface IGetBillInfoBussinessSV {
    public BillOutput getBillInfo(BillInput record) ;
    
    

}
