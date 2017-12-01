package com.ai.opt.sys.api.gnsubject.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.ai.opt.sys.api.gnsubject.interfaces.IGnSubjectQuerySV;

public class GnSubjectDetailVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@NotNull(message="租户id不能为空",groups={IGnSubjectQuerySV.getGnSubjectListMayRelated.class})
	private String tenantId;
	//@NotNull(message="行业类型不能为空",groups={IGnSubjectQuerySV.getGnSubjectListMayRelated.class})
	private String industryCode;
	@NotNull(message="科目类型不能为空",groups={IGnSubjectQuerySV.getGnSubjectListMayRelated.class})
	private String subjectType;
	// ---------------------------------getter and
	// setter---------------------------------
	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public String getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}


}
