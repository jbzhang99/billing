package com.ai.baas.dst.service.atom.interfaces;

import java.util.List;

import com.ai.baas.dst.dao.mapper.bo.DiscountExt;
import com.ai.baas.dst.dao.mapper.bo.DiscountExtCriteria;
import com.ai.opt.base.exception.SystemException;

/**
 * 账单优惠产品扩展表原子操作接口定义
 * @author wangluyang
 *
 */
public interface IDiscountExtAtomSV {

    int saveDiscountExt(DiscountExt record) throws SystemException;
    
    int deleteDiscountExt(String tenantId, String discountId, String extName) throws SystemException;
    
    int updateDiscountExt(DiscountExt record) throws SystemException;
    
    List<DiscountExt> queryDiscountExt(String tenantId, String discountId, String extName) throws SystemException;
    
    List<DiscountExt> queryDiscountExts(DiscountExtCriteria sql) throws SystemException;
}
