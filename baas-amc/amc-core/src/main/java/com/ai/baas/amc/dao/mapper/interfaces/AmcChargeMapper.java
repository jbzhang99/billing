package com.ai.baas.amc.dao.mapper.interfaces;

import com.ai.baas.amc.dao.mapper.bo.AmcCharge;
import com.ai.baas.amc.dao.mapper.bo.AmcChargeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AmcChargeMapper {
    int countByExample(AmcChargeCriteria example);

    int countByExampleForBill(AmcChargeCriteria example);

    int countByExampleAndMonthsForBill(AmcChargeCriteria example);
    
    int deleteByExample(AmcChargeCriteria example);

    int insert(AmcCharge record);

    int insertSelective(AmcCharge record);

    List<AmcCharge> selectByExample(AmcChargeCriteria example);

    List<AmcCharge> selectByExampleForBill(AmcChargeCriteria example);
    
    List<AmcCharge> selectByExampleAndMonthsForBill(AmcChargeCriteria example);

    int updateByExampleSelective(@Param("record") AmcCharge record, @Param("example") AmcChargeCriteria example);

    int updateByExample(@Param("record") AmcCharge record, @Param("example") AmcChargeCriteria example);
}