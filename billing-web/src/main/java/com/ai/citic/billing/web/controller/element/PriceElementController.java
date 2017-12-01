package com.ai.citic.billing.web.controller.element;

import com.ai.baas.prd.api.element.interfaces.IPriceElementSV;
import com.ai.baas.prd.api.element.params.BaseSpecResponse;
import com.ai.baas.prd.api.element.params.CheckCategoryId;
import com.ai.baas.prd.api.element.params.CheckPolicyParam;
import com.ai.baas.prd.api.element.params.Element;
import com.ai.baas.prd.api.element.params.ElementAddResponse;
import com.ai.baas.prd.api.element.params.ElementDeleteVO;
import com.ai.baas.prd.api.element.params.ElementDetailRequireResult;
import com.ai.baas.prd.api.element.params.ElementDetailRequireVO;
import com.ai.baas.prd.api.element.params.ElementIncreaseVO;
import com.ai.baas.prd.api.element.params.ElementRequireResult;
import com.ai.baas.prd.api.element.params.ElementRequireVO;
import com.ai.baas.prd.api.element.params.PmSpecTypeRes;
import com.ai.baas.prd.api.element.params.SpecTypeQueryReq;
import com.ai.baas.prd.api.product.interfaces.IProductDefineSV;
import com.ai.baas.prd.api.product.params.BaseCategoryInfo;
import com.ai.baas.prd.api.product.params.CategoryInfoVO;
import com.ai.baas.prd.api.product.params.SubsProductQueryReq;
import com.ai.citic.billing.web.constants.CiticWebConstants;
import com.ai.citic.billing.web.model.element.ElementAddVo;
import com.ai.citic.billing.web.model.element.ProductVo;
import com.ai.citic.billing.web.model.sso.CiticSSOClientUser;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.web.model.ResponseData;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 定价元素管理
 * @author wangluyang
 *
 */
@RestController
@RequestMapping("/priceElement")
public class PriceElementController {

	public static final Logger LOGGER = LoggerFactory.getLogger(PriceElementController.class);
	
	public final String CATEGORY_TYPE = "ZJ";
	
	@RequestMapping("/toPriceElementList")
    public ModelAndView toPolicyList(HttpServletRequest request, HttpServletResponse response){
        ModelAndView view = new ModelAndView("jsp/element/priceElementList");
        return view;
    }
	
	@RequestMapping("/toPriceElementAdd")
    public ModelAndView toPriceElementAdd(HttpServletRequest request, Model model){
        ModelAndView view = new ModelAndView("jsp/element/priceElementAdd");
        return view;
    }
	
	@RequestMapping("/toCustomPriceElementAdd")
    public ModelAndView toCustomPriceElementAdd(HttpServletRequest request, Model model){
		String mainProductId = request.getParameter("mainProductId");
		String mainProductName = request.getParameter("mainProductName");
		String supplierId = request.getParameter("supplierId");
		String supplierName = request.getParameter("supplierName");
		model.addAttribute("mainProductId",mainProductId);
		model.addAttribute("mainProductName",mainProductName);
		model.addAttribute("supplierId",supplierId);
		model.addAttribute("supplierName",supplierName);
        ModelAndView view = new ModelAndView("jsp/element/customPriceElementAdd");
        return view;
    }
	
	/**
     * 
     * @param categoryId
     * @param request
     * @return
     */
	@RequestMapping("/toCustomElementUpdate")
    public ModelAndView toCustomElementUpdate(String categoryId,String supplierId,String supplierName, Model model){
		model.addAttribute("supplierId",supplierId);
		model.addAttribute("supplierName",supplierName);
		model.addAttribute("categoryId",categoryId);
        return new ModelAndView("jsp/element/customPriceElementUpdate");
    }
	
	/**
     * 
     * @param categoryId
     * @param request
     * @return
     */
	@RequestMapping("/toElementUpdate")
    public ModelAndView toElementUpdate(String categoryId, Model model){
		
		model.addAttribute("categoryId",categoryId);
        return new ModelAndView("jsp/element/priceElementUpdate");
    }
	
	/**
	 * 
	 * @param categoryId
	 * @param request
	 * @return
	 */
	@RequestMapping("/toElementShow")
    public ModelAndView toElementShow(String categoryId, Model model){
		
		model.addAttribute("categoryId",categoryId);
        return new ModelAndView("jsp/element/priceElementShow");
    }
	
	/**
	 * 获得定价策略变量单位
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getSubsProduct")
	public List<CategoryInfoVO> getSubsProduct(String mainProCode, HttpServletRequest request) {
		HttpSession session = request.getSession();
		BaseCategoryInfo info = null;
		try{
			CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
			IProductDefineSV productDefineSV = DubboConsumerFactory.getService(IProductDefineSV.class);
			SubsProductQueryReq queryReq = new SubsProductQueryReq();
			queryReq.setTenantId(user.getTenantId());
			queryReq.setMainProCode(mainProCode);
			info = productDefineSV.querySubsProduct(queryReq);
		} catch (Exception e) {
			LOGGER.info("获取菜单失败", e);
		}
		return info!=null?info.getCategoryInfos():null;
	}
	
	/**
	 * 查询产品规格
	 * @param categoryId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getSpecTypeList")
	public List<PmSpecTypeRes> getSpecTypeList(String categoryId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		BaseSpecResponse info = null;
		try{
			CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
			IPriceElementSV priceElementSV = DubboConsumerFactory.getService(IPriceElementSV.class);
			SpecTypeQueryReq queryReq = new SpecTypeQueryReq();
			queryReq.setTenantId(user.getTenantId());
			queryReq.setMainProductId(categoryId);
			info = priceElementSV.querySpecTypeList(queryReq);
		} catch (Exception e) {
			LOGGER.info("获取菜单失败", e);
		}
		return info!=null?info.getSpecTypes():null;
	}
	
	
	/**
	 * 
	 * @param queryVo
	 * @param request
	 * @return
	 */
    @RequestMapping("/getList")
    public ResponseData<PageInfo<ProductVo>> getList(ElementRequireVO queryVo, HttpServletRequest request){
    	
    	ResponseData<PageInfo<ProductVo>> responseData = null;
    	try {
    		HttpSession session = request.getSession();
    		CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);

    		IPriceElementSV elementSV = DubboConsumerFactory.getService(IPriceElementSV.class);
    		ElementRequireVO queryParam = new ElementRequireVO();
    		queryParam.setTenantId(user.getTenantId());
    		queryParam.setMainProductId(queryVo.getMainProductId());
    		queryParam.setMainProductName(queryVo.getMainProductName());
    		queryParam.setBillingCycle(queryVo.getBillingCycle());
    		
    		String strPageNo=(null==request.getParameter(CiticWebConstants.PAGENO))?"1":request.getParameter(CiticWebConstants.PAGENO);
	    	String strPageSize=(null==request.getParameter(CiticWebConstants.PAGESIZE))?"10":request.getParameter(CiticWebConstants.PAGESIZE);
	    	queryParam.setPageNo(Integer.parseInt(strPageNo));
	    	queryParam.setPageSize(Integer.parseInt(strPageSize));
    		
    		LOGGER.info("定价元素查询入參"+JSONObject.toJSONString(queryParam).toString());
	    	ElementRequireResult response = elementSV.searchElement(queryParam);
	    	LOGGER.info("定价元素查询出參"+JSONObject.toJSONString(response).toString());
 	    	if(response.getResponseHeader().isSuccess()){
 	    		PageInfo<ProductVo> result = new PageInfo<ProductVo>();
 	    		if(response!=null && response.getProducts()!=null && response.getProducts().getResult()!=null 
 	    				&& response.getProducts().getResult().size()>0){
 	    			result.setCount(response.getProducts().getCount());
 	    			result.setPageCount(response.getProducts().getPageCount());
 	    			result.setPageNo(response.getProducts().getPageNo());
 	    			result.setPageSize(response.getProducts().getPageSize());
 	    			List<ProductVo> list = new ArrayList<>();
 	    			for(int i=0; i<response.getProducts().getResult().size(); i++){
 	    				ProductVo productVo = new ProductVo();
 	    				productVo.setProduct(response.getProducts().getResult().get(i));
 	    				productVo.setIndex(String.valueOf((response.getProducts().getPageNo()-1)*response.getProducts().getPageSize()+1+i));
 	    				list.add(productVo);
 	    			}
 	    			result.setResult(list);
 	    		}else{
 	    			result.setCount(0);
 	    			result.setPageCount(0);
 	    			result.setPageNo(Integer.valueOf(strPageNo));
 	    			result.setPageSize(Integer.valueOf(strPageSize));
 	    			List<ProductVo> list = new ArrayList<>();
 	    			result.setResult(list);
 	    		}
 	    		responseData = new ResponseData<PageInfo<ProductVo>>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功", result);
 	    	}else{
 	    		responseData = new ResponseData<PageInfo<ProductVo>>(ResponseData.AJAX_STATUS_FAILURE, "查询失败");
 	    	}
    		
    	} catch (Exception e) {
			responseData = new ResponseData<PageInfo<ProductVo>>(ResponseData.AJAX_STATUS_FAILURE,"获取信息异常");
			LOGGER.error("获取信息出错：", e);
		}
        return responseData;
    }
    
    /**
     * 新增
     * @param vo
     * @param request
     * @return
     */
    @RequestMapping("/saveElement")
	public ResponseData<BaseResponse> saveElement(ElementAddVo vo, HttpServletRequest request){
		 
		ResponseData<BaseResponse> responseData = null;
		String msg = "新增";
		try {
			HttpSession session = request.getSession();
			CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
			
			IPriceElementSV priceElementSV = DubboConsumerFactory.getService(IPriceElementSV.class);
			ElementIncreaseVO saveVo = new ElementIncreaseVO();
			saveVo.setTenantId(user.getTenantId());
			saveVo.setBillingCycle(vo.getBillingCycle());
			saveVo.setCategoryId(vo.getCategoryId());
			saveVo.setCategoryType(CATEGORY_TYPE);
			saveVo.setMainProductId(vo.getMainProductId());
			saveVo.setMainProductName(vo.getMainProductName());
			saveVo.setTradeCode(vo.getTradeCode());
			saveVo.setBillingMode(vo.getBillingMode());
			saveVo.setModeCode(vo.getModeCode());
			List<Element> elements = JSONArray.parseArray(vo.getElementListStr(), Element.class);
			saveVo.setElements(elements);
			
			ElementAddResponse response = null;
			LOGGER.info("定价元素"+msg+"入參"+JSONArray.toJSONString(saveVo));
			response = priceElementSV.addElement(saveVo);
			LOGGER.info("定价元素"+msg+"出參"+JSONArray.toJSONString(response));
			if(response!=null && response.getResponseHeader()!=null && response.getResponseHeader().isSuccess()){
				responseData = new ResponseData<BaseResponse>(ResponseData.AJAX_STATUS_SUCCESS, "添加成功", response);
    		}else{
    			responseData = new ResponseData<BaseResponse>(ResponseData.AJAX_STATUS_FAILURE, "添加失败", response);
    		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			responseData = new ResponseData<BaseResponse>(ResponseData.AJAX_STATUS_FAILURE, "添加异常");
			LOGGER.error("获取信息出错：", e);
		} 
		return responseData;
	 }
    
    /**
     * 修改
     * @param vo
     * @param request
     * @return
     */
    @RequestMapping("/updateElement")
	public ResponseData<BaseResponse> updateElement(ElementAddVo vo, HttpServletRequest request){
		 
		ResponseData<BaseResponse> responseData = null;
		String msg = "修改";
		try {
			HttpSession session = request.getSession();
			CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
			
			IPriceElementSV priceElementSV = DubboConsumerFactory.getService(IPriceElementSV.class);
			ElementIncreaseVO saveVo = new ElementIncreaseVO();
			saveVo.setTenantId(user.getTenantId());
			saveVo.setBillingCycle(vo.getBillingCycle());
			saveVo.setCategoryId(vo.getCategoryId());
			saveVo.setCategoryType(CATEGORY_TYPE);
			saveVo.setMainProductId(vo.getMainProductId());
			saveVo.setMainProductName(vo.getMainProductName());
			saveVo.setTradeCode(vo.getTradeCode());
			saveVo.setBillingMode(vo.getBillingMode());
			saveVo.setModeCode(vo.getModeCode());
			List<Element> elements = JSONArray.parseArray(vo.getElementListStr(), Element.class);
			saveVo.setElements(elements);
			
			BaseResponse response = null;
			LOGGER.info("定价元素"+msg+"入參"+JSONArray.toJSONString(saveVo));
			response = priceElementSV.alterElement(saveVo);
			LOGGER.info("定价元素"+msg+"出參"+JSONArray.toJSONString(response));
			if(response!=null && response.getResponseHeader()!=null && response.getResponseHeader().isSuccess()){
				responseData = new ResponseData<BaseResponse>(ResponseData.AJAX_STATUS_SUCCESS, msg+"成功", response);
    		}else{
    			responseData = new ResponseData<BaseResponse>(ResponseData.AJAX_STATUS_FAILURE, msg+"失败", response);
    		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			responseData = new ResponseData<BaseResponse>(ResponseData.AJAX_STATUS_FAILURE, msg+"异常");
			LOGGER.error("获取信息出错：", e);
		} 
		return responseData;
	 }
    
    /**
     * 
     * @param categoryId
     * @param request
     * @return
     */
	@RequestMapping("/getElement")
	@ResponseBody
    public ResponseData<ElementDetailRequireResult> getElement(String categoryId,HttpServletRequest request){
		
		ResponseData<ElementDetailRequireResult> responseData = null;
		try{
			HttpSession session = request.getSession();
			CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
			
			IPriceElementSV priceElementSV = DubboConsumerFactory.getService(IPriceElementSV.class);
			ElementDetailRequireVO requireVO = new ElementDetailRequireVO();
			requireVO.setTenantId(user.getTenantId());
			requireVO.setCategoryId(categoryId);
			ElementDetailRequireResult response = priceElementSV.searchElementDetail(requireVO);
			responseData = new ResponseData<ElementDetailRequireResult>(ResponseData.AJAX_STATUS_SUCCESS, "获取数据成功", response);
		 }catch(Exception e){
			 responseData=new ResponseData<ElementDetailRequireResult>(ResponseData.AJAX_STATUS_FAILURE, "获取数据失败");
		 }
        return responseData;
    }
	
	/**
     * 
     * @param categoryId
     * @param request
     * @return
     */
	@RequestMapping("/checkCategoryId")
	@ResponseBody
    public ResponseData<BaseResponse> checkCategoryId(String categoryId,HttpServletRequest request){
		
		ResponseData<BaseResponse> responseData = null;
		try{
			HttpSession session = request.getSession();
			CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
			
			IPriceElementSV priceElementSV = DubboConsumerFactory.getService(IPriceElementSV.class);
			CheckCategoryId requireVO = new CheckCategoryId();
			requireVO.setTenantId(user.getTenantId());
			requireVO.setCategoryId(categoryId);
			BaseResponse response = priceElementSV.checkElement(requireVO);
			responseData = new ResponseData<BaseResponse>(ResponseData.AJAX_STATUS_SUCCESS, "获取数据成功", response);
		 }catch(Exception e){
			 responseData=new ResponseData<BaseResponse>(ResponseData.AJAX_STATUS_FAILURE, "获取数据失败");
		 }
        return responseData;
    }
	
	/**
     * 判断策略是否已经被关联
     * @param policyId
     * @param request
     * @return
     */
	@RequestMapping("/checkExistPolicyId")
	@ResponseBody
    public ResponseData<BaseResponse> checkExistPolicyId(String policyId,HttpServletRequest request){
		
		ResponseData<BaseResponse> responseData = null;
		try{
			HttpSession session = request.getSession();
			CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
			
			IPriceElementSV priceElementSV = DubboConsumerFactory.getService(IPriceElementSV.class);
			CheckPolicyParam requireVO = new CheckPolicyParam();
			requireVO.setTenantId(user.getTenantId());
			requireVO.setPolicyId(policyId);
			BaseResponse response = priceElementSV.checkExistPolicyId(requireVO);
			responseData = new ResponseData<BaseResponse>(ResponseData.AJAX_STATUS_SUCCESS, "获取数据成功", response);
		 }catch(Exception e){
			 responseData=new ResponseData<BaseResponse>(ResponseData.AJAX_STATUS_FAILURE, "获取数据失败");
		 }
        return responseData;
    }
    
    /**
     * 
     * @param mainProductId
     * @param categoryId
     * @param request
     * @return
     */
    @RequestMapping("/delElement")
	public ResponseData<Object> delElement(String mainProductId, String categoryId, HttpServletRequest request){
		 
		ResponseData<Object> responseData = null;
		try {
			HttpSession session = request.getSession();
			CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
			
			IPriceElementSV priceElementSV = DubboConsumerFactory.getService(IPriceElementSV.class);
			ElementDeleteVO infoVO = new ElementDeleteVO();
			infoVO.setTenantId(user.getTenantId());
//			infoVO.setMainProductId(mainProductId);
			infoVO.setCategoryId(categoryId);
			BaseResponse response = priceElementSV.deleteElement(infoVO);
			if(response!=null && response.getResponseHeader()!=null && response.getResponseHeader().isSuccess()){
    			responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_SUCCESS, "删除成功");
    		}else{
    			responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_FAILURE, "删除失败");
    		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_FAILURE, "删除异常");
			LOGGER.error("获取信息出错：", e);
		} 
		return responseData;
	 }
    
}
