package com.ai.citic.billing.web.util.excel;

import java.util.List;

/**
 * 
 * @author wangluyang
 *
 */
public class SheetField {

	private String sheetName;
	private int sheetIndex;
	private List<CellField> cells;
	private List<Object> values;
	private Class clazz;
	
	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	public int getSheetIndex() {
		return sheetIndex;
	}
	public void setSheetIndex(int sheetIndex) {
		this.sheetIndex = sheetIndex;
	}
	public List<CellField> getCells() {
		return cells;
	}
	public void setCells(List<CellField> cells) {
		this.cells = cells;
	}
	public List<Object> getValues() {
		return values;
	}
	public void setValues(List<Object> values) {
		this.values = values;
	}
	public Class getClazz() {
		return clazz;
	}
	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}
}
