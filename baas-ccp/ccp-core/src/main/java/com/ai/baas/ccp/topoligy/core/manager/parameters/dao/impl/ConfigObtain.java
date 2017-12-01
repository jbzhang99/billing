package com.ai.baas.ccp.topoligy.core.manager.parameters.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.ccp.constants.CcpConstants;
import com.ai.baas.ccp.dao.mapper.bo.OmcPolicyPara;
import com.ai.baas.ccp.dao.mapper.bo.OmcPolicyParaCriteria;
import com.ai.baas.ccp.dao.mapper.bo.OmcScoutActionDefine;
import com.ai.baas.ccp.dao.mapper.bo.OmcScoutActionDefineCriteria;
import com.ai.baas.ccp.dao.mapper.bo.OmcScoutPolicy;
import com.ai.baas.ccp.dao.mapper.bo.OmcScoutPolicyCriteria;
import com.ai.baas.ccp.dao.mapper.bo.OmcScoutRule;
import com.ai.baas.ccp.dao.mapper.bo.OmcScoutRuleCriteria;
import com.ai.baas.ccp.dao.mapper.bo.OmcTenantPara;
import com.ai.baas.ccp.dao.mapper.bo.OmcTenantParaCriteria;
import com.ai.baas.ccp.dao.mapper.interfaces.OmcPolicyParaMapper;
import com.ai.baas.ccp.dao.mapper.interfaces.OmcScoutActionDefineMapper;
import com.ai.baas.ccp.dao.mapper.interfaces.OmcScoutPolicyMapper;
import com.ai.baas.ccp.dao.mapper.interfaces.OmcScoutRuleMapper;
import com.ai.baas.ccp.dao.mapper.interfaces.OmcTenantParaMapper;
import com.ai.baas.ccp.topoligy.core.exception.OmcException;
import com.ai.baas.ccp.topoligy.core.manager.parameters.dao.IConfigObtain;
import com.ai.baas.ccp.topoligy.core.manager.parameters.entity.OmcCalConf;
import com.ai.baas.ccp.topoligy.core.manager.parameters.entity.OmcCalConfKey;
import com.ai.baas.ccp.topoligy.core.manager.parameters.entity.Policy;
import com.ai.baas.ccp.topoligy.core.manager.parameters.entity.PolicyConf;
import com.ai.baas.ccp.topoligy.core.manager.parameters.entity.PolicyConfKey;
import com.ai.baas.ccp.topoligy.core.manager.parameters.entity.PolicyKey;
import com.ai.baas.ccp.topoligy.core.pojo.SectionRule;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;

@Component
public final class ConfigObtain implements IConfigObtain {

    private static final Logger logger = LoggerFactory.getLogger(ConfigObtain.class);

    @Autowired
    private transient OmcTenantParaMapper omcTenantParaMapper;

    @Autowired
    private transient OmcPolicyParaMapper omcPolicyParaMapper;

    @Autowired
    private transient OmcScoutPolicyMapper omcScoutPolicyMapper;

    @Autowired
    private transient OmcScoutRuleMapper omcScoutRuleMapper;

    @Autowired
    private transient OmcScoutActionDefineMapper omcScoutActionDefineMapper;

    /**
     * 获取信控所有租户参数
     */
    @Override
    public List<OmcCalConf> selectOmcCfgAll() throws OmcException {
        List<OmcTenantPara> omcTenantParas = omcTenantParaMapper
                .selectByExample(new OmcTenantParaCriteria());
        if (CollectionUtil.isEmpty(omcTenantParas)) {
            logger.info("信控计算参数无配置数据[omc_tenant_para]");
            return Collections.emptyList();
        }
        List<OmcCalConf> omcCalConfs = new ArrayList<OmcCalConf>();
        for (OmcTenantPara omcTenantPara : omcTenantParas) {
            OmcCalConf omcCalConf = new OmcCalConf();
            OmcCalConfKey omcCalConfKey = new OmcCalConfKey();
            omcCalConfKey.setTenantid(omcTenantPara.getTenantId());
            omcCalConfKey.setConfkey(omcTenantPara.getParaType());
            omcCalConf.setConfkey(omcCalConfKey);
            omcCalConf.setConfvalue(omcTenantPara.getParaValue());
            omcCalConfs.add(omcCalConf);
        }
        logger.info("信控计算装载参数[omc_tenant_para]共[" + omcCalConfs.size() + "]条");
        return omcCalConfs;
    }

    /**
     * 获取信控所有策略参数
     */
    @Override
    public List<PolicyConf> selectPolicyCfgAll() throws OmcException {
        List<OmcPolicyPara> omcPolicyParas = omcPolicyParaMapper
                .selectByExample(new OmcPolicyParaCriteria());
        if (CollectionUtil.isEmpty(omcPolicyParas)) {
            logger.info("信控计算参数无配置数据【omc_policy_para】");
            return Collections.emptyList();
        }

        List<PolicyConf> policyConfs = new ArrayList<PolicyConf>();

        for (OmcPolicyPara omcPolicyPara : omcPolicyParas) {
            PolicyConf policyConf = new PolicyConf();
            PolicyConfKey policyConfKey = new PolicyConfKey();
            policyConfKey.setTenantid(omcPolicyPara.getTenantId());
            policyConfKey.setConfkey(omcPolicyPara.getParaType());
            policyConfKey.setPolicyid(omcPolicyPara.getPolicyid());
            policyConf.setPolicyConfKey(policyConfKey);
            policyConf.setConfvalue(omcPolicyPara.getParaValue());

            policyConfs.add(policyConf);

        }
        logger.info("信控计算装载参数【omc_policy_para】共【" + policyConfs.size() + "】条");
        return policyConfs;
    }

    /**
     * 获取信控所有策略
     */
    @Override
    public List<Policy> selectPolicyAll() throws OmcException {
        Timestamp sysdate = DateUtil.getSysDate();
        OmcScoutPolicyCriteria criteria = new OmcScoutPolicyCriteria();
        criteria.createCriteria().andStatusEqualTo(CcpConstants.OmcScoutPolicy.Status.VALID)
                /*.andEffDateLessThanOrEqualTo(sysdate).andExpDateGreaterThanOrEqualTo(sysdate)*/;
        List<OmcScoutPolicy> omcScoutPolicies = omcScoutPolicyMapper.selectByExample(criteria);
        if (CollectionUtil.isEmpty(omcScoutPolicies)) {
            throw new OmcException("selectPolicyAll", "信控计算策略无配置数据【omc_scout_policy】");
        }

        List<Policy> policies = new ArrayList<Policy>();

        for (OmcScoutPolicy map : omcScoutPolicies) {
            PolicyKey policyKey = new PolicyKey();
            policyKey.setPolicytype(map.getPolicytype());
            policyKey.setTenantid(map.getTenantId());
            Policy policie = new Policy();
            policie.setPolicyKey(policyKey);
            policie.setPolicyId(map.getPolicyid());
            policie.setStatus(map.getStatus());
            policie.setEffdate(map.getEffDate());
            policie.setExpdate(map.getExpDate());
            policie.setPolicyDescribe(map.getPolicyName());

            policies.add(policie);
        }
        logger.info("信控计算装载参数【omc_scout_policy】共【" + policies.size() + "】条");
        return policies;
    }

    /**
     * 获取信控所有规则
     */
    @Override
    public List<SectionRule> selectSectionRuleAll() throws OmcException {
        List<OmcScoutRule> omcScoutRules = omcScoutRuleMapper
                .selectByExample(new OmcScoutRuleCriteria());
        if (CollectionUtil.isEmpty(omcScoutRules)) {
            throw new OmcException("selectPolicyAll", "信控计算策略无配置数据【selectSectionRuleAll】");
        }
        List<SectionRule> sectionRules = new ArrayList<SectionRule>();
        for (OmcScoutRule map : omcScoutRules) {
            SectionRule sectionRule = new SectionRule();
            sectionRule.setAccttype(map.getAcctType());
            sectionRule.setBalanceceil(map.getBalanceCeil());
            sectionRule.setBalancefloor(map.getBalanceFloor());
            sectionRule.setChargeceil(map.getChargeCeil());
            sectionRule.setChargefloor(map.getChargeFloor());
            sectionRule.setCustlevel(map.getCustLevel());
            sectionRule.setCusttype(map.getCustType());
            sectionRule.setOwemaxdays(map.getOweMaxdays());
            sectionRule.setOwemindays(map.getOweMindays());
            sectionRule.setPolicyId(String.valueOf(map.getPolicyid()));
            sectionRule.setScoutruleid(map.getRuleId().intValue());
            sectionRule.setScouttype(map.getScoutType());
            sectionRule.setTenantid(map.getTenantId());
            sectionRule.setUsertype(map.getUserType());
            sectionRule.setSectiontype(map.getSectionType());

            sectionRules.add(sectionRule);
        }
        logger.info("信控计算装载参数【omc_scout_rule】共【" + sectionRules.size() + "】条");
        return sectionRules;
    }

    /**
     * 获取信控所有指令定义
     */
    @Override
    public List<OmcScoutActionDefine> selectActionAll() throws OmcException {
        List<OmcScoutActionDefine> omcScoutActionDefines = omcScoutActionDefineMapper
                .selectByExample(new OmcScoutActionDefineCriteria());
        if (CollectionUtil.isEmpty(omcScoutActionDefines)) {
            throw new OmcException("selectActionAll", "信控指令未定义【omc_scout_action_define】");
        }
        logger.info("信控计算装载参数【omc_scout_action_define】共【" + omcScoutActionDefines.size() + "】条");
        return omcScoutActionDefines;
    }

}
