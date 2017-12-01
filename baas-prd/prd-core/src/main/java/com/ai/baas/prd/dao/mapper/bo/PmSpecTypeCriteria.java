package com.ai.baas.prd.dao.mapper.bo;

import java.util.ArrayList;
import java.util.List;

public class PmSpecTypeCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public PmSpecTypeCriteria() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
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

        public Criteria andCategoryIdIsNull() {
            addCriterion("CATEGORY_ID is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNotNull() {
            addCriterion("CATEGORY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdEqualTo(String value) {
            addCriterion("CATEGORY_ID =", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotEqualTo(String value) {
            addCriterion("CATEGORY_ID <>", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThan(String value) {
            addCriterion("CATEGORY_ID >", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("CATEGORY_ID >=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThan(String value) {
            addCriterion("CATEGORY_ID <", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThanOrEqualTo(String value) {
            addCriterion("CATEGORY_ID <=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLike(String value) {
            addCriterion("CATEGORY_ID like", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotLike(String value) {
            addCriterion("CATEGORY_ID not like", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIn(List<String> values) {
            addCriterion("CATEGORY_ID in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotIn(List<String> values) {
            addCriterion("CATEGORY_ID not in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdBetween(String value1, String value2) {
            addCriterion("CATEGORY_ID between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotBetween(String value1, String value2) {
            addCriterion("CATEGORY_ID not between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andSpecTypeNameIsNull() {
            addCriterion("SPEC_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSpecTypeNameIsNotNull() {
            addCriterion("SPEC_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSpecTypeNameEqualTo(String value) {
            addCriterion("SPEC_TYPE_NAME =", value, "specTypeName");
            return (Criteria) this;
        }

        public Criteria andSpecTypeNameNotEqualTo(String value) {
            addCriterion("SPEC_TYPE_NAME <>", value, "specTypeName");
            return (Criteria) this;
        }

        public Criteria andSpecTypeNameGreaterThan(String value) {
            addCriterion("SPEC_TYPE_NAME >", value, "specTypeName");
            return (Criteria) this;
        }

        public Criteria andSpecTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("SPEC_TYPE_NAME >=", value, "specTypeName");
            return (Criteria) this;
        }

        public Criteria andSpecTypeNameLessThan(String value) {
            addCriterion("SPEC_TYPE_NAME <", value, "specTypeName");
            return (Criteria) this;
        }

        public Criteria andSpecTypeNameLessThanOrEqualTo(String value) {
            addCriterion("SPEC_TYPE_NAME <=", value, "specTypeName");
            return (Criteria) this;
        }

        public Criteria andSpecTypeNameLike(String value) {
            addCriterion("SPEC_TYPE_NAME like", value, "specTypeName");
            return (Criteria) this;
        }

        public Criteria andSpecTypeNameNotLike(String value) {
            addCriterion("SPEC_TYPE_NAME not like", value, "specTypeName");
            return (Criteria) this;
        }

        public Criteria andSpecTypeNameIn(List<String> values) {
            addCriterion("SPEC_TYPE_NAME in", values, "specTypeName");
            return (Criteria) this;
        }

        public Criteria andSpecTypeNameNotIn(List<String> values) {
            addCriterion("SPEC_TYPE_NAME not in", values, "specTypeName");
            return (Criteria) this;
        }

        public Criteria andSpecTypeNameBetween(String value1, String value2) {
            addCriterion("SPEC_TYPE_NAME between", value1, value2, "specTypeName");
            return (Criteria) this;
        }

        public Criteria andSpecTypeNameNotBetween(String value1, String value2) {
            addCriterion("SPEC_TYPE_NAME not between", value1, value2, "specTypeName");
            return (Criteria) this;
        }

        public Criteria andSpecTypeIdIsNull() {
            addCriterion("SPEC_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andSpecTypeIdIsNotNull() {
            addCriterion("SPEC_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSpecTypeIdEqualTo(String value) {
            addCriterion("SPEC_TYPE_ID =", value, "specTypeId");
            return (Criteria) this;
        }

        public Criteria andSpecTypeIdNotEqualTo(String value) {
            addCriterion("SPEC_TYPE_ID <>", value, "specTypeId");
            return (Criteria) this;
        }

        public Criteria andSpecTypeIdGreaterThan(String value) {
            addCriterion("SPEC_TYPE_ID >", value, "specTypeId");
            return (Criteria) this;
        }

        public Criteria andSpecTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("SPEC_TYPE_ID >=", value, "specTypeId");
            return (Criteria) this;
        }

        public Criteria andSpecTypeIdLessThan(String value) {
            addCriterion("SPEC_TYPE_ID <", value, "specTypeId");
            return (Criteria) this;
        }

        public Criteria andSpecTypeIdLessThanOrEqualTo(String value) {
            addCriterion("SPEC_TYPE_ID <=", value, "specTypeId");
            return (Criteria) this;
        }

        public Criteria andSpecTypeIdLike(String value) {
            addCriterion("SPEC_TYPE_ID like", value, "specTypeId");
            return (Criteria) this;
        }

        public Criteria andSpecTypeIdNotLike(String value) {
            addCriterion("SPEC_TYPE_ID not like", value, "specTypeId");
            return (Criteria) this;
        }

        public Criteria andSpecTypeIdIn(List<String> values) {
            addCriterion("SPEC_TYPE_ID in", values, "specTypeId");
            return (Criteria) this;
        }

        public Criteria andSpecTypeIdNotIn(List<String> values) {
            addCriterion("SPEC_TYPE_ID not in", values, "specTypeId");
            return (Criteria) this;
        }

        public Criteria andSpecTypeIdBetween(String value1, String value2) {
            addCriterion("SPEC_TYPE_ID between", value1, value2, "specTypeId");
            return (Criteria) this;
        }

        public Criteria andSpecTypeIdNotBetween(String value1, String value2) {
            addCriterion("SPEC_TYPE_ID not between", value1, value2, "specTypeId");
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