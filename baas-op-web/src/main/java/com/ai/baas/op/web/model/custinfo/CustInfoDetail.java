package com.ai.baas.op.web.model.custinfo;

import java.io.Serializable;
import java.util.List;

public class CustInfoDetail implements Serializable{

	
	private static final long serialVersionUID = 1L;

	private String custName;
	
	private String custGrade;
	
	private String SearchTime;
	
	private List<String> productList;
	
	private String serviceId;
	
	private String totalMoney;
	
	private String currentTime;

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustGrade() {
		return custGrade;
	}

	public void setCustGrade(String custGrade) {
		this.custGrade = custGrade;
	}

	public String getSearchTime() {
		return SearchTime;
	}

	public void setSearchTime(String searchTime) {
		SearchTime = searchTime;
	}

	public List<String> getProductList() {
		return productList;
	}

	public void setProductList(List<String> productList) {
		this.productList = productList;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}
	
	

}
