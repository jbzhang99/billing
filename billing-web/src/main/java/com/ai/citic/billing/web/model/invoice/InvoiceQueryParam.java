package com.ai.citic.billing.web.model.invoice;

/**
 * 发票信息查询入參
 * @author wangluyang
 *
 */
public class InvoiceQueryParam {

    private String companyName;
    private String status;
    private String billMonth;
    private String byMonth;
    private String startTime;
    private String endTime;
    
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBillMonth() {
		return billMonth;
	}
	public void setBillMonth(String billMonth) {
		this.billMonth = billMonth;
	}
	public String getByMonth() {
		return byMonth;
	}
	public void setByMonth(String byMonth) {
		this.byMonth = byMonth;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
