package com.ai.baas.rtm.api.datacollect.params;
import java.io.Serializable;

public class DataVO implements Serializable{

	/**
	 * 基本信息
	 *
	 * Date: 2016年4月12日 <br>
	 * Copyright (c) 2016 asiainfo.com <br>
	 * @author biancx
	 */
	private static final long serialVersionUID = 5608259413765665970L;
	
	String transData;

	public String getTransData() {
		return transData;
	}

	public void setTransData(String transData) {
		this.transData = transData;
	}

}
