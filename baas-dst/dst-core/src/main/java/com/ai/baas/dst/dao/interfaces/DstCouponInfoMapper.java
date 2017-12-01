package com.ai.baas.dst.dao.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ai.baas.dst.api.coupon.params.ChannelCodeReq;
import com.ai.baas.dst.api.coupon.params.OPCouponCodeReq;
import com.ai.baas.dst.dao.mapper.bo.DstCouponAuditInfo;
import com.ai.baas.dst.dao.mapper.bo.DstCouponInfo;
import com.ai.baas.dst.dao.mapper.bo.DstCouponInfoAndCode;
import com.ai.baas.dst.dao.mapper.bo.DstCouponInfoCriteria;
import com.ai.baas.dst.dao.mapper.bo.DstExportCouponAndCode;

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
    
    List<DstCouponInfoAndCode> queryCouponInfoAndCode(OPCouponCodeReq req);
    
    int queryCouponInfoAndCodeCount(OPCouponCodeReq req);
    
    List<DstExportCouponAndCode> queryExportCouponAndCode(ChannelCodeReq req);
    
    List<DstCouponInfoAndCode> getExportCouponCode(OPCouponCodeReq req); 
    
    List<DstCouponInfo> selectCouponAuditInfo(DstCouponAuditInfo req);

	int getCouponAuditCount(DstCouponAuditInfo req); 
}