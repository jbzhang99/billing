package com.ai.baas.amc.service.atom.interfaces;

import com.ai.baas.amc.dao.mapper.bo.AmcCharge;
import com.ai.baas.amc.dao.mapper.bo.AmcSettleDetail;
import com.ai.opt.base.exception.SystemException;

/**
 * 销账明细表原子服务
 * Date: 2016年7月5日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author LiangMeng
 */
public interface IAmcSettleDetailAtomSV {
    /**
     * 新增销账明细
     * @param amcSettleDetail
     * @return
     * @throws SystemException
     * @author LiangMeng
     */
    public int addSettleDetail(AmcSettleDetail amcSettleDetail) throws SystemException ;

    /**
     * 根据销账明细统计实收金额
     * @param charge
     * @return
     * @throws SystemException
     * @author wangyx13
     */
    Long selectProceeds(AmcCharge charge);
}
