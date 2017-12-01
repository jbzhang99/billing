package com.ai.opt.sys.api.gnfunc.param;

import java.io.Serializable;
import java.sql.Timestamp;

public class GnFuncData implements Serializable {
	private static final long serialVersionUID = 1L;

	private String tenantId;
	/**
	 * 功能ID
	 */
	private long funcId;

	/**
	 * 功能名称
	 */
	private String funcName;

	/**
	 * 功能url
	 */
	private String funcUrl;

	/**
	 * 功能类型
	 */
	private String funcType;

	/**
	 * 排序
	 */
	private long funcSort;

	/**
	 * 上级功能ID
	 */
	private long parentFuncId;

	/**
	 * 功能服务css样式
	 */
	private String funcCssClass;

	/**
	 * 图标
	 */
	private String funcPic;

	/**
	 * 生效时间
	 */
	private Timestamp activeTime;

	/**
	 * 失效时间
	 */
	private Timestamp inactiveTime;

	/**
	 * 创建时间
	 */
	private Timestamp createTime;

	/**
	 * 修改时间
	 */
	private Timestamp updateTime;

	/**
	 * 創建人ID
	 */
	private long createAccountId;

	/**
	 * 修改人ID
	 */
	private long updateAccountId;

	public long getFuncId() {
		return funcId;
	}

	public void setFuncId(long funcId) {
		this.funcId = funcId;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getFuncUrl() {
		return funcUrl;
	}

	public void setFuncUrl(String funcUrl) {
		this.funcUrl = funcUrl;
	}

	public String getFuncType() {
		return funcType;
	}

	public void setFuncType(String funcType) {
		this.funcType = funcType;
	}

	public long getFuncSort() {
		return funcSort;
	}

	public void setFuncSort(long funcSort) {
		this.funcSort = funcSort;
	}

	public long getParentFuncId() {
		return parentFuncId;
	}

	public void setParentFuncId(long parentFuncId) {
		this.parentFuncId = parentFuncId;
	}

	public String getFuncCssClass() {
		return funcCssClass;
	}

	public void setFuncCssClass(String funcCssClass) {
		this.funcCssClass = funcCssClass;
	}

	public String getFuncPic() {
		return funcPic;
	}

	public void setFuncPic(String funcPic) {
		this.funcPic = funcPic;
	}

	public long getCreateAccountId() {
		return createAccountId;
	}

	public void setCreateAccountId(long createAccountId) {
		this.createAccountId = createAccountId;
	}

	public long getUpdateAccountId() {
		return updateAccountId;
	}

	public void setUpdateAccountId(long updateAccountId) {
		this.updateAccountId = updateAccountId;
	}

	public Timestamp getCreateTime() {
		return createTime == null ? null : (Timestamp) createTime.clone();
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = (createTime == null ? null : (Timestamp) createTime.clone());
	}

	public Timestamp getUpdateTime() {
		return updateTime == null ? null : (Timestamp) updateTime.clone();
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = (updateTime == null ? null : (Timestamp) updateTime.clone());
	}

	public Timestamp getActiveTime() {
		return activeTime == null ? null : (Timestamp) activeTime.clone();
	}

	public void setActiveTime(Timestamp activeTime) {
		this.activeTime = (activeTime == null ? null : (Timestamp) activeTime.clone());
	}

	public Timestamp getInactiveTime() {
		return inactiveTime == null ? null : (Timestamp) inactiveTime.clone();
	}

	public void setInactiveTime(Timestamp inactiveTime) {
		this.inactiveTime = (inactiveTime == null ? null : (Timestamp) inactiveTime.clone());
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
}
