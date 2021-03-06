package com.ai.baas.smc.api.policymanage.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.baas.smc.api.policymanage.param.PolicyCancelRequest;
import com.ai.baas.smc.api.policymanage.param.PolicyCreateRequest;
import com.ai.baas.smc.api.policymanage.param.PolicyDetailQueryRequest;
import com.ai.baas.smc.api.policymanage.param.PolicyDetailQueryResponse;
import com.ai.baas.smc.api.policymanage.param.PolicyListQueryRequest;
import com.ai.baas.smc.api.policymanage.param.PolicyListQueryResponse;
import com.ai.baas.smc.api.policymanage.param.PolicyModifyRequest;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;

@Path("/policymanage")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IPolicyManageSV {
    /**
     * 政策创建
     * 
     * @param request
     * @return
     * @author mayt
     * @throws BusinessException
     * @throws SystemException
     * @ApiDocMethod
     * @RestRelativeURL policymanage/createPolicy
     */
    @POST
    @Path("/createPolicy")
    BaseResponse createPolicy(PolicyCreateRequest request) throws BusinessException,
            SystemException;

    /**
     * 政策修改
     * 
     * @param request
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author mayt
     * @ApiDocMethod
     * @RestRelativeURL policymanage/modifyPolicy
     */
    @POST
    @Path("/modifyPolicy")
    BaseResponse modifyPolicy(PolicyModifyRequest request) throws BusinessException,
            SystemException;

    /**
     * 政策注销
     * 
     * @param request
     * @return
     * @throws BusinessException
     * @throws SystemException
     * @author mayt
     * @ApiDocMethod
     * @RestRelativeURL policymanage/cancelPolicy
     */
    @POST
    @Path("/cancelPolicy")
    BaseResponse cancelPolicy(PolicyCancelRequest request) throws BusinessException,
            SystemException;

    /**
     * 政策列表查询-分页/不分页
     * 
     * @param request
     * @return
     * @author mayt
     * @ApiDocMethod
     * @RestRelativeURL policymanage/queryPolicyList
     */
    @POST
    @Path("/queryPolicyList")
    PolicyListQueryResponse queryPolicyList(PolicyListQueryRequest request);

    /**
     * 政策明细查询
     * 
     * @param request
     * @return
     * @author mayt
     * @ApiDocMethod
     * @RestRelativeURL policymanage/queryPolicyDetail
     */
    @POST
    @Path("/queryPolicyDetail")
    PolicyDetailQueryResponse queryPolicyDetail(PolicyDetailQueryRequest request);
}
