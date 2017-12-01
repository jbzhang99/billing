package com.ai.opt.sys.api.gnrole.param;

import com.ai.opt.base.vo.BaseInfo;

public class QueryRoleRequest extends BaseInfo {

	private static final long serialVersionUID = 1L;

	private long roleId;

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

}
