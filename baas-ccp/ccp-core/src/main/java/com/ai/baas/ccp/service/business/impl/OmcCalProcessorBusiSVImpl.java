package com.ai.baas.ccp.service.business.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.ccp.service.business.interfaces.IBalanceCalProcessorBusiSV;
import com.ai.baas.ccp.service.business.interfaces.ICreditCalProcessBusiSV;
import com.ai.baas.ccp.service.business.interfaces.IInformationProcessorBusiSV;
import com.ai.baas.ccp.service.business.interfaces.IOmcCalProcessorBusiSV;
import com.ai.baas.ccp.topoligy.core.constant.FeeSource;
import com.ai.baas.ccp.topoligy.core.constant.OmcCalKey;
import com.ai.baas.ccp.topoligy.core.constant.ScoRuleType;
import com.ai.baas.ccp.topoligy.core.constant.rule.MatchBalance;
import com.ai.baas.ccp.topoligy.core.constant.rule.MatchCharge;
import com.ai.baas.ccp.topoligy.core.constant.rule.MatchCreditLevel;
import com.ai.baas.ccp.topoligy.core.constant.rule.MatchOwners;
import com.ai.baas.ccp.topoligy.core.constant.rule.YesNo;
import com.ai.baas.ccp.topoligy.core.exception.OmcException;
import com.ai.baas.ccp.topoligy.core.pojo.Customer;
import com.ai.baas.ccp.topoligy.core.pojo.OmcObj;
import com.ai.baas.ccp.topoligy.core.pojo.RealTimeBalance;
import com.ai.baas.ccp.topoligy.core.pojo.SectionRule;
import com.ai.baas.ccp.topoligy.core.util.Cal;
import com.ai.baas.ccp.util.OmcUtils;
import com.ai.baas.ccp.vo.BalanceCalProcessorObject;
import com.ai.baas.ccp.vo.ConfigContainerObject;
import com.ai.baas.ccp.vo.CreditCalProcessObject;
import com.ai.baas.ccp.vo.InformationProcessorObject;
import com.ai.baas.ccp.vo.OmcCalProcessorObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Component
public final class OmcCalProcessorBusiSVImpl implements IOmcCalProcessorBusiSV{
    public OmcCalProcessorBusiSVImpl() {
    }

    private static final Logger logger = LoggerFactory.getLogger(OmcCalProcessorBusiSVImpl.class);

    @Autowired
    private IInformationProcessorBusiSV info;

    @Autowired
    private transient IBalanceCalProcessorBusiSV balanceCalProcessor;

    @Autowired
    private transient ICreditCalProcessBusiSV creditCalProcess;

    public OmcCalProcessorObject process(ConfigContainerObject confContainer, OmcObj obj,
            JsonObject jsonObject) throws OmcException {
        OmcCalProcessorObject calProcessorObject = new OmcCalProcessorObject(confContainer, obj,
                jsonObject);

        // String extAmount = jsonObject.get(OmcCalKey.OMC_EXT_AMOUNT).getAsString();
        // 信控规则
        String rules = jsonObject.get(OmcCalKey.OMC_RULE_ID_LIST).toString();
        // 信控策略
        String policyId = jsonObject.get(OmcCalKey.OMC_POLICY_ID).getAsString();
        // 信控规则字符串转换为信控规则对象
        List<SectionRule> sectionRules = OmcUtils.toSectionRules(confContainer, rules);
        // 准备资料 获取三户信息
        InformationProcessorObject informationProcessorObject = info.process(confContainer, obj,
                jsonObject);
        calProcessorObject.setInfo(informationProcessorObject);

        // 余额计算
        BalanceCalProcessorObject balanceCalProcessorObject = balanceCalProcessor.process(
                confContainer, informationProcessorObject, jsonObject);
        RealTimeBalance realTimeBalance = balanceCalProcessorObject.getBalance();

        // 获取信用度
        CreditCalProcessObject calProcessObject = creditCalProcess.process(confContainer,
                informationProcessorObject, jsonObject);
        realTimeBalance.setCreditline(calProcessObject.getCreditline());
        // realTimeBalance.setRealBalance(new BigDecimal("10000"));
        calProcessorObject.setRealTimeBalance(realTimeBalance);

        List<SectionRule> selectedRules = meetRule(sectionRules, policyId,
                informationProcessorObject, realTimeBalance, confContainer, obj);
        // 匹配规则
        JsonArray jsonArray = new JsonArray();
        if ((selectedRules != null) && (!selectedRules.isEmpty())) {
            for (SectionRule sectionRule : selectedRules) {
                JsonObject obj1 = new JsonObject();
                obj1.addProperty(OmcCalKey.OMC_RULE_ID, sectionRule.getScoutruleid());
                jsonArray.add(obj1);
            }
        }
        JsonObject out = new JsonObject();
        out.add(OmcCalKey.OMC_RULE_ID_LIST, jsonArray);
        calProcessorObject.setOutput(out);
        return calProcessorObject;
    }

    /**
     * @param realTimeBalance
     * @param informationProcessorObject
     * @throws OmcException
     *             根据资料和余额情况与规则进行匹配
     * @Title: meetRule
     * @Description: 根据资料和余额情况与规则进行匹配
     * @return void 返回类型
     * @throws
     */
    private List<SectionRule> meetRule(List<SectionRule> matchRule, String policyid,
            InformationProcessorObject informationProcessorObject, RealTimeBalance realTimeBalance,
            ConfigContainerObject cfg, OmcObj omcObj) throws OmcException {
        // 获取客户信息
        Customer customer = informationProcessorObject.getCustomer();
        logger.info("客户信息：" + customer.toString());
        // 首先资料级别匹配 资料匹配没有优先级，需要全量匹配，现金提供客户等级
        // 客户等级匹配的规则
        List<SectionRule> sectionRules = matchCreditLevel(customer, matchRule, policyid, cfg,
                omcObj);
        if ((sectionRules == null) || (sectionRules.isEmpty())) {
            return sectionRules;
        }
        // 阀值类匹配，阀值类匹配 包括欠费，高额，余额等，优先级依次降低
        logger.info("余额信息：" + realTimeBalance.toString());
        logger.info("开始匹配规则");
        // 首先判断欠费
        // 其次判断高额
        // 最后判断余额
        return thresholdMatch(realTimeBalance, sectionRules, policyid, cfg, omcObj);
    }

    /**
     * @throws OmcException
     * 
     * @Title: thresholdMatch
     * @Description: 阀值类匹配 匹配余额，欠费月份，高额等
     * @param @param balance
     * @param @param sectionRules
     * @param @return 设定文件
     * @return List<SectionRule> 返回类型
     * @throws
     */
    private List<SectionRule> thresholdMatch(RealTimeBalance balance,
            List<SectionRule> sectionRules, String policyid, ConfigContainerObject cfg,
            OmcObj omcObj) throws OmcException {
        // 欠费天数
        List<SectionRule> matchRules = matchOwners(balance, sectionRules, policyid, cfg, omcObj);
        // 若欠费天数匹配规则为空,则进行实时费用匹配规则
        if (matchRules == null || matchRules.isEmpty()) {
            matchRules = matchCharge(balance, sectionRules, policyid, cfg, omcObj);
        }
        // 若欠费天数匹配规则和费用匹配规则均为空,则进行余额匹配
        if (matchRules == null || matchRules.isEmpty()) {
            matchRules = matchBalance(balance, sectionRules, policyid, cfg, omcObj);
        }
        return matchRules;
    }

    /**
     * @param cfg
     * @throws OmcException
     * 
     * @Title: matchbalance
     * @Description: 匹配余额
     * @param @param balance
     * @param @param sectionRules
     * @param @return 设定文件
     * @return List<SectionRule> 返回类型
     * @throws
     */

    private List<SectionRule> matchBalance(RealTimeBalance balance, List<SectionRule> sectionRules,
            String policyid, ConfigContainerObject cfg, OmcObj omcObj) throws OmcException {
        List<SectionRule> sRules = new ArrayList<SectionRule>();
        String matchbalance = cfg.getCfgPara(OmcCalKey.OMC_CFG_MATCH_BALANCE, omcObj.getTenantid(),
                policyid, "");
        String stopaddcredit = cfg.getCfgPara(OmcCalKey.OMC_CFG_STOP_ADDCREDITLINE,
                omcObj.getTenantid(), policyid, "");
        String startaddcredit = cfg.getCfgPara(OmcCalKey.OMC_CFG_START_ADDCREDITLINE,
                omcObj.getTenantid(), policyid, "");
        String warnaddcredit = cfg.getCfgPara(OmcCalKey.OMC_CFG_WARN_ADDCREDITLINE,
                omcObj.getTenantid(), policyid, "");

        if (MatchBalance.MATCH.equals(matchbalance)) {
            for (Iterator<SectionRule> iterator = sectionRules.iterator(); iterator.hasNext();) {
                SectionRule sectionRule = iterator.next();
                // 匹配余额
                BigDecimal floor = Cal.bigDecimalFromLong(sectionRule.getBalancefloor(),
                        FeeSource.FROM_NO_SOURCE);
                BigDecimal ceil = Cal.bigDecimalFromLong(sectionRule.getBalanceceil(),
                        FeeSource.FROM_NO_SOURCE);
                // 用户余额
                BigDecimal realbalance = balance.getRealBalance();
                // 监控规则类型
                // 判断是否需要加入信用度
                if (sectionRule.getScouttype().equals(ScoRuleType.HALFSTOP)
                        || sectionRule.getScouttype().equals(ScoRuleType.STOP)) {
                    if (stopaddcredit.equals(YesNo.YES)) {
                        realbalance = realbalance.add(balance.getCreditline());
                    }
                }
                if (sectionRule.getScouttype().equals(ScoRuleType.START)) {
                    if (startaddcredit.equals(YesNo.YES)) {
                        realbalance = realbalance.add(balance.getCreditline());
                    }
                }
                if (sectionRule.getScouttype().equals(ScoRuleType.WARNING)
                        || sectionRule.getScouttype().equals(ScoRuleType.WARNOFF)) {
                    if (warnaddcredit.equals(YesNo.YES)) {
                        realbalance = realbalance.add(balance.getCreditline());
                    }
                }
                if ((realbalance.compareTo(floor) > 0) && (realbalance.compareTo(ceil) <= 0)) {
                    sRules.add(sectionRule);
                }
            }
        }

        return sRules;
    }

    /**
     * @throws OmcException
     * 
     * @Title: matchcharge
     * @Description: 匹配账单
     * @param @param curr
     * @param @param ceil
     * @param @param floor
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     */
    private List<SectionRule> matchCharge(RealTimeBalance balance, List<SectionRule> sectionRules,
            String policyid, ConfigContainerObject cfg, OmcObj omcObj) throws OmcException {
        List<SectionRule> sRules = new ArrayList<SectionRule>();
        String matchcharge = cfg.getCfgPara(OmcCalKey.OMC_CFG_MATCH_CHARGE, omcObj.getTenantid(),
                policyid, "");

        if (MatchCharge.MATCH.equals(matchcharge)) {
            for (Iterator<SectionRule> iterator = sectionRules.iterator(); iterator.hasNext();) {
                SectionRule sectionRule = (SectionRule) iterator.next();
                // 匹配余额 大于费用最小金额,小于费用最大金额
                BigDecimal floor = Cal.bigDecimalFromLong(sectionRule.getChargefloor(),
                        FeeSource.FROM_CREDIT);
                BigDecimal ceil = Cal.bigDecimalFromLong(sectionRule.getChargeceil(),
                        FeeSource.FROM_CREDIT);
                if ((balance.getRealBill().compareTo(floor) > 0)
                        && (balance.getRealBill().compareTo(ceil) <= 0)) {
                    sRules.add(sectionRule);
                }
            }
        }

        return sRules;
    }

    /**
     * @throws OmcException
     * 
     * @Title: matchowners
     * @Description: 欠费类规则匹配
     * @param @param balance
     * @param @param sectionRules
     * @param @return 设定文件
     * @return List<SectionRule> 返回类型
     * @throws
     */

    private List<SectionRule> matchOwners(RealTimeBalance balance, List<SectionRule> sectionRules,
            String policyid, ConfigContainerObject cfg, OmcObj omcObj) throws OmcException {
        List<SectionRule> sRules = new ArrayList<SectionRule>();
        String matchowners = cfg.getCfgPara(OmcCalKey.OMC_CFG_MATCH_OWNERS, omcObj.getTenantid(),
                policyid, "");
        if (MatchOwners.MATCH.equals(matchowners)) {
            for (Iterator<SectionRule> iterator = sectionRules.iterator(); iterator.hasNext();) {
                SectionRule sectionRule = iterator.next();
                // 匹配余额 大于最小天数,小于最大天数
                if ((balance.getUnSettleMons() > sectionRule.getOwemindays())
                        && (balance.getUnSettleMons() <= sectionRule.getOwemaxdays())) {
                    sRules.add(sectionRule);
                }
            }
        }

        return sRules;
    }

    /**
     * @throws OmcException
     *             根据客户等级,匹配相应规则
     * @Title: matchCreditLevel
     * @Description: 信用等级匹配
     * @param @param curr
     * @param @param sectionRules
     * @param @return 设定文件
     * @return List<SectionRule> 返回类型
     * @throws
     */
    private List<SectionRule> matchCreditLevel(Customer customer, List<SectionRule> sectionRules,
            String policyid, ConfigContainerObject cfg, OmcObj omcObj) throws OmcException {
        // 是否参照客户级别
        String matchcreditlevel = cfg.getCfgPara(OmcCalKey.OMC_CFG_MATCH_CREDITLEVEL,
                omcObj.getTenantid(), policyid, "");
        List<SectionRule> midRules = new ArrayList<SectionRule>();

        if (MatchCreditLevel.MATCH.equals(matchcreditlevel)) {
            if (StringUtils.isBlank(customer.getCustLevel())) {
                throw new OmcException("matchCreditLevel", "客户信控级别为空" + customer);
            }

            for (Iterator<SectionRule> iterator = sectionRules.iterator(); iterator.hasNext();) {
                SectionRule sectionRule = iterator.next();
                if (StringUtils.isBlank(sectionRule.getCustlevel())) {
                    throw new OmcException("matchCreditLevel", "信控规则客户等级为空"
                            + sectionRule.toString());
                }

                if (customer.getCustLevel().equals(sectionRule.getCustlevel())) {
                    midRules.add(sectionRule);
                }
            }
        } else {
            midRules.addAll(sectionRules);
        }
        return midRules;
    }

}
