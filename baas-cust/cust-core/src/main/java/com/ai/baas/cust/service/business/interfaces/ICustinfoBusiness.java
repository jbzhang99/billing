package com.ai.baas.cust.service.business.interfaces;

import java.io.IOException;

import com.ai.baas.cust.api.custinfo.params.CustIdParams;
import com.ai.baas.cust.api.custinfo.params.CustInfoParams;
import com.ai.baas.cust.dao.mapper.bo.BlCustinfo;
/**
 * 客户信息同步<br>
 * Date: 2016年3月23日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author wagnzhi
 */
public interface ICustinfoBusiness {
	 /**
     * 消息流水幂等性判断
     */
    public boolean hasSeq(CustInfoParams custInfo) throws IOException;

  
    /**
     * 写入mysql表
     * @return 
     */
    public void writeData(CustInfoParams custInfo, boolean saveAcctInfo);


    void updateCustInfo(CustInfoParams custInfo);

    void deleteCustInfo(CustIdParams params);
}
