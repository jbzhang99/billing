package com.ai.baas.dst.service.atom.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.dst.dao.interfaces.DiscountExtMapper;
import com.ai.baas.dst.dao.mapper.bo.DiscountExt;
import com.ai.baas.dst.dao.mapper.bo.DiscountExtCriteria;
import com.ai.baas.dst.dao.mapper.bo.DiscountExtCriteria.Criteria;
import com.ai.baas.dst.dao.mapper.bo.DiscountInfoCriteria;
import com.ai.baas.dst.service.atom.interfaces.IDiscountExtAtomSV;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.util.StringUtil;

/**
 * 账单优惠产品扩展表原子操作实现类
 * @author wangluyang
 *
 */
@Component
public class DiscountExtAtomSVImpl implements IDiscountExtAtomSV {

	@Autowired
    private DiscountExtMapper discountExtMapper;
	
    @Override
    public int saveDiscountExt(DiscountExt record) throws SystemException {
        return discountExtMapper.insertSelective(record);
    }

    @Override
    public int deleteDiscountExt(String tenantId, String discountId, String extName) throws SystemException {
    	DiscountExtCriteria sql = new DiscountExtCriteria();
    	Criteria criteria = sql.createCriteria();
        if(!StringUtil.isBlank(tenantId)) {
            criteria.andTenantIdEqualTo(tenantId);
        }
        if(!StringUtil.isBlank(discountId)) {
            criteria.andDiscountIdEqualTo(discountId);
        }
        if(!StringUtil.isBlank(extName)) {
            criteria.andExtNameEqualTo(extName);
        }
        return discountExtMapper.deleteByExample(sql);
    }

    @Override
    public List<DiscountExt> queryDiscountExt(String tenantId, String discountId, String extName)
            throws SystemException {
    	DiscountExtCriteria sql = new DiscountExtCriteria();
        Criteria criteria = sql.createCriteria();
        if(!StringUtil.isBlank(tenantId)) {
            criteria.andTenantIdEqualTo(tenantId);
        }
        if(!StringUtil.isBlank(discountId)) {
            criteria.andDiscountIdEqualTo(discountId);
        }
        if(!StringUtil.isBlank(extName)) {
            criteria.andExtNameEqualTo(extName);
        }
        return discountExtMapper.selectByExample(sql);
    }

    @Override
    public int updateDiscountExt(DiscountExt record) throws SystemException {
    	DiscountExtCriteria sql = new DiscountExtCriteria();
        Criteria criteria = sql.createCriteria();
        String tenantId = record.getTenantId();
        String discountId = record.getDiscountId();
        String extName = record.getExtName();
        if(!StringUtil.isBlank(tenantId)) {
            criteria.andTenantIdEqualTo(tenantId);
        }
        if(!StringUtil.isBlank(discountId)) {
            criteria.andDiscountIdEqualTo(discountId);
        }
        if(!StringUtil.isBlank(extName)) {
            criteria.andExtNameEqualTo(extName);
        }
        return discountExtMapper.updateByExampleSelective(record, sql);
    }

	@Override
	public List<DiscountExt> queryDiscountExts(DiscountExtCriteria sql) throws SystemException {
		// TODO Auto-generated method stub
		return discountExtMapper.selectByExample(sql);
	}

}
