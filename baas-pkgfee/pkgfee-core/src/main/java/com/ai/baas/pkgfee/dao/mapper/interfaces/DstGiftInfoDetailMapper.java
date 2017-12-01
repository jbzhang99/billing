package com.ai.baas.pkgfee.dao.mapper.interfaces;

import com.ai.baas.pkgfee.dao.mapper.bo.DstGiftInfoDetail;
import com.ai.baas.pkgfee.dao.mapper.bo.DstGiftInfoDetailCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DstGiftInfoDetailMapper {
    int countByExample(DstGiftInfoDetailCriteria example);

    int deleteByExample(DstGiftInfoDetailCriteria example);

    int deleteByPrimaryKey(String discountId);

    int insert(DstGiftInfoDetail record);

    int insertSelective(DstGiftInfoDetail record);

    List<DstGiftInfoDetail> selectByExample(DstGiftInfoDetailCriteria example);

    DstGiftInfoDetail selectByPrimaryKey(String discountId);

    int updateByExampleSelective(@Param("record") DstGiftInfoDetail record, @Param("example") DstGiftInfoDetailCriteria example);

    int updateByExample(@Param("record") DstGiftInfoDetail record, @Param("example") DstGiftInfoDetailCriteria example);

    int updateByPrimaryKeySelective(DstGiftInfoDetail record);

    int updateByPrimaryKey(DstGiftInfoDetail record);
}