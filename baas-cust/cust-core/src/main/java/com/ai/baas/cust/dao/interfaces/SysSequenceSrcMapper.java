package com.ai.baas.cust.dao.interfaces;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.ai.baas.cust.dao.mapper.bo.SysSequenceSrc;
import com.ai.baas.cust.dao.mapper.bo.SysSequenceSrcCriteria;

public interface SysSequenceSrcMapper {
    int countByExample(SysSequenceSrcCriteria example);

    int deleteByExample(SysSequenceSrcCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(SysSequenceSrc record);

    int insertSelective(SysSequenceSrc record);

    List<SysSequenceSrc> selectByExample(SysSequenceSrcCriteria example);

    SysSequenceSrc selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysSequenceSrc record, @Param("example") SysSequenceSrcCriteria example);

    int updateByExample(@Param("record") SysSequenceSrc record, @Param("example") SysSequenceSrcCriteria example);

    int updateByPrimaryKeySelective(SysSequenceSrc record);

    int updateByPrimaryKey(SysSequenceSrc record);
}