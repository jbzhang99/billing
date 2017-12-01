package com.ai.baas.batch.client.basethread;

public abstract class LoopThread extends Thread {

	//是否退出标志
	public boolean exitFlag = false;
	//工作间隔  
	public int interval=0;
	
	//线程名称
	public String threadName;
	

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public boolean isExitFlag() {
		return exitFlag;
	}

	public void setExitFlag(boolean exitFlag) {
		this.exitFlag = exitFlag;
	}

	public abstract boolean init();

	public abstract boolean unInit();

	
}

