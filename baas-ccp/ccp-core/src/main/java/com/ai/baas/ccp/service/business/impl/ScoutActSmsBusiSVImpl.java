package com.ai.baas.ccp.service.business.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.ccp.dao.mapper.bo.OmcScoutActionDefine;
import com.ai.baas.ccp.dao.mapper.bo.OmcUrgeInterface;
import com.ai.baas.ccp.service.business.interfaces.IScoutActSmsBusiSV;
import com.ai.baas.ccp.service.factory.ServiceFactory;
import com.ai.baas.ccp.topoligy.core.constant.OmcCalKey;
import com.ai.baas.ccp.topoligy.core.constant.ScoRuleType;
import com.ai.baas.ccp.topoligy.core.constant.rule.RemindSpend;
import com.ai.baas.ccp.topoligy.core.exception.OmcException;
import com.ai.baas.ccp.topoligy.core.manager.service.ScoutStatusService;
import com.ai.baas.ccp.topoligy.core.manager.service.UrgeStatusService;
import com.ai.baas.ccp.topoligy.core.pojo.OmcObj;
import com.ai.baas.ccp.topoligy.core.pojo.RealTimeBalance;
import com.ai.baas.ccp.topoligy.core.pojo.SectionRule;
import com.ai.baas.ccp.topoligy.core.util.DateUtils;
import com.ai.baas.ccp.vo.ConfigContainerObject;
import com.ai.baas.ccp.vo.ScoutActSmsObject;
import com.google.gson.JsonObject;

/**
 * 
 * @ClassName: ScoutActBms
 * @Description: 提供预警短信发送
 * @author lvsj
 * @date 2015年11月12日 下午9:03:50
 * 
 */
@Component
public class ScoutActSmsBusiSVImpl implements IScoutActSmsBusiSV{

    protected static final Logger logger = LoggerFactory.getLogger(ScoutActSmsBusiSVImpl.class);

    @Autowired
    protected ScoutStatusService scoutStatusService;

    @Autowired
    protected UrgeStatusService urgeStatusService;

    public int warning(String ownertype, String oid, ScoutActSmsObject scoutActSmsObject)
            throws OmcException {

        ConfigContainerObject config = scoutActSmsObject.getConfig();
        OmcScoutActionDefine omcScoutActionDefine = config.getActionDefine(scoutActSmsObject
                .getOmcobj().getTenantid(), scoutActSmsObject.getOmcobj().getBusinesscode(),
                Integer.toString(scoutActSmsObject.getSectionRule().getScoutruleid()),
                ScoRuleType.WARNING);

        OmcObj omcObj = scoutActSmsObject.getOmcobj();
        String policyId = scoutActSmsObject.getPolicyid();
        SectionRule sectionRule = scoutActSmsObject.getSectionRule();
        String speremind = scoutActSmsObject.getConfig().getCfgPara(OmcCalKey.OMC_CFG_REMINDSPENBR,
                omcObj.getTenantid(), policyId, Integer.toString(sectionRule.getScoutruleid()));
        if (StringUtils.isBlank(speremind)) {
            speremind = RemindSpend.NOSPENBR;
        }

        // 提醒到本用户号码
        String remindnbr = ServiceFactory.getIInformationProcessorBusiSV().getRemindNbr(speremind, omcObj.getTenantid(), ownertype, oid);
        if (StringUtils.isBlank(remindnbr)) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(OmcCalKey.OMC_CFG_REMINDSPENBR, remindnbr);
            jsonObject.addProperty(OmcCalKey.OMC_TENANT_ID, omcObj.getTenantid());
            jsonObject.addProperty(OmcCalKey.OMC_OWNER_TYPE, ownertype);
            jsonObject.addProperty(OmcCalKey.OMC_OWNER_ID, oid);

            throw new OmcException("ScoutActSms.warning", "获取提醒号码异常" + jsonObject.toString());
        }

        this.sendmsg(remindnbr, ScoRuleType.WARNING, omcScoutActionDefine.getInfCommond(),
                String.valueOf(omcScoutActionDefine.getSmsTemplate()), scoutActSmsObject);
        return 1;
    }

    public int warnoff(String ownertype, String oid, ScoutActSmsObject scoutActSmsObject) throws OmcException {
        return 1;
    }

    /**
     * @Description: 停开机前发送短信
     */
    protected void sendmsg(String nbr, String scoutType, String commid, String templateid,
            ScoutActSmsObject scoutActSmsObject) {
        if ((templateid == null) || (templateid.isEmpty())) {
            return;
        }
        RealTimeBalance realtimeBalance = scoutActSmsObject.getRealtimeBalance();
        SectionRule sectionRule = scoutActSmsObject.getSectionRule();
        JsonObject jsonObject = new JsonObject();

        OmcObj obj = scoutActSmsObject.getOmcobj();

        jsonObject.addProperty("template_id", templateid);
        jsonObject.addProperty("phone", nbr);
        jsonObject.addProperty("iccid", nbr);
        jsonObject.addProperty("subs_id", obj.getOwerid());
        jsonObject.addProperty("current_value", realtimeBalance.getRealBalance());
        jsonObject.addProperty("limit_value", sectionRule.getBalanceceil());
        jsonObject.addProperty("limit_type", sectionRule.getScouttype());
        jsonObject.addProperty("scout_type", scoutType);
        jsonObject.addProperty("commanid", commid);

        OmcUrgeInterface omcUrgeInterface = new OmcUrgeInterface();
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
        scoutActSmsObject.setSmsInfs(omcUrgeInterface);
    }

}
