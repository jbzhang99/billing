package com.ai.runner.center.bmc.dao.interfaces;

import com.ai.runner.center.bmc.dao.mapper.bo.BlUserinfo;
import com.ai.runner.center.bmc.dao.mapper.bo.BlUserinfoCriteria;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlUserinfoMapper {
    int countByExample(BlUserinfoCriteria example);

    int deleteByExample(BlUserinfoCriteria example);

    int insert(BlUserinfo record);

    int insertSelective(BlUserinfo record);

    List<BlUserinfo> selectByExample(BlUserinfoCriteria example);

    int updateByExampleSelective(@Param("record") BlUserinfo record, @Param("example") BlUserinfoCriteria example);

    int updateByExample(@Param("record") BlUserinfo record, @Param("example") BlUserinfoCriteria example);
}