package com.ai.runner.center.bmc.resdeposit.constants;

public enum TypeEnum {

    /**
     * 数据套餐包
     */
    PACKAGE("PACKAGE");

    
    public final String type;

    private TypeEnum(String type) {
        this.type = type;
    }
    /**
     * 根据名字获得枚举类
     */
    public static TypeEnum getEnum(String name){
        for(TypeEnum t : TypeEnum.values()){
            if(t.type.equals(name)){
                return t;
            }
        }
        return null;
    }
}
