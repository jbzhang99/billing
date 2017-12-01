package com.ai.baas.abm.api.invoice.params;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 发票详情查询返回
 * @author wangluyang
 *
 */
public class InvoiceInfoQueryResponse extends BaseResponse{
	
	private static final long serialVersionUID = 1L;
	private InvoiceInfoVO invoiceInfoVO;
	
	public InvoiceInfoVO getInvoiceInfoVO() {
		return invoiceInfoVO;
	}
	public void setInvoiceInfoVO(InvoiceInfoVO invoiceInfoVO) {
		this.invoiceInfoVO = invoiceInfoVO;
	}
}
