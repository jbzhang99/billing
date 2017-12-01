package com.ai.baas.amc.service.atom.interfaces;

import com.ai.baas.amc.api.billdiscountquery.param.BillDiscountProductListQueryRequest;
import com.ai.baas.amc.dao.mapper.bo.AmcProductInfo;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;

/**
 * 账单优惠产品信息表原子服务定义类
 *
 * Date: 2016年4月6日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public interface IAmcProductInfoAtomSV {

    int saveAmcProductInfo(AmcProductInfo info) throws SystemException;
    
    int updateAmcProductInfo(AmcProductInfo info) throws SystemException;
    
    int deleteAmcProductInfo(String tenantId, String productId) throws SystemException;
    
    AmcProductInfo getAmcProductInfo(String tenantId, String productId) throws SystemException; 
    
    PageInfo<AmcProductInfo> queryAmcProductInfoList(BillDiscountProductListQueryRequest request) throws SystemException; 
}
