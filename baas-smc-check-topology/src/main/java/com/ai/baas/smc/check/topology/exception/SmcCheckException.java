package com.ai.baas.smc.check.topology.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

public class SmcCheckException extends Exception {
	private static final long serialVersionUID = -4985975715070523516L;
	private String code;
	
	public SmcCheckException(String code, String msg){
		super(msg);
		this.code = code;
	}
	
	public SmcCheckException(String code, String msg, Throwable cause){
		super(msg,cause);
	}
	
	public SmcCheckException(String code, Throwable cause){
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
