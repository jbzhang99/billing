package com.ai.opt.sys.api.gnsubject.param;

import com.ai.opt.base.vo.BaseResponse;

public class GnSubjectListResponse extends BaseResponse {

	/**
	 * @Company asiainfo.com
	 * @Author zhangzhongde
	 * @Date 2016年3月31日 下午5:05:53
	 * @Describtion
	 */
	private static final long serialVersionUID = 1L;

	private Long subjectId;
	private String tenantId;
	private String industryCode;

	private String subjectName;
	private String subjectDesc;

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

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectDesc() {
		return subjectDesc;
	}

	public void setSubjectDesc(String subjectDesc) {
		this.subjectDesc = subjectDesc;
	}
}
