package com.ai.baas.dst.util;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import com.ai.opt.sdk.util.RandomUtil;
import com.google.common.collect.Lists;

public class NumberUtil {

	
	/**
	 * 按比例分摊金额
	 * @param amount
	 * @param num
	 * @return
	 */
	public static List<BigDecimal> getApportionByRatio(BigDecimal amount,int num){
		List<BigDecimal> apportionList = Lists.newArrayList();
		if (amount == null) {
			return apportionList;
		}
		if (amount.compareTo(BigDecimal.ZERO) == -1 || amount.compareTo(BigDecimal.ZERO) == 0) {
			return apportionList;
		}
		BigDecimal[] result = amount.divideAndRemainder(new BigDecimal(num));
		String integerVal = result[0].toPlainString();
		for (int i = 0; i < num - 1; i++) {
			apportionList.add(new BigDecimal(integerVal));
		}
		BigDecimal last = new BigDecimal(integerVal);
		if (result[1].compareTo(BigDecimal.ZERO) == 1) {
			last = last.add(result[1]);
			apportionList.add(last);
		}else{
			apportionList.add(new BigDecimal(integerVal));
		}
		//System.out.println(apportionList.toString());
		return apportionList;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<BigDecimal> temp = NumberUtil.getApportionByRatio(new BigDecimal("110.0"), 3);
		for (BigDecimal bigDecimal : temp) {
			System.out.println(bigDecimal);
		}
		//System.out.println("----"+RandomUtil.randomNum(4));
		
		
	}

}
