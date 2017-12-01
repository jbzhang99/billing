package com.ai.baas.prd.api.product.interfaces;

import com.ai.baas.prd.api.product.params.*;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 *定义产品目录接口
 * Date: 2016年11月9日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author wangyx13
 */
@Path("/standardProduct")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
public interface IStandardProductSV {
    /**
     * 添加标准产品
     * @param req
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author wangyx13
     * @ApiDocMethod
     * @ApiCode prd-0021
     * @RestRelativeURL standardProduct/addProduct
     */
    @POST
    @Path("/addProduct")
    public BaseResponse addProduct(StandardProductRequest req) throws BusinessException, SystemException;

    /**
     * 获取规格列表
     * @param req
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author wangyx13
     * @ApiDocMethod
     * @ApiCode prd-0022
     * @RestRelativeURL standardProduct/getAllSpec
     */
    @POST
    @Path("/getAllSpec")
    public SpecResponse getAllSpec(SpecQueryVo req) throws BusinessException, SystemException;

    /**
     * 分页查询标准产品
     * @param req
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author wangyx13
     * @ApiDocMethod
     * @ApiCode prd-0023
     * @RestRelativeURL standardProduct/getProductList
     */
    @POST
    @Path("/getProductList")
    public PageInfoResponse<StandardProductVo> getProductList(ProductQueryVo req) throws BusinessException, SystemException;

    /**
     * 根据id查询标准产品
     * @param req
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author wangyx13
     * @ApiDocMethod
     * @ApiCode prd-0024
     * @RestRelativeURL standardProduct/getStandardProduct
     */
    @POST
    @Path("/getStandardProduct")
    public StandardProductVo getStandardProduct(ProductQueryVo req) throws BusinessException, SystemException;

    /**
     * 根据id查询标准产品
     * @param req
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author wangyx13
     * @ApiDocMethod
     * @ApiCode prd-0025
     * @RestRelativeURL standardProduct/deleteStandardProduct
     */
    @POST
    @Path("/deleteStandardProduct")
    public BaseResponse deleteStandardProduct(ProductQueryVo req) throws BusinessException, SystemException;
}
