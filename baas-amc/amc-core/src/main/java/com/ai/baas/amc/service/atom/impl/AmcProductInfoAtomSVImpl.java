package com.ai.baas.amc.service.atom.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.baas.amc.api.billdiscountquery.param.BillDiscountProductListQueryRequest;
import com.ai.baas.amc.dao.mapper.bo.AmcProductInfo;
import com.ai.baas.amc.dao.mapper.bo.AmcProductInfoCriteria;
import com.ai.baas.amc.dao.mapper.bo.AmcProductInfoCriteria.Criteria;
import com.ai.baas.amc.dao.mapper.factory.MapperFactory;
import com.ai.baas.amc.dao.mapper.interfaces.AmcProductInfoMapper;
import com.ai.baas.amc.service.atom.interfaces.IAmcProductInfoAtomSV;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;

/**
 * 账单优惠产品信息表原子服务实现类
 *
 * Date: 2016年4月6日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
@Component
public class AmcProductInfoAtomSVImpl implements IAmcProductInfoAtomSV {

    @Override
    public int saveAmcProductInfo(AmcProductInfo info) throws SystemException {
        return MapperFactory.getAmcProductInfoMapper().insertSelective(info);
    }

    @Override
    public int updateAmcProductInfo(AmcProductInfo info) throws SystemException {
        return MapperFactory.getAmcProductInfoMapper().updateByPrimaryKeySelective(info);
    }

    @Override
    public AmcProductInfo getAmcProductInfo(String tenantId, String productId)
            throws SystemException {
        AmcProductInfoCriteria sql = new AmcProductInfoCriteria();
        Criteria criteria = sql.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        criteria.andProductIdEqualTo(productId);
        List<AmcProductInfo> list = MapperFactory.getAmcProductInfoMapper().selectByExample(sql);
        if(CollectionUtil.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public PageInfo<AmcProductInfo> queryAmcProductInfoList(
            BillDiscountProductListQueryRequest request) throws SystemException {
        AmcProductInfoCriteria sql = new AmcProductInfoCriteria();
        Criteria criteria = sql.createCriteria();
        criteria.andTenantIdEqualTo(request.getTenantId());
        if(!StringUtil.isBlank(request.getProductId())) {
            criteria.andProductIdEqualTo(request.getProductId());
        }
        if(!StringUtil.isBlank(request.getProductName())) {
            criteria.andProductNameLike("%" + request.getProductName().trim() + "%");
        }
        if(!StringUtil.isBlank(request.getDiscountType())) {
            criteria.andCalcTypeEqualTo(request.getDiscountType());
        }
        if(request.getEffectDate() != null) {
            criteria.andEffectDateGreaterThanOrEqualTo(request.getEffectDate());
        }
        if(request.getExpireDate() != null) {
            criteria.andExpireDateLessThanOrEqualTo(request.getExpireDate());
        }
        
        PageInfo<AmcProductInfo> pageInfo = new PageInfo<AmcProductInfo>();
        AmcProductInfoMapper mapper = MapperFactory.getAmcProductInfoMapper();
        pageInfo.setCount(mapper.countByExample(sql));
        sql.setLimitStart(request.getPageInfo().getStartRowIndex());
        sql.setLimitEnd(request.getPageInfo().getPageSize());
        pageInfo.setResult(mapper.selectByExample(sql));
        pageInfo.setPageNo(request.getPageInfo().getPageNo());
        pageInfo.setPageSize(request.getPageInfo().getPageSize());
        return pageInfo;
    }

    @Override
    public int deleteAmcProductInfo(String tenantId, String productId) throws SystemException {
        AmcProductInfoCriteria sql = new AmcProductInfoCriteria();
        Criteria criteria = sql.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        criteria.andProductIdEqualTo(productId);
        return MapperFactory.getAmcProductInfoMapper().deleteByExample(sql);
    }

}
