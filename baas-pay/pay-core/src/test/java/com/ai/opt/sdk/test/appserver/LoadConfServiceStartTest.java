package com.ai.opt.sdk.test.appserver;

import com.ai.opt.sdk.appserver.LoadConfServiceStart;

/**
 * 加载各模块配置文件到zk
 *
 * Date: 2016年7月13日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author gucl
 */
public class LoadConfServiceStartTest {
    public static void main(String[] args) {
        args = new String[1];
        args[0] = "e:/github/baas-pay/pay-core/src/main/resources/paas/sdkmode";
        LoadConfServiceStart.main(args);
    }
}
