package com.ai.baas.amc.service.business.interfaces;

import java.util.Map;

import com.ai.baas.amc.vo.AccountPreferentialVo;

/**
 * 总账单计算SV
 * Date: 2016年7月4日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author LiangMeng
 * 
 */
public interface ITotalBillBusiSV {
    /**
     * 总账单计算
     * @param tenantId
     * @param billMonth
     * @return
     * @author LiangMeng
     */
    void totalBill(String tenantId, String billMonth);
}
