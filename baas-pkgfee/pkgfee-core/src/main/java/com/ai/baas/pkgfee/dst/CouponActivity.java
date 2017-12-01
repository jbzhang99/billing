package com.ai.baas.pkgfee.dst;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.pkgfee.api.feecal.params.FeeCalAddParam;
import com.ai.baas.pkgfee.api.feecal.params.ProductItemVO;
import com.ai.baas.pkgfee.constants.CpPkgfeeConstants;
import com.ai.baas.pkgfee.dao.mapper.bo.DstCouponInfo;
import com.ai.baas.pkgfee.service.atom.interfaces.IDstCouponInfoAtomSV;
import com.ai.baas.pkgfee.service.entity.CouponCondition;
import com.ai.baas.pkgfee.service.entity.DstResult;
import com.ai.baas.pkgfee.service.entity.PkgFeeRecord;
import com.ai.baas.pkgfee.util.DateUtils;
import com.ai.opt.base.exception.BusinessException;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

/**
 * 优惠券活动
 * @author majun
 *
 */
public class CouponActivity implements IActivity {
	private static Logger logger = LoggerFactory.getLogger(CouponActivity.class);
	private String tenantId;
	private String deductMode;
	private String couponSubjectCode = CpPkgfeeConstants.COUPON_SUBJECT;
	private String couponId;
	private List<ProductItemVO> productItems;
	private IDstCouponInfoAtomSV dstCouponInfoAtomSV;
	
	public CouponActivity(IDstCouponInfoAtomSV dstCouponInfoAtomSV){
		this.dstCouponInfoAtomSV = dstCouponInfoAtomSV;
	}
	
	@Override
	public void setFeeCalParamData(FeeCalAddParam feeCalAddParam) {
		this.tenantId = feeCalAddParam.getTenantId();
		this.deductMode = feeCalAddParam.getDeductMode();
		this.couponId = feeCalAddParam.getCouponId();
		this.productItems = feeCalAddParam.getProductList();
	}
	
	private void addFeeRecord(ProductItemVO productItem, List<PkgFeeRecord> feeRecordList) {
		PkgFeeRecord feeRecord = new PkgFeeRecord();
		feeRecord.setTenantId(tenantId);
		feeRecord.setDeductMode(deductMode);
		feeRecord.setProductItem(productItem);
		
		feeRecordList.add(feeRecord);
	}
	
	private void addFeeRecordWithDst(ProductItemVO productItem, List<PkgFeeRecord> feeRecordList, BigDecimal dstAmount){
		PkgFeeRecord feeRecord = new PkgFeeRecord();
		feeRecord.setTenantId(tenantId);
		feeRecord.setDeductMode(deductMode);
		feeRecord.setProductItem(productItem);
		
		DstResult dstResult = new DstResult();
		dstResult.setDstAmount(dstAmount);
		dstResult.setDstSubjectCode(couponSubjectCode);
		feeRecord.setDstResult(dstResult);
		
		feeRecordList.add(feeRecord);
	}

	@Override
	public List<PkgFeeRecord> calculateAmount() {
		// TODO Auto-generated method stub
		//logger.info("优惠券功能还没有实现,敬请期待...");
		
		DstCouponInfo couponInfo = dstCouponInfoAtomSV.queryByCouponId(couponId, tenantId);
		if (couponInfo == null) {
			throw new BusinessException("PKG-B0002","没有查询到该优惠卷信息,优惠卷ID["+couponId+"]!");
		}
		String couponType = couponInfo.getCouponType();
		List<PkgFeeRecord> feeRecordList = Lists.newArrayList();
		if (couponType.equalsIgnoreCase(CpPkgfeeConstants.COUPON_TYPE_ALL)) {
			dstProductHandler(couponInfo, productItems, feeRecordList);
		}else if(couponType.equalsIgnoreCase(CpPkgfeeConstants.COUPON_TYPE_APPOINT)){
			String appointPrd = couponInfo.getProductId();
			List<ProductItemVO> couponPrdList = Lists.newArrayList();
			for (ProductItemVO productItemVO : productItems) {
				if (appointPrd.equals(productItemVO.getProductId())) {
					couponPrdList.add(productItemVO);
				}else{
					addFeeRecord(productItemVO, feeRecordList);
				}
			}
			if (couponPrdList.size() > 0) {
				dstProductHandler(couponInfo, couponPrdList, feeRecordList);
			}
		}
		return feeRecordList;
	}
	
	/**
	 * 优惠产品处理
	 * @param couponInfo
	 * @param productItemList
	 * @param feeRecordList
	 */
	private void dstProductHandler(DstCouponInfo couponInfo, 
			List<ProductItemVO> productItemList, List<PkgFeeRecord> feeRecordList){
		CouponCondition couponCondition = JSONObject.parseObject(couponInfo.getCouponCondition(), 
				CouponCondition.class);
		System.out.println("dstTypeUnit="+couponCondition.getDstTypeUnit());
		String couponConType = couponInfo.getCouponConType();
		//满减(满折,满减钱,满减月)
		if (couponConType.equalsIgnoreCase(CpPkgfeeConstants.COUPON_CON_TYPE_FULLREDUCE)) {
			fullReduceCal(couponCondition, couponInfo, productItemList, feeRecordList);
		}else if(couponConType.equalsIgnoreCase(CpPkgfeeConstants.COUPON_CON_TYPE_IMREDUCE)){
			imReduceCal(couponCondition, couponInfo, productItemList, feeRecordList);
		}else{
			throw new BusinessException("PKG-B0009","此优惠卷类型不存在["+couponConType+"]");
		}
		
	}
	
	/**
	 * 优惠卷满减(满钱,满月)计算
	 * @param couponCondition
	 * @param productItemList
	 * @param feeRecordList
	 */
	private void fullReduceCal(CouponCondition couponCondition,DstCouponInfo couponInfo,
			List<ProductItemVO> productItemList, List<PkgFeeRecord> feeRecordList) {
		BigDecimal productPrice = null;
		BigDecimal productAmount = null;
		String reachUnit = couponCondition.getReachUnit();
		if (reachUnit.equalsIgnoreCase(CpPkgfeeConstants.COUPON_UNIT_YUAN)) {
			BigDecimal total = new BigDecimal("0.00");
			for (ProductItemVO productItemVO : productItemList) {
				productPrice = new BigDecimal(productItemVO.getProductPrice());
				String purchaseNum = DateUtils.transformPurchaseUnit(productItemVO.getPurchaseUnit(), productItemVO.getPurchaseNum());
				productAmount = productPrice.multiply(new BigDecimal(purchaseNum)).setScale(2, BigDecimal.ROUND_HALF_UP);
				total = total.add(productAmount);
			}
			BigDecimal fullCost = new BigDecimal(couponCondition.getReachAmount());
			if (total.compareTo(fullCost) == 1 || total.compareTo(fullCost) == 0) {
				//fullReduceCal(couponCondition, feeRecordList);
				reduceAmount(couponCondition, total, couponInfo, productItemList, feeRecordList);
			}else{
				for (ProductItemVO productItemVO : productItemList) {
					addFeeRecord(productItemVO, feeRecordList);
				}
			}
		}else if(reachUnit.equalsIgnoreCase(CpPkgfeeConstants.COUPON_UNIT_MONTH)){
			reduceTime(couponCondition, productItemList, feeRecordList);
		}
		
	}
	
	/**
	 * 优惠卷立减
	 * @param couponCondition
	 * @param couponInfo
	 * @param productItemList
	 * @param feeRecordList
	 */
	private void imReduceCal(CouponCondition couponCondition,DstCouponInfo couponInfo,
			List<ProductItemVO> productItemList, List<PkgFeeRecord> feeRecordList){
		BigDecimal productPrice = null;
		BigDecimal productAmount = null;
		String reachUnit = couponCondition.getReachUnit();
		if (reachUnit.equalsIgnoreCase(CpPkgfeeConstants.COUPON_UNIT_YUAN)) {
			BigDecimal total = new BigDecimal("0.00");
			for (ProductItemVO productItemVO : productItemList) {
				productPrice = new BigDecimal(productItemVO.getProductPrice());
				String purchaseNum = DateUtils.transformPurchaseUnit(productItemVO.getPurchaseUnit(), productItemVO.getPurchaseNum());
				productAmount = productPrice.multiply(new BigDecimal(purchaseNum)).setScale(2, BigDecimal.ROUND_HALF_UP);
				total = total.add(productAmount);
			}
			reduceAmount(couponCondition, total, couponInfo, productItemList, feeRecordList);
		}else if(reachUnit.equalsIgnoreCase(CpPkgfeeConstants.COUPON_UNIT_MONTH)){
			reduceTime(couponCondition, productItemList, feeRecordList);
		}
	}
	
	/**
	 * 满减钱计算
	 * @param couponCondition
	 * @param total
	 * @param productItemList
	 * @param feeRecordList
	 */
	private void reduceAmount(CouponCondition couponCondition, BigDecimal total, DstCouponInfo couponInfo,
			List<ProductItemVO> productItemList, List<PkgFeeRecord> feeRecordList){
		BigDecimal dstAmount = null;
		String dstUnit = couponCondition.getDstUnit();
		String dstValue = couponCondition.getDstValue();
		if (dstUnit.equalsIgnoreCase(CpPkgfeeConstants.COUPON_UNIT_DISCOUNT)) {
			BigDecimal dstPercent = new BigDecimal("10");
			BigDecimal ratio = dstPercent.subtract(new BigDecimal(dstValue));
			ratio = ratio.divide(dstPercent, 3, BigDecimal.ROUND_HALF_UP);
			dstAmount = total.multiply(ratio).setScale(2, BigDecimal.ROUND_HALF_UP);
			String topMoneyStr = couponInfo.getTopMoney();
			if (StringUtils.isNotBlank(topMoneyStr)) {
				BigDecimal topMoney = new BigDecimal(topMoneyStr);
				if (dstAmount.compareTo(topMoney) == 1) {
					dstAmount = topMoney;
				}
			}
		}else if(dstUnit.equalsIgnoreCase(CpPkgfeeConstants.COUPON_UNIT_YUAN)){
			//需要补充。。。
			dstAmount = new BigDecimal(dstValue);
		}
		/**
		 * 优惠:300
		 * prd1:130 130
		 * prd2:80   80  
		 * prd3:200  90
		 *  
		 * prd1:400  300 100
		 */
		BigDecimal productPrice = null;
		BigDecimal amount = null;
		BigDecimal result = null;
		String purchaseNum = "";
		boolean isUsedDst= true;
		for (ProductItemVO productItemVO : productItemList) {
			if (isUsedDst) {
				productPrice = new BigDecimal(productItemVO.getProductPrice());
				purchaseNum = DateUtils.transformPurchaseUnit(productItemVO.getPurchaseUnit(), productItemVO.getPurchaseNum());
				amount = productPrice.multiply(new BigDecimal(purchaseNum)).setScale(2, BigDecimal.ROUND_HALF_UP);
				result = amount.subtract(dstAmount);
				if (result.compareTo(BigDecimal.ZERO) == -1) {
					addFeeRecordWithDst(productItemVO, feeRecordList, amount);
					dstAmount = result.multiply(new BigDecimal("-1"));
				}else{
					addFeeRecordWithDst(productItemVO, feeRecordList, dstAmount);
					isUsedDst = false;
				}
			}else{
				addFeeRecord(productItemVO, feeRecordList);
			}
		}
		
	}

	/**
	 * 满减时间计算
	 * @param couponCondition
	 * @param productItemList
	 * @param feeRecordList
	 */
	private void reduceTime(CouponCondition couponCondition, 
			List<ProductItemVO> productItemList, List<PkgFeeRecord> feeRecordList){
		BigDecimal productPrice = null;
		BigDecimal dstAmount = null;
		String dstMonth = "",purchaseNum = "";
		BigDecimal fullMonth = new BigDecimal(couponCondition.getReachAmount());
		BigDecimal purchaseMonth = null;
		String dstUnit = couponCondition.getDstUnit();
		for (ProductItemVO productItemVO : productItemList) {
			purchaseNum = DateUtils.transformPurchaseUnit(productItemVO.getPurchaseUnit(), productItemVO.getPurchaseNum());
			purchaseMonth = new BigDecimal(purchaseNum);
			if (purchaseMonth.compareTo(fullMonth) == 1 || purchaseMonth.compareTo(fullMonth) == 0) {
				if (dstUnit.equalsIgnoreCase(CpPkgfeeConstants.COUPON_UNIT_MONTH)) {
					productPrice = new BigDecimal(productItemVO.getProductPrice());
					dstMonth = couponCondition.getDstValue();
					dstAmount = productPrice.multiply(new BigDecimal(dstMonth)).setScale(2, BigDecimal.ROUND_HALF_UP);
					addFeeRecordWithDst(productItemVO, feeRecordList, dstAmount);
				}else{
					//满月减钱,暂不支持!
				}
			}else{
				addFeeRecord(productItemVO, feeRecordList);
			}
		}
	}

	/**
	 * 是订单优惠还是针对每个产品实例
	 * @param couponCondition
	 * @return
	 */
	private boolean checkIfCombineCal(CouponCondition couponCondition){
		String reachUnit = couponCondition.getReachUnit();
		String dstUnit = couponCondition.getDstUnit();
		if (reachUnit.equalsIgnoreCase(CpPkgfeeConstants.COUPON_UNIT_MONTH)
				&& dstUnit.equalsIgnoreCase(CpPkgfeeConstants.COUPON_UNIT_MONTH)) {
			return false;
		}else{
			return true;
		}
	}
	
	
//	@Override
//	public List<PkgFeeRecord> calculateAmount(FeeCalAddParam param) {
//		// TODO Auto-generated method stub
//
//		return null;
//	}

	
	
}
