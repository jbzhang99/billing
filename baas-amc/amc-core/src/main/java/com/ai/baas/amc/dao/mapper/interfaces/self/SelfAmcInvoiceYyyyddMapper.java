package com.ai.baas.amc.dao.mapper.interfaces.self;


import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.ai.baas.amc.dao.mapper.bo.AmcInvoiceYyyydd;
import com.ai.baas.amc.dao.mapper.bo.AmcInvoiceYyyyddCriteria;
import com.ai.baas.amc.dao.mapper.model.AmcInvoiceSumYyyydd;


public interface SelfAmcInvoiceYyyyddMapper {
   // int countByExample(@Param("currentMonth") String currentMonth,@Param("amcInvoiceYYYYDDCriteria") AmcInvoiceYyyyddCriteria example);
    
    List<AmcInvoiceSumYyyydd> selectByExampleSum(AmcInvoiceYyyyddCriteria example);//@Param("currentMonth") String currentMonth,  

}