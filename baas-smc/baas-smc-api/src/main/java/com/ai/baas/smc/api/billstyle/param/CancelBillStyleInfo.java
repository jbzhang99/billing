package com.ai.baas.smc.api.billstyle.param;

import java.io.Serializable;

import com.ai.opt.base.vo.BaseInfo;

public class CancelBillStyleInfo extends BaseInfo implements Serializable  {

	private static final long serialVersionUID = 1L;
	 /**
	 * 账单样式ID 必填
	 */
	private Long billStyleId;
	public Long getBillStyleId() {
		return billStyleId;
	}
	public void setBillStyleId(Long billStyleId) {
		this.billStyleId = billStyleId;
	}
	
	
}
