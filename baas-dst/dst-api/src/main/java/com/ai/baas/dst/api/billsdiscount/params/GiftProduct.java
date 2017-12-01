package com.ai.baas.dst.api.billsdiscount.params;

import java.io.Serializable;
import java.util.List;

/**
 * 满赠活动的赠品实体类
 * @author wangluyang
 *
 */
public class GiftProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 赠送业务列表，必填
     */
    private List<String> productIdList;
    
    /**
     * 生效方式，必填<br>
     * 0:立即生效<br>
     * 1:下月生效<br>
     * 2:指定日期到账<br>
     */
    private String activeMode;
    
    /**
     * 赠送业务周期，必填<br>
     * 0:一个月<br>
     * 1:三个月<br>
     * 2:指定时间<br>
     */
    private String activePeriod;
    
    /**
     * 生效时间
     */
    private String effectDate;
    
    /**
     * 赠送现金金额
     */
    private double cashAmount;
    
    /**
     * 虚拟币个数
     */
    private long virtualCoinsNum;
    
    /**
     * 赠品业务类型
     */
    private String giftType;
    
    public List<String> getProductIdList() {
        return productIdList;
    }

    public void setProductIdList(List<String> productIdList) {
        this.productIdList = productIdList;
    }

    public String getActiveMode() {
        return activeMode;
    }

    public void setActiveMode(String activeMode) {
        this.activeMode = activeMode;
    }

    public String getActivePeriod() {
        return activePeriod;
    }

    public void setActivePeriod(String activePeriod) {
        this.activePeriod = activePeriod;
    }

    public String getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(String effectDate) {
        this.effectDate = effectDate;
    }

	public double getCashAmount() {
		return cashAmount;
	}

	public void setCashAmount(double cashAmount) {
		this.cashAmount = cashAmount;
	}

	public long getVirtualCoinsNum() {
		return virtualCoinsNum;
	}

	public void setVirtualCoinsNum(long virtualCoinsNum) {
		this.virtualCoinsNum = virtualCoinsNum;
	}

	public String getGiftType() {
		return giftType;
	}

	public void setGiftType(String giftType) {
		this.giftType = giftType;
	}
}
