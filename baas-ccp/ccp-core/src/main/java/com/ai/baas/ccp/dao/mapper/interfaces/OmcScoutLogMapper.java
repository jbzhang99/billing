package com.ai.baas.ccp.dao.mapper.interfaces;

import com.ai.baas.ccp.dao.mapper.bo.OmcScoutLog;
import com.ai.baas.ccp.dao.mapper.bo.OmcScoutLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmcScoutLogMapper {
    int countByExample(OmcScoutLogCriteria example);

    int deleteByExample(OmcScoutLogCriteria example);

    int insert(OmcScoutLog record);

    int insertSelective(OmcScoutLog record);

    List<OmcScoutLog> selectByExample(OmcScoutLogCriteria example);

    int updateByExampleSelective(@Param("record") OmcScoutLog record, @Param("example") OmcScoutLogCriteria example);

    int updateByExample(@Param("record") OmcScoutLog record, @Param("example") OmcScoutLogCriteria example);
}