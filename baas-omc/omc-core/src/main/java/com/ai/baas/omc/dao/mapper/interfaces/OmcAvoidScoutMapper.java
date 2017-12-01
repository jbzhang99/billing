package com.ai.baas.omc.dao.mapper.interfaces;

import com.ai.baas.omc.dao.mapper.bo.OmcAvoidScout;
import com.ai.baas.omc.dao.mapper.bo.OmcAvoidScoutCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmcAvoidScoutMapper {
    int countByExample(OmcAvoidScoutCriteria example);

    int deleteByExample(OmcAvoidScoutCriteria example);

    int insert(OmcAvoidScout record);

    int insertSelective(OmcAvoidScout record);

    List<OmcAvoidScout> selectByExample(OmcAvoidScoutCriteria example);

    int updateByExampleSelective(@Param("record") OmcAvoidScout record, @Param("example") OmcAvoidScoutCriteria example);

    int updateByExample(@Param("record") OmcAvoidScout record, @Param("example") OmcAvoidScoutCriteria example);
}