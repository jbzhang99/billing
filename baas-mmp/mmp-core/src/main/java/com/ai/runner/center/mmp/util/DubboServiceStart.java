package com.ai.runner.center.mmp.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 缓存服务 2015-03-12
 */
public final class DubboServiceStart {
	private DubboServiceStart() {

	}

	private static final Logger LOG = LogManager.getLogger(DubboServiceStart.class.getName());
	private static final long NSLEEP = 3000L;
	@SuppressWarnings("resource")
	private static void startDubboService() {
		
		LOG.info("开始启动 PaaS Dubbo 服务---------------------------");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath*:context/applicationContext-dubbo-service-provider.xml",
						"classpath*:context/applicationContext-mybatis.xml" });
		context.registerShutdownHook();
		context.start();
		LOG.info("PaaS Dubbo 服务启动完毕---------------------------");
		while (true) {
			try {
				Thread.currentThread();
				Thread.sleep(NSLEEP);
			} catch (InterruptedException e) {
				LOG.error("PaaS Dubbo 服务启动失败:" + e);
			}
		}
	}

	public static void main(String[] args) {
		startDubboService();
	}
}
