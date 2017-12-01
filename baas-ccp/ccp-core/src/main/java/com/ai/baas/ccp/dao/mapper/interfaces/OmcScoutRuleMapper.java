package com.ai.baas.ccp.dao.mapper.interfaces;

import com.ai.baas.ccp.dao.mapper.bo.OmcScoutRule;
import com.ai.baas.ccp.dao.mapper.bo.OmcScoutRuleCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmcScoutRuleMapper {
    int countByExample(OmcScoutRuleCriteria example);

    int deleteByExample(OmcScoutRuleCriteria example);

    int insert(OmcScoutRule record);

    int insertSelective(OmcScoutRule record);

    List<OmcScoutRule> selectByExample(OmcScoutRuleCriteria example);

    int updateByExampleSelective(@Param("record") OmcScoutRule record, @Param("example") OmcScoutRuleCriteria example);

    int updateByExample(@Param("record") OmcScoutRule record, @Param("example") OmcScoutRuleCriteria example);
}