package com.ai.baas.amc.dao.mapper.interfaces;

import com.ai.baas.amc.dao.mapper.bo.AmcInvoiceInfoDetail;
import com.ai.baas.amc.dao.mapper.bo.AmcInvoiceLog;
import com.ai.baas.amc.dao.mapper.bo.AmcInvoiceLogCriteria;
import com.ai.baas.amc.dao.mapper.bo.AmcInvoiceLogDetail;
import com.ai.baas.amc.dao.mapper.bo.AmcInvoiceLogKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AmcInvoiceLogMapper {
    int countByExample(AmcInvoiceLogCriteria example);

    int deleteByExample(AmcInvoiceLogCriteria example);

    int deleteByPrimaryKey(AmcInvoiceLogKey key);

    int insert(AmcInvoiceLog record);

    int insertSelective(AmcInvoiceLog record);

    List<AmcInvoiceLog> selectByExample(AmcInvoiceLogCriteria example);

    AmcInvoiceLog selectByPrimaryKey(AmcInvoiceLogKey key);

    int updateByExampleSelective(@Param("record") AmcInvoiceLog record, @Param("example") AmcInvoiceLogCriteria example);

    int updateByExample(@Param("record") AmcInvoiceLog record, @Param("example") AmcInvoiceLogCriteria example);

    int updateByPrimaryKeySelective(AmcInvoiceLog record);

    int updateByPrimaryKey(AmcInvoiceLog record);
    
    List<AmcInvoiceLogDetail> selectByQueryParam(@Param("example")AmcInvoiceLogCriteria example, @Param("tableMonth")List<String> tableMonth);
    
    int countByQueryParam(@Param("example")AmcInvoiceLogCriteria example, @Param("tableMonth")List<String> tableMonth);
    
    /**
     * 查询发票开具记录详细信息
     * @param example
     * @param tableMonth
     * @return
     */
    List<AmcInvoiceInfoDetail> selectInfoDetailByQueryParam(@Param("example")AmcInvoiceLogCriteria example, @Param("tableMonth")List<String> tableMonth);
}