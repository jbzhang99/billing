package com.ai.baas.batch.client.params.input;
import java.util.ArrayList;
import java.util.List;

public class Shopping_lists
{
    private String list_id;

    private String service_id;

    private Parameters parameters;

    private List<Instances> instances;

    public void setList_id(String list_id){
        this.list_id = list_id;
    }
    public String getList_id(){
        return this.list_id;
    }
    public void setService_id(String service_id){
        this.service_id = service_id;
    }
    public String getService_id(){
        return this.service_id;
    }
    public void setParameters(Parameters parameters){
        this.parameters = parameters;
    }
    public Parameters getParameters(){
        return this.parameters;
    }
    public void setInstances(List<Instances> instances){
        this.instances = instances;
    }
    public List<Instances> getInstances(){
        return this.instances;
    }
}