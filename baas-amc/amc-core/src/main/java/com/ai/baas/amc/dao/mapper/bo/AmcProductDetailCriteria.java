package com.ai.baas.amc.dao.mapper.bo;

import java.util.ArrayList;
import java.util.List;

public class AmcProductDetailCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public AmcProductDetailCriteria() {
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

        public Criteria andProductIdIsNull() {
            addCriterion("PRODUCT_ID is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("PRODUCT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(String value) {
            addCriterion("PRODUCT_ID =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(String value) {
            addCriterion("PRODUCT_ID <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(String value) {
            addCriterion("PRODUCT_ID >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCT_ID >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(String value) {
            addCriterion("PRODUCT_ID <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(String value) {
            addCriterion("PRODUCT_ID <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLike(String value) {
            addCriterion("PRODUCT_ID like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotLike(String value) {
            addCriterion("PRODUCT_ID not like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<String> values) {
            addCriterion("PRODUCT_ID in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<String> values) {
            addCriterion("PRODUCT_ID not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(String value1, String value2) {
            addCriterion("PRODUCT_ID between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(String value1, String value2) {
            addCriterion("PRODUCT_ID not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andPriorityIsNull() {
            addCriterion("PRIORITY is null");
            return (Criteria) this;
        }

        public Criteria andPriorityIsNotNull() {
            addCriterion("PRIORITY is not null");
            return (Criteria) this;
        }

        public Criteria andPriorityEqualTo(String value) {
            addCriterion("PRIORITY =", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotEqualTo(String value) {
            addCriterion("PRIORITY <>", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThan(String value) {
            addCriterion("PRIORITY >", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThanOrEqualTo(String value) {
            addCriterion("PRIORITY >=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThan(String value) {
            addCriterion("PRIORITY <", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThanOrEqualTo(String value) {
            addCriterion("PRIORITY <=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLike(String value) {
            addCriterion("PRIORITY like", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotLike(String value) {
            addCriterion("PRIORITY not like", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityIn(List<String> values) {
            addCriterion("PRIORITY in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotIn(List<String> values) {
            addCriterion("PRIORITY not in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityBetween(String value1, String value2) {
            addCriterion("PRIORITY between", value1, value2, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotBetween(String value1, String value2) {
            addCriterion("PRIORITY not between", value1, value2, "priority");
            return (Criteria) this;
        }

        public Criteria andBillSubjectIdIsNull() {
            addCriterion("BILL_SUBJECT_ID is null");
            return (Criteria) this;
        }

        public Criteria andBillSubjectIdIsNotNull() {
            addCriterion("BILL_SUBJECT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBillSubjectIdEqualTo(String value) {
            addCriterion("BILL_SUBJECT_ID =", value, "billSubjectId");
            return (Criteria) this;
        }

        public Criteria andBillSubjectIdNotEqualTo(String value) {
            addCriterion("BILL_SUBJECT_ID <>", value, "billSubjectId");
            return (Criteria) this;
        }

        public Criteria andBillSubjectIdGreaterThan(String value) {
            addCriterion("BILL_SUBJECT_ID >", value, "billSubjectId");
            return (Criteria) this;
        }

        public Criteria andBillSubjectIdGreaterThanOrEqualTo(String value) {
            addCriterion("BILL_SUBJECT_ID >=", value, "billSubjectId");
            return (Criteria) this;
        }

        public Criteria andBillSubjectIdLessThan(String value) {
            addCriterion("BILL_SUBJECT_ID <", value, "billSubjectId");
            return (Criteria) this;
        }

        public Criteria andBillSubjectIdLessThanOrEqualTo(String value) {
            addCriterion("BILL_SUBJECT_ID <=", value, "billSubjectId");
            return (Criteria) this;
        }

        public Criteria andBillSubjectIdLike(String value) {
            addCriterion("BILL_SUBJECT_ID like", value, "billSubjectId");
            return (Criteria) this;
        }

        public Criteria andBillSubjectIdNotLike(String value) {
            addCriterion("BILL_SUBJECT_ID not like", value, "billSubjectId");
            return (Criteria) this;
        }

        public Criteria andBillSubjectIdIn(List<String> values) {
            addCriterion("BILL_SUBJECT_ID in", values, "billSubjectId");
            return (Criteria) this;
        }

        public Criteria andBillSubjectIdNotIn(List<String> values) {
            addCriterion("BILL_SUBJECT_ID not in", values, "billSubjectId");
            return (Criteria) this;
        }

        public Criteria andBillSubjectIdBetween(String value1, String value2) {
            addCriterion("BILL_SUBJECT_ID between", value1, value2, "billSubjectId");
            return (Criteria) this;
        }

        public Criteria andBillSubjectIdNotBetween(String value1, String value2) {
            addCriterion("BILL_SUBJECT_ID not between", value1, value2, "billSubjectId");
            return (Criteria) this;
        }

        public Criteria andRefSubjectIdIsNull() {
            addCriterion("REF_SUBJECT_ID is null");
            return (Criteria) this;
        }

        public Criteria andRefSubjectIdIsNotNull() {
            addCriterion("REF_SUBJECT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRefSubjectIdEqualTo(String value) {
            addCriterion("REF_SUBJECT_ID =", value, "refSubjectId");
            return (Criteria) this;
        }

        public Criteria andRefSubjectIdNotEqualTo(String value) {
            addCriterion("REF_SUBJECT_ID <>", value, "refSubjectId");
            return (Criteria) this;
        }

        public Criteria andRefSubjectIdGreaterThan(String value) {
            addCriterion("REF_SUBJECT_ID >", value, "refSubjectId");
            return (Criteria) this;
        }

        public Criteria andRefSubjectIdGreaterThanOrEqualTo(String value) {
            addCriterion("REF_SUBJECT_ID >=", value, "refSubjectId");
            return (Criteria) this;
        }

        public Criteria andRefSubjectIdLessThan(String value) {
            addCriterion("REF_SUBJECT_ID <", value, "refSubjectId");
            return (Criteria) this;
        }

        public Criteria andRefSubjectIdLessThanOrEqualTo(String value) {
            addCriterion("REF_SUBJECT_ID <=", value, "refSubjectId");
            return (Criteria) this;
        }

        public Criteria andRefSubjectIdLike(String value) {
            addCriterion("REF_SUBJECT_ID like", value, "refSubjectId");
            return (Criteria) this;
        }

        public Criteria andRefSubjectIdNotLike(String value) {
            addCriterion("REF_SUBJECT_ID not like", value, "refSubjectId");
            return (Criteria) this;
        }

        public Criteria andRefSubjectIdIn(List<String> values) {
            addCriterion("REF_SUBJECT_ID in", values, "refSubjectId");
            return (Criteria) this;
        }

        public Criteria andRefSubjectIdNotIn(List<String> values) {
            addCriterion("REF_SUBJECT_ID not in", values, "refSubjectId");
            return (Criteria) this;
        }

        public Criteria andRefSubjectIdBetween(String value1, String value2) {
            addCriterion("REF_SUBJECT_ID between", value1, value2, "refSubjectId");
            return (Criteria) this;
        }

        public Criteria andRefSubjectIdNotBetween(String value1, String value2) {
            addCriterion("REF_SUBJECT_ID not between", value1, value2, "refSubjectId");
            return (Criteria) this;
        }

        public Criteria andNewSubjectIdIsNull() {
            addCriterion("NEW_SUBJECT_ID is null");
            return (Criteria) this;
        }

        public Criteria andNewSubjectIdIsNotNull() {
            addCriterion("NEW_SUBJECT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andNewSubjectIdEqualTo(String value) {
            addCriterion("NEW_SUBJECT_ID =", value, "newSubjectId");
            return (Criteria) this;
        }

        public Criteria andNewSubjectIdNotEqualTo(String value) {
            addCriterion("NEW_SUBJECT_ID <>", value, "newSubjectId");
            return (Criteria) this;
        }

        public Criteria andNewSubjectIdGreaterThan(String value) {
            addCriterion("NEW_SUBJECT_ID >", value, "newSubjectId");
            return (Criteria) this;
        }

        public Criteria andNewSubjectIdGreaterThanOrEqualTo(String value) {
            addCriterion("NEW_SUBJECT_ID >=", value, "newSubjectId");
            return (Criteria) this;
        }

        public Criteria andNewSubjectIdLessThan(String value) {
            addCriterion("NEW_SUBJECT_ID <", value, "newSubjectId");
            return (Criteria) this;
        }

        public Criteria andNewSubjectIdLessThanOrEqualTo(String value) {
            addCriterion("NEW_SUBJECT_ID <=", value, "newSubjectId");
            return (Criteria) this;
        }

        public Criteria andNewSubjectIdLike(String value) {
            addCriterion("NEW_SUBJECT_ID like", value, "newSubjectId");
            return (Criteria) this;
        }

        public Criteria andNewSubjectIdNotLike(String value) {
            addCriterion("NEW_SUBJECT_ID not like", value, "newSubjectId");
            return (Criteria) this;
        }

        public Criteria andNewSubjectIdIn(List<String> values) {
            addCriterion("NEW_SUBJECT_ID in", values, "newSubjectId");
            return (Criteria) this;
        }

        public Criteria andNewSubjectIdNotIn(List<String> values) {
            addCriterion("NEW_SUBJECT_ID not in", values, "newSubjectId");
            return (Criteria) this;
        }

        public Criteria andNewSubjectIdBetween(String value1, String value2) {
            addCriterion("NEW_SUBJECT_ID between", value1, value2, "newSubjectId");
            return (Criteria) this;
        }

        public Criteria andNewSubjectIdNotBetween(String value1, String value2) {
            addCriterion("NEW_SUBJECT_ID not between", value1, value2, "newSubjectId");
            return (Criteria) this;
        }

        public Criteria andCalcConditionIsNull() {
            addCriterion("CALC_CONDITION is null");
            return (Criteria) this;
        }

        public Criteria andCalcConditionIsNotNull() {
            addCriterion("CALC_CONDITION is not null");
            return (Criteria) this;
        }

        public Criteria andCalcConditionEqualTo(String value) {
            addCriterion("CALC_CONDITION =", value, "calcCondition");
            return (Criteria) this;
        }

        public Criteria andCalcConditionNotEqualTo(String value) {
            addCriterion("CALC_CONDITION <>", value, "calcCondition");
            return (Criteria) this;
        }

        public Criteria andCalcConditionGreaterThan(String value) {
            addCriterion("CALC_CONDITION >", value, "calcCondition");
            return (Criteria) this;
        }

        public Criteria andCalcConditionGreaterThanOrEqualTo(String value) {
            addCriterion("CALC_CONDITION >=", value, "calcCondition");
            return (Criteria) this;
        }

        public Criteria andCalcConditionLessThan(String value) {
            addCriterion("CALC_CONDITION <", value, "calcCondition");
            return (Criteria) this;
        }

        public Criteria andCalcConditionLessThanOrEqualTo(String value) {
            addCriterion("CALC_CONDITION <=", value, "calcCondition");
            return (Criteria) this;
        }

        public Criteria andCalcConditionLike(String value) {
            addCriterion("CALC_CONDITION like", value, "calcCondition");
            return (Criteria) this;
        }

        public Criteria andCalcConditionNotLike(String value) {
            addCriterion("CALC_CONDITION not like", value, "calcCondition");
            return (Criteria) this;
        }

        public Criteria andCalcConditionIn(List<String> values) {
            addCriterion("CALC_CONDITION in", values, "calcCondition");
            return (Criteria) this;
        }

        public Criteria andCalcConditionNotIn(List<String> values) {
            addCriterion("CALC_CONDITION not in", values, "calcCondition");
            return (Criteria) this;
        }

        public Criteria andCalcConditionBetween(String value1, String value2) {
            addCriterion("CALC_CONDITION between", value1, value2, "calcCondition");
            return (Criteria) this;
        }

        public Criteria andCalcConditionNotBetween(String value1, String value2) {
            addCriterion("CALC_CONDITION not between", value1, value2, "calcCondition");
            return (Criteria) this;
        }

        public Criteria andCalcTypeIsNull() {
            addCriterion("CALC_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCalcTypeIsNotNull() {
            addCriterion("CALC_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCalcTypeEqualTo(String value) {
            addCriterion("CALC_TYPE =", value, "calcType");
            return (Criteria) this;
        }

        public Criteria andCalcTypeNotEqualTo(String value) {
            addCriterion("CALC_TYPE <>", value, "calcType");
            return (Criteria) this;
        }

        public Criteria andCalcTypeGreaterThan(String value) {
            addCriterion("CALC_TYPE >", value, "calcType");
            return (Criteria) this;
        }

        public Criteria andCalcTypeGreaterThanOrEqualTo(String value) {
            addCriterion("CALC_TYPE >=", value, "calcType");
            return (Criteria) this;
        }

        public Criteria andCalcTypeLessThan(String value) {
            addCriterion("CALC_TYPE <", value, "calcType");
            return (Criteria) this;
        }

        public Criteria andCalcTypeLessThanOrEqualTo(String value) {
            addCriterion("CALC_TYPE <=", value, "calcType");
            return (Criteria) this;
        }

        public Criteria andCalcTypeLike(String value) {
            addCriterion("CALC_TYPE like", value, "calcType");
            return (Criteria) this;
        }

        public Criteria andCalcTypeNotLike(String value) {
            addCriterion("CALC_TYPE not like", value, "calcType");
            return (Criteria) this;
        }

        public Criteria andCalcTypeIn(List<String> values) {
            addCriterion("CALC_TYPE in", values, "calcType");
            return (Criteria) this;
        }

        public Criteria andCalcTypeNotIn(List<String> values) {
            addCriterion("CALC_TYPE not in", values, "calcType");
            return (Criteria) this;
        }

        public Criteria andCalcTypeBetween(String value1, String value2) {
            addCriterion("CALC_TYPE between", value1, value2, "calcType");
            return (Criteria) this;
        }

        public Criteria andCalcTypeNotBetween(String value1, String value2) {
            addCriterion("CALC_TYPE not between", value1, value2, "calcType");
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