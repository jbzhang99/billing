package com.ai.baas.amc.api.invoice.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.amc.api.invoice.interfaces.IInvoiceInfoSV;
import com.ai.baas.amc.api.invoice.params.InvoiceInfoDetailVO;
import com.ai.baas.amc.api.invoice.params.InvoiceInfoParam;
import com.ai.baas.amc.api.invoice.params.InvoiceInfoQueryResponse;
import com.ai.baas.amc.api.invoice.params.InvoiceInfoVO;
import com.ai.baas.amc.api.invoice.params.InvoiceQueryParam;
import com.ai.baas.amc.api.invoice.params.InvoiceRecordInsertParam;
import com.ai.baas.amc.api.invoice.params.InvoiceRecordQueryParam;
import com.ai.baas.amc.api.invoice.params.InvoiceRecordQueryReponse;
import com.ai.baas.amc.constants.ExceptCodeConstants;
import com.ai.baas.amc.service.business.interfaces.IInvoiceInfoBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * 发票信息管理
 * @author wangluyang
 *
 */
@Service
@Component
public class InvoiceInfoSVImpl implements IInvoiceInfoSV {

	private static final Logger LOG = LogManager.getLogger(InvoiceInfoSVImpl.class);
	
	@Autowired
    private IInvoiceInfoBusiSV invoiceInfoSV;
	
	@Override
	public BaseResponse saveInvoiceInfo(InvoiceInfoParam param) throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		LOG.info("发票详情新增开始");
		if (param == null) {
	            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "请求参数不能为空");
	    }
        if (StringUtil.isBlank(param.getTenantId())) {
            throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "租户ID不能为空");
        }
        if(1==param.getInvoiceType()){
        	if(StringUtil.isBlank(param.getTaxRegNo())){
        		throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "纳税人识别号不能为空");
        	}
        	if(StringUtil.isBlank(param.getBankName())){
        		throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "开户银行名称不能为空");
        	}
        	if(StringUtil.isBlank(param.getBankAcctNo())){
        		throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "开户账号不能为空");
        	}
        	if(StringUtil.isBlank(param.getRegAddress())){
        		throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "公司注册地址不能为空");
        	}
        	if(StringUtil.isBlank(param.getRegPhone())){
        		throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "注册固定电话不能为空");
        	}
        }
		return invoiceInfoSV.saveInvoiceInfo(param);
	}

	@Override
	public InvoiceInfoQueryResponse queryInvoiceInfo(InvoiceQueryParam request)
			throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		LOG.info("发票详情查看");
		if(request==null){
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "请求参数不能为空");
		}
//		if(StringUtils.isBlank(request.getInvoiceInfoId())){
//			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "发票ID不能为空");
//		}
		if(StringUtils.isBlank(request.getTenantId())){
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "租户ID不能为空");
		}
		if(StringUtils.isBlank(request.getUserType())){
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "用户类型不能为空");
		}
		return invoiceInfoSV.queryInvoiceInfo(request);
	}

	@Override
	public InvoiceRecordQueryReponse queryInvoiceRecord(InvoiceRecordQueryParam request)
			throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		LOG.info("发票记录查询");
		if(request==null){
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "请求参数不能为空");
		}
		if(StringUtils.isBlank(request.getTenantId())){
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "租户ID不能为空");
		}
		if(StringUtils.isBlank(request.getUserType())){
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "用户类型不能为空");
		}
		if(request.getPageNo()==null || request.getPageSize()==null){
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "分页信息不能为空");
		}
		return invoiceInfoSV.queryInvoiceRecord(request);
	}

	@Override
	public BaseResponse saveInvoiceRecord(InvoiceRecordInsertParam param) throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		LOG.info("发票开具记录新增开始");
		if(param==null){
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "请求参数不能为空");
		}
		if(StringUtils.isBlank(param.getAcctId())){
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "账户ID不能为空");
		}
		if(StringUtils.isBlank(param.getBillMonth())){
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "账期不能为空");
		}
		if(StringUtils.isBlank(param.getTenantId())){
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "租户ID不能为空");
		}
		if(StringUtils.isBlank(param.getExpressNo())){
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "快递单号不能为空");
		}
		if(param.getSendTime()==null){
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "寄出时间不能为空");
		}
		return invoiceInfoSV.saveInvoiceRecord(param);
	}

	@Override
	public List<InvoiceInfoDetailVO> queryInvoiceInfoDetailRecord(InvoiceRecordQueryParam request)
			throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		LOG.info("发票记录详细信息查询");
		if(request==null){
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "请求参数不能为空");
		}
		if(StringUtils.isBlank(request.getTenantId())){
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "租户ID不能为空");
		}
		if(StringUtils.isBlank(request.getUserType())){
			throw new BusinessException(ExceptCodeConstants.PARAM_IS_NULL, "用户类型不能为空");
		}
		return invoiceInfoSV.queryInvoiceInfoDetailRecord(request);
	}

}
