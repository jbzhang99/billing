package com.ai.baas.bmc.topology.billing.container;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import com.ai.baas.bmc.topology.entity.Interval;
import com.ai.baas.bmc.topology.util.BmcConstants;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;

/**
 * 区间容器
 * @author majun
 *
 */
public class IntervalContainer {
	private static ConcurrentHashMap<String,List<Interval>> intervalMap = new ConcurrentHashMap<String,List<Interval>>();
	
	/**
	 * 根据detail_code得到区间列表
	 * @param stepDatas
	 * @return
	 */
	public static List<Interval> getIntervalsByKey(List<Map<String,String>> stepDatas){
		String key = stepDatas.get(0).get(BmcConstants.DETAIL_CODE);
		return intervalMap.containsKey(key)?(List<Interval>)intervalMap.get(key):addNew(key,stepDatas);
	}

	/**
	 * 根据key从from到to区间列表
	 * @param key
	 * @param from
	 * @param to
	 * @return
	 */
	public static List<Interval> getIntervalsByFromAndTo(String key,int from,int to){
		List<Interval> results = Lists.newArrayList();
		List<Interval> intervals = intervalMap.get(key);
		int order;
		for(Interval interval:intervals){
			order = interval.getOrder().intValue();
			if (order >= from && order <= to) {
				results.add(interval);
			}
		}
		return results;
	}
	
	/**
	 * 根据Key得到区间数量
	 * @param key
	 * @return
	 */
	public static int getIntervalCountByKey(String key){
		return intervalMap.containsKey(key)?intervalMap.get(key).size():0;
	}
	
	/**
	 * 根据key得到区间最大序号
	 * @param key
	 * @return
	 */
	public static int getIntervalMaxOrder(String key){
		int maxOrder = 0;
		int count = getIntervalCountByKey(key);
		if (count > 0) {
			Interval interval = (Interval) intervalMap.get(count - 1);
			maxOrder = interval.getOrder();
		}
		return maxOrder;
	}
	
	
	private static List<Interval> addNew(String key,List<Map<String,String>> stepDatas){
		//String key = stepDatas.get(0).get(BmcConstants.DETAIL_CODE);
		List<Interval> intervals = new ArrayList<Interval>();
		Interval interval = null,prev = null;
		Double lower,upper;
		Range<Double> range = null;
		String strPrice;
		BigDecimal price=null;
		for (Map<String, String> stepData : stepDatas) {
			//prev = interval;
			lower = new Double(StringUtils.trim(stepData.get("section_a")));
			upper = new Double(StringUtils.trim(stepData.get("section_b")));
			range = Range.closed(lower, upper);
			strPrice = StringUtils.trim(stepData.get("price_value"));
			if(StringUtils.isNotBlank(strPrice)){
				price = new BigDecimal(strPrice);
			}else{
				String tmp = StringUtils.defaultString(StringUtils.trim(stepData.get("total_price_value")), "0");
				price = new BigDecimal(tmp);
			}
			interval = new Interval();
			interval.setOrder(new Integer(StringUtils.trim(stepData.get("step_seq"))));
			interval.setRange(range);
			interval.setPrice(price);
			//interval.setPrev(prev);
			
			intervals.add(interval);
		}
		Collections.sort(intervals);
		for(int i=1;i<intervals.size();i++){
			interval = intervals.get(i);
			interval.setPrev(intervals.get(i-1));
		}
		intervalMap.put(key, intervals);
		return intervals;
	}
	
}
