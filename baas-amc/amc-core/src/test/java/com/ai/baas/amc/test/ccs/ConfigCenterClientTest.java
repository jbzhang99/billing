//package com.ai.baas.amc.test.ccs;
//
//import static org.junit.Assert.assertEquals;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import com.ai.opt.sdk.constants.SDKConstants;
//
//public class ConfigCenterClientTest {
//
//    private IConfigCenterClient client;
//
//    @Before
//    public void initData() {
//        this.client = ConfigCenterFactory.getConfigCenterClient();
//    }
//
//    // @Test
//    public void testGetConfig() throws Exception {
//        client.add("/test", "test");
//        assertEquals("test", client.get("/test"));
//        System.out.println("aaaaaa");
//    }
//
//    @Test
//    public void addMcsConfig() {
//        // 缓存服务主机别名
//        String baseConfigRedisCluster = "baseConfigRedisCluster";
//        // 缓存空间
//        String cachesnsConfig = "{\"com.ai.runner.center.dshm.cache.calparam\":\"" + baseConfigRedisCluster
//                + "\"}";
//
//        StringBuilder bu = new StringBuilder();
//        bu.append("{                                                                                ");
//        bu.append("  \"baseConfigRedisCluster\":                     ");
//        bu.append("  {                                      ");
//        bu.append("       \"mcsHost\":\"10.1.130.84:16379\",     ");
//        bu.append("     \"mcsMaxtotal\":\"200\",            ");
//        bu.append("       \"mcsMaxIdle\":\"10\",              ");
//        bu.append("       \"mcsMinIdle\":\"5\",               ");
//        bu.append("       \"mcsTestOnBorrow\":\"true\",       ");
//        bu.append("       \"mcsPassword\":\"\"          ");
////        bu.append("  },                                     ");
////        bu.append("  \"redisClusterB\":                     ");
////        bu.append("  {                                      ");
////        bu.append("      \"mcsHost\":\"localhost:6379\",    ");
////        bu.append("     \"mcsMaxtotal\":\"200\",            ");
////        bu.append("       \"mcsMaxIdle\":\"10\",              ");
////        bu.append("       \"mcsMinIdle\":\"5\",               ");
////        bu.append("       \"mcsTestOnBorrow\":\"true\",       ");
////        bu.append("       \"mcsPassword\":\"123456\"          ");
//        bu.append("  }                                      ");
//        bu.append("}                                        ");
//
//        // 缓存服务主机和密码设置
//        if (!client.exists(SDKConstants.PAAS_CACHE_REDIS_CLUSTER_MAPPED_PATH)) {
//            client.add(SDKConstants.PAAS_CACHE_REDIS_CLUSTER_MAPPED_PATH, bu.toString());
//        } else {
//            client.modify(SDKConstants.PAAS_CACHE_REDIS_CLUSTER_MAPPED_PATH, bu.toString());
//        }
//
//        // 缓存空间配置
//        if (!client.exists(SDKConstants.PAAS_CACHENS_MCS_MAPPED_PATH))
//            client.add(SDKConstants.PAAS_CACHENS_MCS_MAPPED_PATH, cachesnsConfig);
//        else {
//            client.modify(SDKConstants.PAAS_CACHENS_MCS_MAPPED_PATH, cachesnsConfig);
//        }
//    }
//
//    @Test
//    public void readMcsConfig() {
//
//        String cachesns = client.get(SDKConstants.PAAS_CACHENS_MCS_MAPPED_PATH);
//        String redisconf = client.get(SDKConstants.PAAS_CACHE_REDIS_CLUSTER_MAPPED_PATH);
//
//        System.out.println("cachesns:" + cachesns);
//        System.out.println("redisconf:" + redisconf);
//
//    }
//
//    /**
//     * db-conf配置
//     */
//    @Test
//    public void addDbConfInfo() {
//        System.out.println("DBConf config ... start");
//        StringBuilder sb = new StringBuilder();
//
//        sb.append("{																																																				");
//        sb.append("		\"baas-amc-db\":                                                                                   ");
//        sb.append("		{                                                                                                     ");
//        sb.append("			\"driverClassName\":\"com.mysql.jdbc.Driver\",                                                          ");
//        sb.append("			\"jdbcUrl\":\"jdbc:mysql://10.1.235.245:31306/dev_baas_amc1?useUnicode=true&characterEncoding=UTF-8\",   ");
//        sb.append("			\"username\":\"amcusr01\",                                                                         ");
//        sb.append("			\"password\":\"amcusr01_123\",                                                                         ");
//        sb.append("			\"autoCommit\":\"true\",                                                                                ");
//        sb.append("			\"connectionTimeout\":\"30000\",                                                                        ");
//        sb.append("			\"idleTimeout\":\"600000\",                                                                             ");
//        sb.append("			\"maxLifetime\":\"1800000\",                                                                            ");
//        sb.append("			\"maximumPoolSize\":\"10\"                                                                              ");
//        sb.append("		}                                                                                                     ");
//        sb.append("}                                                                                                        ");
//
//        if (!client.exists(SDKConstants.DB_CONF_PATH)) {
//            client.add(SDKConstants.DB_CONF_PATH, sb.toString());
//        } else {
//            client.modify(SDKConstants.DB_CONF_PATH, sb.toString());
//        }
//
//        System.out.println("DBConf config ... end");
//    }
//
//}