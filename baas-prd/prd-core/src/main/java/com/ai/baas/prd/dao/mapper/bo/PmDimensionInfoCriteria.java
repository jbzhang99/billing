package com.ai.baas.prd.dao.mapper.bo;

import java.util.ArrayList;
import java.util.List;

public class PmDimensionInfoCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public PmDimensionInfoCriteria() {
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

        public Criteria andTradeNameIsNull() {
            addCriterion("TRADE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTradeNameIsNotNull() {
            addCriterion("TRADE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTradeNameEqualTo(String value) {
            addCriterion("TRADE_NAME =", value, "tradeName");
            return (Criteria) this;
        }

        public Criteria andTradeNameNotEqualTo(String value) {
            addCriterion("TRADE_NAME <>", value, "tradeName");
            return (Criteria) this;
        }

        public Criteria andTradeNameGreaterThan(String value) {
            addCriterion("TRADE_NAME >", value, "tradeName");
            return (Criteria) this;
        }

        public Criteria andTradeNameGreaterThanOrEqualTo(String value) {
            addCriterion("TRADE_NAME >=", value, "tradeName");
            return (Criteria) this;
        }

        public Criteria andTradeNameLessThan(String value) {
            addCriterion("TRADE_NAME <", value, "tradeName");
            return (Criteria) this;
        }

        public Criteria andTradeNameLessThanOrEqualTo(String value) {
            addCriterion("TRADE_NAME <=", value, "tradeName");
            return (Criteria) this;
        }

        public Criteria andTradeNameLike(String value) {
            addCriterion("TRADE_NAME like", value, "tradeName");
            return (Criteria) this;
        }

        public Criteria andTradeNameNotLike(String value) {
            addCriterion("TRADE_NAME not like", value, "tradeName");
            return (Criteria) this;
        }

        public Criteria andTradeNameIn(List<String> values) {
            addCriterion("TRADE_NAME in", values, "tradeName");
            return (Criteria) this;
        }

        public Criteria andTradeNameNotIn(List<String> values) {
            addCriterion("TRADE_NAME not in", values, "tradeName");
            return (Criteria) this;
        }

        public Criteria andTradeNameBetween(String value1, String value2) {
            addCriterion("TRADE_NAME between", value1, value2, "tradeName");
            return (Criteria) this;
        }

        public Criteria andTradeNameNotBetween(String value1, String value2) {
            addCriterion("TRADE_NAME not between", value1, value2, "tradeName");
            return (Criteria) this;
        }

        public Criteria andTradeCodeIsNull() {
            addCriterion("TRADE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andTradeCodeIsNotNull() {
            addCriterion("TRADE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andTradeCodeEqualTo(String value) {
            addCriterion("TRADE_CODE =", value, "tradeCode");
            return (Criteria) this;
        }

        public Criteria andTradeCodeNotEqualTo(String value) {
            addCriterion("TRADE_CODE <>", value, "tradeCode");
            return (Criteria) this;
        }

        public Criteria andTradeCodeGreaterThan(String value) {
            addCriterion("TRADE_CODE >", value, "tradeCode");
            return (Criteria) this;
        }

        public Criteria andTradeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("TRADE_CODE >=", value, "tradeCode");
            return (Criteria) this;
        }

        public Criteria andTradeCodeLessThan(String value) {
            addCriterion("TRADE_CODE <", value, "tradeCode");
            return (Criteria) this;
        }

        public Criteria andTradeCodeLessThanOrEqualTo(String value) {
            addCriterion("TRADE_CODE <=", value, "tradeCode");
            return (Criteria) this;
        }

        public Criteria andTradeCodeLike(String value) {
            addCriterion("TRADE_CODE like", value, "tradeCode");
            return (Criteria) this;
        }

        public Criteria andTradeCodeNotLike(String value) {
            addCriterion("TRADE_CODE not like", value, "tradeCode");
            return (Criteria) this;
        }

        public Criteria andTradeCodeIn(List<String> values) {
            addCriterion("TRADE_CODE in", values, "tradeCode");
            return (Criteria) this;
        }

        public Criteria andTradeCodeNotIn(List<String> values) {
            addCriterion("TRADE_CODE not in", values, "tradeCode");
            return (Criteria) this;
        }

        public Criteria andTradeCodeBetween(String value1, String value2) {
            addCriterion("TRADE_CODE between", value1, value2, "tradeCode");
            return (Criteria) this;
        }

        public Criteria andTradeCodeNotBetween(String value1, String value2) {
            addCriterion("TRADE_CODE not between", value1, value2, "tradeCode");
            return (Criteria) this;
        }

        public Criteria andMainProductNameIsNull() {
            addCriterion("MAIN_PRODUCT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMainProductNameIsNotNull() {
            addCriterion("MAIN_PRODUCT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMainProductNameEqualTo(String value) {
            addCriterion("MAIN_PRODUCT_NAME =", value, "mainProductName");
            return (Criteria) this;
        }

        public Criteria andMainProductNameNotEqualTo(String value) {
            addCriterion("MAIN_PRODUCT_NAME <>", value, "mainProductName");
            return (Criteria) this;
        }

        public Criteria andMainProductNameGreaterThan(String value) {
            addCriterion("MAIN_PRODUCT_NAME >", value, "mainProductName");
            return (Criteria) this;
        }

        public Criteria andMainProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("MAIN_PRODUCT_NAME >=", value, "mainProductName");
            return (Criteria) this;
        }

        public Criteria andMainProductNameLessThan(String value) {
            addCriterion("MAIN_PRODUCT_NAME <", value, "mainProductName");
            return (Criteria) this;
        }

        public Criteria andMainProductNameLessThanOrEqualTo(String value) {
            addCriterion("MAIN_PRODUCT_NAME <=", value, "mainProductName");
            return (Criteria) this;
        }

        public Criteria andMainProductNameLike(String value) {
            addCriterion("MAIN_PRODUCT_NAME like", value, "mainProductName");
            return (Criteria) this;
        }

        public Criteria andMainProductNameNotLike(String value) {
            addCriterion("MAIN_PRODUCT_NAME not like", value, "mainProductName");
            return (Criteria) this;
        }

        public Criteria andMainProductNameIn(List<String> values) {
            addCriterion("MAIN_PRODUCT_NAME in", values, "mainProductName");
            return (Criteria) this;
        }

        public Criteria andMainProductNameNotIn(List<String> values) {
            addCriterion("MAIN_PRODUCT_NAME not in", values, "mainProductName");
            return (Criteria) this;
        }

        public Criteria andMainProductNameBetween(String value1, String value2) {
            addCriterion("MAIN_PRODUCT_NAME between", value1, value2, "mainProductName");
            return (Criteria) this;
        }

        public Criteria andMainProductNameNotBetween(String value1, String value2) {
            addCriterion("MAIN_PRODUCT_NAME not between", value1, value2, "mainProductName");
            return (Criteria) this;
        }

        public Criteria andMainProductCodeIsNull() {
            addCriterion("MAIN_PRODUCT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andMainProductCodeIsNotNull() {
            addCriterion("MAIN_PRODUCT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andMainProductCodeEqualTo(String value) {
            addCriterion("MAIN_PRODUCT_CODE =", value, "mainProductCode");
            return (Criteria) this;
        }

        public Criteria andMainProductCodeNotEqualTo(String value) {
            addCriterion("MAIN_PRODUCT_CODE <>", value, "mainProductCode");
            return (Criteria) this;
        }

        public Criteria andMainProductCodeGreaterThan(String value) {
            addCriterion("MAIN_PRODUCT_CODE >", value, "mainProductCode");
            return (Criteria) this;
        }

        public Criteria andMainProductCodeGreaterThanOrEqualTo(String value) {
            addCriterion("MAIN_PRODUCT_CODE >=", value, "mainProductCode");
            return (Criteria) this;
        }

        public Criteria andMainProductCodeLessThan(String value) {
            addCriterion("MAIN_PRODUCT_CODE <", value, "mainProductCode");
            return (Criteria) this;
        }

        public Criteria andMainProductCodeLessThanOrEqualTo(String value) {
            addCriterion("MAIN_PRODUCT_CODE <=", value, "mainProductCode");
            return (Criteria) this;
        }

        public Criteria andMainProductCodeLike(String value) {
            addCriterion("MAIN_PRODUCT_CODE like", value, "mainProductCode");
            return (Criteria) this;
        }

        public Criteria andMainProductCodeNotLike(String value) {
            addCriterion("MAIN_PRODUCT_CODE not like", value, "mainProductCode");
            return (Criteria) this;
        }

        public Criteria andMainProductCodeIn(List<String> values) {
            addCriterion("MAIN_PRODUCT_CODE in", values, "mainProductCode");
            return (Criteria) this;
        }

        public Criteria andMainProductCodeNotIn(List<String> values) {
            addCriterion("MAIN_PRODUCT_CODE not in", values, "mainProductCode");
            return (Criteria) this;
        }

        public Criteria andMainProductCodeBetween(String value1, String value2) {
            addCriterion("MAIN_PRODUCT_CODE between", value1, value2, "mainProductCode");
            return (Criteria) this;
        }

        public Criteria andMainProductCodeNotBetween(String value1, String value2) {
            addCriterion("MAIN_PRODUCT_CODE not between", value1, value2, "mainProductCode");
            return (Criteria) this;
        }

        public Criteria andDimensionNameIsNull() {
            addCriterion("DIMENSION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDimensionNameIsNotNull() {
            addCriterion("DIMENSION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDimensionNameEqualTo(String value) {
            addCriterion("DIMENSION_NAME =", value, "dimensionName");
            return (Criteria) this;
        }

        public Criteria andDimensionNameNotEqualTo(String value) {
            addCriterion("DIMENSION_NAME <>", value, "dimensionName");
            return (Criteria) this;
        }

        public Criteria andDimensionNameGreaterThan(String value) {
            addCriterion("DIMENSION_NAME >", value, "dimensionName");
            return (Criteria) this;
        }

        public Criteria andDimensionNameGreaterThanOrEqualTo(String value) {
            addCriterion("DIMENSION_NAME >=", value, "dimensionName");
            return (Criteria) this;
        }

        public Criteria andDimensionNameLessThan(String value) {
            addCriterion("DIMENSION_NAME <", value, "dimensionName");
            return (Criteria) this;
        }

        public Criteria andDimensionNameLessThanOrEqualTo(String value) {
            addCriterion("DIMENSION_NAME <=", value, "dimensionName");
            return (Criteria) this;
        }

        public Criteria andDimensionNameLike(String value) {
            addCriterion("DIMENSION_NAME like", value, "dimensionName");
            return (Criteria) this;
        }

        public Criteria andDimensionNameNotLike(String value) {
            addCriterion("DIMENSION_NAME not like", value, "dimensionName");
            return (Criteria) this;
        }

        public Criteria andDimensionNameIn(List<String> values) {
            addCriterion("DIMENSION_NAME in", values, "dimensionName");
            return (Criteria) this;
        }

        public Criteria andDimensionNameNotIn(List<String> values) {
            addCriterion("DIMENSION_NAME not in", values, "dimensionName");
            return (Criteria) this;
        }

        public Criteria andDimensionNameBetween(String value1, String value2) {
            addCriterion("DIMENSION_NAME between", value1, value2, "dimensionName");
            return (Criteria) this;
        }

        public Criteria andDimensionNameNotBetween(String value1, String value2) {
            addCriterion("DIMENSION_NAME not between", value1, value2, "dimensionName");
            return (Criteria) this;
        }

        public Criteria andDimensionCodeIsNull() {
            addCriterion("DIMENSION_CODE is null");
            return (Criteria) this;
        }

        public Criteria andDimensionCodeIsNotNull() {
            addCriterion("DIMENSION_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andDimensionCodeEqualTo(String value) {
            addCriterion("DIMENSION_CODE =", value, "dimensionCode");
            return (Criteria) this;
        }

        public Criteria andDimensionCodeNotEqualTo(String value) {
            addCriterion("DIMENSION_CODE <>", value, "dimensionCode");
            return (Criteria) this;
        }

        public Criteria andDimensionCodeGreaterThan(String value) {
            addCriterion("DIMENSION_CODE >", value, "dimensionCode");
            return (Criteria) this;
        }

        public Criteria andDimensionCodeGreaterThanOrEqualTo(String value) {
            addCriterion("DIMENSION_CODE >=", value, "dimensionCode");
            return (Criteria) this;
        }

        public Criteria andDimensionCodeLessThan(String value) {
            addCriterion("DIMENSION_CODE <", value, "dimensionCode");
            return (Criteria) this;
        }

        public Criteria andDimensionCodeLessThanOrEqualTo(String value) {
            addCriterion("DIMENSION_CODE <=", value, "dimensionCode");
            return (Criteria) this;
        }

        public Criteria andDimensionCodeLike(String value) {
            addCriterion("DIMENSION_CODE like", value, "dimensionCode");
            return (Criteria) this;
        }

        public Criteria andDimensionCodeNotLike(String value) {
            addCriterion("DIMENSION_CODE not like", value, "dimensionCode");
            return (Criteria) this;
        }

        public Criteria andDimensionCodeIn(List<String> values) {
            addCriterion("DIMENSION_CODE in", values, "dimensionCode");
            return (Criteria) this;
        }

        public Criteria andDimensionCodeNotIn(List<String> values) {
            addCriterion("DIMENSION_CODE not in", values, "dimensionCode");
            return (Criteria) this;
        }

        public Criteria andDimensionCodeBetween(String value1, String value2) {
            addCriterion("DIMENSION_CODE between", value1, value2, "dimensionCode");
            return (Criteria) this;
        }

        public Criteria andDimensionCodeNotBetween(String value1, String value2) {
            addCriterion("DIMENSION_CODE not between", value1, value2, "dimensionCode");
            return (Criteria) this;
        }

        public Criteria andCommentsIsNull() {
            addCriterion("COMMENTS is null");
            return (Criteria) this;
        }

        public Criteria andCommentsIsNotNull() {
            addCriterion("COMMENTS is not null");
            return (Criteria) this;
        }

        public Criteria andCommentsEqualTo(String value) {
            addCriterion("COMMENTS =", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsNotEqualTo(String value) {
            addCriterion("COMMENTS <>", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsGreaterThan(String value) {
            addCriterion("COMMENTS >", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsGreaterThanOrEqualTo(String value) {
            addCriterion("COMMENTS >=", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsLessThan(String value) {
            addCriterion("COMMENTS <", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsLessThanOrEqualTo(String value) {
            addCriterion("COMMENTS <=", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsLike(String value) {
            addCriterion("COMMENTS like", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsNotLike(String value) {
            addCriterion("COMMENTS not like", value, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsIn(List<String> values) {
            addCriterion("COMMENTS in", values, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsNotIn(List<String> values) {
            addCriterion("COMMENTS not in", values, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsBetween(String value1, String value2) {
            addCriterion("COMMENTS between", value1, value2, "comments");
            return (Criteria) this;
        }

        public Criteria andCommentsNotBetween(String value1, String value2) {
            addCriterion("COMMENTS not between", value1, value2, "comments");
            return (Criteria) this;
        }

        public Criteria andDimensionSeqIsNull() {
            addCriterion("DIMENSION_SEQ is null");
            return (Criteria) this;
        }

        public Criteria andDimensionSeqIsNotNull() {
            addCriterion("DIMENSION_SEQ is not null");
            return (Criteria) this;
        }

        public Criteria andDimensionSeqEqualTo(Integer value) {
            addCriterion("DIMENSION_SEQ =", value, "dimensionSeq");
            return (Criteria) this;
        }

        public Criteria andDimensionSeqNotEqualTo(Integer value) {
            addCriterion("DIMENSION_SEQ <>", value, "dimensionSeq");
            return (Criteria) this;
        }

        public Criteria andDimensionSeqGreaterThan(Integer value) {
            addCriterion("DIMENSION_SEQ >", value, "dimensionSeq");
            return (Criteria) this;
        }

        public Criteria andDimensionSeqGreaterThanOrEqualTo(Integer value) {
            addCriterion("DIMENSION_SEQ >=", value, "dimensionSeq");
            return (Criteria) this;
        }

        public Criteria andDimensionSeqLessThan(Integer value) {
            addCriterion("DIMENSION_SEQ <", value, "dimensionSeq");
            return (Criteria) this;
        }

        public Criteria andDimensionSeqLessThanOrEqualTo(Integer value) {
            addCriterion("DIMENSION_SEQ <=", value, "dimensionSeq");
            return (Criteria) this;
        }

        public Criteria andDimensionSeqIn(List<Integer> values) {
            addCriterion("DIMENSION_SEQ in", values, "dimensionSeq");
            return (Criteria) this;
        }

        public Criteria andDimensionSeqNotIn(List<Integer> values) {
            addCriterion("DIMENSION_SEQ not in", values, "dimensionSeq");
            return (Criteria) this;
        }

        public Criteria andDimensionSeqBetween(Integer value1, Integer value2) {
            addCriterion("DIMENSION_SEQ between", value1, value2, "dimensionSeq");
            return (Criteria) this;
        }

        public Criteria andDimensionSeqNotBetween(Integer value1, Integer value2) {
            addCriterion("DIMENSION_SEQ not between", value1, value2, "dimensionSeq");
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