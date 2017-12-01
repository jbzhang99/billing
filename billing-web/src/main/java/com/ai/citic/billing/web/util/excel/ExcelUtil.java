package com.ai.citic.billing.web.util.excel;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ai.citic.billing.web.util.DateUtil;
import com.ai.opt.sdk.components.excel.util.ExcelDateUtil;
import com.ai.opt.sdk.components.excel.util.ExcelStringUtil;
import com.ai.opt.sdk.components.excel.util.ReflectUtil;

/**
 * Excel操作工具类
 * @author wangluyang
 *
 */
public class ExcelUtil {
	
	/**
	 * 对象序列化版本号名称
	 */
	public static final String UID = "serialVersionUID";
	
	/**
	 * 导出excel
	 * @param os
	 * @param sheets  多个sheet list
	 * @param clazz
	 * @param dataModels
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	public static <T> void writeExcel(OutputStream os, List<SheetField> sheets) throws Exception{
		XSSFWorkbook workbook = null;
		// 检测文件是否存在，如果存在则修改文件，否则创建文件
		workbook = new XSSFWorkbook();
		//标题单元格样式
		XSSFCellStyle headCellStyle = buildHeadCellStyle(workbook);
		//正文单元格样式
		XSSFCellStyle bodyCellStyle = buildBodyCellStyle(workbook);
		//正文数字单元格样式
		XSSFCellStyle bodyDigitalCellStyle = buildBodyDigitalCellStyle(workbook);
		
		for(SheetField sheetField:sheets){
			XSSFSheet xssfSheet = workbook.createSheet(sheetField.getSheetName());
			XSSFRow headRow = xssfSheet.createRow(0);
			
			// 添加表格标题
			for (int i = 0; i < sheetField.getCells().size(); i++) {
				XSSFCell cell = headRow.createCell(i);
				String titleName = sheetField.getCells().get(i).getCellTitleName();
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(titleName);
				//设置样式
				cell.setCellStyle(headCellStyle);
				// 设置单元格宽度(20个字节宽度)
				//sheet.setDefaultColumnWidth(20);
				int colWidth=titleName.length()>4?titleName.length():4;
				xssfSheet.setColumnWidth(i, colWidth*1000);
			}
			
			// 添加表格内容
			for (int i = 0; i < sheetField.getValues().size(); i++) {
				Object target = sheetField.getValues().get(i);
				XSSFRow row = xssfSheet.createRow(i + 1);
				// 遍历属性列表
				for (int j = 0; j < sheetField.getCells().size(); j++) {
					// 通过反射获取属性的值域
					String fieldName = sheetField.getCells().get(j).getCellFieldName();
					Class clazz = sheetField.getClazz();
					if (fieldName == null || UID.equals(fieldName)) {
						continue; // 过滤serialVersionUID属性
					}
					Object result = ReflectUtil.invokeGetter(target, fieldName);
					XSSFCell cell = row.createCell(j);
					cell.setCellStyle(bodyCellStyle);
					//数值类型 居右
					if(isDigitalType(clazz, fieldName)){
						cell.setCellStyle(bodyDigitalCellStyle);
					}
					// 如果是日期类型则进行格式化处理
					if (isDateType(clazz, fieldName)) {
						cell.setCellValue(ExcelDateUtil.format((Date) result));
						cell.setCellType(Cell.CELL_TYPE_STRING);
					}
					// 如果是日期类型则进行格式化处理
					else if (isTimestampType(clazz, fieldName)) {
						cell.setCellValue(ExcelDateUtil.format((Timestamp) result));
						cell.setCellType(Cell.CELL_TYPE_STRING);
					}
					//其他类型  均作为字符串处理
					else{
						cell.setCellValue(ExcelStringUtil.toString(result));
						cell.setCellType(Cell.CELL_TYPE_STRING);
					}
				}
			}
		}
		
		// 将数据写到磁盘上
		try {
			workbook.write(os);
			workbook.close();
		} finally {
			if (os != null) {
				os.close(); // 不管是否有异常发生都关闭文件输出流
			}
		}
		
	}
	
	public static <T> List<T> readExcel(InputStream in,Class<T> clazz, String[] fieldNames,
			int sheetNo, boolean hasTitle) throws Exception {
		List<T> dataModels = new ArrayList<T>();
		// 获取excel工作簿
		XSSFWorkbook workbook = new XSSFWorkbook(in);
		XSSFSheet sheet = workbook.getSheetAt(sheetNo);
		int start = sheet.getFirstRowNum() + (hasTitle ? 1 : 0); // 如果有标题则从第二行开始
		for (int i = start; i <= sheet.getLastRowNum(); i++) {
			XSSFRow row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			// 生成实例并通过反射调用setter方法
			T target = clazz.newInstance();
			for (int j = 0; j < fieldNames.length; j++) {
				String fieldName = fieldNames[j];
				if (fieldName == null || UID.equals(fieldName)) {
					continue; // 过滤serialVersionUID属性
				}
				// 获取excel单元格的内容
				XSSFCell cell = row.getCell(j);
				if (cell == null) {
					continue;
				}
				String content = getCellContent(cell);
				// 如果属性是日期类型则将内容转换成日期对象
				if (isDateType(clazz, fieldName)) {
					// 如果属性是日期类型则将内容转换成日期对象
					if(cell.getCellType()==XSSFCell.CELL_TYPE_NUMERIC){
						Date dateValue = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
						ReflectUtil.invokeSetter(target, fieldName,
								dateValue);
					}else if(StringUtils.isNotBlank(content)){
						ReflectUtil.invokeSetter(target, fieldName,
								DateUtil.to_date((content.replace("年", "-").replace("月", "-").replace("日", "").trim()), DateUtil.DATE_FORMAT));
					}
				}else if(isTimestampType(clazz, fieldName)){
					// 如果属性是日期类型则将内容转换成日期对象
					ReflectUtil.invokeSetter(target, fieldName,
							ExcelDateUtil.parseTimestamp(content));
				}else {
					Field field = clazz.getDeclaredField(fieldName);
					ReflectUtil.invokeSetter(target, fieldName,
							parseValueWithType(content, field.getType()));
				}
			}
			dataModels.add(target);
		}
		workbook.close();
		return dataModels;
	}
	
	private static XSSFCellStyle buildBodyDigitalCellStyle(XSSFWorkbook workbook) {
		XSSFCellStyle bodyDigitalCellStyle = workbook.createCellStyle();
		XSSFFont bodyDigitalFont = workbook.createFont();
		bodyDigitalFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
		bodyDigitalFont.setFontHeightInPoints((short)10);
		bodyDigitalCellStyle.setFont(bodyDigitalFont);
		bodyDigitalCellStyle.setWrapText(true);// 设置自动换行
		//bodyDigitalCellStyle.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
		//bodyDigitalCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		bodyDigitalCellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);  //底边框
		bodyDigitalCellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);  //左边框
		bodyDigitalCellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);  //右边框
		bodyDigitalCellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);  //顶边框
		bodyDigitalCellStyle.setAlignment(XSSFCellStyle.ALIGN_RIGHT); //居中
		bodyDigitalCellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		return bodyDigitalCellStyle;
	}


	private static XSSFCellStyle buildBodyCellStyle(XSSFWorkbook workbook) {
		XSSFCellStyle bodyCellStyle = workbook.createCellStyle();
		XSSFFont bodyFont = workbook.createFont();
		bodyFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
		bodyFont.setFontHeightInPoints((short)10);
		bodyCellStyle.setFont(bodyFont);
		bodyCellStyle.setWrapText(true);// 设置自动换行
		//bodyCellStyle.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
		//bodyCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		bodyCellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);  //底边框
		bodyCellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);  //左边框
		bodyCellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);  //右边框
		bodyCellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);  //顶边框
		bodyCellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); //居中
		bodyCellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		return bodyCellStyle;
	}

	
	private static XSSFCellStyle buildHeadCellStyle(XSSFWorkbook workbook) {
		//标题单元格样式
		XSSFCellStyle headCellStyle = workbook.createCellStyle();
		XSSFFont headFont = workbook.createFont();
		headFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		headFont.setFontHeightInPoints((short)10);
		headCellStyle.setFont(headFont);
		headCellStyle.setWrapText(true);// 设置自动换行
		headCellStyle.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
		headCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		headCellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);  //底边框
		headCellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);  //左边框
		headCellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);  //右边框
		headCellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);  //顶边框
		headCellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); //居中
		headCellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		return headCellStyle;
	}

	/**
	 * 判断属性是否为数字类型（int,long,float,double等）
	 * @param clazz
	 * @param fieldName
	 * @return
	 * @author gucl
	 * @ApiDocMethod
	 * @ApiCode
	 */
	protected static <T> boolean isDigitalType(Class<T> clazz, String fieldName) {
		boolean flag = false;
		try {
			Field field = clazz.getDeclaredField(fieldName);
			String fieldType=field.getType().getName();
			flag = fieldType.equals("short")||fieldType.equals("int") 
					|| fieldType.equals("long")||fieldType.equals("float")
					||fieldType.equals("double");
			Object typeObj = field.getType().newInstance();
			flag = typeObj instanceof Integer || typeObj instanceof Long 
					|| typeObj instanceof Float || typeObj instanceof Double 
					|| typeObj instanceof BigDecimal || typeObj instanceof BigInteger;
		} catch (Exception e) {
			// 把异常吞掉直接返回false
		}
		return flag;
	}
	
	/**
	 * 判断属性是否为日期类型
	 * @param clazz 数据类型
	 * @param fieldName 属性名
	 * @return 如果为日期类型返回true，否则返回false
	 */
	protected static <T> boolean isDateType(Class<T> clazz, String fieldName) {
		boolean flag = false;
		try {
			Field field = clazz.getDeclaredField(fieldName);
			Object typeObj = field.getType().newInstance();
			flag = typeObj instanceof Date;
		} catch (Exception e) {
			// 把异常吞掉直接返回false
		}
		return flag;
	}
	
	protected static <T> boolean isTimestampType(Class<T> clazz, String fieldName) {
		boolean flag = false;
		try {
			Field field = clazz.getDeclaredField(fieldName);
			String fieldType=field.getType().getName();
			//Object typeObj = field.getType().newInstance();
			flag = fieldType.equals("java.sql.Timestamp");
		} catch (Exception e) {
			// 把异常吞掉直接返回false
		}
		return flag;
	}
	
	/**
	 * 获取单元格的内容
	 * 
	 * @param cell
	 *            单元格
	 * @return 返回单元格内容
	 */
	private static String getCellContent(XSSFCell cell) {
		StringBuffer buffer = new StringBuffer();
		switch (cell.getCellType()) {
			case XSSFCell.CELL_TYPE_NUMERIC : // 数字
				buffer.append(cell.getNumericCellValue());
				break;
			case XSSFCell.CELL_TYPE_BOOLEAN : // 布尔
				buffer.append(cell.getBooleanCellValue());
				break;
			case XSSFCell.CELL_TYPE_FORMULA : // 公式
				buffer.append(cell.getCellFormula());
				break;
			case XSSFCell.CELL_TYPE_STRING : // 字符串
				buffer.append(cell.getStringCellValue());
				break;
			case XSSFCell.CELL_TYPE_BLANK : // 空值
			case XSSFCell.CELL_TYPE_ERROR : // 故障
			default :
				break;
		}
		return buffer.toString();
	}
	
	/**
	 * 根据类型将指定参数转换成对应的类型
	 * @param value 指定参数
	 * @param type 指定类型
	 * @return 返回类型转换后的对象
	 */
	protected static <T> Object parseValueWithType(String value, Class<?> type) {
		Object result = null;
		if (Byte.TYPE == type || Short.TYPE == type || Short.TYPE == type
				|| Long.TYPE == type) {
			value = String.valueOf((long) Double.parseDouble(value));
		}
		try { // 根据属性的类型将内容转换成对应的类型
			if (Boolean.TYPE == type) {
				result = Boolean.parseBoolean(value);
			} else if (Byte.TYPE == type) {
				result = Byte.parseByte(value);
			} else if (Short.TYPE == type) {
				result = Short.parseShort(value);
			} else if (Integer.TYPE == type) {
				result = Integer.parseInt(value);
			} else if (Long.TYPE == type) {
				result = Long.parseLong(value);
			} else if (Float.TYPE == type) {
				result = Float.parseFloat(value);
			} else if (Double.TYPE == type) {
				result = Double.parseDouble(value);
			} else {
				result = (Object) value;
			}
		} catch (Exception e) {
			// 把异常吞掉直接返回null
		}
		return result;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
