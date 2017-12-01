package com.ai.baas.dst.api.billsdiscount.params;

import java.util.List;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 查询生失效范围内的产品
 * @author wangluyang
 *
 */
public class EffectiveProductQueryResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    private List<String> productIds;

	public List<String> getProductIds() {
		return productIds;
	}

	public void setProductIds(List<String> productIds) {
		this.productIds = productIds;
	}
}
