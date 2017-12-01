package com.ai.baas.dst.service.business.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.dst.api.coupon.params.ConditionDetail;
import com.ai.baas.dst.api.couponcal.params.CouponCalRequest;
import com.ai.baas.dst.api.couponcal.params.CouponCalResponse;
import com.ai.baas.dst.api.couponcal.params.ProductReq;
import com.ai.baas.dst.api.discountcal.interfaces.IDiscountCalSV;
import com.ai.baas.dst.constants.DstConstants.CouponStatus;
import com.ai.baas.dst.constants.DstConstants.DiscountInfo.CouponConType;
import com.ai.baas.dst.constants.DstConstants.DiscountInfo.CouponType;
import com.ai.baas.dst.constants.DstConstants.DiscountInfo.DecimalMode;
import com.ai.baas.dst.constants.DstConstants.DiscountInfo.DstTypeUnit;
import com.ai.baas.dst.constants.DstConstants.DiscountInfo.UnitId;
import com.ai.baas.dst.constants.ExceptCodeConstant;
import com.ai.baas.dst.dao.mapper.bo.DstCouponCode;
import com.ai.baas.dst.dao.mapper.bo.DstCouponInfo;
import com.ai.baas.dst.service.atom.interfaces.ICouponCodeAtomSV;
import com.ai.baas.dst.service.atom.interfaces.ICouponInfoAtomSV;
import com.ai.baas.dst.service.business.interfaces.ICouponCalBusiSV;
import com.ai.baas.dst.util.BusinessUtil;
import com.ai.baas.dst.util.NumberUtil;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.fastjson.JSON;

@Service
@Transactional
public class CouponCalBusiSVImpl implements ICouponCalBusiSV{
	private static final Logger logger = LogManager.getLogger(CouponCalBusiSVImpl.class);

	@Autowired
	private ICouponInfoAtomSV iCouponInfoAtomSV;
	
	@Autowired
	private ICouponCodeAtomSV iCouponCodeAtomSV;
	
	@Autowired
	private IDiscountCalSV iDiscountCalSV;
	
	public CouponCalResponse getResultByCouponCal(CouponCalRequest request){
		//设置返回值
		ResponseHeader header = new ResponseHeader();
		//获取总额
		BigDecimal amount = getAllAmount(request.getProducts());
    	//获取优惠卷信息
		DstCouponInfo coupon = getCouponInfoByCode(request.getTenantId(), request.getCouponCode());
		
		CouponCalResponse response = getProsAmountByCoupon(coupon, request.getProducts(), amount);
		
		logger.info("处理成功！！");
    	header.setResultCode(ExceptCodeConstant.SUCCESS);
    	header.setResultMessage("处理成功！");
    	header.setIsSuccess(true);
    	response.setReturnCode(ExceptCodeConstant.SUCCESS);
    	response.setResponseHeader(header);
    	return response;
	}
	
	/**
	 * 获取总金额
	 * @param pros
	 * @return
	 */
	private BigDecimal getAllAmount(List<ProductReq> pros){
		BigDecimal amount = new BigDecimal("0");
		for(ProductReq pro : pros){
			BigDecimal tempValue = (new BigDecimal(pro.getUnitPrice())).multiply(new BigDecimal(pro.getTimeDuration()));
			if(!StringUtil.isBlank(pro.getQuantity())){
				tempValue = tempValue.multiply(new BigDecimal(pro.getQuantity()));
			}
			amount = amount.add(tempValue);
		}
		return amount;
	}
	
	@SuppressWarnings("unchecked")
	private CouponCalResponse getProsAmountByCoupon(DstCouponInfo coupon, 
			                                   		List<ProductReq> pros,
			                                   		BigDecimal allAmount){
		CouponCalResponse response = new CouponCalResponse();
		Boolean isUsed = false;
		//获取到优惠卷对应条件
		ConditionDetail detail = 
				JSON.parseObject(coupon.getCouponCondition(), ConditionDetail.class);
		
		int roundingMode = getDecimalRoundingMode();
		//满减折||立减折
		if((CouponConType.FULLREDUCE.equalsIgnoreCase(coupon.getCouponConType()) && 
				allAmount.compareTo(new BigDecimal(detail.getReachAmount())) >= 0 &&
				DstTypeUnit.DISCOUNT.equalsIgnoreCase(detail.getDstTypeUnit()) &&
				UnitId.DISCOUNT_UNIT.equalsIgnoreCase(detail.getDstUnit()) &&
				UnitId.COST_UNIT.equalsIgnoreCase(detail.getReachUnit())) ||
			(CouponConType.IMREDUCE.equalsIgnoreCase(coupon.getCouponConType()) &&
				DstTypeUnit.DISCOUNT.equalsIgnoreCase(detail.getDstTypeUnit()) &&
				UnitId.DISCOUNT_UNIT.equalsIgnoreCase(detail.getDstUnit()))){
			
			Object[] returnValue = getInfoByDiscount(allAmount, pros, new BigDecimal(detail.getReachAmount()), 
					                                new BigDecimal(detail.getDstValue()), coupon, roundingMode);
			isUsed = Boolean.valueOf(returnValue[0].toString());
			pros = (List<ProductReq>)returnValue[1];
		}
		
		//满减月
		if(CouponConType.FULLREDUCE.equalsIgnoreCase(coupon.getCouponConType()) && 
				   DstTypeUnit.REDUCE.equalsIgnoreCase(detail.getDstTypeUnit()) &&
				   UnitId.MONTH_UNIT.equalsIgnoreCase(detail.getDstUnit()) &&
				   UnitId.MONTH_UNIT.equalsIgnoreCase(detail.getReachUnit())){
			
			Object[] returnValue = getInfoByReduceMonth(pros, new BigDecimal(detail.getReachAmount()), 
					                                    new BigDecimal(detail.getDstValue()), coupon, roundingMode);
			isUsed = Boolean.valueOf(returnValue[0].toString());
			pros = (List<ProductReq>)returnValue[1];
		}
				
		//满减钱||立减钱
		if((CouponConType.FULLREDUCE.equalsIgnoreCase(coupon.getCouponConType()) && 
				allAmount.compareTo(new BigDecimal(detail.getReachAmount())) >= 0 &&
			   DstTypeUnit.REDUCE.equalsIgnoreCase(detail.getDstTypeUnit()) &&
			   UnitId.COST_UNIT.equalsIgnoreCase(detail.getDstUnit()) &&
			   UnitId.COST_UNIT.equalsIgnoreCase(detail.getReachUnit())) || 
		   (CouponConType.IMREDUCE.equalsIgnoreCase(coupon.getCouponConType()) &&
			   DstTypeUnit.REDUCE.equalsIgnoreCase(detail.getDstTypeUnit()) &&
			   UnitId.COST_UNIT.equalsIgnoreCase(detail.getDstUnit()))){
			
			Object[] returnValue = getInfoByReduceMoney(allAmount, pros, new BigDecimal(detail.getReachAmount()), 
					                                    new BigDecimal(detail.getDstValue()), coupon, roundingMode);
			isUsed = Boolean.valueOf(returnValue[0].toString());
			pros = (List<ProductReq>)returnValue[1];
		}
		//如果没有使用优惠卷，价格恢复为传入的价格
		if(!isUsed){
			for (ProductReq pro : pros) {
				BigDecimal currentAmount = getAmountByProduct(pro);
				pro.setCurAmount(currentAmount.setScale(2, roundingMode).toString());
				pro.setOriginalAmount(currentAmount.setScale(2, roundingMode).toString());
				pro.setDiscountAmount((new BigDecimal("0")).setScale(2, roundingMode).toString());
			}
		}
		response.setIsUsed(isUsed);
		response.setProductInfo(pros);
		
		return response;
	}
	
	private Object[] getInfoByReduceMonth(List<ProductReq> pros,
										  BigDecimal reachValue, 
							              BigDecimal reduceValue,  
							              DstCouponInfo coupon, 
							              int roundingMode){
		
		Object[] objReturn = new Object[2];
		boolean isUsed = false;
		
		for(ProductReq pro : pros){
			BigDecimal originalAmount = getAmountByProduct(pro);
			BigDecimal curTimeDuration = null;
			BigDecimal currentAmount = originalAmount;
			
			if(CouponType.ALL.equalsIgnoreCase(coupon.getCouponType()) || 
					(CouponType.APPOINT.equalsIgnoreCase(coupon.getCouponType()) && pro.getProductID().equals(coupon.getProductId()))){
				
				if((new BigDecimal(pro.getTimeDuration()).compareTo(reachValue)) >= 0){
					curTimeDuration = (new BigDecimal(pro.getTimeDuration())).subtract(reduceValue);
					currentAmount = (new BigDecimal(pro.getUnitPrice())).multiply(curTimeDuration);
				}
				if(!StringUtil.isBlank(pro.getQuantity())){
					currentAmount = currentAmount.multiply(new BigDecimal(pro.getQuantity()));
				}
				isUsed = true;
			}
			pro.setCurAmount(currentAmount.setScale(2, roundingMode).toString());
			pro.setOriginalAmount(originalAmount.setScale(2, roundingMode).toString());
			pro.setDiscountAmount(originalAmount.setScale(2, roundingMode).subtract(currentAmount.setScale(2, roundingMode)).toString());
			
		}
		
		objReturn[0] = isUsed;
		objReturn[1] = pros;
		return objReturn;
	}
	
	/**
	 * 满减钱，立减钱的计算
	 * @param allAmount
	 * @param pros
	 * @param reduceValue
	 * @param coupon
	 * @param roundingMode
	 * @return
	 */
	private Object[] getInfoByReduceMoney(BigDecimal allAmount, 
							              List<ProductReq> pros, 
							              BigDecimal reachAmount,
							              BigDecimal reduceValue,  
							              DstCouponInfo coupon, 
							              int roundingMode){
		Object[] objReturn = new Object[2];
		boolean isUsed = false;
		
		if(CouponType.ALL.equalsIgnoreCase(coupon.getCouponType())){
			reduceAmountAll(pros, reduceValue, roundingMode);
			isUsed = true;
		}
		else if(CouponType.APPOINT.equalsIgnoreCase(coupon.getCouponType())){
			for(ProductReq pro : pros){
				if(pro.getProductID().equals(coupon.getProductId())){
					BigDecimal currentAmount = getAmountByProduct(pro);
					reduceValue = currentAmount.compareTo(reachAmount) < 0  ? new BigDecimal(0) : reduceValue;
					
					pro.setOriginalAmount(currentAmount.setScale(2, roundingMode).toString());
					if(currentAmount.compareTo(reduceValue) < 0){
						pro.setCurAmount(new BigDecimal("0").toString());
						pro.setDiscountAmount(currentAmount.setScale(2, roundingMode).toString());
					}else{
						pro.setCurAmount(currentAmount.setScale(2, roundingMode).subtract(reduceValue.setScale(2, roundingMode)).toString());
						pro.setDiscountAmount(reduceValue.setScale(2, roundingMode).toString());
					}
					//若优惠卷信息表对应的类目ID是复数的话，此break可注释
					isUsed = reduceValue.compareTo(new BigDecimal("0")) > 0 ? true : false;
				}else{
					BigDecimal currentAmount = getAmountByProduct(pro);
					pro.setOriginalAmount(currentAmount.setScale(2, roundingMode).toString());
					pro.setCurAmount(currentAmount.setScale(2, roundingMode).toString());
					pro.setDiscountAmount((new BigDecimal("0")).setScale(2, roundingMode).toString());
				}
			}
		}
		objReturn[0] = isUsed;
		objReturn[1] = pros;
		
		return objReturn;
	}
	
	/**
	 * 满减折，立减折的计算
	 * @param allAmount 
	 * @param pros
	 * @param discountValue
	 * @param coupon
	 * @param roundingMode
	 * @return
	 */
	private Object[] getInfoByDiscount(BigDecimal allAmount, 
			                          List<ProductReq> pros, 
			                          BigDecimal reachAmount,
			                          BigDecimal discountValue,  
			                          DstCouponInfo coupon, 
			                          int roundingMode){
		Object[] objReturn = new Object[2];
		boolean isUsed = false;
		//获取折扣数
		BigDecimal disValueTemp = discountValue.divide(new BigDecimal("10"));
		//获取折扣后的价格
		BigDecimal disAmountTemp =disValueTemp.multiply(allAmount);
		
		if(CouponType.ALL.equalsIgnoreCase(coupon.getCouponType())){
			//获取优惠的价格
			BigDecimal reduce = allAmount.subtract(disAmountTemp);

			boolean isDis = true;
			if(!StringUtil.isBlank(coupon.getTopMoney())){
				BigDecimal topReduce = new BigDecimal(coupon.getTopMoney());
				if(topReduce.compareTo(reduce) < 0){
					reduce = topReduce;
					isDis = false;
				}
			}
			if(!isDis){
				reduceAmountAll(pros, reduce, roundingMode);
			}else{
				for(ProductReq pro : pros){
					BigDecimal originalAmount = getAmountByProduct(pro).setScale(2, roundingMode);
					BigDecimal currentAmount = originalAmount.multiply(disValueTemp).setScale(2, roundingMode);
					pro.setOriginalAmount(originalAmount.toString());
					pro.setCurAmount(currentAmount.toString());
					pro.setDiscountAmount(originalAmount.subtract(currentAmount).toString());
				}
			}
			isUsed = true;
		}
		else if(CouponType.APPOINT.equalsIgnoreCase(coupon.getCouponType())){
			for(ProductReq pro : pros){
				if(pro.getProductID().equals(coupon.getProductId())){
					BigDecimal currentAmount = getAmountByProduct(pro);

					BigDecimal reduce = currentAmount.multiply((new BigDecimal("1")).subtract(disValueTemp));
					if(!StringUtil.isBlank(coupon.getTopMoney())){
						BigDecimal topReduce = new BigDecimal(coupon.getTopMoney());
						reduce = topReduce.compareTo(reduce) > 0 ? reduce : topReduce; 
					}
					reduce = currentAmount.compareTo(reachAmount) < 0  ? new BigDecimal(0) : reduce;
					
					pro.setOriginalAmount(currentAmount.setScale(2, roundingMode).toString());
					pro.setCurAmount(currentAmount.setScale(2, roundingMode).subtract(reduce.setScale(2, roundingMode)).toString());
					pro.setDiscountAmount(reduce.setScale(2, roundingMode).toString());
					//若优惠卷信息表对应的类目ID是复数的话，此break可注释
					isUsed = reduce.compareTo(new BigDecimal("0")) > 0 ? true : false;
				}else{
					BigDecimal currentAmount = getAmountByProduct(pro);
					pro.setOriginalAmount(currentAmount.setScale(2, roundingMode).toString());
					pro.setCurAmount(currentAmount.setScale(2, roundingMode).toString());
					pro.setDiscountAmount((new BigDecimal("0")).setScale(2, roundingMode).toString());
				}
			}	
		}
		objReturn[0] = isUsed;
		objReturn[1] = pros;
		return objReturn;
	}
	

	/**
	 * 获取减钱后每个产品的价格
	 * @param lstPros
	 * @param reduce
	 * @param roundingMode
	 * @return
	 */
	private List<ProductReq> reduceAmountAll(List<ProductReq> lstPros, BigDecimal reduce, int roundingMode){
		for(ProductReq pro : lstPros){
			BigDecimal currentAmount = getAmountByProduct(pro);
			pro.setOriginalAmount(currentAmount.setScale(2, roundingMode).toString());
			
			if(currentAmount.compareTo(reduce) >= 0){
				pro.setCurAmount(currentAmount.setScale(2, roundingMode).subtract(reduce.setScale(2, roundingMode)).toString());
				pro.setDiscountAmount(reduce.setScale(2, roundingMode).toString());
				reduce = reduce.subtract(reduce);
			}else{
				pro.setCurAmount((new BigDecimal("0")).setScale(2, roundingMode).toString());
				pro.setDiscountAmount(currentAmount.setScale(2, roundingMode).toString());
				reduce = reduce.subtract(currentAmount);
			}
		}
		return lstPros;
	}
	
	/**
	 * 获取数字的保留小数的模式
	 * @return
	 */
	private int getDecimalRoundingMode(){
		String decimalMode = BusinessUtil.getDecimalModeByConfig();
		int roundingMode = BigDecimal.ROUND_HALF_UP;
		switch(decimalMode){
	    	case DecimalMode.UP:
	    		roundingMode = BigDecimal.ROUND_CEILING;
	    		break;
	    	case DecimalMode.DOWN:
	    		roundingMode = BigDecimal.ROUND_FLOOR;
	    		break;
	    	case DecimalMode.ROUND:
	    		roundingMode = BigDecimal.ROUND_HALF_UP;
	    		break;
		}
		return roundingMode;
	}
	
	/**
	 * 获取该产品的总金额
	 * @param pro
	 * @return
	 */
	private BigDecimal getAmountByProduct(ProductReq pro){
		BigDecimal currentAmount = (new BigDecimal(pro.getUnitPrice())).multiply(new BigDecimal(pro.getTimeDuration()));
		if(!StringUtil.isBlank(pro.getQuantity())){
			currentAmount = currentAmount.multiply(new BigDecimal(pro.getQuantity()));
		}
	
		return currentAmount;
	}
	
	/**
	 * 通过优惠卷ID获取优惠卷信息
	 * @param tenantId
	 * @param couponCode
	 * @return
	 */
	private DstCouponInfo getCouponInfoByCode(String tenantId, String couponCode){
		try{
			DstCouponCode code = iCouponCodeAtomSV.getSingleCodeForCheck(tenantId, couponCode);
			if(!CouponStatus.EFFECTIVE.equals(code.getCouponStatus()) &&
					!CouponStatus.GOT.equals(code.getCouponStatus())){
				throw new BusinessException(ExceptCodeConstant.ERROE_OPERATION, "优惠券不是生效并且也不是已被领用状态，不能使用");
			}
			//需要用当前时间过滤下
			DstCouponInfo coupon =  iCouponInfoAtomSV.getCouponInfo(tenantId, code.getCouponId());
			Timestamp currentTime = DateUtil.getSysDate();
			if(currentTime.getTime() >= coupon.getActiveTime().getTime() && 
			    currentTime.getTime() <= coupon.getInactiveTime().getTime()){
				return coupon;
			}else{
				throw new BusinessException(ExceptCodeConstant.ERROE_OPERATION, "优惠券不在有效期内，不能使用");
			}
			
		}catch(Exception e){
			logger.error("优惠卷获取出现异常！");
			throw new BusinessException(ExceptCodeConstant.ERROE_OPERATION, "优惠券不存在");
		}
	}
	
}
