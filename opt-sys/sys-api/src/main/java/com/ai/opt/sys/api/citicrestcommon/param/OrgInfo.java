package com.ai.opt.sys.api.citicrestcommon.param;

import java.io.Serializable;
import java.util.List;

/**
 * 中信机构信息实体
 *
 * Date: 2016年7月6日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author wangyx13
 */
public class OrgInfo implements Serializable{
    /**
     * 组织机构的唯一标识。
     */
    private String orgId;
    /**
     * 组织机构在集团LDAP中的唯一标识。
     */
    private String citicOrgId;
    /**
     * 名称。
     */
    private String name;
    /**
     * 简称。
     */
    private String abbreviation;
    /**
     * 上级机构唯一标识。
     */
    private String superior;
    /**
     * 是否成本中心。
     */
    private String isCostCenter;
    /**
     * 层次代码。
     */
    private String level;
    /**
     * 是否租户。
     */
    private String isTenant;
    /**
     * 是否供应商。
     */
    private String isSupplier;
    /**
     * 作为供应商的适配器访问地址。
     */
    private String uri;
    /**
     * 作为租户的VPC服务实例（每个IaaS供应商一个）
     */
    private List<VPC> vpcs;
    /**
     * 作为租户的应用基线。
     */
    private List<AppBaseline> appBaseline;

    /**
     * 租户充值银行账号
     */
    private String bankAccount;
    /**
     * 进入系统时间
     */
    private String enterTime;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(String enterTime) {
        this.enterTime = enterTime;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getCiticOrgId() {
        return citicOrgId;
    }

    public void setCiticOrgId(String citicOrgId) {
        this.citicOrgId = citicOrgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getSuperior() {
        return superior;
    }

    public void setSuperior(String superior) {
        this.superior = superior;
    }

    public String getIsCostCenter() {
		return isCostCenter;
	}

	public void setIsCostCenter(String isCostCenter) {
		this.isCostCenter = isCostCenter;
	}

	public String getIsTenant() {
		return isTenant;
	}

	public void setIsTenant(String isTenant) {
		this.isTenant = isTenant;
	}

	public String getIsSupplier() {
		return isSupplier;
	}

	public void setIsSupplier(String isSupplier) {
		this.isSupplier = isSupplier;
	}

	public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public List<VPC> getVpcs() {
        return vpcs;
    }

    public void setVpcs(List<VPC> vpcs) {
        this.vpcs = vpcs;
    }

    public List<AppBaseline> getAppBaseline() {
        return appBaseline;
    }

    public void setAppBaseline(List<AppBaseline> appBaseline) {
        this.appBaseline = appBaseline;
    }
}
