package com.ai.baas.batch.client.util;

import com.ai.baas.batch.client.core.AmountStart;

/**
 * 
 * Copyright: Copyright (c) 2016 Asiainfo
 * 
 * @ClassName: MsgUtil.java
 * @Description: 处理消息队列
 *
 * @version: v1.0.0
 * @author: hanzf
 * @date: 2017年4月7日 上午10:49:49
 *
 * Modification History:
 * Date             Author          Version            Description
 *---------------------------------------------------------*
 * 2017年4月7日     hanzf           v1.0.0               创建
 */
public class MsgUtil {
	
	/**
	 * 
	 * 
	 * @Description: 该函数的功能描述
	 *
	 * @param:参数描述
	 * @return：直接写入main类中的rtm消息队列
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: hanzf
	 * @date: 2017年4月7日 上午10:53:33
	 *
	 * Modification History:
	 * Date           Author          Version            Description
	 *---------------------------------------------------------*
	 * 2017年4月7日       hanzf           v1.0.0               创建
	 */
	public static boolean  putrtmMsg(String content)
	{
		boolean result = true;
		try {
			AmountStart.qRtmMsg.put(content);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			result = false;
			e.printStackTrace();
		}
		return result;
		
	}

}
