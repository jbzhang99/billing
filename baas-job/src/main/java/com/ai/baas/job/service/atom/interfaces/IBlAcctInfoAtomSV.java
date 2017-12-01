package com.ai.baas.job.service.atom.interfaces;

import com.ai.baas.job.dao.mapper.bo.BlAcctInfo;

public interface IBlAcctInfoAtomSV {

    /**
     * 创建账户后，写入dshm
     * @param blAcctInfo
     * @author mayt
     * @ApiDocMethod
     */
    void addDshmData(BlAcctInfo blAcctInfo);

}
