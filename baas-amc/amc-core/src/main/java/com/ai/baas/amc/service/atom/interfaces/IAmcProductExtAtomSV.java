package com.ai.baas.amc.service.atom.interfaces;

import java.util.List;

import com.ai.baas.amc.dao.mapper.bo.AmcProductExt;
import com.ai.opt.base.exception.SystemException;

/**
 * 账单优惠产品扩展表原子操作接口定义
 *
 * Date: 2016年4月6日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
public interface IAmcProductExtAtomSV {

    int saveAmcProductExt(AmcProductExt record) throws SystemException;
    
    int deleteAmcProductExt(String tenantId, String productId, String extName) throws SystemException;
    
    int updateAmcProductExt(AmcProductExt record) throws SystemException;
    
    List<AmcProductExt> queryAmcProductExt(String tenantId, String productId, String extName) throws SystemException;
}
