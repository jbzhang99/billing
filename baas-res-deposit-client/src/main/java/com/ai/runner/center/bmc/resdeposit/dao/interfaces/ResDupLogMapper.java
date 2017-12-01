package com.ai.runner.center.bmc.resdeposit.dao.interfaces;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.ai.runner.center.bmc.resdeposit.dao.mapper.bo.ResDupLog;
import com.ai.runner.center.bmc.resdeposit.dao.mapper.bo.ResDupLogCriteria;

public interface ResDupLogMapper {
    int countByExample(ResDupLogCriteria example);

    int deleteByExample(ResDupLogCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(ResDupLog record);

    int insertSelective(ResDupLog record);

    List<ResDupLog> selectByExample(ResDupLogCriteria example);

    ResDupLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ResDupLog record, @Param("example") ResDupLogCriteria example);

    int updateByExample(@Param("record") ResDupLog record, @Param("example") ResDupLogCriteria example);

    int updateByPrimaryKeySelective(ResDupLog record);

    int updateByPrimaryKey(ResDupLog record);
    
    /**
     * 判断数据库中是否存在表,0表示不存在
     * @param tableName
     * @return
     */
    int getTableNum(@Param("tableName")String tableName);
    
    int createResDupLogTable(@Param("tableName")String tableName);
    
    int insertResDupLog(@Param("record") ResDupLog record, @Param("tableName")String tableName);
    
    List<ResDupLog> selectResDupLogByExample(@Param("record")ResDupLogCriteria record, @Param("tableName")String tableName);
}