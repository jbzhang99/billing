package com.ai.baas.amc.test;

import com.ai.opt.sdk.appserver.DubboServiceStart;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jackieliu on 16/4/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class DubboStartTest {

    /**
     * 模拟启动dubbo服务
     */
    @Test
    public void testDubboStart(){
        DubboServiceStart.main(null);
    }
    
    @Test
    public void testMDS(){
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
