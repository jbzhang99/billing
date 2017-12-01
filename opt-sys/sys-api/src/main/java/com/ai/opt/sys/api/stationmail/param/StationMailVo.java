package com.ai.opt.sys.api.stationmail.param;

import java.io.Serializable;
import java.sql.Timestamp;

public class StationMailVo implements Serializable {

    /**
     * 邮件标识
     */
    private Long mailId;

    /**
     * 收件人
     */
    private String recipient;

    /**
     * 收件人姓名
     */
    private String recipientName;

    /**
     * 发件人
     */
    private String sender;

    /**
     * 发件人姓名
     */
    private String senderName;

    /**
     * 邮件标题
     */
    private String title;

    /**
     * 邮件重要级别
     */
    private String level;

    /**
     * 是否已读
     */
    private String isRead;

    /**
     * 是否删除
     */
    private String isDel;

    /**
     * 发送时间
     */
    private Timestamp sendtime;

    /**
     * 阅读时间
     */
    private Timestamp readtime;

    /**
     * 邮件内容
     */
    private String content;

    public Long getMailId() {
        return mailId;
    }

    public void setMailId(Long mailId) {
        this.mailId = mailId;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public Timestamp getSendtime() {
        return sendtime;
    }

    public void setSendtime(Timestamp sendtime) {
        this.sendtime = sendtime;
    }

    public Timestamp getReadtime() {
        return readtime;
    }

    public void setReadtime(Timestamp readtime) {
        this.readtime = readtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
