package com.ai.baas.amc.service.atom.interfaces;

import com.ai.baas.amc.dao.mapper.bo.AmcInvoice;

import java.util.List;

/**
 * 账单原子操作
 * Created by jackieliu on 16/3/31.
 */
public interface IAmcInvoiceAtomSV {

    /**
     * 查询用户的账单信息
     *
     * @param accId 账户id
     * @param tenantId 租户id
     * @param unsettledMonths 最后未销账月
     * @return
     */
    public List<AmcInvoice> query(String accId,String tenantId,String unsettledMonths);
    
    AmcInvoice getAmcInvoice(String tenantId, String acctId, String accDate);
}
