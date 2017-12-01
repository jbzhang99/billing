package com.ai.baas.bmc.api.productInfo.interfaces;

import com.ai.baas.bmc.api.productInfo.params.ProductInfoParams;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *产品信息dubbo服务<br>
 * Date: 2015年10月28日 <br>
 * Copyright (c) 2015 asiainfo.com <br>
 * 
 * @author biancx
 */
@Path("/productInfo")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IProductInfoSV {

    /**
     * 套餐包产品信息接口
     * @param product
     * @return CTP-000000成功；其他失败
     * @throws BusinessException,SystemException
     * @author wangkai16
     * @ApiDocMethod
     * @ApiCode CTP-S001
	 * @RestRelativeURL productInfo/productNotify
     */
	@Path("/productNotify")
	@POST
	public String productNotify(ProductInfoParams product) throws BusinessException,SystemException;
	
	
}
