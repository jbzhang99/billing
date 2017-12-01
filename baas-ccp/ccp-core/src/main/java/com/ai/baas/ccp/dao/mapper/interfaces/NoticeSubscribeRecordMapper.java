package com.ai.baas.ccp.dao.mapper.interfaces;

import com.ai.baas.ccp.dao.mapper.bo.NoticeSubscribeRecord;
import com.ai.baas.ccp.dao.mapper.bo.NoticeSubscribeRecordCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NoticeSubscribeRecordMapper {
    int countByExample(NoticeSubscribeRecordCriteria example);

    int deleteByExample(NoticeSubscribeRecordCriteria example);

    int deleteByPrimaryKey(String seqId);

    int insert(NoticeSubscribeRecord record);

    int insertSelective(NoticeSubscribeRecord record);

    List<NoticeSubscribeRecord> selectByExample(NoticeSubscribeRecordCriteria example);

    NoticeSubscribeRecord selectByPrimaryKey(String seqId);

    int updateByExampleSelective(@Param("record") NoticeSubscribeRecord record, @Param("example") NoticeSubscribeRecordCriteria example);

    int updateByExample(@Param("record") NoticeSubscribeRecord record, @Param("example") NoticeSubscribeRecordCriteria example);

    int updateByPrimaryKeySelective(NoticeSubscribeRecord record);

    int updateByPrimaryKey(NoticeSubscribeRecord record);
}