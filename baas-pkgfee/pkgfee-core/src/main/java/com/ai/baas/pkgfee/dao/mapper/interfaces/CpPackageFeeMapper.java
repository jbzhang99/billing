package com.ai.baas.pkgfee.dao.mapper.interfaces;

import com.ai.baas.pkgfee.dao.mapper.bo.CpPackageFee;
import com.ai.baas.pkgfee.dao.mapper.bo.CpPackageFeeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CpPackageFeeMapper {
    int countByExample(CpPackageFeeCriteria example);

    int deleteByExample(CpPackageFeeCriteria example);

    int deleteByPrimaryKey(Long packageId);

    int insert(CpPackageFee record);

    int insertSelective(CpPackageFee record);

    List<CpPackageFee> selectByExampleWithBLOBs(CpPackageFeeCriteria example);

    List<CpPackageFee> selectByExample(CpPackageFeeCriteria example);

    CpPackageFee selectByPrimaryKey(Long packageId);

    int updateByExampleSelective(@Param("record") CpPackageFee record, @Param("example") CpPackageFeeCriteria example);

    int updateByExampleWithBLOBs(@Param("record") CpPackageFee record, @Param("example") CpPackageFeeCriteria example);

    int updateByExample(@Param("record") CpPackageFee record, @Param("example") CpPackageFeeCriteria example);

    int updateByPrimaryKeySelective(CpPackageFee record);

    int updateByPrimaryKeyWithBLOBs(CpPackageFee record);

    int updateByPrimaryKey(CpPackageFee record);
}