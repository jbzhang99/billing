package com.ai.baas.prd.dao.mapper.interfaces;

import com.ai.baas.prd.dao.mapper.bo.PmDimensionInfo;
import com.ai.baas.prd.dao.mapper.bo.PmDimensionInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmDimensionInfoMapper {
    int countByExample(PmDimensionInfoCriteria example);

    int deleteByExample(PmDimensionInfoCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(PmDimensionInfo record);

    int insertSelective(PmDimensionInfo record);

    List<PmDimensionInfo> selectByExample(PmDimensionInfoCriteria example);

    PmDimensionInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PmDimensionInfo record, @Param("example") PmDimensionInfoCriteria example);

    int updateByExample(@Param("record") PmDimensionInfo record, @Param("example") PmDimensionInfoCriteria example);

    int updateByPrimaryKeySelective(PmDimensionInfo record);

    int updateByPrimaryKey(PmDimensionInfo record);
}