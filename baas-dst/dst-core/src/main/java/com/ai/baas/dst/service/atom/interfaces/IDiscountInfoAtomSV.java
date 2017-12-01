package com.ai.baas.dst.service.atom.interfaces;

import java.util.List;

import com.ai.baas.dst.api.billsdiscount.params.BillDiscountListQueryRequest;
import com.ai.baas.dst.dao.mapper.bo.DiscountInfo;
import com.ai.baas.dst.dao.mapper.bo.DiscountInfoCriteria;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;

/**
 * 账单优惠产品信息表原子服务定义类
 * @author wangluyang
 *
 */
public interface IDiscountInfoAtomSV {

    int saveDiscountInfo(DiscountInfo info) throws SystemException;
    
    int updateDiscountInfo(DiscountInfo info) throws SystemException;
    
    int deleteDiscountInfo(String tenantId, String discountId) throws SystemException;
    
    DiscountInfo getDiscountInfo(String tenantId, String discountId) throws SystemException; 
    
    PageInfo<DiscountInfo> queryDiscountInfoList(BillDiscountListQueryRequest request) throws SystemException; 
    
    List<DiscountInfo> queryDiscountInfos(DiscountInfoCriteria sql) throws SystemException;
}
