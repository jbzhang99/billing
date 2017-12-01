package com.ai.opt.sys.test.rest;

import com.ai.opt.sys.api.citicrestcommon.impl.CiticRestReqWrapperSVImpl;
import com.ai.opt.sys.api.citicrestcommon.interfaces.ICiticRestReqWrapperSV;
import com.ai.opt.sys.api.citicrestcommon.param.OrgQueryResponse;
import com.ai.opt.sys.api.citicrestcommon.param.OrgQueryVo;
import com.ai.opt.sys.api.citicrestcommon.param.UserInfoQueryVo;
import com.ai.opt.sys.api.citicrestcommon.param.UserQueryResponse;
import com.alibaba.fastjson.JSON;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyongxin on 16/7/15.
 */
public class CiticTest {

    @Test
    public void test(){
        ICiticRestReqWrapperSV iCiticRestReqWrapperSV = new CiticRestReqWrapperSVImpl();
        OrgQueryVo query = new OrgQueryVo();
        query.setTenantId("citic");
        query.setSelectType("3");
        query.setSelectId("2");
        OrgQueryResponse orgQueryResponse = iCiticRestReqWrapperSV.searchOrg(query);
        System.out.println(JSON.toJSONString(orgQueryResponse));
    }

    @Test
    public void test2(){
        ICiticRestReqWrapperSV iCiticRestReqWrapperSV = new CiticRestReqWrapperSVImpl();
        UserInfoQueryVo query = new UserInfoQueryVo();
        query.setTenantId("citic");
        query.setSelectType("2");
        query.setSelectId(null);
        UserQueryResponse queryResponse = iCiticRestReqWrapperSV.searchUser(query);
        System.out.println(JSON.toJSONString(queryResponse));
    }


    @Test
    public void test1(){
        try {
            String url = "http://10.6.186.30:8080/servermgt/orders.do";
            Map<String, String> param = new HashMap<>();
            param.put("start_time", "2015-06-29 08:00");
            param.put("end_time", "2017-06-30 08:00");
            param.put("state", "已提交");
            param.put("type", "1");
            StringBuffer buffer = new StringBuffer();
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(new URL(url).toURI());

            StringEntity dataEntity = new StringEntity(JSON.toJSONString(param), ContentType.APPLICATION_JSON);
            httpPost.setEntity(dataEntity);
            CloseableHttpResponse response = httpclient.execute(httpPost);

            //释放资源
            if(response != null ){
                response.close();
            }
            if(httpclient != null ){
                httpclient.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
