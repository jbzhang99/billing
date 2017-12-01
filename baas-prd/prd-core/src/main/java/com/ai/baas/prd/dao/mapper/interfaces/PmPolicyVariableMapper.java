package com.ai.baas.prd.dao.mapper.interfaces;

import com.ai.baas.prd.dao.mapper.bo.PmPolicyVariable;
import com.ai.baas.prd.dao.mapper.bo.PmPolicyVariableCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmPolicyVariableMapper {
    int countByExample(PmPolicyVariableCriteria example);

    int deleteByExample(PmPolicyVariableCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(PmPolicyVariable record);

    int insertSelective(PmPolicyVariable record);

    List<PmPolicyVariable> selectByExample(PmPolicyVariableCriteria example);

    PmPolicyVariable selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PmPolicyVariable record, @Param("example") PmPolicyVariableCriteria example);

    int updateByExample(@Param("record") PmPolicyVariable record, @Param("example") PmPolicyVariableCriteria example);

    int updateByPrimaryKeySelective(PmPolicyVariable record);

    int updateByPrimaryKey(PmPolicyVariable record);
}