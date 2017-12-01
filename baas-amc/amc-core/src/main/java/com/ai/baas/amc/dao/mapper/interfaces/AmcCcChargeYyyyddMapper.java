package com.ai.baas.amc.dao.mapper.interfaces;

import com.ai.baas.amc.dao.mapper.bo.AmcCcChargeYyyydd;
import com.ai.baas.amc.dao.mapper.bo.AmcCcChargeYyyyddCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AmcCcChargeYyyyddMapper {
    int countByExample(AmcCcChargeYyyyddCriteria example);

    int deleteByExample(AmcCcChargeYyyyddCriteria example);

    int insert(AmcCcChargeYyyydd record);

    int insertSelective(AmcCcChargeYyyydd record);

    List<AmcCcChargeYyyydd> selectByExample(AmcCcChargeYyyyddCriteria example);

    int updateByExampleSelective(@Param("record") AmcCcChargeYyyydd record, @Param("example") AmcCcChargeYyyyddCriteria example);

    int updateByExample(@Param("record") AmcCcChargeYyyydd record, @Param("example") AmcCcChargeYyyyddCriteria example);
}