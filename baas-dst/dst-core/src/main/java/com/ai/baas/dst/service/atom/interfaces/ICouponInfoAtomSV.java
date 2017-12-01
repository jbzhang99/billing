package com.ai.baas.dst.service.atom.interfaces;

import java.sql.Timestamp;
import java.util.List;

import com.ai.baas.dst.api.coupon.params.ChannelCodeReq;
import com.ai.baas.dst.api.coupon.params.CouponConditionReq;
import com.ai.baas.dst.api.coupon.params.CouponReq;
import com.ai.baas.dst.api.coupon.params.MyOwnCodeReq;
import com.ai.baas.dst.api.coupon.params.OPCouponCodeReq;
import com.ai.baas.dst.api.coupon.params.OPCouponQueryReq;
import com.ai.baas.dst.api.coupon.params.QDCouponQueryReq;
import com.ai.baas.dst.dao.mapper.bo.DstCouponAuditInfo;
import com.ai.baas.dst.dao.mapper.bo.DstCouponCode;
import com.ai.baas.dst.dao.mapper.bo.DstCouponInfo;
import com.ai.baas.dst.dao.mapper.bo.DstCouponInfoAndCode;
import com.ai.baas.dst.dao.mapper.bo.DstExportCouponAndCode;
import com.ai.baas.dst.dao.mapper.bo.DstMyOwnCouponCodeInfo;

public interface ICouponInfoAtomSV {

	int addCouponInfo(DstCouponInfo info);
	List<DstCouponInfo> getCouponList(CouponReq req);
	DstCouponInfo getCouponInfo(String tenantId,String couponId);
	int delCouponInfo(String teantId,String couponId);
	int changeCouponInfo(String tenantId,String couponId,String status);
	int getCouponCount(CouponReq req);
	int getCouponCounts(CouponReq req,Timestamp cTime);
	public List<DstCouponInfo> getGiftCoupons(CouponReq req,Timestamp cTime);
	
	int changeCouponInvalid(String tenantId,String couponId,Timestamp time);
	
	
	List<DstCouponInfo> getCouponList(OPCouponQueryReq req);
	
	int getCouponCount(OPCouponQueryReq req);
	
    List<DstCouponInfo> getQDCoupons(QDCouponQueryReq req);
    
    int  getQDCouponCount(QDCouponQueryReq req);
    int updateChannelInfo(DstCouponInfo info);
    
    List<DstCouponInfoAndCode> getCouponInfoCode(OPCouponCodeReq req);
	
	int getCouponInfoCodeCount(OPCouponCodeReq req);
	
	List<DstMyOwnCouponCodeInfo> getMyOwnCodeInfo(MyOwnCodeReq req);
	
	List<DstCouponInfo> getCouponList(CouponConditionReq req);
	
	int getCouponCount(CouponConditionReq req);
	
	List<DstExportCouponAndCode> getExportCouponAndCode(ChannelCodeReq req);
	
	List<DstCouponInfoAndCode> getExportCouponCode(OPCouponCodeReq req);
	
	List<DstCouponInfo> getCouponAuditPageList(DstCouponAuditInfo req);
	
	int getCouponAuditCount(DstCouponAuditInfo req);
	
	int addCouponCode(DstCouponCode info);
	
	int updateCouponInfo(DstCouponInfo couponInfo);
	
	DstCouponInfo getCouponInfo(String tenantId, String couponId, String string);
	
	List<DstCouponInfo> getCouponPageList(CouponConditionReq req);
	
	int getCouponPageCount(CouponConditionReq req);
	
	List<DstCouponInfo> getCouponAllList(CouponConditionReq req);
	
	int updateCouponInfo(DstCouponInfo couponInfo, CouponConditionReq req);
}
