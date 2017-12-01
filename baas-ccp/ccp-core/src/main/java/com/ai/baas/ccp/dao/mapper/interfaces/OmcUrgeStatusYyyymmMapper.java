package com.ai.baas.ccp.dao.mapper.interfaces;

import com.ai.baas.ccp.dao.mapper.bo.OmcUrgeStatusYyyymm;
import com.ai.baas.ccp.dao.mapper.bo.OmcUrgeStatusYyyymmCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmcUrgeStatusYyyymmMapper {
    int countByExample(OmcUrgeStatusYyyymmCriteria example);

    int deleteByExample(OmcUrgeStatusYyyymmCriteria example);

    int insert(OmcUrgeStatusYyyymm record);

    int insertSelective(OmcUrgeStatusYyyymm record);

    List<OmcUrgeStatusYyyymm> selectByExample(OmcUrgeStatusYyyymmCriteria example);

    int updateByExampleSelective(@Param("record") OmcUrgeStatusYyyymm record, @Param("example") OmcUrgeStatusYyyymmCriteria example);

    int updateByExample(@Param("record") OmcUrgeStatusYyyymm record, @Param("example") OmcUrgeStatusYyyymmCriteria example);
}