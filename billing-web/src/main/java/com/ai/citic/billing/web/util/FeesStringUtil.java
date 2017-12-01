package com.ai.citic.billing.web.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 
 * @author wangluyang
 *
 */
public final class FeesStringUtil {
	
	
	private FeesStringUtil()
	{
		
	}
	
	/**
	 * 请求的空值以及null转换成0方便算费
	 * @param fees
	 * @return
	 */
	public static String nullEmpty2Zero(String fees)
	{
		if(null==fees||"".equals(fees.trim()))
		{
			fees="0";
		}
	 return fees;
	}
	
	
	/**
	 * 厘转换成元
	 * @param fees
	 * @return
	 */
	public static long liToyuan(long fees)
	{
		
		return fees/1000;
	}
	
	/**
	 * 厘转换成元，并保留多少位小数
	 * @param fees  金额
	 * @param scale  保留位数
	 * @return
	 */
	public static String liToyuan(long fees,int scale)
	{
		double fee = fees;
		BigDecimal bd = new BigDecimal(fee/1000);
		double res = bd.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
		return new java.text.DecimalFormat("#0.00").format(res);
	}
	
	/**
	 * 元转换成厘
	 * @param fees
	 * @return
	 */
	public static long yuanToli(long fees)
	{
		return fees*1000;
	}
	
	/**
	 * 元转换成厘
	 * @param fees
	 * @return
	 */
	public static long yuanToli(Double fees)
	{
		Double double1 = (fees*1000);
		return double1.longValue();
	}
}
