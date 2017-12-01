package com.ai.runner.center.mmp.api.manager.param;

import java.util.List;

import com.ai.opt.base.vo.BaseInfo;

public class SMDataInfoNotify extends BaseInfo {
	
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
     * 短信数据列表，0个、一个或者多个列表
     */
    private List<SMData> dataList;
 
    
    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId == null ? null : systemId.trim();
    }
    
    
    public String getMsgSeq() {
        return msgSeq;
    }

    public void setMsgSeq(String msgSeq) {
        this.msgSeq = msgSeq == null ? null : msgSeq.trim();
    }    
    
    public List<SMData> getDataList() {
		return dataList;
	}
	public void setDataList(List<SMData> dataList) {
		this.dataList = dataList;
	}
    
}
