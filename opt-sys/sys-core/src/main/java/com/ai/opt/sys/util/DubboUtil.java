package com.ai.opt.sys.util;

import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sys.api.tenant.interfaces.ITenantManageSV;


/**
 * dubbo服务消费工具类
 *
 * Date: 2015年9月16日 <br>
 * Copyright (c) 2015 asiainfo.com <br>
 * @author fanpw
 */
public final class DubboUtil {

    private DubboUtil() {

    }

    /**
     * 获取账户体系租户查询服务
     * 
     * @return
     * @author lilg
     */
    public static ITenantManageSV getTenantManageSV() {
        ITenantManageSV sv = null;
        try {
            sv = DubboConsumerFactory.getService("tenantManageSV", ITenantManageSV.class);
        } catch (Exception ex) {
            throw new SystemException(ex);
        }
        if (sv == null) {
            throw new SystemException("获取不到远程服务:" + ITenantManageSV.class);
        }
        return sv;
    }

}
