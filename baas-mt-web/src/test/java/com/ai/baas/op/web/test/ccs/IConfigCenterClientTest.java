package com.ai.baas.op.web.test.ccs;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.ai.baas.mt.web.constants.BaaSMTConstants;
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

//    @Ignore
    @Test
    public void addMcsConfig() throws ConfigException {
        // 缓存服务主机
        String baasmtwebRedisHost = "MCS008";
        // 缓存空间
        String cachesnsConfig = "{\"com.ai.opt.uni.session.sessionclient.baasmtweb\":\"" + baasmtwebRedisHost + "\"}";
        
        

        // 缓存空间配置
        if (!client.exists(SDKConstants.PAAS_CACHENS_MCS_MAPPED_PATH))
            client.add(SDKConstants.PAAS_CACHENS_MCS_MAPPED_PATH,
                    cachesnsConfig);
        else {
            client.modify(SDKConstants.PAAS_CACHENS_MCS_MAPPED_PATH,
                    cachesnsConfig);
        }
        

        StringBuilder bu=new StringBuilder();
        bu.append("{								");
        bu.append("  \"MCS008\":                     ");
        bu.append("  {                                      ");
        bu.append("		  \"mcs.host\":\"10.1.130.84:16379\",     ");
        bu.append("	  	  \"mcs.maxtotal\":\"200\",            ");
        bu.append("		  \"mcs.maxIdle\":\"10\",              ");
        bu.append("		  \"mcs.minIdle\":\"5\",               ");
        bu.append("		  \"mcs.testOnBorrow\":\"true\",       ");
        bu.append("		  \"mcs.password\":\"\"          ");
        bu.append("  }                                     ");
        bu.append("}                                        ");
        
        
        
        // 缓存服务主机和密码设置
        if (!client.exists(
                SDKConstants.SDK_MODE_PAAS_MCS_REDIS_MAPPED_PATH)) {
            client.add(
                    SDKConstants.SDK_MODE_PAAS_MCS_REDIS_MAPPED_PATH,
                    bu.toString());
        } else {
            client.modify(
                    SDKConstants.SDK_MODE_PAAS_MCS_REDIS_MAPPED_PATH,
                    bu.toString());
        }
    }
    
    @Test
    public void addServiceIdPwdMap() throws ConfigException {
    	String cachesnsConfig = "{\"MCS008\":\"" + "123456"     
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
    
    //@Ignore
    @Test
    public void readMcsConfig() throws ConfigException {
    	
    	String cachesns=client.get(SDKConstants.PAAS_CACHENS_MCS_MAPPED_PATH);
    	
    	System.out.println("cachesns:"+cachesns);
    	
    }

     
     @Test
     public void addBaaSPTUrlConfig() throws ConfigException{
    	 System.out.println("url config ... start");
    	 String indexUrl = "http://10.1.235.245:14101/baas-pt";
    	 if (!client.exists(BaaSMTConstants.URLConstant.BAAS_PT_INDEX_URL_KEY)) {
             client.add(BaaSMTConstants.URLConstant.BAAS_PT_INDEX_URL_KEY, indexUrl);
         } else {
             client.modify(BaaSMTConstants.URLConstant.BAAS_PT_INDEX_URL_KEY, indexUrl);
         }

    	 System.out.println("url config ... end");
     }

}