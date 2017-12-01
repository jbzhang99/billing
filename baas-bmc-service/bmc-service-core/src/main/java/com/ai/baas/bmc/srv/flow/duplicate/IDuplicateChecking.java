package com.ai.baas.bmc.srv.flow.duplicate;

import java.util.Map;

import com.ai.baas.bmc.srv.failbill.BusinessException;

public interface IDuplicateChecking {
	
	/**
	 * 查重数据
	 * @param aData
	 * @return 
	 *   false:查重失败有重复数据
	 */
	boolean checkData(Map<String,String> data) throws BusinessException;
}
