package com.ai.baas.batch.client.dao.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ai.baas.batch.client.dao.mapper.bo.PmBasedataCode;
import com.ai.baas.batch.client.dao.mapper.bo.PmBasedataCodeCriteria;

public interface PmBasedataCodeMapper {
    int countByExample(PmBasedataCodeCriteria example);

    int deleteByExample(PmBasedataCodeCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(PmBasedataCode record);

    int insertSelective(PmBasedataCode record);

    List<PmBasedataCode> selectByExample(PmBasedataCodeCriteria example);

    PmBasedataCode selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PmBasedataCode record, @Param("example") PmBasedataCodeCriteria example);

    int updateByExample(@Param("record") PmBasedataCode record, @Param("example") PmBasedataCodeCriteria example);

    int updateByPrimaryKeySelective(PmBasedataCode record);

    int updateByPrimaryKey(PmBasedataCode record);
}