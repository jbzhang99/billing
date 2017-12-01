package com.ai.runner.center.bmc.resdeposit.vo;

/**
 * 资源账本必要字段 Date: 2016年2月25日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author caoyf
 */
public class FunResBook {
    /**
     * 系统id
     */
    private String SYSTEM_ID;

    /**
     * 用户号
     */
    private String SUBS_ID;

    /**
     * 租户id
     */
    private String TENANT_ID;

    /**
     * 产品id
     */
    private String PRODUCT_ID;

    /**
     * 产品实例id
     */
    private String SUBS_PRODUCT_ID;

    /**
     * 生效日期
     */
    private String ACTIVE_TIME;

    /**
     * 失效时间
     */
    private String INACTIVE_TIME;

    /**
     * 清零标识
     */
    private String RES_CLEAR_FLAG;

    /**
     * 赠送标识
     */
    private String RES_BONUS_FLAG;

    /**
     * 资源类型
     */
    private String resourceType;

    /**
     * 资源数量
     */
    private String totalAmount;
    
    /**
     * 运营商标识
     */
    private String basicOrgId;

    public String getSYSTEM_ID() {
        return SYSTEM_ID;
    }

    public void setSYSTEM_ID(String sYSTEM_ID) {
        SYSTEM_ID = sYSTEM_ID;
    }

    public String getSUBS_ID() {
        return SUBS_ID;
    }

    public void setSUBS_ID(String sUBS_ID) {
        SUBS_ID = sUBS_ID;
    }

    public String getTENANT_ID() {
        return TENANT_ID;
    }

    public void setTENANT_ID(String tENANT_ID) {
        TENANT_ID = tENANT_ID;
    }

    public String getPRODUCT_ID() {
        return PRODUCT_ID;
    }

    public void setPRODUCT_ID(String pRODUCT_ID) {
        PRODUCT_ID = pRODUCT_ID;
    }

    public String getSUBS_PRODUCT_ID() {
        return SUBS_PRODUCT_ID;
    }

    public void setSUBS_PRODUCT_ID(String sUBS_PRODUCT_ID) {
        SUBS_PRODUCT_ID = sUBS_PRODUCT_ID;
    }

    public String getACTIVE_TIME() {
        return ACTIVE_TIME;
    }

    public void setACTIVE_TIME(String aCTIVE_TIME) {
        ACTIVE_TIME = aCTIVE_TIME;
    }

    public String getINACTIVE_TIME() {
        return INACTIVE_TIME;
    }

    public void setINACTIVE_TIME(String iNACTIVE_TIME) {
        INACTIVE_TIME = iNACTIVE_TIME;
    }

    public String getRES_CLEAR_FLAG() {
        return RES_CLEAR_FLAG;
    }

    public void setRES_CLEAR_FLAG(String rES_CLEAR_FLAG) {
        RES_CLEAR_FLAG = rES_CLEAR_FLAG;
    }

    public String getRES_BONUS_FLAG() {
        return RES_BONUS_FLAG;
    }

    public void setRES_BONUS_FLAG(String rES_BONUS_FLAG) {
        RES_BONUS_FLAG = rES_BONUS_FLAG;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

	public String getBasicOrgId() {
		return basicOrgId;
	}

	public void setBasicOrgId(String basicOrgId) {
		this.basicOrgId = basicOrgId;
	}
    
    
    
}
