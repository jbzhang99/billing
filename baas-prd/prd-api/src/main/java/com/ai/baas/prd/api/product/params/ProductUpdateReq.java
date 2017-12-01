package com.ai.baas.prd.api.product.params;

import java.util.List;

import com.ai.opt.base.vo.BaseInfo;

public class ProductUpdateReq extends BaseInfo {
	private static final long serialVersionUID = 1L;
	private String tradeName;
	private String tradeCode;
	private String mainProductName;
	private String mainProductCode;
	List<DimensionRes> dimensions;
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
	public List<DimensionRes> getDimensions() {
		return dimensions;
	}
	public void setDimensions(List<DimensionRes> dimensions) {
		this.dimensions = dimensions;
	}
}
