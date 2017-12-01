package com.ai.baas.dst.service.atom.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.dst.api.billsdiscount.params.BillDiscountListQueryRequest;
import com.ai.baas.dst.constants.DstConstants;
import com.ai.baas.dst.dao.interfaces.DiscountInfoMapper;
import com.ai.baas.dst.dao.mapper.bo.DiscountInfo;
import com.ai.baas.dst.dao.mapper.bo.DiscountInfoCriteria;
import com.ai.baas.dst.dao.mapper.bo.DiscountInfoCriteria.Criteria;
import com.ai.baas.dst.service.atom.interfaces.IDiscountInfoAtomSV;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sdk.util.StringUtil;

/**
 * 账单优惠产品信息表原子服务实现类
 * @author wangluyang
 *
 */
@Component
public class DiscountInfoAtomSVImpl implements IDiscountInfoAtomSV {

	@Autowired
    private DiscountInfoMapper discountInfoMapper;
	
    @Override
    public int saveDiscountInfo(DiscountInfo info) throws SystemException {
        return discountInfoMapper.insertSelective(info);
    }

    @Override
    public int updateDiscountInfo(DiscountInfo info) throws SystemException {
        return discountInfoMapper.updateByPrimaryKeySelective(info);
    }

    @Override
    public DiscountInfo getDiscountInfo(String tenantId, String discountId)
            throws SystemException {
    	DiscountInfoCriteria sql = new DiscountInfoCriteria();
        Criteria criteria = sql.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        criteria.andDiscountIdEqualTo(discountId);
        List<DiscountInfo> list = discountInfoMapper.selectByExample(sql);
        if(CollectionUtil.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public PageInfo<DiscountInfo> queryDiscountInfoList(
            BillDiscountListQueryRequest request) throws SystemException {
        DiscountInfoCriteria sql = new DiscountInfoCriteria();
        Criteria criteria = sql.createCriteria();
        criteria.andTenantIdEqualTo(request.getTenantId());
        if(!StringUtil.isBlank(request.getDiscountId())) {
            criteria.andDiscountIdEqualTo(request.getDiscountId());
        }
        if(!StringUtil.isBlank(request.getDiscountName())) {
            criteria.andDiscountNameLike("%" + request.getDiscountName().trim() + "%");
        }
        if(!StringUtil.isBlank(request.getDiscountType())) {
            criteria.andCalcTypeEqualTo(request.getDiscountType());
        }
        if(!StringUtil.isBlank(request.getEffectDate())) {
            criteria.andEffectDateGreaterThanOrEqualTo(DateUtil.getTimestamp(request.getEffectDate(),
                    DateUtil.DATETIME_FORMAT));
        }
        if(!StringUtil.isBlank(request.getExpireDate())) {
            criteria.andExpireDateLessThanOrEqualTo(DateUtil.getTimestamp(request.getExpireDate(),
                    DateUtil.DATETIME_FORMAT));
        }
        criteria.andStatusNotEqualTo(DstConstants.DiscountInfo.Status.INVALID);
        PageInfo<DiscountInfo> pageInfo = new PageInfo<DiscountInfo>();
        pageInfo.setCount(discountInfoMapper.countByExample(sql));
        sql.setLimitStart(request.getPageInfo().getStartRowIndex());
        sql.setLimitEnd(request.getPageInfo().getPageSize());
        pageInfo.setResult(discountInfoMapper.selectByExample(sql));
        pageInfo.setPageNo(request.getPageInfo().getPageNo());
        pageInfo.setPageSize(request.getPageInfo().getPageSize());
        return pageInfo;
    }

    @Override
    public int deleteDiscountInfo(String tenantId, String discountId) throws SystemException {
        DiscountInfoCriteria sql = new DiscountInfoCriteria();
        Criteria criteria = sql.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        criteria.andDiscountIdEqualTo(discountId);
        return discountInfoMapper.deleteByExample(sql);
    }

	@Override
	public List<DiscountInfo> queryDiscountInfos(DiscountInfoCriteria sql) throws SystemException {
		// TODO Auto-generated method stub
		return discountInfoMapper.selectByExample(sql);
	}

}
