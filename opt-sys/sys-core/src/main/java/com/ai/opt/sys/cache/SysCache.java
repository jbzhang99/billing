package com.ai.opt.sys.cache;

import com.ai.opt.sdk.components.mcs.MCSClientFactory;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sys.constants.CacheNSMapper;
import com.ai.opt.sys.dao.mapper.bo.GnSubject;
import com.ai.opt.sys.dao.mapper.bo.GnSubjectCriteria;
import com.ai.opt.sys.dao.mapper.bo.GnSubjectFund;
import com.ai.opt.sys.dao.mapper.bo.GnSubjectFundCriteria;
import com.ai.opt.sys.dao.mapper.interfaces.GnSubjectFundMapper;
import com.ai.opt.sys.dao.mapper.interfaces.GnSubjectMapper;
import com.ai.opt.sys.util.SubjectUtil;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目启动自动刷新缓存.
 */
public class SysCache implements InitializingBean{

    private static final Logger LOG = LogManager.getLogger(GnSubjectCache.class);

    @Autowired
    private GnSubjectMapper gnSubjectMapper;
    @Autowired
    private GnSubjectFundMapper gnSubjectFundMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        LOG.error("=====开始刷新缓存======");
        loadGnSubject();
        loadGnSubjectFund();
        LOG.error("=====刷新缓存完成======");
    }

    private void loadGnSubjectFund() {
        List<GnSubjectFund> funSubjectFundList = gnSubjectFundMapper.selectByExample(new GnSubjectFundCriteria());
        if (CollectionUtil.isEmpty(funSubjectFundList)) {
            return;
        }
        ICacheClient cacheClient = MCSClientFactory
                .getCacheClient(CacheNSMapper.CACHE_GN_SUBJECT_FUND);
        cacheClient.del(CacheNSMapper.CACHE_GN_SUBJECT_FUND);
        for (GnSubjectFund funSubjectFund : funSubjectFundList) {
            LOG.info("缓存GnSubjectFund资金科目定义:行业{},租户ID{},科目ID{}",
                    funSubjectFund.getIndustryCode(), funSubjectFund.getTenantId(),
                    funSubjectFund.getSubjectId());
            String key = SubjectUtil.generateKey(funSubjectFund.getIndustryCode(),
                    funSubjectFund.getTenantId(), funSubjectFund.getSubjectId());
            cacheClient.hset(CacheNSMapper.CACHE_GN_SUBJECT_FUND, key, JSON.toJSONString(funSubjectFund));
        }
    }


    private void loadGnSubject() {
        List<GnSubject> funSubjectList = gnSubjectMapper.selectByExample(new GnSubjectCriteria());
        if (CollectionUtil.isEmpty(funSubjectList)) {
            return;
        }
        ICacheClient cacheClient = MCSClientFactory.getCacheClient(CacheNSMapper.CACHE_GN_SUBJECT);
        cacheClient.del(CacheNSMapper.CACHE_GN_SUBJECT);
        Map<String, List<GnSubject>> subjectTypeMap = new HashMap<String, List<GnSubject>>();
        for (GnSubject funSubject : funSubjectList) {
            LOG.debug("缓存GnSubject科目:行业{},租户ID{},科目ID{},名称{}", funSubject.getIndustryCode(),
                    funSubject.getTenantId(), funSubject.getSubjectId(),
                    funSubject.getSubjectName());
            String key1 = SubjectUtil.generateKey(funSubject.getIndustryCode(),
                    funSubject.getTenantId(), funSubject.getSubjectId());
            // 缓存key1数据
            cacheClient.hset(CacheNSMapper.CACHE_GN_SUBJECT, key1, JSON.toJSONString(funSubject));
            // 组装key2数据
            String key2 = SubjectUtil.generateKey(funSubject.getIndustryCode(),
                    funSubject.getTenantId(), funSubject.getSubjectType());
            if (!subjectTypeMap.containsKey(key2)) {
                List<GnSubject> subject = new ArrayList<GnSubject>();
                subjectTypeMap.put(key2, subject);
            }
            subjectTypeMap.get(key2).add(funSubject);
        }
        // 缓存key2
        for (Map.Entry<String, List<GnSubject>> subjectTypeEntry : subjectTypeMap.entrySet()) {
            LOG.debug("缓存GnSubject科目【按照科目类型缓存】行业+租户ID+科目类型{},科目数量{}", subjectTypeEntry.getKey(),
                    subjectTypeEntry.getValue().size());
            String key2 = subjectTypeEntry.getKey();
            cacheClient.hset(CacheNSMapper.CACHE_GN_SUBJECT, key2,
                    JSON.toJSONString(subjectTypeEntry.getValue()));
        }
    }
}
