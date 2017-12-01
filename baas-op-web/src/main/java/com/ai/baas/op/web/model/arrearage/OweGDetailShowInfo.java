package com.ai.baas.op.web.model.arrearage;

import java.io.Serializable;
import java.util.List;

import com.ai.baas.amc.api.oweinfoquery.param.OweDetailInfo;

public class OweGDetailShowInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String date;
	
	private int count;
	
	private List<OweDetailInfo> oweDetailInfoList;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<OweDetailInfo> getOweDetailInfoList() {
		return oweDetailInfoList;
	}

	public void setOweDetailInfoList(List<OweDetailInfo> oweDetailInfoList) {
		this.oweDetailInfoList = oweDetailInfoList;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
