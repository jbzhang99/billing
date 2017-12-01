package com.ai.baas.amc.dao.mapper.interfaces;

import com.ai.baas.amc.dao.mapper.bo.AmcFundSerialSingle;
import com.ai.baas.amc.dao.mapper.bo.AmcFundSerialSingleCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AmcFundSerialSingleMapper {
    int countByExample(AmcFundSerialSingleCriteria example);

    int deleteByExample(AmcFundSerialSingleCriteria example);

    int deleteByPrimaryKey(String paySerialCode);

    int insert(AmcFundSerialSingle record);

    int insertSelective(AmcFundSerialSingle record);

    List<AmcFundSerialSingle> selectByExample(AmcFundSerialSingleCriteria example);

    AmcFundSerialSingle selectByPrimaryKey(String paySerialCode);

    int updateByExampleSelective(@Param("record") AmcFundSerialSingle record, @Param("example") AmcFundSerialSingleCriteria example);

    int updateByExample(@Param("record") AmcFundSerialSingle record, @Param("example") AmcFundSerialSingleCriteria example);

    int updateByPrimaryKeySelective(AmcFundSerialSingle record);

    int updateByPrimaryKey(AmcFundSerialSingle record);
}