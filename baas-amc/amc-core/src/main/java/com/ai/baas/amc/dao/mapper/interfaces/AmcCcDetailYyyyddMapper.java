package com.ai.baas.amc.dao.mapper.interfaces;

import com.ai.baas.amc.dao.mapper.bo.AmcCcDetailYyyydd;
import com.ai.baas.amc.dao.mapper.bo.AmcCcDetailYyyyddCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AmcCcDetailYyyyddMapper {
    int countByExample(AmcCcDetailYyyyddCriteria example);

    int deleteByExample(AmcCcDetailYyyyddCriteria example);

    int insert(AmcCcDetailYyyydd record);

    int insertSelective(AmcCcDetailYyyydd record);

    List<AmcCcDetailYyyydd> selectByExample(AmcCcDetailYyyyddCriteria example);

    int updateByExampleSelective(@Param("record") AmcCcDetailYyyydd record, @Param("example") AmcCcDetailYyyyddCriteria example);

    int updateByExample(@Param("record") AmcCcDetailYyyydd record, @Param("example") AmcCcDetailYyyyddCriteria example);
}