package com.ai.baas.smc.test.ccs;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.ai.opt.sdk.components.ccs.CCSClientFactory;
import com.ai.opt.sdk.constants.SDKConstants;
import com.ai.paas.ipaas.ccs.IConfigClient;
import com.ai.paas.ipaas.ccs.constants.ConfigException;

public class ConfigCenterClientTest {

    private IConfigClient client;

    @Before
    public void initData() {
        this.client = CCSClientFactory.getDefaultConfigClient();
    }

    // @Test
    public void testGetConfig() throws Exception {
        client.add("/test", "test");
        assertEquals("test", client.get("/test"));
        System.out.println("aaaaaa");
    }

    @Test
    public void addMcsConfig() throws ConfigException {
        // 缓存服务主机别名
        String aCluster = "MCS011";
        String bCluster = "MCS002";
        // 缓存空间

        String cachesnsConfig = " { ";
        cachesnsConfig = cachesnsConfig.concat(" \"com.ai.baas.smc.cache.sysparam\":\"" + aCluster
                + "\" ");
        cachesnsConfig = cachesnsConfig.concat(" ,\"com.ai.topology.calculate.common\":\""
                + aCluster + "\" ");
        cachesnsConfig = cachesnsConfig.concat(" ,\"com.ai.topology.bill.cache\":\"" + aCluster
                + "\" ");
        cachesnsConfig = cachesnsConfig.concat(" , \"com.ai.baas.smc.cache.billstyle\":\""
                + aCluster + "\" ");
        cachesnsConfig = cachesnsConfig.concat(" , \"success_record\":\"" + aCluster + "\" ");
        cachesnsConfig = cachesnsConfig.concat(" , \"com.ai.runner.center.dshm.cache.calparam\":\""
                + bCluster + "\" ");
        cachesnsConfig = cachesnsConfig.concat(" , \"com.ai.baas.smc.cache.check.count\":\""
                + aCluster + "\" ");
        cachesnsConfig = cachesnsConfig.concat(" , \"failed_record\":\"" + aCluster + "\" ");
        cachesnsConfig = cachesnsConfig.concat(" , \"stl_obj_stat\":\"" + aCluster + "\" ");
        cachesnsConfig = cachesnsConfig.concat(" , \"stats_times\":\"" + aCluster + "\" ");
        cachesnsConfig = cachesnsConfig.concat(" , \"stats_times_count\":\"" + aCluster + "\" ");
        cachesnsConfig = cachesnsConfig.concat(" , \"com.ai.baas.smc.cache.element\":\"" + aCluster
                + "\" ");
        cachesnsConfig = cachesnsConfig
                .concat(" , \"com.ai.baas.smc.cache.ObjectToElementCache\":\"" + aCluster + "\" ");
        cachesnsConfig = cachesnsConfig
                .concat(" , \"com.ai.baas.smc.cache.ObjectToPolicyCache\":\"" + aCluster + "\" ");
        cachesnsConfig = cachesnsConfig
                .concat(" , \"com.ai.baas.smc.cache.PolicyToElementCache\":\"" + aCluster + "\" ");
        cachesnsConfig = cachesnsConfig
                .concat(" , \"com.ai.baas.smc.cache.StlElementAttrCache\":\"" + aCluster + "\" ");
        cachesnsConfig = cachesnsConfig.concat(" , \"com.ai.baas.smc.cache.policy\":\"" + aCluster
                + "\" ");
        cachesnsConfig = cachesnsConfig.concat("  } ");

        // 缓存空间配置
        if (!client.exists(SDKConstants.PAAS_CACHENS_MCS_MAPPED_PATH))
            client.add(SDKConstants.PAAS_CACHENS_MCS_MAPPED_PATH, cachesnsConfig);
        else {
            client.modify(SDKConstants.PAAS_CACHENS_MCS_MAPPED_PATH, cachesnsConfig);
        }
        System.out.println(client.get(SDKConstants.PAAS_CACHENS_MCS_MAPPED_PATH));
    }

    @Test
    public void readMcsConfig() throws ConfigException {

        String cachesns = client.get(SDKConstants.PAAS_CACHENS_MCS_MAPPED_PATH);

        System.out.println("cachesns:" + cachesns);

    }

    /**
     * db-conf配置
     * 
     * @throws ConfigException
     */
    @Test
    public void addDbConfInfo() throws ConfigException {
        System.out.println("DBConf config ... start");
        StringBuilder sb = new StringBuilder();

         sb.append("{																																																				");
         sb.append("		\"dev_baas_smc1\":                                                                                   ");
         sb.append("		{                                                                                                     ");
         sb.append("			\"driverClassName\":\"com.mysql.jdbc.Driver\",                                                          ");
         sb.append("			\"jdbcUrl\":\"jdbc:mysql://10.1.235.245:31306/dev_baas_smc1?useUnicode=true&characterEncoding=UTF-8\",   ");
         sb.append("			\"username\":\"smcusr01\",                                                                         ");
         sb.append("			\"password\":\"smcusr01_123\",                                                                         ");
         sb.append("			\"autoCommit\":\"true\",                                                                                ");
         sb.append("			\"connectionTimeout\":\"30000\",                                                                        ");
         sb.append("			\"idleTimeout\":\"600000\",                                                                             ");
         sb.append("			\"maxLifetime\":\"1800000\",                                                                            ");
         sb.append("			\"maximumPoolSize\":\"10\"                                                                              ");
         sb.append("		}                                                                                                     ");
         sb.append("}                                                                                                        ");

        // 集成环境
//        sb.append("{                                                                                                                                                                                                                ");
//        sb.append("     \"dev_baas_smc1\":                                                                                   ");
//        sb.append("     {                                                                                                     ");
//        sb.append("         \"driverClassName\":\"com.mysql.jdbc.Driver\",                                                          ");
//        sb.append("         \"jdbcUrl\":\"jdbc:mysql://10.1.130.161:34306/tst_baas_smc1?useUnicode=true&characterEncoding=UTF-8\",   ");
//        sb.append("         \"username\":\"smcusr01\",                                                                         ");
//        sb.append("         \"password\":\"smcusr01_123\",                                                                         ");
//        sb.append("         \"autoCommit\":\"true\",                                                                                ");
//        sb.append("         \"connectionTimeout\":\"30000\",                                                                        ");
//        sb.append("         \"idleTimeout\":\"600000\",                                                                             ");
//        sb.append("         \"maxLifetime\":\"1800000\",                                                                            ");
//        sb.append("         \"maximumPoolSize\":\"10\"                                                                              ");
//        sb.append("     }                                                                                                     ");
//        sb.append("}                                                                                                        ");

        if (!client.exists(SDKConstants.DB_CONF_PATH)) {
            client.add(SDKConstants.DB_CONF_PATH, sb.toString());
        } else {
            client.modify(SDKConstants.DB_CONF_PATH, sb.toString());
        }

        System.out.println("DBConf config ... end");
    }

    @Test
    public void addServiceIdPwdMap() throws ConfigException {
        String cachesnsConfig = "{\"MCS011\":\"" + "123456" + "\",\"MCS002\":\"" + "123456" + "\"}";

        // paas serviceid password 映射配置
        if (!client.exists(SDKConstants.PAAS_SERVICE_PWD_MAPPED_PATH))
            client.add(SDKConstants.PAAS_SERVICE_PWD_MAPPED_PATH, cachesnsConfig);
        else {
            client.modify(SDKConstants.PAAS_SERVICE_PWD_MAPPED_PATH, cachesnsConfig);
        }
    }

}