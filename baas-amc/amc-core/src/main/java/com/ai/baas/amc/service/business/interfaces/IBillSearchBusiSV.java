package com.ai.baas.amc.service.business.interfaces;

import java.util.List;

import com.ai.baas.amc.api.bill.params.BillSearchRequest;
import com.ai.baas.amc.api.bill.params.BillTotalRequest;
import com.ai.baas.amc.dao.mapper.bo.AmcCharge;

public interface IBillSearchBusiSV {
    List<AmcCharge> searchBills(BillSearchRequest vo);

    Integer countBills(BillSearchRequest vo);
    
    Integer countBillsByMonths(BillSearchRequest vo);
    
    Long queryBillTotal(BillTotalRequest vo);
}
