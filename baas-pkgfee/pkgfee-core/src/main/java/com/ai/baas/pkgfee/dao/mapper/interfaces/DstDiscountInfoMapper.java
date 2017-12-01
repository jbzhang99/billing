package com.ai.baas.pkgfee.dao.mapper.interfaces;

import com.ai.baas.pkgfee.dao.mapper.bo.DstDiscountInfo;
import com.ai.baas.pkgfee.dao.mapper.bo.DstDiscountInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DstDiscountInfoMapper {
    int countByExample(DstDiscountInfoCriteria example);

    int deleteByExample(DstDiscountInfoCriteria example);

    int deleteByPrimaryKey(String discountId);

    int insert(DstDiscountInfo record);

    int insertSelective(DstDiscountInfo record);

    List<DstDiscountInfo> selectByExample(DstDiscountInfoCriteria example);

    DstDiscountInfo selectByPrimaryKey(String discountId);

    int updateByExampleSelective(@Param("record") DstDiscountInfo record, @Param("example") DstDiscountInfoCriteria example);

    int updateByExample(@Param("record") DstDiscountInfo record, @Param("example") DstDiscountInfoCriteria example);

    int updateByPrimaryKeySelective(DstDiscountInfo record);

    int updateByPrimaryKey(DstDiscountInfo record);
}