package com.ai.citic.billing.web.util.excel;

public class CellField {

	/**
	 * 列标题
	 */
	private String cellTitleName;
	/**
	 * 列字断名称
	 */
	private String cellFieldName;
	private String cellFieldType;
	/**
	 * 是否只读
	 */
	private boolean readlyOnly = false;
	
	public CellField(String cellTitleName, String cellFieldName, boolean readlyOnly){
		this.cellTitleName = cellTitleName;
		this.cellFieldName = cellFieldName;
		this.readlyOnly = readlyOnly;
	}
	
	public CellField(){
		
	}
	
	public String getCellTitleName() {
		return cellTitleName;
	}
	public void setCellTitleName(String cellTitleName) {
		this.cellTitleName = cellTitleName;
	}
	public String getCellFieldName() {
		return cellFieldName;
	}
	public void setCellFieldName(String cellFieldName) {
		this.cellFieldName = cellFieldName;
	}
	public String getCellFieldType() {
		return cellFieldType;
	}
	public void setCellFieldType(String cellFieldType) {
		this.cellFieldType = cellFieldType;
	}
	public boolean isReadlyOnly() {
		return readlyOnly;
	}
	public void setReadlyOnly(boolean readlyOnly) {
		this.readlyOnly = readlyOnly;
	};
}
