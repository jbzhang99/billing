package com.ai.baas.prd.api.category.interfaces;

import com.ai.baas.prd.api.category.params.CategoryQuery;
import com.ai.baas.prd.api.category.params.CategoryQueryResponse;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 *定义产品类目接口
 * Date: 2016年11月9日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author wangyx13
 */
@Path("/category")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
public interface ICategorySV {

    /**
     * 查询产品类目信息
     * @param req
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author wangyx13
     * @ApiDocMethod
     * @ApiCode prd-0031
     * @RestRelativeURL category/getCategoryList
     */
    @POST
    @Path("/getCategoryList")
    public CategoryQueryResponse getCategoryList(CategoryQuery req) throws BusinessException, SystemException;
}
