package com.ai.baas.prd.service.atom.interfaces;

import java.util.List;

import com.ai.baas.prd.api.element.params.CheckCategoryId;
import com.ai.baas.prd.api.element.params.ElementRequireVO;
import com.ai.baas.prd.api.product.params.ProductQueryVo;
import com.ai.baas.prd.api.product.params.SpecQueryVo;
import com.ai.baas.prd.api.product.params.StandardProductRequest;
import com.ai.baas.prd.dao.mapper.bo.PmCatalogInfo;

public interface IPmCatalogInfoAtomSV {

    public List<PmCatalogInfo> queryElement(ElementRequireVO example);
    
    public List<PmCatalogInfo> queryElementByCategoryIds(List<String> categoryIds,ElementRequireVO vo); 

    void insertElement(List<PmCatalogInfo> pmCatalogInfoList);
    
    void deleteElement(List<PmCatalogInfo> pmCatalogInfoList);
    
    void alterElement(List<PmCatalogInfo> pmCatalogInfoList);
    
    public List<PmCatalogInfo> checkExist(CheckCategoryId example);

    public List<PmCatalogInfo> queryElementDetail(String categoryId, String tenantId);

    List<PmCatalogInfo> queryElementByMainProductId(String tenantId,String mainProductId);

    void deleteElementByCategoryId(String tenantId, String categoryId);

    public int checkExistPolicyId(String policyId, String tenantId);
    
    public List<PmCatalogInfo> queryByPolicyId(String policyId, String tenantId);

    void addCataLog(PmCatalogInfo catalogInfo);

    List<PmCatalogInfo> getAllSpec(SpecQueryVo req);
    
    List<PmCatalogInfo> getAll(String tenantId);

    Integer getProductCount(ProductQueryVo req);

    List<PmCatalogInfo> getProductList(ProductQueryVo req);

    PmCatalogInfo getProductById(String productId);

    void deleteProductById(String productId);

    void updateProduct(StandardProductRequest req);

    PmCatalogInfo getProductByName(String productName);
}
