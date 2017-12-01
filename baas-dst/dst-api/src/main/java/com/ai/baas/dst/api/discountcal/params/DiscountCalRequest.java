package com.ai.baas.dst.api.discountcal.params;

import java.util.List;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 优惠活动计算服务请求参数
 * @author wangjing19
 *
 */
public class DiscountCalRequest extends BaseInfo {

    private static final long serialVersionUID = 1L;
      
    /**
     * 产品ID列表
     */
    private List<String> priceProductIDs;
    
    /**
     * 单价
     */
    private String unitPrice;
    
    /**
     * 数量
     */
    private String quantity;
    
    /**
     * 另一个数量
     */
    private String anoQuantity;
    

	public List<String> getPriceProductIDs() {
		return priceProductIDs;
	}

	public void setPriceProductIDs(List<String> priceProductIDs) {
		this.priceProductIDs = priceProductIDs;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getAnoQuantity() {
		return anoQuantity;
	}

	public void setAnoQuantity(String anoQuantity) {
		this.anoQuantity = anoQuantity;
	}
    
}
