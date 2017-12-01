package com.ai.baas.batch.client.dao.interfaces;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.ai.baas.batch.client.dao.mapper.bo.CpStepInfo;
import com.ai.baas.batch.client.dao.mapper.bo.CpStepInfoCriteria;

public interface CpStepInfoMapper {
    int countByExample(CpStepInfoCriteria example);

    int deleteByExample(CpStepInfoCriteria example);

    int deleteByPrimaryKey(Long setpId);

    int insert(CpStepInfo record);

    int insertSelective(CpStepInfo record);

    List<CpStepInfo> selectByExample(CpStepInfoCriteria example);

    CpStepInfo selectByPrimaryKey(Long setpId);

    int updateByExampleSelective(@Param("record") CpStepInfo record, @Param("example") CpStepInfoCriteria example);

    int updateByExample(@Param("record") CpStepInfo record, @Param("example") CpStepInfoCriteria example);

    int updateByPrimaryKeySelective(CpStepInfo record);

    int updateByPrimaryKey(CpStepInfo record);
}