package com.ai.baas.amc.dao.mapper.interfaces;

import com.ai.baas.amc.dao.mapper.bo.AmcProductInfo;
import com.ai.baas.amc.dao.mapper.bo.AmcProductInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AmcProductInfoMapper {
    int countByExample(AmcProductInfoCriteria example);

    int deleteByExample(AmcProductInfoCriteria example);

    int deleteByPrimaryKey(String productId);

    int insert(AmcProductInfo record);

    int insertSelective(AmcProductInfo record);

    List<AmcProductInfo> selectByExample(AmcProductInfoCriteria example);

    AmcProductInfo selectByPrimaryKey(String productId);

    int updateByExampleSelective(@Param("record") AmcProductInfo record, @Param("example") AmcProductInfoCriteria example);

    int updateByExample(@Param("record") AmcProductInfo record, @Param("example") AmcProductInfoCriteria example);

    int updateByPrimaryKeySelective(AmcProductInfo record);

    int updateByPrimaryKey(AmcProductInfo record);
}