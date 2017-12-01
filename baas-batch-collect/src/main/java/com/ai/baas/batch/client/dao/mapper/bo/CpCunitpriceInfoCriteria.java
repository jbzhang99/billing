package com.ai.baas.batch.client.dao.mapper.bo;

import java.util.ArrayList;
import java.util.List;

public class CpCunitpriceInfoCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public CpCunitpriceInfoCriteria() {
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

        public Criteria andCunitPriceCodeIsNull() {
            addCriterion("CUNIT_PRICE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCunitPriceCodeIsNotNull() {
            addCriterion("CUNIT_PRICE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCunitPriceCodeEqualTo(String value) {
            addCriterion("CUNIT_PRICE_CODE =", value, "cunitPriceCode");
            return (Criteria) this;
        }

        public Criteria andCunitPriceCodeNotEqualTo(String value) {
            addCriterion("CUNIT_PRICE_CODE <>", value, "cunitPriceCode");
            return (Criteria) this;
        }

        public Criteria andCunitPriceCodeGreaterThan(String value) {
            addCriterion("CUNIT_PRICE_CODE >", value, "cunitPriceCode");
            return (Criteria) this;
        }

        public Criteria andCunitPriceCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CUNIT_PRICE_CODE >=", value, "cunitPriceCode");
            return (Criteria) this;
        }

        public Criteria andCunitPriceCodeLessThan(String value) {
            addCriterion("CUNIT_PRICE_CODE <", value, "cunitPriceCode");
            return (Criteria) this;
        }

        public Criteria andCunitPriceCodeLessThanOrEqualTo(String value) {
            addCriterion("CUNIT_PRICE_CODE <=", value, "cunitPriceCode");
            return (Criteria) this;
        }

        public Criteria andCunitPriceCodeLike(String value) {
            addCriterion("CUNIT_PRICE_CODE like", value, "cunitPriceCode");
            return (Criteria) this;
        }

        public Criteria andCunitPriceCodeNotLike(String value) {
            addCriterion("CUNIT_PRICE_CODE not like", value, "cunitPriceCode");
            return (Criteria) this;
        }

        public Criteria andCunitPriceCodeIn(List<String> values) {
            addCriterion("CUNIT_PRICE_CODE in", values, "cunitPriceCode");
            return (Criteria) this;
        }

        public Criteria andCunitPriceCodeNotIn(List<String> values) {
            addCriterion("CUNIT_PRICE_CODE not in", values, "cunitPriceCode");
            return (Criteria) this;
        }

        public Criteria andCunitPriceCodeBetween(String value1, String value2) {
            addCriterion("CUNIT_PRICE_CODE between", value1, value2, "cunitPriceCode");
            return (Criteria) this;
        }

        public Criteria andCunitPriceCodeNotBetween(String value1, String value2) {
            addCriterion("CUNIT_PRICE_CODE not between", value1, value2, "cunitPriceCode");
            return (Criteria) this;
        }

        public Criteria andPriceNameIsNull() {
            addCriterion("PRICE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPriceNameIsNotNull() {
            addCriterion("PRICE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPriceNameEqualTo(String value) {
            addCriterion("PRICE_NAME =", value, "priceName");
            return (Criteria) this;
        }

        public Criteria andPriceNameNotEqualTo(String value) {
            addCriterion("PRICE_NAME <>", value, "priceName");
            return (Criteria) this;
        }

        public Criteria andPriceNameGreaterThan(String value) {
            addCriterion("PRICE_NAME >", value, "priceName");
            return (Criteria) this;
        }

        public Criteria andPriceNameGreaterThanOrEqualTo(String value) {
            addCriterion("PRICE_NAME >=", value, "priceName");
            return (Criteria) this;
        }

        public Criteria andPriceNameLessThan(String value) {
            addCriterion("PRICE_NAME <", value, "priceName");
            return (Criteria) this;
        }

        public Criteria andPriceNameLessThanOrEqualTo(String value) {
            addCriterion("PRICE_NAME <=", value, "priceName");
            return (Criteria) this;
        }

        public Criteria andPriceNameLike(String value) {
            addCriterion("PRICE_NAME like", value, "priceName");
            return (Criteria) this;
        }

        public Criteria andPriceNameNotLike(String value) {
            addCriterion("PRICE_NAME not like", value, "priceName");
            return (Criteria) this;
        }

        public Criteria andPriceNameIn(List<String> values) {
            addCriterion("PRICE_NAME in", values, "priceName");
            return (Criteria) this;
        }

        public Criteria andPriceNameNotIn(List<String> values) {
            addCriterion("PRICE_NAME not in", values, "priceName");
            return (Criteria) this;
        }

        public Criteria andPriceNameBetween(String value1, String value2) {
            addCriterion("PRICE_NAME between", value1, value2, "priceName");
            return (Criteria) this;
        }

        public Criteria andPriceNameNotBetween(String value1, String value2) {
            addCriterion("PRICE_NAME not between", value1, value2, "priceName");
            return (Criteria) this;
        }

        public Criteria andPriceProductTypeIsNull() {
            addCriterion("PRICE_PRODUCT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPriceProductTypeIsNotNull() {
            addCriterion("PRICE_PRODUCT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPriceProductTypeEqualTo(String value) {
            addCriterion("PRICE_PRODUCT_TYPE =", value, "priceProductType");
            return (Criteria) this;
        }

        public Criteria andPriceProductTypeNotEqualTo(String value) {
            addCriterion("PRICE_PRODUCT_TYPE <>", value, "priceProductType");
            return (Criteria) this;
        }

        public Criteria andPriceProductTypeGreaterThan(String value) {
            addCriterion("PRICE_PRODUCT_TYPE >", value, "priceProductType");
            return (Criteria) this;
        }

        public Criteria andPriceProductTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PRICE_PRODUCT_TYPE >=", value, "priceProductType");
            return (Criteria) this;
        }

        public Criteria andPriceProductTypeLessThan(String value) {
            addCriterion("PRICE_PRODUCT_TYPE <", value, "priceProductType");
            return (Criteria) this;
        }

        public Criteria andPriceProductTypeLessThanOrEqualTo(String value) {
            addCriterion("PRICE_PRODUCT_TYPE <=", value, "priceProductType");
            return (Criteria) this;
        }

        public Criteria andPriceProductTypeLike(String value) {
            addCriterion("PRICE_PRODUCT_TYPE like", value, "priceProductType");
            return (Criteria) this;
        }

        public Criteria andPriceProductTypeNotLike(String value) {
            addCriterion("PRICE_PRODUCT_TYPE not like", value, "priceProductType");
            return (Criteria) this;
        }

        public Criteria andPriceProductTypeIn(List<String> values) {
            addCriterion("PRICE_PRODUCT_TYPE in", values, "priceProductType");
            return (Criteria) this;
        }

        public Criteria andPriceProductTypeNotIn(List<String> values) {
            addCriterion("PRICE_PRODUCT_TYPE not in", values, "priceProductType");
            return (Criteria) this;
        }

        public Criteria andPriceProductTypeBetween(String value1, String value2) {
            addCriterion("PRICE_PRODUCT_TYPE between", value1, value2, "priceProductType");
            return (Criteria) this;
        }

        public Criteria andPriceProductTypeNotBetween(String value1, String value2) {
            addCriterion("PRICE_PRODUCT_TYPE not between", value1, value2, "priceProductType");
            return (Criteria) this;
        }

        public Criteria andUnitTypeIsNull() {
            addCriterion("UNIT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andUnitTypeIsNotNull() {
            addCriterion("UNIT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andUnitTypeEqualTo(String value) {
            addCriterion("UNIT_TYPE =", value, "unitType");
            return (Criteria) this;
        }

        public Criteria andUnitTypeNotEqualTo(String value) {
            addCriterion("UNIT_TYPE <>", value, "unitType");
            return (Criteria) this;
        }

        public Criteria andUnitTypeGreaterThan(String value) {
            addCriterion("UNIT_TYPE >", value, "unitType");
            return (Criteria) this;
        }

        public Criteria andUnitTypeGreaterThanOrEqualTo(String value) {
            addCriterion("UNIT_TYPE >=", value, "unitType");
            return (Criteria) this;
        }

        public Criteria andUnitTypeLessThan(String value) {
            addCriterion("UNIT_TYPE <", value, "unitType");
            return (Criteria) this;
        }

        public Criteria andUnitTypeLessThanOrEqualTo(String value) {
            addCriterion("UNIT_TYPE <=", value, "unitType");
            return (Criteria) this;
        }

        public Criteria andUnitTypeLike(String value) {
            addCriterion("UNIT_TYPE like", value, "unitType");
            return (Criteria) this;
        }

        public Criteria andUnitTypeNotLike(String value) {
            addCriterion("UNIT_TYPE not like", value, "unitType");
            return (Criteria) this;
        }

        public Criteria andUnitTypeIn(List<String> values) {
            addCriterion("UNIT_TYPE in", values, "unitType");
            return (Criteria) this;
        }

        public Criteria andUnitTypeNotIn(List<String> values) {
            addCriterion("UNIT_TYPE not in", values, "unitType");
            return (Criteria) this;
        }

        public Criteria andUnitTypeBetween(String value1, String value2) {
            addCriterion("UNIT_TYPE between", value1, value2, "unitType");
            return (Criteria) this;
        }

        public Criteria andUnitTypeNotBetween(String value1, String value2) {
            addCriterion("UNIT_TYPE not between", value1, value2, "unitType");
            return (Criteria) this;
        }

        public Criteria andPriceValueIsNull() {
            addCriterion("PRICE_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andPriceValueIsNotNull() {
            addCriterion("PRICE_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andPriceValueEqualTo(Double value) {
            addCriterion("PRICE_VALUE =", value, "priceValue");
            return (Criteria) this;
        }

        public Criteria andPriceValueNotEqualTo(Double value) {
            addCriterion("PRICE_VALUE <>", value, "priceValue");
            return (Criteria) this;
        }

        public Criteria andPriceValueGreaterThan(Double value) {
            addCriterion("PRICE_VALUE >", value, "priceValue");
            return (Criteria) this;
        }

        public Criteria andPriceValueGreaterThanOrEqualTo(Double value) {
            addCriterion("PRICE_VALUE >=", value, "priceValue");
            return (Criteria) this;
        }

        public Criteria andPriceValueLessThan(Double value) {
            addCriterion("PRICE_VALUE <", value, "priceValue");
            return (Criteria) this;
        }

        public Criteria andPriceValueLessThanOrEqualTo(Double value) {
            addCriterion("PRICE_VALUE <=", value, "priceValue");
            return (Criteria) this;
        }

        public Criteria andPriceValueIn(List<Double> values) {
            addCriterion("PRICE_VALUE in", values, "priceValue");
            return (Criteria) this;
        }

        public Criteria andPriceValueNotIn(List<Double> values) {
            addCriterion("PRICE_VALUE not in", values, "priceValue");
            return (Criteria) this;
        }

        public Criteria andPriceValueBetween(Double value1, Double value2) {
            addCriterion("PRICE_VALUE between", value1, value2, "priceValue");
            return (Criteria) this;
        }

        public Criteria andPriceValueNotBetween(Double value1, Double value2) {
            addCriterion("PRICE_VALUE not between", value1, value2, "priceValue");
            return (Criteria) this;
        }

        public Criteria andFactorCodeIsNull() {
            addCriterion("FACTOR_CODE is null");
            return (Criteria) this;
        }

        public Criteria andFactorCodeIsNotNull() {
            addCriterion("FACTOR_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andFactorCodeEqualTo(String value) {
            addCriterion("FACTOR_CODE =", value, "factorCode");
            return (Criteria) this;
        }

        public Criteria andFactorCodeNotEqualTo(String value) {
            addCriterion("FACTOR_CODE <>", value, "factorCode");
            return (Criteria) this;
        }

        public Criteria andFactorCodeGreaterThan(String value) {
            addCriterion("FACTOR_CODE >", value, "factorCode");
            return (Criteria) this;
        }

        public Criteria andFactorCodeGreaterThanOrEqualTo(String value) {
            addCriterion("FACTOR_CODE >=", value, "factorCode");
            return (Criteria) this;
        }

        public Criteria andFactorCodeLessThan(String value) {
            addCriterion("FACTOR_CODE <", value, "factorCode");
            return (Criteria) this;
        }

        public Criteria andFactorCodeLessThanOrEqualTo(String value) {
            addCriterion("FACTOR_CODE <=", value, "factorCode");
            return (Criteria) this;
        }

        public Criteria andFactorCodeLike(String value) {
            addCriterion("FACTOR_CODE like", value, "factorCode");
            return (Criteria) this;
        }

        public Criteria andFactorCodeNotLike(String value) {
            addCriterion("FACTOR_CODE not like", value, "factorCode");
            return (Criteria) this;
        }

        public Criteria andFactorCodeIn(List<String> values) {
            addCriterion("FACTOR_CODE in", values, "factorCode");
            return (Criteria) this;
        }

        public Criteria andFactorCodeNotIn(List<String> values) {
            addCriterion("FACTOR_CODE not in", values, "factorCode");
            return (Criteria) this;
        }

        public Criteria andFactorCodeBetween(String value1, String value2) {
            addCriterion("FACTOR_CODE between", value1, value2, "factorCode");
            return (Criteria) this;
        }

        public Criteria andFactorCodeNotBetween(String value1, String value2) {
            addCriterion("FACTOR_CODE not between", value1, value2, "factorCode");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeIsNull() {
            addCriterion("SUBJECT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeIsNotNull() {
            addCriterion("SUBJECT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeEqualTo(String value) {
            addCriterion("SUBJECT_CODE =", value, "subjectCode");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeNotEqualTo(String value) {
            addCriterion("SUBJECT_CODE <>", value, "subjectCode");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeGreaterThan(String value) {
            addCriterion("SUBJECT_CODE >", value, "subjectCode");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT_CODE >=", value, "subjectCode");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeLessThan(String value) {
            addCriterion("SUBJECT_CODE <", value, "subjectCode");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT_CODE <=", value, "subjectCode");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeLike(String value) {
            addCriterion("SUBJECT_CODE like", value, "subjectCode");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeNotLike(String value) {
            addCriterion("SUBJECT_CODE not like", value, "subjectCode");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeIn(List<String> values) {
            addCriterion("SUBJECT_CODE in", values, "subjectCode");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeNotIn(List<String> values) {
            addCriterion("SUBJECT_CODE not in", values, "subjectCode");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeBetween(String value1, String value2) {
            addCriterion("SUBJECT_CODE between", value1, value2, "subjectCode");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeNotBetween(String value1, String value2) {
            addCriterion("SUBJECT_CODE not between", value1, value2, "subjectCode");
            return (Criteria) this;
        }

        public Criteria andExtCodeIsNull() {
            addCriterion("EXT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andExtCodeIsNotNull() {
            addCriterion("EXT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andExtCodeEqualTo(String value) {
            addCriterion("EXT_CODE =", value, "extCode");
            return (Criteria) this;
        }

        public Criteria andExtCodeNotEqualTo(String value) {
            addCriterion("EXT_CODE <>", value, "extCode");
            return (Criteria) this;
        }

        public Criteria andExtCodeGreaterThan(String value) {
            addCriterion("EXT_CODE >", value, "extCode");
            return (Criteria) this;
        }

        public Criteria andExtCodeGreaterThanOrEqualTo(String value) {
            addCriterion("EXT_CODE >=", value, "extCode");
            return (Criteria) this;
        }

        public Criteria andExtCodeLessThan(String value) {
            addCriterion("EXT_CODE <", value, "extCode");
            return (Criteria) this;
        }

        public Criteria andExtCodeLessThanOrEqualTo(String value) {
            addCriterion("EXT_CODE <=", value, "extCode");
            return (Criteria) this;
        }

        public Criteria andExtCodeLike(String value) {
            addCriterion("EXT_CODE like", value, "extCode");
            return (Criteria) this;
        }

        public Criteria andExtCodeNotLike(String value) {
            addCriterion("EXT_CODE not like", value, "extCode");
            return (Criteria) this;
        }

        public Criteria andExtCodeIn(List<String> values) {
            addCriterion("EXT_CODE in", values, "extCode");
            return (Criteria) this;
        }

        public Criteria andExtCodeNotIn(List<String> values) {
            addCriterion("EXT_CODE not in", values, "extCode");
            return (Criteria) this;
        }

        public Criteria andExtCodeBetween(String value1, String value2) {
            addCriterion("EXT_CODE between", value1, value2, "extCode");
            return (Criteria) this;
        }

        public Criteria andExtCodeNotBetween(String value1, String value2) {
            addCriterion("EXT_CODE not between", value1, value2, "extCode");
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