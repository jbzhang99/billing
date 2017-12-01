package com.ai.baas.pkgfee.dao.mapper.factory;

import javax.annotation.PostConstruct;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.pkgfee.dao.mapper.interfaces.BlSubsCommMapper;
import com.ai.baas.pkgfee.dao.mapper.interfaces.CpPackageFeeMapper;
import com.ai.baas.pkgfee.dao.mapper.interfaces.CpPackageTaskLogMapper;
import com.ai.baas.pkgfee.dao.mapper.interfaces.DstCouponInfoMapper;
import com.ai.baas.pkgfee.dao.mapper.interfaces.DstDiscountExtMapper;
import com.ai.baas.pkgfee.dao.mapper.interfaces.DstDiscountInfoMapper;


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
     * 包费计算
     * @return
     */
    public static CpPackageFeeMapper getCpPackageFeeMapper(){
    	return sqlSessionTemplate.getMapper(CpPackageFeeMapper.class);
    }
   
    /**
     * 
     * @return BlSubsCommMapper
     */
    public static BlSubsCommMapper getBlSubsCommInfoMapper(){
    	return sqlSessionTemplate.getMapper(BlSubsCommMapper.class);
    }
    
    /**
     * 
     * @return CpPackageTaskLogMapper
     */
    public static CpPackageTaskLogMapper getCpPackageTaskLogMapper(){
    	return sqlSessionTemplate.getMapper(CpPackageTaskLogMapper.class);
    }
    
    
    public static DstDiscountInfoMapper getDiscountInfoMapper(){
    	return sqlSessionTemplate.getMapper(DstDiscountInfoMapper.class);
    }
    
    public static DstDiscountExtMapper getDiscountExtMapper(){
    	return sqlSessionTemplate.getMapper(DstDiscountExtMapper.class);
    }
    
    public static DstCouponInfoMapper getDstCouponInfoMapper(){
    	return sqlSessionTemplate.getMapper(DstCouponInfoMapper.class);
    }
    
}
