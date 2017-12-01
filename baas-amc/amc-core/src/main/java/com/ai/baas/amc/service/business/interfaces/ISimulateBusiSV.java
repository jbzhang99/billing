package com.ai.baas.amc.service.business.interfaces;

import com.ai.baas.amc.api.virtualdeduct.param.BalanceQueryRequest;
import com.ai.baas.amc.api.virtualdeduct.param.VdRealTimeBalance;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;

/**
 * 此接口负责销账所加载信息
 * 屏蔽不同产品的差异
 * @author liutong5
 *
 */
public interface ISimulateBusiSV {
    /**
     * 初始化所需要的信息，信息从客户端传来
     * @param owner
     * @param extbalance
     * @return
     * @throws BusinessException
     */
    Boolean init(BalanceQueryRequest owner, double extbalance) throws BusinessException,SystemException;
    /**
     * 执行加载
     * @return
     * @throws BusinessException
     */
    VdRealTimeBalance process() throws BusinessException,SystemException;
}
