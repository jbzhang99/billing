package com.ai.opt.sys.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ai.opt.sdk.components.mcs.MCSClientFactory;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.opt.sys.constants.CacheNSMapper;
import com.ai.opt.sys.constants.SysConstants;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 从缓存中获取科目定义 <br>
 *
 * Date: 2015年9月9日 <br>
 * Copyright (c) 2015 asiainfo.com <br>
 * 
 * @author lilg
 */
public final class SubjectUtil {

    private static final Logger LOG = LogManager.getLogger(SubjectUtil.class);

    private SubjectUtil() {
    }

    public static String generateKey(String industryCode, String tenantId, Long subjectId) {
        if (subjectId == null) {
            return tenantId.toUpperCase();
        } else {
            return industryCode.toUpperCase() + "." + tenantId.toUpperCase() + "."
                    + String.valueOf(subjectId.longValue());
        }
    }

    public static String generateKey(String industryCode, String tenantId, String subjectType) {
        return industryCode.toUpperCase() + "." + tenantId.toUpperCase() + ".t"
                + subjectType.toUpperCase();
    }

    /**
     * 
     * 从缓存中获取科目名称,翻译科目ID <br>
     * 适用所有科目 <br>
     * 
     * @param industryCode
     * @param tenantId
     * @param subjectId
     * @return
     * @author lilg
     */
    public static String getGnSubjectName(String industryCode, String tenantId, Long subjectId) {
        JSONObject redisData = getGnSubject(industryCode, tenantId, subjectId);
        if (redisData.isEmpty()) {
            LOG.error("翻译科目:industryCode[{}],tenantId[{}],subjectId[{}]失败.失败原因:{}",
                    industryCode, tenantId, subjectId, "缓存中未查询到科目信息");
            return String.valueOf(subjectId);
        }
        return redisData.getString("subjectName");
    }

    /**
     * 
     * 从缓存中获取科目，返回JSON对象 <br>
     * 返回对象仅包含表[FUN_SUBJECT]的数据<br>
     * 适用所有科目 <br>
     * 
     * @param industryCode
     * @param tenantId
     * @param subjectId
     * @return
     * @author lilg
     * @ApiDocMethod
     * @ApiCode
     */
    public static JSONObject getGnSubject(String industryCode, String tenantId, Long subjectId) {
        ICacheClient cacheClient = MCSClientFactory.getCacheClient(CacheNSMapper.CACHE_GN_SUBJECT);
        String data = cacheClient.hget(CacheNSMapper.CACHE_GN_SUBJECT,
                generateKey(industryCode, tenantId, subjectId));
        if (!StringUtil.isBlank(data)) {
            return JSONObject.parseObject(data);
        } else {
            LOG.info("缓存中未找到科目:industryCode[{}],tenantId[{}],subjectId[{}],开始匹配通用租户",
                    industryCode, tenantId, subjectId);
            data = cacheClient.hget(CacheNSMapper.CACHE_GN_SUBJECT,
                    generateKey(industryCode, SysConstants.TenantId.ALL_TENANT, subjectId));
        }
        if (!StringUtil.isBlank(data)) {
            return JSONObject.parseObject(data);
        } else {
            LOG.info("缓存中未找到科目:industryCode[{}],tenantId[{}],subjectId[{}],开始匹配通用行业",
                    industryCode, tenantId, subjectId);
            data = cacheClient.hget(CacheNSMapper.CACHE_GN_SUBJECT,
                    generateKey(SysConstants.GnSubject.ALL_INDUSTRY, tenantId, subjectId));
        }
        if (!StringUtil.isBlank(data)) {
            return JSONObject.parseObject(data);
        } else {
            LOG.info("缓存中未找到科目:industryCode[{}],tenantId[{}],subjectId[{}],开始匹配通用行业通用租户",
                    industryCode, tenantId, subjectId);
            data = cacheClient.hget(
                    CacheNSMapper.CACHE_GN_SUBJECT,
                    generateKey(SysConstants.GnSubject.ALL_INDUSTRY, SysConstants.TenantId.ALL_TENANT,
                            subjectId));
        }
        if (!StringUtil.isBlank(data)) {
            return JSONObject.parseObject(data);
        } else {
            LOG.error("翻译科目Id[{}]失败.失败原因:{}", subjectId, "缓存中未查询到科目信息");
            return new JSONObject();
        }
    }

    /**
     * 从缓存中获取全部科目，返回JSON对象 <br>
     * 
     * @param industryCode
     * @param tenantId
     * @param subjectType
     * @return
     * @author lilg
     */
    public static JSONArray getGnSubject(String industryCode, String tenantId, String subjectType) {
        ICacheClient cacheClient = MCSClientFactory.getCacheClient(CacheNSMapper.CACHE_GN_SUBJECT);
        // 精确匹配
        String data1 = cacheClient.hget(CacheNSMapper.CACHE_GN_SUBJECT,
                generateKey(industryCode, tenantId, subjectType));
        // 通配租户
        String data2 = cacheClient.hget(CacheNSMapper.CACHE_GN_SUBJECT,
                generateKey(industryCode, SysConstants.TenantId.ALL_TENANT, subjectType));
        // 通配行业
        String data3 = cacheClient.hget(CacheNSMapper.CACHE_GN_SUBJECT,
                generateKey(SysConstants.GnSubject.ALL_INDUSTRY, tenantId, subjectType));
        // 通配行业和租户
        String data4 = cacheClient.hget(
                CacheNSMapper.CACHE_GN_SUBJECT,
                generateKey(SysConstants.GnSubject.ALL_INDUSTRY, SysConstants.TenantId.ALL_TENANT,
                        subjectType));

        if (StringUtil.isBlank(data1) && StringUtil.isBlank(data2) && StringUtil.isBlank(data3)
                && StringUtil.isBlank(data4)) {
            LOG.error("缓存中未找到科目:industryCode[{}],tenantId[{}],subjectType[{}]", industryCode,
                    tenantId, subjectType);
            return new JSONArray();
        }
        JSONArray redisData = new JSONArray();
        if (!StringUtil.isBlank(data1)) {
            redisData.addAll(JSONArray.parseArray(data1));
        }
        if (!StringUtil.isBlank(data2)) {
            redisData.addAll(JSONArray.parseArray(data2));
        }
        if (!StringUtil.isBlank(data3)) {
            redisData.addAll(JSONArray.parseArray(data3));
        }
        if (!StringUtil.isBlank(data4)) {
            redisData.addAll(JSONArray.parseArray(data4));
        }
        LOG.info(redisData.toJSONString());
        return redisData;
    }

    /**
     * 
     * 从缓存中获取资金科目，返回JSON对象 <br>
     * 返回对象融合了表[FUN_SUBJECT]和表[FUN_SUBJECT_FUND]的数据 <br>
     * 仅仅适用资金科目 <br>
     * 
     * @param industryCode
     * @param tenantId
     * @param subjectId
     * @return
     * @author lilg
     */
    public static JSONObject getGnSubjectFund(String industryCode, String tenantId, Long subjectId) {
        // 获取SUBJECT subject_type=9
        JSONObject redisData = getGnSubject(industryCode, tenantId, subjectId);
        if (redisData.isEmpty()) {
            LOG.error("科目Id[{}]查询失败.失败原因:{}", subjectId, "缓存中未查询到科目信息");
            return new JSONObject();
        }
        if (!SysConstants.GnSubject.SubjectType.FUND.equals(redisData.getString("subjectType"))) {
            LOG.error("科目Id[{}]查询失败.失败原因:{}", subjectId, "缓存中未查询到资金科目信息");
            return new JSONObject();
        }
        // 获取SUBJECT_FUND
        ICacheClient cacheClient2 = MCSClientFactory
                .getCacheClient(CacheNSMapper.CACHE_GN_SUBJECT_FUND);
        String subjectFund = cacheClient2.hget(CacheNSMapper.CACHE_GN_SUBJECT_FUND,
                generateKey(industryCode, tenantId, subjectId));
        if (StringUtil.isBlank(subjectFund)) {
            LOG.info("缓存中未找到资金科目扩展信息:industryCode[{}],tenantId[{}],subjectId[{}],开始匹配通用租户",
                    industryCode, tenantId, subjectId);
            subjectFund = cacheClient2.hget(CacheNSMapper.CACHE_GN_SUBJECT_FUND,
                    generateKey(industryCode, SysConstants.TenantId.ALL_TENANT, subjectId));
        }
        if (StringUtil.isBlank(subjectFund)) {
            LOG.info("缓存中未找到资金科目扩展信息:industryCode[{}],tenantId[{}],subjectId[{}],开始匹配通用行业",
                    industryCode, tenantId, subjectId);
            subjectFund = cacheClient2.hget(CacheNSMapper.CACHE_GN_SUBJECT_FUND,
                    generateKey(SysConstants.GnSubject.ALL_INDUSTRY, tenantId, subjectId));
        }
        if (StringUtil.isBlank(subjectFund)) {
            LOG.info("缓存中未找到资金科目扩展信息:industryCode[{}],tenantId[{}],subjectId[{}],开始匹配通用行业通用租户",
                    industryCode, tenantId, subjectId);
            subjectFund = cacheClient2.hget(
                    CacheNSMapper.CACHE_GN_SUBJECT_FUND,
                    generateKey(SysConstants.GnSubject.ALL_INDUSTRY, SysConstants.TenantId.ALL_TENANT,
                            subjectId));
        }
        LOG.info("subjectFund=================>:"+subjectFund);
        if (!StringUtil.isBlank(subjectFund)) {
            redisData.putAll(JSON.parseObject(subjectFund));
        }
        return redisData;
    }

}
