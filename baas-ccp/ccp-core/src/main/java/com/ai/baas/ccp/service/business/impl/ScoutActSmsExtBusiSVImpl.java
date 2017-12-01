package com.ai.baas.ccp.service.business.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.ccp.dao.mapper.bo.OmcScoutStatus;
import com.ai.baas.ccp.dao.mapper.bo.OmcUrgeStatus;
import com.ai.baas.ccp.service.business.interfaces.IScoutActSmsBusiSV;
import com.ai.baas.ccp.service.business.interfaces.IScoutActSmsExtBusiSV;
import com.ai.baas.ccp.topoligy.core.constant.OwnerType;
import com.ai.baas.ccp.topoligy.core.constant.ScoStatus;
import com.ai.baas.ccp.topoligy.core.exception.OmcException;
import com.ai.baas.ccp.topoligy.core.manager.service.ScoutStatusService;
import com.ai.baas.ccp.topoligy.core.manager.service.UrgeStatusService;
import com.ai.baas.ccp.topoligy.core.pojo.SectionRule;
import com.ai.baas.ccp.topoligy.core.util.DateUtils;
import com.ai.baas.ccp.vo.ScoutActSmsObject;

/**
 * 
 * @ClassName: ScoutActSmsExt
 * @Description: 根据预警状态判断是否需要生成预警信息
 * @author lvsj
 * @date 2015年11月17日 下午3:08:34
 * 
 */
@Component
public class ScoutActSmsExtBusiSVImpl implements IScoutActSmsExtBusiSV{
    private static final Logger LOGGER = LoggerFactory.getLogger(ScoutActSmsExtBusiSVImpl.class);

    @Autowired
    private ScoutStatusService scoutStatusService;

    @Autowired
    private UrgeStatusService urgeStatusService;

    @Autowired
    private IScoutActSmsBusiSV scoutActSms;

    /**
     * 
     * @Title: warning
     * 
     * @param @param user
     * @param @return 设定文件
     * @return int 返回类型
     * @throws
     */
    public int warning(String ownertype, String oid, ScoutActSmsObject scoutActSmsObject)
            throws OmcException {

        // 根据本地信控状态判断是否需要预警，对于提醒到用户的需要判断用户资料的状态，提醒的账户和客户的不判断资料
        if ((ownertype.equals(OwnerType.SERV))) {
            OmcScoutStatus scoutStatus = scoutStatusService.selectStatus(scoutActSmsObject
                    .getOmcobj().getTenantid(), scoutActSmsObject.getOmcobj().getBusinesscode(),
                    oid);
            if (scoutStatus != null) {
                if ((scoutStatus.getStatus().equals(ScoStatus.STOP))
                        || (scoutStatus.getStatus().equals(ScoStatus.HALFSTOP))
                        || (scoutStatus.getStatus().equals(ScoStatus.DELAYSTOP))) {
                    LOGGER.info("此对象已经停机，不再进行预警提醒" + "ownertype:[" + ownertype + "] oid:[" + oid);
                    return 0;
                }
            }
        }
        // 获取当前预警状态
        OmcUrgeStatus omcUrgeStatus = urgeStatusService.selectUrgeStatus(scoutActSmsObject
                .getOmcobj().getTenantid(), scoutActSmsObject.getOmcobj().getBusinesscode(),
                scoutActSmsObject.getSectionRule().getSectiontype(), ownertype, oid);
        if ((omcUrgeStatus != null) && (ScoStatus.WARNING.equals(omcUrgeStatus.getStatus()))) {
            if (omcUrgeStatus.getNotifyTimes() > 0) {
                LOGGER.info("此对象已经提醒过，不再重复提醒" + "ownertype:[" + ownertype + "] oid:[" + oid);
                return 0;
            }
        }
        // 获取当前信控状态
        SectionRule sectionRule = scoutActSmsObject.getSectionRule();

        if (omcUrgeStatus == null) {
            omcUrgeStatus = new OmcUrgeStatus();
            omcUrgeStatus.setUrgeSerial(0L);
            omcUrgeStatus.setTenantId(scoutActSmsObject.getOmcobj().getTenantid());
            omcUrgeStatus.setSystemId("SystemId");

            omcUrgeStatus.setBusinessCode(scoutActSmsObject.getOmcobj().getBusinesscode());
            omcUrgeStatus.setUrgeType(sectionRule.getSectiontype());
            omcUrgeStatus.setOwnerId(oid);
            omcUrgeStatus.setOwnerType(ownertype);

            omcUrgeStatus.setNotifyType(sectionRule.getSectiontype());
            omcUrgeStatus.setScoutInfo("后续处理");
            omcUrgeStatus.setStatus(ScoStatus.WARNING);
            omcUrgeStatus.setStatusTime(DateUtils.currTimeStamp());
            omcUrgeStatus.setLastStatus(ScoStatus.WARNOFF);
            omcUrgeStatus.setNotifyStatus("1");
            omcUrgeStatus.setNotifyTime(DateUtils.currTimeStamp());
            omcUrgeStatus.setNotifyTimes(1);
        } else {
            omcUrgeStatus.setNotifyType(sectionRule.getSectiontype());
            omcUrgeStatus.setScoutInfo("后续处理");
            omcUrgeStatus.setStatus(ScoStatus.WARNING);
            omcUrgeStatus.setStatusTime(DateUtils.currTimeStamp());
            omcUrgeStatus.setLastStatus(ScoStatus.WARNOFF);
            omcUrgeStatus.setNotifyStatus("1");
            omcUrgeStatus.setNotifyTime(DateUtils.currTimeStamp());
            omcUrgeStatus.setNotifyTimes(1);

        }
        scoutActSmsObject.setMyomcUrgeStatus(omcUrgeStatus);

        return scoutActSms.warning(ownertype, oid, scoutActSmsObject);

    }

    public int warnoff(String ownertype, String oid, ScoutActSmsObject scoutActSmsObject)
            throws OmcException {

        // 获取当前预警状态
        OmcUrgeStatus omcUrgeStatus = urgeStatusService.selectUrgeStatus(scoutActSmsObject
                .getOmcobj().getTenantid(), scoutActSmsObject.getOmcobj().getBusinesscode(),
                scoutActSmsObject.getSectionRule().getSectiontype(), ownertype, oid);

        if (omcUrgeStatus == null || ScoStatus.WARNOFF.equals(omcUrgeStatus.getStatus())) {
            return 0;
        }
        // 获取当前信控状态
        SectionRule sectionRule = scoutActSmsObject.getSectionRule();
        omcUrgeStatus.setNotifyType(sectionRule.getSectiontype());
        omcUrgeStatus.setScoutInfo("后续处理");
        omcUrgeStatus.setStatus(ScoStatus.WARNOFF);
        omcUrgeStatus.setStatusTime(DateUtils.currTimeStamp());
        omcUrgeStatus.setLastStatus(ScoStatus.WARNING);
        omcUrgeStatus.setNotifyStatus("1");
        omcUrgeStatus.setNotifyTime(DateUtils.currTimeStamp());
        omcUrgeStatus.setNotifyTimes(1);

        scoutActSmsObject.setMyomcUrgeStatus(omcUrgeStatus);

        return scoutActSms.warnoff(ownertype, oid, scoutActSmsObject);

    }

}
