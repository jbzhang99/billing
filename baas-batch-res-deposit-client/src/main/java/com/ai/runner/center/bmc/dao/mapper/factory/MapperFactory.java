package com.ai.runner.center.bmc.dao.mapper.factory;

import javax.annotation.PostConstruct;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


import com.ai.runner.center.bmc.dao.interfaces.BlUserinfoMapper;



@Component
public class MapperFactory {

    @Autowired
//    @Qualifier("sqlSessionTemplate2")
    private SqlSessionTemplate st;

    private static SqlSessionTemplate sqlSessionTemplate;

    @PostConstruct
    void init() {
        setSqlSessionTemplate(st);
    }
    
    public static void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        MapperFactory.sqlSessionTemplate = sqlSessionTemplate;
    }


    public static BlUserinfoMapper getBlUserinfoMapper() {
        return sqlSessionTemplate.getMapper(BlUserinfoMapper.class);
    }    
}
