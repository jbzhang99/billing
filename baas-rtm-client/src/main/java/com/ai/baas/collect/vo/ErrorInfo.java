package com.ai.baas.collect.vo;

public enum ErrorInfo {
	
	SUCCESS(0000 ,"成功！"),
	ERROR_FIELD_NUM(0001,"行字段数错误"),
	NO_SERVICE_ID(0002,"未能找到映射SERVICE_ID"),
	ERR_BILL_TYPE(0003,"错误的计费类型"),
	ERR_SEND_RTM(0004,"发送RTM错误");
	
	
	private int errCode;
	private String errMsg;
	private ErrorInfo(int errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
	public int getErrCode() {
		return errCode;
	}
	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	// 覆盖方法
    @Override
    public String toString() {
        return this.errCode + ":" + this.errMsg;
    }
	
	
	
}
