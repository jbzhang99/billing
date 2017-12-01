package com.ai.opt.baas.bmc.test.api.cacherefresh.impl;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.bmc.api.cacherefresh.interfaces.ICacheRefreshSV;
import com.ai.opt.base.vo.BaseResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/applicationContextTest/dubbo-consumer-context.xml" })
public class CacheRefreshSVImplTest {
    @Autowired
    private transient ICacheRefreshSV cacheRefreshSV;

    @Test
    public void refresh() {
        BaseResponse response = cacheRefreshSV.refresh();
        assertTrue(response.getResponseHeader().getIsSuccess());
    }
}
