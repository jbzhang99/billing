package com.ai.baas.batch.client.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ai.opt.sdk.util.StringUtil;
//import net.sf.json.JSONArray;
import com.alibaba.fastjson.JSONArray;
//import net.sf.json.JSONObject;
import com.alibaba.fastjson.JSONObject;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.Gson;


public class MyJsonUtil {
    private static Gson gson = new Gson();

    public static<T> Object JSONToObj(String jsonStr,Class<T> obj) {
        T t = null;
        try {
          ObjectMapper objectMapper = new ObjectMapper();

          t = objectMapper.readValue(jsonStr,
              obj);
        } catch (Exception e) {
          e.printStackTrace();
        }
        return t;
      }
    
    /**
     * 将json转换成实体类
     */
    public static <T> T toBean(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    public static <T> T toBean(String json, Type typeOfT) {
        return gson.fromJson(json, typeOfT);
    }

    /**
     * 将实体类装换成json
     */
    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    /**
     * 判断数据是否符合json格式
     */
    public static boolean isJson(String json) {
        try {
            gson.fromJson(json, Object.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static JSONObject[] analyJson(String json){
    	
		//String json="{\"usage_and_expenses_format\": [{\"name\": \"tradetype3\",\"display_name\": \"交易类型\",\"in_list\": true},{\"name\": \"date3\",\"display_name\": \"日期\",\"in_list\": true},{\"name\": \"instance_id\",\"display_name\": \"服务实例ID\",\"in_list\": false}],\"usage_data\": [{\"instance_id\": \"fbf20ccc-ef9e-4377-bbe1-7c56bd6b996c\",\"start_time\" : \"20160501\",\"end_time\" : \"20160531\",\"costs\": [{\"cost_center_id\" : \"afcc0ccc-ef9e-5377-ebe1-7c56bd6fghhj\", \"ratio\" : \"40\"},{\"cost_center_id\" : \"afcc0ccc-ef9e-5377-ebe1-7c56bd6fghhj\", \"ratio\" : \"60\"}], \"tradetype3\": \"使用虚拟机\",\"date3\": \"2016年5月27日\",},{\"instance_id\": \"fbf20ccc-ef9e-4329-bbe1-7c56bd6b5544\",\"start_time\" : \"20160601\",\"end_time\" : \"20160631\",\"costs\" : [{\"cost_center_id\" : \"afcc0ccc-ef9e-5377-ebe1-7c56bd6fghhj\", \"ratio\" : \"40\"},{\"cost_center_id\" : \"afcc0ccc-ef9e-5377-ebe1-7c56bd6fghhj\", \"ratio\" : \"60\"}], \"tradetype3\": \"开动虚拟机\",\"date3\": \"2016年5月28日\"}]}";
    	//JSONObject jsonObject1= JSONObject.fromObject((Object)json);
    	JSONObject datajson = JSONObject.parseObject(json);
		 String order = datajson.getString("data");
		if(!StringUtil.isBlank(order)){
		JSONObject jsonObject=JSONObject.parseObject(order);
    	//JSONObject jsonObject= JSONObject.fromObject((Object)json)
		  String value=null;
			  for(Object firstV:jsonObject.keySet()){
				  //if("usage_data".equals(firstV)){
				  if("usage_and_expenses_data".equals(firstV)){
					  value=String.valueOf(jsonObject.get(firstV));
					  if(!"".equals(value)||null!=value){
						  if("[".equals(value.substring(0,1))){
							 // JSONArray orderArray = rootObject.getJSONArray("orders");
							  JSONArray jsonDetail=jsonObject.getJSONArray("usage_and_expenses_data");
							  System.out.println("the length is "+jsonDetail.size());
							  JSONObject [] jsonarray=new JSONObject[jsonDetail.size()];
							  for(int i=0;i<jsonDetail.size();i++){
								 // List<Map<String,String>> list = new ArrayList<Map<String,String>>();
								  Map<String, String> data=new HashMap<String,String>();
								  System.out.println("the content is "+jsonDetail.getString(i));
								  JSONObject  json1=JSONObject.parseObject(jsonDetail.getString(i));
								  jsonarray[i]=jsonDetail.getJSONObject(i);
							  }
							  return jsonarray;
						  }else{
							  Map<String, String> data=new HashMap<String,String>();
							  JSONObject jsonvalue= JSONObject.parseObject(value);
							  JSONObject [] jsonarray=new JSONObject[1];
							  jsonarray[0]=jsonvalue;
							  return jsonarray;
						  }
					  }
				  }		
			  }
		 }
		return null;
		  
    }
}
