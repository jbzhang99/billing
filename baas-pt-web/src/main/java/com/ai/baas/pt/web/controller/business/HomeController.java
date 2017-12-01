package com.ai.baas.pt.web.controller.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ai.baas.pt.web.constants.HomePageConstants;
import com.ai.baas.pt.web.model.ConsultDataVo;
import com.ai.net.xss.util.StringUtil;
import com.ai.opt.sdk.components.ccs.CCSClientFactory;
import com.ai.opt.sdk.components.mail.EmailFactory;
import com.ai.opt.sdk.components.mail.EmailTemplateUtil;
import com.ai.opt.sdk.components.mcs.MCSClientFactory;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sdk.web.model.ResponseData;
import com.ai.opt.uac.api.account.interfaces.IIndustryManageSV;
import com.ai.opt.uac.api.account.param.IndustryQueryResponse;
import com.ai.paas.ipaas.ccs.IConfigClient;
import com.ai.paas.ipaas.ccs.constants.ConfigException;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.ai.paas.ipaas.util.JSonUtil;

/**
 * 首页
 * 
 * @author gucl
 */
@RestController
public class HomeController {

	private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping("/home")
	public ModelAndView toList(HttpServletRequest request) {
		Map<String, String> model = new HashMap<String, String>();
		try {
			IIndustryManageSV iIndustryManageSV = DubboConsumerFactory.getService("iIndustryManageSV");
			List<IndustryQueryResponse> industryList = iIndustryManageSV.queryIndustryList();
			if(industryList != null && industryList.size()>0){
				model.put("industryList", JSonUtil.toJSon(industryList));
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("查询企业类型失败", e);
		}
		ModelAndView view = new ModelAndView("jsp/home",model);
		/*
		 * //运营控制台 String baas_op_web_url="#"; IConfigCenterClient
		 * ccs=ConfigCenterFactory.getConfigCenterClient();
		 * if(ccs.exists(BaaSPTConstants.URLConstant.BAAS_OP_INDEX_URL_KEY)){
		 * baas_op_web_url
		 * =ccs.get(BaaSPTConstants.URLConstant.BAAS_OP_INDEX_URL_KEY); }
		 * request.setAttribute("baas_op_web_url", baas_op_web_url);
		 * 
		 * //注册 String
		 * baas_uac_reg_url=SSOClientUtil.getCasServerUrlPrefixRuntime
		 * (request)+"/reg/toRegister"; //账户中心 String
		 * baas_uac_center_url=SSOClientUtil
		 * .getCasServerUrlPrefixRuntime(request
		 * )+"/center/baseInfo/getAccountInfo";
		 * 
		 * request.setAttribute("baas_uac_reg_url", baas_uac_reg_url);
		 * request.setAttribute("baas_uac_center_url", baas_uac_center_url);
		 */

		return view;
	}

	@RequestMapping("/consult/listIndutry")
	@ResponseBody
	public List<IndustryQueryResponse> getAllIndutry() {
		IIndustryManageSV iIndustryManageSV = DubboConsumerFactory.getService("iIndustryManageSV");
		return iIndustryManageSV.queryIndustryList();
	}

	@RequestMapping("/consult/sendEmail")
	public ResponseData<String> sendEmail(HttpServletRequest request, ConsultDataVo consultDataVo) throws ConfigException {
		String ip = getIp(request);
		boolean isOk = checkIPSendEmailCount(ip);
		if (isOk) {
			String errorMsg = checkEmailData(consultDataVo);
			if(!StringUtil.isBlank(errorMsg)){
				return new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, errorMsg);
			}
			String phone = consultDataVo.getPhone();
			String email = consultDataVo.getEmail();
			String tenantName = consultDataVo.getTenantName();
			String tenantType = consultDataVo.getTenantType();
			String message = consultDataVo.getMessage();
			String[] emailData = new String[] { phone, email, tenantName, tenantType, message };
			String htmlcontext = EmailTemplateUtil.buildHtmlTextFromTemplate(HomePageConstants.SendEmail.TEMPLATE_EMAIL_URL, emailData);
			try {
				String toemail = CCSClientFactory.getDefaultConfigClient().get(HomePageConstants.SendEmail.RECEIVE_EMAIL_KEY);
				String date = DateUtil.getDateString("yy/MM/dd HH:mm");
				String emailSubject = "产品咨询(" + date + ")";
				EmailFactory.SendEmail(new String[] { toemail }, null, emailSubject, htmlcontext);
				return new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS, "感谢您的咨询，我们会尽快给您答复");
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("发送邮件失败：", e);
				return new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, "咨询内容提交失败，请稍后再试");
			}
		} else {
			return new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, "操作过于频繁，请稍后提交");
		}
	}
	
	/**
	 * 检查邮件数据
	 * @param consultDataVo
	 * @return
	 */
	private String checkEmailData(ConsultDataVo consultDataVo){
		String errorMsg = "";
		String phone = consultDataVo.getPhone();
		if(!StringUtil.isBlank(phone)){
			if(!phone.startsWith("1")||phone.length()!=11){
				errorMsg +="手机格式不正确;";
			}
		}
		String email = consultDataVo.getEmail();
		if(!StringUtil.isBlank(email)){
			String emailRegex = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.(\\w+([-.]\\w+)*){2,}$";
			if(!Pattern.matches(emailRegex,email)){
				errorMsg +="邮箱格式不正确;";
			}
		}
		String message = consultDataVo.getMessage();
		if(StringUtil.isBlank(message)){
			errorMsg +="咨询内容不能为空;";
		}
		return errorMsg;
	}

	/**
	 * 检查ip发送邮箱验证码次数是否超限
	 * 
	 * @param namespace
	 * @param key
	 * @return
	 * @throws ConfigException 
	 */
	public static boolean checkIPSendEmailCount(String ip) throws ConfigException {
		String namespace = HomePageConstants.SendEmail.CACHE_NAMESPACE;
		String key = ip + HomePageConstants.SendEmail.SEND_EMAIL_NO;
		ICacheClient cacheClient = MCSClientFactory.getCacheClient(namespace);
		String countStr = cacheClient.get(key);
		IConfigClient configClient = CCSClientFactory.getDefaultConfigClient();
		// 限制时间
		String overTime = configClient.get(HomePageConstants.SendEmail.IP_SEND_OVERTIME_KEY);
		if (!StringUtil.isBlank(countStr)) {
			String maxNoStr = configClient.get(HomePageConstants.SendEmail.IP_SEND_MAX_NO_KEY);
			int maxNo = Integer.valueOf(maxNoStr);
			int count = Integer.valueOf(countStr);
			count++;
			if (count > maxNo) {
				return false;
			} else {
				cacheClient.setex(key, Integer.valueOf(overTime), Integer.toString(count));
				return true;
			}
		} else {
			cacheClient.setex(key, Integer.valueOf(overTime), "1");
			return true;
		}
	}

	private String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (!StringUtil.isBlank(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (!StringUtil.isBlank(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}
}
