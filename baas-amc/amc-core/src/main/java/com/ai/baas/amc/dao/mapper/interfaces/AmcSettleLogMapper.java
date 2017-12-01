package com.ai.baas.amc.dao.mapper.interfaces;

import com.ai.baas.amc.dao.mapper.bo.AmcSettleLog;
import com.ai.baas.amc.dao.mapper.bo.AmcSettleLogCriteria;
import com.ai.baas.amc.dao.mapper.bo.AmcSettleLogKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AmcSettleLogMapper {
    int countByExample(AmcSettleLogCriteria example);

    int deleteByExample(AmcSettleLogCriteria example);

    int deleteByPrimaryKey(AmcSettleLogKey key);

    int insert(AmcSettleLog record);

    int insertSelective(AmcSettleLog record);

    List<AmcSettleLog> selectByExample(AmcSettleLogCriteria example);

    AmcSettleLog selectByPrimaryKey(AmcSettleLogKey key);

    int updateByExampleSelective(@Param("record") AmcSettleLog record, @Param("example") AmcSettleLogCriteria example);

    int updateByExample(@Param("record") AmcSettleLog record, @Param("example") AmcSettleLogCriteria example);

    int updateByPrimaryKeySelective(AmcSettleLog record);

    int updateByPrimaryKey(AmcSettleLog record);
}