package com.ai.baas.amc.dao.mapper.interfaces;

import com.ai.baas.amc.dao.mapper.bo.AmcCcDetail;
import com.ai.baas.amc.dao.mapper.bo.AmcCcDetailCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AmcCcDetailMapper {
    int countByExample(AmcCcDetailCriteria example);

    int deleteByExample(AmcCcDetailCriteria example);

    int insert(AmcCcDetail record);

    int insertSelective(AmcCcDetail record);

    List<AmcCcDetail> selectByExample(AmcCcDetailCriteria example);

    int updateByExampleSelective(@Param("record") AmcCcDetail record, @Param("example") AmcCcDetailCriteria example);

    int updateByExample(@Param("record") AmcCcDetail record, @Param("example") AmcCcDetailCriteria example);

    Long selectApportionmentByAcctId(AmcCcDetailCriteria condition);
}