package com.ai.baas.ccp.dao.mapper.interfaces;

import com.ai.baas.ccp.dao.mapper.bo.OmcPolicyPara;
import com.ai.baas.ccp.dao.mapper.bo.OmcPolicyParaCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmcPolicyParaMapper {
    int countByExample(OmcPolicyParaCriteria example);

    int deleteByExample(OmcPolicyParaCriteria example);

    int insert(OmcPolicyPara record);

    int insertSelective(OmcPolicyPara record);

    List<OmcPolicyPara> selectByExample(OmcPolicyParaCriteria example);

    int updateByExampleSelective(@Param("record") OmcPolicyPara record, @Param("example") OmcPolicyParaCriteria example);

    int updateByExample(@Param("record") OmcPolicyPara record, @Param("example") OmcPolicyParaCriteria example);
}