package com.ai.baas.batch.client.util;

import java.util.ArrayList;
import java.util.List;

public class ServiceCheck {
   
    private List<String> packageList = new ArrayList<>();
    private List<String> singleUsageList = new ArrayList<>();
    private List<String> doubleUsageList = new ArrayList<>();
    public ServiceCheck() {
        packageList.add("YOUYC");
        packageList.add("YOUHR");
        packageList.add("YOUBZ");
        packageList.add("WAF");
        packageList.add("CYD");
        packageList.add("ZBJZ");
        packageList.add("CMSTOP");
        packageList.add("TBTION");
        packageList.add("GIXI");
        packageList.add("ZHUCE");
        packageList.add("SMS");
        
        packageList.add("FR");
        packageList.add("ER");
        packageList.add("COMV");
        packageList.add("CAPI");
        packageList.add("BDM");
        packageList.add("TMCH");
        packageList.add("IMP");
        /*
         * record_type : TIME ; STREAM
         */
        doubleUsageList.add("ECS");
        doubleUsageList.add("SLB");
        doubleUsageList.add("EIP");
        
        singleUsageList.add("DISK");
        singleUsageList.add("KVS");
        singleUsageList.add("RDS");      
        singleUsageList.add("EXCONN");
        singleUsageList.add("DRDS");

    }
    
    public boolean isPackage (String type){
        if(packageList.contains(type)){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean isDoubleUsage (String type){
        if(doubleUsageList.contains(type)){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean isSingleUsage (String type){
        if(singleUsageList.contains(type)){
            return true;
        }else{
            return false;
        }
    }
}
