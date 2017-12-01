package com.ai.baas.prd.service.business.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.prd.dao.mapper.bo.PmCatalogInfo;
import com.ai.baas.prd.dao.mapper.bo.PmPolicyDetail;
import com.ai.baas.prd.service.atom.interfaces.IPmCatalogInfoAtomSV;
import com.ai.baas.prd.service.atom.interfaces.PmPolicyDetailAtomSV;
import com.ai.baas.prd.service.business.interfaces.IDisProductsCategoryBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;

@Service
@Transactional
public class DisProductsCategoryBusiSVImpl implements IDisProductsCategoryBusiSV{

	private static final Logger logger = LogManager
			.getLogger(DisProductsCategoryBusiSVImpl.class);

	@Autowired
	private PmPolicyDetailAtomSV pmPolicyDetailAtomSV;
	
	@Autowired
	private IPmCatalogInfoAtomSV iPmCatalogInfoAtomSV;
	
	@Override
	public String getDisProsCategoryID(List<String> productIDs, String tenantId) {
		// TODO Auto-generated method stub
		List<PmPolicyDetail> lstDetail = null;
		List<PmCatalogInfo> lstCatalog = null;
		String categoryID = "";
		
		for(String proID : productIDs){
			//根据price_product_id list，查询定价策略明细表，赋值给ditail_id，获取policy_id[list]
			lstDetail = pmPolicyDetailAtomSV.selectByDetailID(proID, tenantId);
			if (CollectionUtil.isEmpty(lstDetail)) {
				logger.info(String.format("未获取到ProductID为【%s】的PmPolicyDetail表对应记录", proID));
	            continue;
			}
			//查询产品目录信息表pm_catalog_info，获取类目id：category_id
			String policyID = lstDetail.get(0).getPolicyId();
			lstCatalog = iPmCatalogInfoAtomSV.queryByPolicyId(policyID, tenantId);
			if (CollectionUtil.isEmpty(lstCatalog)) {
				logger.info(String.format("未获取到PolicyId为【%s】的PmCatalogInfo表对应记录",policyID));
				continue;
			}
			
			if(StringUtil.isBlank(categoryID)){
				categoryID = lstCatalog.get(0).getCategoryId();
			}else if(!lstCatalog.get(0).getCategoryId().equalsIgnoreCase(categoryID)){
				logger.error(String.format("CategoryID不一致，当前policyID【%s】的categoryID为【%s】，前一个categoryID为【%s】",
						                   policyID, lstCatalog.get(0).getCategoryId(), categoryID));
			}
			
		}
		if (StringUtil.isBlank(categoryID)){
			throw new BusinessException("活动的类目ID查询失败");
		}
		return categoryID;
	}

}
