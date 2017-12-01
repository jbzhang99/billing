package com.ai.baas.prd.api.strategy.params;

import java.io.Serializable;
import java.util.List;

/**
 * 变量VO
 * @author wangluyang
 *
 */
public class DetailShowVO implements Serializable{

	private String detailId;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 价格
	 */
	private String price;
	/**
	 * 
	 */
	private String groupId;
	/**
	 * 序号
	 */
	private Long index;
	/**
	 * 因子元素
	 */
	private List<FactorShowVO> factorVos;
	
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
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
	public List<FactorShowVO> getFactorVos() {
		return factorVos;
	}
	public void setFactorVos(List<FactorShowVO> factorVos) {
		this.factorVos = factorVos;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
}
