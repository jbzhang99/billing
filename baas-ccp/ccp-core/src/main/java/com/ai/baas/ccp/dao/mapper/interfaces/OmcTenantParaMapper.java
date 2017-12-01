package com.ai.baas.ccp.dao.mapper.interfaces;

import com.ai.baas.ccp.dao.mapper.bo.OmcTenantPara;
import com.ai.baas.ccp.dao.mapper.bo.OmcTenantParaCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmcTenantParaMapper {
    int countByExample(OmcTenantParaCriteria example);

    int deleteByExample(OmcTenantParaCriteria example);

    int insert(OmcTenantPara record);

    int insertSelective(OmcTenantPara record);

    List<OmcTenantPara> selectByExample(OmcTenantParaCriteria example);

    int updateByExampleSelective(@Param("record") OmcTenantPara record, @Param("example") OmcTenantParaCriteria example);

    int updateByExample(@Param("record") OmcTenantPara record, @Param("example") OmcTenantParaCriteria example);
}