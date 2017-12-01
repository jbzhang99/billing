package com.ai.baas.amc.service.atom.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ai.baas.amc.dao.mapper.bo.AmcChargeYyyydd;
import com.ai.baas.amc.dao.mapper.bo.AmcChargeYyyyddCriteria;
import com.ai.baas.amc.dao.mapper.bo.AmcChargeYyyyddCriteria.Criteria;
import com.ai.baas.amc.dao.mapper.factory.MapperFactory;
import com.ai.baas.amc.service.atom.interfaces.IAmcChargeYyyyddAtomSV;
import com.ai.baas.amc.service.business.impl.WriteOffSVImpl;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.util.CollectionUtil;
import com.esotericsoftware.minlog.Log;

/**
 * 账单月表
 * Date: 2016年7月5日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author LiangMeng
 */
@Component
public class AmcChargeYyyyddAtomSVImpl implements IAmcChargeYyyyddAtomSV {

    private static Logger LOG = LoggerFactory.getLogger(AmcChargeYyyyddAtomSVImpl.class);
    @Override
    public int addAmcCharge(AmcChargeYyyydd record) throws SystemException {
        return MapperFactory.getAmcChargeYyyyddMapper().insert(record);
    }

    @Override
    public int updateAmcCharge(AmcChargeYyyydd record) throws SystemException {
        LOG.info("更新账单金额为："+record.getBalance());
        AmcChargeYyyyddCriteria example = new AmcChargeYyyyddCriteria();
        example.setBillMonth(record.getBillMonth());
        Criteria criteria = example.createCriteria();
        criteria.andChargeSeqEqualTo(record.getChargeSeq());
        return MapperFactory.getAmcChargeYyyyddMapper().updateByExample(record, example);
    }

    @Override
    public List<AmcChargeYyyydd> queryChargeByAcctId(String acctId, String tenantId, String billMonth)
            throws SystemException {
        AmcChargeYyyyddCriteria sql = new AmcChargeYyyyddCriteria();
        sql.setBillMonth(billMonth);
        Criteria criteria = sql.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        criteria.andAcctIdEqualTo(acctId);
        return MapperFactory.getAmcChargeYyyyddMapper().selectByExample(sql);
    }

    @Override
    public long queryChargeSum(String tenantId, String billMonth,String acctId) throws SystemException {
        return MapperFactory.getAmcChargeYyyyddMapper().getSumTotalAmount(tenantId, billMonth,acctId);
    }

    @Override
    public AmcChargeYyyydd queryChargeBySubjectId(String acctId, String tenantId, String billMonth,
            String subjectId) throws SystemException {
        AmcChargeYyyyddCriteria sql = new AmcChargeYyyyddCriteria();
        sql.setBillMonth(billMonth);
        Criteria criteria = sql.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        criteria.andAcctIdEqualTo(acctId);
        List<AmcChargeYyyydd> list = MapperFactory.getAmcChargeYyyyddMapper().selectByExample(sql);
        if(!CollectionUtil.isEmpty(list)&&list.size()>1){
            throw new BusinessException("查询账单出错：多个同科目账单，acctId["+acctId+"],subjectId["+subjectId+"],tenantId["+tenantId+"]");
        }
        if(CollectionUtil.isEmpty(list)){
            return null;
        }else{
            return list.get(0);
        }
    }

    @Override
    public long queryTotalBill(String tenantId, String billMonth, String acctId)
            throws SystemException {
        return MapperFactory.getAmcChargeYyyyddMapper().getTotalBill(tenantId, billMonth, acctId);
    }
    
    @Override
    public long queryChargeDrSum(String tenantId, String billMonth,String acctId) throws SystemException {
        return MapperFactory.getAmcChargeYyyyddMapper().getDrSumTotalAmount(tenantId, billMonth,acctId);
    }
}
