package com.ai.baas.amc.api.invoice.params;

import java.util.List;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 发票开具记录查询入參
 * @author wangluyang
 *
 */
public class InvoiceRecordQueryParam extends BaseInfo{

	private static final long serialVersionUID = 1L;
	/**
	 * 用户类型   0:租户管理员   1:运营管理员
	 */
	private String userType;
	/**
	 * 公司名称
	 */
	private String companyName;
	/**
	 * 客户id列表
	 */
	private List<Long> custIds;
	
	/**
	 * 账户id列表
	 */
	private List<Long> acctIds;
	
	/**
	 * 开具状态  0:否   1:是
	 */
	private String status;
	/**
	 * 帐期
	 */
	private List<String> billMonth;
	
	 /**
     * 当前第几页,必填
     */
    private Integer pageNo;

    /**
     * 每页数据条数,必填
     */
    private Integer pageSize;
	
	public List<Long> getAcctIds() {
		return acctIds;
	}
	public void setAcctIds(List<Long> acctIds) {
		this.acctIds = acctIds;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public List<Long> getCustIds() {
		return custIds;
	}
	public void setCustIds(List<Long> custIds) {
		this.custIds = custIds;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<String> getBillMonth() {
		return billMonth;
	}
	public void setBillMonth(List<String> billMonth) {
		this.billMonth = billMonth;
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
}
