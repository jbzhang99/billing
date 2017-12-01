package com.ai.baas.prd.dao.mapper.interfaces;

import com.ai.baas.prd.dao.mapper.bo.PmCategoryInfoHis;
import com.ai.baas.prd.dao.mapper.bo.PmCategoryInfoHisCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmCategoryInfoHisMapper {
    int countByExample(PmCategoryInfoHisCriteria example);

    int deleteByExample(PmCategoryInfoHisCriteria example);

    int insert(PmCategoryInfoHis record);

    int insertSelective(PmCategoryInfoHis record);

    List<PmCategoryInfoHis> selectByExample(PmCategoryInfoHisCriteria example);

    int updateByExampleSelective(@Param("record") PmCategoryInfoHis record, @Param("example") PmCategoryInfoHisCriteria example);

    int updateByExample(@Param("record") PmCategoryInfoHis record, @Param("example") PmCategoryInfoHisCriteria example);
}