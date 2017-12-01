package com.ai.opt.sys.dao.mapper.factory;

import javax.annotation.PostConstruct;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.sys.dao.mapper.interfaces.GnFuncMapper;
import com.ai.opt.sys.dao.mapper.interfaces.GnIndustryMapper;
import com.ai.opt.sys.dao.mapper.interfaces.GnRoleFuncRelMapper;
import com.ai.opt.sys.dao.mapper.interfaces.GnSubjectFundMapper;
import com.ai.opt.sys.dao.mapper.interfaces.GnSubjectMapper;
import com.ai.opt.sys.dao.mapper.interfaces.GnTenantMapper;

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
    
    public static GnSubjectMapper getGnSubjectMapper() {
        return sqlSessionTemplate.getMapper(GnSubjectMapper.class);
    }

    public static GnSubjectFundMapper getGnSubjectFundMapper() {
        return sqlSessionTemplate.getMapper(GnSubjectFundMapper.class);
    }
    public static GnFuncMapper getGnFuncMapper() {
        return sqlSessionTemplate.getMapper(GnFuncMapper.class);
    }
    public static GnRoleFuncRelMapper getGnRoleFuncRelMapper() {
        return sqlSessionTemplate.getMapper(GnRoleFuncRelMapper.class);
    }

	public static GnTenantMapper getGnTenantMapper() {
		return sqlSessionTemplate.getMapper(GnTenantMapper.class);
	}

	public static GnIndustryMapper getGnIndustryMapper() {
		return sqlSessionTemplate.getMapper(GnIndustryMapper.class);
	}
}
