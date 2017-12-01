package com.ai.baas.bmc.topology.entity;


public class MatchResult {
	private Double matchValue;
	private String detailCode;
	private Interval interval;
	private boolean isWithin;

	public Double getMatchValue() {
		return matchValue;
	}

	public void setMatchValue(Double matchValue) {
		this.matchValue = matchValue;
	}

	public String getDetailCode() {
		return detailCode;
	}

	public void setDetailCode(String detailCode) {
		this.detailCode = detailCode;
	}

	public Interval getInterval() {
		return interval;
	}

	public void setInterval(Interval interval) {
		this.interval = interval;
	}

	public boolean isWithin() {
		return isWithin;
	}

	public void setWithin(boolean isWithin) {
		this.isWithin = isWithin;
	}
	
	
	
}
