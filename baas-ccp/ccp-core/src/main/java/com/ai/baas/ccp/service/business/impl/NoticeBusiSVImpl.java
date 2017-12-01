package com.ai.baas.ccp.service.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.baas.ccp.action.notice.vo.NoticeRecordVO;
import com.ai.baas.ccp.dao.mapper.bo.NoticeSubscribeRecord;
import com.ai.baas.ccp.dao.mapper.bo.NoticeSubscribeRecordCriteria;
import com.ai.baas.ccp.dao.mapper.factory.MapperFactory;
import com.ai.baas.ccp.service.business.interfaces.INoticeBusiSV;
import com.ai.baas.ccp.service.factory.ServiceFactory;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.constants.ExceptCodeConstants;
import com.ai.opt.sdk.util.CollectionUtil;

@Component
public class NoticeBusiSVImpl implements INoticeBusiSV {

    @Override
    public void saveBatchRecord(List<NoticeRecordVO> records) throws Exception {
        ServiceFactory.getINoticeFrameSV().saveBatchRecord(records);
    }

    @Override
    public void saveRecord(NoticeRecordVO record) throws Exception {
        if (record == null) {
            throw new BusinessException(ExceptCodeConstants.Special.PARAM_IS_NULL,
                    "保存发布记录信息失败：传入数据为空");
        }
        List<NoticeRecordVO> records = new ArrayList<NoticeRecordVO>();
        records.add(record);
        this.saveBatchRecord(records);
    }

    @Override
    public void sendMsg(List<String> recordIds) {
        if (CollectionUtil.isEmpty(recordIds)) {
            return;
        }
        for (String recordId : recordIds) {
            NoticeSubscribeRecordCriteria criteria = new NoticeSubscribeRecordCriteria();
            criteria.createCriteria().andRecordIdEqualTo(recordId);
            List<NoticeSubscribeRecord> subscribeRecords = MapperFactory.getNoticeSubscribeRecordMapper().selectByExample(criteria);
            if (CollectionUtil.isEmpty(subscribeRecords)) {
                continue;
            }
            for (NoticeSubscribeRecord noticeSubscribeRecord : subscribeRecords) {
                ServiceFactory.getINoticeFrameSV().process(noticeSubscribeRecord.getSeqId());
            }
        }
    }

}
