package com.ai.baas.mt.web.model;

import java.io.Serializable;
import java.util.List;

import com.ai.opt.sys.api.stationmail.param.StationMailVo;

/**
 * 站内信
 * @author wangluyang
 *
 */
public class SiteMailVo implements Serializable{

	private String showStr;
	private String keyStr;
	private String totalCount;
	private List<StationMailVo> stationMailVos;
	
	public String getShowStr() {
		return showStr;
	}
	public void setShowStr(String showStr) {
		this.showStr = showStr;
	}
	public String getKeyStr() {
		return keyStr;
	}
	public void setKeyStr(String keyStr) {
		this.keyStr = keyStr;
	}
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public List<StationMailVo> getStationMailVos() {
		return stationMailVos;
	}
	public void setStationMailVos(List<StationMailVo> stationMailVos) {
		this.stationMailVos = stationMailVos;
	}
}
