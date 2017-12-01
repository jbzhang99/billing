package com.ai.runner.center.bmc.resdeposit.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropUtil {
    private static Map<String, Properties> propMap = new HashMap<>();
    /**
     * 通过路径获得Properties对象
     */
    public static Properties getProp(String path){
        Properties prop = propMap.get(path);
        if(prop == null){
            prop = new Properties();
            try {
                prop.load(PropUtil.class.getClassLoader().getResourceAsStream(path));
            } catch (IOException e) {
                return null;
            }
            propMap.put(path, prop);
        }
        return prop;
    }
}
