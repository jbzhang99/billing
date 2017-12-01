package com.ai.baas.dst.service.business.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.dst.api.discountcal.params.DiscountCalRequest;
import com.ai.baas.dst.api.discountcal.params.DiscountCalResponse;
import com.ai.baas.dst.constants.DstConstants.DiscountInfo.AttrName;
import com.ai.baas.dst.constants.DstConstants.DiscountInfo.CalcType;
import com.ai.baas.dst.constants.DstConstants.DiscountInfo.DecimalMode;
import com.ai.baas.dst.constants.DstConstants;
import com.ai.baas.dst.constants.DstConstants.DiscountInfo.UnitId;
import com.ai.baas.dst.constants.ExceptCodeConstant;
import com.ai.baas.dst.dao.mapper.bo.DiscountExt;
import com.ai.baas.dst.dao.mapper.bo.DiscountExtCriteria;
import com.ai.baas.dst.dao.mapper.bo.DiscountInfo;
import com.ai.baas.dst.dao.mapper.bo.DiscountInfoCriteria;
import com.ai.baas.dst.dao.mapper.bo.DiscountInfoCriteria.Criteria;
import com.ai.baas.dst.service.atom.interfaces.IDiscountExtAtomSV;
import com.ai.baas.dst.service.atom.interfaces.IDiscountInfoAtomSV;
import com.ai.baas.dst.service.business.interfaces.IDiscountCalBusiSV;
import com.ai.baas.dst.util.BusinessUtil;
import com.ai.baas.prd.api.category.interfaces.IDiscountPriceProCategorySV;
import com.ai.baas.prd.api.category.params.DiscountPriceProCategoryRequest;
import com.ai.baas.prd.api.category.params.DiscountPriceProCategoryResponse;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sdk.util.StringUtil;

@Service
@Transactional
public class DiscountCalBusiSVImpl implements IDiscountCalBusiSV{
	private static final Logger logger = LogManager.getLogger(DiscountCalBusiSVImpl.class);

	@Autowired
	private IDiscountExtAtomSV iDiscountExtAtomSV;
	
	@Autowired
	private IDiscountInfoAtomSV iDiscountInfoAtomSV;
	
	@Override
	public DiscountCalResponse getResultByDiscountCal(DiscountCalRequest request){
		//设置返回值
		DiscountCalResponse response = new DiscountCalResponse();
		ResponseHeader header = new ResponseHeader();

		BigDecimal uniPrice = new BigDecimal(request.getUnitPrice());
		BigDecimal quantity = new BigDecimal(request.getQuantity());
		BigDecimal sumAmount = uniPrice.multiply(quantity);
		
		String categoryID = getCategoryID(request.getTenantId(), request.getPriceProductIDs());
		
		//根据类目ID获取DiscountExt数据
		List<DiscountExt> discountExts = getDiscountExts(request.getTenantId(), categoryID);
    	if(CollectionUtil.isEmpty(discountExts)){
    		logger.info(String.format("DiscountExts表未有【%s】为【%s】的对应记录！", 
    				                  AttrName.DISCOUNT_PRODUCT_ID, categoryID));
    		header.setResultCode(ExceptCodeConstant.SUCCESS);
    		header.setIsSuccess(true);
    		header.setSuccess(true);
        	header.setResultMessage("暂时没有参加优惠活动！");
        	response.setResponseHeader(header);
        	return response;
    	}
    	//设置DiscountId list信息
    	List<String> lstDiscountIDs = getDiscountIDsByExt(discountExts);
    	//根据DiscountId获取DiscountInfo信息，并用系统时间过滤
    	List<DiscountInfo> discountInfos = getDiscountInfos(request.getTenantId(), lstDiscountIDs);
    	
        if(CollectionUtil.isEmpty(discountInfos)) {
        	logger.info(String.format("DiscountInfo表未有DiscountId为【%s】的对应记录！", 
        							   lstDiscountIDs.toString()));
        	header.setResultCode(ExceptCodeConstant.SUCCESS);
        	header.setIsSuccess(true);
    		header.setSuccess(true);
        	header.setResultMessage("暂时没有参加优惠活动！");
        	response.setResponseHeader(header);
        	return response;
        }
        
        if(discountInfos.size() > 1){
        	logger.error(String.format("类目ID为【%s】在DiscountInfo表中有多条记录！", categoryID));
        }
        
        Map<String, String> mapExt = getDisExtInfoByID(request.getTenantId(), 
        		                                       discountInfos.get(0).getDiscountId());
        
        BigDecimal discountPrice = BigDecimal.ZERO;
    	String decimalMode = BusinessUtil.getDecimalModeByConfig();
    	
    	if(StringUtil.isBlank(request.getAnoQuantity())){
	    	switch(discountInfos.get(0).getCalcType()){
	    	case CalcType.CALC_TYPE_MJ:
	    		discountPrice = calMJ(uniPrice, quantity, mapExt);
	    		break;
	    	case CalcType.CALC_TYPE_MZ:
	    		break;
	    	case CalcType.CALC_TYPE_DZ:
	    		discountPrice = calDZ(sumAmount, mapExt);
	    		break;
	    	}
    	}else{
    		BigDecimal anoQuantity = new BigDecimal(request.getAnoQuantity());
    		sumAmount = sumAmount.multiply(anoQuantity);
    		switch(discountInfos.get(0).getCalcType()){
	    	case CalcType.CALC_TYPE_MJ:
	    		discountPrice = calMJ(uniPrice, quantity, anoQuantity, mapExt);
	    		break;
	    	case CalcType.CALC_TYPE_MZ:
	    		break;
	    	case CalcType.CALC_TYPE_DZ:
	    		discountPrice = calDZ(sumAmount, mapExt);
	    		break;
	    	}
    	}
    	header.setResultCode(ExceptCodeConstant.SUCCESS);
    	header.setIsSuccess(true);
		header.setSuccess(true);
    	header.setResultMessage("当前正在参加优惠活动！");
    	response.setResponseHeader(header);
    	response.setCategoryID(categoryID);
    	
    	sumAmount = sumAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
    	response.setOriginalPrice(sumAmount.toString());
    	
    	response.setDiscountDesc(discountInfos.get(0).getRemark());
    	response.setDiscountID(discountInfos.get(0).getDiscountId());
    	
    	switch(decimalMode){
	    	case DecimalMode.UP:
	    		discountPrice = discountPrice.setScale(2, BigDecimal.ROUND_CEILING);
	    		break;
	    	case DecimalMode.DOWN:
	    		discountPrice = discountPrice.setScale(2, BigDecimal.ROUND_FLOOR);
	    		break;
	    	case DecimalMode.ROUND:
	    		discountPrice = discountPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
	    		break;
    	}
    	response.setDiscountPrice(discountPrice.toString());
    	return response;
    	
    	
	}
	/**
	 * 满减计算
	 * @param originPrice
	 * @param ext
	 * @return
	 */
	private BigDecimal calMJ(BigDecimal unitPrice, BigDecimal number1, BigDecimal number2, Map<String, String> ext){
		BigDecimal originPrice = unitPrice.multiply(number1);
		if(UnitId.COST_UNIT.equalsIgnoreCase(ext.get(AttrName.DISCOUNT_UNIT_ID)) &&
		   UnitId.COST_UNIT.equalsIgnoreCase(ext.get(AttrName.FULL_COST_UNIT_ID))){
			originPrice = originPrice.multiply(number2);
			BigDecimal fullAmount = new BigDecimal(ext.get(AttrName.FULL_COST_AMOUNT));
			BigDecimal disAmount = new BigDecimal(ext.get(AttrName.DISCOUNT_AMOUNT));
			BigDecimal nowAmount = originPrice.compareTo(fullAmount) >= 0 ? originPrice.subtract(disAmount) : originPrice;
			
			return originPrice.subtract(nowAmount);
		}
		if(UnitId.MONTH_UNIT.equalsIgnoreCase(ext.get(AttrName.DISCOUNT_UNIT_ID)) &&
		   UnitId.MONTH_UNIT.equalsIgnoreCase(ext.get(AttrName.FULL_COST_UNIT_ID))){
			BigDecimal fullAmount = new BigDecimal(ext.get(AttrName.FULL_COST_AMOUNT));
			BigDecimal disAmount = new BigDecimal(ext.get(AttrName.DISCOUNT_AMOUNT));
			BigDecimal nowAmount = number1.compareTo(fullAmount) >= 0 ? number1.subtract(disAmount) : number1;
			
			originPrice = originPrice.multiply(number2);
			nowAmount = nowAmount.multiply(number2);
			
			return originPrice.subtract(nowAmount.multiply(unitPrice));
		}
		return originPrice;
	}
	
	/**
	 * 满减计算
	 * @param originPrice
	 * @param ext
	 * @return
	 */
	private BigDecimal calMJ(BigDecimal unitPrice, BigDecimal number, Map<String, String> ext){
		BigDecimal originPrice = unitPrice.multiply(number);
		if(UnitId.COST_UNIT.equalsIgnoreCase(ext.get(AttrName.DISCOUNT_UNIT_ID)) &&
		   UnitId.COST_UNIT.equalsIgnoreCase(ext.get(AttrName.FULL_COST_UNIT_ID))){
			BigDecimal fullAmount = new BigDecimal(ext.get(AttrName.FULL_COST_AMOUNT));
			BigDecimal disAmount = new BigDecimal(ext.get(AttrName.DISCOUNT_AMOUNT));
			BigDecimal nowAmount = originPrice.compareTo(fullAmount) >= 0 ? originPrice.subtract(disAmount) : originPrice;
			
			return originPrice.subtract(nowAmount);
		}
		if(UnitId.MONTH_UNIT.equalsIgnoreCase(ext.get(AttrName.DISCOUNT_UNIT_ID)) &&
		   UnitId.MONTH_UNIT.equalsIgnoreCase(ext.get(AttrName.FULL_COST_UNIT_ID))){
			BigDecimal fullAmount = new BigDecimal(ext.get(AttrName.FULL_COST_AMOUNT));
			BigDecimal disAmount = new BigDecimal(ext.get(AttrName.DISCOUNT_AMOUNT));
			BigDecimal nowAmount = number.compareTo(fullAmount) >= 0 ? number.subtract(disAmount) : number;
			
			return originPrice.subtract(nowAmount.multiply(unitPrice));
		}
		return originPrice;
	}
	
	/**
	 * 折扣计算
	 * @param originPrice
	 * @param ext
	 * @return
	 */
	private BigDecimal calDZ(BigDecimal originPrice, Map<String, String> ext){
		BigDecimal percent = new BigDecimal(ext.get(AttrName.DISCOUNT_PERCENT));	
		BigDecimal discount = originPrice.multiply(percent).divide(new BigDecimal("10"));
		return originPrice.subtract(discount);
	}
	
	/**
	 * 根据discountId获取discountExt表中对应的所有信息
	 * @param tenantId
	 * @param discountId
	 * @return
	 */
	private Map<String, String> getDisExtInfoByID(String tenantId, String discountId){
		List<DiscountExt> discountExts = iDiscountExtAtomSV.queryDiscountExt(tenantId, discountId, null);
		
		Map<String, String> mapExt = new HashMap<>();
		for(DiscountExt ext : discountExts){
			if(ext.getDiscountId().equalsIgnoreCase(discountId)){
				mapExt.put(ext.getExtName(), ext.getExtValue());
			}
		}
		return mapExt;
	}
	
	/**
	 * 根据ProductID获取对应的类目ID
	 * @param tenantId
	 * @param productIDs
	 * @return
	 */
	private String getCategoryID(String tenantId, List<String> productIDs){
		try
		{
			IDiscountPriceProCategorySV iDisPriceProCategorySV = 
					DubboConsumerFactory.getService(IDiscountPriceProCategorySV.class);
			
			DiscountPriceProCategoryRequest request = new DiscountPriceProCategoryRequest();
			request.setPriceProductIDs(productIDs);
			request.setTenantId(tenantId);
			
			DiscountPriceProCategoryResponse response = iDisPriceProCategorySV.getDiscountCategoryID(request);
			if(response.getResponseHeader() != null && 
			   ExceptCodeConstant.SUCCESS.equals(response.getResponseHeader().getResultCode())){
				return response.getDisProsCategoryID();
			}else{
				logger.error("未获取到类目ID");
				throw new BusinessException(ExceptCodeConstant.FAIL, "未获取类目ID");
			}
		}catch(BusinessException ex){
			throw ex;
		}catch(Exception ex){
			throw new BusinessException(ExceptCodeConstant.FAIL, "调用获取类目ID接口失败");
		}
	}
	
	/**
	 * 根据类目ID获取DiscountExt数据
	 * @param tenantID
	 * @param categoryID
	 * @return
	 */
	private List<DiscountExt> getDiscountExts(String tenantID, String categoryID){
		DiscountExtCriteria extSql = new DiscountExtCriteria();
    	com.ai.baas.dst.dao.mapper.bo.DiscountExtCriteria.Criteria extCriteria = extSql.createCriteria();
    	extCriteria.andTenantIdEqualTo(tenantID);
    	extCriteria.andExtNameEqualTo(AttrName.DISCOUNT_PRODUCT_ID);
    	extCriteria.andExtValueEqualTo(categoryID);
    	return iDiscountExtAtomSV.queryDiscountExts(extSql);
	}
	
	/**
	 * 设置DiscountId list信息
	 * @param discountExts
	 * @return
	 */
	private List<String> getDiscountIDsByExt( List<DiscountExt> discountExts){
    	List<String> lstDiscountIDs = new ArrayList<>();
    	for(DiscountExt ext : discountExts){
    		if(!lstDiscountIDs.contains(ext.getDiscountId())){
    			lstDiscountIDs.add(ext.getDiscountId());
    		}
    	}
    	return lstDiscountIDs;
	}
	
	/**
	 * 根据DiscountId获取DiscountInfo信息，并用系统时间过滤
	 * @param tenantID
	 * @param lstDiscountIDs
	 * @return
	 */
	private List<DiscountInfo> getDiscountInfos(String tenantID, List<String> lstDiscountIDs){
		DiscountInfoCriteria sql = new DiscountInfoCriteria();
        Criteria criteria = sql.createCriteria();
        criteria.andTenantIdEqualTo(tenantID)
        .andEffectDateLessThanOrEqualTo(DateUtil.getSysDate())   //产品的有效性检查
        .andExpireDateGreaterThanOrEqualTo(DateUtil.getSysDate())    //产品的有效性检查
        .andStatusNotEqualTo(DstConstants.DiscountInfo.Status.INVALID);
        criteria.andDiscountIdIn(lstDiscountIDs);	
        
        return iDiscountInfoAtomSV.queryDiscountInfos(sql);
	}
    
}
