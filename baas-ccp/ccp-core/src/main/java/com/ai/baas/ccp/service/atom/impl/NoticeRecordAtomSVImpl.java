package com.ai.baas.ccp.service.atom.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.baas.ccp.dao.mapper.bo.NoticeRecord;
import com.ai.baas.ccp.dao.mapper.factory.MapperFactory;
import com.ai.baas.ccp.service.atom.interfaces.INoticeRecordAtomSV;
import com.ai.opt.sdk.util.CollectionUtil;

@Component
public class NoticeRecordAtomSVImpl implements INoticeRecordAtomSV {

    @Override
    public void saveBatch(List<NoticeRecord> recordList) {
        if (CollectionUtil.isEmpty(recordList)) {
            return;
        }
        for (NoticeRecord noticeRecord : recordList) {
            MapperFactory.getNoticeRecordMapper().insertSelective(noticeRecord);
        }
    }

}
