package com.ai.baas.prd.service.business.interfaces;

import com.ai.baas.prd.api.product.params.*;

import java.util.List;

public interface IStandardProductBusiSV {
    void addProduct(StandardProductRequest req);

    List<SpecVo> getAllSpec(SpecQueryVo req);

    Integer getProductCount(ProductQueryVo req);

    List<StandardProductVo> getProductList(ProductQueryVo req);

    StandardProductVo getProductById(String productId);

    void deleteProductById(ProductQueryVo req);

    void updateProduct(StandardProductRequest req);

    StandardProductVo getProductByName(String productName);
}
