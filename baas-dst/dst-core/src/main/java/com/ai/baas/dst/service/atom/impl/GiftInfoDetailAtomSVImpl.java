package com.ai.baas.dst.service.atom.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.dst.dao.interfaces.GiftInfoDetailMapper;
import com.ai.baas.dst.dao.mapper.bo.GiftInfoDetail;
import com.ai.baas.dst.dao.mapper.bo.GiftInfoDetailCriteria;
import com.ai.baas.dst.dao.mapper.bo.GiftInfoDetailCriteria.Criteria;
import com.ai.baas.dst.service.atom.interfaces.IGiftInfoDetailAtomSV;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.util.CollectionUtil;

@Component
public class GiftInfoDetailAtomSVImpl implements IGiftInfoDetailAtomSV{

	@Autowired
	private GiftInfoDetailMapper giftInfoDetailMapper;
	
	@Override
	public int saveGiftInfoDetail(GiftInfoDetail info) throws SystemException {
		// TODO Auto-generated method stub
		return this.giftInfoDetailMapper.insert(info);
	}

	@Override
	public int updateGiftInfoDetail(GiftInfoDetail info) throws SystemException {
		// TODO Auto-generated method stub
		return this.giftInfoDetailMapper.updateByPrimaryKeySelective(info);
	}

	@Override
	public int deleteGiftInfoDetail(String tenantId, String discountId) throws SystemException {
		// TODO Auto-generated method stub
		return this.giftInfoDetailMapper.deleteByPrimaryKey(discountId);
	}

	@Override
	public GiftInfoDetail getGiftInfoDetail(String tenantId, String discountId) throws SystemException {
		// TODO Auto-generated method stub
		GiftInfoDetailCriteria sql = new GiftInfoDetailCriteria();
		Criteria criteria = sql.createCriteria();
		criteria.andDiscountIdEqualTo(discountId);
		criteria.andTenantIdEqualTo(tenantId);
		List<GiftInfoDetail> list = this.giftInfoDetailMapper.selectByExample(sql);
		if(CollectionUtil.isEmpty(list)){
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<GiftInfoDetail> queryGiftInfos(GiftInfoDetailCriteria sql) throws SystemException {
		// TODO Auto-generated method stub
		return this.giftInfoDetailMapper.selectByExample(sql);
	}

}
