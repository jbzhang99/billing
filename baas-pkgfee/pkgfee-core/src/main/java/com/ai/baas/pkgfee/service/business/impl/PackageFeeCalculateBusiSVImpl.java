package com.ai.baas.pkgfee.service.business.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.amc.api.deposit.interfaces.IDepositSV;
import com.ai.baas.amc.api.deposit.param.FundFreezeRequest;
import com.ai.baas.pkgfee.api.feecal.params.ChangeConfigParam;
import com.ai.baas.pkgfee.api.feecal.params.FeeCalAddParam;
import com.ai.baas.pkgfee.api.feecal.params.ProductItemVO;
import com.ai.baas.pkgfee.constants.CpPkgfeeConstants;
import com.ai.baas.pkgfee.dao.mapper.bo.CpPackageFee;
import com.ai.baas.pkgfee.dst.CouponActivity;
import com.ai.baas.pkgfee.dst.DiscountActivity;
import com.ai.baas.pkgfee.dst.IActivity;
import com.ai.baas.pkgfee.service.atom.interfaces.ICpPackageFeeAtomSV;
import com.ai.baas.pkgfee.service.atom.interfaces.IDiscountExtAtomSV;
import com.ai.baas.pkgfee.service.atom.interfaces.IDiscountInfoAtomSV;
import com.ai.baas.pkgfee.service.atom.interfaces.IDstCouponInfoAtomSV;
import com.ai.baas.pkgfee.service.business.interfaces.IPackageFeeCalculateBusiSV;
import com.ai.baas.pkgfee.service.entity.DstResult;
import com.ai.baas.pkgfee.service.entity.PkgFeeFundFreeze;
import com.ai.baas.pkgfee.service.entity.PkgFeeRecord;
import com.ai.baas.pkgfee.util.DateUtils;
import com.ai.baas.pkgfee.util.FeeListUtil;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.components.sequence.util.SeqUtil;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.RandomUtil;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

@Service
@Transactional
public class PackageFeeCalculateBusiSVImpl implements IPackageFeeCalculateBusiSV {
	private static Logger logger = LoggerFactory.getLogger(PackageFeeCalculateBusiSVImpl.class);
	
	@Autowired
	ICpPackageFeeAtomSV packageFeeAtomSV;
	@Autowired
	IDiscountInfoAtomSV discountInfoAtomSV;
	@Autowired
	IDiscountExtAtomSV discountExtAtomSV;
	@Autowired
	IDstCouponInfoAtomSV dstCouponInfoAtomSV;

	@Override
	public void addPackageFeeCal(FeeCalAddParam param){
		/**
		 * 1.判断是优惠活动还是优惠券活动
		 * 2.如果是商品优惠:
		 *   1)根据优惠ID分组,
		 * 
		 * 
		 * 3.如果是优惠券:
		 *   1)根据产品ID分组(也就是产品类目),
		 * 
		 * 
		 */
		List<PkgFeeFundFreeze> pkgFeeFundFreezeList = null;
		IActivity iActivity = differDstActivity(param);
		if (iActivity == null) {//没有优惠活动
			logger.info("[此订单没有优惠!]");
			pkgFeeFundFreezeList = withoutDstActivityProcess(param);
		}else{//有优惠活动
			iActivity.setFeeCalParamData(param);
			List<PkgFeeRecord> pkgFeeRecords = iActivity.calculateAmount();
			
//			for(PkgFeeRecord record:pkgFeeRecords){
//				DstResult dstRes = record.getDstResult();
//				if(dstRes != null){
//					System.out.println("--->>"+dstRes.getDstAmount().toPlainString());
//				}else{
//					System.out.println("---1111>>"+record.getProductItem().getProductId());
//				}
//			}
			
			pkgFeeFundFreezeList = hasDstActivityProcess(pkgFeeRecords,param);
		}
		
		if (param.getDeductMode().equalsIgnoreCase(CpPkgfeeConstants.DEDUCT_MODE_PREPAY)) {
			normalFundFreeze(pkgFeeFundFreezeList);
		}
		
	}
	
	/**
	 * 调用资金冻结
	 * @param param
	 * @param pkgFeeList
	 */
	private void normalFundFreeze(List<PkgFeeFundFreeze> pkgFeeFundFreezeList){
		FundFreezeRequest request = null;
		String busiSerialNo = "";
		IDepositSV iDepositSV = DubboConsumerFactory.getService(IDepositSV.class);
		for (PkgFeeFundFreeze pkgFeeFundFreeze : pkgFeeFundFreezeList) {
			request = new FundFreezeRequest();
			request.setTenantId(pkgFeeFundFreeze.getTenantId());
			request.setExtCustId(pkgFeeFundFreeze.getCustId());
			busiSerialNo = Joiner.on("").join(System.currentTimeMillis(), RandomUtil.randomNum(4));
			request.setBusiSerialNo(busiSerialNo);
			//request.setAmount(1l);
			request.setSubjectId(CpPkgfeeConstants.FUND_FREEZE_SUBJECT);
			request.setBusiDesc("包年包月费用冻结");
			request.setSubsId(pkgFeeFundFreeze.getInstanceId());
			request.setAmount(pkgFeeFundFreeze.getAmount());
			
			iDepositSV.fundFreeze(request);
		}
	}
	
	/**
	 * 不带优惠活动处理
	 * @param param
	 */
	private List<PkgFeeFundFreeze> withoutDstActivityProcess(FeeCalAddParam param){
		String tenantId = param.getTenantId();
		String deductMode = param.getDeductMode();
		String custId = param.getCustId();
		List<PkgFeeFundFreeze> feeFundFreezeList = Lists.newArrayList();
		for(ProductItemVO productItemVO:param.getProductList()){
			CpPackageFee packageFee = executePackageFeeCal(productItemVO, null);
			packageFee.setTenantId(tenantId);
			packageFee.setDeductMode(deductMode);
			packageFeeAtomSV.addCpPackageFee(packageFee);
			
			addPkgFeeFundFreeze(feeFundFreezeList,packageFee,productItemVO.getInstanceId(),custId, productItemVO.getProductMode());
		}
		return feeFundFreezeList;
	}
	
	/**
	 * 带优惠活动处理
	 */
	private List<PkgFeeFundFreeze> hasDstActivityProcess(List<PkgFeeRecord> pkgFeeRecords,FeeCalAddParam param){
		List<PkgFeeFundFreeze> feeFundFreezeList = Lists.newArrayList();
		String custId = param.getCustId();
		ProductItemVO productItem = null;
		for (PkgFeeRecord pkgFeeRecord : pkgFeeRecords) {
			productItem = pkgFeeRecord.getProductItem();
			CpPackageFee packageFee = executePackageFeeCal(productItem,pkgFeeRecord.getDstResult());
			packageFee.setTenantId(pkgFeeRecord.getTenantId());
			packageFee.setDeductMode(pkgFeeRecord.getDeductMode());
			packageFeeAtomSV.addCpPackageFee(packageFee);
			
			addPkgFeeFundFreeze(feeFundFreezeList, packageFee, productItem.getInstanceId(), custId, productItem.getProductMode());
		}
		return feeFundFreezeList;
	}
	

	private void addPkgFeeFundFreeze(List<PkgFeeFundFreeze> feeFundFreezeList,CpPackageFee packageFee,String instanceId,
			String custId,String productMode){
		String strFeeList = packageFee.getFeeList();
		FeeListUtil feeUtil = new FeeListUtil();
		feeUtil.parser(strFeeList);
		BigDecimal feeTotal = feeUtil.calFeeListTotal();
		BigDecimal preferentialTotal = feeUtil.calPreferentialTotal();
		//BigDecimal freezeAmount = feeTotal.subtract(preferentialTotal);
		BigDecimal freezeAmount = feeTotal.add(preferentialTotal);
		freezeAmount = freezeAmount.multiply(new BigDecimal(1000)).setScale(0, BigDecimal.ROUND_HALF_UP);
		
		PkgFeeFundFreeze pkgFeeFundFreeze = new PkgFeeFundFreeze();
		pkgFeeFundFreeze.setTenantId(packageFee.getTenantId());
		pkgFeeFundFreeze.setDeductMode(packageFee.getDeductMode());
		pkgFeeFundFreeze.setInstanceId(instanceId);
		pkgFeeFundFreeze.setCustId(custId);
		pkgFeeFundFreeze.setAmount(freezeAmount.longValue());
		
		feeFundFreezeList.add(pkgFeeFundFreeze);
	}
	
	
	private IActivity differDstActivity(FeeCalAddParam param){
		String discountId = "";
		for(ProductItemVO productItemVO:param.getProductList()){
			discountId = StringUtils.defaultString(productItemVO.getDiscountId());
			if (StringUtils.length(discountId) > 0) {
				break;
			}
		}
		String couponId = StringUtils.defaultString(param.getCouponId());
		int dstLen = discountId.length();
		int couponLen = couponId.length();
		IActivity iActivity = null;
		if (dstLen > 0 && couponLen > 0) {
			//iActivity = null;
			throw new BusinessException("PKG-B0001","两种活动都存在,无法计算优惠金额!");
		}else if(dstLen > 0){
			logger.info("[此订单使用产品优惠!]");
			iActivity = new DiscountActivity(discountInfoAtomSV,discountExtAtomSV);
		}else if(couponLen > 0){
			iActivity = new CouponActivity(dstCouponInfoAtomSV);
		}
		return iActivity;
	}
	
	
	private CpPackageFee executePackageFeeCal(ProductItemVO productVO,DstResult dstResult){
		CpPackageFee cpPackageFee = new CpPackageFee();
		//比如:总价为1200元
		//BigDecimal amount = calculateProductAmount(productVO);
		//比如:满1000元减200
		//优惠活动后金额为1000元
		//amount = getDiscountAmount(productVO, amount);
		logger.info("----------产品ID:"+productVO.getProductId()+"的包费计算开始----------");
		try{
			String feeList = getFeeList(productVO, dstResult);
			logger.info("计算费用列表:"+feeList);
			//System.out.println("feeList="+feeList);
			cpPackageFee.setFeeList(feeList);
		}catch(Exception e){
			logger.error("计算费用列表失败", e);
			throw new BusinessException("PKG-B0001","计算费用列表失败!");
		}
		cpPackageFee.setPackageId(SeqUtil.getNewId("CP_PACKAGE_FEE$PACKAGE_ID$SEQ"));
		cpPackageFee.setPriceCode(productVO.getPriceCode());
		cpPackageFee.setActiveTime(DateUtils.str2Timestamp(productVO.getActiveTime()));
		cpPackageFee.setInactiveTime(DateUtils.str2Timestamp(productVO.getInactiveTime()));
		cpPackageFee.setPriceValue(Double.parseDouble(productVO.getProductPrice()));
		cpPackageFee.setPurchaseNum(productVO.getPurchaseNum());
		String purchaseUnit = productVO.getPurchaseUnit();
		cpPackageFee.setPurchaseUnit(purchaseUnit);
		String productMode = productVO.getProductMode();
		String cycleType = CpPkgfeeConstants.PRODUCT_MODE_ONCE;
		if(productMode.equalsIgnoreCase(CpPkgfeeConstants.PRODUCT_MODE_PKG)){
			cycleType = purchaseUnit;
		}
		cpPackageFee.setCycleType(cycleType);
		cpPackageFee.setCycleInterval(1L);
		cpPackageFee.setCalExpr("");
		cpPackageFee.setSubjectCode(productVO.getSubjectCode());
		cpPackageFee.setFactorCode(productVO.getFactorCode());
		cpPackageFee.setCreateType(new Timestamp(System.currentTimeMillis()));
		cpPackageFee.setAutoRenew(productVO.getAutoRenew());
		//cpPackageFee.setUpdateDate(new Timestamp(System.currentTimeMillis()));
		logger.info("**********产品ID:"+productVO.getProductId()+"的包费计算结束**********");
		return cpPackageFee;
	}
	
	/**
	 * 得到应缴费用列表
	 * @param amount 除去优惠后的金额
	 * @return
	 * @throws ParseException 
	 */
	private String getFeeList(ProductItemVO productVO,DstResult dstResult) throws Exception{
		String productMode = productVO.getProductMode();
		String strFeeList = "";
		//一次性费用
		if(productMode.equalsIgnoreCase(CpPkgfeeConstants.PRODUCT_MODE_ONCE)){
			logger.info("此产品使用一次性费用");
			strFeeList = getOnceFeeList(productVO, dstResult);
		//包年包月费用,按月计费
		}else if(productMode.equalsIgnoreCase(CpPkgfeeConstants.PRODUCT_MODE_PKG)){ 
			logger.info("此产品使用按月缴费");
			strFeeList = getEveryMonthFeeList(productVO, dstResult);
		}
		return strFeeList;
	}
	
	/**
	 * 得到一次性费用列表
	 * @param productVO
	 * @param dstResult
	 * @return
	 * @throws Exception
	 */
	private String getOnceFeeList(ProductItemVO productVO,DstResult dstResult) throws Exception{
		BigDecimal productPrice = new BigDecimal(productVO.getProductPrice());
		String purchaseUnit = StringUtils.defaultString(productVO.getPurchaseUnit());
		BigDecimal purchaseNum = null;
		switch (purchaseUnit) {
		case CpPkgfeeConstants.PURCHASE_UNIT_MON:
			purchaseNum = new BigDecimal(productVO.getPurchaseNum());
			break;
		case CpPkgfeeConstants.PURCHASE_UNIT_DAY:
			purchaseNum = new BigDecimal(productVO.getPurchaseNum());
			break;
		case CpPkgfeeConstants.PURCHASE_UNIT_YEAR:
			purchaseNum = new BigDecimal(productVO.getPurchaseNum());
			purchaseNum.multiply(new BigDecimal("12"));
			break;
		default:
			purchaseNum = new BigDecimal("0");
			break;
		}
		BigDecimal total = productPrice.multiply(purchaseNum).setScale(2, BigDecimal.ROUND_HALF_UP);
		FeeListUtil feeUtil = new FeeListUtil();
		String firstMonth = StringUtils.substring(productVO.getActiveTime(), 0, 6);
		feeUtil.addFee(firstMonth, total.toPlainString());
		if (dstResult != null) {
			BigDecimal disAmount = dstResult.getDstAmount();
			if(disAmount != null && disAmount.compareTo(BigDecimal.ZERO) > 0){
				feeUtil.addPreferential(firstMonth, disAmount.multiply(new BigDecimal("-1")).toPlainString());
				feeUtil.setPreferentialSubjectCode(dstResult.getDstSubjectCode());
			}
		}
		return feeUtil.toPlainString();
	}
	
	/**
	 * 得到每月缴费列表
	 * @param productVO
	 * @param dstResult
	 * @return
	 * @throws Exception
	 */
	private String getEveryMonthFeeList(ProductItemVO productVO,DstResult dstResult) throws Exception{
		BigDecimal productPrice = new BigDecimal(productVO.getProductPrice());
		String activeTime = productVO.getActiveTime();
		String inactiveTime = productVO.getInactiveTime();
		//第一次和最后一次应缴费用
		BigDecimal firstFee = null,lastFee = null;
		boolean isFirstDay = DateUtils.isFirstDayOfMonth(activeTime);
		if (isFirstDay) {
			firstFee = productPrice;
			//lastFee = productPrice;
			boolean isLastDay = DateUtils.isLastDayOfMonth(inactiveTime);
			if (isLastDay) {
				lastFee = productPrice;
			}else{
				int daysOfMonth = DateUtils.getDaysOfMonth(inactiveTime);
				BigDecimal priceOfDay = productPrice.divide(new BigDecimal(daysOfMonth), 6, BigDecimal.ROUND_HALF_UP);
				int actualDays = Integer.parseInt(StringUtils.substring(inactiveTime, 4, 6));
				lastFee = priceOfDay.multiply(new BigDecimal(actualDays)).setScale(2, BigDecimal.ROUND_HALF_UP);
			}
		}else{
			int daysOfMonth = DateUtils.getDaysOfMonth(activeTime);
			BigDecimal priceOfDay = productPrice.divide(new BigDecimal(daysOfMonth), 6, BigDecimal.ROUND_HALF_UP);
			//实际天数=有效期起剩余天数+1
			int actualDays = DateUtils.getRemainDays(activeTime)+1;
			logger.info("订购当天到月末实际发生天数："+actualDays);
			firstFee = priceOfDay.multiply(new BigDecimal(actualDays)).setScale(2, BigDecimal.ROUND_HALF_UP);
			logger.info("第一次应缴金额："+firstFee.toPlainString());
			lastFee = productPrice.subtract(firstFee).setScale(2, BigDecimal.ROUND_HALF_UP);
		}		
		//BigDecimal total = firstFee;
		String startDate = StringUtils.substring(activeTime, 0, 6);
		String endDate = StringUtils.substring(productVO.getInactiveTime(), 0, 6);
		//生成年和月的集合201612 201701
		List<String> months = DateUtils.getMonthBetween(startDate, endDate);
		FeeListUtil feeUtil = new FeeListUtil();
		feeUtil.addFee(months.get(0), firstFee.toPlainString());
		int monLen = months.size();
		for (int i = 1; i < monLen - 1; i++) {
			feeUtil.addFee(months.get(i), productPrice.toPlainString());
		}
		feeUtil.addFee(months.get(monLen-1), lastFee.toPlainString());
		//优惠部分
		if (dstResult != null) {
			BigDecimal disAmount = dstResult.getDstAmount();
			if(disAmount != null && disAmount.compareTo(BigDecimal.ZERO) > 0){
				Map<String,String> feeMap = feeUtil.getFeeMap();
				TreeMap<String,String> tempMap = new TreeMap<String,String>();
				tempMap.putAll(feeMap);
				BigDecimal disTotal = new BigDecimal("0.00");
				BigDecimal feeDecimal = null;
				for(Entry<String, String> entry:tempMap.descendingMap().entrySet()){
					System.out.println(entry.getKey()+":"+entry.getValue());
					feeDecimal = new BigDecimal(entry.getValue());
					disTotal = disTotal.add(feeDecimal);
					if(disTotal.compareTo(disAmount) == -1){
						feeUtil.addPreferential(entry.getKey(), feeDecimal.multiply(new BigDecimal("-1")).toPlainString());
					}else{
						BigDecimal remainFee = disTotal.subtract(feeDecimal);
						remainFee = disAmount.subtract(remainFee).setScale(2, BigDecimal.ROUND_HALF_UP);
						feeUtil.addPreferential(entry.getKey(), remainFee.multiply(new BigDecimal("-1")).toPlainString());
						break;
					}
				}
				feeUtil.setPreferentialSubjectCode(dstResult.getDstSubjectCode());
			}
		}
		
		return feeUtil.toPlainString();
	}
	
//	/**
//	 * 得到优惠后的订单金额
//	 * @param amount
//	 * @return
//	 */
//	private BigDecimal getDiscountAmount(ProductItemVO productVO, BigDecimal amount){
//		amount = amount.subtract(new BigDecimal("200"));
//		System.out.println("优惠后金额="+amount.toPlainString());
//		return amount;
//	}

	@Override
	public void changeConfigPkgFeeCal(ChangeConfigParam param) {
		System.out.println(param.getTenantId()+":"+param.getProductId());
		CpPackageFee cpPackageFee = packageFeeAtomSV.queryByPriceCode(param.getTenantId(), param.getProductId());
		if(cpPackageFee == null){
			throw new BusinessException("PKG-B0001","没有找到该用户订购的包费信息!");
		}
		String productMode = cpPackageFee.getCycleType();
		String strFeeList = cpPackageFee.getFeeList();
		FeeListUtil feeUtil = new FeeListUtil();
		feeUtil.parser(strFeeList);
		System.out.println("变更前的费用列表-----");
		System.out.println(feeUtil.getFeeMap().toString());
		System.out.println(feeUtil.getPreferentialMap().toString());
		Double priceValue = cpPackageFee.getPriceValue();
		try {
//			if (productMode.equals(CpPkgfeeConstants.PRODUCT_MODE_ONCE)) {
//			}else{
//				setChangeMonthFee(feeUtil, param, priceValue);
//				recalculateMonthFee(feeUtil, param, cpPackageFee);
//			}
			setChangeMonthFee(feeUtil, param, priceValue);
			recalculateMonthFee(feeUtil, param, cpPackageFee);
		} catch (Exception e) {
			logger.error("变更配置重新计算费用列表失败", e);
			throw new BusinessException("PKG-B0001","变更配置重新计算费用列表失败!");
		}
		System.out.println("配置变更后的费用列表-----");
		System.out.println(feeUtil.getFeeMap().toString());
		System.out.println(feeUtil.getPreferentialMap().toString());
		saveChangeConfig(cpPackageFee, param, feeUtil.toPlainString());
	}
	
	private void saveChangeConfig(CpPackageFee packageFee,ChangeConfigParam param,String feeList){
		CpPackageFee record = new CpPackageFee();
		record.setPackageId(packageFee.getPackageId());
		record.setFeeList(feeList);
		record.setPriceValue(Double.parseDouble(param.getChangeAfterPrice()));
		record.setUpdateDate(DateUtils.str2Timestamp(param.getChangeTime()));
		packageFeeAtomSV.updatePackageFee(record);
	}
	
//	private void recalculateOnceMonthFee(FeeListUtil feeUtil, ChangeConfigParam param, CpPackageFee packageFee){
//		BigDecimal previousFeeTotal = feeUtil.calFeeListTotal();
//		
//		
//		
//	}
//	
//	/**
//	 * 是否是购买当月变更
//	 * @param activeTime
//	 * @param changeTime
//	 * @return
//	 */
//	private boolean isChangeCurrentMonth(String activeTime, String changeTime){
//		String activeYyyyMm = StringUtils.substring(activeTime, 0, 6);
//		String changeYyyyMm = StringUtils.substring(changeTime, 0, 6);
//		if (activeYyyyMm.equals(changeYyyyMm)) {
//			return true;
//		} else {
//			return false;
//		}
//	}
	
	/**
	 * 重新计算剩余月份费用
	 * @param feeUtil
	 * @param param
	 * @throws ParsException 
	 */
	private void recalculateMonthFee(FeeListUtil feeUtil, ChangeConfigParam param, CpPackageFee packageFee) throws Exception {
		String startDate = StringUtils.substring(param.getChangeTime(), 0, 6);
		String inactiveTime = DateUtils.Timestamp2Str(packageFee.getInactiveTime());
		String endDate = StringUtils.substring(inactiveTime, 0, 6);
		String changePrice = param.getChangeAfterPrice();
		List<String> months = DateUtils.getMonthBetween(startDate, endDate);
		int monLen = months.size();
		for (int i = 1; i < monLen - 1; i++) {
			feeUtil.addFee(months.get(i), changePrice);
		}
		String lastFee = "";
		boolean isFirstDay = DateUtils.isFirstDayOfMonth(packageFee.getActiveTime());
		if (isFirstDay) {
			lastFee = changePrice;
		}else{
			String beginOfDay = StringUtils.substring(inactiveTime, 6, 8);
			int daysOfMonth = DateUtils.getDaysOfMonth(inactiveTime);
			BigDecimal newPrice = new BigDecimal(changePrice);
			BigDecimal amount = newPrice.multiply(new BigDecimal(beginOfDay)).setScale(6, BigDecimal.ROUND_HALF_UP);
			amount = amount.divide(new BigDecimal(daysOfMonth), 2, BigDecimal.ROUND_HALF_UP);
			lastFee = amount.toPlainString();
		}
		feeUtil.addFee(months.get(monLen-1), lastFee);
	}
	
	
	/**
	 * 重新计算变更当月费用
	 * @param feeUtil
	 * @param param
	 * @param priceValue
	 */
	private void setChangeMonthFee(FeeListUtil feeUtil,ChangeConfigParam param,Double priceValue) throws Exception{
		String changeTime = param.getChangeTime();
		BigDecimal oldProductPrice = new BigDecimal(priceValue);
		String beginOfDay = StringUtils.substring(changeTime, 6, 8);
		System.out.println("变更时间离月初时间："+beginOfDay);
		int daysOfMonth = DateUtils.getDaysOfMonth(changeTime);
		BigDecimal preFee = oldProductPrice.multiply(new BigDecimal(beginOfDay)).setScale(6, BigDecimal.ROUND_HALF_UP);
		preFee = preFee.divide(new BigDecimal(daysOfMonth), 2, BigDecimal.ROUND_HALF_UP);
		
		BigDecimal newProductPrice = new BigDecimal(param.getChangeAfterPrice());
		int remainDays = DateUtils.getRemainDays(changeTime)+1;
		System.out.println("变更时间离月末时间："+remainDays);
		BigDecimal postFee = newProductPrice.multiply(new BigDecimal(remainDays)).setScale(6, BigDecimal.ROUND_HALF_UP);
		postFee = postFee.divide(new BigDecimal(daysOfMonth), 2, BigDecimal.ROUND_HALF_UP);
		BigDecimal changeMonthTotal = preFee.add(postFee);
		String changeYear = StringUtils.substring(changeTime, 0, 6);
		feeUtil.addFee(changeYear, changeMonthTotal.toPlainString());
		
	}
	
	
	
	
}
