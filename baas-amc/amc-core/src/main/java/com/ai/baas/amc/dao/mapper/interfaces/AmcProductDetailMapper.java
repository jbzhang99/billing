package com.ai.baas.amc.dao.mapper.interfaces;

import com.ai.baas.amc.dao.mapper.bo.AmcProductDetail;
import com.ai.baas.amc.dao.mapper.bo.AmcProductDetailCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AmcProductDetailMapper {
    int countByExample(AmcProductDetailCriteria example);

    int deleteByExample(AmcProductDetailCriteria example);

    int insert(AmcProductDetail record);

    int insertSelective(AmcProductDetail record);

    List<AmcProductDetail> selectByExample(AmcProductDetailCriteria example);

    int updateByExampleSelective(@Param("record") AmcProductDetail record, @Param("example") AmcProductDetailCriteria example);

    int updateByExample(@Param("record") AmcProductDetail record, @Param("example") AmcProductDetailCriteria example);
}