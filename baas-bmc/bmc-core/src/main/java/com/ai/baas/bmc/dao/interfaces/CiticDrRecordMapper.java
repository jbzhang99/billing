package com.ai.baas.bmc.dao.interfaces;

import com.ai.baas.bmc.dao.mapper.bo.CiticDrRecord;
import com.ai.baas.bmc.dao.mapper.bo.CiticDrRecordCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CiticDrRecordMapper {

    int countByExample(CiticDrRecordCriteria example);

    List<CiticDrRecord> selectByExample(CiticDrRecordCriteria example);

    int getTableNum(@Param("tableName")String tableName);

}