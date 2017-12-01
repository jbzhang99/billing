package com.ai.baas.op.web.controller.drsubject;

import com.ai.baas.op.web.constants.BaaSOPConstants;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.web.model.ResponseData;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import com.ai.opt.sso.client.filter.SSOClientUser;
import com.ai.opt.sys.api.gnsubject.interfaces.IGnSubjectManageSV;
import com.ai.opt.sys.api.gnsubject.interfaces.IGnSubjectQuerySV;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectInfoVo;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectListResponse;
import com.ai.opt.sys.api.gnsubject.param.GnSubjectQueryVo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 详单管理
 * @author zhaogw
 *
 */
@RestController
@RequestMapping("/drSubject")
public class DrSubjectController {

	private static final Logger LOG = Logger.getLogger(DrSubjectController.class);
	
	
	@RequestMapping("/list")
    public ModelAndView toDrList(HttpServletRequest request) {

		return new ModelAndView("jsp/drSubject/drSubjectList");
    }
	
	 /**
     * 配置加载查询
     * @param request
     * @return
     */
    @RequestMapping("/getList")
    public ResponseData<PageInfo<GnSubjectListResponse>> getDrList(HttpServletRequest request , GnSubjectQueryVo queryInfo ){
    	HttpSession session = request.getSession();
    	SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
    	IGnSubjectQuerySV iGnSubjectQuerySV = DubboConsumerFactory.getService("iGnSubjectQuerySV");
    	ResponseData<PageInfo<GnSubjectListResponse>> responseData = null;
    	queryInfo.setIndustryCode(user.getIndustryCode());
    	queryInfo.setSubjectType(BaaSOPConstants.SubjectCode.DR_SUBJECT);
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
    public ModelAndView toDrAdd(Model model , HttpServletRequest request) {
		return new ModelAndView("jsp/drSubject/addDrSubject");
    }
	
	@RequestMapping("/addDrSubject")
	public ResponseData<BaseResponse> getDrList(HttpServletRequest request , GnSubjectInfoVo subjectInfo ){
    	HttpSession session = request.getSession();
    	SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
    	IGnSubjectManageSV iGnSubjectManageSV = DubboConsumerFactory.getService("iGnSubjectManageSV");
    	ResponseData<BaseResponse> responseData = null;
    	subjectInfo.setIndustryCode(user.getIndustryCode());
    	subjectInfo.setSubjectType(BaaSOPConstants.SubjectCode.DR_SUBJECT);
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
    public ModelAndView toEditDrSubject(Model model , HttpServletRequest request , GnSubjectInfoVo queryInfo ) {

		HttpSession session = request.getSession();
    	SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
    	IGnSubjectQuerySV iGnSubjectQuerySV = DubboConsumerFactory.getService("iGnSubjectQuerySV");
    	ResponseData<PageInfo<GnSubjectListResponse>> responseData = null;
    	queryInfo.setIndustryCode(user.getIndustryCode());
    	queryInfo.setTenantId(user.getTenantId());
    	queryInfo.setSubjectType(BaaSOPConstants.SubjectCode.DR_SUBJECT);
    	LOG.debug("配置加载查询入参:"+JSONObject.fromObject(queryInfo).toString());
    	GnSubjectInfoVo subjectInfo = iGnSubjectQuerySV.getGnSubject(queryInfo);
    	model.addAttribute("subjectInfo", subjectInfo);
    	return new ModelAndView("jsp/drSubject/addDrSubject");
    }
	
	@RequestMapping("/deleteSubject")
	public ResponseData<String> deleteDrSubject(HttpServletRequest request , GnSubjectInfoVo subjectInfo ){
    	HttpSession session = request.getSession();
    	SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
    	IGnSubjectManageSV iGnSubjectManageSV = DubboConsumerFactory.getService("iGnSubjectManageSV");
    	ResponseData<String> responseData = null;
    	subjectInfo.setIndustryCode(user.getIndustryCode());
    	subjectInfo.setSubjectType(BaaSOPConstants.SubjectCode.DR_SUBJECT);
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
	
	
	@RequestMapping("/getBillList")
    public ResponseData<PageInfo<GnSubjectListResponse>> getBillList(HttpServletRequest request , GnSubjectQueryVo queryInfo ){
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
	
	
	
}
