package com.ai.baas.abm.util;


import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import com.ai.baas.abm.constants.FeeSource;

public class CalUtil {
	public static final BigDecimal BigDecimalFromBigDecimal(BigDecimal bigDecimal,String resourcetype){
		if (resourcetype.equals(FeeSource.FROMCHARGE)){
			BigDecimal divisor = new BigDecimal(1000000);
			return bigDecimal.divide(divisor,2, RoundingMode.HALF_UP);
		} else if(resourcetype.equals(FeeSource.FROMBALANCE)){
			BigDecimal divisor = new BigDecimal(1000);
			return bigDecimal.divide(divisor,2, RoundingMode.HALF_UP);
		}else if(resourcetype.equals(FeeSource.FROMCREDIT)){
			BigDecimal divisor = new BigDecimal(100);
			return bigDecimal.divide(divisor,2, RoundingMode.HALF_UP);
		}
		return bigDecimal;
	}

	public static final BigDecimal BigDecimalFromDouble(Double value,String resourcetype){
		return BigDecimalFromBigDecimal(BigDecimal.valueOf(value),resourcetype);
	}
	
	public static final BigDecimal BigDecimalFromDoubleStr(String value,String resourcetype){
		Double doublevalue = Double.parseDouble(value);
		return BigDecimalFromDouble(doublevalue,resourcetype);
	}
	
	public static final BigDecimal BigDecimalFromLong(Long value,String resourcetype){
		Double doublevalue = Double.parseDouble(Long.toString(value));
		return BigDecimalFromDouble(doublevalue,resourcetype);
	}

	/**
	 * 将bigDecimal格式化为FROMCHARGE格式的
	 *
	 * @param value
	 * @param valueType
     * @return
     */
	public static final BigDecimal formBigDecimal(BigDecimal value,String valueType){
		BigDecimal divisor = null;
		if(valueType.equals(FeeSource.FROMBALANCE)){
			divisor = new BigDecimal(1000);
			return value.multiply(divisor, MathContext.UNLIMITED);
		}else if(valueType.equals(FeeSource.FROMCREDIT)){
			divisor = new BigDecimal(10000);
			return value.multiply(divisor, MathContext.UNLIMITED);
		}
		return value;
	}

	public static final BigDecimal formBigDecimalFromLong(Long value,String valueType){
		return formBigDecimal(new BigDecimal(value),valueType);
	}

	public static final BigDecimal formBigDecimalFromDouble(Double value,String valueType){
		return formBigDecimal(new BigDecimal(value),valueType);
	}
}
