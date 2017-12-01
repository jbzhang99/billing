package com.ai.opt.sys.api.citicrestcommon.param;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 中信用户查询管理接口查询请求实体
 *
 * Date: 2016年7月6日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author wangyx13
 */
public class UserInfoQueryVo extends BaseInfo{
    /**
     * 查询类型。
     * 1. 按照实例ID查询拥有权限的普通用户
     * 2. 查询所有（待赋权）用户
     * 3. 根据租户ID，返回租户管理员列表
     * 4. 根据租户ID返回租户下所有用户
     * 5. 根据服务ID，返回运营管理员列表
     * 6. 查询所有用户
     * 7. 根据高伟达的用户ID查询某用户
     * 14. 根据单点返回的用户ID查询用户信息
     *
     */
    private String selectType;
    /**
     * 查询ID，当查询类型为1时为服务实例ID，为2时不填，为3和4时为租户ID，为5时为服务ID， 为6时不填， 为7和14时为用户ID
     */
    private String selectId;

    public String getSelectType() {
        return selectType;
    }

    public void setSelectType(String selectType) {
        this.selectType = selectType;
    }

    public String getSelectId() {
        return selectId;
    }

    public void setSelectId(String selectId) {
        this.selectId = selectId;
    }
}
