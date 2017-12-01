package com.ai.baas.amc.api.proferentialbill.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.amc.api.proferentialbill.params.ProductDelVO;
import com.ai.baas.amc.api.proferentialbill.params.ProductResponse;
import com.ai.baas.amc.api.proferentialbill.params.ProductStatusVO;
import com.ai.baas.amc.api.proferentialbill.params.ProferProductVO;
import com.ai.baas.amc.api.proferentialbill.params.RelatedBillInfoVO;
import com.ai.baas.amc.api.proferentialbill.params.RelatedBillQueryVO;
import com.ai.baas.amc.api.proferentialbill.params.RelatedBillResponse;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;

/**
 * 账单优惠管理
 *
 * Date: 2016年3月29日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author gaogang
 */
@Path("/proferProductManage")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IProferProductManageSV {

	/**
	 * 满赠账单添加
	 * @param vo
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gaogang
	 * @ApiDocMethod
	 * @ApiCode AMC_0016
     * @RestRelativeURL proferProductManage/addFullGiftBill
	 */
	@interface AddFullGiftBill{}
	@POST
    @Path("/addFullGiftBill")
	ProductResponse addFullGiftBill(ProferProductVO vo) throws BusinessException,SystemException;
	/**
	 * 满减账单添加
	 * @param vo
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gaogang
	 * @ApiDocMethod
	 * @ApiCode AMC_0017
     * @RestRelativeURL proferProductManage/addFullReduceBill
	 */
	@interface AddFullReduceBill{}
	@POST
    @Path("/addFullReduceBill")
	ProductResponse addFullReduceBill(ProferProductVO vo) throws BusinessException,SystemException;
	/**
	 * 限时折扣账单添加
	 * @param vo
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gaogang
	 * @ApiDocMethod
	 * @ApiCode AMC_0018
     * @RestRelativeURL proferProductManage/addDiscountBill
	 */
	@interface AddDiscountBill{}
	@POST
    @Path("/addDiscountBill")
	ProductResponse addDiscountBill(ProferProductVO vo) throws BusinessException,SystemException;
	
	/**
	 * 保底优惠账单添加
	 * @param vo
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gaogang
	 * @ApiDocMethod
	 * @ApiCode AMC_0019
     * @RestRelativeURL proferProductManage/addBasePriceBill
	 */
	@interface AddBasePriceBill{}

    @POST
    @Path("/addBasePriceBill")
	ProductResponse addBasePriceBill(ProferProductVO vo) throws BusinessException,SystemException;


	/**
	 * 封顶优惠账单添加
	 * @param vo
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gaogang
	 * @ApiDocMethod
	 * @ApiCode AMC_0020
	 */
	@interface AddTopPriceBill{}
	@POST
    @Path("/addTopPriceBill")
	ProductResponse addTopPriceBill(ProferProductVO vo) throws BusinessException,SystemException;

	/**
	 * 修改优惠账单
	 * @param vo
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gaogang
	 * @ApiDocMethod
	 * @ApiCode AMC_0021
	 */
	@interface UpdateProduct{}
	@POST
    @Path("/updateProduct")
	void updateProduct(ProferProductVO vo) throws BusinessException,SystemException;
	/**
	 * 修改产品状态
	 * @param vo
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gaogang
	 * @ApiDocMethod
	 * @ApiCode AMC_0022
	 */
	@interface UpdateProductStatus{}
    @POST
    @Path("/updateProductStatus")
	void updateProductStatus(ProductStatusVO vo) throws BusinessException,SystemException;
	/**
	 * 删除优惠产品
	 * @param vo
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gaogang
	 * @ApiDocMethod
	 * @ApiCode  
	 */
	@interface DelProduct{}
    @POST
    @Path("/delProduct")
	void delProduct(ProductDelVO vo) throws BusinessException,SystemException;
	/**
	 * 获取关联账单科目
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gaogang
	 * @ApiDocMethod
	 * @ApiCode AMC_0023
	 */
	@interface GetRelatedBill{}
	@POST
    @Path("/getRelatedBill")
	RelatedBillResponse getRelatedBill(RelatedBillQueryVO vo) throws BusinessException,SystemException;
	/**
	 * 修改关联优惠账单科目
	 * @param vo
	 * @throws BusinessException
	 * @throws SystemException
	 * @author gaogang
	 * @ApiDocMethod
	 * @ApiCode AMC_0024
	 */
	@interface UpdateRelatedBill{}
	@POST
    @Path("/updateRelatedBill")
	void updateRelatedBill(RelatedBillInfoVO vo) throws BusinessException,SystemException;
}
