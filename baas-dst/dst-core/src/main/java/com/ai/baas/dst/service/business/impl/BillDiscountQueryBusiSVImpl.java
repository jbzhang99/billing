package com.ai.baas.dst.service.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.dst.api.billsdiscount.params.BillDiscountInfo;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountListQueryRequest;
import com.ai.baas.dst.api.billsdiscount.params.BillDiscountQueryRequest;
import com.ai.baas.dst.api.billsdiscount.params.EffectiveProductQueryRequest;
import com.ai.baas.dst.api.billsdiscount.params.ExtendInfo;
import com.ai.baas.dst.api.billsdiscount.params.GiftProductResponseVo;
import com.ai.baas.dst.constants.DstConstants;
import com.ai.baas.dst.dao.mapper.bo.DiscountExt;
import com.ai.baas.dst.dao.mapper.bo.DiscountExtCriteria;
import com.ai.baas.dst.dao.mapper.bo.DiscountInfo;
import com.ai.baas.dst.dao.mapper.bo.DiscountInfoCriteria;
import com.ai.baas.dst.dao.mapper.bo.GiftInfoDetail;
import com.ai.baas.dst.dao.mapper.bo.GiftInfoDetailCriteria;
import com.ai.baas.dst.dao.mapper.bo.DiscountInfoCriteria.Criteria;
import com.ai.baas.dst.service.atom.interfaces.IDiscountExtAtomSV;
import com.ai.baas.dst.service.atom.interfaces.IDiscountInfoAtomSV;
import com.ai.baas.dst.service.atom.interfaces.IGiftInfoDetailAtomSV;
import com.ai.baas.dst.service.business.interfaces.IBillDiscountQueryBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.BaseInfo;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sdk.util.StringUtil;

/**
 * 账单优惠活动查询服务实现类
 * @author wangluyang
 *
 */
@Service
@Transactional
public class BillDiscountQueryBusiSVImpl implements IBillDiscountQueryBusiSV {
        
	@Autowired
    protected IDiscountInfoAtomSV discountInfoAtomSV;
    
    @Autowired
    protected IDiscountExtAtomSV discountExtAtomSV; 
    
    @Autowired
    protected IGiftInfoDetailAtomSV giftInfoDetailAtomSV;

    @Override
    public PageInfo<BillDiscountInfo> queryBillDiscountList(
            BillDiscountListQueryRequest request) throws BusinessException {
        PageInfo<DiscountInfo> discountPageInfo = discountInfoAtomSV.queryDiscountInfoList(request);
        PageInfo<BillDiscountInfo> pageInfo = new PageInfo<BillDiscountInfo>();
        List<BillDiscountInfo> infoList = new ArrayList<BillDiscountInfo>();
        if(!CollectionUtil.isEmpty(discountPageInfo.getResult())) {
            for(DiscountInfo amcProductInfo : discountPageInfo.getResult()) {
            	BillDiscountInfo info = this.assemableBillDiscountProductInfo(amcProductInfo);
                infoList.add(info);
            }
        }
        
        pageInfo.setCount(discountPageInfo.getCount());
        pageInfo.setPageNo(discountPageInfo.getPageNo());
        pageInfo.setPageSize(discountPageInfo.getPageSize());
        pageInfo.setResult(infoList);
        return pageInfo;
    }
    
    /**
     * 组装账单优惠产品信息
     * @param amcProductInfo
     * @return
     * @ApiDocMethod
     * @ApiCode
     */
    private BillDiscountInfo assemableBillDiscountProductInfo(DiscountInfo amcProductInfo) {
        BillDiscountInfo info = new BillDiscountInfo();
        String tenantId = amcProductInfo.getTenantId();
        String productId = amcProductInfo.getDiscountId();
        info.setTenantId(tenantId);
        info.setDiscountId(productId);
        info.setDiscountName(amcProductInfo.getDiscountName());
        info.setDiscountType(amcProductInfo.getCalcType());
        info.setPriority(amcProductInfo.getPriority());
        info.setCreateTime(amcProductInfo.getCreateTime());
        info.setEffectDate(amcProductInfo.getEffectDate());
        info.setExpireDate(amcProductInfo.getExpireDate());
        info.setStatus(amcProductInfo.getStatus());
        info.setRemark(amcProductInfo.getRemark());
        info.setAllPrdDiscount(amcProductInfo.getAllPrdDiscount());
        
        GiftInfoDetail detail = giftInfoDetailAtomSV.getGiftInfoDetail(amcProductInfo.getTenantId(), amcProductInfo.getDiscountId());
        if(detail!=null){
        	GiftProductResponseVo giftProduct = new GiftProductResponseVo();
        	giftProduct.setActiveMode(detail.getActiveMode());
        	giftProduct.setActivePeriod(detail.getBusinessPeriod());
        	giftProduct.setCashAmount(Double.valueOf(detail.getCashAmount()));
        	if(detail.getEffectDate()!=null){
        		giftProduct.setEffectDate(detail.getEffectDate());
        	}
        	giftProduct.setGiftType(detail.getGiftType());
        	giftProduct.setVirtualCoinsNum(Long.valueOf(detail.getVirtualCoinsNum()));
        	info.setGiftProduct(giftProduct);
        }
        List<ExtendInfo> extendInfoList = this.assemableExtendInfo(tenantId, productId);
        info.setExtendInfoList(extendInfoList);
        return info;
    }

    /**
     * 组装扩展信息
     * @param tenantId
     * @param productId
     * @return
     * @ApiDocMethod
     * @ApiCode
     */
    private List<ExtendInfo> assemableExtendInfo(String tenantId, String productId) {
        List<ExtendInfo> extendInfoList = new ArrayList<ExtendInfo>();
        List<DiscountExt> extinfos = discountExtAtomSV.queryDiscountExt(tenantId, productId, null);
        if(CollectionUtil.isEmpty(extinfos)) {
            return extendInfoList;
        }
        
        for(DiscountExt extInfo : extinfos) {
            ExtendInfo extendInfo = new ExtendInfo();
            extendInfo.setAttrName(extInfo.getExtName());
            extendInfo.setAttrValue(extInfo.getExtValue());
            extendInfoList.add(extendInfo);
        }
        return extendInfoList;
    }

	@Override
	public BillDiscountInfo queryBillDiscountProduct(BillDiscountQueryRequest request) throws BusinessException {
		// TODO Auto-generated method stub
		DiscountInfo amcProductInfo = discountInfoAtomSV.getDiscountInfo(
	                request.getTenantId(), request.getDiscountId());
        if(amcProductInfo == null) {
            return null;
        }
        BillDiscountInfo info = this.assemableBillDiscountProductInfo(amcProductInfo);
        return info;
	}

	@Override
	public List<String> queryEffectiveScopeProduct(EffectiveProductQueryRequest request) throws BusinessException {
		// TODO Auto-generated method stub
		List<String> productIds = new ArrayList<String>();
		//查询所有已参加活动的产品
		List<String> discountIds = new ArrayList<String>();
		DiscountInfoCriteria sql = new DiscountInfoCriteria();
        Criteria criteria = sql.createCriteria();
        criteria.andTenantIdEqualTo(request.getTenantId());
        criteria.andStatusNotEqualTo(DstConstants.DiscountInfo.Status.INVALID);
        List<DiscountInfo> discountInfos = this.discountInfoAtomSV.queryDiscountInfos(sql);
        if(discountInfos!=null && discountInfos.size()>0){ 
        	for(DiscountInfo info : discountInfos){
        		discountIds.add(info.getDiscountId());
        	}
        }
        
        //移除未在生效日期范围内的活动
        DiscountInfoCriteria sql2 = new DiscountInfoCriteria();
        Criteria criteria2 = sql2.createCriteria();
        criteria2.andTenantIdEqualTo(request.getTenantId());
        criteria2.andEffectDateGreaterThan(DateUtil.getTimestamp(request.getExpireDate(), DateUtil.DATETIME_FORMAT));
        criteria2.andStatusNotEqualTo(DstConstants.DiscountInfo.Status.INVALID);
        List<DiscountInfo> discountInfos2 = this.discountInfoAtomSV.queryDiscountInfos(sql2);
        if(discountInfos2!=null && discountInfos2.size()>0){
        	for(DiscountInfo info : discountInfos2){
        		if(discountIds.contains(info.getDiscountId())){
        			discountIds.remove(info.getDiscountId());
        		}
        	}
        }
        DiscountInfoCriteria sql3 = new DiscountInfoCriteria();
        Criteria criteria3 = sql3.createCriteria();
        criteria3.andTenantIdEqualTo(request.getTenantId());
        criteria3.andExpireDateLessThan((DateUtil.getTimestamp(request.getEffectDate(), DateUtil.DATETIME_FORMAT)));
        criteria3.andStatusNotEqualTo(DstConstants.DiscountInfo.Status.INVALID);
        List<DiscountInfo> discountInfos3 = this.discountInfoAtomSV.queryDiscountInfos(sql3);
        if(discountInfos3!=null && discountInfos3.size()>0){
        	for(DiscountInfo info : discountInfos3){
        		if(discountIds.contains(info.getDiscountId())){
        			discountIds.remove(info.getDiscountId());
        		}
        	}
        }
        
        //查询所有在生效范围内的产品
        if(!CollectionUtil.isEmpty(discountIds)){
        	DiscountExtCriteria extSql = new DiscountExtCriteria();
        	com.ai.baas.dst.dao.mapper.bo.DiscountExtCriteria.Criteria extCriteria = extSql.createCriteria();
        	extCriteria.andTenantIdEqualTo(request.getTenantId());
        	if(!CollectionUtil.isEmpty(request.getDiscountIds())){
        		for(String discountId : request.getDiscountIds()){
        			if(discountIds.contains(discountId)){
        				discountIds.remove(discountId);
        			}
        		}
        	}
        	extCriteria.andDiscountIdIn(discountIds);
        	extCriteria.andExtNameEqualTo(DstConstants.DiscountInfo.AttrName.DISCOUNT_PRODUCT_ID);
        	List<DiscountExt> discountExts = this.discountExtAtomSV.queryDiscountExts(extSql);
        	
        	if(!CollectionUtil.isEmpty(discountExts)){
        		for(DiscountExt discountExt:discountExts){
        			productIds.add(discountExt.getExtValue());
        		}
        	}
        }
        
		return productIds;
	}

	@Override
	public List<String> queryEffectiveCoupon(BaseInfo request) throws BusinessException {
		// TODO Auto-generated method stub
		List<String> couponIds = new ArrayList<String>();
		List<String> discountIds = new ArrayList<String>();
		DiscountInfoCriteria sql = new DiscountInfoCriteria();
        Criteria criteria = sql.createCriteria();
        criteria.andTenantIdEqualTo(request.getTenantId());
        criteria.andStatusNotEqualTo(DstConstants.DiscountInfo.Status.INVALID);
        criteria.andCalcTypeEqualTo(DstConstants.DiscountInfo.CalcType.CALC_TYPE_MZ);
        List<DiscountInfo> discountInfos = this.discountInfoAtomSV.queryDiscountInfos(sql);
        if(discountInfos!=null && discountInfos.size()>0){
        	for(DiscountInfo info : discountInfos){
        		discountIds.add(info.getDiscountId());
        	}
        }
        
        if(!CollectionUtil.isEmpty(discountIds)){
        	List<String> ids = new ArrayList<String>();
        	GiftInfoDetailCriteria giftInfoDetailSql = new GiftInfoDetailCriteria();
        	com.ai.baas.dst.dao.mapper.bo.GiftInfoDetailCriteria.Criteria giftInfoDetailCriteria = giftInfoDetailSql.createCriteria();
        	giftInfoDetailCriteria.andTenantIdEqualTo(request.getTenantId());
        	giftInfoDetailCriteria.andGiftTypeEqualTo(DstConstants.DiscountInfo.GiftType.GIFT_TYPE_YHQ);
        	giftInfoDetailCriteria.andDiscountIdIn(discountIds);
        	List<GiftInfoDetail> details = this.giftInfoDetailAtomSV.queryGiftInfos(giftInfoDetailSql);
        	if(!CollectionUtil.isEmpty(details)){
        		for(GiftInfoDetail info : details){
        			ids.add(info.getDiscountId());
            	}
        	}
        	
        	DiscountExtCriteria extSql = new DiscountExtCriteria();
        	com.ai.baas.dst.dao.mapper.bo.DiscountExtCriteria.Criteria extCriteria = extSql.createCriteria();
        	extCriteria.andTenantIdEqualTo(request.getTenantId());
        	extCriteria.andDiscountIdIn(ids);
        	extCriteria.andExtNameEqualTo(DstConstants.DiscountInfo.AttrName.GIFT_PRODUCT_ID);
        	List<DiscountExt> discountExts = this.discountExtAtomSV.queryDiscountExts(extSql);
        	
        	if(!CollectionUtil.isEmpty(discountExts)){
        		for(DiscountExt discountExt:discountExts){
        			couponIds.add(discountExt.getExtValue());
        		}
        	}
        }
		
		
		return couponIds;
	}

	@Override
	public List<BillDiscountInfo> queryDiscountList(BillDiscountListQueryRequest request) throws BusinessException {
		// TODO Auto-generated method stub
		
		DiscountInfoCriteria sql = new DiscountInfoCriteria();
        Criteria criteria = sql.createCriteria();
        criteria.andTenantIdEqualTo(request.getTenantId());
        if(!StringUtil.isBlank(request.getDiscountId())) {
            criteria.andDiscountIdEqualTo(request.getDiscountId());
        }
        if(!StringUtil.isBlank(request.getDiscountName())) {
            criteria.andDiscountNameLike("%" + request.getDiscountName().trim() + "%");
        }
        if(!StringUtil.isBlank(request.getDiscountType())) {
            criteria.andCalcTypeEqualTo(request.getDiscountType());
        }
        if(!StringUtil.isBlank(request.getEffectDate())) {
            criteria.andEffectDateGreaterThanOrEqualTo(DateUtil.getTimestamp(request.getEffectDate(),
                    DateUtil.DATETIME_FORMAT));
        }
        if(!StringUtil.isBlank(request.getExpireDate())) {
            criteria.andExpireDateLessThanOrEqualTo(DateUtil.getTimestamp(request.getExpireDate(),
                    DateUtil.DATETIME_FORMAT));
        }
        if(!StringUtil.isBlank(request.getAllPrdDiscount())) {
            criteria.andAllPrdDiscountEqualTo(request.getAllPrdDiscount());
        }
        criteria.andStatusNotEqualTo(DstConstants.DiscountInfo.Status.INVALID);
        List<DiscountInfo> discountInfos = this.discountInfoAtomSV.queryDiscountInfos(sql);
        List<BillDiscountInfo> infoList = new ArrayList<BillDiscountInfo>();
        if(!CollectionUtil.isEmpty(discountInfos)) {
            for(DiscountInfo amcProductInfo : discountInfos) {
            	BillDiscountInfo info = this.assemableBillDiscountProductInfo(amcProductInfo);
                infoList.add(info);
            }
        }
		return infoList;
	}
}
