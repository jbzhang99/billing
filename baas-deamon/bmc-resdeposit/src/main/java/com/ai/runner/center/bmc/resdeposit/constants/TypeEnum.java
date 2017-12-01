package com.ai.runner.center.bmc.resdeposit.constants;

public enum TypeEnum {
    /**
     * 语音套餐包
     */
    VPACKAGE("VPACKAGE"),
    /**
     * 短信套餐包
     */
    SPACKAGE("SPACKAGE"),
    /**
     * 数据套餐包
     */
    DPACKAGE("DPACKAGE"),
    /**
     * 虚拟币套餐包
     */
    MPACKAGE("MPACKAGE");
    
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
