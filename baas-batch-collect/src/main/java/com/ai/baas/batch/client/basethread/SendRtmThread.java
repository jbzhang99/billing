package com.ai.baas.batch.client.basethread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.batch.client.core.AmountStart;
import com.ai.baas.batch.client.service.impl.EipCalProcessorImpl;
import com.ai.baas.batch.client.util.HttpClientUtil;

public class SendRtmThread extends LoopThread {
	private static final Logger logger = LoggerFactory
			.getLogger(SendRtmThread.class);
	// rtm 地址
	String rtmUrl = "";

	@Override
	public boolean init() {
		// TODO Auto-generated method stub
		if (rtmUrl == "" || rtmUrl.length() < 1)
			return false;
		return true;
	}

	@Override
	public boolean unInit() {
		// TODO Auto-generated method stub
		return true;
	}

	public void run() {
		logger.error(this.threadName + "开始运行");
		if (!init()) {
			logger.error(this.threadName + "初始化失败！");
		}
		// 开始运行
		while (true) {
			String rtmMsg = AmountStart.qRtmMsg.poll();

			// 为空且需要退出了 就跳出循环
			if (rtmMsg == null && this.exitFlag) {
				logger.error(this.threadName + "队列为空，且收到退出信号，退出！");
				break;
			}
			if (rtmMsg == null) {
				try {
					Thread.sleep(this.interval * 1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				continue;
			}
			//发送rtm
			HttpClientUtil.send(rtmUrl, rtmMsg);
			
		}
		logger.error(this.threadName + "执行完成！");

	}

	public String getRtmUrl() {
		return rtmUrl;
	}

	public void setRtmUrl(String rtmUrl) {
		this.rtmUrl = rtmUrl;
	}

}
