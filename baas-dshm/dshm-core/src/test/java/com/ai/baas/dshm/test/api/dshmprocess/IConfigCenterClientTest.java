//package com.ai.baas.dshm.test.api.dshmprocess;
//
//import static org.junit.Assert.assertEquals;
//
//import org.junit.Before;
//import org.junit.Ignore;
//import org.junit.Test;
//
//import com.ai.opt.sdk.configcenter.client.IConfigCenterClient;
//import com.ai.opt.sdk.configcenter.factory.ConfigCenterFactory;
//import com.ai.opt.sdk.constants.SDKConstants;
//
//public class IConfigCenterClientTest {
//
//    private IConfigCenterClient client;
//
//    @Before
//    public void initData() {
//        this.client = ConfigCenterFactory.getConfigCenterClient();
//    }
//
//    @Ignore
//    @Test
//    public void testGetConfig() throws Exception {
//        client.add("/test", "test");
//        assertEquals("test", client.get("/test"));
//        System.out.println("aaaaaa");
//    }
//
//    //@Ignore
//    @Test
//    public void addMcsConfig() {
//    	 // 缓存服务主机
//        String redisClusterA = "redisClusterA";
//        //String redisClusterB = "redisClusterB";
//        // 缓存空间
//        //String cachesnsConfig = "{\"com.ai.runner.center.dshm.cache.calparam\":\"" + redisClusterA
//        String cachesnsConfig = "{\"com.ai.baas.rtm.calparam\":\"" + redisClusterA 
//        		   +"\",\"com.ai.runner.center.dshm.cache.calparam\":\"" + redisClusterA
//        
//                + "\"}";       
//        System.out.println(cachesnsConfig);
//        StringBuilder bu=new StringBuilder();
//        bu.append("{																				");
//        bu.append("  \"redisClusterA\":                     ");
//        bu.append("  {                                      ");
//        bu.append("		  \"mcsHost\":\"10.1.130.158:16379\",     ");
//        bu.append("	  	\"mcsMaxtotal\":\"200\",            ");
//        bu.append("		  \"mcsMaxIdle\":\"10\",              ");
//        bu.append("		  \"mcsMinIdle\":\"5\",               ");
//        bu.append("		  \"mcsTestOnBorrow\":\"true\",       ");
//        bu.append("		  \"mcsPassword\":\"\"          ");
//        bu.append("  }                                     ");
//        bu.append("}                                        ");
//        
//        
//        
//        // 缓存服务主机和密码设置
//        if (!client.exists(
//                SDKConstants.PAAS_CACHE_REDIS_CLUSTER_MAPPED_PATH)) {
//            client.add(
//                    SDKConstants.PAAS_CACHE_REDIS_CLUSTER_MAPPED_PATH,
//                    bu.toString());
//        } else {
//            client.modify(
//                    SDKConstants.PAAS_CACHE_REDIS_CLUSTER_MAPPED_PATH,
//                    bu.toString());
//        }
//
//        // 缓存空间配置
//        if (!client.exists(SDKConstants.PAAS_CACHENS_MCS_MAPPED_PATH))
//            client.add(SDKConstants.PAAS_CACHENS_MCS_MAPPED_PATH,
//                    cachesnsConfig);
//        else {
//            client.modify(SDKConstants.PAAS_CACHENS_MCS_MAPPED_PATH,
//                    cachesnsConfig);
//        }
//    }
//    //@Ignore
//    @Test
//    public void readMcsConfig() {
//    	
//    	String cachesns=client.get(SDKConstants.PAAS_CACHENS_MCS_MAPPED_PATH);
//    	String redisconf=client.get(SDKConstants.PAAS_CACHE_REDIS_CLUSTER_MAPPED_PATH);
//    	
//    	System.out.println("cachesns:"+cachesns);
//    	System.out.println("redisconf:"+redisconf);
//    	
//    }
//
//    
//
//    /**
//     * DBS配置
//     */
//     @Test
//    public void addDbConfInfo() {
//        System.out.println("DBConf config ... start");
//        StringBuilder sb = new StringBuilder();
//
//        sb.append("{																																																				");
//        sb.append("		\"opt-uac-db\":                                                                                   ");
//        sb.append("		{                                                                                                     ");
//        sb.append("			\"driverClassName\":\"com.mysql.jdbc.Driver\",                                                          ");
//        sb.append("			\"jdbcUrl\":\"jdbc:mysql://10.1.228.222:39306/devbisdb1?useUnicode=true&characterEncoding=UTF-8\",   ");
//        sb.append("			\"username\":\"devbisusr1\",                                                                         ");
//        sb.append("			\"password\":\"devbisusr1\",                                                                         ");
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