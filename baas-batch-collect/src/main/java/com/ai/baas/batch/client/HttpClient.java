package com.ai.baas.batch.client;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.ai.opt.sdk.dubbo.util.HttpClientUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class HttpClient {

    public static void main(String[] args){
//        String json = BatchStart.recvMsg();
//        JSONObject rootObject = (JSONObject)JSONObject.parse(json);
//        JSONArray orderArray = rootObject.getJSONArray("orders");
//        System.out.println("size:"+orderArray.size()); 
        
        
//        String instance_id = null;
//        BatchStart.recvRatio(instance_id);
        
//        Calendar c =   Calendar.getInstance();   
//        c.add(Calendar.DAY_OF_MONTH, -1);  
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
//        String mDateTime=formatter.format(c.getTime());  
//
//        String strDate = mDateTime.substring(0, 11);
//        String start_time = strDate+"00:00:00";
//        String end_time = strDate+"23:59:59";
//        System.err.println(start_time); 
//        System.err.println(end_time); 
        
//        String url = "http://10.6.186.30:8080/servermgt/orders.do";
////        String url = "http://service.citicdao.com/newservermgt/orders.do";
//        Map<String, String> param = new HashMap<>();
//        param.put("start_time", "2016-08-04 00:00:00");
//        param.put("end_time", "2016-08-04 23:59:59");
//        param.put("state", "4"); //订单状态 4 已完成
//        param.put("type", "3");
//        System.err.println(JSONObject.toJSONString(param));
//        String s = HttpClientUtil.sendPost(url, JSONObject.toJSONString(param));
//              
//        System.err.println("s:"+s); 
        
        
        
        
//        String s  = "{\"orders\":[{\"id\":\"0c743573-3f76-479b-9b90-9c5fcc499fd6\",\"state_id\":\"4\",\"approvInfo\":\"\",\"user_id\":\"be1f7c9c-1c55-429e-9dcf-48f66dbb06e2\",\"shopping_lists\":[{\"service_id\":\"5762107c6ae6ca04e14595b8\",\"parameters\":{\"engine\":\"MySQL\",\"engineVersion\":\"5.6\",\"dBInstanceClass\":\"rds.mysql.s2.large\",\"dBInstanceStorage\":\"10\",\"dBInstanceNetType\":\"Internet\",\"securityIPList\":\"0.0.0.0/0\",\"regionId\":\"cn-beijing\",\"zoneId\":\"cn-beijing-c\",\"payType\":\"Postpaid\",\"instanceNetworkType\":\"VPC\",\"vpcId\":\"vpc-jrwfspj5s\",\"vSwitchId\":\"vsw-9dlv1ldos\"},\"service_name\":\"云数据库RDS\",\"instances\":[{\"Instance_id\":\"f1549750-9ed9-4ab5-8b51-b53bf66c0732\",\"is_deleted\":\"0\",\"app_base_line\":\"1\",\"serviceId\":\"5762107c6ae6ca04e14595b8\",\"instanceStatusId\":\"2\",\"supplyInstanceId\":\"rm-2jd103cr86gb94a6x\",\"use_id\":\"2\"}],\"list_id\":\"a643f557-af92-4677-8291-dc03acbb9a5d\"}],\"state_name\":\"以完成\",\"last_oper_time\":\"2016-08-0410: 34: 52\"},{\"id\":\"35a57764-caea-49ac-9553-85a1d4831e0e\",\"state_id\":\"4\",\"approvInfo\":\"\",\"user_id\":\"be1f7c9c-1c55-429e-9dcf-48f66dbb06e2\",\"shopping_lists\":[{\"service_id\":\"57721e052fa45f06e1c013da\",\"parameters\":{\"ServiceCode\":\"ons\",\"StatusKey1\":\"enabled\",\"StatusValue1\":\"true\"},\"service_name\":\"消息队列\",\"instances\":[{\"Instance_id\":\"af9f8408-fa7a-4bf3-84ff-b224afc9d74a\",\"is_deleted\":\"0\",\"app_base_line\":\"1\",\"serviceId\":\"57721e052fa45f06e1c013da\",\"instanceStatusId\":\"2\",\"supplyInstanceId\":\"1596769687493741\",\"use_id\":\"2\"}],\"list_id\":\"e9c96fb2-26c6-434c-8808-f271df6cb45e\"}],\"state_name\":\"以完成\",\"last_oper_time\":\"2016-08-0410: 35: 34\"}]}";
//        String aaa = s.toLowerCase();
//        System.out.println(aaa); 
        
        
//        Calendar   c   =   Calendar.getInstance();   
//        System.err.println(c.toString());
//        c.add(Calendar.DAY_OF_MONTH, -1);  
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
//        String mDateTime=formatter.format(c.getTime());  
//        System.err.println(mDateTime.toString());
//        String strStart=mDateTime.substring(0, 16);//2007-10-29 09:30  
//        System.err.println(strStart.toString()); 
//        String strDate = mDateTime.substring(0, 11);
//        System.err.println("strDate:"+strDate);
//        String aaa = strDate+"00:00:00";
//        System.err.println(aaa); 
        
        String url = "http://service.citicdao.com/newservermgt/service_instance/cost.do";
        Map<String,String> param = new HashMap<>();
        JSONArray jsonA = new JSONArray();
        JSONObject json1 = new JSONObject();
        JSONObject json2 = new JSONObject();
        json1.put("instance_id","04420a4d-c4ad-4303-86b6-41a750cdc4ae");
        jsonA.add(json1);
//        json2.put("instance_id", "3b00ebbe-45e5-4bd7-b1e1-77c4e20142ef");
//        jsonA.add(json2);       
        param.put("instances_ids", JSON.toJSONString(jsonA));
        
        System.out.println(JSON.toJSONString(param));
        String s = HttpClientUtil.sendGet(url, param);
        
//        String url  = "http://10.248.4.3:20002/baas-bmc/citic/instanceChange";
//        String url = "http://10.248.4.3:20002/baas-bmc/pricemaking/queryPricemakingZX";
        
        
//        String url  = "http://10.1.130.84:10999/baas-batch-order/citic/instanceOrder";
        
//        String url = "http://10.248.4.11:20004/baas-batch-order/citic/instanceOrder";
////        Map<String,String> param = new HashMap<>();
////        param.put("trade_seq", "111112222");
////        param.put("tenant_id", "CITIC");
////        param.put("instance_id", "0662d56f-572d-4d85-9947-dbe2cbdb58b3");
////        param.put("change_code", "REMOVE");
//        String param = "{\"order\":{\"id\":\"e2cb8871-dc37-47de-bd92-64f428719aa1\",\"user_name\":\"te2\",\"state_id\":\"4\",\"old_order_id\":\"\",\"create_time\":\"2016-10-09 14:39:19\",\"approvInfo\":\"中信云测试_审批通过\",\"user_id\":\"d218aee0-ad4b-4176-acda-49310ae8ec91\",\"shopping_lists\":[{\"service_id\":\"57721cb62fa45f06e1c013d6\",\"parameters\":{\"cost_center\":\"8270d509-fc7a-49a0-b74f-2653073595df=中信云测试=100\",\"prices\":\"\",\"RegionId\":\"cn-beijing\",\"property\":\"1=开发\",\"app_base_line\":\"T999=中信云测试\",\"instance_num\":\"1\"},\"service_name\":\"对象存储OSS\",\"instances\":[{\"service_id\":\"57721cb62fa45f06e1c013d6\",\"is_deleted\":\"0\",\"status_name\":\"创建成功\",\"supply_instance_id\":\"1544473240590375\",\"instance_id\":\"0fc458a4-c9da-461e-a7bd-f2c0666cc1f5\"}],\"list_id\":\"D809CE26-F7C2-45C0-8962-F3639E00F0AB\",\"service_type_id\":\"2\"}],\"state_name\":\"已完成\",\"last_oper_time\":\"2016-10-09 14:40:17\"}}";
//        String s = HttpClientUtil.sendPost(url, param);
//        System.out.println(s);
        
        
        
//        String url = "http://user.citicdao.com/usrmgt/users.do";
//        Map<String,String> param = new HashMap<>();
//        param.put("selectType", "7");
//        param.put("selectId", "a8245669-d6c1-46eb-8224-f9c846e32da2");
//        String  aaa = HttpClientUtil.sendGet(url, param);
        
    }
    
}
