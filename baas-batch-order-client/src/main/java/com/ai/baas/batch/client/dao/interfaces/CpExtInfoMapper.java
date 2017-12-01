package com.ai.baas.batch.client.dao.interfaces;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.ai.baas.batch.client.dao.mapper.bo.CpExtInfo;
import com.ai.baas.batch.client.dao.mapper.bo.CpExtInfoCriteria;

public interface CpExtInfoMapper {
    int countByExample(CpExtInfoCriteria example);

    int deleteByExample(CpExtInfoCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(CpExtInfo record);

    int insertSelective(CpExtInfo record);

    List<CpExtInfo> selectByExample(CpExtInfoCriteria example);

    CpExtInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CpExtInfo record, @Param("example") CpExtInfoCriteria example);

    int updateByExample(@Param("record") CpExtInfo record, @Param("example") CpExtInfoCriteria example);

    int updateByPrimaryKeySelective(CpExtInfo record);

    int updateByPrimaryKey(CpExtInfo record);
}