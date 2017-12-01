package com.ai.baas.ccp.topoligy.core.business.command;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ai.baas.ccp.dao.mapper.bo.OmcScoutActionDefine;
import com.ai.baas.ccp.dao.mapper.bo.OmcUrgeInterface;
import com.ai.baas.ccp.dao.mapper.bo.OmcUrgeStatus;
import com.ai.baas.ccp.topoligy.core.business.InformationProcessor;
import com.ai.baas.ccp.topoligy.core.constant.OmcCalKey;
import com.ai.baas.ccp.topoligy.core.constant.ScoRuleType;
import com.ai.baas.ccp.topoligy.core.constant.rule.RemindSpend;
import com.ai.baas.ccp.topoligy.core.exception.OmcException;
import com.ai.baas.ccp.topoligy.core.manager.container.ConfigContainer;
import com.ai.baas.ccp.topoligy.core.manager.service.ScoutStatusService;
import com.ai.baas.ccp.topoligy.core.manager.service.UrgeStatusService;
import com.ai.baas.ccp.topoligy.core.pojo.OmcObj;
import com.ai.baas.ccp.topoligy.core.pojo.RealTimeBalance;
import com.ai.baas.ccp.topoligy.core.pojo.SectionRule;
import com.ai.baas.ccp.topoligy.core.util.DateUtils;
import com.google.gson.JsonObject;

/**
 * 
 * @ClassName: ScoutActBms
 * @Description: 提供预警短信发送
 * @author lvsj
 * @date 2015年11月12日 下午9:03:50
 * 
 */

public class ScoutActSms {

    public ScoutActSms() {
    }

    protected static final Logger logger = LoggerFactory.getLogger(ScoutActBms.class);

    @Autowired
    protected ScoutStatusService scoutStatusService;

    @Autowired
    protected UrgeStatusService urgeStatusService;

    protected OmcUrgeStatus omcUrgeStatus = null;

    protected OmcUrgeInterface omcUrgeInterface = null;

    private SectionRule sectionRule;

    private String policyId;

    private ConfigContainer config;

    private OmcObj omcObj;

    private JsonObject indata;

    private InformationProcessor infomation;

    private RealTimeBalance realtimeBalance;

    public void setDefault(OmcObj owner, InformationProcessor info, ConfigContainer cfg,
            RealTimeBalance balance, JsonObject data) {
        this.config = cfg;
        this.omcObj = owner;
        this.indata = data;
        this.infomation = info;
        this.realtimeBalance = balance;
        policyId = data.get(OmcCalKey.OMC_POLICY_ID).getAsString();
        String ruleid = data.get(OmcCalKey.OMC_RULE_ID).toString();
        this.sectionRule = cfg.getSectionRule(Integer.parseInt(ruleid));
    }

    /**
     * 
     * @Title: stop
     * @Description: 处理停机接口数据，将停机数据插入到停机队列中
     * @param @return 设定文件
     * @return int 返回类型
     * @throws
     */
    public ScoutActSms(OmcObj owner, InformationProcessor info, ConfigContainer cfg,
            RealTimeBalance balance, JsonObject data) {
        super();
        this.config = cfg;
        this.omcObj = owner;
        this.indata = data;
        this.infomation = info;
        this.realtimeBalance = balance;
        policyId = data.get(OmcCalKey.OMC_POLICY_ID).getAsString();
        String ruleid = data.get(OmcCalKey.OMC_RULE_ID).toString();
        this.sectionRule = cfg.getSectionRule(Integer.parseInt(ruleid));

    }

    public int warning(String ownertype, String oid) throws OmcException {

        OmcScoutActionDefine omcScoutActionDefine = config.getActionDefine(this.getOmcobj()
                .getTenantid(), this.getOmcobj().getBusinesscode(), Integer
                .toString(this.sectionRule.getScoutruleid()), ScoRuleType.WARNING);

        String speremind = this.getConfig().getCfgPara(OmcCalKey.OMC_CFG_REMINDSPENBR,
                omcObj.getTenantid(), policyId, Integer.toString(sectionRule.getScoutruleid()));
        if (StringUtils.isBlank(speremind)) {
            speremind = RemindSpend.NOSPENBR;
        }

        // 提醒到本用户号码
        String remindnbr = this.getInfomation().getRemindNbr(speremind, omcObj.getTenantid(),
                ownertype, oid);
        if (StringUtils.isBlank(remindnbr)) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(OmcCalKey.OMC_CFG_REMINDSPENBR, remindnbr);
            jsonObject.addProperty(OmcCalKey.OMC_TENANT_ID, omcObj.getTenantid());
            jsonObject.addProperty(OmcCalKey.OMC_OWNER_TYPE, ownertype);
            jsonObject.addProperty(OmcCalKey.OMC_OWNER_ID, oid);

            throw new OmcException("ScoutActSms.warning", "获取提醒号码异常" + jsonObject.toString());
        }

        this.sendmsg(remindnbr, ScoRuleType.WARNING, omcScoutActionDefine.getInfCommond(),
                String.valueOf(omcScoutActionDefine.getSmsTemplate()));
        return 1;
    }

    public int warnoff(String ownertype, String oid) throws OmcException {
        return 1;
    }

    /**
     * @Description: 停开机前发送短信
     */
    protected void sendmsg(String nbr, String scoutType, String commid, String templateid) {
        if ((templateid == null) || (templateid.isEmpty())) {
            return;
        }
        RealTimeBalance realtimeBalance = this.getRealtimeBalance();
        SectionRule sectionRule = this.getSectionRule();
        JsonObject jsonObject = new JsonObject();

        OmcObj obj = this.getOmcobj();

        jsonObject.addProperty("template_id", templateid);
        jsonObject.addProperty("phone", nbr);
        jsonObject.addProperty("iccid", nbr);
        jsonObject.addProperty("subs_id", obj.getOwerid());
        jsonObject.addProperty("current_value", realtimeBalance.getRealBalance());
        jsonObject.addProperty("limit_value", sectionRule.getBalanceceil());
        jsonObject.addProperty("limit_type", sectionRule.getScouttype());
        jsonObject.addProperty("scout_type", scoutType);
        jsonObject.addProperty("commanid", commid);

        omcUrgeInterface = new OmcUrgeInterface();
        omcUrgeInterface.setDealFlag(0);
        omcUrgeInterface.setDealTime(DateUtils.currTimeStamp());
        omcUrgeInterface.setInsertTime(DateUtils.currTimeStamp());
        omcUrgeInterface.setOwnerId(obj.getOwerid());
        omcUrgeInterface.setOwnerType(obj.getOwertype());
        omcUrgeInterface.setRemark("");
        omcUrgeInterface.setRetryTimes(0);
        omcUrgeInterface.setSerialNo(0L);
        omcUrgeInterface.setSystemId("SYSTEMID");
        omcUrgeInterface.setTenantId(obj.getTenantid());
        omcUrgeInterface.setUrgeInfo(jsonObject.toString());

    }

    public OmcUrgeInterface getSmsInfs() {
        return omcUrgeInterface;
    }

    public void setSmsInfs(OmcUrgeInterface smsInfs) {
        this.omcUrgeInterface = smsInfs;
    }

    public OmcUrgeStatus getMyomcUrgeStatus() {
        return omcUrgeStatus;
    }

    public void setMyomcUrgeStatus(OmcUrgeStatus myomcUrgeStatus) {
        this.omcUrgeStatus = myomcUrgeStatus;
    }

    public ConfigContainer getConfig() {
        return config;
    }

    public OmcObj getOmcobj() {
        return omcObj;
    }

    public JsonObject getIndata() {
        return indata;
    }

    public RealTimeBalance getRealtimeBalance() {
        return realtimeBalance;
    }

    public InformationProcessor getInfomation() {
        return infomation;
    }

    public SectionRule getSectionRule() {
        return sectionRule;
    }

    public String getPolicyid() {
        return policyId;
    }

}
