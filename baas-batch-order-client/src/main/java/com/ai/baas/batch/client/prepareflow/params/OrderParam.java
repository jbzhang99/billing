package com.ai.baas.batch.client.prepareflow.params;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import com.ai.baas.bmc.api.pricemaking.params.PricemakingResponseZX;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderParam implements Serializable 
{

	private static final long serialVersionUID = 1L;
	
	private String orginJson;

	private String orderId;
	//原始订单ID，订单修改时必填
	private String oldOrderId;

    private String stateId;

    private String userId;
    
    private String extCustId;
    //订单通知时间
    private Calendar calendar;
      
    private List<Shopping> shoppingList;
    


	public String getExtCustId() {
		return extCustId;
	}

	public void setExtCustId(String extCustId) {
		this.extCustId = extCustId;
	}

	

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOldOrderId() {
		return oldOrderId;
	}

	public void setOldOrderId(String oldOrderId) {
		this.oldOrderId = oldOrderId;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Shopping> getShoppingList() {
		return shoppingList;
	}

	public void setShoppingList(List<Shopping> shoppingList) {
		this.shoppingList = shoppingList;
	}

	public String getOrginJson() {
		return orginJson;
	}

	public void setOrginJson(String orginJson) {
		this.orginJson = orginJson;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}
	

}