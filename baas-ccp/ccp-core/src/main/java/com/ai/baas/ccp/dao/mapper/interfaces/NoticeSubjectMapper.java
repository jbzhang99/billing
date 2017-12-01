package com.ai.baas.ccp.dao.mapper.interfaces;

import com.ai.baas.ccp.dao.mapper.bo.NoticeSubject;
import com.ai.baas.ccp.dao.mapper.bo.NoticeSubjectCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NoticeSubjectMapper {
    int countByExample(NoticeSubjectCriteria example);

    int deleteByExample(NoticeSubjectCriteria example);

    int deleteByPrimaryKey(String subjectId);

    int insert(NoticeSubject record);

    int insertSelective(NoticeSubject record);

    List<NoticeSubject> selectByExample(NoticeSubjectCriteria example);

    NoticeSubject selectByPrimaryKey(String subjectId);

    int updateByExampleSelective(@Param("record") NoticeSubject record, @Param("example") NoticeSubjectCriteria example);

    int updateByExample(@Param("record") NoticeSubject record, @Param("example") NoticeSubjectCriteria example);

    int updateByPrimaryKeySelective(NoticeSubject record);

    int updateByPrimaryKey(NoticeSubject record);
}