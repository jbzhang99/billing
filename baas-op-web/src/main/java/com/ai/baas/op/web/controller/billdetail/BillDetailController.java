package com.ai.baas.op.web.controller.billdetail;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.baas.bmc.api.baseInfo.interfaces.IBaseInfoSV;
import com.ai.baas.bmc.api.baseInfo.params.BaseCode;
import com.ai.baas.bmc.api.baseInfo.params.BaseCodeInfo;
import com.ai.baas.bmc.api.baseInfo.params.QueryInfoParams;
import com.ai.baas.bmc.api.custInfo.interfaces.ICustInfoQuerySV;
import com.ai.baas.bmc.api.custInfo.params.CustInfo;
import com.ai.baas.bmc.api.custInfo.params.CustInfoResponse;
import com.ai.baas.bmc.api.custInfo.params.QueryCustInfoRequest;
import com.ai.baas.bmc.api.detailbill.interfaces.IDetailBillQuerySV;
import com.ai.baas.bmc.api.detailbill.params.DetailBillResponse;
import com.ai.baas.bmc.api.detailbill.params.GPRSResponse;
import com.ai.baas.bmc.api.detailbill.params.QueryBillRequest;
import com.ai.baas.bmc.api.detailbill.params.VoiceResponse;
import com.ai.baas.op.web.constants.BaaSOPConstants;
import com.ai.baas.op.web.model.custinfo.CustInfoDetail;
import com.ai.baas.op.web.model.custinfo.CustInfoExt;
import com.ai.baas.op.web.model.custinfo.CustInfoQueryParam;
import com.ai.baas.op.web.model.custinfo.DownLoadRequest;
import com.ai.baas.op.web.util.AmountUtil;
import com.ai.baas.op.web.util.DateTransferUtil;
import com.ai.baas.op.web.util.GprsTransfer;
import com.ai.baas.op.web.util.TradeSeqUtil;
import com.ai.net.xss.util.StringUtil;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.components.excel.util.ExcelStringUtil;
import com.ai.opt.sdk.components.excel.util.ReflectUtil;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sdk.web.model.ResponseData;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import com.ai.opt.sso.client.filter.SSOClientUser;

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

@Controller
@RequestMapping("/billdetail")
public class BillDetailController {
	private static final Logger LOG = Logger.getLogger(BillDetailController.class);

	@RequestMapping("/toList")
	public String toList() {
		return "jsp/billdetail/billDetailList";
	}

	@RequestMapping("/custInfoList")
	@ResponseBody
	public ResponseData<PageInfo<CustInfoExt>> getCustInfos(CustInfoQueryParam param, HttpServletRequest request) {
		ICustInfoQuerySV iCustInfoQuerySV = DubboConsumerFactory.getService(ICustInfoQuerySV.class);
		QueryCustInfoRequest vo = new QueryCustInfoRequest();
		ResponseData<PageInfo<CustInfoExt>> responseData = null;
		try {
			HttpSession session = request.getSession();
			SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);

			String strPageNo = (null == request.getParameter(BaaSOPConstants.PAGENO)) ? "1"
					: request.getParameter(BaaSOPConstants.PAGENO);
			String strPageSize = (null == request.getParameter(BaaSOPConstants.PAGESIZE)) ? "10"
					: request.getParameter(BaaSOPConstants.PAGESIZE);
			vo.setPageNo(Integer.parseInt(strPageNo));
			vo.setPageSize(Integer.parseInt(strPageSize));
			vo.setTenantId(user.getTenantId());
			 //vo.setTenantId("VIV-BYD");
			if (!StringUtil.isBlank(param.getCustGrade())) {
				vo.setCustGrade(param.getCustGrade());
			}
			if (!StringUtil.isBlank(param.getCustName())) {
				vo.setCustName(param.getCustName());

			}
			if (!StringUtil.isBlank(param.getServiceId())) {
				vo.setServiceId(param.getServiceId());
			}
			vo.setTradeSeq(TradeSeqUtil.newTradeSeq(user.getTenantId()));
			vo.setIdNumber(param.getIdNumber());
			CustInfoResponse response = iCustInfoQuerySV.getCustInfos(vo);

			PageInfo<CustInfo> pageInfo = response.getPageInfo();
			List<CustInfo> list = pageInfo.getResult();
			List<CustInfoExt> infoExt = new ArrayList<CustInfoExt>();
			if (!CollectionUtil.isEmpty(list)) {
				for (CustInfo ci : list) {
					CustInfoExt iExt = new CustInfoExt();
					BeanUtils.copyProperties(iExt, ci);
					if ("A".equals(ci.getCustGrade())) {
						iExt.setCustGradeVal("V1");
					} else if ("B".equals(ci.getCustGrade())) {
						iExt.setCustGradeVal("V2");
					} else if ("C".equals(ci.getCustGrade())) {
						iExt.setCustGradeVal("V3");
					} else if ("D".equals(ci.getCustGrade())) {
						iExt.setCustGradeVal("V4");
					} else {
						iExt.setCustGradeVal("V5");
					}
					infoExt.add(iExt);
				}
			}

			PageInfo<CustInfoExt> pageInfoRes = new PageInfo<CustInfoExt>();
			pageInfoRes.setCount(pageInfo.getCount());
			pageInfoRes.setPageCount(pageInfo.getPageCount());
			pageInfoRes.setPageNo(pageInfo.getPageNo());
			pageInfoRes.setPageSize(pageInfo.getPageSize());
			pageInfoRes.setResult(infoExt);
			responseData = new ResponseData<PageInfo<CustInfoExt>>(ResponseData.AJAX_STATUS_SUCCESS, "获取信息成功",
					pageInfoRes);
		} catch (Exception e) {
			responseData = new ResponseData<PageInfo<CustInfoExt>>(ResponseData.AJAX_STATUS_FAILURE, "获取信息异常");
			LOG.info("获取信息出错：", e);
		}

		return responseData;
	}

	@RequestMapping("/getCustGrade")
	@ResponseBody
	public ResponseData<BaseCodeInfo> getCustGrade(HttpServletRequest request, QueryInfoParams param) {
		ResponseData<BaseCodeInfo> responseData = null;
		try {
			// BaseCodeInfo getBaseInfo(QueryInfoParams param)
			HttpSession session = request.getSession();
			SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);

			param.setTradeSeq(TradeSeqUtil.newTradeSeq(user.getTenantId()));
			param.setTenantId(user.getTenantId());
			param.setParamType("CUST_LEVEL");
			IBaseInfoSV baseInfoSV = DubboConsumerFactory.getService(IBaseInfoSV.class);
			BaseCodeInfo info = baseInfoSV.getBaseInfo(param);
			responseData = new ResponseData<BaseCodeInfo>(ResponseData.AJAX_STATUS_SUCCESS, "获取数据成功", info);
		} catch (Exception e) {
			responseData = new ResponseData<BaseCodeInfo>(ResponseData.AJAX_STATUS_FAILURE, "获取数据失败", null);
			LOG.info("获取数据失败：", e);
		}

		return responseData;
	}

	@RequestMapping("/downLoadBill")
	public void downLoadDetailBill(DownLoadRequest downLoadRequest, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
		
		try {
		IDetailBillQuerySV iDetailBillQuerySV = DubboConsumerFactory.getService(IDetailBillQuerySV.class);
		//封装查询参数
		QueryBillRequest queryBillRequest = generateQueryParam(downLoadRequest, user);
		 //查询
		 DetailBillResponse detailResponse=iDetailBillQuerySV.getDetailBill(queryBillRequest);
		 VoiceResponse voices=detailResponse.getVoice();

		// 第一个表单的相关信息
		CustInfoDetail custInfoDetail = getCustInfoDetail(downLoadRequest, detailResponse);
		
		WritableWorkbook workbook = null;
		ServletOutputStream os = null;
		Map<String,String> callTypeMap=getBaseCode(user.getTenantId(), "CALL_TYPE");
		Map<String,String> longTypeMap=getBaseCode(user.getTenantId(), "LONG_TYPE");
		
			try {
				os = response.getOutputStream();
				response.reset();// 清空输出流
				response.setContentType("application/msexcel");// 定义输出类型
				response.setHeader("Content-disposition",
						"attachment; filename=bill-detail-" + queryBillRequest.getSearchTime() + ".xls");// 设定输出文件头
				workbook = Workbook.createWorkbook(os);
				// 根据当前工作表数量创建相应编号的工作表
				// 创建第一个工作表单（客户详单信息）
				int sheetNo = workbook.getNumberOfSheets() + 1;
				WritableSheet sheet = workbook.createSheet("客户详单信息(" + queryBillRequest.getSearchTime() + ")", sheetNo);
				sheet.getSettings().setShowGridLines(true);

				String[] custInfoColArray = new String[] { "客户名称", "客户等级", "查询月份", "当前产品", "资源号码", "费用合计", "查询日期" };
				String[] custInfoColValue = new String[] { "custName", "custGrade", "searchTime", "productList","serviceId", "totalMoney", "currentTime" };
				int listCount = 0; /// 用于记录含有list的行数
				for (int i = 0; i < custInfoColArray.length; i++) {
					// 设置标题
					WritableCellFormat titleCellFormat = buildTitleCellFormat(false);
					titleCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.DARK_BLUE);
					Label title = new Label(0, i + listCount, custInfoColArray[i], titleCellFormat);
					sheet.addCell(title);
					// 设置单元格宽度
					sheet.setColumnView(0, custInfoColArray[i].length() + 10);

					Object result = ReflectUtil.invokeGetter(custInfoDetail, custInfoColValue[i]);
					boolean listType = isListType(CustInfoDetail.class, custInfoColValue[i]);
					// 如果是list
					if (listType) {
						WritableCellFormat valueFormat = buildValueFormat(custInfoColArray[i], true, true);

					
						if(!CollectionUtil.isEmpty(custInfoDetail.getProductList())){
							int size = custInfoDetail.getProductList().size();
							
							
							for (int j = 0; j < size; j++) {

								Label labelValue = new Label(1, i + listCount + j, custInfoDetail.getProductList().get(j),
										valueFormat);
								sheet.addCell(labelValue);
								sheet.setColumnView(1, custInfoDetail.getProductList().get(j).length() + 10);
							}	
							listCount = listCount + size - 1;
							sheet.mergeCells(0, i, 0, i + listCount);
						}else{
							Label labelValue = new Label(1, i + listCount + 0, " ",
									valueFormat);
							sheet.addCell(labelValue);
							sheet.setColumnView(1, " ".length() + 10);
						}
						
						
						
						
						

					} else {
						WritableCellFormat valueFormat = buildValueFormat(custInfoColArray[i], true, true);

						String value = ExcelStringUtil.toString(result);

						Label valueLabel = new Label(1, i + listCount, value, valueFormat);
						sheet.addCell(valueLabel);
						// 设置单元格宽度
						sheet.setColumnView(1, result.toString().length() + 10);

					}

				}
			

				// 创建第二个工作表单（通话详单）
				int sheetNo1 = workbook.getNumberOfSheets() + 1;
				WritableSheet sheet1 = workbook.createSheet("通话详单(" + queryBillRequest.getSearchTime() + ")", sheetNo1);
				sheet1.getSettings().setShowGridLines(true);

				// 写入总结信息---start----
				WritableCellFormat titleCellFormat1 = buildTitleCellFormat(false);
				titleCellFormat1.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.DARK_BLUE);

				String[] voiceNameArray = new String[] { "通话时长", "费用合计" };
				String[] voiceValue = new String[] { "voiceTotal", "totalMoney" };

				// ------------设置通话时长单元格
				Label title1 = new Label(0, 0, voiceNameArray[0], titleCellFormat1);
				sheet1.addCell(title1);
				// 设置单元格宽度
				sheet1.setColumnView(0, voiceNameArray[0].length() + 10);

				WritableCellFormat valueFormat = buildValueFormat(voiceNameArray[0], true, true);

				Object result1 = ReflectUtil.invokeGetter(voices, voiceValue[0]);

				String value = DateTransferUtil.tranferSec(Long.valueOf(ExcelStringUtil.toString(result1)));

				Label valueLabel = new Label(1, 0, value, valueFormat);
				sheet1.addCell(valueLabel);
				// 设置单元格宽度
				sheet1.setColumnView(1, result1.toString().length() + 15);
				// -----------设置通话费用单元格

				Label title2 = new Label(0, 1, voiceNameArray[1], titleCellFormat1);
				sheet1.addCell(title2);
				sheet1.setColumnView(0, voiceNameArray[1].length() + 15);

				Object totalMoney = ReflectUtil.invokeGetter(voices, voiceValue[1]);
				String moneyValue =AmountUtil.Li2Yuan(Double.valueOf(ExcelStringUtil.toString(totalMoney)))+BaaSOPConstants.DetailBillConstant.YUAN;
				
				Label moneyLabel = new Label(1, 1, moneyValue, valueFormat);
				sheet1.addCell(moneyLabel);
				sheet1.setColumnView(1, totalMoney.toString().length() + 15);

				// 写入总结信息----end----

				// 书写表格信息
				String[] voiceArrayTitle = new String[] { "序号", "业务类型", "通话起始时间", "通话时长", "呼叫类型", "对方号码", "通话地点",
						"通话类型", "通话费", "其他费", "小计" };
				String[] voiceValueArray = new String[] { "xuhao", "serviceType", "startTime", "duration", "callType",
						"oppNumber", "visitArea", "longType", "fee1", "fee3", "subTotal" };
				// 标题样式
				WritableCellFormat headFormat = buildHeadCellFormat();
				for (int k = 0; k < voiceArrayTitle.length; k++) {
					Label label = new Label(k, 4, voiceArrayTitle[k], headFormat);
					sheet1.addCell(label);
					sheet1.setColumnView(k, voiceArrayTitle[k].length() + 15);

				}

				for (int i = 0; i < voices.getVoice().size(); i++) {// 循环遍历list，然后给每一个cell赋值
					for (int j = 0; j < voiceValueArray.length; j++) {
						// Label label=new Label(); 如果j=0是序号，选哟单独处理
						if (j == 0) {
							int num = i + 1;

							Label innerLabel = new Label(j, (i + 5), String.valueOf(num), valueFormat);
							sheet1.addCell(innerLabel);
							sheet1.setColumnView(j, String.valueOf(num).length() + 10);

						} else if (j == 10) {

							BigDecimal fee1 = new BigDecimal( voices.getVoice().get(i).getFee1());
							BigDecimal fee2 = new BigDecimal( voices.getVoice().get(i).getFee2());
							BigDecimal fee3 = new BigDecimal( voices.getVoice().get(i).getFee3());
							
							Double totalfee=fee1.add(fee2).add(fee3).doubleValue();
							/*Long totalfee = Long.valueOf( voices.getVoice().get(i).getFee1())
									+Long.valueOf(voices.getVoice().get(i).getFee2())+ Long.valueOf( voices.getVoice().get(i).getFee3());*/
							
							Label innerLabel = new Label(j, (i + 5), AmountUtil.Li2Yuan(totalfee)+BaaSOPConstants.DetailBillConstant.YUAN, valueFormat);
							sheet1.addCell(innerLabel);
							sheet1.setColumnView(j, String.valueOf(totalfee).length() + 10);
						} else {
							Object rvalue = ReflectUtil.invokeGetter( voices.getVoice().get(i), voiceValueArray[j]);
							String cellvalue = ExcelStringUtil.toString(rvalue);

							if("duration".equals(voiceValueArray[j])){
								cellvalue=DateTransferUtil.tranferSec(Long.valueOf(cellvalue));
							}
							if("serviceType".equals(voiceValueArray[j])){
								if("VOICE".equals(cellvalue)){
									cellvalue="语音电话";
								}
							}
							if("fee1".equals(voiceValueArray[j])){
								Object feeVal=ReflectUtil.invokeGetter( voices.getVoice().get(i), "fee2");
							//	cellvalue=AmountUtil.Li2Y(Long.valueOf(cellvalue)+Long.valueOf(ExcelStringUtil.toString(feeVal)))+BaaSOPConstants.DetailBillConstant.YUAN;
								
								cellvalue=AmountUtil.Li2Yuan(new BigDecimal(cellvalue).add(new BigDecimal(ExcelStringUtil.toString(feeVal))).doubleValue())+BaaSOPConstants.DetailBillConstant.YUAN;

							}
							if("fee3".equals(voiceValueArray[j])){
								
								//cellvalue=AmountUtil.Li2Y(Long.valueOf(cellvalue))+BaaSOPConstants.DetailBillConstant.YUAN;
								cellvalue=AmountUtil.Li2Yuan(new BigDecimal(cellvalue).doubleValue())+BaaSOPConstants.DetailBillConstant.YUAN;
							}
							if("callType".equals(voiceValueArray[j])){
								cellvalue=callTypeMap.get(cellvalue);
							}
							if("longType".equals(voiceValueArray[j])){
								cellvalue=longTypeMap.get(cellvalue);
							}
							Label innerLabel = new Label(j, (i + 5), cellvalue, valueFormat);
							sheet1.addCell(innerLabel);
							sheet1.setColumnView(1, cellvalue.length() + 10);
						}

					}
				}

				// 语音表单创建完毕

				// 创建第三个工作表单（上网流量）
				int sheetNo2 = workbook.getNumberOfSheets() + 1;
				WritableSheet sheet2 = workbook.createSheet("上网流量(" + queryBillRequest.getSearchTime() + ")", sheetNo2);
				sheet2.getSettings().setShowGridLines(true);

				//获取流量的假数据
				GPRSResponse gr =detailResponse.getGprs();
				/// -------写入总计信息
				WritableCellFormat titleCellFormat2 = buildTitleCellFormat(false);
				titleCellFormat2.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.DARK_BLUE);

				String[] gpNameArray = new String[] { "总流量合计", "费用合计" };
				String[] gpValue = new String[] { "gprsTotal", "totalMoney" };

				// ------------设置通话时长单元格
				Label title3 = new Label(0, 0, gpNameArray[0], titleCellFormat2);
				sheet2.addCell(title3);
				// 设置单元格宽度
				sheet2.setColumnView(0, gpNameArray[0].length() + 10);

				WritableCellFormat gpvalueFormat = buildValueFormat(gpNameArray[0], true, true);

				Object gpre1 = ReflectUtil.invokeGetter(gr, gpValue[0]);

				String gpvalue = GprsTransfer.tranferByte(Long.valueOf(ExcelStringUtil.toString(gpre1)))+BaaSOPConstants.DetailBillConstant.MB;

				Label gpvalueLabel = new Label(1, 0, gpvalue, gpvalueFormat);
				sheet2.addCell(gpvalueLabel);
				// 设置单元格宽度
				sheet2.setColumnView(1, gpre1.toString().length() + 10);
				// -----------设置通话费用单元格

				Label title4 = new Label(0, 1, gpNameArray[1], titleCellFormat1);
				sheet2.addCell(title4);
				sheet2.setColumnView(0, gpNameArray[1].length() + 10);

				Object totalMoney1 = ReflectUtil.invokeGetter(gr, gpValue[1]);
				
				String moneyValue1 = AmountUtil.Li2Yuan(new BigDecimal(ExcelStringUtil.toString(totalMoney1)).doubleValue())+BaaSOPConstants.DetailBillConstant.YUAN;
				
				Label moneyLabel1 = new Label(1, 1, moneyValue1, gpvalueFormat);
				sheet2.addCell(moneyLabel1);
				sheet2.setColumnView(1, totalMoney1.toString().length() + 10);

				Map<String,String> calTypeMap=getBaseCode(user.getTenantId(), "CAL_TYPE");
				Map<String,String> specialMap=getBaseCode(user.getTenantId(), "SPECIAL_TYPE");
				// 设置语音详细信息
				String[] gprsNameArray = new String[] { "序号", "计费类型", "是否定向", "起始时间", "上行流量", "下行流量", "通信地点", "费用",
						 };
				String[] gprsValue = new String[] { "xuhao", "calType", "isSpecial","startTime", "gprsUp", "gprsDown", "visitArea",
						"fee1" };

				// 标题样式
				WritableCellFormat headFormat1 = buildHeadCellFormat();
				for (int k = 0; k < gprsNameArray.length; k++) {
					Label label = new Label(k, 4, gprsNameArray[k], headFormat1);
					sheet2.addCell(label);
					sheet2.setColumnView(k, gprsNameArray[k].length() + 15);

				}
				for (int i = 0; i < gr.getGprs().size(); i++) {
					for (int j = 0; j < gprsValue.length; j++) {
						if (j == 0) { 
							int num = i + 1;
							
							Label innerLabel = new Label(j, (i + 5), String.valueOf(num), valueFormat);
							sheet2.addCell(innerLabel);
							sheet2.setColumnView(j, String.valueOf(num).length() + 10);

						}else {
							Object rvalue = ReflectUtil.invokeGetter(gr.getGprs().get(i), gprsValue[j]);
							String cellvalue = ExcelStringUtil.toString(rvalue);
							if("gprsUp".equals(gprsValue[j])||"gprsDown".equals(gprsValue[j])){
								cellvalue=GprsTransfer.tranferByte(Long.valueOf(cellvalue))+BaaSOPConstants.DetailBillConstant.MB;
							}
							if("fee1".equals(gprsValue[j])){
								cellvalue=AmountUtil.Li2Yuan(Double.valueOf(cellvalue))+BaaSOPConstants.DetailBillConstant.YUAN;
							}
							if("calType".equals(gprsValue[j])){
								cellvalue=calTypeMap.get(cellvalue);
							}
							if("isSpecial".equals(gprsValue[j])){
								cellvalue=specialMap.get(cellvalue);
							}
							Label innerLabel = new Label(j, (i + 5), cellvalue, valueFormat);
							sheet2.addCell(innerLabel);
							sheet2.setColumnView(1, cellvalue.length() + 10);
						}
						
					}
				}

			} finally {
				if (workbook != null) {
					workbook.write();
					workbook.close();
					// 关闭输出流
					if (os != null) {
						os.flush();
						os.close();
					}
				}
			}

		} catch (Exception e) {
			LOG.info("异常信息", e);
		}

	}

	private QueryBillRequest generateQueryParam(DownLoadRequest downLoadRequest, SSOClientUser user) {
		QueryBillRequest queryBillRequest =new QueryBillRequest();
		 queryBillRequest.setTenantId(user.getTenantId());
		 queryBillRequest.setCustId(downLoadRequest.getCustId());
		 queryBillRequest.setSearchTime(downLoadRequest.getSearchTime());
		 queryBillRequest.setServiceId(downLoadRequest.getServiceId());
		 queryBillRequest.setSubsId(downLoadRequest.getSubsId());
		 queryBillRequest.setTradeSeq(TradeSeqUtil.newTradeSeq(user.getTenantId()));
		return queryBillRequest;
	}

	/**
	 * 获取客户基本信息
	 * @param downLoadRequest
	 * @param detailResponse
	 * @return
	 * @author gaogang
	 * @throws UnsupportedEncodingException 
	 * @ApiDocMethod
	 * @ApiCode
	 */
	private CustInfoDetail getCustInfoDetail(DownLoadRequest downLoadRequest, DetailBillResponse detailResponse) throws UnsupportedEncodingException {
		CustInfoDetail custInfoDetail = new CustInfoDetail();
		custInfoDetail.setCurrentTime(DateUtil.getDateString("yyyy-MM-dd"));
		custInfoDetail.setCustGrade(downLoadRequest.getCustGrade());
		custInfoDetail.setCustName(URLDecoder.decode(downLoadRequest.getCustName(), "UTF-8"));
		custInfoDetail.setProductList(detailResponse.getProductNames());
		custInfoDetail.setSearchTime(downLoadRequest.getSearchTime());
		custInfoDetail.setServiceId(downLoadRequest.getServiceId());
		Double vocieTotal= detailResponse.getVoice().getTotalMoney();
		Double gprsTotal=detailResponse.getGprs().getTotalMoney();
		String total=AmountUtil.Li2Yuan(new BigDecimal(vocieTotal).add(new BigDecimal(gprsTotal)).doubleValue());
		custInfoDetail.setTotalMoney(total+BaaSOPConstants.DetailBillConstant.YUAN);
		return custInfoDetail;
	}

	

	/**
	 * 获取值得样式
	 * 
	 * @param colName
	 * @param amountFieldSet
	 * @return
	 * @throws WriteException
	 */
	private WritableCellFormat buildValueFormat(String colName, boolean hasBorder, boolean isTable)
			throws WriteException {
		// 金额字段名
		Set<String> amountFieldSet = getAmountFieldSet();
		WritableCellFormat valueFormat = null;
		if (amountFieldSet.contains(colName)) {
			valueFormat = buildBodyDigitalFormat(hasBorder);
		} else {
			valueFormat = buildBodyCellFormat(hasBorder);
			if (isTable) {
				valueFormat.setAlignment(Alignment.LEFT);
			}
		}
		return valueFormat;
	}

	/**
	 * 设置数字样式
	 * 
	 * @return
	 * @throws WriteException
	 */
	private WritableCellFormat buildBodyDigitalFormat(boolean hasBorder) throws WriteException {
		// 正文数字字体
		WritableFont bodyDigitalwfont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false,
				UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		// 正文数字样式
		WritableCellFormat bodyDigitalFormat = new WritableCellFormat(bodyDigitalwfont);
		bodyDigitalFormat.setAlignment(Alignment.RIGHT);
		bodyDigitalFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		if (hasBorder) {
			bodyDigitalFormat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.DARK_BLUE);
		} else {
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
		WritableFont bodywfont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false,
				UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		// 正文样式
		WritableCellFormat bodyFormat = new WritableCellFormat(bodywfont);
		bodyFormat.setAlignment(Alignment.CENTRE);
		bodyFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		if (hasBorder) {
			bodyFormat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.DARK_BLUE);
		} else {
			bodyFormat.setBorder(Border.ALL, BorderLineStyle.NONE);
		}
		return bodyFormat;
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



	/**
	 * 设置标题样式
	 * 
	 * @return
	 * @throws WriteException
	 */
	private WritableCellFormat buildTitleCellFormat(boolean hasBorder) throws WriteException {
		// 标题字体
		WritableFont headwfont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false,
				UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		// 标题样式
		WritableCellFormat headFormat = new WritableCellFormat(headwfont);
		headFormat.setAlignment(Alignment.LEFT);
		headFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		if (hasBorder) {
			headFormat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.DARK_BLUE);
		} else {
			headFormat.setBorder(Border.ALL, BorderLineStyle.NONE);
		}
		return headFormat;
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
		}
		return flag;
	}

	private WritableCellFormat buildHeadCellFormat() throws WriteException {
		// 标题字体
		WritableFont headwfont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false,
				UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		// 标题样式
		WritableCellFormat headFormat = new WritableCellFormat(headwfont);
		headFormat.setAlignment(Alignment.CENTRE);
		headFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		headFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		headFormat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.DARK_BLUE);
		headFormat.setBackground(Colour.ICE_BLUE);
		return headFormat;
	}
	private Map<String,String> getBaseCode(String tenantId,String paramType){
		QueryInfoParams param=new QueryInfoParams();
		param.setTenantId(tenantId);
		param.setParamType(paramType);
		Map<String,String> retMap=new HashMap<String,String>();
		try {
			param.setTradeSeq(TradeSeqUtil.newTradeSeq(param.getTenantId()));
			IBaseInfoSV baseInfoSV = DubboConsumerFactory
					.getService(IBaseInfoSV.class);
			BaseCodeInfo info = baseInfoSV.getBaseInfo(param);
			List<BaseCode> list=info.getParamList();
			for(BaseCode code:list){
				retMap.put(code.getParamCode(), code.getParamName());	
			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		
		
		
		return retMap;
	}

}
