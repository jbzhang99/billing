package com.ai.baas.amc.dao.mapper.interfaces;

import com.ai.baas.amc.dao.mapper.bo.AmcInvoice;
import com.ai.baas.amc.dao.mapper.bo.AmcInvoiceCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AmcInvoiceMapper {
    int countByExample(AmcInvoiceCriteria example);

    int deleteByExample(AmcInvoiceCriteria example);

    int insert(AmcInvoice record);

    int insertSelective(AmcInvoice record);

    List<AmcInvoice> selectByExample(AmcInvoiceCriteria example);

    int updateByExampleSelective(@Param("record") AmcInvoice record, @Param("example") AmcInvoiceCriteria example);

    int updateByExample(@Param("record") AmcInvoice record, @Param("example") AmcInvoiceCriteria example);
}