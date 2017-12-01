package com.ai.baas.ccp.dao.mapper.interfaces;

import com.ai.baas.ccp.dao.mapper.bo.OmcAvoidscout;
import com.ai.baas.ccp.dao.mapper.bo.OmcAvoidscoutCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmcAvoidscoutMapper {
    int countByExample(OmcAvoidscoutCriteria example);

    int deleteByExample(OmcAvoidscoutCriteria example);

    int insert(OmcAvoidscout record);

    int insertSelective(OmcAvoidscout record);

    List<OmcAvoidscout> selectByExample(OmcAvoidscoutCriteria example);

    int updateByExampleSelective(@Param("record") OmcAvoidscout record, @Param("example") OmcAvoidscoutCriteria example);

    int updateByExample(@Param("record") OmcAvoidscout record, @Param("example") OmcAvoidscoutCriteria example);
}