package com.ai.baas.amc.dao.mapper.interfaces;

import com.ai.baas.amc.dao.mapper.bo.AmcFundDetail;
import com.ai.baas.amc.dao.mapper.bo.AmcFundDetailCriteria;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface AmcFundDetailMapper {
    int countByExample(AmcFundDetailCriteria example);

    int deleteByExample(AmcFundDetailCriteria example);

    int insertSelective(@Param("tableMonth") String tableMonth, @Param("record") AmcFundDetail record);

    List<AmcFundDetail> selectByExample(AmcFundDetailCriteria example);

    int updateByExampleSelective(@Param("record") AmcFundDetail record, @Param("example") AmcFundDetailCriteria example);

    int updateByExample(@Param("record") AmcFundDetail record, @Param("example") AmcFundDetailCriteria example);
}