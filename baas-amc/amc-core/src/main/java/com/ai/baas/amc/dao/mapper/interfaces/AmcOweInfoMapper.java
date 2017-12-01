package com.ai.baas.amc.dao.mapper.interfaces;

import com.ai.baas.amc.dao.mapper.bo.AmcOweInfo;
import com.ai.baas.amc.dao.mapper.bo.AmcOweInfoCriteria;
import com.ai.baas.amc.dao.mapper.bo.AmcOweInfoKey;
import com.ai.baas.amc.dao.mapper.bo.CustOweInfo;
import com.ai.baas.amc.dao.mapper.bo.CustOweInfoQueryParam;
import com.ai.baas.amc.dao.mapper.bo.OweInfoQueryParam;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface AmcOweInfoMapper {
    int countByExample(AmcOweInfoCriteria example);

    int deleteByExample(AmcOweInfoCriteria example);

    int deleteByPrimaryKey(AmcOweInfoKey key);

    int insert(AmcOweInfo record);

    int insertSelective(AmcOweInfo record);

    List<AmcOweInfo> selectByExample(AmcOweInfoCriteria example);

    AmcOweInfo selectByPrimaryKey(AmcOweInfoKey key);

    int updateByExampleSelective(@Param("record") AmcOweInfo record, @Param("example") AmcOweInfoCriteria example);

    int updateByExample(@Param("record") AmcOweInfo record, @Param("example") AmcOweInfoCriteria example);

    int updateByPrimaryKeySelective(AmcOweInfo record);

    int updateByPrimaryKey(AmcOweInfo record);
    
    List<CustOweInfo> getOweInfoListByPageInfo(CustOweInfoQueryParam param);
    
    int getOweInfoCount(CustOweInfoQueryParam param);
    
    CustOweInfo getCustOweInfo(OweInfoQueryParam param);
}