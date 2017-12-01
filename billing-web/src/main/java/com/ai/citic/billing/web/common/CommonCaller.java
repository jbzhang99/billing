package com.ai.citic.billing.web.common;

import com.ai.baas.bmc.api.queryidinfo.interfaces.IQueryIdInfoSV;
import com.ai.baas.bmc.api.queryidinfo.params.BlAcctInfoInfo;
import com.ai.baas.bmc.api.queryidinfo.params.BlAcctInfoResponse;
import com.ai.baas.bmc.api.queryidinfo.params.ExtCustIdInfo;
import com.ai.citic.billing.web.util.CacheUtil;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sys.api.citicrestcommon.interfaces.ICiticRestReqWrapperSV;
import com.ai.opt.sys.api.citicrestcommon.param.OrgInfo;
import com.ai.opt.sys.api.citicrestcommon.param.OrgQueryResponse;
import com.ai.opt.sys.api.citicrestcommon.param.OrgQueryVo;

import com.ai.paas.ipaas.util.SerializeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Component
public class CommonCaller {

    /**
     * 根据外部客户id(中信orgid)获取系统内部账户、客户信息
     * @param tenantId
     * @param orgId
     * @return
     */
    public List<BlAcctInfoInfo> getAcctAndCustInfo(String tenantId,String orgId){
        IQueryIdInfoSV iQueryIdInfoSV = DubboConsumerFactory.getService(IQueryIdInfoSV.class);
        ExtCustIdInfo extCustIdInfo = new ExtCustIdInfo();
        extCustIdInfo.setTenantId(tenantId);
        extCustIdInfo.setExtCustId(orgId);
        BlAcctInfoResponse blAcctInfoResponse = iQueryIdInfoSV.queryAcctIdByExtCustId(extCustIdInfo);
        if(blAcctInfoResponse.getResponseHeader().isSuccess()){
            List<BlAcctInfoInfo> blAcctInfoInfos = blAcctInfoResponse.getBlAcctInfoInfos();
            if(!CollectionUtil.isEmpty(blAcctInfoInfos)){
                return blAcctInfoInfos;
            }
        }
        return null;
    }

    /**
     * 根据输入租户名称查询系统内所有账户客户信息
     * @param tenantId
     * @param custName
     * @return
     */
    public List<BlAcctInfoInfo> getAcctAndCustListByName(String tenantId, String custName) throws UnsupportedEncodingException {
        ICiticRestReqWrapperSV iCiticRestReqWrapperSV = DubboConsumerFactory.getService(ICiticRestReqWrapperSV.class);
        OrgQueryVo orgQueryVo = new OrgQueryVo();
        orgQueryVo.setTenantId(tenantId);
        orgQueryVo.setSelectType("4");
        orgQueryVo.setSelectId(URLEncoder.encode(custName,"utf-8"));
        OrgQueryResponse queryResponse = iCiticRestReqWrapperSV.searchOrg(orgQueryVo);
        if(queryResponse.getResponseHeader().isSuccess()){
            if(!CollectionUtil.isEmpty(queryResponse.getOrgs())){
                List<BlAcctInfoInfo> blAcctInfoInfos = new ArrayList<>();
                for (OrgInfo orgInfo:queryResponse.getOrgs()){
                	if(StringUtils.equals("1", orgInfo.getIsTenant())){
                        CacheUtil.cacheOrgInfo(orgInfo);
                        List<BlAcctInfoInfo> acctAndCustInfo = getAcctAndCustInfo(tenantId, orgInfo.getOrgId());
                        if(acctAndCustInfo!=null){
                            for(BlAcctInfoInfo blAcctInfoInfo:acctAndCustInfo){
                                CacheUtil.cacheExtCustIdForAcct(tenantId+":"+blAcctInfoInfo.getAcctId(),orgInfo.getOrgId());
                            }
                            blAcctInfoInfos.addAll(acctAndCustInfo);
                        }
                    }
                }
                return blAcctInfoInfos;
            }
        }
        return null;
    }
}
