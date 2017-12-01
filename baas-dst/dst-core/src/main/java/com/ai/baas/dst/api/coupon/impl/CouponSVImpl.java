package com.ai.baas.dst.api.coupon.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.dst.api.coupon.interfaces.ICouponSV;
import com.ai.baas.dst.api.coupon.params.ActiveReq;
import com.ai.baas.dst.api.coupon.params.ApplyCouponReq;
import com.ai.baas.dst.api.coupon.params.AuditCouponsReq;
import com.ai.baas.dst.api.coupon.params.BaseReq;
import com.ai.baas.dst.api.coupon.params.BoundCouponCodeReq;
import com.ai.baas.dst.api.coupon.params.ChannelCodeDetailList;
import com.ai.baas.dst.api.coupon.params.ChannelCodeExportRes;
import com.ai.baas.dst.api.coupon.params.ChannelCodeListRes;
import com.ai.baas.dst.api.coupon.params.ChannelCodeReq;
import com.ai.baas.dst.api.coupon.params.ChannelCodeVO;
import com.ai.baas.dst.api.coupon.params.CouponAtom;
import com.ai.baas.dst.api.coupon.params.CouponAuditInfo;
import com.ai.baas.dst.api.coupon.params.CouponAuditListResponse;
import com.ai.baas.dst.api.coupon.params.CouponAuditPageList;
import com.ai.baas.dst.api.coupon.params.CouponChannelReq;
import com.ai.baas.dst.api.coupon.params.CouponCodeReq;
import com.ai.baas.dst.api.coupon.params.CouponConditionReq;
import com.ai.baas.dst.api.coupon.params.CouponExport;
import com.ai.baas.dst.api.coupon.params.CouponInfo;
import com.ai.baas.dst.api.coupon.params.CouponInfoListResponse;
import com.ai.baas.dst.api.coupon.params.CouponInfoReq;
import com.ai.baas.dst.api.coupon.params.CouponListRes;
import com.ai.baas.dst.api.coupon.params.CouponListResponse;
import com.ai.baas.dst.api.coupon.params.CouponPageListRes;
import com.ai.baas.dst.api.coupon.params.CouponReq;
import com.ai.baas.dst.api.coupon.params.CouponResInfo;
import com.ai.baas.dst.api.coupon.params.CouponResponse;
import com.ai.baas.dst.api.coupon.params.CouponsAuditInfoReq;
import com.ai.baas.dst.api.coupon.params.DelReq;
import com.ai.baas.dst.api.coupon.params.DistrResponse;
import com.ai.baas.dst.api.coupon.params.ExPortReq;
import com.ai.baas.dst.api.coupon.params.ExistisCouponReq;
import com.ai.baas.dst.api.coupon.params.ExistsCouponListRes;
import com.ai.baas.dst.api.coupon.params.ExportCodeReq;
import com.ai.baas.dst.api.coupon.params.ExportCouponResponse;
import com.ai.baas.dst.api.coupon.params.ExportExistisCouponReq;
import com.ai.baas.dst.api.coupon.params.ExportExistsCouponListRes;
import com.ai.baas.dst.api.coupon.params.ExportOPCouponCodeRes;
import com.ai.baas.dst.api.coupon.params.InvalidReq;
import com.ai.baas.dst.api.coupon.params.MyOwnCodeReq;
import com.ai.baas.dst.api.coupon.params.MyOwnCouponCodeInfo;
import com.ai.baas.dst.api.coupon.params.MyOwnCouponCodeRes;
import com.ai.baas.dst.api.coupon.params.OPChannelInfoListRes;
import com.ai.baas.dst.api.coupon.params.OPCheckCodeStatusReq;
import com.ai.baas.dst.api.coupon.params.OPCheckCodeStatusRes;
import com.ai.baas.dst.api.coupon.params.OPCodeListRes;
import com.ai.baas.dst.api.coupon.params.OPCouponCodeInfo;
import com.ai.baas.dst.api.coupon.params.OPCouponCodeListRes;
import com.ai.baas.dst.api.coupon.params.OPCouponCodeReq;
import com.ai.baas.dst.api.coupon.params.OPCouponInfo;
import com.ai.baas.dst.api.coupon.params.OPCouponInfoReq;
import com.ai.baas.dst.api.coupon.params.OPCouponListRes;
import com.ai.baas.dst.api.coupon.params.OPCouponQueryReq;
import com.ai.baas.dst.api.coupon.params.OPCouponUsedReq;
import com.ai.baas.dst.api.coupon.params.OPSingleCouponRes;
import com.ai.baas.dst.api.coupon.params.QDCouponInfoReq;
import com.ai.baas.dst.api.coupon.params.QDCouponListRes;
import com.ai.baas.dst.api.coupon.params.QDCouponQueryReq;
import com.ai.baas.dst.api.coupon.params.QDcoupon;
import com.ai.baas.dst.api.coupon.params.SingleCouponReq;
import com.ai.baas.dst.api.coupon.params.UpQDCouponInfoReq;
import com.ai.baas.dst.api.coupon.params.UseReq;
import com.ai.baas.dst.constants.ExceptCodeConstant;
import com.ai.baas.dst.service.business.interfaces.ICouponBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.dubbo.config.annotation.Service;

@Service
@Component
public class CouponSVImpl implements ICouponSV {
	private static final Logger LOG = LogManager.getLogger(CouponSVImpl.class);
	@Autowired
	private ICouponBusiSV iCouponBusiSV;

	@Override
	public BaseResponse addCoupon(CouponInfoReq couponInfo) throws BusinessException, SystemException {
		if (couponInfo == null) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "新增优惠券入参不能为空");
		}
		if (StringUtil.isBlank(couponInfo.getTenantId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户id不能为空");
		}
		if (StringUtil.isBlank(couponInfo.getCouponAmount())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "优惠券数量不能为空");
		}
		if (StringUtil.isBlank(couponInfo.getCouponType())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "适用类型不能为空");
		}
		if (StringUtil.isBlank(couponInfo.getConditionValue())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "使用条件不能为空");
		}
		if (StringUtil.isBlank(couponInfo.getCouponType())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "优惠券类型不能为空");
		}
		if (null == couponInfo.getActiveTime()) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "开始时间不能为空");
		}
		if (null == couponInfo.getInactiveTime()) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "结束时间不能为空");
		}
		BaseResponse response = new BaseResponse();
		int i = iCouponBusiSV.addCoupon(couponInfo);
		ResponseHeader responseHeader = null;
		if (i > 0) {
			responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
		} else {
			responseHeader = new ResponseHeader(false, ExceptCodeConstant.FAIL, "失败");
			LOG.error("添加优惠券失败");
		}
		response.setResponseHeader(responseHeader);
		return response;
	}

	@Override
	public CouponListResponse getCouponList(CouponReq req) throws BusinessException, SystemException {
		if (req == null) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "查询入参不能为空");
		}
		if (StringUtil.isBlank(req.getTenantId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户id不能为空");
		}

		CouponListResponse response = new CouponListResponse();
		PageInfo<CouponInfo> couponList = iCouponBusiSV.getCouponList(req);
		response.setPageInfo(couponList);
		ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
		response.setResponseHeader(responseHeader);
		return response;
	}

	@Override
	public ExportCouponResponse getCoupon(ExPortReq req) throws BusinessException, SystemException {
		if (req == null) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "查询入参不能为空");
		}
		if (StringUtil.isBlank(req.getTenantId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户id不能为空");
		}
		if (StringUtil.isBlank(req.getCouponId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "优惠券id不能为空");
		}
		ExportCouponResponse response = new ExportCouponResponse();
		CouponExport coupon = iCouponBusiSV.getCoupon(req);
		response.setCoupon(coupon);
		ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
		response.setResponseHeader(responseHeader);
		return response;
	}

	@Override
	public BaseResponse delCouponById(DelReq req) throws BusinessException, SystemException {
		if (req == null) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "查询入参不能为空");
		}
		if (StringUtil.isBlank(req.getTenantId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户id不能为空");
		}
		if (StringUtil.isBlank(req.getCouponId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "优惠券id不能为空");
		}
		BaseResponse response = new BaseResponse();
		int i = iCouponBusiSV.delCouponById(req);
		ResponseHeader responseHeader = null;
		if (i > 0) {
			responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
		} else {
			responseHeader = new ResponseHeader(false, ExceptCodeConstant.FAIL, "失败");
		}

		response.setResponseHeader(responseHeader);
		return response;
	}

	@Override
	public CouponResponse getSingleCoupon(SingleCouponReq req) throws BusinessException, SystemException {
		if (req == null) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "查询入参不能为空");
		}
		if (StringUtil.isBlank(req.getTenantId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户id不能为空");
		}
		if (StringUtil.isBlank(req.getCouponId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "优惠券id不能为空");
		}
		CouponResponse response = new CouponResponse();
		response.setSingleCoupon(iCouponBusiSV.getSingleCoupon(req));
		ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
		response.setResponseHeader(responseHeader);
		return response;
	}

	@Override
	public BaseResponse activeCoupon(ActiveReq req) throws BusinessException, SystemException {

		if (req == null) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "查询入参不能为空");
		}
		if (StringUtil.isBlank(req.getTenantId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户id不能为空");
		}
		if (StringUtil.isBlank(req.getCouponId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "优惠券id不能为空");
		}

		BaseResponse response = new BaseResponse();
		int i = iCouponBusiSV.activeCoupon(req);
		ResponseHeader responseHeader = null;
		if (i > 0) {
			responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
		} else {
			responseHeader = new ResponseHeader(false, ExceptCodeConstant.FAIL, "失败");
		}

		response.setResponseHeader(responseHeader);
		return response;
	}

	@Override
	public BaseResponse useCoupon(UseReq req) throws BusinessException, SystemException {
		if (req == null) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "查询入参不能为空");
		}
		if (StringUtil.isBlank(req.getTenantId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户id不能为空");
		}
		if (StringUtil.isBlank(req.getCouponId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "优惠券id不能为空");
		}
		if (StringUtil.isBlank(req.getCouponCode())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "优惠券编码不能为空");
		}
		BaseResponse response = new BaseResponse();
		int i = iCouponBusiSV.useCoupon(req);
		ResponseHeader responseHeader = null;
		if (i > 0) {
			responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
		} else {
			responseHeader = new ResponseHeader(false, ExceptCodeConstant.FAIL, "失败");
		}

		response.setResponseHeader(responseHeader);
		return response;
	}

	@Override
	public CouponListResponse getGiftCoupons(CouponReq req) throws BusinessException, SystemException {
		if (req == null) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "查询入参不能为空");
		}
		if (StringUtil.isBlank(req.getTenantId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户id不能为空");
		}

		CouponListResponse response = new CouponListResponse();
		PageInfo<CouponInfo> couponList = iCouponBusiSV.getGiftCoupons(req);
		response.setPageInfo(couponList);
		ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
		response.setResponseHeader(responseHeader);
		return response;
	}

	@Override
	public BaseResponse invalidCoupon(InvalidReq req) throws BusinessException, SystemException {
		if (req == null) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "查询入参不能为空");
		}
		if (StringUtil.isBlank(req.getTenantId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户id不能为空");
		}
		if (StringUtil.isBlank(req.getCouponId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "优惠券id不能为空");
		}
		/*
		 * if(null==req.getTime()){ throw new
		 * BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "失效时间不能为空"); }
		 */
		BaseResponse response = new BaseResponse();
		int i = iCouponBusiSV.invalidByTime(req);
		ResponseHeader responseHeader = null;
		if (i > 0) {
			responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
		} else {
			responseHeader = new ResponseHeader(false, ExceptCodeConstant.FAIL, "失败");
		}

		response.setResponseHeader(responseHeader);
		return response;
	}

	@Override
	public DistrResponse getBindCoupon(BaseReq req) throws BusinessException, SystemException {
		if (req == null) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "查询入参不能为空");
		}
		if (StringUtil.isBlank(req.getTenantId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户id不能为空");
		}
		if (StringUtil.isBlank(req.getCouponId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "优惠券id不能为空");
		}
		DistrResponse response = new DistrResponse();

		CouponAtom ca = iCouponBusiSV.signGot(req);

		response.setCoupon(ca);
		ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");

		response.setResponseHeader(responseHeader);

		return response;
	}

	@Override
	public DistrResponse getRamdomCoupon(BaseReq req) throws BusinessException, SystemException {
		if (req == null) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "查询入参不能为空");
		}
		if (StringUtil.isBlank(req.getTenantId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户id不能为空");
		}
		if (StringUtil.isBlank(req.getCouponId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "优惠券id不能为空");
		}
		DistrResponse response = new DistrResponse();
		CouponAtom ca = iCouponBusiSV.RandomCoupon(req);
		response.setCoupon(ca);
		ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
		response.setResponseHeader(responseHeader);
		return response;
	}

	@Override
	public BaseResponse refuseCouon(BaseReq req) throws BusinessException, SystemException {
		if (req == null) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "查询入参不能为空");
		}
		if (StringUtil.isBlank(req.getTenantId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户id不能为空");
		}
		if (StringUtil.isBlank(req.getCouponId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "优惠券id不能为空");
		}

		BaseResponse response = new BaseResponse();
		int i = iCouponBusiSV.refuseCoupon(req);
		ResponseHeader responseHeader = null;
		if (i > 0) {
			responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
		} else {
			responseHeader = new ResponseHeader(false, ExceptCodeConstant.FAIL, "失败");
		}

		response.setResponseHeader(responseHeader);
		return response;
	}

	@Override
	public BaseResponse addOPCoupon(OPCouponInfoReq couponInfo) throws BusinessException, SystemException {
		if (couponInfo == null) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "新增优惠券入参不能为空");
		}
		if (StringUtil.isBlank(couponInfo.getTenantId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户id不能为空");
		}
		if (StringUtil.isBlank(couponInfo.getCouponAmount())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "优惠券数量不能为空");
		}
		if (StringUtil.isBlank(couponInfo.getCouponType())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "适用类型不能为空");
		}
		if (StringUtil.isBlank(couponInfo.getConditionValue())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "使用条件不能为空");
		}
		if (StringUtil.isBlank(couponInfo.getCouponType())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "优惠券类型不能为空");
		}
		if (null == couponInfo.getActiveTime()) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "开始时间不能为空");
		}
		if (null == couponInfo.getInactiveTime()) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "结束时间不能为空");
		}
		BaseResponse response = new BaseResponse();
		int i = iCouponBusiSV.addOPCoupon(couponInfo);
		ResponseHeader responseHeader = null;
		if (i > 0) {
			responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
		} else {
			responseHeader = new ResponseHeader(false, ExceptCodeConstant.FAIL, "失败");
			LOG.error("添加优惠券失败");
		}
		response.setResponseHeader(responseHeader);
		return response;
	}

	@Override
	public BaseResponse addQDCoupon(QDCouponInfoReq couponInfo) throws BusinessException, SystemException {

		if (couponInfo == null) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "新增优惠券入参不能为空");
		}
		if (StringUtil.isBlank(couponInfo.getTenantId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户id不能为空");
		}
		if (StringUtil.isBlank(couponInfo.getCouponAmount())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "优惠券数量不能为空");
		}
		if (StringUtil.isBlank(couponInfo.getCouponType())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "适用类型不能为空");
		}
		if (StringUtil.isBlank(couponInfo.getConditionValue())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "使用条件不能为空");
		}
		if (StringUtil.isBlank(couponInfo.getCouponType())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "优惠券类型不能为空");
		}
		if (null == couponInfo.getActiveTime()) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "开始时间不能为空");
		}
		if (null == couponInfo.getInactiveTime()) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "结束时间不能为空");
		}
		BaseResponse response = new BaseResponse();
		int i = iCouponBusiSV.addQDCoupon(couponInfo);
		ResponseHeader responseHeader = null;
		if (i > 0) {
			responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
		} else {
			responseHeader = new ResponseHeader(false, ExceptCodeConstant.FAIL, "失败");
			LOG.error("添加优惠券失败");
		}
		response.setResponseHeader(responseHeader);
		return response;
	}

	@Override
	public OPCouponListRes getOPCoupons(OPCouponQueryReq req) throws BusinessException, SystemException {
		if (req == null) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "查询入参不能为空");
		}
		if (StringUtil.isBlank(req.getTenantId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户id不能为空");
		}

		OPCouponListRes response = new OPCouponListRes();
		PageInfo<OPCouponInfo> couponList = iCouponBusiSV.getCouponList(req);
		response.setPageInfo(couponList);
		ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
		response.setResponseHeader(responseHeader);
		return response;

	}

	@Override
	public OPSingleCouponRes getOPSingleCoupon(BaseReq req) throws BusinessException, SystemException {
		if (req == null) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "查询入参不能为空");
		}
		if (StringUtil.isBlank(req.getTenantId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户id不能为空");
		}
		if (StringUtil.isBlank(req.getCouponId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "优惠券id不能为空");
		}
		OPSingleCouponRes response = iCouponBusiSV.getOPSingleCoupon(req);
		ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
		response.setResponseHeader(responseHeader);

		return response;
	}

	@Override
	public OPCodeListRes getOPCouponCode(CouponCodeReq req) throws BusinessException, SystemException {
		if (req == null) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "查询入参不能为空");
		}
		if (StringUtil.isBlank(req.getTenantId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户id不能为空");
		}
		OPCodeListRes response=new OPCodeListRes();
		response.setPageInfo(iCouponBusiSV.getCodes(req));
		ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
		response.setResponseHeader(responseHeader);
		return response;
	}

	@Override
	public QDCouponListRes getQDCoupons(QDCouponQueryReq req) throws BusinessException, SystemException {

		if (req == null) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "查询入参不能为空");
		}
		if (StringUtil.isBlank(req.getTenantId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户id不能为空");
		}
		QDCouponListRes response = new QDCouponListRes();

		ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");

		response.setPageInfo(iCouponBusiSV.getQDCoupons(req));

		response.setResponseHeader(responseHeader);
		return response;
	}

	@Override
	public ChannelCodeListRes getChannelCodes(ChannelCodeReq req) throws BusinessException, SystemException {
		if (req == null) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "查询入参不能为空");
		}
		if (StringUtil.isBlank(req.getTenantId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户id不能为空");
		}
		ChannelCodeListRes res=new ChannelCodeListRes();
		res.setPageInfo(iCouponBusiSV.getChannelCodes(req));

		ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");

		res.setResponseHeader(responseHeader);
		return res;
	}

	@Override
	public BaseResponse applyCoupon(ApplyCouponReq req) throws BusinessException, SystemException {
		if (req == null) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "查询入参不能为空");
		}
		if (StringUtil.isBlank(req.getTenantId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户id不能为空");
		}
		if (StringUtil.isBlank(req.getChannelAccount())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户id不能为空");
		}
		if (StringUtil.isBlank(req.getChannelId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "渠道id不能为空");
		}
		if (StringUtil.isBlank(req.getCouponId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "优惠券id不能为空");
		}
		if (null==req.getApplyCount()||req.getApplyCount()<0) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "申请数量不能为空或者不能为负");
		}
		
		
		iCouponBusiSV.distrCoupon(req);
		
		BaseResponse response=new BaseResponse();
		ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");

		response.setResponseHeader(responseHeader);
		
		
		return response;
	}

	@Override
	public BaseResponse distrCoupon(ApplyCouponReq req) throws BusinessException, SystemException {
		if (req == null) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "查询入参不能为空");
		}
		if (StringUtil.isBlank(req.getTenantId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户id不能为空");
		}
		if (StringUtil.isBlank(req.getChannelAccount())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户id不能为空");
		}
		if (StringUtil.isBlank(req.getChannelId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "渠道id不能为空");
		}
		if (StringUtil.isBlank(req.getCouponId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "优惠券id不能为空");
		}
		if (null==req.getApplyCount()||req.getApplyCount()<0) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "分配数量不能为空或者不能为负");
		}
		iCouponBusiSV.distrCoupon(req);
		
		BaseResponse response=new BaseResponse();
		ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");

		response.setResponseHeader(responseHeader);
		
		
		return response;

	}

	@Override
	public OPCouponCodeListRes getOPCouponInfoCode(OPCouponCodeReq req) throws BusinessException, SystemException {
		if(req == null){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL,"查询入参不能为空");
		}
		if(StringUtil.isBlank(req.getTenantId())){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL,"租户id不能为空");
		}
		OPCouponCodeListRes response = new OPCouponCodeListRes();
		PageInfo<OPCouponCodeInfo> couponCodeList = iCouponBusiSV.getCouponCodeList(req);
		response.setPageInfo(couponCodeList);
		ResponseHeader responseHeader = new ResponseHeader(true,ExceptCodeConstant.SUCCESS,"成功");
		response.setResponseHeader(responseHeader);
		return response;
	}

	@Override
	public OPCheckCodeStatusRes checkOPCouponCodeForUse(OPCheckCodeStatusReq req) throws BusinessException, SystemException {
		if(req == null){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL,"查询入参不能为空");
		}
		if(StringUtil.isBlank(req.getTenantId())){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL,"租户id不能为空");
		}
		if(StringUtil.isBlank(req.getServiceId())){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL,"客户id不能为空");
		}
		if(StringUtil.isBlank(req.getCouponCode())){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL,"优惠码不能为空");
		}
		OPCheckCodeStatusRes response = new OPCheckCodeStatusRes();
		response = iCouponBusiSV.checkCodeForUsed(req);
		ResponseHeader responseHeader = null;
		if (response.getResult() == 1) {
			responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功","优惠码可用");
		} else {
			responseHeader = new ResponseHeader(false, ExceptCodeConstant.FAIL, "失败","优惠码不可用");
			LOG.error("查询优惠码失败");
		}
		response.setResponseHeader(responseHeader);
		return response;
	}

	@Override
	public BaseResponse changeCouponCodeToUsed(OPCouponUsedReq req) throws BusinessException, SystemException {
		if(req == null){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL,"查询入参不能为空");
		}
		if(StringUtil.isBlank(req.getTenantId())){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL,"租户id不能为空");
		}
		if(StringUtil.isBlank(req.getServiceId())){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL,"客户id不能为空");
		}
		if(StringUtil.isBlank(req.getCouponCode())){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL,"优惠码不能为空");
		}
		if(StringUtil.isBlank(req.getOrderId())){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL,"订单id不能为空");
		}
		BaseResponse response = new BaseResponse();
		int i = iCouponBusiSV.changeCouCodeToUsed(req);
		ResponseHeader responseHeader = null;
		if (i > 0) {
			responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
		} else {
			responseHeader = new ResponseHeader(false, ExceptCodeConstant.FAIL, "失败");
			LOG.error("优惠码状态修改失败");
		}
		response.setResponseHeader(responseHeader);
		return response;
	}

	@Override
	public ExistsCouponListRes getExistsCoupons(ExistisCouponReq req) throws BusinessException, SystemException {
				if(req == null){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL,"查询入参不能为空");
		}
		if(StringUtil.isBlank(req.getTenantId())){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL,"租户id不能为空");
		}
		if(StringUtil.isBlank(req.getChannelId())){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL,"渠道id不能为空");
		}
		ExistsCouponListRes response=new ExistsCouponListRes();
		PageInfo<QDcoupon> pageInfo=iCouponBusiSV.getQDExistsCoupon(req);
		ResponseHeader	responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
		response.setPageInfo(pageInfo);
		response.setResponseHeader(responseHeader);
		
		return response;
	}

	@Override
	public MyOwnCouponCodeRes getMyOwnCouponCode(MyOwnCodeReq req) throws BusinessException, SystemException {
		if(req == null){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL,"查询入参不能为空");
		}
		if(StringUtil.isBlank(req.getTenantId())){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL,"租户id不能为空");
		}
		if(StringUtil.isBlank(req.getServiceId())){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL,"客户id不能为空");
		}
		MyOwnCouponCodeRes response = new MyOwnCouponCodeRes();
		List<MyOwnCouponCodeInfo> myCodeList = iCouponBusiSV.getMyOwnCodeInfo(req);
		response.setPageInfo(myCodeList);
		ResponseHeader responseHeader = new ResponseHeader(true,ExceptCodeConstant.SUCCESS,"成功");
		response.setResponseHeader(responseHeader);
		return response;
	}

	@Override
	public BaseResponse boundCouponCode(BoundCouponCodeReq req) throws BusinessException, SystemException {
		if(req == null){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL,"查询入参不能为空");
		}
		if(StringUtil.isBlank(req.getTenantId())){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL,"租户id不能为空");
		}
		if(StringUtil.isBlank(req.getServiceId())){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL,"客户id不能为空");
		}
		if(StringUtil.isBlank(req.getCouponCode())){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL,"优惠码不能为空");
		}
		BaseResponse response = new BaseResponse();
		ResponseHeader responseHeader = null;
		int i = iCouponBusiSV.boundCouponCode(req);
		if(i > 0){
			responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
		}else{
			responseHeader = new ResponseHeader(false, ExceptCodeConstant.FAIL, "失败");
			LOG.error("优惠码绑定失败");
		}
		response.setResponseHeader(responseHeader);
		return response;
	}

	@Override
	public CouponInfoListResponse getCouponInfoList(CouponConditionReq req) throws BusinessException, SystemException {
		if(req == null){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL,"查询入参不能为空");
		}
		if(StringUtil.isBlank(req.getTenantId())){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL,"租户id不能为空");
		}
		CouponInfoListResponse response = new CouponInfoListResponse();
		PageInfo<CouponResInfo> couponList = iCouponBusiSV.getCouponList(req);
		response.setPageInfo(couponList);
		ResponseHeader responseHeader = new ResponseHeader(true,ExceptCodeConstant.SUCCESS,"成功");
		response.setResponseHeader(responseHeader);
		return response;
	}

	@Override
	public ExportOPCouponCodeRes exportOPCouponCode(OPCouponCodeReq req) throws BusinessException, SystemException {
		if(req == null){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL,"查询入参不能为空");
		}
		if(StringUtil.isBlank(req.getTenantId())){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL,"租户id不能为空");
		}
		ExportOPCouponCodeRes response = new ExportOPCouponCodeRes();
		List<OPCouponCodeInfo> couponCodeList = iCouponBusiSV.exportCouponCodeInfo(req);
		response.setPageInfo(couponCodeList);
		ResponseHeader responseHeader = new ResponseHeader(true,ExceptCodeConstant.SUCCESS,"成功");
		response.setResponseHeader(responseHeader);
		return response;
	}
	
	@Override
	public OPChannelInfoListRes getChannelOPCouponCodeList(CouponChannelReq req) throws BusinessException, SystemException {
		if (req == null) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "查询入参不能为空");
		}
		if (StringUtil.isBlank(req.getTenantId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户id不能为空");
		}
		if (StringUtil.isBlank(req.getCouponId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "优惠券id不能为空");
		}
		OPChannelInfoListRes response=new OPChannelInfoListRes();
		response.setChannelList(iCouponBusiSV.getChannelInfoByCouponAndChannelCondition(req));
		ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
		response.setResponseHeader(responseHeader);
		return response;
	}

	@Override
	public ChannelCodeExportRes getChannelCodesExport(ExportCodeReq req)
			throws BusinessException, SystemException {
		if (req == null) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "查询入参不能为空");
		}
		if (StringUtil.isBlank(req.getTenantId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户id不能为空");
		}
		if (StringUtil.isBlank(req.getChannelId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "渠道id不能为空");
		}
		 List<ChannelCodeVO> list=	iCouponBusiSV.getExpChannelCodes(req);
		 ChannelCodeExportRes response=new ChannelCodeExportRes();
		 response.setCodes(list);
		 ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
		 response.setResponseHeader(responseHeader);
		return response;
	}

	@Override
	public ExportExistsCouponListRes getExistsCouponsExport(ExportExistisCouponReq req) throws BusinessException, SystemException {
		if(req == null){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL,"查询入参不能为空");
		}
		if(StringUtil.isBlank(req.getTenantId())){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL,"租户id不能为空");
		}
		if(StringUtil.isBlank(req.getChannelId())){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL,"渠道id不能为空");
		}
		ExportExistsCouponListRes response=new ExportExistsCouponListRes();
		List<QDcoupon> list=iCouponBusiSV.getQDExistsCouponList(req);
		ResponseHeader	responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
		response.setResponseHeader(responseHeader);
		response.setCouponList(list);
		return response;
	}

	@Override
	public ChannelCodeDetailList getCodeDetailList(ChannelCodeReq req) throws BusinessException, SystemException {
		if (req == null) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "查询入参不能为空");
		}
		if (StringUtil.isBlank(req.getTenantId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户id不能为空");
		}
		if(StringUtil.isBlank(req.getChannelId())){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL,"渠道id不能为空");
		}
		if(StringUtil.isBlank(req.getCouponId())){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL,"优惠券id不能为空");
		}
		ChannelCodeDetailList response = new ChannelCodeDetailList();
		response.setPageInfo(iCouponBusiSV.getChannleCodeList(req));
		ResponseHeader responseHeader = new ResponseHeader(true,ExceptCodeConstant.SUCCESS,"成功");
		response.setResponseHeader(responseHeader);
		return response;
	}
	
	@Override
	public CouponAuditPageList getCouponAuditPageList(CouponsAuditInfoReq req)
			throws BusinessException, SystemException {
		if (req == null) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "查询入参不能为空");
		}
		if (StringUtil.isBlank(req.getTenantId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户id不能为空");
		}
		if (req.getPageNO() == null) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "当前页pageNO不能为空");
		}
		if (req.getPageSize()==null ) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "一页显示记录pageSize不能为空");
		}
		CouponAuditPageList response = new CouponAuditPageList();
		PageInfo<CouponAuditInfo> couponList = iCouponBusiSV.getCouponAuditPageList(req);
		response.setPageInfo(couponList);
		ResponseHeader responseHeader = new ResponseHeader(true,ExceptCodeConstant.SUCCESS,"成功");
		response.setResponseHeader(responseHeader);
		return response;
	}

	@Override
	public CouponAuditListResponse getCouponAuditListExport(CouponsAuditInfoReq req) throws BusinessException, SystemException {
		if (req == null) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "查询入参不能为空");
		}
		if (StringUtil.isBlank(req.getTenantId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户id不能为空");
		}
		if (req.getPageNO() != null) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "当前页pageNO不能传值");
		}
		if (req.getPageSize()!=null ) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "一页显示记录pageSize不能传值");
		}
		CouponAuditListResponse response = new CouponAuditListResponse();
		List<CouponAuditInfo> couponList = iCouponBusiSV.getCouponAuditList(req);
		response.setList(couponList);
		ResponseHeader responseHeader = new ResponseHeader(true,ExceptCodeConstant.SUCCESS,"成功");
		response.setResponseHeader(responseHeader);
		return response;
	}

	@Override
	public BaseResponse auditCouponOP(AuditCouponsReq req) throws BusinessException, SystemException {
		if (req == null) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "查询入参不能为空");
		}
		if (StringUtil.isBlank(req.getTenantId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户id不能为空");
		}
		if (StringUtil.isBlank(req.getStatus())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "当前审核状态status不能为空");
		}
		if(StringUtil.isBlank(req.getCouponId())){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL,"优惠券id不能为空");
		}
		BaseResponse response = new BaseResponse();
		int i = iCouponBusiSV.addAuditCoupon(req);
		ResponseHeader responseHeader = null;
		if (i == 1) {
			responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
		} else {
			responseHeader = new ResponseHeader(false, ExceptCodeConstant.FAIL, "失败");
			LOG.error("审核优惠券失败");
		}
		response.setResponseHeader(responseHeader);
		return response;
	}

	@Override
	public CouponPageListRes getCouponNotAudit(CouponConditionReq req) throws BusinessException, SystemException {
		if (req == null) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "查询入参不能为空");
		}
		if (StringUtil.isBlank(req.getTenantId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户id不能为空");
		}
		if (StringUtil.isBlank(req.getCreatorId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "渠道id不能为空");
		}
		if (req.getPageNO() == null) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "当前页pageNO不能为空");
		}
		if (req.getPageSize()==null ) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "一页显示记录pageSize不能为空");
		}
		CouponPageListRes response = new CouponPageListRes();
		PageInfo<QDcoupon> pageInfo = iCouponBusiSV.getCouponNotAuditPageList(req);
		response.setPageInfo(pageInfo);
		ResponseHeader responseHeader = new ResponseHeader(true,ExceptCodeConstant.SUCCESS,"成功");
		response.setResponseHeader(responseHeader);
		return response;
	}
	@Override
	public CouponListRes getCouponNotAuditExport(CouponConditionReq req) throws BusinessException, SystemException {
		if (req == null) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "查询入参不能为空");
		}
		if (StringUtil.isBlank(req.getTenantId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户id不能为空");
		}
		if (StringUtil.isBlank(req.getCreatorId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "渠道id不能为空");
		}
		CouponListRes response = new CouponListRes();
		List<QDcoupon> list = iCouponBusiSV.getCouponNotAuditList(req);
		response.setList(list);
		ResponseHeader responseHeader = new ResponseHeader(true,ExceptCodeConstant.SUCCESS,"成功");
		response.setResponseHeader(responseHeader);
		return response;
	}

	@Override
	public BaseResponse deleteCouponNotAudit(CouponConditionReq req) throws BusinessException, SystemException {
		if (req == null) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "查询入参不能为空");
		}
		if (StringUtil.isBlank(req.getTenantId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户id不能为空");
		}
		if (StringUtil.isBlank(req.getCreatorId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "渠道id不能为空");
		}
		if(StringUtil.isBlank(req.getCouponId())){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL,"优惠券id不能为空");
		}
		BaseResponse response = new BaseResponse();
		int i = iCouponBusiSV.deleteCouponNotAudit(req);
		ResponseHeader responseHeader = null;
		if (i > 0) {
			responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
		} else {
			responseHeader = new ResponseHeader(false, ExceptCodeConstant.FAIL, "失败");
			LOG.error("删除优惠券失败");
		}
		response.setResponseHeader(responseHeader);
		return response;
	}

	@Override
	public BaseResponse updateCouponNotAudit(UpQDCouponInfoReq req) throws BusinessException, SystemException {
		if (req == null) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "新增优惠券入参不能为空");
		}
		if (StringUtil.isBlank(req.getTenantId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "租户id不能为空");
		}
		if (StringUtil.isBlank(req.getCreatorId())) {
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL, "渠道id不能为空");
		}
		if(StringUtil.isBlank(req.getCouponId())){
			throw new BusinessException(ExceptCodeConstant.PARAM_IS_NULL,"优惠券id不能为空");
		}
		
		BaseResponse response = new BaseResponse();
		int i = iCouponBusiSV.updateQDCoupon(req);
		ResponseHeader responseHeader = null;
		if (i > 0) {
			responseHeader = new ResponseHeader(true, ExceptCodeConstant.SUCCESS, "成功");
		} else {
			responseHeader = new ResponseHeader(false, ExceptCodeConstant.FAIL, "失败");
			LOG.error("更新优惠券失败");
		}
		response.setResponseHeader(responseHeader);
		return response;
	}
}
