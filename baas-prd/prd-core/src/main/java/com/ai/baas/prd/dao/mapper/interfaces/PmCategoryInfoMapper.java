package com.ai.baas.prd.dao.mapper.interfaces;

import com.ai.baas.prd.dao.mapper.bo.PmCategoryInfo;
import com.ai.baas.prd.dao.mapper.bo.PmCategoryInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmCategoryInfoMapper {
    int countByExample(PmCategoryInfoCriteria example);

    int deleteByExample(PmCategoryInfoCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(PmCategoryInfo record);

    int insertSelective(PmCategoryInfo record);

    List<PmCategoryInfo> selectByExample(PmCategoryInfoCriteria example);

    PmCategoryInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PmCategoryInfo record, @Param("example") PmCategoryInfoCriteria example);

    int updateByExample(@Param("record") PmCategoryInfo record, @Param("example") PmCategoryInfoCriteria example);

    int updateByPrimaryKeySelective(PmCategoryInfo record);

    int updateByPrimaryKey(PmCategoryInfo record);
}