package com.ai.baas.bmc.srv.util;

import java.math.BigDecimal;


public class UnitConverter {
	public static final String MEASURE_WORD_CODE_MINUTE_PREFIX = "m";
	public static final String MEASURE_WORD_CODE_KB = "kb";
	public static final String MEASURE_WORD_CODE_MB = "mb";
	public static final String MEASURE_WORD_CODE_GB = "gb";
	public static final String MEASURE_WORD_CODE_TIME = "time";
	public static final String MEASURE_WORD_CODE_MILLION_TIME = "mt";//百万次
	public static final String MEASURE_WORD_CODE_TEN_THOUSAND_TIME = "ttt"; //万次
	
	public String convertToAdvanced(String unit_type, String beforeValue){
		String out = "";
		switch (unit_type){
		case MEASURE_WORD_CODE_MINUTE_PREFIX:
			out = secondConvertMinute(beforeValue);
			break;
		case MEASURE_WORD_CODE_KB:
			out = byetConvertKB(beforeValue);
			break;
		case MEASURE_WORD_CODE_MB:
			out = byetConvertMB(beforeValue);
			break;
		case MEASURE_WORD_CODE_GB:
			out = byteConvertGB(beforeValue);
			break;
		case MEASURE_WORD_CODE_MILLION_TIME:
			out = timeConvertMillionTime(beforeValue);
			break;
		case MEASURE_WORD_CODE_TEN_THOUSAND_TIME:
			out = timeTenThousandTime(beforeValue);
			break;
		default:
			out = beforeValue;
			break;
		}
		return out;
	}
	
	/**
	 * 将高级单位数值转换为基础单位数值
	 * @param unit_type
	 * @param currentValue
	 * @return
	 */
	public String convertToLower(String unit_type, String currentValue){
		String out = "";
		BigDecimal current = new BigDecimal(Double.parseDouble(currentValue));
		switch (unit_type){
		case MEASURE_WORD_CODE_MINUTE_PREFIX:
			out = current.multiply(new BigDecimal(60)).setScale(10, BigDecimal.ROUND_UP).toPlainString();
			break;
		case MEASURE_WORD_CODE_KB:
			out = current.multiply(new BigDecimal(1024)).setScale(10, BigDecimal.ROUND_HALF_UP).toPlainString();
			break;
		case MEASURE_WORD_CODE_MB:
			out = current.multiply(new BigDecimal(1024*1024)).setScale(10, BigDecimal.ROUND_HALF_UP).toPlainString();
			break;
		case MEASURE_WORD_CODE_GB:
			out = current.multiply(new BigDecimal(1024*1024*1024)).setScale(10, BigDecimal.ROUND_HALF_UP).toPlainString();
			break;
		case MEASURE_WORD_CODE_MILLION_TIME:
			out = current.multiply(new BigDecimal(1000000)).toPlainString();
			break;
		case MEASURE_WORD_CODE_TEN_THOUSAND_TIME:
			out = current.multiply(new BigDecimal(10000)).toPlainString();
			break;
		default:
			out = currentValue;
			break;
		}
		return out;
	}
	
	public BigDecimal convertToUpperByKey(String unit_key, String currentValue){
		BigDecimal after = null;
		BigDecimal current = new BigDecimal(currentValue);
		switch (unit_key){
		case "STREAM":
			after = current.divide(new BigDecimal(1024), 10, BigDecimal.ROUND_HALF_UP);
			break;
		case "DURATION":
			after = current.divide(new BigDecimal(60), 10, BigDecimal.ROUND_HALF_UP);
			break;
		default:
			after = current;
			break;
		}
		return after;
	}
	
	public BigDecimal convertToLowerByKey(String unit_key, BigDecimal current){
		BigDecimal after = null;
		//BigDecimal current = new BigDecimal(currentValue);
		switch (unit_key){
		case "STREAM":
			after = current.multiply(new BigDecimal(1024));
			break;
		case "DURATION":
			after = current.multiply(new BigDecimal(60));
			break;
		default:
			after = current;
			break;
		}
		return after;
	}
	
	
	public String secondConvertMinute(String beforeValue){
		BigDecimal before = new BigDecimal(Double.parseDouble(beforeValue));
		return before.divide(new BigDecimal(60), 0, BigDecimal.ROUND_UP).toPlainString();
	}
	
	public String byetConvertKB(String beforeValue){
		BigDecimal before = new BigDecimal(Double.parseDouble(beforeValue));
		return before.divide(new BigDecimal(1024), 0, BigDecimal.ROUND_UP).toPlainString();
	}
	
	public String byetConvertMB(String beforeValue){
		BigDecimal before = new BigDecimal(Double.parseDouble(beforeValue));
		return before.divide(new BigDecimal(1024*1024), 0, BigDecimal.ROUND_UP).toPlainString();
	}
	
	public String byteConvertGB(String beforeValue){
		BigDecimal before = new BigDecimal(Double.parseDouble(beforeValue));
		return before.divide(new BigDecimal(1024*1024*1024), 10, BigDecimal.ROUND_HALF_UP).toPlainString();
		//return before.divide(new BigDecimal(1024*1024*1024)).toPlainString();
	}
	
	public String timeConvertMillionTime(String beforeValue){
		BigDecimal before = new BigDecimal(Double.parseDouble(beforeValue));
		//return before.divide(new BigDecimal(1000000), 0, BigDecimal.ROUND_UP).toPlainString();
		return before.divide(new BigDecimal(1000000)).toPlainString();
	}
	
	public String timeTenThousandTime(String beforeValue){
		BigDecimal before = new BigDecimal(Double.parseDouble(beforeValue));
		return before.divide(new BigDecimal(10000)).toPlainString();
	}
	
	public static void main(String[] args) {
		UnitConverter convert = new UnitConverter();
		//String out = convert.convert("m", "310");
		String out = convert.convertToLower("mb", String.valueOf(1));
		//System.out.println(out);
		//Date d = 
		
	}
}
