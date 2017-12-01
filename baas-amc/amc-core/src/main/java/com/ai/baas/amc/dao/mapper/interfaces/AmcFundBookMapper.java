package com.ai.baas.amc.dao.mapper.interfaces;

import com.ai.baas.amc.dao.mapper.bo.AmcFundBook;
import com.ai.baas.amc.dao.mapper.bo.AmcFundBookCriteria;
import com.ai.baas.amc.dao.mapper.bo.CustBalanceInfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface AmcFundBookMapper {
    int countByExample(AmcFundBookCriteria example);

    int deleteByExample(AmcFundBookCriteria example);

    int deleteByPrimaryKey(Long bookId);

    int insert(AmcFundBook record);

    int insertSelective(AmcFundBook record);

    List<AmcFundBook> selectByExample(AmcFundBookCriteria example);

    AmcFundBook selectByPrimaryKey(Long bookId);

    int updateByExampleSelective(@Param("record") AmcFundBook record, @Param("example") AmcFundBookCriteria example);

    int updateByExample(@Param("record") AmcFundBook record, @Param("example") AmcFundBookCriteria example);

    int updateByPrimaryKeySelective(AmcFundBook record);

    int updateByPrimaryKey(AmcFundBook record);
    
    List<CustBalanceInfo> getCustBalanceInfoListByPageInfo(AmcFundBookCriteria example);
    
    int getCustBalanceInfoCount(AmcFundBookCriteria example);
}    