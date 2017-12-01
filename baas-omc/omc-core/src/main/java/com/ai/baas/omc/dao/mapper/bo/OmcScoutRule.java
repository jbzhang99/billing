package com.ai.baas.omc.dao.mapper.bo;

public class OmcScoutRule {
    private Long ruleId;

    private Long policyid;

    private String scoutRule;

    private String scoutType;

    private Long balanceFloor;

    private Long balanceCeil;

    private Integer oweMaxdays;

    private Integer oweMindays;

    private Long chargeCeil;

    private Long chargeFloor;

    private String custType;

    private String acctType;

    private String userType;

    private String custLevel;

    private String tenantId;

    private String systemId;

    private String sectionType;

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public Long getPolicyid() {
        return policyid;
    }

    public void setPolicyid(Long policyid) {
        this.policyid = policyid;
    }

    public String getScoutRule() {
        return scoutRule;
    }

    public void setScoutRule(String scoutRule) {
        this.scoutRule = scoutRule == null ? null : scoutRule.trim();
    }

    public String getScoutType() {
        return scoutType;
    }

    public void setScoutType(String scoutType) {
        this.scoutType = scoutType == null ? null : scoutType.trim();
    }

    public Long getBalanceFloor() {
        return balanceFloor;
    }

    public void setBalanceFloor(Long balanceFloor) {
        this.balanceFloor = balanceFloor;
    }

    public Long getBalanceCeil() {
        return balanceCeil;
    }

    public void setBalanceCeil(Long balanceCeil) {
        this.balanceCeil = balanceCeil;
    }

    public Integer getOweMaxdays() {
        return oweMaxdays;
    }

    public void setOweMaxdays(Integer oweMaxdays) {
        this.oweMaxdays = oweMaxdays;
    }

    public Integer getOweMindays() {
        return oweMindays;
    }

    public void setOweMindays(Integer oweMindays) {
        this.oweMindays = oweMindays;
    }

    public Long getChargeCeil() {
        return chargeCeil;
    }

    public void setChargeCeil(Long chargeCeil) {
        this.chargeCeil = chargeCeil;
    }

    public Long getChargeFloor() {
        return chargeFloor;
    }

    public void setChargeFloor(Long chargeFloor) {
        this.chargeFloor = chargeFloor;
    }

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType == null ? null : custType.trim();
    }

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType == null ? null : acctType.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel == null ? null : custLevel.trim();
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId == null ? null : systemId.trim();
    }

    public String getSectionType() {
        return sectionType;
    }

    public void setSectionType(String sectionType) {
        this.sectionType = sectionType == null ? null : sectionType.trim();
    }
}