package com.ai.baas.amc.service.atom.interfaces;

import com.ai.baas.amc.api.bill.params.BillSearchRequest;
import com.ai.baas.amc.dao.mapper.bo.AmcCharge;
import com.ai.opt.base.exception.SystemException;

import java.util.List;

/**
 * 账单明细原子操作
 * Created by jackieliu on 16/3/31.
 */
public interface IAmcChargeAtomSV {

    /**
     * 查询指定租户中指定账户的账单明细
     * @param accId
     * @param tenantId
     * @param unsettledMonths
     * @return
     */
    public List<AmcCharge> query(String accId,String tenantId,String unsettledMonths);
    
    /**
     * 查询客户下的账单明细
     * @param tenantId
     * @param custId
     * @param accDate
     * @return
     * @throws SystemException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    List<AmcCharge> queryAmcChargeList(String tenantId, String custId, String accDate) throws SystemException;

    /**
     * 分页查询账单信息
     * @param vo
     * @return
     */
    List<AmcCharge> searchBillPages(BillSearchRequest vo);

    /**
     * 分页查询账单数目
     * @param vo
     * @return
     */
    Integer countBills(BillSearchRequest vo);
    
    /**
     * 分页查询账单数目根据月份
     * @param vo
     * @return
     */
    Integer countBillsByMonths(BillSearchRequest vo);
}
