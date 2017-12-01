package com.ai.runner.center.mmp.api.manager.param;

import com.ai.opt.base.vo.BaseInfo;

public class SMTemplateInfoNotify extends BaseInfo{
    private static final long serialVersionUID = 1L;

    /**
     * 系统ID, 必选, 最大长度32字节
     */
    private String systemId;

    /**
     * 消息流水, 必选, 最大长度32字节
     */
    private String msgSeq;
    
    /**
     * 模板名称, 必选, 最大长度32字节
     */
    private String templateName;
    
    /**
     * 模板内容, 必选, 最大长度32字节
     */
    private String templateText;
    
    /**
     * 发送开启时间, 不必选, 最大长度32字节, 格式：HHMISS,即一天内从这个时刻开始，才能发送，缺省是00:00:00
     */
    private String beginTime;
    
    /**
     * 发送关闭时间, 不必选, 最大长度32字节, 格式：HHMISS,即一天内从这个时刻开始，停止发送，缺省是23:59:59
     */
    private String closeTime;
    
    /**
     * 重试次数 , 不必选, 数字不超过5位, 缺省是1
     */
    private int retryTimes;
    
    /**
     * 优先级, 不必选, 数字不超过5位, 缺省是0
     */
    private int priority;
    
    
    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId =systemId  == null ? null :systemId.trim();
    }    
  
    public String getMsgSeq() {
        return msgSeq;
    }

    public void setMsgSeq(String msgSeq) {
        this.msgSeq = msgSeq == null ? null : msgSeq.trim();
    }        
    
    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }         
    
    
    public String getTemplateText() {
        return templateText;
    }

    public void setTemplateText(String templateText) {
        this.templateText = templateText == null ? null : templateText.trim();
    }  
    
    
    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime == null ? null : beginTime.trim();
    }  
    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime == null ? null : closeTime.trim();
    }  
    public int getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
    }  
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }  
}
