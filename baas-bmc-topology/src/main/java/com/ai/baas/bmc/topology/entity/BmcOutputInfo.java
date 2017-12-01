package com.ai.baas.bmc.topology.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

import com.ai.baas.storm.util.BaseConstants;


public class BmcOutputInfo implements Serializable {
	private static final long serialVersionUID = 5311146133872887984L;
	private Long infoCode;
	private String tenantId;
	private String serviceType;
	private String source;
	private String tablePrefix;
	private String outputType;
	private String outputName;
	private String keySeq;
	private String seqName;
	
	private String tableName;
	private Collection<BmcOutputDetail> rowKeys;
	private List<BmcOutputDetail> details;
	
	public void createTableName() {
		StringBuilder tableNameBuilder = new StringBuilder();
		tableNameBuilder.append(tenantId).append("_");
		tableNameBuilder.append(serviceType).append("_");
		//tableNameBuilder.append(source).append("_");
		tableNameBuilder.append(tablePrefix).append("_");
		this.tableName = tableNameBuilder.toString();
	}
	
	public String getRowKey(BillingDetailRecord detailRecord) {
		StringBuilder stringBuilder = new StringBuilder();
		for (BmcOutputDetail cpOutputDetail : rowKeys) {
			stringBuilder.append(detailRecord.getDataValue(cpOutputDetail.getParamName()));
			stringBuilder.append(BaseConstants.FIELD_SPLIT);
		}
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		return stringBuilder.toString();
	}

	public Long getInfoCode() {
		return infoCode;
	}

	public void setInfoCode(Long infoCode) {
		this.infoCode = infoCode;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTablePrefix() {
		return tablePrefix;
	}

	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix = tablePrefix;
	}

	public String getOutputType() {
		return outputType;
	}

	public void setOutputType(String outputType) {
		this.outputType = outputType;
	}

	public String getOutputName() {
		return outputName;
	}

	public void setOutputName(String outputName) {
		this.outputName = outputName;
	}

	public String getKeySeq() {
		return keySeq;
	}

	public void setKeySeq(String keySeq) {
		this.keySeq = keySeq;
	}

	public String getSeqName() {
		return seqName;
	}

	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<BmcOutputDetail> getDetails() {
		return details;
	}

	public void setDetails(List<BmcOutputDetail> details) {
		TreeMap<Integer, BmcOutputDetail> keys = new TreeMap<>();
		for (BmcOutputDetail cpOutputDetail : details) {
			if ("Y".equals(cpOutputDetail.getIsKey())) {
				keys.put(cpOutputDetail.getDisplayOrder(), cpOutputDetail);
			}
		}
		this.rowKeys = keys.values();
		this.details = details;
		this.details.removeAll(rowKeys);
	}

}
