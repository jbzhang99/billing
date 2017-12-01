package com.ai.baas.amc.api.billmanage.params;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.ai.baas.amc.api.billmanage.interfaces.IBillManageSV;
import com.ai.opt.base.vo.BaseInfo;
/**
 * 新建账单科目入参
 *
 * Date: 2016年3月30日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author gaogang
 */
public class BillInfoVO extends BaseInfo{

	
	private static final long serialVersionUID = 1L;
	/**
     * 消息流水<br>
     * 组成：租户ID + YYMMDDHH24MISS + SSS(毫秒) + 9位序列号<br>
     * 必填<br>
     * VARCHAR(32)
     */
	@NotNull(message="消息流水号不能为空")
	private String tradeSeq;
	/**
	 * 账单科目名称
	 */
	@NotNull(message="账单科目名称不能为空")
	private String billName;
	/**
	 * 账单科目Id
	 */
	@NotNull(message="账单科目Id不能为空",groups={IBillManageSV.UpdateBill.class})
	private String billId;
	
	/**
	 * 账单科目描述
	 */
	private String comments;

	public String getBillName() {
		return billName;
	}

	public void setBillName(String billName) {
		this.billName = billName;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getTradeSeq() {
		return tradeSeq;
	}

	public void setTradeSeq(String tradeSeq) {
		this.tradeSeq = tradeSeq;
	}
	
	
}
