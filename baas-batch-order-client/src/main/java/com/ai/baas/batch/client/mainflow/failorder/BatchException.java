package com.ai.baas.batch.client.mainflow.failorder;

import java.io.PrintWriter;
import java.io.StringWriter;

public class BatchException extends Exception {
	private static final long serialVersionUID = 5402784433522982356L;
	private String code; //错误编码
	
	public BatchException(String code, String msg){
		super(msg);
		this.code = code;
	}
	
	public BatchException(String code, String msg, Throwable cause){
		super(msg,cause);
		this.code = code;
	}
	
	public BatchException(String code, Throwable cause){
		super(cause);
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getStrStackTrace(){
		StringWriter sw = new StringWriter();
		printStackTrace(new PrintWriter(sw, true));
		return sw.toString();
	}
}
