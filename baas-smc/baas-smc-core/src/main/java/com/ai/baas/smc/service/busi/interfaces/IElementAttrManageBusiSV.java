package com.ai.baas.smc.service.busi.interfaces;

import com.ai.baas.smc.dao.mapper.bo.StlElementAttr;

public interface IElementAttrManageBusiSV {
	
	
	 void createElementAttr(StlElementAttr strElementAttr);
	 
	 StlElementAttr searchElementAttrByElementId(Long elementId);
	 
	 void updateElementAttr(StlElementAttr strElementAttr);
	 
}
