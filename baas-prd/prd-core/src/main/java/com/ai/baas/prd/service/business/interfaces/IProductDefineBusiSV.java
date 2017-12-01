package com.ai.baas.prd.service.business.interfaces;

import com.ai.baas.prd.api.product.params.BaseCategoryInfo;
import com.ai.baas.prd.api.product.params.BaseRequest;
import com.ai.baas.prd.api.product.params.CategoryInfo;
import com.ai.baas.prd.api.product.params.DelProductReq;
import com.ai.baas.prd.api.product.params.MainProduct;
import com.ai.baas.prd.api.product.params.MainProductInfo;
import com.ai.baas.prd.api.product.params.MainProductRes;
import com.ai.baas.prd.api.product.params.ProductRequest;
import com.ai.baas.prd.api.product.params.ProductUpdateReq;
import com.ai.baas.prd.api.product.params.QueryPmCategoryInfoReq;
import com.ai.baas.prd.api.product.params.SubQueryReq;
import com.ai.baas.prd.api.product.params.SubsProductQueryReq;
import com.ai.baas.prd.api.product.params.mainProReq;
import com.ai.opt.base.vo.PageInfo;

public interface IProductDefineBusiSV {

	public void addProduct(ProductRequest req);
	
	public PageInfo<MainProduct> getCategoryInfos(QueryPmCategoryInfoReq req);
	
	//删除产品的功能
	public int delProduct(DelProductReq req);

	/**
	 * 查询单个产品
	 */
	public  MainProductRes getProductById(BaseRequest req);
	/**
	 * 更新产品
	 */
	public int updatePmCategoryInfo(ProductUpdateReq req);
	
	public BaseCategoryInfo querySubsProduct(SubsProductQueryReq req);
	
	PageInfo<CategoryInfo> getCategorys(SubQueryReq req);
	
	/**
	 * 查询主产品
	 */
	PageInfo<MainProductInfo> getMainProduct(mainProReq req);
}
