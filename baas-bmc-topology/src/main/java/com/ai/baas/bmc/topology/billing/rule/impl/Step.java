package com.ai.baas.bmc.topology.billing.rule.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.bmc.topology.billing.container.IntervalContainer;
import com.ai.baas.bmc.topology.billing.rule.IBilling;
import com.ai.baas.bmc.topology.billing.util.UnitConverter;
import com.ai.baas.bmc.topology.cache.ChargeCache;
import com.ai.baas.bmc.topology.entity.BillingPriceDetail;
import com.ai.baas.bmc.topology.entity.Interval;
import com.ai.baas.bmc.topology.entity.MatchResult;
import com.ai.baas.bmc.topology.entity.SubjectAndPrice;
import com.ai.baas.bmc.topology.util.BmcConstants;
import com.ai.baas.storm.exception.BusinessException;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;


public class Step implements IBilling{
	//private IntervalMatcher rangeMatcher = new IntervalMatcher();
	private static Logger logger = LoggerFactory.getLogger(Step.class);
	private ChargeCache chargeCache;
	private UnitConverter unitConverter = new UnitConverter();
	
	
	public Step(ChargeCache chargeCache){
		this.chargeCache = chargeCache;
	}

	@Override
	public SubjectAndPrice calculate(BillingPriceDetail priceDetail) throws BusinessException{
		Map<String, String> data = priceDetail.getData();
		String inputDuration = data.get(BmcConstants.DURATION);
		if(StringUtils.isBlank(inputDuration)){
			//需要拋出异常信息
			throw new BusinessException("BMC-B0010","原始报文中没有找到时长字段值!");
		}
		String detailCode = priceDetail.getPriceDetail().get(BmcConstants.DETAIL_CODE);
		List<Map<String,String>> stepDatas = chargeCache.getStepInfoData(detailCode);
		if (stepDatas == null || stepDatas.size() == 0) {
			logger.error("[cp_step_info在缓存没有找到对应的detail_code={0}]", detailCode);
			//return null;
			throw new BusinessException("BMC-B0011","[在缓存表cp_step_info中没有找到对应的detail_code值]");
		}
		String unitType = stepDatas.get(0).get(BmcConstants.UNIT_TYPE);
		//根据单位类型转换后的时长
		String duration = unitConverter.convert(unitType, inputDuration);
		//String duration = inputDuration;
		if(StringUtils.isBlank(unitType)){
			throw new BusinessException("BMC-B0012","该阶梯没有配置UNIT_TYPE!");
		}
		//累计后值
		Double cumulativeAfterVal = chargeCache.getAddupAfter(data, unitType, duration);
		//累计前值
		double cumulativeBeforeVal = cumulativeAfterVal.doubleValue() - Double.parseDouble(duration);
		
		List<Double> matchValues = Lists.newArrayList(new Double(cumulativeBeforeVal),cumulativeAfterVal);
		List<Interval> intervals = IntervalContainer.getIntervalsByKey(stepDatas);
		//String detailCode = stepDatas.get(0).get(BmcConstants.DETAIL_CODE);
		List<MatchResult> matchResults = getMatcher(intervals, matchValues, detailCode);
		BigDecimal total = this.doCal(matchResults);
		SubjectAndPrice subjectAndPrice = null;
		if (total != null) {
			subjectAndPrice = new SubjectAndPrice(stepDatas.get(0).get(BmcConstants.SUBJECT_CODE),total);
		}
		
//		RangeMatchValue rangeMatchValue = rangeMatcher.getMatchPrice(stepDatas, durationAfter);
//		SubjectAndPrice subjectAndPrice = null;
//		if(rangeMatchValue != null){
//			double real_duration = durationAfter.doubleValue() - rangeMatchValue.getUpper().doubleValue();
//			BigDecimal price = rangeMatchValue.getPrice();
//			price.multiply(new BigDecimal(real_duration));
//			subjectAndPrice = new SubjectAndPrice(stepDatas.get(0).get(BmcConstants.SUBJECT_CODE),price);
//		}
//		
		return subjectAndPrice;
	}
	
	
	
	private BigDecimal doCal(List<MatchResult> matchResults){
		BigDecimal total = null;
		switch (getIntervalType(matchResults)) {
		case BmcConstants.STEP_SAME_INTERVAL:
			total = sameIntervalCal(matchResults);
			break;
		case BmcConstants.STEP_CONNECTED_INTERVAL:
			total = connectedIntervalCal(matchResults);
			break;
		case BmcConstants.STEP_UNCONNECTED_INTERVAL:
			total = unConnectedIntervalCal(matchResults);
			break;
		default:
			break;
		}
		return total;
	}
	
	/**
	 * 累计前和累计后值在同一区间内计算单价
	 * @param matchResults
	 * @return
	 */
	private BigDecimal sameIntervalCal(List<MatchResult> matchResults){
		MatchResult matchBefore = matchResults.get(0);
		MatchResult matchAfter = matchResults.get(1);
		double accumulateBeforeVal = matchBefore.getMatchValue().doubleValue();
		double accumulateAfterVal = matchAfter.getMatchValue().doubleValue();
		BigDecimal price,total=null;
		//在阶梯范围内,按阶梯单价计算
		if (matchBefore.isWithin()) {
			double currentValue = accumulateAfterVal - accumulateBeforeVal;
			price = matchAfter.getInterval().getPrice();
			total = price.multiply(new BigDecimal(currentValue));
		} else {
			logger.debug("[累计后超出阶梯范围,目前没有实现超过部分资费计算过程]");
		}
		return total;
	}
	
	/**
	 * 累计前和累计后在相邻区间计算单价
	 * @param matchResults
	 * @return
	 */
	private BigDecimal connectedIntervalCal(List<MatchResult> matchResults){
		BigDecimal total = null;
		MatchResult matchBefore = matchResults.get(0);
		MatchResult matchAfter = matchResults.get(1);
		if (matchBefore.isWithin() && matchAfter.isWithin()) {
			BigDecimal beforeVal = intervalWithinBeforeValCal(matchBefore);
//			Interval interval = matchAfter.getInterval();
//			double lower = interval.getRange().lowerEndpoint().doubleValue();
//			double prevUpper = interval.getPrev().getRange().upperEndpoint().doubleValue();
//			double intervalDiffVal = prevUpper - lower;
//			double afterValue = matchAfter.getMatchValue().doubleValue();
//			double value = afterValue - lower - intervalDiffVal;
//			BigDecimal price = interval.getPrice();
//			total = price.multiply(new BigDecimal(value)).add(beforeVal);
			BigDecimal afterVal = intervalWithinAfterValCal(matchAfter);
			total = beforeVal.add(afterVal);
		}else{
			logger.debug("[累计后超出阶梯范围,目前没有实现超过部分资费计算方式!]");
		}
		return total;
	}
	
	/**
	 * 计算区间内累计前值的价格
	 * @param matchBefore
	 * @return
	 */
	private BigDecimal intervalWithinBeforeValCal(MatchResult matchBefore){
		Interval interval = matchBefore.getInterval();
		double upper = interval.getRange().upperEndpoint().doubleValue();
		double beforeValue = matchBefore.getMatchValue().doubleValue();
		double value = upper - beforeValue;
		BigDecimal price = interval.getPrice();
		return price.multiply(new BigDecimal(value));
	}
	
	/**
	 * 计算区间内累计后值的价格
	 * @param matchAfter
	 * @return
	 */
	private BigDecimal intervalWithinAfterValCal(MatchResult matchAfter){
		Interval interval = matchAfter.getInterval();
		double lower = interval.getRange().lowerEndpoint().doubleValue();
		double prevUpper = interval.getPrev().getRange().upperEndpoint().doubleValue();
		//double intervalDiffVal = prevUpper - lower;
		double intervalDiffVal = lower - prevUpper;
		double afterValue = matchAfter.getMatchValue().doubleValue();
		double value = afterValue - lower + intervalDiffVal;
		BigDecimal price = interval.getPrice();
		return price.multiply(new BigDecimal(value));
	}
	
	/**
	 * 区间内中间值计算
	 * @param matchBefore
	 * @param matchAfter
	 * @return
	 */
	private BigDecimal intervalWithinMiddleValCal(MatchResult matchBefore, MatchResult matchAfter){
		String detailCode = matchAfter.getDetailCode();
		int from = matchBefore.getInterval().getOrder().intValue()+1;
		int to = matchAfter.getInterval().getOrder().intValue()-1;
		List<Interval> intervals = IntervalContainer.getIntervalsByFromAndTo(detailCode, from, to);
		BigDecimal amount = new BigDecimal(new Double(0));
		double lower,upper,prevUpper,intervalDiffVal,subtract;
		for(Interval interval:intervals){
			lower = interval.getRange().lowerEndpoint().doubleValue();
			prevUpper = interval.getPrev().getRange().upperEndpoint().doubleValue();
			//intervalDiffVal = prevUpper - lower;
			intervalDiffVal = lower - prevUpper;
			upper = interval.getRange().upperEndpoint().doubleValue();
			subtract = upper - lower + intervalDiffVal;
			amount = amount.add(interval.getPrice().multiply(new BigDecimal(subtract)));
		}
		System.out.println("amount=="+amount.doubleValue());
		return amount;
	}
	
	private BigDecimal unConnectedIntervalCal(List<MatchResult> matchResults){
		BigDecimal total=null;
		MatchResult matchBefore = matchResults.get(0);
		MatchResult matchAfter = matchResults.get(1);
		if (matchBefore.isWithin() && matchAfter.isWithin()) {
			BigDecimal beforeVal = intervalWithinBeforeValCal(matchBefore);
			BigDecimal middleVal = intervalWithinMiddleValCal(matchBefore, matchAfter);
			BigDecimal afterVal = intervalWithinAfterValCal(matchAfter);
			total = beforeVal.add(middleVal).add(afterVal);
		}else{
			logger.debug("[累计后超出阶梯范围,目前没有实现超过部分资费计算过程]");
		}
		return total;
	}
	
	
	/**
	 * 两个累计值的区间类型
	 * (同一区间、相邻区间、不相邻区间)
	 * @param matchResults
	 * @return
	 */
	private String getIntervalType(List<MatchResult> matchResults){
		String intervalType = "";
		MatchResult matchResult = matchResults.get(0);
		Interval interval = matchResult.getInterval();
		int beforeOrder;
		if (interval != null) {
			beforeOrder = interval.getOrder();
			matchResult.setWithin(true);
		} else {
			beforeOrder = IntervalContainer.getIntervalMaxOrder(matchResult.getDetailCode()) + 1;
			matchResult.setWithin(false);
		}
		matchResult = matchResults.get(1);
		interval = matchResult.getInterval();
		int afterOrder;
		if (interval != null) {
			afterOrder = interval.getOrder();
			matchResult.setWithin(true);
		} else {
			afterOrder = IntervalContainer.getIntervalMaxOrder(matchResult.getDetailCode()) + 1;
			matchResult.setWithin(false);
		}
		int result = afterOrder - beforeOrder;
		if(result == 0){
			intervalType = BmcConstants.STEP_SAME_INTERVAL;
		}else if(result == 1){
			intervalType = BmcConstants.STEP_CONNECTED_INTERVAL;
		}else{
			intervalType = BmcConstants.STEP_UNCONNECTED_INTERVAL;
		}
		return intervalType;
	}
	
	
	/**
	 * 累计前和累计后的值匹配区间
	 * @param intervals
	 * @param matchValues
	 *      0:累计前 值   1:累计后值
	 * @param detail_code
	 * @return
	 */
	private List<MatchResult> getMatcher(List<Interval> intervals,List<Double> matchValues,String detail_code){
		List<MatchResult> matchResults = Lists.newArrayList();
		MatchResult matchResult = null;
		for(Double matchValue:matchValues){
			matchResult = new MatchResult();
			matchResult.setMatchValue(matchValue);
			matchResult.setDetailCode(detail_code);
			matchResults.add(matchResult);
		}
		Range<Double> range;
		int flag = 0;
		for(Interval interval:intervals){
			if(flag == matchResults.size()){
				break;
			}
			range = interval.getRange();
			for(MatchResult result:matchResults){
				if(range.contains(result.getMatchValue())){
					result.setInterval(interval);
					flag++;
				}
			}
		}
		return matchResults;
	}
	
}
