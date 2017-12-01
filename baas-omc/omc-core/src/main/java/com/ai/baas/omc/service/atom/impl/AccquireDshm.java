package com.ai.baas.omc.service.atom.impl;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ai.baas.dshm.client.impl.DshmClient;
import com.ai.baas.dshm.client.interfaces.IDshmClient;
import com.ai.baas.omc.constants.TableCon;
import com.ai.baas.omc.constants.TableCon.CustInfo;
import com.ai.baas.omc.constants.TableCon.UserInfo;
import com.ai.baas.omc.util.DshmUtil;
import com.ai.opt.base.exception.BusinessException;
import com.alibaba.fastjson.JSON;

public class AccquireDshm {

    private static final Log LOG = LogFactory.getLog(AccquireDshm.class);
    
    public static Map<String, String> getBlCustinfo(String extCustID, String tenantID) {
        IDshmClient client = new DshmClient();
        Map<String, String> params = new TreeMap<>();
        params.put(CustInfo.EXT_CUST_ID, extCustID);        
        params.put(CustInfo.TENANT_ID, tenantID);
        List<Map<String, String>> custinfoResults = client.list(TableCon.BL_CUSTINFO)
                .where(params)
                .executeQuery(DshmUtil.getCacheClient());
        LOG.info("params:"+ JSON.toJSONString(params)+", pricedetailResults.size : "+custinfoResults.size()+", pricedetailResults: "+JSON.toJSONString(custinfoResults));
        if(custinfoResults.size()!=1){
            throw new BusinessException("【blcustinfo表读取错误】");
        }
        return custinfoResults.get(0);
    }
    
    public static Map<String, String> getBlUserinfo(String custID, String tenantID) {
        IDshmClient client = new DshmClient();
        Map<String, String> params = new TreeMap<>();
        params.put(UserInfo.CUST_ID, custID);        
        params.put(UserInfo.TENANT_ID, tenantID);
        List<Map<String, String>> userinfoResults = client.list(TableCon.BL_USERINFO)
                .where(params)
                .executeQuery(DshmUtil.getCacheClient());
        LOG.info("params:"+ JSON.toJSONString(params)+", pricedetailResults.size : "+userinfoResults.size()+", pricedetailResults: "+JSON.toJSONString(userinfoResults));
        if(userinfoResults.size()!=1){
            throw new BusinessException("【bluserinfo表读取错误】");
        }
        return userinfoResults.get(0);
    }

}
