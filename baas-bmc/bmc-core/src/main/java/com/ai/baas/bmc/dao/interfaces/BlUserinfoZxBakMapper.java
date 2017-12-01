package com.ai.baas.bmc.dao.interfaces;

import com.ai.baas.bmc.dao.mapper.bo.BlUserinfoZxBak;
import com.ai.baas.bmc.dao.mapper.bo.BlUserinfoZxBakCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlUserinfoZxBakMapper {
    int countByExample(BlUserinfoZxBakCriteria example);

    int deleteByExample(BlUserinfoZxBakCriteria example);

    int deleteByPrimaryKey(String instanceId);

    int insert(BlUserinfoZxBak record);

    int insertSelective(BlUserinfoZxBak record);

    List<BlUserinfoZxBak> selectByExample(BlUserinfoZxBakCriteria example);

    BlUserinfoZxBak selectByPrimaryKey(String instanceId);

    int updateByExampleSelective(@Param("record") BlUserinfoZxBak record, @Param("example") BlUserinfoZxBakCriteria example);

    int updateByExample(@Param("record") BlUserinfoZxBak record, @Param("example") BlUserinfoZxBakCriteria example);

    int updateByPrimaryKeySelective(BlUserinfoZxBak record);

    int updateByPrimaryKey(BlUserinfoZxBak record);
}