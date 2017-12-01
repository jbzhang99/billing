package com.ai.baas.amc.dao.mapper.model;

import com.ai.baas.amc.dao.mapper.bo.AmcInvoiceYyyydd;

public class AmcInvoiceSumYyyydd extends AmcInvoiceYyyydd {

    private Long totalAmountSum;

    private Long adjustAfterwardsSum;

    private Long discTotalAmountSum;

    private Long balanceSum;

    public Long getTotalAmountSum() {
        return totalAmountSum;
    }

    public void setTotalAmountSum(Long totalAmountSum) {
        this.totalAmountSum = totalAmountSum;
    }

    public Long getAdjustAfterwardsSum() {
        return adjustAfterwardsSum;
    }

    public void setAdjustAfterwardsSum(Long adjustAfterwardsSum) {
        this.adjustAfterwardsSum = adjustAfterwardsSum;
    }

    public Long getDiscTotalAmountSum() {
        return discTotalAmountSum;
    }

    public void setDiscTotalAmountSum(Long discTotalAmountSum) {
        this.discTotalAmountSum = discTotalAmountSum;
    }

    public Long getBalanceSum() {
        return balanceSum;
    }

    public void setBalanceSum(Long balanceSum) {
        this.balanceSum = balanceSum;
    }
    
}
