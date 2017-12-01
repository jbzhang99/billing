package com.ai.opt.sys.api.gnrole.param;

import com.ai.opt.base.vo.BaseInfo;

public class DeleteRoleRequest extends BaseInfo{

	private static final long serialVersionUID = 1L;

	private Long roleId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}
