package com.ai.opt.sys.dao.mapper.bo;

import java.sql.Timestamp;

public class GnFunc {
    private long funcId;

    private String funcName;

    private String state;

    private String funcUrl;

    private String funcType;

    private long funcSort;

    private long parentFuncId;

    private String funcCssClass;

    private String funcPic;

    private Timestamp activeTime;

    private Timestamp inactiveTime;

    private Timestamp createTime;

    private Timestamp updateTime;

    private long createAccountId;

    private long updateAccountId;

    public long getFuncId() {
        return funcId;
    }

    public void setFuncId(long funcId) {
        this.funcId = funcId;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName == null ? null : funcName.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getFuncUrl() {
        return funcUrl;
    }

    public void setFuncUrl(String funcUrl) {
        this.funcUrl = funcUrl == null ? null : funcUrl.trim();
    }

    public String getFuncType() {
        return funcType;
    }

    public void setFuncType(String funcType) {
        this.funcType = funcType == null ? null : funcType.trim();
    }

    public long getFuncSort() {
        return funcSort;
    }

    public void setFuncSort(long funcSort) {
        this.funcSort = funcSort;
    }

    public long getParentFuncId() {
        return parentFuncId;
    }

    public void setParentFuncId(long parentFuncId) {
        this.parentFuncId = parentFuncId;
    }

    public String getFuncCssClass() {
        return funcCssClass;
    }

    public void setFuncCssClass(String funcCssClass) {
        this.funcCssClass = funcCssClass == null ? null : funcCssClass.trim();
    }

    public String getFuncPic() {
        return funcPic;
    }

    public void setFuncPic(String funcPic) {
        this.funcPic = funcPic == null ? null : funcPic.trim();
    }

    public Timestamp getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Timestamp activeTime) {
        this.activeTime = activeTime;
    }

    public Timestamp getInactiveTime() {
        return inactiveTime;
    }

    public void setInactiveTime(Timestamp inactiveTime) {
        this.inactiveTime = inactiveTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public long getCreateAccountId() {
        return createAccountId;
    }

    public void setCreateAccountId(long createAccountId) {
        this.createAccountId = createAccountId;
    }

    public long getUpdateAccountId() {
        return updateAccountId;
    }

    public void setUpdateAccountId(long updateAccountId) {
        this.updateAccountId = updateAccountId;
    }
}