package com.ai.baas.dst.dao.mapper.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GiftInfoDetailCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public GiftInfoDetailCriteria() {
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

        public Criteria andDiscountIdIsNull() {
            addCriterion("DISCOUNT_ID is null");
            return (Criteria) this;
        }

        public Criteria andDiscountIdIsNotNull() {
            addCriterion("DISCOUNT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountIdEqualTo(String value) {
            addCriterion("DISCOUNT_ID =", value, "discountId");
            return (Criteria) this;
        }

        public Criteria andDiscountIdNotEqualTo(String value) {
            addCriterion("DISCOUNT_ID <>", value, "discountId");
            return (Criteria) this;
        }

        public Criteria andDiscountIdGreaterThan(String value) {
            addCriterion("DISCOUNT_ID >", value, "discountId");
            return (Criteria) this;
        }

        public Criteria andDiscountIdGreaterThanOrEqualTo(String value) {
            addCriterion("DISCOUNT_ID >=", value, "discountId");
            return (Criteria) this;
        }

        public Criteria andDiscountIdLessThan(String value) {
            addCriterion("DISCOUNT_ID <", value, "discountId");
            return (Criteria) this;
        }

        public Criteria andDiscountIdLessThanOrEqualTo(String value) {
            addCriterion("DISCOUNT_ID <=", value, "discountId");
            return (Criteria) this;
        }

        public Criteria andDiscountIdLike(String value) {
            addCriterion("DISCOUNT_ID like", value, "discountId");
            return (Criteria) this;
        }

        public Criteria andDiscountIdNotLike(String value) {
            addCriterion("DISCOUNT_ID not like", value, "discountId");
            return (Criteria) this;
        }

        public Criteria andDiscountIdIn(List<String> values) {
            addCriterion("DISCOUNT_ID in", values, "discountId");
            return (Criteria) this;
        }

        public Criteria andDiscountIdNotIn(List<String> values) {
            addCriterion("DISCOUNT_ID not in", values, "discountId");
            return (Criteria) this;
        }

        public Criteria andDiscountIdBetween(String value1, String value2) {
            addCriterion("DISCOUNT_ID between", value1, value2, "discountId");
            return (Criteria) this;
        }

        public Criteria andDiscountIdNotBetween(String value1, String value2) {
            addCriterion("DISCOUNT_ID not between", value1, value2, "discountId");
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

        public Criteria andActiveModeIsNull() {
            addCriterion("ACTIVE_MODE is null");
            return (Criteria) this;
        }

        public Criteria andActiveModeIsNotNull() {
            addCriterion("ACTIVE_MODE is not null");
            return (Criteria) this;
        }

        public Criteria andActiveModeEqualTo(String value) {
            addCriterion("ACTIVE_MODE =", value, "activeMode");
            return (Criteria) this;
        }

        public Criteria andActiveModeNotEqualTo(String value) {
            addCriterion("ACTIVE_MODE <>", value, "activeMode");
            return (Criteria) this;
        }

        public Criteria andActiveModeGreaterThan(String value) {
            addCriterion("ACTIVE_MODE >", value, "activeMode");
            return (Criteria) this;
        }

        public Criteria andActiveModeGreaterThanOrEqualTo(String value) {
            addCriterion("ACTIVE_MODE >=", value, "activeMode");
            return (Criteria) this;
        }

        public Criteria andActiveModeLessThan(String value) {
            addCriterion("ACTIVE_MODE <", value, "activeMode");
            return (Criteria) this;
        }

        public Criteria andActiveModeLessThanOrEqualTo(String value) {
            addCriterion("ACTIVE_MODE <=", value, "activeMode");
            return (Criteria) this;
        }

        public Criteria andActiveModeLike(String value) {
            addCriterion("ACTIVE_MODE like", value, "activeMode");
            return (Criteria) this;
        }

        public Criteria andActiveModeNotLike(String value) {
            addCriterion("ACTIVE_MODE not like", value, "activeMode");
            return (Criteria) this;
        }

        public Criteria andActiveModeIn(List<String> values) {
            addCriterion("ACTIVE_MODE in", values, "activeMode");
            return (Criteria) this;
        }

        public Criteria andActiveModeNotIn(List<String> values) {
            addCriterion("ACTIVE_MODE not in", values, "activeMode");
            return (Criteria) this;
        }

        public Criteria andActiveModeBetween(String value1, String value2) {
            addCriterion("ACTIVE_MODE between", value1, value2, "activeMode");
            return (Criteria) this;
        }

        public Criteria andActiveModeNotBetween(String value1, String value2) {
            addCriterion("ACTIVE_MODE not between", value1, value2, "activeMode");
            return (Criteria) this;
        }

        public Criteria andBusinessPeriodIsNull() {
            addCriterion("BUSINESS_PERIOD is null");
            return (Criteria) this;
        }

        public Criteria andBusinessPeriodIsNotNull() {
            addCriterion("BUSINESS_PERIOD is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessPeriodEqualTo(String value) {
            addCriterion("BUSINESS_PERIOD =", value, "businessPeriod");
            return (Criteria) this;
        }

        public Criteria andBusinessPeriodNotEqualTo(String value) {
            addCriterion("BUSINESS_PERIOD <>", value, "businessPeriod");
            return (Criteria) this;
        }

        public Criteria andBusinessPeriodGreaterThan(String value) {
            addCriterion("BUSINESS_PERIOD >", value, "businessPeriod");
            return (Criteria) this;
        }

        public Criteria andBusinessPeriodGreaterThanOrEqualTo(String value) {
            addCriterion("BUSINESS_PERIOD >=", value, "businessPeriod");
            return (Criteria) this;
        }

        public Criteria andBusinessPeriodLessThan(String value) {
            addCriterion("BUSINESS_PERIOD <", value, "businessPeriod");
            return (Criteria) this;
        }

        public Criteria andBusinessPeriodLessThanOrEqualTo(String value) {
            addCriterion("BUSINESS_PERIOD <=", value, "businessPeriod");
            return (Criteria) this;
        }

        public Criteria andBusinessPeriodLike(String value) {
            addCriterion("BUSINESS_PERIOD like", value, "businessPeriod");
            return (Criteria) this;
        }

        public Criteria andBusinessPeriodNotLike(String value) {
            addCriterion("BUSINESS_PERIOD not like", value, "businessPeriod");
            return (Criteria) this;
        }

        public Criteria andBusinessPeriodIn(List<String> values) {
            addCriterion("BUSINESS_PERIOD in", values, "businessPeriod");
            return (Criteria) this;
        }

        public Criteria andBusinessPeriodNotIn(List<String> values) {
            addCriterion("BUSINESS_PERIOD not in", values, "businessPeriod");
            return (Criteria) this;
        }

        public Criteria andBusinessPeriodBetween(String value1, String value2) {
            addCriterion("BUSINESS_PERIOD between", value1, value2, "businessPeriod");
            return (Criteria) this;
        }

        public Criteria andBusinessPeriodNotBetween(String value1, String value2) {
            addCriterion("BUSINESS_PERIOD not between", value1, value2, "businessPeriod");
            return (Criteria) this;
        }

        public Criteria andEffectDateIsNull() {
            addCriterion("EFFECT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andEffectDateIsNotNull() {
            addCriterion("EFFECT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andEffectDateEqualTo(Timestamp value) {
            addCriterion("EFFECT_DATE =", value, "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateNotEqualTo(Timestamp value) {
            addCriterion("EFFECT_DATE <>", value, "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateGreaterThan(Timestamp value) {
            addCriterion("EFFECT_DATE >", value, "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("EFFECT_DATE >=", value, "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateLessThan(Timestamp value) {
            addCriterion("EFFECT_DATE <", value, "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("EFFECT_DATE <=", value, "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateIn(List<Timestamp> values) {
            addCriterion("EFFECT_DATE in", values, "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateNotIn(List<Timestamp> values) {
            addCriterion("EFFECT_DATE not in", values, "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("EFFECT_DATE between", value1, value2, "effectDate");
            return (Criteria) this;
        }

        public Criteria andEffectDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("EFFECT_DATE not between", value1, value2, "effectDate");
            return (Criteria) this;
        }

        public Criteria andCashAmountIsNull() {
            addCriterion("CASH_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andCashAmountIsNotNull() {
            addCriterion("CASH_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andCashAmountEqualTo(String value) {
            addCriterion("CASH_AMOUNT =", value, "cashAmount");
            return (Criteria) this;
        }

        public Criteria andCashAmountNotEqualTo(String value) {
            addCriterion("CASH_AMOUNT <>", value, "cashAmount");
            return (Criteria) this;
        }

        public Criteria andCashAmountGreaterThan(String value) {
            addCriterion("CASH_AMOUNT >", value, "cashAmount");
            return (Criteria) this;
        }

        public Criteria andCashAmountGreaterThanOrEqualTo(String value) {
            addCriterion("CASH_AMOUNT >=", value, "cashAmount");
            return (Criteria) this;
        }

        public Criteria andCashAmountLessThan(String value) {
            addCriterion("CASH_AMOUNT <", value, "cashAmount");
            return (Criteria) this;
        }

        public Criteria andCashAmountLessThanOrEqualTo(String value) {
            addCriterion("CASH_AMOUNT <=", value, "cashAmount");
            return (Criteria) this;
        }

        public Criteria andCashAmountLike(String value) {
            addCriterion("CASH_AMOUNT like", value, "cashAmount");
            return (Criteria) this;
        }

        public Criteria andCashAmountNotLike(String value) {
            addCriterion("CASH_AMOUNT not like", value, "cashAmount");
            return (Criteria) this;
        }

        public Criteria andCashAmountIn(List<String> values) {
            addCriterion("CASH_AMOUNT in", values, "cashAmount");
            return (Criteria) this;
        }

        public Criteria andCashAmountNotIn(List<String> values) {
            addCriterion("CASH_AMOUNT not in", values, "cashAmount");
            return (Criteria) this;
        }

        public Criteria andCashAmountBetween(String value1, String value2) {
            addCriterion("CASH_AMOUNT between", value1, value2, "cashAmount");
            return (Criteria) this;
        }

        public Criteria andCashAmountNotBetween(String value1, String value2) {
            addCriterion("CASH_AMOUNT not between", value1, value2, "cashAmount");
            return (Criteria) this;
        }

        public Criteria andVirtualCoinsNumIsNull() {
            addCriterion("VIRTUAL_COINS_NUM is null");
            return (Criteria) this;
        }

        public Criteria andVirtualCoinsNumIsNotNull() {
            addCriterion("VIRTUAL_COINS_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andVirtualCoinsNumEqualTo(String value) {
            addCriterion("VIRTUAL_COINS_NUM =", value, "virtualCoinsNum");
            return (Criteria) this;
        }

        public Criteria andVirtualCoinsNumNotEqualTo(String value) {
            addCriterion("VIRTUAL_COINS_NUM <>", value, "virtualCoinsNum");
            return (Criteria) this;
        }

        public Criteria andVirtualCoinsNumGreaterThan(String value) {
            addCriterion("VIRTUAL_COINS_NUM >", value, "virtualCoinsNum");
            return (Criteria) this;
        }

        public Criteria andVirtualCoinsNumGreaterThanOrEqualTo(String value) {
            addCriterion("VIRTUAL_COINS_NUM >=", value, "virtualCoinsNum");
            return (Criteria) this;
        }

        public Criteria andVirtualCoinsNumLessThan(String value) {
            addCriterion("VIRTUAL_COINS_NUM <", value, "virtualCoinsNum");
            return (Criteria) this;
        }

        public Criteria andVirtualCoinsNumLessThanOrEqualTo(String value) {
            addCriterion("VIRTUAL_COINS_NUM <=", value, "virtualCoinsNum");
            return (Criteria) this;
        }

        public Criteria andVirtualCoinsNumLike(String value) {
            addCriterion("VIRTUAL_COINS_NUM like", value, "virtualCoinsNum");
            return (Criteria) this;
        }

        public Criteria andVirtualCoinsNumNotLike(String value) {
            addCriterion("VIRTUAL_COINS_NUM not like", value, "virtualCoinsNum");
            return (Criteria) this;
        }

        public Criteria andVirtualCoinsNumIn(List<String> values) {
            addCriterion("VIRTUAL_COINS_NUM in", values, "virtualCoinsNum");
            return (Criteria) this;
        }

        public Criteria andVirtualCoinsNumNotIn(List<String> values) {
            addCriterion("VIRTUAL_COINS_NUM not in", values, "virtualCoinsNum");
            return (Criteria) this;
        }

        public Criteria andVirtualCoinsNumBetween(String value1, String value2) {
            addCriterion("VIRTUAL_COINS_NUM between", value1, value2, "virtualCoinsNum");
            return (Criteria) this;
        }

        public Criteria andVirtualCoinsNumNotBetween(String value1, String value2) {
            addCriterion("VIRTUAL_COINS_NUM not between", value1, value2, "virtualCoinsNum");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Timestamp value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Timestamp value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Timestamp value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Timestamp value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Timestamp> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Timestamp> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIsNull() {
            addCriterion("OPERATOR_ID is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIsNotNull() {
            addCriterion("OPERATOR_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorIdEqualTo(String value) {
            addCriterion("OPERATOR_ID =", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotEqualTo(String value) {
            addCriterion("OPERATOR_ID <>", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdGreaterThan(String value) {
            addCriterion("OPERATOR_ID >", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdGreaterThanOrEqualTo(String value) {
            addCriterion("OPERATOR_ID >=", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLessThan(String value) {
            addCriterion("OPERATOR_ID <", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLessThanOrEqualTo(String value) {
            addCriterion("OPERATOR_ID <=", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLike(String value) {
            addCriterion("OPERATOR_ID like", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotLike(String value) {
            addCriterion("OPERATOR_ID not like", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIn(List<String> values) {
            addCriterion("OPERATOR_ID in", values, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotIn(List<String> values) {
            addCriterion("OPERATOR_ID not in", values, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdBetween(String value1, String value2) {
            addCriterion("OPERATOR_ID between", value1, value2, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotBetween(String value1, String value2) {
            addCriterion("OPERATOR_ID not between", value1, value2, "operatorId");
            return (Criteria) this;
        }

        public Criteria andGiftTypeIsNull() {
            addCriterion("GIFT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andGiftTypeIsNotNull() {
            addCriterion("GIFT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andGiftTypeEqualTo(String value) {
            addCriterion("GIFT_TYPE =", value, "giftType");
            return (Criteria) this;
        }

        public Criteria andGiftTypeNotEqualTo(String value) {
            addCriterion("GIFT_TYPE <>", value, "giftType");
            return (Criteria) this;
        }

        public Criteria andGiftTypeGreaterThan(String value) {
            addCriterion("GIFT_TYPE >", value, "giftType");
            return (Criteria) this;
        }

        public Criteria andGiftTypeGreaterThanOrEqualTo(String value) {
            addCriterion("GIFT_TYPE >=", value, "giftType");
            return (Criteria) this;
        }

        public Criteria andGiftTypeLessThan(String value) {
            addCriterion("GIFT_TYPE <", value, "giftType");
            return (Criteria) this;
        }

        public Criteria andGiftTypeLessThanOrEqualTo(String value) {
            addCriterion("GIFT_TYPE <=", value, "giftType");
            return (Criteria) this;
        }

        public Criteria andGiftTypeLike(String value) {
            addCriterion("GIFT_TYPE like", value, "giftType");
            return (Criteria) this;
        }

        public Criteria andGiftTypeNotLike(String value) {
            addCriterion("GIFT_TYPE not like", value, "giftType");
            return (Criteria) this;
        }

        public Criteria andGiftTypeIn(List<String> values) {
            addCriterion("GIFT_TYPE in", values, "giftType");
            return (Criteria) this;
        }

        public Criteria andGiftTypeNotIn(List<String> values) {
            addCriterion("GIFT_TYPE not in", values, "giftType");
            return (Criteria) this;
        }

        public Criteria andGiftTypeBetween(String value1, String value2) {
            addCriterion("GIFT_TYPE between", value1, value2, "giftType");
            return (Criteria) this;
        }

        public Criteria andGiftTypeNotBetween(String value1, String value2) {
            addCriterion("GIFT_TYPE not between", value1, value2, "giftType");
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