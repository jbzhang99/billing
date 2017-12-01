package com.ai.baas.bmc.srv.entity;

import java.math.BigDecimal;

import com.google.common.collect.Range;

public class Interval implements Comparable<Interval> {
	private Integer order;
	private BigDecimal price;
	private Range<Double> range;
	private Interval prev;

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Range<Double> getRange() {
		return range;
	}

	public void setRange(Range<Double> range) {
		this.range = range;
	}

	public Interval getPrev() {
		return prev;
	}

	public void setPrev(Interval prev) {
		this.prev = prev;
	}

	@Override
	public int compareTo(Interval o) {
		return this.getOrder().compareTo(o.getOrder());
	}

}
