package com.ai.baas.smc.dao.mapper.factory;

import javax.annotation.PostConstruct;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.smc.dao.mapper.interfaces.StlBillDataMapper;
import com.ai.baas.smc.dao.mapper.interfaces.StlBillDetailStyleItemMapper;
import com.ai.baas.smc.dao.mapper.interfaces.StlBillItemDataMapper;
import com.ai.baas.smc.dao.mapper.interfaces.StlBillStyleItemMapper;
import com.ai.baas.smc.dao.mapper.interfaces.StlBillStyleMapper;
import com.ai.baas.smc.dao.mapper.interfaces.StlElementAttrMapper;
import com.ai.baas.smc.dao.mapper.interfaces.StlElementMapper;
import com.ai.baas.smc.dao.mapper.interfaces.StlImportLogMapper;
import com.ai.baas.smc.dao.mapper.interfaces.StlPolicyItemConditionMapper;
import com.ai.baas.smc.dao.mapper.interfaces.StlPolicyItemMapper;
import com.ai.baas.smc.dao.mapper.interfaces.StlPolicyItemPlanMapper;
import com.ai.baas.smc.dao.mapper.interfaces.StlPolicyMapper;
import com.ai.baas.smc.dao.mapper.interfaces.StlSysParamMapper;
import com.ai.baas.smc.dao.mapper.interfaces.StlTestMapper;

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

    public static StlPolicyMapper getStlPolicyMapper() {
        return st.getMapper(StlPolicyMapper.class);
    }

    public static StlElementMapper getElementMapper() {
        return st.getMapper(StlElementMapper.class);
    }

    public static StlBillStyleMapper getStlBillStyleMapper() {
        return st.getMapper(StlBillStyleMapper.class);
    }

    public static StlPolicyItemMapper getStlPolicyItemMapper() {
        return st.getMapper(StlPolicyItemMapper.class);
    }

    public static StlPolicyItemConditionMapper getStlPolicyItemConditionMapper() {
        return st.getMapper(StlPolicyItemConditionMapper.class);
    }

    public static StlPolicyItemPlanMapper getStlPolicyItemPlanMapper() {
        return st.getMapper(StlPolicyItemPlanMapper.class);
    }

    public static StlElementAttrMapper getElementAttrMapper() {
        return st.getMapper(StlElementAttrMapper.class);
    }

    public static StlBillDetailStyleItemMapper getStlBillDetailStyleItemMapper() {
        return st.getMapper(StlBillDetailStyleItemMapper.class);
    }

    public static StlBillStyleItemMapper getStlBillStyleItemMapper() {
        return st.getMapper(StlBillStyleItemMapper.class);
    }

    public static StlSysParamMapper getStlSysParamMapper() {
        return st.getMapper(StlSysParamMapper.class);
    }

    public static StlImportLogMapper getStlImportLogMapper() {
        return st.getMapper(StlImportLogMapper.class);
    }

    public static StlBillDataMapper getStlBillDataMapper() {
        return st.getMapper(StlBillDataMapper.class);
    }

    public static StlBillItemDataMapper getStlBillItemDataMapper() {
        return st.getMapper(StlBillItemDataMapper.class);
    }

    public static StlTestMapper getStlTestMapper() {
        return st.getMapper(StlTestMapper.class);
    }
}
