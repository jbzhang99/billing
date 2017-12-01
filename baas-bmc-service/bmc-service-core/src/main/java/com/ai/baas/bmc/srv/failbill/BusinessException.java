package com.ai.baas.bmc.srv.failbill;

import java.io.PrintWriter;
import java.io.StringWriter;

public class BusinessException extends Exception {
	private static final long serialVersionUID = 5402784433522982356L;
	private String code; //错误编码
	
	public BusinessException(String code, String msg){
		super(msg);
		this.code = code;
	}
	
	public BusinessException(String code, String msg, Throwable cause){
		super(msg,cause);
		this.code = code;
	}
	
	public BusinessException(String code, Throwable cause){
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
