package com.ai.baas.op.web.controller.coupon;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ai.baas.bmc.api.baseInfo.interfaces.IBaseInfoSV;
import com.ai.baas.bmc.api.baseInfo.params.BaseCodeInfo;
import com.ai.baas.bmc.api.baseInfo.params.QueryInfoParams;
import com.ai.baas.dst.api.coupon.interfaces.ICouponSV;
import com.ai.baas.dst.api.coupon.params.ConditionDetail;
import com.ai.baas.dst.api.coupon.params.CouponCodeList;
import com.ai.baas.dst.api.coupon.params.CouponExport;
import com.ai.baas.dst.api.coupon.params.CouponInfo;
import com.ai.baas.dst.api.coupon.params.CouponInfoReq;
import com.ai.baas.dst.api.coupon.params.CouponListResponse;
import com.ai.baas.dst.api.coupon.params.CouponReq;
import com.ai.baas.dst.api.coupon.params.CouponResponse;
import com.ai.baas.dst.api.coupon.params.DelReq;
import com.ai.baas.dst.api.coupon.params.ExPortReq;
import com.ai.baas.dst.api.coupon.params.ExportCouponResponse;
import com.ai.baas.dst.api.coupon.params.SingleCouponReq;
import com.ai.baas.op.web.constants.BaaSOPConstants;
import com.ai.baas.op.web.model.coupon.CouponInfoExt;
import com.ai.baas.op.web.model.coupon.CouponInfoParam;
import com.ai.baas.op.web.model.coupon.CouponList;
import com.ai.baas.op.web.model.coupon.CouponParams;
import com.ai.baas.op.web.util.TradeSeqUtil;
import com.ai.baas.prd.api.product.interfaces.IProductDefineSV;
import com.ai.baas.prd.api.product.params.CategoryInfo;
import com.ai.baas.prd.api.product.params.MainProductInfo;
import com.ai.baas.prd.api.product.params.SubProResponse;
import com.ai.baas.prd.api.product.params.SubQueryReq;
import com.ai.baas.prd.api.product.params.mainProReq;
import com.ai.baas.prd.api.product.params.mainProResponse;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.components.excel.client.AbstractExcelHelper;
import com.ai.opt.sdk.components.excel.client.impl.JxlExcelHelper;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.opt.sdk.web.model.ResponseData;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import com.ai.opt.sso.client.filter.SSOClientUser;

/**
 * 优惠券管理
 */
@Controller
@RequestMapping("/coupon")
public class CouponController {
	private static final Logger LOG = Logger.getLogger(CouponController.class);

	@RequestMapping("/couponList")
	public ModelAndView couponList(HttpServletRequest request) {
		// SSOClientUser user = (SSOClientUser)
		// request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
		ModelAndView view = new ModelAndView("jsp/coupon/couponList");
		return view;
	}

	@RequestMapping("/toAdd")
	public ModelAndView addCoupon(HttpServletRequest request) {

		ModelAndView view = new ModelAndView("jsp/coupon/addCoupon");
		return view;
	}

	@RequestMapping("/toView")
	public String viewCoupon(HttpServletRequest request, Model model) {
		String couponId = request.getParameter("couponId");
		/*
		 * HttpSession session = request.getSession(); SSOClientUser user =
		 * (SSOClientUser) session
		 * .getAttribute(SSOClientConstants.USER_SESSION_KEY);
		 */

		// ICouponSV icouponSV=DubboConsumerFactory.getService(ICouponSV.class);
		// SingleCouponReq req=new SingleCouponReq();
		// req.setCouponId(id);
		// req.setTenantId(user.getTenantId());
		// CouponResponse res= icouponSV.getSingleCoupon(req);
		model.addAttribute("couponId", couponId);

		return "jsp/coupon/viewCoupon";
	}

	@RequestMapping("/getDetail")
	@ResponseBody
	public ResponseData<CouponResponse> getDetail(HttpServletRequest request) {
		ResponseData<CouponResponse> responseData = null;
		try {
			HttpSession session = request.getSession();
			SSOClientUser user = (SSOClientUser) session
					.getAttribute(SSOClientConstants.USER_SESSION_KEY);

			String couponId = request.getParameter("couponId");
			ICouponSV icouponSV = DubboConsumerFactory
					.getService(ICouponSV.class);
			SingleCouponReq req = new SingleCouponReq();
			req.setCouponId(couponId);
			req.setTenantId(user.getTenantId());
			CouponResponse res = icouponSV.getSingleCoupon(req);

			responseData = new ResponseData<CouponResponse>(
					ResponseData.AJAX_STATUS_SUCCESS, "获取数据成功", res);
		} catch (Exception e) {
			responseData = new ResponseData<CouponResponse>(
					ResponseData.AJAX_STATUS_FAILURE, "获取数据失败", null);
			LOG.info("获取数据失败", e);
		}

		return responseData;
	}

	@RequestMapping("/getSelect")
	@ResponseBody
	public ResponseData<BaseCodeInfo> getSelect(QueryInfoParams param,
			HttpServletRequest request) {
		ResponseData<BaseCodeInfo> responseData = null;
		try {
			HttpSession session = request.getSession();
			SSOClientUser user = (SSOClientUser) session
					.getAttribute(SSOClientConstants.USER_SESSION_KEY);

			param.setTradeSeq(TradeSeqUtil.newTradeSeq(user.getTenantId()));
			param.setTenantId(user.getTenantId());
			IBaseInfoSV baseInfoSV = DubboConsumerFactory
					.getService(IBaseInfoSV.class);
			BaseCodeInfo info = baseInfoSV.getBaseInfo(param);
			responseData = new ResponseData<BaseCodeInfo>(
					ResponseData.AJAX_STATUS_SUCCESS, "获取数据成功", info);
		} catch (Exception e) {
			responseData = new ResponseData<BaseCodeInfo>(
					ResponseData.AJAX_STATUS_FAILURE, "获取数据失败", null);
			LOG.info("获取数据失败", e);
		}

		return responseData;
	}

	@RequestMapping("/add")
	@ResponseBody
	public ResponseData<String> add(CouponInfoParam param,
			HttpServletRequest request) {
		ResponseData<String> responseData = null;

		try {
			HttpSession session = request.getSession();
			SSOClientUser user = (SSOClientUser) session
					.getAttribute(SSOClientConstants.USER_SESSION_KEY);
			ICouponSV icouponSV = DubboConsumerFactory
					.getService(ICouponSV.class);
			CouponInfoReq req = new CouponInfoReq();
			req.setTenantId(user.getTenantId());
			req.setCouponValue(param.getCouponValue());

			if (!StringUtil.isBlank(param.getActiveTime())) {
				Timestamp ts = Timestamp.valueOf(param.getActiveTime()
						+ " 00:00:00");
				req.setActiveTime(ts);
				// req.setActiveTime(DateUtil.getTimestamp((param.getActiveTime()+" 00:00:00"),
				// "yyyy-MM-dd HH:mm:ss"));;
			}
			if (!StringUtil.isBlank(param.getInactiveTime())) {
				Timestamp ts = Timestamp.valueOf(param.getInactiveTime()
						+ " 23:59:59");
				req.setInactiveTime(ts);
			}

			ConditionDetail detail = new ConditionDetail();
			detail.setDstTypeUnit(param.getDstTypeUnit());
			detail.setDstUnit(param.getDstUnit());
			detail.setDstValue(param.getDstValue());
			detail.setReachAmount(param.getReachAmount());
			detail.setReachUnit(param.getReachUnit());
			req.setConditonDetail(detail);
			req.setCouponName(param.getCouponName());
			req.setCouponType(param.getCouponType());
			req.setCouponConType(param.getCouponConType());
			req.setCouponAmount(param.getCouponAmount());
			req.setConditionValue(param.getConditionValue());// 只是值，比如满300减多少
			req.setTopMoney(param.getTopMoney());
			req.setProductId(param.getProductId());
			req.setProductName(param.getProductName());
			BaseResponse res = icouponSV.addCoupon(req);

			responseData = new ResponseData<String>(
					ResponseData.AJAX_STATUS_SUCCESS, "获取数据成功", "成功");
			responseData.setResponseHeader(res.getResponseHeader());
		} catch (Exception e) {
			responseData = new ResponseData<String>(
					ResponseData.AJAX_STATUS_FAILURE, "获取数据失败", null);
			LOG.info("获取数据失败", e);
		}

		return responseData;
	}

	@RequestMapping("/delCoupon")
	@ResponseBody
	public ResponseData<BaseResponse> delCoupon(CouponParams param,
			HttpServletRequest request) {

		ResponseData<BaseResponse> responseData = null;
		try {
			HttpSession session = request.getSession();
			SSOClientUser user = (SSOClientUser) session
					.getAttribute(SSOClientConstants.USER_SESSION_KEY);
			ICouponSV icouponSV = DubboConsumerFactory
					.getService(ICouponSV.class);
			DelReq req = new DelReq();
			req.setCouponId(request.getParameter("couponId"));
			req.setTenantId(user.getTenantId());
			BaseResponse res = icouponSV.delCouponById(req);

			responseData = new ResponseData<BaseResponse>(
					ResponseData.AJAX_STATUS_SUCCESS, "获取信息成功", res);
		} catch (Exception e) {
			responseData = new ResponseData<BaseResponse>(
					ResponseData.AJAX_STATUS_FAILURE, "获取信息异常");
			LOG.info("获取信息出错：", e);
		}

		return responseData;
	}
	
	@RequestMapping("/getProductList")
	@ResponseBody
	public ResponseData<PageInfo<CategoryInfo>> getProducts(SubQueryReq param,
			HttpServletRequest request) {

		ResponseData<PageInfo<CategoryInfo>> responseData = null;
		try {
			SubQueryReq req = new SubQueryReq();
			HttpSession session = request.getSession();
			SSOClientUser user = (SSOClientUser) session
					.getAttribute(SSOClientConstants.USER_SESSION_KEY);

			String strPageNo = (null == request
					.getParameter(BaaSOPConstants.PAGENO)) ? "1" : request
					.getParameter(BaaSOPConstants.PAGENO);
			String strPageSize = (null == request
					.getParameter(BaaSOPConstants.PAGESIZE)) ? "10" : request
					.getParameter(BaaSOPConstants.PAGESIZE);
			req.setPageNO(Integer.parseInt(strPageNo));
			req.setPageSize(Integer.parseInt(strPageSize));

			req.setTenantId(user.getTenantId());
			req.setProductId(param.getProductId());
			req.setProductName(param.getProductName());

			IProductDefineSV iProductDefineSV = DubboConsumerFactory
					.getService(IProductDefineSV.class);

			SubProResponse response=iProductDefineSV.querySubProducts(req);
			PageInfo<CategoryInfo> pageInfo = response.getPageInfo();

			responseData = new ResponseData<PageInfo<CategoryInfo>>(
					ResponseData.AJAX_STATUS_SUCCESS, "获取信息成功", pageInfo);
		} catch (Exception e) {
			responseData = new ResponseData<PageInfo<CategoryInfo>>(
					ResponseData.AJAX_STATUS_FAILURE, "获取信息异常");
			LOG.info("获取信息出错：", e);
		}

		return responseData;
	}
	@RequestMapping("/getPageList")
	@ResponseBody
	public ResponseData<PageInfo<CouponInfoExt>> getCoupons(CouponParams param,
			HttpServletRequest request) {
		 
		ResponseData<PageInfo<CouponInfoExt>> responseData = null;
		try {
			CouponReq req = new CouponReq();
			HttpSession session = request.getSession();
			SSOClientUser user = (SSOClientUser) session
					.getAttribute(SSOClientConstants.USER_SESSION_KEY);

			String strPageNo = (null == request
					.getParameter(BaaSOPConstants.PAGENO)) ? "1" : request
					.getParameter(BaaSOPConstants.PAGENO);
			String strPageSize = (null == request
					.getParameter(BaaSOPConstants.PAGESIZE)) ? "10" : request
					.getParameter(BaaSOPConstants.PAGESIZE);
			req.setPageNO(Integer.parseInt(strPageNo));
			req.setPageSize(Integer.parseInt(strPageSize));
			if (!StringUtil.isBlank(param.getStartTime())) {
				req.setStartTime(Timestamp.valueOf(param.getStartTime()
						+ " 00:00:00"));
			}
			if (!StringUtil.isBlank(param.getEndTime())) {
				req.setEndTime(Timestamp.valueOf(param.getEndTime()
						+ " 23:59:59"));
			}

			req.setTenantId(user.getTenantId());
			req.setCouponName(param.getCouponName());
			req.setCouponId(param.getCouponId());
			req.setStatus(param.getStatus());
			ICouponSV icouponSV = DubboConsumerFactory
					.getService(ICouponSV.class);

			CouponListResponse response = icouponSV.getCouponList(req);

			PageInfo<CouponInfo> pageInfo = response.getPageInfo();

			List<CouponInfo> list=pageInfo.getResult();
			List<CouponInfoExt> extlist=new ArrayList<CouponInfoExt>();
			CouponInfoExt ext=null;
			for(CouponInfo info:list){
				ext=new CouponInfoExt();
				BeanUtils.copyProperties(ext, info);
				ext.setCouponStaName(getStaName(info.getStatus()));
				extlist.add(ext);
			}
			PageInfo<CouponInfoExt> pageInfoExt=new  PageInfo<CouponInfoExt>();
			pageInfoExt.setResult(extlist);
			pageInfoExt.setPageCount(pageInfo.getPageCount());
			pageInfoExt.setCount(pageInfo.getCount());
			pageInfoExt.setPageNo(pageInfo.getPageNo());
			pageInfoExt.setPageSize(pageInfo.getPageSize());
			responseData = new ResponseData<PageInfo<CouponInfoExt>>(
					ResponseData.AJAX_STATUS_SUCCESS, "获取信息成功", pageInfoExt);
		} catch (Exception e) {
			responseData = new ResponseData<PageInfo<CouponInfoExt>>(
					ResponseData.AJAX_STATUS_FAILURE, "获取信息异常");
			LOG.info("获取信息出错：", e);
		}

		return responseData;
	}

	@RequestMapping("/downLoadCoupon")
	public void downLoadCoupon( HttpServletRequest request, HttpServletResponse response) {

		try {
			HttpSession session = request.getSession();
			SSOClientUser user = (SSOClientUser) session
					.getAttribute(SSOClientConstants.USER_SESSION_KEY);

			WritableWorkbook workbook = null;
			ServletOutputStream os = null;

			try {
				os = response.getOutputStream();
				response.reset();// 清空输出流
				response.setContentType("application/msexcel");// 定义输出类型

	            //ServletOutputStream outputStream = response.getOutputStream();
	            response.reset();// 清空输出流
	            response.setContentType("application/msexcel");// 定义输出类型
	            response.setHeader("Content-disposition", "attachment; filename=CouponList"
	                    +DateUtil.getDateString(new Date(), DateUtil.YYYYMMDDHHMMSS)+".xls");// 设定输出文件头
				//workbook = Workbook.createWorkbook(os);
				
			//	int sheetNo = workbook.getNumberOfSheets() + 1;
				/*WritableSheet sheet = workbook.createSheet("优惠券信息", sheetNo);
				sheet.getSettings().setShowGridLines(true);*/

				String[] couponInfoArray = new String[] { "优惠券Id", "优惠券名称",
						"优惠券类型","适用产品", "优惠券编码", "优惠方式","最高减免金额","使用条件", "状态", "有效期" };//将面值改为优惠方式和最高减免金额
				String[] couponInfoValue = new String[] { "couponId",
						"couponName", "couponTypeName","productName", "couponCode", "conditionValue","topMoney","condition",
						"couponStaName", "effectTime" };
				ICouponSV icouponSV = DubboConsumerFactory
						.getService(ICouponSV.class);
				ExPortReq req=new ExPortReq();
				req.setTenantId(user.getTenantId());
				req.setCouponId(request.getParameter("couponId"));
				ExportCouponResponse res=icouponSV.getCoupon(req);
				CouponExport export=res.getCoupon();
				List<CouponList> couponList=new ArrayList<CouponList>();
				List<CouponCodeList> list=export.getCodeList();
				
				String couponName=export.getCouponName();
				String couponId=export.getCouponId();
				String couponAmount=export.getCouponAmount();
				String couponType=export.getCouponType();
				//String couponStaName = getStaName(export);
				String conditionValue=export.getConditionValue();
				String topMoney=export.getTopMoney();
				String productName=export.getProductName();
				String conditon=null;
				if(conditionValue.contains(",")){
					conditon=conditionValue.substring(0,conditionValue.indexOf(","))+"可用";
				}else{
					conditon="无门槛";
				}
				String couponTypeName=null;
				if(BaaSOPConstants.Coupon.ALL.equals(couponType)){
					couponTypeName=BaaSOPConstants.Coupon.ALL_USE;
					productName="所有产品";
				}else if(BaaSOPConstants.Coupon.APPOINT.equals(couponType)){
					couponTypeName=BaaSOPConstants.Coupon.APPOINT_TYPE;
				}
				//String couponStatusString=export.getCouponConditon();
				String couponValue=export.getCouponValue();
				String effectiveTime=DateUtil.getDateString(export.getActiveTime(), DateUtil.DATETIME_FORMAT)+" 至 "+DateUtil.getDateString(export.getInactiveTime(),DateUtil.DATETIME_FORMAT);
				CouponList cl=null;
				for(CouponCodeList ccl:list){
					cl=new CouponList();
					cl.setCouponCode(ccl.getCouponCode());
					//cl.setCouponAmount(list.get(i));  //用不到优惠券的数量
					//cl.setCouponConditon(couponConditon);  //需要对json串进行转义处理
					cl.setCouponName(couponName);
					cl.setCouponId(couponId);
					cl.setCouponTypeName(couponTypeName);//需要对couponType进行转义
					cl.setCouponStaName(getStaName(ccl.getSingleStatus()));  //也需要加进行处理
					cl.setCouponValue(couponValue);
					cl.setEffectTime(effectiveTime);//也需要进行拼接
					cl.setCondition(conditon);
					if(!StringUtil.isBlank(topMoney)){
						cl.setTopMoney(topMoney+"元");
					}else{
						cl.setTopMoney("无");
					}
					cl.setProductName(productName);
					
					cl.setConditionValue(conditionValue);
					couponList.add(cl);
					
					
				}
				AbstractExcelHelper eh1 = JxlExcelHelper.getInstance();
				
				eh1.writeExcel(os, "优惠券信息", CouponList.class, couponList,couponInfoValue,couponInfoArray);
				
				

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

	private String getStaName(String status) {
		//String status=export.getStatus();
		String couponStaName=null;
		switch(status){
		case "CREATE":
			couponStaName=BaaSOPConstants.Coupon.NEW;
			break;
		case "EFFECTIVE":
			couponStaName=BaaSOPConstants.Coupon.EFFECTIVE;
			break;
		case "USED":
			couponStaName=BaaSOPConstants.Coupon.USED;
			break;
		case "GOT":
			couponStaName=BaaSOPConstants.Coupon.GOT;
			break;
		case "INVALID":
			couponStaName=BaaSOPConstants.Coupon.INVALID;
		case "REFUSED":
			couponStaName=BaaSOPConstants.Coupon.REFUSE_NAME;
		default:
				break;
		}
		return couponStaName;
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
	
	
	@RequestMapping("/getMainProList")
	@ResponseBody
	public ResponseData<PageInfo<MainProductInfo>> getProducts(mainProReq param,
			HttpServletRequest request) {

		ResponseData<PageInfo<MainProductInfo>> responseData = null;
		try {
			
			HttpSession session = request.getSession();
			SSOClientUser user = (SSOClientUser) session
					.getAttribute(SSOClientConstants.USER_SESSION_KEY);

			String strPageNo = (null == request
					.getParameter(BaaSOPConstants.PAGENO)) ? "1" : request
					.getParameter(BaaSOPConstants.PAGENO);
			String strPageSize = (null == request
					.getParameter(BaaSOPConstants.PAGESIZE)) ? "10" : request
					.getParameter(BaaSOPConstants.PAGESIZE);
			/*req.setPageNO(Integer.parseInt(strPageNo));
			req.setPageSize(Integer.parseInt(strPageSize));

			req.setTenantId(user.getTenantId());
			req.setProductId(param.getProductId());
			req.setProductName(param.getProductName());*/
			param.setTenantId(user.getTenantId());
			param.setPageNO(Integer.parseInt(strPageNo));
			param.setPageSize(Integer.parseInt(strPageSize));
			IProductDefineSV iProductDefineSV = DubboConsumerFactory
					.getService(IProductDefineSV.class);

			mainProResponse response=iProductDefineSV.queryMainProduct(param);
			PageInfo<MainProductInfo> pageInfo = response.getPageInfo();

			responseData = new ResponseData<PageInfo<MainProductInfo>>(
					ResponseData.AJAX_STATUS_SUCCESS, "获取信息成功", pageInfo);
		} catch (Exception e) {
			responseData = new ResponseData<PageInfo<MainProductInfo>>(
					ResponseData.AJAX_STATUS_FAILURE, "获取信息异常");
			LOG.info("获取信息出错：", e);
		}

		return responseData;
	}
	

	
	
}
