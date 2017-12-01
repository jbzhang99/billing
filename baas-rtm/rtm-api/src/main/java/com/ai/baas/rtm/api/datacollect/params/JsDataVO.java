package com.ai.baas.rtm.api.datacollect.params;

import java.io.Serializable;

public class JsDataVO implements Serializable {
	private static final long serialVersionUID = -6497792350561539635L;
	String message;
	String jSBsn;

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getjSBsn() {
		return jSBsn;
	}
	public void setjSBsn(String jSBsn) {
		this.jSBsn = jSBsn;
	}


}
