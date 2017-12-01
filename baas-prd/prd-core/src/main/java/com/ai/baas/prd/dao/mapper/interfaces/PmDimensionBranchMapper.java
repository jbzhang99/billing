package com.ai.baas.prd.dao.mapper.interfaces;

import com.ai.baas.prd.dao.mapper.bo.PmDimensionBranch;
import com.ai.baas.prd.dao.mapper.bo.PmDimensionBranchCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmDimensionBranchMapper {
    int countByExample(PmDimensionBranchCriteria example);

    int deleteByExample(PmDimensionBranchCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(PmDimensionBranch record);

    int insertSelective(PmDimensionBranch record);

    List<PmDimensionBranch> selectByExample(PmDimensionBranchCriteria example);

    PmDimensionBranch selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PmDimensionBranch record, @Param("example") PmDimensionBranchCriteria example);

    int updateByExample(@Param("record") PmDimensionBranch record, @Param("example") PmDimensionBranchCriteria example);

    int updateByPrimaryKeySelective(PmDimensionBranch record);

    int updateByPrimaryKey(PmDimensionBranch record);
}