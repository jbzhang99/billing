package com.ai.baas.ccp.topoligy.core.business;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.ccp.action.notice.interfaces.INoticeFrameSV;
import com.ai.baas.ccp.action.notice.param.ecitic.EciticCcpStopBusiVO;
import com.ai.baas.ccp.constants.CcpConstants;
import com.ai.baas.ccp.dao.mapper.bo.OmcBmsInterface;
import com.ai.baas.ccp.dao.mapper.bo.OmcScoutStatus;
import com.ai.baas.ccp.dao.mapper.bo.OmcUrgeInterface;
import com.ai.baas.ccp.dao.mapper.bo.OmcUrgeStatus;
import com.ai.baas.ccp.topoligy.core.business.base.BaseProcess;
import com.ai.baas.ccp.topoligy.core.business.command.ScoutActBmsExt;
import com.ai.baas.ccp.topoligy.core.business.command.ScoutActSmsExt;
import com.ai.baas.ccp.topoligy.core.constant.AvoidType;
import com.ai.baas.ccp.topoligy.core.constant.OmcCalKey;
import com.ai.baas.ccp.topoligy.core.constant.OwnerType;
import com.ai.baas.ccp.topoligy.core.constant.RemindTarget;
import com.ai.baas.ccp.topoligy.core.constant.ScoRuleType;
import com.ai.baas.ccp.topoligy.core.exception.OmcException;
import com.ai.baas.ccp.topoligy.core.manager.container.ConfigContainer;
import com.ai.baas.ccp.topoligy.core.manager.service.ScoutBmsInterfaceService;
import com.ai.baas.ccp.topoligy.core.manager.service.ScoutLogService;
import com.ai.baas.ccp.topoligy.core.manager.service.ScoutStatusService;
import com.ai.baas.ccp.topoligy.core.manager.service.SgipSrcGsmService;
import com.ai.baas.ccp.topoligy.core.manager.service.SpeUrgeStopService;
import com.ai.baas.ccp.topoligy.core.manager.service.UrgeStatusService;
import com.ai.baas.ccp.topoligy.core.pojo.Account;
import com.ai.baas.ccp.topoligy.core.pojo.Customer;
import com.ai.baas.ccp.topoligy.core.pojo.OmcObj;
import com.ai.baas.ccp.topoligy.core.pojo.RealTimeBalance;
import com.ai.baas.ccp.topoligy.core.pojo.ScoutLog;
import com.ai.baas.ccp.topoligy.core.pojo.SectionRule;
import com.ai.baas.ccp.topoligy.core.pojo.SpeUrgeStop;
import com.ai.baas.ccp.topoligy.core.pojo.User;
import com.ai.baas.ccp.topoligy.core.util.OmcUtils;
import com.ai.opt.sdk.components.sequence.util.SeqUtil;
import com.alibaba.fastjson.JSON;
import com.google.gson.JsonObject;

@Component
public final class NoticeProcessor extends BaseProcess {

    private static Logger LOGGER = LoggerFactory.getLogger(NoticeProcessor.class);

    @Autowired
    private SpeUrgeStopService speUrgeStopService;

    @Autowired
    private ScoutStatusService scoutStatusService;

    @Autowired
    private UrgeStatusService urgeStatusService;

    @Autowired
    private ScoutBmsInterfaceService scoutBmsInterfaceService;

    @Autowired
    private SgipSrcGsmService sgipSrcGsmService;

    @Autowired
    private ScoutLogService scoutLogService;

    @Autowired
    private InformationProcessor info;

    private RealTimeBalance realBalance;

    @Autowired
    private ScoutActBmsExt scoutActBmsExt;
    
    @Autowired
    private INoticeFrameSV noticeFrameSV;

    public void process(ConfigContainer cfg, OmcObj obj, JsonObject data, RealTimeBalance balance)
            throws OmcException {
        setDefault(cfg, obj, data);
        this.realBalance = balance;

        JsonObject jsonObject = this.getInput();
        // 规则id列表
        String rules = jsonObject.get(OmcCalKey.OMC_RULE_ID_LIST).toString();
        // 策略id
        String policyId = jsonObject.get(OmcCalKey.OMC_POLICY_ID).getAsString();
        // 规则详细信息列表
        List<SectionRule> sectionRules = OmcUtils.toSectionRules(this.getConfig(), rules);
        // 获取三户资料
        info.process(this.getConfig(), this.getOmcobj(), jsonObject);

        List<User> users = info.getUsers();
        // 逐条进行处理 信控操作进行处理
        for (SectionRule rule : sectionRules) {
            // 时间过滤
            if (!filterByTime(rule)) {
                continue;
            }
            // 规则类型为stop
            if (rule.getScouttype().equals(ScoRuleType.STOP)) {
                stop(users, rule);
            } else if (rule.getScouttype().equals(ScoRuleType.HALFSTOP)) {
                halfstop(users, rule);
            } else if (rule.getScouttype().equals(ScoRuleType.START)) {
                start(users, rule);
            } else if (rule.getScouttype().equals(ScoRuleType.WARNING)) {
                warning(info, rule, policyId);
            } else if (rule.getScouttype().equals(ScoRuleType.WARNOFF)) {
                warnoff(info, rule, policyId);
            }
        }

        // 按照用户提醒 是否提醒到其他号码 可以本号码/其他号码 用户级余额 账户级余额 客户级余额
        // 按照客户提醒 是否提醒到其他号码 账户提醒 默认提醒到其他号码
        // 按照账户提醒 是否提醒到其他号码 客户提醒 默认提醒到其他号码
    }

    /**
     * @Description: 免催停过滤
     */
    private boolean filterBySpeUrgeStop(String ownertype, String ownerid, SectionRule sectionRules)
            throws OmcException {
        SpeUrgeStop speUrgeStop = speUrgeStopService.selectById(this.getOmcobj().getTenantid(),
                ownertype, ownerid);
        boolean ret = true;
        if (speUrgeStop != null) {
            if ((speUrgeStop.getSpeType().equals(AvoidType.AVOID_STOP))
                    || (speUrgeStop.getSpeType().equals(AvoidType.AVOID_STOPANDURGE))) {
                if ((sectionRules.getScouttype().equals(ScoRuleType.HALFSTOP))
                        || (sectionRules.getScouttype().equals(ScoRuleType.STOP))) {
                    ret = false;
                }
            } else if ((speUrgeStop.getSpeType().equals(AvoidType.AVOID_URGE))
                    || (speUrgeStop.getSpeType().equals(AvoidType.AVOID_STOPANDURGE))) {
                if ((sectionRules.getScouttype().equals(ScoRuleType.WARNING))) {
                    ret = false;
                }
            }
        }
        return ret;

    }

    /**
     * 停机操作
     */
    private void stop(List<User> users, SectionRule sectionRule) throws OmcException {
        List<OmcBmsInterface> omcBmsInterfaces = new ArrayList<OmcBmsInterface>();
        List<OmcUrgeInterface> smsInfs = new ArrayList<OmcUrgeInterface>();
        List<OmcScoutStatus> scoutStatus = new ArrayList<OmcScoutStatus>();
        List<OmcUrgeStatus> omcUrgeStatus = new ArrayList<OmcUrgeStatus>();
        for (User user : users) {
            // 免催免停处理
            if (!filterBySpeUrgeStop(OwnerType.SERV, user.getSubsid(), sectionRule)) {
                continue;
            }
            scoutActBmsExt.stop(user, this.getOmcobj(), this.getConfig(), this.realBalance,
                    this.getInput());

            if (scoutActBmsExt.getMyscoutStatus() != null) {
                scoutStatus.add(scoutActBmsExt.getMyscoutStatus());
            }
            if (scoutActBmsExt.getOmcBmsInterfaces() != null) {
                omcBmsInterfaces.add(scoutActBmsExt.getOmcBmsInterfaces());
            }
            if (scoutActBmsExt.getSmsInfs() != null) {
                smsInfs.add(scoutActBmsExt.getSmsInfs());
            }
            // 采集报文发送第三方
            this.notice3rdSystemStop(user, sectionRule);
        }
        ScoutLog scoLog = null;
        if ((omcBmsInterfaces != null) && (!omcBmsInterfaces.isEmpty())) {
            scoLog = new ScoutLog();
            scoLog.setLogid(0L);
            scoLog.setOwner(this.getOmcobj());
            scoLog.setRealTimeBalance(this.getRealBalance());
            scoLog.setScostatus(ScoRuleType.STOP);
            scoLog.setSectionRules(sectionRule);
            scoLog.setSourceType(sectionRule.getScouttype());
        }

        this.sendCommon(omcBmsInterfaces, smsInfs, scoutStatus, omcUrgeStatus, scoLog);
    }

    private void notice3rdSystemStop(User user, SectionRule sectionRule) {
        EciticCcpStopBusiVO baseVO = new EciticCcpStopBusiVO();
        baseVO.setSubjectId(CcpConstants.NoticeSubject.SubjectId.ECITIC_CCP_STOP);
        baseVO.setTenantId(user.getTenantid());
        baseVO.setSubsId(user.getSubsid());
        LOGGER.info("EciticCcpStopBusiVO : {}", JSON.toJSONString(baseVO));
        noticeFrameSV.createNoticeRecord(baseVO);
    }

    private void start(List<User> users, SectionRule sectionRule) throws OmcException {
        List<OmcBmsInterface> omcBmsInterfaces = new ArrayList<OmcBmsInterface>();
        List<OmcUrgeInterface> smsInfs = new ArrayList<OmcUrgeInterface>();
        List<OmcScoutStatus> scoutStatus = new ArrayList<OmcScoutStatus>();
        List<OmcUrgeStatus> omcUrgeStatus = new ArrayList<OmcUrgeStatus>();
        for (User user : users) {

            scoutActBmsExt.start(user, this.getOmcobj(), this.getConfig(),
                    this.realBalance, this.getInput());
            if (scoutActBmsExt.getMyscoutStatus() != null) {
                scoutStatus.add(scoutActBmsExt.getMyscoutStatus());
            }
            if (scoutActBmsExt.getOmcBmsInterfaces() != null) {
                omcBmsInterfaces.add(scoutActBmsExt.getOmcBmsInterfaces());
            }
            if (scoutActBmsExt.getSmsInfs() != null) {
                smsInfs.add(scoutActBmsExt.getSmsInfs());
            }
        }
        ScoutLog scoLog = null;
        if ((omcBmsInterfaces != null) && (!omcBmsInterfaces.isEmpty())) {
            scoLog = new ScoutLog();
            scoLog.setLogid(0L);
            scoLog.setOwner(this.getOmcobj());
            scoLog.setRealTimeBalance(this.getRealBalance());
            scoLog.setScostatus(ScoRuleType.START);
            scoLog.setSectionRules(sectionRule);
            scoLog.setSourceType(sectionRule.getScouttype());
        }

        sendCommon(omcBmsInterfaces, smsInfs, scoutStatus, omcUrgeStatus, scoLog);
    }

    private void halfstop(List<User> users, SectionRule sectionRule) throws OmcException {
        List<OmcBmsInterface> omcBmsInterfaces = new ArrayList<OmcBmsInterface>();
        List<OmcUrgeInterface> smsInfs = new ArrayList<OmcUrgeInterface>();
        List<OmcScoutStatus> scoutStatus = new ArrayList<OmcScoutStatus>();
        List<OmcUrgeStatus> omcUrgeStatus = new ArrayList<OmcUrgeStatus>();
        ScoutActBmsExt scoutActBmsExt = new ScoutActBmsExt(this.getOmcobj(), this.getConfig(),
                this.realBalance, this.getInput());
        for (User user : users) {
            // 免催免停处理
            if (!filterBySpeUrgeStop(OwnerType.SERV, user.getSubsid(), sectionRule)) {
                continue;
            }
            scoutActBmsExt.halfstop(user);

            if (scoutActBmsExt.getMyscoutStatus() != null) {
                scoutStatus.add(scoutActBmsExt.getMyscoutStatus());
            }
            if (scoutActBmsExt.getOmcBmsInterfaces() != null) {
                omcBmsInterfaces.add(scoutActBmsExt.getOmcBmsInterfaces());
            }
            if (scoutActBmsExt.getSmsInfs() != null) {
                smsInfs.add(scoutActBmsExt.getSmsInfs());
            }

        }

        ScoutLog scoLog = null;
        if ((omcBmsInterfaces != null) && (!omcBmsInterfaces.isEmpty())) {
            scoLog = new ScoutLog();
            scoLog.setLogid(0L);
            scoLog.setOwner(this.getOmcobj());
            scoLog.setRealTimeBalance(this.getRealBalance());
            scoLog.setScostatus(ScoRuleType.HALFSTOP);
            scoLog.setSectionRules(sectionRule);
            scoLog.setSourceType(sectionRule.getScouttype());
        }

        sendCommon(omcBmsInterfaces, smsInfs, scoutStatus, omcUrgeStatus, scoLog);
    }

    private void warning(InformationProcessor info, SectionRule sectionRule, String policyid)
            throws OmcException {
        List<OmcBmsInterface> omcBmsInterfaces = new ArrayList<OmcBmsInterface>();
        List<OmcUrgeInterface> smsInfs = new ArrayList<OmcUrgeInterface>();
        List<OmcScoutStatus> scoutStatus = new ArrayList<OmcScoutStatus>();
        List<OmcUrgeStatus> omcUrgeStatus = new ArrayList<OmcUrgeStatus>();

        ConfigContainer cfg = this.getConfig();

        String remindTarget = cfg.getCfgPara(OmcCalKey.OMC_CFG_REMINDTARGET, this.getOmcobj()
                .getTenantid(), policyid, Integer.toString(sectionRule.getScoutruleid()));

        // 缺省配置
        if (StringUtils.isBlank(remindTarget)) {
            remindTarget = RemindTarget.TOSERV;
        }

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(OmcCalKey.OMC_RULE_ID, sectionRule.getScoutruleid());
        jsonObject.addProperty(OmcCalKey.OMC_RULE_SECTION, sectionRule.getSectiontype());
        jsonObject.addProperty(OmcCalKey.OMC_POLICY_ID, policyid);
        ScoutActSmsExt scoutActSmsExt = new ScoutActSmsExt(this.getOmcobj(), info,
                this.getConfig(), this.getRealBalance(), jsonObject);
        // 根据不同的配置进行处理
        if (remindTarget.equals(RemindTarget.TOSERV)) {
            List<User> remindusers = info.getUsers();
            for (User user : remindusers) {
                // 免催免停处理
                if (!filterBySpeUrgeStop(OwnerType.SERV, user.getSubsid(), sectionRule)) {
                    continue;
                }

                scoutActSmsExt.warning(OwnerType.SERV, user.getSubsid());

                if (scoutActSmsExt.getMyomcUrgeStatus() != null) {
                    omcUrgeStatus.add(scoutActSmsExt.getMyomcUrgeStatus());
                }
                if (scoutActSmsExt.getSmsInfs() != null) {
                    smsInfs.add(scoutActSmsExt.getSmsInfs());
                }
            }

        } else if (remindTarget.equals(RemindTarget.TOACCT)) {
            List<Account> accounts = info.getAccounts();
            for (Account account : accounts) {
                // 免催免停处理
                if (!filterBySpeUrgeStop(OwnerType.ACCT, account.getAccountId(), sectionRule)) {
                    continue;
                }
                scoutActSmsExt.warning(OwnerType.ACCT, account.getAccountId());

                if (scoutActSmsExt.getMyomcUrgeStatus() != null) {
                    omcUrgeStatus.add(scoutActSmsExt.getMyomcUrgeStatus());
                }
                if (scoutActSmsExt.getSmsInfs() != null) {
                    smsInfs.add(scoutActSmsExt.getSmsInfs());
                }
            }

        } else if (remindTarget.equals(RemindTarget.TOCUST)) {
            Customer customer = info.getCustomer();

            // 免催免停处理
            if (!filterBySpeUrgeStop(OwnerType.CUST, customer.getCustomerId(), sectionRule)) {
                return;
            }

            scoutActSmsExt.warning(OwnerType.CUST, customer.getCustomerId());
            if (scoutActSmsExt.getMyomcUrgeStatus() != null) {
                omcUrgeStatus.add(scoutActSmsExt.getMyomcUrgeStatus());
            }
            if (scoutActSmsExt.getSmsInfs() != null) {
                smsInfs.add(scoutActSmsExt.getSmsInfs());
            }

        }
        ScoutLog scoLog = null;
        if ((smsInfs != null) && (!smsInfs.isEmpty())) {
            scoLog = new ScoutLog();
            scoLog.setLogid(0L);
            scoLog.setOwner(this.getOmcobj());
            scoLog.setRealTimeBalance(this.getRealBalance());
            scoLog.setScostatus(ScoRuleType.WARNING);
            scoLog.setSectionRules(sectionRule);
            scoLog.setSourceType(sectionRule.getScouttype());
        }

        sendCommon(omcBmsInterfaces, smsInfs, scoutStatus, omcUrgeStatus, scoLog);
    }

    private void warnoff(InformationProcessor info, SectionRule sectionRule, String policyid)
            throws OmcException {
        List<OmcBmsInterface> omcBmsInterfaces = new ArrayList<OmcBmsInterface>();
        List<OmcUrgeInterface> smsInfs = new ArrayList<OmcUrgeInterface>();
        List<OmcScoutStatus> scoutStatus = new ArrayList<OmcScoutStatus>();
        List<OmcUrgeStatus> omcUrgeStatus = new ArrayList<OmcUrgeStatus>();

        ConfigContainer cfg = this.getConfig();

        String remindTarget = cfg.getCfgPara(OmcCalKey.OMC_CFG_REMINDTARGET, this.getOmcobj()
                .getTenantid(), policyid, Integer.toString(sectionRule.getScoutruleid()));

        // 缺省配置 ,默认提醒到用户
        if (StringUtils.isBlank(remindTarget)) {
            remindTarget = RemindTarget.TOSERV;
        }

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(OmcCalKey.OMC_RULE_ID, sectionRule.getScoutruleid());
        jsonObject.addProperty(OmcCalKey.OMC_RULE_SECTION, sectionRule.getSectiontype());
        jsonObject.addProperty(OmcCalKey.OMC_POLICY_ID, policyid);
        ScoutActSmsExt scoutActSmsExt = new ScoutActSmsExt(this.getOmcobj(), info,
                this.getConfig(), this.getRealBalance(), jsonObject);
        // 根据不同的配置进行处理
        // 用户
        if (remindTarget.equals(RemindTarget.TOSERV)) {
            List<User> remindusers = info.getUsers();
            for (User user : remindusers) {
                // 免催免停处理
                if (!filterBySpeUrgeStop(OwnerType.SERV, user.getSubsid(), sectionRule)) {
                    continue;
                }

                scoutActSmsExt.warnoff(OwnerType.SERV, user.getSubsid());

                if (scoutActSmsExt.getMyomcUrgeStatus() != null) {
                    omcUrgeStatus.add(scoutActSmsExt.getMyomcUrgeStatus());
                }
            }
            // 账户
        } else if (remindTarget.equals(RemindTarget.TOACCT)) {
            List<Account> accounts = info.getAccounts();
            for (Account account : accounts) {
                // 免催免停处理
                if (!filterBySpeUrgeStop(OwnerType.ACCT, account.getAccountId(), sectionRule)) {
                    continue;
                }
                scoutActSmsExt.warnoff(OwnerType.ACCT, account.getAccountId());

                if (scoutActSmsExt.getMyomcUrgeStatus() != null) {
                    omcUrgeStatus.add(scoutActSmsExt.getMyomcUrgeStatus());
                }
            }
            // 客户
        } else if (remindTarget.equals(RemindTarget.TOCUST)) {
            Customer customer = info.getCustomer();

            // 免催免停处理
            if (!filterBySpeUrgeStop(OwnerType.CUST, customer.getCustomerId(), sectionRule)) {
                return;
            }

            scoutActSmsExt.warnoff(OwnerType.CUST, customer.getCustomerId());
            if (scoutActSmsExt.getMyomcUrgeStatus() != null) {
                omcUrgeStatus.add(scoutActSmsExt.getMyomcUrgeStatus());
            }
        }

        ScoutLog scoLog = null;
        if ((omcUrgeStatus != null) && (!omcUrgeStatus.isEmpty())) {
            scoLog = new ScoutLog();
            scoLog.setLogid(0L);
            scoLog.setOwner(this.getOmcobj());
            scoLog.setRealTimeBalance(this.getRealBalance());
            scoLog.setScostatus(ScoRuleType.WARNOFF);
            scoLog.setSectionRules(sectionRule);
            scoLog.setSourceType(sectionRule.getScouttype());
        }

        sendCommon(omcBmsInterfaces, smsInfs, scoutStatus, omcUrgeStatus, scoLog);
    }

    /**
     * 
     * @Title: filterByTime
     * @Description: 按照时间段进行过滤，在指定时间段内不做对应信控动作
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     */
    private boolean filterByTime(SectionRule sectionRule) {
        // Todo 待完成
        return true;
    }

    private void sendCommon(List<OmcBmsInterface> bmsinfs, List<OmcUrgeInterface> smsinfs,
            List<OmcScoutStatus> scoutStatus, List<OmcUrgeStatus> omcurgeStatus, ScoutLog scoutLog)
            throws OmcException {
        Connection connection = null;// DB_PROXY.getConnection();
        // 保存状态表
        this.sendScoStatus(connection, scoutStatus);
        // 保存停开机接口表
        this.sendBmsInterface(connection, bmsinfs);
        // 保存短信通知表
        this.sendSmsInterface(connection, smsinfs);
        // 保存催缴表
        this.sendUrgeStatus(connection, omcurgeStatus);
        // 保存日志表
        this.sendScoLog(connection, scoutLog);
    }

    /**
     * 发送停开机信息
     * 
     * @param connection
     * @param bmsinfs
     * @throws OmcException
     */
    private void sendBmsInterface(Connection connection, List<OmcBmsInterface> bmsinfs)
            throws OmcException {
        if ((bmsinfs == null) || (bmsinfs.isEmpty())) {
            return;
        }

        String breakpoint = "sendCommon.bmsinfs";

        for (OmcBmsInterface inf : bmsinfs) {
            inf.setSerialNo(SeqUtil.getNewId("SCOUT_BMS_INTERFACE_SEQ"));
            if (scoutBmsInterfaceService.addInterFace(connection, inf) <= 0) {
                throw new OmcException(breakpoint, "更新停开机接口表异常");
            }
        }
    }

    /**
     * 发送短信通知
     */
    private void sendSmsInterface(Connection connection, List<OmcUrgeInterface> smsinfs)
            throws OmcException {
        if ((smsinfs == null) || (smsinfs.isEmpty())) {
            return;
        }
        for (OmcUrgeInterface inf : smsinfs) {
            if (inf.getSerialNo() == 0L) {
                inf.setSerialNo(SeqUtil.getNewId("SGIP_SRC_GSM_SEQ"));
            }

            sgipSrcGsmService.insertMsg(connection, inf);
        }
    }

    /**
     * 更新催缴状态
     */
    private void sendUrgeStatus(Connection connection, List<OmcUrgeStatus> omcurgeStatus)
            throws OmcException {
        if ((omcurgeStatus == null) || (omcurgeStatus.isEmpty())) {
            return;
        }

        String breakpoint = "sendCommon.omcurgeStatus";
        for (OmcUrgeStatus status : omcurgeStatus) {
            if (status.getUrgeSerial() == 0L) {
                status.setUrgeSerial(SeqUtil.getNewId("SCOSTATUS_SEQ"));
            }

            if (urgeStatusService.modifyUrgeStatus(connection, status) <= 0) {
                throw new OmcException(breakpoint, "更新预警状态表异常");
            }
        }
    }

    /**
     * 发送信控状态
     * 
     * @param connection
     * @param scoutStatus
     * @throws OmcException
     */
    private void sendScoStatus(Connection connection, List<OmcScoutStatus> scoutStatus)
            throws OmcException {
        if ((scoutStatus == null) || (scoutStatus.isEmpty())) {
            return;
        }

        String breakpoint = "sendCommon.savestatus";

        for (OmcScoutStatus status : scoutStatus) {
            if (status.getScoSeq() == 0L) {
                status.setScoSeq(SeqUtil.getNewId("SCOSTATUS_SEQ"));
            }
            // 更新信控状态
            if (scoutStatusService.modifyScoutStatus(connection, status) <= 0) {
                throw new OmcException(breakpoint, "更新状态异常");
            }
        }
    }

    /**
     * 发送信控日志
     * 
     * @param connection
     * @param scoutLog
     * @throws OmcException
     */
    private void sendScoLog(Connection connection, ScoutLog scoutLog) throws OmcException {
        if ((scoutLog == null)) {
            return;
        }
        scoutLog.setLogid(SeqUtil.getNewId("SCO_SQUENCE"));
        scoutLog.setScostatus("1");
        scoutLogService.insertScoutLog(connection, scoutLog);
    }

    @Override
    public void prepare(JsonObject data) throws OmcException {
    }

    public RealTimeBalance getRealBalance() {
        return realBalance;
    }

    public void setRealBalance(RealTimeBalance realBalance) {
        this.realBalance = realBalance;
    }

    @Override
    public void prepare(String cfg) throws OmcException {
    }

    @Override
    public void process() throws OmcException {
    }

}
