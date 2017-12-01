package com.ai.baas.job.dao.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ai.baas.job.dao.mapper.bo.BlCustinfo;
import com.ai.baas.job.dao.mapper.bo.BlCustinfoCriteria;

public interface BlCustinfoMapper {
    int countByExample(BlCustinfoCriteria example);

    int deleteByExample(BlCustinfoCriteria example);

    int deleteByPrimaryKey(String custId);

    int insert(BlCustinfo record);

    int insertSelective(BlCustinfo record);

    List<BlCustinfo> selectByExample(BlCustinfoCriteria example);

    BlCustinfo selectByPrimaryKey(String custId);

    int updateByExampleSelective(@Param("record") BlCustinfo record, @Param("example") BlCustinfoCriteria example);

    int updateByExample(@Param("record") BlCustinfo record, @Param("example") BlCustinfoCriteria example);

    int updateByPrimaryKeySelective(BlCustinfo record);

    int updateByPrimaryKey(BlCustinfo record);
}