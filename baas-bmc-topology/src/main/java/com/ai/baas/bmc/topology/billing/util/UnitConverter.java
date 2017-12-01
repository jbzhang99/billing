package com.ai.baas.bmc.topology.billing.util;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;


/**
 * 单位转换器
 * @author majun
 *
 */
public class UnitConverter {

	public static final String MEASURE_WORD_CODE_MINUTE_PREFIX = "m";
	public static final String MEASURE_WORD_CODE_KB = "kb";
	public static final String MEASURE_WORD_CODE_MB = "mb";
	
	public String convert(String unit_type, String beforeValue){
		if (StringUtils.startsWithIgnoreCase(unit_type, MEASURE_WORD_CODE_MINUTE_PREFIX)){
			return secondConvertMinute(beforeValue);
		}else if(unit_type.equalsIgnoreCase(MEASURE_WORD_CODE_KB)){
			return byetConvertKB(beforeValue);
		}else if(unit_type.equalsIgnoreCase(MEASURE_WORD_CODE_MB)){
			return byetConvertMB(beforeValue);
		}else{
			return beforeValue;
		}
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
		return before.divide(new BigDecimal(2048), 0, BigDecimal.ROUND_UP).toPlainString();
	}
	
	
	public static void main(String[] args) {
		UnitConverter convert = new UnitConverter();
		String out = convert.convert("m", "310");
		System.out.println(out);
	}
	
}
