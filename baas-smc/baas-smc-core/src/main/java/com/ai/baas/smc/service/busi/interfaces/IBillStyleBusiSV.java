package com.ai.baas.smc.service.busi.interfaces;

import com.ai.baas.smc.api.billstyle.param.AddBillStyleInfo;
import com.ai.baas.smc.api.billstyle.param.CancelBillStyleInfo;
import com.ai.baas.smc.api.billstyle.param.UpdateBillStyleInfo;
import com.ai.opt.base.vo.BaseResponse;

public interface IBillStyleBusiSV {
    /**
     * 增加账单样式<br>
     * 
     * @param addBillStyleInfo
     * @author wangjl9
     * @ApiDocMethod
     */
    BaseResponse addBillStyle(AddBillStyleInfo addBillStyleInfo);

    /**
     * 修改账单样式<br>
     * 
     * @param updateBillStyleInfo
     * @author wangjl9
     * @ApiDocMethod
     */
    BaseResponse updateBillStyle(UpdateBillStyleInfo updateBillStyleInfo);

    /**
     * 账单样式注销<br>
     * 
     * @param cancelBillStyleInfo
     * @author wangjl9
     * @ApiDocMethod
     */
    BaseResponse cancleBillStyle(CancelBillStyleInfo cancelBillStyleInfo);
}
