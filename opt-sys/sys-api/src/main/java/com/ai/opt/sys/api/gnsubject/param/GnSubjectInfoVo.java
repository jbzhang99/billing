package com.ai.opt.sys.api.gnsubject.param;

import java.io.Serializable;

import com.ai.opt.base.vo.BaseResponse;

public class GnSubjectInfoVo implements Serializable {
	/**
	 * @Company asiainfo.com
	 * @Author zhangzhongde
	 * @Date 2016年3月31日 下午5:14:11
	 * @Describtion
	 */
	private static final long serialVersionUID = 1L;
	private Long subjectId;
	private String subjectName;
	private String tenantId;
	private String industryCode;
	private String unitName;

	private String subjectDesc;
	/**
	 * 当前第几页,必填
	 */
	private Integer pageNo;

	/**
	 * 每页数据条数,必填
	 */
	private Integer pageSize;

	// ---------------------------------getter and
	// setter---------------------------------
	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

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

	private String subjectType;

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectDesc() {
		return subjectDesc;
	}

	public void setSubjectDesc(String subjectDesc) {
		this.subjectDesc = subjectDesc;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

}
