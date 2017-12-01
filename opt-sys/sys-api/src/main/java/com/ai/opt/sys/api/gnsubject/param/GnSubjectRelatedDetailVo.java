package com.ai.opt.sys.api.gnsubject.param;

import java.util.List;

public class GnSubjectRelatedDetailVo {

	private static final long serialVersionUID = 1L;
	private String tenantId;
	private List<String> drSubjectId;

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public List<String> getDrSubjectId() {
		return drSubjectId;
	}

	public void setDrSubjectId(List<String> drSubjectId) {
		this.drSubjectId = drSubjectId;
	}

}
