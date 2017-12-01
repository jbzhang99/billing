package com.ai.runner.center.ctp.rtm.core.producer;

public interface IProducer {

	void init();
	
	void sendMessage(String message);
	
	void close();
	
}
