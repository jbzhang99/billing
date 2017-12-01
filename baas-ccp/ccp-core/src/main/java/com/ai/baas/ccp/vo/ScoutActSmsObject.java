package com.ai.baas.ccp.vo;

import com.ai.baas.ccp.dao.mapper.bo.OmcUrgeInterface;
import com.ai.baas.ccp.dao.mapper.bo.OmcUrgeStatus;
import com.ai.baas.ccp.topoligy.core.pojo.OmcObj;
import com.ai.baas.ccp.topoligy.core.pojo.RealTimeBalance;
import com.ai.baas.ccp.topoligy.core.pojo.SectionRule;
import com.google.gson.JsonObject;

/**
 * 
 * @ClassName: ScoutActBms
 * @Description: 提供预警短信发送
 * @author lvsj
 * @date 2015年11月12日 下午9:03:50
 * 
 */

public class ScoutActSmsObject {

    protected OmcUrgeStatus omcUrgeStatus = null;

    protected OmcUrgeInterface omcUrgeInterface = null;

    private SectionRule sectionRule;

    private String policyId;

    private ConfigContainerObject config;

    private OmcObj omcObj;

    private JsonObject indata;

    private InformationProcessorObject infomation;

    private RealTimeBalance realtimeBalance;

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

    public ConfigContainerObject getConfig() {
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

    public InformationProcessorObject getInfomation() {
        return infomation;
    }

    public SectionRule getSectionRule() {
        return sectionRule;
    }

    public String getPolicyid() {
        return policyId;
    }

    public OmcUrgeStatus getOmcUrgeStatus() {
        return omcUrgeStatus;
    }

    public void setOmcUrgeStatus(OmcUrgeStatus omcUrgeStatus) {
        this.omcUrgeStatus = omcUrgeStatus;
    }

    public OmcUrgeInterface getOmcUrgeInterface() {
        return omcUrgeInterface;
    }

    public void setOmcUrgeInterface(OmcUrgeInterface omcUrgeInterface) {
        this.omcUrgeInterface = omcUrgeInterface;
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public OmcObj getOmcObj() {
        return omcObj;
    }

    public void setOmcObj(OmcObj omcObj) {
        this.omcObj = omcObj;
    }

    public void setSectionRule(SectionRule sectionRule) {
        this.sectionRule = sectionRule;
    }

    public void setConfig(ConfigContainerObject config) {
        this.config = config;
    }

    public void setIndata(JsonObject indata) {
        this.indata = indata;
    }

    public void setInfomation(InformationProcessorObject infomation) {
        this.infomation = infomation;
    }

    public void setRealtimeBalance(RealTimeBalance realtimeBalance) {
        this.realtimeBalance = realtimeBalance;
    }

}
