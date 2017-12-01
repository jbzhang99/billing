package com.ai.baas.prd.service.business.impl;

import com.ai.baas.prd.api.product.params.*;
import com.ai.baas.prd.dao.mapper.bo.*;
import com.ai.baas.prd.service.atom.interfaces.IPmCatalogInfoAtomSV;
import com.ai.baas.prd.service.atom.interfaces.IPmCategoryInfoAtomSV;
import com.ai.baas.prd.service.atom.interfaces.PmPolicyDetailAtomSV;
import com.ai.baas.prd.service.atom.interfaces.PmPolicyFactorAtomSV;
import com.ai.baas.prd.service.business.interfaces.IStandardProductBusiSV;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StandardProductBusiSVImpl implements IStandardProductBusiSV{

    @Autowired
    private IPmCatalogInfoAtomSV pmCatalogInfoAtomSV;
    @Autowired
    private IPmCategoryInfoAtomSV pmCategoryInfoAtomSV;
    @Autowired
    private PmPolicyFactorAtomSV policyFactorAtomSV;
    @Autowired
    private PmPolicyDetailAtomSV policyDetailAtomSV;

    @Override
    public void addProduct(StandardProductRequest req) {
        PmCatalogInfo catalogInfo = new PmCatalogInfo();
        BeanUtils.copyProperties(catalogInfo,req);
        pmCatalogInfoAtomSV.addCataLog(catalogInfo);
    }

    @Override
    public List<SpecVo> getAllSpec(SpecQueryVo req) {
        List<PmCatalogInfo> catalogInfos = pmCatalogInfoAtomSV.getAllSpec(req);
        List<SpecVo> specVos = null;
        if(!CollectionUtil.isEmpty(catalogInfos)){
            specVos = new ArrayList<>(catalogInfos.size());
            for(PmCatalogInfo catalogInfo:catalogInfos){
                SpecVo specVo = new SpecVo();
                BeanUtils.copyProperties(specVo,catalogInfo);
                specVos.add(specVo);
            }
        }
        return specVos;
    }

    @Override
    public Integer getProductCount(ProductQueryVo req) {
        return pmCatalogInfoAtomSV.getProductCount(req);
    }

    @Override
    public List<StandardProductVo> getProductList(ProductQueryVo req) {
        List<PmCatalogInfo> catalogInfos = pmCatalogInfoAtomSV.getProductList(req);
        List<StandardProductVo> productVos = null;
        if(!CollectionUtil.isEmpty(catalogInfos)){
            productVos = new ArrayList<>();
            int index = 0;
            for(PmCatalogInfo catalogInfo:catalogInfos){
                index++;
                StandardProductVo standardProductVo = new StandardProductVo();
                BeanUtils.copyProperties(standardProductVo,catalogInfo);
                PmCategoryInfo primary = pmCategoryInfoAtomSV.getCategoryInfoByCategoryId(req.getTenantId(),catalogInfo.getMainProductId());
                PmCategoryInfo second = pmCategoryInfoAtomSV.getCategoryInfoByCategoryId(req.getTenantId(),catalogInfo.getCategoryId());
                if(primary!=null){
                    standardProductVo.setPrimaryCategoryName(primary.getCategoryName());
                }
                if(second!=null){
                    standardProductVo.setSecondCategoryName(second.getCategoryName());
                }
                standardProductVo.setIndex((req.getPageNo()-1)*req.getPageSize()+index);
                List<PmPolicyDetail> pmPolicyDetails = policyDetailAtomSV.selectByPolicyID(catalogInfo.getPricePolicy(), req.getTenantId());
                if(!CollectionUtil.isEmpty(pmPolicyDetails)){
                    List<PriceVO> priceVOS = new ArrayList<>();
                    for(PmPolicyDetail policyDetail:pmPolicyDetails){
                        PriceVO priceVO = new PriceVO();
                        priceVO.setDetailId(policyDetail.getDetailId());
                        priceVO.setPrice(new BigDecimal(policyDetail.getPrice()));
                        List<PmPolicyFactor> pmPolicyFactors = policyFactorAtomSV.queryPolicyByDetailId(policyDetail.getDetailId(), req.getTenantId());
                        if(!CollectionUtil.isEmpty(pmPolicyFactors)){
                            priceVO.setSpecName(pmPolicyFactors.get(0).getFactorValue());
                        }
                        priceVOS.add(priceVO);
                    }
                    standardProductVo.setPriceVOs(priceVOS);
                }
                productVos.add(standardProductVo);
            }
        }
        return productVos;
    }

    @Override
    public StandardProductVo getProductById(String productId) {
        PmCatalogInfo pmCatalogInfo = pmCatalogInfoAtomSV.getProductById(productId);
        if(pmCatalogInfo!=null){
            StandardProductVo standardProductVo = new StandardProductVo();
            BeanUtils.copyProperties(standardProductVo,pmCatalogInfo);
            return standardProductVo;
        }
        return null;
    }

    @Override
    public void deleteProductById(ProductQueryVo req) {
        pmCatalogInfoAtomSV.deleteProductById(req.getProductId());
    }

    @Override
    public void updateProduct(StandardProductRequest req) {
        pmCatalogInfoAtomSV.updateProduct(req);
    }

    @Override
    public StandardProductVo getProductByName(String productName) {
        PmCatalogInfo pmCatalogInfo = pmCatalogInfoAtomSV.getProductByName(productName);
        if(pmCatalogInfo!=null){
            StandardProductVo standardProductVo = new StandardProductVo();
            BeanUtils.copyProperties(standardProductVo,pmCatalogInfo);
            return standardProductVo;
        }
        return null;
    }
}
