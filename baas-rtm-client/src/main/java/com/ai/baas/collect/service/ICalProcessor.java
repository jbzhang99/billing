package com.ai.baas.collect.service;

import com.ai.baas.collect.vo.ServiceParam;

/**
 * 
 * Copyright: Copyright (c) 2016 Asiainfo
 * 
 * @ClassName: ICalProcessor.java
 * @Description: 处理服务的接口
 *
 * @version: v1.0.0
 * @author: hanzf
 * @date: 2017年3月17日 下午2:04:26
 *
 * Modification History:
 * Date             Author          Version            Description
 *---------------------------------------------------------*
 * 2017年3月17日     hanzf           v1.0.0               创建
 */
public interface ICalProcessor {
	/**
	 * 
	 * 
	 * @Description: 处理服务
	 *
	 * @param:参数描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: hanzf
	 * @date: 2017年3月17日 下午2:05:28
	 *
	 * Modification History:
	 * Date           Author          Version            Description
	 *---------------------------------------------------------*
	 * 2017年3月17日       hanzf           v1.0.0               创建
	 */
	public int doProcessor(String szContent,ServiceParam serviceParam);
}
