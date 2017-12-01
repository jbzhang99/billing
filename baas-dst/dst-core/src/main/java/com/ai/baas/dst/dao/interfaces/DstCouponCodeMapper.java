package com.ai.baas.dst.dao.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ai.baas.dst.api.coupon.params.MyOwnCodeReq;
import com.ai.baas.dst.dao.mapper.bo.CouponCode;
import com.ai.baas.dst.dao.mapper.bo.DstCouponCode;
import com.ai.baas.dst.dao.mapper.bo.DstCouponCodeCriteria;
import com.ai.baas.dst.dao.mapper.bo.DstMyOwnCouponCodeInfo;
import com.ai.baas.dst.dao.mapper.bo.OPCouponCode;
import com.ai.baas.dst.dao.mapper.bo.QDCouponCode;
import com.ai.baas.dst.dao.mapper.bo.QDCouponCodes;

public interface DstCouponCodeMapper {
    int countByExample(DstCouponCodeCriteria example);

    int deleteByExample(DstCouponCodeCriteria example);

    int deleteByPrimaryKey(String codeId);

    int insert(DstCouponCode record);

    int insertSelective(DstCouponCode record);

    List<DstCouponCode> selectByExample(DstCouponCodeCriteria example);

    DstCouponCode selectByPrimaryKey(String codeId);

    int updateByExampleSelective(@Param("record") DstCouponCode record, @Param("example") DstCouponCodeCriteria example);

    int updateByExample(@Param("record") DstCouponCode record, @Param("example") DstCouponCodeCriteria example);

    int updateByPrimaryKeySelective(DstCouponCode record);

    int updateByPrimaryKey(DstCouponCode record);
    //自己添加的sql
    List<CouponCode> selectAggreByExample(OPCouponCode example);
    
    int countByAgreeExample(OPCouponCode example);
    
    List<DstMyOwnCouponCodeInfo> getMyOwnCouponCode(MyOwnCodeReq req);
    List<QDCouponCodes> getExistsCouponCode(QDCouponCode example);
   
    int getExistsCouponCodeCount(QDCouponCode example);
    
	List<CouponCode> selectChannelInfoByExample(OPCouponCode example);

	List<QDCouponCodes> getExistsCouponCodeList(QDCouponCode code);

}