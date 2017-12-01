package com.ai.baas.smc.service.busi.interfaces;

import com.ai.baas.smc.api.elementmanage.param.ElementSearchRequest;
import com.ai.baas.smc.api.elementmanage.param.ElementSearchResponseVO;
import com.ai.baas.smc.dao.mapper.bo.StlElement;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.PageInfo;

/**
 * 
 * @author zhangbc
 *
 */
public interface IElementManageBusiSV {

	   void createElement(StlElement stlElement);
	   
	   void deleteElement(Long elementId,String tenantId)throws BusinessException;
	   
	   
	   void updateElement(StlElement stlElement);
	   
	   StlElement searchElementById(Long elementId) throws BusinessException;
	   
	   
	   PageInfo<ElementSearchResponseVO> searchElementList(ElementSearchRequest elementSearchRequest);
	   
	   boolean checkElementCode(StlElement stlElement);
}
