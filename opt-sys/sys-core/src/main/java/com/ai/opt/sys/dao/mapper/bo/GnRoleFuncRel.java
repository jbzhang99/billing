package com.ai.opt.sys.dao.mapper.bo;

import java.sql.Timestamp;

public class GnRoleFuncRel {
    private long roleFuncRelId;

    private String tenantId;

    private long roleId;

    private long funcId;

    private Timestamp activeTime;

    private Timestamp inactiveTime;

    private Timestamp createTime;

    private Timestamp updateTime;

    private long createAccountId;

    private long updateAccountId;

    public long getRoleFuncRelId() {
        return roleFuncRelId;
    }

    public void setRoleFuncRelId(long roleFuncRelId) {
        this.roleFuncRelId = roleFuncRelId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public long getFuncId() {
        return funcId;
    }

    public void setFuncId(long funcId) {
        this.funcId = funcId;
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