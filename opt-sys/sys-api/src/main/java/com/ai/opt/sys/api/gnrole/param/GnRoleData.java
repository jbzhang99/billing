package com.ai.opt.sys.api.gnrole.param;

import java.sql.Timestamp;

import com.ai.opt.base.vo.BaseInfo;

public class GnRoleData extends BaseInfo{

	private static final long serialVersionUID = 1L;

	private long roleId;

    private String roleName;

    private String state;

    private String roleDesc;

    private Timestamp activeTime;

    private Timestamp inactiveTime;

    private Timestamp createTime;

    private Timestamp updateTime;

    private long createAccountId;

    private long updateAccountId;

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
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
