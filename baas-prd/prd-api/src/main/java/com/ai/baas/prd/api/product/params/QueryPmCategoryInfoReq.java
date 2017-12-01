package com.ai.baas.prd.api.product.params;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 查询产品目录入参
 *
 * Date: 2016年11月15日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author gaogang
 */
public class QueryPmCategoryInfoReq extends BaseInfo{

	private static final long serialVersionUID = 1L;
	/**
	 * 主产品Id
	 */
	private String mainProCode;
	/**
	 * 主产品名称
	 */
	private String mainProName;
	/**
	 * 页码
	 */
	private Integer pageNO;
	/**
	 * 每页条数
	 */
	private Integer pageSize;
	
	public Integer getPageNO() {
		return pageNO;
	}
	public void setPageNO(Integer pageNO) {
		this.pageNO = pageNO;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getMainProCode() {
		return mainProCode;
	}
	public void setMainProCode(String mainProCode) {
		this.mainProCode = mainProCode;
	}
	public String getMainProName() {
		return mainProName;
	}
	public void setMainProName(String mainProName) {
		this.mainProName = mainProName;
	}
	
	
	
	
	
}
