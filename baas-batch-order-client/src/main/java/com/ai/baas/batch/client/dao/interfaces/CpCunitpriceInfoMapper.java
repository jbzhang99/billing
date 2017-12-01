package com.ai.baas.batch.client.dao.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ai.baas.batch.client.dao.mapper.bo.CpCunitpriceInfo;
import com.ai.baas.batch.client.dao.mapper.bo.CpCunitpriceInfoCriteria;

public interface CpCunitpriceInfoMapper {
    int countByExample(CpCunitpriceInfoCriteria example);

    int deleteByExample(CpCunitpriceInfoCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(CpCunitpriceInfo record);

    int insertSelective(CpCunitpriceInfo record);

    List<CpCunitpriceInfo> selectByExample(CpCunitpriceInfoCriteria example);

    CpCunitpriceInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CpCunitpriceInfo record, @Param("example") CpCunitpriceInfoCriteria example);

    int updateByExample(@Param("record") CpCunitpriceInfo record, @Param("example") CpCunitpriceInfoCriteria example);

    int updateByPrimaryKeySelective(CpCunitpriceInfo record);

    int updateByPrimaryKey(CpCunitpriceInfo record);
}