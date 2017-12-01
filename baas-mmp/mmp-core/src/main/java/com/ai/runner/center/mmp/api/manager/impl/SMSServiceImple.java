package com.ai.runner.center.mmp.api.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.runner.center.mmp.api.manager.interfaces.SMSServices;
import com.ai.runner.center.mmp.api.manager.param.SMData;
import com.ai.runner.center.mmp.api.manager.param.SMDataInfoNotify;
import com.ai.runner.center.mmp.service.SMSSv;
import com.ai.runner.center.mmp.vo.SMSInputVo;
import com.alibaba.dubbo.config.annotation.Service;

@Service
@Component
public class SMSServiceImple implements SMSServices {
	
	@Autowired
	private SMSSv sMSSvImpl;
	public static final String S_BMC_00002 = "BMC-00002";
	public static final String S_BMC_0002 = "BMC-0002"; 
	/**
     * dataInput()
     */
	@Override
	public String dataInput(String param) throws BusinessException { 
		String resultCode = sMSSvImpl.dataInput(param);
		if ("".equals(resultCode)) {
			resultCode = "";
		}
		return resultCode;
	}

	/**
	 * dataInput()
	 */
	
	@Override 
	public String dataInput(SMDataInfoNotify paramInfo) throws BusinessException {
	    
	    String json = "{";
	    SMSInputVo asMSInputVo = new SMSInputVo();
	    
	    //判断必选项是否为空 
        if(!StringUtil.isBlank(paramInfo.getTenantId())){
            asMSInputVo.setTenementid(paramInfo.getTenantId()); 
            json += "\"TENANT_ID\" : \"" + paramInfo.getTenantId() + "\"";
        } else {
            throw new BusinessException(S_BMC_00002, "TenantId不能为空");
        }	    
	    
	    if(!StringUtil.isBlank(paramInfo.getSystemId())){
	        asMSInputVo.setSystemid(paramInfo.getSystemId());  
	        json += ",\"SYSTEM_ID\" : \"" + paramInfo.getSystemId() + "\"";
        } else {
            throw new BusinessException(S_BMC_00002, "SystemId不能为空");
        }

        if(!StringUtil.isBlank(paramInfo.getMsgSeq())){
            asMSInputVo.setVerifyid(paramInfo.getMsgSeq()); 
            json += ",\"VERIFY_ID\" : \"" + paramInfo.getMsgSeq() + "\"";
        } else {
            throw new BusinessException(S_BMC_00002, "MsgSeq不能为空");
        }

        List<SMData> dataList = paramInfo.getDataList();
       
        for (SMData d : dataList) {
          
            if (!StringUtil.isBlank(d.getTemplateId())) {
                asMSInputVo.setTemplateid(d.getTemplateId());
                json += ",\"TEMPLATE_ID\" : \"" + d.getTemplateId() + "\"";
            } else {
                throw new  BusinessException(S_BMC_0002, "TemplateId不能为空");
            }

            if (!StringUtil.isBlank(d.getPhone())) {
                asMSInputVo.setPhone(d.getPhone());
                json += ",\"PHONE\" : \"" + d.getPhone() + "\"";
            } else {
                throw new  BusinessException(S_BMC_0002, "Phone不能为空");
            }
       
            //非必选项传参 
            if (!StringUtil.isBlank(d.getServiceType())) {
                asMSInputVo.setServicetype(Integer.parseInt(d.getServiceType()));
                json += ",\"SERVICE_TYPE\" : \"" + d.getServiceType() + "\"";
            }
                
            if (!StringUtil.isBlank(d.getGsmContent())) {
                asMSInputVo.setGsmcontent(d.getGsmContent());  
                json += ",\"GSM_CONTENT\" : \"" + d.getGsmContent() + "\"";
            }
            json += "}";
            
        	    /**
            private String priority;
        	     */
            asMSInputVo.setPriority("0");
            
        	sMSSvImpl.dataInput(asMSInputVo);
    	}
//     保留    -----info("向数据库表bl_custinfo中插入" || blCustinfo -----
//      保留   -----if(resultCode. === ("BMC-000000")) -----{-----
//      保留      -----IdshmSV aIdshmSV 启动 DubboConsumerFactory.getService("IdshmSV", IdshmSV.class)-----
//      保留      -----log.info("调用内存接口IdshmSV")-----
//      保留      -----aIdshmSV.initLoader("bl_custinfo", json)-----   
//     保留       -----log.info("向内存中的bl_custinfo中插入"+json)-----
//     保留   -----}-----
        return "MMP-000000";
    }
}