package com.ai.baas.job.query.interfaces;

import java.util.List;

import com.ai.baas.job.vo.PriceBasisInfo;

public interface IpriceTenant {
	public List<PriceBasisInfo> patchPriceInfo(List<PriceBasisInfo> pricebasis);
}
