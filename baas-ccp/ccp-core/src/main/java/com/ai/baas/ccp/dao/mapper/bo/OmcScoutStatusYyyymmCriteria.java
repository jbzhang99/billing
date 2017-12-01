package com.ai.baas.ccp.dao.mapper.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OmcScoutStatusYyyymmCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public OmcScoutStatusYyyymmCriteria() {
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

        public Criteria andScoSeqIsNull() {
            addCriterion("sco_seq is null");
            return (Criteria) this;
        }

        public Criteria andScoSeqIsNotNull() {
            addCriterion("sco_seq is not null");
            return (Criteria) this;
        }

        public Criteria andScoSeqEqualTo(Long value) {
            addCriterion("sco_seq =", value, "scoSeq");
            return (Criteria) this;
        }

        public Criteria andScoSeqNotEqualTo(Long value) {
            addCriterion("sco_seq <>", value, "scoSeq");
            return (Criteria) this;
        }

        public Criteria andScoSeqGreaterThan(Long value) {
            addCriterion("sco_seq >", value, "scoSeq");
            return (Criteria) this;
        }

        public Criteria andScoSeqGreaterThanOrEqualTo(Long value) {
            addCriterion("sco_seq >=", value, "scoSeq");
            return (Criteria) this;
        }

        public Criteria andScoSeqLessThan(Long value) {
            addCriterion("sco_seq <", value, "scoSeq");
            return (Criteria) this;
        }

        public Criteria andScoSeqLessThanOrEqualTo(Long value) {
            addCriterion("sco_seq <=", value, "scoSeq");
            return (Criteria) this;
        }

        public Criteria andScoSeqIn(List<Long> values) {
            addCriterion("sco_seq in", values, "scoSeq");
            return (Criteria) this;
        }

        public Criteria andScoSeqNotIn(List<Long> values) {
            addCriterion("sco_seq not in", values, "scoSeq");
            return (Criteria) this;
        }

        public Criteria andScoSeqBetween(Long value1, Long value2) {
            addCriterion("sco_seq between", value1, value2, "scoSeq");
            return (Criteria) this;
        }

        public Criteria andScoSeqNotBetween(Long value1, Long value2) {
            addCriterion("sco_seq not between", value1, value2, "scoSeq");
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

        public Criteria andSystemIdIsNull() {
            addCriterion("system_id is null");
            return (Criteria) this;
        }

        public Criteria andSystemIdIsNotNull() {
            addCriterion("system_id is not null");
            return (Criteria) this;
        }

        public Criteria andSystemIdEqualTo(String value) {
            addCriterion("system_id =", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdNotEqualTo(String value) {
            addCriterion("system_id <>", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdGreaterThan(String value) {
            addCriterion("system_id >", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdGreaterThanOrEqualTo(String value) {
            addCriterion("system_id >=", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdLessThan(String value) {
            addCriterion("system_id <", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdLessThanOrEqualTo(String value) {
            addCriterion("system_id <=", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdLike(String value) {
            addCriterion("system_id like", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdNotLike(String value) {
            addCriterion("system_id not like", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdIn(List<String> values) {
            addCriterion("system_id in", values, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdNotIn(List<String> values) {
            addCriterion("system_id not in", values, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdBetween(String value1, String value2) {
            addCriterion("system_id between", value1, value2, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdNotBetween(String value1, String value2) {
            addCriterion("system_id not between", value1, value2, "systemId");
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

        public Criteria andAcctIdIsNull() {
            addCriterion("acct_id is null");
            return (Criteria) this;
        }

        public Criteria andAcctIdIsNotNull() {
            addCriterion("acct_id is not null");
            return (Criteria) this;
        }

        public Criteria andAcctIdEqualTo(String value) {
            addCriterion("acct_id =", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdNotEqualTo(String value) {
            addCriterion("acct_id <>", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdGreaterThan(String value) {
            addCriterion("acct_id >", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdGreaterThanOrEqualTo(String value) {
            addCriterion("acct_id >=", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdLessThan(String value) {
            addCriterion("acct_id <", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdLessThanOrEqualTo(String value) {
            addCriterion("acct_id <=", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdLike(String value) {
            addCriterion("acct_id like", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdNotLike(String value) {
            addCriterion("acct_id not like", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdIn(List<String> values) {
            addCriterion("acct_id in", values, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdNotIn(List<String> values) {
            addCriterion("acct_id not in", values, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdBetween(String value1, String value2) {
            addCriterion("acct_id between", value1, value2, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdNotBetween(String value1, String value2) {
            addCriterion("acct_id not between", value1, value2, "acctId");
            return (Criteria) this;
        }

        public Criteria andCustIdIsNull() {
            addCriterion("cust_id is null");
            return (Criteria) this;
        }

        public Criteria andCustIdIsNotNull() {
            addCriterion("cust_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustIdEqualTo(String value) {
            addCriterion("cust_id =", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotEqualTo(String value) {
            addCriterion("cust_id <>", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdGreaterThan(String value) {
            addCriterion("cust_id >", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdGreaterThanOrEqualTo(String value) {
            addCriterion("cust_id >=", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdLessThan(String value) {
            addCriterion("cust_id <", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdLessThanOrEqualTo(String value) {
            addCriterion("cust_id <=", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdLike(String value) {
            addCriterion("cust_id like", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotLike(String value) {
            addCriterion("cust_id not like", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdIn(List<String> values) {
            addCriterion("cust_id in", values, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotIn(List<String> values) {
            addCriterion("cust_id not in", values, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdBetween(String value1, String value2) {
            addCriterion("cust_id between", value1, value2, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotBetween(String value1, String value2) {
            addCriterion("cust_id not between", value1, value2, "custId");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeIsNull() {
            addCriterion("business_code is null");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeIsNotNull() {
            addCriterion("business_code is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeEqualTo(String value) {
            addCriterion("business_code =", value, "businessCode");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeNotEqualTo(String value) {
            addCriterion("business_code <>", value, "businessCode");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeGreaterThan(String value) {
            addCriterion("business_code >", value, "businessCode");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeGreaterThanOrEqualTo(String value) {
            addCriterion("business_code >=", value, "businessCode");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeLessThan(String value) {
            addCriterion("business_code <", value, "businessCode");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeLessThanOrEqualTo(String value) {
            addCriterion("business_code <=", value, "businessCode");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeLike(String value) {
            addCriterion("business_code like", value, "businessCode");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeNotLike(String value) {
            addCriterion("business_code not like", value, "businessCode");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeIn(List<String> values) {
            addCriterion("business_code in", values, "businessCode");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeNotIn(List<String> values) {
            addCriterion("business_code not in", values, "businessCode");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeBetween(String value1, String value2) {
            addCriterion("business_code between", value1, value2, "businessCode");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeNotBetween(String value1, String value2) {
            addCriterion("business_code not between", value1, value2, "businessCode");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andLastStatusIsNull() {
            addCriterion("last_status is null");
            return (Criteria) this;
        }

        public Criteria andLastStatusIsNotNull() {
            addCriterion("last_status is not null");
            return (Criteria) this;
        }

        public Criteria andLastStatusEqualTo(String value) {
            addCriterion("last_status =", value, "lastStatus");
            return (Criteria) this;
        }

        public Criteria andLastStatusNotEqualTo(String value) {
            addCriterion("last_status <>", value, "lastStatus");
            return (Criteria) this;
        }

        public Criteria andLastStatusGreaterThan(String value) {
            addCriterion("last_status >", value, "lastStatus");
            return (Criteria) this;
        }

        public Criteria andLastStatusGreaterThanOrEqualTo(String value) {
            addCriterion("last_status >=", value, "lastStatus");
            return (Criteria) this;
        }

        public Criteria andLastStatusLessThan(String value) {
            addCriterion("last_status <", value, "lastStatus");
            return (Criteria) this;
        }

        public Criteria andLastStatusLessThanOrEqualTo(String value) {
            addCriterion("last_status <=", value, "lastStatus");
            return (Criteria) this;
        }

        public Criteria andLastStatusLike(String value) {
            addCriterion("last_status like", value, "lastStatus");
            return (Criteria) this;
        }

        public Criteria andLastStatusNotLike(String value) {
            addCriterion("last_status not like", value, "lastStatus");
            return (Criteria) this;
        }

        public Criteria andLastStatusIn(List<String> values) {
            addCriterion("last_status in", values, "lastStatus");
            return (Criteria) this;
        }

        public Criteria andLastStatusNotIn(List<String> values) {
            addCriterion("last_status not in", values, "lastStatus");
            return (Criteria) this;
        }

        public Criteria andLastStatusBetween(String value1, String value2) {
            addCriterion("last_status between", value1, value2, "lastStatus");
            return (Criteria) this;
        }

        public Criteria andLastStatusNotBetween(String value1, String value2) {
            addCriterion("last_status not between", value1, value2, "lastStatus");
            return (Criteria) this;
        }

        public Criteria andStatusTimeIsNull() {
            addCriterion("status_time is null");
            return (Criteria) this;
        }

        public Criteria andStatusTimeIsNotNull() {
            addCriterion("status_time is not null");
            return (Criteria) this;
        }

        public Criteria andStatusTimeEqualTo(Timestamp value) {
            addCriterion("status_time =", value, "statusTime");
            return (Criteria) this;
        }

        public Criteria andStatusTimeNotEqualTo(Timestamp value) {
            addCriterion("status_time <>", value, "statusTime");
            return (Criteria) this;
        }

        public Criteria andStatusTimeGreaterThan(Timestamp value) {
            addCriterion("status_time >", value, "statusTime");
            return (Criteria) this;
        }

        public Criteria andStatusTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("status_time >=", value, "statusTime");
            return (Criteria) this;
        }

        public Criteria andStatusTimeLessThan(Timestamp value) {
            addCriterion("status_time <", value, "statusTime");
            return (Criteria) this;
        }

        public Criteria andStatusTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("status_time <=", value, "statusTime");
            return (Criteria) this;
        }

        public Criteria andStatusTimeIn(List<Timestamp> values) {
            addCriterion("status_time in", values, "statusTime");
            return (Criteria) this;
        }

        public Criteria andStatusTimeNotIn(List<Timestamp> values) {
            addCriterion("status_time not in", values, "statusTime");
            return (Criteria) this;
        }

        public Criteria andStatusTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("status_time between", value1, value2, "statusTime");
            return (Criteria) this;
        }

        public Criteria andStatusTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("status_time not between", value1, value2, "statusTime");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeIsNull() {
            addCriterion("notify_time is null");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeIsNotNull() {
            addCriterion("notify_time is not null");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeEqualTo(Timestamp value) {
            addCriterion("notify_time =", value, "notifyTime");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeNotEqualTo(Timestamp value) {
            addCriterion("notify_time <>", value, "notifyTime");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeGreaterThan(Timestamp value) {
            addCriterion("notify_time >", value, "notifyTime");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("notify_time >=", value, "notifyTime");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeLessThan(Timestamp value) {
            addCriterion("notify_time <", value, "notifyTime");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("notify_time <=", value, "notifyTime");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeIn(List<Timestamp> values) {
            addCriterion("notify_time in", values, "notifyTime");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeNotIn(List<Timestamp> values) {
            addCriterion("notify_time not in", values, "notifyTime");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("notify_time between", value1, value2, "notifyTime");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("notify_time not between", value1, value2, "notifyTime");
            return (Criteria) this;
        }

        public Criteria andNotifyTimesIsNull() {
            addCriterion("notify_times is null");
            return (Criteria) this;
        }

        public Criteria andNotifyTimesIsNotNull() {
            addCriterion("notify_times is not null");
            return (Criteria) this;
        }

        public Criteria andNotifyTimesEqualTo(Integer value) {
            addCriterion("notify_times =", value, "notifyTimes");
            return (Criteria) this;
        }

        public Criteria andNotifyTimesNotEqualTo(Integer value) {
            addCriterion("notify_times <>", value, "notifyTimes");
            return (Criteria) this;
        }

        public Criteria andNotifyTimesGreaterThan(Integer value) {
            addCriterion("notify_times >", value, "notifyTimes");
            return (Criteria) this;
        }

        public Criteria andNotifyTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("notify_times >=", value, "notifyTimes");
            return (Criteria) this;
        }

        public Criteria andNotifyTimesLessThan(Integer value) {
            addCriterion("notify_times <", value, "notifyTimes");
            return (Criteria) this;
        }

        public Criteria andNotifyTimesLessThanOrEqualTo(Integer value) {
            addCriterion("notify_times <=", value, "notifyTimes");
            return (Criteria) this;
        }

        public Criteria andNotifyTimesIn(List<Integer> values) {
            addCriterion("notify_times in", values, "notifyTimes");
            return (Criteria) this;
        }

        public Criteria andNotifyTimesNotIn(List<Integer> values) {
            addCriterion("notify_times not in", values, "notifyTimes");
            return (Criteria) this;
        }

        public Criteria andNotifyTimesBetween(Integer value1, Integer value2) {
            addCriterion("notify_times between", value1, value2, "notifyTimes");
            return (Criteria) this;
        }

        public Criteria andNotifyTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("notify_times not between", value1, value2, "notifyTimes");
            return (Criteria) this;
        }

        public Criteria andNotifyStatusIsNull() {
            addCriterion("notify_status is null");
            return (Criteria) this;
        }

        public Criteria andNotifyStatusIsNotNull() {
            addCriterion("notify_status is not null");
            return (Criteria) this;
        }

        public Criteria andNotifyStatusEqualTo(String value) {
            addCriterion("notify_status =", value, "notifyStatus");
            return (Criteria) this;
        }

        public Criteria andNotifyStatusNotEqualTo(String value) {
            addCriterion("notify_status <>", value, "notifyStatus");
            return (Criteria) this;
        }

        public Criteria andNotifyStatusGreaterThan(String value) {
            addCriterion("notify_status >", value, "notifyStatus");
            return (Criteria) this;
        }

        public Criteria andNotifyStatusGreaterThanOrEqualTo(String value) {
            addCriterion("notify_status >=", value, "notifyStatus");
            return (Criteria) this;
        }

        public Criteria andNotifyStatusLessThan(String value) {
            addCriterion("notify_status <", value, "notifyStatus");
            return (Criteria) this;
        }

        public Criteria andNotifyStatusLessThanOrEqualTo(String value) {
            addCriterion("notify_status <=", value, "notifyStatus");
            return (Criteria) this;
        }

        public Criteria andNotifyStatusLike(String value) {
            addCriterion("notify_status like", value, "notifyStatus");
            return (Criteria) this;
        }

        public Criteria andNotifyStatusNotLike(String value) {
            addCriterion("notify_status not like", value, "notifyStatus");
            return (Criteria) this;
        }

        public Criteria andNotifyStatusIn(List<String> values) {
            addCriterion("notify_status in", values, "notifyStatus");
            return (Criteria) this;
        }

        public Criteria andNotifyStatusNotIn(List<String> values) {
            addCriterion("notify_status not in", values, "notifyStatus");
            return (Criteria) this;
        }

        public Criteria andNotifyStatusBetween(String value1, String value2) {
            addCriterion("notify_status between", value1, value2, "notifyStatus");
            return (Criteria) this;
        }

        public Criteria andNotifyStatusNotBetween(String value1, String value2) {
            addCriterion("notify_status not between", value1, value2, "notifyStatus");
            return (Criteria) this;
        }

        public Criteria andNotifyTypeIsNull() {
            addCriterion("notify_type is null");
            return (Criteria) this;
        }

        public Criteria andNotifyTypeIsNotNull() {
            addCriterion("notify_type is not null");
            return (Criteria) this;
        }

        public Criteria andNotifyTypeEqualTo(String value) {
            addCriterion("notify_type =", value, "notifyType");
            return (Criteria) this;
        }

        public Criteria andNotifyTypeNotEqualTo(String value) {
            addCriterion("notify_type <>", value, "notifyType");
            return (Criteria) this;
        }

        public Criteria andNotifyTypeGreaterThan(String value) {
            addCriterion("notify_type >", value, "notifyType");
            return (Criteria) this;
        }

        public Criteria andNotifyTypeGreaterThanOrEqualTo(String value) {
            addCriterion("notify_type >=", value, "notifyType");
            return (Criteria) this;
        }

        public Criteria andNotifyTypeLessThan(String value) {
            addCriterion("notify_type <", value, "notifyType");
            return (Criteria) this;
        }

        public Criteria andNotifyTypeLessThanOrEqualTo(String value) {
            addCriterion("notify_type <=", value, "notifyType");
            return (Criteria) this;
        }

        public Criteria andNotifyTypeLike(String value) {
            addCriterion("notify_type like", value, "notifyType");
            return (Criteria) this;
        }

        public Criteria andNotifyTypeNotLike(String value) {
            addCriterion("notify_type not like", value, "notifyType");
            return (Criteria) this;
        }

        public Criteria andNotifyTypeIn(List<String> values) {
            addCriterion("notify_type in", values, "notifyType");
            return (Criteria) this;
        }

        public Criteria andNotifyTypeNotIn(List<String> values) {
            addCriterion("notify_type not in", values, "notifyType");
            return (Criteria) this;
        }

        public Criteria andNotifyTypeBetween(String value1, String value2) {
            addCriterion("notify_type between", value1, value2, "notifyType");
            return (Criteria) this;
        }

        public Criteria andNotifyTypeNotBetween(String value1, String value2) {
            addCriterion("notify_type not between", value1, value2, "notifyType");
            return (Criteria) this;
        }

        public Criteria andScoutInfoIsNull() {
            addCriterion("scout_info is null");
            return (Criteria) this;
        }

        public Criteria andScoutInfoIsNotNull() {
            addCriterion("scout_info is not null");
            return (Criteria) this;
        }

        public Criteria andScoutInfoEqualTo(String value) {
            addCriterion("scout_info =", value, "scoutInfo");
            return (Criteria) this;
        }

        public Criteria andScoutInfoNotEqualTo(String value) {
            addCriterion("scout_info <>", value, "scoutInfo");
            return (Criteria) this;
        }

        public Criteria andScoutInfoGreaterThan(String value) {
            addCriterion("scout_info >", value, "scoutInfo");
            return (Criteria) this;
        }

        public Criteria andScoutInfoGreaterThanOrEqualTo(String value) {
            addCriterion("scout_info >=", value, "scoutInfo");
            return (Criteria) this;
        }

        public Criteria andScoutInfoLessThan(String value) {
            addCriterion("scout_info <", value, "scoutInfo");
            return (Criteria) this;
        }

        public Criteria andScoutInfoLessThanOrEqualTo(String value) {
            addCriterion("scout_info <=", value, "scoutInfo");
            return (Criteria) this;
        }

        public Criteria andScoutInfoLike(String value) {
            addCriterion("scout_info like", value, "scoutInfo");
            return (Criteria) this;
        }

        public Criteria andScoutInfoNotLike(String value) {
            addCriterion("scout_info not like", value, "scoutInfo");
            return (Criteria) this;
        }

        public Criteria andScoutInfoIn(List<String> values) {
            addCriterion("scout_info in", values, "scoutInfo");
            return (Criteria) this;
        }

        public Criteria andScoutInfoNotIn(List<String> values) {
            addCriterion("scout_info not in", values, "scoutInfo");
            return (Criteria) this;
        }

        public Criteria andScoutInfoBetween(String value1, String value2) {
            addCriterion("scout_info between", value1, value2, "scoutInfo");
            return (Criteria) this;
        }

        public Criteria andScoutInfoNotBetween(String value1, String value2) {
            addCriterion("scout_info not between", value1, value2, "scoutInfo");
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