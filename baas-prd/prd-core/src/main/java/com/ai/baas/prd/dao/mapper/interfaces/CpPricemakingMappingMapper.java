package com.ai.baas.prd.dao.mapper.interfaces;

import com.ai.baas.prd.dao.mapper.bo.CpPricemakingMapping;
import com.ai.baas.prd.dao.mapper.bo.CpPricemakingMappingCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CpPricemakingMappingMapper {
    int countByExample(CpPricemakingMappingCriteria example);

    int deleteByExample(CpPricemakingMappingCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(CpPricemakingMapping record);

    int insertSelective(CpPricemakingMapping record);

    List<CpPricemakingMapping> selectByExample(CpPricemakingMappingCriteria example);

    CpPricemakingMapping selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CpPricemakingMapping record, @Param("example") CpPricemakingMappingCriteria example);

    int updateByExample(@Param("record") CpPricemakingMapping record, @Param("example") CpPricemakingMappingCriteria example);

    int updateByPrimaryKeySelective(CpPricemakingMapping record);

    int updateByPrimaryKey(CpPricemakingMapping record);
}