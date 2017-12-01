package com.ai.baas.dst.service.atom.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.dst.api.coupon.params.ChannelCodeReq;
import com.ai.baas.dst.api.coupon.params.CouponCodeReq;
import com.ai.baas.dst.api.coupon.params.ExportCodeReq;
import com.ai.baas.dst.constants.DstConstants;
import com.ai.baas.dst.dao.interfaces.DstCouponCodeMapper;
import com.ai.baas.dst.dao.mapper.bo.CouponCode;
import com.ai.baas.dst.dao.mapper.bo.DstCouponCode;
import com.ai.baas.dst.dao.mapper.bo.DstCouponCodeCriteria;
import com.ai.baas.dst.dao.mapper.bo.DstCouponCodeCriteria.Criteria;
import com.ai.baas.dst.dao.mapper.bo.OPCouponCode;
import com.ai.baas.dst.dao.mapper.bo.QDCouponCode;
import com.ai.baas.dst.dao.mapper.bo.QDCouponCodes;
import com.ai.baas.dst.service.atom.interfaces.ICouponCodeAtomSV;
import com.ai.opt.sdk.util.StringUtil;

@Component
public class CouponCodeAtomSVImpl implements ICouponCodeAtomSV {
	@Autowired
	private DstCouponCodeMapper dstCouponCodeMapper;

	@Override
	public int addCouponCode(DstCouponCode code) {

		return dstCouponCodeMapper.insertSelective(code);
	}

	@Override
	public List<DstCouponCode> getCouponCodeList(String tenantId, String couponId) {
		DstCouponCodeCriteria sql = new DstCouponCodeCriteria();
		Criteria criteria = sql.createCriteria();
		if (!StringUtil.isBlank(tenantId)) {
			criteria.andTenantIdEqualTo(tenantId);
		}
		if (!StringUtil.isBlank(couponId)) {
			criteria.andCouponIdEqualTo(couponId);
		}
		return dstCouponCodeMapper.selectByExample(sql);
	}

	@Override
	public int delCouponCode(String tenantId, String couponId) {
		DstCouponCodeCriteria sql = new DstCouponCodeCriteria();
		Criteria criteria = sql.createCriteria();
		if (!StringUtil.isBlank(tenantId)) {
			criteria.andTenantIdEqualTo(tenantId);
		}
		if (!StringUtil.isBlank(couponId)) {
			criteria.andCouponIdEqualTo(couponId);
		}
		DstCouponCode code = new DstCouponCode();
		code.setCouponStatus(DstConstants.CouponStatus.DEL);
		return dstCouponCodeMapper.updateByExampleSelective(code, sql);
	}

	@Override
	public int changeCouponCodeStatus(String tenantId, String couponId, String couponCode, String status) {
		DstCouponCodeCriteria sql = new DstCouponCodeCriteria();
		Criteria criteria = sql.createCriteria();

		criteria.andTenantIdEqualTo(tenantId);

		criteria.andCouponIdEqualTo(couponId);

		if (!StringUtil.isBlank(couponCode)) {
			criteria.andCouponCodeEqualTo(couponCode);
		}

		DstCouponCode code = new DstCouponCode();
		code.setCouponStatus(status);
		return dstCouponCodeMapper.updateByExampleSelective(code, sql);
	}

	@Override
	public DstCouponCode getSingleStatus(String tenantId, String couponId, String couponCode) {
		DstCouponCodeCriteria sql = new DstCouponCodeCriteria();
		Criteria criteria = sql.createCriteria();

		criteria.andTenantIdEqualTo(tenantId);

		criteria.andCouponCodeEqualTo(couponCode);

		criteria.andCouponIdEqualTo(couponId);

		return dstCouponCodeMapper.selectByExample(sql).get(0);
	}

	@Override
	public int getEffCount(String tenantId, String couponId) {
		DstCouponCodeCriteria sql = new DstCouponCodeCriteria();
		Criteria criteria = sql.createCriteria();

		criteria.andTenantIdEqualTo(tenantId);

		criteria.andCouponIdEqualTo(couponId);

		List<String> list = new ArrayList<String>();

		list.add(DstConstants.CouponStatus.EFFECTIVE);

		list.add(DstConstants.CouponStatus.GOT);

		criteria.andCouponStatusIn(list);

		return dstCouponCodeMapper.countByExample(sql);
	}

	@Override
	public int getUsedOrGotCount(String tenantId, String couponId) {
		DstCouponCodeCriteria sql = new DstCouponCodeCriteria();
		Criteria criteria = sql.createCriteria();

		criteria.andTenantIdEqualTo(tenantId);

		criteria.andCouponIdEqualTo(couponId);

		List<String> list = new ArrayList<String>();

		list.add(DstConstants.CouponStatus.USED);

		list.add(DstConstants.CouponStatus.GOT);

		criteria.andCouponStatusIn(list);

		return dstCouponCodeMapper.countByExample(sql);
	}

	@Override
	public List<DstCouponCode> getCanDisCoupon(String tenantId, String couponId) {
		DstCouponCodeCriteria sql = new DstCouponCodeCriteria();
		Criteria criteria = sql.createCriteria();
		if (!StringUtil.isBlank(tenantId)) {
			criteria.andTenantIdEqualTo(tenantId);
		}
		if (!StringUtil.isBlank(couponId)) {
			criteria.andCouponIdEqualTo(couponId);
		}
		criteria.andCouponStatusEqualTo(DstConstants.CouponStatus.EFFECTIVE);
		return dstCouponCodeMapper.selectByExample(sql);
	}

	@Override
	public List<DstCouponCode> getOPCouponCode(CouponCodeReq req) {
		DstCouponCodeCriteria sql = new DstCouponCodeCriteria();
		Criteria criteria = sql.createCriteria();
		criteria.andTenantIdEqualTo(req.getTenantId());
		criteria.andCouponIdEqualTo(req.getCouponId());
		if (!StringUtil.isBlank(req.getChannelId())) {
			criteria.andChannelIdEqualTo(req.getChannelId());
		}
		if (!StringUtil.isBlank(req.getChannnelAccount())) {
			criteria.andChannelAccountEqualTo(req.getChannnelAccount());
		}
		if (req.getApplyTime() != null) {
			criteria.andCreateTimeEqualTo(req.getApplyTime());
		}
		sql.setLimitStart((req.getPageNO() - 1) * req.getPageSize());
		sql.setLimitEnd(req.getPageSize());
		return dstCouponCodeMapper.selectByExample(sql);
	}

	@Override
	public List<CouponCode> selectAggreByExample(OPCouponCode example) {
		return dstCouponCodeMapper.selectAggreByExample(example);
	}

	@Override
	public int selectAggreCount(OPCouponCode example) {
		return dstCouponCodeMapper.countByAgreeExample(example);
	}

	@Override
	public List<DstCouponCode> getChannelCodes(ChannelCodeReq req) {
		DstCouponCodeCriteria sql = new DstCouponCodeCriteria();
		Criteria criteria = sql.createCriteria();
		criteria.andTenantIdEqualTo(req.getTenantId());
		criteria.andCouponIdEqualTo(req.getCouponId());
		criteria.andChannelIdEqualTo(req.getChannelId());
		if (!StringUtils.isEmpty(req.getCouponCode())) {
			criteria.andCouponCodeEqualTo(req.getCouponCode());
		}
		if (!StringUtils.isEmpty(req.getStatus())) {
			criteria.andCouponStatusEqualTo(req.getStatus());
		}
		if (!StringUtils.isEmpty(req.getServiceId())) {
			criteria.andServiceIdEqualTo(req.getServiceId());
		}
		
		sql.setLimitStart((req.getPageNO() - 1) * req.getPageSize());
		sql.setLimitEnd(req.getPageSize());
		return dstCouponCodeMapper.selectByExample(sql);
	}

	@Override
	public int getChannelCodesCount(ChannelCodeReq req) {
		DstCouponCodeCriteria sql = new DstCouponCodeCriteria();
		Criteria criteria = sql.createCriteria();
		criteria.andTenantIdEqualTo(req.getTenantId());
		criteria.andCouponIdEqualTo(req.getCouponId());
		criteria.andChannelIdEqualTo(req.getChannelId());
		if (!StringUtils.isEmpty(req.getCouponCode())) {
			criteria.andCouponCodeEqualTo(req.getCouponCode());
		}
		if (!StringUtils.isEmpty(req.getStatus())) {
			criteria.andCouponStatusEqualTo(req.getStatus());
		}
		if (!StringUtils.isEmpty(req.getServiceId())) {
			criteria.andServiceIdEqualTo(req.getServiceId());
		}
		return dstCouponCodeMapper.countByExample(sql);
	}

	@Override
	public int changeCodeStatusAndServiceId(String tenantId, String couponCode, String status,String serviceId,String orderId) {
		DstCouponCodeCriteria sql = new DstCouponCodeCriteria();
		Criteria criteria = sql.createCriteria();

		criteria.andTenantIdEqualTo(tenantId);
		
		criteria.andCouponCodeEqualTo(couponCode);

		DstCouponCode code = new DstCouponCode();
		code.setCouponStatus(status);
		code.setServiceId(serviceId);
		code.setOrderId(orderId);
		return dstCouponCodeMapper.updateByExampleSelective(code, sql);
		
	}

	@Override
	public DstCouponCode getSingleCodeForCheck(String tenantId, String couponCode) {
		DstCouponCodeCriteria sql = new DstCouponCodeCriteria();
		Criteria criteria = sql.createCriteria();

		criteria.andTenantIdEqualTo(tenantId);

		criteria.andCouponCodeEqualTo(couponCode);

		return dstCouponCodeMapper.selectByExample(sql).get(0);
	}
	
		@Override
	public List<QDCouponCodes> getQDExistsCodes(QDCouponCode req) {
		return dstCouponCodeMapper.getExistsCouponCode(req);
	}

	@Override
	public int coutQDExistsCodes(QDCouponCode example) {
		return dstCouponCodeMapper.getExistsCouponCodeCount(example);
	}
	
	@Override
	public List<CouponCode> selectChannelInfoByExample(OPCouponCode example) {
		return dstCouponCodeMapper.selectChannelInfoByExample(example);
	}

	@Override
	public List<DstCouponCode> getExpChannelCodes(ExportCodeReq req) {
		DstCouponCodeCriteria sql = new DstCouponCodeCriteria();
		Criteria criteria = sql.createCriteria();
		criteria.andTenantIdEqualTo(req.getTenantId());
		criteria.andCouponIdEqualTo(req.getCouponId());
		criteria.andChannelIdEqualTo(req.getChannelId());
		if (!StringUtils.isEmpty(req.getCouponCode())) {
			criteria.andCouponCodeEqualTo(req.getCouponCode());
		}
		if (!StringUtils.isEmpty(req.getStatus())) {
			criteria.andCouponStatusEqualTo(req.getStatus());
		}
		if (!StringUtils.isEmpty(req.getServiceId())) {
			criteria.andServiceIdEqualTo(req.getServiceId());
		}
		
		return dstCouponCodeMapper.selectByExample(sql);	
		}

	@Override
	public List<QDCouponCodes> getQDExistsCodesList(QDCouponCode code) {
		return dstCouponCodeMapper.getExistsCouponCodeList(code);
	}

}
