package com.ai.runner.center.mmp.dao.interfaces;

import com.ai.runner.center.mmp.dao.mapper.bo.UmsMsgTemplate;
import com.ai.runner.center.mmp.dao.mapper.bo.UmsMsgTemplateCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMsgTemplateMapper {
    int countByExample(UmsMsgTemplateCriteria example);

    int deleteByExample(UmsMsgTemplateCriteria example);

    int deleteByPrimaryKey(Long sequenceId);

    int insert(UmsMsgTemplate record);

    int insertSelective(UmsMsgTemplate record);

    List<UmsMsgTemplate> selectByExample(UmsMsgTemplateCriteria example);

    UmsMsgTemplate selectByPrimaryKey(Long sequenceId);

    int updateByExampleSelective(@Param("record") UmsMsgTemplate record, @Param("example") UmsMsgTemplateCriteria example);

    int updateByExample(@Param("record") UmsMsgTemplate record, @Param("example") UmsMsgTemplateCriteria example);

    int updateByPrimaryKeySelective(UmsMsgTemplate record);

    int updateByPrimaryKey(UmsMsgTemplate record);
}