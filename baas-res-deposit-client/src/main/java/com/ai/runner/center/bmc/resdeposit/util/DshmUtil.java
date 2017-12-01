//package com.ai.runner.center.bmc.resdeposit.util;
//
//import java.io.IOException;
//import java.util.Properties;
//
//import com.ai.baas.dshm.client.interfaces.IDshmClient;
//import com.ai.runner.center.bmc.resdeposit.constants.Constants;
//import com.ai.runner.center.dshm.api.dshmservice.interfaces.IdshmreadSV;
//
//public class DshmUtil {
//    private static IDshmClient client;
//    
//    private static Properties prop;
//
//    public static IDshmClient getDshmread() {
//        if(prop == null){
//            prop = new Properties();
//            try {
//                prop.load(DshmUtil.class.getClassLoader().getResourceAsStream("context/config.properties"));
//            } catch (IOException e) {
//                e.printStackTrace();
//                LoggerUtil.log.error(e);
//            }
//        }
//        try {
//            client = (IdshmreadSV) ServiceRegiter.registerService(prop.getProperty("dshm.ip"), prop.getProperty("dshm.port"),
//                    Constants.ShmServiceCode.SHM_SERVICE_CODE);
//        } catch (Exception e) {
//            e.printStackTrace();
//            LoggerUtil.log.error(e);
//        }
//        return client;
//    }
//}
