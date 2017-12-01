package com.ai.baas.ccp.dao.mapper.interfaces;

import com.ai.baas.ccp.dao.mapper.bo.OmcUrgeInterface;
import com.ai.baas.ccp.dao.mapper.bo.OmcUrgeInterfaceCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmcUrgeInterfaceMapper {
    int countByExample(OmcUrgeInterfaceCriteria example);

    int deleteByExample(OmcUrgeInterfaceCriteria example);

    int insert(OmcUrgeInterface record);

    int insertSelective(OmcUrgeInterface record);

    List<OmcUrgeInterface> selectByExample(OmcUrgeInterfaceCriteria example);

    int updateByExampleSelective(@Param("record") OmcUrgeInterface record, @Param("example") OmcUrgeInterfaceCriteria example);

    int updateByExample(@Param("record") OmcUrgeInterface record, @Param("example") OmcUrgeInterfaceCriteria example);
}