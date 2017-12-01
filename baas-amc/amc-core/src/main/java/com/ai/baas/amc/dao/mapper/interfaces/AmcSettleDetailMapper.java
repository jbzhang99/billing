package com.ai.baas.amc.dao.mapper.interfaces;

import com.ai.baas.amc.dao.mapper.bo.AmcSettleDetail;
import com.ai.baas.amc.dao.mapper.bo.AmcSettleDetailCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AmcSettleDetailMapper {
    int countByExample(AmcSettleDetailCriteria example);

    int deleteByExample(AmcSettleDetailCriteria example);

    int insert(AmcSettleDetail record);

    int insertSelective(AmcSettleDetail record);

    List<AmcSettleDetail> selectByExample(AmcSettleDetailCriteria example);

    int updateByExampleSelective(@Param("record") AmcSettleDetail record, @Param("example") AmcSettleDetailCriteria example);

    int updateByExample(@Param("record") AmcSettleDetail record, @Param("example") AmcSettleDetailCriteria example);

    Long selectProceeds(AmcSettleDetailCriteria example);
}