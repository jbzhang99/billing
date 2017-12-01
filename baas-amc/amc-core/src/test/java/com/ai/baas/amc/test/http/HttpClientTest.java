package com.ai.baas.amc.test.http;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.ai.opt.sdk.dubbo.util.HttpClientUtil;
import com.alibaba.fastjson.JSON;

public class HttpClientTest {    
    @Test
    public void testSendPostHelloParam(){
        String url="http://10.1.130.84:10883/adapter-url/orders";
        Map<String,String> parameters=new HashMap<String,String>();
        parameters.put("name", "worldparam");
        String data=JSON.toJSONString(parameters);
        String json=HttpClientUtil.sendPost(url, data);
        System.out.println("testSendPostClient="+json);
    }
    
    @Test
    public void testSendGet(){
        String url="http://10.1.130.84:10883/adapter-url/service_instance/cost";
        Map<String,String> parameters=new HashMap<String,String>();
        parameters.put("name", "worldparam");
        String json=HttpClientUtil.sendGet(url, parameters);
        System.out.println("testSendPostClient="+json);
    }
}
