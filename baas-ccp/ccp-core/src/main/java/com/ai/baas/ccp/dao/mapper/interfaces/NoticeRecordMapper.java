package com.ai.baas.ccp.dao.mapper.interfaces;

import com.ai.baas.ccp.dao.mapper.bo.NoticeRecord;
import com.ai.baas.ccp.dao.mapper.bo.NoticeRecordCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NoticeRecordMapper {
    int countByExample(NoticeRecordCriteria example);

    int deleteByExample(NoticeRecordCriteria example);

    int deleteByPrimaryKey(String recordId);

    int insert(NoticeRecord record);

    int insertSelective(NoticeRecord record);

    List<NoticeRecord> selectByExample(NoticeRecordCriteria example);

    NoticeRecord selectByPrimaryKey(String recordId);

    int updateByExampleSelective(@Param("record") NoticeRecord record, @Param("example") NoticeRecordCriteria example);

    int updateByExample(@Param("record") NoticeRecord record, @Param("example") NoticeRecordCriteria example);

    int updateByPrimaryKeySelective(NoticeRecord record);

    int updateByPrimaryKey(NoticeRecord record);
}