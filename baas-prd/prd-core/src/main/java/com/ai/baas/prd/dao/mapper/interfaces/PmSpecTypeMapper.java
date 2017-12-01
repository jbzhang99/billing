package com.ai.baas.prd.dao.mapper.interfaces;

import com.ai.baas.prd.dao.mapper.bo.PmSpecType;
import com.ai.baas.prd.dao.mapper.bo.PmSpecTypeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmSpecTypeMapper {
    int countByExample(PmSpecTypeCriteria example);

    int deleteByExample(PmSpecTypeCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(PmSpecType record);

    int insertSelective(PmSpecType record);

    List<PmSpecType> selectByExample(PmSpecTypeCriteria example);

    PmSpecType selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PmSpecType record, @Param("example") PmSpecTypeCriteria example);

    int updateByExample(@Param("record") PmSpecType record, @Param("example") PmSpecTypeCriteria example);

    int updateByPrimaryKeySelective(PmSpecType record);

    int updateByPrimaryKey(PmSpecType record);
}