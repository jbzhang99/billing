package com.ai.runner.center.mmp.api.manager.param;

import java.io.Serializable; 

public class SMData implements Serializable {

	/**
     * 模板ID, 必选, 最大长度32字节,模板标识
     */
    private String templateId;

    /**
     * 手机号码, 必选, 最大长度32字节 ,目标号码
     */
    private String phone;

    /**
     * 通道选择类型, 不必选, 最大长度8字节 ,CU-联通，CM-移动，CT-电信
     */
    private String serviceType;	
    
    /**
     * 短信数据, 不必选, 最大长度512字节 , 短信数据，json格式
     */
    private String gsmContent;	
    //json格式
    
    /**
     * 
     * @return
     * @author KAI
     * @ApiDocMethod
     */
    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId == null ? null : templateId.trim();
    }
    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }
    
    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType == null ? null : serviceType.trim();
    }
    
    public String getGsmContent() {
        return gsmContent;
    }

    public void setGsmContent(String gsmContent) {
        this.gsmContent = gsmContent == null ? null : gsmContent.trim();
    }
}
