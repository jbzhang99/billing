package com.ai.runner.center.mmp.dao.interfaces;

import com.ai.runner.center.mmp.dao.mapper.bo.UmsMsgService;
import com.ai.runner.center.mmp.dao.mapper.bo.UmsMsgServiceCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMsgServiceMapper {
    int countByExample(UmsMsgServiceCriteria example);

    int deleteByExample(UmsMsgServiceCriteria example);

    int deleteByPrimaryKey(Long serviceId);

    int insert(UmsMsgService record);

    int insertSelective(UmsMsgService record);

    List<UmsMsgService> selectByExample(UmsMsgServiceCriteria example);

    UmsMsgService selectByPrimaryKey(Long serviceId);

    int updateByExampleSelective(@Param("record") UmsMsgService record, @Param("example") UmsMsgServiceCriteria example);

    int updateByExample(@Param("record") UmsMsgService record, @Param("example") UmsMsgServiceCriteria example);

    int updateByPrimaryKeySelective(UmsMsgService record);

    int updateByPrimaryKey(UmsMsgService record);
}