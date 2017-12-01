package com.ai.baas.batch.client.dao.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ai.baas.batch.client.dao.mapper.bo.BatchFailureBill;
import com.ai.baas.batch.client.dao.mapper.bo.BatchFailureBillCriteria;

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