package com.ai.baas.cust.dao.mapper.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BmcFailureBillCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public BmcFailureBillCriteria() {
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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTenantIdIsNull() {
            addCriterion("tenant_id is null");
            return (Criteria) this;
        }

        public Criteria andTenantIdIsNotNull() {
            addCriterion("tenant_id is not null");
            return (Criteria) this;
        }

        public Criteria andTenantIdEqualTo(String value) {
            addCriterion("tenant_id =", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotEqualTo(String value) {
            addCriterion("tenant_id <>", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThan(String value) {
            addCriterion("tenant_id >", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThanOrEqualTo(String value) {
            addCriterion("tenant_id >=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThan(String value) {
            addCriterion("tenant_id <", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThanOrEqualTo(String value) {
            addCriterion("tenant_id <=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLike(String value) {
            addCriterion("tenant_id like", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotLike(String value) {
            addCriterion("tenant_id not like", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdIn(List<String> values) {
            addCriterion("tenant_id in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotIn(List<String> values) {
            addCriterion("tenant_id not in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdBetween(String value1, String value2) {
            addCriterion("tenant_id between", value1, value2, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotBetween(String value1, String value2) {
            addCriterion("tenant_id not between", value1, value2, "tenantId");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIsNull() {
            addCriterion("service_type is null");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIsNotNull() {
            addCriterion("service_type is not null");
            return (Criteria) this;
        }

        public Criteria andServiceTypeEqualTo(String value) {
            addCriterion("service_type =", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNotEqualTo(String value) {
            addCriterion("service_type <>", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeGreaterThan(String value) {
            addCriterion("service_type >", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeGreaterThanOrEqualTo(String value) {
            addCriterion("service_type >=", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeLessThan(String value) {
            addCriterion("service_type <", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeLessThanOrEqualTo(String value) {
            addCriterion("service_type <=", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeLike(String value) {
            addCriterion("service_type like", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNotLike(String value) {
            addCriterion("service_type not like", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIn(List<String> values) {
            addCriterion("service_type in", values, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNotIn(List<String> values) {
            addCriterion("service_type not in", values, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeBetween(String value1, String value2) {
            addCriterion("service_type between", value1, value2, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNotBetween(String value1, String value2) {
            addCriterion("service_type not between", value1, value2, "serviceType");
            return (Criteria) this;
        }

        public Criteria andSourceIsNull() {
            addCriterion("source is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("source is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(String value) {
            addCriterion("source =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(String value) {
            addCriterion("source <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(String value) {
            addCriterion("source >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(String value) {
            addCriterion("source >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(String value) {
            addCriterion("source <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(String value) {
            addCriterion("source <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLike(String value) {
            addCriterion("source like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotLike(String value) {
            addCriterion("source not like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<String> values) {
            addCriterion("source in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<String> values) {
            addCriterion("source not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(String value1, String value2) {
            addCriterion("source between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(String value1, String value2) {
            addCriterion("source not between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andBsnIsNull() {
            addCriterion("bsn is null");
            return (Criteria) this;
        }

        public Criteria andBsnIsNotNull() {
            addCriterion("bsn is not null");
            return (Criteria) this;
        }

        public Criteria andBsnEqualTo(String value) {
            addCriterion("bsn =", value, "bsn");
            return (Criteria) this;
        }

        public Criteria andBsnNotEqualTo(String value) {
            addCriterion("bsn <>", value, "bsn");
            return (Criteria) this;
        }

        public Criteria andBsnGreaterThan(String value) {
            addCriterion("bsn >", value, "bsn");
            return (Criteria) this;
        }

        public Criteria andBsnGreaterThanOrEqualTo(String value) {
            addCriterion("bsn >=", value, "bsn");
            return (Criteria) this;
        }

        public Criteria andBsnLessThan(String value) {
            addCriterion("bsn <", value, "bsn");
            return (Criteria) this;
        }

        public Criteria andBsnLessThanOrEqualTo(String value) {
            addCriterion("bsn <=", value, "bsn");
            return (Criteria) this;
        }

        public Criteria andBsnLike(String value) {
            addCriterion("bsn like", value, "bsn");
            return (Criteria) this;
        }

        public Criteria andBsnNotLike(String value) {
            addCriterion("bsn not like", value, "bsn");
            return (Criteria) this;
        }

        public Criteria andBsnIn(List<String> values) {
            addCriterion("bsn in", values, "bsn");
            return (Criteria) this;
        }

        public Criteria andBsnNotIn(List<String> values) {
            addCriterion("bsn not in", values, "bsn");
            return (Criteria) this;
        }

        public Criteria andBsnBetween(String value1, String value2) {
            addCriterion("bsn between", value1, value2, "bsn");
            return (Criteria) this;
        }

        public Criteria andBsnNotBetween(String value1, String value2) {
            addCriterion("bsn not between", value1, value2, "bsn");
            return (Criteria) this;
        }

        public Criteria andSnIsNull() {
            addCriterion("sn is null");
            return (Criteria) this;
        }

        public Criteria andSnIsNotNull() {
            addCriterion("sn is not null");
            return (Criteria) this;
        }

        public Criteria andSnEqualTo(String value) {
            addCriterion("sn =", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotEqualTo(String value) {
            addCriterion("sn <>", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnGreaterThan(String value) {
            addCriterion("sn >", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnGreaterThanOrEqualTo(String value) {
            addCriterion("sn >=", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnLessThan(String value) {
            addCriterion("sn <", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnLessThanOrEqualTo(String value) {
            addCriterion("sn <=", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnLike(String value) {
            addCriterion("sn like", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotLike(String value) {
            addCriterion("sn not like", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnIn(List<String> values) {
            addCriterion("sn in", values, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotIn(List<String> values) {
            addCriterion("sn not in", values, "sn");
            return (Criteria) this;
        }

        public Criteria andSnBetween(String value1, String value2) {
            addCriterion("sn between", value1, value2, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotBetween(String value1, String value2) {
            addCriterion("sn not between", value1, value2, "sn");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodIsNull() {
            addCriterion("account_period is null");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodIsNotNull() {
            addCriterion("account_period is not null");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodEqualTo(Timestamp value) {
            addCriterion("account_period =", value, "accountPeriod");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodNotEqualTo(Timestamp value) {
            addCriterion("account_period <>", value, "accountPeriod");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodGreaterThan(Timestamp value) {
            addCriterion("account_period >", value, "accountPeriod");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("account_period >=", value, "accountPeriod");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodLessThan(Timestamp value) {
            addCriterion("account_period <", value, "accountPeriod");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodLessThanOrEqualTo(Timestamp value) {
            addCriterion("account_period <=", value, "accountPeriod");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodIn(List<Timestamp> values) {
            addCriterion("account_period in", values, "accountPeriod");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodNotIn(List<Timestamp> values) {
            addCriterion("account_period not in", values, "accountPeriod");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodBetween(Timestamp value1, Timestamp value2) {
            addCriterion("account_period between", value1, value2, "accountPeriod");
            return (Criteria) this;
        }

        public Criteria andAccountPeriodNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("account_period not between", value1, value2, "accountPeriod");
            return (Criteria) this;
        }

        public Criteria andArrivalTimeIsNull() {
            addCriterion("arrival_time is null");
            return (Criteria) this;
        }

        public Criteria andArrivalTimeIsNotNull() {
            addCriterion("arrival_time is not null");
            return (Criteria) this;
        }

        public Criteria andArrivalTimeEqualTo(Timestamp value) {
            addCriterion("arrival_time =", value, "arrivalTime");
            return (Criteria) this;
        }

        public Criteria andArrivalTimeNotEqualTo(Timestamp value) {
            addCriterion("arrival_time <>", value, "arrivalTime");
            return (Criteria) this;
        }

        public Criteria andArrivalTimeGreaterThan(Timestamp value) {
            addCriterion("arrival_time >", value, "arrivalTime");
            return (Criteria) this;
        }

        public Criteria andArrivalTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("arrival_time >=", value, "arrivalTime");
            return (Criteria) this;
        }

        public Criteria andArrivalTimeLessThan(Timestamp value) {
            addCriterion("arrival_time <", value, "arrivalTime");
            return (Criteria) this;
        }

        public Criteria andArrivalTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("arrival_time <=", value, "arrivalTime");
            return (Criteria) this;
        }

        public Criteria andArrivalTimeIn(List<Timestamp> values) {
            addCriterion("arrival_time in", values, "arrivalTime");
            return (Criteria) this;
        }

        public Criteria andArrivalTimeNotIn(List<Timestamp> values) {
            addCriterion("arrival_time not in", values, "arrivalTime");
            return (Criteria) this;
        }

        public Criteria andArrivalTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("arrival_time between", value1, value2, "arrivalTime");
            return (Criteria) this;
        }

        public Criteria andArrivalTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("arrival_time not between", value1, value2, "arrivalTime");
            return (Criteria) this;
        }

        public Criteria andFailStepIsNull() {
            addCriterion("fail_step is null");
            return (Criteria) this;
        }

        public Criteria andFailStepIsNotNull() {
            addCriterion("fail_step is not null");
            return (Criteria) this;
        }

        public Criteria andFailStepEqualTo(String value) {
            addCriterion("fail_step =", value, "failStep");
            return (Criteria) this;
        }

        public Criteria andFailStepNotEqualTo(String value) {
            addCriterion("fail_step <>", value, "failStep");
            return (Criteria) this;
        }

        public Criteria andFailStepGreaterThan(String value) {
            addCriterion("fail_step >", value, "failStep");
            return (Criteria) this;
        }

        public Criteria andFailStepGreaterThanOrEqualTo(String value) {
            addCriterion("fail_step >=", value, "failStep");
            return (Criteria) this;
        }

        public Criteria andFailStepLessThan(String value) {
            addCriterion("fail_step <", value, "failStep");
            return (Criteria) this;
        }

        public Criteria andFailStepLessThanOrEqualTo(String value) {
            addCriterion("fail_step <=", value, "failStep");
            return (Criteria) this;
        }

        public Criteria andFailStepLike(String value) {
            addCriterion("fail_step like", value, "failStep");
            return (Criteria) this;
        }

        public Criteria andFailStepNotLike(String value) {
            addCriterion("fail_step not like", value, "failStep");
            return (Criteria) this;
        }

        public Criteria andFailStepIn(List<String> values) {
            addCriterion("fail_step in", values, "failStep");
            return (Criteria) this;
        }

        public Criteria andFailStepNotIn(List<String> values) {
            addCriterion("fail_step not in", values, "failStep");
            return (Criteria) this;
        }

        public Criteria andFailStepBetween(String value1, String value2) {
            addCriterion("fail_step between", value1, value2, "failStep");
            return (Criteria) this;
        }

        public Criteria andFailStepNotBetween(String value1, String value2) {
            addCriterion("fail_step not between", value1, value2, "failStep");
            return (Criteria) this;
        }

        public Criteria andFailCodeIsNull() {
            addCriterion("fail_code is null");
            return (Criteria) this;
        }

        public Criteria andFailCodeIsNotNull() {
            addCriterion("fail_code is not null");
            return (Criteria) this;
        }

        public Criteria andFailCodeEqualTo(String value) {
            addCriterion("fail_code =", value, "failCode");
            return (Criteria) this;
        }

        public Criteria andFailCodeNotEqualTo(String value) {
            addCriterion("fail_code <>", value, "failCode");
            return (Criteria) this;
        }

        public Criteria andFailCodeGreaterThan(String value) {
            addCriterion("fail_code >", value, "failCode");
            return (Criteria) this;
        }

        public Criteria andFailCodeGreaterThanOrEqualTo(String value) {
            addCriterion("fail_code >=", value, "failCode");
            return (Criteria) this;
        }

        public Criteria andFailCodeLessThan(String value) {
            addCriterion("fail_code <", value, "failCode");
            return (Criteria) this;
        }

        public Criteria andFailCodeLessThanOrEqualTo(String value) {
            addCriterion("fail_code <=", value, "failCode");
            return (Criteria) this;
        }

        public Criteria andFailCodeLike(String value) {
            addCriterion("fail_code like", value, "failCode");
            return (Criteria) this;
        }

        public Criteria andFailCodeNotLike(String value) {
            addCriterion("fail_code not like", value, "failCode");
            return (Criteria) this;
        }

        public Criteria andFailCodeIn(List<String> values) {
            addCriterion("fail_code in", values, "failCode");
            return (Criteria) this;
        }

        public Criteria andFailCodeNotIn(List<String> values) {
            addCriterion("fail_code not in", values, "failCode");
            return (Criteria) this;
        }

        public Criteria andFailCodeBetween(String value1, String value2) {
            addCriterion("fail_code between", value1, value2, "failCode");
            return (Criteria) this;
        }

        public Criteria andFailCodeNotBetween(String value1, String value2) {
            addCriterion("fail_code not between", value1, value2, "failCode");
            return (Criteria) this;
        }

        public Criteria andFailDateIsNull() {
            addCriterion("fail_date is null");
            return (Criteria) this;
        }

        public Criteria andFailDateIsNotNull() {
            addCriterion("fail_date is not null");
            return (Criteria) this;
        }

        public Criteria andFailDateEqualTo(Timestamp value) {
            addCriterion("fail_date =", value, "failDate");
            return (Criteria) this;
        }

        public Criteria andFailDateNotEqualTo(Timestamp value) {
            addCriterion("fail_date <>", value, "failDate");
            return (Criteria) this;
        }

        public Criteria andFailDateGreaterThan(Timestamp value) {
            addCriterion("fail_date >", value, "failDate");
            return (Criteria) this;
        }

        public Criteria andFailDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("fail_date >=", value, "failDate");
            return (Criteria) this;
        }

        public Criteria andFailDateLessThan(Timestamp value) {
            addCriterion("fail_date <", value, "failDate");
            return (Criteria) this;
        }

        public Criteria andFailDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("fail_date <=", value, "failDate");
            return (Criteria) this;
        }

        public Criteria andFailDateIn(List<Timestamp> values) {
            addCriterion("fail_date in", values, "failDate");
            return (Criteria) this;
        }

        public Criteria andFailDateNotIn(List<Timestamp> values) {
            addCriterion("fail_date not in", values, "failDate");
            return (Criteria) this;
        }

        public Criteria andFailDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("fail_date between", value1, value2, "failDate");
            return (Criteria) this;
        }

        public Criteria andFailDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("fail_date not between", value1, value2, "failDate");
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