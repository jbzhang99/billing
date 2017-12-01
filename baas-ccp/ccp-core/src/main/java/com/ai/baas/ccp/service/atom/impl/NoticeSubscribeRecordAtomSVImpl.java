package com.ai.baas.ccp.service.atom.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.baas.ccp.dao.mapper.bo.NoticeSubscribeRecord;
import com.ai.baas.ccp.dao.mapper.factory.MapperFactory;
import com.ai.baas.ccp.service.atom.interfaces.INoticeSubscribeRecordAtomSV;
import com.ai.opt.sdk.util.CollectionUtil;

@Component
public class NoticeSubscribeRecordAtomSVImpl implements INoticeSubscribeRecordAtomSV {

    @Override
    public void saveBatch(List<NoticeSubscribeRecord> subscribeRecordList) {
        if (CollectionUtil.isEmpty(subscribeRecordList)) {
            return;
        }
        for (NoticeSubscribeRecord noticeSubscribeRecord : subscribeRecordList) {
            MapperFactory.getNoticeSubscribeRecordMapper().insertSelective(noticeSubscribeRecord);
        }
    }

}
