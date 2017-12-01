package com.ai.baas.pkgfee.dao.mapper.interfaces;

import com.ai.baas.pkgfee.dao.mapper.bo.DstDiscountExt;
import com.ai.baas.pkgfee.dao.mapper.bo.DstDiscountExtCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DstDiscountExtMapper {
    int countByExample(DstDiscountExtCriteria example);

    int deleteByExample(DstDiscountExtCriteria example);

    int insert(DstDiscountExt record);

    int insertSelective(DstDiscountExt record);

    List<DstDiscountExt> selectByExample(DstDiscountExtCriteria example);

    int updateByExampleSelective(@Param("record") DstDiscountExt record, @Param("example") DstDiscountExtCriteria example);

    int updateByExample(@Param("record") DstDiscountExt record, @Param("example") DstDiscountExtCriteria example);
}