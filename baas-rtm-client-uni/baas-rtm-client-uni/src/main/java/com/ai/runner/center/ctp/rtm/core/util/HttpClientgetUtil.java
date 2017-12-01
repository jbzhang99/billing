package com.ai.runner.center.ctp.rtm.core.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

import com.ai.runner.base.exception.SystemException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

public final class HttpClientgetUtil {
	
	private static final Logger logger=Logger.getLogger(HttpClientgetUtil.class);
	
	private HttpClientgetUtil(){}
	
	  public static String sendPostRequest(String url, String data, Map<String, String> header)
	            throws Exception {
	        CloseableHttpClient httpclient = HttpClients.createDefault();
	        HttpPost httpPost = new HttpPost(new URL(url).toURI());
	        for (Map.Entry<String, String> entry : header.entrySet()) {
	            httpPost.setHeader(entry.getKey(), entry.getValue());
	        }
	        StringEntity dataEntity = new StringEntity(data, ContentType.APPLICATION_JSON);
	        httpPost.setEntity(dataEntity);
	        CloseableHttpResponse response =null;
	        BufferedReader reader = null;
	        if(null!=httpclient){
	            response=   httpclient.execute(httpPost);
	        }
	        try {
	            if (null != response && response.getStatusLine().getStatusCode() == 200) {
	                HttpEntity entity = response.getEntity();
	                if (null == entity) {
	                    throw new SystemException("error code entity is null "
	                            + response.getStatusLine().getStatusCode() + ":"
	                            + response.getStatusLine().getReasonPhrase());
	                }
	             reader = new BufferedReader(new InputStreamReader(
	                        entity.getContent(),"UTF-8"));
	                StringBuilder buffer = new StringBuilder();
	                String tempStr;
	                while ((tempStr = reader.readLine()) != null) {
	                    buffer.append(tempStr);
	                }

	                return buffer.toString();

	            } else  if(null != response && response.getStatusLine().getStatusCode() != 200){
	                throw new SystemException("error code " + response.getStatusLine().getStatusCode()
	                        + ":" + response.getStatusLine().getReasonPhrase());
	            }
	            else{
	                throw new SystemException("调用接口：url="+url+"响应为空" );
	            }
	        } finally {
	            if (null != response) {
	                response.close();
	            }
	            if (null != httpclient) {
	                httpclient.close();
	            }
	            if(null!=reader){
	                reader.close();
	            }
	        }
	  }
	  
    public static String sendPostRequest(String url, String data) throws IOException, URISyntaxException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(new URL(url).toURI());
        StringEntity dataEntity = new StringEntity(data, ContentType.APPLICATION_JSON);
        httpPost.setEntity(dataEntity);
        CloseableHttpResponse response = httpclient.execute(httpPost);
        InputStreamReader isr=null;
        BufferedReader reader=null;
        try {
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                isr = new InputStreamReader(entity.getContent());
                reader = new BufferedReader(isr);
                StringBuilder builder = new StringBuilder();
                String tempStr;
                while ((tempStr = reader.readLine()) != null){
                    builder.append(tempStr);
                }
                return builder.toString();
            } else {
                throw new RuntimeException("error code " + response.getStatusLine().getStatusCode());
            }
        } finally {
        	try {
				reader.close();
				isr.close();
			} catch (Exception e) {
				// TODO: handle exception
				logger.info("HttpClientUtil  close response error " + e.getMessage(),e);
			}
            try {
				response.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.info("HttpClientUtil  close response error " + e.getMessage(),e);
			}
            try {
				httpclient.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.info("HttpClientUtil  close httpclient error " + e.getMessage(),e);
			}
        }
    }
    public static String sendPostNoDataRequest(String url) throws IOException, URISyntaxException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(new URL(url).toURI());
      //  StringEntity dataEntity = new StringEntity( ContentType.APPLICATION_JSON);
      //  httpPost.setEntity(dataEntity);
        CloseableHttpResponse response = httpclient.execute(httpPost);
        
        InputStreamReader isr = null;
        BufferedReader reader = null;
        try {
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                isr = new InputStreamReader(entity.getContent());
                reader = new BufferedReader(isr);
                StringBuilder builder = new StringBuilder();
                String tempStr;
                while ((tempStr = reader.readLine()) != null){
                    builder.append(tempStr);
                }
                return builder.toString();
            } else {
                throw new RuntimeException("error code " + response.getStatusLine().getStatusCode());
            }
        } finally {
        	try {
				reader.close();
				isr.close();
			} catch (Exception e) {
				// TODO: handle exception
				logger.info("HttpClientUtil  close response error " + e.getMessage(),e);
			}
            try {
				response.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.info("HttpClientUtil  close response error " + e.getMessage(),e);
			}
            try {
				httpclient.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.info("HttpClientUtil  close httpclient error " + e.getMessage(),e);
			}
        }
    }
    public static String executeGet(String url) throws IOException, URISyntaxException {  
	       BufferedReader in = null;  
	 
	       String content = null;  
	       try {
	    	   try {
				
			
	    	   CloseableHttpClient client = HttpClients.createDefault();
	    
	            HttpGet request = new HttpGet(new URL(url).toURI());  
	            HttpResponse response = client.execute(request);  
	  
	            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));  
	            StringBuffer sb = new StringBuffer("");  
	            String line = "";   
	            while ((line = in.readLine()) != null) {  
	                sb.append(line );  
	            }  
	              
	            content = sb.toString(); 
	            logger.info(content);
	    	   } catch (Exception e) {
	    		   e.printStackTrace(); 		
	    	   }
	            return content;
	            
	        } finally {  
	            
	                try {  
	                    in.close();// 最后要关闭BufferedReader  
	                } catch (Exception e) {  
	                    e.printStackTrace(); 
	                    logger.info("请求密码失败");
	                }  
	             
	             
	        }  
	    }  
	  

}
