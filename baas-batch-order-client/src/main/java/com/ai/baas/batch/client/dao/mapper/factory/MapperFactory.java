package com.ai.baas.batch.client.dao.mapper.factory;

import javax.annotation.PostConstruct;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.batch.client.dao.interfaces.BatchFailureBillMapper;
import com.ai.baas.batch.client.dao.interfaces.BlUserinfoZxMapper;
import com.ai.baas.batch.client.dao.interfaces.BmcFailureBillMapper;
import com.ai.baas.batch.client.dao.interfaces.BmcOrderLogYyyymmMapper;
import com.ai.baas.batch.client.dao.interfaces.BmcUsageLogYyyymmMapper;
import com.ai.baas.batch.client.dao.interfaces.CpCunitpriceDetailMapper;
import com.ai.baas.batch.client.dao.interfaces.CpCunitpriceInfoMapper;
import com.ai.baas.batch.client.dao.interfaces.CpExtInfoMapper;
import com.ai.baas.batch.client.dao.interfaces.CpFactorInfoMapper;
import com.ai.baas.batch.client.dao.interfaces.CpPackageInfoMapper;
import com.ai.baas.batch.client.dao.interfaces.CpPriceDetailMapper;
import com.ai.baas.batch.client.dao.interfaces.CpPriceInfoMapper;
import com.ai.baas.batch.client.dao.interfaces.CpStepInfoMapper;
import com.ai.baas.batch.client.dao.interfaces.PmBasedataCodeMapper;

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
     * 中信定制表
     * @return
     */
    public static BlUserinfoZxMapper getBlUserinfoZxMapper(){
    	return sqlSessionTemplate.getMapper(BlUserinfoZxMapper.class);
    }
    
    /**
     * 计费错单表
     * @return
     */
    public static BmcFailureBillMapper getBmcFailureBillMapper(){
    	return sqlSessionTemplate.getMapper(BmcFailureBillMapper.class);
    }
    
    /**
     * 批处理错单表
     * @return
     */
    public static BatchFailureBillMapper getBatchFailureBillMapper(){
    	return sqlSessionTemplate.getMapper(BatchFailureBillMapper.class);
    }
    /**
     * 资费信息表
     * @return
     */
    public static CpPriceInfoMapper getCpPriceInfoMapper(){
    	return sqlSessionTemplate.getMapper(CpPriceInfoMapper.class);
    }
    /**
     * 资费明细表
     * @return
     */
    public static CpPriceDetailMapper getCpPriceDetailMapper(){
    	return sqlSessionTemplate.getMapper(CpPriceDetailMapper.class);
    }
    
    /**
     * 参考因素表
     * @return
     */
    public static CpFactorInfoMapper getCpFactorInfoMapper(){
    	return sqlSessionTemplate.getMapper(CpFactorInfoMapper.class);
    }
    /**
     * 扩展表
     * @return
     */
    public static CpExtInfoMapper getCpExtInfoMapper(){
    	return sqlSessionTemplate.getMapper(CpExtInfoMapper.class);
    }
    /**
     * 复合单价明细表
     * @return
     */
    public static CpCunitpriceDetailMapper getCpCunitpriceDetailMapper(){
    	return sqlSessionTemplate.getMapper(CpCunitpriceDetailMapper.class);
    }
    /**
     * 复合单价表
     * @return
     */
    public static CpCunitpriceInfoMapper getCpCunitpriceInfoMapper(){
    	return sqlSessionTemplate.getMapper(CpCunitpriceInfoMapper.class);
    }
    /**
     * 套餐信息表
     * @return
     */
    public static CpPackageInfoMapper getCpPackageInfoMapper(){
    	return sqlSessionTemplate.getMapper(CpPackageInfoMapper.class);
    }
    /**
     * 阶梯表
     * @return
     */
    public static CpStepInfoMapper getCpStepInfoMapper(){
    	return sqlSessionTemplate.getMapper(CpStepInfoMapper.class);
    }
    /**
     * 订单日志表
     * @return
     */
    public static BmcOrderLogYyyymmMapper getBmcOrderLogYyyymmMapper(){
    	return sqlSessionTemplate.getMapper(BmcOrderLogYyyymmMapper.class);
    }
    /**
     * 使用量日志表
     * @return
     */
    public static BmcUsageLogYyyymmMapper getBmcUsageLogYyyymmMapper(){
    	return sqlSessionTemplate.getMapper(BmcUsageLogYyyymmMapper.class);
    }
    
    /**
     * 服务初始化表
     * @return
     */
    public static PmBasedataCodeMapper getPmBasedataCodeMapper(){
    	return sqlSessionTemplate.getMapper(PmBasedataCodeMapper.class);
    }
    
}
