package com.ai.baas.cust.api.custinfo.interfaces;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.cust.api.custinfo.params.*;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;


/**
 * 客户信息dubbo服务<br>
 * Date: 2016年3月15日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author wangzhi
 */ 
@Path("/cust/service")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface ICustInfoSV {

	
	@interface CustNotify{}
	/**
	 * 将客户信息基础资料同步插入到计费域对应的表中
	 *
	 * @return BaaS-000000成功；其他失败
	 * @throws BusinessException
	 * @throws SystemException
	 * @author wangzhi 
	 * @ApiCode BaaS-0001
	 * @param custInfo java beans 对象
	 * @RestRelativeURL cust/service/sync/custNotify
	 */
	@Path("/sync/custNotify")
    @POST
	public BaseResponse custNotify(CustInfoParams custInfo)throws BusinessException, SystemException;
	
	/**
	 * 将客户,账户，用户信息基础资料同步插入到计费域对应的表中
	 *
	 * @return BaaS-000000成功；其他失败
	 * @throws BusinessException
	 * @throws SystemException
	 * @author wangly8 
	 * @ApiCode 
	 * @param custInfo java beans 对象
	 * @RestRelativeURL cust/service/sync/custInfoNotify
	 */
	@Path("/sync/custInfoNotify")
    @POST
	public BaseResponse custInfoNotify(CustInfoParams custInfo)throws BusinessException, SystemException;
	
	/**
	 * 查询客户表信息
	 *
	 * @return BaaS-000000成功；其他失败
	 * @throws BusinessException
	 * @throws SystemException
	 * @author wangly8 
	 * @ApiCode 
	 * @param 
	 * @RestRelativeURL cust/service/getBlCustInfos
	 */
	@Path("/getBlCustInfos")
    @POST
	public List<BlCustinfoVo> getBlCustInfos(BlCustinfoParams queryParam)throws BusinessException, SystemException;

	/**
	 * 设置
	 *
	 * @return BaaS-000000成功；其他失败
	 * @throws BusinessException
	 * @throws SystemException
	 * @author wangly8 
	 * @ApiCode 
	 * @param 
	 * @RestRelativeURL cust/service/setPolicyId
	 */
	@Path("/setPolicyId")
    @POST
	public BaseResponse setPolicyId(ExtCustParams custInfo)throws BusinessException, SystemException;

	/**
	 * 更新
	 *
	 * @return BaaS-000000成功；其他失败
	 * @throws BusinessException
	 * @throws SystemException
	 * @author wangyx13
	 * @ApiCode
	 * @param
	 * @RestRelativeURL cust/service/updateCustInfo
	 */
	@Path("/updateCustInfo")
	@POST
	public BaseResponse updateCustInfo(CustInfoParams custInfo)throws BusinessException, SystemException;

	/**
	 * 删除
	 *
	 * @return BaaS-000000成功；其他失败
	 * @throws BusinessException
	 * @throws SystemException
	 * @author wangyx13
	 * @ApiCode
	 * @param
	 * @RestRelativeURL cust/service/deleteCustInfo
	 */
	@Path("/deleteCustInfo")
	@POST
	public BaseResponse deleteCustInfo(CustIdParams params)throws BusinessException, SystemException;
	
}
