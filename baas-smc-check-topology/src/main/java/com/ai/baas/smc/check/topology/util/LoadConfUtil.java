package com.ai.baas.smc.check.topology.util;

import java.util.Map;
import java.util.Properties;

import com.ai.baas.smc.check.topology.constants.SmcConstant;
import com.ai.opt.sdk.components.base.ComponentConfigLoader;

public class LoadConfUtil {

    public static void loadPaasConf(Map<String, String> conf) {
        Properties p = new Properties();
        p.setProperty(SmcConstant.PAAS_AUTH_URL, conf.get(SmcConstant.PAAS_AUTH_URL));
        p.setProperty(SmcConstant.PAAS_AUTH_PID, conf.get(SmcConstant.PAAS_AUTH_PID));
        p.setProperty(SmcConstant.PAAS_CCS_SERVICEID, conf.get(SmcConstant.PAAS_CCS_SERVICEID));
        p.setProperty(SmcConstant.PAAS_CCS_SERVICEPASSWORD,
                conf.get(SmcConstant.PAAS_CCS_SERVICEPASSWORD));
        ComponentConfigLoader.loadPaaSConf(p);
    }

}
