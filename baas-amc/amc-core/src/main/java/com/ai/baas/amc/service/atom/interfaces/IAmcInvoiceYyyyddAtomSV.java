package com.ai.baas.amc.service.atom.interfaces;

import java.util.List;

import com.ai.baas.amc.dao.mapper.bo.AmcInvoiceYyyydd;
import com.ai.opt.base.exception.SystemException;

/**
 * 账单月表查询
 * Date: 2016年7月5日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author LiangMeng
 */
public interface IAmcInvoiceYyyyddAtomSV {

    /**
     * 增加账单数据
     * @param amcInvoice
     * @param billMonth
     * @return
     * @throws SystemException
     * @author LiangMeng
     */
    public int addAmcInvoice(AmcInvoiceYyyydd amcInvoice) throws SystemException ;
    /**
     * 更新账单数据
     * @param amcInvoice
     * @param billMonth
     * @return
     * @throws SystemException
     * @author LiangMeng
     */
    public int updateAmcInvoice(AmcInvoiceYyyydd amcInvoice) throws SystemException ;
    /**
     * 根据subsId查询账单
     * @param subsId
     * @param tenantId
     * @param billMonth
     * @return
     * @author LiangMeng
     */
    public List<AmcInvoiceYyyydd> queryInvoiceByAcctId(String acctId, String tenantId,String billMonth)  throws SystemException ;
}
