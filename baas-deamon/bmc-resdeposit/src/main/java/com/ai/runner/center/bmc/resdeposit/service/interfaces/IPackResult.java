package com.ai.runner.center.bmc.resdeposit.service.interfaces;

import com.ai.runner.center.bmc.resdeposit.vo.FunResBook;
import com.ai.runner.center.bmc.resdeposit.vo.MsgControl;
import com.ai.runner.viv.api.balance.param.ResourceDeposit;

/**
 * 将FunResBook类转换成协议需要的类
 * Date: 2016年2月26日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author caoyf
 */
public interface IPackResult {
    /**
     * 将FunResBook转换成入账dubbo服务需要的类
     */
    public ResourceDeposit chResourceDeposit(FunResBook book);
    /**
     * 将FunResBook转换成触发信控的类
     */
    public MsgControl chMsgControl(FunResBook book);
}
