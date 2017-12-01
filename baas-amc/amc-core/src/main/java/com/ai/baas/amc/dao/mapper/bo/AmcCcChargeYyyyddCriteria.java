package com.ai.baas.amc.dao.mapper.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AmcCcChargeYyyyddCriteria {
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

	public AmcCcChargeYyyyddCriteria() {
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

        public Criteria andCcChargeSeqIsNull() {
            addCriterion("CC_CHARGE_SEQ is null");
            return (Criteria) this;
        }

        public Criteria andCcChargeSeqIsNotNull() {
            addCriterion("CC_CHARGE_SEQ is not null");
            return (Criteria) this;
        }

        public Criteria andCcChargeSeqEqualTo(Long value) {
            addCriterion("CC_CHARGE_SEQ =", value, "ccChargeSeq");
            return (Criteria) this;
        }

        public Criteria andCcChargeSeqNotEqualTo(Long value) {
            addCriterion("CC_CHARGE_SEQ <>", value, "ccChargeSeq");
            return (Criteria) this;
        }

        public Criteria andCcChargeSeqGreaterThan(Long value) {
            addCriterion("CC_CHARGE_SEQ >", value, "ccChargeSeq");
            return (Criteria) this;
        }

        public Criteria andCcChargeSeqGreaterThanOrEqualTo(Long value) {
            addCriterion("CC_CHARGE_SEQ >=", value, "ccChargeSeq");
            return (Criteria) this;
        }

        public Criteria andCcChargeSeqLessThan(Long value) {
            addCriterion("CC_CHARGE_SEQ <", value, "ccChargeSeq");
            return (Criteria) this;
        }

        public Criteria andCcChargeSeqLessThanOrEqualTo(Long value) {
            addCriterion("CC_CHARGE_SEQ <=", value, "ccChargeSeq");
            return (Criteria) this;
        }

        public Criteria andCcChargeSeqIn(List<Long> values) {
            addCriterion("CC_CHARGE_SEQ in", values, "ccChargeSeq");
            return (Criteria) this;
        }

        public Criteria andCcChargeSeqNotIn(List<Long> values) {
            addCriterion("CC_CHARGE_SEQ not in", values, "ccChargeSeq");
            return (Criteria) this;
        }

        public Criteria andCcChargeSeqBetween(Long value1, Long value2) {
            addCriterion("CC_CHARGE_SEQ between", value1, value2, "ccChargeSeq");
            return (Criteria) this;
        }

        public Criteria andCcChargeSeqNotBetween(Long value1, Long value2) {
            addCriterion("CC_CHARGE_SEQ not between", value1, value2, "ccChargeSeq");
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

        public Criteria andLastDateIsNull() {
            addCriterion("LAST_DATE is null");
            return (Criteria) this;
        }

        public Criteria andLastDateIsNotNull() {
            addCriterion("LAST_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andLastDateEqualTo(Timestamp value) {
            addCriterion("LAST_DATE =", value, "lastDate");
            return (Criteria) this;
        }

        public Criteria andLastDateNotEqualTo(Timestamp value) {
            addCriterion("LAST_DATE <>", value, "lastDate");
            return (Criteria) this;
        }

        public Criteria andLastDateGreaterThan(Timestamp value) {
            addCriterion("LAST_DATE >", value, "lastDate");
            return (Criteria) this;
        }

        public Criteria andLastDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("LAST_DATE >=", value, "lastDate");
            return (Criteria) this;
        }

        public Criteria andLastDateLessThan(Timestamp value) {
            addCriterion("LAST_DATE <", value, "lastDate");
            return (Criteria) this;
        }

        public Criteria andLastDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("LAST_DATE <=", value, "lastDate");
            return (Criteria) this;
        }

        public Criteria andLastDateIn(List<Timestamp> values) {
            addCriterion("LAST_DATE in", values, "lastDate");
            return (Criteria) this;
        }

        public Criteria andLastDateNotIn(List<Timestamp> values) {
            addCriterion("LAST_DATE not in", values, "lastDate");
            return (Criteria) this;
        }

        public Criteria andLastDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("LAST_DATE between", value1, value2, "lastDate");
            return (Criteria) this;
        }

        public Criteria andLastDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("LAST_DATE not between", value1, value2, "lastDate");
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