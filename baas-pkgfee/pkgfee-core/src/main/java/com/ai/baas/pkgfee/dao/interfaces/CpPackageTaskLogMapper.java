package com.ai.baas.pkgfee.dao.interfaces;

import com.ai.baas.pkgfee.dao.mapper.bo.CpPackageTaskLog;
import com.ai.baas.pkgfee.dao.mapper.bo.CpPackageTaskLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CpPackageTaskLogMapper {
    int countByExample(CpPackageTaskLogCriteria example);

    int deleteByExample(CpPackageTaskLogCriteria example);

    int deleteByPrimaryKey(Long taskId);

    int insert(CpPackageTaskLog record);

    int insertSelective(CpPackageTaskLog record);

    List<CpPackageTaskLog> selectByExample(CpPackageTaskLogCriteria example);

    CpPackageTaskLog selectByPrimaryKey(Long taskId);

    int updateByExampleSelective(@Param("record") CpPackageTaskLog record, @Param("example") CpPackageTaskLogCriteria example);

    int updateByExample(@Param("record") CpPackageTaskLog record, @Param("example") CpPackageTaskLogCriteria example);

    int updateByPrimaryKeySelective(CpPackageTaskLog record);

    int updateByPrimaryKey(CpPackageTaskLog record);
}