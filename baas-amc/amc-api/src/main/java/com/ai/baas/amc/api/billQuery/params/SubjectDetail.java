package com.ai.baas.amc.api.billQuery.params;

import java.io.Serializable;

public class SubjectDetail implements Serializable{
    private static final long serialVersionUID = -4989481163493979L;

    /**
     * 科目
     */
    private Long subjectId;
    /**
     * 科目名称
     */
    private String subjectName;
    /**
     * 科目原始费用
     */
    private Long subjectOrgFee;
    /**
     * 科目优惠 费用
     */
    private Long subjectDisFee;
    /**
     * 科目调整费用
     */
    private Long subjectAdjustFee;
    /**
     * 科目费用
     */
    private Long subjectFee;
    

    public Long getSubjectId() {
        return subjectId;
    }
    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }
    public String getSubjectName() {
        return subjectName;
    }
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    public Long getSubjectOrgFee() {
        return subjectOrgFee;
    }
    public void setSubjectOrgFee(Long subjectOrgFee) {
        this.subjectOrgFee = subjectOrgFee;
    }
    public Long getSubjectDisFee() {
        return subjectDisFee;
    }
    public void setSubjectDisFee(Long subjectDisFee) {
        this.subjectDisFee = subjectDisFee;
    }
    public Long getSubjectAdjustFee() {
        return subjectAdjustFee;
    }
    public void setSubjectAdjustFee(Long subjectAdjustFee) {
        this.subjectAdjustFee = subjectAdjustFee;
    }
    public Long getSubjectFee() {
        return subjectFee;
    }
    public void setSubjectFee(Long subjectFee) {
        this.subjectFee = subjectFee;
    }
   
    
}
