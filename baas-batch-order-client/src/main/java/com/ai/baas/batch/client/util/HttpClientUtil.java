package com.ai.baas.batch.client.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.ai.opt.base.exception.SystemException;

public class HttpClientUtil {
    private HttpClientUtil() {
    }

    private static final Logger LOG = LogManager.getLogger(HttpClientUtil.class);

    public static HttpResponse sendPostRequest(String url, String data, Map<String, String> header)
            throws SystemException {
        HttpClient httpclient = new DefaultHttpClient();
        try {
            HttpPost httpPost = new HttpPost(new URL(url).toURI());
            if (header != null) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }
            StringEntity dataEntity = new StringEntity(data, ContentType.APPLICATION_JSON);
            httpPost.setEntity(dataEntity);
            return httpclient.execute(httpPost);
        } catch (MalformedURLException e) {
            throw new SystemException(e);
        } catch (URISyntaxException e) {
            throw new SystemException(e);
        } catch (ClientProtocolException e) {
            throw new SystemException(e);
        } catch (IOException e) {
            throw new SystemException(e);
        }
    }

    public static HttpResponse send(String address, String param) {
        LOG.info("restful address : " + address);
        LOG.info("param : " + param);
        HttpResponse result = null;
        result = HttpClientUtil.sendPostRequest(address, param, null);
        LOG.info("result : " + result);
        return result;
    }
}
