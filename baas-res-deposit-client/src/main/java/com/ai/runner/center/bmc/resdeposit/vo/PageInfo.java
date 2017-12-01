package com.ai.runner.center.bmc.resdeposit.vo;

import java.util.List;

/**
 * Date: 2016年5月4日 <br>
 * @author zhoushanbin
 * Copyright (c) 2016 asiainfo.com <br>
 */
public class PageInfo<T> {
	
	
	private int totalCount;
	
	private int limitStart;
	
	private int size;
	
	private List<T> list;

	private int currPosInDb;
	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getLimitStart() {
		return limitStart;
	}

	public void setLimitStart(int limitStart) {
		this.limitStart = limitStart;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getCurrPosInDb() {
		return currPosInDb;
	}

	public void setCurrPosInDb(int currPosInDb) {
		this.currPosInDb = currPosInDb;
	}
	
	
}
