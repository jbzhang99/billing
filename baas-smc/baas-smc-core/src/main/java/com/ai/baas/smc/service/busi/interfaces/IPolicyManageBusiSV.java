package com.ai.baas.smc.service.busi.interfaces;

import com.ai.baas.smc.api.policymanage.param.PolicyCancelRequest;
import com.ai.baas.smc.api.policymanage.param.PolicyCreateRequest;
import com.ai.baas.smc.api.policymanage.param.PolicyDetailQueryRequest;
import com.ai.baas.smc.api.policymanage.param.PolicyDetailQueryResponse;
import com.ai.baas.smc.api.policymanage.param.PolicyListQueryInfo;
import com.ai.baas.smc.api.policymanage.param.PolicyListQueryRequest;
import com.ai.baas.smc.api.policymanage.param.PolicyModifyRequest;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;

public interface IPolicyManageBusiSV {

    void createPolicy(PolicyCreateRequest request) throws SystemException;

    void modifyPolicy(PolicyModifyRequest request);

    void cancelPolicy(PolicyCancelRequest request);

    PageInfo<PolicyListQueryInfo> queryPolicyList(PolicyListQueryRequest request);

    PolicyDetailQueryResponse queryPolicyDetail(PolicyDetailQueryRequest request);

}
