package com.ai.baas.job.statictis.interfaces;

import java.util.List;

import com.ai.baas.job.vo.PriceBasisInfo;
import com.ai.baas.job.vo.StatictisInfo;

public interface IstatictisSum {
	public List<StatictisInfo> StatictisSum(List<PriceBasisInfo> priceInfos);
}
