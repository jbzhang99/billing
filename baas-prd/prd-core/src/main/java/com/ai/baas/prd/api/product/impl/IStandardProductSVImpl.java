package com.ai.baas.prd.api.product.impl;

import com.ai.baas.prd.api.product.interfaces.IStandardProductSV;
import com.ai.baas.prd.api.product.params.*;
import com.ai.baas.prd.api.strategy.params.*;
import com.ai.baas.prd.service.atom.interfaces.IPmCatalogInfoAtomSV;
import com.ai.baas.prd.service.business.interfaces.IPriceStrategyBusiSV;
import com.ai.baas.prd.service.business.interfaces.IStandardProductBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.dubbo.config.annotation.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service
@Component
public class IStandardProductSVImpl implements IStandardProductSV{
    private static final Logger LOG = LogManager.getLogger(IStandardProductSVImpl.class);
    @Autowired
    private IStandardProductBusiSV standardProductBusiSV;
    @Autowired
    private IPriceStrategyBusiSV priceStrategyBusiSV;

    @Override
    public BaseResponse addProduct(StandardProductRequest req) throws BusinessException, SystemException {
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader header = new ResponseHeader();
        if(StringUtil.isBlank(req.getTenantId())){
            throw new BusinessException("888888", "租户Id不能为空");
        }
        if(StringUtil.isBlank(req.getMainProductName())){
            throw new BusinessException("888888", "产品名称不能为空");
        }
        if(StringUtil.isBlank(req.getPricePolicy())){
            throw new BusinessException("888888", "定价策略不能为空");
        }
        try {
            req.setSpecTypeId(req.getCategoryId());
            req.setSpecTypeName(req.getMainProductName());
            if(StringUtil.isBlank(req.getProductId())){
                standardProductBusiSV.addProduct(req);
            }else{
                standardProductBusiSV.updateProduct(req);
            }
            header.setResultCode("000000");
            header.setIsSuccess(true);
            header.setResultMessage("保存产品成功");
            baseResponse.setResponseHeader(header);
        } catch (Exception e) {
            LOG.error("保存产品异常",e);
            throw new BusinessException("保存产品异常",e);
        }
        return baseResponse;
    }

    @Override
    public SpecResponse getAllSpec(SpecQueryVo req) throws BusinessException, SystemException {
        SpecResponse specResponse = new SpecResponse();
        if(StringUtil.isBlank(req.getTenantId())){
            throw new BusinessException("888888", "租户Id不能为空");
        }
        if(StringUtil.isBlank(req.getTradeCode())){
            throw new BusinessException("888888", "行业编码不能为空");
        }
        ResponseHeader header = new ResponseHeader();
        try {
            List<SpecVo> specs = standardProductBusiSV.getAllSpec(req);
            if(!CollectionUtil.isEmpty(specs)){
                specResponse.setSpecVos(specs);
            }
            header.setIsSuccess(true);
            header.setResultCode("000000");
            header.setResultMessage("查询规格成功");
            specResponse.setResponseHeader(header);
        } catch (Exception e) {
            LOG.error("查询规格异常",e);
            throw new BusinessException("查询规格异常",e);
        }

        return specResponse;
    }

    @Override
    public PageInfoResponse<StandardProductVo> getProductList(ProductQueryVo req) throws BusinessException, SystemException {
        PageInfoResponse<StandardProductVo> response = new PageInfoResponse<>();
        ResponseHeader header = new ResponseHeader();
        if(StringUtil.isBlank(req.getTenantId())){
            throw new BusinessException("888888", "租户Id不能为空");
        }
        if(StringUtil.isBlank(req.getTradeCode())){
            throw new BusinessException("888888", "行业编码不能为空");
        }
        try {
            Integer count = standardProductBusiSV.getProductCount(req);
            if(count!=null&&count>0){
                List<StandardProductVo> productVos = standardProductBusiSV.getProductList(req);
                response.setResult(productVos);
                response.setCount(count);
                response.setPageCount((count+req.getPageSize()-1)/req.getPageSize());
                response.setPageNo(req.getPageNo());
                response.setPageSize(req.getPageSize());
            }else{
                response.setCount(0);
                response.setPageCount(0);
                response.setPageNo(req.getPageNo());
                response.setPageSize(req.getPageSize());
            }
            header.setIsSuccess(true);
            header.setResultMessage("查询产品成功");
            header.setResultCode("000000");
            response.setResponseHeader(header);
        } catch (Exception e) {
            LOG.error("查询产品异常",e);
            throw new BusinessException("查询产品异常",e);
        }

        return response;
    }

    @Override
    public StandardProductVo getStandardProduct(ProductQueryVo req) throws BusinessException, SystemException {
        if(StringUtil.isBlank(req.getTenantId())){
            throw new BusinessException("888888", "租户Id不能为空");
        }
        if(StringUtil.isBlank(req.getProductId())&&StringUtil.isBlank(req.getProductName())){
            throw new BusinessException("888888", "产品id和产品名称同时为空，无法查询");
        }else{
            try {
                if(!StringUtil.isBlank(req.getProductId())){
                    return standardProductBusiSV.getProductById(req.getProductId());
                }else {
                    return standardProductBusiSV.getProductByName(req.getProductName());
                }
            } catch (Exception e) {
                LOG.error("查询标准产品异常",e);
                throw new BusinessException("查询标准产品异常",e);
            }
        }
    }

    @Override
    public BaseResponse deleteStandardProduct(ProductQueryVo req) throws BusinessException, SystemException {
        if(StringUtil.isBlank(req.getTenantId())){
            throw new BusinessException("888888", "租户Id不能为空");
        }
        if(StringUtil.isBlank(req.getProductId())){
            throw new BusinessException("888888", "产品id不能为空");
        }
        BaseResponse baseResponse = new BaseResponse();
        ResponseHeader header = new ResponseHeader();

        try {
            standardProductBusiSV.deleteProductById(req);
            header.setIsSuccess(true);
            header.setResultCode("000000");
            header.setResultMessage("删除【"+req.getProductId()+"】成功");
            baseResponse.setResponseHeader(header);
        } catch (Exception e) {
            LOG.error("删除标准产品异常",e);
            throw new BusinessException("删除标准产品异常",e);
        }
        return baseResponse;
    }
}
