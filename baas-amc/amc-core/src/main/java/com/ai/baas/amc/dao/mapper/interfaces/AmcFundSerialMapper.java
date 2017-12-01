package com.ai.baas.amc.dao.mapper.interfaces;

import com.ai.baas.amc.dao.mapper.bo.AmcFundSerial;
import com.ai.baas.amc.dao.mapper.bo.AmcFundSerialCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AmcFundSerialMapper {
    int countByExample(AmcFundSerialCriteria example);

    int deleteByExample(AmcFundSerialCriteria example);

    int insertSelective(@Param("tableMonth") String tableMonth, @Param("record") AmcFundSerial record);

    List<AmcFundSerial> selectByExample(AmcFundSerialCriteria example);

    int updateByExampleSelective(@Param("record") AmcFundSerial record, @Param("example") AmcFundSerialCriteria example);

    int updateByExample(@Param("record") AmcFundSerial record, @Param("example") AmcFundSerialCriteria example);
}