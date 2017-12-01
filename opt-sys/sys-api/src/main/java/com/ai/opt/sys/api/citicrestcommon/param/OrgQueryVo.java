package com.ai.opt.sys.api.citicrestcommon.param;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 中信机构查询管理接口查询请求实体
 *
 * Date: 2016年7月6日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author wangyx13
 */
public class OrgQueryVo extends BaseInfo{
    /**
     * 查询类型。
     * 1. 查询所有组织
     * 2. 查询所有租户
     * 3. 按机构ID查询某一租户
     * 4. 按机构名称查询组织
     * 5. 按citic_org_id查询
     * 6. 按租户充值银行号查询
     * 7. 查询租户下所有成本中心
     */
    private String selectType;
    /**
     * 查询ID，当查询类型为1时当前值为空，为2时当前值为”1”， 代表机构为租户，为3时当前值为机构ID，
     * 为4时当前值为机构名称， 为5时当前值为citic_org_id， 为6时当前值为租户充值用银行账号，为7时当前值为租户ID
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
