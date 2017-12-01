package com.ai.runner.center.bmc.resdeposit.vo;

/**
 * Date: 2016年5月13日 <br>
 * @author zhoushanbin
 * Copyright (c) 2016 asiainfo.com <br>
 */
public interface ITask {
	
	void prepare();
	
	void execute();
	
	void destory();
	
}
