package com.ai.baas.amc.service.atom.interfaces;

import java.util.List;

import com.ai.baas.amc.dao.mapper.bo.AmcChargeYyyydd;
import com.ai.opt.base.exception.SystemException;

/**
 * 账单月表查询
 * Date: 2016年7月5日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author LiangMeng
 */
public interface IAmcChargeYyyyddAtomSV {

    /**
     * 增加账单数据
     * @param amcCharge
     * @param billMonth
     * @return
     * @throws SystemException
     * @author LiangMeng
     */
    public int addAmcCharge(AmcChargeYyyydd amcCharge) throws SystemException ;
    /**
     * 更新账单数据
     * @param amcCharge
     * @param billMonth
     * @return
     * @throws SystemException
     * @author LiangMeng
     */
    public int updateAmcCharge(AmcChargeYyyydd amcCharge) throws SystemException ;
    /**
     * 根据acctId查询账单
     * @param subsId
     * @param tenantId
     * @param billMonth
     * @return
     * @author LiangMeng
     */
    public List<AmcChargeYyyydd> queryChargeByAcctId(String acctId, String tenantId,String billMonth)  throws SystemException ;
    /**
     * 根据科目查账单
     * @param acctId
     * @param tenantId
     * @param billMonth
     * @param subjectId
     * @return
     * @throws SystemException
     * @author LiangMeng
     */
    public AmcChargeYyyydd queryChargeBySubjectId(String acctId, String tenantId,String billMonth,String subjectId)  throws SystemException ;
    
    /**
     * 查询总账
     * @param tenantId
     * @param billMonth
     * @return
     * @throws SystemException
     * @author LiangMeng
     */
    public long queryChargeSum(String tenantId,String billMonth,String acctId)  throws SystemException ;
    /**
     * 查询总账
     * @param tenantId
     * @param billMonth
     * @return
     * @throws SystemException
     * @author LiangMeng
     */
    public long queryTotalBill(String tenantId,String billMonth,String acctId)  throws SystemException ;

    /**
     * 查询总账
     * @param tenantId
     * @param billMonth
     * @return
     * @throws SystemException
     * @author wangjing19
     */
    public long queryChargeDrSum(String tenantId,String billMonth,String acctId)  throws SystemException ;
}
