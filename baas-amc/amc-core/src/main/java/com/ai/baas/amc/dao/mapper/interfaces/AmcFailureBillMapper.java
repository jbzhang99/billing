package com.ai.baas.amc.dao.mapper.interfaces;

import com.ai.baas.amc.dao.mapper.bo.AmcFailureBill;
import com.ai.baas.amc.dao.mapper.bo.AmcFailureBillCriteria;
import com.ai.baas.amc.dao.mapper.bo.AmcFailureBillWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AmcFailureBillMapper {
    int countByExample(AmcFailureBillCriteria example);

    int deleteByExample(AmcFailureBillCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(AmcFailureBillWithBLOBs record);

    int insertSelective(AmcFailureBillWithBLOBs record);

    List<AmcFailureBillWithBLOBs> selectByExampleWithBLOBs(AmcFailureBillCriteria example);

    List<AmcFailureBill> selectByExample(AmcFailureBillCriteria example);

    AmcFailureBillWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AmcFailureBillWithBLOBs record, @Param("example") AmcFailureBillCriteria example);

    int updateByExampleWithBLOBs(@Param("record") AmcFailureBillWithBLOBs record, @Param("example") AmcFailureBillCriteria example);

    int updateByExample(@Param("record") AmcFailureBill record, @Param("example") AmcFailureBillCriteria example);

    int updateByPrimaryKeySelective(AmcFailureBillWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(AmcFailureBillWithBLOBs record);

    int updateByPrimaryKey(AmcFailureBill record);
}