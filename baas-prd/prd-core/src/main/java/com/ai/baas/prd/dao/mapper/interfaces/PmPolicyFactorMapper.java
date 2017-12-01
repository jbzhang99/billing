package com.ai.baas.prd.dao.mapper.interfaces;

import com.ai.baas.prd.dao.mapper.bo.PmPolicyFactor;
import com.ai.baas.prd.dao.mapper.bo.PmPolicyFactorCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmPolicyFactorMapper {
    int countByExample(PmPolicyFactorCriteria example);

    int deleteByExample(PmPolicyFactorCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(PmPolicyFactor record);

    int insertSelective(PmPolicyFactor record);

    List<PmPolicyFactor> selectByExample(PmPolicyFactorCriteria example);

    PmPolicyFactor selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PmPolicyFactor record, @Param("example") PmPolicyFactorCriteria example);

    int updateByExample(@Param("record") PmPolicyFactor record, @Param("example") PmPolicyFactorCriteria example);

    int updateByPrimaryKeySelective(PmPolicyFactor record);

    int updateByPrimaryKey(PmPolicyFactor record);
}