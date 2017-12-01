package com.ai.baas.ccp.service.business.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.ccp.service.business.interfaces.IEventProcessorBusiSV;
import com.ai.baas.ccp.topoligy.core.constant.OmcCalKey;
import com.ai.baas.ccp.topoligy.core.constant.OwnerType;
import com.ai.baas.ccp.topoligy.core.exception.OmcException;
import com.ai.baas.ccp.topoligy.core.manager.parameters.entity.Policy;
import com.ai.baas.ccp.topoligy.core.manager.service.CustomerService;
import com.ai.baas.ccp.topoligy.core.manager.service.SubsUserService;
import com.ai.baas.ccp.topoligy.core.pojo.Customer;
import com.ai.baas.ccp.topoligy.core.pojo.OmcObj;
import com.ai.baas.ccp.topoligy.core.pojo.SectionRule;
import com.ai.baas.ccp.topoligy.core.pojo.User;
import com.ai.baas.ccp.vo.ConfigContainerObject;
import com.ai.baas.ccp.vo.EventProcessorObject;
import com.ai.paas.ipaas.util.StringUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Component
public class EventProcessorBusiSVImpl implements IEventProcessorBusiSV {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventProcessorBusiSVImpl.class);

    public static final String DF_POLICY = OmcCalKey.OMC_POLICY_ID;

    public static final String DF_RULES = OmcCalKey.OMC_RULE_ID_LIST;

    public static final String DF_RULE_ID = OmcCalKey.OMC_RULE_ID;

    @Autowired
    private SubsUserService subsUserService;

    @Autowired
    private transient CustomerService customerService;

    public EventProcessorObject process(EventProcessorObject eventProcessorObject) {
        ConfigContainerObject configContainerObject = eventProcessorObject.getConfig();
        OmcObj omcObj = eventProcessorObject.getOmcobj();
        // 获取信控策略 update by 2016-05-17
        // 变更点 由根据租户+event_type找的策略id 现根据用户先查用户策略，如果没查到再查对应的公共策略
        Policy policy = null;
        if (OwnerType.SERV.equals(omcObj.getOwertype())) {
            User usrs = subsUserService.selectById(omcObj.getTenantid(), omcObj.getOwerid());
            if (null == usrs) {
                throw new OmcException("Information", "没有取到用户信息infoOwner" + omcObj.toString());
            }
            if (!StringUtil.isBlank(usrs.getPolicy_id())) {
                policy = configContainerObject.getPolicyById(usrs.getPolicy_id());
            }
        }
        if (OwnerType.CUST.equals(omcObj.getOwertype())) {
            Customer customer = customerService.getCustomer(omcObj.getTenantid(),
                    omcObj.getOwerid());
            if (null == customer) {
                throw new OmcException("Information", "没有取到用户信息infoOwner" + omcObj.toString());
            }
            if (!StringUtil.isBlank(customer.getPolicyId())) {
                policy = configContainerObject.getPolicyById(customer.getPolicyId());
            }
        }
        if (null == policy) {
            LOGGER.info("用户[" + omcObj.toString() + "]没有信控方案，采用默认的信控方案");
            policy = configContainerObject
                    .getPolicy(omcObj.getTenantid(), omcObj.getBusinesscode());
            if (null == policy) {
                throw new OmcException("EventProcessor", "获取信控策略失败,请检查配置" + omcObj.toString());
            }
        }
        // //获取信控策略
        // Policy policy = configContainer.getPolicy(obj.getTenantid(),obj.getBusinesscode());
        //
        // if (null == policy) {
        // throw new OmcException("EventProcessor", "获取信控策略失败,请检查配置" + obj.toString());
        // }
        // 查询策略所对应的规则
        List<SectionRule> sectionRules = configContainerObject.getSectionRules(policy
                .getPolicyKey());
        // 不存在对应的信控规则
        if (null == sectionRules || sectionRules.isEmpty()) {
            throw new OmcException("EventProcess", "获取policy[" + policy.getPolicyKey().toString()
                    + "]对应的信控失败，请检查配置");
        }

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(OmcCalKey.OMC_POLICY_ID, policy.getPolicyId());
        JsonArray jsonArray = new JsonArray();
        for (SectionRule sectionRule : sectionRules) {
            JsonObject jsonobj = new JsonObject();
            jsonobj.addProperty(OmcCalKey.OMC_RULE_ID, sectionRule.getScoutruleid());
            jsonArray.add(jsonobj);
        }
        jsonObject.add(OmcCalKey.OMC_RULE_ID_LIST, jsonArray);
        eventProcessorObject.setOutput(jsonObject);
        return eventProcessorObject;
    }

}
