package com.ai.citic.billing.web.util;

import com.ai.citic.billing.web.constants.CiticWebConstants;
import com.ai.opt.sdk.components.mcs.MCSClientFactory;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sys.api.citicrestcommon.interfaces.ICiticRestReqWrapperSV;
import com.ai.opt.sys.api.citicrestcommon.param.*;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.ai.paas.ipaas.util.SerializeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

public class CacheUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheUtil.class);

    /**
     * 获取机构信息，存入缓存
     * @param tenantId 计费平台租户id
     * @param orgId 中信机构id
     */
    public static void fetchOrgInfo(String tenantId,String orgId){
        ICiticRestReqWrapperSV iCiticRestReqWrapperSV = DubboConsumerFactory.getService(ICiticRestReqWrapperSV.class);
        OrgQueryVo orgQueryVo = new OrgQueryVo();
        orgQueryVo.setTenantId(tenantId);
        orgQueryVo.setSelectType("3");
        orgQueryVo.setSelectId(orgId);
        OrgQueryResponse orgQueryResponse = iCiticRestReqWrapperSV.searchOrg(orgQueryVo);
        if(orgQueryResponse!=null&&orgQueryResponse.getResponseHeader().isSuccess()){
            if(!CollectionUtil.isEmpty(orgQueryResponse.getOrgs())){
                ICacheClient cacheClient = MCSClientFactory.getCacheClient(CiticWebConstants.ORGINFO_CACHE_NAMESPACE);
                OrgInfo orgInfo = orgQueryResponse.getOrgs().get(0);
                try {
                    cacheClient.set(orgId.getBytes("utf-8"), SerializeUtil.serialize(orgInfo));
                } catch (UnsupportedEncodingException e) {
                    LOGGER.error("序列化机构信息错误",e);
                }
            }else{
                LOGGER.error("机构【"+orgId+"】信息获取失败");
            }
        }
    }

    /**
     * 缓存用户信息
     * @param tenantId 计费平台租户id
     * @param tenant 中信租户id
     */
    public static void fetchUserInfo(String tenantId,String tenant){
        ICiticRestReqWrapperSV citicRestReqWrapperSV = DubboConsumerFactory.getService(ICiticRestReqWrapperSV.class);
        UserInfoQueryVo query = new UserInfoQueryVo();
        query.setTenantId(tenantId);
        query.setSelectType("4");
        query.setSelectId(tenant);
        UserQueryResponse userQueryResponse = citicRestReqWrapperSV.searchUser(query);
        if(userQueryResponse.getResponseHeader().isSuccess()&&!CollectionUtil.isEmpty(userQueryResponse.getUsers())){
            for (UserInfo info:userQueryResponse.getUsers()){
                ICacheClient cacheClient = MCSClientFactory.getCacheClient(CiticWebConstants.USERINFO_CACHE_NAMESPACE);
                try {
                    cacheClient.set(info.getUserId().getBytes("utf-8"),SerializeUtil.serialize(info));
                } catch (UnsupportedEncodingException e) {
                    LOGGER.error("序列化用户信息错误",e);
                }
            }
        }
    }

    /**
     * 缓存orgInfo
     * @param orgInfo 机构信息
     */
    public static void cacheOrgInfo(OrgInfo orgInfo) {
        ICacheClient cacheClient = MCSClientFactory.getCacheClient(CiticWebConstants.ORGINFO_CACHE_NAMESPACE);
        try {
            cacheClient.set(orgInfo.getOrgId().getBytes("utf-8"), SerializeUtil.serialize(orgInfo));
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("序列化机构信息错误",e);
        }
    }

    /**
     * 根据orgid获取缓存的机构信息
     * @param orgId
     * @return
     */
    public static OrgInfo getOrgInfoFromCache(String orgId) {
        ICacheClient cacheClient = MCSClientFactory.getCacheClient(CiticWebConstants.ORGINFO_CACHE_NAMESPACE);
        try {
            return (OrgInfo) SerializeUtil.deserialize(cacheClient.get(orgId.getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("反序列化机构信息错误",e);
        }
        return null;
    }

    /**
     * 根据用户id获取用户信息
     * @param userId
     * @return
     */
    public static UserInfo getUserInfoFromCache(String userId) {
        ICacheClient cacheClient = MCSClientFactory.getCacheClient(CiticWebConstants.USERINFO_CACHE_NAMESPACE);
        try {
            return (UserInfo) SerializeUtil.deserialize(cacheClient.get(userId.getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("反序列化用户信息错误",e);
        }
        return null;
    }

    /**
     * 缓存用户信息
     * @param userInfo
     */
    public static void cacheUserInfo(UserInfo userInfo) {
        ICacheClient cacheClient = MCSClientFactory.getCacheClient(CiticWebConstants.USERINFO_CACHE_NAMESPACE);
        try {
            cacheClient.set(userInfo.getUserId().getBytes("utf-8"), SerializeUtil.serialize(userInfo));
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("序列化用户信息错误",e);
        }
    }

    /**
     * 缓存账户对应的orgId
     * @param key 租户id+":"+acctId
     * @param orgId
     */
    public static void cacheExtCustIdForAcct(String key, String orgId) {
        MCSClientFactory.getCacheClient(CiticWebConstants.ORGINFO_CACHE_NAMESPACE).set(key,orgId);
    }

    /**
     * 从缓存获取orgId
     * @param key 租户id+":"+acctId
     * @return
     */
    public static String getOrgIdFromCache(String key) {
        return MCSClientFactory.getCacheClient(CiticWebConstants.ORGINFO_CACHE_NAMESPACE).get(key);
    }
}
