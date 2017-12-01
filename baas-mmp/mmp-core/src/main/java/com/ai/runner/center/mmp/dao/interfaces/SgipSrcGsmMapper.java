package com.ai.runner.center.mmp.dao.interfaces;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ai.runner.center.mmp.dao.mapper.bo.SgipSrcGsm;
import com.ai.runner.center.mmp.dao.mapper.bo.SgipSrcGsmCriteria;

public interface SgipSrcGsmMapper {
    int countByExample(SgipSrcGsmCriteria example);

    int deleteByExample(SgipSrcGsmCriteria example);

    int insert(SgipSrcGsm record);

    int insertSelective(SgipSrcGsm record);

    List<SgipSrcGsm> selectByExample(SgipSrcGsmCriteria example);

    int updateByExampleSelective(@Param("record") SgipSrcGsm record, @Param("example") SgipSrcGsmCriteria example);

    int updateByExample(@Param("record") SgipSrcGsm record, @Param("example") SgipSrcGsmCriteria example);
}