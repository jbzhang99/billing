package com.ai.baas.amc.vo;

/**
 * 消费科目
 * Created by jackieliu on 16/4/1.
 */
public class SubjectFeeVo {
    /**
     * 科目id
     */
    private Long subjectId;
    /**
     * 消费优先级,值越大优先级越高
     */
    private Long settlePri;

    /**
     * 是否计算积分,
     * 0：不计算,1：计算
     */
    private String calScore;

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getSettlePri() {
        return settlePri;
    }

    public void setSettlePri(Long settlePri) {
        this.settlePri = settlePri;
    }

    public String getCalScore() {
        return calScore;
    }

    public void setCalScore(String calScore) {
        this.calScore = calScore;
    }
}
