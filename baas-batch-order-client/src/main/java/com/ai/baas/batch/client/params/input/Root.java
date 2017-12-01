package com.ai.baas.batch.client.params.input;

import java.util.ArrayList;
import java.util.List;
public class Root
{
    private List<Orders> orders;

    public void setOrders(List<Orders> orders){
        this.orders = orders;
    }
    public List<Orders> getOrders(){
        return this.orders;
    }
}