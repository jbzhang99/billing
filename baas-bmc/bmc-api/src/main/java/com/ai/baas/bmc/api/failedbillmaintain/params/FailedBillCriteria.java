package com.ai.baas.bmc.api.failedbillmaintain.params;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.opt.base.vo.HBasePager;

/**
 * Created by xin on 16-4-11.
 */
public class FailedBillCriteria extends BaseInfo {
    private HBasePager<FailedBill> pager;

    /**
     * 业务类型
     */
    private String serviceType;

    /**
     * 错单编码
     */
    private String errorCode;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    
    
    public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public HBasePager<FailedBill> getPager() {
        return pager;
    }

    public void setPager(HBasePager<FailedBill> pager) {
        this.pager = pager;
    }
}
