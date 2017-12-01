package com.ai.baas.ccp.dao.mapper.interfaces;

import com.ai.baas.ccp.dao.mapper.bo.OmcCreditfee;
import com.ai.baas.ccp.dao.mapper.bo.OmcCreditfeeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmcCreditfeeMapper {
    int countByExample(OmcCreditfeeCriteria example);

    int deleteByExample(OmcCreditfeeCriteria example);

    int insert(OmcCreditfee record);

    int insertSelective(OmcCreditfee record);

    List<OmcCreditfee> selectByExample(OmcCreditfeeCriteria example);

    int updateByExampleSelective(@Param("record") OmcCreditfee record, @Param("example") OmcCreditfeeCriteria example);

    int updateByExample(@Param("record") OmcCreditfee record, @Param("example") OmcCreditfeeCriteria example);
}