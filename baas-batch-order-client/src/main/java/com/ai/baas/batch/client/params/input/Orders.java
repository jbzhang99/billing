package com.ai.baas.batch.client.params.input;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class Orders implements Serializable 
{
    private String id;

    private String state_id;

    private String user_id;

    private List<Shopping_lists> shopping_lists;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setState_id(String state_id){
        this.state_id = state_id;
    }
    public String getState_id(){
        return this.state_id;
    }
    public void setUser_id(String user_id){
        this.user_id = user_id;
    }
    public String getUser_id(){
        return this.user_id;
    }
    public void setShopping_lists(List<Shopping_lists> shopping_lists){
        this.shopping_lists = shopping_lists;
    }
    public List<Shopping_lists> getShopping_lists(){
        return this.shopping_lists;
    }
}