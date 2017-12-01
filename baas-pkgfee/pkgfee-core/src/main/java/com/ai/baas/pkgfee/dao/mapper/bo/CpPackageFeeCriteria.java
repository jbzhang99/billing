package com.ai.baas.pkgfee.dao.mapper.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CpPackageFeeCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public CpPackageFeeCriteria() {
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

        public Criteria andPackageIdIsNull() {
            addCriterion("PACKAGE_ID is null");
            return (Criteria) this;
        }

        public Criteria andPackageIdIsNotNull() {
            addCriterion("PACKAGE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPackageIdEqualTo(Long value) {
            addCriterion("PACKAGE_ID =", value, "packageId");
            return (Criteria) this;
        }

        public Criteria andPackageIdNotEqualTo(Long value) {
            addCriterion("PACKAGE_ID <>", value, "packageId");
            return (Criteria) this;
        }

        public Criteria andPackageIdGreaterThan(Long value) {
            addCriterion("PACKAGE_ID >", value, "packageId");
            return (Criteria) this;
        }

        public Criteria andPackageIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PACKAGE_ID >=", value, "packageId");
            return (Criteria) this;
        }

        public Criteria andPackageIdLessThan(Long value) {
            addCriterion("PACKAGE_ID <", value, "packageId");
            return (Criteria) this;
        }

        public Criteria andPackageIdLessThanOrEqualTo(Long value) {
            addCriterion("PACKAGE_ID <=", value, "packageId");
            return (Criteria) this;
        }

        public Criteria andPackageIdIn(List<Long> values) {
            addCriterion("PACKAGE_ID in", values, "packageId");
            return (Criteria) this;
        }

        public Criteria andPackageIdNotIn(List<Long> values) {
            addCriterion("PACKAGE_ID not in", values, "packageId");
            return (Criteria) this;
        }

        public Criteria andPackageIdBetween(Long value1, Long value2) {
            addCriterion("PACKAGE_ID between", value1, value2, "packageId");
            return (Criteria) this;
        }

        public Criteria andPackageIdNotBetween(Long value1, Long value2) {
            addCriterion("PACKAGE_ID not between", value1, value2, "packageId");
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

        public Criteria andPriceCodeIsNull() {
            addCriterion("PRICE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andPriceCodeIsNotNull() {
            addCriterion("PRICE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andPriceCodeEqualTo(String value) {
            addCriterion("PRICE_CODE =", value, "priceCode");
            return (Criteria) this;
        }

        public Criteria andPriceCodeNotEqualTo(String value) {
            addCriterion("PRICE_CODE <>", value, "priceCode");
            return (Criteria) this;
        }

        public Criteria andPriceCodeGreaterThan(String value) {
            addCriterion("PRICE_CODE >", value, "priceCode");
            return (Criteria) this;
        }

        public Criteria andPriceCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PRICE_CODE >=", value, "priceCode");
            return (Criteria) this;
        }

        public Criteria andPriceCodeLessThan(String value) {
            addCriterion("PRICE_CODE <", value, "priceCode");
            return (Criteria) this;
        }

        public Criteria andPriceCodeLessThanOrEqualTo(String value) {
            addCriterion("PRICE_CODE <=", value, "priceCode");
            return (Criteria) this;
        }

        public Criteria andPriceCodeLike(String value) {
            addCriterion("PRICE_CODE like", value, "priceCode");
            return (Criteria) this;
        }

        public Criteria andPriceCodeNotLike(String value) {
            addCriterion("PRICE_CODE not like", value, "priceCode");
            return (Criteria) this;
        }

        public Criteria andPriceCodeIn(List<String> values) {
            addCriterion("PRICE_CODE in", values, "priceCode");
            return (Criteria) this;
        }

        public Criteria andPriceCodeNotIn(List<String> values) {
            addCriterion("PRICE_CODE not in", values, "priceCode");
            return (Criteria) this;
        }

        public Criteria andPriceCodeBetween(String value1, String value2) {
            addCriterion("PRICE_CODE between", value1, value2, "priceCode");
            return (Criteria) this;
        }

        public Criteria andPriceCodeNotBetween(String value1, String value2) {
            addCriterion("PRICE_CODE not between", value1, value2, "priceCode");
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

        public Criteria andActiveTimeIsNull() {
            addCriterion("ACTIVE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andActiveTimeIsNotNull() {
            addCriterion("ACTIVE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andActiveTimeEqualTo(Timestamp value) {
            addCriterion("ACTIVE_TIME =", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeNotEqualTo(Timestamp value) {
            addCriterion("ACTIVE_TIME <>", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeGreaterThan(Timestamp value) {
            addCriterion("ACTIVE_TIME >", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("ACTIVE_TIME >=", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeLessThan(Timestamp value) {
            addCriterion("ACTIVE_TIME <", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("ACTIVE_TIME <=", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeIn(List<Timestamp> values) {
            addCriterion("ACTIVE_TIME in", values, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeNotIn(List<Timestamp> values) {
            addCriterion("ACTIVE_TIME not in", values, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("ACTIVE_TIME between", value1, value2, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("ACTIVE_TIME not between", value1, value2, "activeTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeIsNull() {
            addCriterion("INACTIVE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeIsNotNull() {
            addCriterion("INACTIVE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeEqualTo(Timestamp value) {
            addCriterion("INACTIVE_TIME =", value, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeNotEqualTo(Timestamp value) {
            addCriterion("INACTIVE_TIME <>", value, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeGreaterThan(Timestamp value) {
            addCriterion("INACTIVE_TIME >", value, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("INACTIVE_TIME >=", value, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeLessThan(Timestamp value) {
            addCriterion("INACTIVE_TIME <", value, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("INACTIVE_TIME <=", value, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeIn(List<Timestamp> values) {
            addCriterion("INACTIVE_TIME in", values, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeNotIn(List<Timestamp> values) {
            addCriterion("INACTIVE_TIME not in", values, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("INACTIVE_TIME between", value1, value2, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("INACTIVE_TIME not between", value1, value2, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumIsNull() {
            addCriterion("PURCHASE_NUM is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumIsNotNull() {
            addCriterion("PURCHASE_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumEqualTo(String value) {
            addCriterion("PURCHASE_NUM =", value, "purchaseNum");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumNotEqualTo(String value) {
            addCriterion("PURCHASE_NUM <>", value, "purchaseNum");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumGreaterThan(String value) {
            addCriterion("PURCHASE_NUM >", value, "purchaseNum");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumGreaterThanOrEqualTo(String value) {
            addCriterion("PURCHASE_NUM >=", value, "purchaseNum");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumLessThan(String value) {
            addCriterion("PURCHASE_NUM <", value, "purchaseNum");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumLessThanOrEqualTo(String value) {
            addCriterion("PURCHASE_NUM <=", value, "purchaseNum");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumLike(String value) {
            addCriterion("PURCHASE_NUM like", value, "purchaseNum");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumNotLike(String value) {
            addCriterion("PURCHASE_NUM not like", value, "purchaseNum");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumIn(List<String> values) {
            addCriterion("PURCHASE_NUM in", values, "purchaseNum");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumNotIn(List<String> values) {
            addCriterion("PURCHASE_NUM not in", values, "purchaseNum");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumBetween(String value1, String value2) {
            addCriterion("PURCHASE_NUM between", value1, value2, "purchaseNum");
            return (Criteria) this;
        }

        public Criteria andPurchaseNumNotBetween(String value1, String value2) {
            addCriterion("PURCHASE_NUM not between", value1, value2, "purchaseNum");
            return (Criteria) this;
        }

        public Criteria andPurchaseUnitIsNull() {
            addCriterion("PURCHASE_UNIT is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseUnitIsNotNull() {
            addCriterion("PURCHASE_UNIT is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseUnitEqualTo(String value) {
            addCriterion("PURCHASE_UNIT =", value, "purchaseUnit");
            return (Criteria) this;
        }

        public Criteria andPurchaseUnitNotEqualTo(String value) {
            addCriterion("PURCHASE_UNIT <>", value, "purchaseUnit");
            return (Criteria) this;
        }

        public Criteria andPurchaseUnitGreaterThan(String value) {
            addCriterion("PURCHASE_UNIT >", value, "purchaseUnit");
            return (Criteria) this;
        }

        public Criteria andPurchaseUnitGreaterThanOrEqualTo(String value) {
            addCriterion("PURCHASE_UNIT >=", value, "purchaseUnit");
            return (Criteria) this;
        }

        public Criteria andPurchaseUnitLessThan(String value) {
            addCriterion("PURCHASE_UNIT <", value, "purchaseUnit");
            return (Criteria) this;
        }

        public Criteria andPurchaseUnitLessThanOrEqualTo(String value) {
            addCriterion("PURCHASE_UNIT <=", value, "purchaseUnit");
            return (Criteria) this;
        }

        public Criteria andPurchaseUnitLike(String value) {
            addCriterion("PURCHASE_UNIT like", value, "purchaseUnit");
            return (Criteria) this;
        }

        public Criteria andPurchaseUnitNotLike(String value) {
            addCriterion("PURCHASE_UNIT not like", value, "purchaseUnit");
            return (Criteria) this;
        }

        public Criteria andPurchaseUnitIn(List<String> values) {
            addCriterion("PURCHASE_UNIT in", values, "purchaseUnit");
            return (Criteria) this;
        }

        public Criteria andPurchaseUnitNotIn(List<String> values) {
            addCriterion("PURCHASE_UNIT not in", values, "purchaseUnit");
            return (Criteria) this;
        }

        public Criteria andPurchaseUnitBetween(String value1, String value2) {
            addCriterion("PURCHASE_UNIT between", value1, value2, "purchaseUnit");
            return (Criteria) this;
        }

        public Criteria andPurchaseUnitNotBetween(String value1, String value2) {
            addCriterion("PURCHASE_UNIT not between", value1, value2, "purchaseUnit");
            return (Criteria) this;
        }

        public Criteria andCycleTypeIsNull() {
            addCriterion("CYCLE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCycleTypeIsNotNull() {
            addCriterion("CYCLE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCycleTypeEqualTo(String value) {
            addCriterion("CYCLE_TYPE =", value, "cycleType");
            return (Criteria) this;
        }

        public Criteria andCycleTypeNotEqualTo(String value) {
            addCriterion("CYCLE_TYPE <>", value, "cycleType");
            return (Criteria) this;
        }

        public Criteria andCycleTypeGreaterThan(String value) {
            addCriterion("CYCLE_TYPE >", value, "cycleType");
            return (Criteria) this;
        }

        public Criteria andCycleTypeGreaterThanOrEqualTo(String value) {
            addCriterion("CYCLE_TYPE >=", value, "cycleType");
            return (Criteria) this;
        }

        public Criteria andCycleTypeLessThan(String value) {
            addCriterion("CYCLE_TYPE <", value, "cycleType");
            return (Criteria) this;
        }

        public Criteria andCycleTypeLessThanOrEqualTo(String value) {
            addCriterion("CYCLE_TYPE <=", value, "cycleType");
            return (Criteria) this;
        }

        public Criteria andCycleTypeLike(String value) {
            addCriterion("CYCLE_TYPE like", value, "cycleType");
            return (Criteria) this;
        }

        public Criteria andCycleTypeNotLike(String value) {
            addCriterion("CYCLE_TYPE not like", value, "cycleType");
            return (Criteria) this;
        }

        public Criteria andCycleTypeIn(List<String> values) {
            addCriterion("CYCLE_TYPE in", values, "cycleType");
            return (Criteria) this;
        }

        public Criteria andCycleTypeNotIn(List<String> values) {
            addCriterion("CYCLE_TYPE not in", values, "cycleType");
            return (Criteria) this;
        }

        public Criteria andCycleTypeBetween(String value1, String value2) {
            addCriterion("CYCLE_TYPE between", value1, value2, "cycleType");
            return (Criteria) this;
        }

        public Criteria andCycleTypeNotBetween(String value1, String value2) {
            addCriterion("CYCLE_TYPE not between", value1, value2, "cycleType");
            return (Criteria) this;
        }

        public Criteria andCycleIntervalIsNull() {
            addCriterion("CYCLE_INTERVAL is null");
            return (Criteria) this;
        }

        public Criteria andCycleIntervalIsNotNull() {
            addCriterion("CYCLE_INTERVAL is not null");
            return (Criteria) this;
        }

        public Criteria andCycleIntervalEqualTo(Long value) {
            addCriterion("CYCLE_INTERVAL =", value, "cycleInterval");
            return (Criteria) this;
        }

        public Criteria andCycleIntervalNotEqualTo(Long value) {
            addCriterion("CYCLE_INTERVAL <>", value, "cycleInterval");
            return (Criteria) this;
        }

        public Criteria andCycleIntervalGreaterThan(Long value) {
            addCriterion("CYCLE_INTERVAL >", value, "cycleInterval");
            return (Criteria) this;
        }

        public Criteria andCycleIntervalGreaterThanOrEqualTo(Long value) {
            addCriterion("CYCLE_INTERVAL >=", value, "cycleInterval");
            return (Criteria) this;
        }

        public Criteria andCycleIntervalLessThan(Long value) {
            addCriterion("CYCLE_INTERVAL <", value, "cycleInterval");
            return (Criteria) this;
        }

        public Criteria andCycleIntervalLessThanOrEqualTo(Long value) {
            addCriterion("CYCLE_INTERVAL <=", value, "cycleInterval");
            return (Criteria) this;
        }

        public Criteria andCycleIntervalIn(List<Long> values) {
            addCriterion("CYCLE_INTERVAL in", values, "cycleInterval");
            return (Criteria) this;
        }

        public Criteria andCycleIntervalNotIn(List<Long> values) {
            addCriterion("CYCLE_INTERVAL not in", values, "cycleInterval");
            return (Criteria) this;
        }

        public Criteria andCycleIntervalBetween(Long value1, Long value2) {
            addCriterion("CYCLE_INTERVAL between", value1, value2, "cycleInterval");
            return (Criteria) this;
        }

        public Criteria andCycleIntervalNotBetween(Long value1, Long value2) {
            addCriterion("CYCLE_INTERVAL not between", value1, value2, "cycleInterval");
            return (Criteria) this;
        }

        public Criteria andCalExprIsNull() {
            addCriterion("CAL_EXPR is null");
            return (Criteria) this;
        }

        public Criteria andCalExprIsNotNull() {
            addCriterion("CAL_EXPR is not null");
            return (Criteria) this;
        }

        public Criteria andCalExprEqualTo(String value) {
            addCriterion("CAL_EXPR =", value, "calExpr");
            return (Criteria) this;
        }

        public Criteria andCalExprNotEqualTo(String value) {
            addCriterion("CAL_EXPR <>", value, "calExpr");
            return (Criteria) this;
        }

        public Criteria andCalExprGreaterThan(String value) {
            addCriterion("CAL_EXPR >", value, "calExpr");
            return (Criteria) this;
        }

        public Criteria andCalExprGreaterThanOrEqualTo(String value) {
            addCriterion("CAL_EXPR >=", value, "calExpr");
            return (Criteria) this;
        }

        public Criteria andCalExprLessThan(String value) {
            addCriterion("CAL_EXPR <", value, "calExpr");
            return (Criteria) this;
        }

        public Criteria andCalExprLessThanOrEqualTo(String value) {
            addCriterion("CAL_EXPR <=", value, "calExpr");
            return (Criteria) this;
        }

        public Criteria andCalExprLike(String value) {
            addCriterion("CAL_EXPR like", value, "calExpr");
            return (Criteria) this;
        }

        public Criteria andCalExprNotLike(String value) {
            addCriterion("CAL_EXPR not like", value, "calExpr");
            return (Criteria) this;
        }

        public Criteria andCalExprIn(List<String> values) {
            addCriterion("CAL_EXPR in", values, "calExpr");
            return (Criteria) this;
        }

        public Criteria andCalExprNotIn(List<String> values) {
            addCriterion("CAL_EXPR not in", values, "calExpr");
            return (Criteria) this;
        }

        public Criteria andCalExprBetween(String value1, String value2) {
            addCriterion("CAL_EXPR between", value1, value2, "calExpr");
            return (Criteria) this;
        }

        public Criteria andCalExprNotBetween(String value1, String value2) {
            addCriterion("CAL_EXPR not between", value1, value2, "calExpr");
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

        public Criteria andCreateTypeIsNull() {
            addCriterion("CREATE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCreateTypeIsNotNull() {
            addCriterion("CREATE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTypeEqualTo(Timestamp value) {
            addCriterion("CREATE_TYPE =", value, "createType");
            return (Criteria) this;
        }

        public Criteria andCreateTypeNotEqualTo(Timestamp value) {
            addCriterion("CREATE_TYPE <>", value, "createType");
            return (Criteria) this;
        }

        public Criteria andCreateTypeGreaterThan(Timestamp value) {
            addCriterion("CREATE_TYPE >", value, "createType");
            return (Criteria) this;
        }

        public Criteria andCreateTypeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("CREATE_TYPE >=", value, "createType");
            return (Criteria) this;
        }

        public Criteria andCreateTypeLessThan(Timestamp value) {
            addCriterion("CREATE_TYPE <", value, "createType");
            return (Criteria) this;
        }

        public Criteria andCreateTypeLessThanOrEqualTo(Timestamp value) {
            addCriterion("CREATE_TYPE <=", value, "createType");
            return (Criteria) this;
        }

        public Criteria andCreateTypeIn(List<Timestamp> values) {
            addCriterion("CREATE_TYPE in", values, "createType");
            return (Criteria) this;
        }

        public Criteria andCreateTypeNotIn(List<Timestamp> values) {
            addCriterion("CREATE_TYPE not in", values, "createType");
            return (Criteria) this;
        }

        public Criteria andCreateTypeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CREATE_TYPE between", value1, value2, "createType");
            return (Criteria) this;
        }

        public Criteria andCreateTypeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CREATE_TYPE not between", value1, value2, "createType");
            return (Criteria) this;
        }

        public Criteria andAutoRenewIsNull() {
            addCriterion("AUTO_RENEW is null");
            return (Criteria) this;
        }

        public Criteria andAutoRenewIsNotNull() {
            addCriterion("AUTO_RENEW is not null");
            return (Criteria) this;
        }

        public Criteria andAutoRenewEqualTo(String value) {
            addCriterion("AUTO_RENEW =", value, "autoRenew");
            return (Criteria) this;
        }

        public Criteria andAutoRenewNotEqualTo(String value) {
            addCriterion("AUTO_RENEW <>", value, "autoRenew");
            return (Criteria) this;
        }

        public Criteria andAutoRenewGreaterThan(String value) {
            addCriterion("AUTO_RENEW >", value, "autoRenew");
            return (Criteria) this;
        }

        public Criteria andAutoRenewGreaterThanOrEqualTo(String value) {
            addCriterion("AUTO_RENEW >=", value, "autoRenew");
            return (Criteria) this;
        }

        public Criteria andAutoRenewLessThan(String value) {
            addCriterion("AUTO_RENEW <", value, "autoRenew");
            return (Criteria) this;
        }

        public Criteria andAutoRenewLessThanOrEqualTo(String value) {
            addCriterion("AUTO_RENEW <=", value, "autoRenew");
            return (Criteria) this;
        }

        public Criteria andAutoRenewLike(String value) {
            addCriterion("AUTO_RENEW like", value, "autoRenew");
            return (Criteria) this;
        }

        public Criteria andAutoRenewNotLike(String value) {
            addCriterion("AUTO_RENEW not like", value, "autoRenew");
            return (Criteria) this;
        }

        public Criteria andAutoRenewIn(List<String> values) {
            addCriterion("AUTO_RENEW in", values, "autoRenew");
            return (Criteria) this;
        }

        public Criteria andAutoRenewNotIn(List<String> values) {
            addCriterion("AUTO_RENEW not in", values, "autoRenew");
            return (Criteria) this;
        }

        public Criteria andAutoRenewBetween(String value1, String value2) {
            addCriterion("AUTO_RENEW between", value1, value2, "autoRenew");
            return (Criteria) this;
        }

        public Criteria andAutoRenewNotBetween(String value1, String value2) {
            addCriterion("AUTO_RENEW not between", value1, value2, "autoRenew");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("UPDATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("UPDATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Timestamp value) {
            addCriterion("UPDATE_DATE =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Timestamp value) {
            addCriterion("UPDATE_DATE <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Timestamp value) {
            addCriterion("UPDATE_DATE >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("UPDATE_DATE >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Timestamp value) {
            addCriterion("UPDATE_DATE <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("UPDATE_DATE <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Timestamp> values) {
            addCriterion("UPDATE_DATE in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Timestamp> values) {
            addCriterion("UPDATE_DATE not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("UPDATE_DATE between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("UPDATE_DATE not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andDeductModeIsNull() {
            addCriterion("DEDUCT_MODE is null");
            return (Criteria) this;
        }

        public Criteria andDeductModeIsNotNull() {
            addCriterion("DEDUCT_MODE is not null");
            return (Criteria) this;
        }

        public Criteria andDeductModeEqualTo(String value) {
            addCriterion("DEDUCT_MODE =", value, "deductMode");
            return (Criteria) this;
        }

        public Criteria andDeductModeNotEqualTo(String value) {
            addCriterion("DEDUCT_MODE <>", value, "deductMode");
            return (Criteria) this;
        }

        public Criteria andDeductModeGreaterThan(String value) {
            addCriterion("DEDUCT_MODE >", value, "deductMode");
            return (Criteria) this;
        }

        public Criteria andDeductModeGreaterThanOrEqualTo(String value) {
            addCriterion("DEDUCT_MODE >=", value, "deductMode");
            return (Criteria) this;
        }

        public Criteria andDeductModeLessThan(String value) {
            addCriterion("DEDUCT_MODE <", value, "deductMode");
            return (Criteria) this;
        }

        public Criteria andDeductModeLessThanOrEqualTo(String value) {
            addCriterion("DEDUCT_MODE <=", value, "deductMode");
            return (Criteria) this;
        }

        public Criteria andDeductModeLike(String value) {
            addCriterion("DEDUCT_MODE like", value, "deductMode");
            return (Criteria) this;
        }

        public Criteria andDeductModeNotLike(String value) {
            addCriterion("DEDUCT_MODE not like", value, "deductMode");
            return (Criteria) this;
        }

        public Criteria andDeductModeIn(List<String> values) {
            addCriterion("DEDUCT_MODE in", values, "deductMode");
            return (Criteria) this;
        }

        public Criteria andDeductModeNotIn(List<String> values) {
            addCriterion("DEDUCT_MODE not in", values, "deductMode");
            return (Criteria) this;
        }

        public Criteria andDeductModeBetween(String value1, String value2) {
            addCriterion("DEDUCT_MODE between", value1, value2, "deductMode");
            return (Criteria) this;
        }

        public Criteria andDeductModeNotBetween(String value1, String value2) {
            addCriterion("DEDUCT_MODE not between", value1, value2, "deductMode");
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