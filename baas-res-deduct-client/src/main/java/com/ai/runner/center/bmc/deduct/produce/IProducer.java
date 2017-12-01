package com.ai.runner.center.bmc.deduct.produce;

public interface IProducer {

	void init();
	
	void sendMessage(String message);
	
	void close();
	
}
