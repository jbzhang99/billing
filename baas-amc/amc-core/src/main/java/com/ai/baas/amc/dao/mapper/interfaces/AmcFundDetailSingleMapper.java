package com.ai.baas.amc.dao.mapper.interfaces;

import com.ai.baas.amc.dao.mapper.bo.AmcFundDetailSingle;
import com.ai.baas.amc.dao.mapper.bo.AmcFundDetailSingleCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AmcFundDetailSingleMapper {
    int countByExample(AmcFundDetailSingleCriteria example);

    int deleteByExample(AmcFundDetailSingleCriteria example);

    int deleteByPrimaryKey(String serialCode);

    int insert(AmcFundDetailSingle record);

    int insertSelective(AmcFundDetailSingle record);

    List<AmcFundDetailSingle> selectByExample(AmcFundDetailSingleCriteria example);

    AmcFundDetailSingle selectByPrimaryKey(String serialCode);

    int updateByExampleSelective(@Param("record") AmcFundDetailSingle record, @Param("example") AmcFundDetailSingleCriteria example);

    int updateByExample(@Param("record") AmcFundDetailSingle record, @Param("example") AmcFundDetailSingleCriteria example);

    int updateByPrimaryKeySelective(AmcFundDetailSingle record);

    int updateByPrimaryKey(AmcFundDetailSingle record);
}