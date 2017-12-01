package com.ai.baas.job.dao.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ai.baas.job.dao.mapper.bo.BlSubsComm;
import com.ai.baas.job.dao.mapper.bo.BlSubsCommCriteria;
import com.ai.baas.job.dao.mapper.bo.BlSubsCommKey;

public interface BlSubsCommMapper {
    int countByExample(BlSubsCommCriteria example);

    int deleteByExample(BlSubsCommCriteria example);

    int deleteByPrimaryKey(BlSubsCommKey key);

    int insert(BlSubsComm record);

    int insertSelective(BlSubsComm record);

    List<BlSubsComm> selectByExample(BlSubsCommCriteria example);

    BlSubsComm selectByPrimaryKey(BlSubsCommKey key);

    int updateByExampleSelective(@Param("record") BlSubsComm record, @Param("example") BlSubsCommCriteria example);

    int updateByExample(@Param("record") BlSubsComm record, @Param("example") BlSubsCommCriteria example);

    int updateByPrimaryKeySelective(BlSubsComm record);

    int updateByPrimaryKey(BlSubsComm record);
}