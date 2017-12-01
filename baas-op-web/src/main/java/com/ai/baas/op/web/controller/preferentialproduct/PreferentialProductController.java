package com.ai.baas.op.web.controller.preferentialproduct;

import com.ai.baas.bmc.api.baseInfo.interfaces.IBaseInfoSV;
import com.ai.baas.bmc.api.baseInfo.params.BaseCodeInfo;
import com.ai.baas.bmc.api.baseInfo.params.QueryInfoParams;
import com.ai.baas.bmc.api.marktableproduct.interfaces.IQueryProductSV;
import com.ai.baas.bmc.api.marktableproduct.params.ProductInfo;
import com.ai.baas.bmc.api.marktableproduct.params.ProductQueryByIdListVO;
import com.ai.baas.bmc.api.proferentialprocuct.interfaces.IProferProductManageSV;
import com.ai.baas.bmc.api.proferentialprocuct.interfaces.IQueryProferProductSV;
import com.ai.baas.bmc.api.proferentialprocuct.params.*;
import com.ai.baas.op.web.constants.BaaSOPConstants;
import com.ai.baas.op.web.model.*;
import com.ai.baas.op.web.util.DateUtil;
import com.ai.baas.op.web.util.TradeSeqUtil;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.RandomUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.opt.sdk.web.model.ResponseData;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import com.ai.opt.sso.client.filter.SSOClientUser;
import com.ai.opt.sys.api.gnsubject.interfaces.IGnSubjectQuerySV;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectDetailVo;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectListResponse;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.apache.poi.util.SystemOutLogger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 优惠产品
 */
@Controller
@RequestMapping("/preferentialProduct")
public class PreferentialProductController {

	private static final Logger LOG = Logger
			.getLogger(PreferentialProductController.class);

	@RequestMapping("/list")
	public ModelAndView discountProduct(HttpServletRequest request) {
		 SSOClientUser user = (SSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
		    String phone = user.getPhone();
		    request.setAttribute("phoneNum", phone);
		ModelAndView view = new ModelAndView(
				"jsp/preferentialproduct/discountProductList");
		return view;
	}

	@RequestMapping("/toAdd")
	public ModelAndView addDiscProduct(HttpServletRequest request) {
		ModelAndView view = new ModelAndView(
				"jsp/preferentialproduct/addDiscountProduct");
		return view;
	}
	@RequestMapping("/toProductDetail")
	public String toProductDetail(HttpServletRequest request,@RequestParam("productId") String productId,@RequestParam("productType") String productType,@RequestParam("priceCode") String priceCode,Model model) {
		
		model.addAttribute("id",productId);
		model.addAttribute("detailType", productType);
		model.addAttribute("priceCode", priceCode);
		return "jsp/preferentialproduct/productView";
	}
	@RequestMapping("/toProductEdit")
	public String toProductEdit(HttpServletRequest request,@RequestParam("productId") String productId,@RequestParam("productType") String productType,@RequestParam("priceCode") String priceCode,Model model) {
		
		model.addAttribute("id",productId);
		model.addAttribute("detailType", productType);
		model.addAttribute("priceCode", priceCode);
		return "jsp/preferentialproduct/editProduct";
	}
	@RequestMapping("/toRelate")
	public String toRelate(HttpServletRequest request,@RequestParam("productId") String productId,@RequestParam("productName") String productName,@RequestParam("productType") String productType,Model model) throws UnsupportedEncodingException {
		
		model.addAttribute("id",productId);
		model.addAttribute("productName",URLDecoder.decode(productName, "UTF-8"));
		
		
		if("dr_offer".equals(productType)){
			model.addAttribute("productType", "满赠");
		}else{
			model.addAttribute("productType", "满减");
		}
		model.addAttribute("chargeType",productType);
		return "jsp/preferentialproduct/relateCharge";
	}
	@RequestMapping("/getRelated")
	@ResponseBody
	public ResponseData<RelatedResponse> getRelated(HttpServletRequest request,@RequestParam("productId") String productId,Model model) {
		ResponseData<RelatedResponse> respondata=null;
		try{
			IQueryProferProductSV iQueryProferProductSV= DubboConsumerFactory.getService(IQueryProferProductSV.class);
			 HttpSession session = request.getSession();
				SSOClientUser user = (SSOClientUser) session
						.getAttribute(SSOClientConstants.USER_SESSION_KEY);
			RelatedVO vo=new RelatedVO();
			vo.setProductId(Long.valueOf(productId));
			vo.setTenantId(user.getTenantId());
			vo.setTradeSeq(TradeSeqUtil.newTradeSeq(user.getTenantId()));
			RelatedResponse response=iQueryProferProductSV.getRelatedAccount(vo);
			respondata=new ResponseData<RelatedResponse>(ResponseData.AJAX_STATUS_SUCCESS,"获取数据成功",response);
		
		}catch(Exception e){
			respondata=new ResponseData<RelatedResponse>(ResponseData.AJAX_STATUS_SUCCESS,"获取数据成功",null);
			LOG.info("获取信息出错", e);
		}
		
		return respondata;
	}
	
	
	@RequestMapping("/saveRelated")
	@ResponseBody
	public ResponseData<BaseResponse> saveRelate(HttpServletRequest request,@RequestParam("productId") String productId,@RequestParam("productType") String productType,@RequestParam("accountId") String accountId,Model model) {
		//BaseResponse relatedAccout(RelatedAccountVO vo)
		ResponseData<BaseResponse> respondata=null;
		try{
			 HttpSession session = request.getSession();
				SSOClientUser user = (SSOClientUser) session
						.getAttribute(SSOClientConstants.USER_SESSION_KEY);
				RelatedAccountVO vo=new RelatedAccountVO();
				vo.setTenantId(user.getTenantId());
				vo.setAccountType(BaaSOPConstants.SubjectCode.DR_SUBJECT);
				vo.setChargeType(productType);
				vo.setProductId(Long.valueOf(productId));
				List<Long> list=new ArrayList<Long>();
				list.add(Long.valueOf(accountId));
				vo.setRelAccounts(list);
			
				//vo.setFullIds(fullIds);
				vo.setTradeSeq(TradeSeqUtil.newTradeSeq(user.getTenantId()));
				IProferProductManageSV iProferProductManageSV = DubboConsumerFactory
						.getService(IProferProductManageSV.class);
				BaseResponse response=iProferProductManageSV.relatedAccout(vo);
				respondata=new ResponseData<BaseResponse>(ResponseData.AJAX_STATUS_SUCCESS,"关联数据成功",response);
		}catch(Exception e){
			respondata=new ResponseData<BaseResponse>(ResponseData.AJAX_STATUS_FAILURE,"关联数据失败",null);
			LOG.info("关联数据失败", e);
		}
		
		return respondata;
	}
	@RequestMapping("/getProductDetail")
	@ResponseBody
	public ResponseData<SingleProduct> getProductDetail(HttpServletRequest request,@RequestParam("productId") String productId,Model model) {
		 ResponseData<SingleProduct> responseData=null;
		 try{
			 HttpSession session = request.getSession();
				SSOClientUser user = (SSOClientUser) session
						.getAttribute(SSOClientConstants.USER_SESSION_KEY);
				IQueryProferProductSV iQueryProferProductSV = DubboConsumerFactory.getService(IQueryProferProductSV.class);
				ProductQueryParam param=new ProductQueryParam();
				param.setTenantId(user.getTenantId());
				param.setProductId(Long.valueOf(productId));
				param.setTradeSeq(TradeSeqUtil.newTradeSeq(user.getTenantId()));
				SingleProductInfo info=iQueryProferProductSV.getProductById(param);
				SingleProduct sinfo=new SingleProduct();
				
				//BeanUtils.copyProperties(chlAgent, chlAgentUpdateVo);
				BeanUtils.copyProperties(sinfo, info);
				
				IQueryProductSV queryProductSV = DubboConsumerFactory
						.getService(IQueryProductSV.class);
				if(!CollectionUtil.isEmpty(info.getProductList())){
					ProductQueryByIdListVO list=new ProductQueryByIdListVO();
					list.setProductIdList(info.getProductList());
					list.setTenantId(user.getTenantId());
					PageInfo<ProductInfo> pi=queryProductSV.getProductInfoByProductIdList(list);
					
					sinfo.setProList(pi.getResult());
				}
				List<FullVO> preList=new ArrayList<FullVO>();
				List<FullPresent> fullListx= info.getPresentList();
				//目前对赠品只进行业务赠送处理，后边随着业务的拓展，需要进行处理
				if(!CollectionUtil.isEmpty(fullListx)){
					for(FullPresent fp:fullListx){
						FullVO fv=new FullVO();
						
						BeanUtils.copyProperties(fv, fp);
						ProductQueryByIdListVO list=new ProductQueryByIdListVO();
						list.setProductIdList(JSON.parseArray(JSON.toJSONString(fp.getGiftProList()), String.class));
						//暂时写死
						list.setTenantId(user.getTenantId());
						PageInfo<ProductInfo> pl=queryProductSV.getProductInfoByProductIdList(list);
						fv.setGiftProList(pl.getResult());
						//sinfo.setPreList(pl.getResult());
						preList.add(fv);
					}
					sinfo.setPreList(preList);
				}
				
				
				responseData=new ResponseData<SingleProduct>(ResponseData.AJAX_STATUS_SUCCESS,"获取数据成功",sinfo);
				
				
		 
		 }catch(Exception e){
			 responseData=new ResponseData<SingleProduct>(ResponseData.AJAX_STATUS_FAILURE,"获取数据失败",null);
			 LOG.info("关联数据失败", e);
		 }
		
		return responseData;
	}

	@RequestMapping("/changeStatus")
	@ResponseBody
	public ResponseData<BaseResponse> changeStatus(ActiveProductVO vo,
			@RequestParam("productId") String productId,
			HttpServletRequest request) {

		ResponseData<BaseResponse> responseData = null;
		try {
			HttpSession session = request.getSession();
			SSOClientUser user = (SSOClientUser) session
					.getAttribute(SSOClientConstants.USER_SESSION_KEY);
			
			vo.setTradeSeq(TradeSeqUtil.newTradeSeq(user.getTenantId()));
			vo.setTenantId(user.getTenantId());
			vo.setProductId(Long.valueOf(productId));
			IProferProductManageSV iProferProductManageSV = DubboConsumerFactory
					.getService(IProferProductManageSV.class);

			BaseResponse response = iProferProductManageSV
					.updateProferProductStatus(vo);
			responseData = new ResponseData<BaseResponse>(
					ResponseData.AJAX_STATUS_SUCCESS, "更新成功", response);

		} catch (Exception e) {
			responseData = new ResponseData<BaseResponse>(
					ResponseData.AJAX_STATUS_FAILURE, "更新失败", null);
			LOG.info("更新失败", e);
		}

		return responseData;
	}

	@RequestMapping("/addProduct")
	@ResponseBody
	public ResponseData<BaseResponse> addProduct(ProductParam param,
			HttpServletRequest request) {
		ProferProductVO vo = new ProferProductVO();
		ResponseData<BaseResponse> responseData = null;
		try {
			HttpSession session = request.getSession();
			SSOClientUser user = (SSOClientUser) session
					.getAttribute(SSOClientConstants.USER_SESSION_KEY);
			
			 vo.setTenantId(user.getTenantId());
			vo.setTradeSeq(TradeSeqUtil.newTradeSeq(user.getTenantId()));
			//vo.setTenantId("BYD");
			if (!StringUtil.isBlank(param.getActiveDate())) {
				vo.setActiveDate(DateUtil.getTimestamp(param.getActiveDate()));
			}
			if (!StringUtil.isBlank(param.getInvalidDate())) {
				vo.setInvalidDate(DateUtil.getTimestamp(param.getInvalidDate()));
			}

			vo.setComments(param.getComments());
			//待定
			vo.setOperatorId(String.valueOf(user.getAccountId()));
			List<FullPresent> list = new ArrayList<FullPresent>();
			FullPresent fp = new FullPresent();
			fp.setGiftType(BaaSOPConstants.ProferName.SERVICE_OFFER);
			if(!CollectionUtil.isEmpty(param.getPresentList())){
				List<String> ll = new ArrayList<String>();
				for (String id : param.getPresentList()) {
					
					ll.add(id.replace("\"", ""));
				}
				fp.setGiftProList(ll);	
			}
			
			if (!StringUtil.isBlank(param.getPactiveTime())) {
				
				fp.setGiftActiveDate(DateUtil.getTimestamp(param
						.getPactiveTime()));
			}
			if (!StringUtil.isBlank(param.getPinavalidTime())) {
				fp.setGiftInvalidDate(DateUtil.getTimestamp(param
						.getPinavalidTime()));
			}
			if (!StringUtil.isBlank(param.getActiveCycle())) {
			
				fp.setActiveCycle(param.getActiveCycle());
			}
			if (!StringUtil.isBlank(param.getActiveFlag())) {
				fp.setActiveFlag(param.getActiveFlag());
			}

			list.add(fp);
			vo.setPresentList(list);
			if(!CollectionUtil.isEmpty(param.getProductList())){
				List<String> pList = new ArrayList<String>();
				for (String id : param.getProductList()) {
					pList.add(id.replace("\"", ""));
				}
				vo.setProductList(pList);
			}
			
			vo.setProductType(param.getProductType());
			vo.setProgramName(param.getProgramName());
			// vo.setReduceAmount);
			vo.setRuleAmount(Long.valueOf(param.getRuleAmount()));
			vo.setRuleUnit(param.getRuleUnit());
			IProferProductManageSV iProferProductManageSV = DubboConsumerFactory
					.getService(IProferProductManageSV.class);
			ProductResponse response = iProferProductManageSV
					.addProferProduct(vo);
			
			responseData = new ResponseData<BaseResponse>(
					ResponseData.AJAX_STATUS_SUCCESS, "添加成功", response);

		} catch (Exception e) {
			responseData = new ResponseData<BaseResponse>(
					ResponseData.AJAX_STATUS_FAILURE, "添加失败", null);
			LOG.info("添加失败", e);
		}

		return responseData;
	}
	//编辑优惠产品
	@RequestMapping("/editProduct")
	@ResponseBody
	public ResponseData<BaseResponse> editProduct(EditProductParam param,
			HttpServletRequest request) {
		ProferProductVO vo = new ProferProductVO();
		
		ResponseData<BaseResponse> responseData = null;
		try {
			HttpSession session = request.getSession();
			SSOClientUser user = (SSOClientUser) session
					.getAttribute(SSOClientConstants.USER_SESSION_KEY);
		
			// vo.setTenantId(user.getTenantId());
			vo.setTradeSeq(TradeSeqUtil.newTradeSeq(user.getTenantId()));
			vo.setTenantId(user.getTenantId());
			if (!StringUtil.isBlank(param.getActiveDate())) {
				vo.setActiveDate(DateUtil.getTimestamp(param.getActiveDate()));
			}
			if (!StringUtil.isBlank(param.getInvalidDate())) {
				vo.setInvalidDate(DateUtil.getTimestamp(param.getInvalidDate()));
			}
			vo.setProductId(Long.valueOf(param.getProductId()));
			vo.setComments(param.getComments());
			//待定
			vo.setOperatorId(String.valueOf(user.getAccountId()));
			List<FullPresent> list = new ArrayList<FullPresent>();
			FullPresent fp = new FullPresent();
			fp.setGiftType(BaaSOPConstants.ProferName.SERVICE_OFFER);
			
			if(!CollectionUtil.isEmpty(param.getPresentList())){
				List<String> ll = new ArrayList<String>();
				for (String id : param.getPresentList()) {
					
					ll.add(id.replace("\"", ""));
				}
				fp.setGiftProList(ll);
			}
			
			if (!StringUtil.isBlank(param.getPactiveTime())) {
				
				fp.setGiftActiveDate(DateUtil.getTimestamp(param
						.getPactiveTime()));
			}
			if (!StringUtil.isBlank(param.getPinavalidTime())) {
				fp.setGiftInvalidDate(DateUtil.getTimestamp(param
						.getPinavalidTime()));
			}
			if (!StringUtil.isBlank(param.getActiveCycle())) {
			
				fp.setActiveCycle(param.getActiveCycle());
			}
			if (!StringUtil.isBlank(param.getActiveFlag())) {
				fp.setActiveFlag(param.getActiveFlag());
			}

			list.add(fp);
			vo.setPresentList(list);
			
			if(!CollectionUtil.isEmpty(param.getProductList())){
				List<String> pList = new ArrayList<String>();
				for (String id : param.getProductList()) {
					pList.add(id.replace("\"", ""));
				}
				vo.setProductList(pList);
			}
			
			
			vo.setProductType(param.getProductType());
			vo.setProgramName(param.getProgramName());
			// vo.setReduceAmount);
			vo.setRuleAmount(Long.valueOf(param.getRuleAmount()));
			vo.setRuleUnit(param.getRuleUnit());
			if(param.getReduceAmount()!=null){
				vo.setReduceAmount(Long.valueOf(param.getReduceAmount()));	
			}
			
			vo.setPriceCode(param.getPriceCode());
			IProferProductManageSV iProferProductManageSV = DubboConsumerFactory
					.getService(IProferProductManageSV.class);
			BaseResponse response = iProferProductManageSV.updateProferProduct(vo);
			
			responseData = new ResponseData<BaseResponse>(
					ResponseData.AJAX_STATUS_SUCCESS, "更新成功", response);

		} catch (Exception e) {
			responseData = new ResponseData<BaseResponse>(
					ResponseData.AJAX_STATUS_FAILURE, "更新失败", null);
			LOG.info("更新失败", e);
		}

		return responseData;
	}
	
	
	@RequestMapping("/addMjProduct")
	@ResponseBody
	public ResponseData<BaseResponse> addMjProduct(MjProductParam param,
			HttpServletRequest request) {
		ProferProductVO vo = new ProferProductVO();
		ResponseData<BaseResponse> responseData = null;
		try {
			HttpSession session = request.getSession();
			SSOClientUser user = (SSOClientUser) session
					.getAttribute(SSOClientConstants.USER_SESSION_KEY);
			
			// vo.setTenantId(user.getTenantId());
			vo.setTradeSeq(TradeSeqUtil.newTradeSeq(user.getTenantId()));
			vo.setTenantId(user.getTenantId());
			if (!StringUtil.isBlank(param.getActiveDate())) {
				vo.setActiveDate(DateUtil.getTimestamp(param.getActiveDate()));
			}
			if (!StringUtil.isBlank(param.getInvalidDate())) {
				vo.setInvalidDate(DateUtil.getTimestamp(param.getInvalidDate()));
			}

			vo.setComments(param.getComments());
			//TODO 待定
			vo.setOperatorId(String.valueOf(user.getAccountId()));
			
			List<String> pList = new ArrayList<String>();
			for (String id : param.getProductList()) {
				pList.add(id.replace("\"", ""));
			}
			vo.setProductList(pList);
			vo.setProductType(param.getProductType());
			vo.setProgramName(param.getProgramName());
			// vo.setReduceAmount);
			vo.setRuleAmount(Double.valueOf(param.getRuleAmount()));
			vo.setRuleUnit(param.getRuleUnit());
			vo.setReduceAmount(Double.valueOf(param.getReduceAmount()));
			IProferProductManageSV iProferProductManageSV = DubboConsumerFactory
					.getService(IProferProductManageSV.class);
			ProductResponse response = iProferProductManageSV.addDiscontProduct(vo);
			
			responseData = new ResponseData<BaseResponse>(
					ResponseData.AJAX_STATUS_SUCCESS, "添加成功", response);

		} catch (Exception e) {
			responseData = new ResponseData<BaseResponse>(
					ResponseData.AJAX_STATUS_FAILURE, "添加失败", null);
			LOG.info("更新失败", e);
		}

		return responseData;
	}

	@RequestMapping("/getSelect")
	@ResponseBody
	public ResponseData<BaseCodeInfo> getSelect(QueryInfoParams param,
			HttpServletRequest request) {
		ResponseData<BaseCodeInfo> responseData = null;

		try {
			// BaseCodeInfo getBaseInfo(QueryInfoParams param)
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
	@RequestMapping("/getChargeSelect")
	@ResponseBody
	public ResponseData<List<GnSubjectListResponse>> getChargeSelect(HttpServletRequest request) {
		ResponseData<List<GnSubjectListResponse>> responseData = null;

		try {
			GnSubjectDetailVo  param=new GnSubjectDetailVo();
			// BaseCodeInfo getBaseInfo(QueryInfoParams param)
			HttpSession session = request.getSession();
			SSOClientUser user = (SSOClientUser) session
					.getAttribute(SSOClientConstants.USER_SESSION_KEY);
			
			
			param.setTenantId(user.getTenantId());
			param.setSubjectType(BaaSOPConstants.SubjectCode.DR_SUBJECT);
			IGnSubjectQuerySV iGnSubjectQuerySV=DubboConsumerFactory.getService(IGnSubjectQuerySV.class);
			List<GnSubjectListResponse> list=iGnSubjectQuerySV.getGnSubjectListMayRelated(param);
			//getGnSubjectByTenantIdSubjectId getGnSubjectByTenantIdSubjectId(GnSubjectTenantIdSubjectIdRequest vo) 获取单个科目
			responseData = new ResponseData<List<GnSubjectListResponse>>(
					ResponseData.AJAX_STATUS_SUCCESS, "获取数据成功", list);
			
		} catch (Exception e) {
			responseData = new ResponseData<List<GnSubjectListResponse>>(
					ResponseData.AJAX_STATUS_FAILURE, "获取数据失败", null);
			LOG.info("获取数据失败", e);
		}

		return responseData;
	}

	@RequestMapping("/delProduct")
	@ResponseBody
	public ResponseData<BaseResponse> delProduct(productDelVO vo,
			@RequestParam("productId") String productId,
			HttpServletRequest request) {
		// delProferProduct(productDelVO vo)
		ResponseData<BaseResponse> responseData = null;
		try {
			HttpSession session = request.getSession();
			SSOClientUser user = (SSOClientUser) session
					.getAttribute(SSOClientConstants.USER_SESSION_KEY);
			
			vo.setTradeSeq(TradeSeqUtil.newTradeSeq(user.getTenantId()));
			vo.setTenantId(user.getTenantId());
			vo.setProductId(Long.valueOf(productId));
			IProferProductManageSV iProferProductManageSV = DubboConsumerFactory
					.getService(IProferProductManageSV.class);

			BaseResponse response = iProferProductManageSV.delProferProduct(vo);
			responseData = new ResponseData<BaseResponse>(
					ResponseData.AJAX_STATUS_SUCCESS, "删除成功", response);

		} catch (Exception e) {
			responseData = new ResponseData<BaseResponse>(
					ResponseData.AJAX_STATUS_FAILURE, "删除失败", null);
			LOG.info("删除失败", e);
		}

		return responseData;
	}

	@RequestMapping("/getPageList")
	@ResponseBody
	public ResponseData<PageInfo<ProferProductInfo>> getProducts(
			QueryProductRequest param, HttpServletRequest request) {
		// QueryProductRequest
		ProductQueryVO vo = new ProductQueryVO();
		ResponseData<PageInfo<ProferProductInfo>> responseData = null;
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
			vo.setPageNo(Integer.parseInt(strPageNo));
			vo.setPageSize(Integer.parseInt(strPageSize));
			// DateUtil
			if (!StringUtil.isBlank(param.getActiveDate())) {
				vo.setActiveDate(DateUtil.getTimestamp(param.getActiveDate()));

			}
			if (!StringUtil.isBlank(param.getInvalidDate())) {
				vo.setInvalidDate(DateUtil.getTimestamp(param.getInvalidDate()));

			}
			if (!StringUtil.isBlank(param.getProductId())) {
				vo.setProductId(Long.parseLong(param.getProductId()));
			}
			if (!StringUtil.isBlank(param.getProferType())) {
				vo.setProferType(param.getProferType());
			}

			// ProductQueryVO req=new ProductQueryVO();
			vo.setProductName(param.getProductName());
			vo.setTenantId(user.getTenantId());
			vo.setTradeSeq(user.getTenantId()
					+ DateUtil.getDateString(DateUtil.yyyyMMddHHmmssSSS)
					+ RandomUtil.randomString(9));
			
			IQueryProferProductSV iQueryProferProductSV = DubboConsumerFactory
					.getService(IQueryProferProductSV.class);
			ProferProductResponse response = iQueryProferProductSV
					.getProductInfo(vo);

			// PageInfo<ProferProductInfo>
			PageInfo<ProferProductInfo> pageInfo = response.getPageInfo();
			
			responseData = new ResponseData<PageInfo<ProferProductInfo>>(
					ResponseData.AJAX_STATUS_SUCCESS, "获取信息成功", pageInfo);
		} catch (Exception e) {
			responseData = new ResponseData<PageInfo<ProferProductInfo>>(
					ResponseData.AJAX_STATUS_FAILURE, "获取信息异常");
			LOG.info("获取信息出错：", e);
		}

		return responseData;
	}

	// 查询可销售产品
	@RequestMapping("/getProductList")
	@ResponseBody
	public ResponseData<PageInfo<ProductInfo>> getProductList(
			ProductRequest pr, HttpServletRequest request) {
		ResponseData<PageInfo<ProductInfo>> responseData;
		try {
			HttpSession session = request.getSession();
			SSOClientUser user = (SSOClientUser) session
					.getAttribute(SSOClientConstants.USER_SESSION_KEY);
			com.ai.baas.bmc.api.marktableproduct.params.ProductQueryVO productQueryVO = new com.ai.baas.bmc.api.marktableproduct.params.ProductQueryVO();
			IQueryProductSV queryProductSV = DubboConsumerFactory
					.getService(IQueryProductSV.class);
			productQueryVO.setTradeSeq(user.getTenantId());
			String strPageNo = (null == request
					.getParameter(BaaSOPConstants.PAGENO)) ? "1" : request
					.getParameter(BaaSOPConstants.PAGENO);
			String strPageSize = (null == request
					.getParameter(BaaSOPConstants.PAGESIZE)) ? "10" : request
					.getParameter(BaaSOPConstants.PAGESIZE);
			productQueryVO.setProductId(pr.getProductId());
			productQueryVO.setProductName(pr.getProductName());
			productQueryVO.setPageNo(Integer.parseInt(strPageNo));
			productQueryVO.setPageSize(Integer.parseInt(strPageSize));
			productQueryVO.setTenantId(user.getTenantId());
			productQueryVO.setBillingType("STANDARD_GROUP_TYPE");
			//productQueryVO.setActiveStatus("ACTIVE");
			productQueryVO.setServiceType(pr.getServiceType());
			PageInfo<ProductInfo> info = queryProductSV
					.getActiveProductInfo(productQueryVO);
			
			responseData = new ResponseData<PageInfo<ProductInfo>>(
					ResponseData.AJAX_STATUS_SUCCESS, "可销售产品列表查询成功", info);
			
			
		} catch (Exception e) {
			LOG.info("查询可销售产品列表异常", e);
			responseData = new ResponseData<PageInfo<ProductInfo>>(
					ResponseData.AJAX_STATUS_FAILURE, "可销售产品列表查询失败");
		}
		return responseData;

	}
}
