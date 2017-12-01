package com.ai.runner.center.mmp.vo;

import java.io.Serializable;
import java.util.Map;

public class PageEntity implements Serializable {
	private static final long serialVersionUID = 4933771255759680537L;
	
	private int currentPage; // 请求页数
	
	private int pageSize; // 每页大小
	
	private int limitStart ;
	
	private int limitEnd;
	
	private String name;
	
	private Map<String,String> params; // 传入的参数

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		countLimit();
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		countLimit();
	}
	
	private void countLimit(){
		if(this.pageSize != 0 && this.currentPage != 0){
			 this.limitStart = this.pageSize  * (this.currentPage -1);
			 limitEnd = this.pageSize;
		}
	}

	public int getLimitStart() {
		return limitStart;
	}

	public int getLimitEnd() {
		return limitEnd;
	}

	public Map<String,String> getParams() {
		return params;
	}

	public void setParams(Map<String,String> params) {
		this.params = params;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name=name;
	}
		
}
