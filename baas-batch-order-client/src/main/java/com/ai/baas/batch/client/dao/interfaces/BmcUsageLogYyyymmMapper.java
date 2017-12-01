package com.ai.baas.batch.client.dao.interfaces;

import com.ai.baas.batch.client.dao.mapper.bo.BmcUsageLogYyyymm;
import com.ai.baas.batch.client.dao.mapper.bo.BmcUsageLogYyyymmCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BmcUsageLogYyyymmMapper {
    int countByExample(BmcUsageLogYyyymmCriteria example);

    int deleteByExample(BmcUsageLogYyyymmCriteria example);

    int insert(@Param("currentMonth") String currentMonth,@Param("record")BmcUsageLogYyyymm record);

    int insertSelective(@Param("currentMonth") String currentMonth,@Param("record")BmcUsageLogYyyymm record);

    List<BmcUsageLogYyyymm> selectByExample(BmcUsageLogYyyymmCriteria example);

    int updateByExampleSelective(@Param("record") BmcUsageLogYyyymm record, @Param("example") BmcUsageLogYyyymmCriteria example);

    int updateByExample(@Param("record") BmcUsageLogYyyymm record, @Param("example") BmcUsageLogYyyymmCriteria example);
}