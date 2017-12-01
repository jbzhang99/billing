package com.ai.baas.ccp.dao.mapper.interfaces;

import com.ai.baas.ccp.dao.mapper.bo.OmcBmsInterface;
import com.ai.baas.ccp.dao.mapper.bo.OmcBmsInterfaceCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmcBmsInterfaceMapper {
    int countByExample(OmcBmsInterfaceCriteria example);

    int deleteByExample(OmcBmsInterfaceCriteria example);

    int insert(OmcBmsInterface record);

    int insertSelective(OmcBmsInterface record);

    List<OmcBmsInterface> selectByExample(OmcBmsInterfaceCriteria example);

    int updateByExampleSelective(@Param("record") OmcBmsInterface record, @Param("example") OmcBmsInterfaceCriteria example);

    int updateByExample(@Param("record") OmcBmsInterface record, @Param("example") OmcBmsInterfaceCriteria example);
}