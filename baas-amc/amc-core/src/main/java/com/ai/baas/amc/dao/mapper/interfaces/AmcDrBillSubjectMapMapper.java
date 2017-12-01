package com.ai.baas.amc.dao.mapper.interfaces;

import com.ai.baas.amc.dao.mapper.bo.AmcDrBillSubjectMap;
import com.ai.baas.amc.dao.mapper.bo.AmcDrBillSubjectMapCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AmcDrBillSubjectMapMapper {
    int countByExample(AmcDrBillSubjectMapCriteria example);

    int deleteByExample(AmcDrBillSubjectMapCriteria example);

    int insert(AmcDrBillSubjectMap record);

    int insertSelective(AmcDrBillSubjectMap record);

    List<AmcDrBillSubjectMap> selectByExample(AmcDrBillSubjectMapCriteria example);

    int updateByExampleSelective(@Param("record") AmcDrBillSubjectMap record, @Param("example") AmcDrBillSubjectMapCriteria example);

    int updateByExample(@Param("record") AmcDrBillSubjectMap record, @Param("example") AmcDrBillSubjectMapCriteria example);
}