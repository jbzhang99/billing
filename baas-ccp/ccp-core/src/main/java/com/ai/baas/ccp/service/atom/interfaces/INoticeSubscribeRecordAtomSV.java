package com.ai.baas.ccp.service.atom.interfaces;

import java.util.List;

import com.ai.baas.ccp.dao.mapper.bo.NoticeSubscribeRecord;

public interface INoticeSubscribeRecordAtomSV {

    void saveBatch(List<NoticeSubscribeRecord> subscribeRecordList);

}
