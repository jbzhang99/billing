package com.ai.baas.omc.api.avoidscout.params;

import javax.validation.constraints.NotNull;
import com.ai.opt.base.vo.BaseInfo;

/**
 * 信控免催停管理入参
 *
 * Date: 2017年3月2日 <br>
 * Copyright (c) 2017 asiainfo.com <br>
 * 
 * @author wangjing19
 */
public class OmcAvoidScoutInfoVO extends BaseInfo {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 消息流水<br>
	 * 组成：租户ID + YYMMDDHH24MISS + SSS(毫秒) + 9位序列号<br>
	 * 非必填<br>
	 * VARCHAR(32)
	 */
	@NotNull(message = "交易流水号不能为空")
	private String tradeSeq;

	/**
	 * 租户ID<br>
     * 必填<br>
	 * VARCHAR(32)
	 */
	@NotNull(message = "租户ID不能为空")
	private String tenantId;
	
	/**
	 * 外部客户ID<br>
     * 必填<br>
	 * VARCHAR(32)
	 */
	@NotNull(message = "外部客户ID不能为空")
	private String extCustId;
	
	/**
	 * 特殊处理类型<br>
	 * 取值范围：STOP ：停;URGE ：催缴;STOPANDURGE 停和催<br>
	 * 必填<br>
	 * VARCHAR(32)
	 */
	@NotNull(message = "特殊处理类型不能为空")
	private String specialType;
	
	/**
	 * 生效日期<br>
	 * 格式：YYYYMMDDHH24MISS<br>
	 * VARCHAR(14)
	 */
	private String activeTime;
	
	/**
	 * 失效日期<br>
	 * 格式：YYYYMMDDHH24MISS<br>
	 * VARCHAR(14)
	 */
	private String inactiveTime;

	public String getTradeSeq() {
		return tradeSeq;
	}

	public void setTradeSeq(String tradeSeq) {
		this.tradeSeq = tradeSeq;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getExtCustId() {
		return extCustId;
	}

	public void setExtCustId(String extCustId) {
		this.extCustId = extCustId;
	}

	public String getSpecialType() {
		return specialType;
	}

	public void setSpecialType(String specialType) {
		this.specialType = specialType;
	}

	public String getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(String activeTime) {
		this.activeTime = activeTime;
	}

	public String getInactiveTime() {
		return inactiveTime;
	}

	public void setInactiveTime(String inactiveTime) {
		this.inactiveTime = inactiveTime;
	}

}
