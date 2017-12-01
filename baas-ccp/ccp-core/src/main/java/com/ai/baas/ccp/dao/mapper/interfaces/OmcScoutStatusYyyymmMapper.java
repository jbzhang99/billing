package com.ai.baas.ccp.dao.mapper.interfaces;

import com.ai.baas.ccp.dao.mapper.bo.OmcScoutStatusYyyymm;
import com.ai.baas.ccp.dao.mapper.bo.OmcScoutStatusYyyymmCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmcScoutStatusYyyymmMapper {
    int countByExample(OmcScoutStatusYyyymmCriteria example);

    int deleteByExample(OmcScoutStatusYyyymmCriteria example);

    int insert(OmcScoutStatusYyyymm record);

    int insertSelective(OmcScoutStatusYyyymm record);

    List<OmcScoutStatusYyyymm> selectByExample(OmcScoutStatusYyyymmCriteria example);

    int updateByExampleSelective(@Param("record") OmcScoutStatusYyyymm record, @Param("example") OmcScoutStatusYyyymmCriteria example);

    int updateByExample(@Param("record") OmcScoutStatusYyyymm record, @Param("example") OmcScoutStatusYyyymmCriteria example);
}