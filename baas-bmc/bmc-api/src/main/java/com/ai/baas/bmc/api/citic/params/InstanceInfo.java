package com.ai.baas.bmc.api.citic.params;

import java.sql.Timestamp;

import com.ai.opt.base.vo.BaseInfo;

/**
 * 修改服务实例接口入参
 * @author gaogang
 *
 */
public class InstanceInfo extends BaseInfo{

	
	private static final long serialVersionUID = 1L;
	/**
	 * 实例id
	 */
	private String instanceId;
	/**
	 * 失效时间
	 */
	private Timestamp inactiveTime;

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public Timestamp getInactiveTime() {
		return inactiveTime;
	}

	public void setInactiveTime(Timestamp inactiveTime) {
		this.inactiveTime = inactiveTime;
	}
	
	
}
