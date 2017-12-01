package com.ai.baas.amc.api.bill.params;

import com.ai.opt.base.vo.BaseInfo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 账单查询请求.<br>
 *
 * Date: 2016年7月1日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author wangyx13
 */
public class BillSearchRequest extends BaseInfo{

    /**
     * 中信客户Id列表(租户id)
     */
    private List<Long> custIdList;
    /**
     * 账户Id列表
     */
    private List<String> acctIdList;
    /**
     * 中信客户名称(租户名称)
     */
    private String custName;
    /**
     * 租户名称
     */
    private String tenantName;
    /**
     * 支付状态
     */
    private String payState;
    /**
     * 待查询账单月份(格式yyyymm)
     */
    private String billMonth;
    /**
     * 账单金额查询区间起始点
     */
    private BigDecimal billFeeMin;
    /**
     * 账单金额查询区间起始点
     */
    private BigDecimal billFeeMax;
    
    /**
	 * 用户类型   0:租户管理员   1:运营管理员
	 */
	private String userType;

    /**
     * 当前查询页
     */
    private Integer pageNo;
    /**
     * 每页条数
     */
    private Integer pageSize;

    public List<Long> getCustIdList() {
        return custIdList;
    }

    public void setCustIdList(List<Long> custIdList) {
        this.custIdList = custIdList;
    }

    public List<String> getAcctIdList() {
        return acctIdList;
    }

    public void setAcctIdList(List<String> acctIdList) {
        this.acctIdList = acctIdList;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
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

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getPayState() {
        return payState;
    }

    public void setPayState(String payState) {
        this.payState = payState;
    }

    public String getBillMonth() {
        return billMonth;
    }

    public void setBillMonth(String billMonth) {
        this.billMonth = billMonth;
    }

    public BigDecimal getBillFeeMin() {
        return billFeeMin;
    }

    public void setBillFeeMin(BigDecimal billFeeMin) {
        this.billFeeMin = billFeeMin;
    }

    public BigDecimal getBillFeeMax() {
        return billFeeMax;
    }

    public void setBillFeeMax(BigDecimal billFeeMax) {
        this.billFeeMax = billFeeMax;
    }

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
}
