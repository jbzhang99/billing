package com.ai.baas.amc.service.atom.interfaces;

import com.ai.baas.amc.vo.UserVo;
import com.ai.opt.base.exception.BusinessException;

import java.util.List;

/**
 *
* @Description: 得到用户信息 
* @author liutong5
* @date 2016年03月30日
*
 */
public interface IUserAtomSV {

	/**
	 * 根据租户id和用户id获取用户信息
	 *
	 * @param tenantid
	 * @param id
	 * @return
	 * @throws BusinessException
     */
	UserVo selectById(String tenantid, String id) throws BusinessException;
}
