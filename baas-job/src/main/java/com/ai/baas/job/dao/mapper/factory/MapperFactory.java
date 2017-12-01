package com.ai.baas.job.dao.mapper.factory;

import javax.annotation.PostConstruct;

import org.apache.curator.retry.RetryUntilElapsed;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.job.dao.interfaces.BlAcctInfoMapper;
import com.ai.baas.job.dao.interfaces.BlCustinfoMapper;
import com.ai.baas.job.dao.interfaces.BlSubsCommMapper;
import com.ai.baas.job.dao.interfaces.BlSubscommExtMapper;
import com.ai.baas.job.dao.interfaces.BlUserinfoMapper;
import com.ai.baas.job.dao.interfaces.CpExtInfoMapper;
import com.ai.baas.job.dao.interfaces.CpFactorInfoMapper;
import com.ai.baas.job.dao.interfaces.CpPackageInfoMapper;
import com.ai.baas.job.dao.interfaces.CpPriceDetailMapper;
import com.ai.baas.job.dao.interfaces.CpPriceInfoMapper;

@Component
public class MapperFactory {
    private MapperFactory(){}
    @Autowired
    private transient SqlSessionTemplate sqlSessionTemplate;

    private static SqlSessionTemplate st;

    @PostConstruct
    void init() {
        setSqlSessionTemplate(sqlSessionTemplate);
    }

    private static void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        MapperFactory.st = sqlSessionTemplate;
    }

    public static BlCustinfoMapper getBlCustinfoMapper(){
        return st.getMapper(BlCustinfoMapper.class);
    }
    public static BlAcctInfoMapper getBlAcctInfoMapper() {
        return st.getMapper(BlAcctInfoMapper.class);
        }
    public static BlUserinfoMapper getBlUserinfoMapper(){
        return st.getMapper(BlUserinfoMapper.class);
    }
    public static BlSubsCommMapper getBlSubsCommMapper(){
        return st.getMapper(BlSubsCommMapper.class);
    }
    public static BlSubscommExtMapper getBlSubscommExtMapper(){
        return st.getMapper(BlSubscommExtMapper.class);
    }
   public static CpPriceInfoMapper getCpPriceInfoMapper(){
       return st.getMapper(CpPriceInfoMapper.class);
   }
   public static CpPriceDetailMapper getCpPriceDetailMapper(){
       return st.getMapper(CpPriceDetailMapper.class);
   }
   public static CpPackageInfoMapper getCpPackageInfoMapper(){
       return st.getMapper(CpPackageInfoMapper.class);
   }
   public static CpExtInfoMapper getCpExtInfoMapper(){
       return st.getMapper(CpExtInfoMapper.class);
   }
   public static CpFactorInfoMapper getCpFactorInfoMapper(){
       return st.getMapper(CpFactorInfoMapper.class);
   }
   
}
