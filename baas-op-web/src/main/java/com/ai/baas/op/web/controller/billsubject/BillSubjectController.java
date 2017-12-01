package com.ai.baas.op.web.controller.billsubject;

import com.ai.baas.op.web.constants.BaaSOPConstants;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.web.model.ResponseData;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import com.ai.opt.sso.client.filter.SSOClientUser;
import com.ai.opt.sys.api.gnsubject.interfaces.IGnSubjectManageSV;
import com.ai.opt.sys.api.gnsubject.interfaces.IGnSubjectQuerySV;
import com.ai.opt.sys.api.gnsubject.param.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 账单管理
 * @author zhaogw
 *
 */
@RestController
@RequestMapping("/billSubject")
public class BillSubjectController {

	private static final Logger LOG = Logger.getLogger(BillSubjectController.class);
	
	@RequestMapping("/list")
    public ModelAndView toList(HttpServletRequest request) {

		return new ModelAndView("jsp/billSubject/billSubjectList");
    }
	
	 /**
     * 配置加载查询
     * @param request
     * @return
     */
    @RequestMapping("/getList")
    public ResponseData<PageInfo<GnSubjectListResponse>> getList(HttpServletRequest request , GnSubjectQueryVo queryInfo ){
    	HttpSession session = request.getSession();
    	SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
    	IGnSubjectQuerySV iGnSubjectQuerySV = DubboConsumerFactory.getService("iGnSubjectQuerySV");
    	ResponseData<PageInfo<GnSubjectListResponse>> responseData = null;
    	queryInfo.setIndustryCode(user.getIndustryCode());
    	queryInfo.setSubjectType(BaaSOPConstants.SubjectCode.BILL_SUBJECT);
    	queryInfo.setTenantId(user.getTenantId());
    	try {
	    	LOG.debug("配置加载查询入参:"+JSONObject.fromObject(queryInfo).toString());
	    	PageInfo<GnSubjectListResponse> resultInfo = iGnSubjectQuerySV.getGnSubjectList(queryInfo);
	    	LOG.debug("配置加载查询出参:"+JSONArray.fromObject(resultInfo).toString());
    		responseData = new ResponseData<PageInfo<GnSubjectListResponse>>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功", resultInfo);
    	} catch (Exception e) {
    		responseData = new ResponseData<PageInfo<GnSubjectListResponse>>(ResponseData.AJAX_STATUS_FAILURE, "查询失败");
			LOG.error("获取信息出错：", e);
		}
        return responseData;
    }
	
	
	@RequestMapping("/toAdd")
    public ModelAndView toIndex(Model model , HttpServletRequest request) {
		return new ModelAndView("jsp/billSubject/addBillSubject");
    }
	
	@RequestMapping("/addBillSubject")
	public ResponseData<BaseResponse> getList(HttpServletRequest request , GnSubjectInfoVo subjectInfo ){
    	HttpSession session = request.getSession();
    	SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
    	IGnSubjectManageSV iGnSubjectManageSV = DubboConsumerFactory.getService("iGnSubjectManageSV");
    	ResponseData<BaseResponse> responseData = null;
    	subjectInfo.setIndustryCode(user.getIndustryCode());
    	subjectInfo.setSubjectType(BaaSOPConstants.SubjectCode.BILL_SUBJECT);
    	subjectInfo.setTenantId(user.getTenantId());
//    	subjectInfo.setSubjectId(100002l);
    	try {
	    	LOG.debug("配置加载查询入参:"+JSONObject.fromObject(subjectInfo).toString());
	    	if(subjectInfo.getSubjectId() !=null && subjectInfo.getSubjectId() !=0){
	    		
	    		BaseResponse p = iGnSubjectManageSV.updateGnSubject(subjectInfo);
	    		responseData = new ResponseData<BaseResponse>(ResponseData.AJAX_STATUS_SUCCESS, "更新成功", p);
	    	}else{
	    		BaseResponse p = iGnSubjectManageSV.addGnSubject(subjectInfo);
	    		responseData = new ResponseData<BaseResponse>(ResponseData.AJAX_STATUS_SUCCESS, "插入成功", p);
	    	}
    	} catch (Exception e) {
    		responseData = new ResponseData<BaseResponse>(ResponseData.AJAX_STATUS_FAILURE, "false");
			LOG.error("插入科目失败：", e);
		}
        return responseData;
    }
	
	
	
	@RequestMapping("/toEdit")
    public ModelAndView toEdit(Model model , HttpServletRequest request , GnSubjectInfoVo queryInfo ) {

		HttpSession session = request.getSession();
    	SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
    	IGnSubjectQuerySV iGnSubjectQuerySV = DubboConsumerFactory.getService("iGnSubjectQuerySV");
    	ResponseData<PageInfo<GnSubjectListResponse>> responseData = null;
    	queryInfo.setIndustryCode(user.getIndustryCode());
    	queryInfo.setTenantId(user.getTenantId());
    	LOG.debug("配置加载查询入参:"+JSONObject.fromObject(queryInfo).toString());
    	GnSubjectInfoVo subjectInfo = iGnSubjectQuerySV.getGnSubject(queryInfo);
//    	GnSubjectInfoVo subjectInfo =   new GnSubjectInfoVo();
//    	subjectInfo.setSubjectName("test");
//    	subjectInfo.setSubjectDesc("test");
//    	subjectInfo.setSubjectId(2001002l);
    	model.addAttribute("subjectInfo", subjectInfo);
    	return new ModelAndView("jsp/billSubject/addBillSubject");
    }
	
	@RequestMapping("/deleteSubject")
	public ResponseData<String> deleteSubject(HttpServletRequest request , GnSubjectInfoVo subjectInfo ){
    	HttpSession session = request.getSession();
    	SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
    	IGnSubjectManageSV iGnSubjectManageSV = DubboConsumerFactory.getService("iGnSubjectManageSV");
    	ResponseData<String> responseData = null;
    	subjectInfo.setIndustryCode(user.getIndustryCode());
    	subjectInfo.setSubjectType(BaaSOPConstants.SubjectCode.BILL_SUBJECT);
    	subjectInfo.setTenantId(user.getTenantId());
//    	subjectInfo.setSubjectId(100002l);
    	try {
	    	LOG.debug("配置加载查询入参:"+JSONObject.fromObject(subjectInfo).toString());
	    	iGnSubjectManageSV.delGnSubject(subjectInfo);
    		responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS, "删除成功", "000000");
    	} catch (Exception e) {
    		responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, "false");
			LOG.error("插入科目失败：", e);
		}
        return responseData;
    }
	
	@RequestMapping("/toConnected")
    public ModelAndView drSubjectConnected(Model model , HttpServletRequest request,GnSubjectInfoVo subjectInfo) {
		model.addAttribute("subjectInfo", subjectInfo);
		return new ModelAndView("jsp/drSubject/drSubjectConnected");
    }
	
	@RequestMapping("/getConnectList")
    public ResponseData<GnSubjectRelatedDetailViewResponse> getConnectList(HttpServletRequest request ){
    	HttpSession session = request.getSession();
    	SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
    	IGnSubjectQuerySV iGnSubjectQuerySV = DubboConsumerFactory.getService("iGnSubjectQuerySV");
    	ResponseData<GnSubjectRelatedDetailViewResponse> responseData = null;
    	GnSubjectRelatedRequest queryInfo = new GnSubjectRelatedRequest();
    	queryInfo.setIndustryCode(user.getIndustryCode());
    	queryInfo.setSubjectId(request.getParameter("subjectId"));
    	queryInfo.setTenantId(user.getTenantId());
    	try {
	    	LOG.debug("配置加载查询入参:"+JSONObject.fromObject(queryInfo).toString());
	    	GnSubjectRelatedDetailViewResponse resultInfo = iGnSubjectQuerySV.queryRelatedGnSubject(queryInfo);
	    	LOG.debug("配置加载查询出参:"+JSONArray.fromObject(resultInfo).toString());
    		responseData = new ResponseData<GnSubjectRelatedDetailViewResponse>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功", resultInfo);
    	} catch (Exception e) {
    		responseData = new ResponseData<GnSubjectRelatedDetailViewResponse>(ResponseData.AJAX_STATUS_FAILURE, "查询失败");
			LOG.error("获取信息出错：", e);
		}
        return responseData;
    }
	
	/**
	 * 保存关联详单科目
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveConnectSubject")
	public ResponseData<String> saveConnectSubject(HttpServletRequest request ){
    	HttpSession session = request.getSession();
    	String subjectId = request.getParameter("subjectId");
    	String subjectIds = request.getParameter("subjectIds");
    	String [] idArray = getJsonToStringArray(subjectIds);
    	SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
    	IGnSubjectManageSV iGnSubjectManageSV = DubboConsumerFactory.getService("iGnSubjectManageSV");
    	ResponseData<String> responseData = null;
    	try {
    		GnSubjectRelatedParamVO saveVo = new GnSubjectRelatedParamVO();
    		saveVo.setBillSubjectId(subjectId);
    		saveVo.setTenantId(user.getTenantId());
    		
    		List<GnSubjectDrSubjectIdParamVO> list = new ArrayList<GnSubjectDrSubjectIdParamVO>();
    		for(String subId:idArray){
    			GnSubjectDrSubjectIdParamVO vo = new GnSubjectDrSubjectIdParamVO();
    			vo.setDrSubjectId(subId);
    			list.add(vo);
    		}
    		saveVo.setDrSubjectParamVOList(list);
    		LOG.debug("详单科目保存入参:"+JSONObject.fromObject(saveVo).toString());
    		iGnSubjectManageSV.updateRelatedGnSubject(saveVo);
    		responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS, "true");
    	} catch (Exception e) {
    		responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, "false");
			LOG.error("插入科目失败：", e);
		}
        return responseData;
    }
	/**
     * 将json数组转化为String型
     */
    private static String[] getJsonToStringArray(String str) {
        JSONArray jsonArray = JSONArray.fromObject(str);
        String[] arr = new String[jsonArray.size()];
        for(int i=0; i<jsonArray.size(); i++){
            arr[i] = jsonArray.getString(i);
        }
        return arr;
    }
}
