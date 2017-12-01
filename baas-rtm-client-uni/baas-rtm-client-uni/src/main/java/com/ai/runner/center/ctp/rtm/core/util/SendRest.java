package com.ai.runner.center.ctp.rtm.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.rtm.api.datacollect.params.DataVO;
import com.alibaba.fastjson.JSON;

public class SendRest {
    private static Logger logger = LoggerFactory.getLogger(SendRest.class);
    private static String address; // "http://10.1.130.84:10771/baasrtm/dataService/transResource";
    private static String tenant;
    private static String system;
    private  static String flow;
    private  static String user;
    private static  String passwd;
    private static String infoId;
    private static String num;

    public static void scanFiles(String dest, String filename){
        String path = dest;
        
        File root = new File(path);
        File[] files = root.listFiles();
        for(File f : files){
            overfile(f);
        }       
    }

    private static String overfile(File file) {
        if(file.isDirectory()){
            return null;
        }
        int number = Integer.parseInt(num);
        try {
            BufferedReader br = new BufferedReader(new FileReader (file));
            String s = null;
            List<String> lines = new ArrayList<String>();
            
            int i =0; 
            s = br.readLine();
            while(s!=null)
            {
                do {
                    System.out.println(s);
                    lines.add(s);
                    i++;
                } while (i%number != 0  &  (s =  br.readLine()) != null);
            }          
            String message = assembleMessage(lines) ;
            System.out.println("message :" + message);
            lines.clear();
            //send
            DataVO dataVO = new DataVO();
            dataVO.setTransData(message);
            HttpResponse httpResponse = HttpClientUtil.send(address,JSON.toJSONString(dataVO));
            System.err.println("111111");
            logger.info("httpResponse.getStatusLine() = " + httpResponse.getStatusLine());

        } catch (Exception e) {
            e.printStackTrace();
        }     
        return null;       
    }
    
    private static String assembleMessage(List<String> lines){
        StringBuilder busData = new StringBuilder();
        //head:tenant:system:flow:user:passwd
        busData.append(tenant).append(RtmConstants.HEAD_SPLIT);
        busData.append(system).append(RtmConstants.HEAD_SPLIT);
        busData.append(flow).append(RtmConstants.HEAD_SPLIT);
        busData.append(user).append(RtmConstants.HEAD_SPLIT);
        busData.append(passwd).append(RtmConstants.HEAD_SPLIT);

        String[] oneLine = null;
        for(int i=0;i<lines.size();i++){
            StringBuilder record = new StringBuilder();
            oneLine = StringUtils.splitPreserveAllTokens(lines.get(i), "|");
            record.append(infoId).append(RtmConstants.FIELD_SPLIT);
            for(String m : oneLine){
                record.append(m).append(RtmConstants.FIELD_SPLIT);
            }
            busData.append(record.substring(0, record.length()-1)).append(RtmConstants.RECORD_SPLIT);
        }
        return busData.substring(0, busData.length()-1).toString();
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        SendRest.address = address;
    }

    public static String getTenant() {
        return tenant;
    }

    public static void setTenant(String tenant) {
        SendRest.tenant = tenant;
    }

    public static String getSystem() {
        return system;
    }

    public static void setSystem(String system) {
        SendRest.system = system;
    }

    public static String getFlow() {
        return flow;
    }

    public static void setFlow(String flow) {
        SendRest.flow = flow;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        SendRest.user = user;
    }

    public static String getPasswd() {
        return passwd;
    }

    public static void setPasswd(String passwd) {
        SendRest.passwd = passwd;
    }

    public static String getInfoId() {
        return infoId;
    }

    public static void setInfoId(String infoId) {
        SendRest.infoId = infoId;
    }

    public static String getNum() {
        return num;
    }

    public static void setNum(String num) {
        SendRest.num = num;
    }





    
    
}
