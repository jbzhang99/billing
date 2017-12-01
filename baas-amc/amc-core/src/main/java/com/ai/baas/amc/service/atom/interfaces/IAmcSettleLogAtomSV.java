package com.ai.baas.amc.service.atom.interfaces;

import com.ai.baas.amc.dao.mapper.bo.AmcSettleLog;
import com.ai.opt.base.exception.SystemException;

/**
 * 销账记录原子服务
 * Date: 2016年7月5日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author LiangMeng
 */
public interface IAmcSettleLogAtomSV {

    /**
     * 新增销账记录
     * @param amcSettleLog
     * @return
     * @throws SystemException
     * @author LiangMeng
     */
    public int addSettleLog(AmcSettleLog amcSettleLog) throws SystemException ;
}
