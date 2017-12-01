package com.ai.baas.dst.service.business.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.dst.api.coupon.params.ActiveReq;
import com.ai.baas.dst.api.coupon.params.ApplyCouponReq;
import com.ai.baas.dst.api.coupon.params.AuditCouponsReq;
import com.ai.baas.dst.api.coupon.params.BaseReq;
import com.ai.baas.dst.api.coupon.params.BoundCouponCodeReq;
import com.ai.baas.dst.api.coupon.params.ChannelCodeDetailsRes;
import com.ai.baas.dst.api.coupon.params.ChannelCodeReq;
import com.ai.baas.dst.api.coupon.params.ChannelCodeVO;
import com.ai.baas.dst.api.coupon.params.ConditionDetail;
import com.ai.baas.dst.api.coupon.params.CouponAtom;
import com.ai.baas.dst.api.coupon.params.CouponAuditInfo;
import com.ai.baas.dst.api.coupon.params.CouponChannelReq;
import com.ai.baas.dst.api.coupon.params.CouponCodeInfoVO;
import com.ai.baas.dst.api.coupon.params.CouponCodeList;
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
import com.ai.baas.dst.constants.DstConstants;
import com.ai.baas.dst.constants.DstConstants.CouponRule;
import com.ai.baas.dst.constants.DstConstants.CouponStatus;
import com.ai.baas.dst.constants.ExceptCodeConstant;
import com.ai.baas.dst.dao.mapper.bo.CouponCode;
import com.ai.baas.dst.dao.mapper.bo.DstCouponAuditInfo;
import com.ai.baas.dst.dao.mapper.bo.DstCouponCode;
import com.ai.baas.dst.dao.mapper.bo.DstCouponInfo;
import com.ai.baas.dst.dao.mapper.bo.DstCouponInfoAndCode;
import com.ai.baas.dst.dao.mapper.bo.DstExportCouponAndCode;
import com.ai.baas.dst.dao.mapper.bo.DstMyOwnCouponCodeInfo;
import com.ai.baas.dst.dao.mapper.bo.OPCouponCode;
import com.ai.baas.dst.dao.mapper.bo.QDCouponCode;
import com.ai.baas.dst.dao.mapper.bo.QDCouponCodes;
import com.ai.baas.dst.service.atom.interfaces.ICouponCodeAtomSV;
import com.ai.baas.dst.service.atom.interfaces.ICouponInfoAtomSV;
import com.ai.baas.dst.service.business.interfaces.ICouponBusiSV;
import com.ai.baas.dst.util.CouponUtil;
import com.ai.baas.dst.util.DstSeqUtil;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.alibaba.fastjson.JSON;

@Service
@Transactional
public class CouponBusiSVImpl implements ICouponBusiSV {
	private static final Logger log = LogManager.getLogger(CouponBusiSVImpl.class);
	@Autowired
	private ICouponInfoAtomSV iCouponInfoAtomSV;
	@Autowired
	private ICouponCodeAtomSV iCouponCodeAtomSV;

	@Override
	public Integer addCoupon(CouponInfoReq couponInfo) {
		DstCouponInfo info = new DstCouponInfo();
		BeanUtils.copyProperties(info, couponInfo);

		info.setCouponCondition(JSON.toJSONString(couponInfo.getConditonDetail()));
		// info.setConditionValue(couponInfo.getCouponConditon());
		info.setStatus(DstConstants.CouponStatus.CREATE);
		info.setCreateTime(DateUtil.getSysDate());

		String couponId = DstSeqUtil.getCouponInfoId();
		info.setCouponId(couponId);
		log.debug(JSON.toJSONString(info));
		iCouponInfoAtomSV.addCouponInfo(info);

		DstCouponCode code = null;
		for (int i = 0; i < Integer.valueOf(couponInfo.getCouponAmount()); i++) {
			code = new DstCouponCode();
			code.setCodeId(DstSeqUtil.getCouponCodeId());
			code.setCouponCode(DstConstants.CouponHead.HEAD + CouponUtil.generateShortUuid());
			code.setCouponId(couponId);
			code.setCouponStatus(DstConstants.CouponStatus.CREATE);
			code.setTenantId(couponInfo.getTenantId());
			iCouponCodeAtomSV.addCouponCode(code);
		}

		return 1;
	}

	@Override
	public PageInfo<CouponInfo> getCouponList(CouponReq req) {
		PageInfo<CouponInfo> pageInfo = new PageInfo<CouponInfo>();
		List<DstCouponInfo> list = iCouponInfoAtomSV.getCouponList(req);
		List<CouponInfo> infoList = new ArrayList<CouponInfo>();
		if (!CollectionUtil.isEmpty(list)) {
			for (int i = 0; i < list.size(); i++) {
				CouponInfo info = new CouponInfo();

				BeanUtils.copyProperties(info, list.get(i));
				info.setIndex(String.valueOf((req.getPageNO() - 1) * req.getPageSize() + 1 + i));
				infoList.add(info);
			}

		}
		pageInfo.setCount(iCouponInfoAtomSV.getCouponCount(req));
		pageInfo.setPageNo(req.getPageNO());
		pageInfo.setPageSize(req.getPageSize());
		pageInfo.setResult(infoList);
		return pageInfo;
	}

	@Override
	public CouponExport getCoupon(ExPortReq req) {
		DstCouponInfo info = null;
		try {
			info = iCouponInfoAtomSV.getCouponInfo(req.getTenantId(), req.getCouponId());
		} catch (Exception e) {
			throw new BusinessException(ExceptCodeConstant.ERROE_OPERATION, "优惠券不存在");
		}

		List<DstCouponCode> codeList = iCouponCodeAtomSV.getCouponCodeList(req.getTenantId(), req.getCouponId());
		CouponExport export = new CouponExport();
		BeanUtils.copyProperties(export, info);
		List<CouponCodeList> list = new ArrayList<CouponCodeList>();
		CouponCodeList ccl = null;
		for (DstCouponCode code : codeList) {
			ccl = new CouponCodeList();
			ccl.setCouponCode(code.getCouponCode());
			ccl.setSingleStatus(code.getCouponStatus());
			list.add(ccl);
		}
		export.setCodeList(list);
		export.setLastTime(
				String.valueOf(DateUtil.getTimeDifference(export.getInactiveTime(), export.getActiveTime())));
		return export;
	}

	@Override
	public Integer delCouponById(DelReq req) {
		int infodel = iCouponInfoAtomSV.delCouponInfo(req.getTenantId(), req.getCouponId());
		int coudel = iCouponCodeAtomSV.delCouponCode(req.getTenantId(), req.getCouponId());
		if (infodel > 0 && coudel > 0) {
			return 1;
		}
		return 0;
	}

	@Override
	public SingleCoupon getSingleCoupon(SingleCouponReq req) {
		// TODO 需要修改，及细化，目前数据不是特别符合接口要求
		SingleCoupon singleCoupon = new SingleCoupon();
		DstCouponInfo info = null;
		try {
			info = iCouponInfoAtomSV.getCouponInfo(req.getTenantId(), req.getCouponId());
		} catch (Exception e) {
			throw new BusinessException(ExceptCodeConstant.ERROE_OPERATION, "优惠券不存在");
		}

		BeanUtils.copyProperties(singleCoupon, info);
		ConditionDetail detail = JSON.parseObject(info.getCouponCondition(), ConditionDetail.class);
		singleCoupon.setConditonDetail(detail);
		singleCoupon.setLastTime(String
				.valueOf(DateUtil.getTimeDifference(singleCoupon.getInactiveTime(), singleCoupon.getActiveTime())));
		return singleCoupon;
	}

	@Override
	public Integer activeCoupon(ActiveReq req) {
		DstCouponInfo dci = null;
		try {
			dci = iCouponInfoAtomSV.getCouponInfo(req.getTenantId(), req.getCouponId());
		} catch (Exception e) {
			throw new BusinessException(ExceptCodeConstant.ERROE_OPERATION, "优惠券不存在");
		}

		String status = dci.getStatus();
		if (!(DstConstants.CouponStatus.CREATE.equals(status))) {
			throw new BusinessException(ExceptCodeConstant.ERROE_OPERATION, "优惠券已经被使用或者已经是生效状态");
		}
		int i = iCouponInfoAtomSV.changeCouponInfo(req.getTenantId(), req.getCouponId(),
				DstConstants.CouponStatus.EFFECTIVE);

		int j = iCouponCodeAtomSV.changeCouponCodeStatus(req.getTenantId(), req.getCouponId(), null,
				DstConstants.CouponStatus.EFFECTIVE);
		if (i > 0 && j > 0) {
			return 1;
		}

		return 0;
	}

	@Override
	public Integer useCoupon(UseReq req) {
		// iCouponInfoAtomSV.changeCouponInfo(req.getTenantId(),
		// req.getCouponCode(),DstConstants.CouponStatus.EFFECTIVE);
		DstCouponCode dstcode = null;
		try {
			dstcode = iCouponCodeAtomSV.getSingleStatus(req.getTenantId(), req.getCouponId(), req.getCouponCode());
		} catch (Exception e) {
			log.error("当前优惠券不存在");
			throw new BusinessException(ExceptCodeConstant.ERROE_OPERATION, "优惠券不存在");
		}

		if (!DstConstants.CouponStatus.EFFECTIVE.equals(dstcode.getCouponStatus())
				&& !DstConstants.CouponStatus.GOT.equals(dstcode.getCouponStatus())) {
			throw new BusinessException(ExceptCodeConstant.ERROE_OPERATION, "优惠券不是生效并且也不是已被领用状态，不能使用");
		}

		DstCouponInfo info = iCouponInfoAtomSV.getCouponInfo(req.getTenantId(), req.getCouponId());
		if (DateUtil.getSysDate().after(info.getInactiveTime()) || DateUtil.getSysDate().before(info.getActiveTime())) {
			throw new BusinessException(ExceptCodeConstant.ERROE_OPERATION, "优惠券不在有效期内，不能使用");
		}
		int ci = iCouponCodeAtomSV.changeCouponCodeStatus(req.getTenantId(), req.getCouponId(), req.getCouponCode(),
				DstConstants.CouponStatus.USED);

		int ecount = iCouponCodeAtomSV.getEffCount(req.getTenantId(), req.getCouponId());

		if (ecount == 0) {
			iCouponInfoAtomSV.changeCouponInfo(req.getTenantId(), req.getCouponId(), DstConstants.CouponStatus.USED);
		}
		return ci;
	}

	@Override
	public PageInfo<CouponInfo> getGiftCoupons(CouponReq req) {
		PageInfo<CouponInfo> pageInfo = new PageInfo<CouponInfo>();
		Timestamp currentDate = DateUtil.getSysDate();
		List<DstCouponInfo> list = iCouponInfoAtomSV.getGiftCoupons(req, currentDate);

		List<CouponInfo> infoList = new ArrayList<CouponInfo>();
		if (!CollectionUtil.isEmpty(list)) {
			for (int i = 0; i < list.size(); i++) {
				CouponInfo info = new CouponInfo();

				BeanUtils.copyProperties(info, list.get(i));
				info.setIndex(String.valueOf((req.getPageNO() - 1) * req.getPageSize() + 1 + i));
				infoList.add(info);
			}

		}
		pageInfo.setCount(iCouponInfoAtomSV.getCouponCounts(req, currentDate));
		pageInfo.setPageNo(req.getPageNO());
		pageInfo.setPageSize(req.getPageSize());
		pageInfo.setResult(infoList);
		return pageInfo;
	}

	@Override
	public Integer invalidByTime(InvalidReq req) {

		try {
			DstCouponInfo dci = iCouponInfoAtomSV.getCouponInfo(req.getTenantId(), req.getCouponId());
			if (DstConstants.CouponStatus.DEL.equals(dci.getStatus())) {
				log.error("优惠券不存在");
				throw new BusinessException(ExceptCodeConstant.ERROE_OPERATION, "优惠券不存在");
			}
		} catch (Exception e) {
			throw new BusinessException(ExceptCodeConstant.ERROE_OPERATION, "优惠券不存在");
		}
		Timestamp invalidTime = null;
		if (req.getTime() != null) {
			invalidTime = req.getTime();
		} else {
			invalidTime = DateUtil.getSysDate();
		}
		return iCouponInfoAtomSV.changeCouponInvalid(req.getTenantId(), req.getCouponId(), invalidTime);
	}

	@Override
	public CouponAtom signGot(BaseReq req) {
		CouponAtom ca = new CouponAtom();
		// int j
		// =iCouponCodeAtomSV.changeCouponCodeStatus(req.getTenantId(),req.getCouponId(),
		// req.getCouponCode(), DstConstants.CouponStatus.GOT);
		DstCouponInfo dci = null;
		try {
			dci = iCouponInfoAtomSV.getCouponInfo(req.getTenantId(), req.getCouponId());
		} catch (Exception e) {
			throw new BusinessException(ExceptCodeConstant.ERROE_OPERATION, "优惠券不存在");
		}

		String status = dci.getStatus();
		if (DstConstants.CouponStatus.USED.equals(status)) {
			throw new BusinessException(ExceptCodeConstant.ERROE_OPERATION, "优惠券已经被使用");
		}
		if (!DstConstants.CouponStatus.EFFECTIVE.equals(status)) {
			throw new BusinessException(ExceptCodeConstant.ERROE_OPERATION, "优惠券不是生效状态或者已经被使用");
		}

		List<DstCouponCode> codeList = iCouponCodeAtomSV.getCanDisCoupon(req.getTenantId(), req.getCouponId());

		if (!CollectionUtil.isEmpty(codeList)) {
			DstCouponCode code = codeList.get(0);
			ca.setCouponCode(code.getCouponCode());
			ca.setCouponId(code.getCouponId());
			iCouponCodeAtomSV.changeCouponCodeStatus(req.getTenantId(), req.getCouponId(), ca.getCouponCode(),
					DstConstants.CouponStatus.GOT);
			// got+used 券的数据量
			int count = iCouponCodeAtomSV.getUsedOrGotCount(req.getTenantId(), req.getCouponId());
			if (Integer.parseInt(dci.getCouponAmount()) == count) {
				iCouponInfoAtomSV.changeCouponInfo(req.getTenantId(), req.getCouponId(),
						DstConstants.CouponStatus.USED);
			}
			return ca;
		}
		return ca;
	}

	@Override
	public CouponAtom RandomCoupon(BaseReq req) {
		CouponAtom ca = new CouponAtom();
		// int j
		// =iCouponCodeAtomSV.changeCouponCodeStatus(req.getTenantId(),req.getCouponId(),
		// req.getCouponCode(), DstConstants.CouponStatus.GOT);
		DstCouponInfo dci = null;
		try {
			dci = iCouponInfoAtomSV.getCouponInfo(req.getTenantId(), req.getCouponId());
		} catch (Exception e) {
			throw new BusinessException(ExceptCodeConstant.ERROE_OPERATION, "优惠券不存在");
		}

		String status = dci.getStatus();
		if (DstConstants.CouponStatus.USED.equals(status)) {
			throw new BusinessException(ExceptCodeConstant.ERROE_OPERATION, "优惠券已经被使用");
		}
		if (!DstConstants.CouponStatus.EFFECTIVE.equals(status)) {
			throw new BusinessException(ExceptCodeConstant.ERROE_OPERATION, "优惠券不是生效状态或者已经被使用");
		}

		List<DstCouponCode> codeList = iCouponCodeAtomSV.getCanDisCoupon(req.getTenantId(), req.getCouponId());

		if (!CollectionUtil.isEmpty(codeList)) {
			DstCouponCode code = codeList.get(0);
			ca.setCouponCode(code.getCouponCode());
			ca.setCouponId(code.getCodeId());
			return ca;
		}
		return ca;
	}

	@Override
	public Integer refuseCoupon(BaseReq req) {
		DstCouponInfo dci = null;
		try {
			dci = iCouponInfoAtomSV.getCouponInfo(req.getTenantId(), req.getCouponId());
		} catch (Exception e) {
			throw new BusinessException(ExceptCodeConstant.ERROE_OPERATION, "优惠券不存在");
		}

		String status = dci.getStatus();
		if (!(DstConstants.CouponStatus.CREATE.equals(status))) {
			throw new BusinessException(ExceptCodeConstant.ERROE_OPERATION, "优惠券已经被使用或者已经是生效状态");
		}
		int i = iCouponInfoAtomSV.changeCouponInfo(req.getTenantId(), req.getCouponId(),
				DstConstants.CouponStatus.REFUSED);

		int j = iCouponCodeAtomSV.changeCouponCodeStatus(req.getTenantId(), req.getCouponId(), null,
				DstConstants.CouponStatus.REFUSED);

		if (i > 0 && j > 0) {
			return 1;
		}

		return 0;
	}

	@Override
	public Integer addOPCoupon(OPCouponInfoReq opCouponInfo) {
		// TODO 运营优惠券的添加
		DstCouponInfo info = new DstCouponInfo();
		BeanUtils.copyProperties(info, opCouponInfo);

		info.setCouponCondition(JSON.toJSONString(opCouponInfo.getConditonDetail()));
		// info.setConditionValue(couponInfo.getCouponConditon());
		info.setStatus(DstConstants.CouponStatus.EFFECTIVE);
		info.setCreateTime(DateUtil.getSysDate());

		String couponId = DstSeqUtil.getCouponInfoId();
		info.setCouponId(couponId);
		info.setCreatorRole(DstConstants.CouponRule.OPERATOR);
		log.debug(JSON.toJSONString(info));
		// iCouponInfoAtomSV.addCouponInfo(info);
		return iCouponInfoAtomSV.addCouponInfo(info);
	}

	@Override
	public Integer addQDCoupon(QDCouponInfoReq couponInfo) {
		DstCouponInfo info = new DstCouponInfo();
		BeanUtils.copyProperties(info, couponInfo);

		info.setCouponCondition(JSON.toJSONString(couponInfo.getConditonDetail()));
		// info.setConditionValue(couponInfo.getCouponConditon());
		info.setStatus(DstConstants.CouponStatus.CREATE);
		info.setCreateTime(DateUtil.getSysDate());

		String couponId = DstSeqUtil.getCouponInfoId();
		info.setCouponId(couponId);
		info.setCreatorRole(DstConstants.CouponRule.CHANNEL);
		log.debug(JSON.toJSONString(info));
		// iCouponInfoAtomSV.addCouponInfo(info);
		return iCouponInfoAtomSV.addCouponInfo(info);
	}

	@Override
	public PageInfo<OPCouponInfo> getCouponList(OPCouponQueryReq req) {

		PageInfo<OPCouponInfo> pageInfo = new PageInfo<OPCouponInfo>();
		List<DstCouponInfo> list = iCouponInfoAtomSV.getCouponList(req);

		List<OPCouponInfo> infoList = new ArrayList<OPCouponInfo>();
		if (!CollectionUtil.isEmpty(list)) {
			for (int i = 0; i < list.size(); i++) {
				OPCouponInfo info = new OPCouponInfo();

				BeanUtils.copyProperties(info, list.get(i));
				// info.setIndex(String.valueOf((req.getPageNO()-1)*req.getPageSize()+1+i));
				infoList.add(info);
			}

		}
		pageInfo.setCount(iCouponInfoAtomSV.getCouponCount(req));
		pageInfo.setPageNo(req.getPageNO());
		pageInfo.setPageSize(req.getPageSize());
		pageInfo.setResult(infoList);
		return pageInfo;

	}

	@Override
	public OPSingleCouponRes getOPSingleCoupon(BaseReq req) {
		OPSingleCouponRes singleCoupon = new OPSingleCouponRes();
		DstCouponInfo info = null;
		try {
			info = iCouponInfoAtomSV.getCouponInfo(req.getTenantId(), req.getCouponId());
		} catch (Exception e) {
			throw new BusinessException(ExceptCodeConstant.ERROE_OPERATION, "优惠券不存在");
		}

		BeanUtils.copyProperties(singleCoupon, info);
		ConditionDetail detail = JSON.parseObject(info.getCouponCondition(), ConditionDetail.class);
		singleCoupon.setConditonDetail(detail);
		singleCoupon.setLastTime(String
				.valueOf(DateUtil.getTimeDifference(singleCoupon.getInactiveTime(), singleCoupon.getActiveTime())));
		return singleCoupon;
	}

	@Override
	public PageInfo<CouponInfoRes> getQDCoupons(QDCouponQueryReq req) {
		PageInfo<CouponInfoRes> pageInfo = new PageInfo<CouponInfoRes>();
		List<DstCouponInfo> list = iCouponInfoAtomSV.getQDCoupons(req);
		List<CouponInfoRes> res = new ArrayList<CouponInfoRes>();
		CouponInfoRes cir = null;
		for (DstCouponInfo info : list) {
			cir = new CouponInfoRes();
			BeanUtils.copyProperties(cir, info);
			res.add(cir);
		}
		pageInfo.setCount(iCouponInfoAtomSV.getQDCouponCount(req));
		pageInfo.setResult(res);
		pageInfo.setPageNo(req.getPageNO());
		pageInfo.setPageSize(req.getPageSize());
		return pageInfo;
	}

	@Override
	public PageInfo<CouponCodeVO> getCodes(CouponCodeReq req) {
		PageInfo<CouponCodeVO> pageInfo = new PageInfo<CouponCodeVO>();
		OPCouponCode code = new OPCouponCode();
		BeanUtils.copyProperties(code, req);
		code.setLimitStart((req.getPageNO() - 1) * req.getPageSize());
		code.setLimitEnd(req.getPageSize());
		List<CouponCode> list = iCouponCodeAtomSV.selectAggreByExample(code);
		List<CouponCodeVO> listvo=new ArrayList<CouponCodeVO>();
		CouponCodeVO vo=null;
		for(CouponCode cc:list){
			vo=new CouponCodeVO();
			BeanUtils.copyProperties(vo, cc);
			listvo.add(vo);
		}
		pageInfo.setResult(listvo);
		pageInfo.setCount(iCouponCodeAtomSV.selectAggreCount(code));
		pageInfo.setPageNo(req.getPageNO());
		pageInfo.setPageSize(req.getPageSize());
		return pageInfo;
	}

	@Override
	public PageInfo<ChannelCodeVO> getChannelCodes(ChannelCodeReq req) {
		PageInfo<ChannelCodeVO> pageInfo=new PageInfo<ChannelCodeVO>();
		ChannelCodeVO vo=null;
		List<ChannelCodeVO> voList=new ArrayList<ChannelCodeVO>();
		List<DstCouponCode>  list=iCouponCodeAtomSV.getChannelCodes(req);
		for(DstCouponCode code:list){
			vo =new ChannelCodeVO();
			BeanUtils.copyProperties(vo, code);
			voList.add(vo);
		}
		pageInfo.setCount(iCouponCodeAtomSV.getChannelCodesCount(req));
		pageInfo.setResult(voList);
		pageInfo.setPageNo(req.getPageNO());
		pageInfo.setPageSize(req.getPageSize());
		return pageInfo;
	}

	@Override
	public Integer distrCoupon(ApplyCouponReq req) {
		// TODO 分配优惠券
		//第一步判断优惠券的数量和允许最大申请量的数据同申请数量的对比
		int applyCount=req.getApplyCount();
		
		DstCouponInfo info=iCouponInfoAtomSV.getCouponInfo(req.getTenantId(),req.getCouponId());
		if(Integer.parseInt(info.getCanApplyCount())<applyCount&&Integer.parseInt(info.getRemainCount())<applyCount){
			throw new BusinessException("666666", "申请数量大于或者优惠券余量不够");
		}
		//满足条件后分配优惠券，并且附上相关信息
		DstCouponCode code=new DstCouponCode();
		code.setChannelAccount(req.getChannelAccount());
		code.setChannelId(req.getChannelId());
		code.setChannelName(req.getChannelName());
		Timestamp now=DateUtil.getSysDate();
		code.setComments(req.getComments());
		code.setCouponId(req.getCouponId());
		code.setCouponStatus(DstConstants.CouponStatus.EFFECTIVE);
		code.setCreateTime(now);
		code.setGetResource(DstConstants.CouponRule.APPLY);
		code.setTenantId(req.getTenantId());
		code.setUpdateTime(now);
		for(int i=0;i<applyCount;i++){
			code.setCouponCode("QD"+CouponUtil.getRandom_Contain_N_C(4));
			code.setCodeId(DstSeqUtil.getCouponCodeId());
			iCouponCodeAtomSV.addCouponCode(code);
		}
		info.setRemainCount(String.valueOf(Integer.parseInt(info.getCouponAmount())-req.getApplyCount()));
		iCouponInfoAtomSV.updateChannelInfo(info);
		return 1;
	}

	@Override
	public PageInfo<OPCouponCodeInfo> getCouponCodeList(OPCouponCodeReq req) {
		PageInfo<OPCouponCodeInfo> pageInfo = new PageInfo<OPCouponCodeInfo>();
		List<DstCouponInfoAndCode> list = iCouponInfoAtomSV.getCouponInfoCode(req);
		List<OPCouponCodeInfo> infoList = new ArrayList<OPCouponCodeInfo>();
		if(!CollectionUtil.isEmpty(list)){
			for(int i=0;i<list.size();i++){
				OPCouponCodeInfo info = new OPCouponCodeInfo();
				BeanUtils.copyProperties(info, list.get(i));
				infoList.add(info);
			}
		}
		pageInfo.setCount(iCouponInfoAtomSV.getCouponInfoCodeCount(req));
		pageInfo.setPageNo(req.getPageNo());
		pageInfo.setPageSize(req.getPageSize());
		pageInfo.setResult(infoList);
		return pageInfo;
	}

	@Override
	public Integer changeCouCodeToUsed(OPCouponUsedReq req) {
		DstCouponCode dstcode = null;
		int result = 0;
		try {
			dstcode = iCouponCodeAtomSV.getSingleCodeForCheck(req.getTenantId(), req.getCouponCode());
		} catch (Exception e) {
			log.error("当前优惠券不存在");
			throw new BusinessException(ExceptCodeConstant.ERROE_OPERATION, "优惠券不存在");
		}
		if (!DstConstants.CouponStatus.EFFECTIVE.equals(dstcode.getCouponStatus())
				&& !DstConstants.CouponStatus.GOT.equals(dstcode.getCouponStatus())) {
			log.error("优惠券不是生效并且也不是已被领用状态，不能使用");
			throw new BusinessException(ExceptCodeConstant.ERROE_OPERATION, "优惠券不是生效并且也不是已被领用状态，不能使用");
		}
		DstCouponInfo info = iCouponInfoAtomSV.getCouponInfo(req.getTenantId(), dstcode.getCouponId());
		if (DateUtil.getSysDate().after(info.getInactiveTime()) || DateUtil.getSysDate().before(info.getActiveTime())) {
			log.error("优惠券不在有效期内，不能使用");
			throw new BusinessException(ExceptCodeConstant.ERROE_OPERATION, "优惠券不在有效期内，不能使用");
		}
		if(!"".equals(dstcode.getServiceId()) && null != dstcode.getServiceId()){
			if(dstcode.getServiceId().equals(req.getServiceId())){
				result = iCouponCodeAtomSV.changeCodeStatusAndServiceId(req.getTenantId(),req.getCouponCode(),DstConstants.CouponStatus.USED,null,req.getOrderId());
			}else{
				log.error("优惠码已经被预定，不可用");
				throw new BusinessException(ExceptCodeConstant.ERROE_OPERATION,"优惠码已经被预定，不可用");
			}
		}
		result = iCouponCodeAtomSV.changeCodeStatusAndServiceId(req.getTenantId(), req.getCouponCode(),DstConstants.CouponStatus.USED , req.getServiceId(),req.getOrderId());
		return result;
	}

	@Override
	public OPCheckCodeStatusRes checkCodeForUsed(OPCheckCodeStatusReq req) {
		DstCouponCode dstcode = null;
		OPCheckCodeStatusRes codeRes = new OPCheckCodeStatusRes();
		try {
			dstcode = iCouponCodeAtomSV.getSingleCodeForCheck(req.getTenantId(), req.getCouponCode());
		} catch (Exception e) {
			log.error("当前优惠券不存在");
			throw new BusinessException(ExceptCodeConstant.ERROE_OPERATION, "优惠券不存在");
		}
		if (!DstConstants.CouponStatus.EFFECTIVE.equals(dstcode.getCouponStatus())
				&& !DstConstants.CouponStatus.GOT.equals(dstcode.getCouponStatus())) {
			throw new BusinessException(ExceptCodeConstant.ERROE_OPERATION, "优惠券不是生效并且也不是已被领用状态，不能使用");
		}
		DstCouponInfo info = iCouponInfoAtomSV.getCouponInfo(req.getTenantId(), dstcode.getCouponId());
		if (DateUtil.getSysDate().after(info.getInactiveTime()) || DateUtil.getSysDate().before(info.getActiveTime())) {
			throw new BusinessException(ExceptCodeConstant.ERROE_OPERATION, "优惠券不在有效期内，不能使用");
		}
		
		if(!"".equals(dstcode.getServiceId()) && null != dstcode.getServiceId()){
			if(dstcode.getServiceId().equals(req.getServiceId())){
				codeRes.setResult(1);
				codeRes.setCouponId(dstcode.getCouponId());
			}else{
				codeRes.setResult(2);
			}
		}else{
			codeRes.setResult(1);
			codeRes.setCouponId(dstcode.getCouponId());
		}
		return codeRes;
	}

	@Override
	public List<MyOwnCouponCodeInfo> getMyOwnCodeInfo(MyOwnCodeReq req) {
		List<DstMyOwnCouponCodeInfo> list = iCouponInfoAtomSV.getMyOwnCodeInfo(req);
		List<MyOwnCouponCodeInfo> infoList = new ArrayList<MyOwnCouponCodeInfo>();
		if(!CollectionUtil.isEmpty(list)){
			for(int i=0;i<list.size();i++){
				MyOwnCouponCodeInfo info = new MyOwnCouponCodeInfo();
				BeanUtils.copyProperties(info, list.get(i));
				infoList.add(info);
			}
		}
		return infoList;
	}
	@Override
	public PageInfo<QDcoupon> getQDExistsCoupon(ExistisCouponReq req) {
		QDCouponCode code=new QDCouponCode();
		BeanUtils.copyProperties(code, req);
		code.setLimitStart((req.getPageNO() - 1) * req.getPageSize());
		code.setLimitEnd(req.getPageSize());
		List<QDCouponCodes> list=iCouponCodeAtomSV.getQDExistsCodes(code);
		int count=iCouponCodeAtomSV.coutQDExistsCodes(code);
		
		PageInfo<QDcoupon>  pageInfo=new PageInfo<QDcoupon>();
		pageInfo.setCount(count);
		pageInfo.setPageNo(req.getPageNO());
		pageInfo.setPageSize(req.getPageSize());
		List<QDcoupon> lists=new ArrayList<QDcoupon>();
		QDcoupon qdc=null;
		
		
		for(QDCouponCodes qc:list){
			 qdc=new QDcoupon();
			BeanUtils.copyProperties(qdc, qc);
			lists.add(qdc);
		}
		pageInfo.setResult(lists);
		
		return pageInfo;
	}

	@Override
	public Integer boundCouponCode(BoundCouponCodeReq req) {
		DstCouponCode dstcode = null;
		try {
			dstcode = iCouponCodeAtomSV.getSingleCodeForCheck(req.getTenantId(), req.getCouponCode());
		} catch (Exception e) {
			log.error("当前优惠券不存在");
			throw new BusinessException(ExceptCodeConstant.ERROE_OPERATION, "优惠券不存在");
		}
		if (!DstConstants.CouponStatus.EFFECTIVE.equals(dstcode.getCouponStatus())) {
			throw new BusinessException(ExceptCodeConstant.ERROE_OPERATION, "优惠券不是生效状态，不能绑定");
		}
		DstCouponInfo info = iCouponInfoAtomSV.getCouponInfo(req.getTenantId(), dstcode.getCouponId());
		if (DateUtil.getSysDate().after(info.getInactiveTime()) || DateUtil.getSysDate().before(info.getActiveTime())) {
			throw new BusinessException(ExceptCodeConstant.ERROE_OPERATION, "优惠券不在有效期内，不能使用");
		}
		return iCouponCodeAtomSV.changeCodeStatusAndServiceId(req.getTenantId(), req.getCouponCode(),DstConstants.CouponStatus.GOT , req.getServiceId(),null);
	}

	@Override
	public PageInfo<CouponResInfo> getCouponList(CouponConditionReq req) {
		PageInfo<CouponResInfo> pageInfo = new PageInfo<CouponResInfo>();
		List<DstCouponInfo> list = iCouponInfoAtomSV.getCouponList(req);
		List<CouponResInfo> infoList = new ArrayList<CouponResInfo>();
		if (!CollectionUtil.isEmpty(list)) {
			for (int i = 0; i < list.size(); i++) {
				CouponResInfo info = new CouponResInfo();

				BeanUtils.copyProperties(info, list.get(i));
				info.setIndex(String.valueOf((req.getPageNO() - 1) * req.getPageSize() + 1 + i));
				infoList.add(info);
			}

		}
		pageInfo.setCount(iCouponInfoAtomSV.getCouponCount(req));
		pageInfo.setPageNo(req.getPageNO());
		pageInfo.setPageSize(req.getPageSize());
		pageInfo.setResult(infoList);
		return pageInfo;
	}

	@Override
	public List<OPCouponCodeInfo> exportCouponCodeInfo(OPCouponCodeReq req) {
		List<DstCouponInfoAndCode> list = iCouponInfoAtomSV.getExportCouponCode(req);
		List<OPCouponCodeInfo> infoList = new ArrayList<OPCouponCodeInfo>();
		if(!CollectionUtil.isEmpty(list)){
			for(int i=0;i<list.size();i++){
				OPCouponCodeInfo info = new OPCouponCodeInfo();
				BeanUtils.copyProperties(info, list.get(i));
				infoList.add(info);
			}
		}
		
		return infoList;
	}

	@Override
	public List<CouponCodeInfoVO> getChannelInfoByCouponAndChannelCondition(CouponChannelReq req) {
		OPCouponCode code = new OPCouponCode();
		BeanUtils.copyProperties(code, req);
		List<CouponCode> list = iCouponCodeAtomSV.selectChannelInfoByExample(code);
		List<CouponCodeInfoVO> listvo=new ArrayList<CouponCodeInfoVO>();
		CouponCodeInfoVO vo=null;
		for(CouponCode cc:list){
			vo=new CouponCodeInfoVO();
			BeanUtils.copyProperties(vo, cc);
			listvo.add(vo);
		}
		return listvo;
	}

	@Override
	public List<ChannelCodeVO> getExpChannelCodes(ExportCodeReq req) {
		
		ChannelCodeVO vo=null;
		List<ChannelCodeVO> voList=new ArrayList<ChannelCodeVO>();
		List<DstCouponCode>  list=iCouponCodeAtomSV.getExpChannelCodes(req);
		for(DstCouponCode code:list){
			vo =new ChannelCodeVO();
			BeanUtils.copyProperties(vo, code);
			voList.add(vo);
		}
		
		return voList;
	}

	@Override
	public List<QDcoupon> getQDExistsCouponList(ExportExistisCouponReq req) {
		QDCouponCode code=new QDCouponCode();
		BeanUtils.copyProperties(code, req);
		List<QDCouponCodes> list=iCouponCodeAtomSV.getQDExistsCodesList(code);
		
		List<QDcoupon> lists=new ArrayList<QDcoupon>();
		QDcoupon qdc=null;
		for(QDCouponCodes qc:list){
			 qdc=new QDcoupon();
			BeanUtils.copyProperties(qdc, qc);
			lists.add(qdc);
		}
		
		return lists;
	}

	@Override
	public List<ChannelCodeDetailsRes> getChannleCodeList(ChannelCodeReq req) {
		List<DstExportCouponAndCode> list = iCouponInfoAtomSV.getExportCouponAndCode(req);
		List<ChannelCodeDetailsRes> infoList = new ArrayList<ChannelCodeDetailsRes>();
		if(!CollectionUtil.isEmpty(list)){
			for(int i=0;i<list.size();i++){
				ChannelCodeDetailsRes  info = new ChannelCodeDetailsRes();
				BeanUtils.copyProperties(info, list.get(i));
				infoList.add(info);
			}
		}
		return infoList;
	}
	@Override
	public PageInfo<CouponAuditInfo> getCouponAuditPageList(CouponsAuditInfoReq req) {
		PageInfo<CouponAuditInfo> pageInfo = new PageInfo<CouponAuditInfo>();
		DstCouponAuditInfo code=new DstCouponAuditInfo();
		BeanUtils.copyProperties(code, req);
		code.setLimitStart((req.getPageNO() - 1) * req.getPageSize());
		code.setLimitEnd(req.getPageSize());
		List<DstCouponInfo> list = iCouponInfoAtomSV.getCouponAuditPageList(code);
		List<CouponAuditInfo> infoList = new ArrayList<CouponAuditInfo>();
		if (!CollectionUtil.isEmpty(list)) {
			for (int i = 0; i < list.size(); i++) {
				CouponAuditInfo info = new CouponAuditInfo();
				BeanUtils.copyProperties(info, list.get(i));
				infoList.add(info);
			}
		}
		pageInfo.setCount(iCouponInfoAtomSV.getCouponAuditCount(code));
		pageInfo.setPageNo(req.getPageNO());
		pageInfo.setPageSize(req.getPageSize());
		pageInfo.setResult(infoList);
		return pageInfo;
	}

	@Override
	public List<CouponAuditInfo> getCouponAuditList(CouponsAuditInfoReq req) {
		DstCouponAuditInfo code=new DstCouponAuditInfo();
		BeanUtils.copyProperties(code, req);
		List<DstCouponInfo> list = iCouponInfoAtomSV.getCouponAuditPageList(code);
		List<CouponAuditInfo> infoList = new ArrayList<CouponAuditInfo>();
		if (!CollectionUtil.isEmpty(list)) {
			for (int i = 0; i < list.size(); i++) {
				CouponAuditInfo info = new CouponAuditInfo();
				BeanUtils.copyProperties(info, list.get(i));
				infoList.add(info);
			}
		}
		return infoList;
	}

	@Override
	public int addAuditCoupon(AuditCouponsReq req) {
		CouponReq r = new CouponReq();
		r.setCouponId(req.getCouponId());
		r.setTenantId(req.getTenantId());
		DstCouponInfo couponInfo = iCouponInfoAtomSV.getCouponInfo(req.getTenantId(),req.getCouponId(),CouponRule.CHANNEL);
		log.info("优惠券信息couponList"+JSON.toJSONString(couponInfo));
		if(couponInfo!=null ){
			if(CouponStatus.EFFECTIVE.equals(req.getStatus()) && CouponStatus.CREATE.equals(couponInfo.getStatus())){
				if(couponInfo.getCouponAmount()!=null){
					int couponCount = Integer.valueOf(couponInfo.getCouponAmount());
					List<DstCouponCode> couponCodeList = iCouponCodeAtomSV.getCouponCodeList(req.getTenantId(),req.getCouponId());
					//生成优惠码数量
					if(couponCodeList!=null && couponCodeList.size()>0){
						couponCount = couponCount - couponCodeList.size();
					}
					for (int i = 0; i < couponCount ; i++) {
						DstCouponCode info = new DstCouponCode();
						info.setCodeId(DstSeqUtil.getCouponCodeId());
						BeanUtils.copyProperties(info, couponInfo);
						String couponCode = CouponUtil.getRandom_Contain_N_C(CouponRule.CHANNEL,4);
						info.setCouponCode(couponCode);
						info.setCouponStatus(CouponStatus.EFFECTIVE);
						info.setChannelId(couponInfo.getCreatorId());
						info.setChannelName(couponInfo.getCreatorName());
						info.setChannelAccount(couponInfo.getCreatorAccount());
						info.setCreateTime(DateUtil.getSysDate());
						log.info("优惠券信息couponCode"+JSON.toJSONString(info));
						int addCouponCode = iCouponInfoAtomSV.addCouponCode(info);
						if(addCouponCode>0){
							couponInfo.setStatus(CouponStatus.EFFECTIVE);
							couponInfo.setComments(req.getComments());
							int updateCoupon = iCouponInfoAtomSV.updateCouponInfo(couponInfo);
							if(updateCoupon>0){
								log.info("创建第"+(i+1)+"个优惠码成功，生成的优惠码Code为"+couponCode);
							}else{
								log.error("更新优惠失败，生成的优惠信息CouponId为"+couponInfo.getCouponId());
								return 0;
							}
						}else{
							log.error("创建优惠码失败，生成的优惠码Code为"+couponCode);
							return 0;
						}
					}
					return 1;
				}
			}else if(CouponStatus.REFUSED.equals(req.getStatus()) && CouponStatus.CREATE.equals(couponInfo.getStatus())){
				couponInfo.setStatus(CouponStatus.REFUSED);
				couponInfo.setComments(req.getComments());
				int updateCoupon = iCouponInfoAtomSV.updateCouponInfo(couponInfo);
				if(updateCoupon>0){
					return 1;
				}else{
					log.error("更新优惠失败，生成的优惠信息CouponId为"+couponInfo.getCouponId());
					return 0;
				}
			}else {
				log.error("审核状态异常");
				return 2;
			}
		}
		log.info("没有查询到优惠券信息");
		return 0;
	}

	@Override
	public PageInfo<QDcoupon> getCouponNotAuditPageList(CouponConditionReq req) {
		PageInfo<QDcoupon> pageInfo = new PageInfo<QDcoupon>();
		req.setStatus(CouponStatus.CREATE);
		req.setCreatorRole(CouponRule.CHANNEL);
		List<DstCouponInfo> list = iCouponInfoAtomSV.getCouponPageList(req);
		List<QDcoupon> infoList = new ArrayList<QDcoupon>();
		if (!CollectionUtil.isEmpty(list)) {
			for (int i = 0; i < list.size(); i++) {
				QDcoupon info = new QDcoupon();
				BeanUtils.copyProperties(info, list.get(i));
				infoList.add(info);
			}

		}
		pageInfo.setCount(iCouponInfoAtomSV.getCouponPageCount(req));
		pageInfo.setPageNo(req.getPageNO());
		pageInfo.setPageSize(req.getPageSize());
		pageInfo.setResult(infoList);
		return pageInfo;
	}
	@Override
	public List<QDcoupon> getCouponNotAuditList(CouponConditionReq req) {
		req.setStatus(CouponStatus.CREATE);
		req.setCreatorRole(CouponRule.CHANNEL);
		List<DstCouponInfo> list = iCouponInfoAtomSV.getCouponAllList(req);
		List<QDcoupon> infoList = new ArrayList<QDcoupon>();
		if (!CollectionUtil.isEmpty(list)) {
			for (int i = 0; i < list.size(); i++) {
				QDcoupon info = new QDcoupon();
				BeanUtils.copyProperties(info, list.get(i));
				infoList.add(info);
			}
		}
		return infoList;
	}

	@Override
	public int deleteCouponNotAudit(CouponConditionReq req) {
		req.setStatus(CouponStatus.CREATE);
		req.setCreatorRole(CouponRule.CHANNEL);
		DstCouponInfo couponInfo = new DstCouponInfo();
		couponInfo.setTenantId(req.getTenantId());
		couponInfo.setCouponId(req.getCouponId());
		couponInfo.setStatus(CouponStatus.DEL);
		return iCouponInfoAtomSV.updateCouponInfo(couponInfo,req);
	}

	@Override
	public int updateQDCoupon(UpQDCouponInfoReq req) {
		req.setStatus(CouponStatus.CREATE);
		req.setCreatorRole(CouponRule.CHANNEL);
		DstCouponInfo info = new DstCouponInfo();
		CouponConditionReq condition = new CouponConditionReq();  
		BeanUtils.copyProperties(condition, req);
		BeanUtils.copyProperties(info, req);
		if(req.getConditonDetail()!=null)
		info.setCouponCondition(JSON.toJSONString(req.getConditonDetail()));
		log.debug(JSON.toJSONString(info));
		return iCouponInfoAtomSV.updateCouponInfo(info,condition);
	}
}
