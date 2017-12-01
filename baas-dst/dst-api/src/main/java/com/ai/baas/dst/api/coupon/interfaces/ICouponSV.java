package com.ai.baas.dst.api.coupon.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.dst.api.coupon.params.ActiveReq;
import com.ai.baas.dst.api.coupon.params.ApplyCouponReq;
import com.ai.baas.dst.api.coupon.params.AuditCouponsReq;
import com.ai.baas.dst.api.coupon.params.BaseReq;
import com.ai.baas.dst.api.coupon.params.BoundCouponCodeReq;
import com.ai.baas.dst.api.coupon.params.ChannelCodeDetailList;
import com.ai.baas.dst.api.coupon.params.ChannelCodeExportRes;
import com.ai.baas.dst.api.coupon.params.ChannelCodeListRes;
import com.ai.baas.dst.api.coupon.params.ChannelCodeReq;
import com.ai.baas.dst.api.coupon.params.CouponAuditListResponse;
import com.ai.baas.dst.api.coupon.params.CouponAuditPageList;
import com.ai.baas.dst.api.coupon.params.CouponChannelReq;
import com.ai.baas.dst.api.coupon.params.CouponCodeReq;
import com.ai.baas.dst.api.coupon.params.CouponConditionReq;
import com.ai.baas.dst.api.coupon.params.CouponInfoListResponse;
import com.ai.baas.dst.api.coupon.params.CouponInfoReq;
import com.ai.baas.dst.api.coupon.params.CouponListRes;
import com.ai.baas.dst.api.coupon.params.CouponListResponse;
import com.ai.baas.dst.api.coupon.params.CouponPageListRes;
import com.ai.baas.dst.api.coupon.params.CouponReq;
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
import com.ai.baas.dst.api.coupon.params.MyOwnCouponCodeRes;
import com.ai.baas.dst.api.coupon.params.OPChannelInfoListRes;
import com.ai.baas.dst.api.coupon.params.OPCheckCodeStatusReq;
import com.ai.baas.dst.api.coupon.params.OPCheckCodeStatusRes;
import com.ai.baas.dst.api.coupon.params.OPCodeListRes;
import com.ai.baas.dst.api.coupon.params.OPCouponCodeListRes;
import com.ai.baas.dst.api.coupon.params.OPCouponCodeReq;
import com.ai.baas.dst.api.coupon.params.OPCouponInfoReq;
import com.ai.baas.dst.api.coupon.params.OPCouponListRes;
import com.ai.baas.dst.api.coupon.params.OPCouponQueryReq;
import com.ai.baas.dst.api.coupon.params.OPCouponUsedReq;
import com.ai.baas.dst.api.coupon.params.OPSingleCouponRes;
import com.ai.baas.dst.api.coupon.params.QDCouponInfoReq;
import com.ai.baas.dst.api.coupon.params.QDCouponListRes;
import com.ai.baas.dst.api.coupon.params.QDCouponQueryReq;
import com.ai.baas.dst.api.coupon.params.SingleCouponReq;
import com.ai.baas.dst.api.coupon.params.UpQDCouponInfoReq;
import com.ai.baas.dst.api.coupon.params.UseReq;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;

/**
 * 优惠券管理接口
 *
 * Date: 2017年2月16日 <br>
 * Copyright (c) 2017 asiainfo.com <br>
 * @author gaogang
 */
@Path("/coupon")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface ICouponSV {

	/**
	 * 添加优惠券
	 * @param couponInfo
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gaogang
	 * @ApiDocMethod
	 * @ApiCode
	 * @RestRelativeURL coupon/addCoupon
	 */
	@POST
    @Path("/addCoupon")
	BaseResponse addCoupon(CouponInfoReq couponInfo) throws BusinessException,SystemException;
	/**
	 * 优惠券查询
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gaogang
	 * @ApiDocMethod
	 * @ApiCode
	 * @RestRelativeURL coupon/getCouponList
	 */
	@POST
    @Path("/getCouponList")
	CouponListResponse getCouponList(CouponReq req) throws BusinessException,SystemException;
	/**
	 * 查询单个优惠券的类目
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gaogang
	 * @ApiDocMethod
	 * @ApiCode
	 * @RestRelativeURL coupon/getCoupon
	 */
	@POST
    @Path("/getCoupon")
	ExportCouponResponse getCoupon(ExPortReq req) throws BusinessException,SystemException;
	/**
	 * 删除单个优惠券类目
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gaogang
	 * @ApiDocMethod
	 * @ApiCode
	 * @RestRelativeURL coupon/delCouponById 
	 */
	@POST
    @Path("/delCouponById")
	BaseResponse delCouponById(DelReq req) throws BusinessException,SystemException;
	/**
	 * 查询优惠券的信息
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gaogang
	 * @ApiDocMethod
	 * @ApiCode
	 * @RestRelativeURL coupon/getSingleCoupon
	 */
	@POST
    @Path("/getSingleCoupon")
	CouponResponse getSingleCoupon(SingleCouponReq req) throws BusinessException,SystemException;
	/**
	 * 优惠券生效接口
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gaogang
	 * @ApiDocMethod
	 * @ApiCode
	 * @RestRelativeURL coupon/activeCoupon
	 */
	@POST
    @Path("/activeCoupon")
	BaseResponse activeCoupon(ActiveReq req) throws BusinessException,SystemException;
	
	/**
	 * 优惠使用接口
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gaogang
	 * @ApiDocMethod
	 * @ApiCode
	 * @RestRelativeURL coupon/useCoupon
	 */
	@POST
    @Path("/useCoupon")
	BaseResponse useCoupon(UseReq req)  throws BusinessException,SystemException;
	
	/**
	 * 优惠券查询
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gaogang
	 * @ApiDocMethod
	 * @ApiCode
	 * @RestRelativeURL coupon/getGiftCoupons
	 */
	@POST
    @Path("/getGiftCoupons")
	CouponListResponse getGiftCoupons(CouponReq req) throws BusinessException,SystemException;
	
	/**
	 * 强制失效接口
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gaogang
	 * @ApiDocMethod
	 * @ApiCode
	 * @RestRelativeURL coupon/invalidCoupon
	 */
	@POST
    @Path("/invalidCoupon")
	BaseResponse invalidCoupon(InvalidReq req) throws BusinessException,SystemException;
	
	
	
	/**
	 * 优惠券领用接口（针对用户绑定的情况）
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gaogang
	 * @ApiDocMethod
	 * @ApiCode
	 * @RestRelativeURL coupon/getBindCoupon
	 */
	@POST
    @Path("/getBindCoupon")
	DistrResponse getBindCoupon(BaseReq req)  throws BusinessException,SystemException;
	
	/**
	 * 优惠券领用（针对先到先得的情况）
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gaogang
	 * @ApiDocMethod
	 * @ApiCode
	 * @RestRelativeURL coupon/getRamdomCoupon
	 */
	@POST
    @Path("/getRamdomCoupon")
	DistrResponse getRamdomCoupon(BaseReq req)  throws BusinessException,SystemException;
	
	/**
	 * 审核不通过接口
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gaogang
	 * @ApiDocMethod
	 * @ApiCode
	 * @RestRelativeURL coupon/refuseCouon
	 */
	@POST
    @Path("/refuseCouon")
	BaseResponse refuseCouon(BaseReq req) throws BusinessException,SystemException;
	/**
	 * 运营方创建优惠券
	 * @param couponInfo
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	@POST
    @Path("/addOPCoupon")
	BaseResponse addOPCoupon(OPCouponInfoReq couponInfo) throws BusinessException,SystemException;
	
	/**
	 * 渠道方创建优惠券
	 * @param couponInfo
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	@POST
    @Path("/addQDCoupon")
	BaseResponse addQDCoupon(QDCouponInfoReq couponInfo) throws BusinessException,SystemException;
	
	/**
	 * 运营方分页（仅对运营方可见）
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	@POST
    @Path("/getOPCoupons")
	OPCouponListRes getOPCoupons(OPCouponQueryReq req) throws BusinessException,SystemException;
	
	
	
	
	/**
	 * 运营方优惠券详情
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	@POST
    @Path("/getOPSingleCoupon")
	OPSingleCouponRes getOPSingleCoupon(BaseReq req) throws BusinessException,SystemException;
	
	
	
		
	/**
	 * 优惠券详情下的优惠码的查询（分页）
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	@POST
    @Path("/getOPCouponCode")
	OPCodeListRes getOPCouponCode(CouponCodeReq req) throws BusinessException,SystemException;

	/**
	 * 渠道页查看渠道申请的
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	@POST
    @Path("/getQDCoupons")
	QDCouponListRes getQDCoupons(QDCouponQueryReq req) throws BusinessException,SystemException;
	/**
	 * 查看渠道下的优惠码（运营）
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	@POST
    @Path("/getChannelCodes")
	ChannelCodeListRes getChannelCodes(ChannelCodeReq req)throws BusinessException,SystemException;
	
	/**
	 * 渠道商申请优惠券
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	@POST
    @Path("/applyCoupon")
	BaseResponse applyCoupon(ApplyCouponReq req) throws BusinessException,SystemException;
	
	/**
	 * 渠道商申请优惠券
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	@POST
    @Path("/distrCoupon")
	BaseResponse distrCoupon(ApplyCouponReq req) throws BusinessException,SystemException;
	
	/**
	 * 优惠码查询
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author xiaohao
	 */
	@POST
	@Path("/getOPCouponInfoCode")
	OPCouponCodeListRes getOPCouponInfoCode(OPCouponCodeReq req)throws BusinessException,SystemException;
	/**
	 * 优惠码可用校验
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author xiaohao
	 */
	@POST
	@Path("/checkOPCouponCodeForUse")
	OPCheckCodeStatusRes checkOPCouponCodeForUse(OPCheckCodeStatusReq req)throws BusinessException,SystemException;
	/**
	 * 优惠码状态修改
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author xiaohao
	 */
	@POST
	@Path("/changeCouponCodeToUsed")
	BaseResponse changeCouponCodeToUsed(OPCouponUsedReq req) throws BusinessException,SystemException;
	
	
	/**
	 * 渠道查看已经申请得到的优惠券信息（有具体的优惠码的信息）
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	@POST
    @Path("/getExistsCoupons")
	ExistsCouponListRes getExistsCoupons(ExistisCouponReq req) throws BusinessException,SystemException;
	
	/**
	 * 查询客户所属的优惠码
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author xiaohao
	 */
	@POST
	@Path("/getMyOwnCouponCode")
	MyOwnCouponCodeRes getMyOwnCouponCode(MyOwnCodeReq req) throws BusinessException,SystemException;
	
	/*
	 * 优惠码绑定
	 * */
	@POST
	@Path("boundCouponCode")
	BaseResponse boundCouponCode(BoundCouponCodeReq req) throws BusinessException,SystemException;
	
	/**
	 * 查询渠道商申请优惠券
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	@POST
    @Path("/getCouponInfoList")
	CouponInfoListResponse getCouponInfoList(CouponConditionReq req) throws BusinessException,SystemException;
	/**
	 * 优惠码查询导出
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author xiaohao
	 */
	@POST
	@Path("/exportOPCouponCode")
	ExportOPCouponCodeRes exportOPCouponCode(OPCouponCodeReq req) throws BusinessException,SystemException;
	
	/**
	 * 优惠券详情下的渠道商信息查询（所有）
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	@POST
    @Path("/getChannelOPCouponCodeList")
	OPChannelInfoListRes getChannelOPCouponCodeList(CouponChannelReq req) throws BusinessException,SystemException;
	
	/**
	 * 渠道侧优惠券下优惠码导出
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gaogang
	 */
	@POST
    @Path("/getChannelCodesExport")
	ChannelCodeExportRes getChannelCodesExport(ExportCodeReq req)throws BusinessException,SystemException;
	/**
	 * 导出渠道侧已经申请得到的优惠券信息（不分页）
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	@POST
    @Path("/getExistsCouponsExport")
	ExportExistsCouponListRes getExistsCouponsExport(ExportExistisCouponReq req) throws BusinessException,SystemException;
	/**
	 * 渠道查看优惠券详情(优惠码)导出
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author xiaohao
	 */
	@POST
	@Path("/getCodeDetailList")
	ChannelCodeDetailList getCodeDetailList(ChannelCodeReq req) throws BusinessException,SystemException;
	/**
	 * 运营侧优惠券审核页面查询（分页）
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author zhoujw
	 */
	@POST
	@Path("/getCouponAuditPageList")
	CouponAuditPageList getCouponAuditPageList(CouponsAuditInfoReq req) throws BusinessException,SystemException;
	/**
	 * 运营侧优惠券审核页面查询导出（不分页）
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author zhoujw
	 */
	@POST
	@Path("/getCouponAuditListExport")
	CouponAuditListResponse getCouponAuditListExport(CouponsAuditInfoReq req) throws BusinessException,SystemException;
	/**
	 * 运营侧优惠券审核通过生产优惠码
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author zhoujw
	 */
	@POST
	@Path("/auditCouponOP")
	BaseResponse auditCouponOP(AuditCouponsReq req) throws BusinessException,SystemException;
	/**
	 * 自建优惠券且没有被审批生成优惠券的查询（分页）
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author zhoujw
	 */
	@POST
	@Path("/getCouponNotAudit")
	CouponPageListRes getCouponNotAudit(CouponConditionReq req) throws BusinessException,SystemException;
	/**
	 * 自建优惠券且没有被审批生成优惠券的导出（不分页）
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author zhoujw
	 */
	@POST
	@Path("/getCouponNotAuditExport")
	CouponListRes getCouponNotAuditExport(CouponConditionReq req) throws BusinessException,SystemException;
	/**
	 * 自建优惠券且没有被审批生成优惠券的删除操作
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author zhoujw
	 */
	@POST
	@Path("/deleteCouponNotAudit")
	BaseResponse deleteCouponNotAudit(CouponConditionReq req) throws BusinessException,SystemException;
	/**
	 * 自建优惠券且没有被审批生成优惠券的更新操作
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author zhoujw
	 */
	@POST
	@Path("/updateCouponNotAudit")
	BaseResponse updateCouponNotAudit(UpQDCouponInfoReq req) throws BusinessException,SystemException;
	
}
