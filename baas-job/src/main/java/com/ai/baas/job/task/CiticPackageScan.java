package com.ai.baas.job.task;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ai.baas.job.service.busi.interfaces.ICiticPackageScanBusiSV;
import com.ai.baas.job.util.PropertiesUtil;
import com.ai.opt.sdk.util.ApplicationContextUtil;
/**
 * 批量扫描套餐包产品 Date: 2016年9月19日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author wangkai16
 */
public class CiticPackageScan {
    private static final String PATH = "classpath:context/job-context.xml";

    public static void main(String[] args){
        ApplicationContextUtil.loadApplicationContext(new ClassPathXmlApplicationContext(new String[] { PATH }));
        PropertiesUtil.load("job.properties");
        ICiticPackageScanBusiSV packageScan = ApplicationContextUtil.getService(ICiticPackageScanBusiSV.class);
        packageScan.scan();
    }
}
