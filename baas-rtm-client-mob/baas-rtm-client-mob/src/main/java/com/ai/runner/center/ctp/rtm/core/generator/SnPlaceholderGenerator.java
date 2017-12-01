package com.ai.runner.center.ctp.rtm.core.generator;

public class SnPlaceholderGenerator implements ISnGenerator {

	private PsnPlaceholderGenerator snGenerator;
	
	public SnPlaceholderGenerator(String rule){
		snGenerator = new PsnPlaceholderGenerator(rule);
	}
	
	@Override
	public String getNext(Object data) {
		return snGenerator.getNext(data);
	}

}
