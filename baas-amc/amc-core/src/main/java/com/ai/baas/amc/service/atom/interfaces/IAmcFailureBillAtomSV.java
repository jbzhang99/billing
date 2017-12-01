package com.ai.baas.amc.service.atom.interfaces;

import com.ai.baas.amc.dao.mapper.bo.AmcFailureBillWithBLOBs;


/**
 * 错单处理原子服务
 * Date: 2016年8月9日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author LiangMeng
 */
public interface IAmcFailureBillAtomSV {

    /**
     * 入错单表
     * @param vo
     * @return
     * @author LiangMeng
     */
    Integer addFailureBill(AmcFailureBillWithBLOBs amcFailureBill);
}
