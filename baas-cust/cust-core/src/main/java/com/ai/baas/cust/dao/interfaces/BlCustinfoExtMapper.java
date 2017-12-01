package com.ai.baas.cust.dao.interfaces;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.ai.baas.cust.dao.mapper.bo.BlCustinfoExt;
import com.ai.baas.cust.dao.mapper.bo.BlCustinfoExtCriteria;

public interface BlCustinfoExtMapper {
    int countByExample(BlCustinfoExtCriteria example);

    int deleteByExample(BlCustinfoExtCriteria example);

    int deleteByPrimaryKey(Long extId);

    int insert(BlCustinfoExt record);

    int insertSelective(BlCustinfoExt record);

    List<BlCustinfoExt> selectByExample(BlCustinfoExtCriteria example);

    BlCustinfoExt selectByPrimaryKey(Long extId);

    int updateByExampleSelective(@Param("record") BlCustinfoExt record, @Param("example") BlCustinfoExtCriteria example);

    int updateByExample(@Param("record") BlCustinfoExt record, @Param("example") BlCustinfoExtCriteria example);

    int updateByPrimaryKeySelective(BlCustinfoExt record);

    int updateByPrimaryKey(BlCustinfoExt record);
}