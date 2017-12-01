package com.ai.runner.center.bmc.deduct.dto;

/**
 * Date: 2016年4月19日 <br>
 * 
 * @author zhoushanbin 
 * Copyright (c) 2016 asiainfo.com <br>
 */
public class ResDeductPacket extends IPacket {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6880422694701739244L;
	/**
	 * 事件ID，即详单中的序列号sn
	 */
	private String event_id;
	/**
	 * 来源, 填写 resource 资源入账 bmc 计费
	 */
	private String source_type;
	/**
	 * acct:账户；cust:客户；subs:用户
	 */
	private String owner_type;
	/**
	 * 属主ID
	 */
	private String owner_id;
	/**
	 * 事件类型，填写60
	 * 
	 * CASH主业务（按资料信控）
	 * 
	 * VOICE 语音;
	 * 
	 * SMS 短信;
	 * 
	 * DATA 数据;
	 */
	private String event_type;
	/**
	 * 数量
	 */
	private String amount;
	/**
	 * 数量的增减属性，取值：PLUS:导致余额增加的，如缴费导致的，MINUS：导致余额减少的，如业务使用导致的。
	 */
	private String amount_mark;
	/**
	 * 数量的类型：取值范围：
	 * 
	 * VOICE:语音资源，单位：分钟
	 * 
	 * DATA:流量资源，单位：k
	 * 
	 * SM:短信资源，单位：条
	 * 
	 * VC:虚拟币资源,virtual currency，单位：个
	 * 
	 * BOOK:资金账本，单位：厘
	 * 
	 * PC: power_consumption,电量资源，单位：千瓦时
	 */
	private String amount_type;
	
	private String expanded_info;

	public String getEvent_id() {
		return event_id;
	}

	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}

	public String getSource_type() {
		return source_type;
	}

	public void setSource_type(String source_type) {
		this.source_type = source_type;
	}

	public String getOwner_type() {
		return owner_type;
	}

	public void setOwner_type(String owner_type) {
		this.owner_type = owner_type;
	}

	public String getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
	}

	public String getEvent_type() {
		return event_type;
	}

	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAmount_mark() {
		return amount_mark;
	}

	public void setAmount_mark(String amount_mark) {
		this.amount_mark = amount_mark;
	}

	public String getAmount_type() {
		return amount_type;
	}

	public void setAmount_type(String amount_type) {
		this.amount_type = amount_type;
	}

	public String getExpanded_info() {
		return expanded_info;
	}

	public void setExpanded_info(String expanded_info) {
		this.expanded_info = expanded_info;
	}
	
}
