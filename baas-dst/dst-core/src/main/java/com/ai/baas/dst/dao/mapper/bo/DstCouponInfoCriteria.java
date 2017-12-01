package com.ai.baas.dst.dao.mapper.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DstCouponInfoCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public DstCouponInfoCriteria() {
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

        public Criteria andCouponIdIsNull() {
            addCriterion("COUPON_ID is null");
            return (Criteria) this;
        }

        public Criteria andCouponIdIsNotNull() {
            addCriterion("COUPON_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCouponIdEqualTo(String value) {
            addCriterion("COUPON_ID =", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotEqualTo(String value) {
            addCriterion("COUPON_ID <>", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdGreaterThan(String value) {
            addCriterion("COUPON_ID >", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdGreaterThanOrEqualTo(String value) {
            addCriterion("COUPON_ID >=", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdLessThan(String value) {
            addCriterion("COUPON_ID <", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdLessThanOrEqualTo(String value) {
            addCriterion("COUPON_ID <=", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdLike(String value) {
            addCriterion("COUPON_ID like", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotLike(String value) {
            addCriterion("COUPON_ID not like", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdIn(List<String> values) {
            addCriterion("COUPON_ID in", values, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotIn(List<String> values) {
            addCriterion("COUPON_ID not in", values, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdBetween(String value1, String value2) {
            addCriterion("COUPON_ID between", value1, value2, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotBetween(String value1, String value2) {
            addCriterion("COUPON_ID not between", value1, value2, "couponId");
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

        public Criteria andCouponNameIsNull() {
            addCriterion("COUPON_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCouponNameIsNotNull() {
            addCriterion("COUPON_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCouponNameEqualTo(String value) {
            addCriterion("COUPON_NAME =", value, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameNotEqualTo(String value) {
            addCriterion("COUPON_NAME <>", value, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameGreaterThan(String value) {
            addCriterion("COUPON_NAME >", value, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameGreaterThanOrEqualTo(String value) {
            addCriterion("COUPON_NAME >=", value, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameLessThan(String value) {
            addCriterion("COUPON_NAME <", value, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameLessThanOrEqualTo(String value) {
            addCriterion("COUPON_NAME <=", value, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameLike(String value) {
            addCriterion("COUPON_NAME like", value, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameNotLike(String value) {
            addCriterion("COUPON_NAME not like", value, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameIn(List<String> values) {
            addCriterion("COUPON_NAME in", values, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameNotIn(List<String> values) {
            addCriterion("COUPON_NAME not in", values, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameBetween(String value1, String value2) {
            addCriterion("COUPON_NAME between", value1, value2, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameNotBetween(String value1, String value2) {
            addCriterion("COUPON_NAME not between", value1, value2, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIsNull() {
            addCriterion("COUPON_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIsNotNull() {
            addCriterion("COUPON_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCouponTypeEqualTo(String value) {
            addCriterion("COUPON_TYPE =", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNotEqualTo(String value) {
            addCriterion("COUPON_TYPE <>", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeGreaterThan(String value) {
            addCriterion("COUPON_TYPE >", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeGreaterThanOrEqualTo(String value) {
            addCriterion("COUPON_TYPE >=", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeLessThan(String value) {
            addCriterion("COUPON_TYPE <", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeLessThanOrEqualTo(String value) {
            addCriterion("COUPON_TYPE <=", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeLike(String value) {
            addCriterion("COUPON_TYPE like", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNotLike(String value) {
            addCriterion("COUPON_TYPE not like", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIn(List<String> values) {
            addCriterion("COUPON_TYPE in", values, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNotIn(List<String> values) {
            addCriterion("COUPON_TYPE not in", values, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeBetween(String value1, String value2) {
            addCriterion("COUPON_TYPE between", value1, value2, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNotBetween(String value1, String value2) {
            addCriterion("COUPON_TYPE not between", value1, value2, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponValueIsNull() {
            addCriterion("COUPON_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andCouponValueIsNotNull() {
            addCriterion("COUPON_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andCouponValueEqualTo(String value) {
            addCriterion("COUPON_VALUE =", value, "couponValue");
            return (Criteria) this;
        }

        public Criteria andCouponValueNotEqualTo(String value) {
            addCriterion("COUPON_VALUE <>", value, "couponValue");
            return (Criteria) this;
        }

        public Criteria andCouponValueGreaterThan(String value) {
            addCriterion("COUPON_VALUE >", value, "couponValue");
            return (Criteria) this;
        }

        public Criteria andCouponValueGreaterThanOrEqualTo(String value) {
            addCriterion("COUPON_VALUE >=", value, "couponValue");
            return (Criteria) this;
        }

        public Criteria andCouponValueLessThan(String value) {
            addCriterion("COUPON_VALUE <", value, "couponValue");
            return (Criteria) this;
        }

        public Criteria andCouponValueLessThanOrEqualTo(String value) {
            addCriterion("COUPON_VALUE <=", value, "couponValue");
            return (Criteria) this;
        }

        public Criteria andCouponValueLike(String value) {
            addCriterion("COUPON_VALUE like", value, "couponValue");
            return (Criteria) this;
        }

        public Criteria andCouponValueNotLike(String value) {
            addCriterion("COUPON_VALUE not like", value, "couponValue");
            return (Criteria) this;
        }

        public Criteria andCouponValueIn(List<String> values) {
            addCriterion("COUPON_VALUE in", values, "couponValue");
            return (Criteria) this;
        }

        public Criteria andCouponValueNotIn(List<String> values) {
            addCriterion("COUPON_VALUE not in", values, "couponValue");
            return (Criteria) this;
        }

        public Criteria andCouponValueBetween(String value1, String value2) {
            addCriterion("COUPON_VALUE between", value1, value2, "couponValue");
            return (Criteria) this;
        }

        public Criteria andCouponValueNotBetween(String value1, String value2) {
            addCriterion("COUPON_VALUE not between", value1, value2, "couponValue");
            return (Criteria) this;
        }

        public Criteria andCouponAmountIsNull() {
            addCriterion("COUPON_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andCouponAmountIsNotNull() {
            addCriterion("COUPON_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andCouponAmountEqualTo(String value) {
            addCriterion("COUPON_AMOUNT =", value, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountNotEqualTo(String value) {
            addCriterion("COUPON_AMOUNT <>", value, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountGreaterThan(String value) {
            addCriterion("COUPON_AMOUNT >", value, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountGreaterThanOrEqualTo(String value) {
            addCriterion("COUPON_AMOUNT >=", value, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountLessThan(String value) {
            addCriterion("COUPON_AMOUNT <", value, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountLessThanOrEqualTo(String value) {
            addCriterion("COUPON_AMOUNT <=", value, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountLike(String value) {
            addCriterion("COUPON_AMOUNT like", value, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountNotLike(String value) {
            addCriterion("COUPON_AMOUNT not like", value, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountIn(List<String> values) {
            addCriterion("COUPON_AMOUNT in", values, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountNotIn(List<String> values) {
            addCriterion("COUPON_AMOUNT not in", values, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountBetween(String value1, String value2) {
            addCriterion("COUPON_AMOUNT between", value1, value2, "couponAmount");
            return (Criteria) this;
        }

        public Criteria andCouponAmountNotBetween(String value1, String value2) {
            addCriterion("COUPON_AMOUNT not between", value1, value2, "couponAmount");
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

        public Criteria andConditionValueIsNull() {
            addCriterion("CONDITION_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andConditionValueIsNotNull() {
            addCriterion("CONDITION_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andConditionValueEqualTo(String value) {
            addCriterion("CONDITION_VALUE =", value, "conditionValue");
            return (Criteria) this;
        }

        public Criteria andConditionValueNotEqualTo(String value) {
            addCriterion("CONDITION_VALUE <>", value, "conditionValue");
            return (Criteria) this;
        }

        public Criteria andConditionValueGreaterThan(String value) {
            addCriterion("CONDITION_VALUE >", value, "conditionValue");
            return (Criteria) this;
        }

        public Criteria andConditionValueGreaterThanOrEqualTo(String value) {
            addCriterion("CONDITION_VALUE >=", value, "conditionValue");
            return (Criteria) this;
        }

        public Criteria andConditionValueLessThan(String value) {
            addCriterion("CONDITION_VALUE <", value, "conditionValue");
            return (Criteria) this;
        }

        public Criteria andConditionValueLessThanOrEqualTo(String value) {
            addCriterion("CONDITION_VALUE <=", value, "conditionValue");
            return (Criteria) this;
        }

        public Criteria andConditionValueLike(String value) {
            addCriterion("CONDITION_VALUE like", value, "conditionValue");
            return (Criteria) this;
        }

        public Criteria andConditionValueNotLike(String value) {
            addCriterion("CONDITION_VALUE not like", value, "conditionValue");
            return (Criteria) this;
        }

        public Criteria andConditionValueIn(List<String> values) {
            addCriterion("CONDITION_VALUE in", values, "conditionValue");
            return (Criteria) this;
        }

        public Criteria andConditionValueNotIn(List<String> values) {
            addCriterion("CONDITION_VALUE not in", values, "conditionValue");
            return (Criteria) this;
        }

        public Criteria andConditionValueBetween(String value1, String value2) {
            addCriterion("CONDITION_VALUE between", value1, value2, "conditionValue");
            return (Criteria) this;
        }

        public Criteria andConditionValueNotBetween(String value1, String value2) {
            addCriterion("CONDITION_VALUE not between", value1, value2, "conditionValue");
            return (Criteria) this;
        }

        public Criteria andCouponConditionIsNull() {
            addCriterion("COUPON_CONDITION is null");
            return (Criteria) this;
        }

        public Criteria andCouponConditionIsNotNull() {
            addCriterion("COUPON_CONDITION is not null");
            return (Criteria) this;
        }

        public Criteria andCouponConditionEqualTo(String value) {
            addCriterion("COUPON_CONDITION =", value, "couponCondition");
            return (Criteria) this;
        }

        public Criteria andCouponConditionNotEqualTo(String value) {
            addCriterion("COUPON_CONDITION <>", value, "couponCondition");
            return (Criteria) this;
        }

        public Criteria andCouponConditionGreaterThan(String value) {
            addCriterion("COUPON_CONDITION >", value, "couponCondition");
            return (Criteria) this;
        }

        public Criteria andCouponConditionGreaterThanOrEqualTo(String value) {
            addCriterion("COUPON_CONDITION >=", value, "couponCondition");
            return (Criteria) this;
        }

        public Criteria andCouponConditionLessThan(String value) {
            addCriterion("COUPON_CONDITION <", value, "couponCondition");
            return (Criteria) this;
        }

        public Criteria andCouponConditionLessThanOrEqualTo(String value) {
            addCriterion("COUPON_CONDITION <=", value, "couponCondition");
            return (Criteria) this;
        }

        public Criteria andCouponConditionLike(String value) {
            addCriterion("COUPON_CONDITION like", value, "couponCondition");
            return (Criteria) this;
        }

        public Criteria andCouponConditionNotLike(String value) {
            addCriterion("COUPON_CONDITION not like", value, "couponCondition");
            return (Criteria) this;
        }

        public Criteria andCouponConditionIn(List<String> values) {
            addCriterion("COUPON_CONDITION in", values, "couponCondition");
            return (Criteria) this;
        }

        public Criteria andCouponConditionNotIn(List<String> values) {
            addCriterion("COUPON_CONDITION not in", values, "couponCondition");
            return (Criteria) this;
        }

        public Criteria andCouponConditionBetween(String value1, String value2) {
            addCriterion("COUPON_CONDITION between", value1, value2, "couponCondition");
            return (Criteria) this;
        }

        public Criteria andCouponConditionNotBetween(String value1, String value2) {
            addCriterion("COUPON_CONDITION not between", value1, value2, "couponCondition");
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

        public Criteria andCouponConTypeIsNull() {
            addCriterion("COUPON_CON_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCouponConTypeIsNotNull() {
            addCriterion("COUPON_CON_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCouponConTypeEqualTo(String value) {
            addCriterion("COUPON_CON_TYPE =", value, "couponConType");
            return (Criteria) this;
        }

        public Criteria andCouponConTypeNotEqualTo(String value) {
            addCriterion("COUPON_CON_TYPE <>", value, "couponConType");
            return (Criteria) this;
        }

        public Criteria andCouponConTypeGreaterThan(String value) {
            addCriterion("COUPON_CON_TYPE >", value, "couponConType");
            return (Criteria) this;
        }

        public Criteria andCouponConTypeGreaterThanOrEqualTo(String value) {
            addCriterion("COUPON_CON_TYPE >=", value, "couponConType");
            return (Criteria) this;
        }

        public Criteria andCouponConTypeLessThan(String value) {
            addCriterion("COUPON_CON_TYPE <", value, "couponConType");
            return (Criteria) this;
        }

        public Criteria andCouponConTypeLessThanOrEqualTo(String value) {
            addCriterion("COUPON_CON_TYPE <=", value, "couponConType");
            return (Criteria) this;
        }

        public Criteria andCouponConTypeLike(String value) {
            addCriterion("COUPON_CON_TYPE like", value, "couponConType");
            return (Criteria) this;
        }

        public Criteria andCouponConTypeNotLike(String value) {
            addCriterion("COUPON_CON_TYPE not like", value, "couponConType");
            return (Criteria) this;
        }

        public Criteria andCouponConTypeIn(List<String> values) {
            addCriterion("COUPON_CON_TYPE in", values, "couponConType");
            return (Criteria) this;
        }

        public Criteria andCouponConTypeNotIn(List<String> values) {
            addCriterion("COUPON_CON_TYPE not in", values, "couponConType");
            return (Criteria) this;
        }

        public Criteria andCouponConTypeBetween(String value1, String value2) {
            addCriterion("COUPON_CON_TYPE between", value1, value2, "couponConType");
            return (Criteria) this;
        }

        public Criteria andCouponConTypeNotBetween(String value1, String value2) {
            addCriterion("COUPON_CON_TYPE not between", value1, value2, "couponConType");
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

        public Criteria andTopMoneyIsNull() {
            addCriterion("TOP_MONEY is null");
            return (Criteria) this;
        }

        public Criteria andTopMoneyIsNotNull() {
            addCriterion("TOP_MONEY is not null");
            return (Criteria) this;
        }

        public Criteria andTopMoneyEqualTo(String value) {
            addCriterion("TOP_MONEY =", value, "topMoney");
            return (Criteria) this;
        }

        public Criteria andTopMoneyNotEqualTo(String value) {
            addCriterion("TOP_MONEY <>", value, "topMoney");
            return (Criteria) this;
        }

        public Criteria andTopMoneyGreaterThan(String value) {
            addCriterion("TOP_MONEY >", value, "topMoney");
            return (Criteria) this;
        }

        public Criteria andTopMoneyGreaterThanOrEqualTo(String value) {
            addCriterion("TOP_MONEY >=", value, "topMoney");
            return (Criteria) this;
        }

        public Criteria andTopMoneyLessThan(String value) {
            addCriterion("TOP_MONEY <", value, "topMoney");
            return (Criteria) this;
        }

        public Criteria andTopMoneyLessThanOrEqualTo(String value) {
            addCriterion("TOP_MONEY <=", value, "topMoney");
            return (Criteria) this;
        }

        public Criteria andTopMoneyLike(String value) {
            addCriterion("TOP_MONEY like", value, "topMoney");
            return (Criteria) this;
        }

        public Criteria andTopMoneyNotLike(String value) {
            addCriterion("TOP_MONEY not like", value, "topMoney");
            return (Criteria) this;
        }

        public Criteria andTopMoneyIn(List<String> values) {
            addCriterion("TOP_MONEY in", values, "topMoney");
            return (Criteria) this;
        }

        public Criteria andTopMoneyNotIn(List<String> values) {
            addCriterion("TOP_MONEY not in", values, "topMoney");
            return (Criteria) this;
        }

        public Criteria andTopMoneyBetween(String value1, String value2) {
            addCriterion("TOP_MONEY between", value1, value2, "topMoney");
            return (Criteria) this;
        }

        public Criteria andTopMoneyNotBetween(String value1, String value2) {
            addCriterion("TOP_MONEY not between", value1, value2, "topMoney");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNull() {
            addCriterion("PRODUCT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNotNull() {
            addCriterion("PRODUCT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProductNameEqualTo(String value) {
            addCriterion("PRODUCT_NAME =", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotEqualTo(String value) {
            addCriterion("PRODUCT_NAME <>", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThan(String value) {
            addCriterion("PRODUCT_NAME >", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCT_NAME >=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThan(String value) {
            addCriterion("PRODUCT_NAME <", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThanOrEqualTo(String value) {
            addCriterion("PRODUCT_NAME <=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLike(String value) {
            addCriterion("PRODUCT_NAME like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotLike(String value) {
            addCriterion("PRODUCT_NAME not like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameIn(List<String> values) {
            addCriterion("PRODUCT_NAME in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotIn(List<String> values) {
            addCriterion("PRODUCT_NAME not in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameBetween(String value1, String value2) {
            addCriterion("PRODUCT_NAME between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotBetween(String value1, String value2) {
            addCriterion("PRODUCT_NAME not between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andCanApplyCountIsNull() {
            addCriterion("CAN_APPLY_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andCanApplyCountIsNotNull() {
            addCriterion("CAN_APPLY_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andCanApplyCountEqualTo(String value) {
            addCriterion("CAN_APPLY_COUNT =", value, "canApplyCount");
            return (Criteria) this;
        }

        public Criteria andCanApplyCountNotEqualTo(String value) {
            addCriterion("CAN_APPLY_COUNT <>", value, "canApplyCount");
            return (Criteria) this;
        }

        public Criteria andCanApplyCountGreaterThan(String value) {
            addCriterion("CAN_APPLY_COUNT >", value, "canApplyCount");
            return (Criteria) this;
        }

        public Criteria andCanApplyCountGreaterThanOrEqualTo(String value) {
            addCriterion("CAN_APPLY_COUNT >=", value, "canApplyCount");
            return (Criteria) this;
        }

        public Criteria andCanApplyCountLessThan(String value) {
            addCriterion("CAN_APPLY_COUNT <", value, "canApplyCount");
            return (Criteria) this;
        }

        public Criteria andCanApplyCountLessThanOrEqualTo(String value) {
            addCriterion("CAN_APPLY_COUNT <=", value, "canApplyCount");
            return (Criteria) this;
        }

        public Criteria andCanApplyCountLike(String value) {
            addCriterion("CAN_APPLY_COUNT like", value, "canApplyCount");
            return (Criteria) this;
        }

        public Criteria andCanApplyCountNotLike(String value) {
            addCriterion("CAN_APPLY_COUNT not like", value, "canApplyCount");
            return (Criteria) this;
        }

        public Criteria andCanApplyCountIn(List<String> values) {
            addCriterion("CAN_APPLY_COUNT in", values, "canApplyCount");
            return (Criteria) this;
        }

        public Criteria andCanApplyCountNotIn(List<String> values) {
            addCriterion("CAN_APPLY_COUNT not in", values, "canApplyCount");
            return (Criteria) this;
        }

        public Criteria andCanApplyCountBetween(String value1, String value2) {
            addCriterion("CAN_APPLY_COUNT between", value1, value2, "canApplyCount");
            return (Criteria) this;
        }

        public Criteria andCanApplyCountNotBetween(String value1, String value2) {
            addCriterion("CAN_APPLY_COUNT not between", value1, value2, "canApplyCount");
            return (Criteria) this;
        }

        public Criteria andApplyCountIsNull() {
            addCriterion("APPLY_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andApplyCountIsNotNull() {
            addCriterion("APPLY_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andApplyCountEqualTo(String value) {
            addCriterion("APPLY_COUNT =", value, "applyCount");
            return (Criteria) this;
        }

        public Criteria andApplyCountNotEqualTo(String value) {
            addCriterion("APPLY_COUNT <>", value, "applyCount");
            return (Criteria) this;
        }

        public Criteria andApplyCountGreaterThan(String value) {
            addCriterion("APPLY_COUNT >", value, "applyCount");
            return (Criteria) this;
        }

        public Criteria andApplyCountGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_COUNT >=", value, "applyCount");
            return (Criteria) this;
        }

        public Criteria andApplyCountLessThan(String value) {
            addCriterion("APPLY_COUNT <", value, "applyCount");
            return (Criteria) this;
        }

        public Criteria andApplyCountLessThanOrEqualTo(String value) {
            addCriterion("APPLY_COUNT <=", value, "applyCount");
            return (Criteria) this;
        }

        public Criteria andApplyCountLike(String value) {
            addCriterion("APPLY_COUNT like", value, "applyCount");
            return (Criteria) this;
        }

        public Criteria andApplyCountNotLike(String value) {
            addCriterion("APPLY_COUNT not like", value, "applyCount");
            return (Criteria) this;
        }

        public Criteria andApplyCountIn(List<String> values) {
            addCriterion("APPLY_COUNT in", values, "applyCount");
            return (Criteria) this;
        }

        public Criteria andApplyCountNotIn(List<String> values) {
            addCriterion("APPLY_COUNT not in", values, "applyCount");
            return (Criteria) this;
        }

        public Criteria andApplyCountBetween(String value1, String value2) {
            addCriterion("APPLY_COUNT between", value1, value2, "applyCount");
            return (Criteria) this;
        }

        public Criteria andApplyCountNotBetween(String value1, String value2) {
            addCriterion("APPLY_COUNT not between", value1, value2, "applyCount");
            return (Criteria) this;
        }

        public Criteria andAppliedCountIsNull() {
            addCriterion("APPLIED_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andAppliedCountIsNotNull() {
            addCriterion("APPLIED_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andAppliedCountEqualTo(String value) {
            addCriterion("APPLIED_COUNT =", value, "appliedCount");
            return (Criteria) this;
        }

        public Criteria andAppliedCountNotEqualTo(String value) {
            addCriterion("APPLIED_COUNT <>", value, "appliedCount");
            return (Criteria) this;
        }

        public Criteria andAppliedCountGreaterThan(String value) {
            addCriterion("APPLIED_COUNT >", value, "appliedCount");
            return (Criteria) this;
        }

        public Criteria andAppliedCountGreaterThanOrEqualTo(String value) {
            addCriterion("APPLIED_COUNT >=", value, "appliedCount");
            return (Criteria) this;
        }

        public Criteria andAppliedCountLessThan(String value) {
            addCriterion("APPLIED_COUNT <", value, "appliedCount");
            return (Criteria) this;
        }

        public Criteria andAppliedCountLessThanOrEqualTo(String value) {
            addCriterion("APPLIED_COUNT <=", value, "appliedCount");
            return (Criteria) this;
        }

        public Criteria andAppliedCountLike(String value) {
            addCriterion("APPLIED_COUNT like", value, "appliedCount");
            return (Criteria) this;
        }

        public Criteria andAppliedCountNotLike(String value) {
            addCriterion("APPLIED_COUNT not like", value, "appliedCount");
            return (Criteria) this;
        }

        public Criteria andAppliedCountIn(List<String> values) {
            addCriterion("APPLIED_COUNT in", values, "appliedCount");
            return (Criteria) this;
        }

        public Criteria andAppliedCountNotIn(List<String> values) {
            addCriterion("APPLIED_COUNT not in", values, "appliedCount");
            return (Criteria) this;
        }

        public Criteria andAppliedCountBetween(String value1, String value2) {
            addCriterion("APPLIED_COUNT between", value1, value2, "appliedCount");
            return (Criteria) this;
        }

        public Criteria andAppliedCountNotBetween(String value1, String value2) {
            addCriterion("APPLIED_COUNT not between", value1, value2, "appliedCount");
            return (Criteria) this;
        }

        public Criteria andRemainCountIsNull() {
            addCriterion("REMAIN_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andRemainCountIsNotNull() {
            addCriterion("REMAIN_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andRemainCountEqualTo(String value) {
            addCriterion("REMAIN_COUNT =", value, "remainCount");
            return (Criteria) this;
        }

        public Criteria andRemainCountNotEqualTo(String value) {
            addCriterion("REMAIN_COUNT <>", value, "remainCount");
            return (Criteria) this;
        }

        public Criteria andRemainCountGreaterThan(String value) {
            addCriterion("REMAIN_COUNT >", value, "remainCount");
            return (Criteria) this;
        }

        public Criteria andRemainCountGreaterThanOrEqualTo(String value) {
            addCriterion("REMAIN_COUNT >=", value, "remainCount");
            return (Criteria) this;
        }

        public Criteria andRemainCountLessThan(String value) {
            addCriterion("REMAIN_COUNT <", value, "remainCount");
            return (Criteria) this;
        }

        public Criteria andRemainCountLessThanOrEqualTo(String value) {
            addCriterion("REMAIN_COUNT <=", value, "remainCount");
            return (Criteria) this;
        }

        public Criteria andRemainCountLike(String value) {
            addCriterion("REMAIN_COUNT like", value, "remainCount");
            return (Criteria) this;
        }

        public Criteria andRemainCountNotLike(String value) {
            addCriterion("REMAIN_COUNT not like", value, "remainCount");
            return (Criteria) this;
        }

        public Criteria andRemainCountIn(List<String> values) {
            addCriterion("REMAIN_COUNT in", values, "remainCount");
            return (Criteria) this;
        }

        public Criteria andRemainCountNotIn(List<String> values) {
            addCriterion("REMAIN_COUNT not in", values, "remainCount");
            return (Criteria) this;
        }

        public Criteria andRemainCountBetween(String value1, String value2) {
            addCriterion("REMAIN_COUNT between", value1, value2, "remainCount");
            return (Criteria) this;
        }

        public Criteria andRemainCountNotBetween(String value1, String value2) {
            addCriterion("REMAIN_COUNT not between", value1, value2, "remainCount");
            return (Criteria) this;
        }

        public Criteria andCouponRuleIsNull() {
            addCriterion("COUPON_RULE is null");
            return (Criteria) this;
        }

        public Criteria andCouponRuleIsNotNull() {
            addCriterion("COUPON_RULE is not null");
            return (Criteria) this;
        }

        public Criteria andCouponRuleEqualTo(String value) {
            addCriterion("COUPON_RULE =", value, "couponRule");
            return (Criteria) this;
        }

        public Criteria andCouponRuleNotEqualTo(String value) {
            addCriterion("COUPON_RULE <>", value, "couponRule");
            return (Criteria) this;
        }

        public Criteria andCouponRuleGreaterThan(String value) {
            addCriterion("COUPON_RULE >", value, "couponRule");
            return (Criteria) this;
        }

        public Criteria andCouponRuleGreaterThanOrEqualTo(String value) {
            addCriterion("COUPON_RULE >=", value, "couponRule");
            return (Criteria) this;
        }

        public Criteria andCouponRuleLessThan(String value) {
            addCriterion("COUPON_RULE <", value, "couponRule");
            return (Criteria) this;
        }

        public Criteria andCouponRuleLessThanOrEqualTo(String value) {
            addCriterion("COUPON_RULE <=", value, "couponRule");
            return (Criteria) this;
        }

        public Criteria andCouponRuleLike(String value) {
            addCriterion("COUPON_RULE like", value, "couponRule");
            return (Criteria) this;
        }

        public Criteria andCouponRuleNotLike(String value) {
            addCriterion("COUPON_RULE not like", value, "couponRule");
            return (Criteria) this;
        }

        public Criteria andCouponRuleIn(List<String> values) {
            addCriterion("COUPON_RULE in", values, "couponRule");
            return (Criteria) this;
        }

        public Criteria andCouponRuleNotIn(List<String> values) {
            addCriterion("COUPON_RULE not in", values, "couponRule");
            return (Criteria) this;
        }

        public Criteria andCouponRuleBetween(String value1, String value2) {
            addCriterion("COUPON_RULE between", value1, value2, "couponRule");
            return (Criteria) this;
        }

        public Criteria andCouponRuleNotBetween(String value1, String value2) {
            addCriterion("COUPON_RULE not between", value1, value2, "couponRule");
            return (Criteria) this;
        }

        public Criteria andCreatorRoleIsNull() {
            addCriterion("CREATOR_ROLE is null");
            return (Criteria) this;
        }

        public Criteria andCreatorRoleIsNotNull() {
            addCriterion("CREATOR_ROLE is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorRoleEqualTo(String value) {
            addCriterion("CREATOR_ROLE =", value, "creatorRole");
            return (Criteria) this;
        }

        public Criteria andCreatorRoleNotEqualTo(String value) {
            addCriterion("CREATOR_ROLE <>", value, "creatorRole");
            return (Criteria) this;
        }

        public Criteria andCreatorRoleGreaterThan(String value) {
            addCriterion("CREATOR_ROLE >", value, "creatorRole");
            return (Criteria) this;
        }

        public Criteria andCreatorRoleGreaterThanOrEqualTo(String value) {
            addCriterion("CREATOR_ROLE >=", value, "creatorRole");
            return (Criteria) this;
        }

        public Criteria andCreatorRoleLessThan(String value) {
            addCriterion("CREATOR_ROLE <", value, "creatorRole");
            return (Criteria) this;
        }

        public Criteria andCreatorRoleLessThanOrEqualTo(String value) {
            addCriterion("CREATOR_ROLE <=", value, "creatorRole");
            return (Criteria) this;
        }

        public Criteria andCreatorRoleLike(String value) {
            addCriterion("CREATOR_ROLE like", value, "creatorRole");
            return (Criteria) this;
        }

        public Criteria andCreatorRoleNotLike(String value) {
            addCriterion("CREATOR_ROLE not like", value, "creatorRole");
            return (Criteria) this;
        }

        public Criteria andCreatorRoleIn(List<String> values) {
            addCriterion("CREATOR_ROLE in", values, "creatorRole");
            return (Criteria) this;
        }

        public Criteria andCreatorRoleNotIn(List<String> values) {
            addCriterion("CREATOR_ROLE not in", values, "creatorRole");
            return (Criteria) this;
        }

        public Criteria andCreatorRoleBetween(String value1, String value2) {
            addCriterion("CREATOR_ROLE between", value1, value2, "creatorRole");
            return (Criteria) this;
        }

        public Criteria andCreatorRoleNotBetween(String value1, String value2) {
            addCriterion("CREATOR_ROLE not between", value1, value2, "creatorRole");
            return (Criteria) this;
        }

        public Criteria andCreatorNameIsNull() {
            addCriterion("CREATOR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCreatorNameIsNotNull() {
            addCriterion("CREATOR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorNameEqualTo(String value) {
            addCriterion("CREATOR_NAME =", value, "creatorName");
            return (Criteria) this;
        }

        public Criteria andCreatorNameNotEqualTo(String value) {
            addCriterion("CREATOR_NAME <>", value, "creatorName");
            return (Criteria) this;
        }

        public Criteria andCreatorNameGreaterThan(String value) {
            addCriterion("CREATOR_NAME >", value, "creatorName");
            return (Criteria) this;
        }

        public Criteria andCreatorNameGreaterThanOrEqualTo(String value) {
            addCriterion("CREATOR_NAME >=", value, "creatorName");
            return (Criteria) this;
        }

        public Criteria andCreatorNameLessThan(String value) {
            addCriterion("CREATOR_NAME <", value, "creatorName");
            return (Criteria) this;
        }

        public Criteria andCreatorNameLessThanOrEqualTo(String value) {
            addCriterion("CREATOR_NAME <=", value, "creatorName");
            return (Criteria) this;
        }

        public Criteria andCreatorNameLike(String value) {
            addCriterion("CREATOR_NAME like", value, "creatorName");
            return (Criteria) this;
        }

        public Criteria andCreatorNameNotLike(String value) {
            addCriterion("CREATOR_NAME not like", value, "creatorName");
            return (Criteria) this;
        }

        public Criteria andCreatorNameIn(List<String> values) {
            addCriterion("CREATOR_NAME in", values, "creatorName");
            return (Criteria) this;
        }

        public Criteria andCreatorNameNotIn(List<String> values) {
            addCriterion("CREATOR_NAME not in", values, "creatorName");
            return (Criteria) this;
        }

        public Criteria andCreatorNameBetween(String value1, String value2) {
            addCriterion("CREATOR_NAME between", value1, value2, "creatorName");
            return (Criteria) this;
        }

        public Criteria andCreatorNameNotBetween(String value1, String value2) {
            addCriterion("CREATOR_NAME not between", value1, value2, "creatorName");
            return (Criteria) this;
        }

        public Criteria andCreatorIdIsNull() {
            addCriterion("CREATOR_ID is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIdIsNotNull() {
            addCriterion("CREATOR_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorIdEqualTo(String value) {
            addCriterion("CREATOR_ID =", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotEqualTo(String value) {
            addCriterion("CREATOR_ID <>", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdGreaterThan(String value) {
            addCriterion("CREATOR_ID >", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdGreaterThanOrEqualTo(String value) {
            addCriterion("CREATOR_ID >=", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdLessThan(String value) {
            addCriterion("CREATOR_ID <", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdLessThanOrEqualTo(String value) {
            addCriterion("CREATOR_ID <=", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdLike(String value) {
            addCriterion("CREATOR_ID like", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotLike(String value) {
            addCriterion("CREATOR_ID not like", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdIn(List<String> values) {
            addCriterion("CREATOR_ID in", values, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotIn(List<String> values) {
            addCriterion("CREATOR_ID not in", values, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdBetween(String value1, String value2) {
            addCriterion("CREATOR_ID between", value1, value2, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotBetween(String value1, String value2) {
            addCriterion("CREATOR_ID not between", value1, value2, "creatorId");
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

        public Criteria andCreatorAccountIsNull() {
            addCriterion("CREATOR_ACCOUNT is null");
            return (Criteria) this;
        }

        public Criteria andCreatorAccountIsNotNull() {
            addCriterion("CREATOR_ACCOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorAccountEqualTo(String value) {
            addCriterion("CREATOR_ACCOUNT =", value, "creatorAccount");
            return (Criteria) this;
        }

        public Criteria andCreatorAccountNotEqualTo(String value) {
            addCriterion("CREATOR_ACCOUNT <>", value, "creatorAccount");
            return (Criteria) this;
        }

        public Criteria andCreatorAccountGreaterThan(String value) {
            addCriterion("CREATOR_ACCOUNT >", value, "creatorAccount");
            return (Criteria) this;
        }

        public Criteria andCreatorAccountGreaterThanOrEqualTo(String value) {
            addCriterion("CREATOR_ACCOUNT >=", value, "creatorAccount");
            return (Criteria) this;
        }

        public Criteria andCreatorAccountLessThan(String value) {
            addCriterion("CREATOR_ACCOUNT <", value, "creatorAccount");
            return (Criteria) this;
        }

        public Criteria andCreatorAccountLessThanOrEqualTo(String value) {
            addCriterion("CREATOR_ACCOUNT <=", value, "creatorAccount");
            return (Criteria) this;
        }

        public Criteria andCreatorAccountLike(String value) {
            addCriterion("CREATOR_ACCOUNT like", value, "creatorAccount");
            return (Criteria) this;
        }

        public Criteria andCreatorAccountNotLike(String value) {
            addCriterion("CREATOR_ACCOUNT not like", value, "creatorAccount");
            return (Criteria) this;
        }
        public Criteria andCreatorAccountIn(List<String> values) {
            addCriterion("CREATOR_ACCOUNT in", values, "creatorAccount");
            return (Criteria) this;
        }

        public Criteria andCreatorAccountNotIn(List<String> values) {
            addCriterion("CREATOR_ACCOUNT not in", values, "creatorAccount");
            return (Criteria) this;
        }
        
        public Criteria andCreatorAccountBetween(String value1, String value2) {
            addCriterion("CREATOR_ACCOUNT between", value1, value2, "creatorAccount");
            return (Criteria) this;
        }

        public Criteria andCreatorAccountNotBetween(String value1, String value2) {
            addCriterion("CREATOR_ACCOUNT not between", value1, value2, "creatorAccount");
            return (Criteria) this;
        }
        public Criteria andChannelIdEqualTo(String value) {
        	addCriterion("CREATOR_ID =", value, "ChannelId");
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