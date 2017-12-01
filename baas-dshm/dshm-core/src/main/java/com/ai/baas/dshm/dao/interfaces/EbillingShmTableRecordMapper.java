package com.ai.baas.dshm.dao.interfaces;

import com.ai.baas.dshm.dto.EbillingShmTableRecord;
import com.ai.baas.dshm.dto.EbillingShmTableRecordCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EbillingShmTableRecordMapper {
    int countByExample(EbillingShmTableRecordCriteria example);

    int deleteByExample(EbillingShmTableRecordCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(EbillingShmTableRecord record);

    int insertSelective(EbillingShmTableRecord record);

    List<EbillingShmTableRecord> selectByExample(EbillingShmTableRecordCriteria example);

    EbillingShmTableRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EbillingShmTableRecord record, @Param("example") EbillingShmTableRecordCriteria example);

    int updateByExample(@Param("record") EbillingShmTableRecord record, @Param("example") EbillingShmTableRecordCriteria example);

    int updateByPrimaryKeySelective(EbillingShmTableRecord record);

    int updateByPrimaryKey(EbillingShmTableRecord record);
    
    int maxById();
}