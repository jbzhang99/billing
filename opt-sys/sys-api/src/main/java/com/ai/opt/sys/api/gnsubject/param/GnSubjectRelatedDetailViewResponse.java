package com.ai.opt.sys.api.gnsubject.param;

import java.io.Serializable;
import java.util.List;

/**
 * 关联账单科目返回对象
 *
 * Date: 2016年4月7日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author zhangzd
 */
public class GnSubjectRelatedDetailViewResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tenantId;
	private Long billSubjectId;
	private String subjectName;
	private String subjectDesc;
	private List<GnSubjectListResponse> mayRelatedList;
	private List<GnSubjectListResponse> relatedList;

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public Long getBillSubjectId() {
		return billSubjectId;
	}

	public void setBillSubjectId(Long billSubjectId) {
		this.billSubjectId = billSubjectId;
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

	public List<GnSubjectListResponse> getMayRelatedList() {
		return mayRelatedList;
	}

	public void setMayRelatedList(List<GnSubjectListResponse> mayRelatedList) {
		this.mayRelatedList = mayRelatedList;
	}

	public List<GnSubjectListResponse> getRelatedList() {
		return relatedList;
	}

	public void setRelatedList(List<GnSubjectListResponse> relatedList) {
		this.relatedList = relatedList;
	}
}
