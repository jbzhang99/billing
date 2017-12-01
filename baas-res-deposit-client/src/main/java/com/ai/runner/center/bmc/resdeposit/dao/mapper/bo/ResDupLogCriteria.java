package com.ai.runner.center.bmc.resdeposit.dao.mapper.bo;

import java.util.ArrayList;
import java.util.List;

public class ResDupLogCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public ResDupLogCriteria() {
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

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSubsIdIsNull() {
            addCriterion("subs_id is null");
            return (Criteria) this;
        }

        public Criteria andSubsIdIsNotNull() {
            addCriterion("subs_id is not null");
            return (Criteria) this;
        }

        public Criteria andSubsIdEqualTo(String value) {
            addCriterion("subs_id =", value, "subsId");
            return (Criteria) this;
        }

        public Criteria andSubsIdNotEqualTo(String value) {
            addCriterion("subs_id <>", value, "subsId");
            return (Criteria) this;
        }

        public Criteria andSubsIdGreaterThan(String value) {
            addCriterion("subs_id >", value, "subsId");
            return (Criteria) this;
        }

        public Criteria andSubsIdGreaterThanOrEqualTo(String value) {
            addCriterion("subs_id >=", value, "subsId");
            return (Criteria) this;
        }

        public Criteria andSubsIdLessThan(String value) {
            addCriterion("subs_id <", value, "subsId");
            return (Criteria) this;
        }

        public Criteria andSubsIdLessThanOrEqualTo(String value) {
            addCriterion("subs_id <=", value, "subsId");
            return (Criteria) this;
        }

        public Criteria andSubsIdLike(String value) {
            addCriterion("subs_id like", value, "subsId");
            return (Criteria) this;
        }

        public Criteria andSubsIdNotLike(String value) {
            addCriterion("subs_id not like", value, "subsId");
            return (Criteria) this;
        }

        public Criteria andSubsIdIn(List<String> values) {
            addCriterion("subs_id in", values, "subsId");
            return (Criteria) this;
        }

        public Criteria andSubsIdNotIn(List<String> values) {
            addCriterion("subs_id not in", values, "subsId");
            return (Criteria) this;
        }

        public Criteria andSubsIdBetween(String value1, String value2) {
            addCriterion("subs_id between", value1, value2, "subsId");
            return (Criteria) this;
        }

        public Criteria andSubsIdNotBetween(String value1, String value2) {
            addCriterion("subs_id not between", value1, value2, "subsId");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(String value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(String value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(String value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(String value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(String value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(String value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLike(String value) {
            addCriterion("product_id like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotLike(String value) {
            addCriterion("product_id not like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<String> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<String> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(String value1, String value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(String value1, String value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andSubsProductIdIsNull() {
            addCriterion("subs_product_id is null");
            return (Criteria) this;
        }

        public Criteria andSubsProductIdIsNotNull() {
            addCriterion("subs_product_id is not null");
            return (Criteria) this;
        }

        public Criteria andSubsProductIdEqualTo(String value) {
            addCriterion("subs_product_id =", value, "subsProductId");
            return (Criteria) this;
        }

        public Criteria andSubsProductIdNotEqualTo(String value) {
            addCriterion("subs_product_id <>", value, "subsProductId");
            return (Criteria) this;
        }

        public Criteria andSubsProductIdGreaterThan(String value) {
            addCriterion("subs_product_id >", value, "subsProductId");
            return (Criteria) this;
        }

        public Criteria andSubsProductIdGreaterThanOrEqualTo(String value) {
            addCriterion("subs_product_id >=", value, "subsProductId");
            return (Criteria) this;
        }

        public Criteria andSubsProductIdLessThan(String value) {
            addCriterion("subs_product_id <", value, "subsProductId");
            return (Criteria) this;
        }

        public Criteria andSubsProductIdLessThanOrEqualTo(String value) {
            addCriterion("subs_product_id <=", value, "subsProductId");
            return (Criteria) this;
        }

        public Criteria andSubsProductIdLike(String value) {
            addCriterion("subs_product_id like", value, "subsProductId");
            return (Criteria) this;
        }

        public Criteria andSubsProductIdNotLike(String value) {
            addCriterion("subs_product_id not like", value, "subsProductId");
            return (Criteria) this;
        }

        public Criteria andSubsProductIdIn(List<String> values) {
            addCriterion("subs_product_id in", values, "subsProductId");
            return (Criteria) this;
        }

        public Criteria andSubsProductIdNotIn(List<String> values) {
            addCriterion("subs_product_id not in", values, "subsProductId");
            return (Criteria) this;
        }

        public Criteria andSubsProductIdBetween(String value1, String value2) {
            addCriterion("subs_product_id between", value1, value2, "subsProductId");
            return (Criteria) this;
        }

        public Criteria andSubsProductIdNotBetween(String value1, String value2) {
            addCriterion("subs_product_id not between", value1, value2, "subsProductId");
            return (Criteria) this;
        }

        public Criteria andSystemtimeIsNull() {
            addCriterion("systemtime is null");
            return (Criteria) this;
        }

        public Criteria andSystemtimeIsNotNull() {
            addCriterion("systemtime is not null");
            return (Criteria) this;
        }

        public Criteria andSystemtimeEqualTo(String value) {
            addCriterion("systemtime =", value, "systemtime");
            return (Criteria) this;
        }

        public Criteria andSystemtimeNotEqualTo(String value) {
            addCriterion("systemtime <>", value, "systemtime");
            return (Criteria) this;
        }

        public Criteria andSystemtimeGreaterThan(String value) {
            addCriterion("systemtime >", value, "systemtime");
            return (Criteria) this;
        }

        public Criteria andSystemtimeGreaterThanOrEqualTo(String value) {
            addCriterion("systemtime >=", value, "systemtime");
            return (Criteria) this;
        }

        public Criteria andSystemtimeLessThan(String value) {
            addCriterion("systemtime <", value, "systemtime");
            return (Criteria) this;
        }

        public Criteria andSystemtimeLessThanOrEqualTo(String value) {
            addCriterion("systemtime <=", value, "systemtime");
            return (Criteria) this;
        }

        public Criteria andSystemtimeLike(String value) {
            addCriterion("systemtime like", value, "systemtime");
            return (Criteria) this;
        }

        public Criteria andSystemtimeNotLike(String value) {
            addCriterion("systemtime not like", value, "systemtime");
            return (Criteria) this;
        }

        public Criteria andSystemtimeIn(List<String> values) {
            addCriterion("systemtime in", values, "systemtime");
            return (Criteria) this;
        }

        public Criteria andSystemtimeNotIn(List<String> values) {
            addCriterion("systemtime not in", values, "systemtime");
            return (Criteria) this;
        }

        public Criteria andSystemtimeBetween(String value1, String value2) {
            addCriterion("systemtime between", value1, value2, "systemtime");
            return (Criteria) this;
        }

        public Criteria andSystemtimeNotBetween(String value1, String value2) {
            addCriterion("systemtime not between", value1, value2, "systemtime");
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