package com.ai.citic.billing.web.controller.product;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;







import com.ai.baas.prd.api.product.interfaces.IProductConfigSV;
import com.ai.baas.prd.api.product.interfaces.IProductDefineSV;
import com.ai.baas.prd.api.product.params.BaseCodeInfo;
import com.ai.baas.prd.api.product.params.BaseRequest;
import com.ai.baas.prd.api.product.params.ChildeCodeResponse;
import com.ai.baas.prd.api.product.params.CustomProductResponse;
import com.ai.baas.prd.api.product.params.DelProductReq;
import com.ai.baas.prd.api.product.params.MainProduct;
import com.ai.baas.prd.api.product.params.PmcategoryInfoResponse;
import com.ai.baas.prd.api.product.params.ProductRequest;
import com.ai.baas.prd.api.product.params.ProductResponse;
import com.ai.baas.prd.api.product.params.ProductUpdateReq;
import com.ai.baas.prd.api.product.params.QueryChildCodeRequest;
import com.ai.baas.prd.api.product.params.QueryInfoParams;
import com.ai.baas.prd.api.product.params.QueryPmCategoryInfoReq;
import com.ai.citic.billing.web.constants.CiticWebConstants;
import com.ai.citic.billing.web.model.sso.CiticSSOClientUser;
import com.ai.citic.billing.web.util.TradeSeqUtil;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.web.model.ResponseData;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import com.ai.paas.ipaas.util.StringUtil;
import com.alibaba.fastjson.JSON;

/**
 * 定义产品目录控
 *
 * Date: 2016年11月4日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author gaogang
 */
@Controller
@RequestMapping("/product")
public class ProductController {

	@RequestMapping("defineProList")
	public String toDefineProList(){
		
		return "jsp/product/defineProductList";
	}
	@RequestMapping("defineCustomProList")
	public String defineCustomProList(HttpServletRequest request,Model model){
		String mainProductId = request.getParameter("mainProductId");
		String mainProductName = request.getParameter("mainProductName");
		String supplierId = request.getParameter("supplierId");
		String supplierName = request.getParameter("supplierName");
		model.addAttribute("mainProductId",mainProductId);
		model.addAttribute("mainProductName",mainProductName);
		model.addAttribute("supplierId",supplierId);
		model.addAttribute("supplierName",supplierName);
		return "jsp/product/defineCustomProList";
	}
	@RequestMapping("toEditCustomPage")
	public String toEditCustomPage(String mainProId,String mainTag,String supplierId,String supplierName,Model model){
		model.addAttribute("proId",mainProId);
		model.addAttribute("mainTag",mainTag);
		model.addAttribute("supplierId",supplierId);
		model.addAttribute("supplierName",supplierName);
		return "jsp/product/editCustomProduct";
	}
	@RequestMapping("productList")
	public String toProList(){
		
		return "jsp/product/productList";
	}
	@RequestMapping("toEditPage")
	public String toEditPage(String mainProId,String mainTag,Model model){
		model.addAttribute("proId",mainProId);
		model.addAttribute("mainTag",mainTag);
		return "jsp/product/editProduct";
	}
	@RequestMapping("toViewPage")
	public String toViewPage(String mainProId,String mainTag,Model model){
		model.addAttribute("proId",mainProId);
		model.addAttribute("mainTag",mainTag);
		return "jsp/product/viewProduct";
	}
	@RequestMapping("/addProduct")
	@ResponseBody
	public ResponseData<BaseResponse> addProduct(HttpServletRequest request,String product){
		ResponseData<BaseResponse> responseData;
		try{
			IProductDefineSV productDefinSV=DubboConsumerFactory.getService(IProductDefineSV.class);
			CiticSSOClientUser user = (CiticSSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
			ProductRequest req=JSON.parseObject(product, ProductRequest.class);
			req.setTenantId(user.getTenantId());
			CustomProductResponse  response=productDefinSV.addCustomProduct(req);
			responseData=new ResponseData<BaseResponse>(ResponseData.AJAX_STATUS_SUCCESS,"添加成功",response);
		}catch(Exception e){
			responseData=new ResponseData<BaseResponse>(ResponseData.AJAX_STATUS_FAILURE,"添加失败");
		}
		
		return responseData;
	}
	@RequestMapping("/updateProduct")
	@ResponseBody
	public ResponseData<BaseResponse> updateProduct(HttpServletRequest request,String product){
		ResponseData<BaseResponse> responseData;
		try{
			
			//BaseResponse updatePmCategoryInfo(ProductUpdateReq req)
			IProductDefineSV productDefinSV=DubboConsumerFactory.getService(IProductDefineSV.class);
			 CiticSSOClientUser user = (CiticSSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
			 ProductUpdateReq req=JSON.parseObject(product, ProductUpdateReq.class);
			req.setTenantId(user.getTenantId());
			BaseResponse  response=productDefinSV.updatePmCategoryInfo(req);
			responseData=new ResponseData<BaseResponse>(ResponseData.AJAX_STATUS_SUCCESS,"更新成功",response);
		}catch(Exception e){
			responseData=new ResponseData<BaseResponse>(ResponseData.AJAX_STATUS_FAILURE,"更新失败");
		}
		
		return responseData;
	}
	
	@RequestMapping("/getTradeType")
	@ResponseBody
	public ResponseData<BaseCodeInfo> getTradeType(HttpServletRequest request){
		 ResponseData<BaseCodeInfo> responseData;
		 try{
			 CiticSSOClientUser user = (CiticSSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
				QueryInfoParams qip=new QueryInfoParams();
			    qip.setParamType("TRADE_TYPE");
			    qip.setTenantId(user.getTenantId());
			    qip.setTradeSeq(TradeSeqUtil.newTradeSeq(user.getTenantId()));
			  //  IBaseInfoSV ibaseInfo= DubboConsumerFactory.getService(IBaseInfoSV.class);
			    IProductConfigSV ipc=DubboConsumerFactory.getService(IProductConfigSV.class);
			    BaseCodeInfo bci= ipc.getBaseInfo(qip);
			    responseData=new ResponseData<BaseCodeInfo>(ResponseData.AJAX_STATUS_SUCCESS, "获取数据成功", bci);
		 }catch(Exception e){
			 responseData=new ResponseData<BaseCodeInfo>(ResponseData.AJAX_STATUS_FAILURE, "获取数据失败");
		 }
		 return responseData;
	   
	}
	@RequestMapping("/getMainPro")
	@ResponseBody
	public ResponseData<BaseCodeInfo> getMainPro(HttpServletRequest request){
		 ResponseData<BaseCodeInfo> responseData;
		 try{
			 CiticSSOClientUser user = (CiticSSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
				QueryInfoParams qip=new QueryInfoParams();
			    qip.setParamType("TRADE_TYPE");
			    qip.setTenantId(user.getTenantId());
			    qip.setTradeSeq(TradeSeqUtil.newTradeSeq(user.getTenantId()));
			    IProductConfigSV ipc=DubboConsumerFactory.getService(IProductConfigSV.class);

			    BaseCodeInfo bci= ipc.getBaseInfo(qip);
			    responseData=new ResponseData<BaseCodeInfo>(ResponseData.AJAX_STATUS_SUCCESS, "获取数据成功", bci);
		 }catch(Exception e){
			 responseData=new ResponseData<BaseCodeInfo>(ResponseData.AJAX_STATUS_FAILURE, "获取数据失败");
		 }
		 return responseData;
	   
	}
	
	
	@RequestMapping("/getMainProduct")
	@ResponseBody
	public ResponseData<ChildeCodeResponse> getProductByTrade(HttpServletRequest request, @RequestParam("parentId")String parentId){
		 ResponseData<ChildeCodeResponse> responseData;
		 try{
			 CiticSSOClientUser user = (CiticSSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
			 QueryChildCodeRequest qcr=new QueryChildCodeRequest();
			 qcr.setParentCode(parentId);
			 qcr.setTenantId(user.getTenantId());
			 qcr.setTradeSeq(TradeSeqUtil.newTradeSeq(user.getTenantId()));
			 //IBaseInfoSV ibaseInfo= DubboConsumerFactory.getService(IBaseInfoSV.class);
			    IProductConfigSV ipc=DubboConsumerFactory.getService(IProductConfigSV.class);

			 ChildeCodeResponse res=  ipc.getChildCode(qcr);
			
			 responseData=new ResponseData<ChildeCodeResponse>(ResponseData.AJAX_STATUS_SUCCESS, "获取数据成功", res);
		 }catch(Exception e){
			 responseData=new ResponseData<ChildeCodeResponse>(ResponseData.AJAX_STATUS_FAILURE, "获取数据失败");
		 }
		 return responseData;
	}
	
	
	@RequestMapping("/delProduct")
	@ResponseBody
	public ResponseData<BaseResponse> delProduct(HttpServletRequest request,String mainProId,String mainTag){
		 ResponseData<BaseResponse> responseData;
		 try{
			 //BaseResponse delProduct(DelProductReq req)
			 CiticSSOClientUser user = (CiticSSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
			 DelProductReq delReq=new DelProductReq();
			 delReq.setMainProCode(mainProId);
			 delReq.setMainTag(mainTag);
			 delReq.setTenantId(user.getTenantId());
			// delReq.setTradeSeq(TradeSeqUtil.newTradeSeq(user.getTenantId()));
			 IProductDefineSV productDefinSV=DubboConsumerFactory.getService(IProductDefineSV.class);
			 BaseResponse res=  productDefinSV.delProduct(delReq);
			
			 responseData=new ResponseData<BaseResponse>(ResponseData.AJAX_STATUS_SUCCESS, "删除成功", res);
		 }catch(Exception e){
			 responseData=new ResponseData<BaseResponse>(ResponseData.AJAX_STATUS_FAILURE, "删除失败");
		 }
		 return responseData;
	}
	@RequestMapping("/getProduct")
	@ResponseBody
	public ResponseData<ProductResponse> getProduct(HttpServletRequest request){
		//ProductResponse getProduct(BaseRequest req)
		 ResponseData<ProductResponse> responseData;
		 try{
			
			 CiticSSOClientUser user = (CiticSSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
			 String mainProId=request.getParameter("mainProCode");
			 String mainTag=request.getParameter("mainTag");
			 BaseRequest br=new BaseRequest();
			 br.setMainProductCode(mainProId);
			 br.setTenantId(user.getTenantId());
			 br.setMainTag(mainTag);
			 IProductDefineSV productDefinSV=DubboConsumerFactory.getService(IProductDefineSV.class);
			 ProductResponse res=  productDefinSV.getProduct(br);
			
			 responseData=new ResponseData<ProductResponse>(ResponseData.AJAX_STATUS_SUCCESS, "获取数据成功",res);
		 }catch(Exception e){
			 responseData=new ResponseData<ProductResponse>(ResponseData.AJAX_STATUS_FAILURE, "获取数据失败");
		 }
		 return responseData;
	}

	@RequestMapping("/getProducts")
	@ResponseBody
	public ResponseData<PageInfo<MainProduct>> getProducts(HttpServletRequest request, String mainProId,String mainProName){
		 ResponseData<PageInfo<MainProduct>> responseData;
		 try{
			   CiticSSOClientUser user = (CiticSSOClientUser) request.getSession().getAttribute(SSOClientConstants.USER_SESSION_KEY);
			   IProductDefineSV productDefinSV=DubboConsumerFactory.getService(IProductDefineSV.class);
			 	QueryPmCategoryInfoReq req=new QueryPmCategoryInfoReq();
			    String strPageNo=(null==request.getParameter(CiticWebConstants.PAGENO))?"1":request.getParameter(CiticWebConstants.PAGENO);
		    	String strPageSize=(null==request.getParameter(CiticWebConstants.PAGESIZE))?"10":request.getParameter(CiticWebConstants.PAGESIZE);
		    	req.setPageNO(Integer.parseInt(strPageNo));
		    	req.setPageSize(Integer.parseInt(strPageSize));
		    	req.setTenantId(user.getTenantId());
		    	if(!StringUtil.isBlank(mainProId)){
		    	req.setMainProCode(mainProId);	
		    	}
		    	if(!StringUtil.isBlank(mainProName)){
		    		req.setMainProName(mainProName);
		    	}
		    	PmcategoryInfoResponse response=	productDefinSV.getCategoryInfos(req);
		    	
			    responseData=new ResponseData<PageInfo<MainProduct>>(ResponseData.AJAX_STATUS_SUCCESS, "获取数据成功", response.getPageInfo());
			    
		 }catch(Exception e){
			 responseData=new ResponseData<PageInfo<MainProduct>>(ResponseData.AJAX_STATUS_FAILURE, "获取数据失败");
		 }
		 return responseData;
	}
	
	
}
