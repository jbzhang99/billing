package com.ai.baas.amc.service.atom.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.baas.amc.dao.mapper.bo.AmcProductExt;
import com.ai.baas.amc.dao.mapper.bo.AmcProductExtCriteria;
import com.ai.baas.amc.dao.mapper.bo.AmcProductExtCriteria.Criteria;
import com.ai.baas.amc.dao.mapper.factory.MapperFactory;
import com.ai.baas.amc.service.atom.interfaces.IAmcProductExtAtomSV;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.util.StringUtil;

/**
 * 账单优惠产品扩展表原子操作实现类
 *
 * Date: 2016年4月6日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
@Component
public class AmcProductExtAtomSVImpl implements IAmcProductExtAtomSV {

    @Override
    public int saveAmcProductExt(AmcProductExt record) throws SystemException {
        return MapperFactory.getAmcProductExtMapper().insertSelective(record);
    }

    @Override
    public int deleteAmcProductExt(String tenantId, String productId, String extName) throws SystemException {
        AmcProductExtCriteria sql = new AmcProductExtCriteria();
        Criteria criteria = sql.createCriteria();
        if(!StringUtil.isBlank(tenantId)) {
            criteria.andTenantIdEqualTo(tenantId);
        }
        if(!StringUtil.isBlank(productId)) {
            criteria.andProductIdEqualTo(productId);
        }
        if(!StringUtil.isBlank(extName)) {
            criteria.andExtNameEqualTo(extName);
        }
        return MapperFactory.getAmcProductExtMapper().deleteByExample(sql);
    }

    @Override
    public List<AmcProductExt> queryAmcProductExt(String tenantId, String productId, String extName)
            throws SystemException {
        AmcProductExtCriteria sql = new AmcProductExtCriteria();
        Criteria criteria = sql.createCriteria();
        if(!StringUtil.isBlank(tenantId)) {
            criteria.andTenantIdEqualTo(tenantId);
        }
        if(!StringUtil.isBlank(productId)) {
            criteria.andProductIdEqualTo(productId);
        }
        if(!StringUtil.isBlank(extName)) {
            criteria.andExtNameEqualTo(extName);
        }
        return MapperFactory.getAmcProductExtMapper().selectByExample(sql);
    }

    @Override
    public int updateAmcProductExt(AmcProductExt record) throws SystemException {
        AmcProductExtCriteria sql = new AmcProductExtCriteria();
        Criteria criteria = sql.createCriteria();
        String tenantId = record.getTenantId();
        String productId = record.getProductId();
        String extName = record.getExtName();
        if(!StringUtil.isBlank(tenantId)) {
            criteria.andTenantIdEqualTo(tenantId);
        }
        if(!StringUtil.isBlank(productId)) {
            criteria.andProductIdEqualTo(productId);
        }
        if(!StringUtil.isBlank(extName)) {
            criteria.andExtNameEqualTo(extName);
        }
        return MapperFactory.getAmcProductExtMapper().updateByExampleSelective(record, sql);
    }

}
