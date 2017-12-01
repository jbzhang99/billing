package com.ai.baas.amc.api.proferentialbill.params;

import javax.validation.constraints.NotNull;

import com.ai.baas.amc.api.proferentialbill.interfaces.IProferProductManageSV;
import com.ai.opt.base.vo.BaseInfo;

public class ProductStatusVO extends BaseInfo{
	
	private static final long serialVersionUID = 1L;
	/**
     * 消息流水<br>
     * 组成：租户ID + YYMMDDHH24MISS + SSS(毫秒) + 9位序列号<br>
     * 必填<br>
     * VARCHAR(32)
     */
	@NotNull(message="消息流水号不能为空",groups={IProferProductManageSV.UpdateProductStatus.class})
	private String tradeSeq;
	 /**
	  * 优惠产品Id,必填
	  */
	@NotNull(message="优惠产品Id不能为空",groups={IProferProductManageSV.UpdateProductStatus.class})
	private String productId;
	/**
	 * 状态,必填
	 */
	@NotNull(message="产品状态不能为空",groups={IProferProductManageSV.UpdateProductStatus.class})
	private String status;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
