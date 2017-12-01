package com.ai.citic.billing.web.controller.strategy;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ai.baas.prd.api.strategy.interfaces.IPriceStrategySV;
import com.ai.baas.prd.api.strategy.params.CheckPolicyParam;
import com.ai.baas.prd.api.strategy.params.DeleteStrategyParam;
import com.ai.baas.prd.api.strategy.params.DetailAddVO;
import com.ai.baas.prd.api.strategy.params.DetailShowVO;
import com.ai.baas.prd.api.strategy.params.FactorAddVO;
import com.ai.baas.prd.api.strategy.params.FactorShowVO;
import com.ai.baas.prd.api.strategy.params.PolicyAddVO;
import com.ai.baas.prd.api.strategy.params.PriceStrategyQueryReponse;
import com.ai.baas.prd.api.strategy.params.QueryDetailParam;
import com.ai.baas.prd.api.strategy.params.QueryParams;
import com.ai.baas.prd.api.strategy.params.StartegyRecordVO;
import com.ai.baas.prd.api.strategy.params.StrategyAddParams;
import com.ai.baas.prd.api.strategy.params.StrategyDetailQueryReponse;
import com.ai.baas.prd.api.strategy.params.StrategyImportParam;
import com.ai.citic.billing.web.constants.CiticWebConstants;
import com.ai.citic.billing.web.model.sso.CiticSSOClientUser;
import com.ai.citic.billing.web.model.strategy.StrategyAddVo;
import com.ai.citic.billing.web.util.DateUtil;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.opt.sdk.web.model.ResponseData;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


/**
 * 定价策略
 * @author wangluyang
 *
 */
@RestController
@RequestMapping("/strategy")
public class PriceStrategyController {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(PriceStrategyController.class);

	@RequestMapping("/toStrategyList")
    public ModelAndView toStrategyList(HttpServletRequest request, HttpServletResponse response){
        ModelAndView view = new ModelAndView("jsp/strategy/strategyList");
        return view; 
    }
	
	@RequestMapping("/toStrategyAdd")
    public ModelAndView toStrategyAdd(HttpServletRequest request, HttpServletResponse response){
        ModelAndView view = new ModelAndView("jsp/strategy/strategyAdd");
        return view;
    }
	
	/**
	 * 
	 * @param policyId
	 * @param request
	 * @return
	 */
	@RequestMapping("/toStrategyUpdate")
    public ModelAndView toStrategyUpdate(String policyId,HttpServletRequest request){
		
		HttpSession session = request.getSession();
		CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
		
		IPriceStrategySV strategySV = DubboConsumerFactory.getService(IPriceStrategySV.class);
		QueryDetailParam param = new QueryDetailParam();
		param.setPolicyId(policyId);
		param.setTenantId(user.getTenantId());
		StrategyDetailQueryReponse response = strategySV.getStrategyDetail(param);
		if(response.getResponseHeader().isSuccess()){
			request.setAttribute("strategyVO", response.getStrategyShowVO());
		}
		
        ModelAndView view = new ModelAndView("jsp/strategy/strategyUpdate");
        return view;
    }
	
	/**
	 * 
	 * @param policyId
	 * @param request
	 * @return
	 */
	@RequestMapping("/toStrategyShow")
    public ModelAndView toStrategyShow(String policyId,HttpServletRequest request){
		
		HttpSession session = request.getSession();
		CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
		
		IPriceStrategySV strategySV = DubboConsumerFactory.getService(IPriceStrategySV.class);
		QueryDetailParam param = new QueryDetailParam();
		param.setPolicyId(policyId);
		param.setTenantId(user.getTenantId());
		StrategyDetailQueryReponse response = strategySV.getStrategyDetail(param);
		if(response.getResponseHeader().isSuccess()){
			request.setAttribute("strategyVO", response.getStrategyShowVO());
		}
		
        ModelAndView view = new ModelAndView("jsp/strategy/strategyShow");
        return view;
    }
	
	/**
     * 导入
     * @param request
     * @param response
     */
    @RequestMapping("/importTxt")
    public ResponseData<Object> importTxt(HttpServletRequest request, HttpServletResponse response){
    	
    	ResponseData<Object> responseData = null;
    	try {
    		HttpSession session = request.getSession();
    		CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
    		 MultipartRequest multipartRequest = (MultipartRequest) request;
             MultipartFile uploadFile = multipartRequest.getFile("f");
             InputStream in = uploadFile.getInputStream();
             InputStreamReader read = new InputStreamReader(in, "utf-8");
             BufferedReader bufferedReader = new BufferedReader(read);
             StringBuffer jsonStr = new StringBuffer("");
             String lineTxt = null;
             while((lineTxt = bufferedReader.readLine()) != null){
            	 jsonStr.append(lineTxt);
             }
             StrategyImportParam importParam = new StrategyImportParam();
             List<StrategyAddParams> params = JSONArray.parseArray(jsonStr.toString(), StrategyAddParams.class);
             importParam.setTenantId(user.getTenantId());
             importParam.setStrategyVos(params);
             IPriceStrategySV strategySV = DubboConsumerFactory.getService(IPriceStrategySV.class);
             LOGGER.info("定价策略导入入參"+JSONArray.toJSONString(importParam));
             BaseResponse baseResponse = strategySV.addStrategys(importParam);
 			
 			 if(baseResponse!=null && baseResponse.getResponseHeader()!=null && baseResponse.getResponseHeader().isSuccess()){
 				 responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_SUCCESS, "导入成功");
 			 }else{
 				 responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_FAILURE, "导入失败,请确认导入格式为jsonArray");
 			 }
             read.close();
		}catch (Exception e) {
			responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_FAILURE, "导入异常");
			LOGGER.error("获取信息出错：", e);
		} 
		return responseData;
    }
	
	 /**
     * 导出
     * @param policyId 
     * @param request
     * @param response
     */
    @RequestMapping("/exportTxt")
    public void exportTxt(String policyId,HttpServletRequest request, HttpServletResponse response){
    	BufferedOutputStream buff = null;
    	ServletOutputStream os = null;
    	try {
	    	HttpSession session = request.getSession();
			CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
			
			IPriceStrategySV strategySV = DubboConsumerFactory.getService(IPriceStrategySV.class);
			QueryDetailParam param = new QueryDetailParam();
			param.setPolicyId(policyId);
			param.setTenantId(user.getTenantId());
			StrategyDetailQueryReponse queryReponse = strategySV.getStrategyDetail(param);
			os = response.getOutputStream();
			buff = new BufferedOutputStream(os); 
	        response.reset();// 清空输出流
	        response.setContentType("application/msexcel");// 定义输出类型
	        response.setHeader("Content-disposition", "attachment; filename=strategy"
	                +DateUtil.getDateString(new Date(), DateUtil.yyyyMMddHHmmssSSS)+".txt");// 设定输出文件头
	        if(queryReponse!=null){
	        	List<StrategyAddParams> exportList = new ArrayList<>();
	        	StrategyAddParams params = new StrategyAddParams();
	        	params.setPolicyName(queryReponse.getStrategyShowVO().getPolicyName());
	        	params.setTenantId(queryReponse.getStrategyShowVO().getTenantId());
	        	PolicyAddVO policyVo = new PolicyAddVO();
	        	policyVo.setPolicyType(queryReponse.getStrategyShowVO().getPolicyVo().getPolicyType());
	        	List<DetailAddVO> list = new ArrayList<>();
	        	for(DetailShowVO showVO : queryReponse.getStrategyShowVO().getPolicyVo().getVariableVOs()){
	        		DetailAddVO vo = new DetailAddVO();
//	        		vo.setComments(comments);
	        		vo.setIndex(showVO.getIndex());
	        		vo.setPrice(showVO.getPrice());
	        		List<FactorAddVO> factors = new ArrayList<>();
	        		for(FactorShowVO factorShowVO : showVO.getFactorVos()){
	        			FactorAddVO addVo = new FactorAddVO();
	        			BeanUtils.copyProperties(addVo, factorShowVO);
	        			factors.add(addVo);
	        		}
	        		vo.setFactorVos(factors);
	        		list.add(vo);
	        	}
	        	policyVo.setVariableVOs(list);
	        	params.setPolicyVo(policyVo);
	        	exportList.add(params);
	        	buff.write(JSONObject.toJSONString(exportList).getBytes());
	        }
    	} catch (Exception e) {
			LOGGER.error("获取信息出错：", e);
		}finally {
			try { 
				if(buff!=null){
					buff.close();   
				}
				if(os!=null){
					os.close();  
				}
            } catch (Exception e) {       
                e.printStackTrace();       
           }    
		}
    }
	/**
	 * 新增定价策略
	 * @param strategyAddVo
	 * @param request
	 * @return
	 */
    @RequestMapping("/saveStrategy")
	public ResponseData<Object> saveStrategy(StrategyAddVo strategyAddVo, HttpServletRequest request){
		 
		ResponseData<Object> responseData = null;
		String msg = "新增";
		try {
			HttpSession session = request.getSession();
			CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
			
			IPriceStrategySV strategySV = DubboConsumerFactory.getService(IPriceStrategySV.class);
			StrategyAddParams strategyAddParams = new StrategyAddParams();
			strategyAddParams.setPolicyName(strategyAddVo.getPolicyName());
			strategyAddParams.setTenantId(user.getTenantId());
			
			if(!StringUtil.isBlank(strategyAddVo.getPolicyId())){
				strategyAddParams.setPolicyId(strategyAddVo.getPolicyId());
				msg = "修改";
			}
			
			PolicyAddVO policyAddVO = new PolicyAddVO();
			policyAddVO.setPolicyType(strategyAddVo.getPolicyType());
			
			List<DetailAddVO> detailAddVOs = JSONArray.parseArray(strategyAddVo.getDetailListStr(), DetailAddVO.class);
			policyAddVO.setVariableVOs(detailAddVOs);
			strategyAddParams.setPolicyVo(policyAddVO);
			
			LOGGER.info("定价策略"+msg+"入參"+JSONArray.toJSONString(strategyAddParams));
			BaseResponse response = strategySV.addStrategy(strategyAddParams);
			
			if(response!=null && response.getResponseHeader()!=null && response.getResponseHeader().isSuccess()){
				responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_SUCCESS, msg+"成功");
			}else{
				responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_FAILURE, msg+"失败");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_FAILURE, msg+"异常");
			LOGGER.error("获取信息出错：", e);
		} 
		return responseData;
	 }
    
    /**
	 * 定价策略查询
	 * @param queryVo
	 * @param request
	 * @return
	 */
    @RequestMapping("/getList")
    public ResponseData<PageInfo<StartegyRecordVO>> getList(QueryParams queryVo, HttpServletRequest request){
    	
    	ResponseData<PageInfo<StartegyRecordVO>> responseData = null;
    	try {
    		HttpSession session = request.getSession();
    		CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
    		IPriceStrategySV strategySV = DubboConsumerFactory.getService(IPriceStrategySV.class);
    		queryVo.setTenantId(user.getTenantId());
    		
    		String strPageNo=(null==request.getParameter(CiticWebConstants.PAGENO))?"1":request.getParameter(CiticWebConstants.PAGENO);
	    	String strPageSize=(null==request.getParameter(CiticWebConstants.PAGESIZE))?"10":request.getParameter(CiticWebConstants.PAGESIZE);
	    	queryVo.setPageNo(Integer.parseInt(strPageNo));
	    	queryVo.setPageSize(Integer.parseInt(strPageSize));
	    	
	    	PriceStrategyQueryReponse queryReponse = strategySV.queryStrategy(queryVo);
    		
 	    	if(queryReponse.getResponseHeader().isSuccess()){
 	    		responseData = new ResponseData<PageInfo<StartegyRecordVO>>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功", queryReponse.getRecords());
 	    	}else{
 	    		responseData = new ResponseData<PageInfo<StartegyRecordVO>>(ResponseData.AJAX_STATUS_FAILURE, "查询失败");
 	    	}
    		
    	} catch (Exception e) {
			responseData = new ResponseData<PageInfo<StartegyRecordVO>>(ResponseData.AJAX_STATUS_FAILURE,"获取信息异常");
			LOGGER.error("获取信息出错：", e);
		}
        return responseData;
    }
    
    /**
     * 
     * @param policyId
     * @param request
     * @return
     */
    @RequestMapping("/delStrategy")
	public ResponseData<Object> delStrategy(String policyId, HttpServletRequest request){
		 
		ResponseData<Object> responseData = null;
		try {
			HttpSession session = request.getSession();
			CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
			
			IPriceStrategySV strategySV = DubboConsumerFactory.getService(IPriceStrategySV.class);
			DeleteStrategyParam strategyParam = new DeleteStrategyParam();
			strategyParam.setPolicyId(policyId);
			strategyParam.setTenantId(user.getTenantId());
			BaseResponse response = strategySV.deleteStrategy(strategyParam);
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
    
    /**
     * 验证策略名称是否重复
     * @param policyId
     * @param request
     * @return
     */
	@RequestMapping("/checkExistPolicyName")
	@ResponseBody
    public ResponseData<BaseResponse> checkExistPolicyName(String policyName,HttpServletRequest request){
		
		ResponseData<BaseResponse> responseData = null;
		try{
			HttpSession session = request.getSession();
			CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
			
			IPriceStrategySV priceStrategySV = DubboConsumerFactory.getService(IPriceStrategySV.class);
			CheckPolicyParam requireVO = new CheckPolicyParam();
			requireVO.setTenantId(user.getTenantId());
			requireVO.setPolicyName(policyName);
			BaseResponse response = priceStrategySV.checkExistPolicyName(requireVO);
			responseData = new ResponseData<BaseResponse>(ResponseData.AJAX_STATUS_SUCCESS, "获取数据成功", response);
		 }catch(Exception e){
			 responseData=new ResponseData<BaseResponse>(ResponseData.AJAX_STATUS_FAILURE, "获取数据失败");
		 }
        return responseData;
    }
}
