package com.ai.baas.ccp.service.atom.interfaces;

import java.util.List;

import com.ai.baas.ccp.dao.mapper.bo.NoticeRecord;

public interface INoticeRecordAtomSV {

    void saveBatch(List<NoticeRecord> recordList);

}
