package com.ai.baas.prd.api.category.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.prd.api.category.interfaces.IDiscountPriceProCategorySV;
import com.ai.baas.prd.api.category.params.DiscountPriceProCategoryRequest;
import com.ai.baas.prd.api.category.params.DiscountPriceProCategoryResponse;
import com.ai.baas.prd.service.business.interfaces.IDisProductsCategoryBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.dubbo.config.annotation.Service;

@Service
@Component
public class DiscountPriceProCategorySVImpl implements IDiscountPriceProCategorySV{

	@Autowired
	private IDisProductsCategoryBusiSV iDisProductsCategoryBusiSV;
	
	@Override
	public DiscountPriceProCategoryResponse getDiscountCategoryID(
			DiscountPriceProCategoryRequest request) {
		// TODO Auto-generated method stub
		List<String> lstProIDs = request.getPriceProductIDs();
		String tenantID = request.getTenantId();
		
		if(StringUtil.isBlank(tenantID)){
            throw new BusinessException("租户id不能为空");
        }
		
		if (CollectionUtil.isEmpty(lstProIDs)){
			throw new BusinessException("参与活动的产品id不能为空");
		}
		
		String categoryID = iDisProductsCategoryBusiSV.getDisProsCategoryID(lstProIDs, tenantID);
		DiscountPriceProCategoryResponse response = new DiscountPriceProCategoryResponse();
		response.setDisProsCategoryID(categoryID);
		
		ResponseHeader header = new ResponseHeader();
		header.setIsSuccess(true);
		header.setSuccess(true);
		header.setResultCode("000000");
        header.setResultMessage("活动的类目ID查询成功");
        response.setResponseHeader(header);
		return response;
	}

}
