package com.ai.baas.amc.dao.mapper.interfaces;

import com.ai.baas.amc.dao.mapper.bo.AmcInvoiceInfo;
import com.ai.baas.amc.dao.mapper.bo.AmcInvoiceInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AmcInvoiceInfoMapper {
    int countByExample(AmcInvoiceInfoCriteria example);

    int deleteByExample(AmcInvoiceInfoCriteria example);

    int deleteByPrimaryKey(String invoiceInfoId);

    int insert(AmcInvoiceInfo record);

    int insertSelective(AmcInvoiceInfo record);

    List<AmcInvoiceInfo> selectByExample(AmcInvoiceInfoCriteria example);

    AmcInvoiceInfo selectByPrimaryKey(String invoiceInfoId);

    int updateByExampleSelective(@Param("record") AmcInvoiceInfo record, @Param("example") AmcInvoiceInfoCriteria example);

    int updateByExample(@Param("record") AmcInvoiceInfo record, @Param("example") AmcInvoiceInfoCriteria example);

    int updateByPrimaryKeySelective(AmcInvoiceInfo record);

    int updateByPrimaryKey(AmcInvoiceInfo record);
}