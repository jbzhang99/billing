package com.ai.baas.prd.dao.mapper.interfaces;

import com.ai.baas.prd.dao.mapper.bo.PmDimensionBranchHis;
import com.ai.baas.prd.dao.mapper.bo.PmDimensionBranchHisCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmDimensionBranchHisMapper {
    int countByExample(PmDimensionBranchHisCriteria example);

    int deleteByExample(PmDimensionBranchHisCriteria example);

    int insert(PmDimensionBranchHis record);

    int insertSelective(PmDimensionBranchHis record);

    List<PmDimensionBranchHis> selectByExample(PmDimensionBranchHisCriteria example);

    int updateByExampleSelective(@Param("record") PmDimensionBranchHis record, @Param("example") PmDimensionBranchHisCriteria example);

    int updateByExample(@Param("record") PmDimensionBranchHis record, @Param("example") PmDimensionBranchHisCriteria example);
}