package com.ai.baas.batch.client.prepareflow.params;
import java.util.List;
import java.util.Map;

import com.ai.baas.batch.client.dao.mapper.bo.PmBasedataCode;
import com.ai.baas.bmc.api.pricemaking.params.PricemakingResponseZX;

public class Shopping
{
    private String listId;

    private String serviceId;
    
    private String serviceName;
    /*
     * 服务名称: ECS,RDS,KVS......
     */
    private String serviceType;
    /*
     * 单一计费模式时，使用该字段 <临时> 
     */
    private String billingMode;
    /*
     * 厂商适配器传参
     */
    private String parameters;
    /*
     * cronTab表达式
     */
    private String cronTab;
    
    private boolean needPrice;
    /*
     * 价格查询结果集
     */
    private PricemakingResponseZX pricemaking;
    /*
     * 服务相关参数
     */
    private PmBasedataCode pmBasedataCode;
    
    private Map<String, String> parameterMap;
    
//    private List<Instances> instanceList;
    
    private String instance_id;
    
    private List<Map<String, String>> ratioList;
    
	public PricemakingResponseZX getPricemaking() {
		return pricemaking;
	}

	public void setPricemaking(PricemakingResponseZX pricemaking) {
		this.pricemaking = pricemaking;
	}


	public String getListId() {
		return listId;
	}

	public void setListId(String listId) {
		this.listId = listId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public Map<String, String> getParameterMap() {
		return parameterMap;
	}

	public void setParameterMap(Map<String, String> parameterMap) {
		this.parameterMap = parameterMap;
	}

	public String getCronTab() {
		return cronTab;
	}

	public void setCronTab(String cronTab) {
		this.cronTab = cronTab;
	}

	public String getInstance_id() {
		return instance_id;
	}

	public void setInstance_id(String instance_id) {
		this.instance_id = instance_id;
	}

	public List<Map<String, String>> getRatioList() {
		return ratioList;
	}

	public void setRatioList(List<Map<String, String>> ratioList) {
		this.ratioList = ratioList;
	}

	public PmBasedataCode getPmBasedataCode() {
		return pmBasedataCode;
	}

	public void setPmBasedataCode(PmBasedataCode pmBasedataCode) {
		this.pmBasedataCode = pmBasedataCode;
	}

	public String getBillingMode() {
		return billingMode;
	}

	public void setBillingMode(String billingMode) {
		this.billingMode = billingMode;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public boolean isNeedPrice() {
		return needPrice;
	}

	public void setNeedPrice(boolean needPrice) {
		this.needPrice = needPrice;
	}
    
	

 
}