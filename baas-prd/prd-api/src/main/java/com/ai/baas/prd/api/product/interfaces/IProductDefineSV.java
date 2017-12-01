package com.ai.baas.prd.api.product.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.prd.api.product.params.BaseCategoryInfo;
import com.ai.baas.prd.api.product.params.BaseRequest;
import com.ai.baas.prd.api.product.params.DelProductReq;
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
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;

/**
 * 
 *定义产品目录接口
 * Date: 2016年11月9日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author gaogang
 */
@Path("/productDefine")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
public interface IProductDefineSV {

	/**
	 * 添加产品目录
	 * @param res
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gaogang
	 * @ApiDocMethod
	 * @ApiCode prd-001
	 * @RestRelativeURL productDefine/addProduct
	 */
	 @POST
	 @Path("/addProduct")
	public BaseResponse addProduct(ProductRequest res) throws BusinessException, SystemException;
	/**
	 * 删除产品目录
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gaogang
	 * @ApiDocMethod
	 * @ApiCode
	 * @RestRelativeURL productDefine/delProduct
	 */
	 @POST
	 @Path("/delProduct")
	public BaseResponse delProduct(DelProductReq req) throws BusinessException, SystemException;
	
	/**
	 * 产品查询
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gaogang
	 * @ApiDocMethod
	 * @ApiCode
	 * @RestRelativeURL productDefine/getProduct
	 */
	 @POST
	 @Path("/getProduct")
	public  ProductResponse getProduct(BaseRequest req) throws BusinessException, SystemException;
	/**
	 * 产品目录分页查询
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gaogang
	 * @ApiDocMethod
	 * @ApiCode
	 * @RestRelativeURL productDefine/getCategoryInfos
	 */
	 @POST
	 @Path("/getCategoryInfos")
	public PmcategoryInfoResponse getCategoryInfos(QueryPmCategoryInfoReq req) throws BusinessException, SystemException;
	/**
	 * 修改产品类目信息
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gaogang
	 * @ApiDocMethod
	 * @ApiCode
	 * @RestRelativeURL productDefine/updatePmCategoryInfo
	 */
	 @POST
	 @Path("/updatePmCategoryInfo")
	public BaseResponse updatePmCategoryInfo(ProductUpdateReq req) throws BusinessException, SystemException;
	 
	 /**
	 * 查询子产品目录
	 * @param req
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author wangly8
	 * @ApiDocMethod
	 * @ApiCode
	 * @RestRelativeURL productDefine/querySubsProduct
	 */
	 @POST
	 @Path("/querySubsProduct")
	public BaseCategoryInfo querySubsProduct(SubsProductQueryReq req) throws BusinessException, SystemException;
	 
	 /**
	  * 
	  * @param req
	  * @return
	  * @throws BusinessException
	  * @throws SystemException
	  * @author gaogang
	  * @ApiDocMethod
	  * @ApiCode
	  * @RestRelativeURL productDefine/querySubProducts
	  */
	 @POST
	 @Path("/querySubProducts")
	 public SubProResponse  querySubProducts(SubQueryReq req) throws BusinessException, SystemException;
	 
	 /**
	  * 
	  * @throws BusinessException
	  * @throws SystemException
	  * @author gaogang
	  * @ApiDocMethod
	  * @ApiCode
	  * @RestRelativeURL productDefine/queryMainProduct
	  */
	 @POST
	 @Path("/queryMainProduct")
	 public mainProResponse queryMainProduct(mainProReq req) throws BusinessException, SystemException;
}
