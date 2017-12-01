package com.ai.baas.abm.dao.mapper.factory;

import javax.annotation.PostConstruct;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.abm.dao.mapper.interfaces.AmcResBookLogMapper;
import com.ai.baas.abm.dao.mapper.interfaces.AmcResBookMapper;


@Component
public class MapperFactory {

    @Autowired
    private SqlSessionTemplate st;

    private static SqlSessionTemplate sqlSessionTemplate;

    @PostConstruct
    void init() {
        setSqlSessionTemplate(st);
    }
    
    public static void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        MapperFactory.sqlSessionTemplate = sqlSessionTemplate;
    }
    
    public static AmcResBookMapper getAmcResBookMapper(){
    	return sqlSessionTemplate.getMapper(AmcResBookMapper.class);
    }
    
    public static AmcResBookLogMapper getAmcResBookLogMapper(){
    	return sqlSessionTemplate.getMapper(AmcResBookLogMapper.class);
    }
}
