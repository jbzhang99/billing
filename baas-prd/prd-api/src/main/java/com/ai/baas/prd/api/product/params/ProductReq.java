package com.ai.baas.prd.api.product.params;

import java.util.List;

import com.ai.opt.base.vo.BaseInfo;

public class ProductReq extends BaseInfo{
	
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
	 * 维度Id
	 */
	private String DimensionId;
	
	/**
	 * 维度
	 */
	List<DimensionInfo> dimensions;

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

	public String getDimensionId() {
		return DimensionId;
	}

	public void setDimensionId(String dimensionId) {
		DimensionId = dimensionId;
	}

	public List<DimensionInfo> getDimensions() {
		return dimensions;
	}

	public void setDimensions(List<DimensionInfo> dimensions) {
		this.dimensions = dimensions;
	}
	
}
