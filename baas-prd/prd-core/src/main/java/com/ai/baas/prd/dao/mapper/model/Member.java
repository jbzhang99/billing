package com.ai.baas.prd.dao.mapper.model;

import java.io.Serializable;
/**
 * 为产品类目信息表封装的数据结构
 *
 * Date: 2016年11月14日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author gaogang
 */
public class Member implements Serializable{

	
	private static final long serialVersionUID = 1L;

	public String dimension;
	
	public String branch;

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}
	
}
