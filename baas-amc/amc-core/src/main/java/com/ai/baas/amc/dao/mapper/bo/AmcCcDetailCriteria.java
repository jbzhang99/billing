package com.ai.baas.amc.dao.mapper.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AmcCcDetailCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    protected String billMonth;

    public String getBillMonth() {
        return billMonth;
    }

    public void setBillMonth(String billMonth) {
        this.billMonth = billMonth;
    }

    public AmcCcDetailCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimitStart(Integer limitStart) {
        this.limitStart=limitStart;
    }

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(Integer limitEnd) {
        this.limitEnd=limitEnd;
    }

    public Integer getLimitEnd() {
        return limitEnd;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andCcDetailSeqIsNull() {
            addCriterion("CC_DETAIL_SEQ is null");
            return (Criteria) this;
        }

        public Criteria andCcDetailSeqIsNotNull() {
            addCriterion("CC_DETAIL_SEQ is not null");
            return (Criteria) this;
        }

        public Criteria andCcDetailSeqEqualTo(Long value) {
            addCriterion("CC_DETAIL_SEQ =", value, "ccDetailSeq");
            return (Criteria) this;
        }

        public Criteria andCcDetailSeqNotEqualTo(Long value) {
            addCriterion("CC_DETAIL_SEQ <>", value, "ccDetailSeq");
            return (Criteria) this;
        }

        public Criteria andCcDetailSeqGreaterThan(Long value) {
            addCriterion("CC_DETAIL_SEQ >", value, "ccDetailSeq");
            return (Criteria) this;
        }

        public Criteria andCcDetailSeqGreaterThanOrEqualTo(Long value) {
            addCriterion("CC_DETAIL_SEQ >=", value, "ccDetailSeq");
            return (Criteria) this;
        }

        public Criteria andCcDetailSeqLessThan(Long value) {
            addCriterion("CC_DETAIL_SEQ <", value, "ccDetailSeq");
            return (Criteria) this;
        }

        public Criteria andCcDetailSeqLessThanOrEqualTo(Long value) {
            addCriterion("CC_DETAIL_SEQ <=", value, "ccDetailSeq");
            return (Criteria) this;
        }

        public Criteria andCcDetailSeqIn(List<Long> values) {
            addCriterion("CC_DETAIL_SEQ in", values, "ccDetailSeq");
            return (Criteria) this;
        }

        public Criteria andCcDetailSeqNotIn(List<Long> values) {
            addCriterion("CC_DETAIL_SEQ not in", values, "ccDetailSeq");
            return (Criteria) this;
        }

        public Criteria andCcDetailSeqBetween(Long value1, Long value2) {
            addCriterion("CC_DETAIL_SEQ between", value1, value2, "ccDetailSeq");
            return (Criteria) this;
        }

        public Criteria andCcDetailSeqNotBetween(Long value1, Long value2) {
            addCriterion("CC_DETAIL_SEQ not between", value1, value2, "ccDetailSeq");
            return (Criteria) this;
        }

        public Criteria andTenantIdIsNull() {
            addCriterion("TENANT_ID is null");
            return (Criteria) this;
        }

        public Criteria andTenantIdIsNotNull() {
            addCriterion("TENANT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTenantIdEqualTo(String value) {
            addCriterion("TENANT_ID =", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotEqualTo(String value) {
            addCriterion("TENANT_ID <>", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThan(String value) {
            addCriterion("TENANT_ID >", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThanOrEqualTo(String value) {
            addCriterion("TENANT_ID >=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThan(String value) {
            addCriterion("TENANT_ID <", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThanOrEqualTo(String value) {
            addCriterion("TENANT_ID <=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLike(String value) {
            addCriterion("TENANT_ID like", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotLike(String value) {
            addCriterion("TENANT_ID not like", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdIn(List<String> values) {
            addCriterion("TENANT_ID in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotIn(List<String> values) {
            addCriterion("TENANT_ID not in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdBetween(String value1, String value2) {
            addCriterion("TENANT_ID between", value1, value2, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotBetween(String value1, String value2) {
            addCriterion("TENANT_ID not between", value1, value2, "tenantId");
            return (Criteria) this;
        }

        public Criteria andCostCenterIdIsNull() {
            addCriterion("COST_CENTER_ID is null");
            return (Criteria) this;
        }

        public Criteria andCostCenterIdIsNotNull() {
            addCriterion("COST_CENTER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCostCenterIdEqualTo(String value) {
            addCriterion("COST_CENTER_ID =", value, "costCenterId");
            return (Criteria) this;
        }

        public Criteria andCostCenterIdNotEqualTo(String value) {
            addCriterion("COST_CENTER_ID <>", value, "costCenterId");
            return (Criteria) this;
        }

        public Criteria andCostCenterIdGreaterThan(String value) {
            addCriterion("COST_CENTER_ID >", value, "costCenterId");
            return (Criteria) this;
        }

        public Criteria andCostCenterIdGreaterThanOrEqualTo(String value) {
            addCriterion("COST_CENTER_ID >=", value, "costCenterId");
            return (Criteria) this;
        }

        public Criteria andCostCenterIdLessThan(String value) {
            addCriterion("COST_CENTER_ID <", value, "costCenterId");
            return (Criteria) this;
        }

        public Criteria andCostCenterIdLessThanOrEqualTo(String value) {
            addCriterion("COST_CENTER_ID <=", value, "costCenterId");
            return (Criteria) this;
        }

        public Criteria andCostCenterIdLike(String value) {
            addCriterion("COST_CENTER_ID like", value, "costCenterId");
            return (Criteria) this;
        }

        public Criteria andCostCenterIdNotLike(String value) {
            addCriterion("COST_CENTER_ID not like", value, "costCenterId");
            return (Criteria) this;
        }

        public Criteria andCostCenterIdIn(List<String> values) {
            addCriterion("COST_CENTER_ID in", values, "costCenterId");
            return (Criteria) this;
        }

        public Criteria andCostCenterIdNotIn(List<String> values) {
            addCriterion("COST_CENTER_ID not in", values, "costCenterId");
            return (Criteria) this;
        }

        public Criteria andCostCenterIdBetween(String value1, String value2) {
            addCriterion("COST_CENTER_ID between", value1, value2, "costCenterId");
            return (Criteria) this;
        }

        public Criteria andCostCenterIdNotBetween(String value1, String value2) {
            addCriterion("COST_CENTER_ID not between", value1, value2, "costCenterId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdIsNull() {
            addCriterion("SUBJECT_ID is null");
            return (Criteria) this;
        }

        public Criteria andSubjectIdIsNotNull() {
            addCriterion("SUBJECT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectIdEqualTo(Long value) {
            addCriterion("SUBJECT_ID =", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdNotEqualTo(Long value) {
            addCriterion("SUBJECT_ID <>", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdGreaterThan(Long value) {
            addCriterion("SUBJECT_ID >", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdGreaterThanOrEqualTo(Long value) {
            addCriterion("SUBJECT_ID >=", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdLessThan(Long value) {
            addCriterion("SUBJECT_ID <", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdLessThanOrEqualTo(Long value) {
            addCriterion("SUBJECT_ID <=", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdIn(List<Long> values) {
            addCriterion("SUBJECT_ID in", values, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdNotIn(List<Long> values) {
            addCriterion("SUBJECT_ID not in", values, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdBetween(Long value1, Long value2) {
            addCriterion("SUBJECT_ID between", value1, value2, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdNotBetween(Long value1, Long value2) {
            addCriterion("SUBJECT_ID not between", value1, value2, "subjectId");
            return (Criteria) this;
        }

        public Criteria andApportionAcctIdIsNull() {
            addCriterion("APPORTION_ACCT_ID is null");
            return (Criteria) this;
        }

        public Criteria andApportionAcctIdIsNotNull() {
            addCriterion("APPORTION_ACCT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andApportionAcctIdEqualTo(String value) {
            addCriterion("APPORTION_ACCT_ID =", value, "apportionAcctId");
            return (Criteria) this;
        }

        public Criteria andApportionAcctIdNotEqualTo(String value) {
            addCriterion("APPORTION_ACCT_ID <>", value, "apportionAcctId");
            return (Criteria) this;
        }

        public Criteria andApportionAcctIdGreaterThan(String value) {
            addCriterion("APPORTION_ACCT_ID >", value, "apportionAcctId");
            return (Criteria) this;
        }

        public Criteria andApportionAcctIdGreaterThanOrEqualTo(String value) {
            addCriterion("APPORTION_ACCT_ID >=", value, "apportionAcctId");
            return (Criteria) this;
        }

        public Criteria andApportionAcctIdLessThan(String value) {
            addCriterion("APPORTION_ACCT_ID <", value, "apportionAcctId");
            return (Criteria) this;
        }

        public Criteria andApportionAcctIdLessThanOrEqualTo(String value) {
            addCriterion("APPORTION_ACCT_ID <=", value, "apportionAcctId");
            return (Criteria) this;
        }

        public Criteria andApportionAcctIdLike(String value) {
            addCriterion("APPORTION_ACCT_ID like", value, "apportionAcctId");
            return (Criteria) this;
        }

        public Criteria andApportionAcctIdNotLike(String value) {
            addCriterion("APPORTION_ACCT_ID not like", value, "apportionAcctId");
            return (Criteria) this;
        }

        public Criteria andApportionAcctIdIn(List<String> values) {
            addCriterion("APPORTION_ACCT_ID in", values, "apportionAcctId");
            return (Criteria) this;
        }

        public Criteria andApportionAcctIdNotIn(List<String> values) {
            addCriterion("APPORTION_ACCT_ID not in", values, "apportionAcctId");
            return (Criteria) this;
        }

        public Criteria andApportionAcctIdBetween(String value1, String value2) {
            addCriterion("APPORTION_ACCT_ID between", value1, value2, "apportionAcctId");
            return (Criteria) this;
        }

        public Criteria andApportionAcctIdNotBetween(String value1, String value2) {
            addCriterion("APPORTION_ACCT_ID not between", value1, value2, "apportionAcctId");
            return (Criteria) this;
        }

        public Criteria andApportionMethodIsNull() {
            addCriterion("APPORTION_METHOD is null");
            return (Criteria) this;
        }

        public Criteria andApportionMethodIsNotNull() {
            addCriterion("APPORTION_METHOD is not null");
            return (Criteria) this;
        }

        public Criteria andApportionMethodEqualTo(String value) {
            addCriterion("APPORTION_METHOD =", value, "apportionMethod");
            return (Criteria) this;
        }

        public Criteria andApportionMethodNotEqualTo(String value) {
            addCriterion("APPORTION_METHOD <>", value, "apportionMethod");
            return (Criteria) this;
        }

        public Criteria andApportionMethodGreaterThan(String value) {
            addCriterion("APPORTION_METHOD >", value, "apportionMethod");
            return (Criteria) this;
        }

        public Criteria andApportionMethodGreaterThanOrEqualTo(String value) {
            addCriterion("APPORTION_METHOD >=", value, "apportionMethod");
            return (Criteria) this;
        }

        public Criteria andApportionMethodLessThan(String value) {
            addCriterion("APPORTION_METHOD <", value, "apportionMethod");
            return (Criteria) this;
        }

        public Criteria andApportionMethodLessThanOrEqualTo(String value) {
            addCriterion("APPORTION_METHOD <=", value, "apportionMethod");
            return (Criteria) this;
        }

        public Criteria andApportionMethodLike(String value) {
            addCriterion("APPORTION_METHOD like", value, "apportionMethod");
            return (Criteria) this;
        }

        public Criteria andApportionMethodNotLike(String value) {
            addCriterion("APPORTION_METHOD not like", value, "apportionMethod");
            return (Criteria) this;
        }

        public Criteria andApportionMethodIn(List<String> values) {
            addCriterion("APPORTION_METHOD in", values, "apportionMethod");
            return (Criteria) this;
        }

        public Criteria andApportionMethodNotIn(List<String> values) {
            addCriterion("APPORTION_METHOD not in", values, "apportionMethod");
            return (Criteria) this;
        }

        public Criteria andApportionMethodBetween(String value1, String value2) {
            addCriterion("APPORTION_METHOD between", value1, value2, "apportionMethod");
            return (Criteria) this;
        }

        public Criteria andApportionMethodNotBetween(String value1, String value2) {
            addCriterion("APPORTION_METHOD not between", value1, value2, "apportionMethod");
            return (Criteria) this;
        }

        public Criteria andApportionValueIsNull() {
            addCriterion("APPORTION_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andApportionValueIsNotNull() {
            addCriterion("APPORTION_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andApportionValueEqualTo(String value) {
            addCriterion("APPORTION_VALUE =", value, "apportionValue");
            return (Criteria) this;
        }

        public Criteria andApportionValueNotEqualTo(String value) {
            addCriterion("APPORTION_VALUE <>", value, "apportionValue");
            return (Criteria) this;
        }

        public Criteria andApportionValueGreaterThan(String value) {
            addCriterion("APPORTION_VALUE >", value, "apportionValue");
            return (Criteria) this;
        }

        public Criteria andApportionValueGreaterThanOrEqualTo(String value) {
            addCriterion("APPORTION_VALUE >=", value, "apportionValue");
            return (Criteria) this;
        }

        public Criteria andApportionValueLessThan(String value) {
            addCriterion("APPORTION_VALUE <", value, "apportionValue");
            return (Criteria) this;
        }

        public Criteria andApportionValueLessThanOrEqualTo(String value) {
            addCriterion("APPORTION_VALUE <=", value, "apportionValue");
            return (Criteria) this;
        }

        public Criteria andApportionValueLike(String value) {
            addCriterion("APPORTION_VALUE like", value, "apportionValue");
            return (Criteria) this;
        }

        public Criteria andApportionValueNotLike(String value) {
            addCriterion("APPORTION_VALUE not like", value, "apportionValue");
            return (Criteria) this;
        }

        public Criteria andApportionValueIn(List<String> values) {
            addCriterion("APPORTION_VALUE in", values, "apportionValue");
            return (Criteria) this;
        }

        public Criteria andApportionValueNotIn(List<String> values) {
            addCriterion("APPORTION_VALUE not in", values, "apportionValue");
            return (Criteria) this;
        }

        public Criteria andApportionValueBetween(String value1, String value2) {
            addCriterion("APPORTION_VALUE between", value1, value2, "apportionValue");
            return (Criteria) this;
        }

        public Criteria andApportionValueNotBetween(String value1, String value2) {
            addCriterion("APPORTION_VALUE not between", value1, value2, "apportionValue");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(Long value) {
            addCriterion("AMOUNT =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Long value) {
            addCriterion("AMOUNT <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Long value) {
            addCriterion("AMOUNT >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("AMOUNT >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Long value) {
            addCriterion("AMOUNT <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Long value) {
            addCriterion("AMOUNT <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Long> values) {
            addCriterion("AMOUNT in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Long> values) {
            addCriterion("AMOUNT not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Long value1, Long value2) {
            addCriterion("AMOUNT between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Long value1, Long value2) {
            addCriterion("AMOUNT not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAcctIdIsNull() {
            addCriterion("ACCT_ID is null");
            return (Criteria) this;
        }

        public Criteria andAcctIdIsNotNull() {
            addCriterion("ACCT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAcctIdEqualTo(String value) {
            addCriterion("ACCT_ID =", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdNotEqualTo(String value) {
            addCriterion("ACCT_ID <>", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdGreaterThan(String value) {
            addCriterion("ACCT_ID >", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdGreaterThanOrEqualTo(String value) {
            addCriterion("ACCT_ID >=", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdLessThan(String value) {
            addCriterion("ACCT_ID <", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdLessThanOrEqualTo(String value) {
            addCriterion("ACCT_ID <=", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdLike(String value) {
            addCriterion("ACCT_ID like", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdNotLike(String value) {
            addCriterion("ACCT_ID not like", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdIn(List<String> values) {
            addCriterion("ACCT_ID in", values, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdNotIn(List<String> values) {
            addCriterion("ACCT_ID not in", values, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdBetween(String value1, String value2) {
            addCriterion("ACCT_ID between", value1, value2, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdNotBetween(String value1, String value2) {
            addCriterion("ACCT_ID not between", value1, value2, "acctId");
            return (Criteria) this;
        }

        public Criteria andCustIdIsNull() {
            addCriterion("CUST_ID is null");
            return (Criteria) this;
        }

        public Criteria andCustIdIsNotNull() {
            addCriterion("CUST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCustIdEqualTo(Long value) {
            addCriterion("CUST_ID =", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotEqualTo(Long value) {
            addCriterion("CUST_ID <>", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdGreaterThan(Long value) {
            addCriterion("CUST_ID >", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdGreaterThanOrEqualTo(Long value) {
            addCriterion("CUST_ID >=", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdLessThan(Long value) {
            addCriterion("CUST_ID <", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdLessThanOrEqualTo(Long value) {
            addCriterion("CUST_ID <=", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdIn(List<Long> values) {
            addCriterion("CUST_ID in", values, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotIn(List<Long> values) {
            addCriterion("CUST_ID not in", values, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdBetween(Long value1, Long value2) {
            addCriterion("CUST_ID between", value1, value2, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotBetween(Long value1, Long value2) {
            addCriterion("CUST_ID not between", value1, value2, "custId");
            return (Criteria) this;
        }

        public Criteria andSubsIdIsNull() {
            addCriterion("SUBS_ID is null");
            return (Criteria) this;
        }

        public Criteria andSubsIdIsNotNull() {
            addCriterion("SUBS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSubsIdEqualTo(Long value) {
            addCriterion("SUBS_ID =", value, "subsId");
            return (Criteria) this;
        }

        public Criteria andSubsIdNotEqualTo(Long value) {
            addCriterion("SUBS_ID <>", value, "subsId");
            return (Criteria) this;
        }

        public Criteria andSubsIdGreaterThan(Long value) {
            addCriterion("SUBS_ID >", value, "subsId");
            return (Criteria) this;
        }

        public Criteria andSubsIdGreaterThanOrEqualTo(Long value) {
            addCriterion("SUBS_ID >=", value, "subsId");
            return (Criteria) this;
        }

        public Criteria andSubsIdLessThan(Long value) {
            addCriterion("SUBS_ID <", value, "subsId");
            return (Criteria) this;
        }

        public Criteria andSubsIdLessThanOrEqualTo(Long value) {
            addCriterion("SUBS_ID <=", value, "subsId");
            return (Criteria) this;
        }

        public Criteria andSubsIdIn(List<Long> values) {
            addCriterion("SUBS_ID in", values, "subsId");
            return (Criteria) this;
        }

        public Criteria andSubsIdNotIn(List<Long> values) {
            addCriterion("SUBS_ID not in", values, "subsId");
            return (Criteria) this;
        }

        public Criteria andSubsIdBetween(Long value1, Long value2) {
            addCriterion("SUBS_ID between", value1, value2, "subsId");
            return (Criteria) this;
        }

        public Criteria andSubsIdNotBetween(Long value1, Long value2) {
            addCriterion("SUBS_ID not between", value1, value2, "subsId");
            return (Criteria) this;
        }

        public Criteria andIsApportionIsNull() {
            addCriterion("IS_APPORTION is null");
            return (Criteria) this;
        }

        public Criteria andIsApportionIsNotNull() {
            addCriterion("IS_APPORTION is not null");
            return (Criteria) this;
        }

        public Criteria andIsApportionEqualTo(String value) {
            addCriterion("IS_APPORTION =", value, "isApportion");
            return (Criteria) this;
        }

        public Criteria andIsApportionNotEqualTo(String value) {
            addCriterion("IS_APPORTION <>", value, "isApportion");
            return (Criteria) this;
        }

        public Criteria andIsApportionGreaterThan(String value) {
            addCriterion("IS_APPORTION >", value, "isApportion");
            return (Criteria) this;
        }

        public Criteria andIsApportionGreaterThanOrEqualTo(String value) {
            addCriterion("IS_APPORTION >=", value, "isApportion");
            return (Criteria) this;
        }

        public Criteria andIsApportionLessThan(String value) {
            addCriterion("IS_APPORTION <", value, "isApportion");
            return (Criteria) this;
        }

        public Criteria andIsApportionLessThanOrEqualTo(String value) {
            addCriterion("IS_APPORTION <=", value, "isApportion");
            return (Criteria) this;
        }

        public Criteria andIsApportionLike(String value) {
            addCriterion("IS_APPORTION like", value, "isApportion");
            return (Criteria) this;
        }

        public Criteria andIsApportionNotLike(String value) {
            addCriterion("IS_APPORTION not like", value, "isApportion");
            return (Criteria) this;
        }

        public Criteria andIsApportionIn(List<String> values) {
            addCriterion("IS_APPORTION in", values, "isApportion");
            return (Criteria) this;
        }

        public Criteria andIsApportionNotIn(List<String> values) {
            addCriterion("IS_APPORTION not in", values, "isApportion");
            return (Criteria) this;
        }

        public Criteria andIsApportionBetween(String value1, String value2) {
            addCriterion("IS_APPORTION between", value1, value2, "isApportion");
            return (Criteria) this;
        }

        public Criteria andIsApportionNotBetween(String value1, String value2) {
            addCriterion("IS_APPORTION not between", value1, value2, "isApportion");
            return (Criteria) this;
        }

        public Criteria andDrKeyIsNull() {
            addCriterion("DR_KEY is null");
            return (Criteria) this;
        }

        public Criteria andDrKeyIsNotNull() {
            addCriterion("DR_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andDrKeyEqualTo(String value) {
            addCriterion("DR_KEY =", value, "drKey");
            return (Criteria) this;
        }

        public Criteria andDrKeyNotEqualTo(String value) {
            addCriterion("DR_KEY <>", value, "drKey");
            return (Criteria) this;
        }

        public Criteria andDrKeyGreaterThan(String value) {
            addCriterion("DR_KEY >", value, "drKey");
            return (Criteria) this;
        }

        public Criteria andDrKeyGreaterThanOrEqualTo(String value) {
            addCriterion("DR_KEY >=", value, "drKey");
            return (Criteria) this;
        }

        public Criteria andDrKeyLessThan(String value) {
            addCriterion("DR_KEY <", value, "drKey");
            return (Criteria) this;
        }

        public Criteria andDrKeyLessThanOrEqualTo(String value) {
            addCriterion("DR_KEY <=", value, "drKey");
            return (Criteria) this;
        }

        public Criteria andDrKeyLike(String value) {
            addCriterion("DR_KEY like", value, "drKey");
            return (Criteria) this;
        }

        public Criteria andDrKeyNotLike(String value) {
            addCriterion("DR_KEY not like", value, "drKey");
            return (Criteria) this;
        }

        public Criteria andDrKeyIn(List<String> values) {
            addCriterion("DR_KEY in", values, "drKey");
            return (Criteria) this;
        }

        public Criteria andDrKeyNotIn(List<String> values) {
            addCriterion("DR_KEY not in", values, "drKey");
            return (Criteria) this;
        }

        public Criteria andDrKeyBetween(String value1, String value2) {
            addCriterion("DR_KEY between", value1, value2, "drKey");
            return (Criteria) this;
        }

        public Criteria andDrKeyNotBetween(String value1, String value2) {
            addCriterion("DR_KEY not between", value1, value2, "drKey");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("CREATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("CREATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Timestamp value) {
            addCriterion("CREATE_DATE =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Timestamp value) {
            addCriterion("CREATE_DATE <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Timestamp value) {
            addCriterion("CREATE_DATE >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("CREATE_DATE >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Timestamp value) {
            addCriterion("CREATE_DATE <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("CREATE_DATE <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Timestamp> values) {
            addCriterion("CREATE_DATE in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Timestamp> values) {
            addCriterion("CREATE_DATE not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CREATE_DATE between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CREATE_DATE not between", value1, value2, "createDate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}