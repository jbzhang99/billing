package com.ai.baas.amc.dao.mapper.bo;

import java.util.ArrayList;
import java.util.List;

public class AmcDrBillSubjectMapCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public AmcDrBillSubjectMapCriteria() {
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

        public Criteria andDrSubjectIsNull() {
            addCriterion("DR_SUBJECT is null");
            return (Criteria) this;
        }

        public Criteria andDrSubjectIsNotNull() {
            addCriterion("DR_SUBJECT is not null");
            return (Criteria) this;
        }

        public Criteria andDrSubjectEqualTo(String value) {
            addCriterion("DR_SUBJECT =", value, "drSubject");
            return (Criteria) this;
        }

        public Criteria andDrSubjectNotEqualTo(String value) {
            addCriterion("DR_SUBJECT <>", value, "drSubject");
            return (Criteria) this;
        }

        public Criteria andDrSubjectGreaterThan(String value) {
            addCriterion("DR_SUBJECT >", value, "drSubject");
            return (Criteria) this;
        }

        public Criteria andDrSubjectGreaterThanOrEqualTo(String value) {
            addCriterion("DR_SUBJECT >=", value, "drSubject");
            return (Criteria) this;
        }

        public Criteria andDrSubjectLessThan(String value) {
            addCriterion("DR_SUBJECT <", value, "drSubject");
            return (Criteria) this;
        }

        public Criteria andDrSubjectLessThanOrEqualTo(String value) {
            addCriterion("DR_SUBJECT <=", value, "drSubject");
            return (Criteria) this;
        }

        public Criteria andDrSubjectLike(String value) {
            addCriterion("DR_SUBJECT like", value, "drSubject");
            return (Criteria) this;
        }

        public Criteria andDrSubjectNotLike(String value) {
            addCriterion("DR_SUBJECT not like", value, "drSubject");
            return (Criteria) this;
        }

        public Criteria andDrSubjectIn(List<String> values) {
            addCriterion("DR_SUBJECT in", values, "drSubject");
            return (Criteria) this;
        }

        public Criteria andDrSubjectNotIn(List<String> values) {
            addCriterion("DR_SUBJECT not in", values, "drSubject");
            return (Criteria) this;
        }

        public Criteria andDrSubjectBetween(String value1, String value2) {
            addCriterion("DR_SUBJECT between", value1, value2, "drSubject");
            return (Criteria) this;
        }

        public Criteria andDrSubjectNotBetween(String value1, String value2) {
            addCriterion("DR_SUBJECT not between", value1, value2, "drSubject");
            return (Criteria) this;
        }

        public Criteria andBillSubjectIsNull() {
            addCriterion("BILL_SUBJECT is null");
            return (Criteria) this;
        }

        public Criteria andBillSubjectIsNotNull() {
            addCriterion("BILL_SUBJECT is not null");
            return (Criteria) this;
        }

        public Criteria andBillSubjectEqualTo(String value) {
            addCriterion("BILL_SUBJECT =", value, "billSubject");
            return (Criteria) this;
        }

        public Criteria andBillSubjectNotEqualTo(String value) {
            addCriterion("BILL_SUBJECT <>", value, "billSubject");
            return (Criteria) this;
        }

        public Criteria andBillSubjectGreaterThan(String value) {
            addCriterion("BILL_SUBJECT >", value, "billSubject");
            return (Criteria) this;
        }

        public Criteria andBillSubjectGreaterThanOrEqualTo(String value) {
            addCriterion("BILL_SUBJECT >=", value, "billSubject");
            return (Criteria) this;
        }

        public Criteria andBillSubjectLessThan(String value) {
            addCriterion("BILL_SUBJECT <", value, "billSubject");
            return (Criteria) this;
        }

        public Criteria andBillSubjectLessThanOrEqualTo(String value) {
            addCriterion("BILL_SUBJECT <=", value, "billSubject");
            return (Criteria) this;
        }

        public Criteria andBillSubjectLike(String value) {
            addCriterion("BILL_SUBJECT like", value, "billSubject");
            return (Criteria) this;
        }

        public Criteria andBillSubjectNotLike(String value) {
            addCriterion("BILL_SUBJECT not like", value, "billSubject");
            return (Criteria) this;
        }

        public Criteria andBillSubjectIn(List<String> values) {
            addCriterion("BILL_SUBJECT in", values, "billSubject");
            return (Criteria) this;
        }

        public Criteria andBillSubjectNotIn(List<String> values) {
            addCriterion("BILL_SUBJECT not in", values, "billSubject");
            return (Criteria) this;
        }

        public Criteria andBillSubjectBetween(String value1, String value2) {
            addCriterion("BILL_SUBJECT between", value1, value2, "billSubject");
            return (Criteria) this;
        }

        public Criteria andBillSubjectNotBetween(String value1, String value2) {
            addCriterion("BILL_SUBJECT not between", value1, value2, "billSubject");
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