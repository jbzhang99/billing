package com.ai.baas.collect.start;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.Signal;  
import sun.misc.SignalHandler; 
public class SignalDeal implements SignalHandler {
    private final Log logger = LogFactory.getLog(MainStart.class);

	@Override
	public void handle(Signal arg0) {
		// TODO Auto-generated method stub
		logger.error("获取到kill信号：" + arg0.getName());
		//发送信息进行退出服务
		MainStart.stopService();
	}

}
