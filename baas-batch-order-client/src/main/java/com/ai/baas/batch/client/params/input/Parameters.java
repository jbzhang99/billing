package com.ai.baas.batch.client.params.input;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Parameters
{
    private String cost_center;

    private String app_base_line;

    private String instance_num;

    private String property;

    private String regionId;

    private String ZoneId;

    private String HostName;

    private String SystemDisk;

    private String InternetMaxBandwidthIn;

    private String InstanceType;

    public void setCost_center(String cost_center){
        this.cost_center = cost_center;
    }
    public String getCost_center(){
        return this.cost_center;
    }
    public void setApp_base_line(String app_base_line){
        this.app_base_line = app_base_line;
    }
    public String getApp_base_line(){
        return this.app_base_line;
    }
    public void setInstance_num(String instance_num){
        this.instance_num = instance_num;
    }
    public String getInstance_num(){
        return this.instance_num;
    }
    public void setProperty(String property){
        this.property = property;
    }
    public String getProperty(){
        return this.property;
    }

    
    public String getRegionId() {
        return regionId;
    }
    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }
    public void setZoneId(String ZoneId){
        this.ZoneId = ZoneId;
    }
    public String getZoneId(){
        return this.ZoneId;
    }
    public void setHostName(String HostName){
        this.HostName = HostName;
    }
    public String getHostName(){
        return this.HostName;
    }

    public String getSystemDisk() {
        return SystemDisk;
    }
    public void setSystemDisk(String systemDisk) {
        SystemDisk = systemDisk;
    }
    public void setInternetMaxBandwidthIn(String InternetMaxBandwidthIn){
        this.InternetMaxBandwidthIn = InternetMaxBandwidthIn;
    }
    public String getInternetMaxBandwidthIn(){
        return this.InternetMaxBandwidthIn;
    }
    public void setInstanceType(String InstanceType){
        this.InstanceType = InstanceType;
    }
    public String getInstanceType(){
        return this.InstanceType;
    }
}
