package com.ai.baas.prd.api.strategy.params;

import java.io.Serializable;
import java.util.List;

/**
 * 变量VO
 * @author wangluyang
 *
 */
public class DetailAddVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 价格
	 */
	private String price;
	/**
	 * 序号
	 */
	private Long index;
	/**
	 * 因子元素
	 */
	private List<FactorAddVO> factorVos;
	
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Long getIndex() {
		return index;
	}
	public void setIndex(Long index) {
		this.index = index;
	}
	public List<FactorAddVO> getFactorVos() {
		return factorVos;
	}
	public void setFactorVos(List<FactorAddVO> factorVos) {
		this.factorVos = factorVos;
	}
}
