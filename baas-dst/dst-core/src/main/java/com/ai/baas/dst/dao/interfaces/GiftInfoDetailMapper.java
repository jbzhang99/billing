package com.ai.baas.dst.dao.interfaces;

import com.ai.baas.dst.dao.mapper.bo.GiftInfoDetail;
import com.ai.baas.dst.dao.mapper.bo.GiftInfoDetailCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GiftInfoDetailMapper {
    int countByExample(GiftInfoDetailCriteria example);

    int deleteByExample(GiftInfoDetailCriteria example);

    int deleteByPrimaryKey(String discountId);

    int insert(GiftInfoDetail record);

    int insertSelective(GiftInfoDetail record);

    List<GiftInfoDetail> selectByExample(GiftInfoDetailCriteria example);

    GiftInfoDetail selectByPrimaryKey(String discountId);

    int updateByExampleSelective(@Param("record") GiftInfoDetail record, @Param("example") GiftInfoDetailCriteria example);

    int updateByExample(@Param("record") GiftInfoDetail record, @Param("example") GiftInfoDetailCriteria example);

    int updateByPrimaryKeySelective(GiftInfoDetail record);

    int updateByPrimaryKey(GiftInfoDetail record);
}