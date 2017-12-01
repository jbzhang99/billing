package com.ai.opt.sys.api.citicrestcommon.param;

import java.io.Serializable;

/**
 * 中信租户应用基线实体
 *
 * Date: 2016年7月6日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author wangyx13
 */
public class AppBaseline implements Serializable{
    /**
     * 应用基线唯一标识。
     */
    private String id;
    /**
     * 租户内部的应用基线的ID。
     */
    private String appId;
    /**
     * 应用基线名称。
     */
    private String name;
    /**
     * 应用基线简称。
     */
    private String abbreviation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
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
}
