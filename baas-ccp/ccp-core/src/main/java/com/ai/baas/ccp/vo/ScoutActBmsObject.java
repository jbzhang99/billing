package com.ai.baas.ccp.vo;

import com.ai.baas.ccp.dao.mapper.bo.OmcBmsInterface;
import com.ai.baas.ccp.dao.mapper.bo.OmcScoutStatus;
import com.ai.baas.ccp.dao.mapper.bo.OmcUrgeInterface;
import com.ai.baas.ccp.topoligy.core.pojo.OmcObj;
import com.ai.baas.ccp.topoligy.core.pojo.RealTimeBalance;
import com.google.gson.JsonObject;

/**
 * 
 * @ClassName: ScoutActBms
 * @Description: 提供停开机指令生成，停开机前短信发送，停开机预警状态和短信处理功能
 * @author lvsj
 * @date 2015年11月12日 下午9:03:50
 * 
 */

public class ScoutActBmsObject {
    public ScoutActBmsObject() {
    }

    private ConfigContainerObject config;

    private OmcObj omcobj;

    private JsonObject indata;

    private RealTimeBalance realtimeBalance;

    protected OmcBmsInterface omcBmsInterface = null;

    protected OmcScoutStatus myscoutStatus = null;

    protected OmcUrgeInterface smsInfs = null;

    void setdefault(OmcObj owner, ConfigContainerObject cfg, RealTimeBalance balance,
            JsonObject data) {
        this.config = cfg;
        this.omcobj = owner;
        this.indata = data;
        this.realtimeBalance = balance;
    }

    /**
     * 
     * @Title: stop
     * @Description: 处理停机接口数据，将停机数据插入到停机队列中
     * @param @return 设定文件
     * @return int 返回类型
     * @throws
     */
    public ScoutActBmsObject(OmcObj owner, ConfigContainerObject cfg, RealTimeBalance balance,
            JsonObject data) {
        super();
        this.config = cfg;
        this.omcobj = owner;
        this.indata = data;
        this.realtimeBalance = balance;

    }

    public OmcBmsInterface getOmcBmsInterfaces() {
        return omcBmsInterface;
    }

    public void setOmcBmsInterfaces(OmcBmsInterface omcBmsInterfaces) {
        this.omcBmsInterface = omcBmsInterfaces;
    }

    public OmcScoutStatus getMyscoutStatus() {
        return myscoutStatus;
    }

    public void setMyscoutStatus(OmcScoutStatus myscoutStatus) {
        this.myscoutStatus = myscoutStatus;
    }

    public OmcUrgeInterface getSmsInfs() {
        return smsInfs;
    }

    public void setSmsInfs(OmcUrgeInterface smsInfs) {
        this.smsInfs = smsInfs;
    }

    public OmcObj getOmcobj() {
        return omcobj;
    }

    public ConfigContainerObject getConfig() {
        return config;
    }

    public JsonObject getIndata() {
        return indata;
    }

    public OmcBmsInterface getOmcBmsInterface() {
        return omcBmsInterface;
    }

    public void setOmcBmsInterface(OmcBmsInterface omcBmsInterface) {
        this.omcBmsInterface = omcBmsInterface;
    }

    public RealTimeBalance getRealtimeBalance() {
        return realtimeBalance;
    }

    public void setConfig(ConfigContainerObject config) {
        this.config = config;
    }

    public void setOmcobj(OmcObj omcobj) {
        this.omcobj = omcobj;
    }

    public void setIndata(JsonObject indata) {
        this.indata = indata;
    }

    public void setRealtimeBalance(RealTimeBalance realtimeBalance) {
        this.realtimeBalance = realtimeBalance;
    }
}
