package com.ai.opt.sys.dao.mapper.interfaces;

import com.ai.opt.sys.dao.mapper.bo.GnSubject;
import com.ai.opt.sys.dao.mapper.bo.GnSubjectCriteria;
import com.ai.opt.sys.dao.mapper.bo.GnSubjectKey;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface GnSubjectMapper {
    int countByExample(GnSubjectCriteria example);

    int deleteByExample(GnSubjectCriteria example);

    int deleteByPrimaryKey(GnSubjectKey key);

    int insert(GnSubject record);

    int insertSelective(GnSubject record);

    List<GnSubject> selectByExample(GnSubjectCriteria example);

    GnSubject selectByPrimaryKey(GnSubjectKey key);

    int updateByExampleSelective(@Param("record") GnSubject record, @Param("example") GnSubjectCriteria example);

    int updateByExample(@Param("record") GnSubject record, @Param("example") GnSubjectCriteria example);

    int updateByPrimaryKeySelective(GnSubject record);

    int updateByPrimaryKey(GnSubject record);
    
    List<GnSubject> getGnSubjectListRelated(Map<String,Object> map);
}