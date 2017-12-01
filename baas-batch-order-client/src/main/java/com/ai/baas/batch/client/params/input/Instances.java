package com.ai.baas.batch.client.params.input;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Instances
{
    private String Instance_id;

    private String use;

    private String app_base_line;

    private String is_deleted;

    public void setInstance_id(String Instance_id){
        this.Instance_id = Instance_id;
    }
    public String getInstance_id(){
        return this.Instance_id;
    }
    public void setUse(String use){
        this.use = use;
    }
    public String getUse(){
        return this.use;
    }
    public void setApp_base_line(String app_base_line){
        this.app_base_line = app_base_line;
    }
    public String getApp_base_line(){
        return this.app_base_line;
    }
    public void setIs_deleted(String is_deleted){
        this.is_deleted = is_deleted;
    }
    public String getIs_deleted(){
        return this.is_deleted;
    }
}