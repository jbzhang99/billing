package com.ai.baas.batch.client.dao.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ai.baas.batch.client.dao.mapper.bo.CpPackageInfo;
import com.ai.baas.batch.client.dao.mapper.bo.CpPackageInfoCriteria;
import com.ai.baas.batch.client.dao.mapper.bo.CpPackageInfoKey;

public interface CpPackageInfoMapper {
    int countByExample(CpPackageInfoCriteria example);

    int deleteByExample(CpPackageInfoCriteria example);

    int deleteByPrimaryKey(CpPackageInfoKey key);

    int insert(CpPackageInfo record);

    int insertSelective(CpPackageInfo record);

    List<CpPackageInfo> selectByExample(CpPackageInfoCriteria example);

    CpPackageInfo selectByPrimaryKey(CpPackageInfoKey key);

    int updateByExampleSelective(@Param("record") CpPackageInfo record, @Param("example") CpPackageInfoCriteria example);

    int updateByExample(@Param("record") CpPackageInfo record, @Param("example") CpPackageInfoCriteria example);

    int updateByPrimaryKeySelective(CpPackageInfo record);

    int updateByPrimaryKey(CpPackageInfo record);
}