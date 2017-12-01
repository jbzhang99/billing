package com.ai.baas.dst.dao.interfaces;

import com.ai.baas.dst.dao.mapper.bo.DiscountInfo;
import com.ai.baas.dst.dao.mapper.bo.DiscountInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DiscountInfoMapper {
    int countByExample(DiscountInfoCriteria example);

    int deleteByExample(DiscountInfoCriteria example);

    int deleteByPrimaryKey(String discountId);

    int insert(DiscountInfo record);

    int insertSelective(DiscountInfo record);

    List<DiscountInfo> selectByExample(DiscountInfoCriteria example);

    DiscountInfo selectByPrimaryKey(String discountId);

    int updateByExampleSelective(@Param("record") DiscountInfo record, @Param("example") DiscountInfoCriteria example);

    int updateByExample(@Param("record") DiscountInfo record, @Param("example") DiscountInfoCriteria example);

    int updateByPrimaryKeySelective(DiscountInfo record);

    int updateByPrimaryKey(DiscountInfo record);
}