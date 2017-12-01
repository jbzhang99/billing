package com.ai.opt.sys.api.gnfunc.param;

import java.io.Serializable;
import java.sql.Timestamp;

public class UpdateGnFuncRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 功能ID
     */
    private long funcId;

    /**
     * 功能名称
     */
    private String funcName;

    /**
     * 功能url
     */
    private String funcUrl;

    /**
     * 功能类型
     */
    private String funcType;

    /**
     * 排序
     */
    private String funcSort;

    /**
     * 上级功能ID
     */
    private String parentFuncId;

    /**
     * 功能服务css样式
     */
    private String funcCssClass;

    /**
     * 图标
     */
    private String funcPic;

    /**
     * 生效时间
     */
    private Timestamp activeTime;

    /**
     * 失效时间
     */
    private Timestamp inactiveTime;
    
    private Long UpdateAccountId;

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
        this.funcName = funcName;
    }

    public String getFuncUrl() {
        return funcUrl;
    }

    public void setFuncUrl(String funcUrl) {
        this.funcUrl = funcUrl;
    }

    public String getFuncType() {
        return funcType;
    }

    public void setFuncType(String funcType) {
        this.funcType = funcType;
    }

    public String getFuncSort() {
        return funcSort;
    }

    public void setFuncSort(String funcSort) {
        this.funcSort = funcSort;
    }

    public String getParentFuncId() {
        return parentFuncId;
    }

    public void setParentFuncId(String parentFuncId) {
        this.parentFuncId = parentFuncId;
    }

    public String getFuncCssClass() {
        return funcCssClass;
    }

    public void setFuncCssClass(String funcCssClass) {
        this.funcCssClass = funcCssClass;
    }

    public String getFuncPic() {
        return funcPic;
    }

    public void setFuncPic(String funcPic) {
        this.funcPic = funcPic;
    }

    public Timestamp getActiveTime() {
        return activeTime == null ? null : (Timestamp) activeTime.clone();
    }

    public void setActiveTime(Timestamp activeTime) {
        this.activeTime = (activeTime == null ? null : (Timestamp) activeTime.clone());
    }

    public Timestamp getInactiveTime() {
        return inactiveTime == null ? null : (Timestamp) inactiveTime.clone();
    }

    public void setInactiveTime(Timestamp inactiveTime) {
        this.inactiveTime = (inactiveTime == null ? null : (Timestamp) inactiveTime.clone());
    }

	public Long getUpdateAccountId() {
		return UpdateAccountId;
	}

	public void setUpdateAccountId(Long updateAccountId) {
		UpdateAccountId = updateAccountId;
	}
}
