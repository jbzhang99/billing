package com.ai.baas.amc.dao.mapper.interfaces;

import com.ai.baas.amc.dao.mapper.bo.AmcDeductRuleCriteria;
import com.ai.baas.amc.dao.mapper.bo.AmcDeductRuleKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AmcDeductRuleMapper {
    int countByExample(AmcDeductRuleCriteria example);

    int deleteByExample(AmcDeductRuleCriteria example);

    int deleteByPrimaryKey(AmcDeductRuleKey key);

    int insert(AmcDeductRuleKey record);

    int insertSelective(AmcDeductRuleKey record);

    List<AmcDeductRuleKey> selectByExample(AmcDeductRuleCriteria example);

    int updateByExampleSelective(@Param("record") AmcDeductRuleKey record, @Param("example") AmcDeductRuleCriteria example);

    int updateByExample(@Param("record") AmcDeductRuleKey record, @Param("example") AmcDeductRuleCriteria example);
}