package com.ai.baas.abm.dao.mapper.interfaces;

import com.ai.baas.abm.dao.mapper.bo.AmcResBookLog;
import com.ai.baas.abm.dao.mapper.bo.AmcResBookLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AmcResBookLogMapper {
    int countByExample(AmcResBookLogCriteria example);

    int deleteByExample(AmcResBookLogCriteria example);

    int deleteByPrimaryKey(Long bookId);

    int insert(@Param("record")AmcResBookLog record, @Param("acctMonth")String acctMonth);

    int insertSelective(AmcResBookLog record);

    List<AmcResBookLog> selectByExample(AmcResBookLogCriteria example);

    AmcResBookLog selectByPrimaryKey(Long bookId);

    int updateByExampleSelective(@Param("record") AmcResBookLog record, @Param("example") AmcResBookLogCriteria example);

    int updateByExample(@Param("record") AmcResBookLog record, @Param("example") AmcResBookLogCriteria example);

    int updateByPrimaryKeySelective(AmcResBookLog record);

    int updateByPrimaryKey(AmcResBookLog record);
    
    /**
     * 判断数据库中是否存在表,0表示不存在
     * @param tableName
     * @return
     */
    int getTableNum(@Param("tableName")String tableName);
    
    int createResBookLogTable(@Param("tableName")String tableName);
}