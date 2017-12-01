package com.ai.opt.sys.api.gnrole.param;

import com.ai.opt.base.vo.BaseResponse;

public class InsertRoleRequest extends BaseResponse{

	private static final long serialVersionUID = 1L;

	private Long roleId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}
