package com.ai.baas.ccp.dao.mapper.interfaces;

import com.ai.baas.ccp.dao.mapper.bo.OmcScoutActionDefine;
import com.ai.baas.ccp.dao.mapper.bo.OmcScoutActionDefineCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmcScoutActionDefineMapper {
    int countByExample(OmcScoutActionDefineCriteria example);

    int deleteByExample(OmcScoutActionDefineCriteria example);

    int insert(OmcScoutActionDefine record);

    int insertSelective(OmcScoutActionDefine record);

    List<OmcScoutActionDefine> selectByExample(OmcScoutActionDefineCriteria example);

    int updateByExampleSelective(@Param("record") OmcScoutActionDefine record, @Param("example") OmcScoutActionDefineCriteria example);

    int updateByExample(@Param("record") OmcScoutActionDefine record, @Param("example") OmcScoutActionDefineCriteria example);
}