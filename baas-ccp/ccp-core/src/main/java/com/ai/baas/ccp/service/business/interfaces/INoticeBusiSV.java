package com.ai.baas.ccp.service.business.interfaces;

import java.util.List;

import com.ai.baas.ccp.action.notice.vo.NoticeRecordVO;

public interface INoticeBusiSV {

    /**
     * 批量保存发布记录
     */
    public void saveBatchRecord(List<NoticeRecordVO> records) throws Exception;

    /**
     * 保存单个发布记录
     */
    public void saveRecord(NoticeRecordVO record) throws Exception;

    /**
     * 发送报文
     * @param recordIds
     * @author mayt
     * @ApiDocMethod
     */
    public void sendMsg(List<String> recordIds);
    
    

}
