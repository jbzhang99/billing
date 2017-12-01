package com.ai.baas.bmc.srv.flow.billing.rule.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.bmc.srv.entity.BillingPriceParam;
import com.ai.baas.bmc.srv.entity.BillingPriceResult;
import com.ai.baas.bmc.srv.entity.Interval;
import com.ai.baas.bmc.srv.entity.MatchResult;
import com.ai.baas.bmc.srv.entity.SubjectAndPrice;
import com.ai.baas.bmc.srv.failbill.BusinessException;
import com.ai.baas.bmc.srv.flow.billing.container.IntervalContainer;
import com.ai.baas.bmc.srv.flow.billing.rule.IBilling;
import com.ai.baas.bmc.srv.flow.cache.ChargeCache;
import com.ai.baas.bmc.srv.util.BaasConstants;
import com.ai.baas.bmc.srv.util.DateUtil;
import com.ai.baas.bmc.srv.util.UnitConverter;
import com.google.common.base.Joiner;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;

/**
 * 阶梯计费
 * 
 * @author mj
 * @since 2016.7.1
 * 
 */
public class Step implements IBilling {
	private static Logger logger = LoggerFactory.getLogger(Step.class);
	private ChargeCache chargeCache = new ChargeCache();
	private UnitConverter unitConverter = new UnitConverter();

	@Override
	public boolean checkPrice(String detailCode, Map<String, String> data) {
		boolean isSucc = false;
		List<Map<String, String>> stepDatas = chargeCache
				.getStepInfoData(detailCode);
		if (stepDatas != null && stepDatas.size() > 0) {
			String factorCode = stepDatas.get(0).get(BaasConstants.FACTOR_CODE);
			if (chargeCache.isMatchFactorCode(factorCode, data)) {
				isSucc = true;
			}
		}
		return isSucc;
	}

	@Override
	public BillingPriceResult calculate(BillingPriceParam priceParam)
			throws BusinessException, Exception {
		BillingPriceResult priceResult = new BillingPriceResult();
		Map<String, String> priceDetail = priceParam.getPriceDetails().get(0);
		String detailCode = priceDetail.get(BaasConstants.DETAIL_CODE);
		List<Map<String, String>> stepDatas = chargeCache
				.getStepInfoData(detailCode);
		if (stepDatas == null || stepDatas.size() == 0) {
			throw new BusinessException("BMC-B0015",
					"[在缓存表cp_step_info中没有找到对应的detail_code值]");
		}
		String unitType = stepDatas.get(0).get(BaasConstants.UNIT_TYPE);
		if (StringUtils.isBlank(unitType)) {
			throw new BusinessException("BMC-B0016", "该阶梯没有配置UNIT_TYPE!");
		}
		// usage amount
		String usageAmountIn = priceParam.getUsageAmount();
		//System.out.println("[阶梯计费,转换前使用量]-->>"+usageAmountIn);
		// 根据单位类型转换后的时长
		String usageAmount = unitConverter.convertToAdvanced(unitType,
				usageAmountIn);
		//System.out.println("[阶梯计费,转换后使用量]-->>"+usageAmountIn);
		// System.out.println("转换后时长="+usageAmount);
		// String duration = inputDuration;
		Map<String, String> data = priceParam.getData();
		stepDatas = filterIntervals(stepDatas, data);
		String calType = stepDatas.get(0).get(BaasConstants.CAL_TYPE);
		// logger.info("stepDatas(0)="+stepDatas.get(0));
		SubjectAndPrice subjectAndPrice = null;
		if ("C".equalsIgnoreCase(calType)) {
			subjectAndPrice = accordingToCumulativeCalculate(data, unitType,
					usageAmount, stepDatas);
		} else if ("F".equalsIgnoreCase(calType)) {
			subjectAndPrice = accordingToFixedCalculate(data, usageAmount,
					stepDatas);
		} else if ("S".equalsIgnoreCase(calType)) {
			subjectAndPrice = accordingToSwitchCalculate(data, usageAmount,
					stepDatas);
		} else if ("T".equalsIgnoreCase(calType)){//按总量,不累计,按阶梯计费
			System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
			subjectAndPrice = accordingToTotalCalculate(data, usageAmount, stepDatas);
		}
		// priceResult.setSubjectAndPrice(subjectAndPrice);
		if (subjectAndPrice != null) {
			subjectAndPrice.setPriceCode(priceDetail
					.get(BaasConstants.PRICE_CODE));
		}
		priceResult.addSubjectAndPrices(subjectAndPrice);
		return priceResult;
	}

	/**
	 * 
	 * 过滤区间段
	 * 
	 * @param stepDatas
	 * @param data
	 * @return
	 */
	private List<Map<String, String>> filterIntervals(
			List<Map<String, String>> stepDatas, Map<String, String> data) {
		// logger.info("过滤区间段开始。。。。");
		String extCode = stepDatas.get(0).get(BaasConstants.EXT_CODE);
		// logger.info("extCode="+extCode);
		if (StringUtils.isBlank(extCode) || extCode.equalsIgnoreCase("NULL")) {
			return stepDatas;
		}
		// 按照STEP_GROUP字段先分组,再按照EXT_CODE过滤
		ListMultimap<String, Map<String, String>> groups = ArrayListMultimap
				.create();
		String groupId = "";
		for (Map<String, String> stepData : stepDatas) {
			groupId = stepData.get(BaasConstants.STEP_GROUP);
			if (StringUtils.isBlank(groupId)
					|| groupId.equalsIgnoreCase("NULL")) {
				groupId = stepData.get(BaasConstants.DETAIL_CODE);
			}
			groups.put(groupId, stepData);
		}
		// logger.info("groupId="+groupId);
		List<Map<String, String>> rtnValues = null;
		List<Map<String, String>> datas, extInfoDatas;
		// 按照组进行扩展因素匹配
		for (String key : groups.keySet()) {
			datas = groups.get(key);
			Map<String, String> tmpData = datas.get(0);
			// logger.info("data(0)="+datas.get(0));
			extCode = tmpData.get(BaasConstants.EXT_CODE);
			// logger.info("extCode="+extCode);
			extInfoDatas = chargeCache.getExtInfoValue(
					data.get(BaasConstants.TENANT_ID), "STEP", extCode);
			if (extInfoDatas != null && extInfoDatas.size() > 0) {
				String extName = extInfoDatas.get(0)
						.get(BaasConstants.EXT_NAME);
				String extValue = extInfoDatas.get(0).get(
						BaasConstants.EXT_VALUE);
				// logger.info("extName="+extName);
				// logger.info("extValue="+extValue);
				if ("TIME_SEG".equalsIgnoreCase(extName)) {
					if (matchBusyLeisureTime(extValue,
							data.get(BaasConstants.START_TIME))) {
						rtnValues = datas;
						break;
					}
				} else if ("REQ_TYPE".equalsIgnoreCase(extName)) {
					if (matchRequestType(extValue, data.get("request_type"))) {
						rtnValues = datas;
						break;
					}
				}
			}
		}
		//logger.info("过滤区间段结束----" + rtnValues);
		return rtnValues;
	}

	/**
	 * 匹配请求类型
	 * 
	 * @param extValue
	 * @param reqType
	 * @return
	 */
	private boolean matchRequestType(String extValue, String reqType) {
		if (StringUtils.equalsIgnoreCase(reqType, extValue)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 匹配闲忙时
	 * 
	 * @param extValue
	 * @param start_time
	 * @return
	 */
	private boolean matchBusyLeisureTime(String extValue, String start_time) {
		boolean isValue = false;
		String[] extValues = StringUtils.splitPreserveAllTokens(extValue, ";");
		SimpleDateFormat format = new SimpleDateFormat("HHmmss");
		try {
			Date sdate = format.parse(extValues[0]);
			Date edate = format.parse(extValues[1]);
			Date ndate = format.parse(StringUtils.substring(
					DateUtil.filterUnNumber(start_time), 8));
			if (ndate.before(edate) && !ndate.before(sdate)) {
				isValue = true;
			}
		} catch (ParseException e) {
			logger.error("error", e);
		}
		return isValue;
	}

	private SubjectAndPrice accordingToSwitchCalculate(
			Map<String, String> data, String usageAmount,
			List<Map<String, String>> stepDatas) throws BusinessException {
		List<Interval> intervals = getStepInterval(stepDatas);
		List<Double> matchValues = Lists.newArrayList(new Double(usageAmount));
		List<MatchResult> matchResults = getMatcher(intervals, matchValues, "");
		if (matchResults.size() <= 0) {
			throw new BusinessException("BMC-B0018", "没找到匹配的区间段!");
		}
		MatchResult matchResult = matchResults.get(0);
		BigDecimal price = matchResult.getInterval().getPrice();
		BigDecimal total = price.multiply(new BigDecimal(usageAmount));
		return new SubjectAndPrice(stepDatas.get(0).get(
				BaasConstants.SUBJECT_CODE), total);
	}

	private SubjectAndPrice accordingToFixedCalculate(Map<String, String> data,
			String usageAmount, List<Map<String, String>> stepDatas)
			throws BusinessException {
		List<Interval> intervals = getStepInterval(stepDatas);
		List<Double> matchValues = Lists.newArrayList(new Double(usageAmount));
		List<MatchResult> matchResults = getMatcher(intervals, matchValues, "");
		if (matchResults.size() <= 0) {
			throw new BusinessException("BMC-B0018", "没找到匹配的区间段!");
		}
		MatchResult matchResult = matchResults.get(0);
		BigDecimal price = matchResult.getInterval().getPrice();
		// BigDecimal total = price.multiply(new BigDecimal(usageAmount));
		return new SubjectAndPrice(stepDatas.get(0).get(
				BaasConstants.SUBJECT_CODE), price);
	}

	/**
	 * 使用量累计后计算费用
	 * 
	 * @param data
	 * @param unitType
	 * @param duration
	 * @param detailCode
	 * @param stepDatas
	 * @return
	 */
	private SubjectAndPrice accordingToCumulativeCalculate(
			Map<String, String> data, String unitType, String usageAmount,
			List<Map<String, String>> stepDatas) {
		//logger.info("usageAmount--" + usageAmount);
		DecimalFormat myformat = new DecimalFormat("#0.00000000000000000000");
		// 累计后值
		//Double cumulativeAfterVal = chargeCache.getAddupAfter(data, unitType, usageAmount);
		Double cumulativeAfterVal = chargeCache.getAccumulateAfter(data, unitType, usageAmount);
		// System.out.println("累计后值="+myformat.format(cumulativeAfterVal.doubleValue()));
		data.put(BaasConstants.ADD_UP_AFTER, new BigDecimal(String.valueOf(cumulativeAfterVal)).setScale(17,BigDecimal.ROUND_UP).stripTrailingZeros().toPlainString());
		//logger.info("累计后值=" + myformat.format(cumulativeAfterVal.doubleValue()));
		// 累计前值
		double cumulativeBeforeVal = cumulativeAfterVal.doubleValue() - Double.parseDouble(usageAmount);
		// System.out.println("累计前值="+myformat.format(cumulativeBeforeVal));
		data.put(BaasConstants.ADD_UP_BEFORE, new BigDecimal(String.valueOf(cumulativeBeforeVal)).setScale(17,BigDecimal.ROUND_UP).stripTrailingZeros().toPlainString());
		//logger.info("累计前值=" + myformat.format(cumulativeBeforeVal));

		List<Double> matchValues = Lists.newArrayList(new Double(
				cumulativeBeforeVal), cumulativeAfterVal);
		// List<Interval> intervals =
		// IntervalContainer.getIntervalsByKey(stepDatas);
		// String detailCode = stepDatas.get(0).get(BmcConstants.DETAIL_CODE);
		List<Interval> intervals = getStepInterval(stepDatas);
		//logger.info("[accordingToCumulativeCalculate]intervals="+ intervals.toString());
		String detailCode = "";
		String stepGroup = stepDatas.get(0).get(BaasConstants.STEP_GROUP);
		//logger.info("stepGroup====" + stepGroup);
		if (StringUtils.isBlank(stepGroup)
				|| stepGroup.equalsIgnoreCase("NULL")) {
			detailCode = stepDatas.get(0).get(BaasConstants.DETAIL_CODE);
		} else {
			detailCode = Joiner.on("$").join(
					stepDatas.get(0).get(BaasConstants.DETAIL_CODE), stepGroup);
		}
		List<MatchResult> matchResults = getMatcher(intervals, matchValues,
				detailCode);
		BigDecimal total = this.doCal(matchResults, intervals);
		SubjectAndPrice subjectAndPrice = null;
		if (total != null) {
			subjectAndPrice = new SubjectAndPrice(stepDatas.get(0).get(
					BaasConstants.SUBJECT_CODE), total);
		}
		return subjectAndPrice;
	}

	private BigDecimal doCal(List<MatchResult> matchResults,
			List<Interval> intervals) {
		BigDecimal total = null;
		switch (getIntervalType(matchResults)) {
		case BaasConstants.STEP_SAME_INTERVAL:
			total = sameIntervalCal(matchResults);
			break;
		case BaasConstants.STEP_CONNECTED_INTERVAL:
			total = connectedIntervalCal(matchResults);
			break;
		case BaasConstants.STEP_UNCONNECTED_INTERVAL:
			total = unConnectedIntervalCal(matchResults, intervals);
			break;
		default:
			break;
		}
		return total;
	}

	/**
	 * 累计前和累计后值在同一区间内计算单价
	 * 
	 * @param matchResults
	 * @return
	 */
	private BigDecimal sameIntervalCal(List<MatchResult> matchResults) {
		MatchResult matchBefore = matchResults.get(0);
		MatchResult matchAfter = matchResults.get(1);
		double accumulateBeforeVal = matchBefore.getMatchValue().doubleValue();
		// logger.info("accumulateBeforeVal===="+accumulateBeforeVal);
		double accumulateAfterVal = matchAfter.getMatchValue().doubleValue();
		// logger.info("accumulateAfterVal===="+accumulateAfterVal);
		BigDecimal price, total = null;
		// 在阶梯范围内,按阶梯单价计算
		if (matchBefore.isWithin()) {
			double currentValue = accumulateAfterVal - accumulateBeforeVal;
			// logger.info("currentValue===="+currentValue);
			price = matchAfter.getInterval().getPrice();
			//System.out.println("[阶梯计费,单价]-->>"+price.toPlainString());
			// logger.info("price===="+price.toPlainString());
			total = price.multiply(new BigDecimal(currentValue));
			//System.out.println("[阶梯计费,总金额]-->>"+total.toPlainString());
		} else {
			logger.debug("[累计后超出阶梯范围,目前没有实现超过部分资费计算过程]");
		}
		return total;
	}

	/**
	 * 累计前和累计后在相邻区间计算单价
	 * 
	 * @param matchResults
	 * @return
	 */
	private BigDecimal connectedIntervalCal(List<MatchResult> matchResults) {
		BigDecimal total = null;
		MatchResult matchBefore = matchResults.get(0);
		MatchResult matchAfter = matchResults.get(1);
		if (matchBefore.isWithin() && matchAfter.isWithin()) {
			BigDecimal beforeVal = intervalWithinBeforeValCal(matchBefore);
			BigDecimal afterVal = intervalWithinAfterValCal(matchAfter);
			total = beforeVal.add(afterVal);
		} else {
			logger.debug("[累计后超出阶梯范围,目前没有实现超过部分资费计算方式!]");
		}
		return total;
	}

	/**
	 * 计算区间内累计前值的价格
	 * 
	 * @param matchBefore
	 * @return
	 */
	private BigDecimal intervalWithinBeforeValCal(MatchResult matchBefore) {
		Interval interval = matchBefore.getInterval();
		double upper = interval.getRange().upperEndpoint().doubleValue();
		double beforeValue = matchBefore.getMatchValue().doubleValue();
		double value = upper - beforeValue;
		BigDecimal price = interval.getPrice();
		return price.multiply(new BigDecimal(value));
	}

	/**
	 * 计算区间内累计后值的价格
	 * 
	 * @param matchAfter
	 * @return
	 */
	private BigDecimal intervalWithinAfterValCal(MatchResult matchAfter) {
		Interval interval = matchAfter.getInterval();
		double lower = interval.getRange().lowerEndpoint().doubleValue();
		double prevUpper = interval.getPrev().getRange().upperEndpoint()
				.doubleValue();
		// double intervalDiffVal = prevUpper - lower;
		double intervalDiffVal = lower - prevUpper;
		double afterValue = matchAfter.getMatchValue().doubleValue();
		double value = afterValue - lower + intervalDiffVal;
		BigDecimal price = interval.getPrice();
		return price.multiply(new BigDecimal(value));
	}

	/**
	 * 区间内中间值计算
	 * 
	 * @param matchBefore
	 * @param matchAfter
	 * @return
	 */
	private BigDecimal intervalWithinMiddleValCal(MatchResult matchBefore,
			MatchResult matchAfter, List<Interval> intervals) {
		// String detailCode = matchAfter.getDetailCode();
		int from = matchBefore.getInterval().getOrder().intValue() + 1;
		int to = matchAfter.getInterval().getOrder().intValue() - 1;
		// List<Interval> _intervals =
		// IntervalContainer.getIntervalsByFromAndTo(detailCode, from, to);
		List<Interval> interval_tmp = getIntervalsByFromAndTo(intervals, from,
				to);
		BigDecimal amount = new BigDecimal(new Double(0));
		double lower, upper, prevUpper, intervalDiffVal, subtract;
		for (Interval interval : interval_tmp) {
			lower = interval.getRange().lowerEndpoint().doubleValue();
			prevUpper = interval.getPrev().getRange().upperEndpoint()
					.doubleValue();
			// intervalDiffVal = prevUpper - lower;
			intervalDiffVal = lower - prevUpper;
			upper = interval.getRange().upperEndpoint().doubleValue();
			subtract = upper - lower + intervalDiffVal;
			amount = amount.add(interval.getPrice().multiply(
					new BigDecimal(subtract)));
		}
		// System.out.println("[阶梯计费]夸区间中间值计算完成@@@@@@@@@@");
		// System.out.println("amount=="+amount.doubleValue());
		return amount;
	}

	private BigDecimal unConnectedIntervalCal(List<MatchResult> matchResults,
			List<Interval> intervals) {
		BigDecimal total = null;
		MatchResult matchBefore = matchResults.get(0);
		MatchResult matchAfter = matchResults.get(1);
		if (matchBefore.isWithin() && matchAfter.isWithin()) {
			BigDecimal beforeVal = intervalWithinBeforeValCal(matchBefore);
			BigDecimal middleVal = intervalWithinMiddleValCal(matchBefore,
					matchAfter, intervals);
			BigDecimal afterVal = intervalWithinAfterValCal(matchAfter);
			total = beforeVal.add(middleVal).add(afterVal);
		} else {
			logger.debug("[累计后超出阶梯范围,目前没有实现超过部分资费计算过程]");
		}
		return total;
	}

	/**
	 * 两个累计值的区间类型 (同一区间、相邻区间、不相邻区间)
	 * 
	 * @param matchResults
	 * @return
	 */
	private String getIntervalType(List<MatchResult> matchResults) {
		String intervalType = "";
		MatchResult matchResult = matchResults.get(0);
		Interval interval = matchResult.getInterval();
		int beforeOrder;
		if (interval != null) {
			beforeOrder = interval.getOrder();
			matchResult.setWithin(true);
		} else {
			beforeOrder = IntervalContainer.getIntervalMaxOrder(matchResult
					.getDetailCode()) + 1;
			matchResult.setWithin(false);
		}
		matchResult = matchResults.get(1);
		interval = matchResult.getInterval();
		int afterOrder;
		if (interval != null) {
			afterOrder = interval.getOrder();
			matchResult.setWithin(true);
		} else {
			afterOrder = IntervalContainer.getIntervalMaxOrder(matchResult
					.getDetailCode()) + 1;
			matchResult.setWithin(false);
		}
		int result = afterOrder - beforeOrder;
		if (result == 0) {
			intervalType = BaasConstants.STEP_SAME_INTERVAL;
		} else if (result == 1) {
			intervalType = BaasConstants.STEP_CONNECTED_INTERVAL;
		} else {
			intervalType = BaasConstants.STEP_UNCONNECTED_INTERVAL;
		}
		return intervalType;
	}

	/**
	 * 累计前和累计后的值匹配区间
	 * 
	 * @param intervals
	 * @param matchValues
	 *            0:累计前 值 1:累计后值
	 * @param detail_code
	 * @return
	 */
	private List<MatchResult> getMatcher(List<Interval> intervals,
			List<Double> matchValues, String detail_code) {
		List<MatchResult> matchResults = Lists.newArrayList();
		MatchResult matchResult = null;
		for (Double matchValue : matchValues) {
			matchResult = new MatchResult();
			matchResult.setMatchValue(matchValue);
			matchResult.setDetailCode(detail_code);
			matchResults.add(matchResult);
		}
		Range<Double> range;
		int flag = 0;
		for (Interval interval : intervals) {
			if (flag == matchResults.size()) {
				break;
			}
			range = interval.getRange();
			for (MatchResult result : matchResults) {
				if (range.contains(result.getMatchValue())) {
					result.setInterval(interval);
					flag++;
				}
			}
		}
		return matchResults;
	}

	private List<Interval> getStepInterval(List<Map<String, String>> stepDatas) {
		List<Interval> intervals = new ArrayList<Interval>();
		Interval interval = null;
		Double lower, upper;
		Range<Double> range = null;
		String strPrice;
		BigDecimal price = null;
		for (Map<String, String> stepData : stepDatas) {
			lower = new Double(StringUtils.trim(stepData.get("section_a")));
			upper = new Double(StringUtils.trim(stepData.get("section_b")));
			range = Range.closed(lower, upper);
			strPrice = StringUtils.trim(stepData.get("price_value"));
			if (StringUtils.isNotBlank(strPrice)) {
				price = new BigDecimal(strPrice);
			} else {
				String tmp = StringUtils.defaultString(
						StringUtils.trim(stepData.get("total_price_value")),
						"0");
				price = new BigDecimal(tmp);
			}
			interval = new Interval();
			interval.setOrder(new Integer(StringUtils.trim(stepData
					.get("step_seq"))));
			interval.setRange(range);
			interval.setPrice(price);

			intervals.add(interval);
		}
		Collections.sort(intervals);
		for (int i = 1; i < intervals.size(); i++) {
			interval = intervals.get(i);
			interval.setPrev(intervals.get(i - 1));
		}
		return intervals;
	}

	private List<Interval> getIntervalsByFromAndTo(List<Interval> intervals,
			int from, int to) {
		List<Interval> results = Lists.newArrayList();
		int order;
		for (Interval interval : intervals) {
			order = interval.getOrder().intValue();
			if (order >= from && order <= to) {
				results.add(interval);
			}
		}
		return results;
	}
	
	private SubjectAndPrice accordingToTotalCalculate(Map<String, String> data, 
			String usageAmount,List<Map<String, String>> stepDatas) {
		List<Double> matchValues = Lists.newArrayList(Double.parseDouble("0"), Double.parseDouble(usageAmount));
		// String detailCode = stepDatas.get(0).get(BmcConstants.DETAIL_CODE);
		List<Interval> intervals = getStepInterval(stepDatas);
		//logger.info("[accordingToCumulativeCalculate]intervals="+ intervals.toString());
		String detailCode = "";
		String stepGroup = stepDatas.get(0).get(BaasConstants.STEP_GROUP);
		//logger.info("stepGroup====" + stepGroup);
		if (StringUtils.isBlank(stepGroup) || stepGroup.equalsIgnoreCase("NULL")) {
			detailCode = stepDatas.get(0).get(BaasConstants.DETAIL_CODE);
		} else {
			detailCode = Joiner.on("$").join(
					stepDatas.get(0).get(BaasConstants.DETAIL_CODE), stepGroup);
		}
		List<MatchResult> matchResults = getMatcher(intervals, matchValues, detailCode);
		BigDecimal total = this.doCal(matchResults, intervals);
		SubjectAndPrice subjectAndPrice = null;
		if (total != null) {
			subjectAndPrice = new SubjectAndPrice(stepDatas.get(0).get(BaasConstants.SUBJECT_CODE), total);
		}
		return subjectAndPrice;
	}

}
