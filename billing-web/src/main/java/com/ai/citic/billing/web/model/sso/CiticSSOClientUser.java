package com.ai.citic.billing.web.model.sso;

import com.ai.opt.sys.api.citicrestcommon.param.Role;
import com.ai.opt.sys.api.citicrestcommon.param.Service;

import java.io.Serializable;
import java.util.List;

/**
 * 中信项目单点登录成功后的User实体
 *
 * Date: 2016年3月16日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author gucl
 */
public class CiticSSOClientUser implements Serializable{
    private static final long serialVersionUID = -8147635836938729264L;

    //登录名称
    private String username;

    //账号ID
    private String userId;
    //账号名称
    private String loginName;
    //手机号码
    private String mobile;
    //邮件
    private String email;

    /**
     * baas系统租户id
     */
    private String tenantId;
    //组织机构id
    private String orgId;
    //中信tenantId
    private String tenant;

    //账户相关
    private String acctId;
    private String ownerType;
    //客户id
    private String ownerId;
    private String acctName;
    private String acctType;

    private String employeeId;
    //角色
    private List<Role> roles;
    private List<Service> services;
    private List<Service> operatingServices;

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getAcctId() {
        return acctId;
    }

    public void setAcctId(String acctId) {
        this.acctId = acctId;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<Service> getOperatingServices() {
        return operatingServices;
    }

    public void setOperatingServices(List<Service> operatingServices) {
        this.operatingServices = operatingServices;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getLoginName() {
        return loginName;
    }
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


}
