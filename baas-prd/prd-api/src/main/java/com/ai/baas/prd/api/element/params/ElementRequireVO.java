package com.ai.baas.prd.api.element.params;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 定价元素查询vo
 * @author wangkai16
 *
 */
public class ElementRequireVO extends BaseInfo {

	private static final long serialVersionUID = 1L;
	/*
	 * 主产品ID,选填
	 */
	private String mainProductId;
	   /*
     * 主产品名称,选填
     */
	private String mainProductName;
	   /*
     * 计费周期,选填
     */
	private String billingCycle;
	
	   /**
     * 当前第几页,必填
     */
    private Integer pageNo;

    /**
     * 每页数据条数,必填
     */
    private Integer pageSize;
	
    public String getMainProductId() {
        return mainProductId;
    }
    public void setMainProductId(String mainProductId) {
        this.mainProductId = mainProductId;
    }
    public String getMainProductName() {
        return mainProductName;
    }
    public void setMainProductName(String mainProductName) {
        this.mainProductName = mainProductName;
    }
    public String getBillingCycle() {
        return billingCycle;
    }
    public void setBillingCycle(String billingCycle) {
        this.billingCycle = billingCycle;
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
