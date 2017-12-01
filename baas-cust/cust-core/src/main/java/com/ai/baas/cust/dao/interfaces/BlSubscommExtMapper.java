package com.ai.baas.cust.dao.interfaces;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.ai.baas.cust.dao.mapper.bo.BlSubscommExt;
import com.ai.baas.cust.dao.mapper.bo.BlSubscommExtCriteria;

public interface BlSubscommExtMapper {
    int countByExample(BlSubscommExtCriteria example);

    int deleteByExample(BlSubscommExtCriteria example);

    int deleteByPrimaryKey(Integer extId);

    int insert(BlSubscommExt record);

    int insertSelective(BlSubscommExt record);

    List<BlSubscommExt> selectByExample(BlSubscommExtCriteria example);

    BlSubscommExt selectByPrimaryKey(Integer extId);

    int updateByExampleSelective(@Param("record") BlSubscommExt record, @Param("example") BlSubscommExtCriteria example);

    int updateByExample(@Param("record") BlSubscommExt record, @Param("example") BlSubscommExtCriteria example);

    int updateByPrimaryKeySelective(BlSubscommExt record);

    int updateByPrimaryKey(BlSubscommExt record);
}