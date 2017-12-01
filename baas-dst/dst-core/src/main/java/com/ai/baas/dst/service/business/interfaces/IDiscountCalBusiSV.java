package com.ai.baas.dst.service.business.interfaces;

import com.ai.baas.dst.api.discountcal.params.DiscountCalRequest;
import com.ai.baas.dst.api.discountcal.params.DiscountCalResponse;
import com.ai.opt.base.exception.BusinessException;

/**
 * 优惠活动计算服务开始
 * @author wangjing19
 *
 */
public interface IDiscountCalBusiSV {

    /**
     * 优惠活动计算
     * @param request
     * @return
     * @throws BusinessException
     * @ApiDocMethod
     * @ApiCode
     */
	public DiscountCalResponse getResultByDiscountCal(
			DiscountCalRequest request);
    
}
