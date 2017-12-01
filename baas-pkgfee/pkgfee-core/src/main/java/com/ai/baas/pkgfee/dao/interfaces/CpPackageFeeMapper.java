package com.ai.baas.pkgfee.dao.interfaces;

import com.ai.baas.pkgfee.dao.mapper.bo.CpPackageFee;
import com.ai.baas.pkgfee.dao.mapper.bo.CpPackageFeeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CpPackageFeeMapper {
    int countByExample(CpPackageFeeCriteria example);

    int deleteByExample(CpPackageFeeCriteria example);

    int insert(CpPackageFee record);

    int insertSelective(CpPackageFee record);

    List<CpPackageFee> selectByExample(CpPackageFeeCriteria example);

    int updateByExampleSelective(@Param("record") CpPackageFee record, @Param("example") CpPackageFeeCriteria example);

    int updateByExample(@Param("record") CpPackageFee record, @Param("example") CpPackageFeeCriteria example);
}