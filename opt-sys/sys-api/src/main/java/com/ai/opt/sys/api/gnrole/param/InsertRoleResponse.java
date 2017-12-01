package com.ai.opt.sys.api.gnrole.param;

import java.sql.Timestamp;

import com.ai.opt.base.vo.BaseInfo;

public class InsertRoleResponse extends BaseInfo{

	private static final long serialVersionUID = 1L;
	
    private String roleName;

    private String roleDesc;

    private Timestamp activeTime;

    private Timestamp inactiveTime;

    private long createAccountId;

    private long updateAccountId;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
