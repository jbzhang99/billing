package com.ai.baas.amc.api.billQuery.params;

import java.io.Serializable;
import java.util.List;

public class ServiceParams  implements Serializable {

    private static final long serialVersionUID = -49894844979L;
    /**
     * 缴费号码
     */
    private String serviceId;

    /**
     * 账单明细列表
     */
    private List<SubjectDetail>subjectDetailList;
    
    public String getServiceId() {
        return serviceId;
    }
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public List<SubjectDetail> getSubjectDetailList() {
        return subjectDetailList;
    }
    public void setSubjectDetailList(List<SubjectDetail> subjectDetailList) {
        this.subjectDetailList = subjectDetailList;
    }
    
    
}
