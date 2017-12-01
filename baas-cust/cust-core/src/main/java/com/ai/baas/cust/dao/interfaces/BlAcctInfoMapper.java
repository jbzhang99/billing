package com.ai.baas.cust.dao.interfaces;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.ai.baas.cust.dao.mapper.bo.BlAcctInfo;
import com.ai.baas.cust.dao.mapper.bo.BlAcctInfoCriteria;

public interface BlAcctInfoMapper {
    int countByExample(BlAcctInfoCriteria example);

    int deleteByExample(BlAcctInfoCriteria example);

    int deleteByPrimaryKey(String acctId);

    int insert(BlAcctInfo record);

    int insertSelective(BlAcctInfo record);

    List<BlAcctInfo> selectByExample(BlAcctInfoCriteria example);

    BlAcctInfo selectByPrimaryKey(String acctId);

    int updateByExampleSelective(@Param("record") BlAcctInfo record, @Param("example") BlAcctInfoCriteria example);

    int updateByExample(@Param("record") BlAcctInfo record, @Param("example") BlAcctInfoCriteria example);

    int updateByPrimaryKeySelective(BlAcctInfo record);

    int updateByPrimaryKey(BlAcctInfo record);
}