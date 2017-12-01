package com.ai.baas.amc.service.business.interfaces;

import com.ai.baas.amc.vo.AccountPreferentialVo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 账务优惠SV
 * Date: 2016年7月4日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author LiangMeng
 * 
 */
public interface IAccountPreferentialBusiSV {
    /**
     * 账务优惠——沉淀账单
     * 中信一期只做累账，不做优惠
     * @param data
     * @return
     * @author LiangMeng
     */
    int accountPreferential(AccountPreferentialVo accountPreferentialVo);
    
    /**
     * 分摊信息
     * @param apportionList
     * @return
     * @author LiangMeng
     */
    void accountPreferentialApportion(JSONObject jsonRoot);
}
