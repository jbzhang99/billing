package com.ai.baas.prd.dao.mapper.interfaces;

import com.ai.baas.prd.dao.mapper.bo.PmDimensionInfoHis;
import com.ai.baas.prd.dao.mapper.bo.PmDimensionInfoHisCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmDimensionInfoHisMapper {
    int countByExample(PmDimensionInfoHisCriteria example);

    int deleteByExample(PmDimensionInfoHisCriteria example);

    int insert(PmDimensionInfoHis record);

    int insertSelective(PmDimensionInfoHis record);

    List<PmDimensionInfoHis> selectByExample(PmDimensionInfoHisCriteria example);

    int updateByExampleSelective(@Param("record") PmDimensionInfoHis record, @Param("example") PmDimensionInfoHisCriteria example);

    int updateByExample(@Param("record") PmDimensionInfoHis record, @Param("example") PmDimensionInfoHisCriteria example);
}