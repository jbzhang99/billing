package com.ai.baas.prd.service.atom.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.baas.prd.api.product.params.mainProReq;
import com.ai.baas.prd.dao.mapper.bo.BmcBasedataCode;
import com.ai.baas.prd.dao.mapper.bo.BmcBasedataCodeCriteria;
import com.ai.baas.prd.dao.mapper.factory.MapperFactory;
import com.ai.baas.prd.service.atom.interfaces.IBmcBaseCodeInfoAtomSV;
import com.ai.paas.ipaas.util.StringUtil;
@Component
public class BmcBaseCodeInfoAtomSVImpl implements IBmcBaseCodeInfoAtomSV {


	@Override
	public List<BmcBasedataCode> getBasedata(mainProReq req) {

		BmcBasedataCodeCriteria sql = new BmcBasedataCodeCriteria();
		BmcBasedataCodeCriteria.Criteria cre = sql.or();	
		cre.andTenantIdEqualTo(req.getTenantId());
		cre.andParamTypeEqualTo("PRODUCT_CATALOG");
		if(!StringUtil.isBlank(req.getProductId())){
			cre.andParamCodeEqualTo(req.getProductId());
		}
		if(!StringUtil.isBlank(req.getProductName())){
			cre.andParamNameLike("%"+req.getProductName()+"%");
		}
		if(req.getPageNO()!=null||req.getPageSize()!=null){
			sql.setLimitStart((req.getPageNO()-1)*req.getPageSize());
			sql.setLimitEnd(req.getPageSize());
		}
		
		return  MapperFactory.getBmcBasedataCodeMapper().selectByExample(sql);
	}

	@Override
	public int getBaseDataCount(mainProReq req) {
		BmcBasedataCodeCriteria sql = new BmcBasedataCodeCriteria();
		BmcBasedataCodeCriteria.Criteria cre = sql.or();	
		cre.andTenantIdEqualTo(req.getTenantId());
		cre.andParamTypeEqualTo("PRODUCT_CATALOG");
		if(!StringUtil.isBlank(req.getProductId())){
			cre.andParamCodeEqualTo(req.getProductId());
		}
		if(!StringUtil.isBlank(req.getProductName())){
			cre.andParamNameLike("%"+req.getProductName()+"%");
		}
		
		
		
		return  MapperFactory.getBmcBasedataCodeMapper().countByExample(sql);
	}

}
