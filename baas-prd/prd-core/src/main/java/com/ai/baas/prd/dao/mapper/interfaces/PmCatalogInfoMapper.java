package com.ai.baas.prd.dao.mapper.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ai.baas.prd.dao.mapper.bo.PmCatalogInfo;
import com.ai.baas.prd.dao.mapper.bo.PmCatalogInfoCriteria;

public interface PmCatalogInfoMapper {
    int countByExample(PmCatalogInfoCriteria example);

    int deleteByExample(PmCatalogInfoCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(PmCatalogInfo record);

    int insertSelective(PmCatalogInfo record);

    List<PmCatalogInfo> selectByExample(PmCatalogInfoCriteria example);

    PmCatalogInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PmCatalogInfo record, @Param("example") PmCatalogInfoCriteria example);

    int updateByExample(@Param("record") PmCatalogInfo record, @Param("example") PmCatalogInfoCriteria example);

    int updateByPrimaryKeySelective(PmCatalogInfo record);

    int updateByPrimaryKey(PmCatalogInfo record);

    List<PmCatalogInfo> getAllSpecByTradeType(PmCatalogInfoCriteria example);
}