package com.ai.baas.amc.dao.mapper.interfaces;

import com.ai.baas.amc.dao.mapper.bo.AmcCcCharge;
import com.ai.baas.amc.dao.mapper.bo.AmcCcChargeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AmcCcChargeMapper {
    int countByExample(AmcCcChargeCriteria example);

    int deleteByExample(AmcCcChargeCriteria example);

    int insert(AmcCcCharge record);

    int insertSelective(AmcCcCharge record);

    List<AmcCcCharge> selectByExample(AmcCcChargeCriteria example);

    int updateByExampleSelective(@Param("record") AmcCcCharge record, @Param("example") AmcCcChargeCriteria example);

    int updateByExample(@Param("record") AmcCcCharge record, @Param("example") AmcCcChargeCriteria example);
}