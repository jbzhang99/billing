package com.ai.baas.job.util;

import com.ai.baas.job.vo.PriceBasisInfo;

public class CopyBean {
	public PriceBasisInfo copyBean(PriceBasisInfo p){
		PriceBasisInfo priceInfo=new PriceBasisInfo();
		priceInfo.setAddUpSubject(p.getAddUpSubject());
		priceInfo.setMeasureWordCode(p.getMeasureWordCode());
		priceInfo.setServiceType(p.getServiceType());
		priceInfo.setStepIdInfos(p.getStepIdInfos());
		priceInfo.setTenantId(p.getTenantId());
		priceInfo.setPriceCode(p.getPriceCode());
		return p;
		
	}

}
