package com.ai.baas.prd.dao.mapper.factory;

import javax.annotation.PostConstruct;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.prd.dao.mapper.interfaces.BmcBasedataCodeMapper;
import com.ai.baas.prd.dao.mapper.interfaces.CpPricemakingFactorMapper;
import com.ai.baas.prd.dao.mapper.interfaces.CpPricemakingMappingMapper;
import com.ai.baas.prd.dao.mapper.interfaces.CpPricemakingRuleMapper;
import com.ai.baas.prd.dao.mapper.interfaces.PmCatalogInfoMapper;
import com.ai.baas.prd.dao.mapper.interfaces.PmCategoryInfoHisMapper;
import com.ai.baas.prd.dao.mapper.interfaces.PmCategoryInfoMapper;
import com.ai.baas.prd.dao.mapper.interfaces.PmDimensionBranchHisMapper;
import com.ai.baas.prd.dao.mapper.interfaces.PmDimensionBranchMapper;
import com.ai.baas.prd.dao.mapper.interfaces.PmDimensionInfoHisMapper;
import com.ai.baas.prd.dao.mapper.interfaces.PmDimensionInfoMapper;
import com.ai.baas.prd.dao.mapper.interfaces.PmPolicyDetailMapper;
import com.ai.baas.prd.dao.mapper.interfaces.PmPolicyFactorMapper;
import com.ai.baas.prd.dao.mapper.interfaces.PmPolicyInfoMapper;
import com.ai.baas.prd.dao.mapper.interfaces.PmPolicyVariableMapper;
import com.ai.baas.prd.dao.mapper.interfaces.PmSpecTypeMapper;

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
     * 定价策略信息
     * @return
     */
    public static PmPolicyInfoMapper getPmPolicyInfoMapper(){
    	return sqlSessionTemplate.getMapper(PmPolicyInfoMapper.class);
    }
    
    /**
     * 定价策略变量表
     * @return
     */
    public static PmPolicyVariableMapper getPmPolicyVariableMapper(){
    	return sqlSessionTemplate.getMapper(PmPolicyVariableMapper.class);
    }
    
    /**
     * 定价策略明细表
     * @return
     */
    public static PmPolicyDetailMapper getPmPolicyDetailMapper(){
    	return sqlSessionTemplate.getMapper(PmPolicyDetailMapper.class);
    }
    
    /**
     * 定价策略因子
     * @return
     */
    public static PmPolicyFactorMapper getPmPolicyFactorMapper(){
    	return sqlSessionTemplate.getMapper(PmPolicyFactorMapper.class);
    }
    
    /**
     *维度表mapper
     * @return
    
     */
    public static PmDimensionInfoMapper getPmDimensionInfoMapper() {
		return sqlSessionTemplate.getMapper(PmDimensionInfoMapper.class);
	}
    /**
     * 分支
     * @return
     
     */
    public static PmDimensionBranchMapper getPmDimensionBranchMapper() {
		return sqlSessionTemplate.getMapper(PmDimensionBranchMapper.class);
	}
    /**
     * 产品类目信息表
     * @return
     *
     */
    public static PmCategoryInfoMapper getPmCategoryInfoMapper() {
		return sqlSessionTemplate.getMapper(PmCategoryInfoMapper.class);
	}
    
    /**
     * 定价因子构成
     * @return
     */
    public static CpPricemakingFactorMapper getCpPricemakingFactorMapper(){
    	return sqlSessionTemplate.getMapper(CpPricemakingFactorMapper.class);
    }
    
    /**
     * 定价规则
     * @return
     */
    public static CpPricemakingRuleMapper getCpPricemakingRuleMapper(){
    	return sqlSessionTemplate.getMapper(CpPricemakingRuleMapper.class);
    }
    
    /**
     * 产品目录信息表
     * @return
     */
    public static PmCatalogInfoMapper getPmCatalogInfoMapper() {
        return sqlSessionTemplate.getMapper(PmCatalogInfoMapper.class);
    }
    /**
     *维度表His mapper
     * @return
    
     */
    public static PmDimensionInfoHisMapper getPmDimensionInfoHisMapper() {
		return sqlSessionTemplate.getMapper(PmDimensionInfoHisMapper.class);
	}
    /**
     * 分支His
     * @return
     
     */
    public static PmDimensionBranchHisMapper getPmDimensionBranchHisMapper() {
		return sqlSessionTemplate.getMapper(PmDimensionBranchHisMapper.class);
	}
    /**
     * 产品类目信息表His
     * @return
     *
     */
    public static PmCategoryInfoHisMapper getPmCategoryInfoHisMapper() {
		return sqlSessionTemplate.getMapper(PmCategoryInfoHisMapper.class);
	}
    
    /**
     * 产品规格
     * @return
     */
    public static PmSpecTypeMapper getPmSpecTypeMapper() {
		return sqlSessionTemplate.getMapper(PmSpecTypeMapper.class);
	}
    
    /**
     * 定价规则映射
     * @return
     */
    public static CpPricemakingMappingMapper getCpPricemakingMappingMapper(){
    	return sqlSessionTemplate.getMapper(CpPricemakingMappingMapper.class);
    }
    
   public static BmcBasedataCodeMapper getBmcBasedataCodeMapper(){
	   return sqlSessionTemplate.getMapper(BmcBasedataCodeMapper.class);  
   }
}
