package com.ai.baas.pkgfee.dao.mapper.interfaces;

import com.ai.baas.pkgfee.dao.mapper.bo.DstCouponInfo;
import com.ai.baas.pkgfee.dao.mapper.bo.DstCouponInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DstCouponInfoMapper {
    int countByExample(DstCouponInfoCriteria example);

    int deleteByExample(DstCouponInfoCriteria example);

    int deleteByPrimaryKey(String couponId);

    int insert(DstCouponInfo record);

    int insertSelective(DstCouponInfo record);

    List<DstCouponInfo> selectByExample(DstCouponInfoCriteria example);

    DstCouponInfo selectByPrimaryKey(String couponId);

    int updateByExampleSelective(@Param("record") DstCouponInfo record, @Param("example") DstCouponInfoCriteria example);

    int updateByExample(@Param("record") DstCouponInfo record, @Param("example") DstCouponInfoCriteria example);

    int updateByPrimaryKeySelective(DstCouponInfo record);

    int updateByPrimaryKey(DstCouponInfo record);
}