package com.ai.baas.bmc.dao.interfaces;

import com.ai.baas.bmc.dao.mapper.bo.BmcFailureBill;
import com.ai.baas.bmc.dao.mapper.bo.BmcFailureBillCriteria;
import com.ai.baas.bmc.dao.mapper.bo.BmcFailureBillWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BmcFailureBillMapper {
    int countByExample(BmcFailureBillCriteria example);

    int deleteByExample(BmcFailureBillCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(BmcFailureBillWithBLOBs record);

    int insertSelective(BmcFailureBillWithBLOBs record);

    List<BmcFailureBillWithBLOBs> selectByExampleWithBLOBs(BmcFailureBillCriteria example);

    List<BmcFailureBill> selectByExample(BmcFailureBillCriteria example);

    BmcFailureBillWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BmcFailureBillWithBLOBs record, @Param("example") BmcFailureBillCriteria example);

    int updateByExampleWithBLOBs(@Param("record") BmcFailureBillWithBLOBs record, @Param("example") BmcFailureBillCriteria example);

    int updateByExample(@Param("record") BmcFailureBill record, @Param("example") BmcFailureBillCriteria example);

    int updateByPrimaryKeySelective(BmcFailureBillWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(BmcFailureBillWithBLOBs record);

    int updateByPrimaryKey(BmcFailureBill record);
}