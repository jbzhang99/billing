package com.ai.baas.amc.service.business.impl;

import com.ai.baas.amc.api.bill.params.BillSearchRequest;
import com.ai.baas.amc.api.bill.params.BillTotalRequest;
import com.ai.baas.amc.dao.mapper.bo.AmcCharge;
import com.ai.baas.amc.service.atom.interfaces.IAmcChargeAtomSV;
import com.ai.baas.amc.service.atom.interfaces.IAmcChargeYyyyddAtomSV;
import com.ai.baas.amc.service.business.interfaces.IBillSearchBusiSV;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BillSearchBusiSVImpl implements IBillSearchBusiSV {

    @Autowired
    private IAmcChargeAtomSV amcChargeAtomSV;
    @Autowired
    private IAmcChargeYyyyddAtomSV amcChargeYyyyddAtomSV;

    @Override
    public List<AmcCharge> searchBills(BillSearchRequest vo) {
        return amcChargeAtomSV.searchBillPages(vo);
    }

    @Override
    public Integer countBills(BillSearchRequest vo) {
        return amcChargeAtomSV.countBills(vo);
    }

    @Override
    public Long queryBillTotal(BillTotalRequest vo) {
        return amcChargeYyyyddAtomSV.queryTotalBill(vo.getTenantId(), vo.getBillMonth(), vo.getAcctId());
    }

	@Override
	public Integer countBillsByMonths(BillSearchRequest vo) {
		return amcChargeAtomSV.countBillsByMonths(vo);
	}
}
