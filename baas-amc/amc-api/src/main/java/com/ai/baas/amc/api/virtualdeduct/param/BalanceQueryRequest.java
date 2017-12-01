package com.ai.baas.amc.api.virtualdeduct.param;

import com.ai.opt.base.vo.BaseInfo;

import java.io.Serializable;

/**
 * 查询销账信息的条件对象 <br>
 *
 * Date: 2016年03月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 *
 * @author liutong5
 */
public final class BalanceQueryRequest extends BaseInfo implements Serializable  {

	private static final long serialVersionUID = 1l;
	/**
	 * 属主类型
	 */
	private String owerType;
	/**
	 * 属主ID
	 */
	private String owerId;
	/**
	 * 资源类型
	 */
	private String businessCode;
	/**
	 * 产品类型
	 */
	private String productType;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getOwerType() {
		return owerType;
	}

	public void setOwerType(String owerType) {
		this.owerType = owerType;
	}

	public String getOwerId() {
		return owerId;
	}

	public void setOwerId(String owerId) {
		this.owerId = owerId;
	}

	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	@Override
	public String toString() {
		return "OmcObj [tenantid=" + getTenantId() + ", owertype=" + owerType
				+ ", owerid=" + owerId + ", businessCode=" + businessCode
				+ ", productType=" + productType + "]";
	}
	

	
}
