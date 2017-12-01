package com.ai.baas.amc.service.business.interfaces;

import com.ai.baas.amc.api.virtualdeduct.param.VdRealTimeBalance;

/**
 * 此借口是一个用于存储销账所需要的信息操作的
 * 并处理业务逻辑
 * 屏蔽了不同产品的差异
 * @author liutong
 *
 */
public interface IAccountFeeFundBusiSV {
    /**
     * 执行处理
     * @return
     */
    VdRealTimeBalance process();
}
