package com.ai.baas.amc.service.atom.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.baas.amc.dao.mapper.bo.AmcInvoice;
import com.ai.baas.amc.dao.mapper.bo.AmcInvoiceCriteria;
import com.ai.baas.amc.dao.mapper.bo.AmcInvoiceCriteria.Criteria;
import com.ai.baas.amc.dao.mapper.factory.MapperFactory;
import com.ai.baas.amc.service.atom.interfaces.IAmcInvoiceAtomSV;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.util.CollectionUtil;

/**
 * Created by jackieliu on 16/3/31.
 */
@Component
public class AmcInvoiceAtomSVImpl implements IAmcInvoiceAtomSV {
    @Override
    public List<AmcInvoice> query(String accId,String tenantId, String unsettledMonths) {
        AmcInvoiceCriteria example = new AmcInvoiceCriteria();
        example.setTableMonth(unsettledMonths);
        example.createCriteria()
                .andAcctIdEqualTo(accId)
                .andTenantIdEqualTo(tenantId);
        try{
            List<AmcInvoice> amcInvoiceList = MapperFactory.getAmcInvoiceMapper().selectByExample(example);
            /*for (int i=0;amcInvoiceList!=null && i<amcInvoiceList.size();i++) {
                AmcInvoice amcInvoice = amcInvoiceList.get(i);
                String adjust = (amcInvoice.getAdjust() == null) ? "0.0":amcInvoice.getAdjust().toString();
                String balance = (amcInvoice.getBalance() == null) ? "0.0":amcInvoice.getBalance().toString();
                String disc = (amcInvoice.getDisc() == null) ? "0.0":amcInvoice.getDisc().toString();
                String total = (amcInvoice.getTotal() == null) ? "0.0":amcInvoice.getTotal().toString();
                amcInvoice.setAdjust(CalUtil.BigDecimalFromDoubleStr(adjust, FeeSource.FROMCHARGE));
                amcInvoice.setBalance(CalUtil.BigDecimalFromDoubleStr(balance, FeeSource.FROMCHARGE));
                amcInvoice.setDisc(CalUtil.BigDecimalFromDoubleStr(disc, FeeSource.FROMCHARGE));
                amcInvoice.setTotal(CalUtil.BigDecimalFromDoubleStr(total, FeeSource.FROMCHARGE));
            }*/
            return amcInvoiceList;
        }catch (Exception e){
            throw new SystemException("查询账单信息异常",e);
        }
    }

    @Override
    public AmcInvoice getAmcInvoice(String tenantId, String acctId, String accDate) {
        AmcInvoiceCriteria sql = new AmcInvoiceCriteria();
        sql.setTableMonth(accDate);
        Criteria criteria = sql.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        criteria.andAcctIdEqualTo(acctId);
        List<AmcInvoice> invoiceList = MapperFactory.getAmcInvoiceMapper().selectByExample(sql);
        if(CollectionUtil.isEmpty(invoiceList)) {
            return null;
        }
        
        return invoiceList.get(0);
    }
}
