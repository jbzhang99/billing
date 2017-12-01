package com.ai.baas.ccp.dao.mapper.factory;

import javax.annotation.PostConstruct;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.ccp.dao.mapper.interfaces.NoticeRecordMapper;
import com.ai.baas.ccp.dao.mapper.interfaces.NoticeSubjectMapper;
import com.ai.baas.ccp.dao.mapper.interfaces.NoticeSubscribeMapper;
import com.ai.baas.ccp.dao.mapper.interfaces.NoticeSubscribeRecordMapper;
import com.ai.baas.ccp.dao.mapper.interfaces.OmcScoutPolicyMapper;
import com.ai.baas.ccp.dao.mapper.interfaces.StopNoticeLogMapper;

@Component
public class MapperFactory {

    @Autowired
    private transient SqlSessionTemplate sqlSessionTemplate;

    private static SqlSessionTemplate st;

    @PostConstruct
    void init() {
        setSqlSessionTemplate(sqlSessionTemplate);
    }

    public static void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        MapperFactory.st = sqlSessionTemplate;
    }

    public static OmcScoutPolicyMapper getOmcScoutPolicyMapper() {
        return st.getMapper(OmcScoutPolicyMapper.class);
    }

    public static NoticeSubjectMapper getNoticeSubjectMapper() {
        return st.getMapper(NoticeSubjectMapper.class);
    }

    public static NoticeSubscribeMapper getNoticeSubscribeMapper() {
        return st.getMapper(NoticeSubscribeMapper.class);
    }

    public static NoticeSubscribeRecordMapper getNoticeSubscribeRecordMapper() {
        return st.getMapper(NoticeSubscribeRecordMapper.class);
    }
    
    public static NoticeRecordMapper getNoticeRecordMapper() {
        return st.getMapper(NoticeRecordMapper.class);
    }

    public static StopNoticeLogMapper getStopNoticeLogMapper() {
        return st.getMapper(StopNoticeLogMapper.class);
    }
}
