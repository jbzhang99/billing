package com.ai.baas.dst.service.atom.interfaces;

import java.util.List;

import com.ai.baas.dst.dao.mapper.bo.GiftInfoDetail;
import com.ai.baas.dst.dao.mapper.bo.GiftInfoDetailCriteria;
import com.ai.opt.base.exception.SystemException;

/**
 * 赠品信息表原子服务定义类
 * @author wangluyang
 *
 */
public interface IGiftInfoDetailAtomSV {

	int saveGiftInfoDetail(GiftInfoDetail info) throws SystemException;
    
    int updateGiftInfoDetail(GiftInfoDetail info) throws SystemException;
    
    int deleteGiftInfoDetail(String tenantId, String discountId) throws SystemException;
    
    GiftInfoDetail getGiftInfoDetail(String tenantId, String discountId) throws SystemException; 
    
    List<GiftInfoDetail> queryGiftInfos(GiftInfoDetailCriteria sql) throws SystemException;
}
