package com.ai.baas.amc.service.business.interfaces;

import java.util.List;

import com.ai.baas.amc.api.invoice.params.InvoiceInfoDetailVO;
import com.ai.baas.amc.api.invoice.params.InvoiceInfoParam;
import com.ai.baas.amc.api.invoice.params.InvoiceInfoQueryResponse;
import com.ai.baas.amc.api.invoice.params.InvoiceQueryParam;
import com.ai.baas.amc.api.invoice.params.InvoiceRecordInsertParam;
import com.ai.baas.amc.api.invoice.params.InvoiceRecordQueryParam;
import com.ai.baas.amc.api.invoice.params.InvoiceRecordQueryReponse;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.BaseResponse;

public interface IInvoiceInfoBusiSV {

	BaseResponse saveInvoiceInfo(InvoiceInfoParam param) throws BusinessException;
	InvoiceInfoQueryResponse queryInvoiceInfo(InvoiceQueryParam request) throws BusinessException;
	InvoiceRecordQueryReponse queryInvoiceRecord(InvoiceRecordQueryParam request) throws BusinessException;
	List<InvoiceInfoDetailVO> queryInvoiceInfoDetailRecord(InvoiceRecordQueryParam request) throws BusinessException;
	BaseResponse saveInvoiceRecord(InvoiceRecordInsertParam param) throws BusinessException;
}
