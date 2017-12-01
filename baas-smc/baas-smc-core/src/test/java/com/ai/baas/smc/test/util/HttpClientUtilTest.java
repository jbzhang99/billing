package com.ai.baas.smc.test.util;

import org.apache.http.HttpResponse;

import com.ai.baas.smc.util.HttpClientUtil;

public class HttpClientUtilTest {
    public static void main(String[] args) {

        String data = "{\"jSBsn\":\"JSBIUGZT201605mayt0624\",\"message\":\"BIU\\u0003BIU-GZT-detail\\u0003GZT201605mayt0624\\u0003BIU\\u0003123456\\u0003BIU-GZT-detail\\u0001GZT201605mayt0624\\u00013226\\u00013181\\u0001 413026198902162412彭作亮20160503\\u0001彭作亮\\u0001413026198902162412\\u0001一致\\u0001yxzxweb\\u000120160503140835\\u0001gzt\\u000110003\\u00011.5\"}";
        String address = "http://10.1.130.84:10771/baasrtm/dataService/JsResource";
        System.out.println("data = " + data);
        HttpResponse s1 = HttpClientUtil.send(address, data);
        System.out.println(s1.getStatusLine());

    }
}
