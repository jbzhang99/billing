package com.ai.runner.center.mmp.service;

import com.ai.runner.center.mmp.dao.mapper.bo.UmsMsgTemplate;
 
public interface MmpSv { 
	String saveUmsMsgService(String param);	 
	
	String delUmsMsgService(String param);
	
	String getUmsMsgService(String param);
	
	String saveUmsMsgTemplate(String param);	
	
	String saveUmsMsgTemplate(UmsMsgTemplate umsMsgTemplate);    
	
	String delUmsMsgTemplate(String param);
	
	String getUmsMsgTemplate(String param);
	
	String getOneServiceByServiceId(String serviceIdJson);
	
	String getOneTemplateBySequenceId(String sequenceIdJson);
	
	boolean existTemplateByServiceId(String serviceIdJson);
}
