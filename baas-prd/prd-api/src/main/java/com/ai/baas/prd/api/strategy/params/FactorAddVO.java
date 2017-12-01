package com.ai.baas.prd.api.strategy.params;

import java.io.Serializable;

/**
 * 因子入參
 * @author wangluyang
 *
 */
public class FactorAddVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 变量名称
	 */
	private String varName;
	/**
	 * 变量编码
	 */
	private String varCode;
	/**
	 * 变量类型
	 */
	private String varType;
	/**
	 * 变量单位ID
	 */
	private String varUnitId;
	/**
	 * 变量单位：个
	 */
	private String varUnitName;
	/**
	 * 变量编码
	 */
	private String varUnitCode;
	/**
	 * 因子值
	 */
	private String factorValue;
	/**
	 * 变量类型为区间时 因子值 
	 */
	private String factorValueStart;
	/**
	 * 变量类型为区间时 因子值 
	 */
	private String factorValueEnd;
	public String getVarName() {
		return varName;
	}
	public void setVarName(String varName) {
		this.varName = varName;
	}
	public String getVarCode() {
		return varCode;
	}
	public void setVarCode(String varCode) {
		this.varCode = varCode;
	}
	public String getVarUnitId() {
		return varUnitId;
	}
	public void setVarUnitId(String varUnitId) {
		this.varUnitId = varUnitId;
	}
	public String getVarUnitName() {
		return varUnitName;
	}
	public void setVarUnitName(String varUnitName) {
		this.varUnitName = varUnitName;
	}
	public String getVarUnitCode() {
		return varUnitCode;
	}
	public void setVarUnitCode(String varUnitCode) {
		this.varUnitCode = varUnitCode;
	}
	public String getFactorValue() {
		return factorValue;
	}
	public void setFactorValue(String factorValue) {
		this.factorValue = factorValue;
	}
	public String getVarType() {
		return varType;
	}
	public void setVarType(String varType) {
		this.varType = varType;
	}
	public String getFactorValueStart() {
		return factorValueStart;
	}
	public void setFactorValueStart(String factorValueStart) {
		this.factorValueStart = factorValueStart;
	}
	public String getFactorValueEnd() {
		return factorValueEnd;
	}
	public void setFactorValueEnd(String factorValueEnd) {
		this.factorValueEnd = factorValueEnd;
	}
}
