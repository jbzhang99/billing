package com.ai.baas.ccp.dao.mapper.interfaces;

import com.ai.baas.ccp.dao.mapper.bo.OmcScoutStatus;
import com.ai.baas.ccp.dao.mapper.bo.OmcScoutStatusCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmcScoutStatusMapper {
    int countByExample(OmcScoutStatusCriteria example);

    int deleteByExample(OmcScoutStatusCriteria example);

    int insert(OmcScoutStatus record);

    int insertSelective(OmcScoutStatus record);

    List<OmcScoutStatus> selectByExample(OmcScoutStatusCriteria example);

    int updateByExampleSelective(@Param("record") OmcScoutStatus record, @Param("example") OmcScoutStatusCriteria example);

    int updateByExample(@Param("record") OmcScoutStatus record, @Param("example") OmcScoutStatusCriteria example);
}