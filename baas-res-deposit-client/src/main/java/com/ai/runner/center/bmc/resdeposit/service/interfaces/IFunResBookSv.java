package com.ai.runner.center.bmc.resdeposit.service.interfaces;

import java.util.List;

import com.ai.runner.center.bmc.resdeposit.vo.CommMsg;
import com.ai.runner.center.bmc.resdeposit.vo.FunResBook;
import com.ai.runner.center.bmc.resdeposit.vo.UserMsg;


/**
 * 通过共享内存获得相关信息
 * Date: 2016年2月25日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author caoyf
 */
public interface IFunResBookSv {
    
    /**
     * 根据用户信息获得账本数据部分信息
     */
    public List<FunResBook> getFunResBook(UserMsg user);
    
    /**
     * 根据comm简要信息获得账本数据部分信息
     */
    public List<FunResBook> getFunResBook(CommMsg comm);
    
    /**
     * 填充账本的数据类型和数据量
     */
    public String getAmount(String product_id, String charge_type, String tenant_id);
    
    /**
     * 获得productType
     */
    public String getProductType(String product_id,String tenant_id);
}
