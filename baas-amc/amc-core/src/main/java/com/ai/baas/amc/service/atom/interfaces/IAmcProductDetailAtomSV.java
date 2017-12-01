package com.ai.baas.amc.service.atom.interfaces;

import com.ai.baas.amc.dao.mapper.bo.AmcProductDetail;
import com.ai.opt.base.exception.SystemException;

/**
 * 账单优惠产品明细表原子操作定义类
 *
 * Date: 2016年4月6日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public interface IAmcProductDetailAtomSV {
    
    int saveAmcProductDetail(AmcProductDetail amcProductDetail) throws SystemException;
    
    int updateAmcProductDetail(AmcProductDetail amcProductDetail) throws SystemException;
    
    int deleteAmcProductDetail(String tenantId, String productId) throws SystemException;

    AmcProductDetail getAmcProductDetail(String tenantId, String productId) throws SystemException;
}
