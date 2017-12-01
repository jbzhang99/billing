package com.ai.baas.dst.api.couponcal.params;

import java.util.List;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 优惠卷计算服务返回结果
 * @author wangjing19
 *
 */
public class CouponCalResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    /**
     * 返回码
     */
    private String returnCode;
    
    private Boolean isUsed;
    
    /**
     * 含现总价的product信息
     */
    private List<ProductReq> productInfo;

	public List<ProductReq> getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(List<ProductReq> productInfo) {
		this.productInfo = productInfo;
	}

	public Boolean getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
   
	
	
}
