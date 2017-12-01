package com.ai.baas.amc.dao.mapper.interfaces;

import com.ai.baas.amc.dao.mapper.bo.AmcInvoiceYyyydd;
import com.ai.baas.amc.dao.mapper.bo.AmcInvoiceYyyyddCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AmcInvoiceYyyyddMapper {
    int countByExample(AmcInvoiceYyyyddCriteria example);

    int deleteByExample(AmcInvoiceYyyyddCriteria example);

    int insert(AmcInvoiceYyyydd record);

    int insertSelective(AmcInvoiceYyyydd record);

    List<AmcInvoiceYyyydd> selectByExample(AmcInvoiceYyyyddCriteria example);

    int updateByExampleSelective(@Param("record") AmcInvoiceYyyydd record, @Param("example") AmcInvoiceYyyyddCriteria example);

    int updateByExample(@Param("record") AmcInvoiceYyyydd record, @Param("example") AmcInvoiceYyyyddCriteria example);
}