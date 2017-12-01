package com.ai.baas.cust.dao.interfaces;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.ai.baas.cust.dao.mapper.bo.BlUserinfoZxBak;
import com.ai.baas.cust.dao.mapper.bo.BlUserinfoZxBakCriteria;

public interface BlUserinfoZxBakMapper {
    int countByExample(BlUserinfoZxBakCriteria example);

    int deleteByExample(BlUserinfoZxBakCriteria example);

    int deleteByPrimaryKey(String instanceId);

    int insert(BlUserinfoZxBak record);

    int insertSelective(BlUserinfoZxBak record);

    List<BlUserinfoZxBak> selectByExample(BlUserinfoZxBakCriteria example);

    BlUserinfoZxBak selectByPrimaryKey(String instanceId);

    int updateByExampleSelective(@Param("record") BlUserinfoZxBak record, @Param("example") BlUserinfoZxBakCriteria example);

    int updateByExample(@Param("record") BlUserinfoZxBak record, @Param("example") BlUserinfoZxBakCriteria example);

    int updateByPrimaryKeySelective(BlUserinfoZxBak record);

    int updateByPrimaryKey(BlUserinfoZxBak record);
}