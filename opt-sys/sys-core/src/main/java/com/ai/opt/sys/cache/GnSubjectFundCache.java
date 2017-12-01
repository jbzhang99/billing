package com.ai.opt.sys.cache;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.sdk.cache.base.AbstractCache;
import com.ai.opt.sdk.components.mcs.MCSClientFactory;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sys.constants.CacheNSMapper;
import com.ai.opt.sys.dao.mapper.bo.GnSubjectFund;
import com.ai.opt.sys.service.atom.interfaces.IGnSubjectFundAtomSV;
import com.ai.opt.sys.util.SubjectUtil;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.alibaba.fastjson.JSON;

/**
 * 资金科目定义表FUN_SUBJECT_FUND <br>
 * 缓存key为subjectId 缓存对象FUN_SUBJECT_FUND <br>
 * Date: 2015年8月19日 <br>
 * Copyright (c) 2015 asiainfo.com <br>
 * 
 * @author lilg
 */
@Component
public class GnSubjectFundCache extends AbstractCache {

    private static final Logger LOG = LogManager.getLogger(GnSubjectFundCache.class);

    @Autowired
    private IGnSubjectFundAtomSV gnSubjectFundAtomSV;

    @Override
    public void write() throws Exception {
        List<GnSubjectFund> funSubjectFundList = gnSubjectFundAtomSV.queryGnSubjectFund();
        if (CollectionUtil.isEmpty(funSubjectFundList)) {
            return;
        }
        ICacheClient cacheClient = MCSClientFactory
                .getCacheClient(CacheNSMapper.CACHE_GN_SUBJECT_FUND);
        for (GnSubjectFund funSubjectFund : funSubjectFundList) {
            LOG.info("缓存GnSubjectFund资金科目定义:行业{},租户ID{},科目ID{}",
                    funSubjectFund.getIndustryCode(), funSubjectFund.getTenantId(),
                    funSubjectFund.getSubjectId());
            String key = SubjectUtil.generateKey(funSubjectFund.getIndustryCode(),
                    funSubjectFund.getTenantId(), funSubjectFund.getSubjectId());
            cacheClient.hset(CacheNSMapper.CACHE_GN_SUBJECT_FUND, key, JSON.toJSONString(funSubjectFund));
        }
    }

}
