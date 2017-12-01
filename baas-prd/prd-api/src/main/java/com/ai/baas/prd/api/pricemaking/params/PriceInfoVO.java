package com.ai.baas.prd.api.pricemaking.params;

import java.io.Serializable;
import java.util.List;

public class PriceInfoVO implements Serializable {

	private static final long serialVersionUID = 6008284000796263635L;
	private String price;
	private String elementId;
	private List<ElementInfoVO> elementInfoList;
	private String extInfo;

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public List<ElementInfoVO> getElementInfoList() {
		return elementInfoList;
	}

	public void setElementInfoList(List<ElementInfoVO> elementInfoList) {
		this.elementInfoList = elementInfoList;
	}

	public String getExtInfo() {
		return extInfo;
	}

	public void setExtInfo(String extInfo) {
		this.extInfo = extInfo;
	}
	
}
