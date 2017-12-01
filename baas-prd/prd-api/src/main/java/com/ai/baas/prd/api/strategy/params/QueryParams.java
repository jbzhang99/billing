package com.ai.baas.prd.api.strategy.params;

import com.ai.opt.base.vo.BaseInfo;

public class QueryParams extends BaseInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String policyId;
	private String policyName;
	private String policyType;
	
	/**
	 * 0,包含已关联的策略
	 * 1,只查询未关联的策略
	 */
	private String related = "0"; 
	
	/**
     * 当前第几页,必填
     */
    private Integer pageNo;

    /**
     * 每页数据条数,必填
     */
    private Integer pageSize;
	
	public String getPolicyId() {
		return policyId;
	}
	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	public String getPolicyType() {
		return policyType;
	}
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
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
	public String getRelated() {
		return related;
	}
	public void setRelated(String related) {
		this.related = related;
	}
}
