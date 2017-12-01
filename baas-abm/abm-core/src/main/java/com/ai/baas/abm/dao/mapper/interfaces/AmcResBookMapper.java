package com.ai.baas.abm.dao.mapper.interfaces;

import com.ai.baas.abm.dao.mapper.bo.AmcResBook;
import com.ai.baas.abm.dao.mapper.bo.AmcResBookBak;
import com.ai.baas.abm.dao.mapper.bo.AmcResBookCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AmcResBookMapper {
    int countByExample(AmcResBookCriteria example);

    int deleteByExample(AmcResBookCriteria example);

    int deleteByPrimaryKey(Long bookId);

    int insert(AmcResBook record);

    int insertSelective(AmcResBook record);

    List<AmcResBook> selectByExample(AmcResBookCriteria example);

    AmcResBook selectByPrimaryKey(Long bookId);

    int updateByExampleSelective(@Param("record") AmcResBook record, @Param("example") AmcResBookCriteria example);

    int updateByExample(@Param("record") AmcResBook record, @Param("example") AmcResBookCriteria example);

    int updateByPrimaryKeySelective(AmcResBook record);

    int updateByPrimaryKey(AmcResBook record);
    
    /**
     * 判断数据库中是否存在表,0表示不存在
     * @param tableName
     * @return
     */
    int getTableNum(@Param("tableName")String tableName);
    
    int createResBookBakTable(@Param("tableName")String tableName);
    
    int insertResBookBak(@Param("record") AmcResBookBak record, @Param("tableName")String tableName);
}