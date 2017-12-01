package com.ai.runner.center.ctp.rtm.core.util;

import java.io.Serializable;

public class StringLine implements Serializable {

	private static final long serialVersionUID = 1345222554070512990L;

	private String data;
	private String delimiter;
	//private String business_type;
	private String service_id;
	private String source;
	private int row_num = 0; //行号
	private String type = "txt";
	private String psn;
	private String sn;
	
	public StringLine(String data){
		this.data = data;
	}
	
	public StringLine(String data, String delimiter){
		this(data);
		this.delimiter = delimiter;
	}
	
	public StringLine(String data, String delimiter, String source){
		this(data, delimiter);
		this.source = source;
	}
	
	public StringLine(String data, String delimiter, String source, int row_num){
		this(data, delimiter, source);
		this.row_num = row_num;
	}

	public String getServiceId() {
		return service_id;
	}

	public void setServiceId(String service_id) {
		this.service_id = service_id;
	}

	public String getData() {
		return data;
	}
	
	public String getDelimiter() {
		return delimiter;
	}
	
	public String getSource() {
		return source;
	}
	
	public int getRowNum(){
		return row_num;
	}

	public String getPsn() {
		return psn;
	}

	public void setPsn(String psn) {
		this.psn = psn;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	@Override
	public String toString() {
		return "["+data+"]";
	}
	


}
