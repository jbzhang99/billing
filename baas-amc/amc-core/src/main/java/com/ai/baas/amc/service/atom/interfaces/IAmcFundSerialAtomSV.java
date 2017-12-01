package com.ai.baas.amc.service.atom.interfaces;

import com.ai.baas.amc.api.paymentquery.param.PaymentLogQueryRequest;
import com.ai.baas.amc.dao.mapper.bo.AmcFundSerial;
import com.ai.baas.amc.dao.mapper.bo.AmcFundSerialSingle;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;

/**
 * 交易订单原子服务
 *
 * Date: 2016年3月24日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public interface IAmcFundSerialAtomSV {

    /**
     * 根据业务订单流水号、租户ID、系统ID查询，常用于幕等性校验
     * @param peerSerialCode
     * @param tenantId
     * @param systemId
     * @return
     * @author lilg
     * @ApiDocMethod
     */
    AmcFundSerial getAmcFundSerial(String tableMonth, String peerSerialCode, String tenantId, String systemId) throws SystemException;
    
    /**
     * 保存交易记录
     * @param amcFundSerial
     * @return
     * @throws SystemException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    int saveAmcFundSerial(String tableMonth, AmcFundSerial amcFundSerial) throws SystemException;
    
    int saveAmcFundSerialSingle(AmcFundSerialSingle amcFundSerial) throws SystemException;
    
    /**
     * 分页查询资金流水记录
     * @param request
     * @return
     * @throws SystemException
     * @author fanpw
     * @ApiDocMethod
     * @ApiCode
     */
    PageInfo<AmcFundSerial> queryAmcFundSerialList(PaymentLogQueryRequest request) throws SystemException;
}
