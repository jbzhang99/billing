package com.ai.baas.bmc.api.packageassemble.impl;

import com.ai.baas.bmc.api.packageassemble.impl.executor.LoopThread;


public abstract class BaseFlow extends LoopThread {

	@Override
	public boolean init() {
		return true;
	}
	
	@Override
	public boolean unInit() {
		return true;
	}

	@Override
	public void work() {
		process();
		exitFlag = true;
	}
	
	public abstract void process();

}
