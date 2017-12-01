package com.ai.baas.amc.service.atom.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.baas.amc.dao.mapper.bo.AmcInvoiceYyyydd;
import com.ai.baas.amc.dao.mapper.bo.AmcInvoiceYyyyddCriteria;
import com.ai.baas.amc.dao.mapper.bo.AmcInvoiceYyyyddCriteria.Criteria;
import com.ai.baas.amc.dao.mapper.factory.MapperFactory;
import com.ai.baas.amc.service.atom.interfaces.IAmcInvoiceYyyyddAtomSV;
import com.ai.opt.base.exception.SystemException;

/**
 * 账单月表
 * Date: 2016年7月5日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author LiangMeng
 */
@Component
public class AmcInvoiceYyyyddAtomSVImpl implements IAmcInvoiceYyyyddAtomSV {

    @Override
    public int addAmcInvoice(AmcInvoiceYyyydd record) throws SystemException {
        return MapperFactory.getAmcInvoiceYyyyddMapper().insert(record);
    }

    @Override
    public int updateAmcInvoice(AmcInvoiceYyyydd record) throws SystemException {
        AmcInvoiceYyyyddCriteria example = new AmcInvoiceYyyyddCriteria();
        example.setBillMonth(record.getBillMonth());
        Criteria criteria = example.createCriteria();
        criteria.andInvoiceSeqEqualTo(record.getInvoiceSeq());
        return MapperFactory.getAmcInvoiceYyyyddMapper().updateByExample(record, example);
    }

    @Override
    public List<AmcInvoiceYyyydd> queryInvoiceByAcctId(String acctId, String tenantId, String billMonth)
            throws SystemException {
        AmcInvoiceYyyyddCriteria sql = new AmcInvoiceYyyyddCriteria();
        sql.setBillMonth(billMonth);
        Criteria criteria = sql.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        criteria.andAcctIdEqualTo(acctId);
        return MapperFactory.getAmcInvoiceYyyyddMapper().selectByExample(sql);
    }
    
}
