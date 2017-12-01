package com.ai.opt.sys.api.gnrole.param;

import com.ai.opt.base.vo.BaseInfo;

public class QueryRolePageRequest extends BaseInfo{

	private static final long serialVersionUID = 1L;

    private String roleName;

    private String roleDesc;

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
}
