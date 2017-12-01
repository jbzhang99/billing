package com.ai.baas.prd.dao.mapper.interfaces;

import com.ai.baas.prd.dao.mapper.bo.PmPolicyInfo;
import com.ai.baas.prd.dao.mapper.bo.PmPolicyInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmPolicyInfoMapper {
    int countByExample(PmPolicyInfoCriteria example);

    int deleteByExample(PmPolicyInfoCriteria example);

    int deleteByPrimaryKey(String policyId);

    int insert(PmPolicyInfo record);

    int insertSelective(PmPolicyInfo record);

    List<PmPolicyInfo> selectByExample(PmPolicyInfoCriteria example);

    PmPolicyInfo selectByPrimaryKey(String policyId);

    int updateByExampleSelective(@Param("record") PmPolicyInfo record, @Param("example") PmPolicyInfoCriteria example);

    int updateByExample(@Param("record") PmPolicyInfo record, @Param("example") PmPolicyInfoCriteria example);

    int updateByPrimaryKeySelective(PmPolicyInfo record);

    int updateByPrimaryKey(PmPolicyInfo record);
}