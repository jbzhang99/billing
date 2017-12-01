package com.ai.baas.ccp.dao.mapper.interfaces;

import com.ai.baas.ccp.dao.mapper.bo.OmcScoutPolicy;
import com.ai.baas.ccp.dao.mapper.bo.OmcScoutPolicyCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmcScoutPolicyMapper {
    int countByExample(OmcScoutPolicyCriteria example);

    int deleteByExample(OmcScoutPolicyCriteria example);

    int insert(OmcScoutPolicy record);

    int insertSelective(OmcScoutPolicy record);

    List<OmcScoutPolicy> selectByExample(OmcScoutPolicyCriteria example);

    int updateByExampleSelective(@Param("record") OmcScoutPolicy record, @Param("example") OmcScoutPolicyCriteria example);

    int updateByExample(@Param("record") OmcScoutPolicy record, @Param("example") OmcScoutPolicyCriteria example);
}