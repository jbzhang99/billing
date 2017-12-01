package com.ai.baas.abm.dao.mapper.bo;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class AmcResBookLog {
    private Long bookId;

    private String tenantId;

    private String ownerId;

    private Integer ownerType;

    private String subjectId;

    private String goodsType;

    private Integer resourceType;

    private Timestamp createTime;

    private Timestamp effectTime;

    private Timestamp expireTime;

    private BigDecimal totalAmount;

    private BigDecimal transferAmount;

    private BigDecimal deductAmount;

    private BigDecimal occupyAmount;

    private String bookStatus;

    private Integer allowPresent;

    private Integer allowConvert;

    private Integer allowClear;

    private Integer sourceType;

    private String sourceId;

    private BigDecimal changeAmount;

    private String deductType;

    private String deductSource;

    private String acctMonth;

    private Timestamp newExpireDate;

    private String newBookStatus;

    private Integer optType;

    private Timestamp optTime;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId == null ? null : ownerId.trim();
    }

    public Integer getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(Integer ownerType) {
        this.ownerType = ownerType;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId == null ? null : subjectId.trim();
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType == null ? null : goodsType.trim();
    }

    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getEffectTime() {
        return effectTime;
    }

    public void setEffectTime(Timestamp effectTime) {
        this.effectTime = effectTime;
    }

    public Timestamp getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Timestamp expireTime) {
        this.expireTime = expireTime;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }

    public BigDecimal getDeductAmount() {
        return deductAmount;
    }

    public void setDeductAmount(BigDecimal deductAmount) {
        this.deductAmount = deductAmount;
    }

    public BigDecimal getOccupyAmount() {
        return occupyAmount;
    }

    public void setOccupyAmount(BigDecimal occupyAmount) {
        this.occupyAmount = occupyAmount;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus == null ? null : bookStatus.trim();
    }

    public Integer getAllowPresent() {
        return allowPresent;
    }

    public void setAllowPresent(Integer allowPresent) {
        this.allowPresent = allowPresent;
    }

    public Integer getAllowConvert() {
        return allowConvert;
    }

    public void setAllowConvert(Integer allowConvert) {
        this.allowConvert = allowConvert;
    }

    public Integer getAllowClear() {
        return allowClear;
    }

    public void setAllowClear(Integer allowClear) {
        this.allowClear = allowClear;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId == null ? null : sourceId.trim();
    }

    public BigDecimal getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(BigDecimal changeAmount) {
        this.changeAmount = changeAmount;
    }

    public String getDeductType() {
        return deductType;
    }

    public void setDeductType(String deductType) {
        this.deductType = deductType == null ? null : deductType.trim();
    }

    public String getDeductSource() {
        return deductSource;
    }

    public void setDeductSource(String deductSource) {
        this.deductSource = deductSource == null ? null : deductSource.trim();
    }

    public String getAcctMonth() {
        return acctMonth;
    }

    public void setAcctMonth(String acctMonth) {
        this.acctMonth = acctMonth == null ? null : acctMonth.trim();
    }

    public Timestamp getNewExpireDate() {
        return newExpireDate;
    }

    public void setNewExpireDate(Timestamp newExpireDate) {
        this.newExpireDate = newExpireDate;
    }

    public String getNewBookStatus() {
        return newBookStatus;
    }

    public void setNewBookStatus(String newBookStatus) {
        this.newBookStatus = newBookStatus == null ? null : newBookStatus.trim();
    }

    public Integer getOptType() {
        return optType;
    }

    public void setOptType(Integer optType) {
        this.optType = optType;
    }

    public Timestamp getOptTime() {
        return optTime;
    }

    public void setOptTime(Timestamp optTime) {
        this.optTime = optTime;
    }
}