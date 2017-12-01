package com.ai.citic.billing.web.controller.invoice;

import com.ai.baas.amc.api.invoice.interfaces.IInvoiceInfoSV;
import com.ai.baas.amc.api.invoice.params.*;
import com.ai.baas.bmc.api.queryidinfo.params.BlAcctInfoInfo;
import com.ai.citic.billing.web.common.CommonCaller;
import com.ai.citic.billing.web.constants.CiticWebConstants;
import com.ai.citic.billing.web.model.invoice.InvoiceCustInfo;
import com.ai.citic.billing.web.model.invoice.InvoiceInfo;
import com.ai.citic.billing.web.model.invoice.InvoiceQueryParam;
import com.ai.citic.billing.web.model.invoice.InvoiceRecordShowVo;
import com.ai.citic.billing.web.model.sso.CiticSSOClientUser;
import com.ai.citic.billing.web.util.DateUtil;
import com.ai.citic.billing.web.util.FeesStringUtil;
import com.ai.citic.billing.web.util.NumberValidationUtils;
import com.ai.citic.billing.web.util.excel.CellField;
import com.ai.citic.billing.web.util.excel.ExcelUtil;
import com.ai.citic.billing.web.util.excel.SheetField;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.opt.sdk.web.model.ResponseData;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

/**
 * 发票管理模块
 * @author wangluyang
 *
 */
@RestController
@RequestMapping("/invoice")
public class InvoiceController {

	public static final Logger LOGGER = LoggerFactory.getLogger(InvoiceController.class);
	private final String BILL_MONTH_FORMAT_STR = "%s年%s月";
	
	@Autowired
    private CommonCaller commonCaller;
	
	@RequestMapping("/toInvoiceList")
    public ModelAndView toInvoiceList(HttpServletRequest request, HttpServletResponse response){
        ModelAndView view = new ModelAndView("jsp/invoice/invoiceList");
        return view;
    }
	
	@RequestMapping("/toUserInvoiceList")
    public ModelAndView toUserInvoiceList(HttpServletRequest request, HttpServletResponse response){
        ModelAndView view = new ModelAndView("jsp/invoice/userInvoiceList");
        return view;
    }
	
	/**
	 * 发票记录查询
	 * @param queryVo
	 * @param request
	 * @return
	 */
    @RequestMapping("/getList")
    public ResponseData<PageInfo<InvoiceRecordShowVo>> getList(InvoiceQueryParam queryVo, HttpServletRequest request){
    	
    	ResponseData<PageInfo<InvoiceRecordShowVo>> responseData = null;
    	try {
    		HttpSession session = request.getSession();
    		CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);

    		IInvoiceInfoSV iInvoiceInfoSV = DubboConsumerFactory.getService("iInvoiceInfoSV");
    		InvoiceRecordQueryParam queryParam = new InvoiceRecordQueryParam();
    		queryParam.setTenantId(user.getTenantId());
    		queryParam.setUserType("1");
    		if(StringUtils.equals("1", queryVo.getByMonth())){
    			List<String> list = new ArrayList<>();
    			list.add(queryVo.getBillMonth());
    			queryParam.setBillMonth(list); 
    		}else{
    			queryParam.setBillMonth(DateUtil.getMonthListBetweenTimes(queryVo.getStartTime(), queryVo.getEndTime(), "yyyy-MM", "yyyyMM"));
    		}
    		boolean queryName = true;
    		if(!StringUtil.isBlank(queryVo.getCompanyName())){
                //调用中信api根据租户名称查询租户id
    			List<Long> custIds = new ArrayList<>();
                List<BlAcctInfoInfo> blAcctInfoInfos = commonCaller.getAcctAndCustListByName(user.getTenantId(), queryVo.getCompanyName());
                if(!CollectionUtil.isEmpty(blAcctInfoInfos)){
                    for(BlAcctInfoInfo blAcctInfoInfo:blAcctInfoInfos){
                    	custIds.add(Long.parseLong(blAcctInfoInfo.getAcctId()));
                    }
                }
                if(custIds!=null || custIds.size()==0){
                	queryName = false;
                }
                queryParam.setAcctIds(custIds);
            }
    		if(StringUtils.equals("2", queryVo.getStatus())){
    			queryName = false;
    		}else{
    			queryParam.setStatus(queryVo.getStatus());
    		}
    		
    		String strPageNo=(null==request.getParameter(CiticWebConstants.PAGENO))?"1":request.getParameter(CiticWebConstants.PAGENO);
	    	String strPageSize=(null==request.getParameter(CiticWebConstants.PAGESIZE))?"10":request.getParameter(CiticWebConstants.PAGESIZE);
	    	queryParam.setPageNo(Integer.parseInt(strPageNo));
	    	queryParam.setPageSize(Integer.parseInt(strPageSize));
    		
	    	LOGGER.info("发票记录查询入參"+JSONArray.fromObject(queryParam).toString());
    		InvoiceRecordQueryReponse response = iInvoiceInfoSV.queryInvoiceRecord(queryParam);
    		PageInfo<InvoiceRecordVo> result = response.getRecords();
    		PageInfo<InvoiceRecordShowVo> info = new PageInfo<InvoiceRecordShowVo>();
    		List<InvoiceRecordShowVo> resultList = new ArrayList<InvoiceRecordShowVo>();
    		if(result!=null && result.getResult()!=null && 
    				(queryName || (queryParam.getAcctIds()!=null && queryParam.getAcctIds().size()>0))){
    			int pageNo = result.getPageNo();
    			int pageSize = result.getPageSize();
    			for(int i=0; i<result.getResult().size(); i++){
    				InvoiceRecordShowVo vo = new InvoiceRecordShowVo();
    				vo.setIndex(String.valueOf((pageNo-1)*pageSize+1+i));
    				vo.setRecord(result.getResult().get(i));
    				resultList.add(vo);
    			}
    			info.setPageCount(result.getPageCount());
    			info.setCount(result.getCount());
    			info.setPageNo(result.getPageNo());
    			info.setPageSize(result.getPageSize()); 
    		}else{
    			info.setPageCount(1);
    			info.setCount(0);
    			info.setPageNo(Integer.parseInt(strPageNo));
    			info.setPageSize(Integer.parseInt(strPageSize)); 
    		}
    		info.setResult(resultList);
    		responseData = new ResponseData<PageInfo<InvoiceRecordShowVo>>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功", info);
    	} catch (Exception e) {
			responseData = new ResponseData<PageInfo<InvoiceRecordShowVo>>(ResponseData.AJAX_STATUS_FAILURE,"获取信息异常");
			LOGGER.error("获取信息出错：", e);
		}
        return responseData;
    }
    
    /**
	 * 发票记录查询
	 * @param request
	 * @return
	 */
    @RequestMapping("/getUserInvoiceList")
    public ResponseData<PageInfo<InvoiceRecordShowVo>> getUserInvoiceList( HttpServletRequest request){
    	
    	ResponseData<PageInfo<InvoiceRecordShowVo>> responseData = null;
    	try {
    		HttpSession session = request.getSession();
    		CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
    		
    		IInvoiceInfoSV iInvoiceInfoSV = DubboConsumerFactory.getService("iInvoiceInfoSV");
    		
    		InvoiceRecordQueryParam queryParam = new InvoiceRecordQueryParam();
    		queryParam.setTenantId(user.getTenantId());
    		queryParam.setUserType("0");
    		
    		if(!StringUtil.isBlank(user.getTenant())){
                List<Long> ids = new ArrayList<>();
				List<BlAcctInfoInfo> acctAndCustInfos = commonCaller.getAcctAndCustInfo(user.getTenantId(), user.getTenant());
				if(!CollectionUtil.isEmpty(acctAndCustInfos)){
					for(BlAcctInfoInfo acctAndCustInfo:acctAndCustInfos){
						ids.add(Long.parseLong(acctAndCustInfo.getAcctId()));
					}
                }
                queryParam.setAcctIds(ids);
            }
    		
    		String strPageNo=(null==request.getParameter(CiticWebConstants.PAGENO))?"1":request.getParameter(CiticWebConstants.PAGENO);
	    	String strPageSize=(null==request.getParameter(CiticWebConstants.PAGESIZE))?"10":request.getParameter(CiticWebConstants.PAGESIZE);
	    	queryParam.setPageNo(Integer.parseInt(strPageNo));
	    	queryParam.setPageSize(Integer.parseInt(strPageSize));
    		
	    	LOGGER.info("租户发票记录查询入參"+JSONArray.fromObject(queryParam).toString());
	    	if(!CollectionUtil.isEmpty(queryParam.getAcctIds())){
	    		InvoiceRecordQueryReponse response = iInvoiceInfoSV.queryInvoiceRecord(queryParam);
	    		PageInfo<InvoiceRecordVo> result = response.getRecords();
	    		PageInfo<InvoiceRecordShowVo> info = new PageInfo<InvoiceRecordShowVo>();
	    		List<InvoiceRecordShowVo> resultList = new ArrayList<InvoiceRecordShowVo>();
	    		if(result!=null && result.getResult()!=null){
	    			int pageNo = result.getPageNo();
	    			int pageSize = result.getPageSize();
	    			for(int i=0; i<result.getResult().size(); i++){
	    				InvoiceRecordShowVo vo = new InvoiceRecordShowVo();
	    				vo.setIndex(String.valueOf((pageNo-1)*pageSize+1+i));
	    				vo.setRecord(result.getResult().get(i));
	    				resultList.add(vo);
	    			}
	    			info.setPageCount(result.getPageCount());
	    			info.setCount(result.getCount());
	    			info.setPageNo(result.getPageNo());
	    			info.setPageSize(result.getPageSize()); 
	    		}else{
	    			info.setPageCount(1);
	    			info.setCount(0);
	    			info.setPageNo(Integer.parseInt(strPageNo));
	    			info.setPageSize(Integer.parseInt(strPageSize)); 
	    		}
	    		info.setResult(resultList);
	    		responseData = new ResponseData<PageInfo<InvoiceRecordShowVo>>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功", info);
	    	}else{
	    		responseData = new ResponseData<PageInfo<InvoiceRecordShowVo>>(ResponseData.AJAX_STATUS_SUCCESS, "获取账户id失败");
	    	}
    	} catch (Exception e) {
			responseData = new ResponseData<PageInfo<InvoiceRecordShowVo>>(ResponseData.AJAX_STATUS_FAILURE,"获取信息异常");
			LOGGER.error("获取信息出错：", e);
		}
        return responseData;
    }
    
    /**
     * 导入
     * @param request
     * @param response
     */
    @RequestMapping("/importExcel")
    public ResponseData<Object> importExcel(HttpServletRequest request, HttpServletResponse response){
    	
    	ResponseData<Object> responseData = null;
    	try {
    		HttpSession session = request.getSession();
    		CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
    		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
		    MultipartFile file = multipartRequest.getFile("f");
			List<InvoiceInfo> list = new ArrayList<>();
//    	序号	账户ID	客户ID	租户ID	客户名称	账期	账单金额（元）	快递单号	寄出时间
			String[] fileNames = new String[]{"index","acctId","custId","tenantId","custName","billMonth","amount","expressNo","SendTimeDateType" };
			list = ExcelUtil.readExcel(file.getInputStream(), InvoiceInfo.class, fileNames, 0, true);
			
			if(list!=null && list.size()>0){
				int totalNums = list.size();
				int successNums = 0;
				String failMsg = "";
				IInvoiceInfoSV iInvoiceInfoSV = DubboConsumerFactory.getService("iInvoiceInfoSV");
				for(InvoiceInfo vo:list){
					try {
						InvoiceRecordInsertParam insertParam = new InvoiceRecordInsertParam();
						insertParam.setAcctId(vo.getAcctId());
						insertParam.setBillMonth(StringUtils.isNotBlank(vo.getBillMonth())?vo.getBillMonth().replace("年", "").replace("月", "").trim():"");
						insertParam.setCustId(vo.getCustId());
						insertParam.setTenantId(user.getTenantId());
						if(StringUtils.isNotBlank(vo.getExpressNo())){
							if(NumberValidationUtils.isRealNumber(vo.getExpressNo())){
								insertParam.setExpressNo(vo.getExpressNo().split("\\.")[0]);
							}else{
								insertParam.setExpressNo(vo.getExpressNo());
							}
						}
						if(vo.getSendTimeDateType()!=null){
							insertParam.setSendTime(new Timestamp(vo.getSendTimeDateType().getTime()));
						}
						BaseResponse baseResponse = iInvoiceInfoSV.saveInvoiceRecord(insertParam);
						if(baseResponse.getResponseHeader().isSuccess()){
							successNums++;
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						LOGGER.error("获取信息出错：", e);
					} 
				}
				responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_SUCCESS, "导入完成，总共"+totalNums+"条，成功"+successNums+"条"+failMsg, null);
			}else{
				responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_SUCCESS, "没有数据", null);
			}
		}catch(SystemException e){
			responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_FAILURE, "导入失败,请寄出时间导入格式为xxxx年xx月xx日", null);
			LOGGER.error("获取信息出错：", e);
		}catch (Exception e) {
			responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_FAILURE, "导入失败", null);
			LOGGER.error("获取信息出错：", e);
		}
    	return responseData;
    }
    
    /**
     * 导出
     * @param queryVo
     * @param request
     * @param response
     */
    @RequestMapping("/exportToExcel")
    public void exportInvoiceToExcel(InvoiceQueryParam queryVo,HttpServletRequest request, HttpServletResponse response){
    	
        try {
        	HttpSession session = request.getSession();
    		CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
        	IInvoiceInfoSV iInvoiceInfoSV = DubboConsumerFactory.getService("iInvoiceInfoSV");
        	InvoiceRecordQueryParam queryParam = new InvoiceRecordQueryParam();
        	queryParam.setTenantId(user.getTenantId());
    		queryParam.setUserType("1");
    		if(StringUtils.equals("1", queryVo.getByMonth())){
    			List<String> list = new ArrayList<>();
    			list.add(queryVo.getBillMonth());
    			queryParam.setBillMonth(list);
    		}else{
    			queryParam.setBillMonth(DateUtil.getMonthListBetweenTimes(queryVo.getStartTime(), queryVo.getEndTime(), "yyyy-MM", "yyyyMM"));
    		}
    		if(!StringUtil.isBlank(queryVo.getCompanyName())){
                //调用中信api根据租户名称查询租户id
    			List<Long> acctIds = new ArrayList<>();
                List<BlAcctInfoInfo> blAcctInfoInfos = commonCaller.getAcctAndCustListByName(user.getTenantId(), queryVo.getCompanyName());
                if(!CollectionUtil.isEmpty(blAcctInfoInfos)){
                    for(BlAcctInfoInfo blAcctInfoInfo:blAcctInfoInfos){
						acctIds.add(Long.parseLong(blAcctInfoInfo.getAcctId()));
                    }
                }
                queryParam.setAcctIds(acctIds);
            }
    		queryParam.setStatus(queryVo.getStatus());
    		List<InvoiceInfoDetailVO> resultList = iInvoiceInfoSV.queryInvoiceInfoDetailRecord(queryParam);
    		
    		List<SheetField> sheets = new ArrayList<>();
    		//发票信息
    		SheetField invoiceSheet = new SheetField();
    		invoiceSheet.setSheetName("发票信息");
    		invoiceSheet.setSheetIndex(0);
    		invoiceSheet.setCells(this.getInvoiceCells());
    		invoiceSheet.setClazz(InvoiceInfo.class);
//    		invoiceSheet.setValues(values);
    		
    		//客户资料
    		SheetField invoiceCustSheet = new SheetField();
    		invoiceCustSheet.setSheetName("客户资料");
    		invoiceCustSheet.setSheetIndex(1);
    		invoiceCustSheet.setCells(this.getInvoiceCustCells());
    		invoiceSheet.setClazz(InvoiceCustInfo.class);
    		List<Object> invoiceInfos = new ArrayList<>();
    		List<Object> invoiceCustInfos = new ArrayList<>();
    		for(int i=0; i<resultList.size(); i++){
    			InvoiceInfoDetailVO vo = resultList.get(i);
    			InvoiceInfo info = new InvoiceInfo();
    			InvoiceCustInfo custInfo = new InvoiceCustInfo();
    			info.setIndex(String.valueOf(i+1));
    			info.setAcctId(vo.getAcctId());
    			info.setAmount(FeesStringUtil.liToyuan(vo.getTotalAmount(), 2));
    			if(StringUtils.isNotBlank(vo.getBillMonth())){
    				String billMonth = String.format(BILL_MONTH_FORMAT_STR, vo.getBillMonth().substring(0, 4), vo.getBillMonth().substring(4, 6));
    				info.setBillMonth(billMonth);
    			}
    			info.setExpressNo(vo.getExpressNo());
    			info.setCustId(String.valueOf(vo.getCustId()));
    			info.setCustName(vo.getCustName());
    			info.setSendTime(vo.getSendTime()==null?null:DateUtil.getDateString(vo.getSendTime(), "yyyy年MM月dd日"));
    			info.setTenantId(vo.getExtCustId());
    			try {
					BeanUtils.copyProperties(custInfo, vo);
					if(0==custInfo.getInvoiceType()){
						custInfo.setInvoiceTypeStr("增值税普通发票");
					}else{
						custInfo.setInvoiceTypeStr("增值税专用发票");
					}
					
				} catch (Exception e) {
				}
    			invoiceInfos.add(info);
    			invoiceCustInfos.add(custInfo);
    		}
    		invoiceSheet.setValues(invoiceInfos);
    		invoiceCustSheet.setValues(invoiceCustInfos);
    		sheets.add(invoiceSheet);
    		sheets.add(invoiceCustSheet);
        	
        	ServletOutputStream os = response.getOutputStream();
            response.reset();// 清空输出流
            response.setContentType("application/msexcel");// 定义输出类型
            response.setHeader("Content-disposition", "attachment; filename=invoice"
                    +DateUtil.getDateString(new Date(), DateUtil.yyyyMMddHHmmssSSS)+".xlsx");// 设定输出文件头
            ExcelUtil.writeExcel(os, sheets);
    	} catch (Exception e) {
			LOGGER.error("获取信息出错：", e);
		}
    	
    }
    
    private List<CellField> getInvoiceCells(){
    	List<CellField> list = new ArrayList<CellField>() {{
    	    add( new CellField("序号", "index", true));
    	    add( new CellField("账户ID", "acctId", true));
    	    add( new CellField("客户ID", "custId", true));
    	    add( new CellField("租户ID", "tenantId", true));
    	    add( new CellField("客户名称", "custName", true));
    	    add( new CellField("账期", "billMonth", true));
    	    add( new CellField("账单金额（元）", "amount", true));
    	    add( new CellField("快递单号", "expressNo", true));
    	    add( new CellField("寄出时间", "sendTime", true));
    	}};
    	return list;
    }
    
    private List<CellField> getInvoiceCustCells(){
    	List<CellField> list = new ArrayList<CellField>() {{
    	    add( new CellField("发票抬头", "title", true));
    	    add( new CellField("发票类型", "invoiceTypeStr", true));
    	    add( new CellField("税务登记号", "taxRegNo", true));
    	    add( new CellField("开户银行名称", "bankName", true));
    	    add( new CellField("注册场所地址", "regAddress", true));
    	    add( new CellField("基本开户账号", "bankAcctNo", true));
    	    add( new CellField("注册固定电话", "regPhone", true));
    	    add( new CellField("联系人姓名", "linkName", true));
    	    add( new CellField("所在地区", "regAddress", true));
    	    add( new CellField("街道地址", "address", true));
    	    add( new CellField("邮政编码", "postCode", true));
    	    add( new CellField("手机号码", "mobileNo", true));
    	    add( new CellField("固定号码", "regPhone", true));
    	    add( new CellField("邮箱地址", "email", true));
    	}};
    	return list;
    }
}
