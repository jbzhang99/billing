package com.ai.baas.cust.dao.interfaces;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.ai.baas.cust.dao.mapper.bo.BlUserinfoExt;
import com.ai.baas.cust.dao.mapper.bo.BlUserinfoExtCriteria;

public interface BlUserinfoExtMapper {
    int countByExample(BlUserinfoExtCriteria example);

    int deleteByExample(BlUserinfoExtCriteria example);

    int deleteByPrimaryKey(Integer extId);

    int insert(BlUserinfoExt record);

    int insertSelective(BlUserinfoExt record);

    List<BlUserinfoExt> selectByExample(BlUserinfoExtCriteria example);

    BlUserinfoExt selectByPrimaryKey(Integer extId);

    int updateByExampleSelective(@Param("record") BlUserinfoExt record, @Param("example") BlUserinfoExtCriteria example);

    int updateByExample(@Param("record") BlUserinfoExt record, @Param("example") BlUserinfoExtCriteria example);

    int updateByPrimaryKeySelective(BlUserinfoExt record);

    int updateByPrimaryKey(BlUserinfoExt record);
}