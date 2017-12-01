package com.ai.baas.collect.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SystemUtil {
	
	private final static Log logger = LogFactory.getLog(SystemUtil.class);
	
	/**
	 * 
	 * 
	 * @Description: 判断是否操作系统
	 *
	 * @param:参数描述
	 * @return：1 是  其他 否
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: hanzf
	 * @date: 2017年3月16日 上午10:49:39
	 *
	 * Modification History:
	 * Date           Author          Version            Description
	 *---------------------------------------------------------*
	 * 2017年3月16日       hanzf           v1.0.0               创建
	 */
	public static boolean  isWindow()
	{
		String OS = System.getProperty("os.name").toLowerCase(); 
		logger.debug("操作系统：" + OS);
		if(OS.contains("windows"))
			return true;
		
		return false;
	}

}
