package com.ai.opt.baas.bmc.test.api.citic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class OrderinfoChangeSVRestTest {
    @Autowired
    protected ApplicationContext ctx;
    
    @Test
    public void orderinfoDeleteTest() {
        
    }
}
