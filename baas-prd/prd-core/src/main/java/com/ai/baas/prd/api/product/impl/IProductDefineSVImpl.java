package com.ai.baas.prd.api.product.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.ai.baas.prd.api.product.interfaces.IProductDefineSV;
import com.ai.baas.prd.api.product.params.BaseCategoryInfo;
import com.ai.baas.prd.api.product.params.BaseRequest;
import com.ai.baas.prd.api.product.params.CategoryInfo;
import com.ai.baas.prd.api.product.params.DelProductReq;
import com.ai.baas.prd.api.product.params.MainProductInfo;
import com.ai.baas.prd.api.product.params.MainProductRes;
import com.ai.baas.prd.api.product.params.PmcategoryInfoResponse;
import com.ai.baas.prd.api.product.params.ProductRequest;
import com.ai.baas.prd.api.product.params.ProductResponse;
import com.ai.baas.prd.api.product.params.ProductUpdateReq;
import com.ai.baas.prd.api.product.params.QueryPmCategoryInfoReq;
import com.ai.baas.prd.api.product.params.SubProResponse;
import com.ai.baas.prd.api.product.params.SubQueryReq;
import com.ai.baas.prd.api.product.params.SubsProductQueryReq;
import com.ai.baas.prd.api.product.params.mainProReq;
import com.ai.baas.prd.api.product.params.mainProResponse;
import com.ai.baas.prd.constants.ExceptCodeConstants;
import com.ai.baas.prd.service.business.interfaces.IProductDefineBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.dubbo.config.annotation.Service;
@Service
public class IProductDefineSVImpl implements IProductDefineSV {
	@Autowired
	private IProductDefineBusiSV iProductDefineBusiSV;
	@Override
	public BaseResponse addProduct(ProductRequest res) throws BusinessException, SystemException {
		if(StringUtil.isBlank(res.getTenantId())){
			throw new BusinessException("888888", "租户Id不能为空");
		}
		if(StringUtil.isBlank(res.getMainProductCode())){
			throw new BusinessException("888888", "主产品编码不能为空");
		}
		if(StringUtil.isBlank(res.getMainProductName())){
			throw new BusinessException("888888", "主产品名称不能为空");
		}
		if(StringUtil.isBlank(res.getTradeCode())){
			throw new BusinessException("888888", "行业类型不能为空");
		}
		if(StringUtil.isBlank(res.getTradeName())){
			throw new BusinessException("888888", "行业名称不能为空");
		}
		if(CollectionUtil.isEmpty(res.getDimensions())){
			throw new BusinessException("888888", "维度分支不能为空");
		}
		iProductDefineBusiSV.addProduct(res);
		BaseResponse response=new BaseResponse();
		ResponseHeader responseHeader =new ResponseHeader();
		responseHeader.setIsSuccess(true);
		responseHeader.setResultCode("000000");
		responseHeader.setSuccess(true);
		responseHeader.setResultMessage("新增产品成功");
		response.setResponseHeader(responseHeader);
		return response;
	}
	@Override
	public BaseResponse delProduct(DelProductReq req) throws BusinessException, SystemException {
		if(StringUtil.isBlank(req.getTenantId())){
			throw new BusinessException("888888", "租户Id不能为空");
		}
		if(StringUtil.isBlank(req.getMainProCode())){
			throw new BusinessException("888888", "主产品编码不能为空");
		}
		int i=iProductDefineBusiSV.delProduct(req);
		BaseResponse response=new BaseResponse();
		ResponseHeader responseHeader =new ResponseHeader();
		if(i>0){
			responseHeader.setIsSuccess(true);
			responseHeader.setResultCode("000000");
			responseHeader.setSuccess(true);
			responseHeader.setResultMessage("删除产品成功");
		}else{
			responseHeader.setIsSuccess(false);
			responseHeader.setResultCode("666666");
			responseHeader.setSuccess(false);
			responseHeader.setResultMessage("删除产品失败");
		}
		
		response.setResponseHeader(responseHeader);
		return response;
	}
	@Override
	public ProductResponse getProduct(BaseRequest req) throws BusinessException, SystemException {
		if(StringUtil.isBlank(req.getTenantId())){
			throw new BusinessException("888888", "租户Id不能为空");
		}
		if(StringUtil.isBlank(req.getMainProductCode())){
			throw new BusinessException("888888", "主产品编码不能为空");
		}
		ProductResponse response=new ProductResponse();
		MainProductRes mres=iProductDefineBusiSV.getProductById(req);
	    ResponseHeader responseHeader =new ResponseHeader();
		
		responseHeader.setIsSuccess(true);
		responseHeader.setResultCode("000000");
		responseHeader.setSuccess(true);
		responseHeader.setResultMessage("查询成功");
		response.setResponseHeader(responseHeader);
		response.setProduct(mres);
		return response;
	}
	@Override
	public PmcategoryInfoResponse getCategoryInfos(QueryPmCategoryInfoReq req)
			throws BusinessException, SystemException {
		if(StringUtil.isBlank(req.getTenantId())){
			throw new BusinessException("888888", "租户Id不能为空");
		}
		PmcategoryInfoResponse response=new PmcategoryInfoResponse();
		response.setPageInfo(iProductDefineBusiSV.getCategoryInfos(req));
		 ResponseHeader responseHeader =new ResponseHeader();
			
			responseHeader.setIsSuccess(true);
			responseHeader.setResultCode("000000");
			responseHeader.setSuccess(true);
			responseHeader.setResultMessage("查询成功");
			response.setResponseHeader(responseHeader);
		return response;
	}
	@Override
	public BaseResponse updatePmCategoryInfo(ProductUpdateReq req) throws BusinessException, SystemException {
		if(StringUtil.isBlank(req.getTenantId())){
			throw new BusinessException("888888", "租户Id不能为空");
		}
		if(StringUtil.isBlank(req.getMainProductCode())){
			throw new BusinessException("888888", "主产品编码不能为空");
		}
		if(StringUtil.isBlank(req.getMainProductName())){
			throw new BusinessException("888888", "主产品名称不能为空");
		}
		if(StringUtil.isBlank(req.getTradeCode())){
			throw new BusinessException("888888", "行业类型不能为空");
		}
		if(StringUtil.isBlank(req.getTradeName())){
			throw new BusinessException("888888", "行业名称不能为空");
		}
		if(CollectionUtil.isEmpty(req.getDimensions())){
			throw new BusinessException("888888", "维度分支不能为空");
		}
		iProductDefineBusiSV.updatePmCategoryInfo(req);
		BaseResponse response=new BaseResponse();
		ResponseHeader responseHeader =new ResponseHeader();
		
		responseHeader.setIsSuccess(true);
		responseHeader.setResultCode("000000");
		responseHeader.setSuccess(true);
		responseHeader.setResultMessage("更新成功");
		response.setResponseHeader(responseHeader);
		return response;
	}
	@Override
	public BaseCategoryInfo querySubsProduct(SubsProductQueryReq req) throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		if(StringUtil.isBlank(req.getTenantId())){
			throw new BusinessException("888888", "租户Id不能为空");
		}
		if(StringUtil.isBlank(req.getMainProCode())){
			throw new BusinessException("888888", "主产品编码不能为空");
		}
		return this.iProductDefineBusiSV.querySubsProduct(req);
	}
	@Override
	public SubProResponse querySubProducts(SubQueryReq req)
			throws BusinessException, SystemException {
		if(StringUtil.isBlank(req.getTenantId())){
			throw new BusinessException("888888", "租户Id不能为空");
		}
		SubProResponse res=new SubProResponse();
		
		PageInfo<CategoryInfo> info=iProductDefineBusiSV.getCategorys(req);
		res.setPageInfo(info);
		ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstants.SUCCESS, "成功");
		res.setResponseHeader(responseHeader);
		return res;
	}
	@Override
	public mainProResponse queryMainProduct(mainProReq req)	throws BusinessException, SystemException {

		if(StringUtil.isBlank(req.getTenantId())){
			throw new BusinessException("888888", "租户Id不能为空");
		}
		mainProResponse res=new mainProResponse();
		PageInfo<MainProductInfo> pageInfo=iProductDefineBusiSV.getMainProduct(req);
		ResponseHeader responseHeader = new ResponseHeader(true, ExceptCodeConstants.SUCCESS, "成功");
		res.setResponseHeader(responseHeader);
		res.setPageInfo(pageInfo);
		return res;
	}
	
}
