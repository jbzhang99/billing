package com.ai.baas.amc.service.business.interfaces;

import com.ai.baas.amc.dao.mapper.bo.AmcFailureBillWithBLOBs;


/**
 * 
 * Date: 2016年8月9日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author LiangMeng
 */
public interface IAmcFailureBillBusiSV {
    void addFailureBill(AmcFailureBillWithBLOBs amcFailureBill);
}
