package com.ai.baas.ccp.action.notice.interfaces;

import java.util.List;

import com.ai.baas.ccp.action.notice.param.base.AbstractBaseNoticeBusiVO;
import com.ai.baas.ccp.action.notice.vo.NoticeRecordVO;
import com.ai.baas.ccp.dao.mapper.bo.NoticeSubscribeRecord;

public interface INoticeFrameSV {

    /**
     * 更新记录状态
     */
    public void updateSyncSubscribeRecordState(long seqId, String state, String stateDesc)
            throws Exception;

    /**
     * 获取可供处理的订阅记录
     */
    public NoticeSubscribeRecord[] fetchSubscribeRecords(int processNo,
            int processTotalNum, int total, int retryTimes) throws Exception;

    /**
     * 处理单笔订阅记录流水
     */
    public void process(String seqId);

    /**
     * 保存发布记录信息
     * @return recordIds
     */
    public List<String> saveBatchRecord(List<NoticeRecordVO> records) throws Exception;
    
    /**
     * 生成消息通知记录
     * @author mayt
     * @param baseVO 
     * @ApiDocMethod
     */
    void createNoticeRecord(AbstractBaseNoticeBusiVO baseVO);

}
