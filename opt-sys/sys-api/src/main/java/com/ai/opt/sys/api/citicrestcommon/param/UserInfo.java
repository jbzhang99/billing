package com.ai.opt.sys.api.citicrestcommon.param;

import java.io.Serializable;
import java.util.List;

/**
 * 中信用户信息实体,包含用户资料、角色等
 *
 * Date: 2016年7月6日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author wangyx13
 */
public class UserInfo implements Serializable{
    /**
     * 用户的唯一标识。
     */
    private String userId;
    /**
     * 中信集团及其子公司员工在集团LDAP中的员工号。
     */
    private String employeeId;
    /**
     * 姓名。
     */
    private String name;
    /**
     * 电子邮箱。
     */
    private String mail;
    /**
     * 手机。
     */
    private String cell;
    /**
     * 所属组织的唯一标识。
     */
    private String orgId;
    /**
     * 验证状态。
     */
    private String valiState;
    /**
     * 验证码。
     */
    private String valiCode;
    /**
     * 所属租户。
     */
    private String tenant;
    /**
     * 用户所属的角色列表。
     */
    private List<Role> roles;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getValiState() {
        return valiState;
    }

    public void setValiState(String valiState) {
        this.valiState = valiState;
    }

    public String getValiCode() {
        return valiCode;
    }

    public void setValiCode(String valiCode) {
        this.valiCode = valiCode;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }
}
