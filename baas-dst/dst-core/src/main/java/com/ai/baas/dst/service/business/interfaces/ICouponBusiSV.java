package com.ai.baas.dst.service.business.interfaces;

import java.util.List;

import com.ai.baas.dst.api.coupon.params.ActiveReq;
import com.ai.baas.dst.api.coupon.params.ApplyCouponReq;
import com.ai.baas.dst.api.coupon.params.AuditCouponsReq;
import com.ai.baas.dst.api.coupon.params.BaseReq;
import com.ai.baas.dst.api.coupon.params.BoundCouponCodeReq;
import com.ai.baas.dst.api.coupon.params.ChannelCodeDetailsRes;
import com.ai.baas.dst.api.coupon.params.ChannelCodeReq;
import com.ai.baas.dst.api.coupon.params.ChannelCodeVO;
import com.ai.baas.dst.api.coupon.params.CouponAtom;
import com.ai.baas.dst.api.coupon.params.CouponAuditInfo;
import com.ai.baas.dst.api.coupon.params.CouponChannelReq;
import com.ai.baas.dst.api.coupon.params.CouponCodeInfoVO;
import com.ai.baas.dst.api.coupon.params.CouponCodeReq;
import com.ai.baas.dst.api.coupon.params.CouponCodeVO;
import com.ai.baas.dst.api.coupon.params.CouponConditionReq;
import com.ai.baas.dst.api.coupon.params.CouponExport;
import com.ai.baas.dst.api.coupon.params.CouponInfo;
import com.ai.baas.dst.api.coupon.params.CouponInfoReq;
import com.ai.baas.dst.api.coupon.params.CouponInfoRes;
import com.ai.baas.dst.api.coupon.params.CouponReq;
import com.ai.baas.dst.api.coupon.params.CouponResInfo;
import com.ai.baas.dst.api.coupon.params.CouponsAuditInfoReq;
import com.ai.baas.dst.api.coupon.params.DelReq;
import com.ai.baas.dst.api.coupon.params.ExPortReq;
import com.ai.baas.dst.api.coupon.params.ExistisCouponReq;
import com.ai.baas.dst.api.coupon.params.ExportCodeReq;
import com.ai.baas.dst.api.coupon.params.ExportExistisCouponReq;
import com.ai.baas.dst.api.coupon.params.InvalidReq;
import com.ai.baas.dst.api.coupon.params.MyOwnCodeReq;
import com.ai.baas.dst.api.coupon.params.MyOwnCouponCodeInfo;
import com.ai.baas.dst.api.coupon.params.OPCheckCodeStatusReq;
import com.ai.baas.dst.api.coupon.params.OPCheckCodeStatusRes;
import com.ai.baas.dst.api.coupon.params.OPCouponCodeInfo;
import com.ai.baas.dst.api.coupon.params.OPCouponCodeReq;
import com.ai.baas.dst.api.coupon.params.OPCouponInfo;
import com.ai.baas.dst.api.coupon.params.OPCouponInfoReq;
import com.ai.baas.dst.api.coupon.params.OPCouponQueryReq;
import com.ai.baas.dst.api.coupon.params.OPCouponUsedReq;
import com.ai.baas.dst.api.coupon.params.OPSingleCouponRes;
import com.ai.baas.dst.api.coupon.params.QDCouponInfoReq;
import com.ai.baas.dst.api.coupon.params.QDCouponQueryReq;
import com.ai.baas.dst.api.coupon.params.QDcoupon;
import com.ai.baas.dst.api.coupon.params.SingleCoupon;
import com.ai.baas.dst.api.coupon.params.SingleCouponReq;
import com.ai.baas.dst.api.coupon.params.UpQDCouponInfoReq;
import com.ai.baas.dst.api.coupon.params.UseReq;
import com.ai.opt.base.vo.PageInfo;

public interface ICouponBusiSV {
	
	Integer addCoupon(CouponInfoReq couponInfo);
	
	PageInfo<CouponInfo> getCouponList(CouponReq req);
	
	CouponExport getCoupon(ExPortReq req);
	
	Integer  delCouponById(DelReq req);
	
	SingleCoupon getSingleCoupon(SingleCouponReq req);
	
	Integer activeCoupon(ActiveReq req);
	
	Integer useCoupon(UseReq req);
	
	PageInfo<CouponInfo> getGiftCoupons(CouponReq req);
	
	Integer invalidByTime(InvalidReq req);
	/**
	 * 标记为已经 被领用
	 */
	CouponAtom signGot(BaseReq req);
	
	CouponAtom RandomCoupon(BaseReq req);
	
	Integer refuseCoupon(BaseReq req);
	
	Integer addOPCoupon(OPCouponInfoReq opCouponInfo);
	
	
	Integer addQDCoupon(QDCouponInfoReq couponInfo);
	
	PageInfo<OPCouponInfo> getCouponList(OPCouponQueryReq req);
	
	OPSingleCouponRes getOPSingleCoupon(BaseReq req);
	
	PageInfo<CouponInfoRes> getQDCoupons(QDCouponQueryReq req);
	
	
	PageInfo<CouponCodeVO> getCodes(CouponCodeReq req);
	
	PageInfo<ChannelCodeVO> getChannelCodes(ChannelCodeReq req);
	
	Integer distrCoupon(ApplyCouponReq req);
	
	PageInfo<OPCouponCodeInfo> getCouponCodeList(OPCouponCodeReq req);
	
	Integer changeCouCodeToUsed(OPCouponUsedReq req);
	
	
	PageInfo<QDcoupon> getQDExistsCoupon(ExistisCouponReq req);
	
	OPCheckCodeStatusRes checkCodeForUsed(OPCheckCodeStatusReq req);
	
	List<MyOwnCouponCodeInfo> getMyOwnCodeInfo(MyOwnCodeReq req);
	
	Integer boundCouponCode(BoundCouponCodeReq req);
	
	PageInfo<CouponResInfo> getCouponList(CouponConditionReq req);
	
	List<OPCouponCodeInfo> exportCouponCodeInfo(OPCouponCodeReq req);
	
	List<CouponCodeInfoVO> getChannelInfoByCouponAndChannelCondition(CouponChannelReq req);
	
	List<ChannelCodeVO> getExpChannelCodes(ExportCodeReq req);

	List<QDcoupon> getQDExistsCouponList(ExportExistisCouponReq req);
	
	List<ChannelCodeDetailsRes> getChannleCodeList(ChannelCodeReq req);
	
	PageInfo<CouponAuditInfo> getCouponAuditPageList(CouponsAuditInfoReq req);

	List<CouponAuditInfo> getCouponAuditList(CouponsAuditInfoReq req);

	int addAuditCoupon(AuditCouponsReq req);

	PageInfo<QDcoupon> getCouponNotAuditPageList(CouponConditionReq req);
	
	List<QDcoupon> getCouponNotAuditList(CouponConditionReq req);

	int deleteCouponNotAudit(CouponConditionReq req);

	int updateQDCoupon(UpQDCouponInfoReq req);
}
