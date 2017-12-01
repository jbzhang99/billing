package com.ai.baas.amc.api.proferentialbill.params;

import javax.validation.constraints.NotNull;

import com.ai.baas.amc.api.proferentialbill.interfaces.IProferProductManageSV;
import com.ai.opt.base.vo.BaseInfo;

public class ProductDelVO extends BaseInfo {

	private static final long serialVersionUID = 1L;
	/**
     * 消息流水<br>
     * 组成：租户ID + YYMMDDHH24MISS + SSS(毫秒) + 9位序列号<br>
     * 必填<br>
     * VARCHAR(32)
     */
	@NotNull(message="消息流水号不能为空",groups={IProferProductManageSV.DelProduct.class})
	private String tradeSeq;
	/**
	 * 产品Id
	 */
	@NotNull(message="产品Id不能为空",groups={IProferProductManageSV.DelProduct.class})
	private String productId;
	public String getTradeSeq() {
		return tradeSeq;
	}
	public void setTradeSeq(String tradeSeq) {
		this.tradeSeq = tradeSeq;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	
}
