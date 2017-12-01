package com.ai.opt.sys.dao.mapper.interfaces;

import com.ai.opt.sys.dao.mapper.bo.StationMail;
import com.ai.opt.sys.dao.mapper.bo.StationMailCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StationMailMapper {
    int countByExample(StationMailCriteria example);

    int deleteByExample(StationMailCriteria example);

    int deleteByPrimaryKey(Long mailId);

    int insert(StationMail record);

    int insertSelective(StationMail record);

    List<StationMail> selectByExampleWithBLOBs(StationMailCriteria example);

    List<StationMail> selectByExample(StationMailCriteria example);

    StationMail selectByPrimaryKey(Long mailId);

    int updateByExampleSelective(@Param("record") StationMail record, @Param("example") StationMailCriteria example);

    int updateByExampleWithBLOBs(@Param("record") StationMail record, @Param("example") StationMailCriteria example);

    int updateByExample(@Param("record") StationMail record, @Param("example") StationMailCriteria example);

    int updateByPrimaryKeySelective(StationMail record);

    int updateByPrimaryKeyWithBLOBs(StationMail record);

    int updateByPrimaryKey(StationMail record);
}