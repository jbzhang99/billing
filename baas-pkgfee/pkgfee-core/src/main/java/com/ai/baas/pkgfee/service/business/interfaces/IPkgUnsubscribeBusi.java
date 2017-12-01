package com.ai.baas.pkgfee.service.business.interfaces;

import com.ai.baas.pkgfee.api.pkgunsubscribe.params.PkgUnsubscribeRequest;
import com.ai.baas.pkgfee.api.pkgunsubscribe.params.PkgUnsubscribeResponse;

/**
 * 包费退订的业务处理
 * @author WangJing19
 *
 */
public interface IPkgUnsubscribeBusi {
	
	public PkgUnsubscribeResponse pkgUnsubscribe(PkgUnsubscribeRequest request);
}
