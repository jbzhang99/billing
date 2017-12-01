package com.ai.baas.pkgfee.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.pkgfee.constants.CpPkgfeeConstants;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;

public class DateUtils {
	private static Logger logger = LoggerFactory.getLogger(DateUtils.class);
	
	/**
	 * 时间进行格式化
	 * @param date
	 * @param pattern
     * @return
     */
	public static String format(Date date, String pattern){
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}
	
	public static Date format(String date,String pattern) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.parse(date);
	}
	
	
	public static List<String> getMonthBetween(String startDate, String endDate) throws ParseException {
		ArrayList<String> result = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");

		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();

		min.setTime(sdf.parse(startDate));
		min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

		max.setTime(sdf.parse(endDate));
		max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

		Calendar curr = min;
		while (curr.before(max)) {
			result.add(sdf.format(curr.getTime()));
			curr.add(Calendar.MONTH, 1);
		}
		return result;
	}
	
	/**
	 * 指定日期到月末的天数
	 * @param date yyyyMMdd
	 * @return
	 * @throws ParseException 
	 */
	public static int getRemainDays(String date) throws ParseException{
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(format(date,getFormatMode(date)));
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DATE);
	}
	
	/**
	 * 指定月的总天数
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static int getDaysOfMonth(String date) throws ParseException{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(format(date,getFormatMode(date)));
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	public static Timestamp str2Timestamp(String str) throws SystemException{
		Timestamp ts = null;
		try {
			Date date = format(str,"yyyyMMddHHmmss");
			ts = new Timestamp(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ts;
	}
	
	public static String Timestamp2Str(Timestamp ts){
		return format(ts,"yyyyMMddHHmmss");
	}
	
	
	/**
	 * 是否为本月第一天
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static boolean isFirstDayOfMonth(String date) throws ParseException{
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(format(date,getFormatMode(date)));
		return calendar.get(Calendar.DATE) == 1 ? true : false;
	}
	
	/**
	 * 是否为本月最后一天
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static boolean isLastDayOfMonth(String date) throws ParseException{
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(format(date,getFormatMode(date)));
        int remainDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DATE);
		return remainDays == 0 ? true : false;
	}
	
	public static boolean isFirstDayOfMonth(Timestamp ts){
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(ts);
		return calendar.get(Calendar.DATE) == 1 ? true : false;
	}
	
	private static String getFormatMode(String time){
		String strFormat = "yyyyMMddHHmmss";
		int len = StringUtils.length(time);
		if(len==14){
			strFormat = "yyyyMMddHHmmss";
		}else if(len==8){
			strFormat = "yyyyMMdd";
		}
		return strFormat;
	}
	
	public static String verifyTextTime(String time) throws BusinessException{
		int len = StringUtils.length(time);
		if(len == 14){
			return time;
		}else if(len > 14){
			return StringUtils.substring(time, 0, 14);
		}else{
			throw new BusinessException("PKG-B0001","生效时间或失效时间格式不正确,不是yyyyMMddHHmmss样式!");
		}
	}
	
	/**
	 * 年转化成月
	 * @param num
	 * @param unit
	 * @return
	 */
	public static int convertMonth(String num, String unit){
		int monthValue;
		switch (unit) {
		case CpPkgfeeConstants.PURCHASE_UNIT_MON:
			monthValue = Integer.parseInt(num);
			break;
		case CpPkgfeeConstants.PURCHASE_UNIT_YEAR:
			String[] split = num.split(".");
			int integerPart = Integer.parseInt(split[0]);
			if(split.length == 2){
				//0:整数部分 1:小数部分
				int decimalPart = Integer.parseInt(split[1]);
				monthValue = integerPart * 12 + decimalPart;
			}else{
				monthValue = integerPart * 12;
			}
			break;
		default:
			monthValue = 0;
			break;
		}
		return monthValue;
	}
	
	
	public static String transformPurchaseUnit(String unit,String inNum){
		int num = Integer.parseInt(inNum);
		int outNum;
		switch (unit) {
		case CpPkgfeeConstants.PURCHASE_UNIT_MON:
			outNum = num;
			break;
		case CpPkgfeeConstants.PURCHASE_UNIT_DAY:
			outNum = num;
			break;
		case CpPkgfeeConstants.PURCHASE_UNIT_YEAR:
			outNum = num * 12;
			break;
		default:
			outNum = 0;
			break;
		}
		return String.valueOf(outNum);
	}
	
	
	public static void main(String[] args) throws ParseException {
//		try {
//			List<String> months = getMonthBetween("201612","201806");
//			System.out.println(months.toString());
//			
//			//System.out.println(StringUtils.substring("20161201", 0, 5));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		SimpleDateFormat timeformat = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar calendar = Calendar.getInstance();
        try {
			calendar.setTime(timeformat.parse("20161220100000"));
			//calendar.setTime(timeformat.parse("20180210"));
			System.out.println("days="+calendar.get(Calendar.DATE));
			System.out.println("count="+calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			System.out.println("count11="+calendar.getActualMaximum(Calendar.DATE));
			System.out.println("remain="+(calendar.getActualMaximum(Calendar.DAY_OF_MONTH)-calendar.get(Calendar.DATE)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        BigDecimal productPrice = new BigDecimal("100");
        BigDecimal priceOfDay = productPrice.divide(new BigDecimal(31),6, BigDecimal.ROUND_HALF_UP);
        System.out.println(priceOfDay.toPlainString());
        
        List<String> days = getMonthBetween("201704","201712");
        System.out.println("days="+days.toString());
        
        
        
        ArrayList<String> result = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String startDate = "20160101100000";
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(startDate));
		cal.add(Calendar.YEAR, 1);
		
		SimpleDateFormat sdfStr = new SimpleDateFormat("yyyyMMddHHmmss");
		System.out.println("+++"+sdfStr.format(cal.getTime()));
		
		System.out.println("lastDay = "+DateUtils.isLastDayOfMonth("20170229000000"));
		
	}

}
