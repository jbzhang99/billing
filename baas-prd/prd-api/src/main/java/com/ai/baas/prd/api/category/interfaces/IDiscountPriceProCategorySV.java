package com.ai.baas.prd.api.category.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.prd.api.category.params.DiscountPriceProCategoryRequest;
import com.ai.baas.prd.api.category.params.DiscountPriceProCategoryResponse;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;

@Path("/discountPriceProCategory")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
public interface IDiscountPriceProCategorySV {

    /**
     * 活动的类目ID查询
     * @param req
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author wangjing19
     * @ApiDocMethod
     * @ApiCode 
     * @RestRelativeURL discountPriceProCategory/getDiscountCategoryID
     */
    @POST
    @Path("/getDiscountCategoryID")
	public DiscountPriceProCategoryResponse getDiscountCategoryID(DiscountPriceProCategoryRequest request);
}
