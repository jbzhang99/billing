package com.ai.citic.billing.web.model.inoutfund;

import com.ai.baas.amc.api.queryinoutfundserial.params.FundSerialInfo;

public class InOutFundShowVo {

    /**
     * 序号
     */
    private String index;

    /**
     * 收支明细对象
     */
    private FundSerialInfo fundSerialInfo;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public FundSerialInfo getFundSerialInfo() {
        return fundSerialInfo;
    }

    public void setFundSerialInfo(FundSerialInfo fundSerialInfo) {
        this.fundSerialInfo = fundSerialInfo;
    }

}
