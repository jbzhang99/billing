package com.ai.opt.sys.test.ccs;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.ai.opt.sdk.components.ccs.CCSClientFactory;
import com.ai.opt.sdk.constants.SDKConstants;
import com.ai.paas.ipaas.ccs.IConfigClient;
import com.ai.paas.ipaas.ccs.constants.ConfigException;

public class IConfigCenterClientTest {

    private IConfigClient client;

    @Before
    public void initData() {
        this.client = CCSClientFactory.getDefaultConfigClient();
    }

    @Ignore
    @Test
    public void testGetConfig() throws Exception {
        client.add("/test", "test");
        assertEquals("test", client.get("/test"));
        System.out.println("aaaaaa");
    }

    //@Ignore
    @Test
    public void addMcsConfig()throws Exception {
        // 缓存服务主机
        String sysRedisHosts = "MCS010";
        // 缓存空间
        String cachesnsConfig = "{\"com.ai.opt.sys.cache.gnsubject\":\"" + sysRedisHosts
                + "\",\"com.ai.opt.sys.cache.gnsubjectfund\":\"" + sysRedisHosts + "\"}";

        StringBuilder sb = new StringBuilder();

        sb.append("{																																																				");
        sb.append("		\"MCS010\":             ");
        sb.append("		{                                                                                                     ");
        //sb.append("			\"mcs.host\":\"127.0.0.1:6379\",   ");
        sb.append("			\"mcs.host\":\"10.1.130.84:16379\",   ");
        sb.append("			\"mcs.maxtotal\":\"200\",   ");
        sb.append("			\"mcs.maxIdle\":\"10\",     ");
        sb.append("			\"mcs.minIdle\":\"5\",   ");
        sb.append("			\"mcs.testOnBorrow\":\"true\",    ");
        sb.append("			\"mcs.password\":\"123456\"                                                                              ");
        sb.append("		}                                                                                                     ");
        sb.append("}              ");


        // 缓存空间配置
        if (!client.exists(SDKConstants.PAAS_CACHENS_MCS_MAPPED_PATH))
            client.add(SDKConstants.PAAS_CACHENS_MCS_MAPPED_PATH,
                    cachesnsConfig);
        else {
            client.modify(SDKConstants.PAAS_CACHENS_MCS_MAPPED_PATH,
                    cachesnsConfig);
        }

        // redis配置
        if (!client.exists(SDKConstants.SDK_MODE_PAAS_MCS_REDIS_MAPPED_PATH))
            client.add(SDKConstants.SDK_MODE_PAAS_MCS_REDIS_MAPPED_PATH,
                    sb.toString());
        else {
            client.modify(SDKConstants.SDK_MODE_PAAS_MCS_REDIS_MAPPED_PATH,
                    sb.toString());
        }
    }
    @Ignore
    @Test
    public void readMcsConfig()throws Exception {
    	
    	String cachesns=client.get(SDKConstants.PAAS_CACHENS_MCS_MAPPED_PATH);
    	String redisconf=client.get(SDKConstants.PAAS_CACHENS_MCS_MAPPED_PATH);
    	
    	System.out.println("cachesns:"+cachesns);
    	System.out.println("redisconf:"+redisconf);
    	
    }

    

    /**
     * DBS配置
     */
     @Test
    public void addDbConfInfo() throws Exception {
        System.out.println("DBConf config ... start");
        StringBuilder sb = new StringBuilder();

        sb.append("{																																																				");
        sb.append("		\"opt-sys-db\":                                                                                   ");
        sb.append("		{                                                                                                     ");
        sb.append("			\"driverClassName\":\"com.mysql.jdbc.Driver\",                                                          ");
        sb.append("			\"jdbcUrl\":\"jdbc:mysql://10.1.235.245:31306/dev_baas_sys1?useUnicode=true&characterEncoding=UTF-8\",   ");
        sb.append("			\"username\":\"sysusr01\",                                                                         ");
        sb.append("			\"password\":\"sysusr01_123\",                                                                         ");

//        sb.append("			\"jdbcUrl\":\"jdbc:mysql://127.0.0.1:3306/system?useUnicode=true&characterEncoding=UTF-8\",   ");
//        sb.append("			\"username\":\"root\",                                                                         ");
//        sb.append("			\"password\":\"123456\",                                                                         ");

        
        sb.append("			\"autoCommit\":\"true\",                                                                                ");
        sb.append("			\"connectionTimeout\":\"30000\",                                                                        ");
        sb.append("			\"idleTimeout\":\"600000\",                                                                             ");
        sb.append("			\"maxLifetime\":\"1800000\",                                                                            ");
        sb.append("			\"maximumPoolSize\":\"10\"                                                                              ");
        sb.append("		}                                                                                                     ");
        sb.append("}                                                                                                        ");

        if (!client.exists(SDKConstants.DB_CONF_PATH)) {
            client.add(SDKConstants.DB_CONF_PATH, sb.toString());
        } else {
            client.modify(SDKConstants.DB_CONF_PATH, sb.toString());
        }

        System.out.println("DBConf config ... end");
    }
     @Test
     public void addServiceIdPwdMap() throws ConfigException {
     	String cachesnsConfig = "{\"MCS010\":\"" + "123456"     
//     			+ "\",\"MCS002\":\"" + "123456"
//     			+ "\",\"MDS001\":\"" + "123456"
//     			+ "\",\"DSS001\":\"" + "123456"
     			+ "\"}";
         
         // paas serviceid password 映射配置
         if (!client.exists(SDKConstants.PAAS_SERVICE_PWD_MAPPED_PATH))
             client.add(SDKConstants.PAAS_SERVICE_PWD_MAPPED_PATH,
                     cachesnsConfig);
         else {
             client.modify(SDKConstants.PAAS_SERVICE_PWD_MAPPED_PATH,
                     cachesnsConfig);
         }
     }
}