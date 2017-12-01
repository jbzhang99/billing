package com.ai.baas.prd.api.pricemaking.params;

import java.io.Serializable;
import java.util.List;

public class PriceMakingDelParam implements Serializable {

	private static final long serialVersionUID = 7036035473549164754L;
	private List<DetailInfoVO> detailInfoList;

	public List<DetailInfoVO> getDetailInfoList() {
		return detailInfoList;
	}

	public void setDetailInfoList(List<DetailInfoVO> detailInfoList) {
		this.detailInfoList = detailInfoList;
	}

}
