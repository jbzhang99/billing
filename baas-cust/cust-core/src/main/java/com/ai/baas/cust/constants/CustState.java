package com.ai.baas.cust.constants;

import java.util.Enumeration;

/**
 * 客户状态.
 */
public enum CustState {
    NORMAL("正常"),NODOC("未返档"),REGISTER("注册"),OWEFEE("欠费"),FREEZE("冻结"),DELETE("删除");

    private String stateDesc;

    private CustState(String stateDesc){
        this.stateDesc = stateDesc;
    }

    public String getStateDesc() {
        return stateDesc;
    }

    public static boolean hasState(String name){
        for(CustState state:CustState.values()){
            if(state.name().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
}
