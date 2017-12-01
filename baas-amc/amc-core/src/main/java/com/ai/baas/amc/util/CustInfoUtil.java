package com.ai.baas.amc.util;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.ai.baas.amc.vo.CustInfo;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.util.CollectionUtil;

/**
 * 客户信息辅助类
 *
 * Date: 2016年4月18日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public final class CustInfoUtil {
    
    private CustInfoUtil() {
        
    }
    
    private static final CacheClientUtil cacheClient = CacheClientUtil.getInstance();
    
    /**
     * 客户信息表名
     */
    private static final String BL_CUSTINFO_TABLE = "bl_custinfo";
    
    /**
     * 获取客户信息
     * @param tenantId
     * @param custId
     * @return
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    public static CustInfo getCustInfo(String tenantId, String custId) {
        Map<String, String> params = new TreeMap<String, String>();
        params.put("CUST_ID", custId);
        params.put("TENANT_ID",tenantId);
        List<Map<String, String>> resultList = null;
        try {
            resultList = cacheClient.doQuery(BL_CUSTINFO_TABLE, params);
        } catch(Exception ex) {
            throw new SystemException("从共享内存中获取客户信息失败！", ex);
        }
        
        if(CollectionUtil.isEmpty(resultList)) {
            return null;
        }
        Map<String, String> custInfoMap = resultList.get(0);
        if(custInfoMap.isEmpty()) {
            return null;
        }
        CustInfo custInfo = new CustInfo();
        custInfo.setCustName(custInfoMap.get("cust_name"));
        custInfo.setCustGrade(custInfoMap.get("cust_grade"));
        return custInfo;
    }

}
