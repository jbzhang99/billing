package com.ai.baas.ccp.dao.mapper.interfaces;

import com.ai.baas.ccp.dao.mapper.bo.OmcUrgeStatus;
import com.ai.baas.ccp.dao.mapper.bo.OmcUrgeStatusCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmcUrgeStatusMapper {
    int countByExample(OmcUrgeStatusCriteria example);

    int deleteByExample(OmcUrgeStatusCriteria example);

    int insert(OmcUrgeStatus record);

    int insertSelective(OmcUrgeStatus record);

    List<OmcUrgeStatus> selectByExample(OmcUrgeStatusCriteria example);

    int updateByExampleSelective(@Param("record") OmcUrgeStatus record, @Param("example") OmcUrgeStatusCriteria example);

    int updateByExample(@Param("record") OmcUrgeStatus record, @Param("example") OmcUrgeStatusCriteria example);
}