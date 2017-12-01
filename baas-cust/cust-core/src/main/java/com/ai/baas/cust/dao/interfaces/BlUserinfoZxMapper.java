package com.ai.baas.cust.dao.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ai.baas.cust.dao.mapper.bo.BlUserinfoZx;
import com.ai.baas.cust.dao.mapper.bo.BlUserinfoZxCriteria;

public interface BlUserinfoZxMapper {
    int countByExample(BlUserinfoZxCriteria example);

    int deleteByExample(BlUserinfoZxCriteria example);

    int deleteByPrimaryKey(String instanceId);

    int insert(BlUserinfoZx record);

    int insertSelective(BlUserinfoZx record);

    List<BlUserinfoZx> selectByExample(BlUserinfoZxCriteria example);

    BlUserinfoZx selectByPrimaryKey(String instanceId);

    int updateByExampleSelective(@Param("record") BlUserinfoZx record, @Param("example") BlUserinfoZxCriteria example);

    int updateByExample(@Param("record") BlUserinfoZx record, @Param("example") BlUserinfoZxCriteria example);

    int updateByPrimaryKeySelective(BlUserinfoZx record);

    int updateByPrimaryKey(BlUserinfoZx record);
}