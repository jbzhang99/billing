package com.ai.baas.amc.vo;

import java.sql.Timestamp;

/**
 * 科目查询结果 <br>
 *
 * Date: 2015年10月28日 <br>
 * Copyright (c) 2015 asiainfo.com <br>
 * 
 * @author lilg
 */
public class SubjectVo {

    /**
     * 科目ID
     */
    private Long subjectId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 科目名称
     */
    private String subjectName;

    /**
     * 科目描述
     */
    private String subjectDesc;

    /**
     * 科目类型<br>
     * 1：资源科目<br>
     * 2：消费科目（原计费的科目）<br>
     * 21:消费科目中非通信类科目<br>
     * 4：虚科目（暂无）<br>
     * 9：资金科目（原余额中心的的科目）<br>
     * 10:订单科目 （原营业的科目）<br>
     * 11：商品中心科目
     */
    private String subjectType;

    /**
     * 单位显示名称
     */
    private String unitName;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 操作人
     */
    private String createOperId;

    /**
     * 业务点
     */
    private String chnlId;

    /**
     * 税率类型<br>
     * 1、基础;<br>
     * 2、增值;<br>
     * 3、混合比例<br>
     * 4、终端 <br>
     * 如果为空，则不打印发票<br>
     */
    private Integer taxType;
    
    /**
     * 科目商品类型 (用于资源科目)
     */
    private String goodsType;
    
    /**
     * 科目抵扣优先级 (用于资源科目)
     */
    private String resSubjectPri;

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

    public String getSubjectDesc() {
        return subjectDesc;
    }

    public void setSubjectDesc(String subjectDesc) {
        this.subjectDesc = subjectDesc;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Timestamp getCreateTime() {
        return createTime == null ? null : new Timestamp(createTime.getTime());
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime == null ? null : new Timestamp(createTime.getTime());
    }

    public String getCreateOperId() {
        return createOperId;
    }

    public void setCreateOperId(String createOperId) {
        this.createOperId = createOperId;
    }

    public String getChnlId() {
        return chnlId;
    }

    public void setChnlId(String chnlId) {
        this.chnlId = chnlId;
    }

    public Integer getTaxType() {
        return taxType;
    }

    public void setTaxType(Integer taxType) {
        this.taxType = taxType;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getResSubjectPri() {
        return resSubjectPri;
    }

    public void setResSubjectPri(String resSubjectPri) {
        this.resSubjectPri = resSubjectPri;
    }

}
