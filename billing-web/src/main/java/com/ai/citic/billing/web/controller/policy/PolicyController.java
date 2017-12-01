package com.ai.citic.billing.web.controller.policy;

import com.ai.baas.bmc.api.queryidinfo.params.BlAcctInfoInfo;
import com.ai.baas.omc.api.policy.interfaces.IOmcPolicySV;
import com.ai.baas.omc.api.policy.params.GetPolicyInfoParam;
import com.ai.baas.omc.api.policy.params.GetPolicyResponse;
import com.ai.baas.omc.api.policy.params.OmcPolicyInfoVO;
import com.ai.baas.omc.api.policy.params.OmcPolicyResponse;
import com.ai.baas.omc.api.policy.params.PolicyInfoResponseVo;
import com.ai.baas.omc.api.policy.params.QueryInfoParam;
import com.ai.baas.omc.api.policy.params.QueryPolicyResponse;
import com.ai.citic.billing.web.common.CommonCaller;
import com.ai.citic.billing.web.constants.CiticWebConstants;
import com.ai.citic.billing.web.model.policy.PolicyAddVo;
import com.ai.citic.billing.web.model.policy.PolicyInfoVo;
import com.ai.citic.billing.web.model.policy.QueryInfoVo;
import com.ai.citic.billing.web.model.sso.CiticSSOClientUser;
import com.ai.citic.billing.web.util.AmountUtil;
import com.ai.citic.billing.web.util.TradeSeqUtil;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.opt.sdk.web.model.ResponseData;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONArray;

/**
 * 信控管理模块
 * @author wangluyang
 *
 */
@RestController
@RequestMapping("/policy")
public class PolicyController {

	public static final Logger LOGGER = LoggerFactory.getLogger(PolicyController.class);
	
	@Autowired
    private CommonCaller commonCaller;
	
	@RequestMapping("/toPolicyList")
    public ModelAndView toPolicyList(HttpServletRequest request, HttpServletResponse response){
        ModelAndView view = new ModelAndView("jsp/policy/policyList");
        return view;
    }
	
	@RequestMapping("/toPolicyAdd")
    public ModelAndView toPolicyAdd(HttpServletRequest request, HttpServletResponse response){
        ModelAndView view = new ModelAndView("jsp/policy/policyAdd");
        return view;
    }
	
	@RequestMapping("/toPolicyUpdate")
    public ModelAndView toPolicyUpdate(String custId,HttpServletRequest request){
		
		HttpSession session = request.getSession();
		CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
		
		IOmcPolicySV omcRuleSV = DubboConsumerFactory.getService(IOmcPolicySV.class);
		GetPolicyInfoParam param = new GetPolicyInfoParam();
		param.setCustId(custId);
		param.setTenantId(user.getTenantId());
		param.setTradeSeq(TradeSeqUtil.newTradeSeq(user.getTenantId()));
		GetPolicyResponse response = omcRuleSV.getPolicy(param);
		if(response.getResponseHeader().isSuccess()){
			PolicyInfoVo vo = new PolicyInfoVo();
			vo.setExtCustId(response.getResponseVo().getExtcustId());
			vo.setPolicyId(response.getResponseVo().getPolicyid());
			vo.setPolicyName(response.getResponseVo().getPolicyName());
			vo.setPressPayment(String.valueOf(AmountUtil.changeLiToYuan(response.getResponseVo().getWarningCeil())));
			vo.setDescription(response.getResponseVo().getPolicydescribe());
			request.setAttribute("policyVo", vo);
		}
		
        ModelAndView view = new ModelAndView("jsp/policy/policyUpdate");
        return view;
    }
	
	@RequestMapping("/toPolicyShow")
    public ModelAndView toPolicyShow(String custId,HttpServletRequest request){
		
		HttpSession session = request.getSession();
		CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
		
		IOmcPolicySV omcRuleSV = DubboConsumerFactory.getService(IOmcPolicySV.class);
		GetPolicyInfoParam param = new GetPolicyInfoParam();
		param.setCustId(custId);
		param.setTenantId(user.getTenantId());
		param.setTradeSeq(TradeSeqUtil.newTradeSeq(user.getTenantId()));
		GetPolicyResponse response = omcRuleSV.getPolicy(param);
		if(response.getResponseHeader().isSuccess()){
			PolicyInfoVo vo = new PolicyInfoVo();
			vo.setExtCustId(response.getResponseVo().getExtcustId());
			vo.setPolicyId(response.getResponseVo().getPolicyid());
			vo.setPolicyName(response.getResponseVo().getPolicyName());
			vo.setPressPayment(String.valueOf(AmountUtil.changeLiToYuan(response.getResponseVo().getWarningCeil())));
			vo.setDescription(response.getResponseVo().getPolicydescribe());
			request.setAttribute("policyVo", vo);
		}
		
        ModelAndView view = new ModelAndView("jsp/policy/policyShow");
        return view;
    }
	
	/**
	 * 信控查询
	 * @param queryVo
	 * @param request
	 * @return
	 */
    @RequestMapping("/getList")
    public ResponseData<PageInfo<PolicyInfoResponseVo>> getList(QueryInfoVo queryVo, HttpServletRequest request){
    	
    	ResponseData<PageInfo<PolicyInfoResponseVo>> responseData = null;
    	try {
    		HttpSession session = request.getSession();
    		CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);

    		IOmcPolicySV omcRuleSV = DubboConsumerFactory.getService(IOmcPolicySV.class);
    		QueryInfoParam queryParam = new QueryInfoParam();
    		queryParam.setTenantId(user.getTenantId());
    		queryParam.setRuleId(queryVo.getRuleId());
    		queryParam.setRuleName(queryVo.getRuleName());
    		
    		boolean isQuery = true;
    		List<String> accountIds = new ArrayList<>();
            if(!StringUtil.isBlank(queryVo.getCustId())){
	             List<BlAcctInfoInfo> acctAndCustInfos = commonCaller.getAcctAndCustInfo(user.getTenantId(), queryVo.getCustId());
	             if(!CollectionUtil.isEmpty(acctAndCustInfos)){
	                 for (BlAcctInfoInfo blAcctInfoInfo:acctAndCustInfos) {
	                     accountIds.add(blAcctInfoInfo.getAcctId());
	                 }
	             }
	             isQuery = false;
	         }else if(!StringUtil.isBlank(queryVo.getCustName())){
	             //调用中信api根据租户名称查询租户id
	             List<BlAcctInfoInfo> blAcctInfoInfos = commonCaller.getAcctAndCustListByName(user.getTenantId(), queryVo.getCustName());
	             if(!CollectionUtil.isEmpty(blAcctInfoInfos)){
	                 for(BlAcctInfoInfo blAcctInfoInfo:blAcctInfoInfos){
	                 	accountIds.add(blAcctInfoInfo.getAcctId());
	                 }
	             }
	             isQuery = false;
	         }
            if(!CollectionUtil.isEmpty(accountIds)){
            	queryParam.setExtCustId(accountIds);
            }
    		
    		String strPageNo=(null==request.getParameter(CiticWebConstants.PAGENO))?"1":request.getParameter(CiticWebConstants.PAGENO);
	    	String strPageSize=(null==request.getParameter(CiticWebConstants.PAGESIZE))?"10":request.getParameter(CiticWebConstants.PAGESIZE);
	    	queryParam.setPageNo(Integer.parseInt(strPageNo));
	    	queryParam.setPageSize(Integer.parseInt(strPageSize));
    		
	    	 if(isQuery || accountIds.size()>0){
	    		LOGGER.info("信控查询入參"+JSONArray.fromObject(queryParam).toString());
	 	    	QueryPolicyResponse response = omcRuleSV.queryPolicy(queryParam);
	 	    	if(response.getResponseHeader().isSuccess()){
	 	    		responseData = new ResponseData<PageInfo<PolicyInfoResponseVo>>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功", response.getOmcRuleInfoVOs());
	 	    	}else{
	 	    		responseData = new ResponseData<PageInfo<PolicyInfoResponseVo>>(ResponseData.AJAX_STATUS_FAILURE, "查询失败");
	 	    	}
	    	 }else{
	    		 responseData = new ResponseData<PageInfo<PolicyInfoResponseVo>>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功");
	    	 }
    		
    	} catch (Exception e) {
			responseData = new ResponseData<PageInfo<PolicyInfoResponseVo>>(ResponseData.AJAX_STATUS_FAILURE,"获取信息异常");
			LOGGER.error("获取信息出错：", e);
		}
        return responseData;
    }
    
    @RequestMapping("/getPolicy")
    public PolicyInfoVo getPolicy(String custId, HttpServletRequest request){
    	PolicyInfoVo vo = new PolicyInfoVo();
    	try {
	    	HttpSession session = request.getSession();
			CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
	
			IOmcPolicySV omcRuleSV = DubboConsumerFactory.getService(IOmcPolicySV.class);
			GetPolicyInfoParam param = new GetPolicyInfoParam();
			param.setTenantId(user.getTenantId());
			param.setCustId(custId);
			param.setTradeSeq(user.getTenantId() + DateUtil.getDateString(DateUtil.yyyyMMddHHmmssSSS) + UUID.randomUUID());
			GetPolicyResponse response = omcRuleSV.getPolicy(param);
			
			if(response.getResponseHeader().isSuccess()){
				PolicyInfoResponseVo responseVo = response.getResponseVo();
				vo.setExtCustId(responseVo.getExtcustId());
				vo.setPolicyId(responseVo.getPolicyid());
				vo.setPolicyName(responseVo.getPolicyName());
				vo.setPressPayment(String.valueOf(AmountUtil.changeLiToYuan(responseVo.getWarningCeil())));
				vo.setDescription(responseVo.getPolicydescribe());
			}
    	} catch (Exception e) {
			LOGGER.error("获取信息出错：", e);
		}
        return vo;
    	
    }
    
    /**
     * 新增修改信控策略
     * @param vo
     * @param request
     * @return
     */
    @RequestMapping("/savePolicy")
	public ResponseData<Object> savePolicy(PolicyAddVo vo, HttpServletRequest request){
		 
		ResponseData<Object> responseData = null;
		boolean temp = true;
		String msg = "新增";
		try {
			HttpSession session = request.getSession();
			CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
			
			IOmcPolicySV omcRuleSV = DubboConsumerFactory.getService(IOmcPolicySV.class);
			OmcPolicyInfoVO saveVo = new OmcPolicyInfoVO();
			saveVo.setCustIds(vo.getCustIds());
			saveVo.setRuleName(vo.getRuleName());
			saveVo.setPressPayment(AmountUtil.changeYuanToLi(Double.valueOf(vo.getPressPayment())));
			saveVo.setTradeSeq(TradeSeqUtil.newTradeSeq(user.getTenantId()));
			saveVo.setDescription(vo.getDescription());
			saveVo.setTenantId(user.getTenantId());
			if(!StringUtil.isBlank(vo.getRuleId())){
				temp = false;
				msg = "修改";
				saveVo.setCustIds(vo.getCustIds());
			}
//			else{
//				String custId = vo.getCustIds().get(0);
//				List<BlAcctInfoInfo> acctAndCustInfos = commonCaller.getAcctAndCustInfo(user.getTenantId(), custId);
//				if(acctAndCustInfos==null || acctAndCustInfos.size()==0){
//					responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_FAILURE, msg+"失败,未查询到客户id");
//					return responseData;
//				}
//			}
			OmcPolicyResponse response = null;
			LOGGER.info("信控"+msg+"入參"+JSONArray.fromObject(saveVo).toString());
			if(temp){
				response = omcRuleSV.addPolicy(saveVo);
			}else{
				response = omcRuleSV.updatePolicy(saveVo);
			}
			LOGGER.info("信控"+msg+"出參"+JSONArray.fromObject(response).toString());
			if(response!=null && response.getResponseHeader()!=null && response.getResponseHeader().isSuccess()){
				if(temp && response.getFailedRule()!=null && response.getFailedRule().size()>0){
					StringBuffer errorMsg = new StringBuffer();
					for(int i=0; i<response.getFailedRule().size(); i++){
						Map<String, Object> map = (Map<String, Object>) response.getFailedRule().get(i);
						String custId = (String) map.get("custId");
						String errorMsgs = (String) map.get("errorMsg");
						errorMsg.append("客户id:"+custId+" "+errorMsgs);
						if(i<response.getFailedRule().size()-1){
							errorMsg.append(";");
						}
					}
					responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_SUCCESS, msg+"失败,"+errorMsg.toString());
				}else{
					responseData = new ResponseData<Object>(ResponseData.AJAX_STATUS_SUCCESS, msg+"成功");
				}
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
     * 删除信控策略
     * @param vo
     * @param request
     * @return
     */
    @RequestMapping("/delPolicy")
	public ResponseData<Object> delPolicy(String custId, HttpServletRequest request){
		 
		ResponseData<Object> responseData = null;
		try {
			HttpSession session = request.getSession();
			CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
			
			IOmcPolicySV omcRuleSV = DubboConsumerFactory.getService(IOmcPolicySV.class);
			OmcPolicyInfoVO infoVO = new OmcPolicyInfoVO();
			List<String> custIds = new ArrayList<>();
			custIds.add(custId);
			infoVO.setCustIds(custIds);
			infoVO.setTenantId(user.getTenantId());
			infoVO.setTradeSeq(TradeSeqUtil.newTradeSeq(user.getTenantId()));
			BaseResponse response = omcRuleSV.delPolicy(infoVO);
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
