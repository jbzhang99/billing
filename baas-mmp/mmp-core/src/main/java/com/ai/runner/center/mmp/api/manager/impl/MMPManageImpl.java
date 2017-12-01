package com.ai.runner.center.mmp.api.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.runner.center.mmp.api.manager.interfaces.MMPManager;
import com.ai.runner.center.mmp.api.manager.param.SMTemplateInfoNotify;
import com.ai.runner.center.mmp.dao.mapper.bo.UmsMsgTemplate;
import com.ai.runner.center.mmp.service.MmpSv;
import com.alibaba.dubbo.config.annotation.Service;

@Service
public class MMPManageImpl implements MMPManager {
	@Autowired
	private MmpSv mmpSv = null;
	public static final String S_TENANT_ID = "\"TENANT_ID\" : \"";
	public static final String S_SYSTEM_ID = ",\"SYSTEM_ID\" : \"";
	public static final String S_TEMPLATE_ID = "\"Template_ID\" : \"";
	public static final String S_TEMPLATE_NAME = "\"Template_Name\" : \"";
	public static final String S_TEMPLATE_TEXT = "\"Template_Text\" : \"";
	public static final String S_BEGIN_TIME = "\"Begin_Time\" : \"";
	public static final String S_CLOSE_TIME = "\"Close_Time\" : \"";
	public static final String S_RETRY_TIME = "\"Retry_Time\" : \"";
	public static final String S_INSERT_TIME = "\"Insert_Time\" : \"";
	public static final String S_UPDATE_TIME = "\"Update_Time\" : \"";
	
	public static final String S_BMC_00002 = "BMC-00002"; 
	public static final String S_SPLIT = "\"";
	public static final String S_TEMPLATETEXTNOTALLOWNULL = "TemplateText不能为空";
	
	public static final Long NSEQUENCEID = 1111L;
	public static final Long NSERVICEID = 9999L;
	@Override
	public String saveUmsMsgService(String param) {
		String result = mmpSv.saveUmsMsgService(param);
		if ( "".equals(result) ) {
			result = ""; 
		}
		return result;
	}

	@Override
	public String delUmsMsgService(String param) {
		String result = mmpSv.delUmsMsgService(param);
		if ( "".equals(result) ) {
			result = ""; 
		}
		return result;
	}

	@Override
	public String getUmsMsgService(String param) {
		String result = mmpSv.getUmsMsgService(param);
		if ( "".equals(result) ) {
			result = "";
		}
		return result;
	}

	@Override
	public String saveUmsMsgTemplate(String param) {
		String result = mmpSv.saveUmsMsgTemplate(param);
		if ( "".equals(result) ) {
			result = ""; 
		}
		return result;
	}

	@Override
	public String delUmsMsgTemplate(String param) {
		String result = mmpSv.delUmsMsgTemplate(param);
		if ( "".equals(result) ) {
			result = ""; 
		}
		return result;
	}

	@Override
	public String getUmsMsgTemplate(String param) {
		String result = mmpSv.getUmsMsgTemplate(param);
		if ( "".equals(result) ) {
			result = ""; 
		}
		return result;
	}

	@Override
	public String getOneServiceByServiceId(String serviceId) {
		String result = mmpSv.getOneServiceByServiceId(serviceId);
		if ( "".equals(result) ) {
			result = ""; 
		}
		return result;
	}

	@Override
	public String getOneTemplateBySequenceId(String sequenceIdJson) {
		String result = mmpSv.getOneTemplateBySequenceId(sequenceIdJson);
		if ( "".equals(result) ) {
			result = ""; 
		}
		return result;
	}

	@Override
	public boolean existTemplateByServiceId(String serviceId) {
		return mmpSv.existTemplateByServiceId(serviceId);
	}

	@Override
	public String saveUmsMsgTemplate(SMTemplateInfoNotify paramInfo) throws BusinessException {
		String json = "{";

		UmsMsgTemplate umsMsgTemplate = new UmsMsgTemplate();
		// 保留 private Long sequenceId; Long serviceId
		umsMsgTemplate.setSequenceId(NSEQUENCEID);
		umsMsgTemplate.setServiceId(NSERVICEID);

		// 判断必选项是否为空
		if (!StringUtil.isBlank(paramInfo.getTenantId())) {
			umsMsgTemplate.setTenantId(paramInfo.getTenantId());
			json += S_TENANT_ID + paramInfo.getTenantId() + S_SPLIT;
		} else {
			throw new BusinessException(S_BMC_00002, "TenantId不能为空");
		}

		if (!StringUtil.isBlank(paramInfo.getSystemId())) {
			umsMsgTemplate.setSystemId(paramInfo.getSystemId());
			json += S_SYSTEM_ID + paramInfo.getSystemId() + S_SPLIT;
		} else {
			throw new BusinessException(S_BMC_00002, "SystemId不能为空");
		}

		if (!StringUtil.isBlank(paramInfo.getMsgSeq())) {
			umsMsgTemplate.setTemplateId(Long.parseLong(paramInfo.getMsgSeq()));
			json += S_TEMPLATE_ID + paramInfo.getMsgSeq() + S_SPLIT;
		} else {
			throw new BusinessException(S_BMC_00002, "TemplateId不能为空");
		}

		if (!StringUtil.isBlank(paramInfo.getTemplateName())) {
			umsMsgTemplate.setTemplateName(paramInfo.getTemplateName());
			json += S_TEMPLATE_NAME + paramInfo.getTemplateName() + S_SPLIT;
		} else {
			throw new BusinessException(S_BMC_00002, "TemplateName不能为空");
		}

		if (!StringUtil.isBlank(paramInfo.getTemplateText())) {
			umsMsgTemplate.setTemplateText(paramInfo.getTemplateText());
			json += S_TEMPLATE_TEXT + paramInfo.getTemplateText() + S_SPLIT;
		} else {
			throw new BusinessException(S_BMC_00002, S_TEMPLATETEXTNOTALLOWNULL);
		}

		// 非必选

		if (!StringUtil.isBlank(paramInfo.getBeginTime())) {
			umsMsgTemplate.setSbeginTime(paramInfo.getBeginTime());
			json += S_BEGIN_TIME + paramInfo.getBeginTime() + S_SPLIT;
		}

		if (!StringUtil.isBlank(paramInfo.getCloseTime())) {
			umsMsgTemplate.setScloseTime(paramInfo.getCloseTime());
			json += S_CLOSE_TIME + paramInfo.getCloseTime() + S_SPLIT;
		}

		if (!StringUtil.isBlank(Integer.toString(paramInfo.getRetryTimes()))) {
			umsMsgTemplate.setRetryTimes(paramInfo.getRetryTimes());
			json += S_RETRY_TIME + paramInfo.getRetryTimes() + S_SPLIT;
		}

		//保留 Timestamp timestamp 获取当前时间戳 new Timestamp(System.currentTimeMillis());

		umsMsgTemplate.setInsertTime(DateUtil.getSysDate());
		json += S_INSERT_TIME + DateUtil.getDateString(DateUtil.getSysDate(), DateUtil.YYYYMMDDHHMMSS) + S_SPLIT;

		umsMsgTemplate.setUpdateTime(DateUtil.getSysDate());
		json += S_UPDATE_TIME + DateUtil.getDateString(DateUtil.getSysDate(), DateUtil.YYYYMMDDHHMMSS) + S_SPLIT;

		if (!"OK".equals(mmpSv.saveUmsMsgTemplate(umsMsgTemplate))) {
			throw new BusinessException(S_BMC_00002, S_TEMPLATETEXTNOTALLOWNULL);
		}

		return "MMP-000000|模板ID";
	}
}
