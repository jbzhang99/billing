package com.ai.baas.amc.dao.mapper.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ai.baas.amc.dao.mapper.bo.AmcChargeYyyydd;
import com.ai.baas.amc.dao.mapper.bo.AmcChargeYyyyddCriteria;

public interface AmcChargeYyyyddMapper {
    int countByExample(AmcChargeYyyyddCriteria example);

    int deleteByExample(AmcChargeYyyyddCriteria example);

    int insert(AmcChargeYyyydd record);

    int insertSelective(AmcChargeYyyydd record);

    List<AmcChargeYyyydd> selectByExample(AmcChargeYyyyddCriteria example);

    int updateByExampleSelective(@Param("record") AmcChargeYyyydd record, @Param("example") AmcChargeYyyyddCriteria example);

    int updateByExample(@Param("record") AmcChargeYyyydd record, @Param("example") AmcChargeYyyyddCriteria example);
    
    @Select({"SELECT IFNULL(sum(total_amount),0) FROM amc_charge_${billMonth} WHERE tenant_id = #{tenantId} and acct_id <> #{acctId}"})
    public long getSumTotalAmount(@Param("tenantId") String tenantId,@Param("billMonth") String billMonth,@Param("acctId") String acctId);
    
    @Select({"SELECT IFNULL(sum(dr_total_amount),0) FROM amc_charge_${billMonth} WHERE tenant_id = #{tenantId} and acct_id <> #{acctId}"})
    public long getDrSumTotalAmount(@Param("tenantId") String tenantId,@Param("billMonth") String billMonth,@Param("acctId") String acctId);
    
    @Select({"SELECT IFNULL(sum(total_amount),0) FROM amc_charge_${billMonth} WHERE tenant_id = #{tenantId} and acct_id = #{acctId}"})
    public long getTotalBill(@Param("tenantId") String tenantId,@Param("billMonth") String billMonth,@Param("acctId") String acctId);

}