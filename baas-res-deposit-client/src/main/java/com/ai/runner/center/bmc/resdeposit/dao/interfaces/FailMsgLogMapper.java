package com.ai.runner.center.bmc.resdeposit.dao.interfaces;

import com.ai.runner.center.bmc.resdeposit.dao.mapper.bo.FailMsgLog;
import com.ai.runner.center.bmc.resdeposit.dao.mapper.bo.FailMsgLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FailMsgLogMapper {
    int countByExample(FailMsgLogCriteria example);

    int deleteByExample(FailMsgLogCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(FailMsgLog record);

    int insertSelective(FailMsgLog record);

    List<FailMsgLog> selectByExample(FailMsgLogCriteria example);

    FailMsgLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FailMsgLog record, @Param("example") FailMsgLogCriteria example);

    int updateByExample(@Param("record") FailMsgLog record, @Param("example") FailMsgLogCriteria example);

    int updateByPrimaryKeySelective(FailMsgLog record);

    int updateByPrimaryKey(FailMsgLog record);
}