package com.ai.baas.dst.dao.interfaces;

import com.ai.baas.dst.dao.mapper.bo.DiscountExt;
import com.ai.baas.dst.dao.mapper.bo.DiscountExtCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DiscountExtMapper {
    int countByExample(DiscountExtCriteria example);

    int deleteByExample(DiscountExtCriteria example);

    int insert(DiscountExt record);

    int insertSelective(DiscountExt record);

    List<DiscountExt> selectByExample(DiscountExtCriteria example);

    int updateByExampleSelective(@Param("record") DiscountExt record, @Param("example") DiscountExtCriteria example);

    int updateByExample(@Param("record") DiscountExt record, @Param("example") DiscountExtCriteria example);
}