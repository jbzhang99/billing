package com.ai.baas.bmc.api.packageassemble.impl;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.ai.opt.sdk.util.ApplicationContextUtil;

public class StartSrv {


        private static final String PATH = "classpath:context/core-context.xml";

        public static void main(String[] args){
            
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                    new String[] { PATH });
            context.registerShutdownHook();
            context.start();
//            //对外暴露context
//            ApplicationContextUtil.loadApplicationContext(context);
            
        }

}
