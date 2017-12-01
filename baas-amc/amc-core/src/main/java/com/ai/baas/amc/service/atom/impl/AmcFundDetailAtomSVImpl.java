package com.ai.baas.amc.service.atom.impl;

import org.springframework.stereotype.Component;

import com.ai.baas.amc.dao.mapper.bo.AmcFundDetail;
import com.ai.baas.amc.dao.mapper.bo.AmcFundDetailSingle;
import com.ai.baas.amc.dao.mapper.factory.MapperFactory;
import com.ai.baas.amc.service.atom.interfaces.IAmcFundDetailAtomSV;
import com.ai.opt.base.exception.SystemException;

/**
 * 资金流水原子操作实现类
 *
 * Date: 2016年3月24日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author fanpw
 */
@Component
public class AmcFundDetailAtomSVImpl implements IAmcFundDetailAtomSV {

    @Override
    public int saveAmcFundDetail(String tableMonth, AmcFundDetail amcFundDetail) throws SystemException {
        return MapperFactory.getAmcFundDetailMapper().insertSelective(tableMonth, amcFundDetail);
    }

    @Override
    public int saveAmcFundDetailSingle(String tableMonth, AmcFundDetailSingle amcFundDetail)
            throws SystemException {
        return MapperFactory.getAmcFundDetailSingleMapper().insertSelective( amcFundDetail);
    }

}
