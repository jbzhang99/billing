package com.ai.baas.smc.test.dshm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.baas.dshm.api.dshmprocess.interfaces.IdshmSV;
import com.ai.baas.smc.constants.SmcCacheConstant;
import com.ai.baas.smc.vo.dshm.ImportLogDshmVO;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/applicationContextTest/dubbo-consumer-context.xml" })
public class DshmDubboTest {
    @Autowired
    protected ApplicationContext ctx;

    @Test
    public void testDshm() {
        IdshmSV idshmSV = (IdshmSV) ctx.getBean("idshmSV");
        ImportLogDshmVO stlImportLog = new ImportLogDshmVO();
        stlImportLog.setBatch_no("1234");
        stlImportLog.setTenant_id("tenantId");
        stlImportLog.setBill_time_sn("201604");
        int s = idshmSV.initLoader(SmcCacheConstant.Dshm.TableName.STL_IMPORT_LOG,
                JSON.toJSONString(stlImportLog), SmcCacheConstant.Dshm.OptType.UPDATE);
        System.out.println(s);
    }
}
