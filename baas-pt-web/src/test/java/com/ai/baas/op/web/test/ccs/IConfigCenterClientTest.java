package com.ai.baas.op.web.test.ccs;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.ai.baas.pt.web.constants.BaaSPTConstants;
import com.ai.baas.pt.web.constants.HomePageConstants.SendEmail;
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
    public void addMcsConfig() throws ConfigException {
        // 缓存服务主机
        String baasptwebRedisHost = "MCS006";
        // 缓存空间
        String cachesnsConfig = "{\"com.ai.opt.uni.session.sessionclient.baasptweb\":\"" + baasptwebRedisHost 
        		+ "\",\""+SendEmail.CACHE_NAMESPACE+"\":\"" + baasptwebRedisHost
        		+ "\"}";
        
        // 缓存空间配置
        if (!client.exists(SDKConstants.PAAS_CACHENS_MCS_MAPPED_PATH))
            client.add(SDKConstants.PAAS_CACHENS_MCS_MAPPED_PATH, cachesnsConfig);
        else {
            client.modify(SDKConstants.PAAS_CACHENS_MCS_MAPPED_PATH, cachesnsConfig);
        }
    }
    
    @Test
    public void addServiceIdPwdMap() throws ConfigException {
        String cachesnsConfig = "{\"MCS006\":\"" + "123456"     
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
     public void addBaaSOPUrlConfig() throws ConfigException{
    	 System.out.println("url config ... start");
    	 String indexUrl = "http://10.1.235.245:14105/baas-op";
    	 if (!client.exists(BaaSPTConstants.URLConstant.BAAS_OP_INDEX_URL_KEY)) {
             client.add(BaaSPTConstants.URLConstant.BAAS_OP_INDEX_URL_KEY, indexUrl);
         } else {
             client.modify(BaaSPTConstants.URLConstant.BAAS_OP_INDEX_URL_KEY, indexUrl);
         }

    	 System.out.println("url config ... end");
     }
     
     @Test
     public void addSendEmailConfig() throws ConfigException{
    	 System.out.println("addSendEmailConfig ... start");
    	 String EMAIL_IP_SEND_MAXTIMES="4";
    	 String EMAIL_IP_SEND_OVERTIME="300";
    	 String email = "zhaojing7@asiainfo.com";
    	 
    	 if (!client.exists(SendEmail.IP_SEND_MAX_NO_KEY)) {
             client.add(SendEmail.IP_SEND_MAX_NO_KEY, EMAIL_IP_SEND_MAXTIMES);
         } else {
             client.modify(SendEmail.IP_SEND_MAX_NO_KEY, EMAIL_IP_SEND_MAXTIMES);
         }
    	 
    	 if (!client.exists(SendEmail.IP_SEND_OVERTIME_KEY)) {
             client.add(SendEmail.IP_SEND_OVERTIME_KEY, EMAIL_IP_SEND_OVERTIME);
         } else {
             client.modify(SendEmail.IP_SEND_OVERTIME_KEY, EMAIL_IP_SEND_OVERTIME);
         }
    	 
    	 if (!client.exists(SendEmail.RECEIVE_EMAIL_KEY)) {
             client.add(SendEmail.RECEIVE_EMAIL_KEY, email);
         } else {
             client.modify(SendEmail.RECEIVE_EMAIL_KEY, email);
         }
    	 System.out.println("addSendEmailConfig ... end");
     }

}