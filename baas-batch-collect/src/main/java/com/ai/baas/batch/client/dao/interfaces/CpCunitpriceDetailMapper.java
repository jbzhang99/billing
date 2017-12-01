package com.ai.baas.batch.client.dao.interfaces;


import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.ai.baas.batch.client.dao.mapper.bo.CpCunitpriceDetail;
import com.ai.baas.batch.client.dao.mapper.bo.CpCunitpriceDetailCriteria;

public interface CpCunitpriceDetailMapper {
    int countByExample(CpCunitpriceDetailCriteria example);

    int deleteByExample(CpCunitpriceDetailCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(CpCunitpriceDetail record);

    int insertSelective(CpCunitpriceDetail record);

    List<CpCunitpriceDetail> selectByExample(CpCunitpriceDetailCriteria example);

    CpCunitpriceDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CpCunitpriceDetail record, @Param("example") CpCunitpriceDetailCriteria example);

    int updateByExample(@Param("record") CpCunitpriceDetail record, @Param("example") CpCunitpriceDetailCriteria example);

    int updateByPrimaryKeySelective(CpCunitpriceDetail record);

    int updateByPrimaryKey(CpCunitpriceDetail record);
}