package com.ai.baas.ccp.service.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.ccp.dao.mapper.bo.OmcBmsInterface;
import com.ai.baas.ccp.dao.mapper.bo.OmcScoutActionDefine;
import com.ai.baas.ccp.service.business.interfaces.IScoutActBmsBusiSV;
import com.ai.baas.ccp.topoligy.core.constant.OmcCalKey;
import com.ai.baas.ccp.topoligy.core.constant.ScoRuleType;
import com.ai.baas.ccp.topoligy.core.exception.OmcException;
import com.ai.baas.ccp.topoligy.core.manager.service.ScoutStatusService;
import com.ai.baas.ccp.topoligy.core.pojo.OmcObj;
import com.ai.baas.ccp.topoligy.core.pojo.RealTimeBalance;
import com.ai.baas.ccp.topoligy.core.pojo.User;
import com.ai.baas.ccp.topoligy.core.util.DateUtils;
import com.ai.baas.ccp.vo.ConfigContainerObject;
import com.ai.baas.ccp.vo.ScoutActBmsObject;
import com.google.gson.JsonObject;

/**
 * 
 * @ClassName: ScoutActBms
 * @Description: 提供停开机指令生成，停开机前短信发送，停开机预警状态和短信处理功能
 * @author lvsj
 * @date 2015年11月12日 下午9:03:50
 * 
 */
@Component
public class ScoutActBmsBusiSVImpl implements IScoutActBmsBusiSV{
    @Autowired
    protected ScoutStatusService scoutStatusService;

    public int stop(User user, ScoutActBmsObject scoutActBmsObject) throws OmcException {
        ConfigContainerObject cfg = scoutActBmsObject.getConfig();
        OmcObj actionObj = scoutActBmsObject.getOmcobj();
        // 获取指令
        OmcScoutActionDefine action = cfg.getActionDefine(actionObj.getTenantid(),
                actionObj.getBusinesscode(), "-1", ScoRuleType.STOP);
        // 添加短信支持
        sendmsg(user, ScoRuleType.STOP, String.valueOf(action.getSmsTemplate()));
        // 获取停机指令
        String actionType = action.getInfCommond();
        RealTimeBalance realTimeBalance = scoutActBmsObject.getRealtimeBalance();
        JsonObject indata = scoutActBmsObject.getIndata();
        OmcBmsInterface omcBmsInterface = builderinf(user, ScoRuleType.STOP, actionType, indata,
                realTimeBalance);
        scoutActBmsObject.setOmcBmsInterface(omcBmsInterface);
        return 1;
    }

    /**
     * 
     * @Title: start
     * @Description: 处理开机接口数据，将停机数据插入到开机队列中
     * @param @return 设定文件
     * @return int 返回类型
     * @throws
     */
    public int start(User user, ScoutActBmsObject scoutActBmsObject) throws OmcException {
        ConfigContainerObject cfg = scoutActBmsObject.getConfig();
        OmcObj actionObj = scoutActBmsObject.getOmcobj();
        // 获取指定定义
        OmcScoutActionDefine action = cfg.getActionDefine(actionObj.getTenantid(),
                actionObj.getBusinesscode(), "-1", ScoRuleType.START);

        // 添加短信支持
        sendmsg(user, ScoRuleType.START, String.valueOf(action.getSmsTemplate()));
        // 获取信控指令
        String actionType = action.getInfCommond();

        RealTimeBalance realTimeBalance = scoutActBmsObject.getRealtimeBalance();
        JsonObject indata = scoutActBmsObject.getIndata();
        OmcBmsInterface omcBmsInterface = builderinf(user, ScoRuleType.START, actionType, indata,
                realTimeBalance);
        scoutActBmsObject.setOmcBmsInterface(omcBmsInterface);
        return 1;
    }

    public int halfstop(User user, ScoutActBmsObject scoutActBmsObject) throws OmcException {
        ConfigContainerObject cfg = scoutActBmsObject.getConfig();
        OmcObj actionObj = scoutActBmsObject.getOmcobj();
        // 获取指定定义
        OmcScoutActionDefine action = cfg.getActionDefine(actionObj.getTenantid(),
                actionObj.getBusinesscode(), "-1", ScoRuleType.HALFSTOP);

        // 添加短信支持
        sendmsg(user, ScoRuleType.HALFSTOP, String.valueOf(action.getSmsTemplate()));
        // 获取信控指令
        String actionType = action.getInfCommond();

        JsonObject indata = scoutActBmsObject.getIndata();
        RealTimeBalance realTimeBalance = scoutActBmsObject.getRealtimeBalance();
        OmcBmsInterface omcBmsInterface = builderinf(user, ScoRuleType.HALFSTOP, actionType,
                indata, realTimeBalance);
        scoutActBmsObject.setOmcBmsInterface(omcBmsInterface);
        return 1;
    }

    /**
     * 
     * @Title: Sendmsg
     * @Description: 停开机前发送短信
     * @param @param user
     * @param @param scoutType
     * @param @param templateID 设定文件
     * @return void 返回类型
     * @throws
     */
    protected void sendmsg(User user, String scoutType, String templateID) {
        if ((templateID == null) || (templateID.isEmpty())) {
            return;
        }
        // smsInfs = new SmsInf();
        // smsInfs.setCreateTime(DateUtils.currTimeStamp());
        // smsInfs.setCreateTime(DateUtils.currTimeStamp());
        // smsInfs.setFlag(0);
        // smsInfs.setGsmcontent("phone:"+user.getServicenum());
        // smsInfs.setPhone(user.getServicenum());
        // smsInfs.setPriority(0);
        // smsInfs.setServicetype(user.getBasicorgid());
        // smsInfs.setSrcName("CREDIT"+scoutType);
        // smsInfs.setTemplateId(Long.valueOf(templateID));
        // smsInfs.setVerifyid(0l);

    }

    /**
     * 产生停开机接口信息中备注信息
     * 
     * @return
     */
    private String getbmsRemark(RealTimeBalance realtimeBalance) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("实时余额：" + realtimeBalance.getRealBalance());
        stringBuffer.append("预存余额：" + realtimeBalance.getBalance());
        stringBuffer.append("实时话费：" + realtimeBalance.getRealBill());
        stringBuffer.append("历史欠费：" + realtimeBalance.getUnSettleBill());
        stringBuffer.append("欠费月数：" + realtimeBalance.getUnSettleMons());
        stringBuffer.append("最早欠费月：" + realtimeBalance.getFstUnSettleMon());
        return stringBuffer.toString();
    }

    /**
     * 产生停开机接口信息
     * 
     * @param user
     * @param scotype
     * @return
     */
    private String getbmsData(User user, String scotype, RealTimeBalance realtimeBalance) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("3|" + user.getSubsid());
        stringBuffer.append("|" + user.getServicetype());
        stringBuffer.append("|" + "A");
        stringBuffer.append("|" + scotype);
        stringBuffer.append(getbmsRemark(realtimeBalance));
        return stringBuffer.toString();
    }

    private String getinfData(User user, String scouttype, String commonid, JsonObject data,
            RealTimeBalance realtimeBalance) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("balance", realtimeBalance.getRealBalance());
        jsonObject.add("pileid", data.get(OmcCalKey.OMC_CHARGING_PILE));
        jsonObject.add("statid", data.get(OmcCalKey.OMC_CHARGING_STATION));
        jsonObject.addProperty("iccid", user.getServiceId());
        jsonObject.addProperty("subs_id", user.getSubsid());
        jsonObject.addProperty("scout_type", scouttype);
        jsonObject.addProperty("commanid", commonid);

        return jsonObject.toString();

    }

    /**
     * 产生停开机接口
     * 
     * @param user
     * @param scouttype
     * @param commonid
     * @param indata
     * @return
     */
    private OmcBmsInterface builderinf(User user, String scouttype, String commonid,
            JsonObject indata, RealTimeBalance realTimeBalance) {
        OmcBmsInterface bmsinf = new OmcBmsInterface();
        String infdata = getinfData(user, ScoRuleType.HALFSTOP, commonid, indata, realTimeBalance);
        bmsinf.setSerialNo(0L); // 统一赋值
        bmsinf.setTenantId(user.getTenantid());
        bmsinf.setSystemId(user.getSystemid());
        bmsinf.setAcctId(user.getAccountid());

        bmsinf.setBmsData(getbmsData(user, scouttype, realTimeBalance));
        bmsinf.setDealFlag(0);
        bmsinf.setDealTime(DateUtils.currTimeStamp());
        bmsinf.setInsertTime(DateUtils.currTimeStamp());
        bmsinf.setInterfaceData(infdata);
        bmsinf.setRemark("");
        bmsinf.setRetryTimes(0);
        bmsinf.setScoutType(scouttype);

        bmsinf.setSubsId(user.getSubsid());
        return bmsinf;
    }

}
