package com.ai.baas.bmc.api.packageassemble.impl.atom;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ai.baas.bmc.util.DshmUtil;
import com.ai.baas.bmc.util.TableConstants;
import com.ai.baas.bmc.util.TableConstants.PackageInfo;
import com.ai.baas.bmc.util.TableConstants.PriceDetail;
import com.ai.baas.bmc.util.TableConstants.SubsComm;
import com.ai.baas.dshm.client.impl.DshmClient;
import com.ai.baas.dshm.client.interfaces.IDshmClient;
import com.ai.opt.base.exception.BusinessException;
import com.alibaba.fastjson.JSON;

public class AccquireDshm {

    private static final Log LOG = LogFactory.getLog(AccquireDshm.class);
    
    public static Map<String, String> getBlSubsComm(String inputMessage) {
        IDshmClient client = new DshmClient();
        Map<String, String> params = new TreeMap<>();
        params.put(SubsComm.PRODUCT_ID, inputMessage);               
        List<Map<String,String>> subsCommResults = client.list(TableConstants.SUBS_COMM)
                .where(params)
                .executeQuery(DshmUtil.getCacheClient());
        LOG.info("params:"+ JSON.toJSONString(params)+", pricedetailResults.size : "+subsCommResults.size()+", pricedetailResults: "+JSON.toJSONString(subsCommResults));
        if(subsCommResults.size()!=1){
            
            throw new BusinessException("【SubsComm表读取错误】");
        }
        return subsCommResults.get(0);
    }

    public static Map<String, String> getBlSubsCommExt(String inputMessage) {
        IDshmClient client = new DshmClient();
        Map<String, String> params = new TreeMap<>();
        params.put(SubsComm.PRODUCT_ID, inputMessage);     
        params.put(SubsComm.EXT_NAME, "apportion_list");
        List<Map<String,String>> subsCommExtResults = client.list(TableConstants.SUBS_COMM_EXT)
                .where(params)
                .executeQuery(DshmUtil.getCacheClient());
        LOG.info("params:"+ JSON.toJSONString(params)+", pricedetailResults.size : "+subsCommExtResults.size()+", pricedetailResults: "+JSON.toJSONString(subsCommExtResults));

        if(subsCommExtResults.size()!=1){
           
            throw new BusinessException("【SubsCommExt表读取错误】");
        }
        return subsCommExtResults.get(0);
    }

    public static Map<String, String> getBlUserinfo(String subs_id, String tenant_id) {
        IDshmClient client = new DshmClient();
        Map<String, String> params = new TreeMap<>();
        params.put(SubsComm.SUBS_ID, subs_id);        
        params.put(SubsComm.TENANT_ID, tenant_id);
        List<Map<String, String>> userinfoResults = client.list(TableConstants.USER_INFO)
                .where(params)
                .executeQuery(DshmUtil.getCacheClient());
        LOG.info("params:"+ JSON.toJSONString(params)+", pricedetailResults.size : "+userinfoResults.size()+", pricedetailResults: "+JSON.toJSONString(userinfoResults));
        if(userinfoResults.size()!=1){
            
            throw new BusinessException("【bluserinfo表读取错误】");
        }
        return userinfoResults.get(0);
    }

    public static Map<String, String> getPackageinfo(String detail_code) {
        IDshmClient client = new DshmClient();
        Map<String, String> params = new TreeMap<>();
        params.put(PackageInfo.DETAIL_CODE, detail_code);        
        List<Map<String, String>> packageinfoResults = client.list(TableConstants.PACKAGE_INFO)
                .where(params)
                .executeQuery(DshmUtil.getCacheClient());
        LOG.info("params:"+ JSON.toJSONString(params)+", pricedetailResults.size : "+packageinfoResults.size()+", pricedetailResults: "+JSON.toJSONString(packageinfoResults));
        if(packageinfoResults.size()!=1){
            
            throw new BusinessException("【packageinfo表读取错误】");
        }
        return packageinfoResults.get(0);
    }

    public static Map<String, String> getPriceDetail(String price_code) {
        IDshmClient client = new DshmClient();
        Map<String, String> params = new TreeMap<>();
        params.put(PriceDetail.PRICE_CODE, price_code);        
        List<Map<String, String>> pricedetailResults = client.list(TableConstants.PRICE_DETAIL)
                .where(params)
                .executeQuery(DshmUtil.getCacheClient());
        LOG.info("params:"+ JSON.toJSONString(params)+", pricedetailResults.size : "+pricedetailResults.size()+", pricedetailResults: "+JSON.toJSONString(pricedetailResults));
        if(pricedetailResults.size()!=1){
            throw new BusinessException("【priceDetail表读取错误】");
        }
        return pricedetailResults.get(0);
    }


}
