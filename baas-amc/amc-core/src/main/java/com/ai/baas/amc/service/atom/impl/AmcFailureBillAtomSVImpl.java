package com.ai.baas.amc.service.atom.impl;

import org.springframework.stereotype.Component;

import com.ai.baas.amc.dao.mapper.bo.AmcFailureBillWithBLOBs;
import com.ai.baas.amc.dao.mapper.factory.MapperFactory;
import com.ai.baas.amc.service.atom.interfaces.IAmcFailureBillAtomSV;

/**
 * 错单处理
 * Date: 2016年8月9日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author LiangMeng
 */
@Component
public class AmcFailureBillAtomSVImpl implements IAmcFailureBillAtomSV {

    @Override
    public Integer addFailureBill(AmcFailureBillWithBLOBs amcFailureBill) {
        return MapperFactory.getAmcFailureBillMapper().insert(amcFailureBill);
    }
	
	
    
}
