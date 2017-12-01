package com.ai.baas.amc.dao.mapper.interfaces;

import com.ai.baas.amc.dao.mapper.bo.AmcProductExt;
import com.ai.baas.amc.dao.mapper.bo.AmcProductExtCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AmcProductExtMapper {
    int countByExample(AmcProductExtCriteria example);

    int deleteByExample(AmcProductExtCriteria example);

    int insert(AmcProductExt record);

    int insertSelective(AmcProductExt record);

    List<AmcProductExt> selectByExample(AmcProductExtCriteria example);

    int updateByExampleSelective(@Param("record") AmcProductExt record, @Param("example") AmcProductExtCriteria example);

    int updateByExample(@Param("record") AmcProductExt record, @Param("example") AmcProductExtCriteria example);
}