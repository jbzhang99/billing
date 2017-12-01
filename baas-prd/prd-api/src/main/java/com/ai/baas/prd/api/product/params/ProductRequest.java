package com.ai.baas.prd.api.product.params;

import java.util.List;

import com.ai.opt.base.vo.BaseInfo;
/**
 * 添加产品入参
 *
 * Date: 2016年11月9日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author gaogang
 */
public class ProductRequest extends BaseInfo{

	
	private static final long serialVersionUID = 1L;
	/**
	 * 行业类型
	 */
	private String tradeName;
	/**
	 * 行业编码
	 */
	private String tradeCode;
	/**
	 * 主产品名称
	 */
	private String mainProductName;
	/**
	 * 主产品编码
	 */
	private String mainProductCode;
	/**
	 * 维度
	 */
	List<DimensionVO> dimensions;
	
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	public String getTradeCode() {
		return tradeCode;
	}
	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}
	public String getMainProductName() {
		return mainProductName;
	}
	public void setMainProductName(String mainProductName) {
		this.mainProductName = mainProductName;
	}
	public String getMainProductCode() {
		return mainProductCode;
	}
	public void setMainProductCode(String mainProductCode) {
		this.mainProductCode = mainProductCode;
	}
	public List<DimensionVO> getDimensions() {
		return dimensions;
	}
	public void setDimensions(List<DimensionVO> dimensions) {
		this.dimensions = dimensions;
	}
	

}
