package com.ai.baas.mt.web.controller.siteletter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ai.baas.mt.web.constants.BaaSMTConstants;
import com.ai.baas.mt.web.model.MailInfoVo;
import com.ai.baas.mt.web.model.SiteMailVo;
import com.ai.baas.mt.web.util.DateUtil;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.web.model.ResponseData;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import com.ai.opt.sso.client.filter.SSOClientUser;
import com.ai.opt.sys.api.stationmail.interfaces.IGnStationMailManageSV;
import com.ai.opt.sys.api.stationmail.interfaces.IGnStationMailQuerySV;
import com.ai.opt.sys.api.stationmail.param.StationMailQueryRequest;
import com.ai.opt.sys.api.stationmail.param.StationMailVo;
import com.ai.opt.sys.api.stationmail.param.StationMails;
import com.ai.opt.sys.api.stationmail.param.StationMailsPageQueryVo;
import com.ai.opt.sys.api.tenant.interfaces.ITenantManageSV;
import com.ai.opt.sys.api.tenant.param.TenantInfoResponse;
import com.ai.opt.sys.api.tenant.param.TenantInfoVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 站内信管理
 * @author wangluyang
 *
 */
@RestController
@RequestMapping("/siteLetter")
public class SiteLetterController {

	private static final Logger LOG = Logger.getLogger(SiteLetterController.class);
	
	@RequestMapping("/list")
    public ModelAndView toList(HttpServletRequest request) {
        return new ModelAndView("jsp/siteletter/siteLetterList");
    }
	
	@RequestMapping("/toAdd")
    public ModelAndView toAdd(HttpServletRequest request) {
        return new ModelAndView("jsp/siteletter/sendSiteLetter");
    }
	
    @RequestMapping("/getList")
    public ResponseData<PageInfo<SiteMailVo>> getList(HttpServletRequest request){
    	ResponseData<PageInfo<SiteMailVo>> responseData = null;
    	try {
    		HttpSession session = request.getSession();
    		SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);

    		IGnStationMailQuerySV gnStationMailQuerySV = DubboConsumerFactory.getService(IGnStationMailQuerySV.class);
    		
    		StationMailsPageQueryVo queryInfo = new StationMailsPageQueryVo();
    		queryInfo.setTenantId(user.getTenantId());

    		String strPageNo=(null==request.getParameter(BaaSMTConstants.PAGENO))?"1":request.getParameter(BaaSMTConstants.PAGENO);
	    	String strPageSize=(null==request.getParameter(BaaSMTConstants.PAGESIZE))?"10":request.getParameter(BaaSMTConstants.PAGESIZE);
    		queryInfo.setPageNo(Integer.parseInt(strPageNo));
    		queryInfo.setPageSize(Integer.parseInt(strPageSize));
    		
    		LOG.info("站内信查询入参:"+JSONArray.fromObject(queryInfo).toString());
    		PageInfo<StationMailVo> resultInfo = gnStationMailQuerySV.getStationMails(queryInfo);
    		LOG.info("站内信查询出参:"+JSONArray.fromObject(resultInfo).toString());
    		
    		PageInfo<SiteMailVo> pageInfo = new PageInfo<>();
    		pageInfo.setCount(resultInfo.getCount());
    		pageInfo.setPageCount(resultInfo.getPageCount());
    		pageInfo.setPageNo(resultInfo.getPageNo());
    		pageInfo.setPageSize(resultInfo.getPageSize());
    		List<SiteMailVo> dataList = this.getSiteMailList(resultInfo.getResult(), String.valueOf(resultInfo.getCount()));
    		pageInfo.setResult(dataList);
    		
    		responseData = new ResponseData<PageInfo<SiteMailVo>>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功", pageInfo);
    	} catch (Exception e) {
			responseData = new ResponseData<PageInfo<SiteMailVo>>(ResponseData.AJAX_STATUS_FAILURE,"获取信息异常");
			LOG.error("获取信息出错：", e);
		}
        return responseData;
    }
    
    @RequestMapping("/toView")
	public ModelAndView toView(HttpServletRequest request) {

		IGnStationMailQuerySV gnStationMailQuerySV = DubboConsumerFactory.getService(IGnStationMailQuerySV.class);
		StationMailQueryRequest queryInfo = new StationMailQueryRequest();
		String mailId = request.getParameter("mailId")==null?"":request.getParameter("mailId");
		queryInfo.setTenantId("VIV-BYD");
		queryInfo.setMailId(Long.parseLong(mailId));
		LOG.info("站内信详情查询入参:"+JSONArray.fromObject(queryInfo).toString());
		StationMailVo resultInfo = gnStationMailQuerySV.queryStationMailDetail(queryInfo);
		LOG.info("站内信详情查询出参:"+JSONArray.fromObject(resultInfo).toString());
		
		request.setAttribute("siteMailVo", resultInfo);
		return new ModelAndView("jsp/siteletter/siteLetterView");
    }
    
    /**
     * 租户查询
     * @param request
     * @param queryInfo
     * @return
     */
    @RequestMapping("/getTenantInfos")
    public ResponseData<List<TenantInfoVo>> getTenantInfos(HttpServletRequest request, TenantInfoVo queryInfo){
    	ResponseData<List<TenantInfoVo>> responseData = null;
    	try{
    		ITenantManageSV iTenantManageSV = DubboConsumerFactory.getService(ITenantManageSV.class);
    		if(queryInfo!=null){
    			TenantInfoResponse infoResponse = iTenantManageSV.queryTenantInfos(queryInfo);
    			if(infoResponse!=null){
    				responseData = new ResponseData<List<TenantInfoVo>>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功", infoResponse.getTenantInfoVo());
    			}else{
    				responseData = new ResponseData<List<TenantInfoVo>>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功", new ArrayList<TenantInfoVo>());
    			}
    		}
    		responseData = new ResponseData<List<TenantInfoVo>>(ResponseData.AJAX_STATUS_FAILURE, "查询入參不能为空");
    	}catch (Exception e) {
			responseData = new ResponseData<List<TenantInfoVo>>(ResponseData.AJAX_STATUS_FAILURE,"获取信息异常");
			LOG.error("获取信息出错：", e);
		}
        return responseData;
    	
    }
    
    /**
     * 站内信发送
     * @param mailVo
     * @param request
     * @return
     */
    public ResponseData<Object> sendLetter(MailInfoVo mailVo,HttpServletRequest request){
    	ResponseData<Object> responseData = null;
    	String msg = "发送";
    	try {
    		HttpSession session = request.getSession();
    		SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
    		IGnStationMailManageSV stationMailManageSV = DubboConsumerFactory.getService(IGnStationMailManageSV.class);
    		StationMails mails = new StationMails();
    		List<StationMailVo> list = new ArrayList<>();
    		if(mailVo!=null && mailVo.getRecipients()!=null){
    			for(int i=0; i<mailVo.getRecipients().size(); i++){
    				StationMailVo vo = new StationMailVo();
    				vo.setTitle(mailVo.getTitle());
    				vo.setContent(mailVo.getTitle());
    				vo.setLevel(mailVo.getLevel());
    				vo.setIsDel("0");
    				vo.setIsRead("0");
    				vo.setSender(user.getTenantId());
    				vo.setSenderName(user.getTenantName());
    				vo.setRecipient(mailVo.getRecipients().get(i));
    				vo.setRecipientName(mailVo.getRecipientNames().get(i));
    				list.add(vo);
    			}
    		}
    		mails.setMailVoList(list);
    		
    		LOG.debug("站内信发送入参:"+JSONObject.fromObject(mails).toString());
    		BaseResponse baseResponse = stationMailManageSV.saveStationMails(mails);
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
    
    public List<SiteMailVo> getSiteMailList(List<StationMailVo> fromList, String count){
    	List<SiteMailVo> resultList = new ArrayList<>();
    	Map<String, SiteMailVo> map = new HashMap<>();
    	List<String> keys = new ArrayList<>();
    	for(StationMailVo vo:fromList){
    		String keyStr = DateUtil.judgmentDate(vo.getSendtime());
    		if(map.containsKey(keyStr)){
    			SiteMailVo siteMailVo = map.get(keyStr);
    			siteMailVo.getStationMailVos().add(vo);
    		}else{
    			SiteMailVo siteMailVo = new SiteMailVo();
    			siteMailVo.setKeyStr(keyStr);
    			siteMailVo.setTotalCount(count);
    			List<StationMailVo> list = new ArrayList<>();
    			list.add(vo);
    			siteMailVo.setStationMailVos(list);
    			map.put(keyStr, siteMailVo);
    			keys.add(keyStr);
    		}
    	}
    	if(keys.size()>0){
    		for(String key:keys){
    			resultList.add(map.get(key));
    		}
		}
    	return resultList;
    }
}
