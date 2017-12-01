package com.ai.baas.dst.service.atom.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.dst.api.coupon.params.ChannelCodeReq;
import com.ai.baas.dst.api.coupon.params.CouponConditionReq;
import com.ai.baas.dst.api.coupon.params.CouponReq;
import com.ai.baas.dst.api.coupon.params.MyOwnCodeReq;
import com.ai.baas.dst.api.coupon.params.OPCouponCodeReq;
import com.ai.baas.dst.api.coupon.params.OPCouponQueryReq;
import com.ai.baas.dst.api.coupon.params.QDCouponQueryReq;
import com.ai.baas.dst.constants.DstConstants;
import com.ai.baas.dst.constants.DstConstants.CouponRule;
import com.ai.baas.dst.constants.DstConstants.CouponStatus;
import com.ai.baas.dst.dao.interfaces.DstCouponCodeMapper;
import com.ai.baas.dst.dao.interfaces.DstCouponInfoMapper;
import com.ai.baas.dst.dao.mapper.bo.DstCouponAuditInfo;
import com.ai.baas.dst.dao.mapper.bo.DstCouponCode;
import com.ai.baas.dst.dao.mapper.bo.DstCouponInfo;
import com.ai.baas.dst.dao.mapper.bo.DstCouponInfoAndCode;
import com.ai.baas.dst.dao.mapper.bo.DstCouponInfoCriteria;
import com.ai.baas.dst.dao.mapper.bo.DstCouponInfoCriteria.Criteria;
import com.ai.baas.dst.dao.mapper.bo.DstExportCouponAndCode;
import com.ai.baas.dst.dao.mapper.bo.DstMyOwnCouponCodeInfo;
import com.ai.baas.dst.service.atom.interfaces.ICouponInfoAtomSV;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sdk.util.StringUtil;

@Component
public class CouponInfoAtomSVImpl implements ICouponInfoAtomSV {
	@Autowired
	private DstCouponInfoMapper dstCouponInfoMapper;
	@Autowired
	private DstCouponCodeMapper dstCouponCodeMapper;

	@Override
	public int addCouponInfo(DstCouponInfo info) {

		return dstCouponInfoMapper.insertSelective(info);
	}

	@Override
	public List<DstCouponInfo> getCouponList(CouponReq req) {
		DstCouponInfoCriteria sql = new DstCouponInfoCriteria();
		Criteria criteria = sql.createCriteria();
		if (!StringUtil.isBlank(req.getTenantId())) {
			criteria.andTenantIdEqualTo(req.getTenantId());
		}
		if (!StringUtil.isBlank(req.getCouponId())) {
			criteria.andCouponIdEqualTo(req.getCouponId());
		}
		if (!StringUtil.isBlank(req.getCouponName())) {
			criteria.andCouponNameLike("%" + req.getCouponName() + "%");
		}
		/*
		 * if(!StringUtil.isBlank(req.getCouponConType())){
		 * criteria.andCouponConTypeEqualTo(req.getCouponConType()); }
		 */

		if (req.getStartTime() != null && req.getEndTime() == null) {
			criteria.andCreateTimeGreaterThanOrEqualTo(req.getStartTime());
		}
		if (req.getStartTime() == null && req.getEndTime() != null) {
			criteria.andCreateTimeLessThanOrEqualTo(req.getEndTime());
		}
		if (req.getStartTime() != null && req.getEndTime() != null) {
			criteria.andCreateTimeBetween(req.getStartTime(), req.getEndTime());
		}

		if (!StringUtil.isBlank(req.getStatus())) {
			if (DstConstants.CouponStatus.INVALID.equals(req.getStatus())) {
				criteria.andInactiveTimeLessThan(DateUtil.getSysDate());
			} else {
				criteria.andStatusEqualTo(req.getStatus());
			}

		}
		criteria.andStatusNotEqualTo(DstConstants.CouponStatus.DEL);
		sql.setLimitStart((req.getPageNO() - 1) * req.getPageSize());
		sql.setLimitEnd(req.getPageSize());
		return dstCouponInfoMapper.selectByExample(sql);
	}

	@Override
	public DstCouponInfo getCouponInfo(String tenantId, String couponId) {
		DstCouponInfoCriteria sql = new DstCouponInfoCriteria();
		Criteria criteria = sql.createCriteria();
		if (!StringUtil.isBlank(tenantId)) {
			criteria.andTenantIdEqualTo(tenantId);
		}
		if (!StringUtil.isBlank(couponId)) {
			criteria.andCouponIdEqualTo(couponId);
		}
		return dstCouponInfoMapper.selectByExample(sql).get(0);
	}

	@Override
	public int delCouponInfo(String tenantId, String couponId) {
		DstCouponInfoCriteria sql = new DstCouponInfoCriteria();
		Criteria criteria = sql.createCriteria();
		if (!StringUtil.isBlank(tenantId)) {
			criteria.andTenantIdEqualTo(tenantId);
		}
		if (!StringUtil.isBlank(couponId)) {
			criteria.andCouponIdEqualTo(couponId);
		}
		DstCouponInfo info = new DstCouponInfo();
		info.setStatus(DstConstants.CouponStatus.DEL);
		return dstCouponInfoMapper.updateByExampleSelective(info, sql);
	}

	@Override
	public int changeCouponInfo(String tenantId, String couponId, String status) {
		DstCouponInfoCriteria sql = new DstCouponInfoCriteria();
		Criteria criteria = sql.createCriteria();
		if (!StringUtil.isBlank(tenantId)) {
			criteria.andTenantIdEqualTo(tenantId);
		}
		if (!StringUtil.isBlank(couponId)) {
			criteria.andCouponIdEqualTo(couponId);
		}
		DstCouponInfo info = new DstCouponInfo();
		info.setStatus(status);
		return dstCouponInfoMapper.updateByExampleSelective(info, sql);

	}

	@Override
	public int getCouponCount(CouponReq req) {
		DstCouponInfoCriteria sql = new DstCouponInfoCriteria();
		Criteria criteria = sql.createCriteria();
		if (!StringUtil.isBlank(req.getTenantId())) {
			criteria.andTenantIdEqualTo(req.getTenantId());
		}
		if (!StringUtil.isBlank(req.getCouponId())) {
			criteria.andCouponIdEqualTo(req.getCouponId());
		}
		/*
		 * if(!StringUtil.isBlank(req.getCouponName())) {
		 * criteria.andCouponNameLike("%"+req.getCouponName()+"%"); }
		 */
		if (req.getStartTime() != null && req.getEndTime() == null) {
			criteria.andCreateTimeGreaterThanOrEqualTo(req.getStartTime());
		}
		if (req.getStartTime() == null && req.getEndTime() != null) {
			criteria.andCreateTimeLessThanOrEqualTo(req.getEndTime());
		}
		if (req.getStartTime() != null && req.getEndTime() != null) {
			criteria.andCreateTimeBetween(req.getStartTime(), req.getEndTime());
		}
		criteria.andStatusNotEqualTo(DstConstants.CouponStatus.DEL);

		return dstCouponInfoMapper.countByExample(sql);
	}

	@Override
	public List<DstCouponInfo> getGiftCoupons(CouponReq req, Timestamp cTime) {
		DstCouponInfoCriteria sql = new DstCouponInfoCriteria();
		Criteria criteria = sql.createCriteria();
		if (!StringUtil.isBlank(req.getTenantId())) {
			criteria.andTenantIdEqualTo(req.getTenantId());
		}
		if (!StringUtil.isBlank(req.getCouponId())) {
			criteria.andCouponIdEqualTo(req.getCouponId());
		}
		if (!StringUtil.isBlank(req.getCouponName())) {
			criteria.andCouponNameLike("%" + req.getCouponName() + "%");
		}
		if (!StringUtil.isBlank(req.getCouponConType())) {
			criteria.andCouponConTypeEqualTo(req.getCouponConType());
		}
		if (req.getStartTime() != null && req.getEndTime() == null) {
			criteria.andCreateTimeGreaterThanOrEqualTo(req.getStartTime());
		}
		if (req.getStartTime() == null && req.getEndTime() != null) {
			criteria.andCreateTimeLessThanOrEqualTo(req.getEndTime());
		}
		if (req.getStartTime() != null && req.getEndTime() != null) {
			criteria.andCreateTimeBetween(req.getStartTime(), req.getEndTime());
		}
		criteria.andStatusEqualTo(DstConstants.CouponStatus.EFFECTIVE);
		// criteria.andActiveTimeLessThanOrEqualTo(cTime);
		criteria.andInactiveTimeGreaterThanOrEqualTo(cTime);
		sql.setLimitStart((req.getPageNO() - 1) * req.getPageSize());
		sql.setLimitEnd(req.getPageSize());
		return dstCouponInfoMapper.selectByExample(sql);
	}

	@Override
	public int getCouponCounts(CouponReq req, Timestamp cTime) {
		DstCouponInfoCriteria sql = new DstCouponInfoCriteria();
		Criteria criteria = sql.createCriteria();
		if (!StringUtil.isBlank(req.getTenantId())) {
			criteria.andTenantIdEqualTo(req.getTenantId());
		}
		if (!StringUtil.isBlank(req.getCouponId())) {
			criteria.andCouponIdEqualTo(req.getCouponId());
		}
		if (!StringUtil.isBlank(req.getCouponName())) {
			criteria.andCouponNameLike("%" + req.getCouponName() + "%");
		}
		if (req.getStartTime() != null && req.getEndTime() == null) {
			criteria.andCreateTimeGreaterThanOrEqualTo(req.getStartTime());
		}
		if (req.getStartTime() == null && req.getEndTime() != null) {
			criteria.andCreateTimeLessThanOrEqualTo(req.getEndTime());
		}
		if (req.getStartTime() != null && req.getEndTime() != null) {
			criteria.andCreateTimeBetween(req.getStartTime(), req.getEndTime());
		}
		criteria.andStatusEqualTo(DstConstants.CouponStatus.EFFECTIVE);
		criteria.andActiveTimeLessThanOrEqualTo(cTime);
		criteria.andInactiveTimeGreaterThanOrEqualTo(cTime);

		return dstCouponInfoMapper.countByExample(sql);
	}

	@Override
	public int changeCouponInvalid(String tenantId, String couponId, Timestamp time) {
		DstCouponInfoCriteria sql = new DstCouponInfoCriteria();
		Criteria criteria = sql.createCriteria();
		if (!StringUtil.isBlank(tenantId)) {
			criteria.andTenantIdEqualTo(tenantId);
		}
		if (!StringUtil.isBlank(couponId)) {
			criteria.andCouponIdEqualTo(couponId);
		}
		DstCouponInfo info = new DstCouponInfo();
		info.setInactiveTime(time);
		return dstCouponInfoMapper.updateByExampleSelective(info, sql);
	}

	@Override
	public List<DstCouponInfo> getCouponList(OPCouponQueryReq req) {
		DstCouponInfoCriteria sql = new DstCouponInfoCriteria();
		Criteria criteria = sql.createCriteria();
		if (!StringUtil.isBlank(req.getTenantId())) {
			criteria.andTenantIdEqualTo(req.getTenantId());
		}
		if (!StringUtil.isBlank(req.getCouponId())) {
			criteria.andCouponIdEqualTo(req.getCouponId());
		}
		if (!StringUtil.isBlank(req.getCouponName())) {
			criteria.andCouponNameLike("%" + req.getCouponName() + "%");
		}
		if (!StringUtil.isBlank(req.getCouponRule())) {
			criteria.andCouponConTypeEqualTo(req.getCouponRule());
		}

		if (req.getStartTime() != null && req.getEndTime() == null) {
			criteria.andCreateTimeGreaterThanOrEqualTo(req.getStartTime());
		}
		if (req.getStartTime() == null && req.getEndTime() != null) {
			criteria.andCreateTimeLessThanOrEqualTo(req.getEndTime());
		}
		if (req.getStartTime() != null && req.getEndTime() != null) {
			criteria.andCreateTimeBetween(req.getStartTime(), req.getEndTime());
		}

		if (!StringUtil.isBlank(req.getStatus())) {
			if (DstConstants.CouponStatus.INVALID.equals(req.getStatus())) {
				criteria.andInactiveTimeLessThan(DateUtil.getSysDate());
			} else {
				criteria.andStatusEqualTo(req.getStatus());
			}

		}
		criteria.andStatusNotEqualTo(DstConstants.CouponStatus.DEL);
		criteria.andCreatorRoleEqualTo("OP");//运营用户
		sql.setLimitStart((req.getPageNO() - 1) * req.getPageSize());
		sql.setLimitEnd(req.getPageSize());
		return dstCouponInfoMapper.selectByExample(sql);
	}

	@Override
	public int getCouponCount(OPCouponQueryReq req) {
		DstCouponInfoCriteria sql = new DstCouponInfoCriteria();
		Criteria criteria = sql.createCriteria();
		if (!StringUtil.isBlank(req.getTenantId())) {
			criteria.andTenantIdEqualTo(req.getTenantId());
		}
		if (!StringUtil.isBlank(req.getCouponId())) {
			criteria.andCouponIdEqualTo(req.getCouponId());
		}
		if (!StringUtil.isBlank(req.getCouponName())) {
			criteria.andCouponNameLike("%" + req.getCouponName() + "%");
		}
		if (!StringUtil.isBlank(req.getCouponRule())) {
			criteria.andCouponConTypeEqualTo(req.getCouponRule());
		}

		if (req.getStartTime() != null && req.getEndTime() == null) {
			criteria.andCreateTimeGreaterThanOrEqualTo(req.getStartTime());
		}
		if (req.getStartTime() == null && req.getEndTime() != null) {
			criteria.andCreateTimeLessThanOrEqualTo(req.getEndTime());
		}
		if (req.getStartTime() != null && req.getEndTime() != null) {
			criteria.andCreateTimeBetween(req.getStartTime(), req.getEndTime());
		}

		if (!StringUtil.isBlank(req.getStatus())) {
			if (DstConstants.CouponStatus.INVALID.equals(req.getStatus())) {
				criteria.andInactiveTimeLessThan(DateUtil.getSysDate());
			} else {
				criteria.andStatusEqualTo(req.getStatus());
			}

		}
		criteria.andCreatorRoleEqualTo("OP");//运营用户
		criteria.andStatusNotEqualTo(DstConstants.CouponStatus.DEL);
		return dstCouponInfoMapper.countByExample(sql);
	}

	@Override
	public List<DstCouponInfo> getQDCoupons(QDCouponQueryReq req) {
		 
		DstCouponInfoCriteria sql = new DstCouponInfoCriteria();
		Criteria criteria = sql.createCriteria();
		if (!StringUtil.isBlank(req.getTenantId())) {
			criteria.andTenantIdEqualTo(req.getTenantId());
		}
		if (!StringUtil.isBlank(req.getCouponId())) {
			criteria.andCouponIdEqualTo(req.getCouponId());
		}
		if (!StringUtil.isBlank(req.getCouponName())) {
			criteria.andCouponNameLike("%" + req.getCouponName() + "%");
		}
		

		if (req.getStartTime() != null && req.getEndTime() == null) {
			criteria.andCreateTimeGreaterThanOrEqualTo(req.getStartTime());
		}
		if (req.getStartTime() == null && req.getEndTime() != null) {
			criteria.andCreateTimeLessThanOrEqualTo(req.getEndTime());
		}
		if (req.getStartTime() != null && req.getEndTime() != null) {
			criteria.andCreateTimeBetween(req.getStartTime(), req.getEndTime());
		}

		if (!StringUtil.isBlank(req.getStatus())) {
			if (DstConstants.CouponStatus.INVALID.equals(req.getStatus())) {
				criteria.andInactiveTimeLessThan(DateUtil.getSysDate());
			} else {
				criteria.andStatusEqualTo(req.getStatus());
			}

		}
		criteria.andCreatorRoleEqualTo("QD");//运营用户
		criteria.andStatusNotEqualTo(DstConstants.CouponStatus.DEL);
		sql.setLimitStart((req.getPageNO() - 1) * req.getPageSize());
		sql.setLimitEnd(req.getPageSize());
		return dstCouponInfoMapper.selectByExample(sql);
	}

	@Override
	public int getQDCouponCount(QDCouponQueryReq req) {
		DstCouponInfoCriteria sql = new DstCouponInfoCriteria();
		Criteria criteria = sql.createCriteria();
		if (!StringUtil.isBlank(req.getTenantId())) {
			criteria.andTenantIdEqualTo(req.getTenantId());
		}
		if (!StringUtil.isBlank(req.getCouponId())) {
			criteria.andCouponIdEqualTo(req.getCouponId());
		}
		if (!StringUtil.isBlank(req.getCouponName())) {
			criteria.andCouponNameLike("%" + req.getCouponName() + "%");
		}
		

		if (req.getStartTime() != null && req.getEndTime() == null) {
			criteria.andCreateTimeGreaterThanOrEqualTo(req.getStartTime());
		}
		if (req.getStartTime() == null && req.getEndTime() != null) {
			criteria.andCreateTimeLessThanOrEqualTo(req.getEndTime());
		}
		if (req.getStartTime() != null && req.getEndTime() != null) {
			criteria.andCreateTimeBetween(req.getStartTime(), req.getEndTime());
		}

		if (!StringUtil.isBlank(req.getStatus())) {
			if (DstConstants.CouponStatus.INVALID.equals(req.getStatus())) {
				criteria.andInactiveTimeLessThan(DateUtil.getSysDate());
			} else {
				criteria.andStatusEqualTo(req.getStatus());
			}
		}
		criteria.andCreatorRoleEqualTo("QD");//运营用户
		criteria.andStatusNotEqualTo(DstConstants.CouponStatus.DEL);
		return dstCouponInfoMapper.countByExample(sql);

	}

	@Override
	public int updateChannelInfo(DstCouponInfo info) {
		DstCouponInfoCriteria sql = new DstCouponInfoCriteria();
		Criteria criteria = sql.createCriteria();
		criteria.andTenantIdEqualTo(info.getTenantId());
		criteria.andCouponIdEqualTo(info.getCouponId());
		
		return dstCouponInfoMapper.updateByExample(info, sql);
	}

	@Override
	public List<DstCouponInfoAndCode> getCouponInfoCode(OPCouponCodeReq req) {
		if(!StringUtil.isBlank(req.getCouponStatus())){
			if(DstConstants.CouponStatus.INVALID.equals(req.getCouponStatus())){
				req.setSysTime(DateUtil.getSysDate());
				req.setCouponStatus(null);
			}
		}
		req.setLimitStart((req.getPageNo() - 1) * req.getPageSize());
		return dstCouponInfoMapper.queryCouponInfoAndCode(req);
	}

	@Override
	public int getCouponInfoCodeCount(OPCouponCodeReq req) {
		if(!StringUtil.isBlank(req.getCouponStatus())){
			if(DstConstants.CouponStatus.INVALID.equals(req.getCouponStatus())){
				req.setSysTime(DateUtil.getSysDate());
				req.setCouponStatus(null);
			}
		}
		return dstCouponInfoMapper.queryCouponInfoAndCodeCount(req);
	}

	@Override
	public List<DstMyOwnCouponCodeInfo> getMyOwnCodeInfo(MyOwnCodeReq req) {
		return dstCouponCodeMapper.getMyOwnCouponCode(req);
	}

	@Override
	public List<DstCouponInfo> getCouponList(CouponConditionReq req) {
		DstCouponInfoCriteria sql = new DstCouponInfoCriteria();
		Criteria criteria = sql.createCriteria();
		if (!StringUtil.isBlank(req.getTenantId())) {
			criteria.andTenantIdEqualTo(req.getTenantId());
		}
		if (!StringUtil.isBlank(req.getCouponId())) {
			criteria.andCouponIdEqualTo(req.getCouponId());
		}
		if (!StringUtil.isBlank(req.getCouponName())) {
			criteria.andCouponNameLike("%" + req.getCouponName() + "%");
		}
		criteria.andCouponRuleEqualTo("ALL");
		/*
		 * if(!StringUtil.isBlank(req.getCouponConType())){
		 * criteria.andCouponConTypeEqualTo(req.getCouponConType()); }
		 */

		if (req.getStartTime() != null && req.getEndTime() == null) {
			criteria.andCreateTimeGreaterThanOrEqualTo(req.getStartTime());
		}
		if (req.getStartTime() == null && req.getEndTime() != null) {
			criteria.andCreateTimeLessThanOrEqualTo(req.getEndTime());
		}
		if (req.getStartTime() != null && req.getEndTime() != null) {
			criteria.andCreateTimeBetween(req.getStartTime(), req.getEndTime());
		}
		if (!StringUtil.isBlank(req.getStatus())) {
			if (DstConstants.CouponStatus.INVALID.equals(req.getStatus())) {
				criteria.andInactiveTimeLessThan(DateUtil.getSysDate());
			} else {
				criteria.andStatusEqualTo(req.getStatus());
			}

		}
		criteria.andStatusNotEqualTo(DstConstants.CouponStatus.DEL);
		sql.setLimitStart((req.getPageNO() - 1) * req.getPageSize());
		sql.setLimitEnd(req.getPageSize());
		return dstCouponInfoMapper.selectByExample(sql);
	}

	@Override
	public int getCouponCount(CouponConditionReq req) {
		DstCouponInfoCriteria sql = new DstCouponInfoCriteria();
		Criteria criteria = sql.createCriteria();
		if (!StringUtil.isBlank(req.getTenantId())) {
			criteria.andTenantIdEqualTo(req.getTenantId());
		}
		if (!StringUtil.isBlank(req.getCouponId())) {
			criteria.andCouponIdEqualTo(req.getCouponId());
		}
		criteria.andCouponRuleEqualTo("ALL");
		/*
		 * if(!StringUtil.isBlank(req.getCouponName())) {
		 * criteria.andCouponNameLike("%"+req.getCouponName()+"%"); }
		 */
		if (req.getStartTime() != null && req.getEndTime() == null) {
			criteria.andCreateTimeGreaterThanOrEqualTo(req.getStartTime());
		}
		if (req.getStartTime() == null && req.getEndTime() != null) {
			criteria.andCreateTimeLessThanOrEqualTo(req.getEndTime());
		}
		if (req.getStartTime() != null && req.getEndTime() != null) {
			criteria.andCreateTimeBetween(req.getStartTime(), req.getEndTime());
		}
		if (!StringUtil.isBlank(req.getStatus())) {
			if (DstConstants.CouponStatus.INVALID.equals(req.getStatus())) {
				criteria.andInactiveTimeLessThan(DateUtil.getSysDate());
			} else {
				criteria.andStatusEqualTo(req.getStatus());
			}

		}
		criteria.andStatusNotEqualTo(DstConstants.CouponStatus.DEL);

		return dstCouponInfoMapper.countByExample(sql);
	}

	@Override
	public List<DstExportCouponAndCode> getExportCouponAndCode(ChannelCodeReq req) {
		if(!StringUtil.isBlank(req.getStatus())){
			if(DstConstants.CouponStatus.INVALID.equals(req.getStatus())){
				req.setSysTime(DateUtil.getSysDate());
				req.setStatus(null);
			}
		}
		return dstCouponInfoMapper.queryExportCouponAndCode(req);
	}

	@Override
	public List<DstCouponInfoAndCode> getExportCouponCode(OPCouponCodeReq req) {
		if(!StringUtil.isBlank(req.getCouponStatus())){
			if(DstConstants.CouponStatus.INVALID.equals(req.getCouponStatus())){
				req.setSysTime(DateUtil.getSysDate());
				req.setCouponStatus(null);
			}
		}
		return dstCouponInfoMapper.getExportCouponCode(req);
	}

	@Override
	public List<DstCouponInfo> getCouponAuditPageList(DstCouponAuditInfo req) {
		
		return dstCouponInfoMapper.selectCouponAuditInfo(req);
	}

	@Override
	public int getCouponAuditCount(DstCouponAuditInfo req) {
		return dstCouponInfoMapper.getCouponAuditCount(req);
	}

	@Override
	public int addCouponCode(DstCouponCode info) {
		return dstCouponCodeMapper.insertSelective(info);
	}

	@Override
	public int updateCouponInfo(DstCouponInfo couponInfo) {
		DstCouponInfoCriteria sql = new DstCouponInfoCriteria();
		Criteria criteria = sql.createCriteria();
		if (!StringUtil.isBlank(couponInfo.getTenantId())) {
			criteria.andCouponIdEqualTo(couponInfo.getTenantId());
		}
		if (!StringUtil.isBlank(couponInfo.getCouponId())) {
			criteria.andCouponIdEqualTo(couponInfo.getCouponId());
		}
		
		return dstCouponInfoMapper.updateByExampleSelective(couponInfo, sql);
	}
	@Override
	public DstCouponInfo getCouponInfo(String tenantId, String couponId ,String channelCode) {
		DstCouponInfoCriteria sql = new DstCouponInfoCriteria();
		Criteria criteria = sql.createCriteria();
		if (!StringUtil.isBlank(tenantId)) {
			criteria.andTenantIdEqualTo(tenantId);
		}
		if (!StringUtil.isBlank(couponId)) {
			criteria.andCouponIdEqualTo(couponId);
		}
		if (!StringUtil.isBlank(channelCode)) {
			criteria.andCreatorRoleEqualTo(channelCode);
		}
		criteria.andStatusNotEqualTo(DstConstants.CouponStatus.DEL);
		List<DstCouponInfo> selectByExample = dstCouponInfoMapper.selectByExample(sql);
		if(selectByExample!=null && selectByExample.size()>0){
			return selectByExample.get(0);
		}
		else{
			return null;
		}
	}

	@Override
	public List<DstCouponInfo> getCouponPageList(CouponConditionReq req) {
		DstCouponInfoCriteria sql = new DstCouponInfoCriteria();
		Criteria criteria = sql.createCriteria();
		if (!StringUtil.isBlank(req.getTenantId())) {
			criteria.andTenantIdEqualTo(req.getTenantId());
		}
		if (!StringUtil.isBlank(req.getCouponId())) {
			criteria.andCouponIdEqualTo(req.getCouponId());
		}
		if (!StringUtil.isBlank(req.getCouponName())) {
			criteria.andCouponNameLike("%" + req.getCouponName() + "%");
		}
		if (!StringUtil.isBlank(req.getStatus())) {
			criteria.andStatusEqualTo(req.getStatus());
		}
		if (!StringUtil.isBlank(req.getCreatorId())) {
			criteria.andCreatorIdEqualTo(req.getCreatorId());
		}
		if (!StringUtil.isBlank(req.getCreatorRole())) {
			criteria.andCreatorRoleEqualTo(req.getCreatorRole());
		}
		if (req.getStartTime() != null && req.getEndTime() == null) {
			criteria.andCreateTimeGreaterThanOrEqualTo(req.getStartTime());
		}
		if (req.getStartTime() == null && req.getEndTime() != null) {
			criteria.andCreateTimeLessThanOrEqualTo(req.getEndTime());
		}
		if (req.getStartTime() != null && req.getEndTime() != null) {
			criteria.andCreateTimeBetween(req.getStartTime(), req.getEndTime());
		}
		if (!StringUtil.isBlank(req.getStatus())) {
			if (DstConstants.CouponStatus.INVALID.equals(req.getStatus())) {
				criteria.andInactiveTimeLessThan(DateUtil.getSysDate());
			} else {
				criteria.andStatusEqualTo(req.getStatus());
			}

		}
		criteria.andStatusNotEqualTo(DstConstants.CouponStatus.DEL);
		sql.setLimitStart((req.getPageNO() - 1) * req.getPageSize());
		sql.setLimitEnd(req.getPageSize());
		return dstCouponInfoMapper.selectByExample(sql);
	}
	@Override
	public int getCouponPageCount(CouponConditionReq req) {
		DstCouponInfoCriteria sql = new DstCouponInfoCriteria();
		Criteria criteria = sql.createCriteria();
		if (!StringUtil.isBlank(req.getTenantId())) {
			criteria.andTenantIdEqualTo(req.getTenantId());
		}
		if (!StringUtil.isBlank(req.getCouponId())) {
			criteria.andCouponIdEqualTo(req.getCouponId());
		}
		if (!StringUtil.isBlank(req.getCouponName())) {
			criteria.andCouponNameLike("%" + req.getCouponName() + "%");
		}
		if (!StringUtil.isBlank(req.getStatus())) {
			criteria.andStatusEqualTo(req.getStatus());
		}
		if (!StringUtil.isBlank(req.getCreatorId())) {
			criteria.andCreatorIdEqualTo(req.getCreatorId());
		}
		if (!StringUtil.isBlank(req.getCreatorRole())) {
			criteria.andCreatorRoleEqualTo(req.getCreatorRole());
		}
		if (req.getStartTime() != null && req.getEndTime() == null) {
			criteria.andCreateTimeGreaterThanOrEqualTo(req.getStartTime());
		}
		if (req.getStartTime() == null && req.getEndTime() != null) {
			criteria.andCreateTimeLessThanOrEqualTo(req.getEndTime());
		}
		if (req.getStartTime() != null && req.getEndTime() != null) {
			criteria.andCreateTimeBetween(req.getStartTime(), req.getEndTime());
		}
		if (!StringUtil.isBlank(req.getStatus())) {
			if (DstConstants.CouponStatus.INVALID.equals(req.getStatus())) {
				criteria.andInactiveTimeLessThan(DateUtil.getSysDate());
			} else {
				criteria.andStatusEqualTo(req.getStatus());
			}

		}
		criteria.andStatusNotEqualTo(DstConstants.CouponStatus.DEL);
		return dstCouponInfoMapper.countByExample(sql);
	}
	@Override
	public List<DstCouponInfo> getCouponAllList(CouponConditionReq req) {
		DstCouponInfoCriteria sql = new DstCouponInfoCriteria();
		Criteria criteria = sql.createCriteria();
		if (!StringUtil.isBlank(req.getTenantId())) {
			criteria.andTenantIdEqualTo(req.getTenantId());
		}
		if (!StringUtil.isBlank(req.getCouponId())) {
			criteria.andCouponIdEqualTo(req.getCouponId());
		}
		if (!StringUtil.isBlank(req.getCouponName())) {
			criteria.andCouponNameLike("%" + req.getCouponName() + "%");
		}
		if (!StringUtil.isBlank(req.getCreatorId())) {
			criteria.andCreatorIdEqualTo(req.getCreatorId());
		}
		if (!StringUtil.isBlank(req.getCreatorRole())) {
			criteria.andCreatorRoleEqualTo(req.getCreatorRole());
		}
		if (!StringUtil.isBlank(req.getStatus())) {
			if (DstConstants.CouponStatus.INVALID.equals(req.getStatus())) {
				criteria.andInactiveTimeLessThan(DateUtil.getSysDate());
			} else {
				criteria.andStatusEqualTo(req.getStatus());
			}
		}
		criteria.andStatusNotEqualTo(DstConstants.CouponStatus.DEL);
		return dstCouponInfoMapper.selectByExample(sql);
	}

	@Override
	public int updateCouponInfo(DstCouponInfo couponInfo, CouponConditionReq req) {
		DstCouponInfoCriteria sql = new DstCouponInfoCriteria();
		Criteria criteria = sql.createCriteria();
		if (!StringUtil.isBlank(req.getTenantId())) {
			criteria.andTenantIdEqualTo(req.getTenantId());
		}
		if (!StringUtil.isBlank(req.getCouponId())) {
			criteria.andCouponIdEqualTo(req.getCouponId());
		}
		if (!StringUtil.isBlank(req.getCreatorRole())) {
			criteria.andCreatorRoleEqualTo(req.getCreatorRole());
		}
		if (!StringUtil.isBlank(req.getStatus())) {
			criteria.andStatusEqualTo(req.getStatus());
		}
		return dstCouponInfoMapper.updateByExampleSelective(couponInfo, sql);
	}
}
