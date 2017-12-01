package com.ai.baas.ccp.topoligy.core.exception;

import com.ai.opt.base.exception.GenericException;

public class OmcException extends GenericException {
	private static final long serialVersionUID = -4985975715070523516L;
	private String code;
	
	public OmcException(String code, String msg){
		super(msg);
		this.code = code;
	}
	
	public OmcException(String code, String msg, Throwable cause){
		super(msg,cause);
	}
	
	public OmcException(String code, Throwable cause){
		super(cause);
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
}
