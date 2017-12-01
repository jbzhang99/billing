package com.ai.baas.amc.service.atom.interfaces;

import com.ai.baas.amc.dao.mapper.bo.AmcFundDetail;
import com.ai.baas.amc.dao.mapper.bo.AmcFundDetailSingle;
import com.ai.opt.base.exception.SystemException;

/**
 * 资金流水原子操作类定义
 *
 * Date: 2016年3月24日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public interface IAmcFundDetailAtomSV {

    /**
     * 新增资金流水明细
     * @param amcFundDetail
     * @return
     * @throws SystemException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    int saveAmcFundDetail(String tableMonth, AmcFundDetail amcFundDetail) throws SystemException;
    /**
     * 新增资金流水单表
     * @param tableMonth
     * @param amcFundDetail
     * @return
     * @throws SystemException
     * @author LiangMeng
     */
    int saveAmcFundDetailSingle(String tableMonth, AmcFundDetailSingle amcFundDetail) throws SystemException;
    
}
