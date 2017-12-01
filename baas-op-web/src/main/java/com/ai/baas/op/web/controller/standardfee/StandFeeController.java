package com.ai.baas.op.web.controller.standardfee;

import com.ai.baas.bmc.api.baseInfo.params.BaseCode;
import com.ai.baas.bmc.api.priceinfo.interfaces.IPriceInfoSV;
import com.ai.baas.bmc.api.priceinfo.params.*;
import com.ai.baas.op.web.constants.BaaSOPConstants;
import com.ai.baas.op.web.controller.common.ParamController;
import com.ai.baas.op.web.model.AddStandPostageVo;
import com.ai.baas.op.web.model.PostagePeriod;
import com.ai.baas.op.web.model.StandPostageQueryVo;
import com.ai.baas.op.web.model.StandardListVo;
import com.ai.baas.op.web.util.DateUtil;
import com.ai.baas.op.web.util.TradeSeqUtil;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.web.model.ResponseData;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import com.ai.opt.sso.client.filter.SSOClientUser;
import com.ai.opt.sys.api.gnsubject.interfaces.IGnSubjectQuerySV;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectDetailVo;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectListResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 标准资费
 * @author wangluyang
 */
@RestController
@RequestMapping("/standardFee")
public class StandFeeController {
	
	
	private static final Logger LOG = Logger.getLogger(StandFeeController.class);
	
	@RequestMapping("/list")
    public ModelAndView toList(HttpServletRequest request) {

		return new ModelAndView("jsp/standardfee/standPostageList");
    }
	
	@RequestMapping("/toAdd")
	public ModelAndView index(HttpServletRequest request) {

		return new ModelAndView("jsp/standardfee/addStandPostage");
    }
	
	@RequestMapping("/toUpdate")
	public ModelAndView toUpdate(HttpServletRequest request) {

		HttpSession session = request.getSession();
		SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
		
		IPriceInfoSV priceInfoSV = DubboConsumerFactory.getService("iPriceInfoSV");
		
		QueryInfoParams queryInfo = new QueryInfoParams();
		queryInfo.setStandardId(request.getParameter("standardId"));
		queryInfo.setTenantId(user.getTenantId());
		queryInfo.setPageNo(1);
		queryInfo.setPageSize(10);
		
		queryInfo.setTradeSeq("test"+DateUtil.getDateString(DateUtil.yyyyMMddHHmmssSSS)+"1");
		queryInfo.setTenantId(user.getTenantId());
		
		LOG.info("标准资费查询入参:"+JSONArray.fromObject(queryInfo).toString());
		ResponseMessage resultInfo = priceInfoSV.getPriceInfo(queryInfo);
		
		if(resultInfo!=null && resultInfo.getStandardList()!=null && resultInfo.getStandardList().getResult().size()>0){
			request.setAttribute("standardFee", resultInfo.getStandardList().getResult().get(0));
		}else{
			request.setAttribute("standardFee", new StandardList());
		}
		return new ModelAndView("jsp/standardfee/addStandPostage");
    }
	
	/**
	 * 关联详单
	 * @param request
	 * @return
	 */
	@RequestMapping("/toRelateSubject")
	public ModelAndView toRelateSubject(HttpServletRequest request) {

		HttpSession session = request.getSession();
		SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
		
		IPriceInfoSV priceInfoSV = DubboConsumerFactory.getService("iPriceInfoSV");
		
		QueryInfoParams queryInfo = new QueryInfoParams();
		queryInfo.setStandardId(request.getParameter("standardId"));
		queryInfo.setTenantId(user.getTenantId());
		queryInfo.setPageNo(1);
		queryInfo.setPageSize(10);
		
		queryInfo.setTradeSeq("test"+DateUtil.getDateString(DateUtil.yyyyMMddHHmmssSSS)+"1");
		queryInfo.setTenantId(user.getTenantId());
		
		LOG.info("标准资费查询入参:"+JSONArray.fromObject(queryInfo).toString());
		ResponseMessage resultInfo = priceInfoSV.getPriceInfo(queryInfo);
		
		if(resultInfo!=null && resultInfo.getStandardList()!=null && resultInfo.getStandardList().getResult().size()>0){
			request.setAttribute("standardFee", resultInfo.getStandardList().getResult().get(0));
		}else{
			request.setAttribute("standardFee", new StandardList());
		}
		
		return new ModelAndView("jsp/standardfee/relateSubject");
    }
	
	@RequestMapping("/toView")
	public ModelAndView toView(HttpServletRequest request) {

		HttpSession session = request.getSession();
		SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
		
		IPriceInfoSV priceInfoSV = DubboConsumerFactory.getService("iPriceInfoSV");
		
		QueryInfoParams queryInfo = new QueryInfoParams();
		queryInfo.setStandardId(request.getParameter("standardId"));
		queryInfo.setTenantId(user.getTenantId());
		queryInfo.setPageNo(1);
		queryInfo.setPageSize(10);
		
		queryInfo.setTradeSeq("test"+DateUtil.getDateString(DateUtil.yyyyMMddHHmmssSSS)+"1");
		queryInfo.setTenantId(user.getTenantId());
		
		LOG.info("标准资费查询入参:"+JSONArray.fromObject(queryInfo).toString());
		ResponseMessage resultInfo = priceInfoSV.getPriceInfo(queryInfo);
		
		if(resultInfo!=null && resultInfo.getStandardList()!=null && resultInfo.getStandardList().getResult().size()>0){
			request.setAttribute("standardFee", resultInfo.getStandardList().getResult().get(0));
		}else{
			request.setAttribute("standardFee", new StandardList());
		}
		return new ModelAndView("jsp/standardfee/viewStandardFee");
    }
	
	/**
     * 标准资费查询
     * @param request
     * @return
     */
    @RequestMapping("/getList")
    public ResponseData<PageInfo<StandardListVo>> getList(StandPostageQueryVo queryVo, HttpServletRequest request){
    	
    	ResponseData<PageInfo<StandardListVo>> responseData = null;
    	try {
    		HttpSession session = request.getSession();
    		SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
    		
    		IPriceInfoSV priceInfoSV = DubboConsumerFactory.getService("iPriceInfoSV");
    		
    		QueryInfoParams queryInfo = new QueryInfoParams();
    		queryInfo.setStandardId(queryVo.getStandardId());
    		queryInfo.setPriceName(queryVo.getPriceName());
    		queryInfo.setServiceType(queryVo.getServiceType());
    		queryInfo.setSubServiceType(queryVo.getServiceTypeDetail());
    		queryInfo.setPriceState(queryVo.getPriceState()); 
    		
    		queryInfo.setTradeSeq("test"+DateUtil.getDateString(DateUtil.yyyyMMddHHmmssSSS)+"1");
    		queryInfo.setTenantId(user.getTenantId());
    		
    		String strPageNo=(null==request.getParameter(BaaSOPConstants.PAGENO))?"1":request.getParameter(BaaSOPConstants.PAGENO);
	    	String strPageSize=(null==request.getParameter(BaaSOPConstants.PAGESIZE))?"10":request.getParameter(BaaSOPConstants.PAGESIZE);
    		queryInfo.setPageNo(Integer.parseInt(strPageNo));
    		queryInfo.setPageSize(Integer.parseInt(strPageSize));
    		
    		LOG.info("标准资费查询入参:"+JSONArray.fromObject(queryInfo).toString());
    		ResponseMessage resultInfo = priceInfoSV.getPriceInfo(queryInfo);
    		LOG.info("标准资费查询出参:"+JSONArray.fromObject(resultInfo).toString());
    		
    		PageInfo<StandardListVo> result = new PageInfo<StandardListVo>();
    		List<StandardListVo> resultList = new ArrayList<StandardListVo>();
    		if(resultInfo!=null && resultInfo.getStandardList()!=null && resultInfo.getStandardList().getResult()!=null){
    			int pageNo = resultInfo.getStandardList().getPageNo();
    			int pageSize = resultInfo.getStandardList().getPageSize();
    			
    			List<BaseCode> serverTypes = ParamController.getSysParams(
    					user.getTenantId(), "SERVICE_TYPE", TradeSeqUtil.newTradeSeq(user.getTenantId()));
    			
        		for(int i=0; i< resultInfo.getStandardList().getResult().size(); i++){
        			StandardListVo entity = new StandardListVo();
        			entity.setStandardList(resultInfo.getStandardList().getResult().get(i));
        			for(BaseCode code:serverTypes){
        				if(StringUtils.equals(resultInfo.getStandardList().getResult().get(i).getServiceType(), code.getParamCode())){
        					entity.setServiceTypeName(code.getParamName());
        					
        					List<BaseCode> serverTypeDetails = ParamController.getChildCode(
        							user.getTenantId(), String.valueOf(code.getId()), TradeSeqUtil.newTradeSeq(user.getTenantId()));
        					for(BaseCode detailsCode:serverTypeDetails){
        						if(StringUtils.equals(resultInfo.getStandardList().getResult().get(i).getUsageList().get(0).getSubServiceType()
        								, detailsCode.getParamCode())){
        							entity.setSubServiceTypeName(detailsCode.getParamName());
        						}
        					}
        				}
        			}
        			entity.setIndex((pageNo-1)*pageSize+1+i);
        			resultList.add(entity);
        		}
        		result.setPageCount(resultInfo.getStandardList().getPageCount());
        		result.setCount(resultInfo.getStandardList().getCount());
        		result.setPageNo(resultInfo.getStandardList().getPageNo());
        		result.setPageSize(resultInfo.getStandardList().getPageSize()); 
    		}else{
    			result.setPageCount(1);
        		result.setCount(0);
        		result.setPageNo(Integer.parseInt(strPageNo));
        		result.setPageSize(Integer.parseInt(strPageSize)); 
    		}
    		result.setResult(resultList);
    		
    		responseData = new ResponseData<PageInfo<StandardListVo>>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功", result);
    	} catch (Exception e) {
			responseData = new ResponseData<PageInfo<StandardListVo>>(ResponseData.AJAX_STATUS_FAILURE,"获取信息异常");
			LOG.error("获取信息出错：", e);
		}
        return responseData;
    }
    
    /**
     * 新增/修改标准资费
     * @param request
     * @return
     */
    @RequestMapping("/save")
    public ResponseData<Object> save(AddStandPostageVo vo, HttpServletRequest request){
    	
    	ResponseData<Object> responseData = null;
    	String msg = "保存";
    	try {
    		//保存/更新...
    		HttpSession session = request.getSession();
    		SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
    		
    		IPriceInfoSV priceInfoSV = DubboConsumerFactory.getService("iPriceInfoSV");
    		StandardPriceInfoParams saveVo = new StandardPriceInfoParams();
    		
    		saveVo.setTradeSeq(user.getTenantId()+DateUtil.getDateString(DateUtil.yyyyMMddHHmmssSSS)+UUID.randomUUID());
    		saveVo.setPriceName(vo.getPriceName());
    		
    		//标准资费使用量列表
    		List<StanderdPriceInfoUsage> priceInfos = new ArrayList<StanderdPriceInfoUsage>();
    		if(StringUtils.isNotBlank(vo.getPeriodsJsonStr())){
    			JSONArray jsonArray = JSONArray.fromObject(vo.getPeriodsJsonStr());
    			List<PostagePeriod> list = JSONArray.toList(jsonArray, PostagePeriod.class);
    			for(PostagePeriod period : list){
    				StanderdPriceInfoUsage priceVo = new StanderdPriceInfoUsage();
    				priceVo.setAmount(Double.parseDouble(period.getAmount()));
    				saveVo.setServiceType(period.getServiceType());
    				priceVo.setSubServiceType(period.getSubServiceType());
    				priceVo.setUnit(vo.getUnit());
    				priceInfos.add(priceVo);
    			}
    		}
    		saveVo.setUsageList(priceInfos);
    		
//    		/**
//    		 * TIME：次
//    		 * CYCLE：周期
//    		 */
//    		saveVo.setCycleType(vo.getCycleType());
//    		saveVo.setCycleAmount(Double.parseDouble(vo.getCycleAmount()));
//    		saveVo.setCycleId(vo.getCycleId());
    		
    		saveVo.setPrice(Double.parseDouble(vo.getPrice()));
    		saveVo.setPriceType(vo.getPriceType());
    		saveVo.setTenantId(user.getTenantId());
    		
    		saveVo.setComments(vo.getComments());
    		
    		if(StringUtils.isNotBlank(vo.getStandardId())){//更新
    			saveVo.setStandardId(vo.getStandardId());
    			saveVo.setStatus(vo.getStatus());
    			saveVo.setUpdateId("UPDATE");
    			msg = "更新";
    		}else{//保存
    			saveVo.setUpdateId("CREATE");
    			saveVo.setStatus("INACTIVE");
    		}
    		
    		LOG.debug("配置加载查询入参:"+JSONObject.fromObject(saveVo).toString());
    		BaseResponse baseResponse = priceInfoSV.updatePriceInfo(saveVo);
    		if(baseResponse!=null && baseResponse.getResponseHeader()!=null && baseResponse.getResponseHeader().isSuccess()){
    			responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_SUCCESS, msg+"成功");
    		}else{
    			responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_FAILURE, msg+"失败");
    		}
    	} catch (Exception e) {
			responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_FAILURE, msg+"异常");
			LOG.error("获取信息出错：", e);
		}
        return responseData;
    }
    
    /**
     * 更新状态
     * @param vo
     * @param request
     * @return
     */
    @RequestMapping("/updateStatus")
    public ResponseData<Object> updateStatus(AddStandPostageVo vo, HttpServletRequest request){
    	
    	ResponseData<Object> responseData = null;
    	try {
    		HttpSession session = request.getSession();
    		SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
    		
    		IPriceInfoSV priceInfoSV = DubboConsumerFactory.getService("iPriceInfoSV");
    		StandardPriceInfoParams saveVo = new StandardPriceInfoParams();
    		
    		saveVo.setTradeSeq(user.getTenantId()+DateUtil.getDateString(DateUtil.yyyyMMddHHmmssSSS)+UUID.randomUUID());
    		saveVo.setStandardId(vo.getStandardId());
    		saveVo.setTenantId(user.getTenantId());
    		saveVo.setUpdateId("UPDATE");
    		saveVo.setStatus(vo.getStatus());
    		saveVo.setComments(vo.getComments());
    		
    		LOG.debug("更改状态入参:"+JSONObject.fromObject(saveVo).toString());
    		BaseResponse baseResponse = priceInfoSV.deletePriceInfo(saveVo);
    		if(baseResponse!=null && baseResponse.getResponseHeader()!=null && baseResponse.getResponseHeader().isSuccess()){
    			responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_SUCCESS, "操作成功");
    		}else{
    			responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_FAILURE, "操作失败");
    		}
    	} catch (Exception e) {
			responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_FAILURE, "操作异常");
			LOG.error("获取信息出错：", e);
		}
        return responseData;
    }
    
    /**
     * 删除
     * @param vo
     * @param request
     * @return
     */
    @RequestMapping("/delete")
    public ResponseData<Object> delete(AddStandPostageVo vo, HttpServletRequest request){
    	
    	ResponseData<Object> responseData = null;
    	try {
    		HttpSession session = request.getSession();
    		SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
    		
    		IPriceInfoSV priceInfoSV = DubboConsumerFactory.getService("iPriceInfoSV");
    		StandardPriceInfoParams saveVo = new StandardPriceInfoParams();
    		
    		saveVo.setTradeSeq(user.getTenantId()+DateUtil.getDateString(DateUtil.yyyyMMddHHmmssSSS)+UUID.randomUUID());
    		saveVo.setStandardId(vo.getStandardId());
    		saveVo.setTenantId(user.getTenantId());
    		saveVo.setUpdateId("DELETE");
    		saveVo.setComments(vo.getComments()); 
    		
    		LOG.debug("删除入参:"+JSONObject.fromObject(saveVo).toString());
    		BaseResponse baseResponse = priceInfoSV.deletePriceInfo(saveVo);
    		if(baseResponse!=null && baseResponse.getResponseHeader()!=null && baseResponse.getResponseHeader().isSuccess()){
    			responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_SUCCESS, "删除成功");
    		}else{
    			responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_FAILURE, "删除失败");
    		}
    	} catch (Exception e) {
			responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_FAILURE, "删除异常");
			LOG.error("获取信息出错：", e);
		}
        return responseData;
    }
    
	@RequestMapping("/getConnectList")
    public List<GnSubjectListResponse> getConnectList(HttpServletRequest request ){
		List<GnSubjectListResponse> resultInfo = null;
    	HttpSession session = request.getSession();
    	SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
    	IGnSubjectQuerySV iGnSubjectQuerySV = DubboConsumerFactory.getService("iGnSubjectQuerySV");
    	ResponseData<List<GnSubjectListResponse>> responseData = null;
    	GnSubjectDetailVo queryInfo = new GnSubjectDetailVo();
    	queryInfo.setIndustryCode(user.getIndustryCode());
    	queryInfo.setSubjectType(BaaSOPConstants.SubjectCode.DR_SUBJECT);
    	queryInfo.setTenantId(user.getTenantId());
    	try {
	    	resultInfo = iGnSubjectQuerySV.getGnSubjectListMayRelated(queryInfo);
    	} catch (Exception e) {
			LOG.error("获取信息出错：", e);
		}
        return resultInfo;
    }
    
    /**
     * 保存关联详单
     * @param vo
     * @param request
     * @return
     */
    @RequestMapping("/saveRelateSubject")
    public ResponseData<Object> saveRelateSubject(AddStandPostageVo vo, HttpServletRequest request){
    	
    	ResponseData<Object> responseData = null;
    	try {
    		HttpSession session = request.getSession();
    		SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
    		
    		IPriceInfoSV priceInfoSV = DubboConsumerFactory.getService("iPriceInfoSV");
    		SubjectInput saveVo = new SubjectInput();
    		
    		saveVo.setTradeSeq(user.getTenantId()+DateUtil.getDateString(DateUtil.yyyyMMddHHmmssSSS)+UUID.randomUUID());
    		saveVo.setStandardId(vo.getStandardId());
    		saveVo.setTenantId(user.getTenantId());
    		saveVo.setSubjectCode(vo.getSubjectCode());
    		
    		LOG.debug("保存关联详单入参:"+JSONObject.fromObject(saveVo).toString());
    		BaseResponse baseResponse = priceInfoSV.linkSubjectId(saveVo);
    		if(baseResponse!=null && baseResponse.getResponseHeader()!=null && baseResponse.getResponseHeader().isSuccess()){
    			responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_SUCCESS, "关联成功");
    		}else{
    			responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_FAILURE, "关联失败");
    		}
    	} catch (Exception e) {
			responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_FAILURE, "关联异常");
			LOG.error("获取信息出错：", e);
		}
        return responseData;
    }
}
