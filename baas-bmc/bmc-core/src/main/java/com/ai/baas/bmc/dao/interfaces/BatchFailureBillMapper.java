package com.ai.baas.bmc.dao.interfaces;

import com.ai.baas.bmc.dao.mapper.bo.BatchFailureBill;
import com.ai.baas.bmc.dao.mapper.bo.BatchFailureBillCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BatchFailureBillMapper {
    int countByExample(BatchFailureBillCriteria example);

    int deleteByExample(BatchFailureBillCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(BatchFailureBill record);

    int insertSelective(BatchFailureBill record);

    List<BatchFailureBill> selectByExampleWithBLOBs(BatchFailureBillCriteria example);

    List<BatchFailureBill> selectByExample(BatchFailureBillCriteria example);

    BatchFailureBill selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BatchFailureBill record, @Param("example") BatchFailureBillCriteria example);

    int updateByExampleWithBLOBs(@Param("record") BatchFailureBill record, @Param("example") BatchFailureBillCriteria example);

    int updateByExample(@Param("record") BatchFailureBill record, @Param("example") BatchFailureBillCriteria example);

    int updateByPrimaryKeySelective(BatchFailureBill record);

    int updateByPrimaryKeyWithBLOBs(BatchFailureBill record);

    int updateByPrimaryKey(BatchFailureBill record);
}