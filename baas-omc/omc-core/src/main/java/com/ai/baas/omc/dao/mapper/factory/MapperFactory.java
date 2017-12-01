package com.ai.baas.omc.dao.mapper.factory;

import javax.annotation.PostConstruct;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.omc.dao.mapper.interfaces.OmcAvoidScoutMapper;
import com.ai.baas.omc.dao.mapper.interfaces.OmcScoutRuleMapper;

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
    
    /**
     * 信控规则表
     * @return
     * @author bixy
     * @ApiDocMethod
     * @ApiCode
     */
    public static OmcScoutRuleMapper getOmcScoutRuleMapper() {
        return sqlSessionTemplate.getMapper(OmcScoutRuleMapper.class);
    }
    
    /**
     * 免催停
     * @return
     */
    public static OmcAvoidScoutMapper getOmcAvoidScoutMapper(){
    	return sqlSessionTemplate.getMapper(OmcAvoidScoutMapper.class);
    }
}
