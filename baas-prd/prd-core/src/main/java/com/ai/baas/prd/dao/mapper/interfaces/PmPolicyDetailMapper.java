package com.ai.baas.prd.dao.mapper.interfaces;

import com.ai.baas.prd.dao.mapper.bo.PmPolicyDetail;
import com.ai.baas.prd.dao.mapper.bo.PmPolicyDetailCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmPolicyDetailMapper {
    int countByExample(PmPolicyDetailCriteria example);

    int deleteByExample(PmPolicyDetailCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(PmPolicyDetail record);

    int insertSelective(PmPolicyDetail record);

    List<PmPolicyDetail> selectByExample(PmPolicyDetailCriteria example);

    PmPolicyDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PmPolicyDetail record, @Param("example") PmPolicyDetailCriteria example);

    int updateByExample(@Param("record") PmPolicyDetail record, @Param("example") PmPolicyDetailCriteria example);

    int updateByPrimaryKeySelective(PmPolicyDetail record);

    int updateByPrimaryKey(PmPolicyDetail record);
}