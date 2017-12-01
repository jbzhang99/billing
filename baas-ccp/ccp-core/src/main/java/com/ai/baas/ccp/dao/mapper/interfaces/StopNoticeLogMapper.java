package com.ai.baas.ccp.dao.mapper.interfaces;

import com.ai.baas.ccp.dao.mapper.bo.StopNoticeLog;
import com.ai.baas.ccp.dao.mapper.bo.StopNoticeLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StopNoticeLogMapper {
    int countByExample(StopNoticeLogCriteria example);

    int deleteByExample(StopNoticeLogCriteria example);

    int deleteByPrimaryKey(String logId);

    int insert(StopNoticeLog record);

    int insertSelective(StopNoticeLog record);

    List<StopNoticeLog> selectByExample(StopNoticeLogCriteria example);

    StopNoticeLog selectByPrimaryKey(String logId);

    int updateByExampleSelective(@Param("record") StopNoticeLog record, @Param("example") StopNoticeLogCriteria example);

    int updateByExample(@Param("record") StopNoticeLog record, @Param("example") StopNoticeLogCriteria example);

    int updateByPrimaryKeySelective(StopNoticeLog record);

    int updateByPrimaryKey(StopNoticeLog record);
}