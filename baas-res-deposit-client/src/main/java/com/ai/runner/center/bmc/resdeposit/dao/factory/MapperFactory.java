package com.ai.runner.center.bmc.resdeposit.dao.factory;

import javax.annotation.PostConstruct;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ai.runner.center.bmc.resdeposit.dao.interfaces.BlUserinfoMapper;
import com.ai.runner.center.bmc.resdeposit.dao.interfaces.FailMsgLogMapper;

@Component
public class MapperFactory {

    @Autowired
    private transient SqlSessionTemplate st;

    private static SqlSessionTemplate sqlSessionTemplate;

    @PostConstruct
    void init() {
        setSqlSessionTemplate(st);
    }

    public static void setSqlSessionTemplate(SqlSessionTemplate st) {
        MapperFactory.sqlSessionTemplate = st;
    }

    public static BlUserinfoMapper getBlUserinfoMapper() {
        return sqlSessionTemplate.getMapper(BlUserinfoMapper.class);
    }
    
    public static FailMsgLogMapper getFailMsgLogMapper() {
        return sqlSessionTemplate.getMapper(FailMsgLogMapper.class);
    }

}
