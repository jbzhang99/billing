package com.ai.baas.amc.api.proferentialbill.params;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.ai.baas.amc.api.proferentialbill.interfaces.IProferProductManageSV;
import com.ai.opt.base.vo.BaseInfo;
/**
 * 关联账单科目修改入参
 *
 * Date: 2016年3月30日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * @author gaogang
 */
public class RelatedBillInfoVO extends BaseInfo {
	
	private static final long serialVersionUID = 1L;
	/**
     * 消息流水<br>
     * 组成：租户ID + YYMMDDHH24MISS + SSS(毫秒) + 9位序列号<br>
     * 必填<br>
     * VARCHAR(32)
     */
	@NotNull(message="消息流水号不能为空",groups={IProferProductManageSV.UpdateRelatedBill.class})
	private String tradeSeq;
	/**
	 *账单类产品Id
	 */
	@NotNull(message="产品Id不能为空",groups={IProferProductManageSV.UpdateRelatedBill.class})
	private String productId;
	
	/**
	 * 账单类产品名称
	 */
	@NotNull(message="账单产品名称不能为空",groups={IProferProductManageSV.UpdateRelatedBill.class})
	private String productName;
	
	/**
	 * 关联费用科目
	 */
	@NotNull(message="关联费用科目类型不能为空",groups={IProferProductManageSV.UpdateRelatedBill.class})
	private String CostNameList;
	/**
	 * 账单科目描述
	 */
	private String comments;
	public String getTradeSeq() {
		return tradeSeq;
	}
	public void setTradeSeq(String tradeSeq) {
		this.tradeSeq = tradeSeq;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getCostNameList() {
		return CostNameList;
	}
	public void setCostNameList(String costNameList) {
		CostNameList = costNameList;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
