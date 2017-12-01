package com.ai.baas.amc.service.business.interfaces;


/**
 * 销账业务SV
 * Date: 2016年7月4日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author LiangMeng
 */
public interface IWriteOffBusiSV {
    int writeCore(String acctId, String tenantId);
}
