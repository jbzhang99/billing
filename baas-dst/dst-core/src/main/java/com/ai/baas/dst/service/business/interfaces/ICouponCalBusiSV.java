package com.ai.baas.dst.service.business.interfaces;

import com.ai.baas.dst.api.couponcal.params.CouponCalRequest;
import com.ai.baas.dst.api.couponcal.params.CouponCalResponse;
import com.ai.opt.base.exception.BusinessException;

/**
 * 优惠卷计算服务开始
 * @author wangjing19
 *
 */
public interface ICouponCalBusiSV {

    /**
     * 优惠卷计算
     * @param request
     * @return
     * @throws BusinessException
     * @ApiDocMethod
     * @ApiCode
     */
	public CouponCalResponse getResultByCouponCal(
			CouponCalRequest request);
    
}
