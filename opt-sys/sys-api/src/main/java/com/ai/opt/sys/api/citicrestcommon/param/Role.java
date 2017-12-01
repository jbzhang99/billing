package com.ai.opt.sys.api.citicrestcommon.param;

import java.io.Serializable;

/**
 * 中信用户角色信息实体
 *
 * Date: 2016年7月6日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author wangyx13
 */
public class Role implements Serializable {
    /**
     * 角色的唯一标识，自然数，1=普通用户，2=租户管理员，3=运营管理员， 4=系统管理员
     */
    private String roleId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
