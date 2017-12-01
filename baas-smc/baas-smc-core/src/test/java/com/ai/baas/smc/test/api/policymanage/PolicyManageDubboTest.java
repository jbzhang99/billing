package com.ai.baas.smc.test.api.policymanage;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.smc.api.policymanage.interfaces.IPolicyManageSV;
import com.ai.baas.smc.api.policymanage.param.PolicyCreateRequest;
import com.ai.baas.smc.api.policymanage.param.PolicyItemConditionCreateInfo;
import com.ai.baas.smc.api.policymanage.param.PolicyItemCreateInfo;
import com.ai.baas.smc.api.policymanage.param.PolicyItemPlanCreateInfo;
import com.ai.baas.smc.api.policymanage.param.PolicyListQueryInfo;
import com.ai.baas.smc.api.policymanage.param.PolicyListQueryRequest;
import com.ai.baas.smc.api.policymanage.param.PolicyListQueryResponse;
import com.ai.baas.smc.api.policymanage.param.PolicyModifyRequest;
import com.ai.baas.smc.api.policymanage.param.StepCalValue;
import com.ai.baas.smc.constants.SmcConstants;
import com.ai.baas.smc.constants.SmcConstants.StlPolicy;
import com.ai.baas.smc.service.busi.interfaces.IPolicyManageBusiSV;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/applicationContextTest/dubbo-consumer-context.xml" })
public class PolicyManageDubboTest {
    @Autowired
    protected ApplicationContext ctx;

    @Test
    public void createPolicy() throws SystemException {
        PolicyCreateRequest request = new PolicyCreateRequest();
        request.setBillStyleSn("1");
        request.setCheckFeeFlag(SmcConstants.StlPolicy.CheckFeeFlag.NO);
        request.setDataElementId(1L);
        request.setDataObjectId(SmcConstants.StlPolicy.DataObjectId.CUST);
        request.setEndTimeStr("20160331");
        request.setExecCycle(StlPolicy.ExecCycle.DAY);
        request.setExecTimeStr("1");
        request.setOptDeptId("2");
        request.setOptOperId("3");
        request.setPolicyCode("P0001");
        request.setPolicyDesc("test");
        request.setPolicyName("pName");
        request.setPolicyType("4");
        request.setStartTimeStr("20160301");
        request.setStlElementId(2L);
        request.setStlObjectId("1");
        request.setTenantId("1");
        request.setTenantPwd("2");

        PolicyItemCreateInfo policyItemCreateInfo = new PolicyItemCreateInfo();
        PolicyItemConditionCreateInfo policyItemConditionCreateInfo = new PolicyItemConditionCreateInfo();
        policyItemConditionCreateInfo.setElementId(3L);
        policyItemConditionCreateInfo.setMatchType(">");
        policyItemConditionCreateInfo.setMatchValue("1");
        policyItemConditionCreateInfo.setObjectId("cust");

        List<PolicyItemConditionCreateInfo> policyItemConditionCreateInfos = new ArrayList<PolicyItemConditionCreateInfo>();
        policyItemConditionCreateInfos.add(policyItemConditionCreateInfo);
        policyItemCreateInfo.setPolicyItemConditionCreateInfos(policyItemConditionCreateInfos);

        PolicyItemPlanCreateInfo policyItemPlanCreateInfo = new PolicyItemPlanCreateInfo();
        policyItemPlanCreateInfo.setCalType("price");
        policyItemPlanCreateInfo.setElementId(4L);
        policyItemPlanCreateInfo.setFeeItem("123");
        policyItemPlanCreateInfo.setNormalCalValue("1");
        policyItemPlanCreateInfo.setObjectId("cust");
        policyItemPlanCreateInfo.setPlanType("step");
        StepCalValue stepCalValue = new StepCalValue();
        stepCalValue.setStartValue("1");
        stepCalValue.setEndValue("10");
        stepCalValue.setCalValue("1.5");
        List<StepCalValue> stepCalValues = new ArrayList<StepCalValue>();
        stepCalValues.add(stepCalValue);
        policyItemPlanCreateInfo.setStepCalValues(stepCalValues);

        List<PolicyItemPlanCreateInfo> policyItemPlanCreateInfos = new ArrayList<PolicyItemPlanCreateInfo>();
        policyItemPlanCreateInfos.add(policyItemPlanCreateInfo);
        policyItemCreateInfo.setPolicyItemPlanCreateInfos(policyItemPlanCreateInfos);

        List<PolicyItemCreateInfo> policyItemCreateInfos = new ArrayList<PolicyItemCreateInfo>();
        policyItemCreateInfos.add(policyItemCreateInfo);

        request.setPolicyItemCreateInfo(policyItemCreateInfos);
        IPolicyManageSV sv = (IPolicyManageSV) ctx.getBean("iPolicyManageSV");
        System.out.println(JSON.toJSONString(request));
        BaseResponse s = sv.createPolicy(request);
        System.out.println(s.getResponseHeader().getResultMessage());
    }

    @Test
    public void modifyPolicy() {
        PolicyModifyRequest request = new PolicyModifyRequest();
        request.setPolicyId(101L);
        request.setBillStyleSn("2");
        request.setCheckFeeFlag(SmcConstants.StlPolicy.CheckFeeFlag.NO);
        request.setDataElementId(1L);
        request.setEndTimeStr("20160331");
        request.setExecCycle(StlPolicy.ExecCycle.DAY);
        request.setExecTimeStr("1");
        request.setOptDeptId("2");
        request.setOptOperId("3");
        request.setPolicyDesc("test");
        request.setPolicyName("pName");
        request.setPolicyType("4");
        request.setStartTimeStr("20160301");
        request.setStlElementId(322L);
        request.setStlObjectId("1");
        request.setTenantId("FAN");
        request.setTenantPwd("2");

        PolicyItemCreateInfo policyItemCreateInfo = new PolicyItemCreateInfo();
        PolicyItemConditionCreateInfo policyItemConditionCreateInfo = new PolicyItemConditionCreateInfo();
        policyItemConditionCreateInfo.setElementId(3L);
        policyItemConditionCreateInfo.setMatchType(">");
        policyItemConditionCreateInfo.setMatchValue("1");
        policyItemConditionCreateInfo.setObjectId("cust");

        List<PolicyItemConditionCreateInfo> policyItemConditionCreateInfos = new ArrayList<PolicyItemConditionCreateInfo>();
        policyItemConditionCreateInfos.add(policyItemConditionCreateInfo);
        policyItemCreateInfo.setPolicyItemConditionCreateInfos(policyItemConditionCreateInfos);

        PolicyItemPlanCreateInfo policyItemPlanCreateInfo = new PolicyItemPlanCreateInfo();
        policyItemPlanCreateInfo.setCalType("price");
        policyItemPlanCreateInfo.setElementId(4L);
        policyItemPlanCreateInfo.setFeeItem("123");
        policyItemPlanCreateInfo.setNormalCalValue("1");
        policyItemPlanCreateInfo.setObjectId("cust");
        policyItemPlanCreateInfo.setPlanType("step");
        StepCalValue stepCalValue = new StepCalValue();
        stepCalValue.setStartValue("1");
        stepCalValue.setEndValue("10");
        stepCalValue.setCalValue("1.5");
        List<StepCalValue> stepCalValues = new ArrayList<StepCalValue>();
        stepCalValues.add(stepCalValue);
        policyItemPlanCreateInfo.setStepCalValues(stepCalValues);

        List<PolicyItemPlanCreateInfo> policyItemPlanCreateInfos = new ArrayList<PolicyItemPlanCreateInfo>();
        policyItemPlanCreateInfos.add(policyItemPlanCreateInfo);
        policyItemCreateInfo.setPolicyItemPlanCreateInfos(policyItemPlanCreateInfos);

        List<PolicyItemCreateInfo> policyItemCreateInfos = new ArrayList<PolicyItemCreateInfo>();
        policyItemCreateInfos.add(policyItemCreateInfo);

        request.setPolicyItemModifyInfos(policyItemCreateInfos);

        IPolicyManageSV sv = (IPolicyManageSV) ctx.getBean("iPolicyManageSV");
        System.out.println(JSON.toJSONString(request));
        BaseResponse s = sv.modifyPolicy(request);
        System.out.println(s.getResponseHeader().getResultMessage());
    }

    @Test
    public void queryPolicyList() {
        PolicyListQueryRequest request = new PolicyListQueryRequest();
        request.setTenantId("1333");
        request.setPolicyCode("");
        System.out.println(JSON.toJSONString(request));
        PolicyListQueryResponse response = ((IPolicyManageSV) ctx.getBean("iPolicyManageSV"))
                .queryPolicyList(request);
        System.out.println(response.getResponseHeader().getResultMessage());
    }
}
