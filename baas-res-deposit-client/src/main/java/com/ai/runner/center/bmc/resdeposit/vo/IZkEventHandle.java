package com.ai.runner.center.bmc.resdeposit.vo;

import org.apache.zookeeper.WatchedEvent;

/**
 * Date: 2016年5月17日 <br>
 * @author zhoushanbin
 * Copyright (c) 2016 asiainfo.com <br>
 */
public interface IZkEventHandle {

	void handle(WatchedEvent event);
}
