package com.ai.baas.bmc.api.failedbills.params;

import com.ai.opt.base.vo.BaseInfo;

import java.util.List;

public class BillIdList extends BaseInfo{
    private List<Long> billIds;

    public List<Long> getBillIds() {
        return billIds;
    }

    public void setBillIds(List<Long> billIds) {
        this.billIds = billIds;
    }
}
