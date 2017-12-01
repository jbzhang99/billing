package com.ai.baas.amc.service.atom.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.baas.amc.dao.mapper.bo.AmcProductDetail;
import com.ai.baas.amc.dao.mapper.bo.AmcProductDetailCriteria;
import com.ai.baas.amc.dao.mapper.bo.AmcProductDetailCriteria.Criteria;
import com.ai.baas.amc.dao.mapper.factory.MapperFactory;
import com.ai.baas.amc.service.atom.interfaces.IAmcProductDetailAtomSV;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;


/**
 * 账单优惠产品明细表原子操作实现类
 *
 * Date: 2016年4月6日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
@Component
public class AmcProductDetailAtomSVImpl implements IAmcProductDetailAtomSV {

    @Override
    public int saveAmcProductDetail(AmcProductDetail amcProductDetail) throws SystemException {
        return MapperFactory.getAmcProductDetailMapper().insertSelective(amcProductDetail);
    }

    @Override
    public int updateAmcProductDetail(AmcProductDetail amcProductDetail) throws SystemException {
        String tenantId = amcProductDetail.getTenantId();
        String productId = amcProductDetail.getProductId();
        AmcProductDetailCriteria sql = new AmcProductDetailCriteria();
        Criteria criteria = sql.createCriteria();
        if(!StringUtil.isBlank(tenantId)) {
            criteria.andTenantIdEqualTo(tenantId);
        }
        if(!StringUtil.isBlank(productId)) {
            criteria.andProductIdEqualTo(productId);
        }
        
        return MapperFactory.getAmcProductDetailMapper().updateByExampleSelective(amcProductDetail, sql);
    }

    @Override
    public AmcProductDetail getAmcProductDetail(String tenantId, String productId)
            throws SystemException {
        AmcProductDetailCriteria sql = new AmcProductDetailCriteria();
        Criteria criteria = sql.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        criteria.andProductIdEqualTo(productId);
        List<AmcProductDetail> list = MapperFactory.getAmcProductDetailMapper().selectByExample(sql);
        if(CollectionUtil.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public int deleteAmcProductDetail(String tenantId, String productId) throws SystemException {
        AmcProductDetailCriteria sql = new AmcProductDetailCriteria();
        Criteria criteria = sql.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        criteria.andProductIdEqualTo(productId);
        return MapperFactory.getAmcProductDetailMapper().deleteByExample(sql);
    }

}
