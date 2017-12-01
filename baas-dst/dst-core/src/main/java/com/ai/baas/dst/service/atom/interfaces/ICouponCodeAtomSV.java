package com.ai.baas.dst.service.atom.interfaces;

import java.util.List;

import com.ai.baas.dst.api.coupon.params.ChannelCodeReq;
import com.ai.baas.dst.api.coupon.params.CouponCodeReq;
import com.ai.baas.dst.api.coupon.params.ExportCodeReq;
import com.ai.baas.dst.dao.mapper.bo.CouponCode;
import com.ai.baas.dst.dao.mapper.bo.DstCouponCode;
import com.ai.baas.dst.dao.mapper.bo.OPCouponCode;
import com.ai.baas.dst.dao.mapper.bo.QDCouponCode;
import com.ai.baas.dst.dao.mapper.bo.QDCouponCodes;

public interface ICouponCodeAtomSV {

	int addCouponCode(DstCouponCode code);

	List<DstCouponCode> getCouponCodeList(String tenantId, String couponId);

	int delCouponCode(String tenantId, String couponId);

	int changeCouponCodeStatus(String tenantId, String couponId, String couponCode, String status);

	DstCouponCode getSingleStatus(String tenantId, String couponId, String couponCode);

	int getEffCount(String tenantId, String couponId);

	int getUsedOrGotCount(String tenantId, String couponId);

	List<DstCouponCode> getCanDisCoupon(String tenantId, String couponId);

	List<DstCouponCode> getOPCouponCode(CouponCodeReq req);

	List<CouponCode> selectAggreByExample(OPCouponCode example);

	int selectAggreCount(OPCouponCode example);
	
	List<DstCouponCode> getChannelCodes(ChannelCodeReq req);
	
	int getChannelCodesCount(ChannelCodeReq req);
	
	int changeCodeStatusAndServiceId(String tenantId, String couponCode, String status,String serviceId,String orderId);
	
	DstCouponCode getSingleCodeForCheck(String tenantId, String couponCode);
	List<QDCouponCodes> getQDExistsCodes(QDCouponCode req);
	
	int coutQDExistsCodes(QDCouponCode example);
	
	List<CouponCode> selectChannelInfoByExample(OPCouponCode example);
	
	 List<DstCouponCode> getExpChannelCodes(ExportCodeReq req);

	List<QDCouponCodes> getQDExistsCodesList(QDCouponCode code);
	
}
