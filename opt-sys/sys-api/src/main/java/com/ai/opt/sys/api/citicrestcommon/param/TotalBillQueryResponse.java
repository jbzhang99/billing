package com.ai.opt.sys.api.citicrestcommon.param;

import java.util.List;

import com.ai.opt.base.vo.BaseResponse;

/**
 * 总账查询
 * Date: 2016年7月11日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author LiangMeng
 */
public class TotalBillQueryResponse extends BaseResponse{
    private static final long serialVersionUID = 6708207278290632351L;
    /**
     * 总账返回信息，多月
     */
    List<TotalBillInfo> bills;
    public List<TotalBillInfo> getBills() {
        return bills;
    }
    public void setBills(List<TotalBillInfo> bills) {
        this.bills = bills;
    }
    
}
