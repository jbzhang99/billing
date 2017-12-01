package com.ai.baas.ccp.service.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.ccp.dao.mapper.bo.OmcScoutActionDefine;
import com.ai.baas.ccp.service.business.interfaces.IConfigContainerBusiSV;
import com.ai.baas.ccp.topoligy.core.exception.OmcException;
import com.ai.baas.ccp.topoligy.core.manager.parameters.dao.IConfigObtain;
import com.ai.baas.ccp.topoligy.core.manager.parameters.entity.OmcCalConf;
import com.ai.baas.ccp.topoligy.core.manager.parameters.entity.OmcCalConfKey;
import com.ai.baas.ccp.topoligy.core.manager.parameters.entity.Policy;
import com.ai.baas.ccp.topoligy.core.manager.parameters.entity.PolicyConf;
import com.ai.baas.ccp.topoligy.core.manager.parameters.entity.PolicyConfKey;
import com.ai.baas.ccp.topoligy.core.manager.parameters.entity.PolicyKey;
import com.ai.baas.ccp.topoligy.core.pojo.SectionRule;
import com.ai.baas.ccp.vo.ConfigContainerObject;

/**
 * 完成参数加载，匹配功能
 * 
 * @ClassName: ConfigContainer
 * @author lvsj
 * @date 2015年12月24日 下午3:16:20
 * 
 */
@Component
public final class ConfigContainerBusiSVImpl implements IConfigContainerBusiSV {
    // private static final Logger logger = LoggerFactory.getLogger(ConfigContainer.class);
    @Autowired
    private IConfigObtain configObtainService;

    public ConfigContainerObject configObtain() throws OmcException {
        List<Policy> policies = configObtainService.selectPolicyAll();
        List<SectionRule> sectionRules = configObtainService.selectSectionRuleAll();
        List<OmcScoutActionDefine> omcScoutActionDefines = configObtainService.selectActionAll();

        ConfigContainerObject configContainerObject = new ConfigContainerObject();
        routePolicyAndRule(policies, sectionRules, configContainerObject);
        routeOmcCalConf(configObtainService.selectOmcCfgAll(), configContainerObject);
        routePolicyConf(configObtainService.selectPolicyCfgAll(), configContainerObject);

        if (omcScoutActionDefines != null) {
            List<OmcScoutActionDefine> actionDefines = new ArrayList<OmcScoutActionDefine>();
            actionDefines.addAll(omcScoutActionDefines);
            configContainerObject.setActionDefines(actionDefines);
        }
        return configContainerObject;

    }

    /**
     * 将策略和规则放到Map中
     * 
     * @param list
     * @param rules
     * @param configContainerObject
     * @throws OmcException
     */
    private void routePolicyAndRule(List<Policy> list, List<SectionRule> rules,
            ConfigContainerObject configContainerObject) throws OmcException {
        HashMap<PolicyKey, Policy> policyMap = new HashMap<PolicyKey, Policy>();
        HashMap<String, Policy> policyIdMap = new HashMap<String, Policy>();
        HashMap<PolicyKey, List<SectionRule>> policyForRuleMap = new HashMap<PolicyKey, List<SectionRule>>();
        HashMap<Integer, SectionRule> ruleMap = new HashMap<Integer, SectionRule>();

        if ((list == null) || (list.isEmpty())) {
            return;
        }
        // 处理策略
        for (Policy policy : list) {
            PolicyKey policyKey = policy.getPolicyKey();

            if (policyMap.get(policyKey) == null) {
                policyMap.put(policyKey, policy);
            }

            if (policyIdMap.get(policy.getPolicyId()) == null) {
                policyIdMap.put(policy.getPolicyId(), policy);
            }
        }
        // 处理规则
        Iterator<SectionRule> iterator = rules.iterator();
        while (iterator.hasNext()) {
            SectionRule sectionRule = iterator.next();
            if (ruleMap.get(sectionRule.getScoutruleid()) == null) {
                ruleMap.put(sectionRule.getScoutruleid(), sectionRule);
            }

            Policy policy = policyIdMap.get(sectionRule.getPolicyId());
            if (policy == null) {
                throw new OmcException("routePolicyAndRule", "信控策略和规则配置不一致");
            }

            PolicyKey policyKey = policy.getPolicyKey();
            if (!sectionRule.getTenantid().equals(policyKey.getTenantid())) {
                continue;
            }
            List<SectionRule> sectionRules = policyForRuleMap.get(policyKey);
            if (sectionRules == null) {
                sectionRules = new ArrayList<SectionRule>();
                sectionRules.add(sectionRule);
                policyForRuleMap.put(policyKey, sectionRules);
            } else {
                boolean bFind = false;
                for (SectionRule st : sectionRules) {
                    if (st.getScoutruleid() == sectionRule.getScoutruleid()) {
                        bFind = true;
                    }
                }
                if (!bFind) {
                    sectionRules.add(sectionRule);
                }
            }
        }

        configContainerObject.setPolicyMap(policyMap);
        configContainerObject.setPolicyIdMap(policyIdMap);
        configContainerObject.setPolicyForRuleMap(policyForRuleMap);
        configContainerObject.setRuleMap(ruleMap);
    }

    /**
     * 将信控租户参数放到Map中
     * 
     * @param list
     */
    private void routeOmcCalConf(List<OmcCalConf> list, ConfigContainerObject configContainerObject) {
        HashMap<OmcCalConfKey, OmcCalConf> omcCalConfMap = new HashMap<OmcCalConfKey, OmcCalConf>();
        if ((list == null) || (list.isEmpty())) {
            return;
        }

        for (OmcCalConf omcCalConf : list) {
            OmcCalConfKey omcCalConfKey = new OmcCalConfKey();
            omcCalConfKey.setTenantid(omcCalConf.getConfkey().getTenantid());
            omcCalConfKey.setConfkey(omcCalConf.getConfkey().getConfkey());

            if (omcCalConfMap.get(omcCalConfKey) == null) {
                omcCalConfMap.put(omcCalConfKey, omcCalConf);
            }
        }
        configContainerObject.setOmcCalConfMap(omcCalConfMap);
    }

    /**
     * 将策略参数放到Map中
     * 
     * @param list
     * @param configContainerObject
     * @return void
     */
    private void routePolicyConf(List<PolicyConf> list, ConfigContainerObject configContainerObject) {
        Map<PolicyConfKey, PolicyConf> policyConfMap = new HashMap<PolicyConfKey, PolicyConf>();

        if ((list == null) || (list.isEmpty())) {
            return;
        }

        for (PolicyConf policyConf : list) {
            PolicyConfKey policyConfKey = policyConf.getPolicyConfKey();

            if (policyConfMap.get(policyConfKey) == null) {
                policyConfMap.put(policyConfKey, policyConf);
            }

        }
        configContainerObject.setPolicyConfMap(policyConfMap);
    }

}
