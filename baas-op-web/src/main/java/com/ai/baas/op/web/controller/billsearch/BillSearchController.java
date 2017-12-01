package com.ai.baas.op.web.controller.billsearch;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ai.baas.amc.api.billQuery.interfaces.IBillQuerySV;
import com.ai.baas.amc.api.billQuery.params.Bill;
import com.ai.baas.amc.api.billQuery.params.BillDetailInput;
import com.ai.baas.amc.api.billQuery.params.BillDetailOutput;
import com.ai.baas.amc.api.billQuery.params.BillInput;
import com.ai.baas.amc.api.billQuery.params.BillOutput;
import com.ai.baas.amc.api.billQuery.params.ServiceParams;
import com.ai.baas.amc.api.billQuery.params.SubjectDetail;
import com.ai.baas.op.web.constants.Constants.ExcelConstants;
import com.ai.baas.op.web.constants.Constants.ResultCode;
import com.ai.baas.op.web.constants.VerifyConstants.ResultCodeConstants;
import com.ai.baas.op.web.model.bill.BillShowVo;
import com.ai.baas.op.web.util.AmountUtil;
import com.ai.baas.op.web.util.CustGradeUtil;
import com.ai.baas.op.web.util.TradeSeqUtil;
import com.ai.net.xss.util.StringUtil;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.components.ccs.CCSClientFactory;
import com.ai.opt.sdk.components.excel.client.AbstractExcelHelper;
import com.ai.opt.sdk.components.excel.factory.ExcelFactory;
import com.ai.opt.sdk.components.excel.util.ExcelStringUtil;
import com.ai.opt.sdk.components.excel.util.ReflectUtil;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.web.model.ResponseData;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import com.ai.opt.sso.client.filter.SSOClientUser;
import com.ai.paas.ipaas.ccs.IConfigClient;
import com.ai.paas.ipaas.ccs.constants.ConfigException;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 账单查询控制
 * 
 * @author jiaxs
 *
 */
@RequestMapping("/search/bill")
@Controller
public class BillSearchController {

	private static final Logger LOG = Logger.getLogger(BillSearchController.class);

	@RequestMapping("/list")
	public ModelAndView gotoListPage(HttpServletRequest request) {
		Map<String, Integer> model = new HashMap<String,Integer>();
		IConfigClient defaultConfigClient = CCSClientFactory.getDefaultConfigClient();
		try {
			String maxRow = defaultConfigClient.get(ExcelConstants.EXCEL_OUTPUT_MAX_ROW);
			model.put("excelMaxRow", Integer.valueOf(maxRow));
		} catch (ConfigException e) {
			LOG.error("获取excel导出最大行数配置失败", e);
		}
		return new ModelAndView("/jsp/billsearch/billSearchList",model);
	}

	@RequestMapping("/searchList")
	@ResponseBody
	public ResponseData<PageInfo<Bill>> searchList(HttpServletRequest request, BillInput billInput) {
		BillOutput billInfo = queryBillInfo(request, billInput);
		ResponseData<PageInfo<Bill>> responseData = null;
		ResponseHeader responseHeader = null;
		if (billInfo != null && billInfo.getResponseHeader().isSuccess()) {
			// 转换显示形式
			PageInfo<Bill> billPageInfo = billInfo.getBillList();
			List<Bill> billList = billPageInfo.getResult();
			if (billList != null && billList.size() > 0) {
				for (Bill billData : billList) {
					// 转换客户级别
					String custGrade = billData.getCustGrade();
					String custLevelName = CustGradeUtil.getCustLevelName(custGrade);
					billData.setCustGrade(custLevelName);
					// 转化日期
					String billDuration = billData.getBillDuration();
					String yearValue = billDuration.substring(0, 4);
					String monthValue = billDuration.substring(4, 6);
					billData.setBillDuration(yearValue + "年" + monthValue + "月");
				}
			}
			responseData = new ResponseData<PageInfo<Bill>>(ResultCode.SUCCESS_CODE, "查询成功", billPageInfo);
			responseHeader = new ResponseHeader(true, ResultCodeConstants.SUCCESS_CODE, "查询成功");
		} else {
			responseData = new ResponseData<PageInfo<Bill>>(ResultCode.SUCCESS_CODE, "无数据", null);
			responseHeader = new ResponseHeader(true, ResultCodeConstants.SUCCESS_CODE, "无数据");
		}
		responseData.setResponseHeader(responseHeader);
		return responseData;
	}

	@RequestMapping("/info")
	public ModelAndView searchBillInfo(HttpServletRequest request) {
		String queryTime = request.getParameter("queryTime");
		String custId = request.getParameter("custId");
		Map<String, String> model = new HashMap<String, String>();
		model.put("queryTime", queryTime);
		model.put("custId", custId);
		return new ModelAndView("/jsp/billsearch/billInfo", model);
	}

	@RequestMapping("/searchDetailInfo")
	@ResponseBody
	public ResponseData<BillDetailOutput> searchDetailInfo(HttpServletRequest request, BillDetailInput billdetailinput) {
		BillDetailOutput billDetail = queryBillDetailData(request, billdetailinput);
		return new ResponseData<BillDetailOutput>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功", billDetail);
	}

	@RequestMapping("/exportExcel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, BillInput billInput) {
		try {
			BillOutput billInfo = queryBillInfo(request, billInput);
			if (billInfo != null && billInfo.getResponseHeader().isSuccess()) {
				// 转换显示形式
				PageInfo<Bill> billPageInfo = billInfo.getBillList();
				List<Bill> billList = billPageInfo.getResult();
				List<BillShowVo> billShowList = new LinkedList<BillShowVo>();
				if (billList != null && billList.size() > 0) {
					for (Bill billData : billList) {
						BillShowVo billShowVo = new BillShowVo();
						BeanUtils.copyProperties(billShowVo, billData);
						// 转换客户级别
						String custGrade = billData.getCustGrade();
						String custLevelName = CustGradeUtil.getCustLevelName(custGrade);
						billShowVo.setCustGrade(custLevelName);
						// 转化日期
						String billDuration = billData.getBillDuration();
						String yearValue = billDuration.substring(0, 4);
						String monthValue = billDuration.substring(4, 6);
						billShowVo.setBillDuration(yearValue + "年" + monthValue + "月");
						// 转换金额
						String orgFee = AmountUtil.Li2Yuan(billData.getOrgFee());
						billShowVo.setOrgFeeD(orgFee);
						String disFee = AmountUtil.Li2Yuan(billData.getDisFee());
						billShowVo.setDisFeeD(disFee);
						String totalFee = AmountUtil.Li2Yuan(billData.getTotalFee());
						billShowVo.setTotalFeeD(totalFee);

						billShowList.add(billShowVo);
					}
				}
				ServletOutputStream outputStream = response.getOutputStream();
				response.reset();// 清空输出流
				response.setContentType("application/msexcel");// 定义输出类型
				response.setHeader("Content-disposition", "attachment; filename=bill" + billInput.getQueryTime() + ".xls");// 设定输出文件头

				String[] titles = new String[] { "客户名称", "客户等级", "账单生成月份", "账单合计金额（元）", "账单优惠金额（元）", "实际应缴金额（元）" };
				String[] fieldNames = new String[] { "custName", "custGrade", "billDuration", "orgFeeD", "disFeeD", "totalFeeD" };
				AbstractExcelHelper excelHelper = ExcelFactory.getJxlExcelHelper();
				excelHelper.writeExcel(outputStream, "账单信息(" + billInput.getQueryTime() + ")", BillShowVo.class, billShowList, fieldNames, titles);
			}
		} catch (Exception e) {
			LOG.error("导出数据失败：", e);
		}
	}

	private BillOutput queryBillInfo(HttpServletRequest request, BillInput billInput) {
		IBillQuerySV iBillQuerySV = DubboConsumerFactory.getService("iBillQuerySV");
		SSOClientUser userClient = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
		String tenantId = userClient.getTenantId();
		String newTradeSeq = TradeSeqUtil.newTradeSeq(tenantId);
		billInput.setTradeSeq(newTradeSeq);
		billInput.setTenantId(tenantId);
		// 转换时间格式
		String queryData = billInput.getQueryTime().replaceAll("-", "");
		billInput.setQueryTime(queryData);

		BillOutput billInfo = iBillQuerySV.getBillInfo(billInput);
		return billInfo;
	}

	@RequestMapping("/exportDetailExcel")
	public void exportDetailExcel(HttpServletRequest request, HttpServletResponse response, BillDetailInput billdetailinput) {
		WritableWorkbook workbook = null;
		ServletOutputStream outputStream = null;
		try {
			try {
				BillDetailOutput billDetail = queryBillDetailData(request, billdetailinput);
				outputStream = response.getOutputStream();
				response.reset();// 清空输出流
				response.setContentType("application/msexcel");// 定义输出类型
				response.setHeader("Content-disposition", "attachment; filename=bill-detail" + billdetailinput.getQueryTime() + ".xls");// 设定输出文件头

				workbook = Workbook.createWorkbook(outputStream);
				// 根据当前工作表数量创建相应编号的工作表
				int sheetNo = workbook.getNumberOfSheets() + 1;
				WritableSheet sheet = workbook.createSheet("账单明细(" + billdetailinput.getQueryTime() + ")", sheetNo);
				sheet.getSettings().setShowGridLines(true);
				String[] billColNameArray = new String[] { "客户名称：", "客户等级：", "账单生成月份：", "账单科目明细：", "账单合计金额：", "实际应缴金额：" };
				String[] billColArray = new String[] { "custName", "custGrade", "queryTime", "serviceList", "orgFee", "totalFee" };
				int listCount = 0;// 用于记录含有的list的行数
				for (int i = 0; i < billColNameArray.length; i++) {
					// 设置标题
					WritableCellFormat titleCellFormat = buildTitleCellFormat(false);
					Label title = new Label(0, i + listCount, billColNameArray[i], titleCellFormat);
					sheet.addCell(title);
					// 设置单元格宽度
					sheet.setColumnView(0, billColNameArray[i].length() + 10);
					Object result = ReflectUtil.invokeGetter(billDetail, billColArray[i]);
					boolean listType = isListType(BillDetailOutput.class, billColArray[i]);
					// 如果是List
					if (listType) {
						String[] gColNameArray = new String[] { "服务号码", "账单科目名称", "实际应缴金额（元）" };
						String[] gColArray = new String[] { "serviceId", "subjectName", "subjectFee" };

						Gson gson = new Gson();
						List<ServiceParams> billGList = gson.fromJson(gson.toJson(result), new TypeToken<List<ServiceParams>>() {
						}.getType());
						WritableCellFormat headCellFormat = buildHeadCellFormat(true);
						// 画标题行
						for (int j = 0; j < gColNameArray.length; j++) {
							Label gtitle = new Label(j + 1, i + listCount, gColNameArray[j], headCellFormat);
							sheet.addCell(gtitle);
							// 设置单元格宽度
							sheet.setColumnView(j + 1, gColNameArray[j].length() + 10);
						}
						for (ServiceParams billG : billGList) {
							List<SubjectDetail> subjectDetailList = billG.getSubjectDetailList();
							int size = subjectDetailList.size();
							String serviceId = billG.getServiceId();
							// 设置宽度
							sheet.setColumnView(1, serviceId.length() + 10);
							// 合并单元格
							sheet.mergeCells(1, i + listCount + 1, 1, i + listCount + size);
							WritableCellFormat valueFormat = buildValueFormat(gColArray[0],true);
							String value = changeValueShowForm(gColArray[0], serviceId);
							Label valueLabel = new Label(1, i + listCount + 1, value, valueFormat);
							sheet.addCell(valueLabel);
							for (int k = 0; k < size; k++) {
								SubjectDetail subjectDetail = subjectDetailList.get(k);
								for (int l = 0; l < 2; l++) {
									Object gResult = ReflectUtil.invokeGetter(subjectDetail, gColArray[l + 1]);
									valueFormat = buildValueFormat(gColArray[l + 1],true);
									String gValue = ExcelStringUtil.toString(gResult);
									gValue = changeValueShowForm(gColArray[l + 1], gValue);
									Label gValueLabel = new Label(2 + l, i + listCount + k + 1, gValue, valueFormat);
									sheet.addCell(gValueLabel);
								}

							}
							listCount = listCount + size;
						}
					} else {
						WritableCellFormat valueFormat = buildValueFormat(billColArray[i],false,true);
						String value = ExcelStringUtil.toString(result);
						value = changeValueShowForm(billColArray[i], value);
						if(isYuanField(billColArray[i])){
							value = value+" 元";
						}
						Label valueLabel = new Label(1, i + listCount, value, valueFormat);
						sheet.addCell(valueLabel);
						// 设置单元格宽度
						sheet.setColumnView(1, result.toString().length() + 10);
					}
				}
			} finally {
				if (workbook != null) {
					workbook.write();
					workbook.close();
					// 关闭输出流
					if (outputStream != null) {
						outputStream.flush();
						outputStream.close();
					}
				}
			}
		} catch (Exception e) {
			LOG.error("导出数据失败：", e);
		}
	}
	
	/**
	 * 转换值得展现形式
	 * @param colName
	 * @param value
	 * @return
	 * @throws Exception
	 */
	private String changeValueShowForm(String colName,Object value) throws Exception{
		// 金额字段名
		Set<String> amountFieldSet = getAmountFieldSet();
		if (amountFieldSet.contains(colName) && value!=null) {
			String valueStr = value.toString();
			value = AmountUtil.Li2Yuan(Double.parseDouble(valueStr));
		}
		return StringUtil.toString(value);
	}
	
	/**
	 * 获取值得样式
	 * @param colName
	 * @param hasBorder
	 * @return
	 * @throws WriteException
	 */
	private WritableCellFormat buildValueFormat(String colName,boolean hasBorder) throws WriteException{
		return buildValueFormat(colName, hasBorder, false);
	}

	/**
	 * 获取值得样式
	 * 
	 * @param colName
	 * @param amountFieldSet
	 * @return
	 * @throws WriteException
	 */
	private WritableCellFormat buildValueFormat(String colName,boolean hasBorder,boolean isTable) throws WriteException {
		// 金额字段名
		Set<String> amountFieldSet = getAmountFieldSet();
		WritableCellFormat valueFormat = null;
		if (amountFieldSet.contains(colName)) {
			valueFormat = buildBodyDigitalFormat(hasBorder);
		} else {
			valueFormat = buildBodyCellFormat(hasBorder);
			if(isTable){
				valueFormat.setAlignment(Alignment.LEFT);
			}
		}
		return valueFormat;
	}

	/**
	 * 获取金额字段
	 * 
	 * @return
	 */
	private Set<String> getAmountFieldSet() {
		String[] amountFields = new String[] { "orgFee", "totalFee", "subjectFee" };
		Set<String> amountFieldSet = new HashSet<String>();
		for (String amountField : amountFields) {
			amountFieldSet.add(amountField);
		}
		return amountFieldSet;
	}
	
	private boolean isYuanField(String field){
		String[] amountFields = new String[] { "totalFee", "orgFee" };
		return StringUtils.isContains(amountFields, field);
	}

	/**
	 * 判断是否是List类型
	 * 
	 * @param clazz
	 * @param fieldName
	 * @return
	 */
	protected <T> boolean isListType(Class<T> clazz, String fieldName) {
		boolean flag = false;
		try {
			Field field = clazz.getDeclaredField(fieldName);
			// Object typeObj = field.getType();
			Class<?> type = field.getType();
			flag = (type == List.class) || type.isAssignableFrom(List.class);
		} catch (Exception e) {
			// 把异常吞掉直接返回false
			LOG.error("失败：", e);
		}
		return flag;
	}

	/**
	 * 查询账单详情
	 * 
	 * @param request
	 * @param billdetailinput
	 * @return
	 */
	private BillDetailOutput queryBillDetailData(HttpServletRequest request, BillDetailInput billdetailinput) {
		IBillQuerySV iBillQuerySV = DubboConsumerFactory.getService("iBillQuerySV");
		SSOClientUser userClient = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
		String tenantId = userClient.getTenantId();
		String newTradeSeq = TradeSeqUtil.newTradeSeq(tenantId);
		billdetailinput.setTradeSeq(newTradeSeq);
		billdetailinput.setTenantId(tenantId);
		BillDetailOutput billDetail = iBillQuerySV.getBillDetail(billdetailinput);
		if (billDetail != null) {
			// 转换日期显示格式
			String queryTime = billDetail.getQueryTime();
			String year = queryTime.substring(0, 4);
			String month = queryTime.substring(4);
			billDetail.setQueryTime(year + "年" + month + "月");
			// 转换客户级别显示格式
			String custGrade = String.valueOf(billDetail.getCustGrade());
			String custLevelName = CustGradeUtil.getCustLevelName(custGrade);
			billDetail.setCustGrade(Long.valueOf(custLevelName));
		}
		return billDetail;
	}

	/**
	 * 设置数字样式
	 * 
	 * @return
	 * @throws WriteException
	 */
	private WritableCellFormat buildBodyDigitalFormat(boolean hasBorder) throws WriteException {
		// 正文数字字体
		WritableFont bodyDigitalwfont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		// 正文数字样式
		WritableCellFormat bodyDigitalFormat = new WritableCellFormat(bodyDigitalwfont);
		bodyDigitalFormat.setAlignment(Alignment.RIGHT);
		bodyDigitalFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		if(hasBorder){
			bodyDigitalFormat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.DARK_BLUE);
		}else{
			bodyDigitalFormat.setBorder(Border.ALL, BorderLineStyle.NONE);
		}
		return bodyDigitalFormat;
	}

	/**
	 * 设置正文样式
	 * 
	 * @return
	 * @throws WriteException
	 */
	private WritableCellFormat buildBodyCellFormat(boolean hasBorder) throws WriteException {
		// 正文字体
		WritableFont bodywfont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		// 正文样式
		WritableCellFormat bodyFormat = new WritableCellFormat(bodywfont);
		bodyFormat.setAlignment(Alignment.CENTRE);
		bodyFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		if(hasBorder){
			bodyFormat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.DARK_BLUE);
		}else{
			bodyFormat.setBorder(Border.ALL, BorderLineStyle.NONE);
		}
		return bodyFormat;
	}

	/**
	 * 设置标题行样式
	 * 
	 * @return
	 * @throws WriteException
	 */
	private WritableCellFormat buildHeadCellFormat(boolean hasBorder) throws WriteException {
		// 标题字体
		WritableFont headwfont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		// 标题样式
		WritableCellFormat headFormat = new WritableCellFormat(headwfont);
		headFormat.setAlignment(Alignment.CENTRE);
		headFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		if(hasBorder){
			headFormat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.DARK_BLUE);
		}else{
			headFormat.setBorder(Border.ALL, BorderLineStyle.NONE);
		}
		headFormat.setBackground(Colour.ICE_BLUE);
		return headFormat;
	}

	/**
	 * 设置标题样式
	 * 
	 * @return
	 * @throws WriteException
	 */
	private WritableCellFormat buildTitleCellFormat(boolean hasBorder) throws WriteException {
		// 标题字体
		WritableFont headwfont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		// 标题样式
		WritableCellFormat headFormat = new WritableCellFormat(headwfont);
		headFormat.setAlignment(Alignment.LEFT);
		headFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		if(hasBorder){
			headFormat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.DARK_BLUE);
		}else{
			headFormat.setBorder(Border.ALL, BorderLineStyle.NONE);
		}
		return headFormat;
	}
}
