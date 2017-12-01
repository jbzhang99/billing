package com.ai.baas.pkgfee.service.business.interfaces;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 获取包年包月费用的业务处理
 * @author WangJing
 *
 */
public interface IQueryPkgFeeBusi {
	
	//public PkgfeeResponse QueryPkgFee(String tenantID, String priceCode);

	public BaseResponse GetPkgfeeQueInfo(String tenantID);
}
